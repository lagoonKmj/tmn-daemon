package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapter5150;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapter5410;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapterQopas;

import subkjh.bas.co.log.Logger;

public class Ciena extends VendorBaseParser {

	@Override
	public ConfAdapter makeAdapter(IDcn dcn) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case CienaService.MODEL_QOPAS:
				return new CienaConfAdapterQopas(equip, this);
			case CienaService.MODEL_5410:
				return new CienaConfAdapter5410(equip, this);
			case CienaService.MODEL_CESD_5150:
				return new CienaConfAdapter5150(equip, this);
			default:
				Logger.logger.info("NOT FOUND MODEL : {}", equip.getEquipMdlCd());
			}
		}

		return null;
	}

	@Override
	public AlarmAdapter makeAlarmAdapter(IDcn dcn, GwDcnMngThread thread) {

//		if (dcn instanceof EquipDcn) {
//			EquipDcn equip = (EquipDcn) dcn;
//			switch (equip.getEquipMdlCd()) {
//			case CienaService.MODEL_QOPAS:
//				return new CienaAlarmAdapterQopas(equip, thread, this);
//			case CienaService.MODEL_5410:
//				return new CienaAlarmAdapter5410(equip, thread, this);
//			case CienaService.MODEL_CESD_5150:
//				return  null; // TrapService 로 장애 받음
//			default:
//				Logger.logger.info("NOT FOUND MODEL : {}", equip.getEquipMdlCd());
//			}			
//		} TODO 시에나 동시 접속 문제로 인해 커밋

		return null;
	}

	@Override
	public List<DetectedAlarmVo> parseAlarms(IDcn dcn, List<String> dataList,
			String tid) {

		List<DetectedAlarmVo> alarmList = new ArrayList<DetectedAlarmVo>();

		for (String line : dataList) {
			Map<String, String> map = new HashMap<String, String>();

			DetectedAlarmVo alarm = parseAlarm(dcn, line, tid);
			
			if (alarm != null) {
				if (map.get(line) == null) {
					alarmList.add(alarm);
					map.put(line, line);
				} else {
					Logger.logger.trace("Dup String : {}", line);
				}
			}
		}

		return alarmList;
	}
	
	@Override
	public DetectedAlarmVo parseAlarm(IDcn dcn, String line, String tid) {
		
		EquipDcn equip = (EquipDcn) dcn;
		DetectedAlarmVo alarm = null;
		
		switch (equip.getEquipMdlCd()) {
		case CienaService.MODEL_QOPAS:
			alarm = parseAlarmByQOPAS(dcn, line, tid);
			break;
		case CienaService.MODEL_5410:
			alarm = parseAlarmBy5410(dcn, line, tid);
			break;
		case CienaService.MODEL_CESD_5150:
			alarm = parseAlarmBy5150(dcn, line, tid);
			break;
		}			
		return alarm;
	}

	private DetectedAlarmVo parseAlarmByQOPAS(IDcn dcn, String line, String tid) {
		/**
		 * <pre>
		 * -------- 장애 메시지(장애 수신)----------
		 * ^MALM Detect  CR  OTRU4   P4-W      QOPAS     LOS            2019-07-18 02:06:23
		 * ^MALM Detect  CR  OTRU4   P4-S      QOPAS     LOS            2019-07-18 02:06:23
		 * ^MALM Detect  CR  OTRU2   P2-W      QOPAS     LOS            2019-07-18 02:06:23
		 * ^M
		 * ^MALM Release CR  OTRU12  P1-W      QOPAS     LOS            2019-07-18 02:06:26
		 * ^M
		 * --------------------------------
		 * 
		 * -------- 장애 메시지(현재 장애 조회, 아래 샘플은 현재 장애가 존재하지 않아, 장애이력을 조회한 값이다.)----------
		 * OPAS [2] >RTRV-ALM:QOPAS-ChuncheonB-1::1;
		 * Slot    Port      Sev  UnitType  Reason              Date
		 * --------------------------------------------------------------------------- 
		 * OTRU2 P3-SCR  QOPAS RX_POWER        2019-10-10 02:14:52
		 * OTRU2 P3-SCR  QOPAS LOS             2019-10-10 02:14:52
		 * OTRU2 P4-SMN  QOPAS RX_POWER_ALERT  2019-10-10 02:14:52
		 * OTRU2 P4-SCR  QOPAS RX_POWER        2019-10-10 02:14:52
		 * OTRU2 P4-SCR  QOPAS LOS             2019-10-10 02:14:52
		 * 
		 * -------------------------------- 
		 * </pre>
		 */
		
		List<String> list = split(line, " ");
		if (list == null) {
			return null;
		}

		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid);
		if (list.size() == 9) { // 실시간
			alarm.setReason(list.get(6));
			String location = list.get(3) + "-" + list.get(4);
			alarm.setLocation(location);
			alarm.setPortDescr(location);
			String hstime = list.get(7) + " " + list.get(8);
			if (list.get(1).equals("Release")) {
				alarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
				alarm.setClearHstime(hstime);
			} else {
				alarm.setDablGrCd(getDablGrCd(list.get(2)));
				alarm.setOccurHstime(hstime);
			}
		} else if (list.size() == 6) { // 요청
			String temp = list.get(1);
			int index = temp.indexOf("CR");
			if (index < 0) {
				index = temp.indexOf("MJ");
			}
			if (index < 0) {
				index = temp.indexOf("MN");
			}
			if (index < 0) {
				index = temp.indexOf("WN");
			}
			if (index > -1) {
				String port = temp.substring(0, index);
				String sev = temp.substring(index, index + 2);
				alarm.setReason(list.get(3));
				String location = list.get(0);
				if (port.length() > 0) {
					location += "-" + port;
				}
				alarm.setLocation(location);
				alarm.setPortDescr(location);
				alarm.setDablGrCd(getDablGrCd(sev));
				String hstime = list.get(4) + " " + list.get(5);
				alarm.setOccurHstime(hstime);
			} else {
				Logger.logger.info("FAIL TO PARSE : {}", line);
				return null;
			}
		} else {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}
		alarm.setFullMsg(line);

		return alarm;
	}

	private DetectedAlarmVo parseAlarmBy5410(IDcn dcn, String line, String tid) {
		/**
		 * <pre>
		 * -------- 장애 메시지 ----------
		 * "NU2546/47_HyupJae#2" 12-07-15 14:55:09
		 * *  0000122252 REPT ALM ETH100
		 *    "ETH100-1-1-1:MN,BER_SD,SA,07-15,14-55-08,NEND,RCV:\"Signal Degrade\",NONE:0100022107-0003-0822,:YEAR=2012,MODE=NONE"
		 * ;
		 * 
		 *
		 * 현재 장애 조회 메세지,,(TODO 데이터 형식이 다름,, 실시간 확인 필요!)
		 * RTRV-ALM-ALL:::2;^M
		 *
   		 * 5410-SokchoB-2 19-11-05 07:54:56^M
		 * M  2 COMPLD^M
   		 * "1-A-2-2,OTU4:MN,LOS,NSA,11-05,06-46-17,,,:\"Loss of Signal\""^M
		 * ;

		 * --------------------------------
		 * </pre>
		 */
		List<String> list = split(line, " \",:");
		if (list == null) {
			return null;
		}
		if (list.size() < 4) {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}

		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid);
		int idx = 0;
		alarm.setLocation(list.get(idx));
		alarm.setPortDescr(list.get(idx));
		// 현재 장애 조회시 데이터 형식이 다르다, 장애 코드 조회후 못찾을시 인덱스를 하나씩 올리자.
		String dablGrCd = getDablGrCd(list.get(++idx));
		if (dablGrCd.equals(DABL_GR_CD.Info.getCode())) {
			dablGrCd = getDablGrCd(list.get(++idx));
		}
		alarm.setDablGrCd(dablGrCd);
		alarm.setReason(list.get(++idx));
		// alarm.setOccurHstime(ss[7]); // TODO 헤더 값을 가져감, 맞음? 확인 해야 함
		alarm.setFullMsg(line);

		return alarm;
	}

	private DetectedAlarmVo parseAlarmBy5150(IDcn dcn, String line, String tid) {

		/**
		 * <pre>
		 * 
		 * 실시간은 TRAP으로 보내준다.
		 * 현재 장애 조회를 처리한다.
		 * 	
		 * |2742 |    |  16 |warning | Mon Jul  1 06:30:18 2019 |               19 | Link Down        |
		 * |2744 |    |  31 |warning | Mon Jul  1 06:30:30 2019 |  CG_JoongAng1_VR |No RAPS PDU Receiv|
		 * |2745 |    |  16 |warning | Mon Jul  1 06:30:33 2019 |                8 | Link Down        |
		 * |2755 |    |  17 |warning | Mon Jul  1 06:31:19 2019 |               19 | Port Flapping    |
		 * --------------------------------
		 * </pre>
		 */
		List<String> list = split(line, "|");
		if (list == null) {
			return null;
		}
		if (list.size() < 7) {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}
		
		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid);
		alarm.setLocation(list.get(5));
		alarm.setPortDescr(list.get(5));
		alarm.setDablGrCd(getDablGrCdBy5150(list.get(3)));
		alarm.setReason(list.get(6));
		alarm.setOccurHstime(getCinea5150Date(list.get(4).trim()));
		alarm.setFullMsg(line);

		return alarm;
	}
	
	/**
	 * 
	 * input : "Mon Jul  1 06:30:18 2019"
	 * 
	 * @param value
	 * @return "20190701063018"
	 */
	private String getCinea5150Date(String value) {

		String strDate = "";
		if (value.length() == 24) {
			List<String> list = VendorBaseParser.split(value, " "); // [Mon, Jul, 1, 06:30:18, 2019]
			strDate += list.get(4);
			strDate += EngMonths.getValue(list.get(1));
			String day = list.get(2);
			if (day.length() > 1) {
				strDate += day;	
			} else {
				strDate += "0" + day;
			}
			strDate += list.get(3).replaceAll(":", "");
		} else {
			strDate = value;
		}
		return strDate;
	}
	
	private String getDablGrCdBy5150(String s) {

		/**
		 * <pre>
		 * 접속 후 장애 등급 조회
		 * 
		 * CG_JoongAng2> alarm show defined-alarms severity <TAB>
		 * critical      major         minor         warning
		 * 
		 * </pre>
		 */
		String str = s.toLowerCase().trim();

		if (str.equals("critical"))
			return DABL_GR_CD.Critical.getCode();
		if (str.equals("major"))
			return DABL_GR_CD.Major.getCode();
		if (str.equals("minor"))
			return DABL_GR_CD.Minor.getCode();
		if (str.equals("warning"))
			return DABL_GR_CD.Warning.getCode();

		return DABL_GR_CD.Info.getCode();
	}
	
	public static void main(String[] args) throws Exception {
		List<String> msg = new ArrayList<String>();
		msg.add("^MALM Detect  CR  OTRU4   P4-W      QOPAS     LOS            2019-07-18 02:06:23");
		msg.add("^MALM Detect  CR  OTRU4   P4-S      QOPAS     LOS            2019-07-18 02:06:23");
		msg.add("^MALM Detect  CR  OTRU2   P2-W      QOPAS     LOS            2019-07-18 02:06:23");
		msg.add("^M");
		msg.add("^MALM Release CR  OTRU12  P1-W      QOPAS     LOS            2019-07-18 02:06:26");
		msg.add("^M");
		msg.add("OTRU2 CR  QOPAS RX_POWER        2019-10-10 02:14:52");
		msg.add("OTRU2 MJ  QOPAS RX_POWER        2019-10-10 02:14:52");
		msg.add("OTRU2 P3-SMJ  QOPAS RX_POWER        2019-10-10 02:14:52");
//		msg.add("|2742 |    |  16 |warning | Mon Jul  1 06:30:18 2019 |               19 | Link Down        |");
//		msg.add("|2744 |    |  31 |warning | Mon Jul  1 06:30:30 2019 |  CG_JoongAng1_VR |No RAPS PDU Receiv|");
//		msg.add("|2745 |    |  16 |warning | Mon Jul  1 06:30:33 2019 |                8 | Link Down        |");
//		msg.add("|2755 |    |  17 |warning | Mon Jul  1 06:31:19 2019 |               19 | Port Flapping    |");

		Ciena co = new Ciena();
		for (int i = 0; i < msg.size(); i++) {
			co.parseAlarmByQOPAS(null, msg.get(i), "1.1.10.172");			
//			co.parseAlarmBy5150(null, msg.get(i), "1.1.10.172");			
		}
	}

}

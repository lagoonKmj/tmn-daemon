package com.lagoon.tmn.teams.gw.vendor.coweaver;

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
import com.lagoon.tmn.teams.gw.vendor.coweaver.adapter.CoweaverAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.coweaver.adapter.CoweaverAlarmAdapter7300;
import com.lagoon.tmn.teams.gw.vendor.coweaver.adapter.CoweaverAlarmAdapter7400;
import com.lagoon.tmn.teams.gw.vendor.coweaver.adapter.CoweaverConfAdapter;

import subkjh.bas.co.log.Logger;

public class Coweaver extends VendorBaseParser {

	@Override
	public ConfAdapter makeAdapter(IDcn dcn) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case CoweaverService.MODEL_UTRANS_7200:
			case CoweaverService.MODEL_UTRANS_7300:
			case CoweaverService.MODEL_UTRANS_7400:
			case CoweaverService.MODEL_UTRANS_7400C:
				return new CoweaverConfAdapter(equip, this);
			default:
				Logger.logger.info("NOT FOUND MODEL : {}", equip.getEquipMdlCd());
			}			
		}

		return null;
	}

	@Override
	public AlarmAdapter makeAlarmAdapter(IDcn dcn, GwDcnMngThread thread) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case CoweaverService.MODEL_UTRANS_7300:
				return new CoweaverAlarmAdapter7300(equip, thread, this);
			case CoweaverService.MODEL_UTRANS_7400:
				return new CoweaverAlarmAdapter7400(equip, thread, this);
			case CoweaverService.MODEL_UTRANS_7200:
			case CoweaverService.MODEL_UTRANS_7400C:
				return new CoweaverAlarmAdapter(equip, thread, this);
			default:
				Logger.logger.info("NOT FOUND MODEL : {}", equip.getEquipMdlCd());
			}
		}

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
		/**
		 * <pre>
		 * -------- 장애 메시지 7200, 7300----------
		 * 1.1.10.172 ** ALARM      MJ  SA    S.3-P1  tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120[p] LSP-LOC          2019-10-14 11:31:24
		 * 1.1.130.62 ** ALARM      MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  11:28:50
		 * 1.1.130.62 ** ALARM Rel MN NSA S.7-P2  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(w) LSP-RDI            2019-10-14  11:28:56
		 * 1.1.10.172 ** ALARM Rel MJ  NSA S.3-P1  tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120[p] LSP-LOC          2019-10-14 11:31:36
		 * 1.1.130.62 ** ALARM Rel MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  11:29:00
		 * -------- 장애 메시지 7400, 7400C---------- * 위 모델과 동일한거 확인
		 * 1.1.130.62  ** ALARM     MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  17:35:10
 	 	 * 1.1.130.62  ** ALARM Rel MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  17:35:24
 	 	 * --------------------------------
 	 	 * 
 	 	 * ------- 장애 메시지 7400 ----------
 	 	 * # show sytem alarm current
 	 	 * ** ALARM     MJ SA  PSU.B                48V-B-FAIL        2016-10-08 17:16:03
 	 	 * ** ALARM     CR SA  MAIN-P1              OPT-REMOVE        2016-10-08 17:16:04
 	 	 * ** ALARM     CR SA  S.1-P1               OPT-REMOVE        2016-10-08 17:25:50
 	 	 * ** ALARM     CR SA  S.1-P2               OPT-REMOVE        2016-10-08 17:25:50
 	 	 * ** ALARM     MJ SA  PSU.B                48V-B-FAIL        2016-10-09 16:44:48
 	 	 * ** ALARM     CR SA  MAIN-P1              OPT-REMOVE        2016-10-09 16:44:49
 	 	 * ** ALARM     CR SA  MAIN-P7              OPT-REMOVE        2016-10-09 16:44:49
 	 	 * --------------------------------
		 * </pre>
		 */
		List<String> list = split(line, " ");
		if (list == null) {
			return null;
		}
		int iSize = list.size();
		if (iSize < 9) {
			Logger.logger.info("FAIL TO PARSE(사이즈 오류) : {}", line);
			return null;
		}

		if (list.get(2).startsWith("ALARM") == false) {
			Logger.logger.info("FAIL TO PARSE(ALARM 문구 없음) : {}", line);
			return null;
		}
		
		if (!tid.equals(list.get(0))) {	// TODO 확인해야함, 다른 장비의 장애도 같이올라와서 TID 가 일치하는 장애만 처리 			
			Logger.logger.debug("TID NOT MATCHED : {}", line);
			return null;
		}

		String dablGrCd = getDablGrCd(list.get(3));
		if (dablGrCd.equals(DABL_GR_CD.Clear.getCode())) { // 릴리즈는 세번째 칸 지워서 위치배열 위치 조정
			list.remove(3);
			iSize = list.size();
		}

		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid); // (list.get(0) + "-S1"); 레거시 소스
		alarm.setDablGrCd(dablGrCd);
		// 배열 위치 를 지정
		int index = 5;
		alarm.setLocation(list.get(index));
		alarm.setPortDescr(list.get(index));

		index = iSize - 3; // 총사이즈에서 앞으로(-3) 하면 Reason
		// if (iSize > 8) {
		// index++;
		// }
		// if (iSize > 9) {
		// index++;
		// }
		// if (iSize > 10) {
		// index++;
		// }
		alarm.setReason(list.get(index));

		String time = list.get(++index) + " " + list.get(++index);
		if (dablGrCd.equals(DABL_GR_CD.Clear.getCode())) {
			alarm.setClearHstime(time);
		} else {
			alarm.setOccurHstime(time);
		}
		alarm.setFullMsg(line);

		return alarm;
	}

	@Override
	protected String getDablGrCd(String s) {
		String str = s.toLowerCase();
		if (str.equals("cr"))
			return DABL_GR_CD.Critical.getCode();
		if (str.equals("mj"))
			return DABL_GR_CD.Major.getCode();
		if (str.equals("mn"))
			return DABL_GR_CD.Minor.getCode();
		if (str.equals("rel"))
			return DABL_GR_CD.Clear.getCode();

		return DABL_GR_CD.Info.getCode();
	}

	public static void main(String[] args) throws Exception {
		
		final String TYPE = "command!"; // realtime
				
		List<String> msg = new ArrayList<String>();
		
		if (TYPE.equals("command")) {
			msg.add("[TID] " + "** ALARM     MJ SA  PSU.B                48V-B-FAIL        2016-10-08 17:16:03");
			msg.add("[TID] " + "** ALARM     CR SA  MAIN-P1              OPT-REMOVE        2016-10-08 17:16:04");
			msg.add("[TID] " + "** ALARM     CR SA  S.1-P1               OPT-REMOVE        2016-10-08 17:25:50");
			msg.add("1.1.130.62 " + "** ALARM     CR SA  S.1-P2               OPT-REMOVE        2016-10-08 17:25:50");
			msg.add("[TID] " + "** ALARM     MJ SA  PSU.B                48V-B-FAIL        2016-10-09 16:44:48");
			msg.add("[TID] " + "** ALARM     CR SA  MAIN-P1              OPT-REMOVE        2016-10-09 16:44:49");
			msg.add("[TID] " + "** ALARM     CR SA  MAIN-P7              OPT-REMOVE        2016-10-09 16:44:49");
		} else {
			msg.add("1.1.10.172 ** ALARM     MJ SA  S.3-P1  tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120[p] LSP-LOC          2019-10-14 11:31:24");
			msg.add("1.1.130.62 ** ALARM     MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  11:28:50");
			msg.add("1.1.130.62 ** ALARM Rel MN NSA S.7-P2  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(w) LSP-RDI            2019-10-14  11:28:56");
			msg.add("1.1.10.172 ** ALARM Rel MJ NSA S.3-P1  tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120[p] LSP-LOC          2019-10-14 11:31:36");
			msg.add("1.1.130.62 ** ALARM Rel MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  11:29:00");
			msg.add("1.1.10.172 ** ALARM Rel MJ NSA S.3-P1  tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120[p] LSP-LOC          2019-05-31 21:30:45");
			msg.add("1.1.12.35 ** ALARM Rel MJ NSA SYSTEM               UNIT-HIGHTEMP     2019-06-13 14:24:04");
			msg.add("1.1.10.172 ** ALARM     CR SA  S.2-P4               OPT-LOS           2019-05-31 17:02:19");
			msg.add("1.1.130.62  ** ALARM     MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  17:35:10");
			msg.add("1.1.130.62  ** ALARM Rel MN NSA S.6-P1  TNL tp_1521_SJ_EDU_1_SKT_Jochiwon2_4_1_0120(p) LSP-RDI            2019-10-14  17:35:24");
		}

		Coweaver co = new Coweaver();

		for (String line : msg) {
			co.parseAlarm(null, line, "1.1.130.62");
		}
	}
}

package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.vendor.woorinet.adapter.WoorinetAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.adapter.WoorinetConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.adapter.WoorinetConfPsAdapterOpn3000;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.vo.AM;
import fxms.nms.co.tl1_2.vo.ORMF_HEADER;

public class Woorinet extends VendorBaseParser {

	public ConfAdapter makeAdapter(IDcn dcn) {
		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			switch (ems.getEmsTypVal()) {
			case WoorinetService.WOORINET_PTN_EMS:
				return new WoorinetConfAdapter(ems, this);
			default:
				Logger.logger.info("NOT FOUND EMS : {}", ems.getEmsTypVal());
			}

		}
		return null;
	}

	public ConfAdapter makeAdapterOpn3000(IDcn dcn) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case WoorinetService.MODEL_OPN_3000:
				return new WoorinetConfPsAdapterOpn3000(equip, this);
			default:
				Logger.logger.info("NOT FOUND MODEL : {}",
						equip.getEquipMdlCd());
			}
		}

		return null;
	}

	public AlarmAdapter makeAlarmAdapter(IDcn dcn, GwDcnMngThread thread) {

		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			switch (ems.getEmsTypVal()) {
			case WoorinetService.WOORINET_PTN_EMS:
				return new WoorinetAlarmAdapter(ems, thread, this);
			default:
				Logger.logger.info("NOT FOUND EMS : {}", ems.getEmsTypVal());
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
		 * -------- 장애 메시지 (실시간) ----------
		 *    WNO3000_▒ξ▒T01 2019-10-25 16:01:36
		 * A  5185 REPT ALM
		 *    \/* AID,NAME,UNIT,REASON,SEV,SA,DATETIME *\/
		 *    "S07-P1,---,OMX24U,LINK_DOWN,CR,NSA,2019-10-25 16:01:36"
		 * ;
		 * --------------------------------
		 * 
		 * -------- 장애 메시지 (RTRV-ALM 요청) ----------
		 *    WNO3000_순천01 2019-10-25 15:14:53
		 * A  5177 REPT ALM
		 *    \/* TID,AID,SEVERITY,NAME,SA,UNIT-TYPE,ALARM-TYPE,OCCUR-DATE *\/
		 *    "WNO3000_순천01,S07-P4,CR,---,ON,OMX24U,LINK_DOWN,2019-09-30 19:04:02"
		 * ;
		 * --------------------------------
		 * </pre>
		 */

		List<String> list = split(line, " \",");
		if (list == null) {
			return null;
		}
		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid);

		/**
		 * <pre>
		 * 	 Tip.StringTokenizer() 을 사용시 빈칸은 skip 합니다. 이부분을 캐치 하여 리스트에 데이터를 추가 합니다.
		 * 		e.g )"WNO3000_영광T01,S01,MN,---,OFF,OGE40U,FABRIC_CRC_ERROR,2019-11-08 09:43:16"
		 *            "WNO3000_광양01,,CR,---,ON,SHELF,FAN2_M1_OUT,2019-11-07 17:12:50"
		 *            아래 데이터는 "," 잘랐을시 2번째 인덱스에 빈값입니다. 이때 skip을 하니 빈값을 생성해 리스트에 추가 합니다.
		 * </pre>
		 */
		if (list.size() == 8) {
			String[] codes = { "CR", "MJ", "MN", "CL" };
			if (Arrays.asList(codes).contains(list.get(1).toUpperCase())) {
				list.add(1, "");
			}
		}

		if (list.size() == 8) { // 실시간
			alarm.setLocation(list.get(0));
			alarm.setPortDescr(list.get(0));
			alarm.setDablGrCd(getDablGrCd(list.get(4)));
			alarm.setReason(list.get(3));
		} else if (list.size() == 9) { // 요청
			alarm.setLocation(list.get(1));
			alarm.setPortDescr(list.get(1));
			alarm.setDablGrCd(getDablGrCd(list.get(2)));
			alarm.setReason(list.get(6));
			String strDate = list.get(7) + " " + list.get(8);
			if (alarm.getDablGrCd().equals(DABL_GR_CD.Clear.getCode())) { // 릴리즈
				alarm.setClearHstime(strDate);
			} else { // 발생
				alarm.setOccurHstime(strDate);
			}
		} else {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}

		alarm.setFullMsg(line);

		return alarm;
	}

	public static void main(String[] args) throws Exception {
		List<String> msg = new ArrayList<String>();
		// msg.add("WNO3000_▒▒▒▒COT01 2019-10-25 16:00:06");
		// msg.add("A  5185 REPT ALM");
		// msg.add("/* AID,NAME,UNIT,REASON,SEV,SA,DATETIME */");
		// msg.add("\"S07-P1,---,OMX24U,LINK_DOWN,CR,NSA,2019-10-25 16:01:36\"");
		// msg.add("\"S03-P4,---,OMX24U,ETH_OSPF_NBR_STATE_DOWN,MJ,NSA,2019-10-25 16:08:13\"");
		// msg.add("");

		msg.add("WNO3000_순천01 2019-10-25 15:14:53");
		msg.add("M  4 COMPLD");
		msg.add("/* TID,AID,SEVERITY,NAME,SA,UNIT-TYPE,ALARM-TYPE,OCCUR-DATE */");
		msg.add("WNO3000_영광T01,S01,MN,---,OFF,OGE40U,FABRIC_CRC_ERROR,2019-11-08 09:43:16");
		msg.add("WNO3000_광양01,,CR,---,ON,SHELF,FAN2_M1_OUT,2019-11-07 17:12:50");
		msg.add(";");

		AM am = new AM(new ORMF_HEADER(msg.get(0)), msg.get(0) + "\n"
				+ msg.get(1) + "\n" + msg.get(2) + "\n" + msg.get(3) + "\n"
				+ msg.get(4) + "\n" + msg.get(5));

		// Logger.logger.debug("AM={}", am);
		// System.out.println(am.getAi().getVerb());

		// if (am.getAi().getVerb() == null || am.getAi().getVerb().size() < 2)
		// return;
		//
		//
		// if ((am.getAi().getVerb().get(0).equals("REPT") &&
		// am.getAi().getVerb().get(1).equals("ALM")) == false)
		// return;
		//
		// System.out.println(am.getHeader());
		// System.out.println(am.getAi());
		// System.out.println(am.getDataList());

		Woorinet woorinet = new Woorinet();
		for (String data : am.getDataList()) {
			woorinet.parseAlarm(null, data, am.getHeader().getSid());
		}
	}

}

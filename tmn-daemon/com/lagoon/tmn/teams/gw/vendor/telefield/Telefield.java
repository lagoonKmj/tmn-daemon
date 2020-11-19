package com.lagoon.tmn.teams.gw.vendor.telefield;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.vendor.telefield.adapter.TelefieldAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.telefield.adapter.TelefieldConfAdapter;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.vo.AM;
import fxms.nms.co.tl1_2.vo.ORMF_HEADER;

public class Telefield extends VendorBaseParser {

	@Override
	public ConfAdapter makeAdapter(IDcn dcn) {

		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			switch (ems.getEmsTypVal()) {
			case TelefieldService.TFOM_C60_EMS:
			case TelefieldService.TFOM_EMS:
				return new TelefieldConfAdapter(ems, this);
			default:
				Logger.logger.info("NOT FOUND EMS : {}", ems.getEmsTypVal());
			}
		}

		return null;
	}
	
	@Override
	public AlarmAdapter makeAlarmAdapter(IDcn dcn, GwDcnMngThread thread) {

		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;

			switch (ems.getEmsTypVal()) {
			case TelefieldService.TFOM_C60_EMS:
			case TelefieldService.TFOM_EMS:
				return new TelefieldAlarmAdapter(ems, thread, this);
			default:
				Logger.logger.info("NOT FOUND EMS : {}", ems.getEmsTypVal());
			}
		}

		return null;
	}

	@Override
	public List<DetectedAlarmVo> parseAlarms(IDcn dcn, List<String> dataList,
			String tid) {

		EmsDcn ems = (EmsDcn) dcn;

		List<DetectedAlarmVo> alarmList = new ArrayList<DetectedAlarmVo>();
		DetectedAlarmVo alarm = null;

		for (String line : dataList) {
			Map<String, String> map = new HashMap<String, String>();

			switch (ems.getEmsTypVal()) {
			case TelefieldService.TFOM_C60_EMS:
			case TelefieldService.TFOM_EMS:
				alarm = parseAlarm(dcn, line, tid);
				break;
			}

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
	public DetectedAlarmVo parseAlarm(IDcn dcn, String line,
			String tid) {
		/**
		 * <pre>
		 * -------- 장애 메시지 ----------
		 * 1-1-0-0-0-0-0-0 2012-12-28 10:46:53
		 * *  26718 REPT ALM EQPT
		 * \/*AID,UNIT:SEV,SA,SIGNAL,ALARM,DATE&TIME*\/
		 *   FU6.A-P2,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22
		 *   FU6.A-P3,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22
		 *   FU6.A-P4,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22
		 * 
		 * ;
		 * 
		 *  ^M
		 *  0   NE_0101 2012-11-26 17:27:46^M
		 *  1A  74 REPT ALM^M
		 *  2   /* PID:UNIT,REASON,AlarmSev,SrvAffect /^M
		 *  3   "NE_0101-OTC80B-OMCU:OMCU,DCC FAIL(OSCA-WDM1),MJ,NSA"^M
		 *  4;^M
 	 	 * --------------------------------
		 * </pre>
		 */
		List<String> list = split(line, " \",:");
		if (list == null) {
			return null;
		}
		if (list.size() < 6) {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}
		
		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);
		alarm.setEquipTidVal(tid);
		alarm.setDablGrCd(getDablGrCd(list.get(2)));
		alarm.setLocation(list.get(0));
		alarm.setPortDescr(list.get(0));
		alarm.setReason(list.get(5));
		alarm.setFullMsg(line);
		
		return alarm;
	}

	/**
	 * Severty 값이 'FT'인 경우 CRI 
	 * 
	 */
	@Override
	protected String getDablGrCd(String s) {
		if (s.equals("FT")) {
			return DABL_GR_CD.Critical.getCode();
		}
		return DABL_GR_CD.Info.getCode();
	}

	public static void main(String[] args) throws Exception {
		List<String> msg = new ArrayList<String>();
//		msg.add(" 1-1-0-0-0-0-0-0 2012-12-28 10:46:53");
//		msg.add(" *  26718 REPT ALM EQPT");
//		msg.add("\\/*AID,UNIT:SEV,SA,SIGNAL,ALARM,DATE&TIME*\\/");
//		msg.add("FU6.A-P2,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22");
//		msg.add("FU6.A-P3,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22");
//		msg.add("FU6.A-P4,OSIU1:MN,NSA,STM1_MS,RDI,2012-12-28 10:58:22");
		
		msg.add(" 13-11-1-5-17-11-0-0-0-0 2019-10-31 16:43:01");
		msg.add("M  37 COMPLD^M");
		msg.add("/*AID,UNIT:SEV,SA,SIGNAL,REASON,DETECT*/");
		msg.add("EOSU-P1,EOSU:MJ,SA,ETHNET,LOS,2324-09-34 30:00:00");
		msg.add("EOSU-P1,EOSU:MJ,SA,ETHNET,LOS,2324-09-34 30:00:00");
		msg.add("EOSU-P1,EOSU:MJ,SA,ETHNET,LOS,2324-09-34 30:00:00");
		
		/**
		 * <pre>
		 *  -------- 장애 메시지 (현재 장애 요청), 확인결과 실시간 이벤트와 포멧 같음 ----------
		 *    13-11-1-1-0-0-0-0-0-0 2019-10-28 10:22:26
		 * M  113 COMPLD
   		 * \\/*AID,UNIT:SEV,SA,SIGNAL,REASON,DETECT*\\/
		 * "S2.A-P4,STM16U:CR,SA,STM16,SDH-LOS,2019-07-10 02:02:49"
		 * "S2.B-P4,STM16U:CR,SA,STM16,SDH-MSAIS,2019-07-10 02:02:49"
		 * "S3.A-4-1-3-3,STM1U:MJ,SA,TU12,TU-AIS,2019-07-10 02:02:49"
		 * "S3.A-4-1-4-1,STM1U:MJ,SA,TU12,TU-AIS,2019-07-10 02:02:49"
		 * "S3.A-4-1-4-2,STM1U:MJ,SA,TU12,TU-AIS,2019-07-10 02:02:49"
		 * </pre>
		 * 
		 */

		AM am = new AM(new ORMF_HEADER(msg.get(0)), msg.get(0) + "\n" + msg.get(1) + "\n" + msg.get(2) + "\n" + msg.get(3) + "\n" + msg.get(4) + "\n" + msg.get(5));

		System.out.println(am.getHeader().getHstime());
		Logger.logger.debug("AM={}", am);

//		String tid = am.getHeader().getSid();

		if (am.getAi().getVerb() == null || am.getAi().getVerb().size() < 2)
			return;

		if ((am.getAi().getVerb().get(0).equals("REPT") && am.getAi().getVerb().get(1).equals("ALM")) == false)
			return;

		System.out.println(am.getHeader().getHstime());
//		System.out.println(am.getAi());
//		System.out.println(am.getDataList());
		
//		Telefield co = new Telefield();
//		for (int i = 2; i < msg.size(); i++) {
//			co.parseAlarm(null, msg.get(i), "1.1.10.172");			
//		}
	}
}

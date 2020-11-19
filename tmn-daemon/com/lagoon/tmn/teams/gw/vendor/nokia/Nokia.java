package com.lagoon.tmn.teams.gw.vendor.nokia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.fxms.dbo.SyslogPatternAdams;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.vendor.nokia.adapter.NokiaConfAdapter7210SAS;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.nms.co.syslog.vo.SyslogParsingResultVo;
import fxms.nms.co.syslog.vo.SyslogPattern.LogStatus;

public class Nokia extends VendorBaseParser {

	@Override
	public ConfAdapter makeAdapter(IDcn dcn) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case NokiaService.MODEL_7210SAS_T:
				return new NokiaConfAdapter7210SAS(equip, this);
			default:
				Logger.logger.info("NOT FOUND MODEL : {}",
						equip.getEquipMdlCd());
			}
		}

		return null;
	}

	@Override
	public AlarmAdapter makeAlarmAdapter(IDcn dcn, GwDcnMngThread thread) {

		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			switch (equip.getEquipMdlCd()) {
			case NokiaService.MODEL_7210SAS_T:
				return null; // SyslogService 로 장애 받음
			default:
				Logger.logger.info("NOT FOUND MODEL : {}",
						equip.getEquipMdlCd());
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

		// //////////////////////////////////////////////////////////
		// parseAlarm() 함수와 중복됨. TODO 개선 필요
		List<String> list = split(line, "|");
		if (list == null) {
			return null;
		}

		if (list.size() < 6) {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}

		String reason = null;
		if (dcn instanceof EquipDcn) {
			EquipDcn equipDcn = (EquipDcn) dcn;
			List<SyslogPatternAdams> syslogPatternList = GwApi.getApi()
					.getSyslogPatternListMapByEquipMdlCd()
					.get(equipDcn.getEquipMdlCd());
			if (syslogPatternList != null) {
				for (SyslogPatternAdams syslogPattern : syslogPatternList) {
					if (list.get(5).indexOf(syslogPattern.getOcrPatternStr()) > -1) {
						reason = syslogPattern.getOcrPatternStr();
					}
				}
			}
		}

		if (reason == null) {
			return null;
		}

		// //////////////////////////////////////////////////////////

		return parseAlarm(dcn, line, reason, tid, false);

	}

	public DetectedAlarmVo parseAlarm(IDcn dcn,
			SyslogParsingResultVo syslogParsingResultVo, String line) {

		SyslogPatternAdams pattern = (SyslogPatternAdams) syslogParsingResultVo
				.getPattern();
		LogStatus status = syslogParsingResultVo.getStatus();

		String reason = pattern.getOcrPatternStr();
		if (reason == null) {
			return null;
		}

		return parseAlarm(dcn, line, reason, null,
				(status == LogStatus.clearAll || status == LogStatus.clearOne));
	}

	private DetectedAlarmVo parseAlarm(IDcn dcn, String line, String reason,
			String tid, boolean isClear) {

		List<String> list = split(line, "|");
		if (list == null) {
			return null;
		}

		if (list.size() < 6) {
			Logger.logger.info("FAIL TO PARSE : {}", line);
			return null;
		}

		DetectedAlarmVo alarm = new DetectedAlarmVo();
		alarm.setDcn(dcn);

		if (tid == null) {
			tid = list.get(3);
		}

		alarm.setEquipTidVal(tid);
		alarm.setDablGrCd(getDablGrCd(list.get(4)));

		String location = getLocation(list.get(5));
		alarm.setLocation(location);
		alarm.setPortDescr(location);
		alarm.setReason(reason);

		// 장애 클리어는 시스로그 해지문자열(LogStatus) 로 판단 하는걸로 변경
		/*
		 * if (alarm.getDablGrCd().equals(DABL_GR_CD.Clear.getCode())) {
		 * alarm.setClearHstime(nokiaDate); } else {
		 * alarm.setOccurHstime(nokiaDate); }
		 */
		String nokiaDate = getNokiaDate(list.get(0));
		if (isClear) {
			alarm.setClearHstime(nokiaDate);
		} else {
			alarm.setOccurHstime(nokiaDate);
		}

		alarm.setFullMsg(line);

		return alarm;
	}

	@Override
	protected String getDablGrCd(String s) {
		String str = s.toLowerCase();
		if (str.indexOf("crit") > 0)
			return DABL_GR_CD.Major.getCode();
		if (str.indexOf("err") > 0)
			return DABL_GR_CD.Minor.getCode();
		if (str.indexOf("warn") > 0)
			return DABL_GR_CD.Warning.getCode();
		if (str.indexOf("clea") > 0)
			return DABL_GR_CD.Clear.getCode();
		if (str.indexOf("alert") > 0)
			return DABL_GR_CD.Info.getCode();
		// if (str.indexOf("emer") > 0)
		// return ALARM_LEVEL_QUA; // 품질 장애 TODO 확인 필요

		return DABL_GR_CD.Minor.getCode();
	}

	/**
	 * <pre>
	 * 시스로그(17 length) : Sep 11 17:49:43.0 
	 * 장애조회(19 length) : 2019/01/19 20:27:51, 뒤에 소숫점(ms) 짤려서 옴
	 * </pre>
	 * 
	 * @param value
	 * @return
	 */
	private String getNokiaDate(String value) {

		String strDate = "";
		if (value.length() == 17) {
			strDate += String.valueOf(FxApi.getYmd()).substring(0, 4);
			strDate += EngMonths.getValue(value.substring(0, 3));
			strDate += value.substring(4, 6);
			strDate += value.substring(7, 9);
			strDate += value.substring(10, 12);
			strDate += value.substring(13, 15);
		} else {
			strDate = value;
		}

		return strDate;
	}

	@SuppressWarnings("finally")
	private String getLocation(String s) {

		String retStr = "";
		try {
			s = s.replaceAll(":", "").replaceAll("/", "-");

			int start = s.indexOf("[") + 1;
			int end = s.indexOf("]");
			if (start > 0 && end > 0) {
				s = s.substring(start, end);
				if (s.length() > 0) {
					for (int iCnt = 0; iCnt < s.length(); iCnt++) {
						char c = s.charAt(iCnt);
						if (Character.isDigit(c) || c == '-') {
							retStr += c;
						}
					}
				}
			}
		} catch (Exception e) {
			Logger.logger.error(e);
			retStr = "";
		} finally {
			return retStr;
		}
	}

	public static void main(String[] args) {
		List<String> msg = new ArrayList<String>();
		msg.add("Sep 11 17:49:43.0|13.2.49.112|13.2.49.112|NO72NHIS-SKT-BuSa-03:|err| 497 Base PORT-MINOR-etherAlarmSet-2017 [Port 1/1/2]:  Alarm Local Fault Set");
		msg.add("Sep 11 17:49:43.0|13.2.49.115|13.2.49.115|NO72NHIS-SKT-JinJu-01:|err| 489 Base PORT-MINOR-etherAlarmSet-2017 [Port 3/1/2]:  Alarm Remote Fault Set");
		msg.add("Sep 11 17:49:43.0|13.2.49.112|13.2.49.112|NO72NHIS-SKT-BuSa-03:|warning| 498 Base SNMP-WARNING-linkDown-2004 [1/1/2]:  Interface 1/1/2 is not operational");
		msg.add("Sep 11 17:49:43.0|13.2.49.123|13.2.49.123|NO72NHIS-HQ-Center-05:|crit| 1034 Base SVCMGR-MAJOR-sdpBindSdpStateChangeProcessed-2316 []:  Processing of a SDP state change event is finished and the status of all affected SDP Bindings on SDP 262 has been updated.");
		msg.add("Sep 11 17:49:43.0|13.2.49.115|13.2.49.115|NO72NHIS-SKT-JinJu-01:|warning| 493 Base MPLS-WARNING-vRtrMplsIfStateChange-2008 [VR 1:]:  Interface te-3/1/2 is in administrative state: inService, operational state: outOfService");
		// lines[5] =
		// "Index      Date/Time               Severity  Alarm      Resource";
		// lines[6] = "Details";
		// lines[7] =
		// "-------------------------------------------------------------------------------";
		// lines[8] =
		// "5          2019/01/19 20:27:51.40  WARNING   59-2004-1  Port 1/1/17";
		// lines[9] = "Interface 1/1/17 is not operational";
		// 2019/01/19 20:27:51.40|X|X|59-2004-1 Port 1/1/17|WARNING|Interface
		// 1/1/17 is not operational 로케이션이,,,,[],,,,
		Nokia nokia = new Nokia();
		EquipDcn equipDcn = new EquipDcn();
		equipDcn.setEquipMdlCd(NokiaService.MODEL_7210SAS_T);
		for (int i = 0; i < msg.size(); i++) {
			// nokia.parseAlarm(equipDcn, msg.get(i), "NO72NHIS-SKT-JinJu-01");
			List<String> list = split(msg.get(i), "|");
			nokia.getLocation(list.get(5));
		}
	}

}

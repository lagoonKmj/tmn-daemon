package com.lagoon.tmn.teams.gw.co.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

/**
 * 파싱할때 도움을 주는 클래스
 * 
 * @author lagoon
 */
public abstract class VendorBaseParser {

	public abstract ConfAdapter makeAdapter(IDcn dcn);

	public abstract AlarmAdapter makeAlarmAdapter(IDcn dcn,
			GwDcnMngThread thread);

	public abstract List<DetectedAlarmVo> parseAlarms(IDcn dcn,
			List<String> dataList, String tid);

	public abstract DetectedAlarmVo parseAlarm(IDcn dcn, String line, String tid);

	public static List<String> split(String str, String pattern) {

		if (str == null || str.indexOf("/*") >= 0 || str.equals(";")
				|| str.trim().equals("")) {
			return null;
		}

		List<String> retList = new ArrayList<String>();
		String s = str.trim();
		s = s.replaceAll("\\\"", "");
		StringTokenizer stz = new StringTokenizer(s, pattern);
		while (stz.hasMoreElements()) {
			retList.add(stz.nextToken());
		}
		return retList;
	}

	protected String getDablGrCd(String s) {
		String str = s.toLowerCase();
		if (str.equals("cr"))
			return DABL_GR_CD.Critical.getCode();
		if (str.equals("mj"))
			return DABL_GR_CD.Major.getCode();
		if (str.equals("mn"))
			return DABL_GR_CD.Minor.getCode();
		if (str.equals("wn"))
			return DABL_GR_CD.Warning.getCode();
		if (str.equals("cl"))
			return DABL_GR_CD.Clear.getCode();

		return DABL_GR_CD.Info.getCode();
	}

	// TODO 확인 필요함
	protected void setAlarmAid(String s, DetectedAlarmVo alarm) {
		String ss[] = s.split("-");
		alarm.setCardDescr(ss[0].trim());
		if (ss.length > 1) {
			alarm.setPortDescr(s);
		}
	}

	public enum EngMonths {

		jan("01"), feb("02"), mar("03"), apr("04"), may("05"), jun("06"), jul(
				"07"), aug("08"), sep("09"), oct("10"), nov("11"), dec("12");

		private String value;

		private EngMonths(String value) {
			this.value = value;
		}

		public static String getValue(String month) {
			month = month.toLowerCase();
			for (EngMonths e : EngMonths.values()) {
				if (e.name().equals(month)) {
					return e.value;
				}
			}
			return null;
		}
	}

	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}

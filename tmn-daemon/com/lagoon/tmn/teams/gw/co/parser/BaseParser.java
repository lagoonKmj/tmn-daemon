package com.lagoon.tmn.teams.gw.co.parser;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;

import fxms.bas.ao.AoCode.AlarmLevel;

/**
 * 텔레필드 EMS 파싱
 * 
 * @author subkjh
 * 
 */
public abstract class BaseParser {

	protected void setAlarmAid(String s, DetectedAlarmVo alarm) {
		String ss[] = s.split("-");
		alarm.setCardDescr(ss[0].trim());
		if (ss.length > 1) {
			alarm.setPortDescr(s);
		}
	}

	protected int getAlarmLevel(String s) {
		String str = s.toLowerCase();
		if (str.equals("cr") || str.equals("critical") || str.equals("cri"))
			return AlarmLevel.Critical.getNo();
		if (str.equals("mj") || str.equals("major") || str.equals("maj"))
			return AlarmLevel.Major.getNo();
		if (str.equals("mn") || str.equals("minor") || str.equals("min"))
			return AlarmLevel.Minor.getNo();

		return -1;
	}

	protected String getAid(String s) {
		if (s.indexOf(":") > 0) {
			return s.split(":")[0];
		}
		return s;
	}

	protected String[] split(String str, String pattern) {
		String s = str.trim();
		if (s.indexOf("/*") >= 0) {
			return null;
		} else {
			s = s.replaceAll("\\\"", "");
			return s.split(pattern);
		}
	}

	public abstract List<DetectedAlarmVo> parseAlarm(IDcn dcn, List<String> dataList, String tid);

}

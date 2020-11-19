package com.lagoon.tmn.teams.co;

import fxms.bas.ao.AoCode.AlarmLevel;
import fxms.bas.ao.vo.AlarmCode;

@SuppressWarnings({ "unused", "serial" })
public class AdamsAlarmCode extends AlarmCode {

	public AdamsAlarmCode(int alcdNo, String alcdName, AlarmLevel alarmLevel, String psCode, String compareCode, String alarmMsg,
			String treatName, String targetMoClass, int autoClearSec, boolean serviceAlarmYn) {
		super(alcdNo, alcdName, alarmLevel, psCode, compareCode, alarmMsg, treatName, targetMoClass, autoClearSec, serviceAlarmYn);
		// TODO Auto-generated constructor stub
	}

	private String dablLclCd;
	private String dablLclNm;
	private String dablCd;
	private boolean netAlarm;
	private String netAlarmCode;
	private String netAlarmName;

	public String getDablLclCd() {
		return dablLclCd;
	}

	public void setDablLclCd(String dablLclCd) {
		this.dablLclCd = dablLclCd;
	}

	public String getDablLclNm() {
		return dablLclNm;
	}

	public void setDablLclNm(String dablLclNm) {
		this.dablLclNm = dablLclNm;
	}

	public String getDablCd() {
		return dablCd;
	}

	public void setDablCd(String dablCd) {
		this.dablCd = dablCd;
	}

}

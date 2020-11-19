package com.lagoon.tmn.teams.fxms.ao.pdu;

import java.util.ArrayList;

public abstract class BodyComm implements Body {

	static public String SKT = "01";
	static public String SKT2 = "02";
	static public String SKB = "03";

	protected static final String CHARSET_NAME = "euc-kr";
	protected static final char SEPARATOR = 0x1f;

	private String eventType;
	private String alarmLevel;
	private String mngGroupCd = "003003";
	private String trmsNetMgmtOwnerCd = SKB;
	private String dablGrCd;
	private String neId;

	public BodyComm() {

	}

	public byte[] getBytes() throws Exception {
		return getBody().getBytes(CHARSET_NAME);
	}

	public String getAlarmLevel() {
		return alarmLevel;
	}

	public String getDablGrCd() {
		return this.dablGrCd;
	}

	public String getEventType() {
		return this.eventType;
	}

	public String getMngGroupCd() {
		return this.mngGroupCd;
	}

	public String getNeId() {
		return neId;
	}

	public String getTrmsNetMgmtOwnerCd() {
		return this.trmsNetMgmtOwnerCd;
	}

	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
		if (alarmLevel.equals("1") // Info
				|| alarmLevel.equals("2") // Warning
				|| alarmLevel.equals("3") // Minor
				|| alarmLevel.equals("4") // Major
				|| alarmLevel.equals("5") // Critical
				|| alarmLevel.equals("7")) // Fatal
			dablGrCd = "0" + alarmLevel;
		else
			dablGrCd = "01";
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setMngGroupCd(String mngGroupCd) {
		this.mngGroupCd = mngGroupCd;
		if ("003001".equals(mngGroupCd)) {
			this.trmsNetMgmtOwnerCd = SKT;
		} else if ("003002".equals(mngGroupCd)) {
			this.trmsNetMgmtOwnerCd = SKT2;
		} else if ("003003".equals(mngGroupCd)) {
			this.trmsNetMgmtOwnerCd = SKB;
		}
	}

	public void setNeId(String neId) {
		this.neId = neId;
	}

	protected String[] split(byte[] body) throws Exception {
		if (body == null || body.length == 0)
			return null;
		ArrayList<String> strList = new ArrayList<String>();
		int startIndex = 0;
		int nextIndex = 0;
		while (nextIndex < body.length) {
			if (body[nextIndex] == SEPARATOR) {
				if (startIndex == nextIndex) {
					strList.add("");
				} else {
					strList.add(new String(body, startIndex, nextIndex - startIndex, CHARSET_NAME));
				}
				startIndex = nextIndex + 1;
			}
			nextIndex++;
		}
		if (startIndex < nextIndex) {
			strList.add(new String(body, startIndex, nextIndex - startIndex, CHARSET_NAME));
		}
		return strList.toArray(new String[strList.size()]);
	}

	protected void append(StringBuffer sb, String o) {

		if (o != null) {
			sb.append(o);
		}
		sb.append(SEPARATOR);
	}
	
}

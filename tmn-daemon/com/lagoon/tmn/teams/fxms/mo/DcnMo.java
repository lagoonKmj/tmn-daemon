package com.lagoon.tmn.teams.fxms.mo;

import fxms.bas.mo.Mo;

@SuppressWarnings("serial")
public class DcnMo extends Mo {

	private int dcnNum;
	private String dcnNm;

	private String mstDcnIpAddr;
	private String subDcnIpAddr;
	private String dcnTypCd;
	private String dcnEmsTyp;
	private int alarmPort;
	private int confPort;
	private String userId;
	private String passwd;
	private int snmpVer;
	private String snmpCommunity;
	private String equipId;
	private String emsId;
	private String equipTid;
		
	public DcnMo() {
		setMoClass("DCN");
	}

	public int getAlarmPort() {
		return alarmPort;
	}

	public int getConfPort() {
		return confPort;
	}

	public String getDcnEmsTyp() {
		return dcnEmsTyp;
	}

	public String getDcnNm() {
		return dcnNm;
	}

	public int getDcnNum() {
		return dcnNum;
	}

	public String getDcnTypCd() {
		return dcnTypCd;
	}

	public String getEmsId() {
		return emsId;
	}

	public String getEquipId() {
		return equipId;
	}

	public String getEquipTid() {
		return equipTid;
	}

	public String getMstDcnIpAddr() {
		return mstDcnIpAddr;
	}

	public String getPasswd() {
		return passwd;
	}

	public String getSnmpCommunity() {
		return snmpCommunity;
	}

	public int getSnmpVer() {
		return snmpVer;
	}

	public String getSubDcnIpAddr() {
		return subDcnIpAddr;
	}

	public String getUserId() {
		return userId;
	}

	public void setAlarmPort(int alarmPort) {
		this.alarmPort = alarmPort;
	}

	public void setConfPort(int confPort) {
		this.confPort = confPort;
	}

	public void setDcnEmsTyp(String dcnEmsTyp) {
		this.dcnEmsTyp = dcnEmsTyp;
	}

	public void setDcnNm(String dcnNm) {
		this.dcnNm = dcnNm;
	}

	public void setDcnNum(int dcnNum) {
		this.dcnNum = dcnNum;
	}

	public void setDcnTypCd(String dcnTypCd) {
		this.dcnTypCd = dcnTypCd;
	}

	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public void setEquipTid(String equipTid) {
		this.equipTid = equipTid;
	}

	public void setMstDcnIpAddr(String mstDcnIpAddr) {
		this.mstDcnIpAddr = mstDcnIpAddr;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setSnmpCommunity(String snmpCommunity) {
		this.snmpCommunity = snmpCommunity;
	}

	public void setSnmpVer(int snmpVer) {
		this.snmpVer = snmpVer;
	}

	public void setSubDcnIpAddr(String subDcnIpAddr) {
		this.subDcnIpAddr = subDcnIpAddr;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}

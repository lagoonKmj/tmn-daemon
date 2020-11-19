package com.lagoon.tmn.teams.co.vo;

/**
 * EMS DCN
 * 
 * @author subkjh(김종훈)
 *
 */
public class EmsDcn implements IDcn {

	private String emsId;
	private String emsIpAddr;
	private String emsIpAddr2;
	private String emsTypVal;
	private String loginId;
	private String loginPwd;
	private String snmpReadCmntyNm;
	private String snmpWriteCmntyNm;
	private int snmpPortNum;
	private int dablPortNum;
	private int consPortNum;
	private int prfmPortNum;
	private int useIpAddrIdxVal = 1;
	
	@Override
	public String toInfo()
	{
		return "EMS " + emsTypVal + " " + emsIpAddr;
	}

	public int getConsPortNum() {
		return consPortNum;
	}

	public int getDablPortNum() {
		return dablPortNum;
	}

	@Override
	public String getDcnKey() {
		return "EMS:" + emsId;
	}

	public String getEmsId() {
		return emsId;
	}

	public String getEmsIpAddr() {
		return emsIpAddr;
	}

	public String getEmsIpAddr2() {
		return emsIpAddr2;
	}

	public String getEmsTypVal() {
		return emsTypVal;
	}

	public String getLoginId() {
		return loginId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public int getPrfmPortNum() {
		return prfmPortNum;
	}

	public int getSnmpPortNum() {
		return snmpPortNum;
	}

	public String getSnmpReadCmntyNm() {
		return snmpReadCmntyNm;
	}

	public String getSnmpWriteCmntyNm() {
		return snmpWriteCmntyNm;
	}

	public int getUseIpAddrIdxVal() {
		return useIpAddrIdxVal;
	}

	public void setConsPortNum(int consPortNum) {
		this.consPortNum = consPortNum;
	}

	public void setDablPortNum(int dablPortNum) {
		this.dablPortNum = dablPortNum;
	}

	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

	public void setEmsIpAddr(String emsIpAddr) {
		this.emsIpAddr = emsIpAddr;
	}

	public void setEmsIpAddr2(String emsIpAddr2) {
		this.emsIpAddr2 = emsIpAddr2;
	}

	public void setEmsTypVal(String emsTypVal) {
		this.emsTypVal = emsTypVal;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public void setPrfmPortNum(int prfmPortNum) {
		this.prfmPortNum = prfmPortNum;
	}

	public void setSnmpPortNum(int snmpPortNum) {
		this.snmpPortNum = snmpPortNum;
	}

	public void setSnmpReadCmntyNm(String snmpReadCmntyNm) {
		this.snmpReadCmntyNm = snmpReadCmntyNm;
	}

	public void setSnmpWriteCmntyNm(String snmpWriteCmntyNm) {
		this.snmpWriteCmntyNm = snmpWriteCmntyNm;
	}

	public void setUseIpAddrIdxVal(int useIpAddrIdxVal) {
		this.useIpAddrIdxVal = useIpAddrIdxVal;
	}

	@Override
	public String toString() {
		return "EMS(" + emsId + ", " + emsIpAddr + ")";
	}

	@Override
	public String getIpAddress() {
		return emsIpAddr;
	}
}

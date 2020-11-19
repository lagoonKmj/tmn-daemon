package com.lagoon.tmn.teams.co.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 장비 DCN
 * 
 * @author subkjh(김종훈)
 *
 */
public class EquipDcn extends AdamsEquipVo implements IDcn {

	private String loginId;
	private String loginPwd;
	private String snmpReadCmntyNm;
	private String snmpWriteCmntyNm;
	private int snmpPortNum;
	private int dablPortNum;
	private int consPortNum;
	private int prfmPortNum;
	/** COT EQUIP-ID */
	private String cotEquipId;
	/** DCN(COT)이 관리하는 RT 목록 */
	private List<EquipDcn> rtList;

	@Override
	public String toInfo() {
		return "EQUIP " + getEquipMdlNm() + " " + getEquipIpAddr();
	}

	public void addRt(EquipDcn rt) {
		if (rtList == null) {
			rtList = new ArrayList<EquipDcn>();
		}

		rtList.add(rt);
	}

	public int getConsPortNum() {
		return consPortNum;
	}

	public String getCotEquipId() {
		return cotEquipId;
	}

	public int getDablPortNum() {
		return dablPortNum;
	}

	@Override
	public String getDcnKey() {
		return "EQUIP:" + getEquipId();
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

	public List<EquipDcn> getRtList() {
		return rtList;
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

	public void setConsPortNum(int consPortNum) {
		this.consPortNum = consPortNum;
	}

	public void setCotEquipId(String cotEquipId) {
		this.cotEquipId = cotEquipId;
	}

	public void setDablPortNum(int dablPortNum) {
		this.dablPortNum = dablPortNum;
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

	public void setRtList(List<EquipDcn> rtList) {
		this.rtList = rtList;
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

	@Override
	public String toString() {
		return "EQUIP(" + getEquipId() + ", " + getEquipIpAddr() + ")";
	}

}

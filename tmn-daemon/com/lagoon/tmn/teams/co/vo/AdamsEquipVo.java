package com.lagoon.tmn.teams.co.vo;

import fxms.nms.co.snmp.trap.TrapNode;
import fxms.nms.co.syslog.mo.SyslogNode;
import fxms.nms.mo.property.Modelable;

/**
 * ADAMS내 장비정보
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsEquipVo implements TrapNode, SyslogNode {

	private String equipIpAddr;
	private String equipId;
	private String equipTidVal;
	private String equipMdlCd;
	private String equipMdlNm;

	/** 소속된 EMS ID */
	private String emsId;
	/** EMS내 장비 ID */
	private String emsInrEquipKeyVal;

	private String swVerInfo;

	public String getEmsId() {
		return emsId;
	}

	public String getEmsInrEquipKeyVal() {
		return emsInrEquipKeyVal;
	}

	public String getEquipId() {
		return equipId;
	}

	public String getEquipIpAddr() {
		return equipIpAddr;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	public String getEquipTidVal() {
		return equipTidVal;
	}

	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

	public void setEmsInrEquipKeyVal(String emsInrEquipKeyVal) {
		this.emsInrEquipKeyVal = emsInrEquipKeyVal;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public void setEquipIpAddr(String equipIpAddr) {
		this.equipIpAddr = equipIpAddr;
	}

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	public void setEquipTidVal(String equipTidVal) {
		this.equipTidVal = equipTidVal;
	}

	@Override
	public String getIpAddress() {
		return equipIpAddr;
	}

	@Override
	public boolean isTrapRecv() {
		return true;
	}

	public String getSwVerInfo() {
		return swVerInfo;
	}

	public void setSwVerInfo(String swVerInfo) {
		this.swVerInfo = swVerInfo;
	}

	public String getEquipMdlNm() {
		return equipMdlNm;
	}

	public void setEquipMdlNm(String equipMdlNm) {
		this.equipMdlNm = equipMdlNm;
	}

	@Override
	public boolean isSyslogRecv() {
		return true;
	}

	@Override
	public String getNodeName() {
		return equipIpAddr;
	}

	@Override
	public boolean equalModel(Modelable arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}

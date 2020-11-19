package com.lagoon.tmn.teams.co.vo;

/**
 * EMS에서 관리하는 장비 정보
 * 
 * @author subkjh(김종훈)
 *
 */
public class EmsEquipVo extends AdamsEquipVo {

	/** 장비의 소속 EMS */
	private String emsId;

	/** EMS장비번호 */
	private String emsEquipNum;

	public String getEmsId() {
		return emsId;
	}

	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

	public String getEmsEquipNum() {
		return emsEquipNum;
	}

	public void setEmsEquipNum(String emsEquipNum) {
		this.emsEquipNum = emsEquipNum;
	}

}

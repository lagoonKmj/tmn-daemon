package com.lagoon.tmn.teams.fxms.mo;

import fxms.bas.mo.Mo;

public class PortMo extends Mo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6172640984518974631L;

	public static final String MO_CLASS = "PORT";

	/** 장비ID */
	private String equipId;

	/** 장비포트ID */
	private String equipPortId;

	/** 장비구성품ID */
	private String equipConsItmId;

	/** 실장위치번호 */
	private String mounLocNum;

	/** 포트번호 */
	private String portNum;

	/** 포트별칭명 */
	private String portAlsNm;

	/** 포트유형코드 */
	private String portTypCd;

	/** 포트속도코드 */
	private String portSpeedCd;

	/** POLLING감시대상여부 */
	private boolean pollingMObjYn = false;

	/** PING감시대상여부 */
	private boolean pingMObjYn = false;

	/** 등록주기 */
	private int rgstCycl;

	/** 포트설명 */
	private String portDesc;

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getEquipPortId() {
		return equipPortId;
	}

	public void setEquipPortId(String equipPortId) {
		this.equipPortId = equipPortId;
	}

	public String getEquipConsItmId() {
		return equipConsItmId;
	}

	public void setEquipConsItmId(String equipConsItmId) {
		this.equipConsItmId = equipConsItmId;
	}

	public String getMounLocNum() {
		return mounLocNum;
	}

	public void setMounLocNum(String mounLocNum) {
		this.mounLocNum = mounLocNum;
	}

	public String getPortNum() {
		return portNum;
	}

	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	public String getPortAlsNm() {
		return portAlsNm;
	}

	public void setPortAlsNm(String portAlsNm) {
		this.portAlsNm = portAlsNm;
	}

	public String getPortTypCd() {
		return portTypCd;
	}

	public void setPortTypCd(String portTypCd) {
		this.portTypCd = portTypCd;
	}

	public String getPortSpeedCd() {
		return portSpeedCd;
	}

	public void setPortSpeedCd(String portSpeedCd) {
		this.portSpeedCd = portSpeedCd;
	}

	public boolean isPollingMObjYn() {
		return pollingMObjYn;
	}

	public void setPollingMObjYn(boolean pollingMObjYn) {
		this.pollingMObjYn = pollingMObjYn;
	}

	public boolean isPingMObjYn() {
		return pingMObjYn;
	}

	public void setPingMObjYn(boolean pingMObjYn) {
		this.pingMObjYn = pingMObjYn;
	}

	public int getRgstCycl() {
		return rgstCycl;
	}

	public void setRgstCycl(int rgstCycl) {
		this.rgstCycl = rgstCycl;
	}

	public String getPortDesc() {
		return portDesc;
	}

	public void setPortDesc(String portDesc) {
		this.portDesc = portDesc;
	}

}

package com.lagoon.tmn.teams.fxms.mo;

import fxms.bas.mo.Mo;

public class CardMo extends Mo {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4664397778430539102L;

	/** 장비구성품ID */
	private String equipConsItmId;

	/** 상위장비구성품ID */
	private String supEquipConsItmId;

	/** 장비구성품코드 */
	private String equipConsItmCd;

	/** 장비ID */
	private String equipId;

	/** 구성품번호 */
	private String consItmNum;

	/** 장비구성품명 */
	private String equipConsItmNm;

	/** 제조사일련번호 */
	private String mfactSerNum;

	/** 장비구성품설명 */
	private String equipConsItmDesc;

	/** 전면후면구분코드 */
	private String frntBackClCd;

	/** 포트개수 */
	private int portCnt;

	/** POLLING감시대상여부 */
	private boolean pollingMObjYn = false;

	/** 호스트명 */
	private String hostNm;

	/** 메모 */
	private String memo;

	/** 장비구성품별칭명 */
	private String equipConsItmAlsNm;

	/** 전송망장비용량구분코드 */
	private String trmsNetEquipCapaClCd;

	/** 대체가능여부 */
	private boolean altPsblYn = false;

	/** 백업장비ID */
	private String bkEquipId;

	/** 백업장비구성품ID */
	private String bkEquipConsItmId;

	/** 장비구성품상태값 */
	private String equipConsItmStVal;

	/** 장비구성품서브상태값 */
	private String equipConsItmSubStVal;

	/** 자동관리여부 */
	private boolean autoMgmtYn = false;

	/** 등록일시 */
	private String rgstDtm;

	/** 파장값 */
	private double wvlengVal;

	/** 장비구성품용도코드 */
	private String equipConsItmUsgCd;

	/** 대표IP주소 */
	private String repIpAddr;

	public String getEquipConsItmId() {
		return equipConsItmId;
	}

	public void setEquipConsItmId(String equipConsItmId) {
		this.equipConsItmId = equipConsItmId;
	}

	public String getSupEquipConsItmId() {
		return supEquipConsItmId;
	}

	public void setSupEquipConsItmId(String supEquipConsItmId) {
		this.supEquipConsItmId = supEquipConsItmId;
	}

	public String getEquipConsItmCd() {
		return equipConsItmCd;
	}

	public void setEquipConsItmCd(String equipConsItmCd) {
		this.equipConsItmCd = equipConsItmCd;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getConsItmNum() {
		return consItmNum;
	}

	public void setConsItmNum(String consItmNum) {
		this.consItmNum = consItmNum;
	}

	public String getEquipConsItmNm() {
		return equipConsItmNm;
	}

	public void setEquipConsItmNm(String equipConsItmNm) {
		this.equipConsItmNm = equipConsItmNm;
	}

	public String getMfactSerNum() {
		return mfactSerNum;
	}

	public void setMfactSerNum(String mfactSerNum) {
		this.mfactSerNum = mfactSerNum;
	}

	public String getEquipConsItmDesc() {
		return equipConsItmDesc;
	}

	public void setEquipConsItmDesc(String equipConsItmDesc) {
		this.equipConsItmDesc = equipConsItmDesc;
	}

	public String getFrntBackClCd() {
		return frntBackClCd;
	}

	public void setFrntBackClCd(String frntBackClCd) {
		this.frntBackClCd = frntBackClCd;
	}

	public int getPortCnt() {
		return portCnt;
	}

	public void setPortCnt(int portCnt) {
		this.portCnt = portCnt;
	}

	public boolean isPollingMObjYn() {
		return pollingMObjYn;
	}

	public void setPollingMObjYn(boolean pollingMObjYn) {
		this.pollingMObjYn = pollingMObjYn;
	}

	public String getHostNm() {
		return hostNm;
	}

	public void setHostNm(String hostNm) {
		this.hostNm = hostNm;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getEquipConsItmAlsNm() {
		return equipConsItmAlsNm;
	}

	public void setEquipConsItmAlsNm(String equipConsItmAlsNm) {
		this.equipConsItmAlsNm = equipConsItmAlsNm;
	}

	public String getTrmsNetEquipCapaClCd() {
		return trmsNetEquipCapaClCd;
	}

	public void setTrmsNetEquipCapaClCd(String trmsNetEquipCapaClCd) {
		this.trmsNetEquipCapaClCd = trmsNetEquipCapaClCd;
	}

	public boolean isAltPsblYn() {
		return altPsblYn;
	}

	public void setAltPsblYn(boolean altPsblYn) {
		this.altPsblYn = altPsblYn;
	}

	public String getBkEquipId() {
		return bkEquipId;
	}

	public void setBkEquipId(String bkEquipId) {
		this.bkEquipId = bkEquipId;
	}

	public String getBkEquipConsItmId() {
		return bkEquipConsItmId;
	}

	public void setBkEquipConsItmId(String bkEquipConsItmId) {
		this.bkEquipConsItmId = bkEquipConsItmId;
	}

	public String getEquipConsItmStVal() {
		return equipConsItmStVal;
	}

	public void setEquipConsItmStVal(String equipConsItmStVal) {
		this.equipConsItmStVal = equipConsItmStVal;
	}

	public String getEquipConsItmSubStVal() {
		return equipConsItmSubStVal;
	}

	public void setEquipConsItmSubStVal(String equipConsItmSubStVal) {
		this.equipConsItmSubStVal = equipConsItmSubStVal;
	}

	public boolean isAutoMgmtYn() {
		return autoMgmtYn;
	}

	public void setAutoMgmtYn(boolean autoMgmtYn) {
		this.autoMgmtYn = autoMgmtYn;
	}

	public String getRgstDtm() {
		return rgstDtm;
	}

	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	public double getWvlengVal() {
		return wvlengVal;
	}

	public void setWvlengVal(double wvlengVal) {
		this.wvlengVal = wvlengVal;
	}

	public String getEquipConsItmUsgCd() {
		return equipConsItmUsgCd;
	}

	public void setEquipConsItmUsgCd(String equipConsItmUsgCd) {
		this.equipConsItmUsgCd = equipConsItmUsgCd;
	}

	public String getRepIpAddr() {
		return repIpAddr;
	}

	public void setRepIpAddr(String repIpAddr) {
		this.repIpAddr = repIpAddr;
	}

}

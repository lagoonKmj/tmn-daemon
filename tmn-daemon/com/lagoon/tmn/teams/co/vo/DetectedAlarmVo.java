package com.lagoon.tmn.teams.co.vo;

/**
 * 탐색된 경보 정보
 * 
 * @author subkjh(김종훈)
 *
 */
public class DetectedAlarmVo {

	private IDcn dcn;

	private String equipIpAddr;
	private String equipTidVal;

	private String cardDescr;
	private String portDescr;
	private String location;
	private String reason;
	private String dablGrCd;
	private String occurHstime;
	private String clearHstime;
	private String fullMsg;

	/** 장애가 발생한 장비정보 */
	private AdamsEquipVo equip;

	/** OMN33812(전송망메세지) 로직을 무시(시스로그, 트랩) */
	private boolean ignoreOmn33812 = false;
	
	public boolean isIgnoreOmn33812() {
		return ignoreOmn33812;
	}

	public void setIgnoreOmn33812(boolean ignoreOmn33812) {
		this.ignoreOmn33812 = ignoreOmn33812;
	}

	public String getCardDescr() {
		return cardDescr;
	}

	public String getClearHstime() {
		return clearHstime;
	}

	public String getDablGrCd() {
		return dablGrCd;
	}

	public IDcn getDcn() {
		return dcn;
	}

	public AdamsEquipVo getEquip() {
		return equip;
	}

	public String getEquipIpAddr() {
		return equipIpAddr;
	}

	public String getEquipTidVal() {
		return equipTidVal;
	}

	public String getFullMsg() {
		return fullMsg;
	}

	public String getLocation() {
		return location;
	}

	public String getOccurHstime() {
		return occurHstime;
	}

	public String getPortDescr() {
		return portDescr;
	}

	public String getReason() {
		return reason;
	}

	public void setCardDescr(String cardDescr) {
		this.cardDescr = cardDescr;
	}

	public void setClearHstime(String clearHstime) {
		this.clearHstime = clearHstime;
	}

	public void setDablGrCd(String dablGrCd) {
		this.dablGrCd = dablGrCd;
	}

	public void setDcn(IDcn dcn) {
		this.dcn = dcn;
	}

	public void setEquip(AdamsEquipVo equip) {
		this.equip = equip;
	}

	public void setEquipIpAddr(String equipIpAddr) {
		this.equipIpAddr = equipIpAddr;
	}

	public void setEquipTidVal(String equipTidVal) {
		this.equipTidVal = equipTidVal;
	}

	public void setFullMsg(String fullMsg) {
		this.fullMsg = fullMsg;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setOccurHstime(String occurHstime) {
		this.occurHstime = occurHstime;
	}

	public void setPortDescr(String portDescr) {
		this.portDescr = portDescr;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAlarmKey() {
		return fullMsg;
	}
	
	/**
	 * 이벤트 타입 을 반환
	 *
	 * <pre>
	 * 받은 해제 일시로 이벤트 판단
	 * </pre>
	 * 
	 * @return
	 */
	public int getEventType() {
		if (clearHstime != null) {
			return TrmsNetEventVo.CLEAR_EVENT_ALARM;
		}
		return TrmsNetEventVo.OCCUR_EVENT_ALARM;
	}

	@Override
	public String toString() {
		return "DetectedAlarmVo [dcn=" + dcn + ", equipIpAddr=" + equipIpAddr
				+ ", equipTidVal=" + equipTidVal + ", cardDescr=" + cardDescr
				+ ", portDescr=" + portDescr + ", location=" + location
				+ ", reason=" + reason + ", dablGrCd=" + dablGrCd
				+ ", occurHstime=" + occurHstime + ", clearHstime="
				+ clearHstime + ", fullMsg=" + fullMsg + ", equip=" + equip
				+ "]";
	}
	
	
}

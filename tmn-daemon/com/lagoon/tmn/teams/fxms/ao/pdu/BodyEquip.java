package com.lagoon.tmn.teams.fxms.ao.pdu;

import com.lagoon.tmn.teams.fxms.ao.vo.OccurEquipAlarmVo;

public class BodyEquip extends BodyComm {
	private String alarmNo;
	private String nmsCd;
	private String modelCd;
	private String modelName;
	private String neName;
	private String tid;
	private String ipAddr;
	private String equipUsage;
	private String portDescr;
	private String location;
	private String reason;
	private String orgId;
	private String orgName;
	private String linCount;
	private String alarmDupCount;
	private String occurTime;
	private String alarmKey;
	private String workYn;
	private String ticketYn;
	private String egovAlarmYn;
	private String backboneYn;
	private String ofdAlarmYn;
	private String postGroupCd;
	private String postGroupName;
	private String postCd;
	private String postName;
	private String alarmDupYn;
	private String seriousAlarmYn;
	private String alarmTypCd;
	private String alarmIdOther;
	private String closeMode;
	private String modelType;
	private String networkIds;
	private String networkNames;
	private String trRoomId;
	private String trRoomName;
	private String cityId;
	private String cityName;
	private String guId;
	private String guName;
	private String occurRTime;
	private String rasId;
	private String macAddr;
	private String supProviderCd;
	private String supProviderName;
	private String nwCarrierCd;
	private String nwCarrierName;
	private String entServiceNo;
	private String etc;
	private String cotRtType;
	private String focusYn;
	private String userAlarmLevel;
	private String userReason;
	private String alarmKind;

	public BodyEquip(OccurEquipAlarmVo vo) {
		this.setEventType(Header.SERVICE_CODE_EQUIP_FAULT);
		// TODO
	}

	public BodyEquip() {
		setEventType(Header.SERVICE_CODE_EQUIP_FAULT);
	}

	public String getAlarmDupCount() {
		return alarmDupCount;
	}

	public String getAlarmDupYn() {
		return alarmDupYn;
	}

	public String getAlarmIdOther() {
		return alarmIdOther;
	}

	public String getAlarmKey() {
		return alarmKey;
	}

	public String getAlarmKind() {
		return alarmKind;
	}

	public String getAlarmNo() {
		return alarmNo;
	}

	public String getAlarmTypCd() {
		return alarmTypCd;
	}

	public String getBackboneYn() {
		return backboneYn;
	}

	@Override
	public String getBody() {

		StringBuffer sb = new StringBuffer();

		append(sb, alarmNo);
		append(sb, nmsCd);
		append(sb, modelCd);
		append(sb, modelName);
		append(sb, getNeId());
		append(sb, neName);
		append(sb, tid);
		append(sb, ipAddr);
		append(sb, equipUsage);
		append(sb, getAlarmLevel());
		append(sb, portDescr);
		append(sb, location);
		append(sb, reason);
		append(sb, orgId);
		append(sb, orgName);
		append(sb, linCount);
		append(sb, alarmDupCount);
		append(sb, occurTime);
		append(sb, alarmKey);
		append(sb, workYn);
		append(sb, ticketYn);
		append(sb, egovAlarmYn);
		append(sb, backboneYn);
		append(sb, ofdAlarmYn);
		append(sb, postGroupCd);
		append(sb, postGroupName);
		append(sb, postCd);
		append(sb, postName);
		append(sb, alarmDupYn);
		append(sb, seriousAlarmYn);
		append(sb, alarmTypCd);
		append(sb, alarmIdOther);
		append(sb, getMngGroupCd());
		append(sb, closeMode);
		append(sb, modelType);
		append(sb, networkIds);
		append(sb, networkNames);
		append(sb, trRoomId);
		append(sb, trRoomName);
		append(sb, cityId);
		append(sb, cityName);
		append(sb, guId);
		append(sb, guName);
		append(sb, occurRTime);
		append(sb, rasId);
		append(sb, macAddr);
		append(sb, supProviderCd);
		append(sb, supProviderName);
		append(sb, nwCarrierCd);
		append(sb, nwCarrierName);
		append(sb, entServiceNo);
		append(sb, etc);
		append(sb, cotRtType);
		append(sb, focusYn);
		append(sb, userAlarmLevel);
		append(sb, userReason);
		append(sb, alarmKind);

		return sb.toString();
	}

	public String getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCloseMode() {
		return closeMode;
	}

	public String getCotRtType() {
		return cotRtType;
	}

	public String getEgovAlarmYn() {
		return egovAlarmYn;
	}

	public String getEntServiceNo() {
		return entServiceNo;
	}

	public String getEquipUsage() {
		return equipUsage;
	}

	public String getEtc() {
		return etc;
	}

	public String getFocusYn() {
		return focusYn;
	}

	public String getGuId() {
		return guId;
	}

	public String getGuName() {
		return guName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getLinCount() {
		return linCount;
	}

	public String getLocation() {
		return location;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public String getModelCd() {
		return modelCd;
	}

	public String getModelName() {
		return modelName;
	}

	public String getModelType() {
		return modelType;
	}

	public String getNeName() {
		return neName;
	}

	public String getNetworkIds() {
		return networkIds;
	}

	public String getNetworkNames() {
		return networkNames;
	}

	public String getNmsCd() {
		return nmsCd;
	}

	public String getNwCarrierCd() {
		return nwCarrierCd;
	}

	public String getNwCarrierName() {
		return nwCarrierName;
	}

	public String getOccurRTime() {
		return occurRTime;
	}

	public String getOccurTime() {
		return occurTime;
	}

	public String getOfdAlarmYn() {
		return ofdAlarmYn;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public String getPortDescr() {
		return portDescr;
	}

	public String getPostCd() {
		return postCd;
	}

	public String getPostGroupCd() {
		return postGroupCd;
	}

	public String getPostGroupName() {
		return postGroupName;
	}

	public String getPostName() {
		return postName;
	}

	public String getRasId() {
		return rasId;
	}

	public String getReason() {
		return reason;
	}

	public String getSeriousAlarmYn() {
		return seriousAlarmYn;
	}

	public String getSupProviderCd() {
		return supProviderCd;
	}

	public String getSupProviderName() {
		return supProviderName;
	}

	public String getTicketYn() {
		return ticketYn;
	}

	public String getTid() {
		return tid;
	}

	public String getTrRoomId() {
		return trRoomId;
	}

	public String getTrRoomName() {
		return trRoomName;
	}

	public String getUserAlarmLevel() {
		return userAlarmLevel;
	}

	public String getUserReason() {
		return userReason;
	}

	public String getWorkYn() {
		return workYn;
	}

	public void setAlarmDupCount(String alarmDupCount) {
		this.alarmDupCount = alarmDupCount;
	}

	public void setAlarmDupYn(String alarmDupYn) {
		this.alarmDupYn = alarmDupYn;
	}

	public void setAlarmIdOther(String alarmIdOther) {
		this.alarmIdOther = alarmIdOther;
	}

	public void setAlarmKey(String alarmKey) {
		this.alarmKey = alarmKey;
	}

	public void setAlarmKind(String alarmKind) {
		this.alarmKind = alarmKind;
	}

	public void setAlarmNo(String alarmNo) {
		this.alarmNo = alarmNo;
	}

	public void setAlarmTypCd(String alarmTypCd) {
		this.alarmTypCd = alarmTypCd;
	}

	public void setBackboneYn(String backboneYn) {
		this.backboneYn = backboneYn;
	}

	public void setBody(byte bytes[]) throws Exception {

		String items[] = this.split(bytes);

		this.alarmNo = items[0];
		this.nmsCd = items[1];
		this.modelCd = items[2];
		this.modelName = items[3];
		this.setNeId(items[4]);
		this.neName = items[5];
		this.tid = items[6];
		this.ipAddr = items[7];
		this.equipUsage = items[8];
		this.setAlarmLevel(items[9]);
		this.portDescr = items[10];
		this.location = items[11];
		this.reason = items[12];
		this.orgId = items[13];
		this.orgName = items[14];
		this.linCount = items[15];
		this.alarmDupCount = items[16];
		this.occurTime = items[17];
		this.alarmKey = items[18];
		this.workYn = items[19];
		this.ticketYn = items[20];
		this.egovAlarmYn = items[21];
		this.backboneYn = items[22];
		this.ofdAlarmYn = items[23];
		this.postGroupCd = items[24];
		this.postGroupName = items[25];
		this.postCd = items[26];
		this.postName = items[27];
		this.alarmDupYn = items[28];
		this.seriousAlarmYn = items[29];
		this.alarmTypCd = items[30];
		this.alarmIdOther = items[31];
		this.setMngGroupCd(items[32]);
		this.closeMode = items[33];
		this.modelType = items[34];
		this.networkIds = items[35];
		this.networkNames = items[36];
		this.trRoomId = items[37];
		this.trRoomName = items[38];
		this.cityId = items[39];
		this.cityName = items[40];
		this.guId = items[41];
		this.guName = items[42];
		this.occurRTime = items[43];
		this.rasId = items[44];
		this.macAddr = items[45];
		this.supProviderCd = items[46];
		this.supProviderName = items[47];
		this.nwCarrierCd = items[48];
		this.nwCarrierName = items[49];
		this.entServiceNo = items[50];
		this.etc = items[51];
		this.cotRtType = items[52];
		this.focusYn = items[53];
		this.userAlarmLevel = items[54];
		this.userReason = items[55];
		this.alarmKind = items[56];
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public void setCloseMode(String closeMode) {
		this.closeMode = closeMode;
	}

	public void setCotRtType(String cotRtType) {
		this.cotRtType = cotRtType;
	}

	public void setEgovAlarmYn(String egovAlarmYn) {
		this.egovAlarmYn = egovAlarmYn;
	}

	public void setEntServiceNo(String entServiceNo) {
		this.entServiceNo = entServiceNo;
	}

	public void setEquipUsage(String equipUsage) {
		this.equipUsage = equipUsage;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public void setFocusYn(String focusYn) {
		this.focusYn = focusYn;
	}

	public void setGuId(String guId) {
		this.guId = guId;
	}

	public void setGuName(String guName) {
		this.guName = guName;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setLinCount(String linCount) {
		this.linCount = linCount;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public void setModelCd(String modelCd) {
		this.modelCd = modelCd;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setModelType(String modelType) {
		this.modelType = modelType;
	}

	public void setNeName(String neName) {
		this.neName = neName;
	}

	public void setNetworkIds(String networkIds) {
		this.networkIds = networkIds;
	}

	public void setNetworkNames(String networkNames) {
		this.networkNames = networkNames;
	}

	public void setNmsCd(String nmsCd) {
		this.nmsCd = nmsCd;
	}

	public void setNwCarrierCd(String nwCarrierCd) {
		this.nwCarrierCd = nwCarrierCd;
	}

	public void setNwCarrierName(String nwCarrierName) {
		this.nwCarrierName = nwCarrierName;
	}

	public void setOccurRTime(String occurRTime) {
		this.occurRTime = occurRTime;
	}

	public void setOccurTime(String occurTime) {
		this.occurTime = occurTime;
	}

	public void setOfdAlarmYn(String ofdAlarmYn) {
		this.ofdAlarmYn = ofdAlarmYn;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public void setPortDescr(String portDescr) {
		this.portDescr = portDescr;
	}

	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}

	public void setPostGroupCd(String postGroupCd) {
		this.postGroupCd = postGroupCd;
	}

	public void setPostGroupName(String postGroupName) {
		this.postGroupName = postGroupName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public void setRasId(String rasId) {
		this.rasId = rasId;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setSeriousAlarmYn(String seriousAlarmYn) {
		this.seriousAlarmYn = seriousAlarmYn;
	}

	public void setSupProviderCd(String supProviderCd) {
		this.supProviderCd = supProviderCd;
	}

	public void setSupProviderName(String supProviderName) {
		this.supProviderName = supProviderName;
	}

	public void setTicketYn(String ticketYn) {
		this.ticketYn = ticketYn;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setTrRoomId(String trRoomId) {
		this.trRoomId = trRoomId;
	}

	public void setTrRoomName(String trRoomName) {
		this.trRoomName = trRoomName;
	}

	public void setUserAlarmLevel(String userAlarmLevel) {
		this.userAlarmLevel = userAlarmLevel;
	}

	public void setUserReason(String userReason) {
		this.userReason = userReason;
	}

	public void setWorkYn(String workYn) {
		this.workYn = workYn;
	}
}

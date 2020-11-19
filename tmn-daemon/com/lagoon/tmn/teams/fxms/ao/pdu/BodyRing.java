package com.lagoon.tmn.teams.fxms.ao.pdu;

import com.lagoon.tmn.teams.fxms.ao.vo.OccurRingAlarmVo;

public class BodyRing extends BodyComm {

	private String alarmNo;
	private String nmsCd;
	private String networkId;
	private String networkName;
	private String networkAlarmReason;
	private String nmsAlarmNo;
	private String modelId;
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
	private String occurTime;
	private String alarmKey;
	private String workYn;
	private String ticketYn;
	private String egovAlarmYn;
	private String backbonYn;
	private String ofdAlarmYn;
	private String postGroupCd;
	private String postGroupName;
	private String postCd;
	private String postName;
	private String closeMode;
	private String networkAlarmReasonCd;
	private String topologyCd;
	private String ringId;
	private String ringName;
	private String trunkId;
	private String trunkName;
	private String trRoomId;
	private String trRoomName;
	private String occurRTime;
	private String focusYn;
	private String userAlarmLevel;
	private String userReason;
	private String seriousAlarmYn;

	public BodyRing(OccurRingAlarmVo vo) {
		this.setEventType(Header.SERVICE_CODE_RING_FAULT);
		// TODO
	}

	public BodyRing() {
		this.setEventType(Header.SERVICE_CODE_RING_FAULT);
	}

	public String getAlarmKey() {
		return alarmKey;
	}

	public String getAlarmNo() {
		return alarmNo;
	}

	public String getBackbonYn() {
		return backbonYn;
	}

	public String getCloseMode() {
		return closeMode;
	}

	public String getEgovAlarmYn() {
		return egovAlarmYn;
	}

	public String getEquipUsage() {
		return equipUsage;
	}

	public String getFocusYn() {
		return focusYn;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getLocation() {
		return location;
	}

	public String getModelId() {
		return modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public String getNeName() {
		return neName;
	}

	public String getNetworkAlarmReason() {
		return networkAlarmReason;
	}

	public String getNetworkAlarmReasonCd() {
		return networkAlarmReasonCd;
	}

	public String getNetworkId() {
		return networkId;
	}

	public String getNetworkName() {
		return networkName;
	}

	public String getNmsAlarmNo() {
		return nmsAlarmNo;
	}

	public String getNmsCd() {
		return nmsCd;
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

	public String getReason() {
		return reason;
	}

	public String getRingId() {
		return ringId;
	}

	public String getRingName() {
		return ringName;
	}

	public String getSeriousAlarmYn() {
		return seriousAlarmYn;
	}

	public String getTicketYn() {
		return ticketYn;
	}

	public String getTid() {
		return tid;
	}

	public String getTopologyCd() {
		return topologyCd;
	}

	public String getTrRoomId() {
		return trRoomId;
	}

	public String getTrRoomName() {
		return trRoomName;
	}

	public String getTrunkId() {
		return trunkId;
	}

	public String getTrunkName() {
		return trunkName;
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

	public void setAlarmKey(String alarmKey) {
		this.alarmKey = alarmKey;
	}

	public void setAlarmNo(String alarmNo) {
		this.alarmNo = alarmNo;
	}

	public void setBackbonYn(String backbonYn) {
		this.backbonYn = backbonYn;
	}

	public void setBody(byte bytes[]) throws Exception {

		String items[] = this.split(bytes);

		this.alarmNo = items[0];
		this.nmsCd = items[1];
		this.setMngGroupCd(items[2]);
		this.networkId = items[3];
		this.networkName = items[4];
		this.networkAlarmReason = items[5];
		this.nmsAlarmNo = items[6];
		this.modelId = items[7];
		this.modelName = items[8];
		this.setNeId(items[9]);
		this.neName = items[10];
		this.tid = items[11];
		this.ipAddr = items[12];
		this.equipUsage = items[13];
		this.setAlarmLevel(items[14]);
		this.portDescr = items[15];
		this.location = items[16];
		this.reason = items[17];
		this.orgId = items[18];
		this.orgName = items[19];
		this.occurTime = items[20];
		this.alarmKey = items[21];
		this.workYn = items[22];
		this.ticketYn = items[23];
		this.egovAlarmYn = items[24];
		this.backbonYn = items[25];
		this.ofdAlarmYn = items[26];
		this.postGroupCd = items[27];
		this.postGroupName = items[28];
		this.postCd = items[29];
		this.postName = items[30];
		this.closeMode = items[31];
		this.networkAlarmReasonCd = items[32];
		this.topologyCd = items[33];
		this.ringId = items[34];
		this.ringName = items[35];
		this.trunkId = items[36];
		this.trunkName = items[37];
		this.trRoomId = items[38];
		this.trRoomName = items[39];
		this.occurRTime = items[40];
		this.focusYn = items[41];
		this.userAlarmLevel = items[42];
		this.userReason = items[43];
		this.seriousAlarmYn = items[44];
	}

	public void setCloseMode(String closeMode) {
		this.closeMode = closeMode;
	}

	public void setEgovAlarmYn(String egovAlarmYn) {
		this.egovAlarmYn = egovAlarmYn;
	}

	public void setEquipUsage(String equipUsage) {
		this.equipUsage = equipUsage;
	}

	public void setFocusYn(String focusYn) {
		this.focusYn = focusYn;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setNeName(String neName) {
		this.neName = neName;
	}

	public void setNetworkAlarmReason(String networkAlarmReason) {
		this.networkAlarmReason = networkAlarmReason;
	}

	public void setNetworkAlarmReasonCd(String networkAlarmReasonCd) {
		this.networkAlarmReasonCd = networkAlarmReasonCd;
	}

	public void setNetworkId(String networkId) {
		this.networkId = networkId;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	public void setNmsAlarmNo(String nmsAlarmNo) {
		this.nmsAlarmNo = nmsAlarmNo;
	}

	public void setNmsCd(String nmsCd) {
		this.nmsCd = nmsCd;
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

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRingId(String ringId) {
		this.ringId = ringId;
	}

	public void setRingName(String ringName) {
		this.ringName = ringName;
	}

	public void setSeriousAlarmYn(String seriousAlarmYn) {
		this.seriousAlarmYn = seriousAlarmYn;
	}

	public void setTicketYn(String ticketYn) {
		this.ticketYn = ticketYn;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setTopologyCd(String topologyCd) {
		this.topologyCd = topologyCd;
	}

	public void setTrRoomId(String trRoomId) {
		this.trRoomId = trRoomId;
	}

	public void setTrRoomName(String trRoomName) {
		this.trRoomName = trRoomName;
	}

	public void setTrunkId(String trunkId) {
		this.trunkId = trunkId;
	}

	public void setTrunkName(String trunkName) {
		this.trunkName = trunkName;
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

	@Override
	public String getBody() {

		StringBuffer sb = new StringBuffer();

		append(sb, alarmNo);
		append(sb, nmsCd);
		append(sb, getMngGroupCd());
		append(sb, networkId);
		append(sb, networkName);
		append(sb, networkAlarmReason);
		append(sb, nmsAlarmNo);
		append(sb, modelId);
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
		append(sb, occurTime);
		append(sb, alarmKey);
		append(sb, workYn);
		append(sb, ticketYn);
		append(sb, egovAlarmYn);
		append(sb, backbonYn);
		append(sb, ofdAlarmYn);
		append(sb, postGroupCd);
		append(sb, postGroupName);
		append(sb, postCd);
		append(sb, postName);
		append(sb, closeMode);
		append(sb, networkAlarmReasonCd);
		append(sb, topologyCd);
		append(sb, ringId);
		append(sb, ringName);
		append(sb, trunkId);
		append(sb, trunkName);
		append(sb, trRoomId);
		append(sb, trRoomName);
		append(sb, occurRTime);
		append(sb, focusYn);
		append(sb, userAlarmLevel);
		append(sb, userReason);
		append(sb, seriousAlarmYn);
		// TODO Auto-generated method stub
		return null;
	}

}

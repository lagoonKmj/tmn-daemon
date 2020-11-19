package com.lagoon.tmn.teams.fxms.ao.pdu;

import com.lagoon.tmn.teams.fxms.ao.vo.OccurLineAlarmVo;

public class BodyLine extends BodyComm {
	private String alarmNo;
	private String lineNo;
	private String lineName;
	private String custNo;
	private String custName;
	private String custTelNo;
	private String entServiceNo;
	private String lineTypeCd;
	private String lineType;
	private String serviceTypeCd;
	private String serviceType;
	private String leaseLineNo;
	private String leaseCorpCd;
	private String leaseCorp;
	private String linkCorpCd;
	private String linkCorp;
	private String capacityCd;
	private String capacity;
	private String uOrgId;
	private String uOrgName;
	private String uSystemName;
	private String uSystemOrgId;
	private String uSystemOrgName;
	private String lOrgId;
	private String lOrgName;
	private String lSystemName;
	private String lSystemOrgId;
	private String lSystemOrgName;
	private String nmsAlarmNo;
	private String nmsCd;
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
	private String backboneYn;
	private String ofdAlarmYn;
	private String postGroupCd;
	private String postGroupName;
	private String postCd;
	private String postName;
	private String closeMode;
	private String lineClassCd;
	private String lineClass;
	private String alarmIdOther;
	private String lCityId;
	private String lCityName;
	private String lGuId;
	private String lGuName;
	private String trunkId;
	private String trunkName;
	private String networkId;
	private String networkName;
	private String uTrRoomId;
	private String uTrRoomName;
	private String lTrRoomId;
	private String lTrRoomName;
	private String trRoomId;
	private String trRoomName;
	private String occurRTime;
	private String conPortDescr;
	private String rasId;
	private String macAddr;
	private String supProviderCd;
	private String supProviderName;
	private String nwCarrierCd;
	private String nwCarrierName;
	private String shopName;
	private String cotRtType;
	private String lineTrRoomId;
	private String lineTrRoomName;
	private String qdfInfoStr;
	private String ogTie1;
	private String ogTie2;
	private String icTie1;
	private String icTie2;
	private String focusYn;
	private String userAlarmLevel;
	private String userReason;
	private String ringId;
	private String ringName;
	private String modelType;
	private String lineStatusCd;
	private String lineStatus;
	private String effortStatus;
	private String lineAmId;

	public BodyLine(OccurLineAlarmVo vo) {
		this.setEventType(Header.SERVICE_CODE_LINE_FAULT);
		// TODO
	}

	public BodyLine() {
		this.setEventType(Header.SERVICE_CODE_LINE_FAULT);
	}

	public String getAlarmIdOther() {
		return alarmIdOther;
	}

	public String getAlarmKey() {
		return alarmKey;
	}

	public String getAlarmNo() {
		return alarmNo;
	}

	public String getBackboneYn() {
		return backboneYn;
	}

	@Override
	public String getBody() {
		StringBuffer sb = new StringBuffer();

		append(sb, alarmNo);
		append(sb, getAlarmLevel());
		append(sb, lineNo);
		append(sb, lineName);
		append(sb, custNo);
		append(sb, custName);
		append(sb, custTelNo);
		append(sb, entServiceNo);
		append(sb, lineTypeCd);
		append(sb, lineType);
		append(sb, serviceTypeCd);
		append(sb, serviceType);
		append(sb, leaseLineNo);
		append(sb, leaseCorpCd);
		append(sb, leaseCorp);
		append(sb, linkCorpCd);
		append(sb, linkCorp);
		append(sb, capacityCd);
		append(sb, capacity);
		append(sb, uOrgId);
		append(sb, uOrgName);
		append(sb, uSystemName);
		append(sb, uSystemOrgId);
		append(sb, uSystemOrgName);
		append(sb, lOrgId);
		append(sb, lOrgName);
		append(sb, lSystemName);
		append(sb, lSystemOrgId);
		append(sb, lSystemOrgName);
		append(sb, nmsAlarmNo);
		append(sb, getMngGroupCd());
		append(sb, nmsCd);
		append(sb, modelId);
		append(sb, modelName);
		append(sb, getNeId());
		append(sb, neName);
		append(sb, tid);
		append(sb, ipAddr);
		append(sb, equipUsage);
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
		append(sb, backboneYn);
		append(sb, ofdAlarmYn);
		append(sb, postGroupCd);
		append(sb, postGroupName);
		append(sb, postCd);
		append(sb, postName);
		append(sb, closeMode);
		append(sb, lineClassCd);
		append(sb, lineClass);
		append(sb, alarmIdOther);
		append(sb, lCityId);
		append(sb, lCityName);
		append(sb, lGuId);
		append(sb, lGuName);
		append(sb, trunkId);
		append(sb, trunkName);
		append(sb, networkId);
		append(sb, networkName);
		append(sb, uTrRoomId);
		append(sb, uTrRoomName);
		append(sb, lTrRoomId);
		append(sb, lTrRoomName);
		append(sb, trRoomId);
		append(sb, trRoomName);
		append(sb, occurRTime);
		append(sb, conPortDescr);
		append(sb, rasId);
		append(sb, macAddr);
		append(sb, supProviderCd);
		append(sb, supProviderName);
		append(sb, nwCarrierCd);
		append(sb, nwCarrierName);
		append(sb, shopName);
		append(sb, cotRtType);
		append(sb, lineTrRoomId);
		append(sb, lineTrRoomName);
		append(sb, qdfInfoStr);
		append(sb, ogTie1);
		append(sb, ogTie2);
		append(sb, icTie1);
		append(sb, icTie2);
		append(sb, focusYn);
		append(sb, userAlarmLevel);
		append(sb, userReason);
		append(sb, ringId);
		append(sb, ringName);
		append(sb, modelType);
		append(sb, lineStatusCd);
		append(sb, lineStatus);
		append(sb, effortStatus);
		append(sb, lineAmId);

		return sb.toString();
	}

	public String getCapacity() {
		return capacity;
	}

	public String getCapacityCd() {
		return capacityCd;
	}

	public String getCloseMode() {
		return closeMode;
	}

	public String getConPortDescr() {
		return conPortDescr;
	}

	public String getCotRtType() {
		return cotRtType;
	}

	public String getCustName() {
		return custName;
	}

	public String getCustNo() {
		return custNo;
	}

	public String getCustTelNo() {
		return custTelNo;
	}

	public String getEffortStatus() {
		return effortStatus;
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

	public String getFocusYn() {
		return focusYn;
	}

	public String getIcTie1() {
		return icTie1;
	}

	public String getIcTie2() {
		return icTie2;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getlCityId() {
		return lCityId;
	}

	public String getlCityName() {
		return lCityName;
	}

	public String getLeaseCorp() {
		return leaseCorp;
	}

	public String getLeaseCorpCd() {
		return leaseCorpCd;
	}

	public String getLeaseLineNo() {
		return leaseLineNo;
	}

	public String getlGuId() {
		return lGuId;
	}

	public String getlGuName() {
		return lGuName;
	}

	public String getLineAmId() {
		return lineAmId;
	}

	public String getLineClass() {
		return lineClass;
	}

	public String getLineClassCd() {
		return lineClassCd;
	}

	public String getLineName() {
		return lineName;
	}

	public String getLineNo() {
		return lineNo;
	}

	public String getLineStatus() {
		return lineStatus;
	}

	public String getLineStatusCd() {
		return lineStatusCd;
	}

	public String getLineTrRoomId() {
		return lineTrRoomId;
	}

	public String getLineTrRoomName() {
		return lineTrRoomName;
	}

	public String getLineType() {
		return lineType;
	}

	public String getLineTypeCd() {
		return lineTypeCd;
	}

	public String getLinkCorp() {
		return linkCorp;
	}

	public String getLinkCorpCd() {
		return linkCorpCd;
	}

	public String getLocation() {
		return location;
	}

	public String getlOrgId() {
		return lOrgId;
	}

	public String getlOrgName() {
		return lOrgName;
	}

	public String getlSystemName() {
		return lSystemName;
	}

	public String getlSystemOrgId() {
		return lSystemOrgId;
	}

	public String getlSystemOrgName() {
		return lSystemOrgName;
	}

	public String getlTrRoomId() {
		return lTrRoomId;
	}

	public String getlTrRoomName() {
		return lTrRoomName;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public String getModelId() {
		return modelId;
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

	public String getOgTie1() {
		return ogTie1;
	}

	public String getOgTie2() {
		return ogTie2;
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

	public String getQdfInfoStr() {
		return qdfInfoStr;
	}

	public String getRasId() {
		return rasId;
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

	public String getServiceType() {
		return serviceType;
	}

	public String getServiceTypeCd() {
		return serviceTypeCd;
	}

	public String getShopName() {
		return shopName;
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

	public String getTrunkId() {
		return trunkId;
	}

	public String getTrunkName() {
		return trunkName;
	}

	public String getuOrgId() {
		return uOrgId;
	}

	public String getuOrgName() {
		return uOrgName;
	}

	public String getUserAlarmLevel() {
		return userAlarmLevel;
	}

	public String getUserReason() {
		return userReason;
	}

	public String getuSystemName() {
		return uSystemName;
	}

	public String getuSystemOrgId() {
		return uSystemOrgId;
	}

	public String getuSystemOrgName() {
		return uSystemOrgName;
	}

	public String getuTrRoomId() {
		return uTrRoomId;
	}

	public String getuTrRoomName() {
		return uTrRoomName;
	}

	public String getWorkYn() {
		return workYn;
	}

	public void setAlarmIdOther(String alarmIdOther) {
		this.alarmIdOther = alarmIdOther;
	}

	public void setAlarmKey(String alarmKey) {
		this.alarmKey = alarmKey;
	}

	public void setAlarmNo(String alarmNo) {
		this.alarmNo = alarmNo;
	}

	public void setBackboneYn(String backboneYn) {
		this.backboneYn = backboneYn;
	}

	public void setBody(byte bytes[]) throws Exception {

		String items[] = this.split(bytes);

		this.alarmNo = items[0];
		this.setAlarmLevel(items[1]);
		this.lineNo = items[2];
		this.lineName = items[3];
		this.custNo = items[4];
		this.custName = items[5];
		this.custTelNo = items[6];
		this.entServiceNo = items[7];
		this.lineTypeCd = items[8];
		this.lineType = items[9];
		this.serviceTypeCd = items[10];
		this.serviceType = items[11];
		this.leaseLineNo = items[12];
		this.leaseCorpCd = items[13];
		this.leaseCorp = items[14];
		this.linkCorpCd = items[15];
		this.linkCorp = items[16];
		this.capacityCd = items[17];
		this.capacity = items[18];
		this.uOrgId = items[19];
		this.uOrgName = items[20];
		this.uSystemName = items[21];
		this.uSystemOrgId = items[22];
		this.uSystemOrgName = items[23];
		this.lOrgId = items[24];
		this.lOrgName = items[25];
		this.lSystemName = items[26];
		this.lSystemOrgId = items[27];
		this.lSystemOrgName = items[28];
		this.nmsAlarmNo = items[29];
		this.setMngGroupCd(items[30]);
		this.nmsCd = items[31];
		this.modelId = items[32];
		this.modelName = items[33];
		this.setNeId(items[34]);
		this.neName = items[35];
		this.tid = items[36];
		this.ipAddr = items[37];
		this.equipUsage = items[38];
		this.portDescr = items[39];
		this.location = items[40];
		this.reason = items[41];
		this.orgId = items[42];
		this.orgName = items[43];
		this.occurTime = items[44];
		this.alarmKey = items[45];
		this.workYn = items[46];
		this.ticketYn = items[47];
		this.egovAlarmYn = items[48];
		this.backboneYn = items[49];
		this.ofdAlarmYn = items[50];
		this.postGroupCd = items[51];
		this.postGroupName = items[52];
		this.postCd = items[53];
		this.postName = items[54];
		this.closeMode = items[55];
		this.lineClassCd = items[56];
		this.lineClass = items[57];
		this.alarmIdOther = items[58];
		this.lCityId = items[59];
		this.lCityName = items[60];
		this.lGuId = items[61];
		this.lGuName = items[62];
		this.trunkId = items[63];
		this.trunkName = items[64];
		this.networkId = items[65];
		this.networkName = items[66];
		this.uTrRoomId = items[67];
		this.uTrRoomName = items[68];
		this.lTrRoomId = items[69];
		this.lTrRoomName = items[70];
		this.trRoomId = items[71];
		this.trRoomName = items[72];
		this.occurRTime = items[73];
		this.conPortDescr = items[74];
		this.rasId = items[75];
		this.macAddr = items[76];
		this.supProviderCd = items[77];
		this.supProviderName = items[78];
		this.nwCarrierCd = items[79];
		this.nwCarrierName = items[80];
		this.shopName = items[81];
		this.cotRtType = items[82];
		this.lineTrRoomId = items[83];
		this.lineTrRoomName = items[84];
		this.qdfInfoStr = items[85];
		this.ogTie1 = items[86];
		this.ogTie2 = items[87];
		this.icTie1 = items[88];
		this.icTie2 = items[89];
		this.focusYn = items[90];
		this.userAlarmLevel = items[91];
		this.userReason = items[92];
		this.ringId = items[93];
		this.ringName = items[94];
		this.modelType = items[95];
		this.lineStatusCd = items[101];
		this.lineStatus = items[102];
		this.effortStatus = items[103];
		this.lineAmId = items[104];
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public void setCapacityCd(String capacityCd) {
		this.capacityCd = capacityCd;
	}

	public void setCloseMode(String closeMode) {
		this.closeMode = closeMode;
	}

	public void setConPortDescr(String conPortDescr) {
		this.conPortDescr = conPortDescr;
	}

	public void setCotRtType(String cotRtType) {
		this.cotRtType = cotRtType;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public void setCustTelNo(String custTelNo) {
		this.custTelNo = custTelNo;
	}

	public void setEffortStatus(String effortStatus) {
		this.effortStatus = effortStatus;
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

	public void setFocusYn(String focusYn) {
		this.focusYn = focusYn;
	}

	public void setIcTie1(String icTie1) {
		this.icTie1 = icTie1;
	}

	public void setIcTie2(String icTie2) {
		this.icTie2 = icTie2;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setlCityId(String lCityId) {
		this.lCityId = lCityId;
	}

	public void setlCityName(String lCityName) {
		this.lCityName = lCityName;
	}

	public void setLeaseCorp(String leaseCorp) {
		this.leaseCorp = leaseCorp;
	}

	public void setLeaseCorpCd(String leaseCorpCd) {
		this.leaseCorpCd = leaseCorpCd;
	}

	public void setLeaseLineNo(String leaseLineNo) {
		this.leaseLineNo = leaseLineNo;
	}

	public void setlGuId(String lGuId) {
		this.lGuId = lGuId;
	}

	public void setlGuName(String lGuName) {
		this.lGuName = lGuName;
	}

	public void setLineAmId(String lineAmId) {
		this.lineAmId = lineAmId;
	}

	public void setLineClass(String lineClass) {
		this.lineClass = lineClass;
	}

	public void setLineClassCd(String lineClassCd) {
		this.lineClassCd = lineClassCd;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	public void setLineStatus(String lineStatus) {
		this.lineStatus = lineStatus;
	}

	public void setLineStatusCd(String lineStatusCd) {
		this.lineStatusCd = lineStatusCd;
	}

	public void setLineTrRoomId(String lineTrRoomId) {
		this.lineTrRoomId = lineTrRoomId;
	}

	public void setLineTrRoomName(String lineTrRoomName) {
		this.lineTrRoomName = lineTrRoomName;
	}

	public void setLineType(String lineType) {
		this.lineType = lineType;
	}

	public void setLineTypeCd(String lineTypeCd) {
		this.lineTypeCd = lineTypeCd;
	}

	public void setLinkCorp(String linkCorp) {
		this.linkCorp = linkCorp;
	}

	public void setLinkCorpCd(String linkCorpCd) {
		this.linkCorpCd = linkCorpCd;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setlOrgId(String lOrgId) {
		this.lOrgId = lOrgId;
	}

	public void setlOrgName(String lOrgName) {
		this.lOrgName = lOrgName;
	}

	public void setlSystemName(String lSystemName) {
		this.lSystemName = lSystemName;
	}

	public void setlSystemOrgId(String lSystemOrgId) {
		this.lSystemOrgId = lSystemOrgId;
	}

	public void setlSystemOrgName(String lSystemOrgName) {
		this.lSystemOrgName = lSystemOrgName;
	}

	public void setlTrRoomId(String lTrRoomId) {
		this.lTrRoomId = lTrRoomId;
	}

	public void setlTrRoomName(String lTrRoomName) {
		this.lTrRoomName = lTrRoomName;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
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

	public void setOgTie1(String ogTie1) {
		this.ogTie1 = ogTie1;
	}

	public void setOgTie2(String ogTie2) {
		this.ogTie2 = ogTie2;
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

	public void setQdfInfoStr(String qdfInfoStr) {
		this.qdfInfoStr = qdfInfoStr;
	}

	public void setRasId(String rasId) {
		this.rasId = rasId;
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

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	public void setTrunkId(String trunkId) {
		this.trunkId = trunkId;
	}

	public void setTrunkName(String trunkName) {
		this.trunkName = trunkName;
	}

	public void setuOrgId(String uOrgId) {
		this.uOrgId = uOrgId;
	}

	public void setuOrgName(String uOrgName) {
		this.uOrgName = uOrgName;
	}

	public void setUserAlarmLevel(String userAlarmLevel) {
		this.userAlarmLevel = userAlarmLevel;
	}

	public void setUserReason(String userReason) {
		this.userReason = userReason;
	}

	public void setuSystemName(String uSystemName) {
		this.uSystemName = uSystemName;
	}

	public void setuSystemOrgId(String uSystemOrgId) {
		this.uSystemOrgId = uSystemOrgId;
	}

	public void setuSystemOrgName(String uSystemOrgName) {
		this.uSystemOrgName = uSystemOrgName;
	}

	public void setuTrRoomId(String uTrRoomId) {
		this.uTrRoomId = uTrRoomId;
	}

	public void setuTrRoomName(String uTrRoomName) {
		this.uTrRoomName = uTrRoomName;
	}

	public void setWorkYn(String workYn) {
		this.workYn = workYn;
	}
}

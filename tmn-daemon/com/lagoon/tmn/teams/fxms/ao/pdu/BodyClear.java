package com.lagoon.tmn.teams.fxms.ao.pdu;

import com.lagoon.tmn.teams.fxms.ao.vo.ClearAlarmVo;

public class BodyClear extends BodyComm {

	private String alarmEventType;
	private String clrUserId;
	private String clrFlag;
	private String clearTime;
	private String durationSec;
	private String alarmNo;
	private String clearRTime;
	private String trRoomId;
	private String modelId;
	private String nmsCd;
	private String workYn;
	private String linCount;

	public BodyClear(ClearAlarmVo vo) {
		this.setEventType(Header.SERVICE_CODE_CLEAR);
		// TODO
	}

	public BodyClear() {
		this.setEventType(Header.SERVICE_CODE_CLEAR);
	}

	public String getAlarmEventType() {
		return alarmEventType;
	}

	public String getAlarmNo() {
		return alarmNo;
	}

	@Override
	public String getBody() {
		StringBuffer sb = new StringBuffer();

		append(sb, alarmEventType);
		append(sb, clrUserId);
		append(sb, clrFlag);
		append(sb, clearTime);
		append(sb, durationSec);
		append(sb, alarmNo);
		append(sb, getMngGroupCd());
		append(sb, clearRTime);
		append(sb, trRoomId);
		append(sb, modelId);
		append(sb, getNeId());
		append(sb, nmsCd);
		append(sb, workYn);
		append(sb, linCount);

		return sb.toString();
	}

	public String getClearRTime() {
		return clearRTime;
	}

	public String getClearTime() {
		return clearTime;
	}

	public String getClrFlag() {
		return clrFlag;
	}

	public String getClrUserId() {
		return clrUserId;
	}

	public String getDurationSec() {
		return durationSec;
	}

	public String getLinCount() {
		return linCount;
	}

	public String getModelId() {
		return modelId;
	}

	public String getNmsCd() {
		return nmsCd;
	}

	public String getTrRoomId() {
		return trRoomId;
	}

	public String getWorkYn() {
		return workYn;
	}

	public void setAlarmEventType(String alarmEventType) {
		this.alarmEventType = alarmEventType;
	}

	public void setAlarmNo(String alarmNo) {
		this.alarmNo = alarmNo;
	}

	public void setBody(byte bytes[]) throws Exception {
		String items[] = this.split(bytes);
		setItem(items);
	}

	public void setItem(String items[]) {

		this.alarmEventType = items[0];
		this.clrUserId = items[1];
		this.clrFlag = items[2];
		this.clearTime = items[3];
		this.durationSec = items[4];
		this.alarmNo = items[5];
		this.setMngGroupCd(items[6]);
		this.clearRTime = items[7];
		this.setAlarmLevel(items[8]);
		this.trRoomId = items[9];
		this.modelId = items[10];
		this.setNeId(items[11]);
		this.nmsCd = items[12];
		this.workYn = items[13];
		this.linCount = items[14];
	}

	public void setClearRTime(String clearRTime) {
		this.clearRTime = clearRTime;
	}

	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}

	public void setClrFlag(String clrFlag) {
		this.clrFlag = clrFlag;
	}

	public void setClrUserId(String clrUserId) {
		this.clrUserId = clrUserId;
	}

	public void setDurationSec(String durationSec) {
		this.durationSec = durationSec;
	}

	public void setLinCount(String linCount) {
		this.linCount = linCount;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public void setNmsCd(String nmsCd) {
		this.nmsCd = nmsCd;
	}

	public void setTrRoomId(String trRoomId) {
		this.trRoomId = trRoomId;
	}

	public void setWorkYn(String workYn) {
		this.workYn = workYn;
	}

}

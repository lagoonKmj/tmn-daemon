package com.lagoon.tmn.teams.co.vo;

import java.sql.Timestamp;

import com.lagoon.tmn.teams.co.AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD;
import com.lagoon.tmn.teams.co.dao.dbo.OIV10223;
import com.lagoon.tmn.teams.fxms.dbo.OIV10210;
import com.lagoon.tmn.teams.fxms.dbo.OIV10400;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;

public class DetectedPortVo {

	private String portDescr; /* 포트명, 설명 */

	private String portType = "0";

	private String portState;

	private String portSubState;

	private String cardNm;

	private String slotNm;

	private String portSpeedCd;

	private int portNo;
	
	private boolean protectYn;
	
	private String portAlsNm;

	public DetectedPortVo() {
	}

	public void fill(OIV10400 o, OIV10210 o210) {
		o.setMounLocNum(o210 == null ? "" : o210.getConsItmNum());
		o.setEquipConsItmId(o210 == null ? "" : o210.getEquipConsItmId());
		o.setPortAlsNm(portAlsNm);
		Logger.logger.debug("portAlsNm:[{}], cardNm[{}]", portAlsNm , cardNm);
		o.setPortDesc(portDescr);
		o.setPortNum(String.valueOf(portNo));
		o.setPortSpeedCd(portSpeedCd == null ? TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode() : portSpeedCd);
		o.setPortTypCd(portType == null ? "0" : portType);
		o.setPingMObjYn(protectYn);
		
	}

	public String getCardNm() {
		return cardNm;
	}

	public String getPortDescr() {
		return portDescr;
	}

	public int getPortNo() {
		return portNo;
	}

	public String getPortSpeedCd() {
		return portSpeedCd;
	}

	public String getPortState() {
		return portState;
	}

	public String getPortSubState() {
		return portSubState;
	}

	public String getPortType() {
		return portType;
	}

	public String getSlotNm() {
		return slotNm;
	}

	public OIV10223 makeOIV10223(DetectedNeVo ne, OIV10400 oiv10400) {
		OIV10223 o = new OIV10223();

		o.setAuditId("ADANS-TMN");
		o.setAuditDtm(new Timestamp(System.currentTimeMillis()));
		o.setClctDtm(FxApi.getDate(ne.getDetectedMstime()) + "");
		if (oiv10400 != null) {
			o.setEquipPortId(oiv10400.getEquipPortId());
		}
		o.setPortDesc(portDescr);
		o.setPortSpeedCd(portSpeedCd);
		o.setEquipConsItmNm(cardNm);
		o.setEquipId(ne.getEquipId());

		return o;
	}

	public OIV10400 makeOIV10400(String equipId, OIV10210 o210, String equipPortId) {
		OIV10400 o = new OIV10400();
		if (o210 != null) {
			o.setEquipConsItmId(o210.getEquipConsItmId());
		} else {
			o.setEquipConsItmId(null);
		}

		o.setEquipId(equipId);
		o.setEquipPortId(equipPortId);
		o.setPollingMObjYn(false);
		o.setRgstCycl(0);

		fill(o, o210);

		return o;
	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}

	public void setPortDescr(String portDescr) {
		this.portDescr = portDescr;
	}

	public void setPortNo(int portNo) {
		this.portNo = portNo;
	}

	public void setPortSpeedCd(String portSpeedCd) {
		this.portSpeedCd = portSpeedCd;
	}

	public void setPortState(String portState) {
		this.portState = portState;
	}

	public void setPortSubState(String portSubState) {
		this.portSubState = portSubState;
	}

	public void setPortType(String portType) {
		this.portType = portType;
	}

	public void setSlotNm(String slotNm) {
		this.slotNm = slotNm;
	}

	public void testSet() {
		portDescr = "T-1-2-TEST";
		portType = "0";
		portState = "T-1-2-TEST";
		cardNm = "TEST-SLOT";
		portSpeedCd = "100M";
		portNo = 100;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(portNo);
		sb.append("|").append(slotNm);
		sb.append("|").append(cardNm);
		sb.append("|").append(portDescr);
		sb.append("|").append(portType);
		sb.append("|").append(portSpeedCd);
		return sb.toString();
	}
	
	public boolean getProtectYn(){
		return protectYn;
	}
	public void setProtectYn(boolean protectYn){
		this.protectYn = protectYn;
	}

	public String getPortAlsNm() {
		return portAlsNm;
	}

	public void setPortAlsNm(String portAlsNm) {
		this.portAlsNm = portAlsNm;
	}

}

package com.lagoon.tmn.teams.co.vo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.EQUIP_ATTR_CD;
import com.lagoon.tmn.teams.co.dao.dbo.UpdateOIV10100Dbo;
import com.lagoon.tmn.teams.fxms.dbo.OIV10100;
import com.lagoon.tmn.teams.fxms.dbo.OIV10104;
import com.lagoon.tmn.teams.fxms.dbo.OIV10106;
import com.lagoon.tmn.teams.fxms.dbo.OIV10110;
import com.lagoon.tmn.teams.fxms.dbo.OIV10178;

import fxms.bas.api.FxApi;

/**
 * 장비, EMS 등으로 부터 검색된 장비 정보<br>
 * equipId/equipMdlCd는 이미 존재하는 장비의 것이며 null이면 신규 장비를 나타낸다.<br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class DetectedNeVo {

	/** 장비ID */
	private String tid;
	private String ipAddr;
	private String type;
	private String sysname;
	private String vendor;
	private String model;
	private String verFw;
	private String verHw;
	private String verSw;
	private String connState;
	/** 네트워크명 */
	private String netName;

	private long detectedMstime;

	private String equipId; // 장비ID
	private String equipMfactCd; // DB의 제조사코드
	private String equipMdlCd; // DB의 장비모델코드
	private String equipIpAddr; // DB의 IP 주소
	private String swVerInfo; // DB의 SW 버전

	private List<DetectedPortVo> portList = new ArrayList<DetectedPortVo>();
	private List<DetectedCardVo> cardList = new ArrayList<DetectedCardVo>();

	private String emsId;
	private String emsInrEquipKeyVal;

	private String errmsg = null;

	public DetectedNeVo() {
		detectedMstime = System.currentTimeMillis();
	}

	/**
	 * 장비ID, 장비모델코드, IP주소를 설정한다.
	 * 
	 * @param mo
	 */
	public void fill(AdamsEquipVo mo) {
		equipId = mo.getEquipId();
		equipMdlCd = mo.getEquipMdlCd();
		equipIpAddr = mo.getEquipIpAddr();
		swVerInfo = mo.getSwVerInfo();
	}

	/**
	 * 
	 * @param slotNm
	 * @return
	 */
	public DetectedCardVo getCardBySlotNm(String slotNm) {
		for (DetectedCardVo e : cardList) {
			if (slotNm.equals(e.getSlotNm())) {
				return e;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param slotNm
	 * @return
	 */
	public List<DetectedCardVo> getCardChildren(String slotNm) {
		List<DetectedCardVo> ret = new ArrayList<DetectedCardVo>();

		for (DetectedCardVo e : cardList) {
			if (slotNm == null && e.getParentSlotNm() == null) {
				ret.add(e);
			} else if (slotNm != null && slotNm.equals(e.getParentSlotNm())) {
				ret.add(e);
			}
		}

		return ret;
	}

	public List<DetectedCardVo> getCardList() {
		return cardList;
	}

	public String getConnState() {
		return connState;
	}

	public long getDetectedMstime() {
		return detectedMstime;
	}

	public String getEmsId() {
		return emsId;
	}

	public String getEmsInrEquipKeyVal() {
		return emsInrEquipKeyVal;
	}

	public String getEquipId() {
		return equipId;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	public String getEquipMfactCd() {
		return equipMfactCd;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getModel() {
		return model;
	}

	public String getNetName() {
		return netName;
	}

	public List<DetectedPortVo> getPortList() {
		return portList;
	}

	public DetectedPortVo getPort(String portSesc) {
		for (DetectedPortVo e : portList) {
			if (portSesc != null && portSesc.equals(e.getPortDescr())) {
				return e;
			}
		}
		return null;
	}

	public String getSysname() {
		return sysname;
	}

	public String getTid() {
		return tid;
	}

	public String getType() {
		return type;
	}

	public String getVendor() {
		return vendor;
	}

	public String getVerFw() {
		return verFw;
	}

	public String getVerHw() {
		return verHw;
	}

	public String getVerSw() {
		return verSw;
	}

	public boolean isCollectOk() {
		return errmsg == null;
	}

	/**
	 * adams_teams_conf.xml 파일안에 QID=UPDATE_OIV10100__BY_equipId 에 사용될 객체를 만든다.
	 * 
	 * @return
	 */
	public OIV10100 makeOIV10100() {
		OIV10100 o = new OIV10100();

		o.setEquipId(equipId);
		o.setEquipSysNm(sysname);
		o.setSwVerInfo(verSw);
		o.setEquipIpAddr(ipAddr);

		return o;
		/*
		 * update OIV10100 set AUDIT_ID = 'ADAMS-TMN' , AUDIT_DTM = sysdate ,
		 * SW_VER_INFO = $getSwVerInfo() , EQUIP_SYS_NM = $getEquipSysNm() ,
		 * EQUIP_IP_ADDR = $getEquipIpAddr() where EQUIP_ID = $getEquipId()
		 */
	}

	public OIV10104 makeOIV10104(String auditId, String connStVal) {
		OIV10104 o = new OIV10104();
		o.setAuditId(auditId);
		o.setEquipId(equipId);
		o.setConnStVal(connStVal);
		return o;
	}

	public OIV10106 makeOIV10106() {
		OIV10106 o = new OIV10106();

		o.setEquipId(equipId);
		o.setEmsEquipNum("0");
		o.setEmsId(emsId);
		o.setEmsInrEquipKeyVal(emsInrEquipKeyVal);

		return o;

	}

	public List<OIV10110> makeOIV10110() {

		List<OIV10110> ret = new ArrayList<OIV10110>();
		OIV10110 e;

		if (isDiff(verSw, swVerInfo)) {
			e = makeOIV10110("SW_VER_INFO", swVerInfo, verSw);
			if (e != null) {
				ret.add(e);
			}
		}

		if (isDiff(ipAddr, equipIpAddr)) {
			e = makeOIV10110("EQUIP_IP_ADDR", equipIpAddr, ipAddr);
			if (e != null) {
				ret.add(e);
			}
		}

		return ret;
	}

	/**
	 * adams_teams_conf.xml 파일안에 QID=UPDATE_OIV10178__BY_equipId 에 사용될 객체를 만든다.
	 * 
	 * @return
	 */
	public OIV10178 makeOIV10178() {
		OIV10178 o = new OIV10178();

		o.setClctDtm(FxApi.getDate(detectedMstime) + "");
		o.setEquipId(equipId);
		o.setEquipSysNm(sysname);
		o.setFwVerInfo(verSw);
		o.setEquipIpAddr(ipAddr);
		o.setOperEquipMdlCd(equipMdlCd);

		return o;
		/*
		 * update OIV10178 set AUDIT_ID = 'ADAMS-TMN' , AUDIT_DTM = sysdate ,
		 * CLCT_DTM = $getClctDtm() , EQUIP_IP_ADDR = $getEquipIpAddr() ,
		 * EQUIP_SYS_NM = $getEquipSysNm() , FW_VER_INFO = $getFwVerInfo() ,
		 * OPER_EQUIP_MDL_CD = $getOperEquipMdlCd() where EQUIP_ID =
		 * $getEquipId()
		 */
	}

	public UpdateOIV10100Dbo makeUpdateOIV10100Dbo() {
		UpdateOIV10100Dbo o = new UpdateOIV10100Dbo();
		o.setAuditId("ADANS-TMN");
		o.setAuditDtm(new Timestamp(System.currentTimeMillis()));
		o.setEquipId(equipId);
		o.setEquipIpAddr(ipAddr);
		o.setEquipSysNm(sysname);
		o.setSwVerInfo(verFw);

		return o;
	}

	public void setCardList(List<DetectedCardVo> slotList) {
		this.cardList = slotList;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public void setConnState(String connState) {
		this.connState = connState;
	}

	public void setDetectedMstime(long detectedMstime) {
		this.detectedMstime = detectedMstime;
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

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	public void setEquipMfactCd(String equipMfactCd) {
		this.equipMfactCd = equipMfactCd;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public void setPortList(List<DetectedPortVo> portList) {
		this.portList = portList;
	}

	public void setSysname(String sysname) {
		this.sysname = sysname;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setVerFw(String verFw) {
		this.verFw = verFw;
	}

	public void setVerHw(String verHw) {
		this.verHw = verHw;
	}

	public void setVerSw(String verSw) {
		this.verSw = verSw;
	}

	private boolean isDiff(String s1, String s2) {

		if (s1 == null && s2 == null) {
			return false;
		}

		if (s1 != null && s1.equals(s2)) {
			return false;
		}

		return true;
	}

	private OIV10110 makeOIV10110(String field, String bfAttrVal, String attrVal) {

		EQUIP_ATTR_CD cd = EQUIP_ATTR_CD.getCd(field);
		if (cd == null) {
			return null;
		}

		OIV10110 o = new OIV10110();
		o.setAttrVal(attrVal);
		o.setBfAttrVal(bfAttrVal);
		o.setEquipAttrCd(cd.name());
		o.setEquipAttrChgClCd(AdamsCfg.EQUIP_ATTR_CHG_CL_CD.C0.name());
		o.setEquipId(equipId);
		return o;
	}

}

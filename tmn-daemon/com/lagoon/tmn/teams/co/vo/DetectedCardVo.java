package com.lagoon.tmn.teams.co.vo;

import java.sql.Timestamp;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.OIV10209;
import com.lagoon.tmn.teams.fxms.dbo.OIV10210;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221;
import com.lagoon.tmn.teams.fxms.dbo.OIV10222;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;

/**
 * 전송망 장비 슬롯
 * 
 * @author subkjh
 * 
 */
public class DetectedCardVo {

	private final String DEFAULT_EQUIP_CONS_ITM_CD = "0000000000";

	public static DetectedCardVo makeStaticShelf(String shelfName) {
		DetectedCardVo shelf = new DetectedCardVo();
		shelf.cardType = AdamsCfg.EQUIP_CONS_ITM_TYP_CD.Container.getCode();
		shelf.cardModel = "셀프";
		shelf.cardNm = shelfName;
		shelf.slotNm = shelfName;
		shelf.isVirtual = true;
		return shelf;
	}

	/** 장비 TID */
	private String tid;

	/** 슬롯번호 */
	private int slotNo;

	/** 슬롯 명 */
	private String slotNm;

	/** 상위 구성품명 */
	private String parentSlotNm;

	/** 카드 종류 */
	private int cardType = AdamsCfg.EQUIP_CONS_ITM_TYP_CD.Module.getCode();

	/** 카드 일련번호 */
	private String cardSerialNo;

	/** 카드 모델명 */
	private String cardModel;

	/** 카드 설명 */
	private String cardNm;

	/** 카드 상태 */
	private String cardState;

	private String cardSubState;

	/** 카드 용량 */
	private String capacityStr;

	private String capacityCd;

	private String ver;

	private String ip;

	private int portCnt = 0;

	/** 슬롯이 비워있는지 여부 */
	private boolean emptySlot = false;

	/** 가상 카드 여부 */
	private boolean isVirtual = false;

	private String pollingMObjYn = "N";

	public void fill(OIV10210 o) {
		o.setConsItmNum(slotNo + "");
		o.setEquipConsItmAlsNm(cardNm + (cardModel == null ? "" : ":" + cardModel));
		o.setEquipConsItmDesc(cardModel);
		o.setEquipConsItmNm(cardNm);
		o.setEquipConsItmStVal(cardState);
		o.setEquipConsItmSubStVal(cardSubState);
		o.setMfactSerNum(cardSerialNo);
		o.setPortCnt(portCnt);
		o.setRepIpAddr(ip);
		o.setVerInfo(ver);
		Logger.logger.debug("Accepted cardModel[{}]:cardNm[{}]:EquipConsItmAlsNm[{}], Completed",cardModel,  o.getEquipConsItmNm(),o.getEquipConsItmAlsNm());
		o.setTrmsNetEquipCapaClCd(capacityCd == null ? AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode() : capacityCd);
	}

	public String getCardModel() {
		return cardModel;
	}

	public String getCardNm() {
		return cardNm;
	}

	public String getCardSerialNo() {
		return cardSerialNo;
	}

	public String getCardState() {
		return cardState;
	}

	public int getCardType() {
		return cardType;
	}

	public String getIp() {
		return ip;
	}

	public String getParentSlotNm() {
		return parentSlotNm;
	}

	public int getPortCnt() {
		return portCnt;
	}

	public String getSlotNm() {
		return slotNm;
	}

	public int getSlotNo() {
		return slotNo;
	}

	public String getTid() {
		return tid;
	}

	public String getVer() {
		return ver;
	}

	public boolean isEmptySlot() {
		return emptySlot;
	}

	public OIV10210 makeOIV10210(DetectedNeVo ne, OIV10209 o209, String equipConsItmId) {
		OIV10210 o = new OIV10210();

		fill(o);

		o.setAltPsblYn("N");
		o.setAutoMgmtYn("N");
		o.setBkEquipConsItmId(null);
		o.setBkEquipId(null);
		o.setEquipConsItmId(equipConsItmId);
		o.setEquipConsItmUsgCd("");
		o.setEquipId(ne.getEquipId());
		o.setFrntBackClCd("F");
		o.setHostNm(null);
		o.setMemo(null);
		o.setPollingMObjYn(pollingMObjYn);
		o.setRgstDtm(FxApi.getDate(ne.getDetectedMstime()) + "");
		o.setSupEquipConsItmId(null);

		if (o209 != null) {
			o.setEquipConsItmCd(o209.getEquipConsItmCd());
		} else {
			o.setEquipConsItmCd(DEFAULT_EQUIP_CONS_ITM_CD);
		}

		o.setWvlengVal(null);

		return o;
	}

	/**
	 * OIV20221에 기록할 객체를 생성한다.
	 * 
	 * @param ne
	 * @param oiv10210
	 * @return
	 */
	public OIV10221 makeOIV10221(DetectedNeVo ne, OIV10210 oiv10210) {
		OIV10221 o = new OIV10221();

		o.setClctDtm(FxApi.getDate(ne.getDetectedMstime()) + "");
		if (oiv10210 != null) {
			o.setEquipConsItmCd(oiv10210.getEquipConsItmCd());
			o.setEquipConsItmId(oiv10210.getEquipConsItmId());
		}

		if (o.getEquipConsItmCd() == null || o.getEquipConsItmCd().length() == 0) {
			o.setEquipConsItmCd(DEFAULT_EQUIP_CONS_ITM_CD);
		}

		o.setEquipConsItmNm(cardNm);
		o.setEquipId(ne.getEquipId());
		o.setEquipConsItmActStNm(cardState == null ? "-" : cardState);
		o.setEquipConsItmTypNm(cardModel == null ? "-" : cardModel);
		o.setScardNm(slotNm == null ? "-" : slotNm);

		return o;
	}

	public OIV10222 makeOIV10222(DetectedNeVo ne) {
		OIV10222 o = new OIV10222();

		o.setAuditId("ADANS-TMN");
		o.setAuditDtm(new Timestamp(System.currentTimeMillis()));
		o.setEquipConsItmNm(cardModel);
		o.setEquipConsItmTypVal(String.valueOf(cardType));
		o.setEquipMdlCd(ne.getEquipMdlCd());
		return o;
	}

	public void setCardModel(String cardModel) {
		this.cardModel = cardModel;

	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}

	public void setCardSerialNo(String cardSerialNo) {
		this.cardSerialNo = cardSerialNo;
	}

	public void setCardState(String cardState) {
		this.cardState = cardState;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public void setEmptySlot(boolean emptySlot) {
		this.emptySlot = emptySlot;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setParentSlotNm(String parentSlotNm) {
		this.parentSlotNm = parentSlotNm;
	}

	public void setPortCnt(int portCnt) {
		this.portCnt = portCnt;
	}

	public void setSlotNm(String slotNm) {
		this.slotNm = slotNm;
	}

	public void setSlotNo(int slotNo) {
		this.slotNo = slotNo;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public void testSet() {
		slotNo = 123;
		cardType = AdamsCfg.EQUIP_CONS_ITM_TYP_CD.Container.getCode();
		cardSerialNo = "12334566778";
		cardModel = "S0116U"; // 1507911506
		cardNm = "TEST-SLOT";
		cardState = "ACT";
		ver = "1.01";
		ip = "192.168.1.1";
		portCnt = 10;
	}

	public String getCardSubState() {
		return cardSubState;
	}

	public void setCardSubState(String cardSubState) {
		this.cardSubState = cardSubState;
	}

	public String getCapacityStr() {
		return capacityStr;
	}

	public void setCapacityStr(String capacityStr) {
		this.capacityStr = capacityStr;
	}

	public String getCapacityCd() {
		return capacityCd;
	}

	public void setCapacityCd(String capacityCd) {
		this.capacityCd = capacityCd;
	}

	public boolean isVirtual() {
		return isVirtual;
	}

	public void setVirtual(boolean isVirtual) {
		this.isVirtual = isVirtual;
	}
	
	public String getPollingMObjYn() {
		return pollingMObjYn;
	}

	public void setPollingMObjYn(String pollingMObjYn) {
		this.pollingMObjYn = pollingMObjYn;
	}

}

package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28101", comment = "전송장비광레벨수집내역")
public class OCL28101 {

	public OCL28101() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "PORT_NM", size = 100, comment = "포트명")
	private String portNm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "MODULE_PUTON_YN", size = 1, comment = "모듈장착여부", defValue = "'N'")
	private String modulePutonYn = "N";

	@FxColumn(name = "CORE_NUM", size = 50, nullable = true, comment = "코어번호")
	private String coreNum;

	@FxColumn(name = "SUPRT_SGNL_VAL", size = 100, nullable = true, comment = "지원신호값")
	private String suprtSgnlVal;

	@FxColumn(name = "TX_WVLENG_VAL", size = 7, nullable = true, comment = "TX파장값")
	private Float txWvlengVal;

	@FxColumn(name = "RX_WVLENG_VAL", size = 7, nullable = true, comment = "RX파장값")
	private Float rxWvlengVal;

	@FxColumn(name = "DIST_INFO", size = 40, nullable = true, comment = "거리정보")
	private String distInfo;

	@FxColumn(name = "TX_PWR_VAL", size = 10, nullable = true, comment = "TX파워값")
	private Float txPwrVal;

	@FxColumn(name = "RX_PWR_VAL", size = 10, nullable = true, comment = "RX파워값")
	private Float rxPwrVal;

	@FxColumn(name = "TMPR_VAL", size = 15, nullable = true, comment = "온도값")
	private Float tmprVal;

	@FxColumn(name = "SCARD_NM", size = 50, nullable = true, comment = "SLOT카드명")
	private String scardNm;

	@FxColumn(name = "SCARD_TYP_NM", size = 50, nullable = true, comment = "SLOT카드종류명")
	private String scardTypNm;

	/**
	 * 장비ID
	 * 
	 * @return 장비ID
	 */
	public String getEquipId() {
		return equipId;
	}

	/**
	 * 장비ID
	 *
	 * @param equipId
	 *            장비ID
	 */
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	/**
	 * 포트명
	 * 
	 * @return 포트명
	 */
	public String getPortNm() {
		return portNm;
	}

	/**
	 * 포트명
	 *
	 * @param portNm
	 *            포트명
	 */
	public void setPortNm(String portNm) {
		this.portNm = portNm;
	}

	/**
	 * 최종변경자ID
	 * 
	 * @return 최종변경자ID
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * 최종변경자ID
	 *
	 * @param auditId
	 *            최종변경자ID
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * 최종변경일시
	 * 
	 * @return 최종변경일시
	 */
	public Timestamp getAuditDtm() {
		return auditDtm;
	}

	/**
	 * 최종변경일시
	 *
	 * @param auditDtm
	 *            최종변경일시
	 */
	public void setAuditDtm(Timestamp auditDtm) {
		this.auditDtm = auditDtm;
	}

	/**
	 * 모듈장착여부
	 * 
	 * @return 모듈장착여부
	 */
	public String isModulePutonYn() {
		return modulePutonYn;
	}

	/**
	 * 모듈장착여부
	 *
	 * @param modulePutonYn
	 *            모듈장착여부
	 */
	public void setModulePutonYn(String modulePutonYn) {
		this.modulePutonYn = modulePutonYn;
	}

	/**
	 * 코어번호
	 * 
	 * @return 코어번호
	 */
	public String getCoreNum() {
		return coreNum;
	}

	/**
	 * 코어번호
	 *
	 * @param coreNum
	 *            코어번호
	 */
	public void setCoreNum(String coreNum) {
		this.coreNum = coreNum;
	}

	/**
	 * 지원신호값
	 * 
	 * @return 지원신호값
	 */
	public String getSuprtSgnlVal() {
		return suprtSgnlVal;
	}

	/**
	 * 지원신호값
	 *
	 * @param suprtSgnlVal
	 *            지원신호값
	 */
	public void setSuprtSgnlVal(String suprtSgnlVal) {
		this.suprtSgnlVal = suprtSgnlVal;
	}

	/**
	 * TX파장값
	 * 
	 * @return TX파장값
	 */
	public Float getTxWvlengVal() {
		return txWvlengVal;
	}

	/**
	 * TX파장값
	 *
	 * @param txWvlengVal
	 *            TX파장값
	 */
	public void setTxWvlengVal(Float txWvlengVal) {
		this.txWvlengVal = txWvlengVal;
	}

	/**
	 * RX파장값
	 * 
	 * @return RX파장값
	 */
	public Float getRxWvlengVal() {
		return rxWvlengVal;
	}

	/**
	 * RX파장값
	 *
	 * @param rxWvlengVal
	 *            RX파장값
	 */
	public void setRxWvlengVal(Float rxWvlengVal) {
		this.rxWvlengVal = rxWvlengVal;
	}

	/**
	 * 거리정보
	 * 
	 * @return 거리정보
	 */
	public String getDistInfo() {
		return distInfo;
	}

	/**
	 * 거리정보
	 *
	 * @param distInfo
	 *            거리정보
	 */
	public void setDistInfo(String distInfo) {
		this.distInfo = distInfo;
	}

	/**
	 * TX파워값
	 * 
	 * @return TX파워값
	 */
	public Float getTxPwrVal() {
		return txPwrVal;
	}

	/**
	 * TX파워값
	 *
	 * @param txPwrVal
	 *            TX파워값
	 */
	public void setTxPwrVal(Float txPwrVal) {
		this.txPwrVal = txPwrVal;
	}

	/**
	 * RX파워값
	 * 
	 * @return RX파워값
	 */
	public Float getRxPwrVal() {
		return rxPwrVal;
	}

	/**
	 * RX파워값
	 *
	 * @param rxPwrVal
	 *            RX파워값
	 */
	public void setRxPwrVal(Float rxPwrVal) {
		this.rxPwrVal = rxPwrVal;
	}

	/**
	 * 온도값
	 * 
	 * @return 온도값
	 */
	public Float getTmprVal() {
		return tmprVal;
	}

	/**
	 * 온도값
	 *
	 * @param tmprVal
	 *            온도값
	 */
	public void setTmprVal(Float tmprVal) {
		this.tmprVal = tmprVal;
	}

	/**
	 * SLOT카드명
	 * 
	 * @return SLOT카드명
	 */
	public String getScardNm() {
		return scardNm;
	}

	/**
	 * SLOT카드명
	 *
	 * @param scardNm
	 *            SLOT카드명
	 */
	public void setScardNm(String scardNm) {
		this.scardNm = scardNm;
	}

	/**
	 * SLOT카드종류명
	 * 
	 * @return SLOT카드종류명
	 */
	public String getScardTypNm() {
		return scardTypNm;
	}

	/**
	 * SLOT카드종류명
	 *
	 * @param scardTypNm
	 *            SLOT카드종류명
	 */
	public void setScardTypNm(String scardTypNm) {
		this.scardTypNm = scardTypNm;
	}
	
	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public Long getInspSerNum() {
		return inspSerNum;
	}

	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public void setInspSerNum(Long inspSerNum) {
		this.inspSerNum = inspSerNum;
	}
}

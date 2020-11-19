package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28102", comment = "전송장비성능수집내역")
public class OCL28102 {

	public OCL28102() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "PORT_DESC", size = 500, comment = "포트설명")
	private String portDesc;

	@FxColumn(name = "CLCT_PRFM_NM", size = 100, comment = "수집성능명")
	private String clctPrfmNm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "CURR_MIN15_PRFM_VAL", size = 15, nullable = true, comment = "현재15분성능값")
	private Long currMin15PrfmVal;

	@FxColumn(name = "BF_MIN15_PRFM_VAL", size = 15, nullable = true, comment = "이전15분성능값")
	private Long bfMin15PrfmVal;

	@FxColumn(name = "CURR_D1_PRFM_VAL", size = 15, nullable = true, comment = "현재1일성능값")
	private Long currD1PrfmVal;

	@FxColumn(name = "BF_D1_PRFM_VAL", size = 15, nullable = true, comment = "이전1일성능값")
	private Long bfD1PrfmVal;

	@FxColumn(name = "SCARD_NM", size = 50, comment = "SLOT카드명")
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
	 * 포트설명
	 * 
	 * @return 포트설명
	 */
	public String getPortDesc() {
		return portDesc;
	}

	/**
	 * 포트설명
	 *
	 * @param portDesc
	 *            포트설명
	 */
	public void setPortDesc(String portDesc) {
		this.portDesc = portDesc;
	}

	/**
	 * 수집성능명
	 * 
	 * @return 수집성능명
	 */
	public String getClctPrfmNm() {
		return clctPrfmNm;
	}

	/**
	 * 수집성능명
	 *
	 * @param clctPrfmNm
	 *            수집성능명
	 */
	public void setClctPrfmNm(String clctPrfmNm) {
		this.clctPrfmNm = clctPrfmNm;
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
	 * 현재15분성능값
	 * 
	 * @return 현재15분성능값
	 */
	public Long getCurrMin15PrfmVal() {
		return currMin15PrfmVal;
	}

	/**
	 * 현재15분성능값
	 *
	 * @param currMin15PrfmVal
	 *            현재15분성능값
	 */
	public void setCurrMin15PrfmVal(Long currMin15PrfmVal) {
		this.currMin15PrfmVal = currMin15PrfmVal;
	}

	/**
	 * 이전15분성능값
	 * 
	 * @return 이전15분성능값
	 */
	public Long getBfMin15PrfmVal() {
		return bfMin15PrfmVal;
	}

	/**
	 * 이전15분성능값
	 *
	 * @param bfMin15PrfmVal
	 *            이전15분성능값
	 */
	public void setBfMin15PrfmVal(Long bfMin15PrfmVal) {
		this.bfMin15PrfmVal = bfMin15PrfmVal;
	}

	/**
	 * 현재1일성능값
	 * 
	 * @return 현재1일성능값
	 */
	public Long getCurrD1PrfmVal() {
		return currD1PrfmVal;
	}

	/**
	 * 현재1일성능값
	 *
	 * @param currD1PrfmVal
	 *            현재1일성능값
	 */
	public void setCurrD1PrfmVal(Long currD1PrfmVal) {
		this.currD1PrfmVal = currD1PrfmVal;
	}

	/**
	 * 이전1일성능값
	 * 
	 * @return 이전1일성능값
	 */
	public Long getBfD1PrfmVal() {
		return bfD1PrfmVal;
	}

	/**
	 * 이전1일성능값
	 *
	 * @param bfD1PrfmVal
	 *            이전1일성능값
	 */
	public void setBfD1PrfmVal(Long bfD1PrfmVal) {
		this.bfD1PrfmVal = bfD1PrfmVal;
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

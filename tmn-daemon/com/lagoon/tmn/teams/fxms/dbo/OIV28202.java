package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.13 10:17
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV28202", comment = "전송망장비모델별카드템플릿")
public class OIV28202 implements Serializable {

	public OIV28202() {
	}

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "SHLF_DESC", size = 100, comment = "SHELF설명")
	private String shlfDesc;

	@FxColumn(name = "SCARD_DESC", size = 100, comment = "SLOT카드설명")
	private String scardDesc;

	@FxColumn(name = "SCARD_DESC_PTTN_TYP_VAL", size = 50, comment = "SLOT카드설명패턴유형값")
	private String scardDescPttnTypVal;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "PTTN_TYP_CD", size = 2, nullable = true, comment = "패턴유형코드")
	private String pttnTypCd;

	@FxColumn(name = "CL_CHAR_STR_VAL", size = 10, nullable = true, comment = "구분문자열값")
	private String clCharStrVal;

	@FxColumn(name = "PORT_CNT", size = 5, nullable = true, comment = "포트개수")
	private Integer portCnt;

	@FxColumn(name = "MEMO_CTT", size = 400, nullable = true, comment = "메모내용")
	private String memoCtt;

	/**
	 * 장비모델코드
	 * 
	 * @return 장비모델코드
	 */
	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	/**
	 * 장비모델코드
	 *
	 * @param equipMdlCd
	 *            장비모델코드
	 */
	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	/**
	 * SHELF설명
	 * 
	 * @return SHELF설명
	 */
	public String getShlfDesc() {
		return shlfDesc;
	}

	/**
	 * SHELF설명
	 *
	 * @param shlfDesc
	 *            SHELF설명
	 */
	public void setShlfDesc(String shlfDesc) {
		this.shlfDesc = shlfDesc;
	}

	/**
	 * SLOT카드설명
	 * 
	 * @return SLOT카드설명
	 */
	public String getScardDesc() {
		return scardDesc;
	}

	/**
	 * SLOT카드설명
	 *
	 * @param scardDesc
	 *            SLOT카드설명
	 */
	public void setScardDesc(String scardDesc) {
		this.scardDesc = scardDesc;
	}

	/**
	 * SLOT카드설명패턴유형값
	 * 
	 * @return SLOT카드설명패턴유형값
	 */
	public String getScardDescPttnTypVal() {
		return scardDescPttnTypVal;
	}

	/**
	 * SLOT카드설명패턴유형값
	 *
	 * @param scardDescPttnTypVal
	 *            SLOT카드설명패턴유형값
	 */
	public void setScardDescPttnTypVal(String scardDescPttnTypVal) {
		this.scardDescPttnTypVal = scardDescPttnTypVal;
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
	 * 패턴유형코드
	 * 
	 * @return 패턴유형코드
	 */
	public String getPttnTypCd() {
		return pttnTypCd;
	}

	/**
	 * 패턴유형코드
	 *
	 * @param pttnTypCd
	 *            패턴유형코드
	 */
	public void setPttnTypCd(String pttnTypCd) {
		this.pttnTypCd = pttnTypCd;
	}

	/**
	 * 구분문자열값
	 * 
	 * @return 구분문자열값
	 */
	public String getClCharStrVal() {
		return clCharStrVal;
	}

	/**
	 * 구분문자열값
	 *
	 * @param clCharStrVal
	 *            구분문자열값
	 */
	public void setClCharStrVal(String clCharStrVal) {
		this.clCharStrVal = clCharStrVal;
	}

	/**
	 * 포트개수
	 * 
	 * @return 포트개수
	 */
	public Integer getPortCnt() {
		return portCnt;
	}

	/**
	 * 포트개수
	 *
	 * @param portCnt
	 *            포트개수
	 */
	public void setPortCnt(Integer portCnt) {
		this.portCnt = portCnt;
	}

	/**
	 * 메모내용
	 * 
	 * @return 메모내용
	 */
	public String getMemoCtt() {
		return memoCtt;
	}

	/**
	 * 메모내용
	 *
	 * @param memoCtt
	 *            메모내용
	 */
	public void setMemoCtt(String memoCtt) {
		this.memoCtt = memoCtt;
	}
}

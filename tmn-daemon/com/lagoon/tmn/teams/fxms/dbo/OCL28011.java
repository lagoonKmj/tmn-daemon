package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.23 16:51
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28011", comment = "전송장비항목별건수수집내역")
public class OCL28011  {

	public OCL28011() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "CLCT_ITM_CL_NM", size = 20, comment = "수집항목구분명")
	private String clctItmClNm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "CLCT_ITM_CTT", size = 100, nullable = true, comment = "수집항목내용")
	private String clctItmCtt;

	@FxColumn(name = "CMPR_CTT", size = 1000, nullable = true, comment = "비교내용")
	private String cmprCtt;

	@FxColumn(name = "NORM_CNT", size = 10, nullable = true, comment = "정상건수")
	private long normCnt;

	@FxColumn(name = "ABN_CNT", size = 10, nullable = true, comment = "비정상건수")
	private long abnCnt;

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
	 * 수집항목구분명
	 * 
	 * @return 수집항목구분명
	 */
	public String getClctItmClNm() {
		return clctItmClNm;
	}

	/**
	 * 수집항목구분명
	 *
	 * @param clctItmClNm
	 *            수집항목구분명
	 */
	public void setClctItmClNm(String clctItmClNm) {
		this.clctItmClNm = clctItmClNm;
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
	 * 수집항목내용
	 * 
	 * @return 수집항목내용
	 */
	public String getClctItmCtt() {
		return clctItmCtt;
	}

	/**
	 * 수집항목내용
	 *
	 * @param clctItmCtt
	 *            수집항목내용
	 */
	public void setClctItmCtt(String clctItmCtt) {
		this.clctItmCtt = clctItmCtt;
	}

	/**
	 * 비교내용
	 * 
	 * @return 비교내용
	 */
	public String getCmprCtt() {
		return cmprCtt;
	}

	/**
	 * 비교내용
	 *
	 * @param cmprCtt
	 *            비교내용
	 */
	public void setCmprCtt(String cmprCtt) {
		this.cmprCtt = cmprCtt;
	}

	/**
	 * 정상건수
	 * 
	 * @return 정상건수
	 */
	public long getNormCnt() {
		return normCnt;
	}

	/**
	 * 정상건수
	 *
	 * @param normCnt
	 *            정상건수
	 */
	public void setNormCnt(long normCnt) {
		this.normCnt = normCnt;
	}

	/**
	 * 비정상건수
	 * 
	 * @return 비정상건수
	 */
	public long getAbnCnt() {
		return abnCnt;
	}

	/**
	 * 비정상건수
	 *
	 * @param abnCnt
	 *            비정상건수
	 */
	public void setAbnCnt(long abnCnt) {
		this.abnCnt = abnCnt;
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

package com.lagoon.tmn.teams.fxms.dbo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.12.18 18:25
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28190", comment = "전송장비일일점검내역")
public class OCL28190 {

	public OCL28190() {
	}

	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	// @FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	// private String auditId;
	//
	// @FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue =
	// "sysdate")
	// private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_ID", size = 12, nullable = true, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "CLCT_DTM", size = 14, nullable = true, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "INSP_SUSS_YN", size = 1, comment = "점검성공여부", defValue = "'N'")
	private String inspSussYn = "N";

	/**
	 * 점검일련번호
	 * 
	 * @return 점검일련번호
	 */
	public Long getInspSerNum() {
		return inspSerNum;
	}

	/**
	 * 점검일련번호
	 *
	 * @param inspSerNum
	 *            점검일련번호
	 */
	public void setInspSerNum(Long inspSerNum) {
		this.inspSerNum = inspSerNum;
	}

	// /**
	// * 최종변경자ID
	// *
	// * @return 최종변경자ID
	// */
	// public String getAuditId() {
	// return auditId;
	// }
	//
	// /**
	// * 최종변경자ID
	// *
	// * @param auditId
	// * 최종변경자ID
	// */
	// public void setAuditId(String auditId) {
	// this.auditId = auditId;
	// }
	//
	// /**
	// * 최종변경일시
	// *
	// * @return 최종변경일시
	// */
	// public Timestamp getAuditDtm() {
	// return auditDtm;
	// }
	//
	// /**
	// * 최종변경일시
	// *
	// * @param auditDtm
	// * 최종변경일시
	// */
	// public void setAuditDtm(Timestamp auditDtm) {
	// this.auditDtm = auditDtm;
	// }

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
	 * 수집일시
	 * 
	 * @return 수집일시
	 */
	public String getClctDtm() {
		return clctDtm;
	}

	/**
	 * 수집일시
	 *
	 * @param clctDtm
	 *            수집일시
	 */
	public void setClctDtm(String clctDtm) {
		this.clctDtm = clctDtm;
	}

	/**
	 * 점검성공여부
	 * 
	 * @return 점검성공여부
	 */
	public String getInspSussYn() {
		return inspSussYn;
	}

	/**
	 * 점검성공여부
	 *
	 * @param inspSussYn
	 *            점검성공여부
	 */
	public void setInspSussYn(String inspSussYn) {
		this.inspSussYn = inspSussYn;
	}
}

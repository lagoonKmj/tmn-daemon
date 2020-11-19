package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.05 09:42
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10222", comment = "전송장비미등록구성품기본")
public class OIV10222 implements Serializable {

	public OIV10222() {
	}

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "EQUIP_CONS_ITM_NM", size = 60, comment = "장비구성품명")
	private String equipConsItmNm;

	@FxColumn(name = "EQUIP_CONS_ITM_TYP_VAL", size = 100, comment = "장비구성품종류값")
	private String equipConsItmTypVal;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

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
	 * 장비구성품명
	 * 
	 * @return 장비구성품명
	 */
	public String getEquipConsItmNm() {
		return equipConsItmNm;
	}

	/**
	 * 장비구성품명
	 *
	 * @param equipConsItmNm
	 *            장비구성품명
	 */
	public void setEquipConsItmNm(String equipConsItmNm) {
		this.equipConsItmNm = equipConsItmNm;
	}

	/**
	 * 장비구성품종류값
	 * 
	 * @return 장비구성품종류값
	 */
	public String getEquipConsItmTypVal() {
		return equipConsItmTypVal;
	}

	/**
	 * 장비구성품종류값
	 *
	 * @param equipConsItmTypVal
	 *            장비구성품종류값
	 */
	public void setEquipConsItmTypVal(String equipConsItmTypVal) {
		this.equipConsItmTypVal = equipConsItmTypVal;
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
}

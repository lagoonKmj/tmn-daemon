package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.07 10:28
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10110", comment = "장비속성변경이력")
public class OIV10110 implements Serializable {

	public OIV10110() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_ATTR_CD", size = 3, comment = "장비속성코드")
	private String equipAttrCd;

	@FxColumn(name = "CHG_DTM", size = 14, comment = "변경일시")
	private String chgDtm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "ATTR_VAL", size = 2000, nullable = true, comment = "속성값")
	private String attrVal;

	@FxColumn(name = "BF_ATTR_VAL", size = 2000, nullable = true, comment = "이전속성값")
	private String bfAttrVal;

	@FxColumn(name = "EQUIP_ATTR_BAT_HST_SER_NUM", size = 15, nullable = true, comment = "장비속성일괄이력일련번호")
	private Long equipAttrBatHstSerNum;

	@FxColumn(name = "EQUIP_ATTR_CHG_CL_CD", size = 2, comment = "장비속성변경구분코드")
	private String equipAttrChgClCd;

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
	 * 장비속성코드
	 * 
	 * @return 장비속성코드
	 */
	public String getEquipAttrCd() {
		return equipAttrCd;
	}

	/**
	 * 장비속성코드
	 *
	 * @param equipAttrCd
	 *            장비속성코드
	 */
	public void setEquipAttrCd(String equipAttrCd) {
		this.equipAttrCd = equipAttrCd;
	}

	/**
	 * 변경일시
	 * 
	 * @return 변경일시
	 */
	public String getChgDtm() {
		return chgDtm;
	}

	/**
	 * 변경일시
	 *
	 * @param chgDtm
	 *            변경일시
	 */
	public void setChgDtm(String chgDtm) {
		this.chgDtm = chgDtm;
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
	 * 속성값
	 * 
	 * @return 속성값
	 */
	public String getAttrVal() {
		return attrVal;
	}

	/**
	 * 속성값
	 *
	 * @param attrVal
	 *            속성값
	 */
	public void setAttrVal(String attrVal) {
		this.attrVal = attrVal;
	}

	/**
	 * 이전속성값
	 * 
	 * @return 이전속성값
	 */
	public String getBfAttrVal() {
		return bfAttrVal;
	}

	/**
	 * 이전속성값
	 *
	 * @param bfAttrVal
	 *            이전속성값
	 */
	public void setBfAttrVal(String bfAttrVal) {
		this.bfAttrVal = bfAttrVal;
	}

	/**
	 * 장비속성일괄이력일련번호
	 * 
	 * @return 장비속성일괄이력일련번호
	 */
	public Long getEquipAttrBatHstSerNum() {
		return equipAttrBatHstSerNum;
	}

	/**
	 * 장비속성일괄이력일련번호
	 *
	 * @param equipAttrBatHstSerNum
	 *            장비속성일괄이력일련번호
	 */
	public void setEquipAttrBatHstSerNum(Long equipAttrBatHstSerNum) {
		this.equipAttrBatHstSerNum = equipAttrBatHstSerNum;
	}

	/**
	 * 장비속성변경구분코드
	 * 
	 * @return 장비속성변경구분코드
	 */
	public String getEquipAttrChgClCd() {
		return equipAttrChgClCd;
	}

	/**
	 * 장비속성변경구분코드
	 *
	 * @param equipAttrChgClCd
	 *            장비속성변경구분코드
	 */
	public void setEquipAttrChgClCd(String equipAttrChgClCd) {
		this.equipAttrChgClCd = equipAttrChgClCd;
	}
}

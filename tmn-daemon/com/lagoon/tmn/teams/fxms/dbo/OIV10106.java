package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.23 17:00
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10106", comment = "EMS수용장비")
public class OIV10106 implements Serializable {

	public OIV10106() {
	}

	@FxColumn(name = "EMS_ID", size = 12, comment = "EMSID")
	private String emsId;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "EMS_EQUIP_NUM", size = 30, nullable = true, comment = "EMS장비번호")
	private String emsEquipNum;

	@FxColumn(name = "AUTO_MGMT_YN", size = 1, comment = "자동관리여부", defValue = "'N'")
	private String autoMgmtYn = "N";

	@FxColumn(name = "AUTO_MGMT_DTM", size = 0, comment = "자동관리일시", defValue = "sysdate")
	private Timestamp autoMgmtDtm;

	@FxColumn(name = "EMS_INR_EQUIP_KEY_VAL", size = 100, nullable = true, comment = "EMS내부장비키값")
	private String emsInrEquipKeyVal;

	/**
	 * EMSID
	 * 
	 * @return EMSID
	 */
	public String getEmsId() {
		return emsId;
	}

	/**
	 * EMSID
	 *
	 * @param emsId
	 *            EMSID
	 */
	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

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
	 * EMS장비번호
	 * 
	 * @return EMS장비번호
	 */
	public String getEmsEquipNum() {
		return emsEquipNum;
	}

	/**
	 * EMS장비번호
	 *
	 * @param emsEquipNum
	 *            EMS장비번호
	 */
	public void setEmsEquipNum(String emsEquipNum) {
		this.emsEquipNum = emsEquipNum;
	}

	/**
	 * 자동관리여부
	 * 
	 * @return 자동관리여부
	 */
	public String isAutoMgmtYn() {
		return autoMgmtYn;
	}

	/**
	 * 자동관리여부
	 *
	 * @param autoMgmtYn
	 *            자동관리여부
	 */
	public void setAutoMgmtYn(String autoMgmtYn) {
		this.autoMgmtYn = autoMgmtYn;
	}

	/**
	 * 자동관리일시
	 * 
	 * @return 자동관리일시
	 */
	public Timestamp getAutoMgmtDtm() {
		return autoMgmtDtm;
	}

	/**
	 * 자동관리일시
	 *
	 * @param autoMgmtDtm
	 *            자동관리일시
	 */
	public void setAutoMgmtDtm(Timestamp autoMgmtDtm) {
		this.autoMgmtDtm = autoMgmtDtm;
	}

	/**
	 * EMS내부장비키값
	 * 
	 * @return EMS내부장비키값
	 */
	public String getEmsInrEquipKeyVal() {
		return emsInrEquipKeyVal;
	}

	/**
	 * EMS내부장비키값
	 *
	 * @param emsInrEquipKeyVal
	 *            EMS내부장비키값
	 */
	public void setEmsInrEquipKeyVal(String emsInrEquipKeyVal) {
		this.emsInrEquipKeyVal = emsInrEquipKeyVal;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.22 09:32
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV28160", comment = "전송장비CRS내역")
public class OIV28160 implements Serializable {

	public OIV28160() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "ADRC_EQUIP_PORT_DESC", size = 256, comment = "A측장비포트설명")
	private String adrcEquipPortDesc;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "BDRC_EQUIP_PORT_DESC", size = 256, comment = "B측장비포트설명")
	private String bdrcEquipPortDesc;

	@FxColumn(name = "SGNL_TYP_NM", size = 30, nullable = true, comment = "신호종류명")
	private String sgnlTypNm;

	@FxColumn(name = "CRS_CL_VAL", size = 30, nullable = true, comment = "CRS구분값")
	private String crsClVal;

	@FxColumn(name = "CRS_RMK", size = 100, nullable = true, comment = "CRS비고")
	private String crsRmk;

	@FxColumn(name = "CLCT_DTM", size = 14, comment = "수집일시")
	private String clctDtm;

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
	 * A측장비포트설명
	 * 
	 * @return A측장비포트설명
	 */
	public String getAdrcEquipPortDesc() {
		return adrcEquipPortDesc;
	}

	/**
	 * A측장비포트설명
	 *
	 * @param adrcEquipPortDesc
	 *            A측장비포트설명
	 */
	public void setAdrcEquipPortDesc(String adrcEquipPortDesc) {
		this.adrcEquipPortDesc = adrcEquipPortDesc;
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
	 * B측장비포트설명
	 * 
	 * @return B측장비포트설명
	 */
	public String getBdrcEquipPortDesc() {
		return bdrcEquipPortDesc;
	}

	/**
	 * B측장비포트설명
	 *
	 * @param bdrcEquipPortDesc
	 *            B측장비포트설명
	 */
	public void setBdrcEquipPortDesc(String bdrcEquipPortDesc) {
		this.bdrcEquipPortDesc = bdrcEquipPortDesc;
	}

	/**
	 * 신호종류명
	 * 
	 * @return 신호종류명
	 */
	public String getSgnlTypNm() {
		return sgnlTypNm;
	}

	/**
	 * 신호종류명
	 *
	 * @param sgnlTypNm
	 *            신호종류명
	 */
	public void setSgnlTypNm(String sgnlTypNm) {
		this.sgnlTypNm = sgnlTypNm;
	}

	/**
	 * CRS구분값
	 * 
	 * @return CRS구분값
	 */
	public String getCrsClVal() {
		return crsClVal;
	}

	/**
	 * CRS구분값
	 *
	 * @param crsClVal
	 *            CRS구분값
	 */
	public void setCrsClVal(String crsClVal) {
		this.crsClVal = crsClVal;
	}

	/**
	 * CRS비고
	 * 
	 * @return CRS비고
	 */
	public String getCrsRmk() {
		return crsRmk;
	}

	/**
	 * CRS비고
	 *
	 * @param crsRmk
	 *            CRS비고
	 */
	public void setCrsRmk(String crsRmk) {
		this.crsRmk = crsRmk;
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
}

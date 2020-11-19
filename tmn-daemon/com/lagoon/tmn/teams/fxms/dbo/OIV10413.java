package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.16 13:51
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10413", comment = "포트부가")
public class OIV10413 {

	public OIV10413() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_PORT_ID", size = 15, comment = "장비포트ID")
	private String equipPortId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "BK_PORT_SET_YN", size = 1, comment = "백업포트설정여부", defValue = "'N'")
	private String bkPortSetYn = "N";

	@FxColumn(name = "BK_EQUIP_ID", size = 12, nullable = true, comment = "백업장비ID")
	private String bkEquipId;

	@FxColumn(name = "BK_EQUIP_PORT_ID", size = 15, nullable = true, comment = "백업장비포트ID")
	private String bkEquipPortId;

	@FxColumn(name = "ALT_PSBL_YN", size = 1, comment = "대체가능여부", defValue = "'N'")
	private String altPsblYn = "N";

	@FxColumn(name = "AUTO_MGMT_YN", size = 1, comment = "자동관리여부", defValue = "'N'")
	private String autoMgmtYn = "N";

	@FxColumn(name = "PORT_ST_VAL", size = 100, nullable = true, comment = "포트상태값")
	private String portStVal;

	@FxColumn(name = "PORT_SUB_ST_VAL", size = 100, nullable = true, comment = "포트서브상태값")
	private String portSubStVal;

	@FxColumn(name = "PORT_NM", size = 100, nullable = true, comment = "포트명")
	private String portNm;

	@FxColumn(name = "PORT_USG_CD", size = 2, nullable = true, comment = "포트용도코드")
	private String portUsgCd;

	@FxColumn(name = "CLCT_DTM", size = 14, nullable = true, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "UPMV_MDLN_VAL", size = 10, nullable = true, comment = "상향변조방식값")
	private String upmvMdlnVal;

	@FxColumn(name = "DNMV_MDLN_VAL", size = 10, nullable = true, comment = "하향변조방식값")
	private String dnmvMdlnVal;

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
	 * 장비포트ID
	 * 
	 * @return 장비포트ID
	 */
	public String getEquipPortId() {
		return equipPortId;
	}

	/**
	 * 장비포트ID
	 *
	 * @param equipPortId
	 *            장비포트ID
	 */
	public void setEquipPortId(String equipPortId) {
		this.equipPortId = equipPortId;
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
	 * 백업포트설정여부
	 * 
	 * @return 백업포트설정여부
	 */
	public String isBkPortSetYn() {
		return bkPortSetYn;
	}

	/**
	 * 백업포트설정여부
	 *
	 * @param bkPortSetYn
	 *            백업포트설정여부
	 */
	public void setBkPortSetYn(String bkPortSetYn) {
		this.bkPortSetYn = bkPortSetYn;
	}

	/**
	 * 백업장비ID
	 * 
	 * @return 백업장비ID
	 */
	public String getBkEquipId() {
		return bkEquipId;
	}

	/**
	 * 백업장비ID
	 *
	 * @param bkEquipId
	 *            백업장비ID
	 */
	public void setBkEquipId(String bkEquipId) {
		this.bkEquipId = bkEquipId;
	}

	/**
	 * 백업장비포트ID
	 * 
	 * @return 백업장비포트ID
	 */
	public String getBkEquipPortId() {
		return bkEquipPortId;
	}

	/**
	 * 백업장비포트ID
	 *
	 * @param bkEquipPortId
	 *            백업장비포트ID
	 */
	public void setBkEquipPortId(String bkEquipPortId) {
		this.bkEquipPortId = bkEquipPortId;
	}

	/**
	 * 대체가능여부
	 * 
	 * @return 대체가능여부
	 */
	public String isAltPsblYn() {
		return altPsblYn;
	}

	/**
	 * 대체가능여부
	 *
	 * @param altPsblYn
	 *            대체가능여부
	 */
	public void setAltPsblYn(String altPsblYn) {
		this.altPsblYn = altPsblYn;
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
	 * 포트상태값
	 * 
	 * @return 포트상태값
	 */
	public String getPortStVal() {
		return portStVal;
	}

	/**
	 * 포트상태값
	 *
	 * @param portStVal
	 *            포트상태값
	 */
	public void setPortStVal(String portStVal) {
		this.portStVal = portStVal;
	}

	/**
	 * 포트서브상태값
	 * 
	 * @return 포트서브상태값
	 */
	public String getPortSubStVal() {
		return portSubStVal;
	}

	/**
	 * 포트서브상태값
	 *
	 * @param portSubStVal
	 *            포트서브상태값
	 */
	public void setPortSubStVal(String portSubStVal) {
		this.portSubStVal = portSubStVal;
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
	 * 포트용도코드
	 * 
	 * @return 포트용도코드
	 */
	public String getPortUsgCd() {
		return portUsgCd;
	}

	/**
	 * 포트용도코드
	 *
	 * @param portUsgCd
	 *            포트용도코드
	 */
	public void setPortUsgCd(String portUsgCd) {
		this.portUsgCd = portUsgCd;
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
	 * 상향변조방식값
	 * 
	 * @return 상향변조방식값
	 */
	public String getUpmvMdlnVal() {
		return upmvMdlnVal;
	}

	/**
	 * 상향변조방식값
	 *
	 * @param upmvMdlnVal
	 *            상향변조방식값
	 */
	public void setUpmvMdlnVal(String upmvMdlnVal) {
		this.upmvMdlnVal = upmvMdlnVal;
	}

	/**
	 * 하향변조방식값
	 * 
	 * @return 하향변조방식값
	 */
	public String getDnmvMdlnVal() {
		return dnmvMdlnVal;
	}

	/**
	 * 하향변조방식값
	 *
	 * @param dnmvMdlnVal
	 *            하향변조방식값
	 */
	public void setDnmvMdlnVal(String dnmvMdlnVal) {
		this.dnmvMdlnVal = dnmvMdlnVal;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.23 16:51
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OCL28012", comment = "전송장비통보설정수집내역")
public class OCL28012 implements Serializable {

	public OCL28012() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "NOTI_CL_VAL", size = 10, comment = "통보구분값")
	private String notiClVal;

	@FxColumn(name = "NOTI_RCV_OBJ_NM", size = 200, comment = "통보수신대상명")
	private String notiRcvObjNm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "ALM_NOTI_YN", size = 1, comment = "알람통보여부", defValue = "'N'")
	private String almNotiYn = "N";

	@FxColumn(name = "PRFM_NOTI_YN", size = 1, comment = "성능통보여부", defValue = "'N'")
	private String prfmNotiYn = "N";

	@FxColumn(name = "SW_NOTI_YN", size = 1, comment = "SW통보여부", defValue = "'N'")
	private String swNotiYn = "N";

	@FxColumn(name = "SNMP_NOTI_YN", size = 1, comment = "SNMP통보여부", defValue = "'N'")
	private String snmpNotiYn = "N";

	@FxColumn(name = "SYS_LOG_NOTI_YN", size = 1, comment = "시스템로그통보여부", defValue = "'N'")
	private String sysLogNotiYn = "N";

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
	 * 통보구분값
	 * 
	 * @return 통보구분값
	 */
	public String getNotiClVal() {
		return notiClVal;
	}

	/**
	 * 통보구분값
	 *
	 * @param notiClVal
	 *            통보구분값
	 */
	public void setNotiClVal(String notiClVal) {
		this.notiClVal = notiClVal;
	}

	/**
	 * 통보수신대상명
	 * 
	 * @return 통보수신대상명
	 */
	public String getNotiRcvObjNm() {
		return notiRcvObjNm;
	}

	/**
	 * 통보수신대상명
	 *
	 * @param notiRcvObjNm
	 *            통보수신대상명
	 */
	public void setNotiRcvObjNm(String notiRcvObjNm) {
		this.notiRcvObjNm = notiRcvObjNm;
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
	 * 알람통보여부
	 * 
	 * @return 알람통보여부
	 */
	public String isAlmNotiYn() {
		return almNotiYn;
	}

	/**
	 * 알람통보여부
	 *
	 * @param almNotiYn
	 *            알람통보여부
	 */
	public void setAlmNotiYn(String almNotiYn) {
		this.almNotiYn = almNotiYn;
	}

	/**
	 * 성능통보여부
	 * 
	 * @return 성능통보여부
	 */
	public String isPrfmNotiYn() {
		return prfmNotiYn;
	}

	/**
	 * 성능통보여부
	 *
	 * @param prfmNotiYn
	 *            성능통보여부
	 */
	public void setPrfmNotiYn(String prfmNotiYn) {
		this.prfmNotiYn = prfmNotiYn;
	}

	/**
	 * SW통보여부
	 * 
	 * @return SW통보여부
	 */
	public String isSwNotiYn() {
		return swNotiYn;
	}

	/**
	 * SW통보여부
	 *
	 * @param swNotiYn
	 *            SW통보여부
	 */
	public void setSwNotiYn(String swNotiYn) {
		this.swNotiYn = swNotiYn;
	}

	/**
	 * SNMP통보여부
	 * 
	 * @return SNMP통보여부
	 */
	public String isSnmpNotiYn() {
		return snmpNotiYn;
	}

	/**
	 * SNMP통보여부
	 *
	 * @param snmpNotiYn
	 *            SNMP통보여부
	 */
	public void setSnmpNotiYn(String snmpNotiYn) {
		this.snmpNotiYn = snmpNotiYn;
	}

	/**
	 * 시스템로그통보여부
	 * 
	 * @return 시스템로그통보여부
	 */
	public String isSysLogNotiYn() {
		return sysLogNotiYn;
	}

	/**
	 * 시스템로그통보여부
	 *
	 * @param sysLogNotiYn
	 *            시스템로그통보여부
	 */
	public void setSysLogNotiYn(String sysLogNotiYn) {
		this.sysLogNotiYn = sysLogNotiYn;
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

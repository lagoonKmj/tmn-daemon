package com.lagoon.tmn.teams.co.dao.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.06.18 13:26
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10100", comment = "장비기본")
public class UpdateOIV10100Dbo {

	public UpdateOIV10100Dbo() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "SW_VER_INFO", size = 50, nullable = true, comment = "SW버전정보")
	private String swVerInfo;

	@FxColumn(name = "EQUIP_SYS_NM", size = 300, nullable = true, comment = "장비시스템명")
	private String equipSysNm;

	@FxColumn(name = "EQUIP_IP_ADDR", size = 23, nullable = true, comment = "장비IP주소")
	private String equipIpAddr;



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
	 * SW버전정보
	 * 
	 * @return SW버전정보
	 */
	public String getSwVerInfo() {
		return swVerInfo;
	}

	/**
	 * SW버전정보
	 *
	 * @param swVerInfo
	 *            SW버전정보
	 */
	public void setSwVerInfo(String swVerInfo) {
		this.swVerInfo = swVerInfo;
	}

	/**
	 * 장비시스템명
	 * 
	 * @return 장비시스템명
	 */
	public String getEquipSysNm() {
		return equipSysNm;
	}

	/**
	 * 장비시스템명
	 *
	 * @param equipSysNm
	 *            장비시스템명
	 */
	public void setEquipSysNm(String equipSysNm) {
		this.equipSysNm = equipSysNm;
	}

	

	/**
	 * 장비IP주소
	 * 
	 * @return 장비IP주소
	 */
	public String getEquipIpAddr() {
		return equipIpAddr;
	}

	/**
	 * 장비IP주소
	 *
	 * @param equipIpAddr
	 *            장비IP주소
	 */
	public void setEquipIpAddr(String equipIpAddr) {
		this.equipIpAddr = equipIpAddr;
	}


}

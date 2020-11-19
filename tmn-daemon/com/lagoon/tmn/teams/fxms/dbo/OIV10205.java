package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.14 14:23
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10205", comment = "장비포트형상기준")
public class OIV10205 implements Serializable {

	public OIV10205() {
	}

	@FxColumn(name = "EQUIP_CONS_ITM_CD", size = 10, comment = "장비구성품코드")
	private String equipConsItmCd;

	@FxColumn(name = "PORT_NUM", size = 10, comment = "포트번호")
	private String portNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "PORT_NM", size = 100, nullable = true, comment = "포트명")
	private String portNm;

	@FxColumn(name = "PORT_TYP_CD", size = 3, comment = "포트유형코드")
	private String portTypCd;

	@FxColumn(name = "PORT_SPEED_CD", size = 6, nullable = true, comment = "포트속도코드")
	private String portSpeedCd;

	/**
	 * 장비구성품코드
	 * 
	 * @return 장비구성품코드
	 */
	public String getEquipConsItmCd() {
		return equipConsItmCd;
	}

	/**
	 * 장비구성품코드
	 *
	 * @param equipConsItmCd
	 *            장비구성품코드
	 */
	public void setEquipConsItmCd(String equipConsItmCd) {
		this.equipConsItmCd = equipConsItmCd;
	}

	/**
	 * 포트번호
	 * 
	 * @return 포트번호
	 */
	public String getPortNum() {
		return portNum;
	}

	/**
	 * 포트번호
	 *
	 * @param portNum
	 *            포트번호
	 */
	public void setPortNum(String portNum) {
		this.portNum = portNum;
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
	 * 포트유형코드
	 * 
	 * @return 포트유형코드
	 */
	public String getPortTypCd() {
		return portTypCd;
	}

	/**
	 * 포트유형코드
	 *
	 * @param portTypCd
	 *            포트유형코드
	 */
	public void setPortTypCd(String portTypCd) {
		this.portTypCd = portTypCd;
	}

	/**
	 * 포트속도코드
	 * 
	 * @return 포트속도코드
	 */
	public String getPortSpeedCd() {
		return portSpeedCd;
	}

	/**
	 * 포트속도코드
	 *
	 * @param portSpeedCd
	 *            포트속도코드
	 */
	public void setPortSpeedCd(String portSpeedCd) {
		this.portSpeedCd = portSpeedCd;
	}
}

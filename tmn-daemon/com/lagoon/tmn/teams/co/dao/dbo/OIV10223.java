package com.lagoon.tmn.teams.co.dao.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.05 09:41
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10223", comment = "전송장비포트수집내역")
public class OIV10223 implements Serializable {

	public OIV10223() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_CONS_ITM_NM", size = 60, comment = "장비구성품명")
	private String equipConsItmNm;

	@FxColumn(name = "PORT_DESC", size = 500, comment = "포트설명")
	private String portDesc;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "PORT_SPEED_CD", size = 6, nullable = true, comment = "포트속도코드")
	private String portSpeedCd;

	@FxColumn(name = "CLCT_DTM", size = 14, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "EQUIP_PORT_ID", size = 15, nullable = true, comment = "장비포트ID")
	private String equipPortId;

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
	 * 포트설명
	 * 
	 * @return 포트설명
	 */
	public String getPortDesc() {
		return portDesc;
	}

	/**
	 * 포트설명
	 *
	 * @param portDesc
	 *            포트설명
	 */
	public void setPortDesc(String portDesc) {
		this.portDesc = portDesc;
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
}

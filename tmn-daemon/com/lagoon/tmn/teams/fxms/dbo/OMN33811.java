package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.dao.define.INDEX_TYPE;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxIndex;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.07.31 11:13
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN33811", comment = "전송장비장애현재")
@FxIndex(name = "OMN33811_PK", type = INDEX_TYPE.PK, columns = { "TRMS_EQUIP_DABL_NUM" })
public class OMN33811 implements Serializable {

	public OMN33811() {
	}

	@FxColumn(name = "TRMS_EQUIP_DABL_NUM", size = 15, comment = "전송장비장애번호")
	private long trmsEquipDablNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "DABL_GR_CD", size = 2, comment = "장애등급코드")
	private String dablGrCd;

	@FxColumn(name = "DABL_CD", size = 30, comment = "장애코드")
	private String dablCd;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "SCARD_DESC", size = 100, nullable = true, comment = "SLOT카드설명")
	private String scardDesc;

	@FxColumn(name = "PORT_DESC", size = 500, nullable = true, comment = "포트설명")
	private String portDesc;

	@FxColumn(name = "DABL_OCCR_LOC_DESC", size = 1000, nullable = true, comment = "장애발생위치설명")
	private String dablOccrLocDesc;

	@FxColumn(name = "EMS_ID", size = 12, nullable = true, comment = "EMSID")
	private String emsId;

	@FxColumn(name = "EMS_ALM_VAL", size = 100, nullable = true, comment = "EMS알람값")
	private String emsAlmVal;

	/**
	 * 전송장비장애번호
	 * 
	 * @return 전송장비장애번호
	 */
	public long getTrmsEquipDablNum() {
		return trmsEquipDablNum;
	}

	/**
	 * 전송장비장애번호
	 *
	 * @param trmsEquipDablNum
	 *            전송장비장애번호
	 */
	public void setTrmsEquipDablNum(long trmsEquipDablNum) {
		this.trmsEquipDablNum = trmsEquipDablNum;
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
	 * 장애등급코드
	 * 
	 * @return 장애등급코드
	 */
	public String getDablGrCd() {
		return dablGrCd;
	}

	/**
	 * 장애등급코드
	 *
	 * @param dablGrCd
	 *            장애등급코드
	 */
	public void setDablGrCd(String dablGrCd) {
		this.dablGrCd = dablGrCd;
	}

	/**
	 * 장애코드
	 * 
	 * @return 장애코드
	 */
	public String getDablCd() {
		return dablCd;
	}

	/**
	 * 장애코드
	 *
	 * @param dablCd
	 *            장애코드
	 */
	public void setDablCd(String dablCd) {
		this.dablCd = dablCd;
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
	 * SLOT카드설명
	 * 
	 * @return SLOT카드설명
	 */
	public String getScardDesc() {
		return scardDesc;
	}

	/**
	 * SLOT카드설명
	 *
	 * @param scardDesc
	 *            SLOT카드설명
	 */
	public void setScardDesc(String scardDesc) {
		this.scardDesc = scardDesc;
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
	 * 장애발생위치설명
	 * 
	 * @return 장애발생위치설명
	 */
	public String getDablOccrLocDesc() {
		return dablOccrLocDesc;
	}

	/**
	 * 장애발생위치설명
	 *
	 * @param dablOccrLocDesc
	 *            장애발생위치설명
	 */
	public void setDablOccrLocDesc(String dablOccrLocDesc) {
		this.dablOccrLocDesc = dablOccrLocDesc;
	}

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
	 * EMS알람값
	 * 
	 * @return EMS알람값
	 */
	public String getEmsAlmVal() {
		return emsAlmVal;
	}

	/**
	 * EMS알람값
	 *
	 * @param emsAlmVal
	 *            EMS알람값
	 */
	public void setEmsAlmVal(String emsAlmVal) {
		this.emsAlmVal = emsAlmVal;
	}

}

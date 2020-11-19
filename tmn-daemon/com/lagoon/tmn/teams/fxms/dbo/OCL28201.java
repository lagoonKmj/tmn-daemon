package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28201", comment = "전송장비장애수집내역")
public class OCL28201 {

	public OCL28201() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "ALM_LOC_INFO", size = 200, comment = "알람위치정보")
	private String almLocInfo;

	@FxColumn(name = "ALM_TYP_INFO", size = 100, comment = "알람종류정보")
	private String almTypInfo;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "ALM_GR_INFO", size = 20, nullable = true, comment = "알람등급정보")
	private String almGrInfo;

	@FxColumn(name = "SCARD_TYP_NM", size = 50, nullable = true, comment = "SLOT카드종류명")
	private String scardTypNm;

	@FxColumn(name = "OCCR_DTM", size = 14, nullable = true, comment = "발생일시")
	private String occrDtm;

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
	 * 알람위치정보
	 * 
	 * @return 알람위치정보
	 */
	public String getAlmLocInfo() {
		return almLocInfo;
	}

	/**
	 * 알람위치정보
	 *
	 * @param almLocInfo
	 *            알람위치정보
	 */
	public void setAlmLocInfo(String almLocInfo) {
		this.almLocInfo = almLocInfo;
	}

	/**
	 * 알람종류정보
	 * 
	 * @return 알람종류정보
	 */
	public String getAlmTypInfo() {
		return almTypInfo;
	}

	/**
	 * 알람종류정보
	 *
	 * @param almTypInfo
	 *            알람종류정보
	 */
	public void setAlmTypInfo(String almTypInfo) {
		this.almTypInfo = almTypInfo;
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
	 * 알람등급정보
	 * 
	 * @return 알람등급정보
	 */
	public String getAlmGrInfo() {
		return almGrInfo;
	}

	/**
	 * 알람등급정보
	 *
	 * @param almGrInfo
	 *            알람등급정보
	 */
	public void setAlmGrInfo(String almGrInfo) {
		this.almGrInfo = almGrInfo;
	}

	/**
	 * SLOT카드종류명
	 * 
	 * @return SLOT카드종류명
	 */
	public String getScardTypNm() {
		return scardTypNm;
	}

	/**
	 * SLOT카드종류명
	 *
	 * @param scardTypNm
	 *            SLOT카드종류명
	 */
	public void setScardTypNm(String scardTypNm) {
		this.scardTypNm = scardTypNm;
	}

	/**
	 * 발생일시
	 * 
	 * @return 발생일시
	 */
	public String getOccrDtm() {
		return occrDtm;
	}

	/**
	 * 발생일시
	 *
	 * @param occrDtm
	 *            발생일시
	 */
	public void setOccrDtm(String occrDtm) {
		this.occrDtm = occrDtm;
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

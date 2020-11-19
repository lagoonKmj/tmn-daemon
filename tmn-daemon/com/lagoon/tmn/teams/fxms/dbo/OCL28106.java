package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28106", comment = "전송장비FAN수집내역")
public class OCL28106 {

	public OCL28106() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "FAN_MODE_NM", size = 30, comment = "FAN모드명", defValue = "'N'")
	private String fanModeNm = "N";

	@FxColumn(name = "FAN_ST_NM", size = 30, comment = "FAN상태명")
	private String fanStNm;

	@FxColumn(name = "MAX_SPEED_UPMV_COND_VAL", size = 5, nullable = true, comment = "최고속도상향조건값")
	private Integer maxSpeedUpmvCondVal;

	@FxColumn(name = "MEDM_SPEED_UPMV_COND_VAL", size = 5, nullable = true, comment = "중간속도상향조건값")
	private Integer medmSpeedUpmvCondVal;

	@FxColumn(name = "MIN_SPEED_UPMV_COND_VAL", size = 5, nullable = true, comment = "최저속도상향조건값")
	private Integer minSpeedUpmvCondVal;

	@FxColumn(name = "MEDM_SPEED_DNMV_COND_VAL", size = 5, nullable = true, comment = "중간속도하향조건값")
	private Integer medmSpeedDnmvCondVal;

	@FxColumn(name = "MIN_SPEED_DNMV_COND_VAL", size = 5, nullable = true, comment = "최저속도하향조건값")
	private Integer minSpeedDnmvCondVal;

	@FxColumn(name = "FAN_OFF_COND_VAL", size = 5, nullable = true, comment = "FANOFF조건값")
	private Integer fanOffCondVal;

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
	 * FAN모드명
	 * 
	 * @return FAN모드명
	 */
	public String getFanModeNm() {
		return fanModeNm;
	}

	/**
	 * FAN모드명
	 *
	 * @param fanModeNm
	 *            FAN모드명
	 */
	public void setFanModeNm(String fanModeNm) {
		this.fanModeNm = fanModeNm;
	}

	/**
	 * FAN상태명
	 * 
	 * @return FAN상태명
	 */
	public String getFanStNm() {
		return fanStNm;
	}

	/**
	 * FAN상태명
	 *
	 * @param fanStNm
	 *            FAN상태명
	 */
	public void setFanStNm(String fanStNm) {
		this.fanStNm = fanStNm;
	}

	/**
	 * 최고속도상향조건값
	 * 
	 * @return 최고속도상향조건값
	 */
	public Integer getMaxSpeedUpmvCondVal() {
		return maxSpeedUpmvCondVal;
	}

	/**
	 * 최고속도상향조건값
	 *
	 * @param maxSpeedUpmvCondVal
	 *            최고속도상향조건값
	 */
	public void setMaxSpeedUpmvCondVal(Integer maxSpeedUpmvCondVal) {
		this.maxSpeedUpmvCondVal = maxSpeedUpmvCondVal;
	}

	/**
	 * 중간속도상향조건값
	 * 
	 * @return 중간속도상향조건값
	 */
	public Integer getMedmSpeedUpmvCondVal() {
		return medmSpeedUpmvCondVal;
	}

	/**
	 * 중간속도상향조건값
	 *
	 * @param medmSpeedUpmvCondVal
	 *            중간속도상향조건값
	 */
	public void setMedmSpeedUpmvCondVal(Integer medmSpeedUpmvCondVal) {
		this.medmSpeedUpmvCondVal = medmSpeedUpmvCondVal;
	}

	/**
	 * 최저속도상향조건값
	 * 
	 * @return 최저속도상향조건값
	 */
	public Integer getMinSpeedUpmvCondVal() {
		return minSpeedUpmvCondVal;
	}

	/**
	 * 최저속도상향조건값
	 *
	 * @param minSpeedUpmvCondVal
	 *            최저속도상향조건값
	 */
	public void setMinSpeedUpmvCondVal(Integer minSpeedUpmvCondVal) {
		this.minSpeedUpmvCondVal = minSpeedUpmvCondVal;
	}

	/**
	 * 중간속도하향조건값
	 * 
	 * @return 중간속도하향조건값
	 */
	public Integer getMedmSpeedDnmvCondVal() {
		return medmSpeedDnmvCondVal;
	}

	/**
	 * 중간속도하향조건값
	 *
	 * @param medmSpeedDnmvCondVal
	 *            중간속도하향조건값
	 */
	public void setMedmSpeedDnmvCondVal(Integer medmSpeedDnmvCondVal) {
		this.medmSpeedDnmvCondVal = medmSpeedDnmvCondVal;
	}

	/**
	 * 최저속도하향조건값
	 * 
	 * @return 최저속도하향조건값
	 */
	public Integer getMinSpeedDnmvCondVal() {
		return minSpeedDnmvCondVal;
	}

	/**
	 * 최저속도하향조건값
	 *
	 * @param minSpeedDnmvCondVal
	 *            최저속도하향조건값
	 */
	public void setMinSpeedDnmvCondVal(Integer minSpeedDnmvCondVal) {
		this.minSpeedDnmvCondVal = minSpeedDnmvCondVal;
	}

	/**
	 * FANOFF조건값
	 * 
	 * @return FANOFF조건값
	 */
	public Integer getFanOffCondVal() {
		return fanOffCondVal;
	}

	/**
	 * FANOFF조건값
	 *
	 * @param fanOffCondVal
	 *            FANOFF조건값
	 */
	public void setFanOffCondVal(Integer fanOffCondVal) {
		this.fanOffCondVal = fanOffCondVal;
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

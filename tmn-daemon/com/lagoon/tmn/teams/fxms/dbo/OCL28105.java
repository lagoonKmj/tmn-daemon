package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28105", comment = "전송장비TRUNK용량수집내역")
public class OCL28105 {

	public OCL28105() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "PORT_NM", size = 100, comment = "포트명", defValue = "'N'")
	private String portNm = "N";

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "SCARD_NM", size = 50, comment = "SLOT카드명")
	private String scardNm;

	@FxColumn(name = "MAX_BANDW_SPEED_VAL", size = 15, nullable = true, comment = "최대대역폭속도값")
	private Long maxBandwSpeedVal;

	@FxColumn(name = "MAX_PIR_VAL", size = 12, nullable = true, comment = "최대PIR값")
	private Long maxPirVal;

	@FxColumn(name = "USE_PIR_VAL", size = 12, nullable = true, comment = "사용PIR값")
	private Long usePirVal;

	@FxColumn(name = "PIR_USE_RT", size = 5, nullable = true, comment = "PIR사용률")
	private Float pirUseRt;

	@FxColumn(name = "PIR_USE_RT_THRS_VAL", size = 5, nullable = true, comment = "PIR사용률임계치값")
	private Float pirUseRtThrsVal;

	@FxColumn(name = "MAX_CIR_VAL", size = 12, nullable = true, comment = "최대CIR값")
	private Long maxCirVal;

	@FxColumn(name = "USE_CIR_VAL", size = 12, nullable = true, comment = "사용CIR값")
	private Long useCirVal;

	@FxColumn(name = "CIR_USE_RT", size = 5, nullable = true, comment = "CIR사용률")
	private Float cirUseRt;

	@FxColumn(name = "CIR_USE_RT_THRS_VAL", size = 5, nullable = true, comment = "CIR사용률임계치값")
	private Float cirUseRtThrsVal;

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
	 * SLOT카드명
	 * 
	 * @return SLOT카드명
	 */
	public String getScardNm() {
		return scardNm;
	}

	/**
	 * SLOT카드명
	 *
	 * @param scardNm
	 *            SLOT카드명
	 */
	public void setScardNm(String scardNm) {
		this.scardNm = scardNm;
	}

	/**
	 * 최대대역폭속도값
	 * 
	 * @return 최대대역폭속도값
	 */
	public Long getMaxBandwSpeedVal() {
		return maxBandwSpeedVal;
	}

	/**
	 * 최대대역폭속도값
	 *
	 * @param maxBandwSpeedVal
	 *            최대대역폭속도값
	 */
	public void setMaxBandwSpeedVal(Long maxBandwSpeedVal) {
		this.maxBandwSpeedVal = maxBandwSpeedVal;
	}

	/**
	 * 최대PIR값
	 * 
	 * @return 최대PIR값
	 */
	public Long getMaxPirVal() {
		return maxPirVal;
	}

	/**
	 * 최대PIR값
	 *
	 * @param maxPirVal
	 *            최대PIR값
	 */
	public void setMaxPirVal(Long maxPirVal) {
		this.maxPirVal = maxPirVal;
	}

	/**
	 * 사용PIR값
	 * 
	 * @return 사용PIR값
	 */
	public Long getUsePirVal() {
		return usePirVal;
	}

	/**
	 * 사용PIR값
	 *
	 * @param usePirVal
	 *            사용PIR값
	 */
	public void setUsePirVal(Long usePirVal) {
		this.usePirVal = usePirVal;
	}

	/**
	 * PIR사용률
	 * 
	 * @return PIR사용률
	 */
	public Float getPirUseRt() {
		return pirUseRt;
	}

	/**
	 * PIR사용률
	 *
	 * @param pirUseRt
	 *            PIR사용률
	 */
	public void setPirUseRt(Float pirUseRt) {
		this.pirUseRt = pirUseRt;
	}

	/**
	 * PIR사용률임계치값
	 * 
	 * @return PIR사용률임계치값
	 */
	public Float getPirUseRtThrsVal() {
		return pirUseRtThrsVal;
	}

	/**
	 * PIR사용률임계치값
	 *
	 * @param pirUseRtThrsVal
	 *            PIR사용률임계치값
	 */
	public void setPirUseRtThrsVal(Float pirUseRtThrsVal) {
		this.pirUseRtThrsVal = pirUseRtThrsVal;
	}

	/**
	 * 최대CIR값
	 * 
	 * @return 최대CIR값
	 */
	public Long getMaxCirVal() {
		return maxCirVal;
	}

	/**
	 * 최대CIR값
	 *
	 * @param maxCirVal
	 *            최대CIR값
	 */
	public void setMaxCirVal(Long maxCirVal) {
		this.maxCirVal = maxCirVal;
	}

	/**
	 * 사용CIR값
	 * 
	 * @return 사용CIR값
	 */
	public Long getUseCirVal() {
		return useCirVal;
	}

	/**
	 * 사용CIR값
	 *
	 * @param useCirVal
	 *            사용CIR값
	 */
	public void setUseCirVal(Long useCirVal) {
		this.useCirVal = useCirVal;
	}

	/**
	 * CIR사용률
	 * 
	 * @return CIR사용률
	 */
	public Float getCirUseRt() {
		return cirUseRt;
	}

	/**
	 * CIR사용률
	 *
	 * @param cirUseRt
	 *            CIR사용률
	 */
	public void setCirUseRt(Float cirUseRt) {
		this.cirUseRt = cirUseRt;
	}

	/**
	 * CIR사용률임계치값
	 * 
	 * @return CIR사용률임계치값
	 */
	public Float getCirUseRtThrsVal() {
		return cirUseRtThrsVal;
	}

	/**
	 * CIR사용률임계치값
	 *
	 * @param cirUseRtThrsVal
	 *            CIR사용률임계치값
	 */
	public void setCirUseRtThrsVal(Float cirUseRtThrsVal) {
		this.cirUseRtThrsVal = cirUseRtThrsVal;
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

package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.21 10:37
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN25101", comment = "광레벨감시내역")
public class OMN25101 implements Serializable {

	public OMN25101() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "MSRT_PORT_INFO", size = 100, comment = "측정포트정보")
	private String msrtPortInfo;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "SEND_RCV_CL_CD", size = 4, nullable = true, comment = "송수신구분코드")
	private String sendRcvClCd;

	@FxColumn(name = "MNTR_CYCL_CD", size = 2, nullable = true, comment = "감시주기코드")
	private String mntrCyclCd;

	@FxColumn(name = "OPLVL_MIN_VAL", size = 15, nullable = true, comment = "광파워레벨최소값")
	private float oplvlMinVal;

	@FxColumn(name = "OPLVL_MAX_VAL", size = 15, nullable = true, comment = "광파워레벨최대값")
	private float oplvlMaxVal;

	@FxColumn(name = "OPLVL_AVG_VAL", size = 15, nullable = true, comment = "광파워레벨평균값")
	private float oplvlAvgVal;

	@FxColumn(name = "OPLVL_CURR_VAL", size = 15, nullable = true, comment = "광파워레벨현재값")
	private float oplvlCurrVal;

	@FxColumn(name = "CLCT_STA_DTM", size = 0, nullable = true, comment = "수집시작일시")
	private Timestamp clctStaDtm;

	@FxColumn(name = "CLCT_END_DTM", size = 0, nullable = true, comment = "수집종료일시")
	private Timestamp clctEndDtm;

	@FxColumn(name = "CLCT_DTM", size = 14, nullable = true, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "MNTR_YN", size = 1, nullable = true, comment = "감시여부")
	private String mntrYn;

	@FxColumn(name = "PORT_ALS_NM", size = 300, nullable = true, comment = "포트별칭명")
	private String portAlsNm;

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
	 * 측정포트정보
	 * 
	 * @return 측정포트정보
	 */
	public String getMsrtPortInfo() {
		return msrtPortInfo;
	}

	/**
	 * 측정포트정보
	 *
	 * @param msrtPortInfo
	 *            측정포트정보
	 */
	public void setMsrtPortInfo(String msrtPortInfo) {
		this.msrtPortInfo = msrtPortInfo;
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
	 * 송수신구분코드
	 * 
	 * @return 송수신구분코드
	 */
	public String getSendRcvClCd() {
		return sendRcvClCd;
	}

	/**
	 * 송수신구분코드
	 *
	 * @param sendRcvClCd
	 *            송수신구분코드
	 */
	public void setSendRcvClCd(String sendRcvClCd) {
		this.sendRcvClCd = sendRcvClCd;
	}

	/**
	 * 감시주기코드
	 * 
	 * @return 감시주기코드
	 */
	public String getMntrCyclCd() {
		return mntrCyclCd;
	}

	/**
	 * 감시주기코드
	 *
	 * @param mntrCyclCd
	 *            감시주기코드
	 */
	public void setMntrCyclCd(String mntrCyclCd) {
		this.mntrCyclCd = mntrCyclCd;
	}

	/**
	 * 광파워레벨최소값
	 * 
	 * @return 광파워레벨최소값
	 */
	public double getOplvlMinVal() {
		return oplvlMinVal;
	}

	/**
	 * 광파워레벨최소값
	 *
	 * @param oplvlMinVal
	 *            광파워레벨최소값
	 */
	public void setOplvlMinVal(float oplvlMinVal) {
		this.oplvlMinVal = oplvlMinVal;
	}

	/**
	 * 광파워레벨최대값
	 * 
	 * @return 광파워레벨최대값
	 */
	public double getOplvlMaxVal() {
		return oplvlMaxVal;
	}

	/**
	 * 광파워레벨최대값
	 *
	 * @param oplvlMaxVal
	 *            광파워레벨최대값
	 */
	public void setOplvlMaxVal(float oplvlMaxVal) {
		this.oplvlMaxVal = oplvlMaxVal;
	}

	/**
	 * 광파워레벨평균값
	 * 
	 * @return 광파워레벨평균값
	 */
	public double getOplvlAvgVal() {
		return oplvlAvgVal;
	}

	/**
	 * 광파워레벨평균값
	 *
	 * @param oplvlAvgVal
	 *            광파워레벨평균값
	 */
	public void setOplvlAvgVal(float oplvlAvgVal) {
		this.oplvlAvgVal = oplvlAvgVal;
	}

	/**
	 * 광파워레벨현재값
	 * 
	 * @return 광파워레벨현재값
	 */
	public double getOplvlCurrVal() {
		return oplvlCurrVal;
	}

	/**
	 * 광파워레벨현재값
	 *
	 * @param oplvlCurrVal
	 *            광파워레벨현재값
	 */
	public void setOplvlCurrVal(float oplvlCurrVal) {
		this.oplvlCurrVal = oplvlCurrVal;
	}

	/**
	 * 수집시작일시
	 * 
	 * @return 수집시작일시
	 */
	public Timestamp getClctStaDtm() {
		return clctStaDtm;
	}

	/**
	 * 수집시작일시
	 *
	 * @param clctStaDtm
	 *            수집시작일시
	 */
	public void setClctStaDtm(Timestamp clctStaDtm) {
		this.clctStaDtm = clctStaDtm;
	}

	/**
	 * 수집종료일시
	 * 
	 * @return 수집종료일시
	 */
	public Timestamp getClctEndDtm() {
		return clctEndDtm;
	}

	/**
	 * 수집종료일시
	 *
	 * @param clctEndDtm
	 *            수집종료일시
	 */
	public void setClctEndDtm(Timestamp clctEndDtm) {
		this.clctEndDtm = clctEndDtm;
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
	 * 감시여부
	 * 
	 * @return 감시여부
	 */
	public String isMntrYn() {
		return mntrYn;
	}

	/**
	 * 감시여부
	 *
	 * @param mntrYn
	 *            감시여부
	 */
	public void setMntrYn(String mntrYn) {
		this.mntrYn = mntrYn;
	}

	/**
	 * 포트별칭명
	 * 
	 * @return 포트별칭명
	 */
	public String getPortAlsNm() {
		return portAlsNm;
	}

	/**
	 * 포트별칭명
	 *
	 * @param portAlsNm
	 *            포트별칭명
	 */
	public void setPortAlsNm(String portAlsNm) {
		this.portAlsNm = portAlsNm;
	}
}

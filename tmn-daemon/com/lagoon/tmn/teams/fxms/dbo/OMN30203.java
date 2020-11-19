package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.05 09:49
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN30203", comment = "장애코드목록")
public class OMN30203 implements Serializable {

	public OMN30203() {
	}

	@FxColumn(name = "DABL_CD_SER_NUM", size = 15, comment = "장애코드일련번호")
	private long dablCdSerNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "NET_CL_CD", size = 2, nullable = true, comment = "망구분코드")
	private String netClCd;

	@FxColumn(name = "DABL_LCL_CD", size = 2, nullable = true, comment = "장애대분류코드")
	private String dablLclCd;

	@FxColumn(name = "DABL_LCL_NM", size = 40, nullable = true, comment = "장애대분류명")
	private String dablLclNm;

	@FxColumn(name = "DABL_MCL_CD", size = 10, nullable = true, comment = "장애중분류코드")
	private String dablMclCd;

	@FxColumn(name = "DABL_MCL_NM", size = 40, nullable = true, comment = "장애중분류명")
	private String dablMclNm;

	@FxColumn(name = "DABL_CD", size = 30, nullable = true, comment = "장애코드")
	private String dablCd;

	@FxColumn(name = "DABL_CD_NM", size = 80, nullable = true, comment = "장애코드명")
	private String dablCdNm;

	@FxColumn(name = "DABL_CD_DESC", size = 400, nullable = true, comment = "장애코드설명")
	private String dablCdDesc;

	@FxColumn(name = "SRC_RGST_MNDT_YN", size = 1, nullable = true, comment = "원인등록필수여부")
	private boolean srcRgstMndtYn;

	@FxColumn(name = "NMS_DABL_CD", size = 30, nullable = true, comment = "NMS장애코드")
	private String nmsDablCd;

	@FxColumn(name = "CLCT_NMS_CL_CD", size = 3, nullable = true, comment = "수집NMS구분코드")
	private String clctNmsClCd;

	@FxColumn(name = "CLCT_EVT_ID", size = 30, nullable = true, comment = "수집이벤트ID")
	private String clctEvtId;

	@FxColumn(name = "CLCT_EVT_CD", size = 30, nullable = true, comment = "수집이벤트코드")
	private String clctEvtCd;

	@FxColumn(name = "AUTO_DABL_RLSE_SEC_TMS", size = 10, comment = "자동장애해제초시간", defValue = "0")
	private long autoDablRlseSecTms = 0;

	/**
	 * 장애코드일련번호
	 * 
	 * @return 장애코드일련번호
	 */
	public long getDablCdSerNum() {
		return dablCdSerNum;
	}

	/**
	 * 장애코드일련번호
	 *
	 * @param dablCdSerNum
	 *            장애코드일련번호
	 */
	public void setDablCdSerNum(long dablCdSerNum) {
		this.dablCdSerNum = dablCdSerNum;
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
	 * 망구분코드
	 * 
	 * @return 망구분코드
	 */
	public String getNetClCd() {
		return netClCd;
	}

	/**
	 * 망구분코드
	 *
	 * @param netClCd
	 *            망구분코드
	 */
	public void setNetClCd(String netClCd) {
		this.netClCd = netClCd;
	}

	/**
	 * 장애대분류코드
	 * 
	 * @return 장애대분류코드
	 */
	public String getDablLclCd() {
		return dablLclCd;
	}

	/**
	 * 장애대분류코드
	 *
	 * @param dablLclCd
	 *            장애대분류코드
	 */
	public void setDablLclCd(String dablLclCd) {
		this.dablLclCd = dablLclCd;
	}

	/**
	 * 장애대분류명
	 * 
	 * @return 장애대분류명
	 */
	public String getDablLclNm() {
		return dablLclNm;
	}

	/**
	 * 장애대분류명
	 *
	 * @param dablLclNm
	 *            장애대분류명
	 */
	public void setDablLclNm(String dablLclNm) {
		this.dablLclNm = dablLclNm;
	}

	/**
	 * 장애중분류코드
	 * 
	 * @return 장애중분류코드
	 */
	public String getDablMclCd() {
		return dablMclCd;
	}

	/**
	 * 장애중분류코드
	 *
	 * @param dablMclCd
	 *            장애중분류코드
	 */
	public void setDablMclCd(String dablMclCd) {
		this.dablMclCd = dablMclCd;
	}

	/**
	 * 장애중분류명
	 * 
	 * @return 장애중분류명
	 */
	public String getDablMclNm() {
		return dablMclNm;
	}

	/**
	 * 장애중분류명
	 *
	 * @param dablMclNm
	 *            장애중분류명
	 */
	public void setDablMclNm(String dablMclNm) {
		this.dablMclNm = dablMclNm;
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
	 * 장애코드명
	 * 
	 * @return 장애코드명
	 */
	public String getDablCdNm() {
		return dablCdNm;
	}

	/**
	 * 장애코드명
	 *
	 * @param dablCdNm
	 *            장애코드명
	 */
	public void setDablCdNm(String dablCdNm) {
		this.dablCdNm = dablCdNm;
	}

	/**
	 * 장애코드설명
	 * 
	 * @return 장애코드설명
	 */
	public String getDablCdDesc() {
		return dablCdDesc;
	}

	/**
	 * 장애코드설명
	 *
	 * @param dablCdDesc
	 *            장애코드설명
	 */
	public void setDablCdDesc(String dablCdDesc) {
		this.dablCdDesc = dablCdDesc;
	}

	/**
	 * 원인등록필수여부
	 * 
	 * @return 원인등록필수여부
	 */
	public boolean isSrcRgstMndtYn() {
		return srcRgstMndtYn;
	}

	/**
	 * 원인등록필수여부
	 *
	 * @param srcRgstMndtYn
	 *            원인등록필수여부
	 */
	public void setSrcRgstMndtYn(boolean srcRgstMndtYn) {
		this.srcRgstMndtYn = srcRgstMndtYn;
	}

	/**
	 * NMS장애코드
	 * 
	 * @return NMS장애코드
	 */
	public String getNmsDablCd() {
		return nmsDablCd;
	}

	/**
	 * NMS장애코드
	 *
	 * @param nmsDablCd
	 *            NMS장애코드
	 */
	public void setNmsDablCd(String nmsDablCd) {
		this.nmsDablCd = nmsDablCd;
	}

	/**
	 * 수집NMS구분코드
	 * 
	 * @return 수집NMS구분코드
	 */
	public String getClctNmsClCd() {
		return clctNmsClCd;
	}

	/**
	 * 수집NMS구분코드
	 *
	 * @param clctNmsClCd
	 *            수집NMS구분코드
	 */
	public void setClctNmsClCd(String clctNmsClCd) {
		this.clctNmsClCd = clctNmsClCd;
	}

	/**
	 * 수집이벤트ID
	 * 
	 * @return 수집이벤트ID
	 */
	public String getClctEvtId() {
		return clctEvtId;
	}

	/**
	 * 수집이벤트ID
	 *
	 * @param clctEvtId
	 *            수집이벤트ID
	 */
	public void setClctEvtId(String clctEvtId) {
		this.clctEvtId = clctEvtId;
	}

	/**
	 * 수집이벤트코드
	 * 
	 * @return 수집이벤트코드
	 */
	public String getClctEvtCd() {
		return clctEvtCd;
	}

	/**
	 * 수집이벤트코드
	 *
	 * @param clctEvtCd
	 *            수집이벤트코드
	 */
	public void setClctEvtCd(String clctEvtCd) {
		this.clctEvtCd = clctEvtCd;
	}

	/**
	 * 자동장애해제초시간
	 * 
	 * @return 자동장애해제초시간
	 */
	public long getAutoDablRlseSecTms() {
		return autoDablRlseSecTms;
	}

	/**
	 * 자동장애해제초시간
	 *
	 * @param autoDablRlseSecTms
	 *            자동장애해제초시간
	 */
	public void setAutoDablRlseSecTms(long autoDablRlseSecTms) {
		this.autoDablRlseSecTms = autoDablRlseSecTms;
	}
}

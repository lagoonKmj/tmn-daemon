package com.lagoon.tmn.nws.dbo;

import java.sql.Timestamp;

import com.lagoon.tmn.teams.fxms.dbo.IAudit;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.09 14:44
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OES30110", comment = "주소위도경도테이블")
public class OES30110 implements IAudit {

	public OES30110() {
	}

	@FxColumn(name = "ADDR_ID", size = 15, comment = "주소ID")
	private String addrId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "ALL_ADDR", size = 200, comment = "전체주소")
	private String allAddr;

	@FxColumn(name = "LTT_VAL", size = 15, comment = "위도값")
	private Double lttVal;

	@FxColumn(name = "LTD_VAL", size = 15, comment = "경도값")
	private Double ltdVal;

	@FxColumn(name = "SRC_INFO", size = 10, nullable = true, comment = "출처정보")
	private String srcInfo;

	@FxColumn(name = "SRC_BRWS_RSLT_VAL", size = 1, nullable = true, comment = "출처조회결과값")
	private String srcBrwsRsltVal;

	/**
	 * 주소ID
	 * 
	 * @return 주소ID
	 */
	public String getAddrId() {
		return addrId;
	}

	/**
	 * 주소ID
	 *
	 * @param addrId
	 *            주소ID
	 */
	public void setAddrId(String addrId) {
		this.addrId = addrId;
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
	 * 전체주소
	 * 
	 * @return 전체주소
	 */
	public String getAllAddr() {
		return allAddr;
	}

	/**
	 * 전체주소
	 *
	 * @param allAddr
	 *            전체주소
	 */
	public void setAllAddr(String allAddr) {
		this.allAddr = allAddr;
	}

	/**
	 * 위도값
	 * 
	 * @return 위도값
	 */
	public Double getLttVal() {
		return lttVal;
	}

	/**
	 * 위도값
	 *
	 * @param lttVal
	 *            위도값
	 */
	public void setLttVal(Double lttVal) {
		this.lttVal = lttVal;
	}

	/**
	 * 경도값
	 * 
	 * @return 경도값
	 */
	public Double getLtdVal() {
		return ltdVal;
	}

	/**
	 * 경도값
	 *
	 * @param ltdVal
	 *            경도값
	 */
	public void setLtdVal(Double ltdVal) {
		this.ltdVal = ltdVal;
	}

	/**
	 * 출처정보
	 * 
	 * @return 출처정보
	 */
	public String getSrcInfo() {
		return srcInfo;
	}

	/**
	 * 출처정보
	 *
	 * @param srcInfo
	 *            출처정보
	 */
	public void setSrcInfo(String srcInfo) {
		this.srcInfo = srcInfo;
	}

	/**
	 * 출처조회결과값
	 * 
	 * @return 출처조회결과값
	 */
	public String getSrcBrwsRsltVal() {
		return srcBrwsRsltVal;
	}

	/**
	 * 출처조회결과값
	 *
	 * @param srcBrwsRsltVal
	 *            출처조회결과값
	 */
	public void setSrcBrwsRsltVal(String srcBrwsRsltVal) {
		this.srcBrwsRsltVal = srcBrwsRsltVal;
	}
}

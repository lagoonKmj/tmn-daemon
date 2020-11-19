package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.05 09:47
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OCL20120", comment = "수집서버목록")
public class OCL20120 implements Serializable {

	public OCL20120() {
	}

	@FxColumn(name = "CLCT_SVR_NUM", size = 5, comment = "수집서버번호", defValue = "0")
	private int clctSvrNum = 0;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "CLCT_NMS_CL_CD", size = 3, comment = "수집NMS구분코드")
	private String clctNmsClCd;

	@FxColumn(name = "CLCT_SVR_IP_ADDR", size = 23, comment = "수집서버IP주소")
	private String clctSvrIpAddr;

	@FxColumn(name = "CLCT_SVR_SUB_IP_ADDR", size = 23, nullable = true, comment = "수집서버서브IP주소")
	private String clctSvrSubIpAddr;

	@FxColumn(name = "CLCT_SVR_DESC", size = 100, nullable = true, comment = "수집서버설명")
	private String clctSvrDesc;

	@FxColumn(name = "HOST_NM", size = 40, nullable = true, comment = "호스트명")
	private String hostNm;

	/**
	 * 수집서버번호
	 * 
	 * @return 수집서버번호
	 */
	public int getClctSvrNum() {
		return clctSvrNum;
	}

	/**
	 * 수집서버번호
	 *
	 * @param clctSvrNum
	 *            수집서버번호
	 */
	public void setClctSvrNum(int clctSvrNum) {
		this.clctSvrNum = clctSvrNum;
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
	 * 수집서버IP주소
	 * 
	 * @return 수집서버IP주소
	 */
	public String getClctSvrIpAddr() {
		return clctSvrIpAddr;
	}

	/**
	 * 수집서버IP주소
	 *
	 * @param clctSvrIpAddr
	 *            수집서버IP주소
	 */
	public void setClctSvrIpAddr(String clctSvrIpAddr) {
		this.clctSvrIpAddr = clctSvrIpAddr;
	}

	/**
	 * 수집서버서브IP주소
	 * 
	 * @return 수집서버서브IP주소
	 */
	public String getClctSvrSubIpAddr() {
		return clctSvrSubIpAddr;
	}

	/**
	 * 수집서버서브IP주소
	 *
	 * @param clctSvrSubIpAddr
	 *            수집서버서브IP주소
	 */
	public void setClctSvrSubIpAddr(String clctSvrSubIpAddr) {
		this.clctSvrSubIpAddr = clctSvrSubIpAddr;
	}

	/**
	 * 수집서버설명
	 * 
	 * @return 수집서버설명
	 */
	public String getClctSvrDesc() {
		return clctSvrDesc;
	}

	/**
	 * 수집서버설명
	 *
	 * @param clctSvrDesc
	 *            수집서버설명
	 */
	public void setClctSvrDesc(String clctSvrDesc) {
		this.clctSvrDesc = clctSvrDesc;
	}

	/**
	 * 호스트명
	 * 
	 * @return 호스트명
	 */
	public String getHostNm() {
		return hostNm;
	}

	/**
	 * 호스트명
	 *
	 * @param hostNm
	 *            호스트명
	 */
	public void setHostNm(String hostNm) {
		this.hostNm = hostNm;
	}
}

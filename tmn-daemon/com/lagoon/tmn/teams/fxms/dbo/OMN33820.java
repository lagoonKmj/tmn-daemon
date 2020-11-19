package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.dao.define.INDEX_TYPE;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxIndex;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.07.31 11:14
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN33820", comment = "전송망회선장애")
@FxIndex(name = "OMN33820_PK", type = INDEX_TYPE.PK, columns = { "TRMS_LINE_DABL_NUM" })
public class OMN33820 implements Serializable {

	public OMN33820() {
	}

	@FxColumn(name = "TRMS_LINE_DABL_NUM", size = 15, comment = "전송회선장애번호")
	private long trmsLineDablNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "TRMS_EQUIP_DABL_NUM", size = 15, comment = "전송장비장애번호")
	private long trmsEquipDablNum;

	@FxColumn(name = "LINE_NUM", size = 12, comment = "회선번호")
	private String lineNum;

	@FxColumn(name = "CUST_NUM", size = 10, comment = "고객번호")
	private long custNum;

	@FxColumn(name = "SVC_MGMT_NUM", size = 10, comment = "서비스관리번호")
	private long svcMgmtNum;

	@FxColumn(name = "TRMS_NET_LINE_USG_CD", size = 2, comment = "전송망회선용도코드")
	private String trmsNetLineUsgCd;

	@FxColumn(name = "TRMS_NET_LINE_SVC_CD", size = 4, comment = "전송망회선서비스코드")
	private String trmsNetLineSvcCd;

	@FxColumn(name = "LINE_CAPA_CL_CD", size = 4, comment = "회선용량구분코드")
	private String lineCapaClCd;

	@FxColumn(name = "SUP_TPO_CD", size = 6, nullable = true, comment = "상위국사코드")
	private String supTpoCd;

	@FxColumn(name = "SUB_TPO_CD", size = 6, nullable = true, comment = "하위국사코드")
	private String subTpoCd;

	@FxColumn(name = "SALE_CHRGR_ID", size = 100, nullable = true, comment = "영업담당자ID")
	private String saleChrgrId;

	@FxColumn(name = "TRMS_NET_MGMT_GR_CD", size = 5, nullable = true, comment = "전송망관리등급코드")
	private String trmsNetMgmtGrCd;

	@FxColumn(name = "DABL_SEND_YN", size = 1, comment = "장애송신여부", defValue = "'N'")
	private boolean dablSendYn = false;

	@FxColumn(name = "DABL_SEND_DTM", size = 0, nullable = true, comment = "장애송신일시")
	private Timestamp dablSendDtm;

	@FxColumn(name = "DABL_SEND_RSLT_VAL", size = 1, nullable = true, comment = "장애송신결과값")
	private String dablSendRsltVal;

	// 회선명 (아담스 클라이언트에서 필요)
	private String lineNm;
	// 망장애코드 (아담스 클라이언트에서 필요)
	private String netDablCd;

	/**
	 * 전송회선장애번호
	 * 
	 * @return 전송회선장애번호
	 */
	public long getTrmsLineDablNum() {
		return trmsLineDablNum;
	}

	/**
	 * 전송회선장애번호
	 *
	 * @param trmsLineDablNum
	 *            전송회선장애번호
	 */
	public void setTrmsLineDablNum(long trmsLineDablNum) {
		this.trmsLineDablNum = trmsLineDablNum;
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
	 * 회선번호
	 * 
	 * @return 회선번호
	 */
	public String getLineNum() {
		return lineNum;
	}

	/**
	 * 회선번호
	 *
	 * @param lineNum
	 *            회선번호
	 */
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	/**
	 * 고객번호
	 * 
	 * @return 고객번호
	 */
	public long getCustNum() {
		return custNum;
	}

	/**
	 * 고객번호
	 *
	 * @param custNum
	 *            고객번호
	 */
	public void setCustNum(long custNum) {
		this.custNum = custNum;
	}

	/**
	 * 서비스관리번호
	 * 
	 * @return 서비스관리번호
	 */
	public long getSvcMgmtNum() {
		return svcMgmtNum;
	}

	/**
	 * 서비스관리번호
	 *
	 * @param svcMgmtNum
	 *            서비스관리번호
	 */
	public void setSvcMgmtNum(long svcMgmtNum) {
		this.svcMgmtNum = svcMgmtNum;
	}

	/**
	 * 전송망회선용도코드
	 * 
	 * @return 전송망회선용도코드
	 */
	public String getTrmsNetLineUsgCd() {
		return trmsNetLineUsgCd;
	}

	/**
	 * 전송망회선용도코드
	 *
	 * @param trmsNetLineUsgCd
	 *            전송망회선용도코드
	 */
	public void setTrmsNetLineUsgCd(String trmsNetLineUsgCd) {
		this.trmsNetLineUsgCd = trmsNetLineUsgCd;
	}

	/**
	 * 전송망회선서비스코드
	 * 
	 * @return 전송망회선서비스코드
	 */
	public String getTrmsNetLineSvcCd() {
		return trmsNetLineSvcCd;
	}

	/**
	 * 전송망회선서비스코드
	 *
	 * @param trmsNetLineSvcCd
	 *            전송망회선서비스코드
	 */
	public void setTrmsNetLineSvcCd(String trmsNetLineSvcCd) {
		this.trmsNetLineSvcCd = trmsNetLineSvcCd;
	}

	/**
	 * 회선용량구분코드
	 * 
	 * @return 회선용량구분코드
	 */
	public String getLineCapaClCd() {
		return lineCapaClCd;
	}

	/**
	 * 회선용량구분코드
	 *
	 * @param lineCapaClCd
	 *            회선용량구분코드
	 */
	public void setLineCapaClCd(String lineCapaClCd) {
		this.lineCapaClCd = lineCapaClCd;
	}

	/**
	 * 상위국사코드
	 * 
	 * @return 상위국사코드
	 */
	public String getSupTpoCd() {
		return supTpoCd;
	}

	/**
	 * 상위국사코드
	 *
	 * @param supTpoCd
	 *            상위국사코드
	 */
	public void setSupTpoCd(String supTpoCd) {
		this.supTpoCd = supTpoCd;
	}

	/**
	 * 하위국사코드
	 * 
	 * @return 하위국사코드
	 */
	public String getSubTpoCd() {
		return subTpoCd;
	}

	/**
	 * 하위국사코드
	 *
	 * @param subTpoCd
	 *            하위국사코드
	 */
	public void setSubTpoCd(String subTpoCd) {
		this.subTpoCd = subTpoCd;
	}

	/**
	 * 영업담당자ID
	 * 
	 * @return 영업담당자ID
	 */
	public String getSaleChrgrId() {
		return saleChrgrId;
	}

	/**
	 * 영업담당자ID
	 *
	 * @param saleChrgrId
	 *            영업담당자ID
	 */
	public void setSaleChrgrId(String saleChrgrId) {
		this.saleChrgrId = saleChrgrId;
	}

	/**
	 * 전송망관리등급코드
	 * 
	 * @return 전송망관리등급코드
	 */
	public String getTrmsNetMgmtGrCd() {
		return trmsNetMgmtGrCd;
	}

	/**
	 * 전송망관리등급코드
	 *
	 * @param trmsNetMgmtGrCd
	 *            전송망관리등급코드
	 */
	public void setTrmsNetMgmtGrCd(String trmsNetMgmtGrCd) {
		this.trmsNetMgmtGrCd = trmsNetMgmtGrCd;
	}

	/**
	 * 장애송신여부
	 * 
	 * @return 장애송신여부
	 */
	public boolean isDablSendYn() {
		return dablSendYn;
	}

	/**
	 * 장애송신여부
	 *
	 * @param dablSendYn
	 *            장애송신여부
	 */
	public void setDablSendYn(boolean dablSendYn) {
		this.dablSendYn = dablSendYn;
	}

	/**
	 * 장애송신일시
	 * 
	 * @return 장애송신일시
	 */
	public Timestamp getDablSendDtm() {
		return dablSendDtm;
	}

	/**
	 * 장애송신일시
	 *
	 * @param dablSendDtm
	 *            장애송신일시
	 */
	public void setDablSendDtm(Timestamp dablSendDtm) {
		this.dablSendDtm = dablSendDtm;
	}

	/**
	 * 장애송신결과값
	 * 
	 * @return 장애송신결과값
	 */
	public String getDablSendRsltVal() {
		return dablSendRsltVal;
	}

	/**
	 * 장애송신결과값
	 *
	 * @param dablSendRsltVal
	 *            장애송신결과값
	 */
	public void setDablSendRsltVal(String dablSendRsltVal) {
		this.dablSendRsltVal = dablSendRsltVal;
	}

	public String getLineNm() {
		return lineNm;
	}

	public void setLineNm(String lineNm) {
		this.lineNm = lineNm;
	}

	public String getNetDablCd() {
		return netDablCd;
	}

	public void setNetDablCd(String netDablCd) {
		this.netDablCd = netDablCd;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.14 09:59
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10105", comment = "EMS기본")
public class OIV10105 implements Serializable {

	public OIV10105() {
	}

	@FxColumn(name = "EMS_ID", size = 12, comment = "EMSID")
	private String emsId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "SVC_NW_CL_CD", size = 2, comment = "서비스NETWORK구분코드")
	private String svcNwClCd;

	@FxColumn(name = "EMS_IP_ADDR", size = 23, nullable = true, comment = "EMSIP주소")
	private String emsIpAddr;

	@FxColumn(name = "EMS_NM", size = 50, nullable = true, comment = "EMS명")
	private String emsNm;

	@FxColumn(name = "EMS_URL", size = 100, nullable = true, comment = "EMSURL")
	private String emsUrl;

	@FxColumn(name = "LOGIN_ID", size = 15, nullable = true, comment = "로그인ID")
	private String loginId;

	@FxColumn(name = "LOGIN_PWD", size = 150, nullable = true, comment = "로그인비밀번호")
	private String loginPwd;

	@FxColumn(name = "EMS_TYP_VAL", size = 30, comment = "EMS유형값", defValue = "'NONE'")
	private String emsTypVal = "NONE";

	@FxColumn(name = "EMS_IP_ADDR2", size = 23, nullable = true, comment = "EMSIP주소2")
	private String emsIpAddr2;

	@FxColumn(name = "DABL_PORT_NUM", size = 10, comment = "장애포트번호", defValue = "'0'")
	private String dablPortNum = "0";

	@FxColumn(name = "CONS_PORT_NUM", size = 10, comment = "구성포트번호", defValue = "'0'")
	private String consPortNum = "0";

	@FxColumn(name = "PRFM_PORT_NUM", size = 10, comment = "성능포트번호", defValue = "'0'")
	private String prfmPortNum = "0";

	@FxColumn(name = "ETC_PORT_NUM1", size = 10, comment = "기타포트번호1", defValue = "'0'")
	private String etcPortNum1 = "0";

	@FxColumn(name = "ETC_PORT_NUM2", size = 10, comment = "기타포트번호2", defValue = "'0'")
	private String etcPortNum2 = "0";

	@FxColumn(name = "ETC_PORT_NUM3", size = 10, comment = "기타포트번호3", defValue = "'0'")
	private String etcPortNum3 = "0";

	@FxColumn(name = "EMS_ST_VAL", size = 20, nullable = true, comment = "EMS상태값")
	private String emsStVal;

	@FxColumn(name = "USE_IP_ADDR_IDX_VAL", size = 2, comment = "사용IP주소인덱스값", defValue = "1")
	private Integer useIpAddrIdxVal = 1;

	@FxColumn(name = "CLCT_SVR_NUM", size = 5, comment = "수집서버번호", defValue = "0")
	private Integer clctSvrNum = 0;

	@FxColumn(name = "SNMP_READ_CMNTY_NM", size = 20, nullable = true, comment = "SNMPREAD커뮤니티명")
	private String snmpReadCmntyNm;

	@FxColumn(name = "SNMP_WRITE_CMNTY_NM", size = 20, nullable = true, comment = "SNMPWRITE커뮤니티명")
	private String snmpWriteCmntyNm;

	@FxColumn(name = "SNMP_PORT_NUM", size = 5, nullable = true, comment = "SNMP포트번호")
	private Integer snmpPortNum;

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
	 * 서비스NETWORK구분코드
	 * 
	 * @return 서비스NETWORK구분코드
	 */
	public String getSvcNwClCd() {
		return svcNwClCd;
	}

	/**
	 * 서비스NETWORK구분코드
	 *
	 * @param svcNwClCd
	 *            서비스NETWORK구분코드
	 */
	public void setSvcNwClCd(String svcNwClCd) {
		this.svcNwClCd = svcNwClCd;
	}

	/**
	 * EMSIP주소
	 * 
	 * @return EMSIP주소
	 */
	public String getEmsIpAddr() {
		return emsIpAddr;
	}

	/**
	 * EMSIP주소
	 *
	 * @param emsIpAddr
	 *            EMSIP주소
	 */
	public void setEmsIpAddr(String emsIpAddr) {
		this.emsIpAddr = emsIpAddr;
	}

	/**
	 * EMS명
	 * 
	 * @return EMS명
	 */
	public String getEmsNm() {
		return emsNm;
	}

	/**
	 * EMS명
	 *
	 * @param emsNm
	 *            EMS명
	 */
	public void setEmsNm(String emsNm) {
		this.emsNm = emsNm;
	}

	/**
	 * EMSURL
	 * 
	 * @return EMSURL
	 */
	public String getEmsUrl() {
		return emsUrl;
	}

	/**
	 * EMSURL
	 *
	 * @param emsUrl
	 *            EMSURL
	 */
	public void setEmsUrl(String emsUrl) {
		this.emsUrl = emsUrl;
	}

	/**
	 * 로그인ID
	 * 
	 * @return 로그인ID
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * 로그인ID
	 *
	 * @param loginId
	 *            로그인ID
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * 로그인비밀번호
	 * 
	 * @return 로그인비밀번호
	 */
	public String getLoginPwd() {
		return loginPwd;
	}

	/**
	 * 로그인비밀번호
	 *
	 * @param loginPwd
	 *            로그인비밀번호
	 */
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	/**
	 * EMS유형값
	 * 
	 * @return EMS유형값
	 */
	public String getEmsTypVal() {
		return emsTypVal;
	}

	/**
	 * EMS유형값
	 *
	 * @param emsTypVal
	 *            EMS유형값
	 */
	public void setEmsTypVal(String emsTypVal) {
		this.emsTypVal = emsTypVal;
	}

	/**
	 * EMSIP주소2
	 * 
	 * @return EMSIP주소2
	 */
	public String getEmsIpAddr2() {
		return emsIpAddr2;
	}

	/**
	 * EMSIP주소2
	 *
	 * @param emsIpAddr2
	 *            EMSIP주소2
	 */
	public void setEmsIpAddr2(String emsIpAddr2) {
		this.emsIpAddr2 = emsIpAddr2;
	}

	/**
	 * 장애포트번호
	 * 
	 * @return 장애포트번호
	 */
	public String getDablPortNum() {
		return dablPortNum;
	}

	/**
	 * 장애포트번호
	 *
	 * @param dablPortNum
	 *            장애포트번호
	 */
	public void setDablPortNum(String dablPortNum) {
		this.dablPortNum = dablPortNum;
	}

	/**
	 * 구성포트번호
	 * 
	 * @return 구성포트번호
	 */
	public String getConsPortNum() {
		return consPortNum;
	}

	/**
	 * 구성포트번호
	 *
	 * @param consPortNum
	 *            구성포트번호
	 */
	public void setConsPortNum(String consPortNum) {
		this.consPortNum = consPortNum;
	}

	/**
	 * 성능포트번호
	 * 
	 * @return 성능포트번호
	 */
	public String getPrfmPortNum() {
		return prfmPortNum;
	}

	/**
	 * 성능포트번호
	 *
	 * @param prfmPortNum
	 *            성능포트번호
	 */
	public void setPrfmPortNum(String prfmPortNum) {
		this.prfmPortNum = prfmPortNum;
	}

	/**
	 * 기타포트번호1
	 * 
	 * @return 기타포트번호1
	 */
	public String getEtcPortNum1() {
		return etcPortNum1;
	}

	/**
	 * 기타포트번호1
	 *
	 * @param etcPortNum1
	 *            기타포트번호1
	 */
	public void setEtcPortNum1(String etcPortNum1) {
		this.etcPortNum1 = etcPortNum1;
	}

	/**
	 * 기타포트번호2
	 * 
	 * @return 기타포트번호2
	 */
	public String getEtcPortNum2() {
		return etcPortNum2;
	}

	/**
	 * 기타포트번호2
	 *
	 * @param etcPortNum2
	 *            기타포트번호2
	 */
	public void setEtcPortNum2(String etcPortNum2) {
		this.etcPortNum2 = etcPortNum2;
	}

	/**
	 * 기타포트번호3
	 * 
	 * @return 기타포트번호3
	 */
	public String getEtcPortNum3() {
		return etcPortNum3;
	}

	/**
	 * 기타포트번호3
	 *
	 * @param etcPortNum3
	 *            기타포트번호3
	 */
	public void setEtcPortNum3(String etcPortNum3) {
		this.etcPortNum3 = etcPortNum3;
	}

	/**
	 * EMS상태값
	 * 
	 * @return EMS상태값
	 */
	public String getEmsStVal() {
		return emsStVal;
	}

	/**
	 * EMS상태값
	 *
	 * @param emsStVal
	 *            EMS상태값
	 */
	public void setEmsStVal(String emsStVal) {
		this.emsStVal = emsStVal;
	}

	/**
	 * 사용IP주소인덱스값
	 * 
	 * @return 사용IP주소인덱스값
	 */
	public Integer getUseIpAddrIdxVal() {
		return useIpAddrIdxVal;
	}

	/**
	 * 사용IP주소인덱스값
	 *
	 * @param useIpAddrIdxVal
	 *            사용IP주소인덱스값
	 */
	public void setUseIpAddrIdxVal(Integer useIpAddrIdxVal) {
		this.useIpAddrIdxVal = useIpAddrIdxVal;
	}

	/**
	 * 수집서버번호
	 * 
	 * @return 수집서버번호
	 */
	public Integer getClctSvrNum() {
		return clctSvrNum;
	}

	/**
	 * 수집서버번호
	 *
	 * @param clctSvrNum
	 *            수집서버번호
	 */
	public void setClctSvrNum(Integer clctSvrNum) {
		this.clctSvrNum = clctSvrNum;
	}

	/**
	 * SNMPREAD커뮤니티명
	 * 
	 * @return SNMPREAD커뮤니티명
	 */
	public String getSnmpReadCmntyNm() {
		return snmpReadCmntyNm;
	}

	/**
	 * SNMPREAD커뮤니티명
	 *
	 * @param snmpReadCmntyNm
	 *            SNMPREAD커뮤니티명
	 */
	public void setSnmpReadCmntyNm(String snmpReadCmntyNm) {
		this.snmpReadCmntyNm = snmpReadCmntyNm;
	}

	/**
	 * SNMPWRITE커뮤니티명
	 * 
	 * @return SNMPWRITE커뮤니티명
	 */
	public String getSnmpWriteCmntyNm() {
		return snmpWriteCmntyNm;
	}

	/**
	 * SNMPWRITE커뮤니티명
	 *
	 * @param snmpWriteCmntyNm
	 *            SNMPWRITE커뮤니티명
	 */
	public void setSnmpWriteCmntyNm(String snmpWriteCmntyNm) {
		this.snmpWriteCmntyNm = snmpWriteCmntyNm;
	}

	/**
	 * SNMP포트번호
	 * 
	 * @return SNMP포트번호
	 */
	public Integer getSnmpPortNum() {
		return snmpPortNum;
	}

	/**
	 * SNMP포트번호
	 *
	 * @param snmpPortNum
	 *            SNMP포트번호
	 */
	public void setSnmpPortNum(Integer snmpPortNum) {
		this.snmpPortNum = snmpPortNum;
	}
}

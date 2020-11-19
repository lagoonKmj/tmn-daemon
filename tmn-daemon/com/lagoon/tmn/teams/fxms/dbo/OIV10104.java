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
@FxTable(name = "OIV10104", comment = "장비접속상세")
public class OIV10104 implements Serializable {

	public OIV10104() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "LOGIN_ID", size = 15, nullable = true, comment = "로그인ID")
	private String loginId;

	@FxColumn(name = "LOGIN_PWD", size = 150, nullable = true, comment = "로그인비밀번호")
	private String loginPwd;

	@FxColumn(name = "EAB_PWD", size = 150, nullable = true, comment = "ENABLE비밀번호")
	private String eabPwd;

	@FxColumn(name = "SNMP_VER_NUM", size = 3, nullable = true, comment = "SNMP버전번호")
	private Integer snmpVerNum;

	@FxColumn(name = "DATA_ENC_PWD", size = 150, nullable = true, comment = "데이터암호화비밀번호")
	private String dataEncPwd;

	@FxColumn(name = "V3_SCUR_GR_CD", size = 1, nullable = true, comment = "V3보안등급코드")
	private String v3ScurGrCd;

	@FxColumn(name = "ENC_PWD", size = 150, nullable = true, comment = "암호화비밀번호")
	private String encPwd;

	@FxColumn(name = "SNMP_READ_CMNTY_NM", size = 20, nullable = true, comment = "SNMPREAD커뮤니티명")
	private String snmpReadCmntyNm;

	@FxColumn(name = "SNMP_WRITE_CMNTY_NM", size = 20, nullable = true, comment = "SNMPWRITE커뮤니티명")
	private String snmpWriteCmntyNm;

	@FxColumn(name = "SNMP_PORT_NUM", size = 5, nullable = true, comment = "SNMP포트번호")
	private Integer snmpPortNum;

	@FxColumn(name = "LOGIN_PORT_NUM", size = 5, nullable = true, comment = "로그인포트번호")
	private Integer loginPortNum;

	@FxColumn(name = "LOGIN_SSH_USE_YN", size = 1, comment = "로그인SSH사용여부", defValue = "'N'")
	private String loginSshUseYn = "N";

	@FxColumn(name = "SNMP_LOGIN_ID", size = 20, nullable = true, comment = "SNMP로그인ID", defValue = "'TEMP'")
	private String snmpLoginId = "TEMP";

	@FxColumn(name = "CALL_LOG_FTP_LOGIN_ID", size = 30, nullable = true, comment = "콜로그FTP로그인ID")
	private String callLogFtpLoginId;

	@FxColumn(name = "CALL_LOG_FTP_LOGIN_PWD", size = 150, nullable = true, comment = "콜로그FTP로그인비밀번호")
	private String callLogFtpLoginPwd;

	@FxColumn(name = "CALL_LOG_FTP_PORT_NUM", size = 10, nullable = true, comment = "콜로그FTP포트번호")
	private String callLogFtpPortNum;

	@FxColumn(name = "SSH_PORT_NUM", size = 10, nullable = true, comment = "SSH포트번호", defValue = "22")
	private String sshPortNum = "22";

	@FxColumn(name = "HTTP_PORT_NUM", size = 10, nullable = true, comment = "HTTP포트번호", defValue = "80")
	private String httpPortNum = "80";

	@FxColumn(name = "TELNET_PROMPT_INFO", size = 10, nullable = true, comment = "텔넷프롬프트정보", defValue = "'>'")
	private String telnetPromptInfo = ">";

	@FxColumn(name = "TELNET_EAB_PROMPT_INFO", size = 10, nullable = true, comment = "텔넷ENABLE프롬프트정보", defValue = "'#'")
	private String telnetEabPromptInfo = "#";

	@FxColumn(name = "DABL_PORT_NUM", size = 10, comment = "장애포트번호", defValue = "'0'")
	private String dablPortNum = "0";

	@FxColumn(name = "CONS_PORT_NUM", size = 10, comment = "구성포트번호", defValue = "'0'")
	private String consPortNum = "0";

	@FxColumn(name = "PRFM_PORT_NUM", size = 10, comment = "성능포트번호", defValue = "'0'")
	private String prfmPortNum = "0";

	@FxColumn(name = "CONN_ST_VAL", size = 10, nullable = true, comment = "접속상태값")
	private String connStVal;

	@FxColumn(name = "CLCT_SVR_NUM", size = 5, comment = "수집서버번호", defValue = "0")
	private Integer clctSvrNum = 0;

	@FxColumn(name = "AUTO_EXPLR_CONS_ITM_PRTY_YN", size = 1, comment = "자동탐색구성품우선여부", defValue = "Y")
	private String autoExplrConsItmPrtyYn = "Y"; 
	
	
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
	 * ENABLE비밀번호
	 * 
	 * @return ENABLE비밀번호
	 */
	public String getEabPwd() {
		return eabPwd;
	}

	/**
	 * ENABLE비밀번호
	 *
	 * @param eabPwd
	 *            ENABLE비밀번호
	 */
	public void setEabPwd(String eabPwd) {
		this.eabPwd = eabPwd;
	}

	/**
	 * SNMP버전번호
	 * 
	 * @return SNMP버전번호
	 */
	public Integer getSnmpVerNum() {
		return snmpVerNum;
	}

	/**
	 * SNMP버전번호
	 *
	 * @param snmpVerNum
	 *            SNMP버전번호
	 */
	public void setSnmpVerNum(Integer snmpVerNum) {
		this.snmpVerNum = snmpVerNum;
	}

	/**
	 * 데이터암호화비밀번호
	 * 
	 * @return 데이터암호화비밀번호
	 */
	public String getDataEncPwd() {
		return dataEncPwd;
	}

	/**
	 * 데이터암호화비밀번호
	 *
	 * @param dataEncPwd
	 *            데이터암호화비밀번호
	 */
	public void setDataEncPwd(String dataEncPwd) {
		this.dataEncPwd = dataEncPwd;
	}

	/**
	 * V3보안등급코드
	 * 
	 * @return V3보안등급코드
	 */
	public String getV3ScurGrCd() {
		return v3ScurGrCd;
	}

	/**
	 * V3보안등급코드
	 *
	 * @param v3ScurGrCd
	 *            V3보안등급코드
	 */
	public void setV3ScurGrCd(String v3ScurGrCd) {
		this.v3ScurGrCd = v3ScurGrCd;
	}

	/**
	 * 암호화비밀번호
	 * 
	 * @return 암호화비밀번호
	 */
	public String getEncPwd() {
		return encPwd;
	}

	/**
	 * 암호화비밀번호
	 *
	 * @param encPwd
	 *            암호화비밀번호
	 */
	public void setEncPwd(String encPwd) {
		this.encPwd = encPwd;
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

	/**
	 * 로그인포트번호
	 * 
	 * @return 로그인포트번호
	 */
	public Integer getLoginPortNum() {
		return loginPortNum;
	}

	/**
	 * 로그인포트번호
	 *
	 * @param loginPortNum
	 *            로그인포트번호
	 */
	public void setLoginPortNum(Integer loginPortNum) {
		this.loginPortNum = loginPortNum;
	}

	/**
	 * 로그인SSH사용여부
	 * 
	 * @return 로그인SSH사용여부
	 */
	public String isLoginSshUseYn() {
		return loginSshUseYn;
	}

	/**
	 * 로그인SSH사용여부
	 *
	 * @param loginSshUseYn
	 *            로그인SSH사용여부
	 */
	public void setLoginSshUseYn(String loginSshUseYn) {
		this.loginSshUseYn = loginSshUseYn;
	}

	/**
	 * SNMP로그인ID
	 * 
	 * @return SNMP로그인ID
	 */
	public String getSnmpLoginId() {
		return snmpLoginId;
	}

	/**
	 * SNMP로그인ID
	 *
	 * @param snmpLoginId
	 *            SNMP로그인ID
	 */
	public void setSnmpLoginId(String snmpLoginId) {
		this.snmpLoginId = snmpLoginId;
	}

	/**
	 * 콜로그FTP로그인ID
	 * 
	 * @return 콜로그FTP로그인ID
	 */
	public String getCallLogFtpLoginId() {
		return callLogFtpLoginId;
	}

	/**
	 * 콜로그FTP로그인ID
	 *
	 * @param callLogFtpLoginId
	 *            콜로그FTP로그인ID
	 */
	public void setCallLogFtpLoginId(String callLogFtpLoginId) {
		this.callLogFtpLoginId = callLogFtpLoginId;
	}

	/**
	 * 콜로그FTP로그인비밀번호
	 * 
	 * @return 콜로그FTP로그인비밀번호
	 */
	public String getCallLogFtpLoginPwd() {
		return callLogFtpLoginPwd;
	}

	/**
	 * 콜로그FTP로그인비밀번호
	 *
	 * @param callLogFtpLoginPwd
	 *            콜로그FTP로그인비밀번호
	 */
	public void setCallLogFtpLoginPwd(String callLogFtpLoginPwd) {
		this.callLogFtpLoginPwd = callLogFtpLoginPwd;
	}

	/**
	 * 콜로그FTP포트번호
	 * 
	 * @return 콜로그FTP포트번호
	 */
	public String getCallLogFtpPortNum() {
		return callLogFtpPortNum;
	}

	/**
	 * 콜로그FTP포트번호
	 *
	 * @param callLogFtpPortNum
	 *            콜로그FTP포트번호
	 */
	public void setCallLogFtpPortNum(String callLogFtpPortNum) {
		this.callLogFtpPortNum = callLogFtpPortNum;
	}

	/**
	 * SSH포트번호
	 * 
	 * @return SSH포트번호
	 */
	public String getSshPortNum() {
		return sshPortNum;
	}

	/**
	 * SSH포트번호
	 *
	 * @param sshPortNum
	 *            SSH포트번호
	 */
	public void setSshPortNum(String sshPortNum) {
		this.sshPortNum = sshPortNum;
	}

	/**
	 * HTTP포트번호
	 * 
	 * @return HTTP포트번호
	 */
	public String getHttpPortNum() {
		return httpPortNum;
	}

	/**
	 * HTTP포트번호
	 *
	 * @param httpPortNum
	 *            HTTP포트번호
	 */
	public void setHttpPortNum(String httpPortNum) {
		this.httpPortNum = httpPortNum;
	}

	/**
	 * 텔넷프롬프트정보
	 * 
	 * @return 텔넷프롬프트정보
	 */
	public String getTelnetPromptInfo() {
		return telnetPromptInfo;
	}

	/**
	 * 텔넷프롬프트정보
	 *
	 * @param telnetPromptInfo
	 *            텔넷프롬프트정보
	 */
	public void setTelnetPromptInfo(String telnetPromptInfo) {
		this.telnetPromptInfo = telnetPromptInfo;
	}

	/**
	 * 텔넷ENABLE프롬프트정보
	 * 
	 * @return 텔넷ENABLE프롬프트정보
	 */
	public String getTelnetEabPromptInfo() {
		return telnetEabPromptInfo;
	}

	/**
	 * 텔넷ENABLE프롬프트정보
	 *
	 * @param telnetEabPromptInfo
	 *            텔넷ENABLE프롬프트정보
	 */
	public void setTelnetEabPromptInfo(String telnetEabPromptInfo) {
		this.telnetEabPromptInfo = telnetEabPromptInfo;
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
	 * 접속상태값
	 * 
	 * @return 접속상태값
	 */
	public String getConnStVal() {
		return connStVal;
	}

	/**
	 * 접속상태값
	 *
	 * @param connStVal
	 *            접속상태값
	 */
	public void setConnStVal(String connStVal) {
		this.connStVal = connStVal;
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
	
	public String getAutoExplrConsItmPrtyYn() {
		return autoExplrConsItmPrtyYn;
	}

	public void setAutoExplrConsItmPrtyYn(String autoExplrConsItmPrtyYn) {
		this.autoExplrConsItmPrtyYn = autoExplrConsItmPrtyYn;
	}
}

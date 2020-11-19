package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.dao.define.INDEX_TYPE;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxIndex;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.09 16:27
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OMN33812", comment = "전송망메시지")
@FxIndex(name = "OMN33812_PK", type = INDEX_TYPE.PK, columns = { "TRMS_NET_EQUIP_MSG_MGMT_NUM" })
public class OMN33812 implements IAudit {

	public OMN33812() {
	}

	@FxColumn(name = "TRMS_NET_EQUIP_MSG_MGMT_NUM", size = 15, comment = "전송망장비메시지관리번호")
	private Long trmsNetEquipMsgMgmtNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "CMPR_CHAR_STR_VAL", size = 200, comment = "비교문자열값")
	private String cmprCharStrVal;

	@FxColumn(name = "DABL_GR_CD", size = 2, comment = "장애등급코드")
	private String dablGrCd;

	@FxColumn(name = "DABL_CD", size = 30, comment = "장애코드")
	private String dablCd;

	@FxColumn(name = "DABL_APLY_YN", size = 1, comment = "장애적용여부", defValue = "'N'")
	private String dablAplyYn = "N";

	@FxColumn(name = "EMCY_DABL_YN", size = 1, comment = "긴급장애여부", defValue = "'N'")
	private String emcyDablYn = "N";

	@FxColumn(name = "NET_DABL_APLY_YN", size = 1, comment = "망장애적용여부", defValue = "'N'")
	private String netDablAplyYn = "N";

	@FxColumn(name = "NET_DABL_CD", size = 30, nullable = true, comment = "망장애코드")
	private String netDablCd;

	@FxColumn(name = "NET_DABL_NM", size = 200, nullable = true, comment = "망장애명")
	private String netDablNm;

	@FxColumn(name = "DABL_SRC_CTT", size = 1000, nullable = true, comment = "장애원인내용")
	private String dablSrcCtt;

	@FxColumn(name = "DABL_SOLV_MTHD_CTT", size = 1000, nullable = true, comment = "장애해결방법내용")
	private String dablSolvMthdCtt;

	// SYSLOG수신여부
	private boolean syslogRcvYn = false;
	
	// 비교문자열값(해지)
	private String cmprCharStrTermVal;
	
	/**
	 * 전송망장비메시지관리번호
	 * 
	 * @return 전송망장비메시지관리번호
	 */
	public Long getTrmsNetEquipMsgMgmtNum() {
		return trmsNetEquipMsgMgmtNum;
	}

	/**
	 * 전송망장비메시지관리번호
	 *
	 * @param trmsNetEquipMsgMgmtNum
	 *            전송망장비메시지관리번호
	 */
	public void setTrmsNetEquipMsgMgmtNum(Long trmsNetEquipMsgMgmtNum) {
		this.trmsNetEquipMsgMgmtNum = trmsNetEquipMsgMgmtNum;
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
	 * 장비모델코드
	 * 
	 * @return 장비모델코드
	 */
	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	/**
	 * 장비모델코드
	 *
	 * @param equipMdlCd
	 *            장비모델코드
	 */
	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	/**
	 * 비교문자열값
	 * 
	 * @return 비교문자열값
	 */
	public String getCmprCharStrVal() {
		return cmprCharStrVal;
	}

	/**
	 * 비교문자열값
	 *
	 * @param cmprCharStrVal
	 *            비교문자열값
	 */
	public void setCmprCharStrVal(String cmprCharStrVal) {
		this.cmprCharStrVal = cmprCharStrVal;
	}

	/**
	 * 장애등급코드
	 * 
	 * @return 장애등급코드
	 */
	public String getDablGrCd() {
		return dablGrCd;
	}

	/**
	 * 장애등급코드
	 *
	 * @param dablGrCd
	 *            장애등급코드
	 */
	public void setDablGrCd(String dablGrCd) {
		this.dablGrCd = dablGrCd;
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
	 * 장애적용여부
	 * 
	 * @return 장애적용여부
	 */
	public String isDablAplyYn() {
		return dablAplyYn;
	}

	/**
	 * 장애적용여부
	 *
	 * @param dablAplyYn
	 *            장애적용여부
	 */
	public void setDablAplyYn(String dablAplyYn) {
		this.dablAplyYn = dablAplyYn;
	}

	/**
	 * 긴급장애여부
	 * 
	 * @return 긴급장애여부
	 */
	public String isEmcyDablYn() {
		return emcyDablYn;
	}

	/**
	 * 긴급장애여부
	 *
	 * @param emcyDablYn
	 *            긴급장애여부
	 */
	public void setEmcyDablYn(String emcyDablYn) {
		this.emcyDablYn = emcyDablYn;
	}

	/**
	 * 망장애적용여부
	 * 
	 * @return 망장애적용여부
	 */
	public String isNetDablAplyYn() {
		return netDablAplyYn;
	}

	/**
	 * 망장애적용여부
	 *
	 * @param netDablAplyYn
	 *            망장애적용여부
	 */
	public void setNetDablAplyYn(String netDablAplyYn) {
		this.netDablAplyYn = netDablAplyYn;
	}

	/**
	 * 망장애코드
	 * 
	 * @return 망장애코드
	 */
	public String getNetDablCd() {
		return netDablCd;
	}

	/**
	 * 망장애코드
	 *
	 * @param netDablCd
	 *            망장애코드
	 */
	public void setNetDablCd(String netDablCd) {
		this.netDablCd = netDablCd;
	}

	/**
	 * 망장애명
	 * 
	 * @return 망장애명
	 */
	public String getNetDablNm() {
		return netDablNm;
	}

	/**
	 * 망장애명
	 *
	 * @param netDablNm
	 *            망장애명
	 */
	public void setNetDablNm(String netDablNm) {
		this.netDablNm = netDablNm;
	}

	/**
	 * 장애원인내용
	 * 
	 * @return 장애원인내용
	 */
	public String getDablSrcCtt() {
		return dablSrcCtt;
	}

	/**
	 * 장애원인내용
	 *
	 * @param dablSrcCtt
	 *            장애원인내용
	 */
	public void setDablSrcCtt(String dablSrcCtt) {
		this.dablSrcCtt = dablSrcCtt;
	}

	/**
	 * 장애해결방법내용
	 * 
	 * @return 장애해결방법내용
	 */
	public String getDablSolvMthdCtt() {
		return dablSolvMthdCtt;
	}

	/**
	 * 장애해결방법내용
	 *
	 * @param dablSolvMthdCtt
	 *            장애해결방법내용
	 */
	public void setDablSolvMthdCtt(String dablSolvMthdCtt) {
		this.dablSolvMthdCtt = dablSolvMthdCtt;
	}
	
	public boolean isSyslogRcvYn() {
		return syslogRcvYn;
	}

	public void setSyslogRcvYn(boolean syslogRcvYn) {
		this.syslogRcvYn = syslogRcvYn;
	}

	public String getCmprCharStrTermVal() {
		return cmprCharStrTermVal;
	}

	public void setCmprCharStrTermVal(String cmprCharStrTermVal) {
		this.cmprCharStrTermVal = cmprCharStrTermVal;
	}
}

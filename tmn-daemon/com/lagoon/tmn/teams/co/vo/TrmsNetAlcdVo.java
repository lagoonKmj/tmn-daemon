package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.lagoon.tmn.teams.fxms.dbo.OMN33812;

import subkjh.bas.co.log.Logger;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * 전송망메시지 VO (OMN33812)
 * 
 * <pre>
 * SqlUtil 클래스 이용하여 자동 생성
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@FxTable(name = "OMN33812", comment = "전송망메시지")
@SuppressWarnings("serial")
public class TrmsNetAlcdVo implements Serializable {

	public TrmsNetAlcdVo() {
	}

	public TrmsNetAlcdVo(OMN33812 omn33812) {
		setTrmsNetEquipMsgMgmtNum(omn33812.getTrmsNetEquipMsgMgmtNum());
		setEquipMdlCd(omn33812.getEquipMdlCd());
		setCmprCharStrVal(omn33812.getCmprCharStrVal());
		setDablGrCd(omn33812.getDablGrCd());
		setDablCd(omn33812.getDablCd());
		setDablAplyYn(omn33812.isDablAplyYn().equals("Y"));
		setEmcyDablYn(omn33812.isEmcyDablYn().equals("Y"));
		setNetDablAplyYn(omn33812.isNetDablAplyYn().equals("Y"));
		setNetDablCd(omn33812.getNetDablCd());
		setNetDablNm(omn33812.getNetDablNm());
	}

	@FxColumn(name = "TRMS_NET_EQUIP_MSG_MGMT_NUM", size = 15, comment = "전송망장비메시지관리번호")
	private long trmsNetEquipMsgMgmtNum;

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
	private boolean dablAplyYn = false;

	@FxColumn(name = "EMCY_DABL_YN", size = 1, comment = "긴급장애여부", defValue = "'N'")
	private boolean emcyDablYn = false;

	@FxColumn(name = "NET_DABL_APLY_YN", size = 1, comment = "망장애적용여부", defValue = "'N'")
	private boolean netDablAplyYn = false;

	@FxColumn(name = "NET_DABL_CD", size = 30, nullable = true, comment = "망장애코드")
	private String netDablCd;

	@FxColumn(name = "NET_DABL_NM", size = 200, nullable = true, comment = "망장애명")
	private String netDablNm;

	// 자동장애해제초시간
	private long autoDablRlseSecTms = 0;
	
	// SYSLOG수신여부
	private boolean syslogRcvYn = false;
	
	// 비교문자열값(해지)
	private String cmprCharStrTermVal;

	/**
	 * 전송망장비메시지관리번호
	 * 
	 * @return 전송망장비메시지관리번호
	 */
	public long getTrmsNetEquipMsgMgmtNum() {
		return trmsNetEquipMsgMgmtNum;
	}

	/**
	 * 전송망장비메시지관리번호
	 *
	 * @param trmsNetEquipMsgMgmtNum
	 *            전송망장비메시지관리번호
	 */
	public void setTrmsNetEquipMsgMgmtNum(long trmsNetEquipMsgMgmtNum) {
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
	public boolean isDablAplyYn() {
		if (dablAplyYn == false) {
			Logger.logger.info("장애 적용 하지 않음, 전송망장비메시지관리번호 : {}",
					trmsNetEquipMsgMgmtNum);
		}
		return dablAplyYn;
	}

	/**
	 * 장애적용여부
	 *
	 * @param dablAplyYn
	 *            장애적용여부
	 */
	public void setDablAplyYn(boolean dablAplyYn) {
		this.dablAplyYn = dablAplyYn;
	}

	/**
	 * 긴급장애여부
	 * 
	 * @return 긴급장애여부
	 */
	public boolean isEmcyDablYn() {
		return emcyDablYn;
	}

	/**
	 * 긴급장애여부
	 *
	 * @param emcyDablYn
	 *            긴급장애여부
	 */
	public void setEmcyDablYn(boolean emcyDablYn) {
		this.emcyDablYn = emcyDablYn;
	}

	/**
	 * 망장애적용여부
	 * 
	 * @return 망장애적용여부
	 */
	public boolean isNetDablAplyYn() {
		if (netDablAplyYn == false) {
			Logger.logger.info("망 장애 적용 하지 않음, 전송망장비메시지관리번호 : {}",
					trmsNetEquipMsgMgmtNum);
		}
		return netDablAplyYn;
	}

	/**
	 * 망장애적용여부
	 *
	 * @param netDablAplyYn
	 *            망장애적용여부
	 */
	public void setNetDablAplyYn(boolean netDablAplyYn) {
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

	public boolean match(String str) {
		return cmprCharStrVal.equals(str);
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

	@Override
	public String toString() {
		return "TrmsNetAlcdVo [trmsNetEquipMsgMgmtNum="
				+ trmsNetEquipMsgMgmtNum + ", auditId=" + auditId
				+ ", auditDtm=" + auditDtm + ", equipMdlCd=" + equipMdlCd
				+ ", cmprCharStrVal=" + cmprCharStrVal + ", dablGrCd="
				+ dablGrCd + ", dablCd=" + dablCd + ", dablAplyYn="
				+ dablAplyYn + ", emcyDablYn=" + emcyDablYn
				+ ", netDablAplyYn=" + netDablAplyYn + ", netDablCd="
				+ netDablCd + ", netDablNm=" + netDablNm + "]";
	}

}

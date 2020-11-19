package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.dao.define.INDEX_TYPE;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxIndex;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.07.31 11:12
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN33810", comment = "전송장비장애이력")
@FxIndex(name = "OMN33810_PK", type = INDEX_TYPE.PK, columns = { "TRMS_EQUIP_DABL_NUM" })
public class OMN33810 implements Serializable {

	public OMN33810() {
	}

	@FxColumn(name = "TRMS_EQUIP_DABL_NUM", size = 15, comment = "전송장비장애번호")
	private long trmsEquipDablNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "DABL_GR_CD", size = 2, comment = "장애등급코드")
	private String dablGrCd;

	@FxColumn(name = "DABL_CD", size = 30, comment = "장애코드")
	private String dablCd;

	@FxColumn(name = "DABL_MSG_CTT", size = 1000, nullable = true, comment = "장애메시지내용")
	private String dablMsgCtt;

	@FxColumn(name = "OCCR_DTM", size = 14, comment = "발생일시")
	private String occrDtm;

	@FxColumn(name = "OCCR_RCV_DTM", size = 0, comment = "발생수신일시")
	private Timestamp occrRcvDtm;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_NM", size = 300, nullable = true, comment = "장비명")
	private String equipNm;

	@FxColumn(name = "EQUIP_TID_VAL", size = 50, nullable = true, comment = "장비TID값")
	private String equipTidVal;

	@FxColumn(name = "EQUIP_IP_ADDR", size = 23, nullable = true, comment = "장비IP주소")
	private String equipIpAddr;

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, nullable = true, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "TPO_CD", size = 6, nullable = true, comment = "국사코드")
	private String tpoCd;

	@FxColumn(name = "SCARD_DESC", size = 100, nullable = true, comment = "SLOT카드설명")
	private String scardDesc;

	@FxColumn(name = "PORT_DESC", size = 500, nullable = true, comment = "포트설명")
	private String portDesc;

	@FxColumn(name = "DABL_OCCR_LOC_DESC", size = 1000, nullable = true, comment = "장애발생위치설명")
	private String dablOccrLocDesc;

	@FxColumn(name = "ALL_MSG_CTT", size = 2000, comment = "전체메시지내용")
	private String allMsgCtt;

	@FxColumn(name = "TRMS_NET_EQUIP_MSG_MGMT_NUM", size = 15, comment = "전송망장비메시지관리번호")
	private long trmsNetEquipMsgMgmtNum;

	@FxColumn(name = "CMPR_CHAR_STR_VAL", size = 200, comment = "비교문자열값")
	private String cmprCharStrVal;

	@FxColumn(name = "OPER_DABL_YN", size = 1, comment = "작업장애여부", defValue = "'N'")
	private boolean operDablYn = false;

	@FxColumn(name = "EMCY_DABL_YN", size = 1, comment = "긴급장애여부", defValue = "'N'")
	private boolean emcyDablYn = false;

	@FxColumn(name = "DMG_LINE_CNT", size = 9, comment = "피해회선수")
	private int dmgLineCnt;

	@FxColumn(name = "DABL_DUP_CNT", size = 12, comment = "장애중복횟수")
	private long dablDupCnt;

	@FxColumn(name = "EMS_ID", size = 12, nullable = true, comment = "EMSID")
	private String emsId;

	@FxColumn(name = "EMS_ALM_VAL", size = 100, nullable = true, comment = "EMS알람값")
	private String emsAlmVal;

	@FxColumn(name = "RLSE_DTM", size = 14, nullable = true, comment = "해제일시")
	private String rlseDtm;

	@FxColumn(name = "RLSE_RCV_DTM", size = 0, nullable = true, comment = "해제수신일시")
	private Timestamp rlseRcvDtm;

	@FxColumn(name = "DABL_SEND_YN", size = 1, comment = "장애송신여부", defValue = "'N'")
	private boolean dablSendYn = false;

	@FxColumn(name = "DABL_SEND_DTM", size = 0, nullable = true, comment = "장애송신일시")
	private Timestamp dablSendDtm;

	@FxColumn(name = "DABL_SEND_RSLT_VAL", size = 1, nullable = true, comment = "장애송신결과값")
	private String dablSendRsltVal;

	@FxColumn(name = "DABL_RLSE_SEND_YN", size = 1, comment = "장애해제송신여부", defValue = "'N'")
	private boolean dablRlseSendYn = false;

	@FxColumn(name = "DABL_RLSE_SEND_DTM", size = 0, nullable = true, comment = "장애해제송신일시")
	private Timestamp dablRlseSendDtm;

	@FxColumn(name = "DABL_RLSE_SEND_RSLT_VAL", size = 1, nullable = true, comment = "장애해제송신결과값")
	private String dablRlseSendRsltVal;

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
	 * 장애메시지내용
	 * 
	 * @return 장애메시지내용
	 */
	public String getDablMsgCtt() {
		return dablMsgCtt;
	}

	/**
	 * 장애메시지내용
	 *
	 * @param dablMsgCtt
	 *            장애메시지내용
	 */
	public void setDablMsgCtt(String dablMsgCtt) {
		this.dablMsgCtt = dablMsgCtt;
	}

	/**
	 * 발생일시
	 * 
	 * @return 발생일시
	 */
	public String getOccrDtm() {
		return occrDtm;
	}

	/**
	 * 발생일시
	 *
	 * @param occrDtm
	 *            발생일시
	 */
	public void setOccrDtm(String occrDtm) {
		this.occrDtm = occrDtm;
	}

	/**
	 * 발생수신일시
	 * 
	 * @return 발생수신일시
	 */
	public Timestamp getOccrRcvDtm() {
		return occrRcvDtm;
	}

	/**
	 * 발생수신일시
	 *
	 * @param occrRcvDtm
	 *            발생수신일시
	 */
	public void setOccrRcvDtm(Timestamp occrRcvDtm) {
		this.occrRcvDtm = occrRcvDtm;
	}

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
	 * 장비명
	 * 
	 * @return 장비명
	 */
	public String getEquipNm() {
		return equipNm;
	}

	/**
	 * 장비명
	 *
	 * @param equipNm
	 *            장비명
	 */
	public void setEquipNm(String equipNm) {
		this.equipNm = equipNm;
	}

	/**
	 * 장비TID값
	 * 
	 * @return 장비TID값
	 */
	public String getEquipTidVal() {
		return equipTidVal;
	}

	/**
	 * 장비TID값
	 *
	 * @param equipTidVal
	 *            장비TID값
	 */
	public void setEquipTidVal(String equipTidVal) {
		this.equipTidVal = equipTidVal;
	}

	/**
	 * 장비IP주소
	 * 
	 * @return 장비IP주소
	 */
	public String getEquipIpAddr() {
		return equipIpAddr;
	}

	/**
	 * 장비IP주소
	 *
	 * @param equipIpAddr
	 *            장비IP주소
	 */
	public void setEquipIpAddr(String equipIpAddr) {
		this.equipIpAddr = equipIpAddr;
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
	 * 국사코드
	 * 
	 * @return 국사코드
	 */
	public String getTpoCd() {
		return tpoCd;
	}

	/**
	 * 국사코드
	 *
	 * @param tpoCd
	 *            국사코드
	 */
	public void setTpoCd(String tpoCd) {
		this.tpoCd = tpoCd;
	}

	/**
	 * SLOT카드설명
	 * 
	 * @return SLOT카드설명
	 */
	public String getScardDesc() {
		return scardDesc;
	}

	/**
	 * SLOT카드설명
	 *
	 * @param scardDesc
	 *            SLOT카드설명
	 */
	public void setScardDesc(String scardDesc) {
		this.scardDesc = scardDesc;
	}

	/**
	 * 포트설명
	 * 
	 * @return 포트설명
	 */
	public String getPortDesc() {
		return portDesc;
	}

	/**
	 * 포트설명
	 *
	 * @param portDesc
	 *            포트설명
	 */
	public void setPortDesc(String portDesc) {
		this.portDesc = portDesc;
	}

	/**
	 * 장애발생위치설명
	 * 
	 * @return 장애발생위치설명
	 */
	public String getDablOccrLocDesc() {
		return dablOccrLocDesc;
	}

	/**
	 * 장애발생위치설명
	 *
	 * @param dablOccrLocDesc
	 *            장애발생위치설명
	 */
	public void setDablOccrLocDesc(String dablOccrLocDesc) {
		this.dablOccrLocDesc = dablOccrLocDesc;
	}

	/**
	 * 전체메시지내용
	 * 
	 * @return 전체메시지내용
	 */
	public String getAllMsgCtt() {
		return allMsgCtt;
	}

	/**
	 * 전체메시지내용
	 *
	 * @param allMsgCtt
	 *            전체메시지내용
	 */
	public void setAllMsgCtt(String allMsgCtt) {
		this.allMsgCtt = allMsgCtt;
	}

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
	 * 작업장애여부
	 * 
	 * @return 작업장애여부
	 */
	public boolean isOperDablYn() {
		return operDablYn;
	}

	/**
	 * 작업장애여부
	 *
	 * @param operDablYn
	 *            작업장애여부
	 */
	public void setOperDablYn(boolean operDablYn) {
		this.operDablYn = operDablYn;
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
	 * 피해회선수
	 * 
	 * @return 피해회선수
	 */
	public int getDmgLineCnt() {
		return dmgLineCnt;
	}

	/**
	 * 피해회선수
	 *
	 * @param dmgLineCnt
	 *            피해회선수
	 */
	public void setDmgLineCnt(int dmgLineCnt) {
		this.dmgLineCnt = dmgLineCnt;
	}

	/**
	 * 장애중복횟수
	 * 
	 * @return 장애중복횟수
	 */
	public long getDablDupCnt() {
		return dablDupCnt;
	}

	/**
	 * 장애중복횟수
	 *
	 * @param dablDupCnt
	 *            장애중복횟수
	 */
	public void setDablDupCnt(long dablDupCnt) {
		this.dablDupCnt = dablDupCnt;
	}

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
	 * EMS알람값
	 * 
	 * @return EMS알람값
	 */
	public String getEmsAlmVal() {
		return emsAlmVal;
	}

	/**
	 * EMS알람값
	 *
	 * @param emsAlmVal
	 *            EMS알람값
	 */
	public void setEmsAlmVal(String emsAlmVal) {
		this.emsAlmVal = emsAlmVal;
	}

	/**
	 * 해제일시
	 * 
	 * @return 해제일시
	 */
	public String getRlseDtm() {
		return rlseDtm;
	}

	/**
	 * 해제일시
	 *
	 * @param rlseDtm
	 *            해제일시
	 */
	public void setRlseDtm(String rlseDtm) {
		this.rlseDtm = rlseDtm;
	}

	/**
	 * 해제수신일시
	 * 
	 * @return 해제수신일시
	 */
	public Timestamp getRlseRcvDtm() {
		return rlseRcvDtm;
	}

	/**
	 * 해제수신일시
	 *
	 * @param rlseRcvDtm
	 *            해제수신일시
	 */
	public void setRlseRcvDtm(Timestamp rlseRcvDtm) {
		this.rlseRcvDtm = rlseRcvDtm;
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

	/**
	 * 장애해제송신여부
	 * 
	 * @return 장애해제송신여부
	 */
	public boolean isDablRlseSendYn() {
		return dablRlseSendYn;
	}

	/**
	 * 장애해제송신여부
	 *
	 * @param dablRlseSendYn
	 *            장애해제송신여부
	 */
	public void setDablRlseSendYn(boolean dablRlseSendYn) {
		this.dablRlseSendYn = dablRlseSendYn;
	}

	/**
	 * 장애해제송신일시
	 * 
	 * @return 장애해제송신일시
	 */
	public Timestamp getDablRlseSendDtm() {
		return dablRlseSendDtm;
	}

	/**
	 * 장애해제송신일시
	 *
	 * @param dablRlseSendDtm
	 *            장애해제송신일시
	 */
	public void setDablRlseSendDtm(Timestamp dablRlseSendDtm) {
		this.dablRlseSendDtm = dablRlseSendDtm;
	}

	/**
	 * 장애해제송신결과값
	 * 
	 * @return 장애해제송신결과값
	 */
	public String getDablRlseSendRsltVal() {
		return dablRlseSendRsltVal;
	}

	/**
	 * 장애해제송신결과값
	 *
	 * @param dablRlseSendRsltVal
	 *            장애해제송신결과값
	 */
	public void setDablRlseSendRsltVal(String dablRlseSendRsltVal) {
		this.dablRlseSendRsltVal = dablRlseSendRsltVal;
	}
}

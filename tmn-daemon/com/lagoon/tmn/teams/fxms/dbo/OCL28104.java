package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28104", comment = "전송장비클럭품질수집내역")
public class OCL28104 {

	public OCL28104() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "EQUIP_CLK_VAL", size = 30, comment = "장비클럭값", defValue = "'N'")
	private String equipClkVal = "N";

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "RCV_SYNC_QLTY_INFO", size = 30, nullable = true, comment = "수신동기화품질정보")
	private String rcvSyncQltyInfo;

	@FxColumn(name = "SET_RCV_SYNC_QLTY_INFO", size = 30, nullable = true, comment = "설정수신동기화품질정보")
	private String setRcvSyncQltyInfo;

	@FxColumn(name = "SEND_SYNC_QLTY_INFO", size = 30, nullable = true, comment = "송신동기화품질정보")
	private String sendSyncQltyInfo;

	@FxColumn(name = "SET_SEND_SYNC_QLTY_INFO", size = 30, nullable = true, comment = "설정송신동기화품질정보")
	private String setSendSyncQltyInfo;

	@FxColumn(name = "AVAIL_YN", size = 1, comment = "가용여부", defValue = "'N'")
	private String availYn = "N";

	@FxColumn(name = "SA_BIT_SET_NM", size = 50, nullable = true, comment = "SA비트설정명")
	private String saBitSetNm;

	@FxColumn(name = "SA_BIT_ST_NM", size = 50, nullable = true, comment = "SA비트상태명")
	private String saBitStNm;

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
	 * 장비클럭값
	 * 
	 * @return 장비클럭값
	 */
	public String getEquipClkVal() {
		return equipClkVal;
	}

	/**
	 * 장비클럭값
	 *
	 * @param equipClkVal
	 *            장비클럭값
	 */
	public void setEquipClkVal(String equipClkVal) {
		this.equipClkVal = equipClkVal;
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
	 * 수신동기화품질정보
	 * 
	 * @return 수신동기화품질정보
	 */
	public String getRcvSyncQltyInfo() {
		return rcvSyncQltyInfo;
	}

	/**
	 * 수신동기화품질정보
	 *
	 * @param rcvSyncQltyInfo
	 *            수신동기화품질정보
	 */
	public void setRcvSyncQltyInfo(String rcvSyncQltyInfo) {
		this.rcvSyncQltyInfo = rcvSyncQltyInfo;
	}

	/**
	 * 설정수신동기화품질정보
	 * 
	 * @return 설정수신동기화품질정보
	 */
	public String getSetRcvSyncQltyInfo() {
		return setRcvSyncQltyInfo;
	}

	/**
	 * 설정수신동기화품질정보
	 *
	 * @param setRcvSyncQltyInfo
	 *            설정수신동기화품질정보
	 */
	public void setSetRcvSyncQltyInfo(String setRcvSyncQltyInfo) {
		this.setRcvSyncQltyInfo = setRcvSyncQltyInfo;
	}

	/**
	 * 송신동기화품질정보
	 * 
	 * @return 송신동기화품질정보
	 */
	public String getSendSyncQltyInfo() {
		return sendSyncQltyInfo;
	}

	/**
	 * 송신동기화품질정보
	 *
	 * @param sendSyncQltyInfo
	 *            송신동기화품질정보
	 */
	public void setSendSyncQltyInfo(String sendSyncQltyInfo) {
		this.sendSyncQltyInfo = sendSyncQltyInfo;
	}

	/**
	 * 설정송신동기화품질정보
	 * 
	 * @return 설정송신동기화품질정보
	 */
	public String getSetSendSyncQltyInfo() {
		return setSendSyncQltyInfo;
	}

	/**
	 * 설정송신동기화품질정보
	 *
	 * @param setSendSyncQltyInfo
	 *            설정송신동기화품질정보
	 */
	public void setSetSendSyncQltyInfo(String setSendSyncQltyInfo) {
		this.setSendSyncQltyInfo = setSendSyncQltyInfo;
	}

	/**
	 * 가용여부
	 * 
	 * @return 가용여부
	 */
	public String isAvailYn() {
		return availYn;
	}

	/**
	 * 가용여부
	 *
	 * @param availYn
	 *            가용여부
	 */
	public void setAvailYn(String availYn) {
		this.availYn = availYn;
	}

	/**
	 * SA비트설정명
	 * 
	 * @return SA비트설정명
	 */
	public String getSaBitSetNm() {
		return saBitSetNm;
	}

	/**
	 * SA비트설정명
	 *
	 * @param saBitSetNm
	 *            SA비트설정명
	 */
	public void setSaBitSetNm(String saBitSetNm) {
		this.saBitSetNm = saBitSetNm;
	}

	/**
	 * SA비트상태명
	 * 
	 * @return SA비트상태명
	 */
	public String getSaBitStNm() {
		return saBitStNm;
	}

	/**
	 * SA비트상태명
	 *
	 * @param saBitStNm
	 *            SA비트상태명
	 */
	public void setSaBitStNm(String saBitStNm) {
		this.saBitStNm = saBitStNm;
	}
	
	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public Long getInspSerNum() {
		return inspSerNum;
	}

	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public void setInspSerNum(Long inspSerNum) {
		this.inspSerNum = inspSerNum;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.06.18 13:26
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10400", comment = "장비포트기본")
public class OIV10400 {

	public OIV10400() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_PORT_ID", size = 15, comment = "장비포트ID")
	private String equipPortId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_CONS_ITM_ID", size = 12, nullable = true, comment = "장비구성품ID")
	private String equipConsItmId;

	@FxColumn(name = "MOUN_LOC_NUM", size = 10, nullable = true, comment = "실장위치번호")
	private String mounLocNum;

	@FxColumn(name = "PORT_NUM", size = 10, nullable = true, comment = "포트번호")
	private String portNum;

	@FxColumn(name = "PORT_ALS_NM", size = 300, nullable = true, comment = "포트별칭명")
	private String portAlsNm;

	@FxColumn(name = "PORT_TYP_CD", size = 3, comment = "포트유형코드")
	private String portTypCd;

	@FxColumn(name = "PORT_SPEED_CD", size = 6, comment = "포트속도코드")
	private String portSpeedCd;

	@FxColumn(name = "POLLING_M_OBJ_YN", size = 1, comment = "POLLING감시대상여부", defValue = "'N'")
	private boolean pollingMObjYn = false;

	@FxColumn(name = "PING_M_OBJ_YN", size = 1, comment = "PING감시대상여부", defValue = "'N'")
	private boolean pingMObjYn = false;

	@FxColumn(name = "RGST_CYCL", size = 5, nullable = true, comment = "등록주기")
	private int rgstCycl;

	@FxColumn(name = "PORT_DESC", size = 500, nullable = true, comment = "포트설명")
	private String portDesc;
	
	@FxColumn(name = "DATA_RGST_MTHD_CL_CD", size = 2, comment = "데이터등록방식구분코드", defValue = "01")
	private String dataRgstMthdClCd;

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
	 * 장비포트ID
	 * 
	 * @return 장비포트ID
	 */
	public String getEquipPortId() {
		return equipPortId;
	}

	/**
	 * 장비포트ID
	 *
	 * @param equipPortId
	 *            장비포트ID
	 */
	public void setEquipPortId(String equipPortId) {
		this.equipPortId = equipPortId;
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
	 * 장비구성품ID
	 * 
	 * @return 장비구성품ID
	 */
	public String getEquipConsItmId() {
		return equipConsItmId;
	}

	/**
	 * 장비구성품ID
	 *
	 * @param equipConsItmId
	 *            장비구성품ID
	 */
	public void setEquipConsItmId(String equipConsItmId) {
		this.equipConsItmId = equipConsItmId;
	}

	/**
	 * 실장위치번호
	 * 
	 * @return 실장위치번호
	 */
	public String getMounLocNum() {
		return mounLocNum;
	}

	/**
	 * 실장위치번호
	 *
	 * @param mounLocNum
	 *            실장위치번호
	 */
	public void setMounLocNum(String mounLocNum) {
		this.mounLocNum = mounLocNum;
	}

	/**
	 * 포트번호
	 * 
	 * @return 포트번호
	 */
	public String getPortNum() {
		return portNum;
	}

	/**
	 * 포트번호
	 *
	 * @param portNum
	 *            포트번호
	 */
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}

	/**
	 * 포트별칭명
	 * 
	 * @return 포트별칭명
	 */
	public String getPortAlsNm() {
		return portAlsNm;
	}

	/**
	 * 포트별칭명
	 *
	 * @param portAlsNm
	 *            포트별칭명
	 */
	public void setPortAlsNm(String portAlsNm) {
		this.portAlsNm = portAlsNm;
	}

	/**
	 * 포트유형코드
	 * 
	 * @return 포트유형코드
	 */
	public String getPortTypCd() {
		return portTypCd;
	}

	/**
	 * 포트유형코드
	 *
	 * @param portTypCd
	 *            포트유형코드
	 */
	public void setPortTypCd(String portTypCd) {
		this.portTypCd = portTypCd;
	}

	/**
	 * 포트속도코드
	 * 
	 * @return 포트속도코드
	 */
	public String getPortSpeedCd() {
		return portSpeedCd;
	}

	/**
	 * 포트속도코드
	 *
	 * @param portSpeedCd
	 *            포트속도코드
	 */
	public void setPortSpeedCd(String portSpeedCd) {
		this.portSpeedCd = portSpeedCd;
	}

	/**
	 * POLLING감시대상여부
	 * 
	 * @return POLLING감시대상여부
	 */
	public boolean isPollingMObjYn() {
		return pollingMObjYn;
	}

	/**
	 * POLLING감시대상여부
	 *
	 * @param pollingMObjYn
	 *            POLLING감시대상여부
	 */
	public void setPollingMObjYn(boolean pollingMObjYn) {
		this.pollingMObjYn = pollingMObjYn;
	}

	/**
	 * PING감시대상여부
	 * 
	 * @return PING감시대상여부
	 */
	public boolean isPingMObjYn() {
		return pingMObjYn;
	}

	/**
	 * PING감시대상여부
	 *
	 * @param pingMObjYn
	 *            PING감시대상여부
	 */
	public void setPingMObjYn(boolean pingMObjYn) {
		this.pingMObjYn = pingMObjYn;
	}

	/**
	 * 등록주기
	 * 
	 * @return 등록주기
	 */
	public int getRgstCycl() {
		return rgstCycl;
	}

	/**
	 * 등록주기
	 *
	 * @param rgstCycl
	 *            등록주기
	 */
	public void setRgstCycl(int rgstCycl) {
		this.rgstCycl = rgstCycl;
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
	
	public String getDataRgstMthdClCd() {
		return dataRgstMthdClCd;
	}

	public void setDataRgstMthdClCd(String dataRgstMthdClCd) {
		this.dataRgstMthdClCd = dataRgstMthdClCd;
	}
	
}

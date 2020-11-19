package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.14 09:39
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10178", comment = "장비수집내역")
public class OIV10178 implements Serializable {

	public OIV10178() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "CLCT_DTM", size = 14, nullable = true, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "EQUIP_IP_ADDR", size = 23, nullable = true, comment = "장비IP주소")
	private String equipIpAddr;

	@FxColumn(name = "EQUIP_ADD_INFO", size = 500, nullable = true, comment = "장비부가정보")
	private String equipAddInfo;

	@FxColumn(name = "EQUIP_UPTIME_VAL", size = 100, nullable = true, comment = "장비UPTIME값")
	private String equipUptimeVal;

	@FxColumn(name = "EQUIP_UPTIME_VAL_CHG_DTM", size = 0, nullable = true, comment = "장비UPTIME값변경일시")
	private Timestamp equipUptimeValChgDtm;

	@FxColumn(name = "EQUIP_SYS_NM", size = 300, nullable = true, comment = "장비시스템명")
	private String equipSysNm;

	@FxColumn(name = "EQUIP_SET_LOC_DESC", size = 500, nullable = true, comment = "장비설치위치설명")
	private String equipSetLocDesc;

	@FxColumn(name = "OPER_EQUIP_MDL_CD", size = 10, comment = "운영장비모델코드")
	private String operEquipMdlCd;

	@FxColumn(name = "FW_VER_INFO", size = 100, nullable = true, comment = "펌웨어버전정보")
	private String fwVerInfo;

	@FxColumn(name = "PING_ST_VAL", size = 8, nullable = true, comment = "PING상태값")
	private String pingStVal;

	@FxColumn(name = "PING_ST_CHG_DTM", size = 0, nullable = true, comment = "PING상태변경일시")
	private Timestamp pingStChgDtm;

	@FxColumn(name = "SNMP_ST_VAL", size = 15, nullable = true, comment = "SNMP상태값")
	private Long snmpStVal;

	@FxColumn(name = "SNMP_ST_CHG_DTM", size = 0, nullable = true, comment = "SNMP상태변경일시")
	private Timestamp snmpStChgDtm;

	@FxColumn(name = "BTG_IMG_FILE_NM", size = 500, nullable = true, comment = "부팅이미지파일명")
	private String btgImgFileNm;

	@FxColumn(name = "RE_LOD_RSN_CTT", size = 1000, nullable = true, comment = "재적재사유내용")
	private String reLodRsnCtt;

	@FxColumn(name = "EQUIP_CHRGR_CNTC_PLC_INFO", size = 400, nullable = true, comment = "장비담당자연락처정보")
	private String equipChrgrCntcPlcInfo;

	@FxColumn(name = "EQUIP_SVC_CNT", size = 20, nullable = true, comment = "장비서비스수")
	private Long equipSvcCnt;

	@FxColumn(name = "FW_VER_AGRMT_YN", size = 1, nullable = true, comment = "펌웨어버전일치여부", defValue = "'N'")
	private String fwVerAgrmtYn = "N";

	@FxColumn(name = "PROMPT_INFO", size = 50, nullable = true, comment = "프롬프트정보")
	private String promptInfo;

	@FxColumn(name = "MFACT_SER_NUM", size = 20, nullable = true, comment = "제조사일련번호")
	private String mfactSerNum;

	@FxColumn(name = "EQUIP_VER_INFO", size = 100, nullable = true, comment = "장비버전정보")
	private String equipVerInfo;

	@FxColumn(name = "TRAP_RCV_DTM", size = 0, nullable = true, comment = "트랩수신일시")
	private Timestamp trapRcvDtm;

	@FxColumn(name = "OID_DESC", size = 200, nullable = true, comment = "OID설명")
	private String oidDesc;

	@FxColumn(name = "SYS_LOG_RCV_DTM", size = 14, nullable = true, comment = "시스템로그수신일시")
	private String sysLogRcvDtm;

	@FxColumn(name = "VRF_PING_ST_VAL", size = 2, nullable = true, comment = "VRFPING상태값", defValue = "1")
	private Integer vrfPingStVal = 1;

	@FxColumn(name = "VRF_PING_ST_CHG_DTM", size = 14, nullable = true, comment = "VRFPING상태변경일시")
	private String vrfPingStChgDtm;

	@FxColumn(name = "LAST_DABL_RCV_DTM", size = 0, nullable = true, comment = "최종장애수신일시")
	private Timestamp lastDablRcvDtm;

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
	 * 수집일시
	 * 
	 * @return 수집일시
	 */
	public String getClctDtm() {
		return clctDtm;
	}

	/**
	 * 수집일시
	 *
	 * @param clctDtm
	 *            수집일시
	 */
	public void setClctDtm(String clctDtm) {
		this.clctDtm = clctDtm;
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
	 * 장비부가정보
	 * 
	 * @return 장비부가정보
	 */
	public String getEquipAddInfo() {
		return equipAddInfo;
	}

	/**
	 * 장비부가정보
	 *
	 * @param equipAddInfo
	 *            장비부가정보
	 */
	public void setEquipAddInfo(String equipAddInfo) {
		this.equipAddInfo = equipAddInfo;
	}

	/**
	 * 장비UPTIME값
	 * 
	 * @return 장비UPTIME값
	 */
	public String getEquipUptimeVal() {
		return equipUptimeVal;
	}

	/**
	 * 장비UPTIME값
	 *
	 * @param equipUptimeVal
	 *            장비UPTIME값
	 */
	public void setEquipUptimeVal(String equipUptimeVal) {
		this.equipUptimeVal = equipUptimeVal;
	}

	/**
	 * 장비UPTIME값변경일시
	 * 
	 * @return 장비UPTIME값변경일시
	 */
	public Timestamp getEquipUptimeValChgDtm() {
		return equipUptimeValChgDtm;
	}

	/**
	 * 장비UPTIME값변경일시
	 *
	 * @param equipUptimeValChgDtm
	 *            장비UPTIME값변경일시
	 */
	public void setEquipUptimeValChgDtm(Timestamp equipUptimeValChgDtm) {
		this.equipUptimeValChgDtm = equipUptimeValChgDtm;
	}

	/**
	 * 장비시스템명
	 * 
	 * @return 장비시스템명
	 */
	public String getEquipSysNm() {
		return equipSysNm;
	}

	/**
	 * 장비시스템명
	 *
	 * @param equipSysNm
	 *            장비시스템명
	 */
	public void setEquipSysNm(String equipSysNm) {
		this.equipSysNm = equipSysNm;
	}

	/**
	 * 장비설치위치설명
	 * 
	 * @return 장비설치위치설명
	 */
	public String getEquipSetLocDesc() {
		return equipSetLocDesc;
	}

	/**
	 * 장비설치위치설명
	 *
	 * @param equipSetLocDesc
	 *            장비설치위치설명
	 */
	public void setEquipSetLocDesc(String equipSetLocDesc) {
		this.equipSetLocDesc = equipSetLocDesc;
	}

	/**
	 * 운영장비모델코드
	 * 
	 * @return 운영장비모델코드
	 */
	public String getOperEquipMdlCd() {
		return operEquipMdlCd;
	}

	/**
	 * 운영장비모델코드
	 *
	 * @param operEquipMdlCd
	 *            운영장비모델코드
	 */
	public void setOperEquipMdlCd(String operEquipMdlCd) {
		this.operEquipMdlCd = operEquipMdlCd;
	}

	/**
	 * 펌웨어버전정보
	 * 
	 * @return 펌웨어버전정보
	 */
	public String getFwVerInfo() {
		return fwVerInfo;
	}

	/**
	 * 펌웨어버전정보
	 *
	 * @param fwVerInfo
	 *            펌웨어버전정보
	 */
	public void setFwVerInfo(String fwVerInfo) {
		this.fwVerInfo = fwVerInfo;
	}

	/**
	 * PING상태값
	 * 
	 * @return PING상태값
	 */
	public String getPingStVal() {
		return pingStVal;
	}

	/**
	 * PING상태값
	 *
	 * @param pingStVal
	 *            PING상태값
	 */
	public void setPingStVal(String pingStVal) {
		this.pingStVal = pingStVal;
	}

	/**
	 * PING상태변경일시
	 * 
	 * @return PING상태변경일시
	 */
	public Timestamp getPingStChgDtm() {
		return pingStChgDtm;
	}

	/**
	 * PING상태변경일시
	 *
	 * @param pingStChgDtm
	 *            PING상태변경일시
	 */
	public void setPingStChgDtm(Timestamp pingStChgDtm) {
		this.pingStChgDtm = pingStChgDtm;
	}

	/**
	 * SNMP상태값
	 * 
	 * @return SNMP상태값
	 */
	public Long getSnmpStVal() {
		return snmpStVal;
	}

	/**
	 * SNMP상태값
	 *
	 * @param snmpStVal
	 *            SNMP상태값
	 */
	public void setSnmpStVal(Long snmpStVal) {
		this.snmpStVal = snmpStVal;
	}

	/**
	 * SNMP상태변경일시
	 * 
	 * @return SNMP상태변경일시
	 */
	public Timestamp getSnmpStChgDtm() {
		return snmpStChgDtm;
	}

	/**
	 * SNMP상태변경일시
	 *
	 * @param snmpStChgDtm
	 *            SNMP상태변경일시
	 */
	public void setSnmpStChgDtm(Timestamp snmpStChgDtm) {
		this.snmpStChgDtm = snmpStChgDtm;
	}

	/**
	 * 부팅이미지파일명
	 * 
	 * @return 부팅이미지파일명
	 */
	public String getBtgImgFileNm() {
		return btgImgFileNm;
	}

	/**
	 * 부팅이미지파일명
	 *
	 * @param btgImgFileNm
	 *            부팅이미지파일명
	 */
	public void setBtgImgFileNm(String btgImgFileNm) {
		this.btgImgFileNm = btgImgFileNm;
	}

	/**
	 * 재적재사유내용
	 * 
	 * @return 재적재사유내용
	 */
	public String getReLodRsnCtt() {
		return reLodRsnCtt;
	}

	/**
	 * 재적재사유내용
	 *
	 * @param reLodRsnCtt
	 *            재적재사유내용
	 */
	public void setReLodRsnCtt(String reLodRsnCtt) {
		this.reLodRsnCtt = reLodRsnCtt;
	}

	/**
	 * 장비담당자연락처정보
	 * 
	 * @return 장비담당자연락처정보
	 */
	public String getEquipChrgrCntcPlcInfo() {
		return equipChrgrCntcPlcInfo;
	}

	/**
	 * 장비담당자연락처정보
	 *
	 * @param equipChrgrCntcPlcInfo
	 *            장비담당자연락처정보
	 */
	public void setEquipChrgrCntcPlcInfo(String equipChrgrCntcPlcInfo) {
		this.equipChrgrCntcPlcInfo = equipChrgrCntcPlcInfo;
	}

	/**
	 * 장비서비스수
	 * 
	 * @return 장비서비스수
	 */
	public Long getEquipSvcCnt() {
		return equipSvcCnt;
	}

	/**
	 * 장비서비스수
	 *
	 * @param equipSvcCnt
	 *            장비서비스수
	 */
	public void setEquipSvcCnt(Long equipSvcCnt) {
		this.equipSvcCnt = equipSvcCnt;
	}

	/**
	 * 펌웨어버전일치여부
	 * 
	 * @return 펌웨어버전일치여부
	 */
	public String isFwVerAgrmtYn() {
		return fwVerAgrmtYn;
	}

	/**
	 * 펌웨어버전일치여부
	 *
	 * @param fwVerAgrmtYn
	 *            펌웨어버전일치여부
	 */
	public void setFwVerAgrmtYn(String fwVerAgrmtYn) {
		this.fwVerAgrmtYn = fwVerAgrmtYn;
	}

	/**
	 * 프롬프트정보
	 * 
	 * @return 프롬프트정보
	 */
	public String getPromptInfo() {
		return promptInfo;
	}

	/**
	 * 프롬프트정보
	 *
	 * @param promptInfo
	 *            프롬프트정보
	 */
	public void setPromptInfo(String promptInfo) {
		this.promptInfo = promptInfo;
	}

	/**
	 * 제조사일련번호
	 * 
	 * @return 제조사일련번호
	 */
	public String getMfactSerNum() {
		return mfactSerNum;
	}

	/**
	 * 제조사일련번호
	 *
	 * @param mfactSerNum
	 *            제조사일련번호
	 */
	public void setMfactSerNum(String mfactSerNum) {
		this.mfactSerNum = mfactSerNum;
	}

	/**
	 * 장비버전정보
	 * 
	 * @return 장비버전정보
	 */
	public String getEquipVerInfo() {
		return equipVerInfo;
	}

	/**
	 * 장비버전정보
	 *
	 * @param equipVerInfo
	 *            장비버전정보
	 */
	public void setEquipVerInfo(String equipVerInfo) {
		this.equipVerInfo = equipVerInfo;
	}

	/**
	 * 트랩수신일시
	 * 
	 * @return 트랩수신일시
	 */
	public Timestamp getTrapRcvDtm() {
		return trapRcvDtm;
	}

	/**
	 * 트랩수신일시
	 *
	 * @param trapRcvDtm
	 *            트랩수신일시
	 */
	public void setTrapRcvDtm(Timestamp trapRcvDtm) {
		this.trapRcvDtm = trapRcvDtm;
	}

	/**
	 * OID설명
	 * 
	 * @return OID설명
	 */
	public String getOidDesc() {
		return oidDesc;
	}

	/**
	 * OID설명
	 *
	 * @param oidDesc
	 *            OID설명
	 */
	public void setOidDesc(String oidDesc) {
		this.oidDesc = oidDesc;
	}

	/**
	 * 시스템로그수신일시
	 * 
	 * @return 시스템로그수신일시
	 */
	public String getSysLogRcvDtm() {
		return sysLogRcvDtm;
	}

	/**
	 * 시스템로그수신일시
	 *
	 * @param sysLogRcvDtm
	 *            시스템로그수신일시
	 */
	public void setSysLogRcvDtm(String sysLogRcvDtm) {
		this.sysLogRcvDtm = sysLogRcvDtm;
	}

	/**
	 * VRFPING상태값
	 * 
	 * @return VRFPING상태값
	 */
	public Integer getVrfPingStVal() {
		return vrfPingStVal;
	}

	/**
	 * VRFPING상태값
	 *
	 * @param vrfPingStVal
	 *            VRFPING상태값
	 */
	public void setVrfPingStVal(Integer vrfPingStVal) {
		this.vrfPingStVal = vrfPingStVal;
	}

	/**
	 * VRFPING상태변경일시
	 * 
	 * @return VRFPING상태변경일시
	 */
	public String getVrfPingStChgDtm() {
		return vrfPingStChgDtm;
	}

	/**
	 * VRFPING상태변경일시
	 *
	 * @param vrfPingStChgDtm
	 *            VRFPING상태변경일시
	 */
	public void setVrfPingStChgDtm(String vrfPingStChgDtm) {
		this.vrfPingStChgDtm = vrfPingStChgDtm;
	}

	/**
	 * 최종장애수신일시
	 * 
	 * @return 최종장애수신일시
	 */
	public Timestamp getLastDablRcvDtm() {
		return lastDablRcvDtm;
	}

	/**
	 * 최종장애수신일시
	 *
	 * @param lastDablRcvDtm
	 *            최종장애수신일시
	 */
	public void setLastDablRcvDtm(Timestamp lastDablRcvDtm) {
		this.lastDablRcvDtm = lastDablRcvDtm;
	}
}

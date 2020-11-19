package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.lagoon.tmn.teams.co.AdamsCfg.DATA_RGST_MTHD_CL_CD;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.13 14:22
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10210", comment = "장비구성품내역")
public class OIV10210 implements Serializable {

	public OIV10210() {
	}

	@FxColumn(name = "EQUIP_CONS_ITM_ID", size = 12, comment = "장비구성품ID")
	private String equipConsItmId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "SUP_EQUIP_CONS_ITM_ID", size = 12, nullable = true, comment = "상위장비구성품ID")
	private String supEquipConsItmId;

	@FxColumn(name = "EQUIP_CONS_ITM_CD", size = 10, comment = "장비구성품코드")
	private String equipConsItmCd;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "CONS_ITM_NUM", size = 3, nullable = true, comment = "구성품번호")
	private String consItmNum;

	@FxColumn(name = "EQUIP_CONS_ITM_NM", size = 60, nullable = true, comment = "장비구성품명")
	private String equipConsItmNm;

	@FxColumn(name = "MFACT_SER_NUM", size = 20, nullable = true, comment = "제조사일련번호")
	private String mfactSerNum;

	@FxColumn(name = "EQUIP_CONS_ITM_DESC", size = 100, nullable = true, comment = "장비구성품설명")
	private String equipConsItmDesc;

	@FxColumn(name = "FRNT_BACK_CL_CD", size = 1, comment = "전면후면구분코드")
	private String frntBackClCd;

	@FxColumn(name = "PORT_CNT", size = 5, nullable = true, comment = "포트개수")
	private Integer portCnt;

	@FxColumn(name = "POLLING_M_OBJ_YN", size = 1, comment = "POLLING감시대상여부", defValue = "'N'")
	private String pollingMObjYn = "N";

	@FxColumn(name = "HOST_NM", size = 40, nullable = true, comment = "호스트명")
	private String hostNm;

	@FxColumn(name = "MEMO", size = 2000, nullable = true, comment = "메모")
	private String memo;

	@FxColumn(name = "EQUIP_CONS_ITM_ALS_NM", size = 200, nullable = true, comment = "장비구성품별칭명")
	private String equipConsItmAlsNm;

	@FxColumn(name = "TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, nullable = true, comment = "전송망장비용량구분코드")
	private String trmsNetEquipCapaClCd;

	@FxColumn(name = "ALT_PSBL_YN", size = 1, comment = "대체가능여부", defValue = "'N'")
	private String altPsblYn = "N";

	@FxColumn(name = "BK_EQUIP_ID", size = 12, nullable = true, comment = "백업장비ID")
	private String bkEquipId;

	@FxColumn(name = "BK_EQUIP_CONS_ITM_ID", size = 12, nullable = true, comment = "백업장비구성품ID")
	private String bkEquipConsItmId;

	@FxColumn(name = "EQUIP_CONS_ITM_ST_VAL", size = 100, nullable = true, comment = "장비구성품상태값")
	private String equipConsItmStVal;

	@FxColumn(name = "EQUIP_CONS_ITM_SUB_ST_VAL", size = 100, nullable = true, comment = "장비구성품서브상태값")
	private String equipConsItmSubStVal;

	@FxColumn(name = "AUTO_MGMT_YN", size = 1, comment = "자동관리여부", defValue = "'N'")
	private String autoMgmtYn = "N";

	@FxColumn(name = "RGST_DTM", size = 14, nullable = true, comment = "등록일시")
	private String rgstDtm;

	@FxColumn(name = "WVLENG_VAL", size = 7, nullable = true, comment = "파장값")
	private Double wvlengVal;

	@FxColumn(name = "EQUIP_CONS_ITM_USG_CD", size = 3, nullable = true, comment = "장비구성품용도코드")
	private String equipConsItmUsgCd;

	@FxColumn(name = "REP_IP_ADDR", size = 23, nullable = true, comment = "대표IP주소")
	private String repIpAddr;

	@FxColumn(name = "VER_INFO", size = 150, nullable = true, comment = "버전정보")
	private String verInfo;

	@FxColumn(name = "DATA_RGST_MTHD_CL_CD", size = 2, comment = "데이터등록방식구분코드", defValue = "01")
	private String dataRgstMthdClCd;
	
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
	 * 상위장비구성품ID
	 * 
	 * @return 상위장비구성품ID
	 */
	public String getSupEquipConsItmId() {
		return supEquipConsItmId;
	}

	/**
	 * 상위장비구성품ID
	 *
	 * @param supEquipConsItmId
	 *            상위장비구성품ID
	 */
	public void setSupEquipConsItmId(String supEquipConsItmId) {
		this.supEquipConsItmId = supEquipConsItmId;
	}

	/**
	 * 장비구성품코드
	 * 
	 * @return 장비구성품코드
	 */
	public String getEquipConsItmCd() {
		return equipConsItmCd;
	}

	/**
	 * 장비구성품코드
	 *
	 * @param equipConsItmCd
	 *            장비구성품코드
	 */
	public void setEquipConsItmCd(String equipConsItmCd) {
		this.equipConsItmCd = equipConsItmCd;
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
	 * 구성품번호
	 * 
	 * @return 구성품번호
	 */
	public String getConsItmNum() {
		return consItmNum;
	}

	/**
	 * 구성품번호
	 *
	 * @param consItmNum
	 *            구성품번호
	 */
	public void setConsItmNum(String consItmNum) {
		this.consItmNum = consItmNum;
	}

	/**
	 * 장비구성품명
	 * 
	 * @return 장비구성품명
	 */
	public String getEquipConsItmNm() {
		return equipConsItmNm;
	}

	/**
	 * 장비구성품명
	 *
	 * @param equipConsItmNm
	 *            장비구성품명
	 */
	public void setEquipConsItmNm(String equipConsItmNm) {
		this.equipConsItmNm = equipConsItmNm;
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
	 * 장비구성품설명
	 * 
	 * @return 장비구성품설명
	 */
	public String getEquipConsItmDesc() {
		return equipConsItmDesc;
	}

	/**
	 * 장비구성품설명
	 *
	 * @param equipConsItmDesc
	 *            장비구성품설명
	 */
	public void setEquipConsItmDesc(String equipConsItmDesc) {
		this.equipConsItmDesc = equipConsItmDesc;
	}

	/**
	 * 전면후면구분코드
	 * 
	 * @return 전면후면구분코드
	 */
	public String getFrntBackClCd() {
		return frntBackClCd;
	}

	/**
	 * 전면후면구분코드
	 *
	 * @param frntBackClCd
	 *            전면후면구분코드
	 */
	public void setFrntBackClCd(String frntBackClCd) {
		this.frntBackClCd = frntBackClCd;
	}

	/**
	 * 포트개수
	 * 
	 * @return 포트개수
	 */
	public Integer getPortCnt() {
		return portCnt;
	}

	/**
	 * 포트개수
	 *
	 * @param portCnt
	 *            포트개수
	 */
	public void setPortCnt(Integer portCnt) {
		this.portCnt = portCnt;
	}

	/**
	 * POLLING감시대상여부
	 * 
	 * @return POLLING감시대상여부
	 */
	public String isPollingMObjYn() {
		return pollingMObjYn;
	}

	/**
	 * POLLING감시대상여부
	 *
	 * @param pollingMObjYn
	 *            POLLING감시대상여부
	 */
	public void setPollingMObjYn(String pollingMObjYn) {
		this.pollingMObjYn = pollingMObjYn;
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

	/**
	 * 메모
	 * 
	 * @return 메모
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 메모
	 *
	 * @param memo
	 *            메모
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 장비구성품별칭명
	 * 
	 * @return 장비구성품별칭명
	 */
	public String getEquipConsItmAlsNm() {
		return equipConsItmAlsNm;
	}

	/**
	 * 장비구성품별칭명
	 *
	 * @param equipConsItmAlsNm
	 *            장비구성품별칭명
	 */
	public void setEquipConsItmAlsNm(String equipConsItmAlsNm) {
		this.equipConsItmAlsNm = equipConsItmAlsNm;
	}

	/**
	 * 전송망장비용량구분코드
	 * 
	 * @return 전송망장비용량구분코드
	 */
	public String getTrmsNetEquipCapaClCd() {
		return trmsNetEquipCapaClCd;
	}

	/**
	 * 전송망장비용량구분코드
	 *
	 * @param trmsNetEquipCapaClCd
	 *            전송망장비용량구분코드
	 */
	public void setTrmsNetEquipCapaClCd(String trmsNetEquipCapaClCd) {
		this.trmsNetEquipCapaClCd = trmsNetEquipCapaClCd;
	}

	/**
	 * 대체가능여부
	 * 
	 * @return 대체가능여부
	 */
	public String isAltPsblYn() {
		return altPsblYn;
	}

	/**
	 * 대체가능여부
	 *
	 * @param altPsblYn
	 *            대체가능여부
	 */
	public void setAltPsblYn(String altPsblYn) {
		this.altPsblYn = altPsblYn;
	}

	/**
	 * 백업장비ID
	 * 
	 * @return 백업장비ID
	 */
	public String getBkEquipId() {
		return bkEquipId;
	}

	/**
	 * 백업장비ID
	 *
	 * @param bkEquipId
	 *            백업장비ID
	 */
	public void setBkEquipId(String bkEquipId) {
		this.bkEquipId = bkEquipId;
	}

	/**
	 * 백업장비구성품ID
	 * 
	 * @return 백업장비구성품ID
	 */
	public String getBkEquipConsItmId() {
		return bkEquipConsItmId;
	}

	/**
	 * 백업장비구성품ID
	 *
	 * @param bkEquipConsItmId
	 *            백업장비구성품ID
	 */
	public void setBkEquipConsItmId(String bkEquipConsItmId) {
		this.bkEquipConsItmId = bkEquipConsItmId;
	}

	/**
	 * 장비구성품상태값
	 * 
	 * @return 장비구성품상태값
	 */
	public String getEquipConsItmStVal() {
		return equipConsItmStVal;
	}

	/**
	 * 장비구성품상태값
	 *
	 * @param equipConsItmStVal
	 *            장비구성품상태값
	 */
	public void setEquipConsItmStVal(String equipConsItmStVal) {
		this.equipConsItmStVal = equipConsItmStVal;
	}

	/**
	 * 장비구성품서브상태값
	 * 
	 * @return 장비구성품서브상태값
	 */
	public String getEquipConsItmSubStVal() {
		return equipConsItmSubStVal;
	}

	/**
	 * 장비구성품서브상태값
	 *
	 * @param equipConsItmSubStVal
	 *            장비구성품서브상태값
	 */
	public void setEquipConsItmSubStVal(String equipConsItmSubStVal) {
		this.equipConsItmSubStVal = equipConsItmSubStVal;
	}

	/**
	 * 자동관리여부
	 * 
	 * @return 자동관리여부
	 */
	public String isAutoMgmtYn() {
		return autoMgmtYn;
	}

	/**
	 * 자동관리여부
	 *
	 * @param autoMgmtYn
	 *            자동관리여부
	 */
	public void setAutoMgmtYn(String autoMgmtYn) {
		this.autoMgmtYn = autoMgmtYn;
	}
	
	public String getAutoMgmtYn() {
		if (getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.USER)) {
			return "N";
		}
		return "Y";
	}

	/**
	 * 등록일시
	 * 
	 * @return 등록일시
	 */
	public String getRgstDtm() {
		return rgstDtm;
	}

	/**
	 * 등록일시
	 *
	 * @param rgstDtm
	 *            등록일시
	 */
	public void setRgstDtm(String rgstDtm) {
		this.rgstDtm = rgstDtm;
	}

	/**
	 * 파장값
	 * 
	 * @return 파장값
	 */
	public Double getWvlengVal() {
		return wvlengVal;
	}

	/**
	 * 파장값
	 *
	 * @param wvlengVal
	 *            파장값
	 */
	public void setWvlengVal(Double wvlengVal) {
		this.wvlengVal = wvlengVal;
	}

	/**
	 * 장비구성품용도코드
	 * 
	 * @return 장비구성품용도코드
	 */
	public String getEquipConsItmUsgCd() {
		return equipConsItmUsgCd;
	}

	/**
	 * 장비구성품용도코드
	 *
	 * @param equipConsItmUsgCd
	 *            장비구성품용도코드
	 */
	public void setEquipConsItmUsgCd(String equipConsItmUsgCd) {
		this.equipConsItmUsgCd = equipConsItmUsgCd;
	}

	/**
	 * 대표IP주소
	 * 
	 * @return 대표IP주소
	 */
	public String getRepIpAddr() {
		return repIpAddr;
	}

	/**
	 * 대표IP주소
	 *
	 * @param repIpAddr
	 *            대표IP주소
	 */
	public void setRepIpAddr(String repIpAddr) {
		this.repIpAddr = repIpAddr;
	}

	/**
	 * 버전정보
	 * 
	 * @return 버전정보
	 */
	public String getVerInfo() {
		return verInfo;
	}

	/**
	 * 버전정보
	 *
	 * @param verInfo
	 *            버전정보
	 */
	public void setVerInfo(String verInfo) {
		this.verInfo = verInfo;
	}
	
	public String getDataRgstMthdClCd() {
		return dataRgstMthdClCd;
	}

	public void setDataRgstMthdClCd(String dataRgstMthdClCd) {
		this.dataRgstMthdClCd = dataRgstMthdClCd;
	}
}

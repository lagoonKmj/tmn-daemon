package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 14:10
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10221", comment = "전송장비구성품수집내역")
public class OIV10221 {

	public OIV10221() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "EQUIP_CONS_ITM_NM", size = 60, comment = "장비구성품명")
	private String equipConsItmNm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_CONS_ITM_CD", size = 10, nullable = true, comment = "장비구성품코드")
	private String equipConsItmCd;

	@FxColumn(name = "CLCT_DTM", size = 14, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "EQUIP_CONS_ITM_ID", size = 12, nullable = true, comment = "장비구성품ID")
	private String equipConsItmId;

	@FxColumn(name = "SCARD_NM", size = 50, comment = "SLOT카드명")
	private String scardNm;

	@FxColumn(name = "EQUIP_CONS_ITM_TYP_NM", size = 50, comment = "장비구성품종류명")
	private String equipConsItmTypNm;

	@FxColumn(name = "EQUIP_CONS_ITM_ACT_ST_NM", size = 10, comment = "장비구성품활성상태명", defValue = "'ACT'")
	private String equipConsItmActStNm = "ACT";

	@FxColumn(name = "CPU_USE_RT", size = 5, nullable = true, comment = "CPU사용률")
	private Double cpuUseRt;

	@FxColumn(name = "MMR_USE_RT", size = 10, nullable = true, comment = "메모리사용률")
	private Double mmrUseRt;

	@FxColumn(name = "TMPR_VAL", size = 15, nullable = true, comment = "온도값")
	private Double tmprVal;

	@FxColumn(name = "VER_INFO", size = 150, nullable = true, comment = "버전정보")
	private String verInfo;

	@FxColumn(name = "VER_APLY_DTM", size = 0, nullable = true, comment = "버전적용일시")
	private Timestamp verAplyDtm;

	@FxColumn(name = "ACTVN_IDX_NUM", size = 5, nullable = true, comment = "활성화인덱스번호")
	private String actvnIdxNum;

	@FxColumn(name = "FST_ETC_VER_INFO", size = 100, nullable = true, comment = "1차기타버전정보")
	private String fstEtcVerInfo;

	@FxColumn(name = "SCND_ETC_VER_INFO", size = 100, nullable = true, comment = "2차기타버전정보")
	private String scndEtcVerInfo;

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
	 * SLOT카드명
	 * 
	 * @return SLOT카드명
	 */
	public String getScardNm() {
		return scardNm;
	}

	/**
	 * SLOT카드명
	 *
	 * @param scardNm
	 *            SLOT카드명
	 */
	public void setScardNm(String scardNm) {
		this.scardNm = scardNm;
	}

	/**
	 * 장비구성품종류명
	 * 
	 * @return 장비구성품종류명
	 */
	public String getEquipConsItmTypNm() {
		return equipConsItmTypNm;
	}

	/**
	 * 장비구성품종류명
	 *
	 * @param equipConsItmTypNm
	 *            장비구성품종류명
	 */
	public void setEquipConsItmTypNm(String equipConsItmTypNm) {
		this.equipConsItmTypNm = equipConsItmTypNm;
	}

	/**
	 * 장비구성품활성상태명
	 * 
	 * @return 장비구성품활성상태명
	 */
	public String getEquipConsItmActStNm() {
		return equipConsItmActStNm;
	}

	/**
	 * 장비구성품활성상태명
	 *
	 * @param equipConsItmActStNm
	 *            장비구성품활성상태명
	 */
	public void setEquipConsItmActStNm(String equipConsItmActStNm) {
		this.equipConsItmActStNm = equipConsItmActStNm;
	}

	/**
	 * CPU사용률
	 * 
	 * @return CPU사용률
	 */
	public Double getCpuUseRt() {
		return cpuUseRt;
	}

	/**
	 * CPU사용률
	 *
	 * @param cpuUseRt
	 *            CPU사용률
	 */
	public void setCpuUseRt(Double cpuUseRt) {
		this.cpuUseRt = cpuUseRt;
	}

	/**
	 * 메모리사용률
	 * 
	 * @return 메모리사용률
	 */
	public Double getMmrUseRt() {
		return mmrUseRt;
	}

	/**
	 * 메모리사용률
	 *
	 * @param mmrUseRt
	 *            메모리사용률
	 */
	public void setMmrUseRt(Double mmrUseRt) {
		this.mmrUseRt = mmrUseRt;
	}

	/**
	 * 온도값
	 * 
	 * @return 온도값
	 */
	public Double getTmprVal() {
		return tmprVal;
	}

	/**
	 * 온도값
	 *
	 * @param tmprVal
	 *            온도값
	 */
	public void setTmprVal(Double tmprVal) {
		this.tmprVal = tmprVal;
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

	/**
	 * 버전적용일시
	 * 
	 * @return 버전적용일시
	 */
	public Timestamp getVerAplyDtm() {
		return verAplyDtm;
	}

	/**
	 * 버전적용일시
	 *
	 * @param verAplyDtm
	 *            버전적용일시
	 */
	public void setVerAplyDtm(Timestamp verAplyDtm) {
		this.verAplyDtm = verAplyDtm;
	}

	/**
	 * 활성화인덱스번호
	 * 
	 * @return 활성화인덱스번호
	 */
	public String getActvnIdxNum() {
		return actvnIdxNum;
	}

	/**
	 * 활성화인덱스번호
	 *
	 * @param actvnIdxNum
	 *            활성화인덱스번호
	 */
	public void setActvnIdxNum(String actvnIdxNum) {
		this.actvnIdxNum = actvnIdxNum;
	}

	/**
	 * 1차기타버전정보
	 * 
	 * @return 1차기타버전정보
	 */
	public String getFstEtcVerInfo() {
		return fstEtcVerInfo;
	}

	/**
	 * 1차기타버전정보
	 *
	 * @param fstEtcVerInfo
	 *            1차기타버전정보
	 */
	public void setFstEtcVerInfo(String fstEtcVerInfo) {
		this.fstEtcVerInfo = fstEtcVerInfo;
	}

	/**
	 * 2차기타버전정보
	 * 
	 * @return 2차기타버전정보
	 */
	public String getScndEtcVerInfo() {
		return scndEtcVerInfo;
	}

	/**
	 * 2차기타버전정보
	 *
	 * @param scndEtcVerInfo
	 *            2차기타버전정보
	 */
	public void setScndEtcVerInfo(String scndEtcVerInfo) {
		this.scndEtcVerInfo = scndEtcVerInfo;
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

package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.25 16:56
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10209", comment = "장비구성품기본")
public class OIV10209 {

	public OIV10209() {
	}

	@FxColumn(name = "EQUIP_CONS_ITM_CD", size = 10, comment = "장비구성품코드")
	private String equipConsItmCd;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_MFACT_CD", size = 3, comment = "장비제조사코드")
	private String equipMfactCd;

	@FxColumn(name = "CONS_ITM_MDL_NM", size = 50, nullable = true, comment = "구성품모델명")
	private String consItmMdlNm;

	@FxColumn(name = "CONS_ITM_TYP_CD", size = 2, comment = "구성품유형코드")
	private String consItmTypCd;

	@FxColumn(name = "EQUIP_CONS_ITM_TYP_CD", size = 4, comment = "장비구성품종류코드")
	private String equipConsItmTypCd;

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "PORT_CNT", size = 5, nullable = true, comment = "포트개수")
	private Integer portCnt;

	@FxColumn(name = "PORT_CRE_YN", size = 1, comment = "포트생성여부", defValue = "'N'")
	private String portCreYn = "N";

	@FxColumn(name = "PORT_STA_NUM", size = 5, nullable = true, comment = "포트시작번호")
	private String portStaNum;

	@FxColumn(name = "CARD_TRMS_NET_CAPA_CL_CD", size = 4, nullable = true, comment = "카드전송망용량구분코드")
	private String cardTrmsNetCapaClCd;

	@FxColumn(name = "PORT_TRMS_NET_CAPA_CL_CD", size = 4, nullable = true, comment = "포트전송망용량구분코드")
	private String portTrmsNetCapaClCd;

	@FxColumn(name = "NMS_USE_YN", size = 1, nullable = true, comment = "NMS사용여부")
	private String nmsUseYn;

	@FxColumn(name = "AUTO_MGMT_YN", size = 1, nullable = true, comment = "자동관리여부")
	private String autoMgmtYn;

	@FxColumn(name = "STRD_VER_INFO", size = 100, nullable = true, comment = "기준버전정보")
	private String strdVerInfo;

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
	 * 장비제조사코드
	 * 
	 * @return 장비제조사코드
	 */
	public String getEquipMfactCd() {
		return equipMfactCd;
	}

	/**
	 * 장비제조사코드
	 *
	 * @param equipMfactCd
	 *            장비제조사코드
	 */
	public void setEquipMfactCd(String equipMfactCd) {
		this.equipMfactCd = equipMfactCd;
	}

	/**
	 * 구성품모델명
	 * 
	 * @return 구성품모델명
	 */
	public String getConsItmMdlNm() {
		return consItmMdlNm;
	}

	/**
	 * 구성품모델명
	 *
	 * @param consItmMdlNm
	 *            구성품모델명
	 */
	public void setConsItmMdlNm(String consItmMdlNm) {
		this.consItmMdlNm = consItmMdlNm;
	}

	/**
	 * 구성품유형코드
	 * 
	 * @return 구성품유형코드
	 */
	public String getConsItmTypCd() {
		return consItmTypCd;
	}

	/**
	 * 구성품유형코드
	 *
	 * @param consItmTypCd
	 *            구성품유형코드
	 */
	public void setConsItmTypCd(String consItmTypCd) {
		this.consItmTypCd = consItmTypCd;
	}

	/**
	 * 장비구성품종류코드
	 * 
	 * @return 장비구성품종류코드
	 */
	public String getEquipConsItmTypCd() {
		return equipConsItmTypCd;
	}

	/**
	 * 장비구성품종류코드
	 *
	 * @param equipConsItmTypCd
	 *            장비구성품종류코드
	 */
	public void setEquipConsItmTypCd(String equipConsItmTypCd) {
		this.equipConsItmTypCd = equipConsItmTypCd;
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
	 * 포트생성여부
	 * 
	 * @return 포트생성여부
	 */
	public String getPortCreYn() {
		return portCreYn;
	}

	/**
	 * 포트생성여부
	 *
	 * @param portCreYn
	 *            포트생성여부
	 */
	public void setPortCreYn(String portCreYn) {
		this.portCreYn = portCreYn;
	}

	/**
	 * 포트시작번호
	 * 
	 * @return 포트시작번호
	 */
	public String getPortStaNum() {
		return portStaNum;
	}

	/**
	 * 포트시작번호
	 *
	 * @param portStaNum
	 *            포트시작번호
	 */
	public void setPortStaNum(String portStaNum) {
		this.portStaNum = portStaNum;
	}

	/**
	 * 카드전송망용량구분코드
	 * 
	 * @return 카드전송망용량구분코드
	 */
	public String getCardTrmsNetCapaClCd() {
		return cardTrmsNetCapaClCd;
	}

	/**
	 * 카드전송망용량구분코드
	 *
	 * @param cardTrmsNetCapaClCd
	 *            카드전송망용량구분코드
	 */
	public void setCardTrmsNetCapaClCd(String cardTrmsNetCapaClCd) {
		this.cardTrmsNetCapaClCd = cardTrmsNetCapaClCd;
	}

	/**
	 * 포트전송망용량구분코드
	 * 
	 * @return 포트전송망용량구분코드
	 */
	public String getPortTrmsNetCapaClCd() {
		return portTrmsNetCapaClCd;
	}

	/**
	 * 포트전송망용량구분코드
	 *
	 * @param portTrmsNetCapaClCd
	 *            포트전송망용량구분코드
	 */
	public void setPortTrmsNetCapaClCd(String portTrmsNetCapaClCd) {
		this.portTrmsNetCapaClCd = portTrmsNetCapaClCd;
	}

	/**
	 * NMS사용여부
	 * 
	 * @return NMS사용여부
	 */
	public String isNmsUseYn() {
		return nmsUseYn;
	}

	/**
	 * NMS사용여부
	 *
	 * @param nmsUseYn
	 *            NMS사용여부
	 */
	public void setNmsUseYn(String nmsUseYn) {
		this.nmsUseYn = nmsUseYn;
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

	/**
	 * 기준버전정보
	 * 
	 * @return 기준버전정보
	 */
	public String getStrdVerInfo() {
		return strdVerInfo;
	}

	/**
	 * 기준버전정보
	 *
	 * @param strdVerInfo
	 *            기준버전정보
	 */
	public void setStrdVerInfo(String strdVerInfo) {
		this.strdVerInfo = strdVerInfo;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.06.18 13:26
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10100", comment = "장비기본")
public class OIV10100 {

	public OIV10100() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "SYSDATE")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_MGMT_NUM", size = 8, nullable = true, comment = "장비관리번호")
	private String equipMgmtNum;

	@FxColumn(name = "TPO_CD", size = 6, comment = "국사코드")
	private String tpoCd;

	@FxColumn(name = "MGMT_TPO_CD", size = 6, comment = "관리국사코드")
	private String mgmtTpoCd;

	@FxColumn(name = "EXCHG_TPO_CD", size = 6, comment = "교환국사코드")
	private String exchgTpoCd;

	@FxColumn(name = "JURIS_TPO_CD", size = 6, comment = "관할국사코드")
	private String jurisTpoCd;

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "EQUIP_SYS_SET_DT", size = 8, nullable = true, comment = "장비시스템설치일자")
	private String equipSysSetDt;

	@FxColumn(name = "EQUIP_USG_CD", size = 4, comment = "장비용도코드")
	private String equipUsgCd;

	@FxColumn(name = "EQUIP_TYP_CL_CD", size = 5, comment = "장비유형구분코드")
	private String equipTypClCd;

	@FxColumn(name = "SW_VER_INFO", size = 50, nullable = true, comment = "SW버전정보")
	private String swVerInfo;

	@FxColumn(name = "EQUIP_SYS_NM", size = 300, nullable = true, comment = "장비시스템명")
	private String equipSysNm;

	@FxColumn(name = "EQUIP_TID_VAL", size = 50, nullable = true, comment = "장비TID값")
	private String equipTidVal;

	@FxColumn(name = "EQUIP_NM", size = 300, nullable = true, comment = "장비명")
	private String equipNm;

	@FxColumn(name = "EQUIP_OWN_YN", size = 1, comment = "장비소유여부", defValue = "'N'")
	private boolean equipOwnYn = false;

	@FxColumn(name = "EQUIP_SET_LOC_DESC", size = 500, nullable = true, comment = "장비설치위치설명")
	private String equipSetLocDesc;

	@FxColumn(name = "EQUIP_IP_ADDR", size = 23, nullable = true, comment = "장비IP주소")
	private String equipIpAddr;

	@FxColumn(name = "LINK_EQUIP_ID", size = 12, nullable = true, comment = "연결장비ID")
	private String linkEquipId;

	@FxColumn(name = "MEMO", size = 2000, nullable = true, comment = "메모")
	private String memo;

	@FxColumn(name = "DEL_YN", size = 1, comment = "삭제여부", defValue = "'N'")
	private boolean delYn = false;

	@FxColumn(name = "POLLING_M_OBJ_YN", size = 1, comment = "POLLING감시대상여부", defValue = "'N'")
	private boolean pollingMObjYn = false;

	@FxColumn(name = "PING_M_OBJ_YN", size = 1, comment = "PING감시대상여부", defValue = "'N'")
	private boolean pingMObjYn = false;

	@FxColumn(name = "M_OBJ_YN", size = 1, comment = "감시대상여부", defValue = "'N'")
	private boolean mObjYn = false;

	@FxColumn(name = "NMS_EQUIP_CD", size = 12, comment = "NMS장비코드")
	private String nmsEquipCd;

	@FxColumn(name = "CHRGR_NM", size = 20, nullable = true, comment = "담당자명")
	private String chrgrNm;

	@FxColumn(name = "CHRGR_CNTC_NUM", size = 12, nullable = true, comment = "담당자연락전화번호")
	private String chrgrCntcNum;

	@FxColumn(name = "SUB_CHRGR_NM", size = 20, nullable = true, comment = "부담당자명")
	private String subChrgrNm;

	@FxColumn(name = "SUB_CHRGR_CNTC_NUM", size = 12, nullable = true, comment = "부담당자연락전화번호")
	private String subChrgrCntcNum;

	@FxColumn(name = "DHCP_EQUIP_ID", size = 12, nullable = true, comment = "DHCP장비ID")
	private String dhcpEquipId;

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
	 * 장비관리번호
	 * 
	 * @return 장비관리번호
	 */
	public String getEquipMgmtNum() {
		return equipMgmtNum;
	}

	/**
	 * 장비관리번호
	 *
	 * @param equipMgmtNum
	 *            장비관리번호
	 */
	public void setEquipMgmtNum(String equipMgmtNum) {
		this.equipMgmtNum = equipMgmtNum;
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
	 * 관리국사코드
	 * 
	 * @return 관리국사코드
	 */
	public String getMgmtTpoCd() {
		return mgmtTpoCd;
	}

	/**
	 * 관리국사코드
	 *
	 * @param mgmtTpoCd
	 *            관리국사코드
	 */
	public void setMgmtTpoCd(String mgmtTpoCd) {
		this.mgmtTpoCd = mgmtTpoCd;
	}

	/**
	 * 교환국사코드
	 * 
	 * @return 교환국사코드
	 */
	public String getExchgTpoCd() {
		return exchgTpoCd;
	}

	/**
	 * 교환국사코드
	 *
	 * @param exchgTpoCd
	 *            교환국사코드
	 */
	public void setExchgTpoCd(String exchgTpoCd) {
		this.exchgTpoCd = exchgTpoCd;
	}

	/**
	 * 관할국사코드
	 * 
	 * @return 관할국사코드
	 */
	public String getJurisTpoCd() {
		return jurisTpoCd;
	}

	/**
	 * 관할국사코드
	 *
	 * @param jurisTpoCd
	 *            관할국사코드
	 */
	public void setJurisTpoCd(String jurisTpoCd) {
		this.jurisTpoCd = jurisTpoCd;
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
	 * 장비시스템설치일자
	 * 
	 * @return 장비시스템설치일자
	 */
	public String getEquipSysSetDt() {
		return equipSysSetDt;
	}

	/**
	 * 장비시스템설치일자
	 *
	 * @param equipSysSetDt
	 *            장비시스템설치일자
	 */
	public void setEquipSysSetDt(String equipSysSetDt) {
		this.equipSysSetDt = equipSysSetDt;
	}

	/**
	 * 장비용도코드
	 * 
	 * @return 장비용도코드
	 */
	public String getEquipUsgCd() {
		return equipUsgCd;
	}

	/**
	 * 장비용도코드
	 *
	 * @param equipUsgCd
	 *            장비용도코드
	 */
	public void setEquipUsgCd(String equipUsgCd) {
		this.equipUsgCd = equipUsgCd;
	}

	/**
	 * 장비유형구분코드
	 * 
	 * @return 장비유형구분코드
	 */
	public String getEquipTypClCd() {
		return equipTypClCd;
	}

	/**
	 * 장비유형구분코드
	 *
	 * @param equipTypClCd
	 *            장비유형구분코드
	 */
	public void setEquipTypClCd(String equipTypClCd) {
		this.equipTypClCd = equipTypClCd;
	}

	/**
	 * SW버전정보
	 * 
	 * @return SW버전정보
	 */
	public String getSwVerInfo() {
		return swVerInfo;
	}

	/**
	 * SW버전정보
	 *
	 * @param swVerInfo
	 *            SW버전정보
	 */
	public void setSwVerInfo(String swVerInfo) {
		this.swVerInfo = swVerInfo;
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
	 * 장비소유여부
	 * 
	 * @return 장비소유여부
	 */
	public boolean isEquipOwnYn() {
		return equipOwnYn;
	}

	/**
	 * 장비소유여부
	 *
	 * @param equipOwnYn
	 *            장비소유여부
	 */
	public void setEquipOwnYn(boolean equipOwnYn) {
		this.equipOwnYn = equipOwnYn;
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
	 * 연결장비ID
	 * 
	 * @return 연결장비ID
	 */
	public String getLinkEquipId() {
		return linkEquipId;
	}

	/**
	 * 연결장비ID
	 *
	 * @param linkEquipId
	 *            연결장비ID
	 */
	public void setLinkEquipId(String linkEquipId) {
		this.linkEquipId = linkEquipId;
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
	 * 삭제여부
	 * 
	 * @return 삭제여부
	 */
	public boolean isDelYn() {
		return delYn;
	}

	/**
	 * 삭제여부
	 *
	 * @param delYn
	 *            삭제여부
	 */
	public void setDelYn(boolean delYn) {
		this.delYn = delYn;
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
	 * 감시대상여부
	 * 
	 * @return 감시대상여부
	 */
	public boolean isMObjYn() {
		return mObjYn;
	}

	/**
	 * 감시대상여부
	 *
	 * @param mObjYn
	 *            감시대상여부
	 */
	public void setMObjYn(boolean mObjYn) {
		this.mObjYn = mObjYn;
	}

	/**
	 * NMS장비코드
	 * 
	 * @return NMS장비코드
	 */
	public String getNmsEquipCd() {
		return nmsEquipCd;
	}

	/**
	 * NMS장비코드
	 *
	 * @param nmsEquipCd
	 *            NMS장비코드
	 */
	public void setNmsEquipCd(String nmsEquipCd) {
		this.nmsEquipCd = nmsEquipCd;
	}

	/**
	 * 담당자명
	 * 
	 * @return 담당자명
	 */
	public String getChrgrNm() {
		return chrgrNm;
	}

	/**
	 * 담당자명
	 *
	 * @param chrgrNm
	 *            담당자명
	 */
	public void setChrgrNm(String chrgrNm) {
		this.chrgrNm = chrgrNm;
	}

	/**
	 * 담당자연락전화번호
	 * 
	 * @return 담당자연락전화번호
	 */
	public String getChrgrCntcNum() {
		return chrgrCntcNum;
	}

	/**
	 * 담당자연락전화번호
	 *
	 * @param chrgrCntcNum
	 *            담당자연락전화번호
	 */
	public void setChrgrCntcNum(String chrgrCntcNum) {
		this.chrgrCntcNum = chrgrCntcNum;
	}

	/**
	 * 부담당자명
	 * 
	 * @return 부담당자명
	 */
	public String getSubChrgrNm() {
		return subChrgrNm;
	}

	/**
	 * 부담당자명
	 *
	 * @param subChrgrNm
	 *            부담당자명
	 */
	public void setSubChrgrNm(String subChrgrNm) {
		this.subChrgrNm = subChrgrNm;
	}

	/**
	 * 부담당자연락전화번호
	 * 
	 * @return 부담당자연락전화번호
	 */
	public String getSubChrgrCntcNum() {
		return subChrgrCntcNum;
	}

	/**
	 * 부담당자연락전화번호
	 *
	 * @param subChrgrCntcNum
	 *            부담당자연락전화번호
	 */
	public void setSubChrgrCntcNum(String subChrgrCntcNum) {
		this.subChrgrCntcNum = subChrgrCntcNum;
	}

	/**
	 * DHCP장비ID
	 * 
	 * @return DHCP장비ID
	 */
	public String getDhcpEquipId() {
		return dhcpEquipId;
	}

	/**
	 * DHCP장비ID
	 *
	 * @param dhcpEquipId
	 *            DHCP장비ID
	 */
	public void setDhcpEquipId(String dhcpEquipId) {
		this.dhcpEquipId = dhcpEquipId;
	}
}

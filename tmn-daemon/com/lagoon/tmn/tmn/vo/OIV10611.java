package com.lagoon.tmn.tmn.vo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.11.18 16:00
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10611", comment = "회선기본")
public class OIV10611 {

	public OIV10611() {
	}

	@FxColumn(name = "LINE_NUM", size = 12, comment = "회선번호")
	private String lineNum;

	@FxColumn(name = "CUST_NUM", size = 10, nullable = true, comment = "고객번호")
	private Long custNum;

	@FxColumn(name = "SVC_NUM", size = 20, nullable = true, comment = "서비스번호")
	private String svcNum;

	@FxColumn(name = "LINE_NM", size = 100, nullable = true, comment = "회선명")
	private String lineNm;

	@FxColumn(name = "RENT_BBNT_LINE_NUM", size = 32, nullable = true, comment = "임차기간망회선번호")
	private String rentBbntLineNum;

	@FxColumn(name = "RENT_LINE_BIZR_CD", size = 4, comment = "임대회선사업자코드")
	private String rentLineBizrCd;

	@FxColumn(name = "LINK_LINE_BIZR_CD", size = 4, comment = "연결회선사업자코드")
	private String linkLineBizrCd;

	@FxColumn(name = "LINE_CNTRCT_PRD_TYP_CD", size = 2, comment = "회선계약기간유형코드")
	private String lineCntrctPrdTypCd;

	@FxColumn(name = "TRMS_NET_MGMT_OWNR_CD", size = 2, comment = "전송망관리주체코드")
	private String trmsNetMgmtOwnrCd;

	@FxColumn(name = "TRMS_NET_LINE_USG_CD", size = 2, comment = "전송망회선용도코드")
	private String trmsNetLineUsgCd;

	@FxColumn(name = "TRMS_NET_LINE_SVC_CD", size = 4, comment = "전송망회선서비스코드")
	private String trmsNetLineSvcCd;

	@FxColumn(name = "MAX_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, comment = "최대전송망장비용량구분코드")
	private String maxTrmsNetEquipCapaClCd;

	@FxColumn(name = "MIN_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, comment = "최소전송망장비용량구분코드")
	private String minTrmsNetEquipCapaClCd;

	@FxColumn(name = "SUP_TPO_CD", size = 6, nullable = true, comment = "상위국사코드")
	private String supTpoCd;

	@FxColumn(name = "SUP_TPO_SYS_INFO", size = 40, nullable = true, comment = "상위국사시스템정보")
	private String supTpoSysInfo;

	@FxColumn(name = "SUB_TPO_CD", size = 6, nullable = true, comment = "하위국사코드")
	private String subTpoCd;

	@FxColumn(name = "SUB_TPO_SYS_INFO", size = 40, nullable = true, comment = "하위국사시스템정보")
	private String subTpoSysInfo;

	@FxColumn(name = "SVC_DTM", size = 14, nullable = true, comment = "개통일시")
	private String svcDtm;

	@FxColumn(name = "TERM_DTM", size = 14, nullable = true, comment = "해지일시")
	private String termDtm;

	@FxColumn(name = "APPL_NUM", size = 20, nullable = true, comment = "청약번호")
	private String applNum;

	@FxColumn(name = "APPL_DTM", size = 14, nullable = true, comment = "청약일시")
	private String applDtm;

	@FxColumn(name = "TRMS_NET_BTW_EQUIP_DIST_TYP_CD", size = 4, comment = "전송망장비간거리유형코드")
	private String trmsNetBtwEquipDistTypCd;

	@FxColumn(name = "TRMS_NET_RNG_ALL_DIST_TYP_CD", size = 3, comment = "전송망구간전체거리유형코드")
	private String trmsNetRngAllDistTypCd;

	@FxColumn(name = "FEE_INFO", size = 100, nullable = true, comment = "요금정보")
	private String feeInfo;

	@FxColumn(name = "TRMS_NET_FEE_CL_CD", size = 2, comment = "전송망요금구분코드")
	private String trmsNetFeeClCd;

	@FxColumn(name = "FEE_PAY_TPO_CD", size = 6, nullable = true, comment = "요금납부국사코드")
	private String feePayTpoCd;

	@FxColumn(name = "CNTR_ORG_ID", size = 10, nullable = true, comment = "센터조직ID")
	private String cntrOrgId;

	@FxColumn(name = "TRMS_NET_BILL_ST_CD", size = 2, comment = "전송망과금상태코드")
	private String trmsNetBillStCd;

	@FxColumn(name = "LINE_ST_CD", size = 2, comment = "회선상태코드")
	private String lineStCd;

	@FxColumn(name = "DABL_MGMT_YN", size = 1, comment = "장애관리여부", defValue = "'N'")
	private String dablMgmtYn = "N";

	@FxColumn(name = "TRMS_NET_MGMT_GR_CD", size = 5, comment = "전송망관리등급코드")
	private String trmsNetMgmtGrCd;

	@FxColumn(name = "TRMS_NET_SKT_AREA_CL_CD", size = 3, comment = "전송망SKT지역구분코드")
	private String trmsNetSktAreaClCd;

	@FxColumn(name = "TRMS_NET_LNE_ENCDG_TYP_CD", size = 2, comment = "전송망선로인코딩유형코드")
	private String trmsNetLneEncdgTypCd;

	@FxColumn(name = "NMS_MNTR_YN", size = 1, comment = "NMS감시여부", defValue = "'N'")
	private String nmsMntrYn = "N";

	@FxColumn(name = "LINE_SUP_STN_NUM", size = 10, nullable = true, comment = "회선상위국번호")
	private String lineSupStnNum;

	@FxColumn(name = "LINE_SUB_STN_NUM", size = 10, nullable = true, comment = "회선하위국번호")
	private String lineSubStnNum;

	@FxColumn(name = "GRP_CO_SYS_LINE_NUM", size = 20, nullable = true, comment = "그룹사시스템회선번호")
	private String grpCoSysLineNum;

	@FxColumn(name = "LNKG_SYS_SPEED_CD_VAL", size = 100, nullable = true, comment = "연동시스템속도코드값")
	private String lnkgSysSpeedCdVal;

	@FxColumn(name = "GRP_CO_SYS_LINE_PATH_INFO", size = 1000, nullable = true, comment = "그룹사시스템회선경로정보")
	private String grpCoSysLinePathInfo;

	@FxColumn(name = "TRMS_NET_DIRN_CD", size = 1, comment = "전송망방향코드")
	private String trmsNetDirnCd;

	@FxColumn(name = "SUB_SUP_STN_NUM", size = 10, nullable = true, comment = "서브상위국번호")
	private String subSupStnNum;

	@FxColumn(name = "SUB_SUB_STN_NUM", size = 10, nullable = true, comment = "서브하위국번호")
	private String subSubStnNum;

	@FxColumn(name = "MEMO_CTT1", size = 400, nullable = true, comment = "메모내용1")
	private String memoCtt1;

	@FxColumn(name = "MEMO_CTT2", size = 400, nullable = true, comment = "메모내용2")
	private String memoCtt2;

	@FxColumn(name = "MEMO_CTT3", size = 400, nullable = true, comment = "메모내용3")
	private String memoCtt3;

	@FxColumn(name = "BAS_CNTR_NUM", size = 10, nullable = true, comment = "기지국번호")
	private String basCntrNum;

	@FxColumn(name = "CO_LINE_VRF_YN", size = 1, nullable = true, comment = "기업회선검증여부")
	private String coLineVrfYn;

	@FxColumn(name = "QDF_MSC_NM", size = 100, nullable = true, comment = "QDF교환기명")
	private String qdfMscNm;

	@FxColumn(name = "ESS_NM", size = 30, nullable = true, comment = "ESS명")
	private String essNm;

	@FxColumn(name = "SUP_CO_LINE_LLCF_ST_CD", size = 2, nullable = true, comment = "상위기업회선LLCF상태코드")
	private String supCoLineLlcfStCd;

	@FxColumn(name = "SUP_CO_LINE_NEGO_STRD_CD", size = 4, nullable = true, comment = "상위기업회선협상기준코드")
	private String supCoLineNegoStrdCd;

	@FxColumn(name = "SUB_CO_LINE_LLCF_ST_CD", size = 2, nullable = true, comment = "하위기업회선LLCF상태코드")
	private String subCoLineLlcfStCd;

	@FxColumn(name = "SUB_CO_LINE_NEGO_STRD_CD", size = 4, nullable = true, comment = "하위기업회선협상기준코드")
	private String subCoLineNegoStrdCd;

	@FxColumn(name = "CUST_NM", size = 40, nullable = true, comment = "고객명")
	private String custNm;

	@FxColumn(name = "RGSTR_INFO", size = 20, nullable = true, comment = "등록자정보")
	private String rgstrInfo;

	@FxColumn(name = "RGST_DTM", size = 14, nullable = true, comment = "등록일시")
	private String rgstDtm;

	@FxColumn(name = "CHGR_INFO", size = 20, nullable = true, comment = "변경자정보")
	private String chgrInfo;

	@FxColumn(name = "CHG_DTM", size = 14, nullable = true, comment = "변경일시")
	private String chgDtm;

	@FxColumn(name = "SKT_LINE_MGMT_NUM", size = 32, nullable = true, comment = "SKT회선관리번호")
	private String sktLineMgmtNum;

	@FxColumn(name = "SALE_CHRGR_ID", size = 100, nullable = true, comment = "영업담당자ID")
	private String saleChrgrId;

	@FxColumn(name = "TANGO_SVC_LINE_NUM", size = 13, nullable = true, comment = "TANGO망번호")
	private String tangoSvcLineNum;


	public String getTangoSvcLineNum() {
		return tangoSvcLineNum;
	}

	public void setTangoSvcLineNum(String tangoSvcLineNum) {
		this.tangoSvcLineNum = tangoSvcLineNum;
	}
	/**
	 * 회선번호
	 *
	 * @return 회선번호
	 */
	public String getLineNum() {
		return lineNum;
	}

	/**
	 * 회선번호
	 *
	 * @param lineNum
	 *            회선번호
	 */
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	/**
	 * 고객번호
	 *
	 * @return 고객번호
	 */
	public Long getCustNum() {
		return custNum;
	}

	/**
	 * 고객번호
	 *
	 * @param custNum
	 *            고객번호
	 */
	public void setCustNum(Long custNum) {
		this.custNum = custNum;
	}

	/**
	 * 서비스번호
	 *
	 * @return 서비스번호
	 */
	public String getSvcNum() {
		return svcNum;
	}

	/**
	 * 서비스번호
	 *
	 * @param svcNum
	 *            서비스번호
	 */
	public void setSvcNum(String svcNum) {
		this.svcNum = svcNum;
	}

	/**
	 * 회선명
	 *
	 * @return 회선명
	 */
	public String getLineNm() {
		return lineNm;
	}

	/**
	 * 회선명
	 *
	 * @param lineNm
	 *            회선명
	 */
	public void setLineNm(String lineNm) {
		this.lineNm = lineNm;
	}

	/**
	 * 임차기간망회선번호
	 *
	 * @return 임차기간망회선번호
	 */
	public String getRentBbntLineNum() {
		return rentBbntLineNum;
	}

	/**
	 * 임차기간망회선번호
	 *
	 * @param rentBbntLineNum
	 *            임차기간망회선번호
	 */
	public void setRentBbntLineNum(String rentBbntLineNum) {
		this.rentBbntLineNum = rentBbntLineNum;
	}

	/**
	 * 임대회선사업자코드
	 *
	 * @return 임대회선사업자코드
	 */
	public String getRentLineBizrCd() {
		return rentLineBizrCd;
	}

	/**
	 * 임대회선사업자코드
	 *
	 * @param rentLineBizrCd
	 *            임대회선사업자코드
	 */
	public void setRentLineBizrCd(String rentLineBizrCd) {
		this.rentLineBizrCd = rentLineBizrCd;
	}

	/**
	 * 연결회선사업자코드
	 *
	 * @return 연결회선사업자코드
	 */
	public String getLinkLineBizrCd() {
		return linkLineBizrCd;
	}

	/**
	 * 연결회선사업자코드
	 *
	 * @param linkLineBizrCd
	 *            연결회선사업자코드
	 */
	public void setLinkLineBizrCd(String linkLineBizrCd) {
		this.linkLineBizrCd = linkLineBizrCd;
	}

	/**
	 * 회선계약기간유형코드
	 *
	 * @return 회선계약기간유형코드
	 */
	public String getLineCntrctPrdTypCd() {
		return lineCntrctPrdTypCd;
	}

	/**
	 * 회선계약기간유형코드
	 *
	 * @param lineCntrctPrdTypCd
	 *            회선계약기간유형코드
	 */
	public void setLineCntrctPrdTypCd(String lineCntrctPrdTypCd) {
		this.lineCntrctPrdTypCd = lineCntrctPrdTypCd;
	}

	/**
	 * 전송망관리주체코드
	 *
	 * @return 전송망관리주체코드
	 */
	public String getTrmsNetMgmtOwnrCd() {
		return trmsNetMgmtOwnrCd;
	}

	/**
	 * 전송망관리주체코드
	 *
	 * @param trmsNetMgmtOwnrCd
	 *            전송망관리주체코드
	 */
	public void setTrmsNetMgmtOwnrCd(String trmsNetMgmtOwnrCd) {
		this.trmsNetMgmtOwnrCd = trmsNetMgmtOwnrCd;
	}

	/**
	 * 전송망회선용도코드
	 *
	 * @return 전송망회선용도코드
	 */
	public String getTrmsNetLineUsgCd() {
		return trmsNetLineUsgCd;
	}

	/**
	 * 전송망회선용도코드
	 *
	 * @param trmsNetLineUsgCd
	 *            전송망회선용도코드
	 */
	public void setTrmsNetLineUsgCd(String trmsNetLineUsgCd) {
		this.trmsNetLineUsgCd = trmsNetLineUsgCd;
	}

	/**
	 * 전송망회선서비스코드
	 *
	 * @return 전송망회선서비스코드
	 */
	public String getTrmsNetLineSvcCd() {
		return trmsNetLineSvcCd;
	}

	/**
	 * 전송망회선서비스코드
	 *
	 * @param trmsNetLineSvcCd
	 *            전송망회선서비스코드
	 */
	public void setTrmsNetLineSvcCd(String trmsNetLineSvcCd) {
		this.trmsNetLineSvcCd = trmsNetLineSvcCd;
	}

	/**
	 * 최대전송망장비용량구분코드
	 *
	 * @return 최대전송망장비용량구분코드
	 */
	public String getMaxTrmsNetEquipCapaClCd() {
		return maxTrmsNetEquipCapaClCd;
	}

	/**
	 * 최대전송망장비용량구분코드
	 *
	 * @param maxTrmsNetEquipCapaClCd
	 *            최대전송망장비용량구분코드
	 */
	public void setMaxTrmsNetEquipCapaClCd(String maxTrmsNetEquipCapaClCd) {
		this.maxTrmsNetEquipCapaClCd = maxTrmsNetEquipCapaClCd;
	}

	/**
	 * 최소전송망장비용량구분코드
	 *
	 * @return 최소전송망장비용량구분코드
	 */
	public String getMinTrmsNetEquipCapaClCd() {
		return minTrmsNetEquipCapaClCd;
	}

	/**
	 * 최소전송망장비용량구분코드
	 *
	 * @param minTrmsNetEquipCapaClCd
	 *            최소전송망장비용량구분코드
	 */
	public void setMinTrmsNetEquipCapaClCd(String minTrmsNetEquipCapaClCd) {
		this.minTrmsNetEquipCapaClCd = minTrmsNetEquipCapaClCd;
	}

	/**
	 * 상위국사코드
	 *
	 * @return 상위국사코드
	 */
	public String getSupTpoCd() {
		return supTpoCd;
	}

	/**
	 * 상위국사코드
	 *
	 * @param supTpoCd
	 *            상위국사코드
	 */
	public void setSupTpoCd(String supTpoCd) {
		this.supTpoCd = supTpoCd;
	}

	/**
	 * 상위국사시스템정보
	 *
	 * @return 상위국사시스템정보
	 */
	public String getSupTpoSysInfo() {
		return supTpoSysInfo;
	}

	/**
	 * 상위국사시스템정보
	 *
	 * @param supTpoSysInfo
	 *            상위국사시스템정보
	 */
	public void setSupTpoSysInfo(String supTpoSysInfo) {
		this.supTpoSysInfo = supTpoSysInfo;
	}

	/**
	 * 하위국사코드
	 *
	 * @return 하위국사코드
	 */
	public String getSubTpoCd() {
		return subTpoCd;
	}

	/**
	 * 하위국사코드
	 *
	 * @param subTpoCd
	 *            하위국사코드
	 */
	public void setSubTpoCd(String subTpoCd) {
		this.subTpoCd = subTpoCd;
	}

	/**
	 * 하위국사시스템정보
	 *
	 * @return 하위국사시스템정보
	 */
	public String getSubTpoSysInfo() {
		return subTpoSysInfo;
	}

	/**
	 * 하위국사시스템정보
	 *
	 * @param subTpoSysInfo
	 *            하위국사시스템정보
	 */
	public void setSubTpoSysInfo(String subTpoSysInfo) {
		this.subTpoSysInfo = subTpoSysInfo;
	}

	/**
	 * 개통일시
	 *
	 * @return 개통일시
	 */
	public String getSvcDtm() {
		return svcDtm;
	}

	/**
	 * 개통일시
	 *
	 * @param svcDtm
	 *            개통일시
	 */
	public void setSvcDtm(String svcDtm) {
		this.svcDtm = svcDtm;
	}

	/**
	 * 해지일시
	 *
	 * @return 해지일시
	 */
	public String getTermDtm() {
		return termDtm;
	}

	/**
	 * 해지일시
	 *
	 * @param termDtm
	 *            해지일시
	 */
	public void setTermDtm(String termDtm) {
		this.termDtm = termDtm;
	}

	/**
	 * 청약번호
	 *
	 * @return 청약번호
	 */
	public String getApplNum() {
		return applNum;
	}

	/**
	 * 청약번호
	 *
	 * @param applNum
	 *            청약번호
	 */
	public void setApplNum(String applNum) {
		this.applNum = applNum;
	}

	/**
	 * 청약일시
	 *
	 * @return 청약일시
	 */
	public String getApplDtm() {
		return applDtm;
	}

	/**
	 * 청약일시
	 *
	 * @param applDtm
	 *            청약일시
	 */
	public void setApplDtm(String applDtm) {
		this.applDtm = applDtm;
	}

	/**
	 * 전송망장비간거리유형코드
	 *
	 * @return 전송망장비간거리유형코드
	 */
	public String getTrmsNetBtwEquipDistTypCd() {
		return trmsNetBtwEquipDistTypCd;
	}

	/**
	 * 전송망장비간거리유형코드
	 *
	 * @param trmsNetBtwEquipDistTypCd
	 *            전송망장비간거리유형코드
	 */
	public void setTrmsNetBtwEquipDistTypCd(String trmsNetBtwEquipDistTypCd) {
		this.trmsNetBtwEquipDistTypCd = trmsNetBtwEquipDistTypCd;
	}

	/**
	 * 전송망구간전체거리유형코드
	 *
	 * @return 전송망구간전체거리유형코드
	 */
	public String getTrmsNetRngAllDistTypCd() {
		return trmsNetRngAllDistTypCd;
	}

	/**
	 * 전송망구간전체거리유형코드
	 *
	 * @param trmsNetRngAllDistTypCd
	 *            전송망구간전체거리유형코드
	 */
	public void setTrmsNetRngAllDistTypCd(String trmsNetRngAllDistTypCd) {
		this.trmsNetRngAllDistTypCd = trmsNetRngAllDistTypCd;
	}

	/**
	 * 요금정보
	 *
	 * @return 요금정보
	 */
	public String getFeeInfo() {
		return feeInfo;
	}

	/**
	 * 요금정보
	 *
	 * @param feeInfo
	 *            요금정보
	 */
	public void setFeeInfo(String feeInfo) {
		this.feeInfo = feeInfo;
	}

	/**
	 * 전송망요금구분코드
	 *
	 * @return 전송망요금구분코드
	 */
	public String getTrmsNetFeeClCd() {
		return trmsNetFeeClCd;
	}

	/**
	 * 전송망요금구분코드
	 *
	 * @param trmsNetFeeClCd
	 *            전송망요금구분코드
	 */
	public void setTrmsNetFeeClCd(String trmsNetFeeClCd) {
		this.trmsNetFeeClCd = trmsNetFeeClCd;
	}

	/**
	 * 요금납부국사코드
	 *
	 * @return 요금납부국사코드
	 */
	public String getFeePayTpoCd() {
		return feePayTpoCd;
	}

	/**
	 * 요금납부국사코드
	 *
	 * @param feePayTpoCd
	 *            요금납부국사코드
	 */
	public void setFeePayTpoCd(String feePayTpoCd) {
		this.feePayTpoCd = feePayTpoCd;
	}

	/**
	 * 센터조직ID
	 *
	 * @return 센터조직ID
	 */
	public String getCntrOrgId() {
		return cntrOrgId;
	}

	/**
	 * 센터조직ID
	 *
	 * @param cntrOrgId
	 *            센터조직ID
	 */
	public void setCntrOrgId(String cntrOrgId) {
		this.cntrOrgId = cntrOrgId;
	}

	/**
	 * 전송망과금상태코드
	 *
	 * @return 전송망과금상태코드
	 */
	public String getTrmsNetBillStCd() {
		return trmsNetBillStCd;
	}

	/**
	 * 전송망과금상태코드
	 *
	 * @param trmsNetBillStCd
	 *            전송망과금상태코드
	 */
	public void setTrmsNetBillStCd(String trmsNetBillStCd) {
		this.trmsNetBillStCd = trmsNetBillStCd;
	}

	/**
	 * 회선상태코드
	 *
	 * @return 회선상태코드
	 */
	public String getLineStCd() {
		return lineStCd;
	}

	/**
	 * 회선상태코드
	 *
	 * @param lineStCd
	 *            회선상태코드
	 */
	public void setLineStCd(String lineStCd) {
		this.lineStCd = lineStCd;
	}

	/**
	 * 장애관리여부
	 *
	 * @return 장애관리여부
	 */
	public String isDablMgmtYn() {
		return dablMgmtYn;
	}

	/**
	 * 장애관리여부
	 *
	 * @param dablMgmtYn
	 *            장애관리여부
	 */
	public void setDablMgmtYn(String dablMgmtYn) {
		this.dablMgmtYn = dablMgmtYn;
	}

	/**
	 * 전송망관리등급코드
	 *
	 * @return 전송망관리등급코드
	 */
	public String getTrmsNetMgmtGrCd() {
		return trmsNetMgmtGrCd;
	}

	/**
	 * 전송망관리등급코드
	 *
	 * @param trmsNetMgmtGrCd
	 *            전송망관리등급코드
	 */
	public void setTrmsNetMgmtGrCd(String trmsNetMgmtGrCd) {
		this.trmsNetMgmtGrCd = trmsNetMgmtGrCd;
	}

	/**
	 * 전송망SKT지역구분코드
	 *
	 * @return 전송망SKT지역구분코드
	 */
	public String getTrmsNetSktAreaClCd() {
		return trmsNetSktAreaClCd;
	}

	/**
	 * 전송망SKT지역구분코드
	 *
	 * @param trmsNetSktAreaClCd
	 *            전송망SKT지역구분코드
	 */
	public void setTrmsNetSktAreaClCd(String trmsNetSktAreaClCd) {
		this.trmsNetSktAreaClCd = trmsNetSktAreaClCd;
	}

	/**
	 * 전송망선로인코딩유형코드
	 *
	 * @return 전송망선로인코딩유형코드
	 */
	public String getTrmsNetLneEncdgTypCd() {
		return trmsNetLneEncdgTypCd;
	}

	/**
	 * 전송망선로인코딩유형코드
	 *
	 * @param trmsNetLneEncdgTypCd
	 *            전송망선로인코딩유형코드
	 */
	public void setTrmsNetLneEncdgTypCd(String trmsNetLneEncdgTypCd) {
		this.trmsNetLneEncdgTypCd = trmsNetLneEncdgTypCd;
	}

	/**
	 * NMS감시여부
	 *
	 * @return NMS감시여부
	 */
	public String isNmsMntrYn() {
		return nmsMntrYn;
	}

	/**
	 * NMS감시여부
	 *
	 * @param nmsMntrYn
	 *            NMS감시여부
	 */
	public void setNmsMntrYn(String nmsMntrYn) {
		this.nmsMntrYn = nmsMntrYn;
	}

	/**
	 * 회선상위국번호
	 *
	 * @return 회선상위국번호
	 */
	public String getLineSupStnNum() {
		return lineSupStnNum;
	}

	/**
	 * 회선상위국번호
	 *
	 * @param lineSupStnNum
	 *            회선상위국번호
	 */
	public void setLineSupStnNum(String lineSupStnNum) {
		this.lineSupStnNum = lineSupStnNum;
	}

	/**
	 * 회선하위국번호
	 *
	 * @return 회선하위국번호
	 */
	public String getLineSubStnNum() {
		return lineSubStnNum;
	}

	/**
	 * 회선하위국번호
	 *
	 * @param lineSubStnNum
	 *            회선하위국번호
	 */
	public void setLineSubStnNum(String lineSubStnNum) {
		this.lineSubStnNum = lineSubStnNum;
	}

	/**
	 * 그룹사시스템회선번호
	 *
	 * @return 그룹사시스템회선번호
	 */
	public String getGrpCoSysLineNum() {
		return grpCoSysLineNum;
	}

	/**
	 * 그룹사시스템회선번호
	 *
	 * @param grpCoSysLineNum
	 *            그룹사시스템회선번호
	 */
	public void setGrpCoSysLineNum(String grpCoSysLineNum) {
		this.grpCoSysLineNum = grpCoSysLineNum;
	}

	/**
	 * 연동시스템속도코드값
	 *
	 * @return 연동시스템속도코드값
	 */
	public String getLnkgSysSpeedCdVal() {
		return lnkgSysSpeedCdVal;
	}

	/**
	 * 연동시스템속도코드값
	 *
	 * @param lnkgSysSpeedCdVal
	 *            연동시스템속도코드값
	 */
	public void setLnkgSysSpeedCdVal(String lnkgSysSpeedCdVal) {
		this.lnkgSysSpeedCdVal = lnkgSysSpeedCdVal;
	}

	/**
	 * 그룹사시스템회선경로정보
	 *
	 * @return 그룹사시스템회선경로정보
	 */
	public String getGrpCoSysLinePathInfo() {
		return grpCoSysLinePathInfo;
	}

	/**
	 * 그룹사시스템회선경로정보
	 *
	 * @param grpCoSysLinePathInfo
	 *            그룹사시스템회선경로정보
	 */
	public void setGrpCoSysLinePathInfo(String grpCoSysLinePathInfo) {
		this.grpCoSysLinePathInfo = grpCoSysLinePathInfo;
	}

	/**
	 * 전송망방향코드
	 *
	 * @return 전송망방향코드
	 */
	public String getTrmsNetDirnCd() {
		return trmsNetDirnCd;
	}

	/**
	 * 전송망방향코드
	 *
	 * @param trmsNetDirnCd
	 *            전송망방향코드
	 */
	public void setTrmsNetDirnCd(String trmsNetDirnCd) {
		this.trmsNetDirnCd = trmsNetDirnCd;
	}

	/**
	 * 서브상위국번호
	 *
	 * @return 서브상위국번호
	 */
	public String getSubSupStnNum() {
		return subSupStnNum;
	}

	/**
	 * 서브상위국번호
	 *
	 * @param subSupStnNum
	 *            서브상위국번호
	 */
	public void setSubSupStnNum(String subSupStnNum) {
		this.subSupStnNum = subSupStnNum;
	}

	/**
	 * 서브하위국번호
	 *
	 * @return 서브하위국번호
	 */
	public String getSubSubStnNum() {
		return subSubStnNum;
	}

	/**
	 * 서브하위국번호
	 *
	 * @param subSubStnNum
	 *            서브하위국번호
	 */
	public void setSubSubStnNum(String subSubStnNum) {
		this.subSubStnNum = subSubStnNum;
	}

	/**
	 * 메모내용1
	 *
	 * @return 메모내용1
	 */
	public String getMemoCtt1() {
		return memoCtt1;
	}

	/**
	 * 메모내용1
	 *
	 * @param memoCtt1
	 *            메모내용1
	 */
	public void setMemoCtt1(String memoCtt1) {
		this.memoCtt1 = memoCtt1;
	}

	/**
	 * 메모내용2
	 *
	 * @return 메모내용2
	 */
	public String getMemoCtt2() {
		return memoCtt2;
	}

	/**
	 * 메모내용2
	 *
	 * @param memoCtt2
	 *            메모내용2
	 */
	public void setMemoCtt2(String memoCtt2) {
		this.memoCtt2 = memoCtt2;
	}

	/**
	 * 메모내용3
	 *
	 * @return 메모내용3
	 */
	public String getMemoCtt3() {
		return memoCtt3;
	}

	/**
	 * 메모내용3
	 *
	 * @param memoCtt3
	 *            메모내용3
	 */
	public void setMemoCtt3(String memoCtt3) {
		this.memoCtt3 = memoCtt3;
	}

	/**
	 * 기지국번호
	 *
	 * @return 기지국번호
	 */
	public String getBasCntrNum() {
		return basCntrNum;
	}

	/**
	 * 기지국번호
	 *
	 * @param basCntrNum
	 *            기지국번호
	 */
	public void setBasCntrNum(String basCntrNum) {
		this.basCntrNum = basCntrNum;
	}

	/**
	 * 기업회선검증여부
	 *
	 * @return 기업회선검증여부
	 */
	public String isCoLineVrfYn() {
		return coLineVrfYn;
	}

	/**
	 * 기업회선검증여부
	 *
	 * @param coLineVrfYn
	 *            기업회선검증여부
	 */
	public void setCoLineVrfYn(String coLineVrfYn) {
		this.coLineVrfYn = coLineVrfYn;
	}

	/**
	 * QDF교환기명
	 *
	 * @return QDF교환기명
	 */
	public String getQdfMscNm() {
		return qdfMscNm;
	}

	/**
	 * QDF교환기명
	 *
	 * @param qdfMscNm
	 *            QDF교환기명
	 */
	public void setQdfMscNm(String qdfMscNm) {
		this.qdfMscNm = qdfMscNm;
	}

	/**
	 * ESS명
	 *
	 * @return ESS명
	 */
	public String getEssNm() {
		return essNm;
	}

	/**
	 * ESS명
	 *
	 * @param essNm
	 *            ESS명
	 */
	public void setEssNm(String essNm) {
		this.essNm = essNm;
	}

	/**
	 * 상위기업회선LLCF상태코드
	 *
	 * @return 상위기업회선LLCF상태코드
	 */
	public String getSupCoLineLlcfStCd() {
		return supCoLineLlcfStCd;
	}

	/**
	 * 상위기업회선LLCF상태코드
	 *
	 * @param supCoLineLlcfStCd
	 *            상위기업회선LLCF상태코드
	 */
	public void setSupCoLineLlcfStCd(String supCoLineLlcfStCd) {
		this.supCoLineLlcfStCd = supCoLineLlcfStCd;
	}

	/**
	 * 상위기업회선협상기준코드
	 *
	 * @return 상위기업회선협상기준코드
	 */
	public String getSupCoLineNegoStrdCd() {
		return supCoLineNegoStrdCd;
	}

	/**
	 * 상위기업회선협상기준코드
	 *
	 * @param supCoLineNegoStrdCd
	 *            상위기업회선협상기준코드
	 */
	public void setSupCoLineNegoStrdCd(String supCoLineNegoStrdCd) {
		this.supCoLineNegoStrdCd = supCoLineNegoStrdCd;
	}

	/**
	 * 하위기업회선LLCF상태코드
	 *
	 * @return 하위기업회선LLCF상태코드
	 */
	public String getSubCoLineLlcfStCd() {
		return subCoLineLlcfStCd;
	}

	/**
	 * 하위기업회선LLCF상태코드
	 *
	 * @param subCoLineLlcfStCd
	 *            하위기업회선LLCF상태코드
	 */
	public void setSubCoLineLlcfStCd(String subCoLineLlcfStCd) {
		this.subCoLineLlcfStCd = subCoLineLlcfStCd;
	}

	/**
	 * 하위기업회선협상기준코드
	 *
	 * @return 하위기업회선협상기준코드
	 */
	public String getSubCoLineNegoStrdCd() {
		return subCoLineNegoStrdCd;
	}

	/**
	 * 하위기업회선협상기준코드
	 *
	 * @param subCoLineNegoStrdCd
	 *            하위기업회선협상기준코드
	 */
	public void setSubCoLineNegoStrdCd(String subCoLineNegoStrdCd) {
		this.subCoLineNegoStrdCd = subCoLineNegoStrdCd;
	}

	/**
	 * 고객명
	 *
	 * @return 고객명
	 */
	public String getCustNm() {
		return custNm;
	}

	/**
	 * 고객명
	 *
	 * @param custNm
	 *            고객명
	 */
	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	/**
	 * 등록자정보
	 *
	 * @return 등록자정보
	 */
	public String getRgstrInfo() {
		return rgstrInfo;
	}

	/**
	 * 등록자정보
	 *
	 * @param rgstrInfo
	 *            등록자정보
	 */
	public void setRgstrInfo(String rgstrInfo) {
		this.rgstrInfo = rgstrInfo;
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
	 * 변경자정보
	 *
	 * @return 변경자정보
	 */
	public String getChgrInfo() {
		return chgrInfo;
	}

	/**
	 * 변경자정보
	 *
	 * @param chgrInfo
	 *            변경자정보
	 */
	public void setChgrInfo(String chgrInfo) {
		this.chgrInfo = chgrInfo;
	}

	/**
	 * 변경일시
	 *
	 * @return 변경일시
	 */
	public String getChgDtm() {
		return chgDtm;
	}

	/**
	 * 변경일시
	 *
	 * @param chgDtm
	 *            변경일시
	 */
	public void setChgDtm(String chgDtm) {
		this.chgDtm = chgDtm;
	}

	/**
	 * SKT회선관리번호
	 *
	 * @return SKT회선관리번호
	 */
	public String getSktLineMgmtNum() {
		return sktLineMgmtNum;
	}

	/**
	 * SKT회선관리번호
	 *
	 * @param sktLineMgmtNum
	 *            SKT회선관리번호
	 */
	public void setSktLineMgmtNum(String sktLineMgmtNum) {
		this.sktLineMgmtNum = sktLineMgmtNum;
	}

	/**
	 * 영업담당자ID
	 *
	 * @return 영업담당자ID
	 */
	public String getSaleChrgrId() {
		return saleChrgrId;
	}

	/**
	 * 영업담당자ID
	 *
	 * @param saleChrgrId
	 *            영업담당자ID
	 */
	public void setSaleChrgrId(String saleChrgrId) {
		this.saleChrgrId = saleChrgrId;
	}
}

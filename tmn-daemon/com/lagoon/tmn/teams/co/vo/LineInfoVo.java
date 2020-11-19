package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;

import com.lagoon.tmn.teams.co.AdamsCfg.LineStCd;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * 회선기본 VO (OIV10611)
 * 
 * <pre>
 *  SqlUtil 클래스 이용하여 자동 생성
 *  2019.08.21 불필요 필드 주석(필요시 사용)
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@FxTable(name = "OIV10611", comment = "전송망회선기본")
@SuppressWarnings("serial")
public class LineInfoVo implements Serializable {

	@FxColumn(name = "LINE_NUM", size = 12, comment = "회선번호")
	private String lineNum;

	@FxColumn(name = "CUST_NUM", size = 10, nullable = true, comment = "고객번호")
	private long custNum;

	@FxColumn(name = "SVC_NUM", size = 20, nullable = true, comment = "서비스번호")
	private String svcNum;

	@FxColumn(name = "LINE_NM", size = 100, nullable = true, comment = "회선명")
	private String lineNm;

	@FxColumn(name = "TRMS_NET_LINE_USG_CD", size = 2, comment = "전송망회선용도코드")
	private String trmsNetLineUsgCd;

	@FxColumn(name = "TRMS_NET_LINE_SVC_CD", size = 4, comment = "전송망회선서비스코드")
	private String trmsNetLineSvcCd;

	@FxColumn(name = "MAX_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, comment = "최대전송망장비용량구분코드")
	private String maxTrmsNetEquipCapaClCd;
	@FxColumn(name = "SUP_TPO_CD", size = 6, nullable = true, comment = "상위국사코드")
	private String supTpoCd;

	@FxColumn(name = "SUB_TPO_CD", size = 6, nullable = true, comment = "하위국사코드")
	private String subTpoCd;

	@FxColumn(name = "LINE_ST_CD", size = 2, comment = "회선상태코드")
	private String lineStCd;

	@FxColumn(name = "DABL_MGMT_YN", size = 1, comment = "장애관리여부", defValue = "'N'")
	private boolean dablMgmtYn = false;

	@FxColumn(name = "TRMS_NET_MGMT_GR_CD", size = 5, comment = "전송망관리등급코드")
	private String trmsNetMgmtGrCd;

	@FxColumn(name = "CUST_NM", size = 40, nullable = true, comment = "고객명")
	private String custNm;

	@FxColumn(name = "SALE_CHRGR_ID", size = 100, nullable = true, comment = "영업담당자ID")
	private String saleChrgrId;

	// 하위국사명
	private String subTpoNm;

	// 상위국사명
	private String supTpoNm;

	// 전송망관리등급코드명
	private String trmsNetMgmtGrNm;

	// 망장애코드 (아담스 클라이언트에서 필요)
	private String netDablCd;

	/*
	 * 불필요 필드 주석(필요시 사용)
	 * 
	 * @FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID") private
	 * String auditId;
	 * 
	 * @FxColumn(name = "RENT_BBNT_LINE_NUM", size = 32, nullable = true,
	 * comment = "임차기간망회선번호") private String rentBbntLineNum;
	 * 
	 * @FxColumn(name = "RENT_LINE_BIZR_CD", size = 4, comment = "임대회선사업자코드")
	 * private String rentLineBizrCd;
	 * 
	 * @FxColumn(name = "LINK_LINE_BIZR_CD", size = 4, comment = "연결회선사업자코드")
	 * private String linkLineBizrCd;
	 * 
	 * @FxColumn(name = "LINE_CNTRCT_PRD_TYP_CD", size = 2, comment =
	 * "회선계약기간유형코드") private String lineCntrctPrdTypCd;
	 * 
	 * @FxColumn(name = "TRMS_NET_MGMT_OWNR_CD", size = 2, comment =
	 * "전송망관리주체코드") private String trmsNetMgmtOwnrCd;
	 * 
	 * @FxColumn(name = "MIN_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, comment =
	 * "최소전송망장비용량구분코드") private String minTrmsNetEquipCapaClCd;
	 * 
	 * @FxColumn(name = "SUP_TPO_SYS_INFO", size = 40, nullable = true, comment
	 * = "상위국사시스템정보") private String supTpoSysInfo;
	 * 
	 * @FxColumn(name = "SUB_TPO_SYS_INFO", size = 40, nullable = true, comment
	 * = "하위국사시스템정보") private String subTpoSysInfo;
	 * 
	 * @FxColumn(name = "SVC_DTM", size = 14, nullable = true, comment = "개통일시")
	 * private String svcDtm;
	 * 
	 * @FxColumn(name = "TERM_DTM", size = 14, nullable = true, comment =
	 * "해지일시") private String termDtm;
	 * 
	 * @FxColumn(name = "APPL_NUM", size = 20, nullable = true, comment =
	 * "청약번호") private String applNum;
	 * 
	 * @FxColumn(name = "APPL_DTM", size = 14, nullable = true, comment =
	 * "청약일시") private String applDtm;
	 * 
	 * @FxColumn(name = "TRMS_NET_BTW_EQUIP_DIST_TYP_CD", size = 4, comment =
	 * "전송망장비간거리유형코드") private String trmsNetBtwEquipDistTypCd;
	 * 
	 * @FxColumn(name = "TRMS_NET_RNG_ALL_DIST_TYP_CD", size = 2, comment =
	 * "전송망구간전체거리유형코드") private String trmsNetRngAllDistTypCd;
	 * 
	 * @FxColumn(name = "FEE_INFO", size = 100, nullable = true, comment =
	 * "요금정보") private String feeInfo;
	 * 
	 * @FxColumn(name = "TRMS_NET_FEE_CL_CD", size = 2, comment = "전송망요금구분코드")
	 * private String trmsNetFeeClCd;
	 * 
	 * @FxColumn(name = "FEE_PAY_TPO_CD", size = 6, nullable = true, comment =
	 * "요금납부국사코드") private String feePayTpoCd;
	 * 
	 * @FxColumn(name = "CNTR_ORG_ID", size = 10, nullable = true, comment =
	 * "센터조직ID") private String cntrOrgId;
	 * 
	 * @FxColumn(name = "TRMS_NET_BILL_ST_CD", size = 2, comment = "전송망과금상태코드")
	 * private String trmsNetBillStCd;
	 * 
	 * @FxColumn(name = "TRMS_NET_SKT_AREA_CL_CD", size = 3, comment =
	 * "전송망SKT지역구분코드") private String trmsNetSktAreaClCd;
	 * 
	 * @FxColumn(name = "TRMS_NET_LNE_ENCDG_TYP_CD", size = 2, comment =
	 * "전송망선로인코딩유형코드") private String trmsNetLneEncdgTypCd;
	 * 
	 * @FxColumn(name = "NMS_MNTR_YN", size = 1, comment = "NMS감시여부", defValue =
	 * "'N'") private boolean nmsMntrYn = false;
	 * 
	 * @FxColumn(name = "LINE_SUP_STN_NUM", size = 10, nullable = true, comment
	 * = "회선상위국번호") private String lineSupStnNum;
	 * 
	 * @FxColumn(name = "LINE_SUB_STN_NUM", size = 10, nullable = true, comment
	 * = "회선하위국번호") private String lineSubStnNum;
	 * 
	 * @FxColumn(name = "GRP_CO_SYS_LINE_NUM", size = 20, nullable = true,
	 * comment = "그룹사시스템회선번호") private String grpCoSysLineNum;
	 * 
	 * @FxColumn(name = "LNKG_SYS_SPEED_CD_VAL", size = 100, nullable = true,
	 * comment = "연동시스템속도코드값") private String lnkgSysSpeedCdVal;
	 * 
	 * @FxColumn(name = "GRP_CO_SYS_LINE_PATH_INFO", size = 1000, nullable =
	 * true, comment = "그룹사시스템회선경로정보") private String grpCoSysLinePathInfo;
	 * 
	 * @FxColumn(name = "TRMS_NET_DIRN_CD", size = 1, comment = "전송망방향코드")
	 * private String trmsNetDirnCd;
	 * 
	 * @FxColumn(name = "SUB_SUP_STN_NUM", size = 10, nullable = true, comment =
	 * "서브상위국번호") private String subSupStnNum;
	 * 
	 * @FxColumn(name = "SUB_SUB_STN_NUM", size = 10, nullable = true, comment =
	 * "서브하위국번호") private String subSubStnNum;
	 * 
	 * @FxColumn(name = "MEMO_CTT1", size = 400, nullable = true, comment =
	 * "메모내용1") private String memoCtt1;
	 * 
	 * @FxColumn(name = "MEMO_CTT2", size = 400, nullable = true, comment =
	 * "메모내용2") private String memoCtt2;
	 * 
	 * @FxColumn(name = "MEMO_CTT3", size = 400, nullable = true, comment =
	 * "메모내용3") private String memoCtt3;
	 * 
	 * @FxColumn(name = "BAS_CNTR_NUM", size = 10, nullable = true, comment =
	 * "기지국번호") private String basCntrNum;
	 * 
	 * @FxColumn(name = "CO_LINE_VRF_YN", size = 1, nullable = true, comment =
	 * "기업회선검증여부") private boolean coLineVrfYn;
	 * 
	 * @FxColumn(name = "QDF_MSC_NM", size = 100, nullable = true, comment =
	 * "QDF교환기명") private String qdfMscNm;
	 * 
	 * @FxColumn(name = "ESS_NM", size = 30, nullable = true, comment = "ESS명")
	 * private String essNm;
	 * 
	 * @FxColumn(name = "SUP_CO_LINE_LLCF_ST_CD", size = 2, nullable = true,
	 * comment = "상위기업회선LLCF상태코드") private String supCoLineLlcfStCd;
	 * 
	 * @FxColumn(name = "SUP_CO_LINE_NEGO_STRD_CD", size = 4, nullable = true,
	 * comment = "상위기업회선협상기준코드") private String supCoLineNegoStrdCd;
	 * 
	 * @FxColumn(name = "SUB_CO_LINE_LLCF_ST_CD", size = 2, nullable = true,
	 * comment = "하위기업회선LLCF상태코드") private String subCoLineLlcfStCd;
	 * 
	 * @FxColumn(name = "SUB_CO_LINE_NEGO_STRD_CD", size = 4, nullable = true,
	 * comment = "하위기업회선협상기준코드") private String subCoLineNegoStrdCd;
	 * 
	 * @FxColumn(name = "RGSTR_INFO", size = 20, nullable = true, comment =
	 * "등록자정보") private String rgstrInfo;
	 * 
	 * @FxColumn(name = "RGST_DTM", size = 14, nullable = true, comment =
	 * "등록일시") private String rgstDtm;
	 * 
	 * @FxColumn(name = "CHGR_INFO", size = 20, nullable = true, comment =
	 * "변경자정보") private String chgrInfo;
	 * 
	 * @FxColumn(name = "CHG_DTM", size = 14, nullable = true, comment = "변경일시")
	 * private String chgDtm;
	 * 
	 * @FxColumn(name = "SKT_LINE_MGMT_NUM", size = 32, nullable = true, comment
	 * = "SKT회선관리번호") private String sktLineMgmtNum;
	 * 
	 * @FxColumn(name = "SUP_EQUIP_ID", size = 12, nullable = true, comment =
	 * "상위장비ID") private String supEquipId;
	 * 
	 * @FxColumn(name = "SUP_EQUIP_PORT_INFO", size = 200, nullable = true,
	 * comment = "상위장비포트정보") private String supEquipPortInfo;
	 * 
	 * @FxColumn(name = "SUB_EQUIP_ID", size = 7, nullable = true, comment =
	 * "하위장비ID") private String subEquipId;
	 * 
	 * @FxColumn(name = "SUB_EQUIP_PORT_INFO", size = 200, nullable = true,
	 * comment = "하위장비포트정보") private String subEquipPortInfo;
	 * 
	 * @FxColumn(name = "BBNT_LINE_NUM", size = 32, nullable = true, comment =
	 * "기간망회선번호") private String bbntLineNum;
	 * 
	 * @FxColumn(name = "BBNT_TRUNK_NM", size = 100, nullable = true, comment =
	 * "기간망TRUNK명") private String bbntTrunkNm;
	 * 
	 * @FxColumn(name = "BBNT_RGST_YN", size = 1, comment = "기간망등록여부", defValue
	 * = "'N'") private boolean bbntRgstYn = false;
	 * 
	 * @FxColumn(name = "TRMS_NET_CO_LINE_NVRF_RSN_CD", size = 2, nullable =
	 * true, comment = "전송망기업회선비검증사유코드") private String trmsNetCoLineNvrfRsnCd;
	 * 
	 * @FxColumn(name = "NEW_LINE_YN", size = 1, comment = "신규회선여부", defValue =
	 * "'N'") private boolean newLineYn = false;
	 * 
	 * @FxColumn(name = "MNTR_OBJ_LINE_YN", size = 1, comment = "감시대상회선여부",
	 * defValue = "'N'") private boolean mntrObjLineYn = false;
	 * 
	 * @FxColumn(name = "LINE_ST_CHG_DTM", size = 14, nullable = true, comment =
	 * "회선상태변경일시") private String lineStChgDtm;
	 * 
	 * @FxColumn(name = "TERMR_INFO", size = 20, nullable = true, comment =
	 * "해지자정보") private String termrInfo;
	 * 
	 * @FxColumn(name = "TERM_CHKR_INFO", size = 20, nullable = true, comment =
	 * "해지확인자정보") private String termChkrInfo;
	 * 
	 * @FxColumn(name = "LINK_INS_DTM", size = 14, nullable = true, comment =
	 * "링크입력일시") private String linkInsDtm;
	 * 
	 * @FxColumn(name = "LINK_RGSTR_INFO", size = 20, nullable = true, comment =
	 * "링크등록자정보") private String linkRgstrInfo;
	 * 
	 * @FxColumn(name = "EGOV_BB_NET_LNKG_INFO", size = 50, nullable = true,
	 * comment = "전자정부백본망연동정보") private String egovBbNetLnkgInfo;
	 * 
	 * @FxColumn(name = "EGOV_BB_NET_CHECK_NEED_YN", size = 1, comment =
	 * "전자정부백본망확인필요여부", defValue = "'N'") private boolean egovBbNetCheckNeedYn =
	 * false;
	 * 
	 * @FxColumn(name = "EGOV_BB_NET_CHECK_INFO", size = 500, nullable = true,
	 * comment = "전자정부백본망확인정보") private String egovBbNetCheckInfo;
	 * 
	 * @FxColumn(name = "ATFIL_GRP_SER_NUM", size = 15, nullable = true, comment
	 * = "첨부파일그룹일련번호") private long atfilGrpSerNum;
	 */

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
	public long getCustNum() {
		return custNum;
	}

	/**
	 * 고객번호
	 *
	 * @param custNum
	 *            고객번호
	 */
	public void setCustNum(long custNum) {
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
	public boolean isDablMgmtYn() {
		return dablMgmtYn;
	}

	/**
	 * 장애관리여부
	 *
	 * @param dablMgmtYn
	 *            장애관리여부
	 */
	public void setDablMgmtYn(boolean dablMgmtYn) {
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

	public String getSubTpoNm() {
		return subTpoNm;
	}

	public void setSubTpoNm(String subTpoNm) {
		this.subTpoNm = subTpoNm;
	}

	public String getSupTpoNm() {
		return supTpoNm;
	}

	public void setSupTpoNm(String supTpoNm) {
		this.supTpoNm = supTpoNm;
	}

	public String getTrmsNetMgmtGrNm() {
		return trmsNetMgmtGrNm;
	}

	public void setTrmsNetMgmtGrNm(String trmsNetMgmtGrNm) {
		this.trmsNetMgmtGrNm = trmsNetMgmtGrNm;
	}

	public String getNetDablCd() {
		return netDablCd;
	}

	public void setNetDablCd(String netDablCd) {
		this.netDablCd = netDablCd;
	}

	/**
	 * 회선 상태코드 값이 해지(08), 미개통(12), 개통중(13) 은 true)
	 * 
	 * @return boolean
	 */
	public boolean isLineSkip() {
		return (lineStCd.equals(LineStCd.CLOSE)
				|| lineStCd.equals(LineStCd.NOT_OPEN) || lineStCd
					.equals(LineStCd.OPENING));
	}

	@Override
	public String toString() {
		return "LineInfoVo [lineNum=" + lineNum + ", custNum=" + custNum
				+ ", svcNum=" + svcNum + ", lineNm=" + lineNm + "]";
	}

}
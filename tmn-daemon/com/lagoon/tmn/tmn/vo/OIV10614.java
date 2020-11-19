package com.lagoon.tmn.tmn.vo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.11.18 16:00
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10614", comment = "네트워크기본")
public class OIV10614 {

	public OIV10614() {
	}

	@FxColumn(name = "NET_NUM", size = 12, comment = "망번호")
	private String netNum;

	@FxColumn(name = "NW_NM", size = 100, comment = "NETWORK명")
	private String nwNm;

	@FxColumn(name = "TRMS_NET_TOPO_TYP_CD", size = 4, comment = "전송망토폴로지유형코드")
	private String trmsNetTopoTypCd;

	@FxColumn(name = "TRMS_NET_USG_CD", size = 2, comment = "전송망용도코드")
	private String trmsNetUsgCd;

	@FxColumn(name = "TRMS_NET_MGMT_OWNR_CD", size = 2, comment = "전송망관리주체코드")
	private String trmsNetMgmtOwnrCd;

	@FxColumn(name = "MAX_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, nullable = true, comment = "최대전송망장비용량구분코드")
	private String maxTrmsNetEquipCapaClCd;

	@FxColumn(name = "MIN_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, nullable = true, comment = "최소전송망장비용량구분코드")
	private String minTrmsNetEquipCapaClCd;

	@FxColumn(name = "SUP_TPO_CD", size = 6, nullable = true, comment = "상위국사코드")
	private String supTpoCd;

	@FxColumn(name = "SUB_TPO_CD", size = 6, nullable = true, comment = "하위국사코드")
	private String subTpoCd;

	@FxColumn(name = "LINE_RMK", size = 100, nullable = true, comment = "회선비고")
	private String lineRmk;

	@FxColumn(name = "LINE_BIZR_CD", size = 4, nullable = true, comment = "회선사업자코드")
	private String lineBizrCd;

	@FxColumn(name = "ETC_DESC", size = 200, nullable = true, comment = "기타설명")
	private String etcDesc;

	@FxColumn(name = "OTHR_SYS_NET_NUM", size = 100, nullable = true, comment = "타시스템망번호")
	private String othrSysNetNum;

	@FxColumn(name = "RGSTR_INFO", size = 20, nullable = true, comment = "등록자정보")
	private String rgstrInfo;

	@FxColumn(name = "RGST_DTM", size = 14, nullable = true, comment = "등록일시")
	private String rgstDtm;

	@FxColumn(name = "CHGR_INFO", size = 20, nullable = true, comment = "변경자정보")
	private String chgrInfo;

	@FxColumn(name = "CHG_DTM", size = 14, nullable = true, comment = "변경일시")
	private String chgDtm;

	@FxColumn(name = "RTYP_NET_SWTCHG_MTHD_CD", size = 2, nullable = true, comment = "링형망절체방식코드")
	private String rtypNetSwtchgMthdCd;

	@FxColumn(name = "INS_PROC_ST_VAL", size = 1, nullable = true, comment = "입력진행상태값")
	private String insProcStVal;

	@FxColumn(name = "SVC_DTM", size = 14, nullable = true, comment = "개통일시")
	private String svcDtm;

	@FxColumn(name = "SKT_EMS_ID", size = 50, nullable = true, comment = "SKTEMSID")
	private String sktEmsId;

	@FxColumn(name = "BF_NET_NUM", size = 10, nullable = true, comment = "이전망번호")
	private String bfNetNum;

	@FxColumn(name = "LINE_ST_CD", size = 2, comment = "회선상태코드")
	private String lineStCd;

	@FxColumn(name = "TERM_DTM", size = 14, nullable = true, comment = "해지일시")
	private String termDtm;

	@FxColumn(name = "DABL_MGMT_YN", size = 1, comment = "장애관리여부", defValue = "'N'")
	private String dablMgmtYn = "N";

	@FxColumn(name = "TRMS_NET_LINE_SVC_CD", size = 4, nullable = true, comment = "전송망회선서비스코드")
	private String trmsNetLineSvcCd;

	@FxColumn(name = "RENT_BBNT_LINE_NUM", size = 32, nullable = true, comment = "임차기간망회선번호")
	private String rentBbntLineNum;

	@FxColumn(name = "SUP_TPO_SYS_INFO", size = 40, nullable = true, comment = "상위국사시스템정보")
	private String supTpoSysInfo;

	@FxColumn(name = "SUB_TPO_SYS_INFO", size = 40, nullable = true, comment = "하위국사시스템정보")
	private String subTpoSysInfo;

	@FxColumn(name = "CNTR_ORG_ID", size = 10, nullable = true, comment = "센터조직ID")
	private String cntrOrgId;

	@FxColumn(name = "TRMS_NET_MGMT_GR_CD", size = 5, nullable = true, comment = "전송망관리등급코드")
	private String trmsNetMgmtGrCd;

	@FxColumn(name = "MEMO_CTT1", size = 400, nullable = true, comment = "메모내용1")
	private String memoCtt1;

	@FxColumn(name = "MEMO_CTT2", size = 400, nullable = true, comment = "메모내용2")
	private String memoCtt2;

	@FxColumn(name = "MEMO_CTT3", size = 400, nullable = true, comment = "메모내용3")
	private String memoCtt3;

	@FxColumn(name = "VLAN_INFO", size = 100, nullable = true, comment = "VLAN정보")
	private String vlanInfo;

	@FxColumn(name = "RT_RING_NUM", size = 10, nullable = true, comment = "RT링번호")
	private String rtRingNum;

	@FxColumn(name = "TRUNK_IDLE_RT", size = 15, nullable = true, comment = "TRUNK유휴율")
	private Double trunkIdleRt;

	@FxColumn(name = "TDM_USE_YN", size = 1, comment = "TDM사용여부", defValue = "'N'")
	private String tdmUseYn = "N";

	@FxColumn(name = "TDM_USE_YN_RSN_INFO", size = 500, nullable = true, comment = "TDM사용여부사유정보")
	private String tdmUseYnRsnInfo;

	@FxColumn(name = "TDM_USE_YN_CHGR_ID", size = 15, nullable = true, comment = "TDM사용여부변경자ID")
	private String tdmUseYnChgrId;

	@FxColumn(name = "TERMR_ID", size = 15, nullable = true, comment = "해지자ID")
	private String termrId;

	@FxColumn(name = "WDM_TRUNK_SUBS_INFO", size = 50, nullable = true, comment = "WDMTRUNK대역정보")
	private String wdmTrunkSubsInfo;

	@FxColumn(name = "WDM_TRUNK_CHNL_INFO", size = 50, nullable = true, comment = "WDMTRUNK채널정보")
	private String wdmTrunkChnlInfo;

	@FxColumn(name = "WDM_TRUNK_FREQ_VAL", size = 15, nullable = true, comment = "WDMTRUNK주파수값")
	private Double wdmTrunkFreqVal;

	@FxColumn(name = "WDM_TRUNK_DIRN_INFO", size = 200, nullable = true, comment = "WDMTRUNK방향정보")
	private String wdmTrunkDirnInfo;

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, nullable = true, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "TEAM_ORG_ID", size = 10, nullable = true, comment = "팀조직ID")
	private String teamOrgId;

	@FxColumn(name = "LINK_INS_DTM", size = 14, nullable = true, comment = "링크입력일시")
	private String linkInsDtm;

	@FxColumn(name = "LINK_RGSTR_INFO", size = 20, nullable = true, comment = "링크등록자정보")
	private String linkRgstrInfo;

	@FxColumn(name = "OPER_FNSH_YN", size = 1, comment = "작업완료여부", defValue = "'Y'")
	private String operFnshYn = "Y";

	@FxColumn(name = "TSDN_BBNT_LINE_NUM", size = 10, nullable = true, comment = "TSDN기간망회선번호")
	private String tsdnBbntLineNum;

	@FxColumn(name = "RSV_TSDN_BBNT_LINE_NUM", size = 10, nullable = true, comment = "예비TSDN기간망회선번호")
	private String rsvTsdnBbntLineNum;

	@FxColumn(name = "TSDN_PRD_NET_INFO_UPD_DT", size = 8, nullable = true, comment = "TSDN기간망정보수정일자")
	private String tsdnPrdNetInfoUpdDt;

	@FxColumn(name = "TANGO_NET_NUM", size = 13, nullable = true, comment = "TANGO망번호")
	private String tangoNetNum;

	/**
	 * 망번호
	 *
	 * @return 망번호
	 */
	public String getNetNum() {
		return netNum;
	}

	/**
	 * 망번호
	 *
	 * @param netNum
	 *            망번호
	 */
	public void setNetNum(String netNum) {
		this.netNum = netNum;
	}

	/**
	 * NETWORK명
	 *
	 * @return NETWORK명
	 */
	public String getNwNm() {
		return nwNm;
	}

	/**
	 * NETWORK명
	 *
	 * @param nwNm
	 *            NETWORK명
	 */
	public void setNwNm(String nwNm) {
		this.nwNm = nwNm;
	}

	/**
	 * 전송망토폴로지유형코드
	 *
	 * @return 전송망토폴로지유형코드
	 */
	public String getTrmsNetTopoTypCd() {
		return trmsNetTopoTypCd;
	}

	/**
	 * 전송망토폴로지유형코드
	 *
	 * @param trmsNetTopoTypCd
	 *            전송망토폴로지유형코드
	 */
	public void setTrmsNetTopoTypCd(String trmsNetTopoTypCd) {
		this.trmsNetTopoTypCd = trmsNetTopoTypCd;
	}

	/**
	 * 전송망용도코드
	 *
	 * @return 전송망용도코드
	 */
	public String getTrmsNetUsgCd() {
		return trmsNetUsgCd;
	}

	/**
	 * 전송망용도코드
	 *
	 * @param trmsNetUsgCd
	 *            전송망용도코드
	 */
	public void setTrmsNetUsgCd(String trmsNetUsgCd) {
		this.trmsNetUsgCd = trmsNetUsgCd;
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
	 * 회선비고
	 *
	 * @return 회선비고
	 */
	public String getLineRmk() {
		return lineRmk;
	}

	/**
	 * 회선비고
	 *
	 * @param lineRmk
	 *            회선비고
	 */
	public void setLineRmk(String lineRmk) {
		this.lineRmk = lineRmk;
	}

	/**
	 * 회선사업자코드
	 *
	 * @return 회선사업자코드
	 */
	public String getLineBizrCd() {
		return lineBizrCd;
	}

	/**
	 * 회선사업자코드
	 *
	 * @param lineBizrCd
	 *            회선사업자코드
	 */
	public void setLineBizrCd(String lineBizrCd) {
		this.lineBizrCd = lineBizrCd;
	}

	/**
	 * 기타설명
	 *
	 * @return 기타설명
	 */
	public String getEtcDesc() {
		return etcDesc;
	}

	/**
	 * 기타설명
	 *
	 * @param etcDesc
	 *            기타설명
	 */
	public void setEtcDesc(String etcDesc) {
		this.etcDesc = etcDesc;
	}

	/**
	 * 타시스템망번호
	 *
	 * @return 타시스템망번호
	 */
	public String getOthrSysNetNum() {
		return othrSysNetNum;
	}

	/**
	 * 타시스템망번호
	 *
	 * @param othrSysNetNum
	 *            타시스템망번호
	 */
	public void setOthrSysNetNum(String othrSysNetNum) {
		this.othrSysNetNum = othrSysNetNum;
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
	 * 링형망절체방식코드
	 *
	 * @return 링형망절체방식코드
	 */
	public String getRtypNetSwtchgMthdCd() {
		return rtypNetSwtchgMthdCd;
	}

	/**
	 * 링형망절체방식코드
	 *
	 * @param rtypNetSwtchgMthdCd
	 *            링형망절체방식코드
	 */
	public void setRtypNetSwtchgMthdCd(String rtypNetSwtchgMthdCd) {
		this.rtypNetSwtchgMthdCd = rtypNetSwtchgMthdCd;
	}

	/**
	 * 입력진행상태값
	 *
	 * @return 입력진행상태값
	 */
	public String getInsProcStVal() {
		return insProcStVal;
	}

	/**
	 * 입력진행상태값
	 *
	 * @param insProcStVal
	 *            입력진행상태값
	 */
	public void setInsProcStVal(String insProcStVal) {
		this.insProcStVal = insProcStVal;
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
	 * SKTEMSID
	 *
	 * @return SKTEMSID
	 */
	public String getSktEmsId() {
		return sktEmsId;
	}

	/**
	 * SKTEMSID
	 *
	 * @param sktEmsId
	 *            SKTEMSID
	 */
	public void setSktEmsId(String sktEmsId) {
		this.sktEmsId = sktEmsId;
	}

	/**
	 * 이전망번호
	 *
	 * @return 이전망번호
	 */
	public String getBfNetNum() {
		return bfNetNum;
	}

	/**
	 * 이전망번호
	 *
	 * @param bfNetNum
	 *            이전망번호
	 */
	public void setBfNetNum(String bfNetNum) {
		this.bfNetNum = bfNetNum;
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
	 * VLAN정보
	 *
	 * @return VLAN정보
	 */
	public String getVlanInfo() {
		return vlanInfo;
	}

	/**
	 * VLAN정보
	 *
	 * @param vlanInfo
	 *            VLAN정보
	 */
	public void setVlanInfo(String vlanInfo) {
		this.vlanInfo = vlanInfo;
	}

	/**
	 * RT링번호
	 *
	 * @return RT링번호
	 */
	public String getRtRingNum() {
		return rtRingNum;
	}

	/**
	 * RT링번호
	 *
	 * @param rtRingNum
	 *            RT링번호
	 */
	public void setRtRingNum(String rtRingNum) {
		this.rtRingNum = rtRingNum;
	}

	/**
	 * TRUNK유휴율
	 *
	 * @return TRUNK유휴율
	 */
	public Double getTrunkIdleRt() {
		return trunkIdleRt;
	}

	/**
	 * TRUNK유휴율
	 *
	 * @param trunkIdleRt
	 *            TRUNK유휴율
	 */
	public void setTrunkIdleRt(Double trunkIdleRt) {
		this.trunkIdleRt = trunkIdleRt;
	}

	/**
	 * TDM사용여부
	 *
	 * @return TDM사용여부
	 */
	public String isTdmUseYn() {
		return tdmUseYn;
	}

	/**
	 * TDM사용여부
	 *
	 * @param tdmUseYn
	 *            TDM사용여부
	 */
	public void setTdmUseYn(String tdmUseYn) {
		this.tdmUseYn = tdmUseYn;
	}

	/**
	 * TDM사용여부사유정보
	 *
	 * @return TDM사용여부사유정보
	 */
	public String getTdmUseYnRsnInfo() {
		return tdmUseYnRsnInfo;
	}

	/**
	 * TDM사용여부사유정보
	 *
	 * @param tdmUseYnRsnInfo
	 *            TDM사용여부사유정보
	 */
	public void setTdmUseYnRsnInfo(String tdmUseYnRsnInfo) {
		this.tdmUseYnRsnInfo = tdmUseYnRsnInfo;
	}

	/**
	 * TDM사용여부변경자ID
	 *
	 * @return TDM사용여부변경자ID
	 */
	public String getTdmUseYnChgrId() {
		return tdmUseYnChgrId;
	}

	/**
	 * TDM사용여부변경자ID
	 *
	 * @param tdmUseYnChgrId
	 *            TDM사용여부변경자ID
	 */
	public void setTdmUseYnChgrId(String tdmUseYnChgrId) {
		this.tdmUseYnChgrId = tdmUseYnChgrId;
	}

	/**
	 * 해지자ID
	 *
	 * @return 해지자ID
	 */
	public String getTermrId() {
		return termrId;
	}

	/**
	 * 해지자ID
	 *
	 * @param termrId
	 *            해지자ID
	 */
	public void setTermrId(String termrId) {
		this.termrId = termrId;
	}

	/**
	 * WDMTRUNK대역정보
	 *
	 * @return WDMTRUNK대역정보
	 */
	public String getWdmTrunkSubsInfo() {
		return wdmTrunkSubsInfo;
	}

	/**
	 * WDMTRUNK대역정보
	 *
	 * @param wdmTrunkSubsInfo
	 *            WDMTRUNK대역정보
	 */
	public void setWdmTrunkSubsInfo(String wdmTrunkSubsInfo) {
		this.wdmTrunkSubsInfo = wdmTrunkSubsInfo;
	}

	/**
	 * WDMTRUNK채널정보
	 *
	 * @return WDMTRUNK채널정보
	 */
	public String getWdmTrunkChnlInfo() {
		return wdmTrunkChnlInfo;
	}

	/**
	 * WDMTRUNK채널정보
	 *
	 * @param wdmTrunkChnlInfo
	 *            WDMTRUNK채널정보
	 */
	public void setWdmTrunkChnlInfo(String wdmTrunkChnlInfo) {
		this.wdmTrunkChnlInfo = wdmTrunkChnlInfo;
	}

	/**
	 * WDMTRUNK주파수값
	 *
	 * @return WDMTRUNK주파수값
	 */
	public Double getWdmTrunkFreqVal() {
		return wdmTrunkFreqVal;
	}

	/**
	 * WDMTRUNK주파수값
	 *
	 * @param wdmTrunkFreqVal
	 *            WDMTRUNK주파수값
	 */
	public void setWdmTrunkFreqVal(Double wdmTrunkFreqVal) {
		this.wdmTrunkFreqVal = wdmTrunkFreqVal;
	}

	/**
	 * WDMTRUNK방향정보
	 *
	 * @return WDMTRUNK방향정보
	 */
	public String getWdmTrunkDirnInfo() {
		return wdmTrunkDirnInfo;
	}

	/**
	 * WDMTRUNK방향정보
	 *
	 * @param wdmTrunkDirnInfo
	 *            WDMTRUNK방향정보
	 */
	public void setWdmTrunkDirnInfo(String wdmTrunkDirnInfo) {
		this.wdmTrunkDirnInfo = wdmTrunkDirnInfo;
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
	 * 팀조직ID
	 *
	 * @return 팀조직ID
	 */
	public String getTeamOrgId() {
		return teamOrgId;
	}

	/**
	 * 팀조직ID
	 *
	 * @param teamOrgId
	 *            팀조직ID
	 */
	public void setTeamOrgId(String teamOrgId) {
		this.teamOrgId = teamOrgId;
	}

	/**
	 * 링크입력일시
	 *
	 * @return 링크입력일시
	 */
	public String getLinkInsDtm() {
		return linkInsDtm;
	}

	/**
	 * 링크입력일시
	 *
	 * @param linkInsDtm
	 *            링크입력일시
	 */
	public void setLinkInsDtm(String linkInsDtm) {
		this.linkInsDtm = linkInsDtm;
	}

	/**
	 * 링크등록자정보
	 *
	 * @return 링크등록자정보
	 */
	public String getLinkRgstrInfo() {
		return linkRgstrInfo;
	}

	/**
	 * 링크등록자정보
	 *
	 * @param linkRgstrInfo
	 *            링크등록자정보
	 */
	public void setLinkRgstrInfo(String linkRgstrInfo) {
		this.linkRgstrInfo = linkRgstrInfo;
	}

	/**
	 * 작업완료여부
	 *
	 * @return 작업완료여부
	 */
	public String isOperFnshYn() {
		return operFnshYn;
	}

	/**
	 * 작업완료여부
	 *
	 * @param operFnshYn
	 *            작업완료여부
	 */
	public void setOperFnshYn(String operFnshYn) {
		this.operFnshYn = operFnshYn;
	}

	/**
	 * TSDN기간망회선번호
	 *
	 * @return TSDN기간망회선번호
	 */
	public String getTsdnBbntLineNum() {
		return tsdnBbntLineNum;
	}

	/**
	 * TSDN기간망회선번호
	 *
	 * @param tsdnBbntLineNum
	 *            TSDN기간망회선번호
	 */
	public void setTsdnBbntLineNum(String tsdnBbntLineNum) {
		this.tsdnBbntLineNum = tsdnBbntLineNum;
	}

	/**
	 * 예비TSDN기간망회선번호
	 *
	 * @return 예비TSDN기간망회선번호
	 */
	public String getRsvTsdnBbntLineNum() {
		return rsvTsdnBbntLineNum;
	}

	/**
	 * 예비TSDN기간망회선번호
	 *
	 * @param rsvTsdnBbntLineNum
	 *            예비TSDN기간망회선번호
	 */
	public void setRsvTsdnBbntLineNum(String rsvTsdnBbntLineNum) {
		this.rsvTsdnBbntLineNum = rsvTsdnBbntLineNum;
	}

	/**
	 * TSDN기간망정보수정일자
	 *
	 * @return TSDN기간망정보수정일자
	 */
	public String getTsdnPrdNetInfoUpdDt() {
		return tsdnPrdNetInfoUpdDt;
	}

	/**
	 * TSDN기간망정보수정일자
	 *
	 * @param tsdnPrdNetInfoUpdDt
	 *            TSDN기간망정보수정일자
	 */
	public void setTsdnPrdNetInfoUpdDt(String tsdnPrdNetInfoUpdDt) {
		this.tsdnPrdNetInfoUpdDt = tsdnPrdNetInfoUpdDt;
	}

	/**
	 * TANGO망번호
	 *
	 * @return TANGO망번호
	 */
	public String getTangoNetNum() {
		return tangoNetNum;
	}

	/**
	 * TANGO망번호
	 *
	 * @param tangoNetNum
	 *            TANGO망번호
	 */
	public void setTangoNetNum(String tangoNetNum) {
		this.tangoNetNum = tangoNetNum;
	}
}

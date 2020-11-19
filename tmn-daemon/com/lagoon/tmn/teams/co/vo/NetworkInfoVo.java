package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;

import com.lagoon.tmn.teams.co.AdamsCfg.LineStCd;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.AdamsCfg.TrmsNetTopoTypCd;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * 망 기본 VO (OIV10614)
 * 
 * <pre>
 * 	SqlUtil 클래스 이용하여 자동 생성
 * 	2019.08.21 불필요 필드 주석(필요시 사용)
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@FxTable(name = "OIV10614", comment = "전송망NETWORK기본")
@SuppressWarnings("serial")
public class NetworkInfoVo implements Serializable {

	@FxColumn(name = "NET_NUM", size = 12, comment = "망번호")
	private String netNum;

	@FxColumn(name = "NW_NM", size = 100, comment = "NETWORK명")
	private String nwNm;

	@FxColumn(name = "TRMS_NET_TOPO_TYP_CD", size = 4, comment = "전송망토폴로지유형코드")
	private String trmsNetTopoTypCd;

	@FxColumn(name = "TRMS_NET_USG_CD", size = 2, comment = "전송망용도코드")
	private String trmsNetUsgCd;

	@FxColumn(name = "MAX_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, nullable = true, comment = "최대전송망장비용량구분코드")
	private String maxTrmsNetEquipCapaClCd;

	@FxColumn(name = "LINE_ST_CD", size = 2, comment = "회선상태코드")
	private String lineStCd;

	@FxColumn(name = "DABL_MGMT_YN", size = 1, comment = "장애관리여부", defValue = "'N'")
	private boolean dablMgmtYn = false;

	@FxColumn(name = "TRMS_NET_LINE_SVC_CD", size = 4, nullable = true, comment = "전송망회선서비스코드")
	private String trmsNetLineSvcCd;

	// 트렁크 번호 (아담스 클라이언트에서 필요)
	private String trunkNum;
	// 망장애코드 (망 장애 생성시 필요)
	private String netDablCd;

	/*
	 * 불필요 필드 주석(필요시 사용)
	 * 
	 * @FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID") private
	 * String auditId;
	 * 
	 * @FxColumn(name = "TRMS_NET_MGMT_OWNR_CD", size = 2, comment =
	 * "전송망관리주체코드") private String trmsNetMgmtOwnrCd;
	 * 
	 * @FxColumn(name = "MIN_TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, nullable =
	 * true, comment = "최소전송망장비용량구분코드") private String minTrmsNetEquipCapaClCd;
	 * 
	 * @FxColumn(name = "SUP_TPO_CD", size = 6, nullable = true, comment =
	 * "상위국사코드") private String supTpoCd;
	 * 
	 * @FxColumn(name = "SUB_TPO_CD", size = 6, nullable = true, comment =
	 * "하위국사코드") private String subTpoCd;
	 * 
	 * @FxColumn(name = "LINE_RMK", size = 100, nullable = true, comment =
	 * "회선비고") private String lineRmk;
	 * 
	 * @FxColumn(name = "LINE_BIZR_CD", size = 4, nullable = true, comment =
	 * "회선사업자코드") private String lineBizrCd;
	 * 
	 * @FxColumn(name = "ETC_DESC", size = 200, nullable = true, comment =
	 * "기타설명") private String etcDesc;
	 * 
	 * @FxColumn(name = "OTHR_SYS_NET_NUM", size = 100, nullable = true, comment
	 * = "타시스템망번호") private String othrSysNetNum;
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
	 * @FxColumn(name = "RTYP_NET_SWTCHG_MTHD_CD", size = 2, nullable = true,
	 * comment = "링형망절체방식코드") private String rtypNetSwtchgMthdCd;
	 * 
	 * @FxColumn(name = "INS_PROC_ST_VAL", size = 1, nullable = true, comment =
	 * "입력진행상태값") private String insProcStVal;
	 * 
	 * @FxColumn(name = "SVC_DTM", size = 14, nullable = true, comment = "개통일시")
	 * private String svcDtm;
	 * 
	 * @FxColumn(name = "SKT_EMS_ID", size = 50, nullable = true, comment =
	 * "SKTEMSID") private String sktEmsId;
	 * 
	 * @FxColumn(name = "BF_NET_NUM", size = 10, nullable = true, comment =
	 * "이전망번호") private String bfNetNum;
	 * 
	 * @FxColumn(name = "TERM_DTM", size = 14, nullable = true, comment =
	 * "해지일시") private String termDtm;
	 * 
	 * @FxColumn(name = "RENT_BBNT_LINE_NUM", size = 32, nullable = true,
	 * comment = "임차기간망회선번호") private String rentBbntLineNum;
	 * 
	 * @FxColumn(name = "SUP_TPO_SYS_INFO", size = 40, nullable = true, comment
	 * = "상위국사시스템정보") private String supTpoSysInfo;
	 * 
	 * @FxColumn(name = "SUB_TPO_SYS_INFO", size = 40, nullable = true, comment
	 * = "하위국사시스템정보") private String subTpoSysInfo;
	 * 
	 * @FxColumn(name = "CNTR_ORG_ID", size = 10, nullable = true, comment =
	 * "센터조직ID") private String cntrOrgId;
	 * 
	 * @FxColumn(name = "TRMS_NET_MGMT_GR_CD", size = 5, nullable = true,
	 * comment = "전송망관리등급코드") private String trmsNetMgmtGrCd;
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
	 * @FxColumn(name = "VLAN_INFO", size = 100, nullable = true, comment =
	 * "VLAN정보") private String vlanInfo;
	 * 
	 * @FxColumn(name = "RT_RING_NUM", size = 10, nullable = true, comment =
	 * "RT링번호") private String rtRingNum;
	 * 
	 * @FxColumn(name = "TRUNK_IDLE_RT", size = 15, nullable = true, comment =
	 * "TRUNK유휴율") private Number trunkIdleRt;
	 * 
	 * @FxColumn(name = "TDM_USE_YN", size = 1, comment = "TDM사용여부", defValue =
	 * "'N'") private boolean tdmUseYn = false;
	 * 
	 * @FxColumn(name = "TDM_USE_YN_RSN_INFO", size = 500, nullable = true,
	 * comment = "TDM사용여부사유정보") private String tdmUseYnRsnInfo;
	 * 
	 * @FxColumn(name = "TDM_USE_YN_CHGR_ID", size = 15, nullable = true,
	 * comment = "TDM사용여부변경자ID") private String tdmUseYnChgrId;
	 * 
	 * @FxColumn(name = "TERMR_ID", size = 15, nullable = true, comment =
	 * "해지자ID") private String termrId;
	 * 
	 * @FxColumn(name = "WDM_TRUNK_SUBS_INFO", size = 50, nullable = true,
	 * comment = "WDMTRUNK대역정보") private String wdmTrunkSubsInfo;
	 * 
	 * @FxColumn(name = "WDM_TRUNK_CHNL_INFO", size = 50, nullable = true,
	 * comment = "WDMTRUNK채널정보") private String wdmTrunkChnlInfo;
	 * 
	 * @FxColumn(name = "WDM_TRUNK_FREQ_VAL", size = 15, nullable = true,
	 * comment = "WDMTRUNK주파수값") private Number wdmTrunkFreqVal;
	 * 
	 * @FxColumn(name = "WDM_TRUNK_DIRN_INFO", size = 200, nullable = true,
	 * comment = "WDMTRUNK방향정보") private String wdmTrunkDirnInfo;
	 * 
	 * @FxColumn(name = "EQUIP_MDL_CD", size = 10, nullable = true, comment =
	 * "장비모델코드") private String equipMdlCd;
	 * 
	 * @FxColumn(name = "TEAM_ORG_ID", size = 10, nullable = true, comment =
	 * "팀조직ID") private String teamOrgId;
	 * 
	 * @FxColumn(name = "LINK_INS_DTM", size = 14, nullable = true, comment =
	 * "링크입력일시") private String linkInsDtm;
	 * 
	 * @FxColumn(name = "LINK_RGSTR_INFO", size = 20, nullable = true, comment =
	 * "링크등록자정보") private String linkRgstrInfo;
	 */
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

	public boolean isRingType() {
		if (!trmsNetTopoTypCd.equals(TrmsNetTopoTypCd.TRUNK)) {
			return true;
		}
		return false;
	}

	public boolean isTrunkType() {
		if (trmsNetTopoTypCd.equals(TrmsNetTopoTypCd.TRUNK)) {
			return true;
		}
		return false;
	}

	public boolean isNotApplyType(String netType) {
		if (netType.equals(TeamsCode.NET_TYPE_RING)) {
			return !isRingType();
		}
		return !isTrunkType();
	}

	/**
	 * 회선 상태코드 값이 해지(08) 는 true 반환
	 * 
	 * <pre>
	 *  	실제 레거시 프로젝트 쿼리는 008008 만 제거
	 *      해지만 true 반환
	 * </pre>
	 * 
	 * @return boolean
	 */
	public boolean isNetworkSkip() {
		return (lineStCd.equals(LineStCd.CLOSE));
	}

	public String getTrunkNum() {
		return trunkNum;
	}

	public void setTrunkNum(String trunkNum) {
		this.trunkNum = trunkNum;
	}

	public String getNetDablCd() {
		return netDablCd;
	}

	public void setNetDablCd(String netDablCd) {
		this.netDablCd = netDablCd;
	}

}
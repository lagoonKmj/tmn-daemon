package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.09.02 10:59
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "ONM10100", comment = "망작업내역")
public class ONM10100 implements Serializable {

	public ONM10100() {
	}

	@FxColumn(name = "NET_OP_NUM", size = 15, comment = "망작업번호")
	private String netOpNum;

	/*
	 * 불필요 필드 주석(필요시 사용)
	 * 
	 * @FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID") private
	 * String auditId;
	 * 
	 * @FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue =
	 * "sysdate") private Timestamp auditDtm;
	 * 
	 * @FxColumn(name = "NET_OP_TYP_CD", size = 3, comment = "망작업유형코드") private
	 * String netOpTypCd;
	 * 
	 * @FxColumn(name = "DABL_OPER_CL_CD", size = 1, comment = "장애작업구분코드")
	 * private String dablOperClCd;
	 * 
	 * @FxColumn(name = "OPER_INFLU_AREA_DESC", size = 2000, nullable = true,
	 * comment = "작업영향지역설명") private String operInfluAreaDesc;
	 * 
	 * @FxColumn(name = "NET_OP_ST_CD", size = 1, comment = "망작업상태코드") private
	 * String netOpStCd;
	 * 
	 * @FxColumn(name = "NET_CL_CD", size = 2, comment = "망구분코드") private String
	 * netClCd;
	 * 
	 * @FxColumn(name = "OPER_STA_DTM", size = 14, nullable = true, comment =
	 * "작업시작일시") private String operStaDtm;
	 * 
	 * @FxColumn(name = "EXPT_OPER_FNSH_DTM", size = 14, nullable = true,
	 * comment = "예상작업완료일시") private String exptOperFnshDtm;
	 * 
	 * @FxColumn(name = "OPER_FNSH_DTM", size = 14, nullable = true, comment =
	 * "작업완료일시") private String operFnshDtm;
	 * 
	 * @FxColumn(name = "OPER_RSN_CTT", size = 2000, nullable = true, comment =
	 * "작업사유내용") private String operRsnCtt;
	 * 
	 * @FxColumn(name = "OPER_CTT", size = 2000, nullable = true, comment =
	 * "작업내용") private String operCtt;
	 * 
	 * @FxColumn(name = "SVC_INFLU_YN", size = 1, comment = "서비스영향여부", defValue
	 * = "'N'") private boolean svcInfluYn = false;
	 * 
	 * @FxColumn(name = "SVC_INFLU_TYP_CD", size = 4, nullable = true, comment =
	 * "서비스영향유형코드") private String svcInfluTypCd;
	 * 
	 * @FxColumn(name = "VOC_CNT", size = 12, nullable = true, comment =
	 * "VOC건수") private long vocCnt;
	 * 
	 * @FxColumn(name = "BF_NET_OP_NUM", size = 15, nullable = true, comment =
	 * "이전망작업번호") private String bfNetOpNum;
	 * 
	 * @FxColumn(name = "OPERTR_ID", size = 15, nullable = true, comment =
	 * "작업자ID") private String opertrId;
	 * 
	 * @FxColumn(name = "OPERTR_NM", size = 40, nullable = true, comment =
	 * "작업자명") private String opertrNm;
	 * 
	 * @FxColumn(name = "OPERTR_PHON_NUM", size = 12, nullable = true, comment =
	 * "작업자전화번호") private String opertrPhonNum;
	 * 
	 * @FxColumn(name = "NET_DABL_OPER_ACT_TYP_CD", size = 4, nullable = true,
	 * comment = "망장애작업조치유형코드") private String netDablOperActTypCd;
	 * 
	 * @FxColumn(name = "OPER_ACT_CTT", size = 1000, nullable = true, comment =
	 * "작업조치내용") private String operActCtt;
	 * 
	 * @FxColumn(name = "FWUP_ACT_NEED_CTT", size = 2000, nullable = true,
	 * comment = "후속조치필요내용") private String fwupActNeedCtt;
	 * 
	 * @FxColumn(name = "RGST_DTM", size = 14, nullable = true, comment =
	 * "등록일시") private String rgstDtm;
	 * 
	 * @FxColumn(name = "RGSTR_ID", size = 15, nullable = true, comment =
	 * "등록자ID") private String rgstrId;
	 * 
	 * @FxColumn(name = "RGSTR_NM", size = 100, nullable = true, comment =
	 * "등록자명") private String rgstrNm;
	 * 
	 * @FxColumn(name = "RGSTR_POST_CTT", size = 80, nullable = true, comment =
	 * "등록자소속내용") private String rgstrPostCtt;
	 * 
	 * @FxColumn(name = "RGSTR_PHON_NUM", size = 12, nullable = true, comment =
	 * "등록자전화번호") private String rgstrPhonNum;
	 * 
	 * @FxColumn(name = "TPO_CD", size = 6, nullable = true, comment = "국사코드")
	 * private String tpoCd;
	 * 
	 * @FxColumn(name = "NMS_CD", size = 12, nullable = true, comment = "NMS코드")
	 * private String nmsCd;
	 * 
	 * @FxColumn(name = "NET_OP_DABL_TYP_CD", size = 4, nullable = true, comment
	 * = "망작업장애유형코드") private String netOpDablTypCd;
	 * 
	 * @FxColumn(name = "ALM_SET_ITM_CTT", size = 1000, nullable = true, comment
	 * = "알람설정항목내용") private String almSetItmCtt;
	 * 
	 * @FxColumn(name = "ALM_RSN_DTL_CTT", size = 1000, nullable = true, comment
	 * = "알람사유상세내용") private String almRsnDtlCtt;
	 * 
	 * @FxColumn(name = "NET_OP_SVC_TYP_CD", size = 4, nullable = true, comment
	 * = "망작업서비스종류코드") private String netOpSvcTypCd;
	 * 
	 * @FxColumn(name = "IF_CL_CD", size = 4, nullable = true, comment =
	 * "전문구분코드") private String ifClCd;
	 * 
	 * @FxColumn(name = "OP_OPER_FNSH_DTM", size = 0, nullable = true, comment =
	 * "처리작업완료일시") private Timestamp opOperFnshDtm;
	 * 
	 * @FxColumn(name = "DABL_SRC_CD", size = 12, nullable = true, comment =
	 * "장애원인코드") private String dablSrcCd;
	 * 
	 * @FxColumn(name = "DABL_CONS_ITM_CTT", size = 2000, nullable = true,
	 * comment = "장애구성품내용") private String dablConsItmCtt;
	 * 
	 * @FxColumn(name = "DABL_DTL_SRC_CTT", size = 2000, nullable = true,
	 * comment = "장애세부원인내용") private String dablDtlSrcCtt;
	 * 
	 * @FxColumn(name = "OP_RGST_DTM", size = 0, nullable = true, comment =
	 * "처리등록일시") private Timestamp opRgstDtm;
	 * 
	 * @FxColumn(name = "OP_RGSTR_ID", size = 15, nullable = true, comment =
	 * "처리등록자ID") private String opRgstrId;
	 * 
	 * @FxColumn(name = "OP_RGSTR_NM", size = 100, nullable = true, comment =
	 * "처리등록자명") private String opRgstrNm;
	 * 
	 * @FxColumn(name = "OP_RGSTR_TEAM_ORG_NM", size = 80, nullable = true,
	 * comment = "처리등록자팀조직명") private String opRgstrTeamOrgNm;
	 * 
	 * @FxColumn(name = "OP_RGSTR_PHON_NUM", size = 12, nullable = true, comment
	 * = "처리등록자전화번호") private String opRgstrPhonNum;
	 * 
	 * @FxColumn(name = "EMAIL_SND_YN", size = 1, comment = "EMAIL발송여부",
	 * defValue = "'N'") private boolean emailSndYn = false;
	 * 
	 * @FxColumn(name = "SWING_LNKG_YN", size = 1, comment = "SWING연동여부",
	 * defValue = "'N'") private boolean swingLnkgYn = false;
	 */

	/**
	 * ONM10100 테이블 정보
	 * 
	 */
	// 장비ID
	private String equipId;

	// 포트번호 (현재 수집되고 있지 않아 사용 못함)
	private String portNum;

	/**
	 * 망작업번호
	 * 
	 * @return 망작업번호
	 */
	public String getNetOpNum() {
		return netOpNum;
	}

	/**
	 * 망작업번호
	 *
	 * @param netOpNum
	 *            망작업번호
	 */
	public void setNetOpNum(String netOpNum) {
		this.netOpNum = netOpNum;
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

}

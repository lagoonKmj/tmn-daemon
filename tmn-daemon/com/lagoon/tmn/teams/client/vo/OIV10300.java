package com.lagoon.tmn.teams.client.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2017.11.29 14:03
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OIV10300", comment = "국사기본")
public class OIV10300 implements Serializable {

	public OIV10300() {
	}

	@FxColumn(name = "ADDR_ID", size = 15, comment = "ADDR_ID")
	private String addrId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "AUDIT_DTM")
	private Timestamp auditDtm;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "AUDIT_ID")
	private String auditId;

	@FxColumn(name = "EXLINE_CHRGR_NM", size = 30, nullable = true, comment = "EXLINE_CHRGR_NM")
	private String exlineChrgrNm;

	@FxColumn(name = "EXLINE_CHRGR_PHON_NUM", size = 12, nullable = true, comment = "EXLINE_CHRGR_PHON_NUM")
	private String exlineChrgrPhonNum;

	@FxColumn(name = "FORGN_ADDR", size = 500, nullable = true, comment = "FORGN_ADDR")
	private String forgnAddr;

	@FxColumn(name = "FORGN_CNTC_NUM", size = 50, nullable = true, comment = "FORGN_CNTC_NUM")
	private String forgnCntcNum;

	@FxColumn(name = "JURIS_INFO_CNTR_CL_NUM", size = 2, nullable = true, comment = "JURIS_INFO_CNTR_CL_NUM")
	private String jurisInfoCntrClNum;

	@FxColumn(name = "KT_OPER_GRP_CD", size = 10, nullable = true, comment = "KT_OPER_GRP_CD")
	private String ktOperGrpCd;

	@FxColumn(name = "KT_TPO_CD", size = 10, nullable = true, comment = "KT_TPO_CD")
	private String ktTpoCd;

	@FxColumn(name = "MGMT_TPO_CD", size = 6, nullable = true, comment = "MGMT_TPO_CD")
	private String mgmtTpoCd;

	@FxColumn(name = "NMS_ID", size = 20, nullable = true, comment = "NMS_ID")
	private String nmsId;

	@FxColumn(name = "OPER_GRP_ID", size = 10, nullable = true, comment = "OPER_GRP_ID")
	private String operGrpId;

	@FxColumn(name = "RENT_TPO_YN", size = 1, nullable = true, comment = "RENT_TPO_YN")
	private String rentTpoYn;

	@FxColumn(name = "TPO_CD", size = 6, comment = "TPO_CD")
	private String tpoCd;

	@FxColumn(name = "TPO_CNTC_NUM", size = 12, nullable = true, comment = "TPO_CNTC_NUM")
	private String tpoCntcNum;

	@FxColumn(name = "TPO_DSRCT_AREA_CL_CD", size = 6, nullable = true, comment = "TPO_DSRCT_AREA_CL_CD")
	private String tpoDsrctAreaClCd;

	@FxColumn(name = "TPO_NM", size = 60, nullable = true, comment = "TPO_NM")
	private String tpoNm;

	@FxColumn(name = "TPO_TYP_CD", size = 2, comment = "TPO_TYP_CD")
	private String tpoTypCd;

	@FxColumn(name = "TPO_USE_YN", size = 1, nullable = true, comment = "TPO_USE_YN")
	private String tpoUseYn;

	/**
	 * ADDR_ID
	 * 
	 * @return ADDR_ID
	 */
	public String getAddrId() {
		return addrId;
	}

	/**
	 * ADDR_ID
	 *
	 * @param addrId
	 *            ADDR_ID
	 */
	public void setAddrId(String addrId) {
		this.addrId = addrId;
	}

	/**
	 * AUDIT_DTM
	 * 
	 * @return AUDIT_DTM
	 */
	public Timestamp getAuditDtm() {
		return auditDtm;
	}

	/**
	 * AUDIT_DTM
	 *
	 * @param auditDtm
	 *            AUDIT_DTM
	 */
	public void setAuditDtm(Timestamp auditDtm) {
		this.auditDtm = auditDtm;
	}

	/**
	 * AUDIT_ID
	 * 
	 * @return AUDIT_ID
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * AUDIT_ID
	 *
	 * @param auditId
	 *            AUDIT_ID
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * EXLINE_CHRGR_NM
	 * 
	 * @return EXLINE_CHRGR_NM
	 */
	public String getExlineChrgrNm() {
		return exlineChrgrNm;
	}

	/**
	 * EXLINE_CHRGR_NM
	 *
	 * @param exlineChrgrNm
	 *            EXLINE_CHRGR_NM
	 */
	public void setExlineChrgrNm(String exlineChrgrNm) {
		this.exlineChrgrNm = exlineChrgrNm;
	}

	/**
	 * EXLINE_CHRGR_PHON_NUM
	 * 
	 * @return EXLINE_CHRGR_PHON_NUM
	 */
	public String getExlineChrgrPhonNum() {
		return exlineChrgrPhonNum;
	}

	/**
	 * EXLINE_CHRGR_PHON_NUM
	 *
	 * @param exlineChrgrPhonNum
	 *            EXLINE_CHRGR_PHON_NUM
	 */
	public void setExlineChrgrPhonNum(String exlineChrgrPhonNum) {
		this.exlineChrgrPhonNum = exlineChrgrPhonNum;
	}

	/**
	 * FORGN_ADDR
	 * 
	 * @return FORGN_ADDR
	 */
	public String getForgnAddr() {
		return forgnAddr;
	}

	/**
	 * FORGN_ADDR
	 *
	 * @param forgnAddr
	 *            FORGN_ADDR
	 */
	public void setForgnAddr(String forgnAddr) {
		this.forgnAddr = forgnAddr;
	}

	/**
	 * FORGN_CNTC_NUM
	 * 
	 * @return FORGN_CNTC_NUM
	 */
	public String getForgnCntcNum() {
		return forgnCntcNum;
	}

	/**
	 * FORGN_CNTC_NUM
	 *
	 * @param forgnCntcNum
	 *            FORGN_CNTC_NUM
	 */
	public void setForgnCntcNum(String forgnCntcNum) {
		this.forgnCntcNum = forgnCntcNum;
	}

	/**
	 * JURIS_INFO_CNTR_CL_NUM
	 * 
	 * @return JURIS_INFO_CNTR_CL_NUM
	 */
	public String getJurisInfoCntrClNum() {
		return jurisInfoCntrClNum;
	}

	/**
	 * JURIS_INFO_CNTR_CL_NUM
	 *
	 * @param jurisInfoCntrClNum
	 *            JURIS_INFO_CNTR_CL_NUM
	 */
	public void setJurisInfoCntrClNum(String jurisInfoCntrClNum) {
		this.jurisInfoCntrClNum = jurisInfoCntrClNum;
	}

	/**
	 * KT_OPER_GRP_CD
	 * 
	 * @return KT_OPER_GRP_CD
	 */
	public String getKtOperGrpCd() {
		return ktOperGrpCd;
	}

	/**
	 * KT_OPER_GRP_CD
	 *
	 * @param ktOperGrpCd
	 *            KT_OPER_GRP_CD
	 */
	public void setKtOperGrpCd(String ktOperGrpCd) {
		this.ktOperGrpCd = ktOperGrpCd;
	}

	/**
	 * KT_TPO_CD
	 * 
	 * @return KT_TPO_CD
	 */
	public String getKtTpoCd() {
		return ktTpoCd;
	}

	/**
	 * KT_TPO_CD
	 *
	 * @param ktTpoCd
	 *            KT_TPO_CD
	 */
	public void setKtTpoCd(String ktTpoCd) {
		this.ktTpoCd = ktTpoCd;
	}

	/**
	 * MGMT_TPO_CD
	 * 
	 * @return MGMT_TPO_CD
	 */
	public String getMgmtTpoCd() {
		return mgmtTpoCd;
	}

	/**
	 * MGMT_TPO_CD
	 *
	 * @param mgmtTpoCd
	 *            MGMT_TPO_CD
	 */
	public void setMgmtTpoCd(String mgmtTpoCd) {
		this.mgmtTpoCd = mgmtTpoCd;
	}

	/**
	 * NMS_ID
	 * 
	 * @return NMS_ID
	 */
	public String getNmsId() {
		return nmsId;
	}

	/**
	 * NMS_ID
	 *
	 * @param nmsId
	 *            NMS_ID
	 */
	public void setNmsId(String nmsId) {
		this.nmsId = nmsId;
	}

	/**
	 * OPER_GRP_ID
	 * 
	 * @return OPER_GRP_ID
	 */
	public String getOperGrpId() {
		return operGrpId;
	}

	/**
	 * OPER_GRP_ID
	 *
	 * @param operGrpId
	 *            OPER_GRP_ID
	 */
	public void setOperGrpId(String operGrpId) {
		this.operGrpId = operGrpId;
	}

	/**
	 * RENT_TPO_YN
	 * 
	 * @return RENT_TPO_YN
	 */
	public String isRentTpoYn() {
		return rentTpoYn;
	}

	/**
	 * RENT_TPO_YN
	 *
	 * @param rentTpoYn
	 *            RENT_TPO_YN
	 */
	public void setRentTpoYn(String rentTpoYn) {
		this.rentTpoYn = rentTpoYn;
	}

	/**
	 * TPO_CD
	 * 
	 * @return TPO_CD
	 */
	public String getTpoCd() {
		return tpoCd;
	}

	/**
	 * TPO_CD
	 *
	 * @param tpoCd
	 *            TPO_CD
	 */
	public void setTpoCd(String tpoCd) {
		this.tpoCd = tpoCd;
	}

	/**
	 * TPO_CNTC_NUM
	 * 
	 * @return TPO_CNTC_NUM
	 */
	public String getTpoCntcNum() {
		return tpoCntcNum;
	}

	/**
	 * TPO_CNTC_NUM
	 *
	 * @param tpoCntcNum
	 *            TPO_CNTC_NUM
	 */
	public void setTpoCntcNum(String tpoCntcNum) {
		this.tpoCntcNum = tpoCntcNum;
	}

	/**
	 * TPO_DSRCT_AREA_CL_CD
	 * 
	 * @return TPO_DSRCT_AREA_CL_CD
	 */
	public String getTpoDsrctAreaClCd() {
		return tpoDsrctAreaClCd;
	}

	/**
	 * TPO_DSRCT_AREA_CL_CD
	 *
	 * @param tpoDsrctAreaClCd
	 *            TPO_DSRCT_AREA_CL_CD
	 */
	public void setTpoDsrctAreaClCd(String tpoDsrctAreaClCd) {
		this.tpoDsrctAreaClCd = tpoDsrctAreaClCd;
	}

	/**
	 * TPO_NM
	 * 
	 * @return TPO_NM
	 */
	public String getTpoNm() {
		return tpoNm;
	}

	/**
	 * TPO_NM
	 *
	 * @param tpoNm
	 *            TPO_NM
	 */
	public void setTpoNm(String tpoNm) {
		this.tpoNm = tpoNm;
	}

	/**
	 * TPO_TYP_CD
	 * 
	 * @return TPO_TYP_CD
	 */
	public String getTpoTypCd() {
		return tpoTypCd;
	}

	/**
	 * TPO_TYP_CD
	 *
	 * @param tpoTypCd
	 *            TPO_TYP_CD
	 */
	public void setTpoTypCd(String tpoTypCd) {
		this.tpoTypCd = tpoTypCd;
	}

	/**
	 * TPO_USE_YN
	 * 
	 * @return TPO_USE_YN
	 */
	public String isTpoUseYn() {
		return tpoUseYn;
	}

	/**
	 * TPO_USE_YN
	 *
	 * @param tpoUseYn
	 *            TPO_USE_YN
	 */
	public void setTpoUseYn(String tpoUseYn) {
		this.tpoUseYn = tpoUseYn;
	}
}

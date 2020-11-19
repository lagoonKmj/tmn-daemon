package com.lagoon.tmn.teams.co.dao;


/**
 * File : adams_OMN33812.xml<br>
 * 
 * @since 20190823102904
 * @author subkjh
 *
 */

public class AdamsOMN33812Qid {

	/** 쿼리 모임 화일명. adams_OMN33812.xml */
	public static final String QUERY_XML_FILE = "adams_OMN33812.xml";

	public AdamsOMN33812Qid() {
	}

	/**
	 * para : $getTrmsNetEquipMsgMgmtNum(), $getEquipMdlCd(),
	 * $getCmprCharStrVal(), $getDablGrCd(), $getDablCd(), $isDablAplyYn(),
	 * $isEmcyDablYn(), $isNetDablAplyYn(), $getNetDablCd(), $getNetDablNm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OMN33812 (<br>
	 * TRMS_NET_EQUIP_MSG_MGMT_NUM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EQUIP_MDL_CD<br>
	 * , CMPR_CHAR_STR_VAL<br>
	 * , DABL_GR_CD<br>
	 * , DABL_CD<br>
	 * , DABL_APLY_YN<br>
	 * , EMCY_DABL_YN<br>
	 * , NET_DABL_APLY_YN<br>
	 * , NET_DABL_CD<br>
	 * , NET_DABL_NM<br>
	 * ) values (<br>
	 * $getTrmsNetEquipMsgMgmtNum()<br>
	 * , 'ADAMS-TMN'<br>
	 * , sysdate<br>
	 * , $getEquipMdlCd()<br>
	 * , $getCmprCharStrVal()<br>
	 * , $getDablGrCd()<br>
	 * , $getDablCd()<br>
	 * , $isDablAplyYn()<br>
	 * , $isEmcyDablYn()<br>
	 * , $isNetDablAplyYn()<br>
	 * , $getNetDablCd()<br>
	 * , $getNetDablNm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OMN33812 = "INSERT_OMN33812";

	/**
	 * para : <br>
	 * result : RESULT_LONG=java.lang.Long<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select OMN33812_S1.nextval<br>
	 * from dual<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NEW_OMN33812_ID = "SELECT_NEW_OMN33812_ID";

	/**
	 * para : #equipMdlCd$equipMfactCd<br>
	 * result : RESULT_OMN33812=com.skb.adams.teams.fxms.dbo.OMN33812<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.TRMS_NET_EQUIP_MSG_MGMT_NUM<br>
	 * , a.EQUIP_MDL_CD<br>
	 * , a.CMPR_CHAR_STR_VAL<br>
	 * , a.DABL_GR_CD<br>
	 * , a.DABL_CD<br>
	 * , a.DABL_APLY_YN<br>
	 * , a.EMCY_DABL_YN<br>
	 * , a.NET_DABL_APLY_YN<br>
	 * , a.NET_DABL_CD<br>
	 * , a.NET_DABL_NM<br>
	 * from OMN33812 a<br>
	 * , OIV10200 b<br>
	 * where a.EQUIP_MDL_CD = b.EQUIP_MDL_CD<br>
	 * <br>
	 * and a.EQUIP_MDL_CD in ( #equipMdlCd )<br>
	 * <br>
	 * and b.EQUIP_MFACT_CD = $equipMfactCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN33812__BY_equipMfactCd_equipMdlCd = "SELECT_OMN33812__BY_equipMfactCd_equipMdlCd";

}
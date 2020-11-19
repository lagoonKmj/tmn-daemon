package com.lagoon.tmn.teams.co.dao;

/**
 * File : adams_OMN33811.xml<br>
 * 
 * @since 20190918133142
 * @author subkjh
 *
 */

public class AdamsOMN33811Qid {

	/** 쿼리 모임 화일명. adams_OMN33811.xml */
	public static final String QUERY_XML_FILE = "adams_OMN33811.xml";

	public AdamsOMN33811Qid() {
	}

	/**
	 * para : #equipMdlCd<br>
	 * result : RESULT_OMN33811Ext=com.skb.adams.teams.fxms.dbo.OMN33811Ext<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.TRMS_EQUIP_DABL_NUM<br>
	 * , b.EQUIP_MDL_CD<br>
	 * , b.ALL_MSG_CTT<br>
	 * , b.RLSE_DTM<br>
	 * , a.EQUIP_ID<br>
	 * , a.DABL_GR_CD<br>
	 * , a.DABL_CD<br>
	 * , a.SCARD_DESC<br>
	 * , a.PORT_DESC<br>
	 * , a.DABL_OCCR_LOC_DESC<br>
	 * , a.EMS_ID<br>
	 * , a.EMS_ALM_VAL<br>
	 * from OMN33811 a<br>
	 * , OMN33810 b<br>
	 * where a.TRMS_EQUIP_DABL_NUM = b.TRMS_EQUIP_DABL_NUM<br>
	 * and b.EQUIP_MDL_CD in ( #equipMdlCd )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN33811 = "SELECT_OMN33811";

}
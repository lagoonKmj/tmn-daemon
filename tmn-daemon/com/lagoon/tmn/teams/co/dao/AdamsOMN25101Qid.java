package com.lagoon.tmn.teams.co.dao;

/**
* File : adams_OMN25101.xml<br>
* @since 20191022133946
* @author subkjh 
*
*/


public class AdamsOMN25101Qid { 

/** 쿼리 모임 화일명. adams_OMN25101.xml*/
public static final String QUERY_XML_FILE = "adams_OMN25101.xml";

public AdamsOMN25101Qid() { 
} 
/**
* para : $getEquipId(), $getMsrtPortInfo(), $getSendRcvClCd(), $getMntrCyclCd(), $getOplvlMinVal(), $getOplvlMaxVal(), $getOplvlAvgVal(), $getOplvlCurrVal(), $getClctStaDtm(), $getClctEndDtm(), $getClctDtm(), $isMntrYn(), $getPortAlsNm()<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OMN25101 (<br>			EQUIP_ID<br>			, MSRT_PORT_INFO<br>			, AUDIT_ID<br>			, AUDIT_DTM<br>			, SEND_RCV_CL_CD<br>			, MNTR_CYCL_CD<br>			, OPLVL_MIN_VAL<br>			, OPLVL_MAX_VAL<br>			, OPLVL_AVG_VAL<br>			, OPLVL_CURR_VAL<br>			, CLCT_STA_DTM<br>			, CLCT_END_DTM<br>			, CLCT_DTM<br>			, MNTR_YN<br>			, PORT_ALS_NM<br>		) values (<br>			$getEquipId()<br>			, $getMsrtPortInfo()<br>			, 'ADAMSTMN'<br>			, sysdate<br>			, $getSendRcvClCd()<br>			, $getMntrCyclCd()<br>			, $getOplvlMinVal()<br>			, $getOplvlMaxVal()<br>			, $getOplvlAvgVal()<br>			, $getOplvlCurrVal()<br>			, $getClctStaDtm()<br>			, $getClctEndDtm()<br>			, $getClctDtm()<br>			, $isMntrYn()<br>			, $getPortAlsNm()<br>		)<br><br> <br>
*/
public final String INSERT_OMN25101 = "INSERT_OMN25101";

/**
* para : $getClctDtm(), $getEquipId(), $getMsrtPortInfo(), $getAuditId(), $getAuditDtm(), $getSendRcvClCd(), $getMntrCyclCd(), $getOplvlMinVal(), $getOplvlMaxVal(), $getOplvlAvgVal(), $getOplvlCurrVal(), $getClctStaDtm(), $getClctEndDtm(), $isMntrYn(), $getPortAlsNm(), $getOlineUsgCd(), $getCurrSpanLossVal(), $getBasSpanLossVal()<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OMN25102 (<br>			CLCT_DTM<br>			, EQUIP_ID<br>			, MSRT_PORT_INFO<br>			, AUDIT_ID<br>			, AUDIT_DTM<br>			, SEND_RCV_CL_CD<br>			, MNTR_CYCL_CD<br>			, OPLVL_MIN_VAL<br>			, OPLVL_MAX_VAL<br>			, OPLVL_AVG_VAL<br>			, OPLVL_CURR_VAL<br>			, CLCT_STA_DTM<br>			, CLCT_END_DTM<br>			, MNTR_YN<br>			, PORT_ALS_NM<br>			, OLINE_USG_CD<br>			, CURR_SPAN_LOSS_VAL<br>			, BAS_SPAN_LOSS_VAL<br>		) values (<br>			$getClctDtm()<br>			, $getEquipId()<br>			, $getMsrtPortInfo()<br>			, $getAuditId()<br>			, $getAuditDtm()<br>			, $getSendRcvClCd()<br>			, $getMntrCyclCd()<br>			, $getOplvlMinVal()<br>			, $getOplvlMaxVal()<br>			, $getOplvlAvgVal()<br>			, $getOplvlCurrVal()<br>			, $getClctStaDtm()<br>			, $getClctEndDtm()<br>			, $isMntrYn()<br>			, $getPortAlsNm()<br>			, $getOlineUsgCd()<br>			, $getCurrSpanLossVal()<br>			, $getBasSpanLossVal()<br>		)<br><br> <br>
*/
public final String INSERT_OMN25102 = "INSERT_OMN25102";

}
package com.lagoon.tmn.teams.co.dao;

/**
* File : adams_OMN25112.xml<br>
* @since 20191023135128
* @author subkjh 
*
*/


public class AdamsOMN25112Qid { 

/** 쿼리 모임 화일명. adams_OMN25112.xml*/
public static final String QUERY_XML_FILE = "adams_OMN25112.xml";

public AdamsOMN25112Qid() { 
} 
/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>delete <br>		from 	omn25112<br>		where	clct_dtm < trunc(sysdate - 7)<br><br> <br>
*/
public final String DELETE_OMN25112__By_term = "DELETE_OMN25112__By_term";

/**
* para : $clctDtm<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>delete <br>		from 	omn25113<br>		where	mntr_dt = $clctDtm<br><br> <br>
*/
public final String DELETE_OMN25113__By_day = "DELETE_OMN25113__By_day";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>delete <br>		from 	omn25113<br>		where	mntr_dt < to_char(sysdate - 100, 'YYYYMMDD')<br><br> <br>
*/
public final String DELETE_OMN25113__By_term = "DELETE_OMN25113__By_term";

/**
* para : $getClctDtm(), $getEquipId(), $getScardNm(), $getPortNm(), $getTmprVal()<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OMN25112 (<br>			CLCT_DTM<br>			, EQUIP_ID<br>			, SCARD_NM<br>			, PORT_NM<br>			, AUDIT_ID<br>			, AUDIT_DTM<br>			, TMPR_VAL<br>		) values (<br>			$getClctDtm()<br>			, $getEquipId()<br>			, $getScardNm()<br>			, $getPortNm()<br>			, 'ADAMSTMN'<br>			, sysdate<br>			, $getTmprVal()<br>		)<br><br> <br>
*/
public final String INSERT_OMN25112 = "INSERT_OMN25112";

/**
* para : $clctDtm, $clctDtmStart, $clctDtmEnd<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into omn25113 ( <br>				mntr_dt  <br>				, equip_id <br>				, scard_nm<br>				, port_nm <br>				, tmpr_cnt    <br>				, min_tmpr_val  <br>				, max_tmpr_val  <br>				, avg_tmpr_val  <br>				, audit_id <br>				, audit_dtm         <br>		)<br>		select	$clctDtm<br>				, equip_id<br>				, scard_nm<br>				, port_nm<br>				, count(1)					as TMPR_CNT<br>				, min(tmpr_val)				as MIN_TMPR_VAL<br>				, max(tmpr_val)				as MAX_TMPR_VAL<br>				, avg(tmpr_val)				as AVG_TMPR_VAL<br>				, 'ADAMSTMN'<br>				, sysdate<br>		from	OMN25112<br>		where	clct_dtm >= $clctDtmStart<br>		and		clct_dtm <= $clctDtmEnd<br>		group by<br>				equip_id<br>				, scard_nm<br>				, port_nm<br><br> <br>
*/
public final String INSERT_OMN25113 = "INSERT_OMN25113";

}
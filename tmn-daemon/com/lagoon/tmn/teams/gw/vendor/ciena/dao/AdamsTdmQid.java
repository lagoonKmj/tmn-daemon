package com.lagoon.tmn.teams.gw.vendor.ciena.dao;

/**
* File : adams_tdm.xml<br>
* @since 20190822095626
* @author subkjh 
*
*/


public class AdamsTdmQid { 

/** 쿼리 모임 화일명. adams_tdm.xml*/
public static final String QUERY_XML_FILE = "adams_tdm.xml";

public AdamsTdmQid() { 
} 
/**
* para : $equipId<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>delete<br>		from	OIV28160<br>		where	EQUIP_ID = $equipId<br><br> <br>
*/
public final String DELETE_OIV28160__BY_equipId = "DELETE_OIV28160__BY_equipId";

/**
* para : $getEquipId(), $getAdrcEquipPortDesc(), $getBdrcEquipPortDesc(), $getSgnlTypNm(), $getCrsClVal(), $getCrsRmk(), $getClctDtm()<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OIV28160 (<br>			EQUIP_ID<br>			, ADRC_EQUIP_PORT_DESC<br>			, AUDIT_ID<br>			, AUDIT_DTM<br>			, BDRC_EQUIP_PORT_DESC<br>			, SGNL_TYP_NM<br>			, CRS_CL_VAL<br>			, CRS_RMK<br>			, CLCT_DTM<br>		) values (<br>			$getEquipId()<br>			, $getAdrcEquipPortDesc()<br>			, 'ADAMS-TMN'<br>			, sysdate<br>			, $getBdrcEquipPortDesc()<br>			, $getSgnlTypNm()<br>			, $getCrsClVal()<br>			, $getCrsRmk()<br>			, $getClctDtm()<br>		)<br><br> <br>
*/
public final String INSERT_OIV28160 = "INSERT_OIV28160";

/**
* para : <br>
* result : RESULT_EQUIP_DCN=com.skb.adams.teams.co.vo.EquipDcn<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	b.cons_port_num			as conf_port<br>				, b.dabl_port_num		as alarm_port<br>				, b.snmp_port_num       as snmp_port  <br>			    , c.equip_id			as equip_id<br>			    , c.equip_tid_val 		as equip_tid<br>			    , c.equip_ip_addr 		as ip_addr<br>			    , b.login_pwd 			as passwd<br>			    , b.snmp_read_cmnty_nm	as snmp_community<br>			    , b.snmp_ver_num  		as snmp_ver		  <br>			    , b.login_id			as user_id<br>		from 	oiv28150 a<br>			    , oiv10104 b<br>			    , oiv10100 c<br>		where 	a.equip_id = b.equip_id<br>		and 	b.equip_id = c.equip_id<br>		and		c.del_yn = 'N'<br>		<br>		union<br>		<br>		select 	b.cons_port_num			as conf_port<br>				, b.dabl_port_num		as alarm_port<br>				, b.snmp_port_num       as snmp_port  <br>			    , c.equip_id			as equip_id<br>			    , c.equip_tid_val 		as equip_tid<br>			    , c.equip_ip_addr 		as ip_addr<br>			    , b.login_pwd 			as passwd<br>			    , b.snmp_read_cmnty_nm	as snmp_community<br>			    , b.snmp_ver_num  		as snmp_ver		  <br>			    , b.login_id			as user_id<br>		from 	oiv28150 a<br>			    , oiv10104 b<br>			    , oiv10100 c<br>		where 	a.parg_equip_id = b.equip_id<br>		and 	b.equip_id = c.equip_id<br>		and		c.del_yn = 'N'<br><br> <br>
*/
public final String SELECT_OIV28150_DCN = "SELECT_OIV28150_DCN";

}
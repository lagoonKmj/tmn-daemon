package com.lagoon.tmn.teams.co.dao;

/**
* File : test_conf.xml<br>
* @since 20190813160012
* @author subkjh 
*
*/


public class TestConfQid { 

/** 쿼리 모임 화일명. test_conf.xml*/
public static final String QUERY_XML_FILE = "test_conf.xml";

public TestConfQid() { 
} 
/**
* para : <br>
* result : RESULT_DETECTED_NE_VO=com.skb.adams.teams.co.vo.DetectedNeVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select	<br>        a.EQUIP_ID<br>        , a.EQUIP_MDL_CD<br>			  , a.equip_ip_addr as IP_ADDR<br>			  , b.equip_mdl_nm as MODEL<br>			, ''  as NET_NAME<br>			, a.equip_sys_nm as SYSNAME<br>			, a.equip_tid_val as TID<br>			, '' as TYPE<br>			, '' as VENDOR<br>			, a.sw_ver_info as VER_FW<br>			, '' as VER_HW<br>			, '' as VER_SW<br>      , a.*<br>		from	oiv10100 a<br>          , oiv10200 b<br>    where a.equip_id = '000000342407'<br>    and   a.equip_mdl_cd = b.equip_mdl_cd<br><br> <br>
*/
public final String SELECT_DETECTED_NE_VO = "SELECT_DETECTED_NE_VO";

/**
* para : <br>
* result : RESULT_DETECTED_PORT_VO=com.skb.adams.teams.co.vo.DetectedPortVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select a.port_desc            as port_descr<br>      , a.port_speed_cd       as port_speed_cd  <br>      , a1.equip_cons_itm_nm  as slot_descr<br>      , '' as port_state<br>      , '' as port_type      <br>from oiv10400 a<br>    left join oiv10210 a1 on a1.equip_id = a.equip_id and a.equip_cons_itm_id = a1.equip_cons_itm_id<br>where a.equip_id = '000000342407'<br><br> <br>
*/
public final String SELECT_DETECTED_PORT_VO = "SELECT_DETECTED_PORT_VO";

/**
* para : <br>
* result : RESULT_DETECTED_SLOT_VO=com.skb.adams.teams.co.vo.DetectedSlotVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select	<br>				a.rep_ip_addr 				as IP<br>				, a.PORT_CNT					as PORT_CNT<br>				, a.EQUIP_CONS_ITM_DESC			as SLOT_DESCR<br>				, b.cons_itm_mdl_nm				as SLOT_MODEL<br>				, a.CONS_ITM_NUM				as SLOT_NO<br>				, a.MFACT_SER_NUM				as SLOT_SERIAL_NO<br>				, a.EQUIP_CONS_ITM_ST_VAL		as SLOT_STATE<br>				, b.EQUIP_CONS_ITM_TYP_CD		as SLOT_TYPE<br>				, ''							as VER<br>		from	oiv10210 a<br>				left join oiv10209 b on a.equip_cons_itm_cd = b.equip_cons_itm_cd<br>		where 	a.equip_id = '000000342407'<br><br> <br>
*/
public final String SELECT_DETECTED_SLOT_VO = "SELECT_DETECTED_SLOT_VO";

}
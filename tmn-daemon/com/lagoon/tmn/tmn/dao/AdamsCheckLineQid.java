package com.lagoon.tmn.tmn.dao;

/**
* File : adams_check_line.xml<br>
* @since 20191118162218
* @author subkjh 
*
*/


public class AdamsCheckLineQid { 

/** 쿼리 모임 화일명. adams_check_line.xml*/
public static final String QUERY_XML_FILE = "adams_check_line.xml";

public AdamsCheckLineQid() { 
} 
/**
* para : <br>
* result : RESULT_OIV10611=com.skb.adams.tmn.vo.OIV10611Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a.*<br>		from 	oiv10611 a<br>		where	exists ( select 1 from oiv10612 t where a.line_num = t.line_num and t.wdm_trunk_id is not null )<br><br> <br>
*/
public final String SELECT_LINE_BASE__By_WdmTrunk = "SELECT_LINE_BASE__By_WdmTrunk";

/**
* para : <br>
* result : RESULT_OIV10612=com.skb.adams.tmn.vo.OIV10612Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	b.*<br>		from 	oiv10611 a<br>				, oiv10612 b<br>		where	exists ( select 1 from oiv10612 t where a.line_num = t.line_num and t.wdm_trunk_id is not null )<br>		and		a.line_num = b.line_num<br><br> <br>
*/
public final String SELECT_LINE_PATH__By_WdmTrunk = "SELECT_LINE_PATH__By_WdmTrunk";

/**
* para : <br>
* result : RESULT_OIV10614=com.skb.adams.tmn.vo.OIV10614Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a.*<br>				, b.etc_attr_val1		as trms_net_topo_typ_grp_nm<br>		from 	oiv10614 a<br>				, oco20101 b<br>		where	b.comm_cd = 'TRMS_NET_TOPO_TYP_CD'<br>		and		b.comm_cd_val = a.trms_net_topo_typ_cd<br>		and		exists ( select 1 from oiv10615 t where a.net_num = t.net_num and t.wdm_trunk_id is not null )<br><br> <br>
*/
public final String SELECT_NETWORK_BASE__By_WdmTrunk = "SELECT_NETWORK_BASE__By_WdmTrunk";

/**
* para : $netTopoType<br>
* result : RESULT_OIV10614=com.skb.adams.tmn.vo.OIV10614Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a.*<br>				, b.etc_attr_val1		as trms_net_topo_typ_grp_nm<br>		from 	oiv10614 a<br>				, oco20101 b<br>		where	b.comm_cd = 'TRMS_NET_TOPO_TYP_CD'<br>		and		b.comm_cd_val = a.trms_net_topo_typ_cd<br>		and		b.etc_attr_val1 = $netTopoType<br><br> <br>
*/
public final String SELECT_NETWORK_BASE__By_netTopoType = "SELECT_NETWORK_BASE__By_netTopoType";

/**
* para : <br>
* result : RESULT_OIV10615=com.skb.adams.tmn.vo.OIV10615Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	b.*<br>		from 	oiv10614 a<br>				, oiv10615 b<br>		where	exists ( select 1 from oiv10615 t where a.net_num = t.net_num and t.wdm_trunk_id is not null )<br>		and		a.net_num = b.net_num<br><br> <br>
*/
public final String SELECT_NETWORK_PATH__By_WdmTrunk = "SELECT_NETWORK_PATH__By_WdmTrunk";

/**
* para : $netTopoType<br>
* result : RESULT_OIV10615=com.skb.adams.tmn.vo.OIV10615Ext<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	c.*<br>		from 	oiv10614 a<br>				, oco20101 b<br>				, oiv10615 c<br>		where	b.comm_cd = 'TRMS_NET_TOPO_TYP_CD'<br>		and		b.comm_cd_val = a.trms_net_topo_typ_cd<br>		and		b.etc_attr_val1 = $netTopoType<br>		and		a.net_num = c.net_num<br>		order	by c.net_num<br>				, c.net_link_seq1<br>				, c.net_link_seq2<br><br> <br>
*/
public final String SELECT_NETWORK_PATH__By_netTopoType = "SELECT_NETWORK_PATH__By_netTopoType";

}
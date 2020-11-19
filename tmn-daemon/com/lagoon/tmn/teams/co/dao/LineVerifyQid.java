package com.lagoon.tmn.teams.co.dao;

/**
* File : line_verify.xml<br>
* @since 20190730135238
* @author subkjh 
*
*/


public class LineVerifyQid { 

/** 쿼리 모임 화일명. line_verify.xml*/
public static final String QUERY_XML_FILE = "line_verify.xml";

public LineVerifyQid() { 
} 
/**
* para : <br>
* result : RESULT_LINE_VERIFY_VO=com.skb.adams.teams.co.vo.LineVerifyVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a.line_num, a.line_nm, a.sup_tpo_cd, a.sub_tpo_cd, a.trms_net_dirn_cd<br>				, a1.tpo_nm as sup_tpo_nm<br>				, a2.tpo_nm as sub_tpo_nm<br>		from 	oiv10611 a<br>					left join oiv10300 a1 on a1.tpo_cd = a.sup_tpo_cd<br>					left join oiv10300 a2 on a2.tpo_cd = a.sub_tpo_cd<br>		where 	a.trms_net_mgmt_ownr_cd = '03'<br>		and 	a.line_st_cd = '15'<br>		and 	a.co_line_vrf_yn = 'Y'<br><br> <br>
*/
public final String SELECT_LINE_INFO = "SELECT_LINE_INFO";

/**
* para : <br>
* result : RESULT_LINE_VERIFY_PATH_VO=com.skb.adams.teams.co.vo.LineVerifyPathVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select a.line_num,  b.net_link_seq1, b.net_link_seq2, b.equip_id, b.equip_a_port_info, b.equip_b_port_info, b.a_port_info, b.b_port_info<br>		      , b1.equip_id as mst_equip_id, b1.tpo_cd, b1.del_yn<br>		      , b2.equip_mdl_nm<br>		      , b3.comm_cd_val_nm as equip_mdl_typ_nm<br>		      , b4.tpo_nm<br>		      , b5.port_desc as mst_a_port_desc<br>		      , b6.port_desc as mst_b_port_desc<br>		      , b7.tpo_cd as mgmt_tpo_cd<br>		      , b7.tpo_nm as mgmt_tpo_nm<br>		from oiv10611 a<br>		    , oiv10612 b<br>		      left join oiv10100 b1 on b1.equip_id = b.equip_id<br>		      left join oiv10200 b2 on b2.equip_mdl_cd = b1.equip_mdl_cd<br>		      left join OCO20101 b3 on b3.comm_cd = 'EQUIP_MDL_TYP_CD' and b3.comm_cd_val = b2.equip_mdl_typ_cd<br>		      left join oiv10300 b4 on b4.tpo_cd = b1.tpo_cd<br>		      left join oiv10400 b5 on b5.equip_id = b.equip_id and b5.port_desc = b.a_port_info<br>		      left join oiv10400 b6 on b6.equip_id = b.equip_id and b6.port_desc = b.b_port_info<br>		      left join oiv10300 b7 on b7.tpo_cd = b4.mgmt_tpo_cd<br>		where 	a.trms_net_mgmt_ownr_cd = '03'<br>		and 	a.line_st_cd = '15'<br>		and 	a.co_line_vrf_yn = 'Y'<br>		and 	a.line_num = b.line_num<br>		order by a.line_num<br>				, b.net_link_seq1<br>				, b.net_link_seq2<br><br> <br>
*/
public final String SELECT_LINE_PATH = "SELECT_LINE_PATH";

/**
* para : <br>
* result : RESULT_LINE_VERIFY_TPO_VO=com.skb.adams.teams.co.vo.LineVerifyTpoVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a.trms_net_mgmt_obj_num<br>      			, a1.tpo_cd<br>      			, a1.tpo_nm<br>		from  	oiv10613 a<br>					left join oiv10300 a1 on a1.tpo_cd = a.trms_net_mgmt_tpo_cd<br>		where 	a.trms_net_mgmt_obj_typ_cd = 'L'<br>		and		a.trms_net_mgmt_ownr_cd = '03'<br>		and		a1.tpo_use_yn = 'Y'<br>		order by <br>				a.trms_net_mgmt_obj_num<br>				, a.trms_net_mgmt_tpo_seq<br><br> <br>
*/
public final String SELECT_LINE_TPO = "SELECT_LINE_TPO";

}
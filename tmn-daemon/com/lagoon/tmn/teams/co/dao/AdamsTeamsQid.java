package com.lagoon.tmn.teams.co.dao;

/**
 * File : adams_teams.xml<br>
 * 
 * @since 20191119175351
 * @author subkjh
 *
 */

public class AdamsTeamsQid {

	/** 쿼리 모임 화일명. adams_teams.xml */
	public static final String QUERY_XML_FILE = "adams_teams.xml";

	public AdamsTeamsQid() {
	}

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_CARD = "DELETE_CARD";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_PORT = "DELETE_PORT";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_CARD = "INSERT_CARD";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_PORT = "INSERT_PORT";

	/**
	 * para : #equipMdlCd<br>
	 * result : RESULT_DCN_MO=com.skb.adams.teams.fxms.mo.DcnMo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select to_number(replace(a2.equip_id, 'S', '99')) as MO_NO<br>
	 * , a2.equip_nm as MO_NAME<br>
	 * , 'NE' as MO_CLASS<br>
	 * <br>
	 * , a2.equip_id <br>
	 * <br>
	 * , a.dcn_num<br>
	 * , a.dcn_nm <br>
	 * , a.mst_dcn_ip_addr<br>
	 * , a.sub_dcn_ip_addr<br>
	 * , a.dcn_typ_cd<br>
	 * , a1.comm_cd_val_nm as DCN_EMS_TYP<br>
	 * <br>
	 * , decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0)<br>
	 * as ALARM_PORT<br>
	 * , decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0)<br>
	 * as CONF_PORT<br>
	 * <br>
	 * , b.login_id as USER_ID<br>
	 * , b.login_pwd as PASSWD<br>
	 * , b.snmp_ver_num as SNMP_VER<br>
	 * , b.snmp_read_cmnty_nm as SNMP_COMMUNITY<br>
	 * <br>
	 * from OIV10618 a ' DCN기본 ' <br>
	 * left join oco20101 a1 on a1.comm_cd = 'DCN_EMS_TYP_CD' and a1.comm_cd_val
	 * = a.dcn_ems_typ_cd<br>
	 * left join oiv10100 a2 on a2.equip_id = a.dcn_cot_equip_id <br>
	 * , OIV10619 b ' DCN포트기본 '<br>
	 * where a.dcn_num = b.dcn_num <br>
	 * and a.dcn_typ_cd = 'D'<br>
	 * and b.use_yn = 'Y' <br>
	 * and b.dcn_port_usg_val = '10000000'<br>
	 * <br>
	 * and a2.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_DCN_COT__BY_equipMdlCd = "SELECT_DCN_COT__BY_equipMdlCd";

	/**
	 * para : $dcnEmsTyp<br>
	 * result : RESULT_DCN_MO=com.skb.adams.teams.fxms.mo.DcnMo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.dcn_num as MO_NO<br>
	 * , a.dcn_nm as MO_NAME<br>
	 * , 'DCN' as MO_CLASS<br>
	 * <br>
	 * , a.dcn_num<br>
	 * , a.dcn_nm <br>
	 * , a.mst_dcn_ip_addr<br>
	 * , a.sub_dcn_ip_addr<br>
	 * , a.dcn_typ_cd<br>
	 * , a1.comm_cd_val_nm as DCN_EMS_TYP<br>
	 * <br>
	 * , decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0)<br>
	 * as ALARM_PORT<br>
	 * , decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0)<br>
	 * as CONF_PORT<br>
	 * <br>
	 * , b.login_id as USER_ID<br>
	 * , b.login_pwd as PASSWD<br>
	 * , b.snmp_ver_num as SNMP_VER<br>
	 * , b.snmp_read_cmnty_nm as SNMP_COMMUNITY<br>
	 * <br>
	 * from OIV10618 a ' DCN기본 ' <br>
	 * left join oco20101 a1 on a1.comm_cd = 'DCN_EMS_TYP_CD' and a1.comm_cd_val
	 * = a.dcn_ems_typ_cd <br>
	 * , OIV10619 b ' DCN포트기본 '<br>
	 * where a.dcn_num = b.dcn_num <br>
	 * and a.dcn_typ_cd = 'E'<br>
	 * and b.use_yn = 'Y'<br>
	 * <br>
	 * and a1.comm_cd_val_nm = $dcnEmsTyp<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_DCN_MO__BY_dcnEmsTyp = "SELECT_DCN_MO__BY_dcnEmsTyp";

	/**
	 * para : <br>
	 * result : RESULT_LINE_INFO_VO=com.skb.adams.teams.co.vo.LineInfoVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.cust_nm,<br>
	 * NVL(a.cust_num, 0) as cust_num,<br>
	 * a.dabl_mgmt_yn,<br>
	 * a.line_nm,<br>
	 * a.line_num,<br>
	 * a.line_st_cd,<br>
	 * a.max_trms_net_equip_capa_cl_cd,<br>
	 * a.sale_chrgr_id,<br>
	 * a.sub_tpo_cd,<br>
	 * a.sup_tpo_cd,<br>
	 * a.svc_num,<br>
	 * a.trms_net_line_svc_cd,<br>
	 * a.trms_net_line_usg_cd,<br>
	 * a.trms_net_mgmt_gr_cd,<br>
	 * a1.tpo_nm as sup_tpo_nm,<br>
	 * a2.tpo_nm as sub_tpo_nm,<br>
	 * a3.comm_cd_val_nm as trms_net_mgmt_gr_nm<br>
	 * FROM <br>
	 * oiv10611 a<br>
	 * LEFT JOIN <br>
	 * oiv10300 a1 ON a1.tpo_cd = a.sup_tpo_cd<br>
	 * LEFT JOIN <br>
	 * oiv10300 a2 ON a2.tpo_cd = a.sub_tpo_cd<br>
	 * LEFT JOIN <br>
	 * oco20101 a3 ON a3.comm_cd_val = a.trms_net_mgmt_gr_cd AND a3.comm_cd =
	 * 'TRMS_NET_MGMT_GR_CD'<br>
	 * WHERE<br>
	 * a.trms_net_mgmt_ownr_cd = '03'<br>
	 * AND a.co_line_vrf_yn = 'Y'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_LINE_INFO_LIST = "SELECT_LINE_INFO_LIST";

	/**
	 * para : $lineNum$ringNum$equipId<br>
	 * result : RESULT_LINE_VO=com.skb.adams.teams.co.vo.LineVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.line_num,<br>
	 * a.net_link_seq1,<br>
	 * a.net_link_seq2,<br>
	 * a.ring_num,<br>
	 * a.equip_a_port_info,<br>
	 * a.equip_b_port_info,<br>
	 * a.ring_dirn_num,<br>
	 * a.a_port_info,<br>
	 * a.a_chnl_info,<br>
	 * a.b_port_info,<br>
	 * a.b_chnl_info,<br>
	 * a.equip_id,<br>
	 * a.trunk_num<br>
	 * FROM <br>
	 * oiv10612 a<br>
	 * INNER JOIN <br>
	 * oiv10611 b ON a.line_num = b.line_num AND b.trms_net_mgmt_ownr_cd = '03'<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * <br>
	 * AND a.line_num = $lineNum<br>
	 * <br>
	 * AND a.ring_num = $ringNum<br>
	 * <br>
	 * AND a.equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_LINE_LIST__BY_lineNum_ringNum_equipId = "SELECT_LINE_LIST__BY_lineNum_ringNum_equipId";

	/**
	 * para : <br>
	 * result : RESULT_NETWORK_INFO_VO=com.skb.adams.teams.co.vo.NetworkInfoVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.net_num,<br>
	 * a.nw_nm,<br>
	 * a.trms_net_topo_typ_cd,<br>
	 * a.trms_net_usg_cd,<br>
	 * a.max_trms_net_equip_capa_cl_cd,<br>
	 * a.line_st_cd,<br>
	 * a.dabl_mgmt_yn,<br>
	 * a.trms_net_line_svc_cd<br>
	 * FROM <br>
	 * oiv10614 a<br>
	 * WHERE<br>
	 * a.trms_net_mgmt_ownr_cd = '03'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NETWORK_INFO_LIST = "SELECT_NETWORK_INFO_LIST";

	/**
	 * para : $netNum, $netNum<br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * aa.type, aa.net_num, aa.equip_id, aa.b_port_info as port_info<br>
	 * FROM<br>
	 * (<br>
	 * SELECT<br>
	 * 'src' as type, a.net_num, b.net_link_seq1, c.equip_id, b.b_port_info<br>
	 * FROM<br>
	 * oiv10614 a, oiv10615 b, oiv10100 c, oiv10200 d<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * AND a.net_num = b.net_num<br>
	 * AND b.equip_id = c.equip_id<br>
	 * AND c.equip_mdl_cd = d.equip_mdl_cd<br>
	 * AND d.equip_mdl_typ_cd NOT IN ( '608' )<br>
	 * AND a.net_num = $netNum<br>
	 * UNION<br>
	 * SELECT<br>
	 * 'dst' as type, a.net_num, b.net_link_seq1, c.equip_id, b.a_port_info<br>
	 * FROM<br>
	 * oiv10614 a, oiv10615 b, OIV10100 c, oiv10200 d<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * AND a.net_num = b.net_num<br>
	 * AND b.equip_id = c.equip_id<br>
	 * AND c.equip_mdl_cd = d.equip_mdl_cd<br>
	 * AND d.equip_mdl_typ_cd NOT IN ( '608' )<br>
	 * AND a.net_num = $netNum<br>
	 * ) aa<br>
	 * ORDER BY<br>
	 * aa.type DESC, aa.net_link_seq1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NETWORK_LINK_LIST__BY_netNum = "SELECT_NETWORK_LINK_LIST__BY_netNum";

	/**
	 * para : $netNum$ringNum$equipId<br>
	 * result : RESULT_NETWORK_VO=com.skb.adams.teams.co.vo.NetworkVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.a_chnl_info,<br>
	 * a.a_port_info,<br>
	 * a.b_chnl_info,<br>
	 * a.b_port_info,<br>
	 * a.equip_a_port_info,<br>
	 * a.equip_b_port_info,<br>
	 * a.equip_id,<br>
	 * a.net_link_seq1,<br>
	 * a.net_link_seq2,<br>
	 * a.net_num,<br>
	 * a.ring_cot_rt_info,<br>
	 * a.ring_dirn_num,<br>
	 * a.ring_num,<br>
	 * a.trunk_num,<br>
	 * b.trms_net_topo_typ_cd<br>
	 * FROM <br>
	 * oiv10615 a<br>
	 * INNER JOIN <br>
	 * oiv10614 b ON a.net_num = b.net_num AND b.trms_net_mgmt_ownr_cd = '03'<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * <br>
	 * and a.net_num = $netNum<br>
	 * <br>
	 * and a.ring_num = $ringNum<br>
	 * <br>
	 * and a.equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NETWORK_LIST__BY_netNum_ringNum_equipId = "SELECT_NETWORK_LIST__BY_netNum_ringNum_equipId";

	/**
	 * para : <br>
	 * result : RESULT_EQUIP_CONS_ITM_ID=java.lang.String<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * select<br>
	 * OIV10210.nextval from dual<br>
	 * <br>
	 */
	public final String SELECT_NEW_EQUIP_CONS_ITM_ID = "SELECT_NEW_EQUIP_CONS_ITM_ID";

	/**
	 * para : <br>
	 * result : RESULT_EQUIP_PORT_ID=java.lang.String<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * select<br>
	 * OIV10210.nextval from dual<br>
	 * <br>
	 */
	public final String SELECT_NEW_EQUIP_PORT_ID = "SELECT_NEW_EQUIP_PORT_ID";

	/**
	 * para : #equipMdlCd<br>
	 * result : RESULT_NE_MO=com.skb.adams.teams.fxms.mo.EquipMo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select b.equip_id<br>
	 * , to_number(replace(b.equip_id, 'S', '99')) as MO_NO <br>
	 * , b.equip_nm as MO_NAME <br>
	 * , b.equip_tid_val<br>
	 * , b.equip_mdl_cd <br>
	 * , b.equip_ip_addr <br>
	 * <br>
	 * , to_number(replace(c.equip_id, 'S', '99')) as UPPER_MO_NO <br>
	 * <br>
	 * , 0 as DCN_NUM <br>
	 * <br>
	 * from OIV28101 a<br>
	 * , oiv10100 b<br>
	 * , oiv10100 c<br>
	 * where a.equip_id = b.equip_id<br>
	 * and a.cot_equip_id = c.equip_id<br>
	 * and b.del_yn = 'N'<br>
	 * and c.del_yn = 'N'<br>
	 * and a.equip_id != a.cot_equip_id<br>
	 * <br>
	 * and b.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NE_LIST__BY_Cot_equipMdlCd = "SELECT_NE_LIST__BY_Cot_equipMdlCd";

	/**
	 * para : #equipMdlCd$dcnEmsTyp$dcnNum<br>
	 * result : RESULT_NE_MO=com.skb.adams.teams.fxms.mo.EquipMo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_id<br>
	 * , to_number(replace(a.equip_id, 'S', '99')) as MO_NO <br>
	 * , a.equip_nm as MO_NAME<br>
	 * , 0 as UPPER_MO_NO <br>
	 * , a.equip_tid_val<br>
	 * , a.equip_mdl_cd <br>
	 * , a.equip_ip_addr<br>
	 * , nvl(b.dcn_num, 0) as DCN_NUM <br>
	 * , b2.comm_cd_val_nm as DCN_EMS_TYP<br>
	 * , a.tpo_cd<br>
	 * , a1.tpo_nm<br>
	 * , a2.equip_mdl_nm<br>
	 * from oiv10100 a<br>
	 * left join oiv10300 a1 on a1.tpo_cd = a.tpo_cd<br>
	 * left join oiv10200 a2 on a2.equip_mdl_cd = a.equip_mdl_cd <br>
	 * , oiv10011 b<br>
	 * left join oiv10618 b1 on b1.dcn_num = b.dcn_num<br>
	 * left join oco20101 b2 on b2.comm_cd = 'DCN_EMS_TYP_CD' and b2.comm_cd_val
	 * = b1.dcn_ems_typ_cd<br>
	 * where a.equip_id = b.equip_id<br>
	 * and a.del_yn = 'N'<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * and b2.comm_cd_val_nm = $dcnEmsTyp<br>
	 * <br>
	 * and b.dcn_num = $dcnNum<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NE_LIST__BY_equipMdlCd_dcnEmsTyp_dcnNum = "SELECT_NE_LIST__BY_equipMdlCd_dcnEmsTyp_dcnNum";

	/**
	 * para : <br>
	 * result : RESULT_PORT_MO=com.skb.adams.teams.fxms.mo.PortMo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.equip_id, <br>
	 * a.equip_port_id, <br>
	 * a.port_desc <br>
	 * FROM <br>
	 * oiv10400 a <br>
	 * WHERE <br>
	 * a.equip_id IN (SELECT aa.equip_id FROM oiv10100 aa INNER JOIN oiv10011 bb
	 * ON aa.equip_id = bb.equip_id AND aa.del_yn = 'N')<br>
	 * AND a.port_desc is not null<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_PORT_LIST = "SELECT_PORT_LIST";

	/**
	 * para : #equipMdlCd<br>
	 * result : RESULT_TRMS_NET_ALCD=com.skb.adams.teams.co.vo.TrmsNetAlcdVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.trms_net_equip_msg_mgmt_num,<br>
	 * a.equip_mdl_cd,<br>
	 * a.cmpr_char_str_val,<br>
	 * a.dabl_gr_cd,<br>
	 * a.dabl_cd,<br>
	 * a.dabl_aply_yn,<br>
	 * a.emcy_dabl_yn,<br>
	 * a.net_dabl_aply_yn,<br>
	 * a.net_dabl_cd,<br>
	 * a.net_dabl_nm,<br>
	 * nvl(b.auto_dabl_rlse_sec_tms, 0) as auto_dabl_rlse_sec_tms<br>
	 * FROM <br>
	 * omn33812 a<br>
	 * LEFT JOIN<br>
	 * omn30203 b ON a.dabl_cd = b.dabl_cd<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_TRMS_NET_ALCD = "SELECT_TRMS_NET_ALCD";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_CARD = "UPDATE_CARD";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_EQUIP_VER = "UPDATE_EQUIP_VER";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_PORT = "UPDATE_PORT";

}
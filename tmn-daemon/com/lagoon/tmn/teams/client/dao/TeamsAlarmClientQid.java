package com.lagoon.tmn.teams.client.dao;

/**
 * File : teams_alarm_client.xml<br>
 * 
 * @since 20191204095912
 * @author subkjh
 *
 */

public class TeamsAlarmClientQid {

	/** 쿼리 모임 화일명. teams_alarm_client.xml */
	public static final String QUERY_XML_FILE = "teams_alarm_client.xml";

	public TeamsAlarmClientQid() {
	}

	/**
	 * para : <br>
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
	 * <br>
	 * <br>
	 */
	public final String SELECT_LINE_LIST = "SELECT_LINE_LIST";

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
	 * para : <br>
	 * result : RESULT_OIV10200=com.skb.adams.teams.fxms.dbo.OIV10200<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * DISTINCT<br>
	 * b.equip_mdl_cd, <br>
	 * b.equip_mdl_nm<br>
	 * FROM <br>
	 * oiv10100 a<br>
	 * LEFT JOIN <br>
	 * oiv10200 b ON b.equip_mdl_cd = a.equip_mdl_cd <br>
	 * WHERE<br>
	 * a.del_yn = 'N'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10200 = "SELECT_OIV10200";

	/**
	 * para : <br>
	 * result : RESULT_OIV10300=com.skb.adams.teams.client.vo.OIV10300<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * DISTINCT<br>
	 * b.tpo_cd,<br>
	 * b.tpo_nm<br>
	 * FROM <br>
	 * oiv10100 a<br>
	 * LEFT JOIN <br>
	 * oiv10300 b ON b.tpo_cd = a.tpo_cd<br>
	 * WHERE<br>
	 * a.del_yn = 'N'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10300 = "SELECT_OIV10300";

}
package com.lagoon.tmn.teams.co.dao;

/**
 * File : alarm.xml<br>
 * 
 * @since 20191217175558
 * @author subkjh
 *
 */

public class AlarmQid {

	/** 쿼리 모임 화일명. alarm.xml */
	public static final String QUERY_XML_FILE = "alarm.xml";

	public AlarmQid() {
	}

	/**
	 * para : #tableName, $trmsEquipDablNum<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * DELETE <br>
	 * FROM <br>
	 * #tableName<br>
	 * WHERE <br>
	 * trms_equip_dabl_num = $trmsEquipDablNum<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_ALARM__BY_trmsEquipDablNum = "DELETE_ALARM__BY_trmsEquipDablNum";

	/**
	 * para : <br>
	 * result : RESULT_ALARM_VO=com.skb.adams.teams.fxms.dbo.OMN33810<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * b.trms_equip_dabl_num, <br>
	 * b.dabl_gr_cd, <br>
	 * b.dabl_cd, <br>
	 * b.dabl_msg_ctt, <br>
	 * b.occr_dtm, <br>
	 * b.occr_rcv_dtm, <br>
	 * b.equip_id, <br>
	 * b.equip_nm, <br>
	 * b.equip_tid_val, <br>
	 * b.equip_ip_addr, <br>
	 * b.equip_mdl_cd, <br>
	 * b.tpo_cd, <br>
	 * b.scard_desc, <br>
	 * b.port_desc, <br>
	 * b.dabl_occr_loc_desc, <br>
	 * b.all_msg_ctt, <br>
	 * b.trms_net_equip_msg_mgmt_num, <br>
	 * b.cmpr_char_str_val, <br>
	 * b.oper_dabl_yn, <br>
	 * b.emcy_dabl_yn, <br>
	 * b.dmg_line_cnt, <br>
	 * b.dabl_dup_cnt, <br>
	 * b.ems_id, <br>
	 * b.ems_alm_val, <br>
	 * b.rlse_dtm, <br>
	 * b.rlse_rcv_dtm, <br>
	 * b.dabl_send_yn, <br>
	 * b.dabl_send_dtm, <br>
	 * b.dabl_send_rslt_val, <br>
	 * b.dabl_rlse_send_yn, <br>
	 * b.dabl_rlse_send_dtm, <br>
	 * b.dabl_rlse_send_rslt_val<br>
	 * FROM <br>
	 * omn33811 a<br>
	 * INNER JOIN <br>
	 * omn33810 b ON a.trms_equip_dabl_num = b.trms_equip_dabl_num<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_ALARM_LIST = "SELECT_ALARM_LIST";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.*<br>
	 * FROM<br>
	 * (<br>
	 * SELECT<br>
	 * a.parg_nm,<br>
	 * a.equip_id,<br>
	 * TO_CHAR(TO_DATE(b.clct_dtm, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS')
	 * clct_dtm,<br>
	 * nvl(b.adrc_equip_port_desc, '미정의') AS adrc_equip_port_desc,<br>
	 * nvl(b.bdrc_equip_port_desc, '미정의') AS bdrc_equip_port_desc,<br>
	 * a.parg_equip_id AS parg_equip_id,<br>
	 * nvl(c.adrc_equip_port_desc, '미정의') AS parg_adrc_equip_port_desc,<br>
	 * nvl(c.bdrc_equip_port_desc, '미정의') AS parg_bdrc_equip_port_desc,<br>
	 * CASE<br>
	 * WHEN b.bdrc_equip_port_desc = c.bdrc_equip_port_desc <br>
	 * THEN 'Y'<br>
	 * ELSE 'N'<br>
	 * END AS status<br>
	 * FROM<br>
	 * oiv28150 a,<br>
	 * oiv28160 b,<br>
	 * oiv28160 c<br>
	 * WHERE<br>
	 * a.equip_id = b.equip_id<br>
	 * AND a.parg_equip_id = c.equip_id (+)<br>
	 * AND b.adrc_equip_port_desc = c.adrc_equip_port_desc (+)<br>
	 * UNION<br>
	 * SELECT<br>
	 * a.parg_nm,<br>
	 * a.equip_id,<br>
	 * TO_CHAR(TO_DATE(b.clct_dtm, 'YYYYMMDDHH24MISS'), 'YYYY/MM/DD HH24:MI:SS')
	 * clct_dtm,<br>
	 * nvl(c.adrc_equip_port_desc, '미정의') AS adrc_equip_port_desc,<br>
	 * nvl(c.bdrc_equip_port_desc, '미정의') AS bdrc_equip_port_desc,<br>
	 * a.parg_equip_id AS parg_equip_id,<br>
	 * nvl(b.adrc_equip_port_desc, '미정의') AS parg_adrc_equip_port_desc,<br>
	 * nvl(b.bdrc_equip_port_desc, '미정의') AS parg_bdrc_equip_port_desc,<br>
	 * CASE<br>
	 * WHEN b.bdrc_equip_port_desc = c.bdrc_equip_port_desc <br>
	 * THEN 'Y'<br>
	 * ELSE 'N'<br>
	 * END AS status<br>
	 * FROM<br>
	 * oiv28150 a,<br>
	 * oiv28160 b,<br>
	 * oiv28160 c<br>
	 * WHERE<br>
	 * a.parg_equip_id = b.equip_id<br>
	 * AND a.equip_id = c.equip_id (+)<br>
	 * AND b.adrc_equip_port_desc = c.adrc_equip_port_desc (+)<br>
	 * ) a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * AND status = 'Y'<br>
	 * AND ROWNUM = 1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_CRS_DISACCORD_COUNT = "SELECT_CRS_DISACCORD_COUNT";

	/**
	 * para : <br>
	 * result : RESULT_ALARM_VO=com.skb.adams.teams.fxms.dbo.OMN33810<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.trms_equip_dabl_num, <br>
	 * a.dabl_gr_cd, <br>
	 * a.dabl_cd, <br>
	 * a.dabl_msg_ctt, <br>
	 * a.occr_dtm, <br>
	 * a.occr_rcv_dtm, <br>
	 * a.equip_id, <br>
	 * a.equip_nm, <br>
	 * a.equip_tid_val, <br>
	 * a.equip_ip_addr, <br>
	 * a.equip_mdl_cd, <br>
	 * a.tpo_cd, <br>
	 * a.scard_desc, <br>
	 * a.port_desc, <br>
	 * a.dabl_occr_loc_desc, <br>
	 * a.all_msg_ctt, <br>
	 * a.trms_net_equip_msg_mgmt_num, <br>
	 * a.cmpr_char_str_val, <br>
	 * a.oper_dabl_yn, <br>
	 * a.emcy_dabl_yn, <br>
	 * a.dmg_line_cnt, <br>
	 * a.dabl_dup_cnt, <br>
	 * a.ems_id, <br>
	 * a.ems_alm_val, <br>
	 * a.rlse_dtm, <br>
	 * a.rlse_rcv_dtm, <br>
	 * a.dabl_send_yn, <br>
	 * a.dabl_send_dtm, <br>
	 * a.dabl_send_rslt_val, <br>
	 * a.dabl_rlse_send_yn, <br>
	 * a.dabl_rlse_send_dtm, <br>
	 * a.dabl_rlse_send_rslt_val<br>
	 * FROM <br>
	 * omn33810 a<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * <br>
	 * AND (a.dabl_send_rslt_val is null or a.dabl_send_rslt_val != '1')<br>
	 * <br>
	 * AND a.rlse_dtm is not null AND (a.dabl_rlse_send_rslt_val is null or
	 * a.dabl_rlse_send_rslt_val != '1')<br>
	 * <br>
	 * AND a.dabl_send_rslt_val = 1 AND a.occr_dtm < TO_CHAR(sysdate-7,
	 * 'YYYYMMDDHH24MISS')<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_EQUIP_ALARM_LIST = "SELECT_EQUIP_ALARM_LIST";

	/**
	 * para : <br>
	 * result : RESULT_NET_ALARM_VO=com.skb.adams.teams.fxms.dbo.OMN33830<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.trms_ring_dabl_num, <br>
	 * a.trms_equip_dabl_num, <br>
	 * a.net_num, <br>
	 * a.nw_nm, <br>
	 * a.trms_net_topo_typ_cd, <br>
	 * a.trms_net_usg_cd, <br>
	 * a.trms_net_equip_capa_cl_cd, <br>
	 * a.trunk_num, <br>
	 * a.net_dabl_cd<br>
	 * FROM<br>
	 * omn33830 a<br>
	 * INNER JOIN<br>
	 * omn33811 b ON a.trms_equip_dabl_num = b.trms_equip_dabl_num<br>
	 * INNER JOIN<br>
	 * omn33810 c ON b.trms_equip_dabl_num = c.trms_equip_dabl_num<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NET_ALARM_LIST = "SELECT_NET_ALARM_LIST";

	/**
	 * para : <br>
	 * result : RESULT_NET_OPER_VO=com.skb.adams.teams.fxms.dbo.ONM10100<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.net_op_num, <br>
	 * b.equip_id, <br>
	 * b.port_num<br>
	 * FROM <br>
	 * onm10100 a<br>
	 * INNER JOIN <br>
	 * onm10101 b ON a.net_op_num = b.net_op_num<br>
	 * WHERE <br>
	 * a.oper_sta_dtm <= to_char(sysdate, 'yyyymmddhhmiss')<br>
	 * AND (<br>
	 * (a.oper_fnsh_dtm is not null AND a.oper_fnsh_dtm > to_char(sysdate,
	 * 'yyyymmddhhmiss')) <br>
	 * OR<br>
	 * ( <br>
	 * (a.oper_fnsh_dtm is null or a.oper_fnsh_dtm = '') <br>
	 * AND a.expt_oper_fnsh_dtm > to_char(sysdate, 'yyyymmddhhmiss') <br>
	 * )<br>
	 * )<br>
	 * AND a.net_op_st_cd in ('1', '2')<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NET_OPER_LIST = "SELECT_NET_OPER_LIST";

	/**
	 * para : <br>
	 * result : RESULT_LINE_ALARM_VO=com.skb.adams.teams.fxms.dbo.OMN33820<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.trms_line_dabl_num,<br>
	 * a.trms_equip_dabl_num,<br>
	 * a.line_num,<br>
	 * a.cust_num,<br>
	 * a.svc_mgmt_num,<br>
	 * a.trms_net_line_usg_cd,<br>
	 * a.trms_net_line_svc_cd,<br>
	 * a.line_capa_cl_cd,<br>
	 * a.sup_tpo_cd,<br>
	 * a.sub_tpo_cd,<br>
	 * a.sale_chrgr_id,<br>
	 * a.trms_net_mgmt_gr_cd<br>
	 * FROM <br>
	 * omn33820 a<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * AND (a.dabl_send_rslt_val is null or a.dabl_send_rslt_val != '1')<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NO_SEND_LINE_ALARM_LIST = "SELECT_NO_SEND_LINE_ALARM_LIST";

	/**
	 * para : <br>
	 * result : RESULT_NET_ALARM_VO=com.skb.adams.teams.fxms.dbo.OMN33830<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.trms_ring_dabl_num, <br>
	 * a.trms_equip_dabl_num, <br>
	 * a.net_num, <br>
	 * a.nw_nm, <br>
	 * a.trms_net_topo_typ_cd, <br>
	 * a.trms_net_usg_cd, <br>
	 * a.trms_net_equip_capa_cl_cd, <br>
	 * a.trunk_num,<br>
	 * a.net_dabl_cd<br>
	 * FROM <br>
	 * omn33830 a<br>
	 * WHERE <br>
	 * 1 = 1<br>
	 * AND (a.dabl_send_rslt_val is null or a.dabl_send_rslt_val != '1')<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_NO_SEND_NET_ALARM_LIST = "SELECT_NO_SEND_NET_ALARM_LIST";

	/**
	 * para : $occrRcvDtm, $equipId<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * UPDATE <br>
	 * oiv10178 <br>
	 * SET<br>
	 * audit_id = 'ADAMSTMN',<br>
	 * audit_dtm = sysdate,<br>
	 * last_dabl_rcv_dtm = $occrRcvDtm<br>
	 * WHERE<br>
	 * equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10178__BY_equipId = "UPDATE_OIV10178__BY_equipId";

	/**
	 * para : $occrDtm, $dcnNum<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * UPDATE <br>
	 * oiv10618 <br>
	 * SET<br>
	 * audit_id = 'ADAMSTMN',<br>
	 * audit_dtm = sysdate,<br>
	 * last_dabl_occr_dtm = $occrDtm<br>
	 * WHERE<br>
	 * dcn_num = $dcnNum<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10618__BY_dcnNum = "UPDATE_OIV10618__BY_dcnNum";

}
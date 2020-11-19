package com.lagoon.tmn.test;

/**
 * File : test_tmn.xml<br>
 * 
 * @since 20190910101207
 * @author subkjh
 *
 */

public class TestTmnQid {

	/** 쿼리 모임 화일명. test_tmn.xml */
	public static final String QUERY_XML_FILE = "test_tmn.xml";

	public TestTmnQid() {
	}

	/**
	 * para : <br>
	 * result :
	 * RESULT_TRMS_NET_EVENT_VO=com.skb.adams.teams.co.vo.TrmsNetEventVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.dabl_desc as FULL_MSG<br>
	 * , a.dabl_desc as REASON<br>
	 * , a.dabl_cd as DABL_CD<br>
	 * , a.dabl_gr_cd as DABL_GR_CD<br>
	 * , a.occr_dtm as OCCUR_TIME<br>
	 * , a.equip_id as EQUIP_ID<br>
	 * , a.dabl_occr_loc_desc as LOCATION<br>
	 * , a.dabl_port_desc as PORT_DESCR<br>
	 * , 0 as CLEAR_TIME<br>
	 * from omn30100 a<br>
	 * where a.occr_rcv_dtm > sysdate - ( 60 / 86400 ) <br>
	 * and a.nms_cd = '005'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_ALARM_1Min = "SELECT_ALARM_1Min";

	/**
	 * para : <br>
	 * result :
	 * RESULT_TRMS_NET_EVENT_VO=com.skb.adams.teams.co.vo.TrmsNetEventVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.dabl_desc as FULL_MSG<br>
	 * , a.dabl_desc as REASON<br>
	 * , a.dabl_cd as DABL_CD<br>
	 * , a.dabl_gr_cd as DABL_GR_CD<br>
	 * , 0 as OCCUR_TIME<br>
	 * , a.equip_id as EQUIP_ID<br>
	 * , a.dabl_occr_loc_desc as LOCATION<br>
	 * , a.dabl_port_desc as PORT_DESCR<br>
	 * , a.rlse_dtm as CLEAR_TIME<br>
	 * from omn30100 a<br>
	 * where a.rlse_rcv_dtm > sysdate - ( 60 / 86400 ) <br>
	 * and a.nms_cd = '005'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_ALARM_CLEAR_1Min = "SELECT_ALARM_CLEAR_1Min";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.dabl_num, a.dabl_nm, a.dabl_desc, a.dabl_port_desc,
	 * TO_CHAR(a.occr_rcv_dtm, 'YYYYMMDDHHMISS') as occr_rcv_dtm_string<br>
	 * , b.LINE_NUM, b.equip_id, b.net_num, a.equip_dabl_smry_ctt<br>
	 * from omn30100 a <br>
	 * , OMN30120 b<br>
	 * where a.occr_rcv_dtm > trunc(sysdate)<br>
	 * and a.dabl_num = b.dabl_num<br>
	 * -- and a.dabl_desc like '%Loss Of Signal%' <br>
	 * order by a.dabl_num<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_ALARM_LINE_EQUIP = "SELECT_ALARM_LINE_EQUIP";

	/**
	 * para : $equipId, $portDesc, $portDesc, $equipId<br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select distinct b.line_num, b.line_nm <br>
	 * , a.equip_a_port_info, a.equip_b_port_info, a.equip_id, a.ring_num <br>
	 * from oiv10612 a<br>
	 * , oiv10611 b<br>
	 * where a.ring_num in ( select net_num<br>
	 * from oiv10615 <br>
	 * where equip_id = $equipId<br>
	 * and ( a_port_info = $portDesc <br>
	 * or b_port_info = $portDesc ) <br>
	 * )<br>
	 * and a.line_num = b.line_num<br>
	 * and a.equip_id = $equipId<br>
	 * -- and ( a.a_port_info = $portDesc <br>
	 * -- or a.b_port_info = $portDesc ) <br>
	 * and b.line_st_cd != '08'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10611 = "SELECT_OIV10611";

	/**
	 * para : $equipId, $portDesc, $portDesc<br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select line_num, equip_id, equip_a_port_info, equip_b_port_info<br>
	 * from oiv10612 <br>
	 * where equip_id = $equipId<br>
	 * and ( a_port_info = $portDesc <br>
	 * or b_port_info = $portDesc )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10612 = "SELECT_OIV10612";

	/**
	 * para : $equipId, $portDesc, $portDesc<br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select net_num, equip_id, equip_a_port_info, equip_b_port_info<br>
	 * from oiv10615 <br>
	 * where equip_id = $equipId<br>
	 * and ( a_port_info = $portDesc <br>
	 * or b_port_info = $portDesc )<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10615 = "SELECT_OIV10615";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.occr_rcv_dtm,<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.equip_dabl_smry_ctt, <br>
	 * a.nms_dabl_num_ctt,<br>
	 * a.dabl_cd,<br>
	 * a.dabl_nm<br>
	 * FROM <br>
	 * omn30100 a<br>
	 * WHERE <br>
	 * a.occr_rcv_dtm > trunc(sysdate)<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND (<br>
	 * -- UPPER(a.dabl_desc) like '%SD%' OR <br>
	 * -- UPPER(a.dabl_desc) like '%COMMUNICATIONS SUBSYSTEM FAILURE%' OR <br>
	 * UPPER(a.dabl_desc) like '%LOSS%' OR<br>
	 * UPPER(a.dabl_desc) like '%LOS%'<br>
	 * )<br>
	 * ORDER BY a.dabl_num<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN30100 = "SELECT_OMN30100";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc<br>
	 * FROM <br>
	 * omn30100 a<br>
	 * WHERE <br>
	 * a.occr_rcv_dtm > trunc(sysdate)<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND (<br>
	 * UPPER(a.dabl_desc) like '%LOSS%' OR<br>
	 * UPPER(a.dabl_desc) like '%LOS%'<br>
	 * )<br>
	 * -- and dabl_cd = '012001'<br>
	 * GROUP BY <br>
	 * a.equip_id, a.dabl_port_desc<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN30100_GROUPBY_EQUIP_ID_DABL_PORT_DESC = "SELECT_OMN30100_GROUPBY_EQUIP_ID_DABL_PORT_DESC";

	/**
	 * para : $dabl_cd<br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.equip_dabl_smry_ctt AS key_value_1,<br>
	 * a.nms_dabl_num_ctt AS key_value_2,<br>
	 * a.dabl_num,<br>
	 * b.net_num,<br>
	 * b.trunk_num<br>
	 * FROM<br>
	 * omn30100 a<br>
	 * LEFT OUTER JOIN omn30120 b ON a.dabl_num = b.dabl_num<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * -- AND a.dabl_num between 191829245 and 192142985<br>
	 * AND a.occr_rcv_dtm > trunc(SYSDATE) -- AND a.dabl_num < 193253272<br>
	 * -- AND to_char(a.occr_rcv_dtm, 'YYYYMMDDHHMISS') between
	 * to_char(sysdate-1, 'YYYYMMDD')||'000000' and to_char(sysdate-1,
	 * 'YYYYMMDD')||'235959'<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND ( upper(a.dabl_desc) LIKE '%LOSS%'<br>
	 * OR upper(a.dabl_desc) LIKE '%LOS%' )<br>
	 * AND dabl_cd = $dabl_cd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN30100_TEST_CHECK_QRY = "SELECT_OMN30100_TEST_CHECK_QRY";

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
	 * aa.equip_id,<br>
	 * aa.dabl_port_desc,<br>
	 * aa.dabl_desc,<br>
	 * aa.dabl_cons_itm_desc,<br>
	 * aa.dabl_occr_loc_desc,<br>
	 * aa.key_value<br>
	 * FROM<br>
	 * (<br>
	 * SELECT<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.nms_dabl_num_ctt AS key_value<br>
	 * FROM<br>
	 * omn30100 a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * -- AND a.dabl_num between 191829245 and 192142985<br>
	 * AND a.occr_rcv_dtm > trunc(SYSDATE) -- AND a.dabl_num < 193253272<br>
	 * -- AND to_char(a.occr_rcv_dtm, 'YYYYMMDDHHMISS') between
	 * to_char(sysdate-1, 'YYYYMMDD')||'000000' and to_char(sysdate-1,
	 * 'YYYYMMDD')||'235959'<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND ( upper(a.dabl_desc) LIKE '%LOSS%'<br>
	 * OR upper(a.dabl_desc) LIKE '%LOS%' )<br>
	 * AND dabl_cd = '012001'<br>
	 * GROUP BY<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.nms_dabl_num_ctt<br>
	 * UNION ALL<br>
	 * SELECT<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.equip_dabl_smry_ctt AS key_value<br>
	 * FROM<br>
	 * omn30100 a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * -- AND a.dabl_num between 191829245 and 192142985<br>
	 * AND a.occr_rcv_dtm > trunc(SYSDATE) -- AND a.dabl_num < 193253272<br>
	 * -- AND to_char(a.occr_rcv_dtm, 'YYYYMMDDHHMISS') between
	 * to_char(sysdate-1, 'YYYYMMDD')||'000000' and to_char(sysdate-1,
	 * 'YYYYMMDD')||'235959'<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND ( upper(a.dabl_desc) LIKE '%LOSS%'<br>
	 * OR upper(a.dabl_desc) LIKE '%LOS%' )<br>
	 * AND a.dabl_cd IN (<br>
	 * '042001',<br>
	 * '042003'<br>
	 * )<br>
	 * GROUP BY<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.equip_dabl_smry_ctt<br>
	 * ) aa<br>
	 * GROUP BY<br>
	 * aa.equip_id,<br>
	 * aa.dabl_port_desc,<br>
	 * aa.dabl_desc,<br>
	 * aa.dabl_cons_itm_desc,<br>
	 * aa.dabl_occr_loc_desc,<br>
	 * aa.key_value<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN30100_UNIONALL_NMS_DABL_NUM_CTT_AND_EQUIP_DABL_SMRY_CTT = "SELECT_OMN30100_UNIONALL_NMS_DABL_NUM_CTT_AND_EQUIP_DABL_SMRY_CTT";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * SELECT <br>
	 * a.occr_rcv_dtm,<br>
	 * a.equip_id,<br>
	 * a.dabl_port_desc,<br>
	 * a.dabl_desc,<br>
	 * a.dabl_cons_itm_desc,<br>
	 * a.dabl_occr_loc_desc,<br>
	 * a.equip_dabl_smry_ctt, <br>
	 * a.nms_dabl_num_ctt,<br>
	 * a.dabl_cd,<br>
	 * a.dabl_nm<br>
	 * FROM <br>
	 * omn30100 a<br>
	 * WHERE <br>
	 * a.occr_rcv_dtm > trunc(sysdate)<br>
	 * AND a.net_cl_cd = '02'<br>
	 * AND a.equip_id != '*****'<br>
	 * AND (<br>
	 * UPPER(a.dabl_desc) like '%LOSS%' OR<br>
	 * UPPER(a.dabl_desc) like '%LOS%'<br>
	 * )<br>
	 * and dabl_cd = '012001'<br>
	 * and dabl_dup_cnt > 1<br>
	 * ORDER BY a.dabl_num<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN30100_WITH_LINE_ALARM = "SELECT_OMN30100_WITH_LINE_ALARM";

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
	 * OMN33810 a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN33810 = "SELECT_OMN33810";

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
	 * a.*,<br>
	 * (select b.equip_id from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as equip_id,<br>
	 * (select b.port_desc from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as port_desc,<br>
	 * (select dabl_msg_ctt from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as key_value<br>
	 * FROM<br>
	 * OMN33820 a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN33820 = "SELECT_OMN33820";

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
	 * a.*,<br>
	 * (select b.equip_id from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as equip_id,<br>
	 * (select b.port_desc from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as port_desc,<br>
	 * (select dabl_msg_ctt from OMN33810 b where a.trms_equip_dabl_num =
	 * b.trms_equip_dabl_num) as key_value<br>
	 * FROM<br>
	 * OMN33830 a<br>
	 * WHERE<br>
	 * 1 = 1<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OMN33830 = "SELECT_OMN33830";

}
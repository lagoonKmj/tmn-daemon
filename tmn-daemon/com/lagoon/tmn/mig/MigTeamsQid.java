package com.lagoon.tmn.mig;

/**
* File : mig_teams.xml<br>
* @since 20191021151345
* @author subkjh 
*
*/


public class MigTeamsQid { 

/** 쿼리 모임 화일명. mig_teams.xml*/
public static final String QUERY_XML_FILE = "mig_teams.xml";

public MigTeamsQid() { 
} 
/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>merge into OIV10104 A<br>			using ( select equip_id<br>					      , max(login_id)				as login_id<br>					      , max(login_pwd)				as login_pwd<br>					      , max(dabl_port_num)			as dabl_port_num<br>					      , max(cons_port_num)			as cons_port_num<br>					      , max(snmp_port_num)			as snmp_port_num<br>					      , max(snmp_read_cmnty_nm)		as snmp_read_cmnty_nm<br>					      , max(snmp_write_cmnty_nm)	as snmp_write_cmnty_nm<br>					from 	(<br>								select 	a.dcn_cot_equip_id 													as equip_id<br>										, decode(b.dcn_port_num, 161, '', b.login_id) 						as login_id<br>										, decode(b.dcn_port_num, 161, '', b.login_pwd) 						as login_pwd<br>										, decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0) 	as dabl_port_num<br>										, decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0) 	as cons_port_num<br>										, decode(b.dcn_port_num, 161, 161, 0) 								as snmp_port_num<br>										, decode(b.dcn_port_num, 161, b.snmp_read_cmnty_nm, '') 			as snmp_read_cmnty_nm<br>										, decode(b.dcn_port_num, 161, b.snmp_write_cmnty_nm, '') 			as snmp_write_cmnty_nm<br>								from 	OIV10618 a<br>										, oiv10619 b<br>								where  	a.dcn_typ_cd = 'D'<br>								and 	a.dcn_num = b.dcn_num<br>								and 	b.use_yn = 'Y'		<br>								and 	a.dcn_cot_equip_id is not null			<br>							)<br>					group by <br>					      equip_id<br>					) B<br>			on ( A.equip_id = B.equip_id )<br>			when not matched then<br>				insert ( <br>					a.equip_id<br>					, a.login_id<br>					, a.login_pwd<br>					, a.dabl_port_num<br>					, a.cons_port_num<br>					, a.snmp_port_num	<br>					, a.snmp_read_cmnty_nm<br>					, a.snmp_write_cmnty_nm<br><br>					, a.audit_id<br>					, a.audit_dtm<br>					, a.prfm_port_num<br>					, a.conn_st_val<br>					, a.clct_svr_num<br>				) values (<br>					b.equip_id<br>					, b.login_id<br>					, b.login_pwd<br>					, b.dabl_port_num<br>					, b.cons_port_num<br>					, b.snmp_port_num	<br>					, b.snmp_read_cmnty_nm<br>					, b.snmp_write_cmnty_nm<br><br>					, 'ADAMSTMN'<br>					, sysdate<br>					, 0<br>					, '0'<br>					, 0<br>				)<br><br> <br>
*/
public final String MERGE_OIV10104__From_DCN = "MERGE_OIV10104__From_DCN";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>merge into OIV10105 A<br>			using ( select ems_id<br>					      , ems_nm<br>					      , ems_ip_addr<br>					      , ems_ip_addr2<br>					      , ems_typ_val<br>					      , max(login_id)				as login_id<br>					      , max(login_pwd)				as login_pwd<br>					      , max(dabl_port_num)			as dabl_port_num<br>					      , max(cons_port_num)			as cons_port_num<br>					      , max(snmp_port_num)			as snmp_port_num<br>					      , max(snmp_read_cmnty_nm)		as snmp_read_cmnty_nm<br>					      , max(snmp_write_cmnty_nm)	as snmp_write_cmnty_nm<br>					      , max(use_ip_addr_idx_val)  	as use_ip_addr_idx_val   <br>					from 	(<br>								select 	a.dcn_num 															as ems_id<br>										, a.dcn_nm 															as ems_nm<br>										, a.mst_dcn_ip_addr 												as ems_ip_addr<br>										, a.sub_dcn_ip_addr 												as ems_ip_addr2        <br>										, nvl(a1.comm_cd_val_nm, 'NOTDEFINE') 								as ems_typ_val<br>										, decode(b.dcn_port_num, 161, '', b.login_id) 						as login_id<br>										, decode(b.dcn_port_num, 161, '', b.login_pwd) 						as login_pwd<br>										, decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0) 	as dabl_port_num<br>										, decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0) 	as cons_port_num<br>										, decode(b.dcn_port_num, 161, 161, 0) 								as snmp_port_num<br>										, decode(b.dcn_port_num, 161, b.snmp_read_cmnty_nm, '') 			as snmp_read_cmnty_nm<br>										, decode(b.dcn_port_num, 161, b.snmp_write_cmnty_nm, '') 			as snmp_write_cmnty_nm<br>										, decode(b.rsv_port_yn, 'Y', 2, 1) 									as use_ip_addr_idx_val<br>								from 	OIV10618 a<br>											left join OCO20101 a1 on a1.comm_cd = 'DCN_EMS_TYP_CD' and a1.comm_cd_val = a.DCN_EMS_TYP_CD<br>										, oiv10619 b<br>								where  	a.dcn_typ_cd = 'E'<br>								and 	a.dcn_num = b.dcn_num<br>								and 	b.use_yn = 'Y'					<br>							)<br>					group by <br>					      ems_id<br>					      , ems_nm<br>					      , ems_ip_addr<br>					      , EMS_TYP_VAL<br>					      , ems_ip_addr2<br>					) B<br>			on ( A.ems_id = B.ems_id )<br>			when not matched then<br>				insert ( <br>					a.ems_id<br>					, a.ems_nm<br>					, a.ems_ip_addr<br>					, a.ems_ip_addr2<br>					, a.ems_typ_val<br>					, a.login_id<br>					, a.login_pwd<br>					, a.dabl_port_num<br>					, a.cons_port_num<br>					, a.snmp_port_num	<br>					, a.snmp_read_cmnty_nm<br>					, a.snmp_write_cmnty_nm<br>					, a.use_ip_addr_idx_val<br><br>					, a.audit_id<br>					, a.audit_dtm<br>					, a.svc_nw_cl_cd<br>					, a.ems_url<br>					, a.prfm_port_num<br>					, a.etc_port_num1<br>					, a.etc_port_num2<br>					, a.etc_port_num3<br>					, a.ems_st_val<br>					, a.clct_svr_num<br>				) values (<br>					b.ems_id<br>					, b.ems_nm<br>					, b.ems_ip_addr<br>					, b.ems_ip_addr2<br>					, b.ems_typ_val<br>					, b.login_id<br>					, b.login_pwd<br>					, b.dabl_port_num<br>					, b.cons_port_num<br>					, b.snmp_port_num	<br>					, b.snmp_read_cmnty_nm<br>					, b.snmp_write_cmnty_nm<br>					, b.use_ip_addr_idx_val<br><br>					, 'ADAMSTMN'<br>					, sysdate<br>					, '00'<br>					, null<br>					, 0<br>					, 0<br>					, 0<br>					, 0<br>					, '0'<br>					, 0<br>				)<br><br> <br>
*/
public final String MERGE_OIV10105__From_DCN = "MERGE_OIV10105__From_DCN";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>merge into OIV10106 A<br>			using ( select 	a.dcn_num			as ems_id<br>							, b.equip_id		as equip_id<br>							, c.equip_tid_val	as equip_tid_val<br>					from 	oiv10618 a<br>    						, oiv10011 b<br>    						, oiv10100 c<br>					where 	a.dcn_typ_cd = 'E'<br>					and 	b.dcn_num = a.dcn_num <br>					and		c.equip_id = b.equip_id<br>					) B<br>			on ( A.ems_id = B.ems_id and A.equip_id = B.equip_id )<br>			when not matched then<br>				insert ( <br>					A.EMS_ID<br>					, A.EQUIP_ID<br>					, A.AUDIT_ID<br>					, A.AUDIT_DTM<br>					, A.EMS_EQUIP_NUM<br>					, A.AUTO_MGMT_YN<br>					, A.AUTO_MGMT_DTM<br>					, A.EMS_INR_EQUIP_KEY_VAL			<br>				) values (<br>					B.EMS_ID<br>					, B.EQUIP_ID<br>					, 'ADAMS-TMN'<br>					, sysdate<br>					, null<br>					, 'N'<br>					, sysdate					<br>					, B.equip_tid_val<br>				)<br><br> <br>
*/
public final String MERGE_OIV10106 = "MERGE_OIV10106";

/**
* para : <br>
* result : RESULT_MIG_MODEL_VO=com.skb.adams.mig.MigModelVo<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select a.equip_mdl_cd<br>		      , a1.equip_mdl_nm<br>		      , CLCT_EQUIP_MDL_CD as model_id<br>		      , CLCT_EQUIP_MDL_nm as model_nm<br>		from oiv10213 a<br>		      left join oiv10200 a1 on a.equip_mdl_cd = a1.equip_mdl_cd<br>		where CLCT_NMS_CL_CD = '005'<br><br> <br>
*/
public final String SELECT_MODEL_INFO = "SELECT_MODEL_INFO";

/**
* para : <br>
* result : RESULT_OMN33812=com.skb.adams.teams.fxms.dbo.OMN33812<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	a1.EQUIP_MDL_CD as equip_mdl_cd<br>				, a.alarm_reason as CMPR_CHAR_STR_VAL<br>				, max(a.alarm_reason_seq) as TRMS_NET_EQUIP_MSG_MGMT_NUM<br>				, decode(max(a.alarm_level), '10000', '05', '01000', '04', '00100', '03', '00010', '02', '01' ) as DABL_GR_CD<br>				, '1030003' as DABL_CD<br>				, max(a.monitor_yn) as DABL_APLY_YN<br>				, 'N' as EMCY_DABL_YN<br>				, max(a.network_alarm_yn) as NET_DABL_APLY_YN<br>				, max(network_alarm_cd) as NET_DABL_CD<br>				, max(network_alarm_reason) as NET_DABL_NM<br>		from 	tea_cf_alarm_reason_info a<br>					left join oiv10213 a1 on a1.CLCT_NMS_CL_CD = '005' and a1.clct_equip_mdl_cd = a.model_id<br>					left join oiv10200 a2 on a2.equip_mdl_cd = a1.CLCT_EQUIP_MDL_CD<br>		where 	clct_oper_key_val = ( <br>						select max(clct_oper_key_val) from tea_cf_alarm_reason_info<br>					)<br>		--and a.nms_cd = '034001' ' TEAMS '<br>		group by a1.equip_mdl_cd,  a.alarm_reason<br><br> <br>
*/
public final String SELECT_cf_alarm_reason_info = "SELECT_cf_alarm_reason_info";

/**
* para : $getClctMthdClCd(), $getEquipMdlCd()<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>update 	oiv10200<br>		set 	clct_mthd_cl_cd = $getClctMthdClCd()<br>				, audit_id = 'SKAdams_subkjh'<br>				, audit_dtm = sysdate<br>		where 	equip_mdl_cd = $getEquipMdlCd()<br><br> <br>
*/
public final String UPDATE_OIV10200_clctMthdClCd = "UPDATE_OIV10200_clctMthdClCd";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>update 	oiv10200<br>		set 	clct_mthd_cl_cd = '01'<br>				, audit_id = 'SKAdams_subkjh'<br>				, audit_dtm = sysdate<br>		where 	equip_mdl_cd in (<br>					select 	distinct c.equip_mdl_cd<br>					from 	oiv10618 a ' dcn기본 ' <br>							, oiv10011 b<br>							, oiv10100 c<br>					where 	a.dcn_typ_cd = 'E'<br>					and		b.dcn_num = a.dcn_num<br>					and		c.equip_id = b.equip_id<br>					and		a.dcn_ems_typ_cd is not null<br>				)<br><br> <br>
*/
public final String UPDATE_OIV10200_clctMthdClCd__EMS = "UPDATE_OIV10200_clctMthdClCd__EMS";

}
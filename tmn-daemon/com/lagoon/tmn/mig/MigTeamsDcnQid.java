package com.lagoon.tmn.mig;

/**
 * File : mig/teams_dcn.xml<br>
 * 
 * @since 20200210172645
 * @author subkjh
 *
 */

public class MigTeamsDcnQid {

	/** 쿼리 모임 화일명. mig/teams_dcn.xml */
	public static final String QUERY_XML_FILE = "mig/teams_dcn.xml";

	public MigTeamsDcnQid() {
	}

	/**
	 * para : #equipMdlCd#emsTypVal$clctSvrIpAddr<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10104 A<br>
	 * using ( select equip_id<br>
	 * , '02' as net_cl_cd<br>
	 * from oiv10011<br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id and a.net_cl_cd = b.net_cl_cd )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * a.net_cl_cd<br>
	 * , a.equip_id<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * ) values (<br>
	 * '02'<br>
	 * , b.equip_id<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * )<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * and a.ems_typ_val in ( #emsTypVal )<br>
	 * b.clct_svr_ip_addr = $clctSvrIpAddr<br>
	 * <br>
	 */
	public final String MERGE_OIV10104_BY_CLCT_SVR_NUM = "MERGE_OIV10104_BY_CLCT_SVR_NUM";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10104 A<br>
	 * using ( select equip_id<br>
	 * , max(login_id) as login_id<br>
	 * , max(login_pwd) as login_pwd<br>
	 * , max(dabl_port_num) as dabl_port_num<br>
	 * , max(cons_port_num) as cons_port_num<br>
	 * , max(snmp_ver_num) as snmp_ver_num<br>
	 * , max(snmp_port_num) as snmp_port_num<br>
	 * , max(snmp_read_cmnty_nm) as snmp_read_cmnty_nm<br>
	 * , max(snmp_write_cmnty_nm) as snmp_write_cmnty_nm<br>
	 * , max(equip_ip_addr) as equip_ip_addr<br>
	 * , max(scnd_equip_ip_addr) as scnd_equip_ip_addr<br>
	 * , max(use_ip_addr_idx_val) as use_ip_addr_idx_val<br>
	 * , max(conn_cl_val) as conn_cl_val<br>
	 * from (<br>
	 * select a.dcn_cot_equip_id as equip_id<br>
	 * , decode(b.dcn_port_num, 161, '', b.login_id) as login_id<br>
	 * , decode(b.dcn_port_num, 161, '', b.login_pwd) as login_pwd<br>
	 * , decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0) as
	 * dabl_port_num<br>
	 * , decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0) as
	 * cons_port_num<br>
	 * , decode(b.dcn_port_num, 161, 161, 0) as snmp_port_num<br>
	 * , b.snmp_ver_num as snmp_ver_num<br>
	 * , decode(b.dcn_port_num, 161, b.snmp_read_cmnty_nm, '') as
	 * snmp_read_cmnty_nm<br>
	 * , decode(b.dcn_port_num, 161, b.snmp_write_cmnty_nm, '') as
	 * snmp_write_cmnty_nm<br>
	 * , a.mst_dcn_ip_addr as equip_ip_addr<br>
	 * , a.sub_dcn_ip_addr as scnd_equip_ip_addr<br>
	 * , decode(b.rsv_port_yn, 'Y', 2, 1) as use_ip_addr_idx_val<br>
	 * , 'DCN-' || a.dcn_num as conn_cl_val <br>
	 * from OIV10618 a<br>
	 * , oiv10619 b<br>
	 * where a.dcn_typ_cd = 'D'<br>
	 * and a.dcn_num = b.dcn_num<br>
	 * and b.use_yn = 'Y' <br>
	 * and a.dcn_cot_equip_id is not null <br>
	 * )<br>
	 * group by <br>
	 * equip_id<br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * a.equip_id<br>
	 * , a.login_id<br>
	 * , a.login_pwd<br>
	 * , a.dabl_port_num<br>
	 * , a.cons_port_num<br>
	 * , a.snmp_ver_num<br>
	 * , a.snmp_port_num <br>
	 * , a.snmp_read_cmnty_nm<br>
	 * , a.snmp_write_cmnty_nm<br>
	 * <br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * , a.prfm_port_num<br>
	 * , a.conn_st_val<br>
	 * , a.clct_svr_num<br>
	 * <br>
	 * , a.equip_ip_addr <br>
	 * , a.scnd_equip_ip_addr <br>
	 * , a.use_ip_addr_idx_val <br>
	 * , a.conn_st_chg_dtm <br>
	 * , a.conn_cl_val <br>
	 * ) values (<br>
	 * b.equip_id<br>
	 * , b.login_id<br>
	 * , b.login_pwd<br>
	 * , b.dabl_port_num<br>
	 * , b.cons_port_num<br>
	 * , b.snmp_ver_num<br>
	 * , b.snmp_port_num <br>
	 * , b.snmp_read_cmnty_nm<br>
	 * , b.snmp_write_cmnty_nm<br>
	 * <br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , 0<br>
	 * , '0'<br>
	 * , 0<br>
	 * <br>
	 * , b.equip_ip_addr <br>
	 * , b.scnd_equip_ip_addr <br>
	 * , b.use_ip_addr_idx_val <br>
	 * , sysdate <br>
	 * , b.conn_cl_val <br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.login_id = b.login_id<br>
	 * , a.login_pwd = b.login_pwd<br>
	 * , a.dabl_port_num = b.dabl_port_num<br>
	 * , a.cons_port_num = b.cons_port_num<br>
	 * , a.snmp_ver_num = b.snmp_ver_num<br>
	 * , a.snmp_port_num = b.snmp_port_num<br>
	 * , a.snmp_read_cmnty_nm = b.snmp_read_cmnty_nm<br>
	 * , a.snmp_write_cmnty_nm = b.snmp_write_cmnty_nm<br>
	 * , a.equip_ip_addr = b.equip_ip_addr<br>
	 * , a.scnd_equip_ip_addr = b.scnd_equip_ip_addr<br>
	 * , a.use_ip_addr_idx_val = b.use_ip_addr_idx_val<br>
	 * , a.conn_cl_val = b.conn_cl_val<br>
	 * <br>
	 * , a.audit_id = 'ADAMSTMN'<br>
	 * , a.audit_dtm = sysdate<br>
	 * , a.prfm_port_num = 0<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10104__From_DCN = "MERGE_OIV10104__From_DCN";

	/**
	 * para : #equipMdlCd#emsTypVal$clctSvrIpAddr<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10105 A<br>
	 * using ( select equip_id<br>
	 * , '02' as net_cl_cd<br>
	 * from oiv10011<br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id and a.net_cl_cd = b.net_cl_cd )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * a.net_cl_cd<br>
	 * , a.equip_id<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * ) values (<br>
	 * '02'<br>
	 * , b.equip_id<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * )<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * and a.ems_typ_val in ( #emsTypVal )<br>
	 * b.clct_svr_ip_addr = $clctSvrIpAddr<br>
	 * <br>
	 */
	public final String MERGE_OIV10105_BY_CLCT_SVR_NUM = "MERGE_OIV10105_BY_CLCT_SVR_NUM";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10105 A<br>
	 * using ( select ems_id<br>
	 * , ems_nm<br>
	 * , ems_ip_addr<br>
	 * , ems_ip_addr2<br>
	 * , ems_typ_val<br>
	 * , max(tpo_cd) as tpo_cd<br>
	 * , max(login_id) as login_id<br>
	 * , max(login_pwd) as login_pwd<br>
	 * , max(dabl_port_num) as dabl_port_num<br>
	 * , max(cons_port_num) as cons_port_num<br>
	 * , max(snmp_ver_num) as snmp_ver_num<br>
	 * , max(snmp_port_num) as snmp_port_num<br>
	 * , max(snmp_read_cmnty_nm) as snmp_read_cmnty_nm<br>
	 * , max(snmp_write_cmnty_nm) as snmp_write_cmnty_nm<br>
	 * , max(use_ip_addr_idx_val) as use_ip_addr_idx_val <br>
	 * from (<br>
	 * select a.dcn_num as ems_id<br>
	 * , a.dcn_nm as ems_nm<br>
	 * , a.mst_dcn_ip_addr as ems_ip_addr<br>
	 * , a.sub_dcn_ip_addr as ems_ip_addr2 <br>
	 * , nvl(a1.comm_cd_val_nm, 'NOTDEFINE') as ems_typ_val<br>
	 * , nvl(a.tpo_cd, 'A20100') as tpo_cd ' 없으면 동작종합통신센터 '<br>
	 * , decode(b.dcn_port_num, 161, '', b.login_id) as login_id<br>
	 * , decode(b.dcn_port_num, 161, '', b.login_pwd) as login_pwd<br>
	 * , decode(substr(b.dcn_port_usg_val, 1, 1), '1', b.dcn_port_num, 0) as
	 * dabl_port_num<br>
	 * , decode(substr(b.dcn_port_usg_val, 2, 1), '1', b.dcn_port_num, 0) as
	 * cons_port_num<br>
	 * , b.snmp_ver_num as snmp_ver_num<br>
	 * , decode(b.dcn_port_num, 161, 161, 0) as snmp_port_num<br>
	 * , decode(b.dcn_port_num, 161, b.snmp_read_cmnty_nm, '') as
	 * snmp_read_cmnty_nm<br>
	 * , decode(b.dcn_port_num, 161, b.snmp_write_cmnty_nm, '') as
	 * snmp_write_cmnty_nm<br>
	 * , decode(b.rsv_port_yn, 'Y', 2, 1) as use_ip_addr_idx_val <br>
	 * from OIV10618 a<br>
	 * left join OCO20101 a1 on a1.comm_cd = 'DCN_EMS_TYP_CD' and a1.comm_cd_val
	 * = a.DCN_EMS_TYP_CD<br>
	 * , oiv10619 b<br>
	 * where a.dcn_typ_cd = 'E'<br>
	 * and a.dcn_num = b.dcn_num<br>
	 * and b.use_yn = 'Y' <br>
	 * )<br>
	 * group by <br>
	 * ems_id<br>
	 * , ems_nm<br>
	 * , ems_ip_addr<br>
	 * , EMS_TYP_VAL<br>
	 * , ems_ip_addr2<br>
	 * ) B<br>
	 * on ( A.ems_id = B.ems_id )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * a.ems_id<br>
	 * , a.ems_nm<br>
	 * , a.ems_ip_addr<br>
	 * , a.ems_ip_addr2<br>
	 * , a.ems_typ_val<br>
	 * , a.tpo_cd<br>
	 * , a.login_id<br>
	 * , a.login_pwd<br>
	 * , a.dabl_port_num<br>
	 * , a.cons_port_num<br>
	 * , a.snmp_ver_num<br>
	 * , a.snmp_port_num <br>
	 * , a.snmp_read_cmnty_nm<br>
	 * , a.snmp_write_cmnty_nm<br>
	 * , a.use_ip_addr_idx_val<br>
	 * <br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * , a.svc_nw_cl_cd<br>
	 * , a.ems_url<br>
	 * , a.prfm_port_num<br>
	 * , a.etc_port_num1<br>
	 * , a.etc_port_num2<br>
	 * , a.etc_port_num3<br>
	 * , a.ems_st_val<br>
	 * , a.ems_st_chg_dtm <br>
	 * , a.clct_svr_num<br>
	 * <br>
	 * ) values (<br>
	 * b.ems_id<br>
	 * , b.ems_nm<br>
	 * , b.ems_ip_addr<br>
	 * , b.ems_ip_addr2<br>
	 * , b.ems_typ_val<br>
	 * , b.tpo_cd<br>
	 * , b.login_id<br>
	 * , b.login_pwd<br>
	 * , b.dabl_port_num<br>
	 * , b.cons_port_num<br>
	 * , b.snmp_ver_num <br>
	 * , b.snmp_port_num <br>
	 * , b.snmp_read_cmnty_nm<br>
	 * , b.snmp_write_cmnty_nm<br>
	 * , b.use_ip_addr_idx_val<br>
	 * <br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , '00'<br>
	 * , null<br>
	 * , 0<br>
	 * , 0<br>
	 * , 0<br>
	 * , 0<br>
	 * , '2'<br>
	 * , sysdate <br>
	 * , 0<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.ems_nm = b.ems_nm<br>
	 * , a.ems_ip_addr = b.ems_ip_addr<br>
	 * , a.ems_ip_addr2 = b.ems_ip_addr2<br>
	 * , a.ems_typ_val = b.ems_typ_val<br>
	 * , a.tpo_cd = b.tpo_cd<br>
	 * , a.login_id = b.login_id<br>
	 * , a.login_pwd = b.login_pwd<br>
	 * , a.dabl_port_num = b.dabl_port_num<br>
	 * , a.cons_port_num = b.cons_port_num<br>
	 * , a.snmp_ver_num = b.snmp_ver_num<br>
	 * , a.snmp_port_num = b.snmp_port_num<br>
	 * , a.snmp_read_cmnty_nm = b.snmp_read_cmnty_nm<br>
	 * , a.snmp_write_cmnty_nm = b.snmp_write_cmnty_nm<br>
	 * , a.use_ip_addr_idx_val = b.use_ip_addr_idx_val<br>
	 * <br>
	 * , a.audit_id = 'ADAMSTMN'<br>
	 * , a.audit_dtm = sysdate<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10105__From_DCN = "MERGE_OIV10105__From_DCN";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10106 A<br>
	 * using ( select a.dcn_num as ems_id<br>
	 * , b.equip_id as equip_id<br>
	 * , c.equip_tid_val as equip_tid_val<br>
	 * from oiv10618 a<br>
	 * , oiv10011 b<br>
	 * , oiv10100 c<br>
	 * where a.dcn_typ_cd = 'E'<br>
	 * and b.dcn_num = a.dcn_num <br>
	 * and c.equip_id = b.equip_id<br>
	 * ) B<br>
	 * on ( A.ems_id = B.ems_id and A.equip_id = B.equip_id )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.EMS_ID<br>
	 * , A.EQUIP_ID<br>
	 * , A.AUDIT_ID<br>
	 * , A.AUDIT_DTM<br>
	 * , A.EMS_EQUIP_NUM<br>
	 * , A.AUTO_MGMT_YN<br>
	 * , A.AUTO_MGMT_DTM<br>
	 * , A.EMS_INR_EQUIP_KEY_VAL <br>
	 * ) values (<br>
	 * B.EMS_ID<br>
	 * , B.EQUIP_ID<br>
	 * , 'ADAMS-TMN'<br>
	 * , sysdate<br>
	 * , null<br>
	 * , 'N'<br>
	 * , sysdate <br>
	 * , B.equip_tid_val<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10106 = "MERGE_OIV10106";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10115 A<br>
	 * using ( select equip_id<br>
	 * , '02' as net_cl_cd<br>
	 * from oiv10011<br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id and a.net_cl_cd = b.net_cl_cd )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * a.net_cl_cd<br>
	 * , a.equip_id<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * ) values (<br>
	 * '02'<br>
	 * , b.equip_id<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10115 = "MERGE_OIV10115";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10104 A<br>
	 * using ( select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , to_number(t.dabl_port_num) as dcn_port_num <br>
	 * , case <br>
	 * when t.dabl_port_num = t.cons_port_num then '11000000'<br>
	 * else '10000000'<br>
	 * end as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.dabl_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , to_number(t.cons_port_num) as dcn_port_num<br>
	 * , '01000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.cons_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , t.snmp_port_num as dcn_port_num<br>
	 * , '10000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.snmp_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * ) B<br>
	 * on ( A.dcn_num = B.dcn_num and A.dcn_port_num = B.dcn_port_num )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.dcn_num<br>
	 * , A.dcn_port_num<br>
	 * , A.dcn_port_usg_val<br>
	 * , A.audit_id<br>
	 * , A.audit_dtm<br>
	 * , A.login_id<br>
	 * , A.login_pwd<br>
	 * , A.snmp_ver_num<br>
	 * , A.snmp_read_cmnty_nm<br>
	 * , A.snmp_write_cmnty_nm<br>
	 * , A.use_yn<br>
	 * , A.dcn_st_cd<br>
	 * , A.dcn_st_last_clct_dtm<br>
	 * , A.last_clct_dtm<br>
	 * , A.rsv_port_yn<br>
	 * ) values (<br>
	 * B.dcn_num<br>
	 * , B.dcn_port_num<br>
	 * , B.dcn_port_usg_val<br>
	 * , B.audit_id<br>
	 * , B.audit_dtm<br>
	 * , B.login_id<br>
	 * , B.login_pwd<br>
	 * , B.snmp_ver_num<br>
	 * , B.snmp_read_cmnty_nm<br>
	 * , B.snmp_write_cmnty_nm<br>
	 * , B.use_yn<br>
	 * , B.dcn_st_cd<br>
	 * , B.dcn_st_last_clct_dtm<br>
	 * , B.last_clct_dtm<br>
	 * , B.rsv_port_yn<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10614__From_OIV10104 = "MERGE_OIV10614__From_OIV10104";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10618 A<br>
	 * using ( select replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , a.equip_nm as dcn_nm<br>
	 * , t.equip_ip_addr as mst_dcn_ip_addr<br>
	 * , t.scnd_equip_ip_addr as sub_dcn_ip_addr<br>
	 * , 'D' as dcn_typ_cd<br>
	 * , null as dcn_ems_typ_cd<br>
	 * , null as dcn_cot_equip_id<br>
	 * , t.clct_svr_num as dcn_clct_svr_id<br>
	 * , a.tpo_cd as tpo_cd<br>
	 * , null as dcn_add_info<br>
	 * , t2.clct_nms_cl_cd as trms_net_nms_cl_cd<br>
	 * , 'N' as rsv_port_yn<br>
	 * , null as last_dabl_occr_dtm<br>
	 * , t.clct_svr_num as clct_svr_num<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * from OIV10100 a<br>
	 * , OIV10104 t<br>
	 * left join OCL20120 t2 on t2.clct_svr_num = t.clct_svr_num <br>
	 * where a.equip_id = t.equip_id<br>
	 * and t.conn_cl_val like 'DCN%' <br>
	 * ) B<br>
	 * on ( A.dcn_num = B.dcn_num )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.dcn_num<br>
	 * , A.audit_id<br>
	 * , A.audit_dtm<br>
	 * , A.dcn_nm<br>
	 * , A.mst_dcn_ip_addr<br>
	 * , A.sub_dcn_ip_addr<br>
	 * , A.dcn_typ_cd<br>
	 * , A.dcn_ems_typ_cd<br>
	 * , A.dcn_cot_equip_id<br>
	 * , A.dcn_clct_svr_id<br>
	 * , A.tpo_cd<br>
	 * , A.dcn_add_info<br>
	 * , A.trms_net_nms_cl_cd<br>
	 * , A.rsv_port_yn<br>
	 * , A.last_dabl_occr_dtm<br>
	 * , A.clct_svr_num<br>
	 * ) values (<br>
	 * B.dcn_num<br>
	 * , B.audit_id<br>
	 * , B.audit_dtm<br>
	 * , B.dcn_nm<br>
	 * , B.mst_dcn_ip_addr<br>
	 * , B.sub_dcn_ip_addr<br>
	 * , B.dcn_typ_cd<br>
	 * , B.dcn_ems_typ_cd<br>
	 * , B.dcn_cot_equip_id<br>
	 * , B.dcn_clct_svr_id<br>
	 * , B.tpo_cd<br>
	 * , B.dcn_add_info<br>
	 * , B.trms_net_nms_cl_cd<br>
	 * , B.rsv_port_yn<br>
	 * , B.last_dabl_occr_dtm<br>
	 * , B.clct_svr_num<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10618__From_OIV10104 = "MERGE_OIV10618__From_OIV10104";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10618 A<br>
	 * using ( select t.ems_id as dcn_num<br>
	 * , t.ems_nm as dcn_nm<br>
	 * , t.ems_ip_addr as mst_dcn_ip_addr<br>
	 * , t.ems_ip_addr2 as sub_dcn_ip_addr<br>
	 * , 'E' as dcn_typ_cd<br>
	 * , t1.comm_cd_val as dcn_ems_typ_cd<br>
	 * , null as dcn_cot_equip_id<br>
	 * , t.clct_svr_num as dcn_clct_svr_id<br>
	 * , t.tpo_cd as tpo_cd<br>
	 * , null as dcn_add_info<br>
	 * , t2.clct_nms_cl_cd as trms_net_nms_cl_cd<br>
	 * , 'N' as rsv_port_yn<br>
	 * , null as last_dabl_occr_dtm<br>
	 * , t.clct_svr_num as clct_svr_num<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * from OIV10105 t<br>
	 * left join OCO20101 t1 on t1.comm_cd = 'DCN_EMS_TYP_CD' and
	 * t1.comm_cd_val_nm = t.ems_typ_val<br>
	 * left join OCL20120 t2 on t2.clct_svr_num = t.clct_svr_num <br>
	 * ) B<br>
	 * on ( A.dcn_num = B.dcn_num )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.dcn_num<br>
	 * , A.audit_id<br>
	 * , A.audit_dtm<br>
	 * , A.dcn_nm<br>
	 * , A.mst_dcn_ip_addr<br>
	 * , A.sub_dcn_ip_addr<br>
	 * , A.dcn_typ_cd<br>
	 * , A.dcn_ems_typ_cd<br>
	 * , A.dcn_cot_equip_id<br>
	 * , A.dcn_clct_svr_id<br>
	 * , A.tpo_cd<br>
	 * , A.dcn_add_info<br>
	 * , A.trms_net_nms_cl_cd<br>
	 * , A.rsv_port_yn<br>
	 * , A.last_dabl_occr_dtm<br>
	 * , A.clct_svr_num<br>
	 * ) values (<br>
	 * B.dcn_num<br>
	 * , B.audit_id<br>
	 * , B.audit_dtm<br>
	 * , B.dcn_nm<br>
	 * , B.mst_dcn_ip_addr<br>
	 * , B.sub_dcn_ip_addr<br>
	 * , B.dcn_typ_cd<br>
	 * , B.dcn_ems_typ_cd<br>
	 * , B.dcn_cot_equip_id<br>
	 * , B.dcn_clct_svr_id<br>
	 * , B.tpo_cd<br>
	 * , B.dcn_add_info<br>
	 * , B.trms_net_nms_cl_cd<br>
	 * , B.rsv_port_yn<br>
	 * , B.last_dabl_occr_dtm<br>
	 * , B.clct_svr_num<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10618__From_OIV10105 = "MERGE_OIV10618__From_OIV10105";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10619 A<br>
	 * using ( select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , to_number(t.dabl_port_num) as dcn_port_num <br>
	 * , case <br>
	 * when t.dabl_port_num = t.cons_port_num then '11000000'<br>
	 * else '10000000'<br>
	 * end as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.dabl_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , to_number(t.cons_port_num) as dcn_port_num<br>
	 * , '01000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.cons_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * replace(t.conn_cl_val, 'DCN-', '') <br>
	 * as dcn_num<br>
	 * , t.snmp_port_num as dcn_port_num<br>
	 * , '10000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10104 t<br>
	 * where t.snmp_port_num > 0<br>
	 * and t.conn_cl_val like 'DCN%'<br>
	 * <br>
	 * ) B<br>
	 * on ( A.dcn_num = B.dcn_num and A.dcn_port_num = B.dcn_port_num )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.dcn_num<br>
	 * , A.dcn_port_num<br>
	 * , A.dcn_port_usg_val<br>
	 * , A.audit_id<br>
	 * , A.audit_dtm<br>
	 * , A.login_id<br>
	 * , A.login_pwd<br>
	 * , A.snmp_ver_num<br>
	 * , A.snmp_read_cmnty_nm<br>
	 * , A.snmp_write_cmnty_nm<br>
	 * , A.use_yn<br>
	 * , A.dcn_st_cd<br>
	 * , A.dcn_st_last_clct_dtm<br>
	 * , A.last_clct_dtm<br>
	 * , A.rsv_port_yn<br>
	 * ) values (<br>
	 * B.dcn_num<br>
	 * , B.dcn_port_num<br>
	 * , B.dcn_port_usg_val<br>
	 * , B.audit_id<br>
	 * , B.audit_dtm<br>
	 * , B.login_id<br>
	 * , B.login_pwd<br>
	 * , B.snmp_ver_num<br>
	 * , B.snmp_read_cmnty_nm<br>
	 * , B.snmp_write_cmnty_nm<br>
	 * , B.use_yn<br>
	 * , B.dcn_st_cd<br>
	 * , B.dcn_st_last_clct_dtm<br>
	 * , B.last_clct_dtm<br>
	 * , B.rsv_port_yn<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10619__From_OIV10104 = "MERGE_OIV10619__From_OIV10104";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10619 A<br>
	 * using ( select <br>
	 * t.ems_id as dcn_num<br>
	 * , to_number(t.dabl_port_num) as dcn_port_num <br>
	 * , case <br>
	 * when t.dabl_port_num = t.cons_port_num then '11000000'<br>
	 * else '10000000'<br>
	 * end as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10105 t<br>
	 * where t.dabl_port_num > 0<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * t.ems_id as dcn_num<br>
	 * , to_number(t.cons_port_num) as dcn_port_num<br>
	 * , '01000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10105 t<br>
	 * where t.cons_port_num > 0<br>
	 * <br>
	 * union<br>
	 * <br>
	 * select <br>
	 * t.ems_id as dcn_num<br>
	 * , t.snmp_port_num as dcn_port_num<br>
	 * , '10000000' as dcn_port_usg_val<br>
	 * , t.audit_id as audit_id<br>
	 * , t.audit_dtm as audit_dtm<br>
	 * , t.login_id as login_id<br>
	 * , t.login_pwd as login_pwd<br>
	 * , t.snmp_ver_num as snmp_ver_num<br>
	 * , t.snmp_read_cmnty_nm as snmp_read_cmnty_nm<br>
	 * , t.snmp_write_cmnty_nm as snmp_write_cmnty_nm<br>
	 * , 'Y' as use_yn<br>
	 * , '1' as dcn_st_cd<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as dcn_st_last_clct_dtm<br>
	 * , to_char(sysdate, 'yyyymmddhh24miss') as last_clct_dtm<br>
	 * , decode(t.use_ip_addr_idx_val, 1, 'N', 'Y') <br>
	 * as rsv_port_yn<br>
	 * from OIV10105 t<br>
	 * where t.snmp_port_num > 0<br>
	 * <br>
	 * ) B<br>
	 * on ( A.dcn_num = B.dcn_num and A.dcn_port_num = B.dcn_port_num )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * A.dcn_num<br>
	 * , A.dcn_port_num<br>
	 * , A.dcn_port_usg_val<br>
	 * , A.audit_id<br>
	 * , A.audit_dtm<br>
	 * , A.login_id<br>
	 * , A.login_pwd<br>
	 * , A.snmp_ver_num<br>
	 * , A.snmp_read_cmnty_nm<br>
	 * , A.snmp_write_cmnty_nm<br>
	 * , A.use_yn<br>
	 * , A.dcn_st_cd<br>
	 * , A.dcn_st_last_clct_dtm<br>
	 * , A.last_clct_dtm<br>
	 * , A.rsv_port_yn<br>
	 * ) values (<br>
	 * B.dcn_num<br>
	 * , B.dcn_port_num<br>
	 * , B.dcn_port_usg_val<br>
	 * , B.audit_id<br>
	 * , B.audit_dtm<br>
	 * , B.login_id<br>
	 * , B.login_pwd<br>
	 * , B.snmp_ver_num<br>
	 * , B.snmp_read_cmnty_nm<br>
	 * , B.snmp_write_cmnty_nm<br>
	 * , B.use_yn<br>
	 * , B.dcn_st_cd<br>
	 * , B.dcn_st_last_clct_dtm<br>
	 * , B.last_clct_dtm<br>
	 * , B.rsv_port_yn<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10619__From_OIV10105 = "MERGE_OIV10619__From_OIV10105";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.ems_id as "DCN번호" <br>
	 * , a.ems_nm as "DCN명"<br>
	 * , 'EMS접속' as "접속유형"<br>
	 * , b.tpo_nm as "정보센터"<br>
	 * , decode(a.ems_st_val, '1', '정상', '비정상') as "상태"<br>
	 * , a.EMS_TYP_VAL as "EMS구분"<br>
	 * , a.ems_ip_addr as "기본IP"<br>
	 * , a.ems_ip_addr2 as "예비IP"<br>
	 * , decode(a.use_ip_addr_idx_val, 1, '기본', '예비') as "사용IP"<br>
	 * , a.dabl_port_num as "장애포트번호"<br>
	 * , a.cons_port_num as "구성수집포트번호"<br>
	 * , nvl(a2.comm_cd_val_nm, a1.clct_nms_cl_cd) as "수집유형" <br>
	 * , nvl(a1.HOST_NM, a1.clct_svr_ip_addr) as "수집서버"<br>
	 * , a.audit_dtm as "최근작업일시"<br>
	 * , ( select count(1) from oiv10106 where ems_id = a.ems_id )<br>
	 * as "수용장비수" <br>
	 * from oiv10105 a<br>
	 * left join OCL20120 a1 on a.clct_svr_num = a1.clct_svr_num<br>
	 * left join OCO20101 a2 on a2.comm_cd = 'CLCT_NMS_CL_CD' and a2.comm_cd_val
	 * = a1.CLCT_NMS_CL_CD<br>
	 * , oiv10301 b<br>
	 * where b.tpo_cd = 'A20100' ' 고정 '<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_DCN_EMS_LIST = "SELECT_DCN_EMS_LIST";

	/**
	 * para : <br>
	 * result : RESULT_MAP=java.util.HashMap<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_id as "DCN번호"<br>
	 * , b.equip_nm as "DCN명"<br>
	 * , '장비접속' as "접속유형"<br>
	 * , c.tpo_nm as "정보센터"<br>
	 * , decode(a.conn_st_val, '1', '정상', '비정상') as "상태"<br>
	 * , '' as "EMS구분"<br>
	 * , a.equip_ip_addr as "기본IP"<br>
	 * , a.scnd_equip_ip_addr as "예비IP"<br>
	 * , decode(a.use_ip_addr_idx_val, 1, '기본', '예비') as "사용IP"<br>
	 * , a.dabl_port_num as "장애포트번호"<br>
	 * , a.cons_port_num as "구성수집포트번호"<br>
	 * , nvl(a2.comm_cd_val_nm, a1.clct_nms_cl_cd) as "수집유형" <br>
	 * , nvl(a1.HOST_NM, a1.clct_svr_ip_addr) as "수집서버"<br>
	 * , a.conn_st_chg_dtm as "최근작업일시"<br>
	 * , ( select count(1) from oiv28101 where cot_equip_id = a.equip_id )<br>
	 * as "수용장비수"<br>
	 * from oiv10104 a<br>
	 * left join OCL20120 a1 on a.clct_svr_num = a1.clct_svr_num<br>
	 * left join OCO20101 a2 on a2.comm_cd = 'CLCT_NMS_CL_CD' and a2.comm_cd_val
	 * = a1.CLCT_NMS_CL_CD<br>
	 * , oiv10100 b<br>
	 * , oiv10300 c<br>
	 * where a.equip_id = b.equip_id<br>
	 * and a.conn_cl_val != 'NONE'<br>
	 * and b.tpo_cd = c.tpo_cd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_DCN_EQUIP_LIST = "SELECT_DCN_EQUIP_LIST";

	/**
	 * para : <br>
	 * result : RESULT_MIG_MODEL_VO=com.skb.adams.mig.MigModelVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_mdl_cd<br>
	 * , a1.equip_mdl_nm<br>
	 * , CLCT_EQUIP_MDL_CD as model_id<br>
	 * , CLCT_EQUIP_MDL_nm as model_nm<br>
	 * from oiv10213 a<br>
	 * left join oiv10200 a1 on a.equip_mdl_cd = a1.equip_mdl_cd<br>
	 * where CLCT_NMS_CL_CD = '005'<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_MODEL_INFO = "SELECT_MODEL_INFO";

	/**
	 * para : <br>
	 * result : RESULT_OMN33812=com.skb.adams.teams.fxms.dbo.OMN33812<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a1.EQUIP_MDL_CD as equip_mdl_cd<br>
	 * , a.alarm_reason as CMPR_CHAR_STR_VAL<br>
	 * , max(a.alarm_reason_seq) as TRMS_NET_EQUIP_MSG_MGMT_NUM<br>
	 * , decode(max(a.alarm_level), '10000', '05', '01000', '04', '00100', '03',
	 * '00010', '02', '01' ) as DABL_GR_CD<br>
	 * , '1030003' as DABL_CD<br>
	 * , max(a.monitor_yn) as DABL_APLY_YN<br>
	 * , 'N' as EMCY_DABL_YN<br>
	 * , max(a.network_alarm_yn) as NET_DABL_APLY_YN<br>
	 * , max(network_alarm_cd) as NET_DABL_CD<br>
	 * , max(network_alarm_reason) as NET_DABL_NM<br>
	 * from tea_cf_alarm_reason_info a<br>
	 * left join oiv10213 a1 on a1.CLCT_NMS_CL_CD = '005' and
	 * a1.clct_equip_mdl_cd = a.model_id<br>
	 * left join oiv10200 a2 on a2.equip_mdl_cd = a1.CLCT_EQUIP_MDL_CD<br>
	 * where clct_oper_key_val = ( <br>
	 * select max(clct_oper_key_val) from tea_cf_alarm_reason_info<br>
	 * )<br>
	 * --and a.nms_cd = '034001' ' TEAMS '<br>
	 * group by a1.equip_mdl_cd, a.alarm_reason<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_cf_alarm_reason_info = "SELECT_cf_alarm_reason_info";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10104 A<br>
	 * using (<br>
	 * with t as ( <br>
	 * select c.equip_id as equip_id<br>
	 * , ( select decode(b.dcn_st_cd, '1', 1, '2', 1, '3', 0, null) <br>
	 * ' 0:unknown, 1:connected, 2:onnecting, 3:disconnected '<br>
	 * from oiv10619 b <br>
	 * where a.dcn_num = b.dcn_num <br>
	 * and b.use_yn = 'Y' <br>
	 * and b.dcn_st_cd != '0'<br>
	 * order by <br>
	 * dcn_st_last_clct_dtm desc <br>
	 * fetch first 1 rows only <br>
	 * )<br>
	 * as conn_st_val<br>
	 * from OIV10618 a<br>
	 * , oiv10011 b<br>
	 * , oiv10100 c<br>
	 * where a.dcn_typ_cd = 'E'<br>
	 * and b.dcn_num = a.dcn_num <br>
	 * and c.equip_id = b.equip_id<br>
	 * )<br>
	 * select equip_id as equip_id<br>
	 * , max(conn_st_val) as conn_st_val<br>
	 * from t<br>
	 * where conn_st_val is not null<br>
	 * group by <br>
	 * equip_id <br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.conn_st_val = decode(b.conn_st_val, -1, a.conn_st_val,
	 * b.conn_st_val)<br>
	 * , a.audit_id = decode(b.conn_st_val, -1, 'ADAMSTMN', 'TMNDCN')<br>
	 * , a.audit_dtm = sysdate<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10104__From_EMS_DCN = "UPDATE_OIV10104__From_EMS_DCN";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10104 A<br>
	 * using (<br>
	 * with t as ( <br>
	 * select a.dcn_cot_equip_id as equip_id<br>
	 * , ( select decode(b.dcn_st_cd, '1', 1, '2', 1, '3', 0, null) <br>
	 * ' 0:unknown, 1:connected, 2:onnecting, 3:disconnected '<br>
	 * from oiv10619 b <br>
	 * where a.dcn_num = b.dcn_num <br>
	 * and b.use_yn = 'Y' <br>
	 * and b.dcn_st_cd != '0'<br>
	 * order by <br>
	 * dcn_st_last_clct_dtm desc <br>
	 * fetch first 1 rows only <br>
	 * )<br>
	 * as conn_st_val<br>
	 * from OIV10618 a<br>
	 * where a.dcn_typ_cd = 'D'<br>
	 * and a.dcn_cot_equip_id is not null<br>
	 * )<br>
	 * select equip_id as equip_id<br>
	 * , max(conn_st_val) as conn_st_val<br>
	 * from t<br>
	 * where conn_st_val is not null<br>
	 * group by <br>
	 * equip_id <br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.conn_st_val = decode(b.conn_st_val, -1, a.conn_st_val,
	 * b.conn_st_val)<br>
	 * , a.audit_id = decode(b.conn_st_val, -1, 'ADAMSTMN', 'TMNDCN')<br>
	 * , a.audit_dtm = sysdate<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10104__From_EQUIP_DCN = "UPDATE_OIV10104__From_EQUIP_DCN";

	/**
	 * para : $getClctMthdClCd(), $getEquipMdlCd()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update oiv10200<br>
	 * set clct_mthd_cl_cd = $getClctMthdClCd()<br>
	 * , audit_id = 'SKAdams_subkjh'<br>
	 * , audit_dtm = sysdate<br>
	 * where equip_mdl_cd = $getEquipMdlCd()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10200_clctMthdClCd = "UPDATE_OIV10200_clctMthdClCd";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update oiv10200<br>
	 * set clct_mthd_cl_cd = '01'<br>
	 * , audit_id = 'SKAdams_subkjh'<br>
	 * , audit_dtm = sysdate<br>
	 * where equip_mdl_cd in (<br>
	 * select distinct c.equip_mdl_cd<br>
	 * from oiv10618 a ' dcn기본 ' <br>
	 * , oiv10011 b<br>
	 * , oiv10100 c<br>
	 * where a.dcn_typ_cd = 'E'<br>
	 * and b.dcn_num = a.dcn_num<br>
	 * and c.equip_id = b.equip_id<br>
	 * and a.dcn_ems_typ_cd is not null<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10200_clctMthdClCd__EMS = "UPDATE_OIV10200_clctMthdClCd__EMS";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OMN33812 A<br>
	 * using ( <br>
	 * select a1.EQUIP_MDL_CD as equip_mdl_cd<br>
	 * , a.alarm_reason as CMPR_CHAR_STR_VAL<br>
	 * , max(a.alarm_reason_seq) as TRMS_NET_EQUIP_MSG_MGMT_NUM<br>
	 * , decode(max(a.alarm_level), '10000', '05', '01000', '04', '00100', '03',
	 * '00010', '02', '01' ) as DABL_GR_CD<br>
	 * , '1030003' as DABL_CD<br>
	 * , max(a.monitor_yn) as DABL_APLY_YN<br>
	 * , 'N' as EMCY_DABL_YN<br>
	 * , 'Y' as NET_DABL_APLY_YN<br>
	 * , max(network_alarm_cd) as NET_DABL_CD<br>
	 * , max(network_alarm_reason) as NET_DABL_NM<br>
	 * from tea_cf_alarm_reason_info a<br>
	 * left join oiv10213 a1 on a1.CLCT_NMS_CL_CD = '005' and
	 * a1.clct_equip_mdl_cd = a.model_id<br>
	 * left join oiv10200 a2 on a2.equip_mdl_cd = a1.CLCT_EQUIP_MDL_CD<br>
	 * inner join (select a.equip_mdl_cd from oiv10100 a inner join oiv10011 b
	 * on a.equip_id = b.equip_id and a.del_yn = 'N' group by a.equip_mdl_cd
	 * having count(1) > 0) temp_tab on a1.EQUIP_MDL_CD = temp_tab.equip_mdl_cd<br>
	 * where clct_oper_key_val = ( select max(clct_oper_key_val) from
	 * tea_cf_alarm_reason_info )<br>
	 * group by a1.equip_mdl_cd, a.alarm_reason<br>
	 * ) B<br>
	 * on ( a.equip_mdl_cd = b.equip_mdl_cd and a.cmpr_char_str_val =
	 * b.cmpr_char_str_val )<br>
	 * when not matched then<br>
	 * insert ( <br>
	 * trms_net_equip_msg_mgmt_num ' 전송망장비메시지관리번호 ' <br>
	 * , audit_id ' 최종변경자id ' <br>
	 * , audit_dtm ' 최종변경일시 ' <br>
	 * , equip_mdl_cd ' 장비모델코드 ' <br>
	 * , cmpr_char_str_val ' 비교문자열값 ' <br>
	 * , dabl_gr_cd ' 장애등급코드 ' <br>
	 * , dabl_cd ' 장애코드 ' <br>
	 * , dabl_aply_yn ' 장애적용여부 ' <br>
	 * , emcy_dabl_yn ' 긴급장애여부 ' <br>
	 * , net_dabl_aply_yn ' 망장애적용여부 ' <br>
	 * , net_dabl_cd ' 망장애코드 ' <br>
	 * , net_dabl_nm ' 망장애명 ' <br>
	 * , dabl_src_ctt ' 장애원인내용 ' <br>
	 * , dabl_solv_mthd_ctt ' 장애해결방법내용 ' <br>
	 * , syslog_rcv_yn ' syslog수신여부 ' <br>
	 * , cmpr_char_str_term_val ' 비교문자열해지값 ' <br>
	 * ) values (<br>
	 * b.trms_net_equip_msg_mgmt_num<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , b.equip_mdl_cd<br>
	 * , b.cmpr_char_str_val<br>
	 * , b.dabl_gr_cd<br>
	 * , b.dabl_cd<br>
	 * , b.dabl_aply_yn<br>
	 * , b.emcy_dabl_yn<br>
	 * , b.net_dabl_aply_yn<br>
	 * , b.net_dabl_cd<br>
	 * , b.net_dabl_nm<br>
	 * , null<br>
	 * , null<br>
	 * , 'N'<br>
	 * , null<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OMN33812__From_CF_ALARM_REASON_INFO = "UPDATE_OMN33812__From_CF_ALARM_REASON_INFO";

}
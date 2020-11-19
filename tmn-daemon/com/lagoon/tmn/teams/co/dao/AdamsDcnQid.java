package com.lagoon.tmn.teams.co.dao;

/**
 * File : adams_dcn.xml<br>
 * 
 * @since 20191210152147
 * @author subkjh
 *
 */

public class AdamsDcnQid {

	/** 쿼리 모임 화일명. adams_dcn.xml */
	public static final String QUERY_XML_FILE = "adams_dcn.xml";

	public AdamsDcnQid() {
	}

	/**
	 * para : $getEmsId(), $getEquipId(), $getEmsEquipNum(), $isAutoMgmtYn(),
	 * $getEmsInrEquipKeyVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10106 (<br>
	 * EMS_ID<br>
	 * , EQUIP_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EMS_EQUIP_NUM<br>
	 * , AUTO_MGMT_YN<br>
	 * , AUTO_MGMT_DTM<br>
	 * , EMS_INR_EQUIP_KEY_VAL<br>
	 * ) values (<br>
	 * $getEmsId()<br>
	 * , $getEquipId()<br>
	 * , 'ADAMS-TMN'<br>
	 * , sysdate<br>
	 * , $getEmsEquipNum()<br>
	 * , $isAutoMgmtYn()<br>
	 * , sysdate<br>
	 * , $getEmsInrEquipKeyVal()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10106 = "INSERT_OIV10106";

	/**
	 * para : #equipMdlCd<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * MERGE INTO <br>
	 * OIV10107 a<br>
	 * USING <br>
	 * (SELECT aa.equip_id, aa.equip_ip_addr, aa.equip_sys_nm, aa.equip_mdl_cd,
	 * aa.equip_tid_val, aa.equip_mgmt_num, aa.equip_nm FROM oiv10100 aa INNER
	 * JOIN oiv10011 bb ON aa.equip_id = bb.equip_id AND aa.del_yn = 'N' and
	 * where equip_mdl_cd in ( #equipMdlCd ) ) b<br>
	 * ON <br>
	 * (a.equip_id = b.equip_id and a.clct_nms_cl_cd = '005' and
	 * a.clct_equip_num = b.equip_id)<br>
	 * WHEN MATCHED THEN<br>
	 * UPDATE SET <br>
	 * a.AUDIT_DTM = sysdate<br>
	 * WHEN NOT MATCHED THEN<br>
	 * INSERT <br>
	 * (Equip_mapp_ser_num ' 장비매핑일련번호 '<br>
	 * , audit_id ' 최종변경자id ' <br>
	 * , audit_dtm ' 최종변경일시 ' <br>
	 * , equip_id ' 장비id ' <br>
	 * , equip_mgmt_num ' 장비관리번호 ' <br>
	 * , equip_tid_val ' 장비tid값 ' <br>
	 * , clct_nms_cl_cd ' 수집nms구분코드 ' <br>
	 * , clct_equip_num ' 수집장비번호 ' <br>
	 * , clct_tbl_nm ' 수집테이블명 ' <br>
	 * , clct_equip_ip_addr ' 수집장비ip주소 ' <br>
	 * , clct_equip_nm ' 수집장비명 ' <br>
	 * , clct_equip_sys_nm ' 수집장비시스템명 ' <br>
	 * , clct_equip_aply_yn ' 수집장비반영여부 '<br>
	 * ) values (<br>
	 * oiv10107_s1.nextval<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , b.equip_id<br>
	 * , b.equip_mgmt_num<br>
	 * , b.equip_tid_val<br>
	 * , '005'<br>
	 * , b.equip_id<br>
	 * , 'OIV10100'<br>
	 * , b.equip_ip_addr<br>
	 * , b.equip_nm<br>
	 * , b.equip_sys_nm<br>
	 * , 'Y'<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10107__From_OIV10100 = "MERGE_OIV10107__From_OIV10100";

	/**
	 * para : #equipMdlCd<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * MERGE INTO <br>
	 * OIV10213 a<br>
	 * USING <br>
	 * (select equip_mdl_cd, equip_mdl_nm, equip_typ_cd, olvl_yn from OIV10200
	 * where equip_mdl_cd in ( #equipMdlCd ) ) b<br>
	 * ON <br>
	 * (a.equip_mdl_cd = b.equip_mdl_cd and a.clct_nms_cl_cd = '005' and
	 * a.clct_equip_mdl_cd = b.equip_mdl_cd)<br>
	 * WHEN MATCHED THEN<br>
	 * UPDATE SET <br>
	 * a.AUDIT_DTM = sysdate<br>
	 * WHEN NOT MATCHED THEN<br>
	 * INSERT<br>
	 * (mdl_mapp_ser_num ' 모델매핑일련번호 ' <br>
	 * , audit_id ' 최종변경자id ' <br>
	 * , audit_dtm ' 최종변경일시 ' <br>
	 * , equip_mdl_cd ' 장비모델코드 ' <br>
	 * , clct_nms_cl_cd ' 수집nms구분코드 ' <br>
	 * , clct_equip_mdl_cd ' 수집장비모델코드 ' <br>
	 * , clct_equip_mdl_nm ' 수집장비모델명 ' <br>
	 * , clct_equip_typ_cd ' 수집장비종류코드 ' <br>
	 * , clct_tbl_nm ' 수집테이블명 ' <br>
	 * , clct_equip_olvl_yn ' 수집장비광레벨여부 '<br>
	 * ) values (<br>
	 * oiv10213_s1.nextval<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , b.equip_mdl_cd<br>
	 * , '005'<br>
	 * , b.equip_mdl_cd<br>
	 * , b.equip_mdl_nm<br>
	 * , b.equip_typ_cd<br>
	 * , 'OIV10200'<br>
	 * , b.olvl_yn<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10213__From_OIV10200 = "MERGE_OIV10213__From_OIV10200";

	/**
	 * para : #equipMdlCd$equipMfactCd<br>
	 * result : RESULT_ADAMS_EQUIP_VO=com.skb.adams.teams.co.vo.AdamsEquipVo<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_id<br>
	 * , a.equip_ip_addr<br>
	 * , a.equip_mdl_cd<br>
	 * , b.equip_mdl_nm<br>
	 * , a.equip_tid_val<br>
	 * , a.sw_ver_info<br>
	 * , a1.ems_id<br>
	 * , a1.ems_inr_equip_key_val<br>
	 * from oiv10100 a<br>
	 * left join oiv10106 a1 on a1.equip_id = a.equip_id<br>
	 * left join oiv10105 a2 on a2.ems_id = a1.ems_id<br>
	 * , oiv10200 b<br>
	 * where a.del_yn = 'N'<br>
	 * and a.equip_mdl_cd = b.equip_mdl_cd<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * and b.equip_mfact_cd = $equipMfactCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_ADAMS_EQUIP__BY_equipMdlCd = "SELECT_ADAMS_EQUIP__BY_equipMdlCd";

	/**
	 * para : #emsTypVal, $clctSvrIpAddr<br>
	 * result : RESULT_EMS_DCN=com.skb.adams.teams.co.vo.EmsDcn<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select nvl(CONS_PORT_NUM, -1) as CONS_PORT_NUM<br>
	 * , nvl(DABL_PORT_NUM, -1) as DABL_PORT_NUM<br>
	 * , EMS_ID<br>
	 * , EMS_IP_ADDR<br>
	 * , EMS_IP_ADDR2<br>
	 * , USE_IP_ADDR_IDX_VAL<br>
	 * , EMS_TYP_VAL<br>
	 * , LOGIN_ID<br>
	 * , LOGIN_PWD<br>
	 * , nvl(PRFM_PORT_NUM, -1) as PRFM_PORT_NUM<br>
	 * , nvl(SNMP_PORT_NUM, -1) as SNMP_PORT_NUM<br>
	 * , SNMP_READ_CMNTY_NM<br>
	 * , SNMP_WRITE_CMNTY_NM<br>
	 * from oiv10105 a<br>
	 * , OCL20120 b <br>
	 * where a.ems_typ_val in ( #emsTypVal )<br>
	 * and a.clct_svr_num = b.clct_svr_num<br>
	 * and b.clct_svr_ip_addr = $clctSvrIpAddr<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_EMS_DCN__BY_emsTypVal = "SELECT_EMS_DCN__BY_emsTypVal";

	/**
	 * para : $clctSvrIpAddr#equipMdlCd$equipMfactCd$equipTidVal$equipIpAddr<br>
	 * result : RESULT_EQUIP_DCN=com.skb.adams.teams.co.vo.EquipDcn<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select nvl(b.CONS_PORT_NUM, -1) as CONS_PORT_NUM<br>
	 * , nvl(b.DABL_PORT_NUM, -1) as DABL_PORT_NUM<br>
	 * , b.EQUIP_ID<br>
	 * , b.EQUIP_ID as COT_EQUIP_ID<br>
	 * , nvl(b.EQUIP_IP_ADDR, a.equip_ip_addr) <br>
	 * as EQUIP_IP_ADDR<br>
	 * , a.EQUIP_MDL_CD<br>
	 * , c.equip_mdl_nm<br>
	 * , a.EQUIP_TID_VAL<br>
	 * , b.LOGIN_ID<br>
	 * , b.LOGIN_PWD<br>
	 * , nvl(b.PRFM_PORT_NUM, -1) as PRFM_PORT_NUM<br>
	 * , nvl(b.SNMP_PORT_NUM, -1) as SNMP_PORT_NUM<br>
	 * , b.SNMP_READ_CMNTY_NM<br>
	 * , b.SNMP_WRITE_CMNTY_NM<br>
	 * from oiv10100 a<br>
	 * , oiv10104 b<br>
	 * , oiv10200 c<br>
	 * , OCL20120 d<br>
	 * where a.equip_id = b.equip_id<br>
	 * and a.del_yn = 'N'<br>
	 * and a.equip_mdl_cd = c.equip_mdl_cd<br>
	 * and d.clct_svr_num = b.clct_svr_num<br>
	 * and d.clct_svr_ip_addr = $clctSvrIpAddr<br>
	 * <br>
	 * and a.equip_mdl_cd in ( #equipMdlCd )<br>
	 * <br>
	 * and c.EQUIP_MFACT_CD = $equipMfactCd<br>
	 * <br>
	 * and a.EQUIP_TID_VAL = $equipTidVal<br>
	 * <br>
	 * and a.EQUIP_IP_ADDR = $equipIpAddr<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_EQUIP_DCN__BY_equipMfactCd_equipMdlCd = "SELECT_EQUIP_DCN__BY_equipMfactCd_equipMdlCd";

	/**
	 * para : $clctSvrIpAddr#equipMdlCd$equipMfactCd<br>
	 * result : RESULT_EQUIP_DCN=com.skb.adams.teams.co.vo.EquipDcn<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select nvl(b.CONS_PORT_NUM, -1) as CONS_PORT_NUM<br>
	 * , nvl(b.DABL_PORT_NUM, -1) as DABL_PORT_NUM<br>
	 * , b.EQUIP_ID<br>
	 * , a.EQUIP_IP_ADDR<br>
	 * , a.EQUIP_MDL_CD<br>
	 * , c.equip_mdl_nm<br>
	 * , a.EQUIP_TID_VAL<br>
	 * , b.LOGIN_ID<br>
	 * , b.LOGIN_PWD<br>
	 * , nvl(b.PRFM_PORT_NUM, -1) as PRFM_PORT_NUM<br>
	 * , nvl(b.SNMP_PORT_NUM, -1) as SNMP_PORT_NUM<br>
	 * , b.SNMP_READ_CMNTY_NM<br>
	 * , b.SNMP_WRITE_CMNTY_NM<br>
	 * , e.cot_equip_id as COT_EQUIP_ID<br>
	 * from oiv10100 a<br>
	 * , oiv10104 b<br>
	 * , oiv10200 c<br>
	 * , OCL20120 d <br>
	 * , oiv28101 e ' 전송망 COT기준장비 ' <br>
	 * , oiv10011 f<br>
	 * where a.equip_id = b.equip_id<br>
	 * and a.del_yn = 'N'<br>
	 * and a.equip_mdl_cd = c.equip_mdl_cd <br>
	 * and d.clct_svr_num = b.clct_svr_num<br>
	 * and d.clct_svr_ip_addr = $clctSvrIpAddr<br>
	 * and f.equip_id = a.equip_id<br>
	 * and f.egov_bb_typ_cd = 'R' ' RT '<br>
	 * and e.equip_id = a.equip_id<br>
	 * and a.equip_ip_addr is not null<br>
	 * <br>
	 * and a.EQUIP_MDL_CD in ( #equipMdlCd )<br>
	 * <br>
	 * and c.EQUIP_MFACT_CD = $equipMfactCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_EQUIP_RT__BY_equipMfactCd_equipMdlCd = "SELECT_EQUIP_RT__BY_equipMfactCd_equipMdlCd";

	/**
	 * para : $useIpAddrIdxVal, $emsId<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update oiv10105 set <br>
	 * USE_IP_ADDR_IDX_VAL = $useIpAddrIdxVal<br>
	 * , AUDIT_ID = 'ADAMS-TMN'<br>
	 * , AUDIT_DTM = sysdate<br>
	 * where EMS_ID = $emsId<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10105__BY_emsId = "UPDATE_OIV10105__BY_emsId";

}
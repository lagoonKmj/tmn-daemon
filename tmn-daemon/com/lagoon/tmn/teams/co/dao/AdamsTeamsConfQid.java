package com.lagoon.tmn.teams.co.dao;

/**
 * File : adams_teams_conf.xml<br>
 * 
 * @since 20200120170802
 * @author subkjh
 *
 */

public class AdamsTeamsConfQid {

	/** 쿼리 모임 화일명. adams_teams_conf.xml */
	public static final String QUERY_XML_FILE = "adams_teams_conf.xml";

	public AdamsTeamsConfQid() {
	}

	/**
	 * para : $getEquipId(), $getEquipConsItmId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OIV10210<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * and EQUIP_CONS_ITM_ID = $getEquipConsItmId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OIV10210__BY_equipId_equipConsItmId = "DELETE_OIV10210__BY_equipId_equipConsItmId";

	/**
	 * para : $equipId, $auditDtm<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OIV10221<br>
	 * where EQUIP_ID = $equipId<br>
	 * and AUDIT_DTM < $auditDtm<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OIV10221__BY_equipId_auditDtm = "DELETE_OIV10221__BY_equipId_auditDtm";

	/**
	 * para : $equipId<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OIV10223<br>
	 * where EQUIP_ID = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OIV10223__BY_equipId = "DELETE_OIV10223__BY_equipId";

	/**
	 * para : $getEquipId(), $getEquipPortId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OIV10400<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * and EQUIP_PORT_ID = $getEquipPortId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OIV10400__BY_equipId_equipPortId = "DELETE_OIV10400__BY_equipId_equipPortId";

	/**
	 * para : $equipId<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OIV10413 a<br>
	 * where a.equip_id = $equipId<br>
	 * and not exists ( select 1 from oiv10400 b where a.equip_id = b.equip_id
	 * and a.equip_port_id = b.equip_port_id )<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OIV10413__BY_equipId = "DELETE_OIV10413__BY_equipId";

	/**
	 * para : $getEquipId(), $getEquipAttrCd(), $getAttrVal(), $getBfAttrVal(),
	 * $getEquipAttrChgClCd()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10110 (<br>
	 * EQUIP_ID<br>
	 * , EQUIP_ATTR_CD<br>
	 * , CHG_DTM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , ATTR_VAL<br>
	 * , BF_ATTR_VAL<br>
	 * , EQUIP_ATTR_BAT_HST_SER_NUM<br>
	 * , EQUIP_ATTR_CHG_CL_CD<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getEquipAttrCd()<br>
	 * , sysdate<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getAttrVal()<br>
	 * , $getBfAttrVal()<br>
	 * , 0<br>
	 * , $getEquipAttrChgClCd()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10110 = "INSERT_OIV10110";

	/**
	 * para : $getEquipId(), $getClctDtm(), $getEquipIpAddr(),
	 * $getEquipAddInfo(), $getEquipUptimeVal(), $getEquipUptimeValChgDtm(),
	 * $getEquipSysNm(), $getEquipSetLocDesc(), $getOperEquipMdlCd(),
	 * $getFwVerInfo(), $getPingStVal(), $getPingStChgDtm(), $getSnmpStVal(),
	 * $getSnmpStChgDtm(), $getBtgImgFileNm(), $getReLodRsnCtt(),
	 * $getEquipChrgrCntcPlcInfo(), $getEquipSvcCnt(), $isFwVerAgrmtYn(),
	 * $getPromptInfo(), $getMfactSerNum(), $getEquipVerInfo(),
	 * $getTrapRcvDtm(), $getOidDesc(), $getSysLogRcvDtm(), $getVrfPingStVal(),
	 * $getVrfPingStChgDtm(), $getLastDablRcvDtm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10178 (<br>
	 * EQUIP_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , CLCT_DTM<br>
	 * , EQUIP_IP_ADDR<br>
	 * , EQUIP_ADD_INFO<br>
	 * , EQUIP_UPTIME_VAL<br>
	 * , EQUIP_UPTIME_VAL_CHG_DTM<br>
	 * , EQUIP_SYS_NM<br>
	 * , EQUIP_SET_LOC_DESC<br>
	 * , OPER_EQUIP_MDL_CD<br>
	 * , FW_VER_INFO<br>
	 * , PING_ST_VAL<br>
	 * , PING_ST_CHG_DTM<br>
	 * , SNMP_ST_VAL<br>
	 * , SNMP_ST_CHG_DTM<br>
	 * , BTG_IMG_FILE_NM<br>
	 * , RE_LOD_RSN_CTT<br>
	 * , EQUIP_CHRGR_CNTC_PLC_INFO<br>
	 * , EQUIP_SVC_CNT<br>
	 * , FW_VER_AGRMT_YN<br>
	 * , PROMPT_INFO<br>
	 * , MFACT_SER_NUM<br>
	 * , EQUIP_VER_INFO<br>
	 * , TRAP_RCV_DTM<br>
	 * , OID_DESC<br>
	 * , SYS_LOG_RCV_DTM<br>
	 * , VRF_PING_ST_VAL<br>
	 * , VRF_PING_ST_CHG_DTM<br>
	 * , LAST_DABL_RCV_DTM<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getClctDtm()<br>
	 * , $getEquipIpAddr()<br>
	 * , $getEquipAddInfo()<br>
	 * , $getEquipUptimeVal()<br>
	 * , $getEquipUptimeValChgDtm()<br>
	 * , $getEquipSysNm()<br>
	 * , $getEquipSetLocDesc()<br>
	 * , $getOperEquipMdlCd()<br>
	 * , $getFwVerInfo()<br>
	 * , $getPingStVal()<br>
	 * , $getPingStChgDtm()<br>
	 * , $getSnmpStVal()<br>
	 * , $getSnmpStChgDtm()<br>
	 * , $getBtgImgFileNm()<br>
	 * , $getReLodRsnCtt()<br>
	 * , $getEquipChrgrCntcPlcInfo()<br>
	 * , $getEquipSvcCnt()<br>
	 * , $isFwVerAgrmtYn()<br>
	 * , $getPromptInfo()<br>
	 * , $getMfactSerNum()<br>
	 * , $getEquipVerInfo()<br>
	 * , $getTrapRcvDtm()<br>
	 * , $getOidDesc()<br>
	 * , $getSysLogRcvDtm()<br>
	 * , $getVrfPingStVal()<br>
	 * , $getVrfPingStChgDtm()<br>
	 * , $getLastDablRcvDtm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10178 = "INSERT_OIV10178";

	/**
	 * para : $getEquipConsItmCd(), $getEquipMfactCd(), $getConsItmMdlNm(),
	 * $getConsItmTypCd(), $getEquipConsItmTypCd(), $getEquipMdlCd(),
	 * $getPortCnt(), $getStrdVerInfo()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10209 (<br>
	 * EQUIP_CONS_ITM_CD<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EQUIP_MFACT_CD<br>
	 * , CONS_ITM_MDL_NM<br>
	 * , CONS_ITM_TYP_CD<br>
	 * , EQUIP_CONS_ITM_TYP_CD<br>
	 * , EQUIP_MDL_CD<br>
	 * , PORT_CNT<br>
	 * , PORT_CRE_YN<br>
	 * , PORT_STA_NUM<br>
	 * , CARD_TRMS_NET_CAPA_CL_CD<br>
	 * , PORT_TRMS_NET_CAPA_CL_CD<br>
	 * , NMS_USE_YN<br>
	 * , AUTO_MGMT_YN<br>
	 * , STRD_VER_INFO<br>
	 * ) values (<br>
	 * $getEquipConsItmCd()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getEquipMfactCd()<br>
	 * , $getConsItmMdlNm()<br>
	 * , $getConsItmTypCd()<br>
	 * , $getEquipConsItmTypCd()<br>
	 * , $getEquipMdlCd()<br>
	 * , $getPortCnt()<br>
	 * , 'N'<br>
	 * , null<br>
	 * , null<br>
	 * , null<br>
	 * , 'Y'<br>
	 * , 'Y'<br>
	 * , $getStrdVerInfo()<br>
	 * <br>
	 * <br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10209 = "INSERT_OIV10209";

	/**
	 * para : $getEquipConsItmId(), $getSupEquipConsItmId(),
	 * $getEquipConsItmCd(), $getEquipId(), $getConsItmNum(),
	 * $getEquipConsItmNm(), $getMfactSerNum(), $getEquipConsItmDesc(),
	 * $getFrntBackClCd(), $getPortCnt(), $isPollingMObjYn(), $getHostNm(),
	 * $getMemo(), $getEquipConsItmAlsNm(), $getTrmsNetEquipCapaClCd(),
	 * $isAltPsblYn(), $getBkEquipId(), $getBkEquipConsItmId(),
	 * $getEquipConsItmStVal(), $getEquipConsItmSubStVal(), $getRgstDtm(),
	 * $getWvlengVal(), $getEquipConsItmUsgCd(), $getRepIpAddr(), $getVerInfo()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10210 (<br>
	 * EQUIP_CONS_ITM_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , SUP_EQUIP_CONS_ITM_ID<br>
	 * , EQUIP_CONS_ITM_CD<br>
	 * , EQUIP_ID<br>
	 * , CONS_ITM_NUM<br>
	 * , EQUIP_CONS_ITM_NM<br>
	 * , MFACT_SER_NUM<br>
	 * , EQUIP_CONS_ITM_DESC<br>
	 * , FRNT_BACK_CL_CD<br>
	 * , PORT_CNT<br>
	 * , POLLING_M_OBJ_YN<br>
	 * , HOST_NM<br>
	 * , MEMO<br>
	 * , EQUIP_CONS_ITM_ALS_NM<br>
	 * , TRMS_NET_EQUIP_CAPA_CL_CD<br>
	 * , ALT_PSBL_YN<br>
	 * , BK_EQUIP_ID<br>
	 * , BK_EQUIP_CONS_ITM_ID<br>
	 * , EQUIP_CONS_ITM_ST_VAL<br>
	 * , EQUIP_CONS_ITM_SUB_ST_VAL<br>
	 * , AUTO_MGMT_YN<br>
	 * , RGST_DTM<br>
	 * , WVLENG_VAL<br>
	 * , EQUIP_CONS_ITM_USG_CD<br>
	 * , REP_IP_ADDR<br>
	 * , VER_INFO<br>
	 * , DATA_RGST_MTHD_CL_CD<br>
	 * ) values (<br>
	 * $getEquipConsItmId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getSupEquipConsItmId()<br>
	 * , $getEquipConsItmCd()<br>
	 * , $getEquipId()<br>
	 * , $getConsItmNum()<br>
	 * , $getEquipConsItmNm()<br>
	 * , $getMfactSerNum()<br>
	 * , $getEquipConsItmDesc()<br>
	 * , $getFrntBackClCd()<br>
	 * , $getPortCnt()<br>
	 * , $isPollingMObjYn()<br>
	 * , $getHostNm()<br>
	 * , $getMemo()<br>
	 * , $getEquipConsItmAlsNm()<br>
	 * , $getTrmsNetEquipCapaClCd()<br>
	 * , $isAltPsblYn()<br>
	 * , $getBkEquipId()<br>
	 * , $getBkEquipConsItmId()<br>
	 * , $getEquipConsItmStVal()<br>
	 * , $getEquipConsItmSubStVal()<br>
	 * , 'Y'<br>
	 * , $getRgstDtm()<br>
	 * , $getWvlengVal()<br>
	 * , $getEquipConsItmUsgCd()<br>
	 * , $getRepIpAddr()<br>
	 * , $getVerInfo()<br>
	 * , '10'<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10210 = "INSERT_OIV10210";

	/**
	 * para : $getEquipMdlCd(), $getEquipConsItmNm(), $getEquipConsItmTypVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10222 (<br>
	 * EQUIP_MDL_CD<br>
	 * , EQUIP_CONS_ITM_NM<br>
	 * , EQUIP_CONS_ITM_TYP_VAL<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * ) values (<br>
	 * $getEquipMdlCd()<br>
	 * , $getEquipConsItmNm()<br>
	 * , $getEquipConsItmTypVal()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10222 = "INSERT_OIV10222";

	/**
	 * para : $getEquipId(), $getEquipConsItmNm(), $getPortDesc(),
	 * $getPortSpeedCd(), $getClctDtm(), $getEquipPortId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10223 (<br>
	 * EQUIP_ID<br>
	 * , EQUIP_CONS_ITM_NM<br>
	 * , PORT_DESC<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , PORT_SPEED_CD<br>
	 * , CLCT_DTM<br>
	 * , EQUIP_PORT_ID<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getEquipConsItmNm()<br>
	 * , $getPortDesc()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getPortSpeedCd()<br>
	 * , $getClctDtm()<br>
	 * , $getEquipPortId()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10223 = "INSERT_OIV10223";

	/**
	 * para : $getEquipId(), $getEquipPortId(), $getAuditDtm(),
	 * $getEquipConsItmId(), $getMounLocNum(), $getPortNum(), $getPortAlsNm(),
	 * $getPortTypCd(), $getPortSpeedCd(), $isPollingMObjYn(), $isPingMObjYn(),
	 * $getRgstCycl(), $getPortDesc()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10400 (<br>
	 * EQUIP_ID<br>
	 * , EQUIP_PORT_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EQUIP_CONS_ITM_ID<br>
	 * , MOUN_LOC_NUM<br>
	 * , PORT_NUM<br>
	 * , PORT_ALS_NM<br>
	 * , PORT_TYP_CD<br>
	 * , PORT_SPEED_CD<br>
	 * , POLLING_M_OBJ_YN<br>
	 * , PING_M_OBJ_YN<br>
	 * , RGST_CYCL<br>
	 * , PORT_DESC<br>
	 * , DATA_RGST_MTHD_CL_CD<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getEquipPortId()<br>
	 * , 'ADAMSTMN'<br>
	 * , $getAuditDtm()<br>
	 * , $getEquipConsItmId()<br>
	 * , $getMounLocNum()<br>
	 * , $getPortNum()<br>
	 * , $getPortAlsNm()<br>
	 * , $getPortTypCd()<br>
	 * , $getPortSpeedCd()<br>
	 * , $isPollingMObjYn()<br>
	 * , $isPingMObjYn()<br>
	 * , $getRgstCycl()<br>
	 * , $getPortDesc()<br>
	 * , '10'<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10400 = "INSERT_OIV10400";

	/**
	 * para : $getEquipId(), $getEquipPortId(), $isBkPortSetYn(),
	 * $getBkEquipId(), $getBkEquipPortId(), $isAltPsblYn(), $getPortStVal(),
	 * $getPortSubStVal(), $getPortNm(), $getPortUsgCd(), $getClctDtm(),
	 * $getUpmvMdlnVal(), $getDnmvMdlnVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OIV10413 (<br>
	 * EQUIP_ID<br>
	 * , EQUIP_PORT_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , BK_PORT_SET_YN<br>
	 * , BK_EQUIP_ID<br>
	 * , BK_EQUIP_PORT_ID<br>
	 * , ALT_PSBL_YN<br>
	 * , AUTO_MGMT_YN<br>
	 * , PORT_ST_VAL<br>
	 * , PORT_SUB_ST_VAL<br>
	 * , PORT_NM<br>
	 * , PORT_USG_CD<br>
	 * , CLCT_DTM<br>
	 * , UPMV_MDLN_VAL<br>
	 * , DNMV_MDLN_VAL<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getEquipPortId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $isBkPortSetYn()<br>
	 * , $getBkEquipId()<br>
	 * , $getBkEquipPortId()<br>
	 * , $isAltPsblYn()<br>
	 * , 'Y'<br>
	 * , $getPortStVal()<br>
	 * , $getPortSubStVal()<br>
	 * , $getPortNm()<br>
	 * , $getPortUsgCd()<br>
	 * , $getClctDtm()<br>
	 * , $getUpmvMdlnVal()<br>
	 * , $getDnmvMdlnVal()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OIV10413 = "INSERT_OIV10413";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * MERGE INTO OIV10011 a<br>
	 * USING ( select equip_id from OIV10100<br>
	 * where<br>
	 * equip_typ_cl_cd in(
	 * '01','02','03','04','05','13','24','25','26','27','49','51','80','81','82','83','84','85','86','89','90','91','92','94','98'
	 * )<br>
	 * ) b<br>
	 * ON (a.equip_id = b.equip_id)<br>
	 * WHEN NOT MATCHED THEN<br>
	 * INSERT<br>
	 * (EQUIP_ID<br>
	 * ,AUDIT_ID<br>
	 * ,AUDIT_DTM<br>
	 * ,RGST_DTM<br>
	 * ,CLCT_SVR_CL_CD<br>
	 * ,DFLT_USE_YN<br>
	 * ,AUTO_SET_YN<br>
	 * ,HST_MGMT_YN<br>
	 * ,THRS_USE_YN<br>
	 * ,PRFM_DABL_MGMT_YN<br>
	 * ,PRFM_CLCT_CYCL<br>
	 * ,PWD_ACTVN_YN<br>
	 * ,SSH_USE_YN<br>
	 * ,RECNT_CONN_DTM<br>
	 * ,LOGIN_RSLT_CD<br>
	 * ,QLTY_MNTR_YN<br>
	 * ,LINE_NUM_CLCT_YN<br>
	 * ,EGOV_BB_YN<br>
	 * ,AON_YN<br>
	 * ,CHG_OPER_SCHD_YN<br>
	 * ) values(<br>
	 * b.equip_id<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , sysdate<br>
	 * , '0'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , '0'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , sysdate<br>
	 * , '0'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10011 = "MERGE_OIV10011";

	/**
	 * para : $getEquipId(), $getEmsId(), $getEquipId(), $getEmsEquipNum(),
	 * $getEmsInrEquipKeyVal(), $getEmsId(), $getEmsEquipNum(),
	 * $getEmsInrEquipKeyVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10106 a<br>
	 * using (<br>
	 * select<br>
	 * $getEquipId() as EQUIP_ID<br>
	 * from dual<br>
	 * ) b<br>
	 * on ( a.equip_id = b.equip_id )<br>
	 * when not matched then<br>
	 * insert (<br>
	 * a.ems_id<br>
	 * , a.equip_id<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * , a.ems_equip_num<br>
	 * , a.auto_mgmt_yn<br>
	 * , a.auto_mgmt_dtm<br>
	 * , a.ems_inr_equip_key_val<br>
	 * ) values (<br>
	 * $getEmsId()<br>
	 * , $getEquipId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getEmsEquipNum()<br>
	 * , 'Y'<br>
	 * , sysdate<br>
	 * , $getEmsInrEquipKeyVal()<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.audit_id = 'ADAMSTMN'<br>
	 * , a.audit_dtm = sysdate<br>
	 * , a.ems_id = $getEmsId()<br>
	 * , a.ems_equip_num = $getEmsEquipNum()<br>
	 * , a.auto_mgmt_yn = 'Y'<br>
	 * , a.auto_mgmt_dtm = sysdate<br>
	 * , a.ems_inr_equip_key_val = $getEmsInrEquipKeyVal()<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10106__By_equipId = "MERGE_OIV10106__By_equipId";

	/**
	 * para : <br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * MERGE INTO OIV10115 a<br>
	 * USING ( select equip_id from OIV10100<br>
	 * where<br>
	 * equip_typ_cl_cd in(
	 * '01','02','03','04','05','13','24','25','26','27','49','51','80','81','82','83','84','85','86','89','90','91','92','94','98'
	 * )<br>
	 * ) b<br>
	 * ON (a.equip_id = b.equip_id and a.net_cl_cd='02')<br>
	 * WHEN NOT MATCHED THEN<br>
	 * INSERT<br>
	 * (net_cl_cd<br>
	 * , equip_id<br>
	 * , audit_id<br>
	 * , audit_dtm<br>
	 * ) values(<br>
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
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10210 a<br>
	 * using (<br>
	 * select<br>
	 * equip_id<br>
	 * , equip_cons_itm_id<br>
	 * , ver_info<br>
	 * from oiv10221<br>
	 * where equip_id = $getEquipId()<br>
	 * and ver_info is not null<br>
	 * ) b<br>
	 * on ( a.equip_id = b.equip_id<br>
	 * and a.equip_cons_itm_id = b.equip_cons_itm_id<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set a.ver_info = b.ver_info<br>
	 * , audit_id = 'ADAMSTMN'<br>
	 * , audit_dtm = sysdate<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10210__By_OIV10221 = "MERGE_OIV10210__By_OIV10221";

	/**
	 * para : $getEquipId(), $getEquipConsItmNm(), $getEquipConsItmCd(),
	 * $getClctDtm(), $getEquipConsItmId(), $getScardNm(),
	 * $getEquipConsItmTypNm(), $getEquipConsItmActStNm(), $getCpuUseRt(),
	 * $getMmrUseRt(), $getTmprVal(), $getVerInfo(), $getVerAplyDtm(),
	 * $getAuditDtm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10221 A<br>
	 * using ( select $getEquipId() as EQUIP_ID<br>
	 * , $getEquipConsItmNm() as EQUIP_CONS_ITM_NM<br>
	 * , $getEquipConsItmCd() as EQUIP_CONS_ITM_CD<br>
	 * , $getClctDtm() as CLCT_DTM<br>
	 * , $getEquipConsItmId() as EQUIP_CONS_ITM_ID<br>
	 * , $getScardNm() as SCARD_NM<br>
	 * , $getEquipConsItmTypNm() as EQUIP_CONS_ITM_TYP_NM<br>
	 * , $getEquipConsItmActStNm() as EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , $getCpuUseRt() as CPU_USE_RT<br>
	 * , $getMmrUseRt() as MMR_USE_RT<br>
	 * , $getTmprVal() as TMPR_VAL<br>
	 * , $getVerInfo() as VER_INFO<br>
	 * , $getVerAplyDtm() as VER_APLY_DTM<br>
	 * , $getAuditDtm() as AUDIT_DTM<br>
	 * from dual<br>
	 * ) B<br>
	 * on ( A.EQUIP_ID = B.EQUIP_ID and A.EQUIP_CONS_ITM_NM =
	 * B.EQUIP_CONS_ITM_NM )<br>
	 * when not matched then<br>
	 * insert (<br>
	 * A.EQUIP_ID<br>
	 * , A.EQUIP_CONS_ITM_NM<br>
	 * , A.AUDIT_ID<br>
	 * , A.AUDIT_DTM<br>
	 * , A.EQUIP_CONS_ITM_CD<br>
	 * , A.CLCT_DTM<br>
	 * , A.EQUIP_CONS_ITM_ID<br>
	 * , A.SCARD_NM<br>
	 * , A.EQUIP_CONS_ITM_TYP_NM<br>
	 * , A.EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , A.CPU_USE_RT<br>
	 * , A.MMR_USE_RT<br>
	 * , A.TMPR_VAL<br>
	 * , A.VER_INFO<br>
	 * , A.VER_APLY_DTM<br>
	 * ) values (<br>
	 * B.EQUIP_ID<br>
	 * , B.EQUIP_CONS_ITM_NM<br>
	 * , 'ADAMSTMN'<br>
	 * , B.AUDIT_DTM<br>
	 * , B.EQUIP_CONS_ITM_CD<br>
	 * , B.CLCT_DTM<br>
	 * , B.EQUIP_CONS_ITM_ID<br>
	 * , B.SCARD_NM<br>
	 * , B.EQUIP_CONS_ITM_TYP_NM<br>
	 * , B.EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , B.CPU_USE_RT<br>
	 * , B.MMR_USE_RT<br>
	 * , B.TMPR_VAL<br>
	 * , B.VER_INFO<br>
	 * , B.VER_APLY_DTM<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set A.AUDIT_ID = 'ADAMSTMN'<br>
	 * , A.AUDIT_DTM = B.AUDIT_DTM<br>
	 * , A.EQUIP_CONS_ITM_CD = B.EQUIP_CONS_ITM_CD<br>
	 * , A.CLCT_DTM = B.CLCT_DTM<br>
	 * , A.EQUIP_CONS_ITM_ID = B.EQUIP_CONS_ITM_ID<br>
	 * , A.SCARD_NM = B.SCARD_NM<br>
	 * , A.EQUIP_CONS_ITM_TYP_NM = B.EQUIP_CONS_ITM_TYP_NM<br>
	 * , A.EQUIP_CONS_ITM_ACT_ST_NM = B.EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , A.CPU_USE_RT = nvl(B.CPU_USE_RT, A.CPU_USE_RT)<br>
	 * , A.MMR_USE_RT = nvl(B.MMR_USE_RT, A.MMR_USE_RT)<br>
	 * , A.TMPR_VAL = nvl(B.TMPR_VAL, A.TMPR_VAL)<br>
	 * , A.VER_INFO = nvl(B.VER_INFO, A.VER_INFO)<br>
	 * , A.VER_APLY_DTM = nvl(B.VER_APLY_DTM, A.VER_APLY_DTM)<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10221 = "MERGE_OIV10221";

	/**
	 * para : $equipId, $auditDtm, $auditDtm, $clctDtm, $auditDtm, $clctDtm<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10413 A<br>
	 * using ( select port_desc<br>
	 * , equip_id<br>
	 * , equip_port_id<br>
	 * , decode(data_rgst_mthd_cl_cd, '01', 'N', 'Y') as auto_mgmt_yn<br>
	 * from oiv10400<br>
	 * where equip_id = $equipId<br>
	 * and audit_dtm = $auditDtm<br>
	 * ) B<br>
	 * on ( A.equip_id = B.equip_id and A.equip_port_id = B.equip_port_id )<br>
	 * <br>
	 * when not matched then<br>
	 * insert (<br>
	 * a.equip_id<br>
	 * , a.equip_port_id<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * , a.bk_port_set_yn<br>
	 * , a.alt_psbl_yn<br>
	 * , a.auto_mgmt_yn<br>
	 * , a.port_nm<br>
	 * , a.clct_dtm<br>
	 * ) values (<br>
	 * b.equip_id<br>
	 * , b.equip_port_id<br>
	 * , 'ADAMSTMN'<br>
	 * , $auditDtm<br>
	 * , 'N'<br>
	 * , 'N'<br>
	 * , b.auto_mgmt_yn<br>
	 * , b.port_desc<br>
	 * , $clctDtm<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set A.auto_mgmt_yn = b.auto_mgmt_yn<br>
	 * , A.AUDIT_ID = 'ADAMSTMN'<br>
	 * , A.AUDIT_DTM = $auditDtm<br>
	 * , A.CLCT_DTM = $clctDtm<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10413__BY_equipId = "MERGE_OIV10413__BY_equipId";

	/**
	 * para : $equipId<br>
	 * result : RESULT_OIV10104=com.skb.adams.teams.fxms.dbo.OIV10104<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select<br>
	 * *<br>
	 * from OIV10104<br>
	 * where equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10104__BY_equipId = "SELECT_OIV10104__BY_equipId";

	/**
	 * para : $equipMdlCd<br>
	 * result : RESULT_OIV10200=com.skb.adams.teams.fxms.dbo.OIV10200<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select equip_mdl_cd<br>
	 * , equip_mfact_cd<br>
	 * , equip_mdl_nm<br>
	 * from oiv10200<br>
	 * where equip_mdl_cd = $equipMdlCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10200__By_equipMdlCd = "SELECT_OIV10200__By_equipMdlCd";

	/**
	 * para : $equipMdlCd<br>
	 * result : RESULT_OIV10205=com.skb.adams.teams.fxms.dbo.OIV10205<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_cons_itm_cd<br>
	 * , a.port_num<br>
	 * , a.audit_id<br>
	 * , a.audit_dtm<br>
	 * , a.port_nm<br>
	 * , a.port_typ_cd<br>
	 * , a.port_speed_cd<br>
	 * from oiv10205 a ' 장비포트형상기준 '<br>
	 * , oiv10209 b<br>
	 * where a.equip_cons_itm_cd = b.equip_cons_itm_cd<br>
	 * and b.equip_mdl_cd = $equipMdlCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10205__By_equipMdlCd = "SELECT_OIV10205__By_equipMdlCd";

	/**
	 * para : <br>
	 * result : RESULT_STRING=java.lang.String<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select lpad(oiv10209_s1.nextval, 10, '0')<br>
	 * from dual<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10209_NEW_ID = "SELECT_OIV10209_NEW_ID";

	/**
	 * para : $equipMdlCd<br>
	 * result : RESULT_OIV10209=com.skb.adams.teams.fxms.dbo.OIV10209<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select equip_cons_itm_cd<br>
	 * , equip_mfact_cd<br>
	 * , cons_itm_mdl_nm<br>
	 * , cons_itm_typ_cd<br>
	 * , equip_cons_itm_typ_cd<br>
	 * , equip_mdl_cd<br>
	 * , port_cnt<br>
	 * , port_cre_yn<br>
	 * , port_sta_num<br>
	 * , card_trms_net_capa_cl_cd<br>
	 * , port_trms_net_capa_cl_cd<br>
	 * , nms_use_yn<br>
	 * , auto_mgmt_yn<br>
	 * , strd_ver_info<br>
	 * from oiv10209<br>
	 * where equip_mdl_cd = $equipMdlCd<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10209__By_equipMdlCd = "SELECT_OIV10209__By_equipMdlCd";

	/**
	 * para : <br>
	 * result : RESULT_STRING=java.lang.String<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select lpad(oiv10210_s1.nextval, 12, '0')<br>
	 * from dual<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10210_NEW_ID = "SELECT_OIV10210_NEW_ID";

	/**
	 * para : $equipId<br>
	 * result : RESULT_OIV10210=com.skb.adams.teams.fxms.dbo.OIV10210<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select EQUIP_CONS_ITM_ID<br>
	 * , SUP_EQUIP_CONS_ITM_ID<br>
	 * , EQUIP_CONS_ITM_CD<br>
	 * , EQUIP_ID<br>
	 * , CONS_ITM_NUM<br>
	 * , EQUIP_CONS_ITM_NM<br>
	 * , nvl(EQUIP_CONS_ITM_ALS_NM, EQUIP_CONS_ITM_NM) as EQUIP_CONS_ITM_ALS_NM<br>
	 * , MFACT_SER_NUM<br>
	 * , EQUIP_CONS_ITM_DESC<br>
	 * , TRMS_NET_EQUIP_CAPA_CL_CD<br>
	 * , REP_IP_ADDR<br>
	 * , VER_INFO<br>
	 * , DATA_RGST_MTHD_CL_CD<br>
	 * from OIV10210<br>
	 * where equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10210__BY_equipId = "SELECT_OIV10210__BY_equipId";

	/**
	 * para : $getEquipMdlCd(), $getEquipConsItmNm()<br>
	 * result : RESULT_INTEGER=java.lang.Integer<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select count(1) as CNT<br>
	 * from OIV10222<br>
	 * where EQUIP_MDL_CD = $getEquipMdlCd()<br>
	 * and EQUIP_CONS_ITM_NM = $getEquipConsItmNm()<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10222__BY_PK = "SELECT_OIV10222__BY_PK";

	/**
	 * para : <br>
	 * result : RESULT_STRING=java.lang.String<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select lpad(oiv10400_s1.nextval, 15, '0')<br>
	 * from dual<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10400_NEW_ID = "SELECT_OIV10400_NEW_ID";

	/**
	 * para : $equipId<br>
	 * result : RESULT_OIV10400=com.skb.adams.teams.fxms.dbo.OIV10400<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select a.equip_id<br>
	 * , a.equip_port_id<br>
	 * , a.equip_cons_itm_id<br>
	 * , a.moun_loc_num<br>
	 * , a.port_num<br>
	 * , a.port_als_nm<br>
	 * , a.port_typ_cd<br>
	 * , a.port_speed_cd<br>
	 * , a.port_desc<br>
	 * , a.data_rgst_mthd_cl_cd<br>
	 * from oiv10400 a<br>
	 * where a.equip_id = $equipId<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV10400__BY_equipId = "SELECT_OIV10400__BY_equipId";

	/**
	 * para : <br>
	 * result : RESULT_OIV28202=com.skb.adams.teams.fxms.dbo.OIV28202<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select EQUIP_MDL_CD<br>
	 * , SHLF_DESC<br>
	 * , SCARD_DESC<br>
	 * , SCARD_DESC_PTTN_TYP_VAL<br>
	 * , PTTN_TYP_CD<br>
	 * , CL_CHAR_STR_VAL<br>
	 * , PORT_CNT<br>
	 * , MEMO_CTT<br>
	 * from OIV28202<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_OIV28202 = "SELECT_OIV28202";

	/**
	 * para : $getSwVerInfo(), $getEquipSysNm(), $getEquipIpAddr(),
	 * $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10100 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , AUDIT_DTM = sysdate<br>
	 * , SW_VER_INFO = $getSwVerInfo()<br>
	 * , EQUIP_SYS_NM = $getEquipSysNm()<br>
	 * , EQUIP_IP_ADDR = $getEquipIpAddr()<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10100__BY_equipId = "UPDATE_OIV10100__BY_equipId";

	/**
	 * para : $getConnStVal(), $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10104 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , CONN_ST_VAL = $getConnStVal()<br>
	 * , CONN_ST_CHG_DTM = sysdate<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10104__BY_equipId = "UPDATE_OIV10104__BY_equipId";

	/**
	 * para : $getEmsStVal(), $getEmsId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10105 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , EMS_ST_VAL = $getEmsStVal()<br>
	 * , EMS_ST_CHG_DTM = sysdate<br>
	 * where EMS_ID = $getEmsId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10105__BY_emsId = "UPDATE_OIV10105__BY_emsId";

	/**
	 * para : $getClctDtm(), $getEquipIpAddr(), $getEquipSysNm(),
	 * $getFwVerInfo(), $getOperEquipMdlCd(), $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10178 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , AUDIT_DTM = sysdate<br>
	 * , CLCT_DTM = $getClctDtm()<br>
	 * , EQUIP_IP_ADDR = $getEquipIpAddr()<br>
	 * , EQUIP_SYS_NM = $getEquipSysNm()<br>
	 * , FW_VER_INFO = $getFwVerInfo()<br>
	 * , OPER_EQUIP_MDL_CD = $getOperEquipMdlCd()<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10178__BY_equipId = "UPDATE_OIV10178__BY_equipId";

	/**
	 * para : $getPortCnt(), $getSupEquipConsItmId(), $getEquipConsItmCd(),
	 * $getEquipConsItmStVal(), $getEquipConsItmSubStVal(),
	 * $getEquipConsItmAlsNm(), $getConsItmNum(), $getEquipConsItmNm(),
	 * $getMfactSerNum(), $getEquipConsItmDesc(), $getTrmsNetEquipCapaClCd(),
	 * $getRepIpAddr(), $isPollingMObjYn(), $getVerInfo(),
	 * $getDataRgstMthdClCd(), $getAutoMgmtYn(), $getEquipId(),
	 * $getEquipConsItmId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10210 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , AUDIT_DTM = sysdate<br>
	 * , PORT_CNT = $getPortCnt()<br>
	 * , SUP_EQUIP_CONS_ITM_ID = $getSupEquipConsItmId()<br>
	 * , EQUIP_CONS_ITM_CD = $getEquipConsItmCd()<br>
	 * , EQUIP_CONS_ITM_ST_VAL = $getEquipConsItmStVal()<br>
	 * , EQUIP_CONS_ITM_SUB_ST_VAL = $getEquipConsItmSubStVal()<br>
	 * , EQUIP_CONS_ITM_ALS_NM = $getEquipConsItmAlsNm()<br>
	 * , CONS_ITM_NUM = $getConsItmNum()<br>
	 * , EQUIP_CONS_ITM_NM = $getEquipConsItmNm()<br>
	 * , MFACT_SER_NUM = $getMfactSerNum()<br>
	 * , EQUIP_CONS_ITM_DESC = $getEquipConsItmDesc()<br>
	 * , TRMS_NET_EQUIP_CAPA_CL_CD = $getTrmsNetEquipCapaClCd()<br>
	 * , REP_IP_ADDR = $getRepIpAddr()<br>
	 * , POLLING_M_OBJ_YN = $isPollingMObjYn()<br>
	 * , VER_INFO = $getVerInfo()<br>
	 * , DATA_RGST_MTHD_CL_CD = $getDataRgstMthdClCd()<br>
	 * , AUTO_MGMT_YN = $getAutoMgmtYn()<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * and EQUIP_CONS_ITM_ID = $getEquipConsItmId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10210__BY_equipId_equipConsItmId = "UPDATE_OIV10210__BY_equipId_equipConsItmId";

	/**
	 * para : $getAuditDtm(), $getEquipConsItmId(), $getMounLocNum(),
	 * $getPortNum(), $getPortAlsNm(), $getPortTypCd(), $getPortSpeedCd(),
	 * $getPortDesc(), $getDataRgstMthdClCd(), $getEquipId(), $getEquipPortId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * update OIV10400 set<br>
	 * AUDIT_ID = 'ADAMSTMN'<br>
	 * , AUDIT_DTM = $getAuditDtm()<br>
	 * , EQUIP_CONS_ITM_ID = $getEquipConsItmId()<br>
	 * , MOUN_LOC_NUM = $getMounLocNum()<br>
	 * , PORT_NUM = $getPortNum()<br>
	 * , PORT_ALS_NM = $getPortAlsNm()<br>
	 * , PORT_TYP_CD = $getPortTypCd()<br>
	 * , PORT_SPEED_CD = $getPortSpeedCd()<br>
	 * , PORT_DESC = $getPortDesc()<br>
	 * , DATA_RGST_MTHD_CL_CD = $getDataRgstMthdClCd()<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * and EQUIP_PORT_ID = $getEquipPortId()<br>
	 * <br>
	 * <br>
	 */
	public final String UPDATE_OIV10400__BY_equipId_equipPortId = "UPDATE_OIV10400__BY_equipId_equipPortId";

}
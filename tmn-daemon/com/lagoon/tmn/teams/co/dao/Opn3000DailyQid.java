package com.lagoon.tmn.teams.co.dao;

/**
 * File : opn3000_daily.xml<br>
 * 
 * @since 20191218182934
 * @author subkjh
 *
 */

public class Opn3000DailyQid {

	/** 쿼리 모임 화일명. opn3000_daily.xml */
	public static final String QUERY_XML_FILE = "opn3000_daily.xml";

	public Opn3000DailyQid() {
	}

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28011<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28011__By_equipId = "DELETE_OCL28011__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28012<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28012__By_equipId = "DELETE_OCL28012__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28101<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28101__By_equipId = "DELETE_OCL28101__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28102<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28102__By_equipId = "DELETE_OCL28102__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28103<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28103__By_equipId = "DELETE_OCL28103__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28104<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28104__By_equipId = "DELETE_OCL28104__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28105<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28105__By_equipId = "DELETE_OCL28105__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28106<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28106__By_equipId = "DELETE_OCL28106__By_equipId";

	/**
	 * para : $getEquipId()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * delete<br>
	 * from OCL28201<br>
	 * where EQUIP_ID = $getEquipId()<br>
	 * <br>
	 * <br>
	 */
	public final String DELETE_OCL28201__By_equipId = "DELETE_OCL28201__By_equipId";

	/**
	 * para : $getEquipId(), $getClctItmClNm(), $getClctItmCtt(), $getCmprCtt(),
	 * $getNormCnt(), $getAbnCnt()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28011 (<br>
	 * EQUIP_ID<br>
	 * , CLCT_ITM_CL_NM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , CLCT_ITM_CTT<br>
	 * , CMPR_CTT<br>
	 * , NORM_CNT<br>
	 * , ABN_CNT<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getClctItmClNm()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getClctItmCtt()<br>
	 * , $getCmprCtt()<br>
	 * , $getNormCnt()<br>
	 * , $getAbnCnt()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28011 = "INSERT_OCL28011";

	/**
	 * para : $getEquipId(), $getNotiClVal(), $getNotiRcvObjNm(),
	 * $isAlmNotiYn(), $isPrfmNotiYn(), $isSwNotiYn(), $isSnmpNotiYn(),
	 * $isSysLogNotiYn()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28012 (<br>
	 * EQUIP_ID<br>
	 * , NOTI_CL_VAL<br>
	 * , NOTI_RCV_OBJ_NM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , ALM_NOTI_YN<br>
	 * , PRFM_NOTI_YN<br>
	 * , SW_NOTI_YN<br>
	 * , SNMP_NOTI_YN<br>
	 * , SYS_LOG_NOTI_YN<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getNotiClVal()<br>
	 * , $getNotiRcvObjNm()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $isAlmNotiYn()<br>
	 * , $isPrfmNotiYn()<br>
	 * , $isSwNotiYn()<br>
	 * , $isSnmpNotiYn()<br>
	 * , $isSysLogNotiYn()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28012 = "INSERT_OCL28012";

	/**
	 * para : $getEquipId(), $getPortNm(), $isModulePutonYn(), $getCoreNum(),
	 * $getSuprtSgnlVal(), $getTxWvlengVal(), $getRxWvlengVal(), $getDistInfo(),
	 * $getTxPwrVal(), $getRxPwrVal(), $getTmprVal(), $getScardNm(),
	 * $getScardTypNm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28101 (<br>
	 * EQUIP_ID<br>
	 * , PORT_NM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , MODULE_PUTON_YN<br>
	 * , CORE_NUM<br>
	 * , SUPRT_SGNL_VAL<br>
	 * , TX_WVLENG_VAL<br>
	 * , RX_WVLENG_VAL<br>
	 * , DIST_INFO<br>
	 * , TX_PWR_VAL<br>
	 * , RX_PWR_VAL<br>
	 * , TMPR_VAL<br>
	 * , SCARD_NM<br>
	 * , SCARD_TYP_NM<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getPortNm()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $isModulePutonYn()<br>
	 * , $getCoreNum()<br>
	 * , $getSuprtSgnlVal()<br>
	 * , $getTxWvlengVal()<br>
	 * , $getRxWvlengVal()<br>
	 * , $getDistInfo()<br>
	 * , $getTxPwrVal()<br>
	 * , $getRxPwrVal()<br>
	 * , $getTmprVal()<br>
	 * , $getScardNm()<br>
	 * , $getScardTypNm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28101 = "INSERT_OCL28101";

	/**
	 * para : $getEquipId(), $getPortDesc(), $getClctPrfmNm(),
	 * $getCurrMin15PrfmVal(), $getBfMin15PrfmVal(), $getCurrD1PrfmVal(),
	 * $getBfD1PrfmVal(), $getScardNm(), $getScardTypNm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28102 (<br>
	 * EQUIP_ID<br>
	 * , PORT_DESC<br>
	 * , CLCT_PRFM_NM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , CURR_MIN15_PRFM_VAL<br>
	 * , BF_MIN15_PRFM_VAL<br>
	 * , CURR_D1_PRFM_VAL<br>
	 * , BF_D1_PRFM_VAL<br>
	 * , SCARD_NM<br>
	 * , SCARD_TYP_NM<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getPortDesc()<br>
	 * , $getClctPrfmNm()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getCurrMin15PrfmVal()<br>
	 * , $getBfMin15PrfmVal()<br>
	 * , $getCurrD1PrfmVal()<br>
	 * , $getBfD1PrfmVal()<br>
	 * , $getScardNm()<br>
	 * , $getScardTypNm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28102 = "INSERT_OCL28102";

	/**
	 * para : $getEquipId(), $getEquipClkModeNm(), $getCurrEquipClkVal(),
	 * $getStaDtm(), $getEquipMgmtModeNm(), $getEquipClkVal1(),
	 * $getEquipClkVal2(), $getEquipClkVal3(), $getEquipClkVal4(),
	 * $getEquipClkVal5(), $getEquipClkVal6(), $getEquipClkVal7(),
	 * $getEquipClkVal8()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28103 (<br>
	 * EQUIP_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EQUIP_CLK_MODE_NM<br>
	 * , CURR_EQUIP_CLK_VAL<br>
	 * , STA_DTM<br>
	 * , EQUIP_MGMT_MODE_NM<br>
	 * , EQUIP_CLK_VAL1<br>
	 * , EQUIP_CLK_VAL2<br>
	 * , EQUIP_CLK_VAL3<br>
	 * , EQUIP_CLK_VAL4<br>
	 * , EQUIP_CLK_VAL5<br>
	 * , EQUIP_CLK_VAL6<br>
	 * , EQUIP_CLK_VAL7<br>
	 * , EQUIP_CLK_VAL8<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getEquipClkModeNm()<br>
	 * , $getCurrEquipClkVal()<br>
	 * , $getStaDtm()<br>
	 * , $getEquipMgmtModeNm()<br>
	 * , $getEquipClkVal1()<br>
	 * , $getEquipClkVal2()<br>
	 * , $getEquipClkVal3()<br>
	 * , $getEquipClkVal4()<br>
	 * , $getEquipClkVal5()<br>
	 * , $getEquipClkVal6()<br>
	 * , $getEquipClkVal7()<br>
	 * , $getEquipClkVal8()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28103 = "INSERT_OCL28103";

	/**
	 * para : $getEquipId(), $getEquipClkVal(), $getRcvSyncQltyInfo(),
	 * $getSetRcvSyncQltyInfo(), $getSendSyncQltyInfo(),
	 * $getSetSendSyncQltyInfo(), $isAvailYn(), $getSaBitSetNm(),
	 * $getSaBitStNm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28104 (<br>
	 * EQUIP_ID<br>
	 * , EQUIP_CLK_VAL<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , RCV_SYNC_QLTY_INFO<br>
	 * , SET_RCV_SYNC_QLTY_INFO<br>
	 * , SEND_SYNC_QLTY_INFO<br>
	 * , SET_SEND_SYNC_QLTY_INFO<br>
	 * , AVAIL_YN<br>
	 * , SA_BIT_SET_NM<br>
	 * , SA_BIT_ST_NM<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getEquipClkVal()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getRcvSyncQltyInfo()<br>
	 * , $getSetRcvSyncQltyInfo()<br>
	 * , $getSendSyncQltyInfo()<br>
	 * , $getSetSendSyncQltyInfo()<br>
	 * , $isAvailYn()<br>
	 * , $getSaBitSetNm()<br>
	 * , $getSaBitStNm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28104 = "INSERT_OCL28104";

	/**
	 * para : $getEquipId(), $getPortNm(), $getScardNm(),
	 * $getMaxBandwSpeedVal(), $getMaxPirVal(), $getUsePirVal(), $getPirUseRt(),
	 * $getPirUseRtThrsVal(), $getMaxCirVal(), $getUseCirVal(), $getCirUseRt(),
	 * $getCirUseRtThrsVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28105 (<br>
	 * EQUIP_ID<br>
	 * , PORT_NM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , SCARD_NM<br>
	 * , MAX_BANDW_SPEED_VAL<br>
	 * , MAX_PIR_VAL<br>
	 * , USE_PIR_VAL<br>
	 * , PIR_USE_RT<br>
	 * , PIR_USE_RT_THRS_VAL<br>
	 * , MAX_CIR_VAL<br>
	 * , USE_CIR_VAL<br>
	 * , CIR_USE_RT<br>
	 * , CIR_USE_RT_THRS_VAL<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getPortNm()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getScardNm()<br>
	 * , $getMaxBandwSpeedVal()<br>
	 * , $getMaxPirVal()<br>
	 * , $getUsePirVal()<br>
	 * , $getPirUseRt()<br>
	 * , $getPirUseRtThrsVal()<br>
	 * , $getMaxCirVal()<br>
	 * , $getUseCirVal()<br>
	 * , $getCirUseRt()<br>
	 * , $getCirUseRtThrsVal()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28105 = "INSERT_OCL28105";

	/**
	 * para : $getEquipId(), $getFanModeNm(), $getFanStNm(),
	 * $getMaxSpeedUpmvCondVal(), $getMedmSpeedUpmvCondVal(),
	 * $getMinSpeedUpmvCondVal(), $getMedmSpeedDnmvCondVal(),
	 * $getMinSpeedDnmvCondVal(), $getFanOffCondVal()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28106 (<br>
	 * EQUIP_ID<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , FAN_MODE_NM<br>
	 * , FAN_ST_NM<br>
	 * , MAX_SPEED_UPMV_COND_VAL<br>
	 * , MEDM_SPEED_UPMV_COND_VAL<br>
	 * , MIN_SPEED_UPMV_COND_VAL<br>
	 * , MEDM_SPEED_DNMV_COND_VAL<br>
	 * , MIN_SPEED_DNMV_COND_VAL<br>
	 * , FAN_OFF_COND_VAL<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getFanModeNm()<br>
	 * , $getFanStNm()<br>
	 * , $getMaxSpeedUpmvCondVal()<br>
	 * , $getMedmSpeedUpmvCondVal()<br>
	 * , $getMinSpeedUpmvCondVal()<br>
	 * , $getMedmSpeedDnmvCondVal()<br>
	 * , $getMinSpeedDnmvCondVal()<br>
	 * , $getFanOffCondVal()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28106 = "INSERT_OCL28106";

	/**
	 * para : $getInspSerNum(), $getEquipId(), $getClctDtm(), $getInspSussYn()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28190 (<br>
	 * INSP_SER_NUM<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , EQUIP_ID<br>
	 * , CLCT_DTM<br>
	 * , INSP_SUSS_YN<br>
	 * ) values (<br>
	 * $getInspSerNum()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getEquipId()<br>
	 * , $getClctDtm()<br>
	 * , $getInspSussYn()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28190 = "INSERT_OCL28190";

	/**
	 * para : $getEquipId(), $getAlmLocInfo(), $getAlmTypInfo(),
	 * $getAlmGrInfo(), $getScardTypNm(), $getOccrDtm()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * insert into OCL28201 (<br>
	 * EQUIP_ID<br>
	 * , ALM_LOC_INFO<br>
	 * , ALM_TYP_INFO<br>
	 * , AUDIT_ID<br>
	 * , AUDIT_DTM<br>
	 * , ALM_GR_INFO<br>
	 * , SCARD_TYP_NM<br>
	 * , OCCR_DTM<br>
	 * ) values (<br>
	 * $getEquipId()<br>
	 * , $getAlmLocInfo()<br>
	 * , $getAlmTypInfo()<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , $getAlmGrInfo()<br>
	 * , $getScardTypNm()<br>
	 * , $getOccrDtm()<br>
	 * )<br>
	 * <br>
	 * <br>
	 */
	public final String INSERT_OCL28201 = "INSERT_OCL28201";

	/**
	 * para : $getEquipId(), $getEquipConsItmNm(), $getClctDtm(), $getScardNm(),
	 * $getEquipConsItmTypNm(), $getEquipConsItmActStNm(), $getCpuUseRt(),
	 * $getMmrUseRt(), $getTmprVal(), $getVerInfo(), $getVerAplyDtm(),
	 * $getActvnIdxNum(), $getFstEtcVerInfo(), $getScndEtcVerInfo()<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * merge into OIV10224 A<br>
	 * using ( select $getEquipId() as EQUIP_ID<br>
	 * , $getEquipConsItmNm() as EQUIP_CONS_ITM_NM<br>
	 * , $getClctDtm() as CLCT_DTM<br>
	 * , $getScardNm() as SCARD_NM<br>
	 * , $getEquipConsItmTypNm() as EQUIP_CONS_ITM_TYP_NM<br>
	 * , $getEquipConsItmActStNm() as EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , $getCpuUseRt() as CPU_USE_RT<br>
	 * , $getMmrUseRt() as MMR_USE_RT<br>
	 * , $getTmprVal() as TMPR_VAL<br>
	 * , $getVerInfo() as VER_INFO<br>
	 * , $getVerAplyDtm() as VER_APLY_DTM<br>
	 * , $getActvnIdxNum() as ACTVN_IDX_NUM<br>
	 * , $getFstEtcVerInfo() as FST_ETC_VER_INFO<br>
	 * , $getScndEtcVerInfo() as SCND_ETC_VER_INFO<br>
	 * from dual <br>
	 * ) B<br>
	 * on ( A.EQUIP_ID = B.EQUIP_ID and A.EQUIP_CONS_ITM_NM =
	 * B.EQUIP_CONS_ITM_NM )<br>
	 * <br>
	 * when not matched then<br>
	 * insert ( <br>
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
	 * , A.ACTVN_IDX_NUM<br>
	 * , A.FST_ETC_VER_INFO<br>
	 * , A.SCND_ETC_VER_INFO<br>
	 * ) values (<br>
	 * B.EQUIP_ID<br>
	 * , B.EQUIP_CONS_ITM_NM<br>
	 * , 'ADAMSTMN'<br>
	 * , sysdate<br>
	 * , '0000000000'<br>
	 * , B.CLCT_DTM<br>
	 * , null<br>
	 * , B.SCARD_NM<br>
	 * , B.EQUIP_CONS_ITM_TYP_NM<br>
	 * , B.EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , B.CPU_USE_RT<br>
	 * , B.MMR_USE_RT<br>
	 * , B.TMPR_VAL<br>
	 * , B.VER_INFO<br>
	 * , B.VER_APLY_DTM<br>
	 * , B.ACTVN_IDX_NUM<br>
	 * , B.FST_ETC_VER_INFO<br>
	 * , B.SCND_ETC_VER_INFO<br>
	 * )<br>
	 * when matched then<br>
	 * update<br>
	 * set A.AUDIT_ID = 'ADAMSTMN'<br>
	 * , A.AUDIT_DTM = sysdate<br>
	 * , A.CLCT_DTM = B.CLCT_DTM <br>
	 * , A.SCARD_NM = B.SCARD_NM <br>
	 * , A.EQUIP_CONS_ITM_TYP_NM = B.EQUIP_CONS_ITM_TYP_NM <br>
	 * , A.EQUIP_CONS_ITM_ACT_ST_NM = B.EQUIP_CONS_ITM_ACT_ST_NM<br>
	 * , A.CPU_USE_RT = B.CPU_USE_RT<br>
	 * , A.MMR_USE_RT = B.MMR_USE_RT<br>
	 * , A.TMPR_VAL = B.TMPR_VAL<br>
	 * , A.VER_INFO = B.VER_INFO<br>
	 * , A.VER_APLY_DTM = B.VER_APLY_DTM<br>
	 * , A.ACTVN_IDX_NUM = B.ACTVN_IDX_NUM<br>
	 * , A.FST_ETC_VER_INFO = B.FST_ETC_VER_INFO<br>
	 * , A.SCND_ETC_VER_INFO = B.SCND_ETC_VER_INFO<br>
	 * <br>
	 * <br>
	 */
	public final String MERGE_OIV10224 = "MERGE_OIV10224";

	/**
	 * para : <br>
	 * result : RESULT_LONG=java.lang.Long<br>
	 * ------------------------------------------------------------------------
	 * ---------- <br>
	 * database : null<br>
	 * sql <br>
	 * <br>
	 * <br>
	 * select OCL28190_S1.nextval<br>
	 * from dual<br>
	 * <br>
	 * <br>
	 */
	public final String SELECT_INSP_SER_NUM__NEXT = "SELECT_INSP_SER_NUM__NEXT";

}
package com.lagoon.tmn.teams.co.dao;

/**
* File : adams_statistic.xml<br>
* @since 20191023135128
* @author subkjh 
*
*/


public class AdamsStatisticQid { 

/** 쿼리 모임 화일명. adams_statistic.xml*/
public static final String QUERY_XML_FILE = "adams_statistic.xml";

public AdamsStatisticQid() { 
} 
/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>delete <br>		from 	oiv28911<br>		where	rgst_dt = to_char(sysdate, 'YYYYMMDD')<br><br> <br>
*/
public final String DELETE_OIV28911__BY_today = "DELETE_OIV28911__BY_today";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OIV28910 ( <br>				RGST_DT  <br>				, EQUIP_ID <br>				, AUDIT_ID <br>				, SLOT_CNT    <br>				, MOUN_SLOT_CNT    <br>				, EQUIP_CAPA_VAL   <br>				, PORT_QTY    <br>				, USE_PORT_QTY    <br>				, ACEPT_LINE_CNT    <br>				, USE_EQUIP_CAPA_VAL   <br>		) <br>		<br>		with slotdef as (<br>				select	a.equip_mdl_cd<br>						, count(1) as tot_slot_cnt<br>				from 	oiv28202 a ' 전송망장비모델별카드템플릿 ' <br>				group by <br>						a.equip_mdl_cd<br>		)<br>		, slot as (<br>				select	equip_id<br>						, count(1) as slot_use_cnt<br>				from 	oiv10210 <br>				where	port_cnt > 0<br>				group by <br>						equip_id<br>		)<br>		, port as (<br>				select	t.equip_id<br>						, sum(t.bw)			as EQUIP_CAPA_VAL<br>						, sum(decode(t.port_use_yn, 'Y', bw, 0))	<br>											as USE_EQUIP_CAPA_VAL<br>						, count(1)			as PORT_QTY<br>						, sum(decode(t.port_use_yn, 'Y', 1, 0))	<br>											as USE_PORT_QTY<br>				from (<br>						select a.equip_id<br>						      , case <br>						            when instr(a1.comm_cd_val_nm, 'DWDM') > 0 then 400000 -- 400G<br>						            when instr(a1.comm_cd_val_nm, 'Kbps') > 0 then to_number(replace(a1.comm_cd_val_nm, 'Kbps', '') * .001) <br>						            when instr(a1.comm_cd_val_nm, 'M') > 0 then to_number(replace(a1.comm_cd_val_nm, 'M', '') * 1)<br>						            when instr(a1.comm_cd_val_nm, 'GE') > 0 then to_number(replace(a1.comm_cd_val_nm, 'GE', '') * 1000) <br>						            when instr(a1.comm_cd_val_nm, 'G') > 0 then to_number(replace(a1.comm_cd_val_nm, 'G', '') * 1000) <br>						            when instr(a1.comm_cd_val_nm, 'E1') > 0 then 1.544 <br>						            when instr(a1.comm_cd_val_nm, 'T1') > 0 then 2.048 <br>						            when instr(a1.comm_cd_val_nm, 'G') > 0 then to_number(replace(a1.comm_cd_val_nm, 'G', '') * 1000) <br>						            else 0<br>						          end as bw<br>								, a.port_use_yn		             <br>						from OIV28121 a<br>						    left join OCO20101 a1 on a1.comm_cd = 'TRMS_NET_EQUIP_CAPA_CL_CD' and a1.comm_cd_val = a.trms_net_equip_capa_cl_cd<br>						) t<br>				group by t.equip_id<br>		) <br>	<br>		select	'20190821'					as RGST_DT<br>				, a.equip_id				as EQUIP_ID<br>				, 'SKAdams-subkjh'			as AUDIT_ID<br>				, nvl(a3.tot_slot_cnt, 0)	as SLOT_CNT				<br>				, nvl(a1.slot_use_cnt, 0)	as MOUN_SLOT_CNT	<br>				, a.EQUIP_CAPA_VAL<br>				, a.PORT_QTY<br>				, a.USE_PORT_QTY<br>				, -1						as EQUIP_CAPA_VAL<br>				, a.USE_EQUIP_CAPA_VAL<br>		from	port a<br>					left join slot a1 on a1.equip_id = a.equip_id<br>					left join oiv10100 a2 on a2.equip_id = a1.equip_id<br>					left join slotdef a3 on a3.equip_mdl_cd = a2.equip_mdl_cd<br><br> <br>
*/
public final String INSERT_OIV28910 = "INSERT_OIV28910";

/**
* para : <br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>insert into OIV28911 (<br>				RGST_DT<br>				, EQUIP_MDL_CD<br>				, EGOV_BB_TYP_CD<br>				, MGMT_TPO_CD<br>				, EQUIP_VER_INFO<br>				, AUDIT_ID<br>				, AUDIT_DTM<br>				, EQUIP_QTY<br>		)<br>		select 	to_char(sysdate, 'YYYYMMDD')	as RGST_DT<br>				, a.equip_mdl_cd				as EQUIP_MDL_CD<br>				, nvl(b.egov_bb_typ_cd, '-')	as EGOV_BB_TYP_CD<br>				, c.mgmt_tpo_cd					as MGMT_TPO_CD<br>				, nvl(a.sw_ver_info, '-')		as EQUIP_VER_INFO<br>				, 'ADAMS-TMN'					as AUDIT_ID<br>				, sysdate						as AUDIT_DTM<br>				, count(1)						as EQUIP_QTY<br>		from 	oiv10100 a<br>		    	, oiv10011 b<br>		    	, oiv10300 c<br>		where 	a.del_yn = 'N'<br>		and		a.equip_id = b.equip_id<br>		and		c.tpo_cd = a.tpo_cd<br>		group by <br>				c.mgmt_tpo_cd<br>				, b.egov_bb_typ_cd<br>				, a.equip_mdl_cd<br>				, a.sw_ver_info<br><br> <br>
*/
public final String INSERT_OIV28911 = "INSERT_OIV28911";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select distinct a.trms_net_equip_capa_cl_cd<br>		      , a1.comm_cd_val_nm<br>		      , case <br>		            when instr(a1.comm_cd_val_nm, 'DWDM') > 0 then 400000 -- 400G<br>		            when instr(a1.comm_cd_val_nm, 'Kbps') > 0 then to_number(replace(a1.comm_cd_val_nm, 'Kbps', '') * .001) <br>		            when instr(a1.comm_cd_val_nm, 'M') > 0 then to_number(replace(a1.comm_cd_val_nm, 'M', '') * 1)<br>		            when instr(a1.comm_cd_val_nm, 'GE') > 0 then to_number(replace(a1.comm_cd_val_nm, 'GE', '') * 1000) <br>		            when instr(a1.comm_cd_val_nm, 'G') > 0 then to_number(replace(a1.comm_cd_val_nm, 'G', '') * 1000) <br>		            when instr(a1.comm_cd_val_nm, 'E1') > 0 then 1.544 <br>		            when instr(a1.comm_cd_val_nm, 'T1') > 0 then 2.048 <br>		            when instr(a1.comm_cd_val_nm, 'G') > 0 then to_number(replace(a1.comm_cd_val_nm, 'G', '') * 1000) <br>		            else 0<br>		          end as bw      <br>		from OIV28121 a<br>		    left join OCO20101 a1 on a1.comm_cd = 'TRMS_NET_EQUIP_CAPA_CL_CD' and a1.comm_cd_val = a.trms_net_equip_capa_cl_cd<br>		order by a1.comm_cd_val_nm<br><br> <br>
*/
public final String SELECT_BW__IN_PORT = "SELECT_BW__IN_PORT";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>with mdl as (<br>			select	a.equip_mdl_cd<br>					, b.equip_mdl_nm<br>					, count(1) as tot_slot_cnt<br>			from 	oiv28202 a ' 전송망장비모델별카드템플릿 ' <br>					, oiv10200 b<br>			where   a.equip_mdl_cd = b.equip_mdl_cd      <br>			group by <br>					a.equip_mdl_cd<br>					, b.equip_mdl_nm<br>		)<br>		select a.equip_mdl_cd<br>				, a.equip_mdl_nm<br>				, b.tot_slot_cnt<br>				, ( select count(1) from oiv10100 a1 where a1.equip_mdl_cd = a.equip_mdl_cd and a1.del_yn = 'N' ) as equip_cnt<br>		from	oiv10200 a<br>				, mdl b<br>		where	a.equip_mdl_cd = b.equip_mdl_cd<br><br> <br>
*/
public final String SELECT_PORT_USE = "SELECT_PORT_USE";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select	a.equip_id<br>				, a.equip_port_id<br>--				, a5.equip_cons_itm_als_nm as scard_desc<br>				, a.port_desc<br>				, a.port_speed_cd as trms_net_equip_capa_cl_cd<br>				, decode(a1.equip_id, null, decode(a2.equip_id, null, decode(a3.equip_id, null, decode(a4.equip_id, null, 'N', 'Y'), 'Y'), 'Y'), 'Y') as port_use_yn <br>		from 	oiv10400 a<br> 					left join oiv10615 a1 on a1.equip_id = a.equip_id and a.port_desc = a1.a_port_info<br> 					left join oiv10615 a2 on a2.equip_id = a.equip_id and a.port_desc = a2.b_port_info<br> 					left join oiv10612 a3 on a3.equip_id = a.equip_id and a.port_desc = a3.a_port_info<br> 					left join oiv10612 a4 on a4.equip_id = a.equip_id and a.port_desc = a4.b_port_info<br> 					left join oiv10210 a5 on a5.equip_id = a.equip_id and a5.equip_cons_itm_id = a.equip_cons_itm_id<br>  				, oiv10115 b<br>		where 	b.net_cl_cd = '02'<br>		and		a.equip_id = b.equip_id<br>		and		b.equip_id = '000000062772'<br><br>		minus    <br>    <br>		select 	equip_id<br>				, equip_port_id<br>				--, scard_desc<br>				, port_desc<br>				, trms_net_equip_capa_cl_cd<br>				, port_use_yn<br>		from 	OIV28121 <br>		where	 equip_id = '000000062772'<br><br> <br>
*/
public final String SELECT_PORT_USE_CHECK = "SELECT_PORT_USE_CHECK";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select	a.equip_id<br>				, count(1) as slot_use_cnt<br>		from 	oiv10115 a<br>				, oiv10210 b <br>		where 	a.net_cl_cd = '02'<br>		and		a.equip_id = b.equip_id<br>		and		b.port_cnt > 0<br>		group by <br>				a.equip_id<br><br> <br>
*/
public final String SELECT_SLOT_COUNT_IN_EQUIP = "SELECT_SLOT_COUNT_IN_EQUIP";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	equip_mdl_cd<br>				, count(1) as slot_tot_cnt <br>		from 	OIV28202 <br>		group by <br>				equip_mdl_cd<br><br> <br>
*/
public final String SELECT_SLOT_COUNT__BY_equipMdlCd = "SELECT_SLOT_COUNT__BY_equipMdlCd";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select 	b.equip_mdl_nm<br>				, decode(a.EGOV_BB_TYP_CD, 'K', '기간망', 'C', 'COT', 'R', 'RT', '미확인') as "망구분"<br>				, c.tpo_nm<br>				, a.EQUIP_VER_INFO<br>				, a.EQUIP_QTY<br>				, decode( ( select count(1) from OIV10211 x where x.EQUIP_MDL_CD = a.EQUIP_MDL_CD and x.OS_VER_STRD_INFO = a.EQUIP_VER_INFO), 1, '표준', '비표준') as is_std<br>		from 	OIV28911 a<br>				, oiv10200 b<br>				, oiv10301 c<br>		where 	a.equip_mdl_cd = b.equip_mdl_cd<br>		and 	a.mgmt_tpo_cd = c.tpo_cd<br>		order by  <br>				b.equip_mdl_nm<br>				, a.EGOV_BB_TYP_CD<br>				, c.tpo_nm<br><br> <br>
*/
public final String SELECT_TRMSNET_MODEL = "SELECT_TRMSNET_MODEL";

/**
* para : <br>
* result : RESULT_MAP=java.util.HashMap<br>
* ---------------------------------------------------------------------------------- <br>
* database : null<br>
* sql <br><br>
 * <br>select	b.equip_mdl_cd<br>				, c.equip_mdl_nm<br>				, count(1)			as equip_cnt<br>				, 'teams'			as type<br>		from 	oiv10107 a<br>				, oiv10100 b<br>				, oiv10200 c<br>		where 	a.clct_nms_cl_cd = '005'<br>		and		a.equip_id = b.equip_id<br>		and		b.del_yn = 'N'<br>		and		b.equip_mdl_cd = c.equip_mdl_cd<br>		group by<br>				b.equip_mdl_cd<br>				, c.equip_mdl_nm<br>		<br>		union all<br>		<br>		select 	x.equip_mdl_cd<br>				, y.equip_mdl_nm<br>				, count(1)			as equip_cnt<br>				, 'other'			as type<br>		from	oiv10100 x<br>				, oiv10200 y<br>		where	exists ( <br>					select	1 <br>					from 	oiv10107 a<br>							, oiv10100 b<br>					where 	a.clct_nms_cl_cd = '005'<br>					and		a.equip_id = b.equip_id<br>					and		b.equip_mdl_cd = x.equip_mdl_cd<br>					and		b.del_yn = 'N' <br>				)<br>		and		x.equip_mdl_cd = y.equip_mdl_cd<br>		and		x.del_yn = 'N'				<br>		group by<br>				x.equip_mdl_cd<br>				, y.equip_mdl_nm<br><br> <br>
*/
public final String SELECT_TRMSNET_MODEL2 = "SELECT_TRMSNET_MODEL2";

}
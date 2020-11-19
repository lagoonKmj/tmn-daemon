package com.lagoon.tmn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.dao.AdamsTeamsQid;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class CheckAlarmByRingAndLine {

	public static void main(String[] args) throws Exception {
		
		Logger.logger.setLevel(LOG_LEVEL.info);
		CheckAlarmByRingAndLine alarm = new CheckAlarmByRingAndLine();
		alarm.dbTrans = DBManager.getMgr().getDataBase("MIDBDEV").createDbTrans(AdamsCfg.getHomeDeployConfSql("test_tmn.xml")); 
//		alarm.getAlarm();
//		alarm.check();
		alarm.getPortAndChl();
	}

	private DbTrans dbTrans;
	@SuppressWarnings("unchecked")
	public void getAlarm() throws Exception  {
		
		List<Map<String, Object>> list = dbTrans.selectQid2Res("SELECT_TEST___GET_ALARM", null);
		
		int count = 0;
		
		for (Map<String, Object> map : list) {
			
			String equipId = map.get("EQUIP_ID") + "";
			String portDesc = map.get("PORT_DESC") + "";
			String trmsEquipDablNum = map.get("TRMS_EQUIP_DABL_NUM") + "";
			String allMsgCtt = map.get("ALL_MSG_CTT") + "";
			
			Logger.logger.info("equip_id : {}, port_desc : {}, trms_equip_dabl_num : {}, all_msg_ctt : {}", equipId, portDesc, trmsEquipDablNum, allMsgCtt);

			int netSize = netSize(equipId, portDesc);
			Logger.logger.info(" ㄴ 찾은 링 갯수 : {}", netSize);
			count += netSize;
			
			int lineSize = lineSize(equipId, portDesc);
			Logger.logger.info(" ㄴ 찾은 회선 갯수 : {}", lineSize);
			count += lineSize;
		}
		Logger.logger.info(" ㄴ 찾은 링, 회선 수 : {}", count);
	}

	public void check() throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("S00013828660	1-1-4-THRU");
		list.add("000000384658	MCU");
		list.add("000000303564	FN1-P31");
		list.add("000000405295	PWR.B-TGUB");
		list.add("000000133337	PTLM-P05");
		list.add("000000308301	1-3-13");
		list.add("S00329916600	OXC");
		list.add("S00329916600	OXC");
		list.add("000000245570	CU12");
		list.add("000000335699	TIU1.B-P1");
		list.add("000000335699	TIU1.A-P1");
		list.add("000000335699	TIU1.A-P2");
		list.add("000000335699	TIU1.A-P1");
		list.add("000000335699	TIU1.A-P2");
		list.add("000000335699	TIU1.B-P2");
		list.add("000000624935	01-1-02-05");
		list.add("S00013828660	1-1-4-THRU");
		list.add("S00013828660	1-2-4-THRU");
		list.add("000000220903	MCUA");
		list.add("000000303564	FN1-P31");
		list.add("000000383322	PCU");
		for (String string : list) {
			String[] arr = string.split("	");
			String equipId = arr[0];
			String portDesc = arr[1];
			Logger.logger.info("equip_id : {}, port_desc : {}", equipId, portDesc);
			int netSize = netSize(equipId, portDesc);
			Logger.logger.info(" ㄴ 찾은 링 갯수 : {}", netSize);
			int lineSize = lineSize(equipId, portDesc);
			Logger.logger.info(" ㄴ 찾은 회선 갯수 : {}", lineSize);
		}
	}
	
	@SuppressWarnings("unchecked")
	public int netSize(String equipId, String portDesc) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("equipId", equipId);
		param.put("portDesc", portDesc);
		List<Map<String, Object>> list = dbTrans.selectQid2Res("SELECT_TEST___GET_NET_ALARM", param);
		return list.size();
	}
	
	@SuppressWarnings("unchecked")
	public int lineSize(String equipId, String portDesc) throws Exception {
		Map<String, String> param = new HashMap<String, String>();
		param.put("equipId", equipId);
		param.put("portDesc", portDesc);
		List<Map<String, Object>> list = dbTrans.selectQid2Res("SELECT_TEST___GET_LINE_ALARM__LIKE", param);
		return list.size();
	}
	
	public static int iii = 0;
	// 포트, 채널 문자열 확인
	@SuppressWarnings("unused")
	public void getPortAndChl() throws Exception {
		
		DbTrans tran = DBManager.getMgr().getDataBase("MIDBDEV").createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsTeamsQid.QUERY_XML_FILE));
		
		tran.start();
		Map<String, Object> para = new HashMap<String, Object>();
		// para.put("equipId", "000000613037");
		tran.setDaoListener(new DaoListener() {

			@Override
			public void onExecuted(Object arg0, Exception arg1)
					throws Exception {
			}

			@Override
			public void onFinish(Exception arg0) throws Exception {
			}

			@Override
			public void onSelected(int arg0, Object obj) throws Exception {
				LineNetworkBaseVo baseVo = (LineNetworkBaseVo) obj;
				setMoDataMap(baseVo);
			}

			@Override
			public void onStart(String[] arg0) throws Exception {
			}

		});
		
		AdamsTeamsQid QID = new AdamsTeamsQid();
		tran.selectQid2Res(QID.SELECT_LINE_LIST__BY_lineNum_ringNum_equipId, para);
		
		System.out.println(map.size());
		for (String key : map.keySet()) {
			List<LineNetworkBaseVo> list = map.get(key);
//			System.out.println(key + "  :   " + list.size());
		}
	}
	
	public Map<String, List<LineNetworkBaseVo>> map = new HashMap<String, List<LineNetworkBaseVo>>();
	private void setMoDataMap(LineNetworkBaseVo lnBaseVo) {
		List<LineNetworkBaseVo> lnBaseList;
		if (lnBaseVo.getEquipAPortInfo() != null) {
			lnBaseList = map.get(lnBaseVo.getEquipAPortInfo());
			setLnBaseVo2List(lnBaseList, lnBaseVo, map, lnBaseVo.getEquipAPortInfo());
		}
		if (lnBaseVo.getEquipBPortInfo() != null) {
			lnBaseList = map.get(lnBaseVo.getEquipBPortInfo());
			setLnBaseVo2List(lnBaseList, lnBaseVo, map, lnBaseVo.getEquipBPortInfo());
		}
	}
	
	private void setLnBaseVo2List(List<LineNetworkBaseVo> lnBaseList, LineNetworkBaseVo lnBaseVo, Map<String, List<LineNetworkBaseVo>> targetMap, String key) {

		if (lnBaseList == null) {
			lnBaseList = new ArrayList<LineNetworkBaseVo>();
			targetMap.put(key, lnBaseList);
		}
		lnBaseList.add(lnBaseVo);
	}
	
}

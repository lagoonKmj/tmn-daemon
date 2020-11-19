package com.lagoon.tmn.teams.client;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.api.FxApi;
import fxms.bas.co.utils.FxmsClient;
import fxms.bas.fxo.FxCfg;

public class TeamsHandlerClient {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {

		
		DbTrans dbTrans = DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB).createDbTrans(AdamsCfg.getHomeDeployConfSql("test_tmn.xml"));
		
		try {

			for (int i = 0; i < args.length; i++) {
				Logger.logger.debug("args[{}] : {}", i, args[i]);
			}

			if (args.length == 0) {
				Logger.logger
						.error("e.g) release-current-alarm-all, reload, reload-app-data-mgr, reload-alarm-mgr, fire-event(equipId, portDescr isAlarm)");
				System.exit(0);
			}

			String methodName = args[0];

			Map<String, Object> para = new HashMap<String, Object>();
			TrmsNetEventVo trmsNetEventVo = null;

			if (methodName.equals("fire-event")) {

				if (args.length == 4) {
					String equipId = args[1];
					String portDescr = args[2];
					String isAlarm = args[3];

					Map<String, String> param = new HashMap<String, String>();
					param.put("equipId", equipId);
					List<Map<String, String>> list = dbTrans.selectQid2Res("SELECT_TEST___GET_REASON", param);
					
					String reason = list.get(0).get("REASON");
					
					if (reason == null) {
						Logger.logger.error("장비명으로 재검색 을 시도 합니다.");
						List<Map<String, String>> list__2 = dbTrans.selectQid2Res("SELECT_TEST___GET_EQUIP_ID", param);
						if (list__2 == null || list__2.size() == 0) {
							Logger.logger.error("장비ID(명) 을 확인 하세요. 종료~!");
							System.exit(0);
						} else {
							equipId = list__2.get(0).get("EQUIP_ID");
							param.put("equipId", equipId);
							List<Map<String, String>> list__3 = dbTrans.selectQid2Res("SELECT_TEST___GET_REASON", param);
							reason = list__3.get(0).get("REASON");
							if (reason == null) {
								Logger.logger.error("reason 이 존재 하지 않는 장비 모델 입니다. 종료~!");
								System.exit(0);	
							}
						}
					}
					
					
					trmsNetEventVo = new TrmsNetEventVo();
					trmsNetEventVo.setEquipId(equipId);
					trmsNetEventVo.setPortDescr(portDescr);
					trmsNetEventVo.setReason(reason);
					trmsNetEventVo.setLocation(portDescr);
					trmsNetEventVo.setDablGrCd(DABL_GR_CD.Critical.getCode());
					trmsNetEventVo.setFullMsg("by.lagoon");
					trmsNetEventVo.setRecvTime(System.currentTimeMillis());
					if (isAlarm.equalsIgnoreCase("N")) {
						trmsNetEventVo.setClearTime(Long.toString(FxApi
								.getDate(System.currentTimeMillis())));
					} else {
						trmsNetEventVo.setOccurTime(Long.toString(FxApi
								.getDate(System.currentTimeMillis())));
					}
					para.put("event", trmsNetEventVo);

				} else {
					Logger.logger
							.error("e.g) fire-event(equipId, portDescr, isAlarm)");
					System.exit(0);
				}
			}
			FxmsClient client = new FxmsClient(FxCfg.getCfg().getString(
					"APP_SERVER", "localhost"), 10005);
			client.login("AdamsService", "AdamsService");

			client.testRetObj("teams", methodName, para);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			dbTrans.setDaoListener(null);
			dbTrans.stop();
		}

	}
}

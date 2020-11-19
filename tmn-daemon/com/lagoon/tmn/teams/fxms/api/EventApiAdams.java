package com.lagoon.tmn.teams.fxms.api;

import java.rmi.NotBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsAlarmCode;
import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.OMN30203;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;
import fxms.bas.ao.AlarmEvent;
import fxms.bas.ao.AoCode.AlarmLevel;
import fxms.bas.ao.vo.Alarm;
import fxms.bas.ao.vo.AlarmCfg;
import fxms.bas.ao.vo.AlarmCode;
import fxms.bas.ao.vo.OccurAlarm;
import fxms.bas.api.EventApi;
import fxms.bas.api.ServiceApi;
import fxms.bas.fxo.service.app.AppService;

public class EventApiAdams extends EventApi {

	@Override
	public List<AlarmCfg> doSelectAlarmCfgAll(Map<String, Object> parameters) throws Exception {
		return new ArrayList<AlarmCfg>();
	}

	@Override
	public List<AlarmCode> doSelectAlarmCodeAll() throws Exception {
		FxDaoExecutor tran = DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB).createFxDao();
		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("netClCd", "02");
			List<AlarmCode> ret = new ArrayList<AlarmCode>();
			List<OMN30203> list = tran.select(OMN30203.class, para, OMN30203.class);
			AdamsAlarmCode c;
			for (OMN30203 e : list) {

				c = new AdamsAlarmCode((int) e.getDablCdSerNum(), e.getDablCdNm(), AlarmLevel.Critical, "", "EQ", e.getDablCdNm(),
						null, "NE", 0, true);
				ret.add(c);
			}
			return ret;
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	@Override
	public List<Alarm> doSelectCurAlarmAll(Map<String, Object> parameters) throws Exception {
		return new ArrayList<Alarm>();
	}

	@Override
	public OccurAlarm doSelectAlarmHst(long alarmNo) throws Exception {
		return null;
	}

	@Override
	protected Alarm doSendEvent(AlarmEvent event) {
		try {
			AppService appService = ServiceApi.getApi().getService(AppService.class);
			return appService.onEvent(event);
		} catch (NotBoundException e) {
			Logger.logger.fail("{} {}", e.getClass().getSimpleName(), e.getMessage());
			return null;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

}

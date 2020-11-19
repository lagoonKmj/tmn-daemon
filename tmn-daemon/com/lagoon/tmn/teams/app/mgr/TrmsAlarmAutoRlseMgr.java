package com.lagoon.tmn.teams.app.mgr;

import java.sql.Timestamp;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.bas.co.cron.Cron;
import fxms.bas.fxo.thread.CycleFxThread;

/**
 * 자동해제시간 이 지정된 장애를 릴리즈 한다.
 * 
 * 
 * @author lagoon(강명중)
 *
 */
public class TrmsAlarmAutoRlseMgr extends CycleFxThread implements IManager {

	/**
	 * 
	 * CycleFxThread 1MIN
	 * 
	 * @throws Exception
	 */
	public TrmsAlarmAutoRlseMgr() throws Exception {
		super("ALARM_AUTO_RELEASE-THREAD", Cron.EVERY_MINUTES);
	}

	@Override
	protected void doCycle(long arg0) {

		if (AdamsApiApp.getApiApp().isReady()) {
			reload();
		}

	}

	@Override
	public void init() {
		Logger.logger.trace("TrmsAlarmAutoRlseMgr Initalize.");
		start();
	}

	@Override
	public void reload() {

		if (!AdamsApiApp.getApiApp().isReady()) {
			return;
		}
		
		List<OMN33810> releaseAlarmList = AdamsApiApp.getApiApp()
				.findReleaseAlarmList();

		StringBuffer sbLog = new StringBuffer();
		sbLog.append(Logger.makeString("자동해제시간에 따른 장애 릴리즈 건수",
				releaseAlarmList.size()));

		String rlseDtm = Long.toString(FxApi.getDate());
		Timestamp rlseRcvDtm = new Timestamp(System.currentTimeMillis());

		for (OMN33810 omn33810 : releaseAlarmList) {
			AdamsApiApp.getApiApp().releaseCurrentAlarm(omn33810, rlseDtm,
					rlseRcvDtm);

			sbLog.append(Logger.makeSubString("OMN33810", omn33810));
		}

		Logger.logger.debug(sbLog.toString());
	}

	@Override
	public boolean isReady() {
		return true;
	}

}

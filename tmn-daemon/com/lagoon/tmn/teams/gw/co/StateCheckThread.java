package com.lagoon.tmn.teams.gw.co;

import subkjh.bas.co.log.Logger;

import com.lagoon.tmn.teams.co.AdamsApi;
import com.lagoon.tmn.teams.co.vo.DataStateVo;

import fxms.bas.api.FxApi;
import fxms.bas.fxo.thread.CycleFxThread;

public class StateCheckThread extends CycleFxThread {

	private long lastMstime = 0;

	public StateCheckThread() {
		super("StateCheckThread", 30, true);
	}

	@Override
	protected void doCycle(long mstime) {
		try {
			DataStateVo vo = AdamsApi.getApi().getChangedState(lastMstime);
			lastMstime = vo.getLastMstime();

			StringBuffer sb = new StringBuffer();
			sb.append(Logger.makeString(
					"Data Checking",
					"ne=" + vo.isNeChanged() + ",net=" + vo.isNetworkChanged() + ",line=" + vo.isLineChanged() + ","
							+ FxApi.getDate(vo.getLastMstime())));
			Logger.logger.debug(sb.toString());

		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}
}

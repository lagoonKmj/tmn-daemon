package com.lagoon.tmn.teams.client;

import java.util.Map;

import subkjh.bas.co.log.Logger;
import fxms.bas.fxo.FxCfg;

public class ClientCfg {

	public ClientCfg() {
		// TODO Auto-generated constructor stub
	}

	public static void initSingleStart(String args[]) {
		FxCfg.setFxServiceName("TeamsAlarmClientService");
		try {
			Map<String, Object> para = FxCfg.parse(args);
			Logger.logger.info("{}", para.toString());
			FxCfg.getCfg().setPara(para);
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}
}

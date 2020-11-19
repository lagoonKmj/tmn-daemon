package com.lagoon.tmn.teams.client;

import java.io.File;
import java.util.Arrays;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import fxms.bas.fxo.FxCfg;

public abstract class SingleStart {

	protected String appSvrIpAddress;
	protected int appSvrPortNo;
	protected String pushIpAddress;
	protected int pushPortNo;
	protected String nmsCd;
	
	public SingleStart(String name, String args[]) {

		Logger.logger = Logger.createLogger(FxCfg.getHome() + File.separator + "logs" + File.separator, name);
		Logger.logger.setLevel(LOG_LEVEL.debug);
		Logger.logger.debug("{}", args == null ? "" : Arrays.toString(args));

		ClientCfg.initSingleStart(args);

		appSvrIpAddress = FxCfg.getCfg().getString("APP_SERVER",  null);
		appSvrPortNo = FxCfg.getCfg().getInt("APP_SERVER_PORT", 5000);
		pushIpAddress = FxCfg.getCfg().getString("WEB_PUSH_IPADDRESS",  null);
		pushPortNo = FxCfg.getCfg().getInt("WEB_PUSH_PORT", 32771);
		nmsCd = FxCfg.getCfg().getString("NMS_CD", "030");

		Logger.logger.info("ADAMS-TMN App-Server, IP-ADDRESS  : {}, PORT : {}", appSvrIpAddress,  appSvrPortNo);
		Logger.logger.info("ADAMS-WEB Push-Server, IP-ADDRESS  : {}, PORT : {}", pushIpAddress,  pushPortNo);
		
		run();
		
	}

	protected abstract void run();

}

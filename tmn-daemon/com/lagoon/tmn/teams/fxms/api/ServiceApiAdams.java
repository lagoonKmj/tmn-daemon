package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.List;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.ServiceApi;
import fxms.bas.co.vo.FxServiceVo;
import fxms.bas.mo.Mo;

public class ServiceApiAdams extends ServiceApi {

	@Override
	protected void doAddService(String arg0, String arg1, String arg2) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doRemoveService(String arg0, String arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Mo doSelectMyMo(String arg0, String arg1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<FxServiceVo> doServiceList() throws Exception {

		List<FxServiceVo> ret = new ArrayList<FxServiceVo>();

		for (String key : getFxPara().getMap().keySet()) {
			if (key.endsWith("Service")) {
				ret.add(new FxServiceVo("localhost", key, getFxPara().getString(key)));
			}
			Logger.logger.info("para-key={}", key);
		}

		return ret;
	}

	@Override
	protected void doSetAllServiceStatus(String arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doUpdateServiceStatus(String arg0, String arg1, long arg2, String arg3) throws Exception {
		// TODO Auto-generated method stub

	}

}

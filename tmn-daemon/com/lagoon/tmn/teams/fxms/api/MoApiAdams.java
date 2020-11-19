package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.dao.AdamsDao;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.MoApi;
import fxms.bas.mo.Mo;
import fxms.bas.mo.attr.MoLocation;
import fxms.bas.mo.attr.Model;
import fxms.bas.mo.child.MoConfig;

public class MoApiAdams extends MoApi {

	@Override
	protected Mo doAdd(Mo arg0, MoConfig arg1, String arg2, int arg3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Mo> doDelete(Mo arg0, int arg1, String arg2) throws Exception {
		return new ArrayList<Mo>();
	}

	@Override
	protected List<Mo> doSelectMo(Map<String, Object> arg0, boolean arg1) throws Exception {
		return new ArrayList<Mo>();
	}

	@Override
	protected List<MoLocation> doSelectLocationList() throws Exception {
		return new ArrayList<MoLocation>();
	}

	@Override
	protected List<Model> doSelectModelList() throws Exception {
		return new ArrayList<Model>();
	}

	@Override
	protected void doSetMoChildren(MoConfig moConfig) throws Exception {
		try {
			new AdamsDao().updateMoConfig(moConfig);
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	@Override
	protected Mo doUpdate(Mo arg0, Map<String, Object> arg1) throws Exception {
		return null;
	}

	@Override
	protected Mo doUpdate(Mo arg0, MoConfig arg1) throws Exception {
		return null;
	}

}

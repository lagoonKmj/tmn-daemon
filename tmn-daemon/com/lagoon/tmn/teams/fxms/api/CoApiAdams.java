package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import subkjh.bas.co.log.LOG_LEVEL;
import fxms.bas.api.CoApi;
import fxms.bas.co.AmGroupVo;
import fxms.bas.co.AmHstVo;
import fxms.bas.co.OpCode;
import fxms.bas.co.vo.FxVar;
import fxms.module.restapi.vo.SessionVo;

public class CoApiAdams extends CoApi {

	private long sessionNo = System.currentTimeMillis();

	@Override
	public String getState(LOG_LEVEL arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<OpCode> doSelectOpCode() throws Exception {
		return new ArrayList<OpCode>();
	}

	@Override
	protected Map<Long, List<AmGroupVo>> loadAmGroup() throws Exception {
		return new HashMap<Long, List<AmGroupVo>>();
	}

	@Override
	public void logAmHst(List<AmHstVo> arg0) throws Exception {
	}

	@Override
	public void logUserAccess() {
	}

	@Override
	public void logUserOp(int arg0, String arg1, OpCode arg2, String arg3, String arg4, int arg5, String arg6, long arg7) {
	}

	private synchronized long getNext() {
		return ++sessionNo;
	}

	@Override
	protected SessionVo doLogin(String userId, String password, String hostname) throws Exception {
		SessionVo vo = new SessionVo();
		vo.setSessionId("TEAMS-" + getNext());
		return vo;
	}

	@Override
	protected void doLogout(String arg0) throws Exception {
	}

	@Override
	protected void reload() throws Exception {
	}

	@Override
	protected FxVar doSelectVar(String arg0) throws Exception {
		return null;
	}

	@Override
	protected List<FxVar> doSelectVarAll() throws Exception {
		return new ArrayList<FxVar>();
	}

	@Override
	protected void doUpdateVarValue(String arg0, Object arg1) throws Exception {

	}

}

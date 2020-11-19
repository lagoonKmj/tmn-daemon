package com.lagoon.tmn.teams.fxms.ao;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.soproth.FxJsonSoproth;

import com.google.gson.Gson;
import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.fxms.ao.pdu.BodyLogin;

public class TeamsJSonSoproth extends FxJsonSoproth {

	private BodyLogin loginInfo;

	public TeamsJSonSoproth() {

	}

	@Override
	protected void processBody(int length, byte[] bytes) {

		String json = new String(bytes);

		Gson gson = new Gson();
		TeamsAlarmFxEvent teamsAlarmFxEvent = gson.fromJson(json,
				TeamsAlarmFxEvent.class);

		switch (teamsAlarmFxEvent.getType()) {
		case TeamsAlarmFxEvent.LOGIN:
			loginInfo = new BodyLogin();
			loginInfo.setUserId(teamsAlarmFxEvent.getUserId());
			loginInfo.setPasswd(teamsAlarmFxEvent.getPasswd());
			teamsAlarmFxEvent.setMsg("LOGIN OK");
			try {
				send(FxJsonSoproth.makePduByte(teamsAlarmFxEvent.toJson()));
			} catch (Exception e) {
				Logger.logger.error(e);
			}
			break;
		case TeamsAlarmFxEvent.ECHO:
			teamsAlarmFxEvent.setMsg("ECHO OK");
			try {
				send(FxJsonSoproth.makePduByte(teamsAlarmFxEvent.toJson()));
			} catch (Exception e) {
				Logger.logger.error(e);
			}
			break;
		case TeamsAlarmFxEvent.ALARM:
		case TeamsAlarmFxEvent.NO_SEND_ALARM:
			AdamsApiApp.getApiApp().updateAlarmStatus(teamsAlarmFxEvent, false);
			break;
		}
	}

	@Override
	protected void initProcess() throws Exception {
		// TODO Auto-generated method stub
	}

	/**
	 * LOGIN된 클라이언트에게만 내용을 보낸다.
	 */
	@Override
	public void send(byte bytes[]) throws Exception {
		if (loginInfo != null) {
			super.send(bytes);
		}
	}

}

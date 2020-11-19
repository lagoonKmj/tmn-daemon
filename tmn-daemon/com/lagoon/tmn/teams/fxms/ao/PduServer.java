package com.lagoon.tmn.teams.fxms.ao;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.NioServer;
import subkjh.bas.net.soproth.FxJsonSoproth;

import com.google.gson.Gson;
import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.AdamsApiApp;

import fxms.bas.co.noti.FxEvent;
import fxms.bas.co.noti.NotiReceiver;
import fxms.bas.fxo.FxPara;
import fxms.bas.fxo.service.property.FxServiceMember;

/**
 * 장애가 발생/해제된 경우 방송하는 서버
 * 
 * @author subkjh(김종훈)
 *
 */
public class PduServer extends NioServer<FxJsonSoproth> implements
		FxServiceMember, NotiReceiver {

	public static void main(String[] args) {
		PduServer server = new PduServer();
		try {

			server.startMember();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FxPara data;

	public PduServer() {
		data = new FxPara();
	}

	@Override
	public FxPara getFxPara() {
		return data;
	}

	@Override
	public void setPara(String name, String value) {
		data.set(name, value);
	}

	@Override
	public void onCreated() {
	}

	/**
	 * 장애 발생/해제 내용을 클라이언트에 보낸다.
	 * 
	 * <pre>
	 * 	클라이언트에 수신 데이터 변경 처리 함
	 * </pre>
	 */
	@Override
	public void onNotify(FxEvent event) throws Exception {

		Gson gson = new Gson();
		String json = gson.toJson(event);

		byte bytes[] = FxJsonSoproth.makePduByte(json);
		this.broadcast(bytes);

		if (event instanceof TeamsAlarmFxEvent) {
			TeamsAlarmFxEvent teamsAlarmFxEvent = (TeamsAlarmFxEvent) event;
			updateAlarmStatus(teamsAlarmFxEvent);
		}
	}

	private void updateAlarmStatus(TeamsAlarmFxEvent teamsAlarmFxEvent) {
		AdamsApiApp.getApiApp().updateAlarmStatus(teamsAlarmFxEvent, true);
	}

	@Override
	public void setName(String arg0) {

	}

	@Override
	public void startMember() throws Exception {
		try {
			this.startServer("TEAMS-CLIENT-SUPPORT", null, 5000,
					TeamsJSonSoproth.class);
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}

}

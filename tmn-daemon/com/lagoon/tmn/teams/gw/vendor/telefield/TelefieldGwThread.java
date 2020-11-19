package com.lagoon.tmn.teams.gw.vendor.telefield;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

/**
 * 텔레필드 EMS에 클라이언트로 접속하여 장애를 계속 받는 쓰레드
 * 
 * @author subkjh(김종훈)
 *
 */
public class TelefieldGwThread extends GwDcnMngThread {

	private Telefield telefield;

	public TelefieldGwThread() throws Exception {
		telefield = new Telefield();
	}

	@Override
	protected AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception {
		return telefield.makeAlarmAdapter(dcn, this);
	}

	@Override
	protected List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn) throws Exception {
		return telefield.makeAdapter(dcn).collectAlarm();
	}

}

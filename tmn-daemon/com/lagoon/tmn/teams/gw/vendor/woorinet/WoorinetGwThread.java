package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

public class WoorinetGwThread extends GwDcnMngThread {

	private Woorinet woorinet;

	public WoorinetGwThread() throws Exception {
		woorinet = new Woorinet();
	}

	@Override
	protected AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception {
		return woorinet.makeAlarmAdapter(dcn, this);
	}

	@Override
	protected List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn) throws Exception {
		return woorinet.makeAdapter(dcn).collectAlarm();
	}

}
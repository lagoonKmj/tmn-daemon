package com.lagoon.tmn.teams.gw.vendor.nokia;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

public class NokiaGwThread extends GwDcnMngThread {

	private Nokia nokia;

	public NokiaGwThread() throws Exception {
		nokia = new Nokia();
	}

	@Override
	protected AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception {
		return nokia.makeAlarmAdapter(dcn, this);
	}

	@Override
	protected List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn) throws Exception {
		return nokia.makeAdapter(dcn).collectAlarm();
	}

}
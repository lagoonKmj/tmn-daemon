package com.lagoon.tmn.teams.gw.vendor.coweaver;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

public class CoweaverGwThread extends GwDcnMngThread {

	private Coweaver coweaver;

	public CoweaverGwThread() throws Exception {
		coweaver = new Coweaver();
	}

	@Override
	protected AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception {
		return coweaver.makeAlarmAdapter(dcn, this);
	}

	@Override
	protected List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn) throws Exception {
		return coweaver.makeAdapter(dcn).collectAlarm();
	}

}

package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.daemon.AlarmAdapter;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;

public class CienaGwThread extends GwDcnMngThread {

	private Ciena ciena;

	public CienaGwThread() throws Exception {
		ciena = new Ciena();
	}

	@Override
	protected AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception {
		return ciena.makeAlarmAdapter(dcn, this);
	}

	@Override
	protected List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn) throws Exception {
		return ciena.makeAdapter(dcn).collectAlarm();
	}

}

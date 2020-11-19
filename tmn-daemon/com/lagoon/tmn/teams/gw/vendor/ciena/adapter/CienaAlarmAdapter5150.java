package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TelnetAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.Ciena;

public class CienaAlarmAdapter5150 extends TelnetAlarmAdapter<EquipDcn> {

	private final String PROMPT = "> ";

	public CienaAlarmAdapter5150(EquipDcn dcn, GwDcnMngThread gwThread,
			Ciena ciena) {

		super(dcn, gwThread, ciena);

		this.setLoginPrompt("login:", "Password:", new String[] { PROMPT });
	}

	@Override
	protected DetectedAlarmVo parseAlarm(String msg) throws Exception {
		
		DetectedAlarmVo detectedAlarm = parser.parseAlarm(dcn, msg, dcn.getEquipTidVal());

		return detectedAlarm;
	}

}
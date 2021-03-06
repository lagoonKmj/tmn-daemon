package com.lagoon.tmn.teams.gw.vendor.coweaver.adapter;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TelnetAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.coweaver.Coweaver;

public class CoweaverAlarmAdapter7300 extends TelnetAlarmAdapter<EquipDcn> {

	private final String PROMPT = "> ";

	public CoweaverAlarmAdapter7300(EquipDcn dcn, GwDcnMngThread gwThread,
			Coweaver parser) {

		super(dcn, gwThread, parser);

		this.setLoginPrompt("Login ID:", "Password:", new String[] { PROMPT });
	}

	@Override
	protected DetectedAlarmVo parseAlarm(String msg) throws Exception {
		
		DetectedAlarmVo detectedAlarm = parser.parseAlarm(dcn, msg, dcn.getEquipTidVal());

		return detectedAlarm;
	}

}
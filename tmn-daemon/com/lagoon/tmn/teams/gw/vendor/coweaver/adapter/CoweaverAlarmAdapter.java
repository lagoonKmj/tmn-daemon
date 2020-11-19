package com.lagoon.tmn.teams.gw.vendor.coweaver.adapter;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TelnetAlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.coweaver.Coweaver;

public class CoweaverAlarmAdapter extends TelnetAlarmAdapter<EquipDcn> {

	public static void main(String[] args) throws Exception {
		EquipDcn dcn = new EquipDcn();
		dcn.setEquipIpAddr("12.160.11.171");
		dcn.setDablPortNum(9000);
		dcn.setLoginId("admin");
		dcn.setLoginPwd("admin");
		CoweaverAlarmAdapter adapter = new CoweaverAlarmAdapter(dcn, null, new Coweaver());
		adapter.startDcn();
	}

	private final String PROMPT = "> ";

	public CoweaverAlarmAdapter(EquipDcn dcn, GwDcnMngThread gwThread,
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
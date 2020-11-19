package com.lagoon.tmn.teams.fxms.api;

import fxms.bas.api.VoApi;
import fxms.bas.co.def.PS_TYPE;

public class VoApiAdams extends VoApi {

	@Override
	public String dropPsTables() throws Exception {
		return "Do Not Drop Tables";
	}

	@Override
	public String dropPsTables(PS_TYPE arg0, long arg1) throws Exception {
		return "Do Not Drop Tables";
	}

	@Override
	public String makePsTables() throws Exception {
		return "Do Not Make Tables";
	}

	@Override
	public String makePsTables(PS_TYPE arg0, long arg1) throws Exception {
		// TODO Auto-generated method stub
		return "Do Not Make Tables";
	}

}

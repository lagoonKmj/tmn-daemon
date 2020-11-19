package com.lagoon.tmn.teams.app.cron;

import com.lagoon.tmn.teams.co.dao.AdamsOMN25112Dao;

import fxms.bas.co.cron.Crontab;

public class MakeStatisticCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1518337911629330781L;

	@Override
	public void cron() throws Exception {
		new AdamsOMN25112Dao().makeTemp();
	}

	@Override
	public String getGroup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 0;
	}

}

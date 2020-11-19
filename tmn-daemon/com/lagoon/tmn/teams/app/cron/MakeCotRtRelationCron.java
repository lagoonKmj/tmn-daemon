package com.lagoon.tmn.teams.app.cron;

import com.lagoon.tmn.teams.co.dao.AdamsOIV28101Dao;

import fxms.bas.co.cron.Crontab;

/**
 * COT & RT관계를 주기적으로 생성하는 크론
 * 
 * @author subkjh(김종훈)
 *
 */
public class MakeCotRtRelationCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2759465257613609895L;

	public MakeCotRtRelationCron() {
	}

	@Override
	public void cron() throws Exception {

		new AdamsOIV28101Dao().makeCotRtRelation();

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

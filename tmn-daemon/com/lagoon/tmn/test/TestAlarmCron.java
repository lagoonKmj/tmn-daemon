package com.lagoon.tmn.test;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.gw.co.GwApi;

import fxms.bas.co.cron.Crontab;

public class TestAlarmCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8619965520127332017L;

	@Override
	public void cron() throws Exception {

		List<TrmsNetEventVo> list = new TestTmnDao().selectAlarm();
		if (list != null && list.size() > 0) {
			for (TrmsNetEventVo vo : list) {
				vo.setRecvTime(System.currentTimeMillis());
				GwApi.getApi().callFireEvent(vo);
			}
		}

		List<TrmsNetEventVo> list__2 = new TestTmnDao().selectAlarmClear();
		if (list__2 != null && list__2.size() > 0) {
			for (TrmsNetEventVo vo : list__2) {
				vo.setRecvTime(System.currentTimeMillis());
				GwApi.getApi().callFireEvent(vo);
			}
		}
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

package com.lagoon.tmn.teams.app.cron;

import subkjh.bas.co.log.Logger;

import com.lagoon.tmn.mig.MigTeamsDcnDao;
import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.co.dao.AdamsTeamsConfDao;

import fxms.bas.co.cron.Crontab;

/**
 * 특정 시간에 데이터를 변경(크론)
 *
 * <pre>
 *  매시 01분에 실행
 *  1. 분석 서버 초기화
 *  2. 분석 서버 리로드
 *  3. 전송장비 동기화 일배치 후, 필요 테이블에 데이터 머지
 *  4. 장비연동관련 '접속상태값 변경' (OIV10104)
 *
 * </pre>
 *
 * @author lagoon(강명중)
 *
 */
public class AppDataCron extends Crontab {

	private static final long serialVersionUID = 8621802237367257805L;

	private boolean isInitalize = false;

	@Override
	public void cron() throws Exception {

		Thread.sleep(3000L);
		
		// AppService 최초 실행시 초기화를 한다.
		if (isInitalize == false) {
			Logger.logger.info("AppDataCron : Initalize for App Server");
			AdamsApiApp.getApiApp();
			isInitalize = true;
		} else {
			Logger.logger.info("AppDataCron : Reload for App Server");
			AdamsApiApp.getApiApp().reloadMgr();
		}
		Logger.logger.info("AppDataCron : mergeData(OIV10011, OIV10115, OIV10104)");
		mergeData();
	}

	/**
	 * <pre>
	 * 3. 전송장비 동기화 일배치 후, 필요 테이블에 데이터 머지
	 * 	- OIV10011
	 * 	- OIV10115
	 * 4. 장비연동관련 '접속상태값 변경'
	 *  - OIV10104
	 * </pre>
	 */
	private void mergeData() {
		try {
			/* CM-BATCH에서 진행 하므로 주석 처리함. 20.11.17 by.lagoon 
			new AdamsTeamsConfDao().mergeOIV10011();
			new AdamsTeamsConfDao().mergeOIV10115();
			*/
			new MigTeamsDcnDao().mergeOIV10104();
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}
	
	@Override
	public String getGroup() {
		return "AppDataCron";
	}

	@Override
	public String getLog() {
		return null;
	}

	@Override
	public int getOpcode() {
		return 0;
	}

}

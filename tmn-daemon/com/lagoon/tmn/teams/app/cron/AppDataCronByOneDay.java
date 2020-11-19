package com.lagoon.tmn.teams.app.cron;

import java.util.List;

import com.lagoon.tmn.mig.MigTeamsDcnDao;
import com.lagoon.tmn.teams.co.dao.AlarmDao;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Crontab;

/**
 * 특정 시간에 데이터를 변경(크론)
 * 
 * <pre>
 *  07시11분 실행
 *  1. 불필요한 장애 데이터를 삭제
 *  2. EMS 데이터 머지
 * 		- OIV10105, OIV10106
 *  3.. 전송장비이벤트 머지
 *      - OMN33812
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
public class AppDataCronByOneDay extends Crontab {

	private static final long serialVersionUID = 8621802237367257805L;

	@Override
	public void cron() throws Exception {
		
		Thread.sleep(3000L);
		
		Logger.logger.info("AppDataCronByOneDay : Deleted old alarm");
		removeOldAlarms();
		
		Logger.logger.info("AppDataCronByOneDay : mergeData(OIV10105, OIV10106, OMN33812)");
		mergeData();
	}
	
	/**
	 * 클라이언트에 수신 이 완료 된 일주일이 지난 장애 데이터 삭제
	 * @throws Exception 
	 * 
	 */
	private void removeOldAlarms() throws Exception {
		// 해당 되는 타겟 조회
		List<OMN33810> omn33810List = new AlarmDao()
				.selectEquipAlarmList4Deleted();
		// 장비 장애번호 기준 데이터 삭제
		for (OMN33810 omn33810 : omn33810List) {
			new AlarmDao().deleteAlarmByTrmsEquipDablNum(omn33810
					.getTrmsEquipDablNum());
		}
	}
	
	/**
	 * <pre>
	 * 3. 전송장비이벤트 머지 (TODO 추후 필요없음)
	 * 	- OMN33812
	 * 4. DCN, EMS 머지
	 *  - 
	 * </pre>
	 */
	private void mergeData() {
		try {
			new MigTeamsDcnDao().mergeOMN33812();
			new MigTeamsDcnDao().mergeDcnAndEms();
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	@Override
	public String getGroup() {
		return "AppDataCronByOneDay";
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

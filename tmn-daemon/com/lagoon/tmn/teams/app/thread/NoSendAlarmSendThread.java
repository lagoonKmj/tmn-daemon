package com.lagoon.tmn.teams.app.thread;

import java.util.List;

import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.dao.AlarmDao;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33820;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Cron;
import fxms.bas.co.noti.FxEvent.STATUS;
import fxms.bas.fxo.service.FxServiceImpl;
import fxms.bas.fxo.thread.CycleFxThread;

/**
 * 상위 시스템에 전달되지 않은 장애를 다시 보내는 스레드
 * 
 * @author subkjh(김종훈)
 *
 */
public class NoSendAlarmSendThread extends CycleFxThread {

	/** SEND_KIND = 1:미전송 장애 모두, 2:미해제된 장애중 미전송 장애만 */
	private int sendKind;

	public NoSendAlarmSendThread() throws Exception {
		super("NOSEND-ALARM-SEND-THREAD", Cron.CYCLE_30_SECONDS);
		start();
	}

	@Override
	public void onCreated() {
		sendKind = getFxPara().getInt("SEND_KIND", 1);
	}

	@Override
	protected void doCycle(long mstime) {

		try {
			// 미전송 장비 장애
			List<OMN33810> alarmList = selectNoSendEquipAlarmList(false);
			for (OMN33810 omn33810 : alarmList) {
				onNotify(omn33810, null, null, STATUS.added);
			}
			// 미전송 장비 장애 해제
			List<OMN33810> alarmClearList = selectNoSendEquipAlarmList(true);
			for (OMN33810 omn33810 : alarmClearList) {
				onNotify(omn33810, null, null, STATUS.deleted);
			}
			// 미전송 망 장애
			List<OMN33830> netAlarmList = selectNoSendNetAlarmList();
			for (OMN33830 omn33830 : netAlarmList) {
				OMN33810 omn33810 = selectEquipAlarmByTrmsEquipDablNum(omn33830
						.getTrmsEquipDablNum());
				if (omn33810 != null) {
					onNotify(omn33810, null, omn33830, STATUS.added);
				}
			}

			// 미전송 회선 장애
			List<OMN33820> lineAlarmList = selectNoSendLineAlarmList();
			for (OMN33820 omn33820 : lineAlarmList) {
				OMN33810 omn33810 = selectEquipAlarmByTrmsEquipDablNum(omn33820
						.getTrmsEquipDablNum());
				if (omn33810 != null) {
					onNotify(omn33810, omn33820, null, STATUS.added);
				}
			}

		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}

	private void onNotify(OMN33810 omn33810, OMN33820 omn33820,
			OMN33830 omn33830, STATUS status) throws Exception {

		if (sendKind == 2 && omn33810.getRlseDtm() == null) {
			return;
		}

		TeamsAlarmFxEvent teamsAlarmFxEvent = new TeamsAlarmFxEvent();
		teamsAlarmFxEvent.setType(TeamsAlarmFxEvent.NO_SEND_ALARM);
		teamsAlarmFxEvent.setStatus(status);
		teamsAlarmFxEvent.setOmn33810(omn33810);
		teamsAlarmFxEvent.setOmn33820(omn33820);
		teamsAlarmFxEvent.setOmn33830(omn33830);
		FxServiceImpl.fxService.onNotify(teamsAlarmFxEvent);
	}

	private List<OMN33810> selectNoSendEquipAlarmList(boolean isClear) {
		try {
			return new AlarmDao().selectNoSendEquipAlarmList(isClear);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private List<OMN33830> selectNoSendNetAlarmList() {
		try {
			return new AlarmDao().selectNoSendNetAlarmList();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private List<OMN33820> selectNoSendLineAlarmList() {
		try {
			return new AlarmDao().selectNoSendLineAlarmList();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private OMN33810 selectEquipAlarmByTrmsEquipDablNum(long trmsEquipDablNum) {
		try {
			return new AlarmDao()
					.selectEquipAlarmByTrmsEquipDablNum(trmsEquipDablNum);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

}

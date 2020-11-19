package com.lagoon.tmn.teams.gw.co;

import subkjh.bas.co.log.Logger;

import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;

import fxms.bas.fxo.thread.CycleFxThread;

/**
 * 처리되지 않은 이벤트를 처리하는 쓰레드
 * 
 * @author 손지은
 *
 */
public class FireEventThread extends CycleFxThread {

	public FireEventThread() {
		super("FireEventThread", 10, true);
	}

	@Override
	protected void doCycle(long mstime) {

		StringBuffer sb = new StringBuffer();
		GwApi api = GwApi.getApi();

		try {

			TrmsNetEventVo list[] = api.getNotSendEventList();

			for (TrmsNetEventVo e : list) {
				OMN33811Ext ret = GwApi.getApi().callFireEvent(e);
				if (ret != null) {
					api.checkOMN33811(ret);
					api.removeNotSend(e);
					e.setFailEvtNm(e.getFailEvtNm() - 1);
					sb.append(Logger.makeString("Send Event OK.", "Event key="
							+ e.getFullMsg()));
				} else {
					e.setFailEvtNm(e.getFailEvtNm() + 1);
					sb.append(Logger.makeString("Send Event Fail.",
							"Event key=" + e.getFullMsg()));
				}

			}

			Long clearList[] = api.getNotSendClearAlarmList();

			for (Long trmsEquipDablNum : clearList) {

				OMN33811Ext ret = api.callClearAlarm(trmsEquipDablNum);
				if (ret != null) {
					api.checkOMN33811(ret);
					api.removeNotSend(trmsEquipDablNum);
					sb.append(Logger.makeString("Send Event OK.", "Event key="
							+ trmsEquipDablNum));
				} else {
					sb.append(Logger.makeString("Send Event Fail.",
							"Event key=" + trmsEquipDablNum));
				}

			}
			
		} catch (Exception e) {
			Logger.logger.error(e);
		}

		if (sb.length() > 0) {
			Logger.logger.debug(sb.toString());
		}

	}

}

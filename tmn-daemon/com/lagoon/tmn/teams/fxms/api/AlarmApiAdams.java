package com.lagoon.tmn.teams.fxms.api;

import com.lagoon.tmn.teams.fxms.ao.vo.ClearAlarmVo;
import com.lagoon.tmn.teams.fxms.ao.vo.OccurEquipAlarmVo;

import fxms.bas.ao.AlarmEvent;
import fxms.bas.ao.vo.Alarm;
import fxms.bas.ao.vo.AlarmCode;
import fxms.bas.ao.vo.ClearAlarm;
import fxms.bas.ao.vo.OccurAlarm;
import fxms.bas.api.AlarmApi;
import fxms.bas.mo.Mo;

public class AlarmApiAdams extends AlarmApi {

	@Override
	protected void doDeleteAlarm(ClearAlarm arg0, Alarm arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doInsertAlarm(OccurAlarm arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void doUpdateAlarm(AlarmEvent arg0, long arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected Alarm makeBroadcastAlarmClass(OccurAlarm alarm) {
		return alarm;
	}

	protected ClearAlarm makeClearAlarmClass() throws Exception {
		return new ClearAlarmVo();
	}

	protected OccurAlarm makeOccurAlarmClass() throws Exception {
		return new OccurEquipAlarmVo();
	}

	@Override
	protected void doInitOccurAlarm(OccurAlarm arg0, AlarmEvent arg1, AlarmCode arg2, Mo arg3, Mo arg4) {
		// TODO Auto-generated method stub
		
	}

}

package com.lagoon.tmn.teams.gw.daemon.actor;

import subkjh.bas.co.log.Logger;
import fxms.bas.fxo.FxActorImpl;
import fxms.nms.co.snmp.trap.TrapNode;
import fxms.nms.co.snmp.trap.adapter.TrapAdapter;
import fxms.nms.co.snmp.trap.vo.TrapVo;

public class TestTrapActor extends FxActorImpl implements TrapAdapter {

	@Override
	public TrapVo parse(TrapNode node, TrapVo vo) {
		if (node == null) {
			return vo;
		}

		Logger.logger.debug("{}", vo);

		return vo;
	}

}

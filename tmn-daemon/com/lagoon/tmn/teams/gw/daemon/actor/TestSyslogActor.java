package com.lagoon.tmn.teams.gw.daemon.actor;

import fxms.bas.fxo.FxActorImpl;
import fxms.nms.co.syslog.adapter.SyslogAdapter;
import fxms.nms.co.syslog.mo.SyslogNode;
import fxms.nms.co.syslog.vo.SyslogVo;

public class TestSyslogActor extends FxActorImpl implements SyslogAdapter {

	@Override
	public SyslogVo parse(SyslogNode node, SyslogVo vo) throws Exception {
		System.out.println(vo.getMsg());
		return vo;
	}

}

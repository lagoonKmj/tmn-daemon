package com.lagoon.tmn.teams.gw.vendor.nokia.adapter;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.nokia.Nokia;

import fxms.bas.fxo.FxActorImpl;
import fxms.bas.fxo.thread.CycleFxThread;
import fxms.nms.api.SyslogApi;
import fxms.nms.co.syslog.adapter.SyslogAdapter;
import fxms.nms.co.syslog.mo.SyslogNode;
import fxms.nms.co.syslog.vo.SyslogParsingResultVo;
import fxms.nms.co.syslog.vo.SyslogVo;
import fxms.nms.service.SyslogServiceImpl;

public class NokiaSyslogAdapter extends FxActorImpl implements SyslogAdapter {

	@SuppressWarnings("unused")
	public NokiaSyslogAdapter() throws Exception {
		
		String occurMsg = "Sep 18 04:23:30.0|13.1.247.31|13.1.247.31|NO72HIRA-SKB-DaeJeon-04:|warning| 991 Base SNMP-WARNING-linkDown-2005 [2/1/2]:  Interface te-2/1/2 is operational";
		String clearMsg = "Sep 18 04:23:30.0|13.1.247.31|13.1.247.31|NO72HIRA-SKB-DaeJeon-04:|warning| 991 Base SNMP-WARNING-linkUp-2005 [2/1/2]:  Interface te-2/1/2 is operational";
		
		CycleFxThread thread = new CycleFxThread("LAGOON-SERVER", "period 10") {

			boolean isB = true;
			@Override
			protected void doCycle(long arg0) {
				if (isB) {
					SyslogServiceImpl.syslogService.putSyslog(new SyslogVo("13.1.247.31", occurMsg));
				} else {
					SyslogServiceImpl.syslogService.putSyslog(new SyslogVo("13.1.247.31", clearMsg));
				}
				isB = !isB;
			}
		};

//		thread.start();
	}

	@Override
	public SyslogVo parse(SyslogNode node, SyslogVo vo) throws Exception {

		if ((node instanceof EquipDcn) == false) {
			return vo;
		}

		EquipDcn equip = (EquipDcn) node;

		SyslogParsingResultVo syslogParsingResultVo = SyslogApi.getApi().parse(node, vo);
		if (syslogParsingResultVo == null) {
			return vo;
		}
		
		DetectedAlarmVo alarm = new Nokia().parseAlarm(equip, syslogParsingResultVo, vo.getMsg());
		if (alarm != null) {
			alarm.setIgnoreOmn33812(true);
			GwApi.getApi().fireAlarm(alarm);
			return null;
		}

		return vo;
	}

}

package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapterCrs;

import fxms.bas.co.cron.Crontab;

public class CienaCrsCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1114113972395935640L;

	@Override
	public void cron() throws Exception {

		List<EquipDcn> dcnList = GwApi.getApi().getTdmList();
		List<OIV28160> oList;

		for (EquipDcn dcn : dcnList) {
			CienaConfAdapterCrs client = new CienaConfAdapterCrs(dcn, null);
			oList = client.getCrs();
			if (oList != null && oList.size() > 0) {
				GwApi.getApi().updateOiv28160(dcn.getEquipId(), oList);
			}
		}
		
		GwApi.getApi().findCrsAlarmAndFireEvent();
	}

	@Override
	public String getGroup() {
		return "CienaCron";
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

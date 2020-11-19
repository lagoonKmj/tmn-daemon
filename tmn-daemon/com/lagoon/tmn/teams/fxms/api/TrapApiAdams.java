package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaService;

import fxms.nms.api.TrapApi;
import fxms.nms.co.snmp.trap.TrapNode;
import fxms.nms.co.snmp.trap.vo.TrapEventLog;
import fxms.nms.co.snmp.trap.vo.TrapPattern;

public class TrapApiAdams extends TrapApi {

	@Override
	public void doDeleteLogExpired() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void doInsertLog(List<TrapEventLog> logList) {
		for (TrapEventLog e : logList) {
			System.out.println(e.getTrapOid());
		}
	}

	@Override
	public List<TrapNode> doSelectTrapNode() {

		List<TrapNode> nodeList = new ArrayList<TrapNode>();
		
		List<IDcn> list = GwApi.getApi().getDcnList(new String[] { CienaService.MODEL_CESD_5150 });
		for (IDcn dcn : list) {
			if (dcn instanceof TrapNode) {
				nodeList.add((TrapNode) dcn);
			}
		}
		
		return nodeList;
		
	}

	@Override
	public List<TrapPattern> doSelectTrapPattern() {
		return new ArrayList<TrapPattern>();
	}

}

package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.nokia.NokiaService;

import fxms.nms.api.SyslogApi;
import fxms.nms.co.syslog.mo.SyslogNode;
import fxms.nms.co.syslog.vo.SyslogEventLog;
import fxms.nms.co.syslog.vo.SyslogPattern;

public class SyslogApiAdams extends SyslogApi {

	public SyslogApiAdams() {
		GwApi.setEquipMfactCd(NokiaService.VENDOR_NOKIA);
		GwApi.setEquipMdlCd(new String[] { NokiaService.MODEL_7210SAS_T });
	}

	@Override
	public void doDeleteLogExpired() throws Exception {
	}

	@Override
	public void doInsertLog(List<SyslogEventLog> arg0) throws Exception {
	}

	@Override
	public List<SyslogNode> doSelectSyslogNode() throws Exception {

		// EquipDcn 목록 조회
		List<SyslogNode> nodeList = new ArrayList<SyslogNode>();

		List<IDcn> list = GwApi.getApi().getDcnList(
				new String[] { NokiaService.MODEL_7210SAS_T  });
		for (IDcn dcn : list) {
			if (dcn instanceof SyslogNode) {
				nodeList.add((SyslogNode) dcn);
			}
		}

		return nodeList;
	}

	@Override
	public List<SyslogPattern> doSelectSyslogPattern() throws Exception {
		return GwApi.getApi().getSyslogPattern();
	}

}

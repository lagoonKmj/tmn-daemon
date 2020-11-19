package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TL1AlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaTL1PduMaker;

import fxms.nms.co.tl1_2.NetPduMakerTL1;

public class CienaAlarmAdapterQopas extends TL1AlarmAdapter<EquipDcn> {

	public CienaAlarmAdapterQopas(EquipDcn dcn, GwDcnMngThread gwThread,
			VendorBaseParser parser) {
		
		super(dcn, gwThread, parser);
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new CienaTL1PduMaker();
	}

	@Override
	protected List<DetectedAlarmVo> parseAlarm(List<String> dataList, String tid)
			throws Exception {

		List<DetectedAlarmVo> alarmList = parser
				.parseAlarms(dcn, dataList, tid);
		return alarmList;
	}

}

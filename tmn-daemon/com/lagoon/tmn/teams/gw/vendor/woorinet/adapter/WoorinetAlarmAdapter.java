package com.lagoon.tmn.teams.gw.vendor.woorinet.adapter;

import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TL1AlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.WoorinetTL1PduMaker;

import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.vo.AM;

public class WoorinetAlarmAdapter extends TL1AlarmAdapter<EmsDcn> {

	public WoorinetAlarmAdapter(EmsDcn dcn, GwDcnMngThread gwThread,
			VendorBaseParser parser) {
		super(dcn, gwThread, parser);
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new WoorinetTL1PduMaker();
	}

	@Override
	protected List<DetectedAlarmVo> parseAlarm(List<String> dataList, String tid)
			throws Exception {

		List<DetectedAlarmVo> alarmList = parser
				.parseAlarms(dcn, dataList, tid);
		return alarmList;
	}

	@Override
	protected void setDataAlarmListAndGwThreadPut(AM am,
			List<DetectedAlarmVo> alarmList) {

		String headerHstime = am.getHeader().getHstime() + "";
		for (DetectedAlarmVo alarmVo : alarmList) {
			if (alarmVo.getDablGrCd().equals(DABL_GR_CD.Clear.getCode())) { // 릴리즈
				alarmVo.setClearHstime(headerHstime);
			} else { // 발생
				alarmVo.setOccurHstime(headerHstime);
			}
			gwThread.put(alarmVo);
		}
	}

}

package com.lagoon.tmn.teams.gw.vendor.telefield.adapter;

import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.daemon.GwDcnMngThread;
import com.lagoon.tmn.teams.gw.daemon.TL1AlarmAdapter;
import com.lagoon.tmn.teams.gw.vendor.telefield.TelefieldTL1PduMaker;

import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.vo.AM;

public class TelefieldAlarmAdapter extends TL1AlarmAdapter<EmsDcn> {

	public TelefieldAlarmAdapter(EmsDcn dcn, GwDcnMngThread gwThread, VendorBaseParser parser) {
		super(dcn, gwThread, parser);
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new TelefieldTL1PduMaker();
	}

	@Override
	protected List<DetectedAlarmVo> parseAlarm(List<String> dataList, String tid)
			throws Exception {

		List<DetectedAlarmVo> alarmList = parser.parseAlarms(dcn, dataList, tid);
		return alarmList;
	}

	@Override
	protected void setDataAlarmListAndGwThreadPut(AM am, List<DetectedAlarmVo> alarmList) {
		String headerHstime = am.getHeader().getHstime() + "";

		String almCode = am.getAi().getAlmcode();
		for (DetectedAlarmVo alarmVo : alarmList) {
			
			if (almCode.equals(DABL_GR_CD.Clear.getCode())) {			// 릴리즈
				alarmVo.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else {																		// 발생		
				if (!alarmVo.getDablGrCd().equals(DABL_GR_CD.Info.getCode())) {
					alarmVo.setDablGrCd(almCode);
				}
			}
			alarmVo.setOccurHstime(headerHstime);
			gwThread.put(alarmVo);
		}
	}

}

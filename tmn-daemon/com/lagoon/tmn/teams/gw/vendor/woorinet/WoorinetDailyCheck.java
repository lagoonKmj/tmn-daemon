package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.TL1ConfAdapter;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Crontab;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.vo.ORMF;

/**
 * 일일점검
 * 
 * @author subkjh(김종훈)
 *
 */
@SuppressWarnings("serial")
public class WoorinetDailyCheck extends Crontab {

	@Override
	public void cron() throws Exception {
		List<IDcn> list = GwApi.getApi().getDcnList(false);

		StringBuffer sb = new StringBuffer();
		sb.append(Logger.makeString("Collect Woorinet", (list == null ? 0 : list.size())));
		Logger.logger.info(sb.toString());

		if (list == null) {
			return;
		}

//		List<DetectedNeVo> neList;
		for (IDcn dcn : list) {
			if (dcn instanceof EmsDcn) {
				EmsDcn ems = (EmsDcn)dcn;
				TL1ConfAdapter<EmsDcn> adapter = new TL1ConfAdapter<EmsDcn>(ems) {

					@Override
					protected NetPduMakerTL1 getPduMaker() {
						return new WoorinetTL1PduMaker();
					}

					@Override
					protected void logout() throws Exception {
						tl1Client.send("CANC-USER", null, null, new String[] { dcn.getLoginId() });

						// CANC-USER:::100:<loginId>;
					}

					@Override
					protected int sendLogin() throws Exception {
						return tl1Client.send("ACT-USER", null, null, new String[] { dcn.getLoginId() + "," + dcn.getLoginPwd() });
					}
					
					@Override
					protected String getCharset() {
						return "utf-8";
					}
				};
				
				rtrv(ems, adapter);
				
			} else {
				sb = new StringBuffer();
				sb.append(Logger.makeString("Collect " + dcn.getDcnKey(), "TODO"));
				Logger.logger.fail(sb.toString());
			}
		}

	}

	@Override
	public String getGroup() {
		return "WoorinetCron";
	}

	@Override
	public String getLog() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getOpcode() {
		// TODO Auto-generated method stub
		return 0;
	}

	private void rtrv(EmsDcn dcn, TL1ConfAdapter<EmsDcn> adapter) {
		try {

			adapter.open(dcn.getEmsIpAddr(), dcn.getConsPortNum());

			rtrvProgramInfo(adapter);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			adapter.close();
		}
	}

	@SuppressWarnings("unused")
	private void rtrvProgramInfo(TL1ConfAdapter<EmsDcn> adapter) throws Exception {
		ORMF m = adapter.rtrv("RTRV-PROGRAM-INFO", null, null, null);

	}
	
	@SuppressWarnings("unused")
	private void rtrvProgramInfoall(TL1ConfAdapter<EmsDcn> adapter) throws Exception {
		ORMF m = adapter.rtrv("RTRV-PROGRAM-INFOALL", null, null, null);
	}
	
	@SuppressWarnings("unused")
	private void rtrvClk(TL1ConfAdapter<EmsDcn> adapter) throws Exception {
		ORMF m = adapter.rtrv("RTRV-CLK", null, null, null);
	}
	
	@SuppressWarnings("unused")
	private void rtrvOpticModule(TL1ConfAdapter<EmsDcn> adapter) throws Exception {
		ORMF m = adapter.rtrv("RTRV-OPTIC-MODULE", null, null, null);
	}
	
	@SuppressWarnings("unused")
	private void rtrvUnitTemperaturepm(TL1ConfAdapter<EmsDcn> adapter) throws Exception {
		ORMF m = adapter.rtrv("RTRV-UNIT-TEMPERATUREPM", null, null, null);
	}


}

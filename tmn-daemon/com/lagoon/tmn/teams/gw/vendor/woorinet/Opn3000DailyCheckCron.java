package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.woorinet.adapter.WoorinetConfAdapterOpn3000;
import com.lagoon.tmn.teams.gw.vendor.woorinet.vo.Opn3000Vo;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Crontab;

/**
 * 우리넷 OPN-3000 장비에 대한 일일 점검 데이터를 수집하는 크론
 * 
 * @author subkjh(김종훈)
 *
 */
public class Opn3000DailyCheckCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1603696155698223587L;

	public Opn3000DailyCheckCron() {
	}

	@Override
	public void cron() throws Exception {
		List<IDcn> dcnList = GwApi.getApi().getDcnList(new String[] { WoorinetService.MODEL_OPN_3000 });

		Logger.logger.info(Logger.makeString("OPN-3000 EQUIP COUNT", dcnList.size()));

		for (IDcn dcn : dcnList) {
			if (dcn instanceof EquipDcn) {
				try {
					Opn3000Vo vo = new WoorinetConfAdapterOpn3000((EquipDcn) dcn, null).rtrvDaily();
					GwApi.getApi().addOpn3000DailyReport(vo);
					Logger.logger.info(Logger.makeString("OPN-3000 " + ((EquipDcn) dcn).getEquipIpAddr() + " Daily", "OK"));
				} catch (Exception e) {
					Logger.logger.error(e);
					Logger.logger.fail(Logger.makeString("OPN-3000 " + ((EquipDcn) dcn).getEquipIpAddr() + " Daily", "fail"));

				}
			}
		}

	}

	@Override
	public String getGroup() {
		return "WoorinetCron";
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

package com.lagoon.tmn.teams.gw.collect.conf;

import java.net.ConnectException;
import java.util.List;

import com.lagoon.tmn.teams.co.exp.LoginDenyException;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.GwApi;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Crontab;
import fxms.bas.fxo.FxCfg;

public abstract class PsCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5552079084990121979L;

	@Override
	public void cron() throws Exception {

		List<IDcn> list = getDcnList();

		Logger.logger.info(Logger.makeString("Collect PS", (list == null ? 0 : list.size())));

		if (list == null) {
			return;
		}

		List<PsVo> psList;

		for (IDcn dcn : list) {
			ConfAdapter adapter = makeAdapter(dcn);
			if (adapter != null) {
				try {

					psList = adapter.collectPs();

					if (psList != null && psList.size() > 0) {
						GwApi.getApi().insertPs(psList);
						Logger.logger.info(Logger.makeString(dcn.getDcnKey(), "ps:" + psList.size()));
					}
					if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
						GwApi.getApi().updateDcnStatus(dcn, true);
					}

				} catch (LoginDenyException e) {
					if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
						GwApi.getApi().updateDcnStatus(dcn, false);
					}
					Logger.logger.error(e);
				} catch (ConnectException e) {
					if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
						GwApi.getApi().updateDcnStatus(dcn, false);
					}
					Logger.logger.error(e);
				} catch (Exception e) {
					if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
						GwApi.getApi().updateDcnStatus(dcn, false);
					}
					Logger.logger.error(e);
				}
			} else {
				Logger.logger.fail(Logger.makeString("Collect " + dcn.getDcnKey(), "TODO"));
			}
		}

	}

	@Override
	public String getGroup() {
		return "DcnCron";
	}

	@Override
	public String getLog() {
		return null;
	}

	@Override
	public int getOpcode() {
		return 0;
	}

	/**
	 * 
	 * @return
	 */
	protected abstract List<IDcn> getDcnList();

	/**
	 * 
	 * @param dcn
	 * @return
	 */
	protected abstract ConfAdapter makeAdapter(IDcn dcn);
}
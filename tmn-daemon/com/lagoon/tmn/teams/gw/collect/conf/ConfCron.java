package com.lagoon.tmn.teams.gw.collect.conf;

import java.net.ConnectException;
import java.util.List;

import com.lagoon.tmn.teams.co.exp.LoginDenyException;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Crontab;

public abstract class ConfCron extends Crontab {

	/**
	 * 
	 */
	private static final long serialVersionUID = 667436235590196641L;

	@Override
	public void cron() throws Exception {

		List<IDcn> list = getDcnList();

		Logger.logger.info(Logger.makeString("Collect Config", (list == null ? 0 : list.size())));

		if (list == null) {
			return;
		}

		List<DetectedNeVo> neList;
		for (IDcn dcn : list) {
			ConfAdapter adapter = makeAdapter(dcn);
			if (adapter != null) {
				try {

					neList = adapter.collectConf();

					for (DetectedNeVo ne : neList) {
						GwApi.getApi().updateDetectedNe(ne);
					}

					GwApi.getApi().updateDcnStatus(dcn, true);

				} catch (LoginDenyException e) {
					GwApi.getApi().updateDcnStatus(dcn, false);
					Logger.logger.error(e);
				} catch (ConnectException e) {
					GwApi.getApi().updateDcnStatus(dcn, false);
					Logger.logger.error(e);
				} catch (Exception e) {
					GwApi.getApi().updateDcnStatus(dcn, false);
					Logger.logger.error(e);
				}
			} else {
				Logger.logger.fail(Logger.makeString("DCN:" + dcn.toInfo(), "Adapter is null"));
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

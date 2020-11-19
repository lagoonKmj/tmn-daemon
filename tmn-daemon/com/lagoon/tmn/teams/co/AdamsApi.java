package com.lagoon.tmn.teams.co;

import java.util.List;

import com.lagoon.tmn.teams.co.dao.AdamsDao;
import com.lagoon.tmn.teams.co.vo.DataStateVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.mo.DcnMo;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.bas.po.PsVo;

public abstract class AdamsApi extends FxApi {

	private static AdamsApi api = null;
	private static Object lockObj = new Object();

	public static AdamsApi getApi() {

		if (api == null) {
			synchronized (lockObj) {
				if (api == null) {
					api = (AdamsApi) makeApi(AdamsApi.class);
					try {
						api.reload();
					} catch (Exception e) {
						Logger.logger.error(e);
					}
				}
			}
		}

		return api;
	}

	/**
	 * 데이터가 변경되었는지 확인한다.
	 * 
	 * @param lastMstime
	 * @return
	 * @throws Exception
	 */
	public abstract DataStateVo getChangedState(long lastMstime) throws Exception;

	/**
	 * COT의 모델 기준으로 DCN을 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 */
	public List<DcnMo> getDcnCotList(String equipMdlCd[]) {
		try {
			return new AdamsDao().selectDcnCotListByModel(equipMdlCd);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	public EquipMo getNe(TrmsNetEventVo data) {
		return null;
	}

	public List<EquipMo> getNeListWithCot(String equipMdlCd[]) {
		try {
			return new AdamsDao().selectNeListWithCot(equipMdlCd);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@Override
	public String getState(LOG_LEVEL level) {
		return "";
	}

	/**
	 * 수집된 광레벨을 기록한다.
	 * 
	 * @param list
	 */
	public void insertOptLevel(List<PsVo> list) {

	}

	@Override
	protected void initApi() throws Exception {

	}

	@Override
	protected void reload() throws Exception {

	}
}
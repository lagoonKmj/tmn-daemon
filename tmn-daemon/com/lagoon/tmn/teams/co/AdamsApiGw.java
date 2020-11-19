package com.lagoon.tmn.teams.co;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.vo.DataStateVo;
import com.lagoon.tmn.teams.fxms.mo.DcnMo;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.co.utils.ObjectUtil;
import fxms.bas.co.utils.FxmsClient;
import fxms.bas.fxo.FxCfg;

public class AdamsApiGw extends AdamsApi {

	public static void main(String[] args) {
		AdamsApi.getApi().getNeListWithCot(new String[] { "0000000348", "0000000347" });
	}

	private FxmsClient client;

	// @Override
	// public List<EquipMo> getNeList(String dcnEmsTyp, String equipMdlCd[], int
	// dcnNum) {
	//
	// try {
	// List<EquipMo> ret = new ArrayList<EquipMo>();
	//
	// Map<String, Object> para = new HashMap<String, Object>();
	// para.put("dcnEmsTyp", dcnEmsTyp);
	// para.put("equipMdlCd", equipMdlCd);
	// List<Map<String, Object>> list = client.testRetList("teams",
	// "get-ne-list", para);
	// for (Map<String, Object> map : list) {
	// EquipMo item = new EquipMo();
	// ObjectUtil.toObject(map, item);
	// ret.add(item);
	// }
	// return ret;
	// } catch (Exception e) {
	// Logger.logger.error(e);
	// return null;
	// }
	//
	// }

	
	@Override
	public DataStateVo getChangedState(long lastMstime) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("last-mstime", lastMstime);
			Map<String, Object> map = client.testRetObj("teams", "check-state", para);

			DataStateVo vo = new DataStateVo();
			ObjectUtil.toObject(map, vo);
			return vo;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	/**
	 * COT의 모델 기준으로 DCN을 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 */
	@Override
	public List<DcnMo> getDcnCotList(String equipMdlCd[]) {
		try {
			List<DcnMo> ret = new ArrayList<DcnMo>();

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipMdlCd", equipMdlCd);
			List<Map<String, Object>> list = client.testRetList("teams", "get-dcn-cot-list", para);
			for (Map<String, Object> map : list) {
				DcnMo item = new DcnMo();
				ObjectUtil.toObject(map, item);
				ret.add(item);
			}
			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@Override
	public List<EquipMo> getNeListWithCot(String equipMdlCd[]) {
		try {

			List<EquipMo> ret = new ArrayList<EquipMo>();

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipMdlCd", equipMdlCd);
			List<Map<String, Object>> list = client.testRetList("teams", "get-ne-list-with-cot", para);
			for (Map<String, Object> map : list) {
				map.remove("status");
				EquipMo item = new EquipMo();
				ObjectUtil.toObject(map, item);
				ret.add(item);
			}

			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@Override
	protected void initApi() throws Exception {
		client = new FxmsClient(FxCfg.getCfg().getString("APP_SERVER", "localhost"), 10005);
		client.login("AdamsService", "AdamsService");
	}


}

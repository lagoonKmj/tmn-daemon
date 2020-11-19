package com.lagoon.tmn.teams.co.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN25101;
import com.lagoon.tmn.teams.fxms.dbo.OMN25102;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.api.FxApi;

public class AdamsOMN25101Dao {

	private AdamsOMN25101Qid QID = new AdamsOMN25101Qid();

	public static void main(String[] args) {
		List<PsVo> list = new ArrayList<PsVo>();

		AdamsOMN25101Dao dao = new AdamsOMN25101Dao();

		PsVo vo = new PsVo();
		vo.setEquipId("TEST");
		vo.setInstance("RX");
		vo.setPortDesc("TEST-PORT");
		vo.setPsCode(PsVo.PSCODE_OPT_LEVEL_CURR);
		vo.setPsValue(-23);
		list.add(vo);

		try {
			dao.insertOMN25101(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsOMN25101Qid.QUERY_XML_FILE));
	}

	/**
	 * 수집한 온도값을 기록한다.
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertOMN25101(List<PsVo> list) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();

			Map<String, OMN25101> map = new HashMap<String, OMN25101>();
			OMN25101 vo;
			String key;
			String clctDtm = String.valueOf(FxApi.getDate());

			for (PsVo e : list) {

				key = e.getEquipId() + "-" + e.getPortDesc() + "-" + e.getInstance();

				vo = map.get(key);

				if (vo == null) {
					vo = new OMN25101();
					vo.setClctDtm(clctDtm);
					vo.setEquipId(e.getEquipId());
					vo.setMntrCyclCd("10");
					vo.setMntrYn("Y");
					vo.setMsrtPortInfo(e.getPortDesc() + "-" + e.getInstance());
					vo.setPortAlsNm(e.getPortDesc());
					vo.setSendRcvClCd(e.getInstance());
					//Logger.logger.debug("equipId[{}]:msrtPortInfo[{}]:", vo.getEquipId(),vo.getMsrtPortInfo());

					map.put(key, vo);
				}

				if (PsVo.PSCODE_OPT_LEVEL_AVG.equals(e.getPsCode())) {
					vo.setOplvlAvgVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_MIN.equals(e.getPsCode())) {
					vo.setOplvlMinVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_MAX.equals(e.getPsCode())) {
					vo.setOplvlMaxVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_CURR.equals(e.getPsCode())) {
					vo.setOplvlCurrVal(e.getPsValue());
				}

			}

			int cnt = tran.execute(QID.INSERT_OMN25101, map.values());
			tran.commit();

			return cnt;
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	public int insertOMN25102(List<PsVo> list) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();

			Map<String, OMN25102> map = new HashMap<String, OMN25102>();
			OMN25102 vo;
			String key;
			String clctDtm = String.valueOf(FxApi.getDate());

			for (PsVo e : list) {

				key = e.getEquipId() + "-" + e.getPortDesc() + "-" + e.getInstance();

				vo = map.get(key);

				if (vo == null) {
					vo = new OMN25102();
					vo.setClctDtm(clctDtm);
					vo.setEquipId(e.getEquipId());
					vo.setMntrCyclCd("10");
					vo.setMntrYn("Y");
					vo.setMsrtPortInfo(e.getPortDesc() + "-" + e.getInstance());
					vo.setPortAlsNm(e.getPortDesc());
					vo.setSendRcvClCd(e.getInstance());

					map.put(key, vo);
				}

				if (PsVo.PSCODE_OPT_LEVEL_AVG.equals(e.getPsCode())) {
					vo.setOplvlAvgVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_MIN.equals(e.getPsCode())) {
					vo.setOplvlMinVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_MAX.equals(e.getPsCode())) {
					vo.setOplvlMaxVal(e.getPsValue());
				} else if (PsVo.PSCODE_OPT_LEVEL_CURR.equals(e.getPsCode())) {
					vo.setOplvlCurrVal(e.getPsValue());
				}

			}

			int cnt = tran.execute(QID.INSERT_OMN25102, map.values());
			tran.commit();

			return cnt;
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}
}

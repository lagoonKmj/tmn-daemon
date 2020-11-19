package com.lagoon.tmn.teams.co.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN25112;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.api.FxApi;

public class AdamsOMN25112Dao {

	private AdamsOMN25112Qid QID = new AdamsOMN25112Qid();

	// public static void main(String[] args) {
	// AdamsOMN25112Dao dao = new AdamsOMN25112Dao();
	// List<PsVo> list = new ArrayList<PsVo>();
	// PsVo vo = new PsVo();
	//
	// vo.setEquipId("S00075460442");
	// vo.setPsCode(PsVo.PSCODE_TEMPERRATURE);
	// vo.setPsValue(Math.random() * 100);
	// vo.setCardNm("SHELF-1");
	// list.add(vo);
	//
	// vo = new PsVo();
	//
	// vo.setEquipId("S00075460442");
	// vo.setPsCode(PsVo.PSCODE_TEMPERRATURE);
	// vo.setPsValue(Math.random() * 100);
	// vo.setCardNm("1-GR1A");
	// list.add(vo);
	//
	// // 없는 슬롯
	// vo = new PsVo();
	//
	// vo.setEquipId("S00075460442");
	// vo.setPsCode(PsVo.PSCODE_TEMPERRATURE);
	// vo.setPsValue(Math.random() * 100);
	// vo.setCardNm("TEST");
	// list.add(vo);
	//
	// try {
	// System.out.println(dao.insertOMN25112(list));
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsOMN25112Qid.QUERY_XML_FILE));
	}

	/**
	 * 수집한 온도값을 기록한다.
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertOMN25112(List<PsVo> list) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();

			List<OMN25112> newList = new ArrayList<OMN25112>();
			OMN25112 e;
			String clctDtm = String.valueOf(FxApi.getDate());

			for (PsVo vo : list) {

				e = new OMN25112();
				e.setClctDtm(clctDtm);
				e.setEquipId(vo.getEquipId());
				e.setPortNm(vo.getPortDesc() == null ? "-" : vo.getPortDesc());
				e.setScardNm(vo.getCardNm() == null ? "-" : vo.getCardNm());
				e.setTmprVal(vo.getPsValue());

				newList.add(e);

			}

			if (newList.size() > 0) {
				int cnt = tran.execute(QID.INSERT_OMN25112, newList);
				tran.commit();
				return cnt;
			}

			return 0;
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	/**
	 * 수집한 온도에 대한 통계를 생성한다.
	 * 
	 * @throws Exception
	 */
	public void makeTemp() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			Map<String, Object> para = new HashMap<String, Object>();

			// 어제
			long clctDtm = FxApi.getYmd(System.currentTimeMillis() - 86400000L);

			para.put("clctDtm", clctDtm);
			para.put("clctDtmStart", clctDtm + "000000");
			para.put("clctDtmEnd", clctDtm + "235959");

			tran.execute(QID.DELETE_OMN25113__By_day, para);
			tran.execute(QID.INSERT_OMN25113, para);
			tran.commit();

			tran.execute(QID.DELETE_OMN25112__By_term, null);
			tran.commit();

			tran.execute(QID.DELETE_OMN25113__By_term, null);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {

			tran.stop();
		}
	}
}

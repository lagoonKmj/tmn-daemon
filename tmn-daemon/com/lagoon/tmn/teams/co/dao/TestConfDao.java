package com.lagoon.tmn.teams.co.dao;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class TestConfDao {

	private TestConfQid QID = new TestConfQid();

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(TestConfQid.QUERY_XML_FILE));
	}

	@SuppressWarnings("unchecked")
	public DetectedNeVo selectNe() throws Exception {

		DbTrans tran = getDbTrans();
		DetectedNeVo vo = new DetectedNeVo();
		try {
			tran.start();

			vo = (DetectedNeVo) (tran.selectQid2Res(QID.SELECT_DETECTED_NE_VO, null)).get(0);
			vo.setCardList(tran.selectQid2Res(QID.SELECT_DETECTED_SLOT_VO, null));
			vo.setPortList(tran.selectQid2Res(QID.SELECT_DETECTED_PORT_VO, null));
			return vo;
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}
}

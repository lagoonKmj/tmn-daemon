package com.lagoon.tmn.teams.co.dao;

import com.lagoon.tmn.teams.co.AdamsCfg;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class AdamsOIV28101Dao {

	private AdamsOIV28101Qid QID = new AdamsOIV28101Qid();

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsOIV28101Qid.QUERY_XML_FILE));
	}

	public static void main(String[] args) {
		AdamsOIV28101Dao dao = new AdamsOIV28101Dao();
		try {
			dao.makeCotRtRelation();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 링 선번을 이용하여 COT RT 정보를 생성한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public int makeCotRtRelation() throws Exception {

		DbTrans tran = getDbTrans();
		int cnt = 0;
		try {
			tran.start();

			tran.execute(QID.DELETE_OIV28101__all, null);
			cnt += tran.execute(QID.INSERT_OIV28101__1, null);
			cnt += tran.execute(QID.INSERT_OIV28101__2, null);
			cnt += tran.execute(QID.INSERT_OIV28101__3, null);
			cnt += tran.execute(QID.INSERT_OIV28101__4, null);

			tran.commit();

			Logger.logger.info(Logger.makeString("OIV28101(COT-RT)", cnt));

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

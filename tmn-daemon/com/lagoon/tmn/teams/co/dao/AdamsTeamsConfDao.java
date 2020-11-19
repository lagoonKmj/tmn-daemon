package com.lagoon.tmn.teams.co.dao;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.OIV10104;
import com.lagoon.tmn.teams.fxms.dbo.OIV10105;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 *
 * @author subkjh(김종훈)
 *
 */
public class AdamsTeamsConfDao {

	private AdamsTeamsConfQid QID;

	public AdamsTeamsConfDao() throws Exception {
		QID = new AdamsTeamsConfQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager
				.getMgr()
				.getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(
						AdamsCfg.getHomeDeployConfSql(AdamsTeamsConfQid.QUERY_XML_FILE));
	}

	public void updateEmsStatus(String emsId, String connStVal)
			throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			OIV10105 o = new OIV10105();
			o.setEmsId(emsId);
			o.setEmsStVal(connStVal);

			tran.execute(QID.UPDATE_OIV10105__BY_emsId, o);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {

			tran.stop();
		}
	}

	/**
	 *
	 * @param equipId
	 * @param connStVal
	 * @throws Exception
	 */
	public void updateNeStatus(String equipId, String connStVal)
			throws Exception {

		DbTrans tran = getDbTrans();
		try {

			OIV10104 o = new OIV10104();
			o.setEquipId(equipId);
			o.setConnStVal(connStVal);

			tran.start();

			tran.execute(QID.UPDATE_OIV10104__BY_equipId, o);

			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	public void mergeOIV10011() throws Exception {
		DbTrans tran = getDbTrans();
		try {

			tran.start();
			tran.execute(QID.MERGE_OIV10011, null);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}

	}

	public void mergeOIV10115() throws Exception {
		DbTrans tran = getDbTrans();
		try {

			tran.start();
			tran.execute(QID.MERGE_OIV10115, null);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}

	}

}

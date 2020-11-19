package com.lagoon.tmn.mig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.OIV10105;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;

public class TeamsMigDao extends MigDao {

	private MigTeamsQid QID = new MigTeamsQid();

	@Override
	protected DbTrans getDbTrans(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createDbTrans(AdamsCfg.getHomeDeployConfSql(MigTeamsQid.QUERY_XML_FILE));
	}

	@Override
	protected FxDaoExecutor getFxDaoExecutor(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createFxDao();
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, MigModelVo> selectModelInfo() throws Exception {
		DbTrans tran = getDbTrans(MIDBDEV);

		try {
			tran.start();

			List<MigModelVo> list = tran.selectQid2Res(QID.SELECT_MODEL_INFO, null);
			Map<Integer, MigModelVo> ret = new HashMap<Integer, MigModelVo>();

			for (MigModelVo e : list) {
				ret.put(e.getModelId(), e);
			}

			return ret;
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	public void mergeFromDcn() throws Exception {
		DbTrans tran = getDbTrans(MIDBDEV);

		try {
			tran.start();

			tran.execute(QID.MERGE_OIV10104__From_DCN, null);
			tran.execute(QID.MERGE_OIV10105__From_DCN, null);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	public void insertOIV10105(List<OIV10105> list) throws Exception {
		FxDaoExecutor tran = getFxDaoExecutor(MIDBDEV);

		try {
			tran.start();
			tran.insertOfClass(OIV10105.class, list);
			tran.commit();
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
		} finally {
			tran.stop();
		}
	}

	public void updateModelClctMthdClCd(List<MigModelVo> list) throws Exception {
		DbTrans tran = getDbTrans(MIDBDEV);

		try {
			tran.start();

			tran.execute(QID.UPDATE_OIV10200_clctMthdClCd, list);
			tran.execute(QID.UPDATE_OIV10200_clctMthdClCd__EMS, null);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
		} finally {
			tran.stop();
		}
	}
}

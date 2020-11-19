package com.lagoon.tmn.mig;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.IAudit;

import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;

public class MigDao {

	protected final String ADAMSDEV = "ADAMSDEV";
	protected final String MIDBDEV = "MIDBDEV";

	protected DbTrans getDbTrans(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createDbTrans(AdamsCfg.getHomeDeployConfSql("adams_nws.xml"));
	}

	protected FxDaoExecutor getFxDaoExecutor(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createFxDao();
	}

	public void sync(String qid, Class<?> classOf, IChecker checker) throws Exception {
		DbTrans src = getDbTrans(ADAMSDEV);
		FxDaoExecutor dst = getFxDaoExecutor(MIDBDEV);

		try {
			dst.start();
			src.start();
			src.setDaoListener(new DaoListener() {

				private List<Object> list = new ArrayList<Object>();
				private Timestamp stamp = new Timestamp(System.currentTimeMillis());
				private Object prevObj = null;

				@Override
				public void onExecuted(Object arg0, Exception e) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
					if (list.size() > 0) {
						dst.insertOfClass(classOf, list);
						dst.commit();
					}
				}

				@Override
				public void onSelected(int arg0, Object o) throws Exception {

					if (checker != null && checker.isValid(prevObj, o) == false) {
						return;
					}

					if (o instanceof IAudit) {
						((IAudit) o).setAuditId("SKAdams_subkjh");
						((IAudit) o).setAuditDtm(stamp);
					}

					list.add(o);

					if (list.size() > 10000) {
						dst.insertOfClass(classOf, list);
						dst.commit();
						list.clear();
					}

					prevObj = o;
				}

				@Override
				public void onStart(String[] cols) throws Exception {
					System.out.println(Arrays.toString(cols));
				}

			});

			src.selectQid2Res(qid, null);

			dst.commit();

		} catch (Exception e) {
			e.printStackTrace();
			dst.rollback();
		} finally {
			dst.stop();
			src.stop();
		}
	}

}

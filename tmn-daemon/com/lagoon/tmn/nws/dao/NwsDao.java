package com.lagoon.tmn.nws.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lagoon.tmn.nws.dbo.OES30110;
import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.IAudit;

import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;

public class NwsDao {

	protected final String ADAMSDEV = "ADAMSDEV";
	protected final String MIDBDEV = "MIDBDEV";
	protected final String ADAMS = "ADAMS";

	public static void main(String[] args) {
		NwsDao dao = new NwsDao();
		try {
			dao.sync("SELECT_OES30110", OES30110.class);
//			dao.sync("SELECT_OES10302", OES10302.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected DbTrans getDbTrans(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createDbTrans(AdamsCfg.getHomeDeployConfSql("adams_nws.xml"));
	}

	protected FxDaoExecutor getFxDaoExecutor(String db) throws Exception {
		return DBManager.getMgr().getDataBase(db).createFxDao();
	}

	private void sync(String qid, Class<?> classOf) throws Exception {
		DbTrans src = getDbTrans(ADAMSDEV);
		FxDaoExecutor dst = getFxDaoExecutor(ADAMS);

		try {
			dst.start();
			src.start();
			src.setDaoListener(new DaoListener() {

				private List<Object> list = new ArrayList<Object>();
				private Timestamp stamp = new Timestamp(System.currentTimeMillis());

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

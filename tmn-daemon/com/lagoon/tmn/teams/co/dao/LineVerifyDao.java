package com.lagoon.tmn.teams.co.dao;

import java.util.HashMap;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.LineVerifyPathVo;
import com.lagoon.tmn.teams.co.vo.LineVerifyTpoVo;
import com.lagoon.tmn.teams.co.vo.LineVerifyVo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class LineVerifyDao {

	public static void main(String[] args) {
		LineVerifyDao dao = new LineVerifyDao();
		try {
			dao.checkLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private LineVerifyQid QID;

	public LineVerifyDao() {
		QID = new LineVerifyQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(LineVerifyQid.QUERY_XML_FILE));
	}

	public void checkLine() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, LineVerifyVo> lineMap = new HashMap<String, LineVerifyVo>();

		try {
			tran.start();
			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					LineVerifyVo vo = (LineVerifyVo) obj;
					lineMap.put(vo.getLineNum(), vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_INFO, null);

			System.out.println("LINE-COUNT = " + lineMap.size());

			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					LineVerifyTpoVo vo = (LineVerifyTpoVo) obj;
					LineVerifyVo line = lineMap.get(vo.getLineNum());
					if (line != null) {
						line.addTpo(vo);
					}
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_TPO, null);

			tran.setDaoListener(new DaoListener() {
				private LineVerifyPathVo prev;
				private LineVerifyVo line;
				int index = 0;

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
					if (line != null) {
						String ret = line.checkTpo();
						if ("OK".equals(ret) == false) {
							System.out.println(index + ". " + line.getLineNum() + "/" + line.getLineNm() + ":" + ret);
						}
					}
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					LineVerifyPathVo vo = (LineVerifyPathVo) obj;

					if (prev != null && vo.getLineNum().equals(prev.getLineNum()) == false) {
						if (line != null) {
							String ret = line.checkTpo();
							if ("OK".equals(ret) == false) {
								System.out.println(index + ". " + line.getLineNum() + "/" + line.getLineNm() + ":" + ret);
							}
							index++;
						}
						line = lineMap.get(vo.getLineNum());
					}

					if (line == null) {
						line = lineMap.get(vo.getLineNum());
					}

					if (line != null) {
						line.addPath(vo);
					}

					prev = vo;
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_PATH, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}
	}

}

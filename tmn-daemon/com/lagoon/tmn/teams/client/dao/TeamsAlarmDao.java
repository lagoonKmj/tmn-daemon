package com.lagoon.tmn.teams.client.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.client.vo.OIV10300;
import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.LineVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.fxms.dbo.OIV10200;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 데이터베이스 관련 클래스
 * 
 * @author lagoon (강명중)
 *
 */
public class TeamsAlarmDao {

	private TeamsAlarmClientQid QID;

	public TeamsAlarmDao() {
		QID = new TeamsAlarmClientQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager
				.getMgr()
				.getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(
						AdamsCfg.getHomeDeployConfSql(TeamsAlarmClientQid.QUERY_XML_FILE));
	}

	@SuppressWarnings("finally")
	public Map<String, OIV10300> selectTpo2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, OIV10300> map = new HashMap<String, OIV10300>();

		try {
			tran.start();
			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1)
						throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					OIV10300 oiv10300 = (OIV10300) obj;
					map.put(oiv10300.getTpoCd(), oiv10300);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_OIV10300, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
			return map;
		}
	}

	@SuppressWarnings("finally")
	public Map<String, String> selectEquipModel2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, String> map = new HashMap<String, String>();

		try {
			tran.start();
			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1)
						throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					OIV10200 oiv10200 = (OIV10200) obj;
					map.put(oiv10200.getEquipMdlCd(), oiv10200.getEquipMdlNm());
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_OIV10200, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
			return map;
		}
	}

	@SuppressWarnings("finally")
	public Map<String, List<LineVo>> selectLine2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, List<LineVo>> map = new HashMap<String, List<LineVo>>();

		try {
			tran.start();
			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1)
						throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					LineVo vo = (LineVo) obj;
					List<LineVo> entry = map.get(vo.getLineNum());
					if (entry == null) {
						entry = new ArrayList<LineVo>();
						map.put(vo.getLineNum(), entry);
					}
					entry.add(vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_LIST, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
			return map;
		}
	}

	/**
	 * 망 기본정보를 조회하여, Map 으로 가공 하여 반환 한다.
	 * 
	 * @return Map<String, LineInfoVo>
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public Map<String, NetworkInfoVo> selectNetworkInfo2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, NetworkInfoVo> map = new HashMap<String, NetworkInfoVo>();

		Map<String, Object> para = new HashMap<String, Object>();

		try {
			tran.start();
			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1)
						throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					NetworkInfoVo networkInfoVo = (NetworkInfoVo) obj;
					map.put(networkInfoVo.getNetNum(), networkInfoVo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_NETWORK_INFO_LIST, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
			return map;
		}
	}

}

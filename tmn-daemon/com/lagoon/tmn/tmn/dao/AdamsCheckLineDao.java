package com.lagoon.tmn.tmn.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.tmn.vo.OIV10611Ext;
import com.lagoon.tmn.tmn.vo.OIV10612Ext;
import com.lagoon.tmn.tmn.vo.OIV10614Ext;
import com.lagoon.tmn.tmn.vo.OIV10615Ext;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class AdamsCheckLineDao {

	public AdamsCheckLineDao() {
		// TODO Auto-generated constructor stub
	}

	private AdamsCheckLineQid QID = new AdamsCheckLineQid();

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsCheckLineQid.QUERY_XML_FILE));
	}

	/**
	 * NET 목록을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, OIV10614Ext> selectNet(String netTopoType) throws Exception {

		DbTrans tran = getDbTrans();

		final Map<String, OIV10614Ext> map = new HashMap<String, OIV10614Ext>();

		try {
			tran.start();
			List<OIV10614Ext> list = tran.selectQid2Res(QID.SELECT_NETWORK_BASE__By_netTopoType, netTopoType);
			for (OIV10614Ext vo : list) {
				map.put(vo.getNetNum(), vo);
			}

			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					OIV10615Ext vo = (OIV10615Ext) obj;
					OIV10614Ext net = map.get(vo.getNetNum());
					if (net != null) {
						net.getPathList().add(vo);
					}
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_NETWORK_PATH__By_netTopoType, netTopoType);

			return map;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

	}

	/**
	 * 입력된 토폴로지 종류를 사용하는 네트워크을 조회한다.
	 * 
	 * @param netTopoType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OIV10614Ext> selectNetUse(String netTopoType) throws Exception {

		DbTrans tran = getDbTrans();

		final Map<String, OIV10614Ext> map = new HashMap<String, OIV10614Ext>();

		try {
			tran.start();
			List<OIV10614Ext> list = tran.selectQid2Res(QID.SELECT_NETWORK_BASE__By_WdmTrunk, null);
			for (OIV10614Ext vo : list) {
				map.put(vo.getNetNum(), vo);
			}

			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					OIV10615Ext vo = (OIV10615Ext) obj;
					OIV10614Ext net = map.get(vo.getNetNum());
					if (net != null) {
						net.getPathList().add(vo);
					}
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_NETWORK_PATH__By_WdmTrunk, null);

			return list;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

	}

	/**
	 * 입력된 토폴로지 종류를 사용하는 회선을 조회한다.
	 * 
	 * @param netTopoType
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<OIV10611Ext> selectLineUse(String netTopoType) throws Exception {

		DbTrans tran = getDbTrans();

		final Map<String, OIV10611Ext> map = new HashMap<String, OIV10611Ext>();

		try {
			tran.start();

			List<OIV10611Ext> list = tran.selectQid2Res(QID.SELECT_LINE_BASE__By_WdmTrunk, null);
			for (OIV10611Ext vo : list) {
				map.put(vo.getLineNum(), vo);
			}

			tran.setDaoListener(new DaoListener() {

				@Override
				public void onExecuted(Object arg0, Exception arg1) throws Exception {
				}

				@Override
				public void onFinish(Exception arg0) throws Exception {
				}

				@Override
				public void onSelected(int arg0, Object obj) throws Exception {
					OIV10612Ext vo = (OIV10612Ext) obj;
					OIV10611Ext net = map.get(vo.getLineNum());
					if (net != null) {
						net.getPathList().add(vo);
					}
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
					System.out.println(Arrays.toString(arg0));
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_PATH__By_WdmTrunk, null);

			return list;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

	}
}

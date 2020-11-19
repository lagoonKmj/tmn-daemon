package com.lagoon.tmn.test;

import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 데이터베이스 관련 클래스
 * 
 * @author subkjh(김종훈)
 *
 */
public class TestTmnDao {

	private TestTmnQid QID;

	public TestTmnDao() {
		QID = new TestTmnQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDEV)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(TestTmnQid.QUERY_XML_FILE));
	}

	@SuppressWarnings("unchecked")
	public List<TrmsNetEventVo> selectAlarm() throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();
			return (List<TrmsNetEventVo>) tran.selectQid2Res(QID.SELECT_ALARM_1Min, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TrmsNetEventVo> selectAlarmClear() throws Exception {
		DbTrans tran = getDbTrans();
		
		try {
			tran.start();
			return (List<TrmsNetEventVo>) tran.selectQid2Res(QID.SELECT_ALARM_CLEAR_1Min, null);
			
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}
}

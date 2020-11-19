package com.lagoon.tmn.teams.co.dao;

import com.lagoon.tmn.teams.co.AdamsCfg;

import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsStatisticDao {

	@SuppressWarnings("unused")
	private AdamsStatisticQid QID;

	public AdamsStatisticDao() {
		QID = new AdamsStatisticQid();
	}

	@SuppressWarnings("unused")
	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsStatisticQid.QUERY_XML_FILE));
	}

}

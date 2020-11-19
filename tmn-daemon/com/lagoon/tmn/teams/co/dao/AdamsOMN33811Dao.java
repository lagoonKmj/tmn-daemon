package com.lagoon.tmn.teams.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 * 
 *
 */
public class AdamsOMN33811Dao {

	private AdamsOMN33811Qid QID = new AdamsOMN33811Qid();

	@SuppressWarnings("unchecked")
	public List<OMN33811Ext> selectOMN33811Ext(Map<String, Object> parameters) throws Exception {
		DbTrans tran = getDbTrans();
		try {
			String equipMdlCd =  (String) parameters.get("equipMdlCd");
			
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipMdlCd", equipMdlCd);
			
			return (List<OMN33811Ext>) tran.selectQid2Res(QID.SELECT_OMN33811, para);

		} catch (Exception e) {
			e.printStackTrace();
			Logger.logger.error(e);
			throw e;
		} finally {

			tran.stop();
		}
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsOMN33811Qid.QUERY_XML_FILE));
	}
}

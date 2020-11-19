package com.lagoon.tmn.teams.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;

import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

public class _AdamsDao {

	protected DbTrans getDbTrans(String db, String... qidFiles) throws Exception {
		return DBManager.getMgr().getDataBase(db).createDbTrans(AdamsCfg.getHomeDeployConfSql(qidFiles));
	}

	protected Map<String, Object> getPara(Map<String, Object> orgPara, String... cols) {
		Map<String, Object> para = new HashMap<String, Object>();
		Object val;

		for (String s : cols) {
			val = orgPara.get(s);
			if (val != null) {
				if (val instanceof List) {
					List<?> list = (List<?>) val;
					StringBuffer sb = new StringBuffer();
					for (Object o : list) {
						if (sb.length() > 0) {
							sb.append(", ");
						}
						sb.append("'").append(o).append("'");
					}
					para.put(s, sb.toString());
				} else {
					para.put(s, val);
				}
			}
		}

		return para;
	}
}

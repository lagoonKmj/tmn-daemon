package com.lagoon.tmn.mig;

import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.dao._AdamsDao;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;

public class MigTeamsDcnDao extends _AdamsDao {

	private MigTeamsDcnQid QID;

	public MigTeamsDcnDao() {
		QID = new MigTeamsDcnQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return getDbTrans(AdamsCfg.ADAMSDB, MigTeamsDcnQid.QUERY_XML_FILE);
	}
	
	public void mergeOIV10104() throws Exception {
		DbTrans tran = getDbTrans();
		try {
			tran.start();
			tran.execute(QID.UPDATE_OIV10104__From_EQUIP_DCN, null);
			tran.execute(QID.UPDATE_OIV10104__From_EMS_DCN, null);
			tran.commit();
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	public void mergeOMN33812() throws Exception {
		DbTrans tran = getDbTrans();
		try {
			tran.start();
			tran.execute(QID.UPDATE_OMN33812__From_CF_ALARM_REASON_INFO, null);
			tran.commit();
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	/**
	 * DCN, EMS 머지(하루 한번)
	 * 
	 * @throws Exception
	 */
	public void mergeDcnAndEms() throws Exception {
		DbTrans tran = getDbTrans();
		try {
			tran.start();
			tran.execute(QID.MERGE_OIV10104__From_DCN, null);
			tran.execute(QID.MERGE_OIV10105__From_DCN, null);
//			tran.execute(QID.MERGE_OIV10106, null);
//			tran.execute(QID.MERGE_OIV10618__From_OIV10105, null);
//			tran.execute(QID.MERGE_OIV10618__From_OIV10104, null);
//			tran.execute(QID.MERGE_OIV10619__From_OIV10105, null);
//			tran.execute(QID.MERGE_OIV10619__From_OIV10104, null);
//			tran.execute(QID.MERGE_OIV10614__From_OIV10104, null);
			tran.commit();
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}
	
	public void mergeFromClctSvr(Map<String, Object> para) throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();
			
			if (para.containsKey("emsTypVal")) {
				tran.execute(QID.MERGE_OIV10105_BY_CLCT_SVR_NUM, getPara(para, "emsTypVal", "clctSvrIpAddr"));
			}
			if (para.containsKey("equipMdlCd")) {
				tran.execute(QID.MERGE_OIV10104_BY_CLCT_SVR_NUM, getPara(para, "equipMdlCd", "clctSvrIpAddr"));
			}
			tran.commit();
		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

}

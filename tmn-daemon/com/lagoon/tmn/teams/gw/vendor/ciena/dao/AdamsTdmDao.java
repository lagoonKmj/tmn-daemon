package com.lagoon.tmn.teams.gw.vendor.ciena.dao;

import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsTdmDao {

	public static void main(String[] args) {
		try {
			AdamsTdmDao dao = new AdamsTdmDao();
			dao.test("TEST");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private AdamsTdmQid QID;

	public AdamsTdmDao() throws Exception {
		QID = new AdamsTdmQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsTdmQid.QUERY_XML_FILE));
	}

	/**
	 * 저속급 TDM 목록을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EquipDcn> selectTdmDcn() throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();
			return (List<EquipDcn>) tran.selectQid2Res(QID.SELECT_OIV28150_DCN, null);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}
	}

	/**
	 * 수집한 CRS 정보를 등록한다.
	 * 
	 * @param equipId
	 * @param list
	 * @throws Exception
	 */
	public void setOIV28160(String equipId, List<OIV28160> list) throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();

			tran.execute(QID.DELETE_OIV28160__BY_equipId, equipId);

			tran.execute(QID.INSERT_OIV28160, list);

			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

	}

	public void test(String equipId) throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();

			tran.execute(QID.DELETE_OIV28160__BY_equipId, equipId);

			List<OIV28160> list = new ArrayList<OIV28160>();

			OIV28160 ret = new OIV28160();
			ret.setAdrcEquipPortDesc("A");
			ret.setBdrcEquipPortDesc("B");
			ret.setCrsClVal("CRS");
			ret.setCrsRmk("C");
			ret.setSgnlTypNm("D");
			ret.setEquipId("QQ");
			ret.setClctDtm("AAAAAAA");
			list.add(ret);

			tran.execute(QID.INSERT_OIV28160, list);

			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

	}

}

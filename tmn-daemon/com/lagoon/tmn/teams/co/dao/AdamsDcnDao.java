package com.lagoon.tmn.teams.co.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OIV10106;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;

/**
 * 데이터베이스 관련 클래스
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsDcnDao extends _AdamsDao {

	public static void main(String[] args) {
		AdamsDcnDao dao = new AdamsDcnDao();
		Map<String, Object> para = new HashMap<String, Object>();
		para.put("equipMdlCd", "'0000000637', '0000000624'");
		para.put("equipMfactCd", "219");
		para.put("emsTypVal", "'WOORINET_PTN_EMS'");
		try {
			dao.selectEquipDcnList(para);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private AdamsDcnQid QID;

	private DbTrans getDbTrans() throws Exception {
		return getDbTrans(AdamsCfg.ADAMSDB, AdamsDcnQid.QUERY_XML_FILE);
	}

	public AdamsDcnDao() {
		QID = new AdamsDcnQid();
	}

	@SuppressWarnings("unchecked")
	public List<EmsDcn> selectEmsDcnList(Map<String, Object> para) throws Exception {

		DbTrans tran = getDbTrans();

		if (para.get("emsTypVal") == null) {
			return new ArrayList<EmsDcn>();
		}

		try {
			tran.start();
			return (List<EmsDcn>) tran.selectQid2Res(QID.SELECT_EMS_DCN__BY_emsTypVal, getPara(para, "emsTypVal", "clctSvrIpAddr"));
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}

	@SuppressWarnings("unchecked")
	public List<EquipDcn> selectEquipDcnList(Map<String, Object> para) throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();
			return (List<EquipDcn>) tran.selectQid2Res(QID.SELECT_EQUIP_DCN__BY_equipMfactCd_equipMdlCd//
					, getPara(para, "equipMdlCd", "equipMfactCd", "equipTidVal", "equipIpAddr", "clctSvrIpAddr"));
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}

	@SuppressWarnings("unchecked")
	public List<EquipDcn> selectEquipRtList(Map<String, Object> para) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			return (List<EquipDcn>) tran.selectQid2Res(QID.SELECT_EQUIP_RT__BY_equipMfactCd_equipMdlCd//
					, getPara(para, "equipMdlCd", "equipMfactCd", "clctSvrIpAddr"));
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}

	/**
	 * 
	 * @param para
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<AdamsEquipVo> selectEquipList(Map<String, Object> para) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			
			Map<String, Object> param = getPara(para, "equipMdlCd", "equipMfactCd");
			return (List<AdamsEquipVo>) tran.selectQid2Res(QID.SELECT_ADAMS_EQUIP__BY_equipMdlCd, param);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}

	/**
	 * EMS장비내역(OIV10106)에 추가
	 * 
	 * @param emsId
	 * @param equipId
	 * @param emsInrEquipKeyVal
	 * @return
	 * @throws Exception
	 */
	public OIV10106 insertOIV10106(String emsId, String equipId, String emsInrEquipKeyVal) throws Exception {
		DbTrans tran = getDbTrans();

		try {
			OIV10106 o = new OIV10106();
			o.setAutoMgmtYn("Y");
			o.setEmsEquipNum("");
			o.setEmsId(emsId);
			o.setEmsInrEquipKeyVal(emsInrEquipKeyVal);
			o.setEquipId(equipId);

			tran.start();
			tran.execute(QID.INSERT_OIV10106, o);
			tran.commit();

			return o;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	/**
	 * EMS의 사용하는 IP를 설정한다.
	 * 
	 * @param emsId
	 * @param useIpAddrIdxVal
	 * @throws Exception
	 */
	public void updateOIV10105(String emsId, int useIpAddrIdxVal) throws Exception {
		DbTrans tran = getDbTrans();

		try {

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("emsId", emsId);
			para.put("useIpAddrIdxVal", useIpAddrIdxVal);

			tran.start();
			tran.execute(QID.UPDATE_OIV10105__BY_emsId, para);
			tran.commit();

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	/**
	 * 
	 * 필요 데이터를 생성 합니다.
	 * 
	 * <pre>
	 * 수집장비모델맵핑테이블(OIV10213)
	 * 수집장비맵핑테이블(OIV10107)
	 * </pre>
	 * 
	 * @param para
	 * @throws Exception
	 */
	public void mergeFromOIV10200AndOIV10100(Map<String, Object> para) throws Exception {
		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> param = getPara(para, "equipMdlCd");
			tran.execute(QID.MERGE_OIV10213__From_OIV10200, param);
			tran.execute(QID.MERGE_OIV10107__From_OIV10100, param);
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

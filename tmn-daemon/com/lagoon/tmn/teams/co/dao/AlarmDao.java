package com.lagoon.tmn.teams.co.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsAlarmCode;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.vo.LineInfoVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetAlcdVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811;
import com.lagoon.tmn.teams.fxms.dbo.OMN33820;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;
import com.lagoon.tmn.teams.fxms.dbo.ONM10100;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;
import fxms.bas.co.noti.FxEvent.STATUS;

/**
 * Teams 장애 처리
 * 
 * @author lagoon(강명중)
 *
 */
public class AlarmDao {

	private AlarmQid QID;

	public AlarmDao() {
		QID = new AlarmQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager
				.getMgr()
				.getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(
						AdamsCfg.getHomeDeployConfSql(AlarmQid.QUERY_XML_FILE));
	}

	private FxDaoExecutor getFxDaoExecutor() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB).createFxDao();
	}

	private Timestamp getNowTimestamp() {
		Timestamp nowTimestamp = new Timestamp(System.currentTimeMillis());
		return nowTimestamp;
	}

	/**
	 * 다음 시퀀스 번호를 호출하여 반환
	 * 
	 * @param seqName
	 * @return Long
	 */
	@SuppressWarnings("finally")
	public Long getNextVal(String seqName) {

		FxDaoExecutor fxDaoExecutor = null;
		Long nextNumber = null;

		try {
			fxDaoExecutor = getFxDaoExecutor();
			fxDaoExecutor.start();

			nextNumber = fxDaoExecutor.getNextVal(seqName, Long.class);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			fxDaoExecutor.stop();
			return nextNumber;
		}
	}

	/**
	 * 전송장비 장애 발생(추가)
	 * 
	 * @param mo
	 * @param trmsNetAlcdVo
	 * @param trmsNetEventVo
	 * @return omn33810
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public OMN33810 insertEquipAlarm(EquipMo mo, TrmsNetAlcdVo trmsNetAlcdVo,
			TrmsNetEventVo trmsNetEventVo) throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();
		OMN33810 omn33810 = null;
		OMN33811 omn33811 = null;

		try {
			fxDaoExecutor.start();

			/* 전송장비장애이력 */
			omn33810 = new OMN33810();
			omn33810.setTrmsEquipDablNum(trmsNetEventVo.getTrmsEquipDablNum());
			omn33810.setAuditId(TeamsCode.AUDIT_ID);
			omn33810.setAuditDtm(getNowTimestamp());
			omn33810.setDablGrCd(trmsNetEventVo.getDablGrCd());
			omn33810.setDablCd(TeamsAlarmCode.DABL_CD_EQUIP_ALARM);
			omn33810.setOccrDtm(Long.toString(trmsNetEventVo.getOccurTime()));

			omn33810.setOccrRcvDtm(new Timestamp(trmsNetEventVo.getRecvTime()));

			omn33810.setEquipId(mo.getEquipId());
			omn33810.setEquipNm(mo.getMoName());
			omn33810.setEquipTidVal(mo.getEquipTidVal());
			omn33810.setEquipIpAddr(mo.getEquipIpAddr());
			omn33810.setEquipMdlCd(mo.getEquipMdlCd());
			omn33810.setTpoCd(mo.getTpoCd());

			omn33810.setScardDesc(trmsNetEventVo.getCardDescr());
			omn33810.setAllMsgCtt(trmsNetEventVo.getFullMsg()); // ALL_MSG_CTT
																// 전체메시지내용
																// VARCHAR2(2000
																// BYTE)
			omn33810.setTrmsNetEquipMsgMgmtNum(trmsNetAlcdVo
					.getTrmsNetEquipMsgMgmtNum());
			omn33810.setCmprCharStrVal(trmsNetAlcdVo.getCmprCharStrVal());
			omn33810.setOperDablYn(trmsNetEventVo.isOperDablYn());
			omn33810.setEmcyDablYn(trmsNetAlcdVo.isEmcyDablYn());
			omn33810.setDmgLineCnt(trmsNetEventVo.getDmgLineCnt());
			omn33810.setDablDupCnt(trmsNetEventVo.getDablDupCnt());
			omn33810.setPortDesc(trmsNetEventVo.getPortDescr());
			omn33810.setDablMsgCtt(trmsNetEventVo.getReason()); // TODO 확인필요
			omn33810.setDablOccrLocDesc(trmsNetEventVo.getLocation());
			omn33810.setEmsId(trmsNetEventVo.getEmsId());
			omn33810.setEmsAlmVal(trmsNetEventVo.getEmsAlmVal());
			// omn33810.setDablSendYn(true);
			// omn33810.setDablSendDtm(getNowTimestamp());
			// omn33810.setDablSendRsltVal("0");
			fxDaoExecutor.insertOfClass(OMN33810.class, omn33810);

			/* 전송장비장애현재 */
			omn33811 = new OMN33811();
			omn33811.setTrmsEquipDablNum(trmsNetEventVo.getTrmsEquipDablNum());
			omn33811.setAuditId(TeamsCode.AUDIT_ID);
			omn33811.setAuditDtm(getNowTimestamp());
			omn33811.setDablGrCd(trmsNetEventVo.getDablGrCd());
			omn33811.setDablCd(TeamsAlarmCode.DABL_CD_EQUIP_ALARM);
			omn33811.setEquipId(mo.getEquipId());
			omn33811.setPortDesc(trmsNetEventVo.getPortDescr());
			omn33811.setScardDesc(trmsNetEventVo.getCardDescr());
			omn33811.setDablOccrLocDesc(trmsNetEventVo.getLocation());
			omn33811.setEmsId(trmsNetEventVo.getEmsId());
			omn33811.setEmsAlmVal(trmsNetEventVo.getEmsAlmVal());
			fxDaoExecutor.insertOfClass(OMN33811.class, omn33811);

		} catch (Exception e) {
			Logger.logger.error(e);
			omn33810 = null;
		} finally {
			fxDaoExecutor.stop();
			return omn33810;
		}
	}

	/**
	 * 전송장비 망장애 발생(추가)
	 * 
	 * @param trmsEquipDablNum
	 * @param networkInfoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public List<OMN33830> insertNetworkAlarm(Long trmsEquipDablNum,
			List<NetworkInfoVo> networkInfoList) throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();
		List<OMN33830> omn33830List = new ArrayList<OMN33830>();

		try {
			fxDaoExecutor.start();

			for (NetworkInfoVo networkInfoVo : networkInfoList) {

				Long trmsRingDablNum = fxDaoExecutor.getNextVal("OMN33830_S1",
						Long.class);

				/* 전송망링장애 */
				OMN33830 omn33830 = new OMN33830();
				omn33830.setTrmsRingDablNum(trmsRingDablNum);
				omn33830.setAuditId(TeamsCode.AUDIT_ID);
				omn33830.setAuditDtm(getNowTimestamp());
				omn33830.setTrmsEquipDablNum(trmsEquipDablNum);
				omn33830.setNetNum(networkInfoVo.getNetNum());
				omn33830.setNwNm(networkInfoVo.getNwNm());
				omn33830.setTrmsNetTopoTypCd(networkInfoVo
						.getTrmsNetTopoTypCd());
				omn33830.setTrmsNetUsgCd(networkInfoVo.getTrmsNetUsgCd());
				omn33830.setTrmsNetEquipCapaClCd(networkInfoVo
						.getMaxTrmsNetEquipCapaClCd());
				omn33830.setNetDablCd(networkInfoVo.getNetDablCd());
				omn33830.setTrunkNum(networkInfoVo.getTrunkNum());
				// omn33830.setDablSendYn(true);
				// omn33830.setDablSendDtm(getNowTimestamp());
				// omn33830.setDablSendRsltVal("0");
				omn33830List.add(omn33830);
			}

			fxDaoExecutor.insertOfClass(OMN33830.class, omn33830List);

		} catch (Exception e) {
			Logger.logger.error(e);
			omn33830List.clear();
		} finally {
			fxDaoExecutor.stop();
			return omn33830List;
		}
	}

	/**
	 * 전송장비 회선장애 발생(추가)
	 * 
	 * @param trmsEquipDablNum
	 * @param lineInfoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("finally")
	public List<OMN33820> insertLineAlarm(Long trmsEquipDablNum,
			List<LineInfoVo> lineInfoList) throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();
		List<OMN33820> omn33820List = new ArrayList<OMN33820>();

		try {
			fxDaoExecutor.start();

			for (LineInfoVo lineInfoVo : lineInfoList) {

				Long trmsLineDablNum = fxDaoExecutor.getNextVal("OMN33820_S1",
						Long.class);

				/* 전송망회선장애 */
				OMN33820 omn33820 = new OMN33820();
				omn33820.setTrmsLineDablNum(trmsLineDablNum);
				omn33820.setAuditId(TeamsCode.AUDIT_ID);
				omn33820.setAuditDtm(getNowTimestamp());
				omn33820.setTrmsEquipDablNum(trmsEquipDablNum);
				omn33820.setLineNum(lineInfoVo.getLineNum());
				omn33820.setCustNum(lineInfoVo.getCustNum());
				if (lineInfoVo.getSvcNum() != null) {
					omn33820.setSvcMgmtNum(Long.parseLong(lineInfoVo
							.getSvcNum()));
				}
				omn33820.setTrmsNetLineUsgCd(lineInfoVo.getTrmsNetLineUsgCd());
				omn33820.setTrmsNetLineSvcCd(lineInfoVo.getTrmsNetLineSvcCd());
				omn33820.setLineCapaClCd(lineInfoVo
						.getMaxTrmsNetEquipCapaClCd());
				omn33820.setSupTpoCd(lineInfoVo.getSupTpoCd());
				omn33820.setSubTpoCd(lineInfoVo.getSubTpoCd());
				omn33820.setSaleChrgrId(lineInfoVo.getSaleChrgrId());
				omn33820.setTrmsNetMgmtGrCd(lineInfoVo.getTrmsNetMgmtGrCd());
				// omn33820.setDablSendYn(true);
				// omn33820.setDablSendDtm(getNowTimestamp());
				// omn33820.setDablSendRsltVal("0");

				// 아담스 클라이언트에서 필요한 필드's
				omn33820.setLineNm(lineInfoVo.getLineNm());
				omn33820.setNetDablCd(lineInfoVo.getNetDablCd());
				
				omn33820List.add(omn33820);
			}

			fxDaoExecutor.insertOfClass(OMN33820.class, omn33820List);

		} catch (Exception e) {
			Logger.logger.error(e);
			omn33820List.clear();
		} finally {
			fxDaoExecutor.stop();
			return omn33820List;
		}
	}

	/**
	 * 전송장비 현재 장애를 조회하여 조회하여, 장비 장애번호를 키로 Map 으로 가공 하여 반환 한다.
	 * 
	 * @return Map<Long, OMN33810>
	 * @throws Exception
	 */
	public Map<Long, OMN33810> selectAlarm2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<Long, OMN33810> map = new ConcurrentHashMap<Long, OMN33810>();

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
					OMN33810 vo = (OMN33810) obj;
					map.put(vo.getTrmsEquipDablNum(), vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_ALARM_LIST, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

		return map;
	}

	/**
	 * 전송장비 현재 망 장애를 조회하여 조회하여, 망 장애번호를 키로 Map 으로 가공 하여 반환 한다.
	 * 
	 * @return Map<Long, OMN33830>
	 * @throws Exception
	 */
	public Map<Long, OMN33830> selectNetAlarm2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<Long, OMN33830> map = new HashMap<Long, OMN33830>();

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
					OMN33830 vo = (OMN33830) obj;
					map.put(vo.getTrmsRingDablNum(), vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_NET_ALARM_LIST, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

		return map;
	}

	public void deleteEquipAlarm(Long trmsEquipDablNum, String rlseDtm,
			Timestamp rlseRcvDtm) throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();

		try {
			fxDaoExecutor.start();

			/* 전송장비장애이력 수정 */
			Map<String, Object> wherePara = new HashMap<String, Object>();
			wherePara.put("rlseDtm", rlseDtm);
			wherePara.put("rlseRcvDtm", rlseRcvDtm);
			wherePara.put("dablRlseSendYn", true);
			wherePara.put("dablRlseSendDtm", getNowTimestamp());
			wherePara.put("dablRlseSendRsltVal", "0");
			wherePara.put("trmsEquipDablNum", trmsEquipDablNum);
			fxDaoExecutor.updateOfClass(OMN33810.class, wherePara);

			/* 전송장비장애현재 삭제 */
			wherePara.remove("rlseDtm");
			wherePara.remove("rlseRcvDtm");
			fxDaoExecutor.deleteOfClass(OMN33811.class, null, wherePara);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			fxDaoExecutor.stop();
		}
	}

	/**
	 * 장비ID를 기준으로 망작업내역을 Map 으로 가공 하여 반환 한다.
	 * 
	 * @return Map<String, List<ONM10100>>
	 * @throws Exception
	 */
	public Map<String, List<ONM10100>> selectNetOper2MapByEquipId()
			throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, List<ONM10100>> map = new HashMap<String, List<ONM10100>>();

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
					ONM10100 vo = (ONM10100) obj;
					List<ONM10100> entry = map.get(vo.getEquipId());
					if (entry == null) {
						entry = new ArrayList<ONM10100>();
						map.put(vo.getEquipId(), entry);
					}
					entry.add(vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_NET_OPER_LIST, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

		return map;
	}

	public void updateAlarmStatus(TeamsAlarmFxEvent teamsAlarmFxEvent,
			boolean isNotify) throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();

		try {
			fxDaoExecutor.start();
			Map<String, Object> wherePara = null;
			OMN33810 omn33810 = teamsAlarmFxEvent.getOmn33810();
			if (teamsAlarmFxEvent.getStatus() == STATUS.added) {

				switch (teamsAlarmFxEvent.getAlarmType()) {

				case TeamsAlarmFxEvent.EQUIP_ALARM:
					wherePara = setPara(wherePara, false, isNotify);
					wherePara.put("trmsEquipDablNum",
							omn33810.getTrmsEquipDablNum());
					fxDaoExecutor.updateOfClass(OMN33810.class, wherePara);
					break;
				case TeamsAlarmFxEvent.NETWORK_ALARM:
					OMN33830 omn33830 = teamsAlarmFxEvent.getOmn33830();
					wherePara = setPara(wherePara, false, isNotify);
					wherePara.put("trmsRingDablNum",
							omn33830.getTrmsRingDablNum());
					fxDaoExecutor.updateOfClass(OMN33830.class, wherePara);
					break;
				case TeamsAlarmFxEvent.LINE_ALARM:
					OMN33820 omn33820 = teamsAlarmFxEvent.getOmn33820();
					wherePara = setPara(wherePara, false, isNotify);
					wherePara.put("trmsLineDablNum",
							omn33820.getTrmsLineDablNum());
					fxDaoExecutor.updateOfClass(OMN33820.class, wherePara);
					break;

				}

			} else {
				wherePara = setPara(wherePara, true, isNotify);
				wherePara.put("trmsEquipDablNum",
						omn33810.getTrmsEquipDablNum());
				fxDaoExecutor.updateOfClass(OMN33810.class, wherePara);
			}
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			fxDaoExecutor.stop();
		}
	}

	private Map<String, Object> setPara(Map<String, Object> wherePara,
			boolean isRelease, boolean isNotify) {
		wherePara = new HashMap<String, Object>();
		if (isRelease) {
			if (isNotify) {
				wherePara.put("dablRlseSendYn", true);
				wherePara.put("dablRlseSendDtm", getNowTimestamp());
			} else {
				wherePara.put("dablRlseSendRsltVal", "1");
			}
		} else {
			if (isNotify) {
				wherePara.put("dablSendYn", true);
				wherePara.put("dablSendDtm", getNowTimestamp());
			} else {
				wherePara.put("dablSendRsltVal", "1");
			}
		}
		return wherePara;
	}

	@SuppressWarnings("unchecked")
	public List<OMN33810> selectNoSendEquipAlarmList(boolean isClear)
			throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			if (isClear) {
				para.put("isNoSendAlarmClear", true);
			} else {
				para.put("isNoSendAlarm", true);
			}
			return tran.selectQid2Res(QID.SELECT_EQUIP_ALARM_LIST, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OMN33830> selectNoSendNetAlarmList() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			return tran.selectQid2Res(QID.SELECT_NO_SEND_NET_ALARM_LIST, null);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OMN33820> selectNoSendLineAlarmList() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			return tran.selectQid2Res(QID.SELECT_NO_SEND_LINE_ALARM_LIST, null);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}

	public OMN33810 selectEquipAlarmByTrmsEquipDablNum(long trmsEquipDablNum)
			throws Exception {

		FxDaoExecutor fxDaoExecutor = getFxDaoExecutor();
		try {

			fxDaoExecutor.start();

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("trmsEquipDablNum", trmsEquipDablNum);

			return fxDaoExecutor
					.selectOne(OMN33810.class, para, OMN33810.class);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			fxDaoExecutor.stop();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<OMN33810> selectEquipAlarmList4Deleted() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("isDeleted", true);

			return tran.selectQid2Res(QID.SELECT_EQUIP_ALARM_LIST, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}

	public void deleteAlarmByTrmsEquipDablNum(long trmsEquipDablNum)
			throws Exception {

		DbTrans tran = getDbTrans();

		String qid = QID.DELETE_ALARM__BY_trmsEquipDablNum;
		try {
			tran.start();

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("trmsEquipDablNum", trmsEquipDablNum);

			// 장비 장애 삭제
			para.put("tableName", "OMN33810");
			tran.execute(qid, para);
			// 장비 장애(현재) 삭제
			para.put("tableName", "OMN33811");
			tran.execute(qid, para);
			// 망 장애 삭제
			para.put("tableName", "OMN33830");
			tran.execute(qid, para);
			// 회선 장애 삭제
			para.put("tableName", "OMN33820");
			tran.execute(qid, para);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
	}
	
	public void updateLastDablDtm(EquipMo mo, OMN33810 omn33810)
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipId", mo.getEquipId());
			para.put("dcnNum", mo.getDcnNum());
			para.put("occrDtm", omn33810.getOccrDtm());
			para.put("occrRcvDtm", omn33810.getOccrRcvDtm());
			
			tran.execute(QID.UPDATE_OIV10178__BY_equipId, para);

			tran.execute(QID.UPDATE_OIV10618__BY_dcnNum, para);

		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectCrsDisaccordCount() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();
			return tran.selectQid2Res(QID.SELECT_CRS_DISACCORD_COUNT, null);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}

}

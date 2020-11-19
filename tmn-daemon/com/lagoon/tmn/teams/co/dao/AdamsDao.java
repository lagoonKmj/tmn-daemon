package com.lagoon.tmn.teams.co.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.app.mgr.TrmsAppDataMgr;
import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.LineInfoVo;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;
import com.lagoon.tmn.teams.co.vo.LineVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.co.vo.NetworkLinkVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetAlcdVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN30203;
import com.lagoon.tmn.teams.fxms.mo.CardMo;
import com.lagoon.tmn.teams.fxms.mo.DcnMo;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;
import com.lagoon.tmn.teams.fxms.mo.PortMo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DaoListener;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import subkjh.bas.fxdao.control.FxDaoExecutor;
import fxms.bas.co.noti.FxEvent.STATUS;
import fxms.bas.mo.child.MoConfig;

/**
 * 데이터베이스 관련 클래스
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsDao {

	public static void main(String[] args) {
		AdamsDao dao = new AdamsDao();
		try {
			// Map<String, List<EventMsgVo>> map = dao.selectEventMsg(new
			// String[] {});
			// Map<String, List<EventMsgVo>> map = dao.selectEventMsg(new
			// String[] { "0000190347", "0000190348" });
			// System.out.println(map.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// List<NeMo> neList = dao.selectNeList("TFOM_EMS", null);
		// System.out.println(neList.size());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// List<DcnMo> neList = dao.selectDcnListByEmsType("TFOM_EMS");
		// System.out.println(neList.size());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// List<DcnMo> neList = dao.selectDcnCotListByModel(new String[] {
		// "0000000348", "0000000347" });
		// System.out.println(neList.size());
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		try {
			List<EquipMo> neList = dao.selectNeListWithCot(new String[] {
					"0000000348", "0000000347" });
			System.out.println(neList.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private AdamsTeamsQid QID;

	public AdamsDao() {
		QID = new AdamsTeamsQid();
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager
				.getMgr()
				.getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(
						AdamsCfg.getHomeDeployConfSql(AdamsTeamsQid.QUERY_XML_FILE));
	}

	private FxDaoExecutor getFxDaoExecutor() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB).createFxDao();
	}

	public List<OMN30203> selectOMN30203() throws Exception {

		FxDaoExecutor tran = getFxDaoExecutor();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("netClCd", "02");

			return tran.select(OMN30203.class, para, OMN30203.class);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}

	}

	/**
	 * EMS종류, 장비모델 기준으로 장비를 조회한다.
	 * 
	 * @param dcnEmsTyp
	 * @param equipMdlCd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EquipMo> selectNeList(String dcnEmsTyp, String equipMdlCd[],
			int dcnNum) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			if (dcnEmsTyp != null)
				para.put("dcnEmsTyp", dcnEmsTyp);
			if (equipMdlCd != null)
				para.put("equipMdlCd", getString(equipMdlCd));
			if (dcnNum > 0)
				para.put("dcnNum", dcnNum);

			return (List<EquipMo>) tran.selectQid2Res(
					QID.SELECT_NE_LIST__BY_equipMdlCd_dcnEmsTyp_dcnNum, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	/**
	 * 모델 조건으로 COT에 연결된 RT 장비를 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<EquipMo> selectNeListWithCot(String equipMdlCd[])
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipMdlCd", getString(equipMdlCd));

			return (List<EquipMo>) tran.selectQid2Res(
					QID.SELECT_NE_LIST__BY_Cot_equipMdlCd, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	/**
	 * EMS 종류를 기준으로 DCN을 조회한다.
	 * 
	 * @param dcnEmsTyp
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DcnMo> selectDcnListByEmsType(String dcnEmsTyp)
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("dcnEmsTyp", dcnEmsTyp);

			return (List<DcnMo>) tran.selectQid2Res(
					QID.SELECT_DCN_MO__BY_dcnEmsTyp, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	/**
	 * COT의 모델 기준으로 DCN을 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<DcnMo> selectDcnCotListByModel(String equipMdlCd[])
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipMdlCd", getString(equipMdlCd));

			return (List<DcnMo>) tran.selectQid2Res(
					QID.SELECT_DCN_COT__BY_equipMdlCd, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<DcnMo> selectDcnList(Map<String, Object> para) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			if (para.get("equipMdlCd") != null) {
				return (List<DcnMo>) tran.selectQid2Res(
						QID.SELECT_DCN_COT__BY_equipMdlCd, para);
			} else if (para.get("dcnEmsTyp") != null) {
				return (List<DcnMo>) tran.selectQid2Res(
						QID.SELECT_DCN_MO__BY_dcnEmsTyp, para);
			} else {
				return new ArrayList<DcnMo>();
			}
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	/**
	 * 장비가 보내는 이벤트(OMN33812) 내용을 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<TrmsNetAlcdVo>> trmsNetAlcdMapByEquipMdlCd(
			String equipMdlCd[]) throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, List<TrmsNetAlcdVo>> map = new HashMap<String, List<TrmsNetAlcdVo>>();

		Map<String, Object> para = new HashMap<String, Object>();
		if (equipMdlCd != null) {
			para.put("equipMdlCd", getString(equipMdlCd));
		}

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
					TrmsNetAlcdVo vo = (TrmsNetAlcdVo) obj;
					List<TrmsNetAlcdVo> entry = map.get(vo.getEquipMdlCd());
					if (entry == null) {
						entry = new ArrayList<TrmsNetAlcdVo>();
						map.put(vo.getEquipMdlCd(), entry);
					}
					entry.add(vo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_TRMS_NET_ALCD, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

		return map;
	}

	private String getString(String s[]) {
		if (s == null || s.length == 0) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append("'");
		sb.append(s[0]);
		sb.append("'");
		for (int i = 1; i < s.length; i++) {
			sb.append(",'");
			sb.append(s[i]);
			sb.append("'");
		}

		return sb.toString();
	}

	/**
	 * 
	 * @param moConfig
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void updateMoConfig(MoConfig moConfig) throws Exception {

		DbTrans tran = getDbTrans();

		String newEquipPortId;
		String equipConsItmId;
		EquipMo parent = (EquipMo) moConfig.getParent();

		try {
			tran.start();

			// 1. 장비 버전 수정

			tran.execute(QID.UPDATE_EQUIP_VER, parent);

			// 2. 카드 추가/삭제/수정

			List<CardMo> cardList = (List<CardMo>) moConfig
					.getChildren(CardMo.MO_CLASS);

			for (CardMo card : cardList) {
				if (card.getStatus() == STATUS.deleted) {
					tran.execute(QID.DELETE_CARD, card);
				} else if (card.getStatus() == STATUS.changed) {
					tran.execute(QID.UPDATE_CARD, card);
				} else if (card.getStatus() == STATUS.added) {
					equipConsItmId = tran.selectQidStr(
							QID.SELECT_NEW_EQUIP_CONS_ITM_ID, null);
					card.setEquipId(parent.getEquipId());
					card.setEquipConsItmId(equipConsItmId);
					tran.execute(QID.INSERT_CARD, card);
				}
			}

			// 3. 포트 추가 / 수정 / 삭제

			List<PortMo> portList = (List<PortMo>) moConfig
					.getChildren(PortMo.MO_CLASS);

			for (PortMo port : portList) {
				if (port.getStatus() == STATUS.deleted) {
					tran.execute(QID.DELETE_PORT, port);
				} else if (port.getStatus() == STATUS.changed) {
					tran.execute(QID.UPDATE_PORT, port);
				} else if (port.getStatus() == STATUS.added) {
					newEquipPortId = tran.selectQidStr(
							QID.SELECT_NEW_EQUIP_PORT_ID, null);
					port.setEquipPortId(newEquipPortId);
					for (CardMo card : cardList) {
						if (card.getEquipConsItmDesc().equals(
								port.getEquipConsItmId())) {
							port.setEquipConsItmId(card.getEquipConsItmId());
							break;
						}
					}
					tran.execute(QID.INSERT_PORT, port);
				}
			}

			tran.commit();
		} catch (Exception ex) {
			tran.rollback();
			throw ex;
		} finally {
			tran.stop();
		}
	}

	/**
	 * 전체 회선을 조회 한다.
	 * 
	 * @param
	 * @return List<LineVo>
	 * @throws Exception
	 */
	public List<LineVo> selectLineList() throws Exception {
		return selectLineList(null, null, null);
	}

	/**
	 * 회선번호, 링번호, 장비ID 기준으로 회선을 조회 한다.
	 * 
	 * @param lineNum
	 * @param ringNum
	 * @param equipId
	 * @return List<LineVo>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<LineVo> selectLineList(String lineNum, String ringNum,
			String equipId) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			if (lineNum != null) {
				para.put("lineNum", lineNum);
			}
			if (ringNum != null) {
				para.put("ringNum", ringNum);
			}
			if (equipId != null) {
				para.put("equipId", equipId);
			}
			return (List<LineVo>) tran.selectQid2Res(
					QID.SELECT_LINE_LIST__BY_lineNum_ringNum_equipId, para);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}

	/**
	 * 회선 기본정보를 조회하여, Map 으로 가공 하여 반환 한다.
	 * 
	 * <pre>
	 * 		WHERE 기업 회선 검증여부(co_line_vrf_yn) = "Y"
	 * </pre>
	 * 
	 * @return Map<String, LineInfoVo>
	 * @throws Exception
	 */
	public Map<String, LineInfoVo> selectLineInfo2Map() throws Exception {

		DbTrans tran = getDbTrans();

		Map<String, LineInfoVo> map = new HashMap<String, LineInfoVo>();

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
					LineInfoVo lineInfoVo = (LineInfoVo) obj;
					map.put(lineInfoVo.getLineNum(), lineInfoVo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}
			});

			tran.selectQid2Res(QID.SELECT_LINE_INFO_LIST, para);

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
	 * 망 기본정보를 조회하여, Map 으로 가공 하여 반환 한다.
	 * 
	 * @return Map<String, LineInfoVo>
	 * @throws Exception
	 */
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
		}

		return map;
	}

	/**
	 * 회선 내역 정보 를 조회(OIV10612) & 메니저가 데이터를 가공을 할수 있게 도와준다.
	 * 
	 * 
	 * @param TrmsAppDataMgr
	 * @return void
	 * @throws Exception
	 */
	public void selectLineListAndProcessData(TrmsAppDataMgr trmsAppDataMgr)
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			// para.put("equipId", "000000613037");

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
					LineNetworkBaseVo baseVo = (LineNetworkBaseVo) obj;
					trmsAppDataMgr.setDataMapByLineNetworkBaseVo(baseVo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}

			});
			tran.selectQid2Res(
					QID.SELECT_LINE_LIST__BY_lineNum_ringNum_equipId, para);
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();

			// 초기화 가 완료 된것은 리로드 시그널로 판단
			if (trmsAppDataMgr.isReady()) {
				// TODO
			}
		}
	}

	/**
	 * 네트워크 선번 내역 을 조회(OIV10615) & 메니저가 데이터를 가공을 할수 있게 도와준다.
	 * 
	 * 
	 * @param TrmsAppDataMgr
	 * @return void
	 * @throws Exception
	 */
	public void selectNetworkListAndProcessData(TrmsAppDataMgr trmsAppDataMgr)
			throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			// para.put("equipId", "000000613037");

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
					LineNetworkBaseVo baseVo = (LineNetworkBaseVo) obj;
					trmsAppDataMgr.setDataMapByLineNetworkBaseVo(baseVo);
				}

				@Override
				public void onStart(String[] arg0) throws Exception {
				}

			});
			tran.selectQid2Res(
					QID.SELECT_NETWORK_LIST__BY_netNum_ringNum_equipId, para);
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}
	}

	/**
	 * 
	 * 망 링크 정보를 조회해 필요한 데이터를 가공(레거시 소스 원복)
	 * 
	 * 
	 * <pre>
	 * * ****** 레거시소스 cfNeNetworkInfoTbl.cpp ****** *
	 * 
	 * 			ST_NETWORK_LINK_PORT_RAW st_data;
	 * 
	 *             os >> st_data.gubun;
	 *             os >> st_data.network_id;
	 *             os >> st_data.seq;
	 *             os >> st_data.ne_id;
	 *             os >> st_data.port_descr; if(os.is_null()) strcpy(st_data.port_descr, "");
	 * 
	 * 			if (!strcmp(st_data.gubun, "src"))
	 * 			{
	 * 				st_data.seq = vecSrc.size()+1;
	 * 				vecSrc.push_back(st_data);
	 * 			}
	 * 			else
	 * 			{
	 * 				st_data.seq = vecDst.size()+1;
	 * 				vecDst.push_back(st_data);
	 * 			}
	 * 			// ��ȸ�� ������ ���/��Ʈ - ���/��Ʈ link ������ �����.
	 * 			int max_ring_seq = vecSrc.size(); // vecSrc/vecDst�� ����� ������ ����.
	 * 			char szKey[100] = {0, }; 
	 * 			for (int i=0; i<max_ring_seq; i++)
	 * 			{
	 * 				ST_NETWORK_LINK_PORT st_link;
	 * 		
	 * 				st_link.network_id = vecSrc[i].network_id;
	 * 				st_link.src_ne_id = vecSrc[i].ne_id;
	 * 				strcpy(st_link.src_port_descr, vecSrc[i].port_descr);
	 * 		
	 * 				if (i+1 < max_ring_seq)
	 * 				{
	 * 					st_link.dst_ne_id = vecDst[i+1].ne_id;
	 * 					strcpy(st_link.dst_port_descr, vecDst[i+1].port_descr);
	 * 				}
	 * 				else
	 * 				{
	 * 					st_link.dst_ne_id = vecDst[0].ne_id;
	 * 					strcpy(st_link.dst_port_descr, vecDst[0].port_descr);
	 * 				}
	 * 				// src ����
	 * 				szKey[0]= '\0';
	 * 				sprintf(szKey, "%d^%s", st_link.src_ne_id, st_link.src_port_descr);
	 * 				m_mapLinkPort[szKey] = st_link;
	 * 		
	 * 				// dst ����
	 * 				szKey[0]= '\0';
	 * 				sprintf(szKey, "%d^%s", st_link.dst_ne_id, st_link.dst_port_descr);
	 * 				m_mapLinkPort[szKey] = st_link;
	 * 			}
	 * </pre>
	 * 
	 * @param netNum
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Map<String, NetworkLinkVo> selectNetworkLinkListAndMakeLinkData(
			String netNum) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("netNum", netNum);

			List<Map<String, String>> mapList = (List<Map<String, String>>) tran
					.selectQid2Res(QID.SELECT_NETWORK_LINK_LIST__BY_netNum,
							para);

			List<Map<String, String>> srcList = new ArrayList<Map<String, String>>();
			List<Map<String, String>> dstList = new ArrayList<Map<String, String>>();
			for (Map<String, String> map : mapList) {
				if (map.get("TYPE").equals("src")) {
					srcList.add(map);
				} else {
					dstList.add(map);
				}
			}

			Map<String, NetworkLinkVo> networkLinkMap = new HashMap<String, NetworkLinkVo>();
			int max_ring_seq = srcList.size();
			for (int iCnt = 0; iCnt < max_ring_seq; iCnt++) {

				Map<String, String> map = srcList.get(iCnt);

				NetworkLinkVo networkLinkVo = new NetworkLinkVo();
				networkLinkVo.setNetNum(map.get("NET_NUM"));
				networkLinkVo.setSrcEquipId(map.get("EQUIP_ID"));
				networkLinkVo.setSrcPortDescr(map.get("PORT_INFO"));

				if (iCnt + 1 < max_ring_seq) {
					networkLinkVo.setDstEquipId(dstList.get(iCnt + 1).get(
							"EQUIP_ID"));
					networkLinkVo.setDstPortDescr(dstList.get(iCnt + 1).get(
							"PORT_INFO"));
				} else {

					networkLinkVo.setDstEquipId(dstList.get(0).get("EQUIP_ID"));
					networkLinkVo.setDstPortDescr(dstList.get(0).get(
							"PORT_INFO"));
				}

				networkLinkMap.put(networkLinkVo.getSrcEquipId() + "@_@"
						+ networkLinkVo.getSrcPortDescr(), networkLinkVo);
				networkLinkMap.put(networkLinkVo.getDstEquipId() + "@_@"
						+ networkLinkVo.getDstPortDescr(), networkLinkVo);
			}

			return networkLinkMap;
			
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}

		return null;
	}
	
	/**
	 * 포트 전체 조회
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<PortMo> selectPortList() throws Exception {
		DbTrans tran = getDbTrans();
		try {
			tran.start();
			return (List<PortMo>) tran.selectQid2Res(QID.SELECT_PORT_LIST, null);
		} catch (Exception e) {
			Logger.logger.error(e);
		} finally {
			tran.stop();
		}
		return null;
	}
	
}

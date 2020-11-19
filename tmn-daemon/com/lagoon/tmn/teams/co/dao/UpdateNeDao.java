package com.lagoon.tmn.teams.co.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.DATA_RGST_MTHD_CL_CD;
import com.lagoon.tmn.teams.co.dao.dbo.OIV10223;
import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.fxms.dbo.OIV10104;
import com.lagoon.tmn.teams.fxms.dbo.OIV10110;
import com.lagoon.tmn.teams.fxms.dbo.OIV10200;
import com.lagoon.tmn.teams.fxms.dbo.OIV10205;
import com.lagoon.tmn.teams.fxms.dbo.OIV10209;
import com.lagoon.tmn.teams.fxms.dbo.OIV10210;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221;
import com.lagoon.tmn.teams.fxms.dbo.OIV10400;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.api.FxApi;
import fxms.bas.co.FamilyVo;
import fxms.bas.co.FamilyVo.Status;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 *
 * @author subkjh(김종훈)
 *
 */
public class UpdateNeDao {

	private AdamsTeamsConfQid QID;
	private Map<String, OIV10209> o209Map;
	private Map<String, List<OIV10205>> o205Map;
	private Timestamp auditDtm;

	public static void main(String[] args) throws Exception {

		DetectedNeVo ne = new DetectedNeVo();
		AdamsEquipVo mo = new AdamsEquipVo();
		mo.setEquipId("000000177617");
		mo.setEquipMdlCd("0000000356");
		mo.setEquipIpAddr("12.160.11.173");
		mo.setSwVerInfo("1.0.0");
		ne.fill(mo);

		DetectedCardVo card = new DetectedCardVo();
		card.setCardNm("SHELF-1");
		DetectedCardVo card2 = new DetectedCardVo();
		card2.setCardNm("Line card");

		List<DetectedCardVo> slotList = new ArrayList<DetectedCardVo>();
		slotList.add(card);
		slotList.add(card2);
		ne.setCardList(slotList);

		new UpdateNeDao(ne);
	}


	@SuppressWarnings("unchecked")
	public UpdateNeDao(DetectedNeVo ne) throws Exception {

		QID = new AdamsTeamsConfQid();

		long mstime = (System.currentTimeMillis() / 1000) * 1000;
		auditDtm = new Timestamp(mstime);

		FamilyVo<DetectedNeVo, Object> family = new FamilyVo<DetectedNeVo, Object>(ne) {

			@Override
			protected boolean equipChild(String tag, Object c1, Object c2) {

				if ("210".equals(tag)) {

					// 같은 카드 검색
					OIV10210 o1 = (OIV10210) c1;
					OIV10210 o2 = (OIV10210) c2;
					// 카드명으로 비교
					return o1.getEquipConsItmNm().equals(o2.getEquipConsItmNm());
				} else if ("400".equals(tag)) {

					// 같은 포트 검색
					OIV10400 o1 = (OIV10400) c1;
					OIV10400 o2 = (OIV10400) c2;

					// 포트 설명으로 비교
					return o1.getPortDesc().equals(o2.getPortDesc());
				}
				return false;
			}

			@Override
			protected boolean isMatch(String tag, Object c1, Object condition) {

				if ("210".equals(tag)) {
					// 카드 매칭
					OIV10210 o1 = (OIV10210) c1;
					return o1.getEquipConsItmNm().equals(condition.toString());
				} else if ("400".equals(tag)) {
					// 포트 매칭
					OIV10400 o1 = (OIV10400) c1;
					if(o1.getPortDesc()!= null){
						return o1.getPortDesc().equals(condition.toString());
					}
				}
				return false;
			}

		};

		StringBuffer sb = new StringBuffer();
		sb.append(Logger.makeString("Configuration, equipId", ne.getEquipId()));

		DbTrans tran = getDbTrans();
		Map<String, Object> para = new HashMap<String, Object>();
		try {
			tran.start();

			para.put("equipId", ne.getEquipId());

			init209Map(tran, ne);
			init205Map(tran, ne);
			// 속성 조회 ("자동탐색 구성품 우선")
			OIV10104 oiv10104 = (OIV10104) tran.selectQidObj(QID.SELECT_OIV10104__BY_equipId, para);
			String autoExplrConsItmPrtyYn = oiv10104.getAutoExplrConsItmPrtyYn();

			family.addOrgChildList("210", tran.selectQid2Res(QID.SELECT_OIV10210__BY_equipId, para));
			family.addOrgChildList("400", tran.selectQid2Res(QID.SELECT_OIV10400__BY_equipId, para));

			sb.append(Logger.makeSubString("org-210", family.size("210")));
			sb.append(Logger.makeSubString("org-400", family.size("400")));

			// 기존 내용을 삭제 플래그를 설정하면 아래쪽에서 비교하여 있다면 수정으로 다시 변경해 준다.
			family.setStatus("210", null, Status.deleted);
			family.setStatus("400", null, Status.deleted);

			// 자동으로 추가되어야할 카드, 포트 넣기
			setAutoPort(tran, family);

			// 장비기본 ( OIV10100 )
			tran.update(QID.UPDATE_OIV10100__BY_equipId, ne.makeOIV10100());

			// 장비접속상세 ( OIV10104 )
			tran.update(QID.UPDATE_OIV10104__BY_equipId, ne.makeOIV10104("TMNCONF", "1"));

			// 장비수집내역 ( OIV10178 )
			if (tran.update(QID.UPDATE_OIV10178__BY_equipId, ne.makeOIV10178()) == 0) {
				// 등록된 내용이 없으면 추가해 준다.
				tran.execute(QID.INSERT_OIV10178, ne.makeOIV10178());
			}

			// MERGE_OIV10106 ( EMS 장비 내역 )
			if (ne.getEmsId() != null) {
				tran.execute(QID.MERGE_OIV10106__By_equipId, ne.makeOIV10106());
			}

			// OIV10110 장비속성변경이력
			List<OIV10110> o110List = ne.makeOIV10110();
			if (o110List.size() > 0) {
				try {
					tran.execute(QID.INSERT_OIV10110, o110List);
				} catch (Exception e) {
					Logger.logger.error(e);
				}

			}

			// 카드
			processCard(tran, ne.getEquipId(), autoExplrConsItmPrtyYn, family);

			processPort(tran, ne.getEquipId(), autoExplrConsItmPrtyYn, family);

			sb.append(Logger.makeSubString("ret-210", family.getSizeString("210")));
			sb.append(Logger.makeSubString("ret-400", family.getSizeString("400")));

			tran.commit();

			Logger.logger.info(sb.toString());

		} catch (Exception e) {
			Logger.logger.error(e);
			tran.rollback();
			throw e;
		} finally {
			tran.stop();
		}
	}

	private List<OIV10221> check210(DbTrans tran, FamilyVo<DetectedNeVo, Object> family) throws Exception {
		List<OIV10221> o221List = new ArrayList<OIV10221>();
		DetectedNeVo ne = family.getParent();

		List<DetectedCardVo> cardList = ne.getCardChildren(null);
		if (cardList != null && cardList.size() > 0) {
			check210(tran, family, null, cardList, o221List);
		} else {
			check210(tran, family, null, ne.getCardList(), o221List);
		}

		return o221List;
	}

	private void check210(DbTrans tran, FamilyVo<DetectedNeVo, Object> family, OIV10210 parent, List<DetectedCardVo> cardList,
			List<OIV10221> o221List) throws Exception {
		DetectedNeVo ne = family.getParent();
		OIV10210 o210;
		OIV10209 o209;

		if (cardList == null || cardList.size() == 0) {
			return;
		}

		for (DetectedCardVo vo : cardList) {

			// Logger.logger.debug("*** {}:{}: {}:{}",
			// vo.getTid(),vo.getSlotNm(), vo.getCardNm(),vo.getPortCnt());

			if (vo.isEmptySlot() == false) {

				// 슬롯에 카드가 실장되어 있다면

				o210 = (OIV10210) family.getChild("210", vo.getCardNm());
				o209 = o209Map.get(vo.getCardModel());

				if (o210 != null) {
					// 찾은 구성품 정보를 기존 내용에 설정한다.
					vo.fill(o210);
					if (o209 != null) {
						o210.setEquipConsItmCd(o209.getEquipConsItmCd());
					}
					if (parent != null) {
						o210.setSupEquipConsItmId(parent.getEquipConsItmId());
					}

				} else {
					// 새로운 구성품을 만든다.
					o210 = vo.makeOIV10210(ne, o209, getO210NewId(tran));
					if (parent != null) {
						o210.setSupEquipConsItmId(parent.getEquipConsItmId());
					}
				}

				family.addDetectedChild("210", o210);

				o221List.add(vo.makeOIV10221(ne, o210));
				// 하위 카드를 처리한다.
				if (vo.getSlotNm() != null) {
					check210(tran, family, o210, ne.getCardChildren(vo.getSlotNm()), o221List);
				}

			} else {

				// 슬롯이 비워있다면 자체 저장소에만 기록한다.

				o221List.add(vo.makeOIV10221(ne, null));

			}
		}
	}

	private List<OIV10223> check400(DbTrans tran, FamilyVo<DetectedNeVo, Object> family) throws Exception {
		List<OIV10223> list = new ArrayList<OIV10223>();
		DetectedNeVo ne = family.getParent();
		OIV10400 o400;
		OIV10210 o210;
		for (DetectedPortVo vo : ne.getPortList()) {

			o400 = (OIV10400) family.getChild("400", vo.getPortDescr());
			o210 = (OIV10210) family.getChild("210", "1-" + vo.getCardNm());
			if (o210 == null) {
				o210 = (OIV10210) family.getChild("210", vo.getCardNm());
			}
			if (o400 != null) {
				// 조회된 내용을 기존 포트에 설정
				vo.fill(o400, o210);

			} else {
				// 새로운 포트를 만든다.
				o400 = vo.makeOIV10400(ne.getEquipId(), o210, getO400NewId(tran));
			}

			family.addDetectedChild("400", o400);

			list.add(vo.makeOIV10223(ne, o400));

		}

		return list;
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsTeamsConfQid.QUERY_XML_FILE));
	}

	@SuppressWarnings("unchecked")
	private String getO210NewId(DbTrans tran) throws Exception {
		List<String> list = tran.selectQid2Res(QID.SELECT_OIV10210_NEW_ID, null);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	private String getO400NewId(DbTrans tran) throws Exception {
		List<String> list = tran.selectQid2Res(QID.SELECT_OIV10400_NEW_ID, null);
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * 부가적으로 추가할 포트 정보를 읽는다.
	 *
	 * @param tran
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private void init205Map(DbTrans tran, DetectedNeVo ne) throws Exception {

		o205Map = new HashMap<String, List<OIV10205>>();

		List<OIV10205> o10205List = tran.selectQid2Res(QID.SELECT_OIV10205__By_equipMdlCd, ne.getEquipMdlCd());
		List<OIV10205> e10205;
		for (OIV10205 o : o10205List) {
			e10205 = o205Map.get(o.getEquipConsItmCd());
			if (e10205 == null) {
				e10205 = new ArrayList<OIV10205>();
				o205Map.put(o.getEquipConsItmCd(), e10205);
			}
			e10205.add(o);
		}
	}

	// private OIV10413 make10413(OIV10400 o400) {
	// OIV10413 o = new OIV10413();
	// o.setEquipId(o400.getEquipId());
	// o.setEquipPortId(o400.getEquipPortId());
	// o.setPortNm(o400.getPortDesc());
	// return o;
	// }

	/**
	 * 슬롯에 대한 DB 처리 작업을 수행한다.
	 *
	 * @param tran
	 * @param equipId
	 * @param family
	 * @throws Exception
	 */
	private void processCard(DbTrans tran, String equipId,
			String autoExplrConsItmPrtyYn, FamilyVo<DetectedNeVo, Object> family)
			throws Exception {

		List<?> list;
		List<OIV10221> o221List;

		// 수집한 구성품
		o221List = check210(tran, family);
		if (o221List.size() > 0) {

			for (OIV10221 e : o221List) {
				e.setAuditDtm(auditDtm);
			}

			try {
				// 구성품의 정보는 다른곳에서 속성을 추가하므로 병합한다.
				tran.execute(QID.MERGE_OIV10221, o221List);
				Map<String, Object> para = new HashMap<String, Object>();
				para.put("equipId", equipId);
				para.put("auditDtm", auditDtm);
				tran.execute(QID.DELETE_OIV10221__BY_equipId_auditDtm, para);

			} catch (Exception e) {
				// 오류가 발생하면 로그만 남기고 계속 처리한다.
				Logger.logger.error(e);
			}
		}

		// 미등록구성품기본 추가
		list = family.getChildren("222", Status.added);
		if (list.size() > 0) {
			for (Object o : list) {
				try {
					if (tran.selectQidInt(QID.SELECT_OIV10222__BY_PK, o, 0) <= 0) {
						tran.execute(QID.INSERT_OIV10222, o);
					}
				} catch (Exception e) {
					// 오류가 발생하면 로그만 남기고 계속 처리한다.
					Logger.logger.error(e);
				}
			}
		}

		// 신규 (운용자 등록이 없으므로 REG_MTHD_CD = '10')
		list = family.getChildren("210", Status.added);
		if (list.size() > 0) {
			tran.execute(QID.INSERT_OIV10210, list);
		}

		// 변경
		list = family.getChildren("210", Status.changed);
		for (Object obj : list) {
			OIV10210 oiv10210 = (OIV10210) obj;
			if (oiv10210.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.USER)) {
				// 화면에서 우선 등록 (자동탐색구성품우선여부 에 따라 코드값 적용)
				oiv10210.setDataRgstMthdClCd(autoExplrConsItmPrtyYn.equals("Y")
						? DATA_RGST_MTHD_CL_CD.SYSTEM
						: DATA_RGST_MTHD_CL_CD.USER_AND_SYSTEM);
			}
			tran.execute(QID.UPDATE_OIV10210__BY_equipId_equipConsItmId, oiv10210);
		}

		// 삭제
		list = family.getChildren("210", Status.deleted);
		for (Object obj : list) {
			OIV10210 oiv10210 = (OIV10210) obj;
			if (oiv10210.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.SYSTEM)) {
				// 시스템이 등록('10') 하였으면 삭제
				tran.execute(QID.DELETE_OIV10210__BY_equipId_equipConsItmId, oiv10210);
			} else if (oiv10210.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.USER_AND_SYSTEM)) {
				// 시스템+운용자('11') 이면 사용자('01') 로 변경
				oiv10210.setDataRgstMthdClCd(DATA_RGST_MTHD_CL_CD.USER);
				tran.execute(QID.UPDATE_OIV10210__BY_equipId_equipConsItmId, oiv10210);
			}
		}
	}

	/**
	 * 포트에 대한 DB 처리 작업을 수행한다.
	 *
	 * @param tran
	 * @param equipId
	 * @param family
	 * @throws Exception
	 */
	private void processPort(DbTrans tran, String equipId,
			String autoExplrConsItmPrtyYn, FamilyVo<DetectedNeVo, Object> family)
			throws Exception {

		List<?> list;

		// 수집한 포트
		list = check400(tran, family);
		if (list.size() > 0) {
			tran.execute(QID.DELETE_OIV10223__BY_equipId, equipId);
			tran.execute(QID.INSERT_OIV10223, list);
		}

		/// 신규 (운용자 등록이 없으므로 REG_MTHD_CD = '10')
		list = family.getChildren("400", Status.added);
		if (list.size() > 0) {
			for (Object o : list) {
				((OIV10400) o).setAuditDtm(auditDtm);
			}
			tran.execute(QID.INSERT_OIV10400, list);
		}

		// 변경
		list = family.getChildren("400", Status.changed);
		for (Object obj : list) {
			OIV10400 oiv10400 = (OIV10400) obj;
			if (oiv10400.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.USER)) {
				// 화면에서 우선 등록 (자동탐색구성품우선여부 에 따라 코드값 적용)
				oiv10400.setDataRgstMthdClCd(autoExplrConsItmPrtyYn.equals("Y")
						? DATA_RGST_MTHD_CL_CD.SYSTEM
						: DATA_RGST_MTHD_CL_CD.USER_AND_SYSTEM);
			}
			oiv10400.setAuditDtm(auditDtm);
			tran.execute(QID.UPDATE_OIV10400__BY_equipId_equipPortId, oiv10400);
		}

		// 삭제
		list = family.getChildren("400", Status.deleted);
		for (Object obj : list) {
			OIV10400 oiv10400 = (OIV10400) obj;
			if (oiv10400.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.SYSTEM)) {
				// 시스템이 등록('10') 하였으면 삭제
				tran.execute(QID.DELETE_OIV10400__BY_equipId_equipPortId, oiv10400);
			} else if (oiv10400.getDataRgstMthdClCd().equals(DATA_RGST_MTHD_CL_CD.USER_AND_SYSTEM)) {
				// 시스템+운용자('11') 이면 사용자('01') 로 변경
				oiv10400.setDataRgstMthdClCd(DATA_RGST_MTHD_CL_CD.USER);
				oiv10400.setAuditDtm(auditDtm);
				tran.execute(QID.UPDATE_OIV10400__BY_equipId_equipPortId, oiv10400);
			}
		}

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("clctDtm", String.valueOf(FxApi.getDate()));
		para.put("equipId", equipId);
		para.put("auditDtm", auditDtm);

		tran.execute(QID.MERGE_OIV10413__BY_equipId, para);
		tran.execute(QID.DELETE_OIV10413__BY_equipId, para);

	}

	/**
	 * 구성품별 추가해야할 포트가 있으면 포트목록에 추가한다.
	 *
	 * @param tran
	 * @param family
	 * @throws Exception
	 */
	private void setAutoPort(DbTrans tran, FamilyVo<DetectedNeVo, Object> family) throws Exception {

		String portDesc;
		OIV10400 o400;
		OIV10209 o209;
		OIV10210 o210;
		List<OIV10205> o10205List;

		// 새롭게 추가된 카드에 대해서만 포트를 추가한다.
		List<?> o210List = family.getChildren("210", null);
		// List<?> o210List = family.getChildren("210", Status.added);

		if (o210List.size() <= 0) {
			return;
		}

		for (Object o : o210List) {
			o210 = (OIV10210) o;
			o209 = o209Map.get(o210.getEquipConsItmDesc());

			// 장비구성품기준에서 포트를 생성하도록 되어 있다면
			if (o209 != null && "y".equalsIgnoreCase(o209.getPortCreYn())) {


				// 생성할 포트 목록을 가져와 생성한다.
				o10205List = o205Map.get(o209.getEquipConsItmCd());

				if (o10205List != null) {
					for (OIV10205 o205 : o10205List) {
						portDesc = o205.getPortNm();
						portDesc = portDesc.replaceAll("%slot%", o210.getEquipConsItmAlsNm());

						Logger.logger.debug("Added portDesc [{}]", portDesc);
						o400 = (OIV10400) family.getChild("400", portDesc);


						if (o400 == null) {
							DetectedPortVo vo = new DetectedPortVo();
							vo.setPortDescr(portDesc);
							vo.setPortAlsNm(o210.getEquipConsItmNm()+"-"+ o205.getPortNm());
							vo.setPortSpeedCd(o205.getPortSpeedCd());
							vo.setPortState("M");
							vo.setPortType(o205.getPortTypCd());
							vo.setCardNm(o210.getEquipConsItmNm());
							o400 = vo.makeOIV10400(o210.getEquipId(), o210, getO400NewId(tran));
							family.addDetectedChild("400", o400);
						}
					}
				}
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void init209Map(DbTrans tran, DetectedNeVo ne) throws Exception {

		o209Map = new HashMap<String, OIV10209>();
		Map<String, Object> para = new HashMap<String, Object>();

		para.put("equipMdlCd", ne.getEquipMdlCd());

		// 1. 장비구성품기준을 가져온다. ( OIV10209 )
		List<OIV10200> mdlList = tran.selectQid2Res(QID.SELECT_OIV10200__By_equipMdlCd, para);
		if (mdlList.size() == 0) {
			throw new Exception("EQUIP-MODEL " + ne.getEquipMdlCd() + " Not Found");
		}
		OIV10200 mdl = mdlList.get(0);

		List<OIV10209> list = tran.selectQid2Res(QID.SELECT_OIV10209__By_equipMdlCd, para);
		for (OIV10209 e : list) {
			o209Map.put(e.getConsItmMdlNm(), e);
		}

		OIV10209 e;
		for (DetectedCardVo card : ne.getCardList()) {

			e = o209Map.get(card.getCardModel());
			if (e == null) {

				e = new OIV10209();
				e.setConsItmMdlNm(card.getCardModel());
				e.setEquipConsItmCd(tran.selectQidStr(QID.SELECT_OIV10209_NEW_ID, null));
				e.setEquipMdlCd(ne.getEquipMdlCd());
				e.setEquipConsItmTypCd(AdamsCfg.EQUIP_CONS_ITM_TYP_CD.Module.getCode() + "");
				e.setConsItmTypCd(AdamsCfg.CONS_ITM_TYP_CD.CARD);
				e.setEquipMfactCd(mdl.getEquipMfactCd());
				e.setPortCnt(card.getPortCnt());
				e.setStrdVerInfo(card.getVer());

				tran.execute(QID.INSERT_OIV10209, e);
				o209Map.put(e.getConsItmMdlNm(), e);
			}
		}

		tran.commit();

	}

	// /**
	// * 자동으로 추가되어야할 카드, 포트 넣기
	// *
	// * @param family
	// */
	// private void setAutoAddConf(FamilyVo<DetectedNeVo, Object> family) {
	// List<OIV28202> o282List =
	// o28202Map.get(family.getParent().getEquipMdlCd());
	// if (o282List != null) {
	// String name;
	// OIV10210 o210;
	// OIV10400 o400;
	//
	// for (OIV28202 o : o282List) {
	//
	// if (o.getClCharStrVal() == null) {
	// name = o.getScardDesc();
	// } else {
	// name = o.getShlfDesc() + o.getClCharStrVal() + o.getScardDesc();
	// }
	//
	// o210 = (OIV10210) family.getChild("210", name);
	// if (o210 == null) {
	// // TODO
	// System.out.println("Not Found SLOT : " + name);
	// } else {
	// System.out.println("Found SLOT : " + name);
	// }
	//
	// for (int portNo = 1; portNo <= o.getPortCnt(); portNo++) {
	// name = o.getScardDesc() + o.getClCharStrVal() +
	// o.getScardDescPttnTypVal().replaceFirst("#", portNo + "");
	// o400 = (OIV10400) family.getChild("400", name);
	// if (o400 == null) {
	// // TODO
	// System.out.println("Not Found PORT : " + name);
	// } else {
	// System.out.println("Found PORT : " + name);
	// }
	// }
	// }
	// }
	// }

}

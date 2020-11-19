package com.lagoon.tmn.teams.co.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import subkjh.bas.co.log.Logger;
import subkjh.bas.co.utils.ObjectUtil;

import com.google.gson.Gson;
import com.lagoon.tmn.mig.MigTeamsDcnDao;
import com.lagoon.tmn.teams.co.dao.AdamsDcnDao;
import com.lagoon.tmn.teams.co.dao.AdamsOMN25101Dao;
import com.lagoon.tmn.teams.co.dao.AdamsOMN25112Dao;
import com.lagoon.tmn.teams.co.dao.AdamsOMN33811Dao;
import com.lagoon.tmn.teams.co.dao.AdamsOMN33812Dao;
import com.lagoon.tmn.teams.co.dao.AdamsTeamsConfDao;
import com.lagoon.tmn.teams.co.dao.Opn3000DailyDao;
import com.lagoon.tmn.teams.co.dao.UpdateNeDao;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.fxms.dbo.OCL28011;
import com.lagoon.tmn.teams.fxms.dbo.OCL28012;
import com.lagoon.tmn.teams.fxms.dbo.OCL28101;
import com.lagoon.tmn.teams.fxms.dbo.OCL28102;
import com.lagoon.tmn.teams.fxms.dbo.OCL28103;
import com.lagoon.tmn.teams.fxms.dbo.OCL28104;
import com.lagoon.tmn.teams.fxms.dbo.OCL28105;
import com.lagoon.tmn.teams.fxms.dbo.OCL28106;
import com.lagoon.tmn.teams.fxms.dbo.OCL28201;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221Ext;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;
import com.lagoon.tmn.teams.gw.vendor.ciena.dao.AdamsTdmDao;
import com.lagoon.tmn.teams.gw.vendor.woorinet.vo.Opn3000Vo;

import fxms.bas.fxo.FxCfg;
import fxms.module.restapi.CommHandler;
import fxms.module.restapi.vo.SessionVo;

public class DataHandler extends CommHandler {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		DetectedNeVo ne = new DetectedNeVo();
		ne.setEquipId("aaa");
		DetectedPortVo port = new DetectedPortVo();
		port.setPortDescr("TEST");
		ne.getPortList().add(port);
		Gson gson = new Gson();
		String s = gson.toJson(ne);

		DetectedPortVo p1;
		DetectedNeVo vo = new DetectedNeVo();
		Map map = gson.fromJson(s, HashMap.class);

		List<Map> portMapList = (List<Map>) map.remove("portList");

		ObjectUtil.toObject(map, vo);

		for (Map e : portMapList) {
			p1 = new DetectedPortVo();
			ObjectUtil.toObject(e, p1);
			vo.getPortList().add(p1);
		}

		System.out.println("map:" + map);
		System.out.println(vo.getEquipId());
		System.out.println(vo.getPortList().get(0));
	}

	/**
	 * EMS & 장비 관계 기록
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object addOiv10106(SessionVo session, Map<String, Object> parameters) throws Exception {

		String emsId = getString(parameters, "emsId");
		String equipId = getString(parameters, "equipId");
		String emsInrEquipKeyVal = getString(parameters, "emsInrEquipKeyVal");

		return new AdamsDcnDao().insertOIV10106(emsId, equipId, emsInrEquipKeyVal);
	}

	/**
	 * 새로운 전송망장비메시지내역(OMN33812)을 추가하고 추가된 내용을 제공한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object addOmn33812(SessionVo session, Map<String, Object> parameters) throws Exception {

		String equipMdlCd = getString(parameters, "equipMdlCd");
		String reason = getString(parameters, "reason");
		String dablGrCd = getString(parameters, "dablGrCd");

		return new AdamsOMN33812Dao().insertOMN33812(equipMdlCd, reason, dablGrCd);
	}

	/**
	 * OPN-3000 일일 점검 내역을 기록한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object addOpn3000DailyReport(SessionVo session, Map<String, Object> parameters) throws Exception {

		Opn3000Vo vo = new Opn3000Vo();

		vo.setEquipId(this.getString(parameters, "equipId"));
		vo.setOiv10221List(convert((List<Map<String, Object>>) parameters.get("list-OIV10221"), OIV10221Ext.class));
		vo.setOcl28011List(convert((List<Map<String, Object>>) parameters.get("list-OCL28011"), OCL28011.class));
		vo.setOcl28012List(convert((List<Map<String, Object>>) parameters.get("list-OCL28012"), OCL28012.class));
		vo.setOcl28101List(convert((List<Map<String, Object>>) parameters.get("list-OCL28101"), OCL28101.class));
		vo.setOcl28102List(convert((List<Map<String, Object>>) parameters.get("list-OCL28102"), OCL28102.class));
		vo.setOcl28103List(convert((List<Map<String, Object>>) parameters.get("list-OCL28103"), OCL28103.class));
		vo.setOcl28104List(convert((List<Map<String, Object>>) parameters.get("list-OCL28104"), OCL28104.class));
		vo.setOcl28105List(convert((List<Map<String, Object>>) parameters.get("list-OCL28105"), OCL28105.class));
		vo.setOcl28106List(convert((List<Map<String, Object>>) parameters.get("list-OCL28106"), OCL28106.class));
		vo.setOcl28201List(convert((List<Map<String, Object>>) parameters.get("list-OCL28201"), OCL28201.class));

		new Opn3000DailyDao().insert(vo);

		return parameters;
	}

	private <T> List<T> convert(List<Map<String, Object>> mapList, Class<T> classOfT) throws Exception {
		List<T> list = new ArrayList<T>();
		T obj;

		for (Map<String, Object> map : mapList) {
			obj = classOfT.newInstance();
			ObjectUtil.toObject(map, obj);
			list.add(obj);
		}

		return list;
	}

	/**
	 * 수집한 성능(광레벨, 온도 내역을 기록한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object addPs(SessionVo session, Map<String, Object> parameters) throws Exception {

		List<Map<String, Object>> list = (List<Map<String, Object>>) parameters.get("value-list");
		PsVo ps;
		List<PsVo> tempList = new ArrayList<PsVo>();
		List<PsVo> otpList = new ArrayList<PsVo>();

		for (Map<String, Object> map : list) {
			ps = new PsVo();
			ObjectUtil.toObject(map, ps);
			if (PsVo.PSCODE_TEMPERRATURE.equals(ps.getPsCode())) {
				tempList.add(ps);
			} else if (ps.getPsCode().startsWith(PsVo.PSCODE_OPT_LEVEL)) {
				otpList.add(ps);
			}
		}

		// 기록할 항목을 옵션으로 가져온다.(TODO 추후 불필요시 삭제 필요 by.lagoon)
		String psRecordType = FxCfg.getCfg().getString("PS-RECORD-TYPE",  "tmpr,oplvl");
		if (otpList.size() > 0 && psRecordType.indexOf("oplvl") > -1) {
			// 광레벨 기록
			new AdamsOMN25101Dao().insertOMN25102(otpList);
		}

		if (tempList.size() > 0 && psRecordType.indexOf("tmpr") > -1) {
			// 온도 기록
			new AdamsOMN25112Dao().insertOMN25112(tempList);
		}

		Map<String, Object> ret = new HashMap<String, Object>();
		ret.put(PsVo.PSCODE_TEMPERRATURE, tempList.size());
		return ret;
	}

	/**
	 * 수집부에서 처리할 DCN 정보를 제공한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object getDcnList(SessionVo session, Map<String, Object> parameters) throws Exception {

		List<IDcn> ret = new ArrayList<IDcn>();

		AdamsDcnDao dao = new AdamsDcnDao();

		List<EmsDcn> list = dao.selectEmsDcnList(parameters);
		if (list != null) {
			ret.addAll(list);
		}

		List<EquipDcn> list2 = dao.selectEquipDcnList(parameters);
		if (list2 != null) {
			for (EquipDcn dcn : list2) {
				if (dcn.getEquipIpAddr() != null) {
					ret.add(dcn);
				}
			}
		}

		Logger.logger.info(Logger.makeString("getDcnList", ret.size()));

		return ret;
	}

	/**
	 * 수집부에서 장애 이벤트를 수집할 장비 목록을 제공한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object getEquipList(SessionVo session, Map<String, Object> parameters) throws Exception {
		return new AdamsDcnDao().selectEquipList(parameters);
	}

	/**
	 * 장비모델의 전송망장비메시지내역(OMN33812)을 조회한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object getOmn33812(SessionVo session, Map<String, Object> parameters) throws Exception {
		List<String> list = (List<String>) parameters.get("equipMdlCd");
		String equipMfactCd = getString(parameters, "equipMfactCd", null);
		boolean isSyslogRcv = false;
		if (parameters.containsKey("isSyslogRcv")) {
			boolean b = (boolean) parameters.get("isSyslogRcv");
			isSyslogRcv = b; 
		}
		return new AdamsOMN33812Dao().selectOMN33812(equipMfactCd, list == null ? null : list.toArray(new String[list.size()]), isSyslogRcv);
	}

	public Object getRtList(SessionVo session, Map<String, Object> parameters) throws Exception {
		List<IDcn> ret = new ArrayList<IDcn>();

		AdamsDcnDao dao = new AdamsDcnDao();

		List<EquipDcn> list2 = dao.selectEquipRtList(parameters);
		if (list2 != null) {
			ret.addAll(list2);
		}
		return ret;
	}

	/**
	 * 저속급 TDM 목록을 조회한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object getTdmList(SessionVo session, Map<String, Object> parameters) throws Exception {
		return new AdamsTdmDao().selectTdmDcn();
	}

	/**
	 * 구성정보 수집 내역을 수정한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object updateDetectedNe(SessionVo session, Map<String, Object> parameters) throws Exception {

		DetectedNeVo vo = new DetectedNeVo();
		Map<String, Object> neMap = (Map<String, Object>) parameters.get("ne");
		List<Map> slotMapList = (List<Map>) neMap.remove("cardList");
		List<Map> portMapList = (List<Map>) neMap.remove("portList");

		ObjectUtil.toObject(neMap, vo);

		DetectedPortVo p1;
		for (Map e : portMapList) {
			p1 = new DetectedPortVo();
			ObjectUtil.toObject(e, p1);
			vo.getPortList().add(p1);
		}

		DetectedCardVo s1;
		for (Map e : slotMapList) {
			s1 = new DetectedCardVo();
			ObjectUtil.toObject(e, s1);
			vo.getCardList().add(s1);
		}

		new UpdateNeDao(vo);

		return parameters;
	}

	/**
	 * EMS IP주소 사용 인덱스를 설정한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object updateEmsIpIndex(SessionVo session, Map<String, Object> parameters) throws Exception {

		int useIpAddrIdxVal = getInt(parameters, "useIpAddrIdxVal");
		String emsId = getString(parameters, "emsId");

		new AdamsDcnDao().updateOIV10105(emsId, useIpAddrIdxVal);

		return parameters;
	}

	/**
	 * EMS 수집 상태를 수정한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object updateEmsStatus(SessionVo session, Map<String, Object> parameters) throws Exception {

		String emsId = (String) parameters.get("emsId");
		String connStVal = (String) parameters.get("connStVal");

		new AdamsTeamsConfDao().updateEmsStatus(emsId, connStVal);

		return parameters;
	}

	/**
	 * 장비 수집 상태를 수정한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object updateNeStatus(SessionVo session, Map<String, Object> parameters) throws Exception {

		String equipId = (String) parameters.get("equipId");
		String connStVal = (String) parameters.get("connStVal");

		new AdamsTeamsConfDao().updateNeStatus(equipId, connStVal);

		return parameters;
	}

	/**
	 * 전송장비CRS내역를 등록한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public Object updateOiv28160(SessionVo session, Map<String, Object> parameters) throws Exception {

		String equipId = this.getString(parameters, "equipId");
		List<Map<String, Object>> list = (List<Map<String, Object>>) parameters.get("list");
		List<OIV28160> oList = new ArrayList<OIV28160>();
		OIV28160 o;
		for (Map<String, Object> map : list) {
			o = new OIV28160();
			ObjectUtil.toObject(map, o);
			oList.add(o);
		}

		try {
			new AdamsTdmDao().setOIV28160(equipId, oList);
			return new HashMap<String, Object>();
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	/**
	 * 현재 장비장애를 가져온다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object selectOmn33811(SessionVo session, Map<String, Object> parameters) throws Exception {

		return new AdamsOMN33811Dao().selectOMN33811Ext(parameters);
	}
	
	/**
	 * 필요 데이터를 생성 합니다.
	 * 
	 * <pre>
	 * 수집장비모델맵핑테이블(OIV10213)
	 * 수집장비맵핑테이블(OIV10107)
	 * </pre>
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object mergeData(SessionVo session, Map<String, Object> parameters) throws Exception {
		new AdamsDcnDao().mergeFromOIV10200AndOIV10100(parameters);
		return new ResultMap();
	}

	/**
	 * (자동) 수집서버 설정 (OIV10104, OIV10105)
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object mergeFromClctSvr(SessionVo session, Map<String, Object> parameters) throws Exception {
		new MigTeamsDcnDao().mergeFromClctSvr(parameters);
		return new ResultMap();
	}
}

/**
 * testRetObj 함수 사용시 파싱을 위한 깡통 결과값을 리턴 해준다. 
 * 
 * @author lagoon
 *
 */
@SuppressWarnings("serial")
class ResultMap extends HashMap<String, Object> {
	ResultMap() {
		this.put("result", "OK");
	}
}

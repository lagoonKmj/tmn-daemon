package com.lagoon.tmn.teams.gw.co;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OIV10106;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;
import com.lagoon.tmn.teams.fxms.dbo.OMN33812;
import com.lagoon.tmn.teams.fxms.dbo.SyslogPatternAdams;
import com.lagoon.tmn.teams.gw.vendor.woorinet.vo.Opn3000Vo;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import subkjh.bas.co.utils.ObjectUtil;
import fxms.bas.api.FxApi;
import fxms.bas.co.utils.FxmsClient;
import fxms.bas.fxo.FxCfg;
import fxms.nms.co.syslog.vo.SyslogPattern;

public class GwApi extends FxApi {

	private static GwApi api = null;
	private static Object lockObj = new Object();

	/** 담당 제조사 코드 */
	private static String equipMfactCd;
	/** 장비 모델 코드 */
	private static String equipMdlCd[];
	/** EMS종류 코드 */
	private static String emsTypVal[];

	public static GwApi getApi() {

		if (api == null) {
			synchronized (lockObj) {
				if (api == null) {
					api = (GwApi) makeApi(GwApi.class);
				}
			}
		}

		return api;
	}

	public static void setEmsTypVal(String[] emsTypVal) {
		GwApi.emsTypVal = emsTypVal;
	}

	public static void setEquipMdlCd(String[] equipMdlCd) {
		GwApi.equipMdlCd = equipMdlCd;
	}

	public static void setEquipMfactCd(String equipMfactCd) {
		GwApi.equipMfactCd = equipMfactCd;
	}

	/** 모델별 이벤트 정의 내용 */
	private Map<String, List<OMN33812>> eventMap = new HashMap<String, List<OMN33812>>();
	/** DCN 현재 상태 보관 */
	private Map<String, String> dcnStateMap = new HashMap<String, String>();
	/** key : EMS-ID */
	private Map<String, List<AdamsEquipVo>> emsEquipMap = new HashMap<String, List<AdamsEquipVo>>();
	private Object equipLockObj = new Object();
	private boolean isToReload = true;

	private FxmsClient client;
	private FxmsClient clientTeams;

	/** 현재 장애 */
	private Map<String, OMN33811Ext> alarmMap = new HashMap<String, OMN33811Ext>();

	/** 분석서버 미처리 장애 **/
	private List<TrmsNetEventVo> notSendEventList = Collections.synchronizedList(new ArrayList<TrmsNetEventVo>());

	private List<Long> notSendClearAlarmList = Collections.synchronizedList(new ArrayList<Long>());

	/** key : dcnKey, key2 : alarmKey */
	private Map<String, Map<String, OMN33811Ext>> dcnAlarmList = new HashMap<String, Map<String, OMN33811Ext>>();

	/** EMS가 설정되어 있지 않는 장비 지정 EMS ID */
	private final String UNDEFINE_EMS_ID = "NOTDEF";

	/**
	 * 장비의 담당 EMS 정보를 설정한다.
	 * 
	 * @param emsId
	 * @param equipId
	 * @param emsInrEquipKeyVal
	 * @return
	 */
	public OIV10106 addOIV10106(String emsId, String equipId, String emsInrEquipKeyVal) {
		try {
			Map<String, Object> para = new HashMap<String, Object>();

			para.put("emsId", emsId);
			para.put("equipId", equipId);
			para.put("emsInrEquipKeyVal", emsInrEquipKeyVal);

			Map<String, Object> ret = client.testRetObj("datas", "add-oiv10106", para);
			OIV10106 o = new OIV10106();
			ObjectUtil.toObject(ret, o);
			return o;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * OPN-3000 일일점검 내역 기록
	 * 
	 * @param vo
	 */
	public void addOpn3000DailyReport(Opn3000Vo vo) {
		try {
			Map<String, Object> para = new HashMap<String, Object>();

			para.put("equipId", vo.getEquipId());
			para.put("list-OIV10221", vo.getOiv10221List());
			para.put("list-OCL28011", vo.getOcl28011List());
			para.put("list-OCL28012", vo.getOcl28012List());
			para.put("list-OCL28101", vo.getOcl28101List());
			para.put("list-OCL28102", vo.getOcl28102List());
			para.put("list-OCL28103", vo.getOcl28103List());
			para.put("list-OCL28104", vo.getOcl28104List());
			para.put("list-OCL28105", vo.getOcl28105List());
			para.put("list-OCL28106", vo.getOcl28106List());
			para.put("list-OCL28201", vo.getOcl28201List());

			client.testRetObj("datas", "add-opn3000-daily-report", para);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	public OMN33811Ext callFireEvent(TrmsNetEventVo trmsNetEventVo) {
		try {

			Map<String, Object> para = new HashMap<String, Object>();
			para.put("event", trmsNetEventVo);

			Map<String, Object> map = clientTeams.testRetObj("teams", "fire-event", para);
			if (map == null) {
				Logger.logger.error("TEAMS 분석(APP) API 가 초기화 되지 않은 상태 입니다.");
				return null;
			}

			OMN33811Ext ret = new OMN33811Ext();
			ObjectUtil.toObject(map, ret);
			ret.setDcn(trmsNetEventVo.getDcn());

			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 분석서버에서 처리한 결과를 어떻게 할 것이지를 결정하는 함수
	 * 
	 * @param alarm
	 */
	public void checkOMN33811(OMN33811Ext alarm) {

		Logger.logger.info("dcnAlarmList size : {}, dcnAlarmList : {}, dcnKey : {}", dcnAlarmList.size(), dcnAlarmList, alarm
				.getDcn().getDcnKey());

		Map<String, OMN33811Ext> map = dcnAlarmList.get(alarm.getDcn().getDcnKey());

		if (alarm.isClear()) {
			// 해제된 장애
			if (map != null) {
				map.remove(alarm.getAlarmKey());
			}
			alarmMap.remove(alarm.getAlarmKey());

		} else {
			if (map == null) {
				map = new HashMap<String, OMN33811Ext>();
				dcnAlarmList.put(alarm.getDcn().getDcnKey(), map);
			}
			// 발생된 장애
			map.put(alarm.getAlarmKey(), alarm);
			alarmMap.put(alarm.getAlarmKey(), alarm);
		}
	}

	/**
	 * 장애번호를 이용하여 장애를 해제한다.
	 * 
	 * @param omn33811Ext
	 * @return
	 */
	public OMN33811Ext clearAlarm(OMN33811Ext omn33811Ext) {

		Long trmsEquipDablNum = omn33811Ext.getTrmsEquipDablNum();

		try {
			OMN33811Ext ret = callClearAlarm(trmsEquipDablNum);
			if (ret != null) {
				checkOMN33811(omn33811Ext);
				removeNotSend(trmsEquipDablNum);
			} else {
				notSendClearAlarmList.add(trmsEquipDablNum);
			}

			return ret;
		} catch (Exception e) {
			if (notSendClearAlarmList.contains(trmsEquipDablNum) == false) {
				notSendClearAlarmList.add(trmsEquipDablNum);
				Logger.logger.error(e);
			}
		}

		return null;
	}

	/**
	 * EMS내 IP를 이용하여 장비를 찾는다.
	 * 
	 * @param dcn
	 * @param tid
	 * @return
	 */
	public AdamsEquipVo findEquipByIp(EmsDcn dcn, String ip) {

		synchronized (equipLockObj) {

			if (isToReload) {
				loadEquip();
			}

			List<AdamsEquipVo> list;
			if (dcn == null) {
				list = emsEquipMap.get(UNDEFINE_EMS_ID);
			} else {
				list = emsEquipMap.get(dcn.getEmsId());
			}

			if (list != null) {
				for (AdamsEquipVo vo : list) {
					if (ip.equals(vo.getEquipIpAddr())) {
						return vo;
					}
				}
			}

			return null;
		}
	}

	/**
	 * EMS내 TID를 이용하여 장비를 찾는다.
	 * 
	 * @param dcn
	 * @param tid
	 * @return
	 */
	public AdamsEquipVo findEquipByTid(EmsDcn dcn, String tid) {

		synchronized (equipLockObj) {

			if (isToReload) {
				loadEquip();
			}

			List<AdamsEquipVo> list;
			if (dcn == null) {
				list = emsEquipMap.get(UNDEFINE_EMS_ID);
			} else {
				list = emsEquipMap.get(dcn.getEmsId());
			}

			if (list != null) {
				for (AdamsEquipVo vo : list) {
					if (tid.equals(vo.getEquipTidVal())) {
						return vo;
					}
				}
			}

			return null;

		}
	}

	public void fireAlarm(DetectedAlarmVo data) throws Exception {

		AdamsEquipVo equip = getEquip(data);

		if (equip == null) {
			Logger.logger.fail("Not Found MO : {}", data.toString());
			return;
		}

		data.setEquip(equip);

		TrmsNetEventVo event = makeTrmsNetEvent(data.getDcn(), data.getEquip(), data);

		if (data.getEventType() == TrmsNetEventVo.OCCUR_EVENT_ALARM) {
			// 받은 장애 중복 여부 확인
			if (isDup(data)) {
				Logger.logger.debug("중복 이벤트 : {}", data);
				return;
			}

			if (event.isDablAplyYn() == false) {
				Logger.logger.debug("장애 적용 하지 않음 : {}", data);
				return;
			}
		}

		// 받은 장애 내용을 분석 서버에 보냄
		OMN33811Ext ret = callFireEvent(event);
		if (ret != null) {
			ret.setDcn(data.getDcn());
			checkOMN33811(ret);
			removeNotSend(event);
		} else {
			notSendEventList.add(event);
		}

	}

	/**
	 * DCN 목록을 조회한다.
	 * 
	 * @param includeRt
	 *            RT 장비 포함 여부
	 * @return
	 */
	public List<IDcn> getDcnList(boolean includeRt) {

		Map<String, Object> para = getPara();

		List<IDcn> list;
		try {
			list = callGetDcnList(para, includeRt);
		} catch (Exception e) {
			Logger.logger.error(e);
			list = null;
		}

		FxCfg.getCfg().setPara("DCN-SIZE", list == null ? 0 : list.size());

		StringBuffer sb = new StringBuffer();
		sb.append(Logger.makeString("DCN-SIZE", list == null ? "error" : list.size()));
		sb.append(getParaString());
		Logger.logger.info(sb.toString());

		return list;
	}

	/**
	 * 입력된 모델의 EquipDcn을 조회한다.
	 * 
	 * @param equipMdlCd
	 * @return
	 */
	public List<IDcn> getDcnList(String equipMdlCd[]) {

		Map<String, Object> para = new HashMap<String, Object>();
		para.put("equipMdlCd", equipMdlCd);
		para.put("clctSvrIpAddr", FxCfg.getCfg().getString("CLCT_SVR_IP_ADDR", null));

		List<IDcn> list;
		try {
			list = callGetDcnList(para, false);
		} catch (Exception e) {
			Logger.logger.error(e);
			list = null;
		}

		FxCfg.getCfg().setPara("DCN-SIZE", list == null ? 0 : list.size());

		StringBuffer sb = new StringBuffer();
		sb.append(Logger.makeString("DCN-SIZE", list == null ? "error" : list.size()));
		sb.append(getParaString());
		Logger.logger.info(sb.toString());

		return list;
	}

	/**
	 * 수집한 경보의 해당 장비를 조회한다.<br>
	 * 기본은 TID를 이용하여 조회하며, 다른 경우는 Override한다.
	 * 
	 * @param data
	 * @return
	 */
	public AdamsEquipVo getEquip(DetectedAlarmVo data) {
		if (data.getDcn() instanceof EmsDcn) {
			return findEquipByTid((EmsDcn) data.getDcn(), data.getEquipTidVal());
		} else {
			return (EquipDcn) data.getDcn();
		}
	}

	public String[] getEquipMdlCd() {
		return equipMdlCd;
	}

	public String getEquipMfactCd() {
		return equipMfactCd;
	}

	public Long[] getNotSendClearAlarmList() {
		return notSendClearAlarmList.toArray(new Long[notSendClearAlarmList.size()]);
	}

	public TrmsNetEventVo[] getNotSendEventList() {
		return notSendEventList.toArray(new TrmsNetEventVo[notSendEventList.size()]);
	}

	@Override
	public String getState(LOG_LEVEL level) {
		return "";
	}

	/**
	 * 저속급 TDM 장비 목록을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<EquipDcn> getTdmList() throws Exception {
		return callTdmList();
	}

	public void insertPs(List<PsVo> psList) {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("value-list", psList);
			client.testRetObj("datas", "add-ps", para);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	/**
	 * 중복 여부를 확인하다.
	 * 
	 * @param vo
	 * @return
	 */
	public boolean isDup(DetectedAlarmVo vo) {
		//
		String alarmKey = vo.getFullMsg(); // vo를 이용하여 alarmKey를 만든다.
		return alarmMap.get(alarmKey) != null;
	}

	/**
	 * 현재 장애를 가져온다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public void loadCurAlarm() throws Exception {

		Map<String, Object> para = new HashMap<String, Object>();
		String[] equipMdCds = getEquipMdlCd();
		// String[] equipMdCds = {"00000036","00000037","00000038","00000040"};

		String equipMdlCd = new String();

		if (equipMdCds.length != 0) {
			for (String equip : equipMdCds) {
				equipMdlCd = equipMdlCd.concat(",'" + equip + "'");
			}
			equipMdlCd = equipMdlCd.substring(2);
			equipMdlCd = equipMdlCd.substring(0, equipMdlCd.length() - 1);

			para.put("equipMdlCd", equipMdlCd);
		} else {
			Logger.logger.info(equipMdlCd);
		}

		List<Map<String, Object>> list = client.testRetList("datas", "select-omn33811", para);

		OMN33811Ext o;

		for (Map<String, Object> e : list) {
			o = new OMN33811Ext();
			ObjectUtil.toObject(e, o);
			alarmMap.put(o.getAlarmKey(), o);

			// DCN을 키를 가지는 현재 장애 객체를 초기화
			String dcnKey = "EQUIP:" + o.getEquipId();
			if (o.getEmsId() != null) {
				dcnKey = "EMS:" + o.getEmsId();
			}
			Map<String, OMN33811Ext> map = dcnAlarmList.get(dcnKey);
			if (map == null) {
				map = new HashMap<String, OMN33811Ext>();
				dcnAlarmList.put(dcnKey, map);
			}
			map.put(o.getAlarmKey(), o);
		}
	}

	/**
	 * 새로운 전송망이벤트 정보를 추가한다.
	 * 
	 * @param equipMdlCd
	 *            장비모델코드
	 * @param reason
	 *            발생원인
	 * @param dablGrCd
	 *            장애등급코드
	 * @return 추가된 전송망장비메시지내역(OMN33812)
	 * @throws Exception
	 */
	public OMN33812 makeNewOMN33812(String equipMdlCd, String reason, String dablGrCd) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();

			para.put("equipMdlCd", equipMdlCd);
			para.put("reason", reason);
			para.put("dablGrCd", dablGrCd);

			Map<String, Object> ret = clientTeams.testRetObj("teams", "add-omn33812", para);
			OMN33812 o = new OMN33812();
			ObjectUtil.toObject(ret, o);
			return o;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@Override
	public void reload() throws Exception {
		reloadOMN33812();
		loadCurAlarm();
//		mergeData(); NMS 코드 변경으로 인한 불필요(기존 팀스 005 사용 X)
	}

	public void removeNotSend(Long trmsEquipDablNum) {
		notSendClearAlarmList.remove(trmsEquipDablNum);
	}

	public void removeNotSend(TrmsNetEventVo event) {
		notSendEventList.remove(event);
	}

	/**
	 * DCN에서 가져온 장애목록으로 현재 장애를 동기화 한다.
	 * 
	 * @param dcn
	 * @param list
	 * @throws Exception
	 */
	public void syncAlarmCur(IDcn dcn, List<DetectedAlarmVo> list) throws Exception {

		Map<String, OMN33811Ext> tmpMap = new HashMap<String, OMN33811Ext>();
		Map<String, OMN33811Ext> map = dcnAlarmList.get(dcn.getDcnKey());
		if (map != null) {
			tmpMap.putAll(map);
		}
		OMN33811Ext cur;
		for (DetectedAlarmVo e : list) {
			cur = tmpMap.remove(e.getAlarmKey());
			if (cur == null) {
				// 현재 장애에 없으면 발생시켜줌.
				fireAlarm(e);
			}
		}
		// 현재장애목록에서 DCN으로부터 가져온 장애를 제외하고 남아 있으면 해제한다.
		for (OMN33811Ext e : tmpMap.values()) {
			e.setDcn(dcn);
			clearAlarm(e);
		}

	}

	/**
	 * DCN 상태를 수정한다.<br>
	 * 동일 이유이면 수정하지 않는다.
	 * 
	 * @param ems
	 * @param reason
	 * @param status
	 */
	public synchronized void updateDcnStatus(IDcn dcn, boolean isConn) {

		String prevReason = dcnStateMap.get(dcn.getDcnKey());

		String connStVal = isConn ? AdamsCfg.CONN_ST_VAL.CONNECTED : AdamsCfg.CONN_ST_VAL.DISCONNECTED;

		if (prevReason == null || prevReason.equals(connStVal) == false) {

			if (dcn instanceof EquipDcn) {
				try {
					callUpdateNeStatus(((EquipDcn) dcn).getEquipId(), connStVal);
				} catch (Exception e) {
					Logger.logger.error(e);
					return;
				}
			} else {
				try {
					callUpdateEmsStatus(((EmsDcn) dcn).getEmsId(), connStVal);
				} catch (Exception e) {
					Logger.logger.error(e);
					return;
				}
			}

			dcnStateMap.put(dcn.getDcnKey(), connStVal);
		}
	}

	public void updateDetectedNe(DetectedNeVo ne) {

		if (ne.isCollectOk()) {
			try {

				Map<String, Object> para = new HashMap<String, Object>();
				para.put("ne", ne);

				client.testRetObj("datas", "update-detected-ne", para);

				Logger.logger.info(Logger.makeString("EQUIP:" + ne.getEquipId(), "slot:" + ne.getCardList().size() + ",port:"
						+ ne.getPortList().size()));

			} catch (Exception e) {
				Logger.logger.error(e);
				Logger.logger.fail(Logger.makeString("EQUIP:" + ne.getEquipId(), "udpate fail"));
			}
		} else {
			Logger.logger.fail(Logger.makeString("EQUIP:" + ne.getEquipId(), ne.getErrmsg()));
			return;
		}
	}

	/**
	 * EMS IP주소 사용 인덱스를 설정한다.
	 * 
	 * @param emsId
	 * @param useIpAddrIdxVal
	 * @return
	 */
	public OIV10106 updateEmsIpIndex(String emsId, int useIpAddrIdxVal) {
		try {
			Map<String, Object> para = new HashMap<String, Object>();

			para.put("emsId", emsId);
			para.put("useIpAddrIdxVal", useIpAddrIdxVal);

			Map<String, Object> ret = client.testRetObj("datas", "update-ems-ip-index", para);
			OIV10106 o = new OIV10106();
			ObjectUtil.toObject(ret, o);
			return o;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 장비의 CRS 내역을 기록한다.
	 * 
	 * @param equipId
	 * @param list
	 * @throws Exception
	 */
	public void updateOiv28160(String equipId, List<OIV28160> list) throws Exception {
		callUpdateOiv28160(equipId, list);
	}

	@Override
	protected void initApi() throws Exception {

		client = new FxmsClient(FxCfg.getCfg().getString("APP_SERVER", "localhost"), 10006);
		client.login("AdamsService", "AdamsService");

		clientTeams = new FxmsClient(FxCfg.getCfg().getString("APP_SERVER", "localhost"), 10005);
		clientTeams.login("AdamsService", "AdamsService");
	}

	public OMN33811Ext callClearAlarm(long trmsEquipDablNum) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("trmsEquipDablNum", trmsEquipDablNum);

			Map<String, Object> map = clientTeams.testRetObj("teams", "clear-alarm", para);
			OMN33811Ext ret = new OMN33811Ext();
			ObjectUtil.toObject(map, ret);
			return ret;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private List<AdamsEquipVo> callEquipList(Map<String, Object> para) {
		try {

			Map<String, Integer> countMap = new HashMap<String, Integer>();
			Integer count;

			List<Map<String, Object>> list = client.testRetList("datas", "get-equip-list", para);
			List<AdamsEquipVo> ret = new ArrayList<AdamsEquipVo>();
			AdamsEquipVo item;
			for (Map<String, Object> map : list) {
				item = new AdamsEquipVo();
				ObjectUtil.toObject(map, item);
				ret.add(item);

				count = countMap.get(item.getEquipMdlNm());
				if (count == null) {
					countMap.put(item.getEquipMdlNm(), 1);
				} else {
					countMap.put(item.getEquipMdlNm(), count.intValue() + 1);

				}

			}

			StringBuffer sb = new StringBuffer();
			sb.append(Logger.makeString("EQUIP-SIZE", ret.size()));
			for (String key : countMap.keySet()) {
				sb.append(Logger.makeSubString(key, countMap.get(key)));
			}
			Logger.logger.info(sb.toString());

			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * EMS 종류를 기준으로 DCN을 조회한다.
	 * 
	 * @param dcnEmsTyp
	 * @return
	 */
	private List<IDcn> callGetDcnList(Map<String, Object> para, boolean includeRt) throws Exception {
			
			
		try {
			// (자동) 수집서버 설정 (OIV10104, OIV10105)
			client.testRetObj("datas", "merge-from-clct-svr", getPara());
			
			List<IDcn> ret = new ArrayList<IDcn>();
			Map<String, EquipDcn> cotMap = new HashMap<String, EquipDcn>();

			List<Map<String, Object>> list = client.testRetList("datas", "get-dcn-list", para);
			for (Map<String, Object> map : list) {
				if (map.get("emsIpAddr") != null) {
					EmsDcn item = new EmsDcn();
					ObjectUtil.toObject(map, item);
					ret.add(item);
				} else {
					EquipDcn item = new EquipDcn();
					ObjectUtil.toObject(map, item);
					ret.add(item);
					cotMap.put(item.getEquipId(), item);
				}
			}

			if (includeRt && cotMap.size() > 0) {
				list = client.testRetList("datas", "get-rt-list", para);
				for (Map<String, Object> map : list) {
					EquipDcn item = new EquipDcn();
					ObjectUtil.toObject(map, item);
					EquipDcn cot = cotMap.get(item.getCotEquipId());
					if (cot != null) {
						cot.addRt(item);
					}
				}
			}

			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	/**
	 * 장비모델의 전송망장비메시지내역(OMN33812)을 조회한다.
	 * 
	 * @param equipMfactCd
	 * @param equipMdlCd
	 * @return
	 * @throws Exception
	 */
	private List<OMN33812> callOmn33812(String equipMfactCd, String[] equipMdlCd) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();

			if (equipMfactCd != null) {
				para.put("equipMfactCd", equipMfactCd);
			}
			if (equipMdlCd != null) {
				para.put("equipMdlCd", equipMdlCd);
			}

			List<OMN33812> ret = new ArrayList<OMN33812>();
			List<Map<String, Object>> list = client.testRetList("datas", "get-omn33812", para);
			for (Map<String, Object> map : list) {
				OMN33812 item = new OMN33812();
				ObjectUtil.toObject(map, item);
				ret.add(item);
			}
			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private List<EquipDcn> callTdmList() throws Exception {
		try {

			Map<String, Object> para = new HashMap<String, Object>();
			List<EquipDcn> ret = new ArrayList<EquipDcn>();
			List<Map<String, Object>> list = client.testRetList("datas", "get-tdm-list", para);
			for (Map<String, Object> map : list) {
				EquipDcn item = new EquipDcn();
				ObjectUtil.toObject(map, item);
				ret.add(item);
			}
			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * EMS 상태를 수정한다.
	 * 
	 * @param emsId
	 * @param reason
	 * @param status
	 * @throws Exception
	 */
	private void callUpdateEmsStatus(String emsId, String connStVal) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("emsId", emsId);
			para.put("connStVal", connStVal);
			Map<String, Object> ret = client.testRetObj("datas", "update-ems-status", para);
			Logger.logger.info("ret={}", ret);
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}

	/**
	 * 장비 수집 상태를 수정한다.
	 * 
	 * @param equipId
	 * @param reason
	 * @param status
	 * @throws Exception
	 */
	private void callUpdateNeStatus(String equipId, String connStVal) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipId", equipId);
			para.put("connStVal", connStVal);
			Map<String, Object> ret = client.testRetObj("datas", "update-ne-status", para);
			Logger.logger.info("ret={}", ret);
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}

	private void callUpdateOiv28160(String equipId, List<OIV28160> list) throws Exception {
		try {
			Map<String, Object> para = new HashMap<String, Object>();
			para.put("equipId", equipId);
			para.put("list", list);
			client.testRetObj("datas", "update-oiv28160", para);

			Logger.logger.info(Logger.makeString("EQUIP:" + equipId, "TDM:" + list.size()));
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	/**
	 * 전송망장비메시지를 찾아 제공한다.<br>
	 * 등록되어 있지 않으면 추가 요청한다.
	 * 
	 * @param equipMdlCd
	 * @param reason
	 * @return
	 */
	private OMN33812 findOMN33812(String equipMdlCd, String reason, String dablGrCd) {

		if (eventMap.size() == 0) {
			try {
				reloadOMN33812();
			} catch (Exception e) {
				Logger.logger.error(e);
				return null;
			}
		}

		List<OMN33812> list = eventMap.get(equipMdlCd);

		if (list != null) {
			for (OMN33812 e : list) {
				if (e.getCmprCharStrVal().equals(reason)) {
					return e;
				}
			}
		}

		try {
			OMN33812 newOMN33812 = makeNewOMN33812(equipMdlCd, reason, dablGrCd);
			if (list == null) {
				list = new ArrayList<OMN33812>();
				eventMap.put(equipMdlCd, list);
			}
			list.add(newOMN33812);

			return newOMN33812;
		} catch (Exception e) {
			Logger.logger.error(e);
		}

		return null;
	}

	private Map<String, Object> getPara() {

		Map<String, Object> para = new HashMap<String, Object>();

		if (equipMfactCd != null) {
			para.put("equipMfactCd", equipMfactCd);
		}
		if (equipMdlCd != null) {
			para.put("equipMdlCd", equipMdlCd);
		}
		if (emsTypVal != null) {
			para.put("emsTypVal", emsTypVal);
		}

		para.put("clctSvrIpAddr", FxCfg.getCfg().getString("CLCT_SVR_IP_ADDR", null));

		return para;
	}

	private String getParaString() {

		StringBuffer sb = new StringBuffer();
		if (equipMfactCd != null) {
			sb.append(Logger.makeSubString("equipMfactCd", equipMfactCd));
		}
		if (equipMdlCd != null) {
			sb.append(Logger.makeSubString("equipMdlCd", Arrays.toString(equipMdlCd)));
		}
		if (emsTypVal != null) {
			sb.append(Logger.makeSubString("emsTypVal", Arrays.toString(emsTypVal)));
		}

		sb.append(Logger.makeSubString("cclctSvrIpAddr", FxCfg.getCfg().getString("CLCT_SVR_IP_ADDR", null)));

		return sb.toString();
	}

	private synchronized void loadEquip() {

		List<AdamsEquipVo> list;

		list = callEquipList(getPara());

		Logger.logger.info(Logger.makeString("EQUIP-SIZE", (list == null ? 0 : list.size())));

		this.isToReload = false;

		if (list == null) {
			return;
		}

		Map<String, List<AdamsEquipVo>> emsEquipMap = new HashMap<String, List<AdamsEquipVo>>();
		List<AdamsEquipVo> entry;
		String emsId;

		for (AdamsEquipVo vo : list) {
			emsId = vo.getEmsId();
			if (emsId == null) {
				emsId = UNDEFINE_EMS_ID;
			}

			entry = emsEquipMap.get(emsId);
			if (entry == null) {
				entry = new ArrayList<AdamsEquipVo>();
				emsEquipMap.put(emsId, entry);
			}
			entry.add(vo);
		}

		this.emsEquipMap = emsEquipMap;

	}

	/**
	 * 장비에서 발생된 경보내용으로 APP로 보낼 이벤트를 만든다.
	 * 
	 * @param ems
	 *            EMS
	 * @param equip
	 *            장비정보
	 * @param vo
	 *            받은 경보
	 * 
	 * @return
	 */
	private TrmsNetEventVo makeTrmsNetEvent(IDcn dcn, AdamsEquipVo equip, DetectedAlarmVo vo) {

		TrmsNetEventVo alarm = new TrmsNetEventVo();

		if (dcn instanceof EmsDcn) {
			alarm.setEmsId(((EmsDcn) dcn).getEmsId());
		}
		alarm.setDcn(dcn);

		alarm.setCardDescr(vo.getCardDescr());
		alarm.setPortDescr(vo.getPortDescr());
		alarm.setLocation(vo.getLocation());
		alarm.setReason(vo.getReason());
		alarm.setOccurTime(vo.getOccurHstime());
		alarm.setFullMsg(vo.getFullMsg());
		alarm.setDablGrCd(vo.getDablGrCd());
		alarm.setRecvTime(System.currentTimeMillis());
		alarm.setClearTime(vo.getClearHstime());

		alarm.setEquipMdlCd(equip.getEquipMdlCd());
		alarm.setTid(equip.getEquipTidVal());
		alarm.setEquipId(equip.getEquipId());
		alarm.setIpAddr(equip.getEquipIpAddr());
		alarm.setIgnoreOmn33812(vo.isIgnoreOmn33812());

		if (vo.getEventType() == TrmsNetEventVo.OCCUR_EVENT_ALARM) {
			// 시스로그,트랩 장애 는 OMN33812 조건 무시
			if (vo.isIgnoreOmn33812() == true) {
				alarm.setTrmsNetEquipMsgMgmtNum(-1);
				alarm.setDablAplyYn("Y");
			} else {
				OMN33812 o33812 = findOMN33812(equip.getEquipMdlCd(), vo.getReason(), vo.getDablGrCd());
				if (o33812 != null) {
					alarm.setTrmsNetEquipMsgMgmtNum(o33812.getTrmsNetEquipMsgMgmtNum());
					alarm.setDablAplyYn(o33812.isDablAplyYn());
				}
			}
		}
		return alarm;
	}

	private void reloadOMN33812() throws Exception {
		//
		List<OMN33812> list = callOmn33812(equipMfactCd, equipMdlCd);
		if (list != null && list.size() > 0) {
			Map<String, List<OMN33812>> map = new HashMap<String, List<OMN33812>>();
			List<OMN33812> entry;
			for (OMN33812 o : list) {
				entry = map.get(o.getEquipMdlCd());
				if (entry == null) {
					entry = new ArrayList<OMN33812>();
					map.put(o.getEquipMdlCd(), entry);
				}
				entry.add(o);
			}
			eventMap = map;
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Logger.makeString("LOADED OMN33812", (list == null ? "null" : list.size())));
		sb.append(Logger.makeSubString("equipMfactCd", equipMfactCd));
		sb.append(Logger.makeSubString("equipMdlCd", Arrays.toString(equipMdlCd)));
		Logger.logger.info(sb.toString());
	}

	/**
	 * 시스로그 패턴을 조회하여 가지고 있는다.
	 *
	 * <pre>
	 * 	특정 모델(Nokia7210) 은 장애를 시스로그 로 판단하고 분석 한다.
	 * </pre>
	 * 
	 */
	private Map<String, List<SyslogPatternAdams>> syslogPatternListMapByEquipMdlCd;

	public Map<String, List<SyslogPatternAdams>> getSyslogPatternListMapByEquipMdlCd() {
		return syslogPatternListMapByEquipMdlCd;
	}

	/**
	 * 노키아 DCN 접속시 초기 장애 파싱을 하기위한 함수 (다른방향 검토후 제거 필요)
	 * 
	 * @author lagoon lagoon
	 */
	public void loadSyslogPattern() {

		syslogPatternListMapByEquipMdlCd = new HashMap<String, List<SyslogPatternAdams>>();

		List<SyslogPattern> list = getSyslogPattern();
		for (SyslogPattern syslogPattern : list) {
			SyslogPatternAdams pattern = (SyslogPatternAdams) syslogPattern;
			List<SyslogPatternAdams> entry = syslogPatternListMapByEquipMdlCd.get(pattern.getEquipMdlCd());
			if (entry == null) {
				entry = new ArrayList<SyslogPatternAdams>();
				syslogPatternListMapByEquipMdlCd.put(pattern.getEquipMdlCd(), entry);
			}
			entry.add(pattern);
		}
	}

	public List<SyslogPattern> getSyslogPattern() {

		Map<String, Object> para = new HashMap<String, Object>();
		if (equipMfactCd != null) {
			para.put("equipMfactCd", equipMfactCd);
		}
		if (equipMdlCd != null) {
			para.put("equipMdlCd", equipMdlCd);
		}
		para.put("isSyslogRcv", true);

		List<SyslogPattern> list = new ArrayList<SyslogPattern>();
		try {
			List<Map<String, Object>> mapList = client.testRetList("datas", "get-omn33812", para);
			for (Map<String, Object> map : mapList) {
				OMN33812 item = new OMN33812();
				ObjectUtil.toObject(map, item);
				SyslogPatternAdams p = new SyslogPatternAdams();
				p.setOcrPatternStr(item.getCmprCharStrVal());
				p.setClrPatternStr(item.getCmprCharStrTermVal());
				p.setEquipMdlCd(item.getEquipMdlCd());
				list.add(p);
			}
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}

		return list;

	}

	/**
	 * 필요 데이터를 생성 합니다. (최초 시작시 한번)
	 * 
	 * <pre>
	 * 수집장비모델맵핑테이블(OIV10213)
	 * 수집장비맵핑테이블(OIV10107)
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void mergeData() throws Exception {
		try {
			client.testRetObj("datas", "merge-data", getPara());
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}
	
	/**
	 * 저속급TDM 장애 체크 및 장애 발생
	 * 
	 * @throws Exception
	 */
	public void findCrsAlarmAndFireEvent() throws Exception {
		Logger.logger.debug(Logger.makeString("저속급TDM 장애 체크"));
		try {
			clientTeams.testRetObj("teams", "find-crs-alarm-and-fire-event", null);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}
	
}
//
//
// /**
// * IP주소를 이용하여 장비를 찾는다.
// *
// * @param ipAddr
// * @return
// */
// public AdamsEquipVo getEquipByIp(String ipAddr) {
// Map<String, Object> para = getPara();
// para.put("equipIpAddr", ipAddr);
//
// List<AdamsEquipVo> list;
// try {
// list = callEquipList(para);
// } catch (Exception e) {
// Logger.logger.error(e);
// return null;
// }
//
// if (list != null && list.size() > 0) {
// return list.get(0);
// }
// return null;
// }
//
// /**
// * TID기존으로 장비를 조회한다. ( 제조사, 모델이 이 서비스 것을 참고함 )
// *
// * @param tid
// * @return
// */
// public AdamsEquipVo getEquipByTid(String tid) {
// Map<String, Object> para = getPara();
// para.put("equipTidVal", tid);
//
// List<AdamsEquipVo> list;
// try {
// list = callEquipList(para);
// } catch (Exception e) {
// Logger.logger.error(e);
// return null;
// }
//
// if (list != null && list.size() > 0) {
// return list.get(0);
// }
// return null;
// }


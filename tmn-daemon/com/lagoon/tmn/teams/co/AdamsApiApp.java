package com.lagoon.tmn.teams.co;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.lagoon.tmn.teams.app.mgr.IManager;
import com.lagoon.tmn.teams.app.mgr.TrmsAlarmAutoRlseMgr;
import com.lagoon.tmn.teams.app.mgr.TrmsAlarmMgr;
import com.lagoon.tmn.teams.app.mgr.TrmsAppDataMgr;
import com.lagoon.tmn.teams.app.mgr.TrmsNetOperMgr;
import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.vo.DataStateVo;
import com.lagoon.tmn.teams.co.vo.LineInfoVo;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.co.vo.TrmsMoData;
import com.lagoon.tmn.teams.co.vo.TrmsNetAlcdVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;
import com.lagoon.tmn.teams.fxms.dbo.ONM10100;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.bas.fxo.service.FxServiceImpl;

public class AdamsApiApp extends AdamsApi {

	// 분석에 필요한 데이터 메니저
	private TrmsAppDataMgr trmsAppDataMgr;
	// 현재 장애 메니저
	private TrmsAlarmMgr trmsAlarmMgr;
	// 망 작업 내역 메니저
	private TrmsNetOperMgr trmsNetOperMgr;
	// 장애 자동 해제 메니저
	private TrmsAlarmAutoRlseMgr trmsAlarmAutoRlseMgr;
	// 메니저's
	private Set<IManager> mgrSet;

	/**
	 * AdamsApiApp 인스턴스
	 * 
	 * @return AdamsApiApp
	 */
	public static AdamsApiApp getApiApp() {
		return (AdamsApiApp) getApi();
	}

	@Override
	protected void initApi() throws Exception {
		super.initApi();

		// 망 작업 내역 메니저 초기화
		trmsNetOperMgr = new TrmsNetOperMgr();
		// 분석에 필요한 데이터 메니저 초기화
		trmsAppDataMgr = new TrmsAppDataMgr();
		// 현재 장애 메니저 초기화
		trmsAlarmMgr = new TrmsAlarmMgr();
		// 장애 자동 해제 메니저 초기화
		trmsAlarmAutoRlseMgr = new TrmsAlarmAutoRlseMgr();
		//
		mgrSet = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(
				trmsNetOperMgr, trmsAppDataMgr, trmsAlarmMgr,
				trmsAlarmAutoRlseMgr)));
		mgrSet.forEach(mgr -> mgr.init());

	}

	@Override
	protected void reload() throws Exception {
		super.reload();
		reloadMgr();
	}

	public void reloadMgr() {
		Logger.logger.info(Logger.makeString("ReloadSignal"));
		mgrSet.forEach(mgr -> mgr.reload());
	}

	/**
	 * [1-STEP] 망(링, 트렁크) 정보 찾기(OIV10615)
	 * 
	 * @param equipId
	 * @param portDescr
	 * @return List<LineInfoVo>
	 */
	public List<NetworkInfoVo> findNetworkInfoListByEquipIdAndPortDescr(
			String equipId, String portDescr, String netType) {

		List<NetworkInfoVo> networkInfoList = trmsAppDataMgr
				.findNetworkInfoListByEquipIdAndPortDescr(equipId, portDescr,
						netType);

		networkInfoList = networkInfoList.parallelStream().distinct()
				.collect(Collectors.toList());

		return networkInfoList;
	}

	/**
	 * [2-STEP] 망 정보로 회선 찾기 (OIV10612)
	 * 
	 * @param networkInfoList
	 * @return List<LineInfoVo>
	 */
	public List<LineInfoVo> findLineInfoListByNetworkInfoList(
			List<NetworkInfoVo> networkInfoList, String netType) {

		List<LineInfoVo> lineInfoList = new ArrayList<LineInfoVo>();

		for (NetworkInfoVo networkInfoVo : networkInfoList) {
			List<LineInfoVo> tempList = trmsAppDataMgr
					.findLineInfoListByNetNumAndIsRingTypeAndIsTrunkType(
							networkInfoVo.getNetNum(), netType);

			// 링 장애는 회선정보에 망장애코드를 셋팅 한다.
			if (netType.equals(TeamsCode.NET_TYPE_RING)) {
				tempList.forEach(lineInfoVo -> lineInfoVo
						.setNetDablCd(networkInfoVo.getNetDablCd()));
			}

			lineInfoList.addAll(tempList);
		}

		lineInfoList = lineInfoList.parallelStream().distinct()
				.collect(Collectors.toList());

		return lineInfoList;
	}

	/**
	 * [3-STEP] 회선내역에서 회선 찾기(OIV10612)
	 * 
	 * @param equipId
	 * @param portDescr
	 * @param isLikeSearch
	 * @return List<LineInfoVo>
	 */
	public List<LineInfoVo> findLineInfoListByEquipIdAndPortDescr(
			String equipId, String portDescr, boolean isLikeSearch) {

		List<LineInfoVo> lineInfoList = trmsAppDataMgr
				.findLineInfoListByEquipIdAndPortDescr(equipId, portDescr,
						isLikeSearch);

		lineInfoList = lineInfoList.parallelStream().distinct()
				.collect(Collectors.toList());

		return lineInfoList;
	}

	/**
	 * 장비정보 찾기
	 * 
	 * @param equipId
	 * @return EquipMo
	 */
	public EquipMo findEquipMoByEquipId(String equipId) {
		TrmsMoData trmsMoData = trmsAppDataMgr.findEquipMoByEquipId(equipId);
		if (trmsMoData == null) {
			Logger.logger.fail("Not Found EQUIP_ID : {}", equipId);
			return null;
		}
		return trmsMoData.getEquipMo();
	}

	public TrmsNetAlcdVo findTrmsNetAlcdByEquipMdlCdAndReason(
			String equipMdlCd, String reason) {

		TrmsNetAlcdVo trmsNetAlcdVo = trmsAlarmMgr
				.findTrmsNetAlcdByEquipMdlCdAndReason(equipMdlCd, reason);
		return trmsNetAlcdVo;
	}

	public OMN33811Ext fireEvent(TrmsNetEventVo trmsNetEventVo)
			throws Exception {

		if (!isReady()) {
			throw new Exception("TEAMS 분석(APP) API 가 초기화 되지 않은 상태 입니다.");
		}

		OMN33811Ext omn33811Ext = null;
		switch (trmsNetEventVo.getEventType()) {
		case TrmsNetEventVo.OCCUR_EVENT_ALARM:
			omn33811Ext = trmsAlarmMgr.makeOccurAlarm(trmsNetEventVo);
			break;
		case TrmsNetEventVo.CLEAR_EVENT_ALARM:
			omn33811Ext = trmsAlarmMgr.clearAlarm(trmsNetEventVo);
			break;
		}

		return omn33811Ext;
	}

	public boolean isReady() {
		return mgrSet.stream().allMatch(mgr -> mgr.isReady());
	}

	@Override
	public DataStateVo getChangedState(long lastMstime) throws Exception {

		Map<String, Long> eventMap = FxServiceImpl.fxService.getEventRecvMap();
		DataStateVo ret = new DataStateVo();
		Long time;

		time = eventMap.get("ne");
		ret.setNeChanged(time != null && time.longValue() > lastMstime);

		time = eventMap.get("network");
		ret.setNetworkChanged(time != null && time.longValue() > lastMstime);

		time = eventMap.get("line");
		ret.setLineChanged(time != null && time.longValue() > lastMstime);

		ret.setLastMstime(System.currentTimeMillis());

		return ret;
	}

	/**
	 * 장비ID로 망작업내역을 찾아 존재하면 true 로 반환 한다,.
	 * 
	 * @param equipId
	 * @return
	 */
	public boolean isOperDablYnByEquipId(String equipId) {
		List<ONM10100> onm10100List = trmsNetOperMgr
				.findNetOperListByEquipId(equipId);
		if (onm10100List == null || onm10100List.size() == 0) {
			return false;
		}
		return true;
	}

	/**
	 * 장애 데이터 수정
	 * 
	 * @param teamsAlarmFxEvent
	 * @param isNotify
	 */
	public void updateAlarmStatus(TeamsAlarmFxEvent teamsAlarmFxEvent,
			boolean isNotify) {
		trmsAlarmMgr.updateAlarmStatus(teamsAlarmFxEvent, isNotify);
	}

	/**
	 * 해지되지않은 장애 모두 삭제(이벤트)
	 * 
	 * <pre>
	 * 테스트 용도
	 * </pre>
	 * 
	 * @return
	 * @throws Exception
	 */
	public Object releaseCurrentAlarmAll() throws Exception {

		Map<Long, OMN33810> trmsAlarmMap = trmsAlarmMgr.getTrmsAlarmMap();

		Logger.logger.debug("releaseCurrentAlarmAll() size : {}",
				trmsAlarmMap.size());

		String rlseDtm = Long.toString(FxApi.getDate());
		Timestamp rlseRcvDtm = new Timestamp(System.currentTimeMillis());

		Collection<OMN33810> omn33810List = trmsAlarmMap.values();
		omn33810List.forEach(omn33810 -> {
			// if (omn33810.getEquipId().equals("*****")) {
			// continue;
			// }
			// TrmsNetEventVo trmsNetEventVo = new TrmsNetEventVo();
			// trmsNetEventVo.setDablCd(omn33810.getDablCd());
			// trmsNetEventVo.setDablGrCd(omn33810.getDablGrCd());
			// trmsNetEventVo.setEquipId(omn33810.getEquipId());
			// trmsNetEventVo.setFullMsg(omn33810.getDablMsgCtt());
			// trmsNetEventVo.setLocation(omn33810.getDablOccrLocDesc());
			// trmsNetEventVo.setOccurTime(0);
			// trmsNetEventVo.setPortDescr(omn33810.getPortDesc());
			// trmsNetEventVo.setReason(omn33810.getCmprCharStrVal());
			// trmsNetEventVo.setClearTime(System.currentTimeMillis());
			// fireEvent(trmsNetEventVo);
				releaseCurrentAlarm(omn33810, rlseDtm, rlseRcvDtm);
			});

		return "OK";
	}

	/**
	 * 추가된 전송망 메세지 처리
	 * 
	 * @param trmsNetAlcdVo
	 */
	public void addTrmsNetAlcd(TrmsNetAlcdVo trmsNetAlcdVo) {
		if (!isReady()) {
			trmsAlarmMgr.addTrmsNetAlcd(trmsNetAlcdVo);
		}
	}

	/**
	 * 현재 장애중, 해지 할 장애가 있는지 찾아서 반환 (자동 해제 시간 설정)
	 * 
	 * @return
	 */
	public List<OMN33810> findReleaseAlarmList() {
		return trmsAlarmMgr.findReleaseAlarmList();
	}

	/**
	 * 현재 장애 릴리즈
	 * 
	 * @param omn33810
	 * @param rlseDtm
	 * @param rlseRcvDtm
	 */
	public void releaseCurrentAlarm(OMN33810 omn33810, String rlseDtm,
			Timestamp rlseRcvDtm) {

		try {
			trmsAlarmMgr.releaseCurrentAlarm(omn33810, rlseDtm, rlseRcvDtm);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	public OMN33811Ext clearAlarm(long trmsEquipDablNum) throws Exception {

		if (!isReady()) {
			throw new Exception("TEAMS 분석(APP) API 가 초기화 되지 않은 상태 입니다.");
		}

		OMN33811Ext omn33811Ext = trmsAlarmMgr.clearAlarm(trmsEquipDablNum);

		return omn33811Ext;
	}

	/**
	 * 장비정보 데이터 찾기
	 * 
	 * @param equipId
	 */
	public TrmsMoData findTrmsMoDataByEquipId(String equipId) {
		return trmsAppDataMgr.findEquipMoByEquipId(equipId);
	}

	/**
	 * 망 번호로 망 내역 찾기
	 * 
	 * @param netNum
	 */
	public List<LineNetworkBaseVo> findNetworkListByNetNum(String netNum) {
		return trmsAppDataMgr.findNetworkListByNetNum(netNum);
	}

	/**
	 * 저속급 TDM점검결과 '불일치' 일시 장애 생성
	 * 
	 */
	public void findCrsAlarmAndFireEvent() {
		trmsAlarmMgr.findCrsAlarmAndFireEvent();
	}

}

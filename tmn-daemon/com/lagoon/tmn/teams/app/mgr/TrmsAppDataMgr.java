package com.lagoon.tmn.teams.app.mgr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.TeamsAlarmCode;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.dao.AdamsDao;
import com.lagoon.tmn.teams.co.vo.LineInfoVo;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.co.vo.NetworkVo;
import com.lagoon.tmn.teams.co.vo.TrmsMoData;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.Logger;

/**
 * 회선, 망 을 관리 및 검색을 도와주는 메니저
 * 
 * @author lagoon(강명중)
 *
 */
public class TrmsAppDataMgr implements IManager {

	// 장비ID를 기준으로, 포트의 회선번호 및 망번호 내역을 가진다.
	private Map<String, TrmsMoData> trmsMoDataMap;
	// 링번호를 기준으로 회선 내역을 가진다.
	private Map<String, List<LineNetworkBaseVo>> trmsRing4LineMap;
	// 트렁크번호를 기준으로 회선 내역을 가진다.
	private Map<String, List<LineNetworkBaseVo>> trmsTrunk4LineMap;
	// 회선번호를 기준으로 회선 정보를 가진다.
	private Map<String, LineInfoVo> trmsLineInfoMap;
	// 망번호를 기준으로 망 정보를 가진다.
	private Map<String, NetworkInfoVo> trmsNetworkInfoMap;
	// 망번호를 기준으로 망 내역을 가진다.
	private Map<String, List<LineNetworkBaseVo>> trmsRing4NetworkMap;
	// 임시 데이터 처리 객체(리로드 시그널 이 올때 재 조회 후 해당 임시 영역에 보관하여 처리)
	private Map<String, TrmsMoData> trmsMoDataMapTemp;
	private Map<String, List<LineNetworkBaseVo>> trmsRing4LineMapTemp;
	private Map<String, List<LineNetworkBaseVo>> trmsTrunk4LineMapTemp;
	private Map<String, List<LineNetworkBaseVo>> trmsRing4NetworkMapTemp;

	public TrmsAppDataMgr() {
		super();
	}

	@Override
	public void init() {
		Logger.logger.trace("TrmsAppDataMgr Initalize.");
		// reload();
	}

	@Override
	public void reload() {

		// 1. (임시) 객체 초기화
		trmsMoDataMapTemp = new HashMap<String, TrmsMoData>();
		trmsRing4LineMapTemp = new HashMap<String, List<LineNetworkBaseVo>>();
		trmsTrunk4LineMapTemp = new HashMap<String, List<LineNetworkBaseVo>>();
		trmsRing4NetworkMapTemp = new HashMap<String, List<LineNetworkBaseVo>>();

		// 2. 장비 조회 및 셋팅
		List<EquipMo> equipMoList = getNeList();
		TrmsMoData data;
		for (EquipMo equipMo : equipMoList) {
			data = new TrmsMoData(equipMo);
			trmsMoDataMapTemp.put(equipMo.getEquipId(), data);
		}

		// 3. 회선 기본 정보 조회 및 셋팅
		Map<String, LineInfoVo> trmsLineInfoMapTemp = selectLineInfo2Map();

		// 4. 망 기본 정보 조회 및 셋팅
		Map<String, NetworkInfoVo> trmsNetworkInfoMapTemp = selectNetworkInfo2Map();

		// 5. 회선 선번 내역 조회 및 셋팅
		selectLineListAndProcessData();

		// 6. 망 선번 내역 조회 및 셋팅
		selectNetworkListAndProcessData();

		// 8. 조회 및 셋팅이 완료된 (임시) 데이터 적용
		trmsMoDataMap = trmsMoDataMapTemp;
		trmsRing4LineMap = trmsRing4LineMapTemp;
		trmsTrunk4LineMap = trmsTrunk4LineMapTemp;
		trmsLineInfoMap = trmsLineInfoMapTemp;
		trmsNetworkInfoMap = trmsNetworkInfoMapTemp;
		trmsRing4NetworkMap = trmsRing4NetworkMapTemp;

		Logger.logger
				.info("TRMS EQUIP(MO) COUNT : {}, LINE INFO COUNT : {}, TRUNK COUNT(회선기준) : {}, RING COUNT(회선기준) : {}, NETWORK INFO COUNT : {}",
						trmsMoDataMap.size(), trmsLineInfoMap.size(),
						trmsTrunk4LineMap.size(), trmsRing4LineMap.size(),
						trmsNetworkInfoMap.size());

	}

	/**
	 * 메니저 초기화 여부
	 * 
	 * <pre>
	 * 	각 객체들이 null이 아니면 준비된 상태라고 판단
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public boolean isReady() {
		return (trmsMoDataMap != null && trmsRing4LineMap != null
				&& trmsTrunk4LineMap != null && trmsLineInfoMap != null
				&& trmsNetworkInfoMap != null && trmsRing4NetworkMap != null);
	}

	/**
	 * 모든 장비를 조회 하여 반환
	 * 
	 * @param
	 * @return List<EquipMo>
	 */
	private List<EquipMo> getNeList() {
		try {
			return new AdamsDao().selectNeList(null, null, 0);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 회선 내역 정보를 조회 & 데이터를 가공을 실행
	 * 
	 * @param
	 * @return void
	 */
	private void selectLineListAndProcessData() {
		try {
			new AdamsDao().selectLineListAndProcessData(this);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	/**
	 * 네트워크 선번 내역 정보를 조회 & 데이터를 가공을 실행
	 * 
	 * @param
	 * @return void
	 */
	private void selectNetworkListAndProcessData() {
		try {
			new AdamsDao().selectNetworkListAndProcessData(this);
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	/**
	 * 회선 기본정보를 조회하여, Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<String, LineInfoVo>
	 */
	private Map<String, LineInfoVo> selectLineInfo2Map() {
		try {
			return new AdamsDao().selectLineInfo2Map();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 망 기본정보를 조회하여, Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<String, NetworkInfoVo>
	 */
	private Map<String, NetworkInfoVo> selectNetworkInfo2Map() {
		try {
			return new AdamsDao().selectNetworkInfo2Map();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 데이터 가공에 필요한 객체
	 * 
	 */
	private List<LineNetworkBaseVo> lnBaseList = null;

	/**
	 * 데이터 가공에 필요한 함수를 호출
	 * 
	 * @param lnBaseVo
	 * @return void
	 * 
	 */
	public void setDataMapByLineNetworkBaseVo(LineNetworkBaseVo lnBaseVo) {
		setMoDataMap(lnBaseVo);
		if (lnBaseVo.getLineNum() != null) {
			setRing4LineMap(lnBaseVo);
			setTrunk4LineMap(lnBaseVo);
		}
		if (lnBaseVo.getNetNum() != null) {
			setRing4NetworkMap(lnBaseVo);
		}
	}

	/**
	 * trmsMoDataMap 객체를 가공한다.
	 * 
	 * <pre>
	 * 		{
	 * 			장비ID : {
	 *   					장비정보, 
	 *     					포트기준 회선 내역 리스트, 
	 *     					포트기준 망 내역 리스트 
	 * 						  } 	
	 * 		}
	 * </pre>
	 * 
	 * @param lnBaseVo
	 * @return void
	 * 
	 */
	private void setMoDataMap(LineNetworkBaseVo lnBaseVo) {
		TrmsMoData trmsMoData = trmsMoDataMapTemp.get(lnBaseVo.getEquipId());
		// 장비 존재 여부
		if (trmsMoData != null) {
			// A포트 정보 존재
			if (lnBaseVo.getEquipAPortInfo() != null) {
				// 회선, 망 데이터 처리 타겟 다름
				if (lnBaseVo.getLineNum() != null) {
					lnBaseList = trmsMoData.getLineListByPortInfo(lnBaseVo
							.getEquipAPortInfo());
					setLnBaseVo2List(lnBaseList, lnBaseVo,
							trmsMoData.getPortLines(),
							lnBaseVo.getEquipAPortInfo());
				} else if (lnBaseVo.getNetNum() != null) {
					lnBaseList = trmsMoData.getNetworkListByPortInfo(lnBaseVo
							.getEquipAPortInfo());
					setLnBaseVo2List(lnBaseList, lnBaseVo,
							trmsMoData.getPortNetworks(),
							lnBaseVo.getEquipAPortInfo());
				}
			}
			// B포트 정보 존재
			if (lnBaseVo.getEquipBPortInfo() != null) {
				// 회선, 망 데이터 처리 타겟 다름
				if (lnBaseVo.getLineNum() != null) {
					lnBaseList = trmsMoData.getLineListByPortInfo(lnBaseVo
							.getEquipBPortInfo());
					setLnBaseVo2List(lnBaseList, lnBaseVo,
							trmsMoData.getPortLines(),
							lnBaseVo.getEquipBPortInfo());
				} else if (lnBaseVo.getNetNum() != null) {
					lnBaseList = trmsMoData.getNetworkListByPortInfo(lnBaseVo
							.getEquipBPortInfo());
					setLnBaseVo2List(lnBaseList, lnBaseVo,
							trmsMoData.getPortNetworks(),
							lnBaseVo.getEquipBPortInfo());
				}

			}
		}
	}

	/**
	 * trmsRing4LineMap 객체를 가공한다.
	 * 
	 * <pre>
	 * 		{
	 * 			링번호 : 회선 리스트 
	 * 		}
	 * </pre>
	 * 
	 * @param lnBaseVo
	 * @return void
	 * 
	 */
	private void setRing4LineMap(LineNetworkBaseVo lnBaseVo) {
		if (lnBaseVo.getRingNum() != null) {
			lnBaseList = trmsRing4LineMapTemp.get(lnBaseVo.getRingNum());
			setLnBaseVo2List(lnBaseList, lnBaseVo, trmsRing4LineMapTemp,
					lnBaseVo.getRingNum());
		}
	}

	/**
	 * trmsTrunk4LineMap 객체를 가공한다.
	 * 
	 * <pre>
	 * 		{
	 * 			트렁크 번호 : 회선 리스트 
	 * 		}
	 * </pre>
	 * 
	 * @param lnBaseVo
	 * @return void
	 * 
	 */
	private void setTrunk4LineMap(LineNetworkBaseVo lnBaseVo) {
		if (lnBaseVo.getTrunkNum() != null) {
			lnBaseList = trmsTrunk4LineMapTemp.get(lnBaseVo.getTrunkNum());
			setLnBaseVo2List(lnBaseList, lnBaseVo, trmsTrunk4LineMapTemp,
					lnBaseVo.getTrunkNum());
		}
	}

	/**
	 * trmsRing4NetworkMap 객체를 가공한다.
	 * 
	 * <pre>
	 * 		{
	 * 			링번호 : 망 내역 리스트 (링 타입만 가짐)
	 * 		}
	 * </pre>
	 * 
	 * @param lnBaseVo
	 * @return void
	 * 
	 */
	private void setRing4NetworkMap(LineNetworkBaseVo lnBaseVo) {

		if (lnBaseVo instanceof NetworkVo) {
			NetworkVo networkVo = (NetworkVo) lnBaseVo;
			if (networkVo.isRingType()) {
				lnBaseList = trmsRing4NetworkMapTemp.get(lnBaseVo.getNetNum());
				setLnBaseVo2List(lnBaseList, lnBaseVo, trmsRing4NetworkMapTemp,
						lnBaseVo.getNetNum());
			}
		}
	}

	/**
	 * 타겟MAP에 회선, 망 리스트를 가공 한다.
	 *
	 * @param lnBaseVo
	 * @param lnBaseVo
	 * @return void
	 */
	private void setLnBaseVo2List(List<LineNetworkBaseVo> lnBaseList,
			LineNetworkBaseVo lnBaseVo,
			Map<String, List<LineNetworkBaseVo>> targetMap, String key) {

		if (lnBaseList == null) {
			lnBaseList = new ArrayList<LineNetworkBaseVo>();
			targetMap.put(key, lnBaseList);
		}
		lnBaseList.add(lnBaseVo);
	}

	/**
	 * 장비ID로 TrmsMoData 를 찾아서 반환
	 * 
	 * @param equipId
	 * @return EquipMo
	 */
	public TrmsMoData findEquipMoByEquipId(String equipId) {
		return trmsMoDataMap.get(equipId);
	}

	/**
	 * 망내역으로 회선정보 찾아서 리스트 반환
	 *
	 * @param netNum
	 * @param isRingType
	 * @param isTrunkType
	 * @return List<LineInfoVo>
	 */
	public List<LineInfoVo> findLineInfoListByNetNumAndIsRingTypeAndIsTrunkType(
			String netNum, String netType) {

		List<LineInfoVo> lineInfoList = new ArrayList<LineInfoVo>();
		List<LineNetworkBaseVo> lnBaseList = null;
		if (netType.equals(TeamsCode.NET_TYPE_RING)) {
			lnBaseList = trmsRing4LineMap.get(netNum);
		} else if (netType.equals(TeamsCode.NET_TYPE_TRUNK)) {
			lnBaseList = trmsTrunk4LineMap.get(netNum);
		}

		if (lnBaseList != null) {
			for (LineNetworkBaseVo lnBaseVo : lnBaseList) {
				LineInfoVo lineInfoVo = trmsLineInfoMap.get(lnBaseVo
						.getLineNum());
				if (lineInfoVo != null) {
					if (lineInfoVo.isLineSkip()) {
						Logger.logger
								.info("상태 코드로 인한 스킵(CLOSE, NOT_OPEN, OPENING) , 회선 번호 : {}",
										lineInfoVo.getLineNum());
						continue;
					}
					lineInfoList.add(lineInfoVo);
				}
			}
		}

		return lineInfoList;
	}

	/**
	 * equipId와 portDescr 으로 회선을 검색하여 회선정보 리스트를 반환
	 *
	 * @param equipId
	 * @param portDescr
	 * @param isLikeSearch
	 * @return List<LineInfoVo>
	 */
	public List<LineInfoVo> findLineInfoListByEquipIdAndPortDescr(
			String equipId, String portDescr, boolean isLikeSearch) {

		List<LineInfoVo> lineInfoList = new ArrayList<LineInfoVo>();
		TrmsMoData moData = trmsMoDataMap.get(equipId);

		if (moData != null) {

			List<LineNetworkBaseVo> lnBaseList = moData
					.getLnBaseListByPortDescr(portDescr, false, isLikeSearch);

			for (LineNetworkBaseVo lnBaseVo : lnBaseList) {
				LineInfoVo lineInfoVo = trmsLineInfoMap.get(lnBaseVo
						.getLineNum());
				if (lineInfoVo != null) {
					if (lineInfoVo.isLineSkip()) {
						Logger.logger
								.info("상태 코드로 인한 스킵(CLOSE, NOT_OPEN, OPENING) , 회선 번호 : {}",
										lineInfoVo.getLineNum());
						continue;
					}
					lineInfoList.add(lineInfoVo);
				} else {
					Logger.logger
							.info("회선 정보가 없어서 스킵(where co_line_vrf_yn = 'Y and trms_net_mgmt_ownr_cd = '03') , 회선 번호 : {}",
									lnBaseVo.getLineNum());
				}
			}

		}
		return lineInfoList;
	}

	/**
	 * equipId와 portDescr 으로 망 을 검색하여 망 내역을 반환
	 *
	 * @param equipId
	 * @param portDescr
	 * @param isLikeSearch
	 * @return List<LineNetworkBaseVo>
	 */
	public List<LineNetworkBaseVo> findLnBaseListByEquipIdAndPortDescr(
			String equipId, String portDescr, boolean isLikeSearch) {

		List<LineNetworkBaseVo> lnBaseList = null;
		TrmsMoData moData = trmsMoDataMap.get(equipId);

		if (moData != null) {
			lnBaseList = moData.getLnBaseListByPortDescr(portDescr, true,
					isLikeSearch);
		}

		if (lnBaseList == null) {
			lnBaseList = new ArrayList<LineNetworkBaseVo>();
		}
		return lnBaseList;
	}

	/**
	 * equipId와 portDescr 으로 망 을 검색하여 망 정보를 반환(링,트렁크 구분)
	 * 
	 * @param equipId
	 * @param portDescr
	 * @param netType
	 * @return List<NetworkInfoVo>
	 */
	public List<NetworkInfoVo> findNetworkInfoListByEquipIdAndPortDescr(
			String equipId, String portDescr, String netType) {

		List<NetworkInfoVo> networkInfoList = new ArrayList<NetworkInfoVo>();
		TrmsMoData moData = trmsMoDataMap.get(equipId);

		if (moData != null) {
			lnBaseList = moData.getLnBaseListByPortDescr(portDescr, true, true);
			for (LineNetworkBaseVo lnBaseVo : lnBaseList) {

				NetworkInfoVo networkInfoVo = trmsNetworkInfoMap.get(lnBaseVo
						.getNetNum());
				if (networkInfoVo != null) {
					if (networkInfoVo.isNetworkSkip()) {
						Logger.logger.info(
								"상태 코드로 인한 스킵(LineStCd.CLOSE) , 망 번호 : {}",
								networkInfoVo.getNetNum());
						continue;
					}
					if (networkInfoVo.isNotApplyType(netType)) {
						continue;
					}

					// 트렁크 번호 추가
					networkInfoVo.setTrunkNum(lnBaseVo.getTrunkNum());
					if (netType.equals(TeamsCode.NET_TYPE_TRUNK)) {
						networkInfoVo
								.setNetDablCd(TeamsAlarmCode.NET_DABL_CD_TRUNK_ALARM);
					}
					//
					networkInfoList.add(networkInfoVo);
				}
			}
		}

		return networkInfoList;
	}

	/**
	 * 망 번호로 망 내역 찾기
	 * 
	 * @param netNum
	 */
	public List<LineNetworkBaseVo> findNetworkListByNetNum(String netNum) {
		return trmsRing4NetworkMap.get(netNum);
	}

}

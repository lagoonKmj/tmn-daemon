package com.lagoon.tmn.teams.app.mgr;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsAlarmCode;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.dao.AdamsDao;
import com.lagoon.tmn.teams.co.dao.AlarmDao;
import com.lagoon.tmn.teams.co.vo.LineInfoVo;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.co.vo.NetworkLinkVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetAlcdVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;
import com.lagoon.tmn.teams.fxms.dbo.OMN33820;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.bas.co.noti.FxEvent.STATUS;
import fxms.bas.fxo.service.FxServiceImpl;

/**
 * 현재 장애를 관리해주는 메니저
 * 
 * @author lagoon(강명중)
 *
 */
public class TrmsAlarmMgr implements IManager {

	// 1. Key : 장비 장애번호, Value : 장비 장애 정보
	private Map<Long, OMN33810> trmsAlarmMap;
	// 2. Key : 망 장애번호, Value : 망 장애 정보
	private Map<Long, OMN33830> trmsNetAlarmMap;
	// 3. Key : 망 번호, Value : [망 장애번호]
	private Map<String, List<Long>> trmsNetAlarmMapByRingDablNum;
	// 4. Key : 장비 장애번호, Value : [망 장애번호]
	private Map<Long, List<Long>> trmsEquipDablNum2RingDablNum;
	// 5. Key : 장비모델ID, Value : [전송망메세지]
	private Map<String, List<TrmsNetAlcdVo>> trmsNetAlcdMapByEquipMdlCd;

	public TrmsAlarmMgr() {
		super();
	}

	@Override
	public void init() {
		Logger.logger.trace("TrmsAlarmMgr Initalize.");
		// reload();
	}

	@Override
	public void reload() {

		// 1. 전송장비 현재 장애 조회 및 셋팅
		trmsAlarmMap = selectAlarm2Map();

		// 2. 전송장비 현재 망 장애 조회 및 셋팅
		trmsNetAlarmMap = selectNetAlarm2Map();

		// 3. 망 장애(현재) 조회 및 셋팅
		trmsNetAlarmMapByRingDablNum = setNetAlarmMapByRingDablNum();

		// 4. 장비 장애 번호를 기준 망 장애 번호 조회 및 셋팅
		trmsEquipDablNum2RingDablNum = selectEquipDablNum2RingDablNums();

		// 5. 전송망 메세지(장애 조건) 조회 및 셋팅
		trmsNetAlcdMapByEquipMdlCd = trmsNetAlcdMapByEquipMdlCd();

		Logger.logger
				.info("TRMS EQUIP ALARM COUNT : {}, TRMS NET ALARM BY NET NUM COUNT : {}, ALARM CODE BY MODEL COUNT : {}",
						trmsAlarmMap.size(), trmsNetAlarmMap.size(),
						trmsNetAlcdMapByEquipMdlCd.size());
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
		return (trmsAlarmMap != null && trmsNetAlarmMap != null
				&& trmsNetAlarmMapByRingDablNum != null
				&& trmsEquipDablNum2RingDablNum != null && trmsNetAlcdMapByEquipMdlCd != null);
	}

	/**
	 * 전송장비 현재 장애를 조회하여, 장비 장애번호를 키로 Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<Long, OMN33810>
	 */
	private Map<Long, OMN33810> selectAlarm2Map() {
		try {
			return new AlarmDao().selectAlarm2Map();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 전송장비 현재 망 장애를 조회하여, 망 장애번호를 키로 Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<Long, OMN33830>
	 */
	private Map<Long, OMN33830> selectNetAlarm2Map() {
		try {
			return new AlarmDao().selectNetAlarm2Map();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 망 번호를 키로 현재 망장애 번호 내역 Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<String, List<Long>>
	 */
	private Map<String, List<Long>> setNetAlarmMapByRingDablNum() {

		Map<String, List<Long>> map = new HashMap<String, List<Long>>();

		trmsNetAlarmMap.forEach((key, value) -> {
			List<Long> entry = map.get(value.getNetNum());
			if (entry == null) {
				entry = new ArrayList<Long>();
				map.put(value.getNetNum(), entry);
			}
			entry.add(key);
		});

		return map;
	}

	/**
	 * 장비 장애 번호를 기준으로 망 장애 번호들 Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<Long, List<Long>>
	 */
	private Map<Long, List<Long>> selectEquipDablNum2RingDablNums() {

		Map<Long, List<Long>> map = new HashMap<Long, List<Long>>();

		trmsNetAlarmMap.forEach((key, value) -> {
			List<Long> entry = map.get(value.getTrmsEquipDablNum());
			if (entry == null) {
				entry = new ArrayList<Long>();
				map.put(value.getTrmsEquipDablNum(), entry);
			}
			entry.add(key);
		});

		return map;
	}

	/**
	 * 전송망 메세지(장애 조건) 을 조회하여, 장비모델을 기준으로 Map을 가공 하여 반환 한다.
	 * 
	 * @return Map<String, List<TrmsNetAlcdVo>>
	 */
	private Map<String, List<TrmsNetAlcdVo>> trmsNetAlcdMapByEquipMdlCd() {
		try {
			return new AdamsDao().trmsNetAlcdMapByEquipMdlCd(null);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 추가된 전송망 메세지 처리
	 * 
	 * @param trmsNetAlcdVo
	 */
	public void addTrmsNetAlcd(TrmsNetAlcdVo trmsNetAlcdVo) {

		List<TrmsNetAlcdVo> entry = trmsNetAlcdMapByEquipMdlCd
				.get(trmsNetAlcdVo.getEquipMdlCd());
		if (entry == null) {
			entry = new ArrayList<TrmsNetAlcdVo>();
			trmsNetAlcdMapByEquipMdlCd
					.put(trmsNetAlcdVo.getEquipMdlCd(), entry);
		}
		entry.add(trmsNetAlcdVo);
	}

	/**
	 * 모델코드와, 원인을 가지고 전송망메세지VO를 반환
	 * 
	 * @param equipMdlCd
	 * @param reason
	 * @return TrmsNetAlcdVo
	 */
	public TrmsNetAlcdVo findTrmsNetAlcdByEquipMdlCdAndReason(
			String equipMdlCd, String reason) {

		List<TrmsNetAlcdVo> trmsNetAlcdList = trmsNetAlcdMapByEquipMdlCd
				.get(equipMdlCd);

		TrmsNetAlcdVo trmsNetAlcdVo = null;
		if (trmsNetAlcdList != null) {
			for (TrmsNetAlcdVo vo : trmsNetAlcdList) {
				if (vo.match(reason)) {
					trmsNetAlcdVo = vo;
					break;
				}
			}
		}

		return trmsNetAlcdVo;
	}

	/**
	 * 장애 생성
	 * 
	 * @param trmsNetEventVo
	 * @param api
	 * @return {@link OMN33811Ext}
	 */
	public OMN33811Ext makeOccurAlarm(TrmsNetEventVo trmsNetEventVo) {

		try {

			AdamsApiApp api = AdamsApiApp.getApiApp();

			StringBuffer sbLogSub = new StringBuffer();

			// 장비 정보
			EquipMo mo = api.findEquipMoByEquipId(trmsNetEventVo.getEquipId());
			if (mo == null) {
				sbLogSub.append(Logger.makeSubString(
						"장비 정보(OIV10100) 가 DB에 존재 안함", trmsNetEventVo));
				mo = new EquipMo();
				mo.setEquipId(trmsNetEventVo.getEquipId());
				mo.setMoName("*****");
				mo.setEquipTidVal(trmsNetEventVo.getTid());
				mo.setEquipIpAddr(trmsNetEventVo.getIpAddr());
				mo.setEquipMdlCd(trmsNetEventVo.getEquipMdlCd());
				mo.setTpoCd("*****");
			}
			sbLogSub.append(Logger.makeSubString("장비(OIV10100)", mo));

			// 전송망 메세지 필터
			TrmsNetAlcdVo trmsNetAlcdVo = null;
			// 시스로그,트랩, TDM 장애는 OMN33812 조건 무시
			if (trmsNetEventVo.isIgnoreOmn33812() == true) {
				trmsNetAlcdVo = new TrmsNetAlcdVo();
				trmsNetAlcdVo.setTrmsNetEquipMsgMgmtNum(-1);
				trmsNetAlcdVo.setCmprCharStrVal(trmsNetEventVo.getReason());
				trmsNetAlcdVo.setDablAplyYn(true);
			} else {
				trmsNetAlcdVo = findTrmsNetAlcdByEquipMdlCdAndReason(
						mo.getEquipMdlCd(), trmsNetEventVo.getReason());
				sbLogSub.append(Logger.makeSubString("전송망 메세지(OMN33812)",
						trmsNetAlcdVo));
			}

			if (trmsNetAlcdVo == null) {
				return null;
			}

			if (trmsNetAlcdVo.isDablAplyYn() == false) {
				return null;
			}

			List<NetworkInfoVo> networkInfoList = null;
			List<LineInfoVo> lineInfoList = null;

			// FxEvent 에 담을 내용
			List<OMN33830> omn33830List = null;
			List<OMN33820> omn33820List = null;
			OMN33810 omn33810 = null;

			// 전송장비장애번호 시퀀스 값 가져오기
			Long trmsEquipDablNum = new AlarmDao().getNextVal("OMN33810_S1");
			sbLogSub.append(Logger.makeSubString("전송장비장애번호", trmsEquipDablNum));

			if (trmsNetAlcdVo.isNetDablAplyYn() == true
					&& trmsNetEventVo.isMakeOccurNetAlarm() == true) {

				sbLogSub.append(Logger.makeSubString("망장애 생성 로직(OIV10615)",
						"OK"));
				/**
				 * <pre>
				 * 1. 링을 찾음
				 * 2. 링이 고립인지, 오픈인지 확인
				 * 3. 존재시 링 기준 회선 찾음
				 * 4. 회선이 존재 안하면, 검색된 회선이 존재 안할시 트렁크 찾음
				 * 5. 존재시 트렁크 기준 회선 찾음
				 * </pre>
				 */
				// 링 찾기
				networkInfoList = api.findNetworkInfoListByEquipIdAndPortDescr(
						trmsNetEventVo.getEquipId(),
						trmsNetEventVo.getPortDescr(), TeamsCode.NET_TYPE_RING);
				sbLogSub.append(Logger.makeSubString("찾은 링 갯수(OIV10614)",
						networkInfoList.size()));

				if (networkInfoList != null && networkInfoList.size() > 0) {

					// 링 타입 설정
					findRingTypeAndSetNetDablCd(trmsNetEventVo,
							networkInfoList, api);

					// 링 장애 발생
					omn33830List = insertNetworkAlarm(trmsEquipDablNum,
							networkInfoList);
					// 회선 찾기(링 정보 참조)
					lineInfoList = api.findLineInfoListByNetworkInfoList(
							networkInfoList, TeamsCode.NET_TYPE_RING);

					sbLogSub.append(Logger.makeSubString(
							"링 장애 발생(OMN33830) 및 링 정보로 찾은 회선 갯수(OIV10612)",
							lineInfoList.size()));

				} else if (trmsNetEventVo.isReasonLos()) {

					sbLogSub.append(Logger.makeSubString(
							"찾은 링(OIV10615)이 존재 하지 않아 트렁크 찾기(OIV10615)",
							"LOS 장애만 찾기"));

					// 트렁크 찾기
					networkInfoList = api
							.findNetworkInfoListByEquipIdAndPortDescr(
									trmsNetEventVo.getEquipId(),
									trmsNetEventVo.getPortDescr(),
									TeamsCode.NET_TYPE_TRUNK);

					sbLogSub.append(Logger.makeSubString("찾은 트렁크 갯수(OIV10614)",
							networkInfoList.size()));

					if (networkInfoList != null && networkInfoList.size() > 0) {
						// 트렁크 장애 발생
						omn33830List = insertNetworkAlarm(trmsEquipDablNum,
								networkInfoList);
						// 회선 찾기(트렁크 정보 참조)
						lineInfoList = api.findLineInfoListByNetworkInfoList(
								networkInfoList, TeamsCode.NET_TYPE_TRUNK);

						sbLogSub.append(Logger
								.makeSubString(
										"트렁크 장애 발생(OMN33830) 및 링 정보로 찾은 회선 갯수(OIV10612)",
										lineInfoList.size()));
					}
				}
			} else {
				// 망장애 생성 로직 무시 로그
				sbLogSub.append(Logger.makeString("망장애 생성 로직 무시"));
				sbLogSub.append(Logger.makeSubString("망장애적용여부",
						trmsNetAlcdVo.isNetDablAplyYn()));
				sbLogSub.append(Logger.makeSubString("포트DESCR",
						trmsNetEventVo.getPortDescr()));
				sbLogSub.append(Logger.makeSubString("발생일시",
						trmsNetEventVo.getOccurTime()));
			}

			// 회선 찾기(직접 검색)
			if ((lineInfoList == null || lineInfoList.size() == 0)
					&& trmsNetEventVo.getPortDescr() != null) {
				lineInfoList = api.findLineInfoListByEquipIdAndPortDescr(
						trmsNetEventVo.getEquipId(),
						trmsNetEventVo.getPortDescr(),
						trmsNetEventVo.isReasonLos());

				sbLogSub.append(Logger.makeSubString(
						"직접 검색하여 찾은 회선 갯수(OIV10612)", lineInfoList.size()));

				/**
				 * // 그래도 없을시 멀티포트 회선 찾기(성능이슈 가 있다는 주석이 존재) if (lineInfoList ==
				 * null || lineInfoList.size() == 0) { lineInfoList =
				 * findMultiPortLine(); }
				 **/

			}

			/**
			 * // 회선이 전혀 없다. (임시회선 생성) if (lineInfoList == null ||
			 * lineInfoList.size() == 0) { lineInfoList = findTempLine(); }
			 **/

			if (lineInfoList != null && lineInfoList.size() > 0) {
				// 회선 장애 발생
				omn33820List = insertLineAlarm(trmsEquipDablNum, lineInfoList);
				sbLogSub.append(Logger
						.makeSubString("회선 장애 생성(OMN33820)", "OK"));
			}

			// 장비 장애 발생
			trmsNetEventVo.setTrmsEquipDablNum(trmsEquipDablNum);
			trmsNetEventVo.setDmgLineCnt(omn33820List);
			trmsNetEventVo.setOperDablYn(api.isOperDablYnByEquipId(mo
					.getEquipId()));
			// trmsNetEventVo.increaseDablDupCnt(trmsAlarmMap); 아담스 클라이언트에서
			// 셋팅하므로 불필요 ! by.lagoon
			omn33810 = insertEquipAlarmAndUpdateLastDablDtm(mo, trmsNetAlcdVo,
					trmsNetEventVo);

			sbLogSub.append(Logger.makeSubString("장비 장애 생성(OMN33810)", "OK"));

			// 노티 메세지 보내기
			onNotify(omn33810, omn33820List, omn33830List, STATUS.added);

			StringBuffer sbLog = new StringBuffer();
			sbLog.append(Logger.makeString("장애 발생(OMN33810)", trmsNetEventVo,
					sbLogSub.toString()));
			Logger.logger.debug(sbLog.toString());

			//
			OMN33811Ext omn33811Ext = new OMN33811Ext(omn33810);
			return omn33811Ext;

		} catch (Exception e) {
			Logger.logger.error(e);
		}
		return null;
	}

	/**
	 * 현재 장애 해제(TrmsNetEventVo 사용)
	 * 
	 * @param trmsNetEventVo
	 * @param api
	 * @return {@link OMN33811Ext}
	 * @throws Exception
	 */
	public OMN33811Ext clearAlarm(TrmsNetEventVo trmsNetEventVo)
			throws Exception {

		/**
		 * <pre>
		 * 
		 * NMS 장애인지 판단 (로직 무시)
		 * 현재 장비 장애를 찾는다.
		 *   ㄴ 알람키(EMS키일듯) 가 -1^, -99990^ 는 쿼리가 다르다. (로직 무시)
		 *    			"SELECT "\
		 * 					"ALARM_NO, OCCUR_TIME, CLEAR_FLAG, CLEAR_TIME, DURATION_SEC, "\
		 * 					"OCCUR_R_TIME, CLEAR_R_TIME, ALARM_KEY, TRIM(ALARM_ID_OTHER), MNG_GROUP_CD, "\
		 * 					"ALARM_LEVEL, ACK_MODE, WORK_YN, CLOSE_MODE, NE_ID, "\
		 * 					"ORG_ID, TR_ROOM_ID, MODEL_ID, NMS_CD, LINE_COUNT "\
		 * 					"FROM STS_ALARM "\
		 * 					"WHERE "\
		 * 					"ALARM_NO=:f1<char[%d]> ", 
		 * 찾은 장애로 링을 찾아 해제 (액션 필요X)
		 * 회선을 찾아 해제 (액션 필요X)
		 * 장비 장애 해제
		 * 
		 * </pre>
		 */
		String rlseDtm = Long.toString(FxApi.getDate(trmsNetEventVo
				.getClearTime()));
		Timestamp rlseRcvDtm = new Timestamp(trmsNetEventVo.getRecvTime());

		OMN33811Ext omn33811Ext = null;
		StringBuffer sbLogSub = new StringBuffer();

		// 현재 장애 찾음 (장비ID, 포트DESCR, 비교문자열)

		Iterator<OMN33810> iter = trmsAlarmMap.values().iterator();
		while (iter.hasNext()) {

			OMN33810 omn33810 = iter.next();

			if (omn33810.getEquipId().equals("*****")) {
				continue;
			}

			if (trmsNetEventVo.match(omn33810)) {
				releaseCurrentAlarm(omn33810, rlseDtm, rlseRcvDtm);
				omn33811Ext = new OMN33811Ext(omn33810);
				omn33811Ext.setClear(true);
				sbLogSub.append(Logger.makeSubString("장애 클리어(JVM), 장애번호 : {} ",
						omn33810.getTrmsEquipDablNum()));
			}

		}

		StringBuffer sbLog = new StringBuffer();
		sbLog.append(Logger.makeString("장애 릴리즈(OMN33810)", trmsNetEventVo,
				sbLogSub.toString()));
		Logger.logger.debug(sbLog.toString());
		return omn33811Ext;

	}

	public OMN33811Ext clearAlarm(long trmsEquipDablNum) throws Exception {

		OMN33810 alarm = trmsAlarmMap.get(trmsEquipDablNum);
		OMN33811Ext omn33811Ext = null;
		if (alarm == null) {
			// throw new Exception("Not Found Alarm : " + trmsEquipDablNum);
			Logger.logger.debug("Not Found Alarm : " + trmsEquipDablNum);
			return new OMN33811Ext();
		}

		String rlseDtm = Long.toString(FxApi.getDate());
		Timestamp rlseRcvDtm = new Timestamp(System.currentTimeMillis());

		StringBuffer sbLog = new StringBuffer();
		sbLog.append(Logger.makeString("장애 릴리즈", trmsEquipDablNum));

		releaseCurrentAlarm(alarm, rlseDtm, rlseRcvDtm);
		omn33811Ext = new OMN33811Ext(alarm);
		omn33811Ext.setClear(true);
		sbLog.append(Logger.makeSubString("장애 릴리즈 ", "OK"));

		Logger.logger.debug(sbLog.toString());
		return omn33811Ext;
	}

	/**
	 * 현재 장애 릴리즈(OMN33810 사용)
	 * 
	 * @param omn33810
	 * @param rlseDtm
	 * @param rlseRcvDtm
	 * @throws Exception
	 */
	public void releaseCurrentAlarm(OMN33810 omn33810, String rlseDtm,
			Timestamp rlseRcvDtm) throws Exception {

		new AlarmDao().deleteEquipAlarm(omn33810.getTrmsEquipDablNum(),
				rlseDtm, rlseRcvDtm);

		// 현재 장애 Map 에서 삭제
		trmsAlarmMap.remove(omn33810.getTrmsEquipDablNum());
		// 장비 장애번호 를 가지고 해제된 망 장애 관련 데이터 제거
		List<Long> trmsRingDablNumList = trmsEquipDablNum2RingDablNum
				.get(omn33810.getTrmsEquipDablNum());
		if (trmsRingDablNumList != null) {
			for (Long trmsRingDablNum : trmsRingDablNumList) {
				OMN33830 omn33830 = trmsNetAlarmMap.get(trmsRingDablNum);
				if (omn33830 != null) {
					trmsNetAlarmMap.remove(omn33830.getTrmsRingDablNum());
					trmsNetAlarmMapByRingDablNum.get(omn33830.getNetNum())
							.removeIf(
									ringDablNum -> ringDablNum == omn33830
											.getTrmsRingDablNum());
				}
			}
		}
		// 노티 메세지 보내기
		onNotify(omn33810, null, null, STATUS.deleted);
	}

	/**
	 * 전송장비 장애 발생(추가)
	 * 
	 * <pre>
	 * 1. DB에 데이터 추가
	 * 2. 장비수집내역(OIV10178) 의 LAST_DABL_RCV_DTM 업데이트
	 * 3. DCN기본(OIV10618) 의 LAST_DABL_OCCR_DTM 업데이트
	 * 4. 메모리에 현재 장애 데이터 추가
	 * 
	 * </pre>
	 * 
	 * @param mo
	 * @param trmsNetAlcdVo
	 * @param trmsNetEventVo
	 * @return OMN33810
	 */
	private OMN33810 insertEquipAlarmAndUpdateLastDablDtm(EquipMo mo,
			TrmsNetAlcdVo trmsNetAlcdVo, TrmsNetEventVo trmsNetEventVo) {

		try {

			// 1
			OMN33810 omn33810 = new AlarmDao().insertEquipAlarm(mo,
					trmsNetAlcdVo, trmsNetEventVo);
			// 2 and 3
			new AlarmDao().updateLastDablDtm(mo, omn33810);
			// 4
			trmsAlarmMap.put(omn33810.getTrmsEquipDablNum(), omn33810);

			return omn33810;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 전송장비 망장애 발생(추가)
	 * 
	 * <pre>
	 * 	DB 정상 처리후, 메모리에 현재 장애 관리 처리
	 * </pre>
	 * 
	 * @param trmsEquipDablNum
	 * @param networkInfoList
	 * @return List<OMN33830>
	 */
	private List<OMN33830> insertNetworkAlarm(Long trmsEquipDablNum,
			List<NetworkInfoVo> networkInfoList) {

		try {

			List<OMN33830> omn33830List = new AlarmDao().insertNetworkAlarm(
					trmsEquipDablNum, networkInfoList);

			for (OMN33830 omn33830 : omn33830List) {

				// 2. Key : 망 장애번호, Value : 망 장애 정보
				trmsNetAlarmMap.put(omn33830.getTrmsRingDablNum(), omn33830);

				// 3. Key : 망 번호, Value : [망 장애번호]
				List<Long> entryByNetNum = trmsNetAlarmMapByRingDablNum
						.get(omn33830.getNetNum());
				if (entryByNetNum == null) {
					entryByNetNum = new ArrayList<Long>();
					trmsNetAlarmMapByRingDablNum.put(omn33830.getNetNum(),
							entryByNetNum);
				}
				entryByNetNum.add(omn33830.getTrmsRingDablNum());

				// 4. Key : 장비 장애번호, Value : [망 장애번호]
				List<Long> entryByEquipDablNum = trmsNetAlarmMapByRingDablNum
						.get(omn33830.getTrmsEquipDablNum());
				if (entryByEquipDablNum == null) {
					entryByEquipDablNum = new ArrayList<Long>();
					trmsEquipDablNum2RingDablNum
							.put(omn33830.getTrmsEquipDablNum(),
									entryByEquipDablNum);
				}
				entryByEquipDablNum.add(omn33830.getTrmsRingDablNum());

			}
			return omn33830List;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}

	}

	/**
	 * 전송장비 회선장애 발생(추가)
	 * 
	 * <pre>
	 * 	DB 정상 처리후, 클라이언트에 메세지 보내기
	 * </pre>
	 * 
	 * @param trmsEquipDablNum
	 * @param lineInfoList
	 * @return List<OMN33820>
	 */
	private List<OMN33820> insertLineAlarm(Long trmsEquipDablNum,
			List<LineInfoVo> lineInfoList) {

		try {

			List<OMN33820> omn33820List = new AlarmDao().insertLineAlarm(
					trmsEquipDablNum, lineInfoList);

			return omn33820List;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}

	}

	/**
	 * 서버에 접속된 클라이언트에 JSON 형식의 메세지 보내기
	 * 
	 * @param omn33810
	 * @param omn33820List
	 * @param omn33830List
	 * @param status
	 * @throws Exception
	 */
	private void onNotify(OMN33810 omn33810, List<OMN33820> omn33820List,
			List<OMN33830> omn33830List, STATUS status) throws Exception {

		// 장비 장애 메세지 보내기
		TeamsAlarmFxEvent teamsAlarmFxEvent = new TeamsAlarmFxEvent();
		teamsAlarmFxEvent.setType(TeamsAlarmFxEvent.ALARM);
		teamsAlarmFxEvent.setStatus(status);
		teamsAlarmFxEvent.setOmn33810(omn33810);
		FxServiceImpl.fxService.onNotify(teamsAlarmFxEvent);
		// 망 장애 메세지 보내기
		if (omn33830List != null) {
			for (OMN33830 omn33830 : omn33830List) {
				teamsAlarmFxEvent.setOmn33820(null);
				teamsAlarmFxEvent.setOmn33830(omn33830);
				FxServiceImpl.fxService.onNotify(teamsAlarmFxEvent);
			}
		}
		// 회선 장애 메세지 보내기
		if (omn33820List != null) {
			for (OMN33820 omn33820 : omn33820List) {
				teamsAlarmFxEvent.setOmn33820(omn33820);
				teamsAlarmFxEvent.setOmn33830(null);
				FxServiceImpl.fxService.onNotify(teamsAlarmFxEvent);
			}
		}
	}

	/**
	 * 장애 데이터 수정
	 * 
	 * @param teamsAlarmFxEvent
	 * @param isNotify
	 */
	public void updateAlarmStatus(TeamsAlarmFxEvent teamsAlarmFxEvent,
			boolean isNotify) {

		try {
			new AlarmDao().updateAlarmStatus(teamsAlarmFxEvent, isNotify);
		} catch (Exception e) {
			Logger.logger.error(e);
		}

	}

	public Map<Long, OMN33810> getTrmsAlarmMap() {
		return trmsAlarmMap;
	}

	/**
	 * 현재 장애중, 해지 할 장애가 있는지 찾아서 반환 (자동 해제 시간 설정)
	 * 
	 * <pre>
	 * TODO 전송망 메세지관리번호로 찾아야 하나, 기존에 쓰던 함수(findTrmsNetAlcdByEquipMdlCdAndReason) 
	 * 			를 사용해도 문제가 없어보여서 일단 사용한다.,
	 * 			추후 성능 문제시 trmsNetEquipMsgMgmtNum 사용으로 변경한다.
	 * </pre>
	 * 
	 * @return
	 */
	public List<OMN33810> findReleaseAlarmList() {

		List<OMN33810> releaseAlarmList = new ArrayList<OMN33810>();

		for (OMN33810 omn33810 : trmsAlarmMap.values()) {

			TrmsNetAlcdVo trmsNetAlcdVo = findTrmsNetAlcdByEquipMdlCdAndReason(
					omn33810.getEquipMdlCd(), omn33810.getCmprCharStrVal());

			if (trmsNetAlcdVo == null) {
				continue;
			}

			if (trmsNetAlcdVo.getAutoDablRlseSecTms() > 0) {

				Long autoRlseTime = System.currentTimeMillis()
						- (trmsNetAlcdVo.getAutoDablRlseSecTms() * 1000);

				if (omn33810.getOccrRcvDtm().getTime() > autoRlseTime) {
					releaseAlarmList.add(omn33810);
				}
			}

		}

		return releaseAlarmList;
	}

	/**
	 * 링타입이 오픈인지 고립인지 판단
	 * 
	 * <pre>
	 *  링 타입을 구해 망정보 VO에 바로 대입 한다.
	 * </pre>
	 * 
	 * <pre>
	 * 	한 장비를 기준으로, A, B 포트가 전부 LOS 장애가 활성화중이면,
	 *  링 고립 장애(NET_DABL_CD_RING_ISOLATION) 를 셋팅
	 *  
	 *  tip. 레거시스 소스확인결과 LOS 장애만 하지않고, 모든 링장애를 기준으로 처리함.
	 * </pre>
	 * 
	 * @return void
	 * @throws Exception
	 */
	private void findRingTypeAndSetNetDablCd(TrmsNetEventVo trmsNetEventVo,
			List<NetworkInfoVo> networkInfoList, AdamsApiApp api)
			throws Exception {

		// 새로운 장애(추가 예정)
		String equipId = trmsNetEventVo.getEquipId();
		String portDescr = trmsNetEventVo.getPortDescr();

		for (NetworkInfoVo networkInfoVo : networkInfoList) {

			String netDablCd = TeamsAlarmCode.NET_DABL_CD_RING_OPEN;

			// 1. 망 번호를 기준 현재 망 장애가 있는지 찾음
			List<Long> ringDablNumList = trmsNetAlarmMapByRingDablNum
					.get(networkInfoVo.getNetNum());

			// 2. 현재 링 장애가 없으면 무시 (링 오픈)
			if (ringDablNumList != null) {

				// 3. 장애 만들기 [장비ID@_@포트]
				Map<String, String> alarmMap = new HashMap<String, String>();
				alarmMap.put(equipId + "@_@" + portDescr, "");
				for (Long ringDablNum : ringDablNumList) {
					OMN33830 omn33830 = trmsNetAlarmMap.get(ringDablNum);
					OMN33810 omn33810 = trmsAlarmMap.get(omn33830
							.getTrmsEquipDablNum());
					alarmMap.put(
							omn33810.getEquipId() + "@_@"
									+ omn33810.getPortDesc(), "");
				}

				// 4. 망내역 하나씩 장애 매칭
				LOOP: for (Long ringDablNum : ringDablNumList) {
					OMN33830 omn33830 = trmsNetAlarmMap.get(ringDablNum);
					if (!omn33830.getNetDablCd().equals(
							TeamsAlarmCode.NET_DABL_CD_TRUNK_ALARM)) {
						// 링 번호로 망 내역 찾기(트렁크 제외...검토)
						List<LineNetworkBaseVo> lnBaseList = api
								.findNetworkListByNetNum(omn33830.getNetNum());
						for (LineNetworkBaseVo lineNetworkBaseVo : lnBaseList) {
							String key = lineNetworkBaseVo.getEquipId() + "@_@"
									+ lineNetworkBaseVo.getEquipAPortInfo();
							if (alarmMap.containsKey(key)) {
								key = lineNetworkBaseVo.getEquipId() + "@_@"
										+ lineNetworkBaseVo.getEquipBPortInfo();
								if (alarmMap.containsKey(key)) {
									Logger.logger
											.info("링 고립(RING ISOLATION) 타입, 망번호 : {}, 장비ID : {}, A : {}, B : {}",
													lineNetworkBaseVo
															.getNetNum(),
													lineNetworkBaseVo
															.getEquipId(),
													lineNetworkBaseVo
															.getEquipAPortInfo(),
													lineNetworkBaseVo
															.getEquipBPortInfo());
									netDablCd = TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION;
									break LOOP;
								}
							}
						}
					}
				}
			}
			networkInfoVo.setNetDablCd(netDablCd);
		}
	}

	/**
	 * 저속급 TDM점검결과 '불일치' 일시 장애 생성
	 * 
	 */
	public void findCrsAlarmAndFireEvent() {

		try {
			List<Map<String, Object>> crsAlarmList = new AlarmDao()
					.selectCrsDisaccordCount();
			for (Map<String, Object> crsAlarm : crsAlarmList) {
				String equipId = String.valueOf(crsAlarm.get("EQUIP_ID"));
				String pargEquipId = String.valueOf(crsAlarm
						.get("PARG_EQUIP_ID"));
				String[] arrEquipId = new String[] { equipId, pargEquipId };

				double d = Double.valueOf(crsAlarm.get("DISACCORD_COUNT") + "");
				int disaccordCount = (int) d;

				TrmsNetEventVo trmsNetEventVo = new TrmsNetEventVo();
				if (disaccordCount > 0) { // 장애 발생
					trmsNetEventVo.setDablGrCd(DABL_GR_CD.Major.getCode());
					trmsNetEventVo.setOccurTime(FxApi.getDate());
					trmsNetEventVo.setRecvTime(System.currentTimeMillis());
					trmsNetEventVo.setReason(String.format(
							"TDM-CRS 불일치 : %s 건", disaccordCount));
					trmsNetEventVo.setPortDescr(TrmsNetEventVo.TDM_CRS);
					trmsNetEventVo
							.setFullMsg(String
									.format("EQUIP_ID : %s, PARG_EQUIP_ID : %s, DISACCORD_COUNT : %d",
											equipId, pargEquipId,
											disaccordCount));
					trmsNetEventVo.setIgnoreOmn33812(true);
					for (String id : arrEquipId) {
						trmsNetEventVo.setEquipId(id);
						makeOccurAlarm(trmsNetEventVo);
					}
				} else { // 장애 해제
					trmsNetEventVo
							.setClearTime(String.valueOf(FxApi.getDate()));
					trmsNetEventVo.setRecvTime(System.currentTimeMillis());
					trmsNetEventVo.setPortDescr(TrmsNetEventVo.TDM_CRS);
					trmsNetEventVo
							.setFullMsg(String
									.format("EQUIP_ID : %s, PARG_EQUIP_ID : %s, DISACCORD_COUNT : %d",
											equipId, pargEquipId,
											disaccordCount));
					for (String id : arrEquipId) {
						trmsNetEventVo.setEquipId(id);
						clearAlarm(trmsNetEventVo);
					}
				}
			}
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	// /////////////////////////////////////////////////////////////// 기존 소스 를
	// 보관 ( 레거시 소스 보고 작성 한 부분)

	/**
	 * 링타입이 오픈인지 고립인지 판단
	 * 
	 * <pre>
	 *  링 타입을 구해 망정보 VO에 바로 대입 한다.
	 * </pre>
	 * 
	 * <pre>
	 * ***** 레거시 소스 ***** *
	 *  링이 고립인지 판단.
	 *  링 고립 장애 조회 (NET_NUM, 현재장애 떠있고, 리즌이 같음)
	 *  
	 *  		"select "\
	 *  		"almnet.network_id, almnet.network_name, almnet.ne_id, almnet.port_descr "
	 *  		"from "\ "TBL_STS_ALARM alm, TBL_STS_ALARM_NETWORK almnet "\
	 *  		"where 1=1 "\ "and alm.alarm_no = almnet.nms_alarm_no "\
	 *  		"and almnet.clear_flag='N' "\ "and almnet.network_id=:f1<int> "\
	 *  		"and almnet.network_alarm_reason_cd=NET_DABL_CD_NODE_ISOLATION  "
	 *  
	 *  있으면 링(고립) 장애 발생 리턴
	 *  없으면 링오픈 장애 조회 (NET_NUM, 현재장애 떠있고, 리즌이 같음)
	 *  
	 *  		"select "\
	 *  		"almnet.network_id, almnet.network_name, almnet.ne_id, almnet.port_descr "
	 *  	"from "\ "TBL_STS_ALARM alm, TBL_STS_ALARM_NETWORK almnet "\
	 *  		"where 1=1 "\ "and alm.alarm_no = almnet.nms_alarm_no "\
	 *  		"and almnet.clear_flag='N' "\ "and almnet.network_id=:f1<int> "\
	 *  		"and almnet.network_alarm_reason_cd=NET_DABL_CD_RING_OPEN  "
	 *  
	 *  없으면 링(오픈) 장애 발생 리턴
	 *  있으면 해당 링에 속한 노드들의 링크 정보를 읽음
	 *  
	 *  		"select "\
	 *  "	'src' gubun, net.network_id, netp.seq, ne.ne_id, netp.b_port_descr "
	 *  		"from "\ "	%s net, %s netp, %s ne, %s nm "\ "where 1=1 "\
	 *  		"and net.network_id = netp.network_id "\
	 *  		"and netp.ne_id = ne.ne_id "\ "	and ne.model_id = nm.model_id "\
	 *  		"and nm.model_type_cd not in ('025002') "\
	 *  		"and net.network_id=:f1<int> "\ "union "\ "select "\
	 *  		"'dst' gubun, net.network_id, netp.seq, ne.ne_id, netp.a_port_descr "
	 *  		"from "\ "	%s net, %s netp, %s ne, %s nm "\ "where 1=1 "\
	 *  		"and net.network_id = netp.network_id "\
	 *  		"and netp.ne_id = ne.ne_id "\ "   and ne.model_id = nm.model_id "\
	 *  		"and nm.model_type_cd not in ('025002') "\
	 *  		"and net.network_id=:f2<int> "\ "order by "\ "gubun desc, seq "
	 *  
	 *  없으면 링(고립) 장애 발생
	 *  있으면 대국포트 체크
	 *  
	 *  		for (int j=0; j<neNetworkTbl.m_vecNetAlarm.size(); j++) { // 대국 포트 발생 체크 
	 *  			bConNeLinkPort = neNetworkTbl.isConNeLinkPort(iNeId, szPortDescr,
	 *  			neNetworkTbl.m_vecNetAlarm[j].iNeId,
	 *  			neNetworkTbl.m_vecNetAlarm[j].szPortDescr);
	 *  
	 *  		if (bConNeLinkPort) break; }
	 *  
	 *  true면 링(오픈) 장애 발생,
	 *  false 면 링(고립) 장애 발생
	 * </pre>
	 * 
	 * @return void
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private void findRingTypeAndSetNetDablCd__delete(
			TrmsNetEventVo trmsNetEventVo, List<NetworkInfoVo> networkInfoList)
			throws Exception {

		List<OMN33830> tempList = null;
		for (NetworkInfoVo networkInfoVo : networkInfoList) {

			tempList = new ArrayList<OMN33830>();

			// 1. 망 번호를 기준 현재 망 장애가 있는지 찾음
			List<Long> ringDablNumList = trmsNetAlarmMapByRingDablNum
					.get(networkInfoVo.getNetNum());

			if (ringDablNumList != null) {
				// 2. 현재 망 장애 존재
				for (Long ringDablNum : ringDablNumList) {
					OMN33830 omn33830 = trmsNetAlarmMap.get(ringDablNum);
					if (omn33830.getNetDablCd().equals(
							TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION)) {
						// 2-1. 고립 장애가 존재 하면 고립장애 셋팅
						networkInfoVo
								.setNetDablCd(TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION);
					} else {
						// 2- 2. 링 오픈 장애면 임시 리스트에 추가
						tempList.add(omn33830);
					}
				}
			} else {
				// 3. 없으면 링 오픈 장애 발생 셋팅
				networkInfoVo
						.setNetDablCd(TeamsAlarmCode.NET_DABL_CD_RING_OPEN);
			}

			// 4. 링 오픈 장애가 존재시 ,,,,(?) , TODO 로직이 이해가 안감,, 레거시와 동일하게 소스 작성(추후에
			// 확인후 보완 및 삭제 처리 함)
			if (networkInfoVo.getNetDablCd() == null) {

				boolean bConNeLinkPort = false;

				Map<String, NetworkLinkVo> networkLinkMap = new AdamsDao()
						.selectNetworkLinkListAndMakeLinkData(networkInfoVo
								.getNetNum());

				if (networkLinkMap != null && networkLinkMap.size() > 0) {

					for (OMN33830 temp : tempList) {

						OMN33810 omn33810 = trmsAlarmMap.get(temp
								.getTrmsEquipDablNum());
						if (omn33810 != null) {
							bConNeLinkPort = isConNeLinkPort__delete(
									trmsNetEventVo.getEquipId(),
									trmsNetEventVo.getPortDescr(),
									omn33810.getEquipId(),
									omn33810.getPortDesc(), networkLinkMap);
							if (bConNeLinkPort) {
								break;
							}
						}
					}
				}

				if (bConNeLinkPort) {
					networkInfoVo
							.setNetDablCd(TeamsAlarmCode.NET_DABL_CD_RING_OPEN);
				} else {
					networkInfoVo
							.setNetDablCd(TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION);
				}

			}
		}
	}

	/**
	 * 
	 * 
	 * <pre>
	 * * ****** 레거시소스 cfNeNetworkInfoTbl.cpp ****** *
	 * 	// src와 dst가 동일한 장비/포트인 경우 링고립 장애 케이스가 아니다.
	 * 	if ( _src_ne_id == _dst_ne_id && !strcmp(_src_port, _dst_port) )
	 * 	{
	 * 		return true;
	 * 	}
	 * 
	 * 
	 * 	sprintf(szKey, "%d^%s", _src_ne_id, _src_port);
	 * 	
	 * 	itr = m_mapLinkPort.find(szKey);
	 * 	if (itr == m_mapLinkPort.end())
	 * 	{
	 * 		return false;
	 * 	}
	 * 	else
	 * 	{
	 * 		if (
	 * 			(itr->second.src_ne_id==_dst_ne_id && !strcmp(itr->second.src_port_descr, _dst_port)) ||
	 * 			(itr->second.dst_ne_id==_dst_ne_id && !strcmp(itr->second.dst_port_descr, _dst_port)) 
	 * 		)
	 * 		{
	 * 			return true;
	 * 		}
	 * 		else
	 * 		{
	 * 			return false;
	 * 		}
	 * 	}
	 * 
	 * </pre>
	 * 
	 * @param srcEquipId
	 * @param srcPortDescr
	 * @param dstEquipId
	 * @param dstPortDescr
	 * @return
	 */
	private boolean isConNeLinkPort__delete(String srcEquipId,
			String srcPortDescr, String dstEquipId, String dstPortDescr,
			Map<String, NetworkLinkVo> networkLinkMap) {

		// src와 dst가 동일한 장비/포트인 경우 링고립 장애 케이스가 아니다.
		if (srcEquipId.equals(dstEquipId) && srcPortDescr.equals(dstPortDescr)) {
			return true;
		}

		NetworkLinkVo networkLinkVo = networkLinkMap.get(srcEquipId + "@_@"
				+ srcPortDescr);
		if (networkLinkVo == null) {
			return true;
		} else {
			if ((networkLinkVo.getSrcEquipId().equals(dstEquipId) && networkLinkVo
					.getSrcPortDescr().equals(dstPortDescr))
					|| (networkLinkVo.getDstEquipId().equals(dstEquipId) && networkLinkVo
							.getDstPortDescr().equals(dstPortDescr))) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 멀티 포트 찾기
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<LineInfoVo> findMultiPortLine__delete() {
		/**
		 * <pre>
		 * CF_NETWORK c, CF_LINE_PATH_DETAIL a_pd on c.a_port_detail_seq = a_pd.seq(+)
		 * CF_NETWORK c, CF_LINE_PATH_DETAIL b_pd on c.b_port_detail_seq = b_pd.seq(+)
		 * CF_LINE_PATH_DETAIL 정보가 없음.
		 * 아마 필요없을 듯 함. 확인 필요(우선 보류)
		 * </pre>
		 */
		return null;
	}

	/**
	 * 임시 회선 찾기
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	private List<LineInfoVo> findTempLine__delete() {
		/**
		 * <pre>
		 * 임시 회선 장애 생성
		 * 	ㄴ 회선 장애가 하나도 존재 안할시
		 * 	ㄴ DCS나 AON 장비의 E1 장애인 경우
		 * 아마 필요없을 듯 함. 확인 필요(우선 보류)
		 * </pre>
		 */
		return null;
	}

	// ///////////////////////////////////////////////////////////////////////////////////

}

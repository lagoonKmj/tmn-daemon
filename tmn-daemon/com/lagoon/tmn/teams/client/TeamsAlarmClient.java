package com.lagoon.tmn.teams.client;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONObject;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.NioClient;
import subkjh.bas.net.soproth.FxJsonSoproth;

import com.google.gson.Gson;
import com.lagoon.tmn.teams.app.vo.TeamsAlarmFxEvent;
import com.lagoon.tmn.teams.client.dao.TeamsAlarmDao;
import com.lagoon.tmn.teams.client.vo.OIV10300;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsAlarmCode;
import com.lagoon.tmn.teams.co.vo.LineVo;
import com.lagoon.tmn.teams.co.vo.NetworkInfoVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33820;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;

import fxms.bas.co.noti.FxEvent.STATUS;

/**
 * ADAMS 팀스 서버에서 보내는 장애를 받아 데이터 포멧을 ADAMS 웹에 맞게 변경 후 웹푸시 서버로 보내는 클라이언트
 * 
 * <pre>
 * 기존 사용하던 포멧 형식을 그대로 사용하여, 동작에 무리없게 구현
 *   ㄴ 기존 소스 : com.skb.mi.realtime.RealTimeCollectTeams.java
 * </pre>
 * 
 * @author lagoon (강명중)
 *
 */
@SuppressWarnings({ "unchecked", "unused" })
public class TeamsAlarmClient extends SingleStart {

	public static void main(String[] args) {
		new TeamsAlarmClient(args);
	}

	public TeamsAlarmClient(String[] args) {
		super("TeamsAlarmClientService", args);
	}

	final private String EQUIP_DABL_LCL_NM = "장비장애";
	final private String EQUIP_DABL_MCL_CD = "01201";
	final private String EQUIP_DABL_MCL_NM = "전송망장비장애";
	final private String EQUIP_DABL_CD = "012001";
	final private String EQUIP_DABL_NM = "TEAMS 장비장애";

	final private String CIRCUIT_DABL_LCL_NM = "회선장애";
	final private String CIRCUIT_DABL_MCL_CD = "04201";
	final private String CIRCUIT_DABL_MCL_NM = "전송망회선장애";
	final private String CIRCUIT_DABL_CD = "042001";
	final private String CIRCUIT_DABL_NM = "TEAMS 회선장애";

	final private String RING_DABL_LCL_NM = "회선장애";
	final private String RING_DABL_MCL_CD = "04203";
	final private String RING_DABL_MCL_NM = "전송망링장애";
	final private String RING_DABL_CD = "042003";
	final private String RING_DABL_NM = "TEAMS 링장애";

	final private String NMS_NM = "TEAMS";

	final private String NET_CL_CD = "02"; // 전송망
	final private String NET_CL_NM = "전송망";

	final private String EQUIP_FAULT = "01"; // 01: 장비 장애
	final private String CIRCUIT_FAULT = "04"; // 04: 회선 장애
	final private String TRAFFIC_FAULT = "03"; // 03: 트래픽 장애

	final private String GENERATE_EVENT = "GEN";
	final private String CLEAR_EVENT = "CLR";

	final private String GENERATED_FAULT_NM = "장애중";
	final private String CLEARED_FAULT_NM = "장애해제";

	final private String GENERATED_FAULT_CD = "01";
	final private String CLEARED_FAULT_CD = "02";

	final private String TRMS_NET_MGMT_OWNR_SKB_CD = "03";

	private final int MIN_3 = 1000 * 60 * 3;

	private NioClient client;
	private FxJsonSoproth soproth;
	private SendJsonThread jsonSender;

	private Map<String, OIV10300> tpoMap;
	private Map<String, String> equipMdlNmMap;
	private Map<String, List<LineVo>> lineMap;
	private Map<String, NetworkInfoVo> networkInfoMap;

	private long echoTime;

	// @Override
	protected void run() {

		Logger.logger.info(Logger.makeString("TeamsAlarmClient Start"));

		try {

			jsonSender = new SendJsonThread(pushIpAddress, pushPortNo, false);

			// 1. 기초 데이터 적재
			reload();

			// 2. 클라이언트 시작
			startClient();

			// 3. 타이머 시작
			setTimer();

		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	private void reload() {
		try {
			tpoMap = new TeamsAlarmDao().selectTpo2Map();
			equipMdlNmMap = new TeamsAlarmDao().selectEquipModel2Map();
			lineMap = new TeamsAlarmDao().selectLine2Map();
			networkInfoMap = new TeamsAlarmDao().selectNetworkInfo2Map();
		} catch (Exception e) {
			Logger.logger.error(e);
		}
	}

	private void setTimer() {
		TimerTask task = new TimerTask() {
			int tickCount = 0;

			@Override
			public void run() {

				// 클라이언트 끊김
				if (client.isAlive() == false) {
					soproth = null;
					tickCount = 0;
					echoTime = 0;
					startClient();
					return;
				}

				// 에코 메세지 전송(3분)
				try {
					TeamsAlarmFxEvent teamsAlarmFxEvent = new TeamsAlarmFxEvent();
					teamsAlarmFxEvent.setType(TeamsAlarmFxEvent.ECHO);
					soproth.send(FxJsonSoproth.makePduByte(teamsAlarmFxEvent
							.toJson()));
				} catch (Exception e) {
					Logger.logger.error("ADAMS-TEAMS CLIENT ERROR : " + e);
					restartClient();
				}

				// 틱 카운트가 10보다 크면 데이터를 리로드 한번해준다.(대략~30분)
				if (tickCount > 10) {
					tickCount = 0;
					reload();
					// 헬스체크 (에코타임이 6분 전 이면, 리 컨넥트)
					long currentTime = System.currentTimeMillis();
					if ((currentTime - echoTime) > MIN_3 * 2) {
						restartClient();
					}

				} else {
					tickCount++;
				}
			}
		};
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(task, MIN_3, MIN_3);
	}

	private void startClient() {

		Gson gson = new Gson();
		soproth = new FxJsonSoproth() {
			@Override
			protected void processBody(int length, byte[] bytes) {

				String json = null;
				try {
					json = new String(bytes, "UTF-8");
				} catch (UnsupportedEncodingException e1) {
					Logger.logger.error(e1);
				}

				TeamsAlarmFxEvent teamsAlarmFxEvent = gson.fromJson(json,
						TeamsAlarmFxEvent.class);

				// TODO
				if (teamsAlarmFxEvent.getType() < 0) {
					return;
				}

				switch (teamsAlarmFxEvent.getType()) {

				case TeamsAlarmFxEvent.LOGIN:

					break;

				case TeamsAlarmFxEvent.ECHO:
					echoTime = System.currentTimeMillis();
					break;

				case TeamsAlarmFxEvent.ALARM:
				case TeamsAlarmFxEvent.NO_SEND_ALARM:
					if (processAlarm(teamsAlarmFxEvent)) {
						try {
							teamsAlarmFxEvent.setMsg("ALARM PROCESS OK");
							soproth.send(FxJsonSoproth
									.makePduByte(teamsAlarmFxEvent.toJson()));
						} catch (Exception e) {
							Logger.logger.error(e);
						}
					}
					break;
				}

			}

			@Override
			protected void initProcess() throws Exception {
				TeamsAlarmFxEvent teamsAlarmFxEvent = new TeamsAlarmFxEvent();
				teamsAlarmFxEvent.setType(TeamsAlarmFxEvent.LOGIN);
				teamsAlarmFxEvent.setUserId("ADAMS_TMN");
				teamsAlarmFxEvent.setPasswd("ADAMS_TMN");
				soproth.send(FxJsonSoproth.makePduByte(teamsAlarmFxEvent
						.toJson()));
			}

			private boolean processAlarm(TeamsAlarmFxEvent teamsAlarmFxEvent) {
				OMN33810 omn33810 = teamsAlarmFxEvent.getOmn33810();
				if (omn33810 == null) {
					return false;
				}

				try {
					if (teamsAlarmFxEvent.getStatus() == STATUS.added) {

						switch (teamsAlarmFxEvent.getAlarmType()) {

						case TeamsAlarmFxEvent.EQUIP_ALARM:
							onProcessByEquipAlarm(omn33810);
							break;
						case TeamsAlarmFxEvent.NETWORK_ALARM:
							onProcessByNetAlarm(omn33810,
									teamsAlarmFxEvent.getOmn33830());
							break;
						case TeamsAlarmFxEvent.LINE_ALARM:
							onProcessByLineAlarm(omn33810,
									teamsAlarmFxEvent.getOmn33820());
							break;

						}

					} else {
						onProcessByEquipAlarmClear(omn33810);
					}
				} catch (Exception e) {
					Logger.logger.error("ADAMS-TEAMS CLIENT ERROR : " + e);
					return false;
				}

				return true;
			}
		};

		try {

			client = new NioClient();
			client.setKeepAlive(true);
			client.startClient("ADAMS-TEAMS CLIENT", appSvrIpAddress,
					appSvrPortNo, soproth);

		} catch (Exception e) {
			Logger.logger.error("ADAMS-TEAMS CLIENT ERROR : " + e);
			restartClient();
		}
	}

	/**
	 * TODO 클라이언트 재접속(추후 보강)
	 * 
	 */
	private void restartClient() {

		try {
			Thread.sleep(3000L);
			Logger.logger.debug("클라이언트 재접속");
			client = new NioClient();
			client.setKeepAlive(true);
			client.startClient("ADAMS-TEAMS CLIENT", appSvrIpAddress,
					appSvrPortNo, soproth);
		} catch (Exception e) {
			Logger.logger.error("ADAMS-TEAMS CLIENT ERROR : " + e);
			restartClient();
		}

	}

	private void onProcessByEquipAlarmClear(OMN33810 omn33810) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NMS_CD", nmsCd);
		jsonObject.put("NMS_NM", NMS_NM);
		jsonObject.put("NET_CL_CD", NET_CL_CD);
		jsonObject.put("NET_CL_NM", NET_CL_NM);
		jsonObject.put("NMS_DABL_NUM_CTT",
				String.valueOf(omn33810.getTrmsEquipDablNum()));
		jsonObject.put("DABL_ST_CD", CLEARED_FAULT_CD);
		jsonObject.put("DABL_ST_NM", CLEARED_FAULT_NM);
		jsonObject.put("RLSE_DTM", omn33810.getRlseDtm());
		jsonObject.put("RLSE_RCV_DTM", MiscUtils.getDateTime());
		jsonObject.put("kind", CLEAR_EVENT);

		String log = Logger.makeString("장비 장애 해제",
				omn33810.getTrmsEquipDablNum(),
				Logger.makeSubString("송신 JSON", jsonObject.toJSONString()));
		Logger.logger.debug(log);

		jsonSender.put(jsonObject);
	}

	private void onProcessByEquipAlarm(OMN33810 omn33810) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NMS_CD", nmsCd);
		jsonObject.put("NMS_NM", NMS_NM);
		jsonObject.put("NET_CL_CD", NET_CL_CD);
		jsonObject.put("NET_CL_NM", NET_CL_NM);
		jsonObject.put("LINE_DABL_TYP_CD", "N");
		jsonObject.put("kind", GENERATE_EVENT);
		jsonObject.put("EQUIP_ID", omn33810.getEquipId());
		jsonObject.put("EQUIP_TID_VAL", omn33810.getEquipTidVal());
		jsonObject.put("EQUIP_IP_ADDR", omn33810.getEquipIpAddr());
		jsonObject.put("DABL_GR_CD", omn33810.getDablGrCd());
		jsonObject.put("PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("DABL_DESC", omn33810.getDablMsgCtt());
		jsonObject.put("TPO_CD", omn33810.getTpoCd());
		jsonObject.put("TPO_NM", getTpoName(omn33810.getTpoCd()));
		jsonObject.put ("MGMT_TPO_CD", getMgmtTpoCd(omn33810.getTpoCd()));
		jsonObject.put("OCCR_DTM", omn33810.getOccrDtm());
		jsonObject.put("OPER_DABL_YN", (omn33810.isOperDablYn() == true ? "Y"
				: "N"));
		jsonObject.put("OCCR_RCV_DTM", MiscUtils.getDateTime());
		jsonObject.put("NMS_DABL_NUM_CTT",
				String.valueOf(omn33810.getTrmsEquipDablNum()));
		jsonObject.put("DABL_ST_CD", GENERATED_FAULT_CD);
		jsonObject.put("DABL_ST_NM", GENERATED_FAULT_NM);
		jsonObject.put("EQUIP_NM", omn33810.getEquipNm());
		jsonObject.put("EQUIP_MDL_CD", omn33810.getEquipMdlCd());
		jsonObject.put("EQUIP_MDL_NM",
				getEquipModelName(omn33810.getEquipMdlCd()));
		jsonObject.put("DABL_LCL_CD", EQUIP_FAULT);
		jsonObject.put("DABL_LCL_NM", EQUIP_DABL_LCL_NM);
		jsonObject.put("DABL_MCL_CD", EQUIP_DABL_MCL_CD);
		jsonObject.put("DABL_MCL_NM", EQUIP_DABL_MCL_NM);
		jsonObject.put("DABL_CD", EQUIP_DABL_CD);
		jsonObject.put("DABL_NM", EQUIP_DABL_NM);
		jsonObject.put("DABL_OCCR_LOC_DESC", omn33810.getDablOccrLocDesc());
		jsonObject.put("DABL_PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("TRMS_NET_MGMT_OWNR_CD", TRMS_NET_MGMT_OWNR_SKB_CD);
		jsonObject.put("USE_USG_DESC", ""); // OMN30110 에 있는 필드
		jsonObject.put("TROOM_NM", ""); // OMN30110 에 있는 필드

		String log = Logger.makeString("장비 장애 발생",
				omn33810.getTrmsEquipDablNum(),
				Logger.makeSubString("송신 JSON", jsonObject.toJSONString()));
		Logger.logger.debug(log);

		jsonSender.put(jsonObject);
	}

	private void onProcessByNetAlarm(OMN33810 omn33810, OMN33830 omn33830) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NMS_CD", nmsCd);
		jsonObject.put("NMS_NM", NMS_NM);
		jsonObject.put("NET_CL_CD", NET_CL_CD);
		jsonObject.put("NET_CL_NM", NET_CL_NM);
		jsonObject.put("NMS_ALARM_NO",
				String.valueOf(omn33830.getTrmsRingDablNum())); // ?
		jsonObject.put("ALARM_NO", omn33830.getTrmsRingDablNum()); // ?
		jsonObject.put("kind", GENERATE_EVENT);
		jsonObject.put("EQUIP_ID", omn33810.getEquipId());
		jsonObject.put("EQUIP_TID_VAL", omn33810.getEquipTidVal());
		jsonObject.put("EQUIP_IP_ADDR", omn33810.getEquipIpAddr());
		jsonObject.put("DABL_GR_CD", omn33810.getDablGrCd());
		jsonObject.put("PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("DABL_DESC", omn33810.getDablMsgCtt());
		jsonObject.put("TPO_CD", omn33810.getTpoCd());
		jsonObject.put("TPO_NM", getTpoName(omn33810.getTpoCd()));
		jsonObject.put ("MGMT_TPO_CD", getMgmtTpoCd(omn33810.getTpoCd()));
		jsonObject.put("OCCR_DTM", omn33810.getOccrDtm());
		jsonObject.put("OCCR_RCV_DTM", MiscUtils.getDateTime());
		jsonObject.put("OPER_DABL_YN", (omn33810.isOperDablYn() == true ? "Y"
				: "N"));
		jsonObject.put("NMS_DABL_NUM_CTT",
				String.valueOf(omn33830.getTrmsRingDablNum()));
		jsonObject.put("DABL_ST_CD", GENERATED_FAULT_CD);
		jsonObject.put("DABL_ST_NM", GENERATED_FAULT_NM);
		jsonObject.put("EQUIP_NM", omn33810.getEquipNm());
		jsonObject.put("EQUIP_MDL_CD", omn33810.getEquipMdlCd());
		jsonObject.put("EQUIP_MDL_NM",
				getEquipModelName(omn33810.getEquipMdlCd()));
		jsonObject.put("DABL_LCL_CD", CIRCUIT_FAULT);
		jsonObject.put("DABL_LCL_NM", CIRCUIT_DABL_LCL_NM);
		jsonObject.put("DABL_MCL_CD", RING_DABL_MCL_CD);
		jsonObject.put("DABL_MCL_NM", RING_DABL_MCL_NM);
		jsonObject.put("DABL_CD", RING_DABL_CD);
		jsonObject.put("DABL_NM", RING_DABL_NM);
		if (omn33830.getNetDablCd().equals(
				TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION)) {
			jsonObject.put("NET_DABL_SRC_CTT", "RING_ISOLATION");
			jsonObject.put("LINE_DABL_TYP_CD", "I");
		} else {
			jsonObject.put("NET_DABL_SRC_CTT", "RING_OPEN");
			jsonObject.put("LINE_DABL_TYP_CD", "N");
		}
		jsonObject.put("DABL_OCCR_LOC_DESC", omn33810.getDablOccrLocDesc());
		jsonObject.put("DABL_PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("NET_NUM", omn33830.getNetNum());
		jsonObject.put("RING_NM", omn33830.getNwNm());
		jsonObject.put("TRUNK_NUM", omn33830.getTrunkNum());
		jsonObject.put("TRUNK_NM", omn33830.getNwNm()); // 확인필요
		jsonObject.put("TRMS_NET_MGMT_OWNR_CD", TRMS_NET_MGMT_OWNR_SKB_CD);
		jsonObject.put("USE_USG_DESC", ""); // OMN30110 에 있는 필드
		jsonObject.put("TROOM_NM", ""); // OMN30110 에 있는 필드

		String log = Logger.makeString("망 장애 발생",
				omn33830.getTrmsRingDablNum(),
				Logger.makeSubString("송신 JSON", jsonObject.toJSONString()));
		Logger.logger.debug(log);

		jsonSender.put(jsonObject);
	}

	private void onProcessByLineAlarm(OMN33810 omn33810, OMN33820 omn33820) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("NMS_CD", nmsCd);
		jsonObject.put("NMS_NM", NMS_NM);
		jsonObject.put("NET_CL_CD", NET_CL_CD);
		jsonObject.put("NET_CL_NM", NET_CL_NM);
		jsonObject.put("kind", GENERATE_EVENT);
		jsonObject.put("NMS_ALARM_NO",
				String.valueOf(omn33820.getTrmsLineDablNum())); // ?
		jsonObject.put("ALARM_NO", omn33820.getTrmsLineDablNum()); // ?
		jsonObject.put("EQUIP_ID", omn33810.getEquipId());
		jsonObject.put("EQUIP_TID_VAL", omn33810.getEquipTidVal());
		jsonObject.put("EQUIP_IP_ADDR", omn33810.getEquipIpAddr());
		jsonObject.put("DABL_GR_CD", omn33810.getDablGrCd());
		jsonObject.put("PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("DABL_DESC", omn33810.getDablMsgCtt());
		jsonObject.put("TPO_CD", omn33810.getTpoCd());
		jsonObject.put("TPO_NM", getTpoName(omn33810.getTpoCd()));
		jsonObject.put ("MGMT_TPO_CD", getMgmtTpoCd(omn33810.getTpoCd()));
		jsonObject.put("OCCR_DTM", omn33810.getOccrDtm());
		jsonObject.put("OCCR_RCV_DTM", MiscUtils.getDateTime());
		jsonObject.put("OPER_DABL_YN", (omn33810.isOperDablYn() == true ? "Y"
				: "N"));
		jsonObject.put("NMS_DABL_NUM_CTT",
				String.valueOf(omn33820.getTrmsLineDablNum()));
		jsonObject.put("DABL_ST_CD", GENERATED_FAULT_CD);
		jsonObject.put("DABL_ST_NM", GENERATED_FAULT_NM);
		jsonObject.put("EQUIP_NM", omn33810.getEquipNm());
		jsonObject.put("EQUIP_MDL_CD", omn33810.getEquipMdlCd());
		jsonObject.put("EQUIP_MDL_NM",
				getEquipModelName(omn33810.getEquipMdlCd()));
		jsonObject.put("DABL_OCCR_LOC_DESC", omn33810.getDablOccrLocDesc());
		jsonObject.put("DABL_PORT_DESC", omn33810.getPortDesc());
		jsonObject.put("DABL_LCL_CD", CIRCUIT_FAULT);
		jsonObject.put("DABL_LCL_NM", CIRCUIT_DABL_LCL_NM);
		jsonObject.put("DABL_MCL_CD", CIRCUIT_DABL_MCL_CD);
		jsonObject.put("DABL_MCL_NM", CIRCUIT_DABL_MCL_NM);
		jsonObject.put("DABL_CD", CIRCUIT_DABL_CD);
		jsonObject.put("DABL_NM", CIRCUIT_DABL_NM);
		jsonObject.put("LINE_NUM", omn33820.getLineNum());
		jsonObject.put("CUST_NUM", String.valueOf(omn33820.getCustNum()));
		jsonObject.put("CUST_NM", "***"); // 고객명은 스윙에서 연동될때부터 *** 로 확인
		jsonObject.put("CHRGR_ID", omn33820.getSaleChrgrId());

		// String userNm =
		// adamsSession.selectOne("RealtimeTeams.selectUserNm",
		// omn33820.getSaleChrgrId());
		// logger.debug(String.format("selectUserNm(%s)->%s",
		// omn33820.getSaleChrgrId(), userNm));

		// if (userNm != null) {
		// jsonObject.put("CHRGR_NM", userNm);
		jsonObject.put("CHRGR_NM", "");
		// }
		jsonObject.put("LINE_NM", omn33820.getLineNm());
		jsonObject.put("LINE_CL_CD", "*");
		jsonObject.put("LINE_CL_NM", "일반회선");
		jsonObject.put("SVC_TECH_MTHD_CD", "*");
		jsonObject.put("SVC_TECH_MTHD_NM", "*");
		jsonObject.put("SUP_TPO_CD", omn33820.getSupTpoCd());
		jsonObject.put("SUP_TPO_NM", getTpoName(omn33820.getSupTpoCd()));
		jsonObject.put("SUB_TPO_CD", omn33820.getSubTpoCd());
		jsonObject.put("SUB_TPO_NM", getTpoName(omn33820.getSubTpoCd()));
		jsonObject.put("SPEED_CD", "*");
		jsonObject.put("SPEED_NM", "*");
		jsonObject
				.put("SVC_MGMT_NUM", String.valueOf(omn33820.getSvcMgmtNum()));
		jsonObject.put("CUST_CHRTIC_CD", "*");
		jsonObject.put("CUST_CHRTIC_NM", "*");

		jsonObject.put("SUP_EQUIP_ID", "");
		jsonObject.put("SUP_EQUIP_NM", "");
		jsonObject.put("SUB_EQUIP_ID", "");
		jsonObject.put("SUB_EQUIP_NM", "");
		jsonObject.put("DMG_LINE_CNT", "0");
		jsonObject.put("TRMS_NET_MGMT_OWNR_CD", TRMS_NET_MGMT_OWNR_SKB_CD);

		if (omn33820.getNetDablCd() != null) {
			if (omn33820.getNetDablCd().equals(
					TeamsAlarmCode.NET_DABL_CD_RING_ISOLATION)) {
				jsonObject.put("NET_DABL_SRC_CTT", "RING_ISOLATION");
				jsonObject.put("LINE_DABL_TYP_CD", "I");
			} else {
				jsonObject.put("NET_DABL_SRC_CTT", "RING_OPEN");
				jsonObject.put("LINE_DABL_TYP_CD", "Y");
			}
		}
		
		List<LineVo> lineList = lineMap.get(omn33820.getLineNum());
		if (lineList == null) {
			jsonObject.put("NET_NUM", "");
			jsonObject.put("RING_NM", "");
			jsonObject.put("TRUNK_NUM", "");
			jsonObject.put("TRUNK_NM", "");
		} else {
			for (LineVo lineVo : lineList) {
				if (!isEmpty(lineVo.getRingNum())) {
					jsonObject.put("NET_NUM", lineVo.getRingNum());
					NetworkInfoVo networkInfoVo = networkInfoMap.get(lineVo
							.getRingNum());
					if (networkInfoVo != null) {
						jsonObject.put("RING_NM", networkInfoVo.getNwNm());
					}
					break;
				}
				if (!isEmpty(lineVo.getTrunkNum())) {
					jsonObject.put("TRUNK_NUM", lineVo.getTrunkNum());
					NetworkInfoVo networkInfoVo = networkInfoMap.get(lineVo
							.getTrunkNum());
					if (networkInfoVo != null) {
						jsonObject.put("TRUNK_NM",
								networkInfoMap.get(networkInfoVo.getNwNm()));
					}
					break;
				}
			}
		}

		jsonObject.put("USE_USG_DESC", ""); // OMN30110 에 있는 필드
		jsonObject.put("TROOM_NM", ""); // OMN30110 에 있는 필드

		String log = Logger.makeString("회선 장애 발생",
				omn33820.getTrmsLineDablNum(),
				Logger.makeSubString("송신 JSON", jsonObject.toJSONString()));
		Logger.logger.debug(log);

		jsonSender.put(jsonObject);
	}

	private String getTpoName(String tpoCd) {
		OIV10300 oiv10300 = tpoMap.get(tpoCd);
		if (oiv10300 == null) {
			return "";
		}
		return oiv10300.getTpoNm();
	}

	private String getMgmtTpoCd(String tpoCd) {
		OIV10300 oiv10300 = tpoMap.get(tpoCd);
		if (oiv10300 == null) {
			return "";
		}
		return oiv10300.getMgmtTpoCd();
	}

	private String getEquipModelName(String equipMdlCd) {
		String equipMdlNm = equipMdlNmMap.get(equipMdlCd);
		if (equipMdlNm == null) {
			return "";
		}
		return equipMdlNm;
	}

	public static boolean isEmpty(String s) {
		if (s == null)
			return true;
		return s.isEmpty();
	}

}

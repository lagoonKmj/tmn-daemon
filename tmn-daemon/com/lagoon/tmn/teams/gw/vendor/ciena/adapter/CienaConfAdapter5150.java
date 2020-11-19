package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.PORT_TYP_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TelnetConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.Ciena;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import subkjh.bas.net.emulator.Emulator;

public class CienaConfAdapter5150 extends TelnetConfAdapter<EquipDcn> implements
		ConfAdapter {

	private final String SHELF_NAME = "SHELF-1";

	public static void main(String[] args) throws ConnectException, Exception {
		Logger.logger = Logger.createLogger(".", "ciena5150");
		Logger.logger.setLevel(LOG_LEVEL.trace);

		EquipDcn dcn = new EquipDcn();
		dcn.setEquipIpAddr("13.240.154.102");
		dcn.setConsPortNum(23);
		dcn.setLoginId("su");
		dcn.setLoginPwd("wwp");

		CienaConfAdapter5150 conf = new CienaConfAdapter5150(dcn, new Ciena());

		conf.collectConf();
		conf.collectPs();
	}

	private final String PROMPT = "> ";

	public CienaConfAdapter5150(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);

		this.setLoginPrompt("login:", "Password:", new String[] { PROMPT });
	}

	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		try {

			openAndSetMoreOff();

			List<DetectedNeVo> neList = new ArrayList<DetectedNeVo>();
			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);
			neList.add(ne);

			rtrvVer(ne);
			rtrvCardPort(ne);

			return neList;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}

	private void rtrvCardPort(DetectedNeVo ne) throws Exception {
		String rtrv = rtrv("port show");
		Logger.logger.debug("rtrv[{}], Completed", rtrv);
		parseCardPort(ne, rtrv);

	}

	private void parseCardPort(DetectedNeVo ne, String rtrv) {
		String[] lines = rtrv.split("\r");
		boolean dataFlag = false;

		String cardNm;
		String portNum;
		Map<String, DetectedCardVo> cardMap = new HashMap<String, DetectedCardVo>();

		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);
		
		DetectedCardVo card;
		for (String line : lines) {
			if (!line.contains("More") && !line.contains("----")
					&& line.length() > 3 && !line.contains(">")) {
				if (dataFlag == true) {
					String[] datas = line.split("\\|");
					cardNm = datas[1].trim().split("\\.")[0];
					portNum = datas[1].trim().split("\\.")[1];
					// 여기서 부터 PRE랑 현재랑 다르게 저장 다를시 카드 새로 추가 // 같을시 이전 카드vo에 포트정보
					// 저장

					card = cardMap.get(cardNm);
					if (card == null) {
						card = new DetectedCardVo();
						cardMap.put(cardNm, card);
						ne.getCardList().add(card);
						card.setParentSlotNm(SHELF_NAME);
						card.setCardNm("1-"+cardNm);
						card.setSlotNm(cardNm);
						card.setCardModel("Line card");//TODO: 모델 확인 
						card.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
						card.setSlotNo(cardMap.size());
						card.setPortCnt(0);
						card.setPollingMObjYn("N");
					}

					DetectedPortVo po = new DetectedPortVo();
					po.setCardNm(cardNm);
					po.setSlotNm(cardNm);
					try {
						po.setPortNo(Integer.parseInt(portNum));
					} catch (Exception e) {
					}
					po.setPortDescr(datas[1].trim());
					po.setPortAlsNm(po.getSlotNm()+"-"+po.getPortDescr());
					po.setPortState(datas[3].trim());
					setPort(po, datas[2].trim());

					card.setPortCnt(card.getPortCnt() + 1);

					ne.getPortList().add(po);

					Logger.logger.debug("{}, Completed", po.toString());

				}
				if (line.contains("Name")) { // Name 뒤에 나오는 라인이 실데이터
					dataFlag = true;
				}

			} else {
				continue;
			}

		}
		Logger.logger.debug("Accepted CardSize[{}]:portSize[{}], Completed", ne
				.getCardList().size(), ne.getPortList().size());

	}

	private void rtrvVer(DetectedNeVo ne) throws Exception {
		String rtrv = rtrv("software show");
		parseVer(ne, rtrv);

	}

	private String rtrv(String command) throws Exception {

		String result = send(command, PROMPT, "--More--");
		do {

			if (Emulator.isPrompt(result, new String[] { PROMPT })) {
				return result;
			}

			if (Emulator.isPrompt(result, new String[] { "--More--" })) {
				// --More--이면 공백을 보낸다.
				String e = send("", ">", "--More--");
				if (e != null) {
					result += e;
				} else {
					return result;
				}
			}

		} while (true);

	}

	private void parseVer(DetectedNeVo ne, String rtrv) {
		// #define SW_VER "Installed Package"
		// #define FW_VER "Running Kernel"
		String[] lines = rtrv.split("\\|\r");

		for (String line : lines) {
			if (line.contains("Installed Package")) {
				int index = line.indexOf(":");
				if (index > 0) {
					String swVer = line.substring(index + 1).trim();
					ne.setVerSw(swVer);
				}
			} else if (line.contains("Running Kernel")) {
				int index = line.indexOf(":");
				if (index > 0) {
					String fwVer = line.substring(index + 1).trim();
					ne.setVerFw(fwVer);
				}
			} else {
				continue;
			}
		}
		if (ne.getVerSw() == null && ne.getVerFw() == null) {
			Logger.logger.fail("get version failed");
		} else {
			Logger.logger.debug("verSw[{}]:verFw[{}], Completed",
					ne.getVerSw(), ne.getVerFw());
		}
	}

	@Override
	public List<PsVo> collectPs() throws ConnectException {
		try {

			openAndSetMoreOff();

			List<PsVo> psList = new ArrayList<PsVo>();

			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);
			rtrvCardPort(ne); // port 정보 가져오기
			List<DetectedPortVo> ports = ne.getPortList();

			if (ports == null) {
				Logger.logger.fail("Get Portlist failed");
			} else {

				for (DetectedPortVo port : ports) {
					String rtrv = send("port xcvr show port "
							+ port.getPortDescr() + " diagnostics");

					if (rtrv.split("\r").length < 5) { // 메시지 4라인 이하면 해당 포트 정보없음
						continue;
					}

					List<PsVo> ps = parseRtrvPs(rtrv, port);

					if (ps != null) {
						psList.addAll(ps);
					}
				}
			}

			return psList;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}

	private List<PsVo> parseRtrvPs(String rtrv, DetectedPortVo port) {
		List<PsVo> list = new ArrayList<PsVo>();

		String item[] = rtrv.split("\r");
		for (String line : item) {
			if (line.contains("Tx Power") && line.contains("dBm")) {
				String[] txline = line.split("\\|");
				float tx = Float.parseFloat(txline[2]);
				list.add(new PsVo(dcn.getEquipId(), port.getCardNm(), port
						.getPortDescr(), "TX", PsVo.PSCODE_OPT_LEVEL_CURR, tx));
				Logger.logger.debug("TX[{}], Completed", tx);
			} else if (line.contains("Rx Power") && line.contains("dBm")) {
				String[] rxline = line.split("\\|");
				float rx = Float.parseFloat(rxline[2]);
				list.add(new PsVo(dcn.getEquipId(), port.getCardNm(), port
						.getPortDescr(), "RX", PsVo.PSCODE_OPT_LEVEL_CURR, rx));
				Logger.logger.debug("RX[{}], Completed", rx);
			} else if (line.contains("Temp") && line.contains("degC")) {
				String[] tmpline = line.split("\\|");
				float temper = Float.parseFloat(tmpline[2]);
				list.add(new PsVo(dcn.getEquipId(), port.getCardNm(), port
						.getPortDescr(), null, PsVo.PSCODE_TEMPERRATURE, temper));
				Logger.logger.debug("Temper[{}], Completed", temper);
			} else {
				continue;
			}
		}

		return list;
	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException,
			Exception {
		try {
			openAndSetMoreOff();

			String rtrv = rtrv("alarm show active-alarms");
			/**
			 * <pre>
			 * CG_JoongAng1> alarm show active-alarms
			 * 
			 * +--------------------------------------- ACTIVE ALARMS ---------------------------------------+
			 * | IID |Ack|ATID |Severity| Date & Time (Local)      | Instance         | Description          |
			 * +-----+---+-----+--------+--------------------------+-----------------------------------------+
			 * |No Entries                                                                                   |
			 * +---------------------------------------------------------------------------------------------+
			 * 
			 * CG_JoongAng1> alarm show history last 3000h
			 * 
			 * +---------------------------------------- ALARM HISTORY ------------------------------------------+
			 * | HID | Rsn | IID |ATID |Severity| Date & Time (Local)      | Instance         | Description      |
			 * +-----+-----+-----+-----+--------+--------------------------+------------------+------------------+
			 * |2742 | set |   1 |  16 |warning | Mon Jul  1 06:30:18 2019 |               19 | Link Down        |
			 * |2744 | set |   5 |  31 |warning | Mon Jul  1 06:30:30 2019 |  CG_JoongAng1_VR |No RAPS PDU Receiv|
			 * |2745 | clr |   3 |  16 |warning | Mon Jul  1 06:30:33 2019 |                8 | Link Down        |
			 * |2755 | clr |   2 |  17 |warning | Mon Jul  1 06:31:19 2019 |               19 | Port Flapping    |
			 * +-----+-----+-----+-----+--------+--------------------------+------------------+------------------+
			 * |  |           |     \-----> ATID : Alarm Table ID                                                |
			 * |  |           \-----------> IID  : Alarm Instance ID                                             |
			 * |  \-----------------------> HID  : Alarm History ID                                              |
			 * +-------------------------------------------------------------------------------------------------+
			 * CG_JoongAng1>
			 * 
			 * 
			 * </pre>
			 * 
			 */
			String[] lines = rtrv.split("\r\n");
			List<String> dataList = new ArrayList<String>();
			int tick = 0;
			for (String line : lines) {
				if (line.contains("+--")) {
					tick++;
					continue; 
				}
				if (tick == 2) {
					dataList.add(line);
				} 
			}

			List<DetectedAlarmVo> detectedAlarms = parser.parseAlarms(dcn,
					dataList, dcn.getEquipTidVal());
			
			return detectedAlarms;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}

	private void setPort(DetectedPortVo po, String s) {

		if (s.indexOf("100") > 0) {
			po.setPortType(PORT_TYP_CD.gigabitEthernet);
			po.setPortSpeedCd(TRMS_NET_EQUIP_CAPA_CL_CD._100G.getCode());
		} else if (s.indexOf("10") > 0) {
			po.setPortType(PORT_TYP_CD.gigabitEthernet);
			po.setPortSpeedCd(TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
		} else {
			po.setPortType(PORT_TYP_CD.unknown);
			po.setPortSpeedCd(TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
		}
	}

	private void openAndSetMoreOff() throws Exception {

		open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(),
				dcn.getLoginPwd());
		send("system shell session set more off");

	}
}

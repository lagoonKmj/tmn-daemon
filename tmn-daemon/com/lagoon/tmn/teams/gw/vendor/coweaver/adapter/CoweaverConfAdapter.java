package com.lagoon.tmn.teams.gw.vendor.coweaver.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TelnetConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.coweaver.Coweaver;
import com.lagoon.tmn.teams.gw.vendor.coweaver.CoweaverService;
import com.lagoon.tmn.teams.gw.vendor.coweaver.CoweaverTL1PduMaker;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;

public class CoweaverConfAdapter extends TelnetConfAdapter<EquipDcn> implements ConfAdapter {
	private final String SHELF_NAME = "SHELF-1";

	public static void main(String[] args) throws Exception {

		Logger.logger = Logger.createLogger(".", "coweaver");
		Logger.logger.setLevel(LOG_LEVEL.trace);

		EquipDcn dcn = new EquipDcn();

		/* 7300 test */
		// dcn.setEquipIpAddr("12.160.11.171");
		// dcn.setConsPortNum(9000);
		// dcn.setEquipMdlCd("0000000355");
		// dcn.setLoginId("admin");
		// dcn.setLoginPwd("admin");

		/* 7400 test */
		dcn.setEquipIpAddr("12.160.11.173");
		dcn.setConsPortNum(23);
		dcn.setEquipMdlCd("0000000356");
		dcn.setLoginId("root");
		dcn.setLoginPwd("coweaver");

		CoweaverConfAdapter confAda = new CoweaverConfAdapter(dcn, new Coweaver());

		confAda.collectConf();
		confAda.collectPs();

	}

	public CoweaverConfAdapter(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);

		if (CoweaverService.MODEL_UTRANS_7200.equals(dcn.getEquipMdlCd())
				|| CoweaverService.MODEL_UTRANS_7300.equals(dcn.getEquipMdlCd())) {
			this.setLoginPrompt("Login ID:", "Password:", new String[] { ">" });
		} else { // 7400_7400C
			// 같은 모델인데 대소문자가 달라서 login 만 넘기는걸로 수정 
			this.setLoginPrompt("login:", "Password:", new String[] { ">" });
		}
	}

	@Override
	public List<DetectedNeVo> collectConf() throws Exception {
		try {

			openAndEnMode();

			List<DetectedNeVo> list = new ArrayList<DetectedNeVo>();
			DetectedNeVo ne = new DetectedNeVo();
			list.add(ne);
			ne.setEquipId(dcn.getEquipId());
			ne.setEquipMdlCd(dcn.getEquipMdlCd());
			ne.setIpAddr(dcn.getEquipIpAddr());

			this.rtrvVer(ne);
			this.rtrvCard(ne);
			this.rtrvPort(ne);

			// RT
			if (dcn.getRtList() != null) {
				// connect rt
				for (EquipDcn rt : dcn.getRtList()) {

					ne = new DetectedNeVo();
					list.add(ne);
					ne.setEquipId(rt.getEquipId()); // dcn_id
					ne.setEquipMdlCd(rt.getEquipMdlCd());

					telnet.cmd("telnet " + rt.getEquipIpAddr(), new String[] { "LOGIN:" });
					telnet.cmd(rt.getLoginId(), new String[] { "PASSWORD:" });
					telnet.cmd(rt.getLoginPwd(), new String[] { ">" });
					telnet.cmd("enable", new String[] { "#" });
					telnet.cmd("configure terminal", new String[] { "#" });

					/**
					 * 192.168.110.195>enable 192.168.110.195#configure terminal
					 * Enter configuration commands, one per line. End with
					 * CNTL/Z. 192.168.110.195(config)#log output console
					 * 192.168.110.195(config)#
					 */
					this.rtrvVer(ne);
					this.rtrvCard(ne);
					this.rtrvPort(ne);
					// rt의 버전
					// rt의 slot
					// rt의 포트
				}
			}
			return list;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}

	public void rtrvVer(DetectedNeVo ne) throws Exception {

		if (CoweaverService.MODEL_UTRANS_7200.equals(dcn.getEquipMdlCd())) {
			String rtrv = send("show system software package working-papa");
			parseVersion7200_7300(ne, rtrv);

		} else if (CoweaverService.MODEL_UTRANS_7300.equals(dcn.getEquipMdlCd())) {
			String rtrv = send("show system software package working-papu");
			parseVersion7200_7300(ne, rtrv);
		} else { // 7400
			String rtrv = send("show current image");
			parseVersion7400(ne, rtrv);
		}
	}

	private void parseVersion7200_7300(DetectedNeVo ne, String line) {

		String ver = getVer(line); // PKG 2016092210-ut7300 1.0.0
		if (ver != null) {
			ne.setVerSw(ver);
			Logger.logger.debug("7300SlotVer[{}], Completed", ver);
		}

	}

	private void parseVersion7400(DetectedNeVo ne, String rtrv) { // ne 버전 한개만
																	// 세팅됨
		String line = getLine(rtrv);

		if (line != null) {
			String[] s = line.split(" +");
			ne.setVerSw(s[3].trim());
			Logger.logger.debug("7400SlotVer [{}] , Completed", s[3]);
		} else {
			Logger.logger.fail("Get version failed:{}", rtrv);
		}

	}

	private String getVer(String line) {

		String item[];
		item = line.split("Image");
		String tmpVer  = null;
		String verion  = null;
		
		for (String str : item) {
			if (str.indexOf("OK") > 0) {
				int index = str.indexOf("Name");
				if (index > 0) {
					String name = str.substring(index, (str.substring(index).indexOf("+") + index));
					name = name.substring(name.indexOf(":") + 1);
					if (tmpVer == null) {
						tmpVer =  name.split(" +")[3].trim();
					} else {
						verion = tmpVer.concat("/" + name.split(" +")[3].trim());
					}
				}
			}
		}
		
		return verion;		
		
	}

	public void rtrvPort(DetectedNeVo ne) throws Exception {

		// ena/ auto forward learn inter Pause Port
		// port link speed duplex nego state oper face oper act
		// =============================================================================
		// ge1 up 1g FD Yes DISABLE FA NO PHY !ena act
		send("terminal length 0");

		if (CoweaverService.MODEL_UTRANS_7400.equals(dcn.getEquipMdlCd())
				|| CoweaverService.MODEL_UTRANS_7400C.equals(dcn.getEquipMdlCd())) {
			String rtrv = send("show port status");

			DetectedCardVo card = ne.getCardList().get(1);
			List<DetectedPortVo> portList = parsePort7400(rtrv, card);
			if (portList != null) {
				ne.getPortList().addAll(portList);
			} else {
				Logger.logger.fail("Port parsing failed:[{}]", dcn.getEquipMdlCd());
			}

		} else { // 7200_7300
			for (DetectedCardVo card : ne.getCardList()) {

				String rtrv = send("show port status slot " + card.getSlotNm());

				List<DetectedPortVo> portList = parsePort(rtrv, card);
				if (portList != null) {
					ne.getPortList().addAll(portList);
				} else {
					Logger.logger.fail("Port parsing failed:[{}]", dcn.getEquipMdlCd());
				}
			}
		}
	}

	private List<DetectedPortVo> parsePort7400(String rtrv, DetectedCardVo card) {
		String item[];
		List<DetectedPortVo> portList = new ArrayList<DetectedPortVo>();
		item = rtrv.split("\r\n"); // 한줄씩 자르기
		int lineFlag = 0;
		int portCnt = 0;
		for (String line : item) {
			if (line.contains("===")) { // 첫번째 행은 skip
				if (lineFlag == 1) {
					break;
				} else {
					lineFlag = 1;
				}
				continue;
			} else if (line.contains("#")) {
				break;
			} else if (lineFlag == 1) {
				DetectedPortVo po = new DetectedPortVo();
				portCnt+=1;
				String[] s = line.split(" +");
				String portName = s[0];
				String portSpeed = s[2];
				String portAct = s[10];

				po.setPortDescr(portName);
				po.setPortSpeedCd(portSpeed);
				if ("1g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._1GE.getCode());
				} else if ("10g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
				} else if ("100g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.E1.getCode());
				} else {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
				}
				po.setPortState(portAct);
				po.setCardNm(card.getSlotNm());
				po.setSlotNm(card.getSlotNm());
				po.setPortAlsNm(card.getSlotNm()+"-"+portName);
				po.setProtectYn(false);
				portList.add(po);
				Logger.logger.debug("CardNM[{}]:PortNm[{}]:PortSpeed[{}]:PortState[{}], Completed", card.getCardNm(), portName,
						portSpeed, portAct);
			}
		}
		card.setPortCnt(portCnt);
		return portList;
	}

	private List<DetectedPortVo> parsePort(String rtrv, DetectedCardVo card) {
		String item[];

		List<DetectedPortVo> portList = new ArrayList<DetectedPortVo>();

		item = rtrv.split("\r\n"); // 한줄씩 자르기
		int lineFlag = 0; // === 시작하면 -- 나올때까지 스킵
		int portCnt = 0;

		for (String line : item) {
			if (line.contains("===")) { // 첫번째 행은 skip
				if (lineFlag == 1) {
					break;
				}
				continue;
			} else if (line.contains("---")) {
				lineFlag = 1;
				continue;
			} else if (lineFlag == 1) {
				DetectedPortVo po = new DetectedPortVo();
				String[] s = line.split(" +");
				portCnt+=1;
				String portName = s[1];
				String portSpeed = s[4];
				String portAct = s[9];

				po.setPortDescr(portName);
				po.setPortSpeedCd(portSpeed);
				if ("1g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
				} else if ("10g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
				} else if ("100g".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._100G.getCode());
				} else if ("100m".equals(po.getPortSpeedCd())) {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._100m.getCode());
				} else {
					po.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
				}
				po.setProtectYn(false);
				po.setPortState(portAct);
				po.setCardNm(card.getSlotNm());
				po.setSlotNm(card.getSlotNm());
				po.setPortAlsNm(card.getSlotNm()+"-"+portName);
				portList.add(po);
				Logger.logger.debug("CardNM[{}]:PortNm[{}]:PortSpeed[{}]:PortState[{}], Completed", card.getCardNm(), portName,
						portSpeed, portAct);
			}
		}
		card.setPortCnt(portCnt);
		return portList;
	}

	@Override
	public List<PsVo> collectPs() throws ConnectException {
		try {

			openAndEnMode();

			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);
			List<PsVo> list = new ArrayList<PsVo>();

			if (dcn.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7200)
					|| dcn.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7300)) {
				this.rtrvCard(ne); // 카드목록 가져오기
				List<DetectedCardVo> slots = ne.getCardList();

				for (DetectedCardVo slot : slots) {
					String rtrv = send("show port module slot " + slot.getSlotNm());
					if (rtrv.split("\r\n").length < 5) { // 메시지 6라인 이하면 해당 포트
															// 정보없음
						continue;
					}

					PsVo psTmp = getTemper(slot); // temp 구하기

					List<PsVo> ps = parseRtrvPs7200_7300(rtrv, slot);
					if (psTmp != null) {
						list.add(psTmp);
					}
					if (ps != null) {
						list.addAll(ps);
					}
				}

				return list;
			} else { // 7400인경우
				this.rtrvCard(ne);
				this.rtrvPort(ne); // port 정보 get
				PsVo psTmp = getTemper(ne.getCardList().get(1)); // temp
																				// 구하기
				if (psTmp != null) {
					list.add(psTmp);
				}
				
				for (DetectedPortVo vo : ne.getPortList()) {

					String rtrv = send("show module-info " + vo.getPortDescr());

					if (rtrv.split("\r\n").length < 6) { // 메시지 6라인 이하면 해당 포트
															// 정보없음
						continue;
					}

					List<PsVo> ps = parseRtrvPs7400(vo, rtrv); // 광레벨 구하기

					if (ps != null) {
						list.addAll(ps);
					}
				}
				return list;
			}
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}

	}

	private List<PsVo> parseRtrvPs7400(DetectedPortVo vo, String rtrv) throws Exception {

		// #show module-info xe27
		// [interface xe27 module info]
		// Transceiver type : STM64
		// Distance : 40.000000 Km
		// Wavelength : 1550.000 nm
		// Connector Type : LC
		// Vendor name : INNOLIGHT
		// *Part name : TR-XX15E-N00
		// *Serial number : INGBA0010487
		// *Product date : 2016-07-18
		// DDM SFP RX power : -3.976144 dBm
		// DDM SFP TX power : 0.895519 dBm
		// DDM SFP TX bias : 0.000000 mA
		// DDM SFP Temperature : 42.437500 C
		// DDM SFP VCC : 0.000000 V

		List<PsVo> list = getPsList(rtrv, vo.getPortDescr(), vo.getCardNm());

		if (list != null) {
			return list;
		} else {
			Logger.logger.fail("Ps parsing failed:[{}]", vo.getPortDescr());
			return null;
		}

	}

	private List<PsVo> getPsList(String rtrv, String portNm, String cardNm) throws Exception {
		List<PsVo> list = new ArrayList<PsVo>();
		String rx = getRX(rtrv);
		String tx = getTX(rtrv);
		String temp = getPortTemper(rtrv);

		if (rx != null) {
			list.add(new PsVo(dcn.getEquipId(), cardNm, portNm, "RX", PsVo.PSCODE_OPT_LEVEL_CURR, Float.parseFloat(rx)));
		}
		if (tx != null) {
			list.add(new PsVo(dcn.getEquipId(), cardNm, portNm, "TX", PsVo.PSCODE_OPT_LEVEL_CURR, Float.parseFloat(tx)));
		}
		if (temp != null) {
			list.add(new PsVo(dcn.getEquipId(), cardNm, portNm, null, PsVo.PSCODE_TEMPERRATURE, Float.parseFloat(temp)));
		}

		Logger.logger.debug("Slot[{}]:Port[{}]::RX[{}]:TX[{}]:Temp[{}], Completed", cardNm, portNm, rx, tx, temp);
		return list;
	}

	private List<PsVo> parseRtrvPs7200_7300(String psline, DetectedCardVo slot) throws Exception {
		// command : [show port module slot slot-1]
		// [Interface ge-1/1 module info]
		// Transceiver type : 1GBE
		// Distance : 10.00 Km (10000 M)
		// Wavelength : 1310 nm
		// Vendor name : Gigalight
		// * Part name : GP-3124-L1CD-M
		// * Serial number : S1602157959
		// * Product date : 2016-02-24
		//
		// DDM SFP RX power : -40.000 dBm
		// DDM SFP TX power : -40.000 dBm
		// DDM SFP TX bias : 0.176 mA
		// DDM SFP Temperature : 46.688 C
		// DDM SFP VCC : 3.334 V
		//
		// Optical Power Threshold(H) : -3.000 dBm
		// Optical Power Threshold(L) : -19.000 dBm

		List<PsVo> pss = new ArrayList<PsVo>();
		String str = psline;

		if (str != null) {

			String item[]; // interface 리스트
			item = str.split("\\["); // 한줄씩 자르기 Interface로 시작

			for (String line : item) {
				if (!line.contains("Interface")) {
					continue;
				}
				pss.addAll(getPsList(line, getPortName(line), slot.getSlotNm()));

			}
		}
		return pss;
	}

	private PsVo getTemper(DetectedCardVo card) throws Exception {

		// 12.160.11.173>show system temperature
		//
		// ============================
		// | Slot | Temperature |
		// +--------+-----------------+
		// | Main | 43 |
		// +--------+-----------------+
		String rtrv = null;

		if (CoweaverService.MODEL_UTRANS_7200.equals(dcn.getEquipMdlCd())
				|| CoweaverService.MODEL_UTRANS_7300.equals(dcn.getEquipMdlCd())) {
			rtrv = send("show system temperature slot " + card.getSlotNm()); // 7200_7300
		} else {
			rtrv = send("show system temperature"); // 7400_7400c
		}

		String lines[] = rtrv.split("\r\n"); //
		int dataChk = 0;

		for (String line : lines) {
			if (line.contains("---")) {
				dataChk = 1;
			} else if (dataChk == 1) {
				String[] tmp = line.split("\\|");
				String temper = tmp[2].trim();

				PsVo ps = new PsVo(dcn.getEquipId(), card.getSlotNm(), null, null, PsVo.PSCODE_TEMPERRATURE,
						Float.parseFloat(temper));

				Logger.logger.debug("Slot[{}]:temper[{}], Completed", card.getSlotNm(), temper);
				return ps;
			}
		}

		return null;

	}

	private String getPortTemper(String line) {

		String val = line;
		int index = val.indexOf("DDM SFP Temperature");
		if (index > 0) {
			String temper = val.substring(index, (val.substring(index).indexOf("C") + index));
			temper = temper.substring(temper.indexOf(":") + 1).trim();
			return temper;
		}
		return null;
	}

	private String getTX(String line) {
		String val = line;
		int index = val.indexOf("DDM SFP TX power");
		if (index > 0) {
			String tx = val.substring(index, (val.substring(index).indexOf("dBm") + index));
			tx = tx.substring(tx.indexOf(":") + 1).trim();
			return tx;
		}
		return null;
	}

	private String getRX(String line) {
		String val = line;
		int index = val.indexOf("DDM SFP RX power");
		if (index > 0) {
			String rx = val.substring(index, (val.substring(index).indexOf("dBm") + index));
			rx = rx.substring(rx.indexOf(":") + 1).trim();
			return rx;
		}
		return null;
	}

	private String getPortName(String line) {
		String[] vals = line.split("/r/n");

		if (vals[0].contains("Interface")) {
			String portNm = vals[0].split(" ")[1];
			return portNm;
		}
		return null;
	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws Exception {
		try {

			openAndEnMode();

			String rtrv = "";
			String lines[] = null;
			String line = "";
			List<String> dataList = new ArrayList<String>();

			switch (dcn.getEquipMdlCd()) {
			case CoweaverService.MODEL_UTRANS_7200:
			case CoweaverService.MODEL_UTRANS_7300:

				rtrv = send("show system alarm current all");
				Logger.logger.debug("show system alarm current all : {}", rtrv);;
				/**
				 * <pre>
				 * 12.160.11.171# show system alarm current all
				 * size() : 16
				 * 0 : 
				 * 1 : 
				 * 2 : Current alarm information of system
				 * 3 : =============================================================================================
				 * 4 : | Sev. | SA / | Address | Reason                                      | Event Time          |
				 * 5 : | Lev. | NSA  |         | (Expanded address)                          |                     |
				 * 6 : ---------------------------------------------------------------------------------------------
				 * 7 : |  MIN | NSA  | S5  P2  | PW 3103                              PW-CSF | 2019-07-26 19:08:33 |
				 * 8 : |  CRI |  SA  | S1  P8  |                                     OPT-LOS | 2019-07-26 19:32:07 |
				 * 9 : | CRI |  SA  | S1  P2  |                                     OPT-LOS | 2019-08-02 18:12:49 |
				 * 10 : |  CRI |  SA  | S1  P1  |                                     OPT-LOS | 2019-08-02 18:18:37 |
				 * 11 : |  MIN | NSA  | S6  P1  | PW 3102                              PW-CSF | 2019-08-03 10:56:43 |
				 * 12 : |  MIN | NSA  | S6  P1  | PW 3101                              PW-CSF | 2019-08-03 10:56:43 |
				 * 13 : ---------------------------------------------------------------------------------------------
				 * 14 : Found 6 results of 6 alarm messages
				 * 15 : 12.160.11.171#
				 * 
				 * </pre>
				 * 
				 */	
				lines = rtrv.split("\r\n");
				
				boolean bStart = false;
				for (int index = 0; index < lines.length; index++) {
					line = lines[index];
					if (line.contains("---")) {
						if (bStart == false) {
							bStart = true;
							continue;
						} else {
							break;
						}
					}
					if (bStart == true) {
						String[] sLine = line.split("\\|");
						sLine[3] = line.split("\\|")[3].trim().replaceAll("  ", "-"); // e.g) S5  P2 사이 '-'  를 붙인다. 
						dataList.add(makeAlarm(sLine));
					}
				}
				break;

			case CoweaverService.MODEL_UTRANS_7400:
				rtrv = send("show system alarm current");
				Logger.logger.debug("show system alarm current : {}", rtrv);;
				/**
				 * <pre>
				 * size() : 3
				 * 0 : show system alarm current
				 * 1 : ** ALARM     MJ SA  MAIN-P1              OPT-PWR-LOW       2019-07-30 11:26:32
				 * 2 : 12.160.11.176#
				 * </pre>
				 */
				lines = rtrv.split("\r\n");
				for (int index = 0; index < lines.length; index++) {
					line = dcn.getEquipTidVal() + " " + lines[index]; // TID 가 없어서 앞에 붙임 
					dataList.add(line);
				}
				break;
			}
			
			List<DetectedAlarmVo> detectedAlarms = parser.parseAlarms(dcn, dataList, dcn.getEquipTidVal());
			
			return detectedAlarms;

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}
	
	private String makeAlarm(String[] sLine) {
		StringBuffer sb = new StringBuffer();
		sb.append(dcn.getEquipTidVal());
		sb.append(" ");
		sb.append("**");
		sb.append(" ");
		sb.append("ALARM");
		sb.append(" ");
		for (String str : sLine) {
			sb.append(str);
		}
		return sb.toString();
	}

	private void rtrvCard(DetectedNeVo ne) throws Exception {
		if (dcn.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7400)) {
			String rtrv = send("show version hardware");
			ne.setCardList(parseRtrvSlot7400(ne, rtrv));
		} else {
			String rtrv = send("show system version hardware");
			ne.setCardList(parseRtrvSlot7200_7300(rtrv));
		}

	}

	private List<DetectedCardVo> parseRtrvSlot7400(DetectedNeVo ne, String rtrv) {
		/*
		 * UTRANS-7400 version hardware 1.1.10.172# show version hardware
		 * =====================================================================
		 * slot |version |fpga-ver |fpga-date
		 * =====================================================================
		 * main 1.0 2.0 2013-08-28
		 */

		List<DetectedCardVo> slots = new ArrayList<DetectedCardVo>();

		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		slots.add(shelf);

		String line = getLine(rtrv); // 실데이터 라인 가져오기
		if (line != null) {
			String[] s = line.split(" +");
			DetectedCardVo slot = new DetectedCardVo();
			slot.setParentSlotNm(SHELF_NAME);
			slot.setSlotNm(s[0]);
			slot.setCardNm("1-" + s[0]);
			slot.setCardModel("Line card");
			slot.setVer(s[1]);
			slots.add(slot);
			Logger.logger.debug("CardNm[{}]:CardVer[{}], Completed", slot.getCardNm(), slot.getVer());
		} else {
			Logger.logger.fail("Get line failed:{}", rtrv);
		}
		return slots;
	}

	private String getLine(String rtrv) {
		String item[];
		item = rtrv.split("\r\n"); // 한줄씩 자르기
		int lineFlag = 0;

		for (String line : item) {
			if (line.contains("===")) {
				if (lineFlag == 1) { // 2번째 "===" 를 만난 다음이 실데이터
					lineFlag = 2;
				} else {
					lineFlag = 1;
				}
			} else if (lineFlag == 2) {
				return line;
			} else {
				continue;
			}
		}
		return null;
	}

	private List<DetectedCardVo> parseRtrvSlot7200_7300(String dataList) {

		List<DetectedCardVo> slots = new ArrayList<DetectedCardVo>();
		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		slots.add(shelf);

		String str = dataList;

		if (str != null) {
			String item[];
			int slotNum = 0; // slot번호
			item = str.split("\r\n"); // 한줄씩 자르기

			for (String line : item) {
				if (line.contains("Slot-name") || line.contains("Ver.") || !line.startsWith("|")) {
					continue;
				} else {
					String[] s = line.split("\\|");
					DetectedCardVo e = new DetectedCardVo();
					++slotNum;

					if (s != null) {
						e.setParentSlotNm(SHELF_NAME);
						e.setSlotNm(s[1].trim());
						e.setCardNm("1-"+s[1].trim()); // slot name
						e.setCardModel("Line card"); // unit-name
						e.setVer(s[3].trim()); // unit-version
						e.setSlotNo(slotNum); // slot번호
						slots.add(e);
						Logger.logger.trace("CardNm[{}]:slotNm[{}]:CardModel[{}], Completed", e.getCardNm(),e.getSlotNm(), e.getCardModel());
					}
				}

			}

		}
		return slots;

	}

	protected NetPduMakerTL1 getPduMaker() {
		return new CoweaverTL1PduMaker();
	}

	private void openAndEnMode() throws Exception {

		Logger.logger.debug("dcn.getLoginId() :{}, EquipMdlID :{}, EquipID :{}", dcn.getLoginId(), dcn.getEquipMdlCd(),
				dcn.getEquipId());

		open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());
		telnet.setPromptArr(new String[] { "#" });
		send("enable");

		switch (dcn.getEquipMdlCd()) {
		case CoweaverService.MODEL_UTRANS_7200:
		case CoweaverService.MODEL_UTRANS_7300:
			break;
		case CoweaverService.MODEL_UTRANS_7400:
			send("terminal length 0");
			break;
		}
	}

}

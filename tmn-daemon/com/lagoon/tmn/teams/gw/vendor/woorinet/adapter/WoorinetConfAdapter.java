package com.lagoon.tmn.teams.gw.vendor.woorinet.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.exp.LoginDenyException;
import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TL1ConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.Woorinet;
import com.lagoon.tmn.teams.gw.vendor.woorinet.WoorinetTL1PduMaker;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.TL1Client;
import fxms.nms.co.tl1_2.vo.ORMF;

public class WoorinetConfAdapter extends TL1ConfAdapter<EmsDcn> implements ConfAdapter {

	public static void main(String[] args) throws ConnectException, Exception {

		if (args.length != 1) {
			Logger.logger.error("1 : collectConf(), 2 : collectPs(), 3. collectAlarm()");
			System.exit(0);
		}

		Logger.logger = Logger.createLogger(".", "woorinet");

		EmsDcn dcn = new EmsDcn();

		// dcn.setEmsIpAddr("13.0.244.147");
		// dcn.setLoginId("teams");
		// dcn.setLoginPwd("!teams");
		// dcn.setEmsId("2627012");
		// dcn.setConsPortNum(19011);

		// opn3000 test
		dcn.setEmsIpAddr("12.4.132.84");
		dcn.setLoginId("teams");
		dcn.setLoginPwd("!teams");
		dcn.setConsPortNum(19011);

		WoorinetConfAdapter confAda = new WoorinetConfAdapter(dcn, new Woorinet());

		switch (args[0]) {
		case "1":
			confAda.collectConf();
			break;
		case "2":
			confAda.collectPs();
			break;
		case "3":
			List<DetectedAlarmVo> alarmList = confAda.collectAlarm();
			if (alarmList.size() > 0) {
				Logger.logger.info("장애 존재, size :{}", alarmList.size());
			}
			break;

		}
	}

	private List<String> modelList;
	private String charset = "euc-kr";
	private final String SHELF_NAME = "SHELF-1";

	public WoorinetConfAdapter(EmsDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
		initModelList();
	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException, Exception {
		try {

			open(dcn.getEmsIpAddr(), dcn.getConsPortNum());

			List<DetectedNeVo> detectedNeVos = rtrvNetOnly();
			List<DetectedAlarmVo> ret = new ArrayList<DetectedAlarmVo>();
			List<DetectedAlarmVo> entry;

			for (DetectedNeVo detectedNeVo : detectedNeVos) {

				try {
					entry = rtrvAlm(detectedNeVo);
				} catch (Exception e) { // Deny 익셉션 캐치
					continue;
				}

				if (entry != null && entry.size() > 0) {
					ret.addAll(entry);
				}

				// EMS에 부하를 줄이기 위해 잠시 쉬었다 요청한다.
				Thread.sleep(500);
			}

			return ret;
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		try {

			open(dcn.getEmsIpAddr(), dcn.getConsPortNum());

			List<DetectedNeVo> ret = rtrvNetOnly();

			for (DetectedNeVo ne : ret) {
				rtrvSlotPort(ne);
				GwApi.getApi().updateDetectedNe(ne);
			}

			// 위에서 저장해서 여기서는 없는 것으로 한다.
			return new ArrayList<DetectedNeVo>();

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	@Override
	public List<PsVo> collectPs() throws ConnectException, Exception {

		// List<PsVo> list = new ArrayList<PsVo>();
		//
		// PsVo vo = new PsVo();
		//
		// List<DetectedNeVo> neList = this.rtrvNet(); // 카드목록 가져오기
		//
		// for (DetectedNeVo ne : neList) {
		// ORMF m = rtrv("RTRV-SLOT", ne.getTid(), "ALL", null);
		// List<DetectedCardVo> slots = ne.getCardList();
		//
		// }
		return null;
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new WoorinetTL1PduMaker();
	}

	@Override
	protected void logout() throws Exception {
		tl1Client.send("CANC-USER", null, null, new String[] { dcn.getLoginId() });

		// CANC-USER:::100:teams;
	}

	@Override
	protected int sendLogin() throws Exception {
		// ACT-USER:::1234:teams,!teams;
		return tl1Client.send("ACT-USER", null, null, new String[] { dcn.getLoginId() + "," + dcn.getLoginPwd() });
	}

	private void initModelList() {
		modelList = new ArrayList<String>();

		modelList.add("MMU");
		modelList.add("MXCU");
		modelList.add("MAUXU");
		modelList.add("MFU");
		modelList.add("SBATF");
		modelList.add("RCEAU");
		modelList.add("RXCU");
		modelList.add("SPSU_AC");
		modelList.add("OMU");
		modelList.add("OXCU");
		modelList.add("EMU");
		modelList.add("EXCU");
		modelList.add("EMAUX");
		modelList.add("EXAUX");
		modelList.add("EPWRU");
		modelList.add("EFLU");
		modelList.add("EFCU");
	}

	private DetectedPortVo parsePort(String s) throws Exception {
		String ss[] = split(s);
		String slotNm;

		if (ss.length >= 12 && ss[0].equals("TID") == false) {
			DetectedPortVo vo = new DetectedPortVo();

			vo.setPortDescr(ss[1]); // AID
			vo.setPortAlsNm(vo.getPortDescr());

			slotNm = vo.getPortDescr().split("-")[0];

			vo.setPortNo(parseInt(vo.getPortDescr().split("-")[1]));
			vo.setPortSpeedCd(ss[5]); // SPEED
			vo.setPortState(ss[4]); // STATE
			// vo.setPortType(ss[6]); // DUPLEX
			vo.setPortType("0");
			vo.setSlotNm(slotNm);
			vo.setCardNm(slotNm);

			if ("1G".equals(vo.getPortSpeedCd())) {
				vo.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._1G.getCode());
			} else if ("10G".equals(vo.getPortSpeedCd())) {
				vo.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
			} else if ("E1".equals(vo.getPortSpeedCd())) {
				vo.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.E1.getCode());
			} else if ("STM1".equals(vo.getPortSpeedCd())) {
				vo.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.STM1.getCode());
			} else {
				vo.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
			}
			Logger.logger.trace("Slot[{}]:Port[{}]:[{}]:[{}]:[{}], Completed", vo.getSlotNm(), vo.getPortDescr(),
					vo.getPortSpeedCd(), vo.getPortState());
			return vo;

		}

		return null;

		// /*
		// TID,AID,NAME,DESCR,STATE,SPEED,DUPLEX,AUTO-NEGO,TX-FX,PAUSE-TX,PAUSE-RX,MAX-FRAME,LOOPBACK,MAX-LEARNING-MAC,ETH-PTM,PORT-SHUTDOWN,TPID-PROF-ID,LINK-STATUS,STP-STATE,TX-STATUS,COUNTER,POLL-RATE,REPORT-PM,MONITE-NO-PKT,SD-ENABLE,SD-TH-COUNT
		// */[collectWoorinetptnPort.cpp:777]
		// "80C,M01-BP1,,,ACT,1G,FULL,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,UP,FORWARD,---,ENABLE,ENABLE,ENABLE,DISABLE,FALSE,1000"[collectWoorinetptnPort.cpp:777]
		// "고척도서관,SXC-P7,,,DEACT,---,---,TRUE,TX,FALSE,FALSE,1600,NONE,0,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"[collectWoorinetptnPort.cpp:764]

	}

	private DetectedNeVo parseRtrvNet(String s) throws Exception {
		// RTRV-NET:::100;
		// RTRV-NET;
		// TL1ADAPTER 2017-08-25 09:33:38
		// M 100 COMPLD
		// /*
		// TID-ID,TID,NODE-TYPE,IP-ADDR,MANUFACTURER,HARDWARE,SOFTWARE,SERIAL-NUMBER,NODE-STATUS,SUBNET,SUBNET-ID,SYSTEM-TYPE
		// */
		// "101,80C,ptn,10.1.80.30,WOORINET,0.0.3,1.0.9,---,normal,SYSTEM4,133,APN80A"
		// "102,80D,ptn,10.1.80.40,WOORINET,0.0.3,1.0.9,---,normal,SYSTEM3,132,APN80A"
		// "105,20A_01,ptn,10.1.20.101,WOORINET,1.0.0,1.0.9,---,normal,SYSTEM4,133,APN20A"
		// "114,20D,ptn,10.1.20.54,WOORINET,1.0.0,1.0.9,---,normal,SYSTEM3,132,APN20B"
		// "113,20E,ptn,10.1.20.56,WOORINET,1.0.0,1.0.9,---,normal,SYSTEM3,132,APN20B"
		// ;

		String ss[] = split(s);

		if (ss != null && ss.length >= 12 && ss[0].equals("TID-ID") == false) {

			DetectedNeVo ret = new DetectedNeVo();
			ret.setIpAddr(ss[3]); // IP-ADDR
			ret.setModel(ss[11]); // SYSTEM-TYPE
			ret.setVendor(ss[4]); // MANUFACTURER
			ret.setVerHw(ss[5]); // HARDWARE
			ret.setVerSw(ss[6]); // SOFTWARE
			ret.setTid(ss[1]); // TID
			ret.setConnState(ss[8]); // NODE-STATUS

			Logger.logger.trace("TID[{}]:Ver[{}]:type[{}]", ss[1], ss[5], ss[11]);
			return ret;
		}

		return null;

	}

	@SuppressWarnings("unused")
	private List<DetectedNeVo> rtrvNet() throws Exception {

		List<DetectedNeVo> ret = rtrvNetOnly();

		for (DetectedNeVo ne : ret) {

			Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.

			try {
				rtrvSlot(ne);
			} catch (Exception e) {
				ne.setErrmsg("slot error");
				Logger.logger.error(e);
			}
		}

		for (DetectedNeVo ne : ret) {

			if (ne.isCollectOk() == false) {
				continue;
			}

			Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.

			try {
				rtrvPort(ne);
			} catch (Exception e) {
				ne.setErrmsg("port error");
				Logger.logger.error(e);
			}

		}

		return ret;

	}

	private void rtrvSlotPort(DetectedNeVo ne) throws Exception {

		Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.

		try {
			rtrvSlot(ne);
		} catch (Exception e) {
			ne.setErrmsg("slot error");
			Logger.logger.error(e);
			return;
		}

		Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.

		try {
			rtrvPort(ne);
		} catch (Exception e) {
			ne.setErrmsg("port error");
			Logger.logger.error(e);
		}

	}

	private List<DetectedNeVo> rtrvNetOnly() throws Exception {

		ORMF m = rtrv("RTRV-NET", null, null, null);

		List<DetectedNeVo> ret = new ArrayList<DetectedNeVo>();
		DetectedNeVo vo;
		AdamsEquipVo equip;

		if (m.isCompld() || m.isRtrv()) {
			for (String s : m.getDataList()) {
				vo = parseRtrvNet(s);
				if (vo != null) {

					equip = findEquip(vo);

					if (equip != null) {

						vo.fill(equip);
						vo.setEmsId(dcn.getEmsId());
						vo.setDetectedMstime(System.currentTimeMillis());
						vo.setEmsInrEquipKeyVal(vo.getTid());
						ret.add(vo);
					}

				}
			}

			return ret;
		} else if (m.isDeny()) {
			throw new Exception("RTRV-NET deny");
		} else {
			return ret;
		}

	}

	private AdamsEquipVo findEquip(DetectedNeVo vo) {

		AdamsEquipVo equip = GwApi.getApi().findEquipByTid(dcn, vo.getTid());

		if (equip == null) {
			equip = GwApi.getApi().findEquipByIp(dcn, vo.getIpAddr());
		}

		if (equip == null) {
			equip = GwApi.getApi().findEquipByTid(null, vo.getTid());
		}

		if (equip == null) {
			equip = GwApi.getApi().findEquipByIp(null, vo.getIpAddr());
		}

		if (equip == null) {
			Logger.logger.fail(Logger.makeString(dcn.getEmsIpAddr() + " :: " + vo.getTid() + " :: " + vo.getIpAddr(),
					"Not Found In DB"));
		}
		return equip;
	}

	private void rtrvPort(DetectedNeVo ne) throws Exception {

		/*
		 * SEND=[RTRV-ETH-PORT:카카오_분당#1:MAUXU:95;] 카카오_분당#1 2019-10-01 14:10:22
		 * M 95 COMPLD /*
		 * TID,AID,NAME,DESCR,STATE,SPEED,DUPLEX,AUTO-NEGO,TX-FX,PAUSE
		 * -TX,PAUSE-RX
		 * ,MAX-FRAME,LOOPBACK,MAX-LEARNING-MAC,ETH-PTM,PORT-SHUTDOWN
		 * ,TPID-PROF-ID
		 * ,LINK-STATUS,STP-STATE,TX-STATUS,COUNTER,POLL-RATE,REPORT
		 * -PM,MONITE-NO-PKT,SD-ENABLE,SD-TH-COUNT
		 */
		// "카카오_분당#1,M01-P1,,,DEACT,10G,FULL,FALSE,FX,FALSE,FALSE,10100,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M01-P2,,,DEACT,10G,FULL,FALSE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P1,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P2,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P3,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P4,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P5,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P6,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P7,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M02-P8,,,DEACT,---,---,TRUE,FX,FALSE,FALSE,1600,NONE,32767,FALSE,FALSE,1,DOWN,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"
		// "카카오_분당#1,M03-P1,카카오_분당#1-M03-P1:카카오_판교CNC#1-M04-P1,,ACT,10G,FULL,FALSE,FX,FALSE,FALSE,10100,NONE,32767,FALSE,FALSE,1,UP,FORWARD,---,DISABLE,DISABLE,DISABLE,DISABLE,FALSE,1000"

		DetectedCardVo card;
		DetectedPortVo port;
		// sprintf(szSbuf, "RTRV-ETH-PORT:%s:%s:102;\n",s.c_str(),
		// g_vecNeCard[j].card_descr);

		ORMF m = rtrv("RTRV-ETH-PORT", ne.getTid(), "ALL", null);
		for (String s : m.getDataList()) {
			port = parsePort(s);
			if (port != null) {
				ne.getPortList().add(port);

				card = ne.getCardBySlotNm(port.getSlotNm());

				if (card != null) {
					card.setPortCnt(card.getPortCnt() + 1);
				}
			}
		}

		// for (DetectedCardVo slot : shelf.getChildren()) {
		//
		// Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.
		//
		// if (modelList.contains(slot.getCardModel()) == false) {
		// continue;
		// }
		//
		// ORMF m = rtrv("RTRV-ETH-PORT", ne.getTid(), slot.getCardDescr(),
		// null);
		// portCnt = 0;
		// for (String s : m.getDataList()) {
		// port = parsePort(slot, s);
		// if (port != null) {
		// ne.getPortList().add(port);
		// portCnt++;
		// }
		// }
		// slot.setPortCnt(portCnt);
		//
		// }
	}

	/**
	 * 장비의 슬롯 정보를 조회한다.
	 * 
	 * @param ne
	 * @throws Exception
	 */
	private void rtrvSlot(DetectedNeVo ne) throws Exception {

		// 셀프 정보를 수동으로 넣는다.
		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);

		int slotNo = 0;
		String ss[];
		String slotNm;
		String cardModel;
		DetectedCardVo vo;
		ORMF m = rtrv("RTRV-SLOT", ne.getTid(), null, null);

		for (String s : m.getDataList()) {

			ss = split(s);

			if (ss != null && ss.length >= 12 && ss[0].equals("TID") == false) {

				vo = new DetectedCardVo();
				ne.getCardList().add(vo);

				String aa[] = ss[1].split(":");
				slotNm = aa[0];
				cardModel = ss[2]; // EQ-UNIT

				vo.setParentSlotNm(SHELF_NAME);
				vo.setIp("");
				vo.setPortCnt(0);
				vo.setSlotNm(slotNm);
				vo.setCardNm(slotNm); // EQ-UNIT
				vo.setCardModel(cardModel); // EQ-UNIT
				vo.setCardType(AdamsCfg.EQUIP_CONS_ITM_TYP_CD.Module.getCode());// EQ-UNIT
				vo.setSlotNo(slotNo++);
				vo.setCardSerialNo("");
				vo.setCardState(ss[4]); // ADMIN-STATE
				vo.setTid(ss[0]); // TID
				vo.setVer(ss[9]); // HW-VER
				vo.setEmptySlot("----".equals(ss[2]));

				if (cardModel.contains("MXG02U") || cardModel.equals("RXG2U") || cardModel.equals("RTX4U")
						|| cardModel.contains("OMX24U")) {
					vo.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
				} else if (cardModel.contains("MGE08U") || cardModel.contains("MTX08U") || cardModel.equals("RGE4U")
						|| cardModel.contains("RXCU")) {
					vo.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._1G.getCode());
				} else if (cardModel.contains("ME116U") || cardModel.contains("RCEU")) {
					vo.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.E1.getCode());
				} else if (cardModel.contains("MS0104U") || cardModel.contains("RS14U")) {
					vo.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.STM1.getCode());
				} else {
					vo.setCapacityCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
				}

				Logger.logger.trace("CardNm[{}]:CardModel[{}], Completed", vo.getCardNm(), vo.getCardModel());

			}

			// TID,SLOT:PRV-UNIT,EQ-UNIT,EQ-STATE,ADMIN-STATE,INIT-OK,BP-STATE,IPC-STATE,FAIL-STATE,HW-VER,SW-VER,FPGA-VER,FPGA-DATE
			// "80D,MAUX:MAUXU,MAUXU,IN,ACT,TRUE,----,----,OK,1.0.0,----,1.0.0,2015-6-19"
		}
		// RTRV-SLOT;
		// M 100 COMPLD[collecWoorinetPtnCard.cpp:892]
		// 16:36:41 I} /*
		//
		// */[collecWoorinetPtnCard.cpp:892]
		/*
		 * 16:36:41 I}
		 * 
		 * [collecWoorinetPtnCard.cpp:892] 16:36:41 I}
		 * "80D,MMA:MMU,MMU,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.0.9,1.1.0,2016-11-24"
		 * [collecWoorinetPtnCard.cpp:892] 16:36:41 I}
		 * "80D,MMB:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		 * [collecWoorinetPtnCard.cpp:892] 16:36:41 I}
		 * "80D,M01:MS0104UB,MS0104UB,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.0.9,1.0.0,2017-1-24"
		 * [collecWoorinetPtnCard.cpp:892] 16:36:41 I}
		 * "80D,M02:ME116U,ME116U,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.0.7,1.0.1,2017-1-26"
		 * [collecWoorinetPtnCard.cpp:892]
		 */

	}

	/**
	 * 장애를 조회 한다.
	 * 
	 * @param ne
	 * @return
	 * @throws Exception
	 */
	protected List<DetectedAlarmVo> rtrvAlm(DetectedNeVo ne) throws Exception {

		ORMF m = rtrv("RTRV-ALM", ne.getTid(), null, null);

		List<DetectedAlarmVo> alarmList = parser.parseAlarms(dcn, m.getDataList(), ne.getTid());

		String headerHstime = m.getHeader().getHstime() + "";
		for (DetectedAlarmVo alarmVo : alarmList) {
			alarmVo.setOccurHstime(headerHstime);
		}

		Logger.logger.debug("collectAlarm TID : {}, Count : {}", ne.getTid(), alarmList.size());
		return alarmList;
	}

	private String[] split(String s) {
		String line = s.trim();
		if (line.length() == 0 || line.charAt(0) != '"') {
			return new String[0];
		}

		line = line.replaceAll("\"", "");
		return line.split(",");
	}

	@Override
	public void open(String ip, int port) throws ConnectException, Exception {

		NetPduMakerTL1 makert = getPduMaker();
		makert.setCharset(getCharset());

		tl1Client = new TL1Client("Woorinet-Conf", ip, port, Logger.logger, this, makert);
		tl1Client.setCharset(getCharset());
		tl1Client.open();

		long ptime = System.currentTimeMillis();

		try {

			while (isConnected == false) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}

				if (ptime + 15000 < System.currentTimeMillis()) {
					throw new Exception("Timeout");
				}
			}

			int ctag = sendLogin();

			ORMF m = getORMF(ctag);
			if (m == null) {
				throw new Exception("Timeout");
			}
			if (m.isCompld())
				return;

			if (m.isDeny())
				new LoginDenyException("Login fail : deny");

			if (m.getRi().getCc().toLowerCase().indexOf("deny") >= 0) {
				new LoginDenyException(m.getRi().getCc());
			}

			throw new Exception("Login fail : " + m.getRi().getCc());

		} catch (Exception e) {
			close();
			throw e;
		}
	}

	@Override
	protected String getCharset() {
		return charset;
	}

}
package com.lagoon.tmn.teams.gw.vendor.telefield.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg.CAPACITY_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.CF_NE_MODEL_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.TF_PORT_TYPE;
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
import com.lagoon.tmn.teams.gw.vendor.telefield.TelefieldTL1PduMaker;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.TL1Client;
import fxms.nms.co.tl1_2.vo.ORMF;

/**
 * 텔레필드 EMS에서 구성 정보를 가져온다.<br>
 * 
 * ACT-USER::::ADMIN,ADMIN;<br>
 * "ACT-USER", null, null, null, dcn.getUserId() + "," + dcn.getPasswd());
 * 
 * @author subkjh(김종훈)
 *
 */
public class TelefieldConfAdapter extends TL1ConfAdapter<EmsDcn> implements
		ConfAdapter {
	private final String SHELF_NAME = "SHELF-1";

	public TelefieldConfAdapter(EmsDcn dcn, VendorBaseParser parser) {

		super(dcn, parser);

	}

	// public static void main(String[] args) throws Exception {
	// Logger.logger = Logger.createLogger(".", "telefield");
	//
	// EmsDcn dcn = new EmsDcn();
	//
	// dcn.setEmsIpAddr("12.16.24.173");
	// dcn.setLoginId("op");
	// dcn.setLoginPwd("op");
	// dcn.setConsPortNum(9031);
	//
	// TelefieldConfAdapter confAda = new TelefieldConfAdapter(dcn, new
	// Telefield());
	//
	// confAda.collectConf();
	// }

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws Exception {
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
	public List<DetectedNeVo> collectConf() throws Exception {
		try {
			open(dcn.getEmsIpAddr(), dcn.getConsPortNum());
			rtrvNetOnly();

			// 위에서 저장해서 여기서는 없는 것으로 한다.
			return new ArrayList<DetectedNeVo>();
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	private List<DetectedNeVo> rtrvNetOnly() throws Exception {
		ORMF m = rtrv("RTRV-NET", null, null, null);
		List<DetectedNeVo> netList = parseRtrvNet(m.getDataList());
		if (netList == null)
			return null;
		for (DetectedNeVo ne : netList) {
			if ("CONN".equals(ne.getConnState())) {
				try {
					rtrvSlotPort(ne);
					rtrvVer(ne);
					cardNmRename(ne);
					GwApi.getApi().updateDetectedNe(ne);
				} catch (Exception e) {
					Logger.logger.error(e);
				}
			}

		}

		return netList;

	}

	private void cardNmRename(DetectedNeVo ne) {

		for (DetectedCardVo card : ne.getCardList()) {
			if (!card.getCardNm().contains("SHELF"))
				card.setCardNm("1-" + card.getCardNm());
		}

	}

	private void rtrvVer(DetectedNeVo ne) throws Exception {
		ORMF m = rtrv("RTRV-SWVER", ne.getTid(), null, null);
		String[] result;
		if (m.getDataList() != null) {

			for (String s : m.getDataList()) {
				if (s.startsWith("\"") && s.contains("OPR")) {
					s = s.replace("\"", "");
					result = s.split(",");
					ne.setVerSw(result[2]);
					ne.setVerFw(result[2]);
				}
			}
		}
	}

	@Override
	public List<PsVo> collectPs() throws Exception {

		return null;
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

	private void rtrvSlotPort(DetectedNeVo ne) throws InterruptedException {
		Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.

		try {
			rtrvSlot(ne);
		} catch (Exception e) {
			ne.setErrmsg("slot error");
			Logger.logger.error(e);
			return;
		}

		Thread.sleep(500); // EMS 부하를 고려하여 0.5초 쉬고 처리함.
		for (DetectedCardVo card : ne.getCardList()) {
			try {
				String s = findCardType(card); // 현재 카드모델이 OTSU 일 경우만 가능
				if (s != null && s.equals("TFOM_PORT")) {
					rtrvPort(ne, card);
				} else {
					continue;
				}

				Logger.logger.debug(
						"equipId[{}]:cardNm[{}]:cardPortSize[{}] Completed",
						ne.getEquipId(), card.getCardNm(), card.getPortCnt());

			} catch (Exception e) {
				ne.setErrmsg("port error");
				Logger.logger.error(e);
			}
		}

	}

	private void rtrvPort(DetectedNeVo ne, DetectedCardVo card)
			throws Exception {
		DetectedPortVo port;

		ORMF m = rtrv("RTRV-PORT", ne.getTid(), card.getCardNm(), null); // cardNm
																			// //AID/
																			// //RTRV-PORT:13-11-1-4-18-21-0-0-0-0:OTSU.B;
		card.setPortCnt(0);
		for (String s : m.getDataList()) {
			port = parsePort(s, card);

			if (port != null) {
				ne.getPortList().add(port);
				card = ne.getCardBySlotNm(port.getSlotNm());

				if (card != null) {
					port.setCardNm(card.getCardNm());
					card.setPortCnt(card.getPortCnt() + 1);
				}
			} else {
				continue;
			}
		}

	}

	private DetectedPortVo parsePort(String s, DetectedCardVo card) {
		DetectedPortVo port;
		String[] result;

		if (s.startsWith("\"")) {
			s = s.replace("\"", "");
			result = s.split(",");
			port = new DetectedPortVo();

			int iStrNum = result.length;

			if (iStrNum < 5) {
				return null;
			}
			port.setPortDescr(result[0]);
			port.setPortAlsNm(port.getPortDescr());
			String cardNm = result[0].split("-")[0];
			port.setCardNm(cardNm);
			port.setSlotNm(cardNm);
			port.setPortType(result[2]);
			// port capacity
			port.setPortState(result[1]);
			if (!port.getCardNm().equals(card.getCardNm())) {
				return null;
			} else {
				return port;
			}
		} else {
			return null;
		}

	}

	private void rtrvSlot(DetectedNeVo ne) throws Exception {
		// ;RTRV-SLOT:13-11-1-4-18-21-0-0-0-0,S570;
		// 13-11-1-4-18-21-0-0-0-0,S570 2019-10-28 13:36:51
		// M 0 COMPLD
		// /* AID:STATE,RACK,SHELF,UNIT-TYPE,REAL-TYPE */
		// "PSU.A,NORM:RACK1,SHELF1,PSU,PSU-DC"
		// "PSU.B,NORM:RACK1,SHELF1,PSU,PSU-DC"
		// "MCU,NORM:RACK1,SHELF1,MCU,MCU"
		// "OTSU.A,NORM:RACK1,SHELF1,OTSU,OTSU-4P"
		// "OTSU.B,NORM:RACK1,SHELF1,OTSU,OTSU-4P"
		// "FU1.A,OUT:RACK1,SHELF1,---,---"
		// "FU1.B,OUT:RACK1,SHELF1,---,---"
		// "FU2.A,OUT:RACK1,SHELF1,---,---"
		// "FU2.B,OUT:RACK1,SHELF1,---,---"
		// "EOSU,NORM:RACK1,SHELF1,EOSU,EOSU-G2"
		// "FAN.L,NORM:RACK1,SHELF1,FAN,FAN-LU"
		// "FAN.R,NORM:RACK1,SHELF1,FAN,FAN-RU"
		// "FU1.IO,OUT:RACK1,SHELF1,---,---"
		// "FU2.IO,OUT:RACK1,SHELF1,---,---"
		// "NVRAM,NORM:RACK1,SHELF1,NVRAM,NVRAM-512K"
		// "SBU,NORM:RACK1,SHELF1,SBU,SBU"

		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);
		// 해당장비의 카드정보를 db에서 지운다
		int slotNo = 0;
		int iStrNum;
		DetectedCardVo vo;
		String[] result;

		ORMF m = rtrv("RTRV-SLOT", ne.getTid(), null, null);

		for (String s : m.getDataList()) {
			if (s.startsWith("\"")) {
				s = s.replace("\"", "");
				result = s.split(",");

				iStrNum = result.length;
				if (iStrNum < 5) {
					continue;
				}
				vo = new DetectedCardVo();
				vo.setParentSlotNm(SHELF_NAME);
				vo.setCardNm(result[0]);
				vo.setSlotNm(result[0]);
				vo.setCardModel(result[3]);
				vo.setTid(ne.getTid());

				if (vo.getCardModel().equals("---")
						|| vo.getCardModel().equals("BLANK")) {
					continue;
				}
				// pst sst
				slotNo += 1;
				vo.setSlotNo(slotNo);
				vo.setCardState(result[1].split(":")[0]);
				getCardCapacity(vo);
				ne.getCardList().add(vo);
			} else {
				continue;
			}

		}
	}

	private void getCardCapacity(DetectedCardVo vo) {
		// ### CAPACITY_STR ###
		// 1X2G5AGG (2.5G)
		// 28XE1DS1 (155M)
		// 2X155622M(622M)
		// 2X155M (155M)
		// 622M (622M)
		// 8XETH (Fast Ethernet)
		// ELAN02 (x)
		// L2PA622M (622M)

		if (vo.getCardModel().equals("STM16")) {
			vo.setCapacityStr("STM16");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_25G);
		} else if (vo.getCardModel().equals("STM4")) {
			vo.setCapacityStr("STM4");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_622M);
		} else if (vo.getCardModel().equals("STM64")) {
			vo.setCapacityStr("STM64");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_10G);
		} else if (vo.getCardModel().equals("STM1U")) {
			vo.setCapacityStr("STM1");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_155M);
		} else if (vo.getCardModel().equals("TGE")) {
			vo.setCapacityStr("10GE");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_10GE);
		} else {
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_NONE);
		}
	}

	private String findCardType(DetectedCardVo vo) {
		// if (vo.getCardModel().equals("SCU") ||
		// vo.getCardModel().equals("SCXU")
		// || vo.getCardModel().equals("CPSU")
		// || vo.getCardModel().equals("MBU")
		// || vo.getCardModel().equals("MCU")
		// || vo.getCardModel().equals("PSU")
		// || vo.getCardModel().equals("FAN")
		// || vo.getCardModel().equals("OTSU")) {
		if (vo.getCardModel().contains("OTSU")) {
			return TF_PORT_TYPE.OTSU;

		} else {
			return null;
		}
	}

	// private List<DetectedCardVo> getTSlotList(DetectedNeVo node) throws
	// Exception {
	// ORMF m = rtrv("RTRV-SLOT", node.getTid(), null, null);
	// return parseRtrvSlot(m.getDataList());
	//
	// }

	@SuppressWarnings("unused")
	private List<DetectedPortVo> makePort2Passive(DetectedNeVo ne) {
		// MoNode node = TnmsApi.getApi().getNode(ne.tid);
		// if (node == null) return;
		//
		// MakePortPassive maker = getPortMaker();
		// for (TSlot slot : ne.getSlotList()) {
		// maker.makePort(node.getModelName(), slot);
		// }
		return null;
	}

	@Override
	protected void logout() throws Exception {
		tl1Client.send("CANC-USER", null, null,
				new String[] { dcn.getLoginId() });

		// CANC-USER:::100:<loginId>;
	}

	@Override
	protected int sendLogin() throws Exception {
		return tl1Client.send("ACT-USER", null, null,
				new String[] { dcn.getLoginId() + "," + dcn.getLoginPwd() });
	}

	private List<DetectedNeVo> parseRtrvNet(List<String> dataList)
			throws Exception {

		int iStrNum = 0;

		List<DetectedNeVo> list = new ArrayList<DetectedNeVo>();

		DetectedNeVo e;
		AdamsEquipVo equip;
		if (dataList != null) {
			String s;
			String result[];
			for (String str : dataList) {
				e = new DetectedNeVo();

				s = str.trim();
				if (s.startsWith("\"")) {
					s = s.replace("\"", "");
					result = s.split(",");

					iStrNum = result.length;

					e.setTid(result[0]);
					e.setEmsId(dcn.getEmsId() + "^" + result[0]);// ne_id other
																	// :dcn_id +
																	// "^" + tid
					// netname
					if (iStrNum == 5) {
						e.setNetName(result[4]);
						e.setSysname(result[4]);
						e.setConnState(result[3]);
					} else if (iStrNum == 6) {
						e.setNetName(result[5]);
						e.setSysname(result[5]);
						e.setConnState(result[4]);
					} else if (iStrNum == 7) {
						e.setNetName(result[6]);
						e.setSysname(result[6]);
						e.setConnState(result[5]);
					} else {
						continue;
					}

					if (str.contains("S570")) { // 이것만 대상
						e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S570);

						equip = GwApi.getApi().findEquipByTid(dcn, e.getTid());
						if (equip != null) {
							e.fill(equip);
							e.setEmsId(dcn.getEmsId());
							e.setDetectedMstime(System.currentTimeMillis());
							e.setEmsInrEquipKeyVal(e.getTid());
						} else {
							Logger.logger.fail(Logger.makeString(
									dcn.getEmsIpAddr() + " :: " + e.getTid(),
									"Not Found In DB"));
							continue;
						}

						list.add(e);
					} else {
						continue;
					}

					// model_id
					// if(str.contains("S5500")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S5500);
					// }else if(str.contains("S550")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S550);
					// }else if(str.contains("S540A")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S540A);
					// }else if(str.contains("S540")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S540);
					// }else if(str.contains("S530")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S530);
					// }else if(str.contains("S520")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S520);
					// }else if(str.contains("700")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_FM700);
					// }else if(str.contains("S541")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S541);
					// }else if(str.contains("S560")){
					// e.setModel(CF_NE_MODEL_CD.CF_NE_MODEL_TFOM_S560);}

					// e.capacity_cd == none
					// strcpy(st_ne_data.reg_flag, NE_REG_FLAG_AUTO);
					// fw version = null
					// ne.state

				} else if (str.contains("DISCONN")) {
					continue;
				} else {
					continue;
				}

			}
		}
		return list;
	}

	@SuppressWarnings("unused")
	private void parseRtrvSys(List<String> dataList, DetectedNeVo node) {
		if (dataList != null) {
			String s;
			String ss[];
			for (String str : dataList) {
				s = str.trim();
				if (s.indexOf("/*") >= 0) {
				} else {
					s = s.replaceAll("\\\"", "");
					ss = s.split(":");
					if (ss.length == 2) {
						if ("TYPE".equals(ss[0]))
							node.setModel("TFOM-" + ss[1]);
						else if ("SYSTEM_NAME".equals(ss[0]))
							node.setSysname(ss[1]);
						// else if ( "VENDOR".equals(ss[0]))
						// node.setVendorName(ss[1]);
						else if ("IP".equals(ss[0]))
							node.setIpAddr(ss[1]);
						else if ("WORKING_MCU_BSP_VER".equals(ss[0]))
							node.setVerSw(ss[1]);
						else if ("WORKING_MCU_FPGA_VER".equals(ss[0]))
							node.setVerFw(ss[1]);
						// else if ( "TYPE".equals(ss[0]))
						// node.setModelName(ss[1]);
					}
				}

			}
		}
	}

	protected String[] split(String str, String pattern) {
		String s = str.trim();
		if (s.indexOf("/*") >= 0) {
			return null;
		} else {
			s = s.replaceAll("\\\"", "");
			return s.split(pattern);
		}
	}

	protected String getAid(String s) {
		if (s.indexOf(":") > 0) {
			return s.split(":")[0];
		}
		return s;
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new TelefieldTL1PduMaker();
	}

	@Override
	protected String getCharset() {
		return "euc-kr";
	}

	@Override
	public void open(String ip, int port) throws ConnectException, Exception {

		NetPduMakerTL1 makert = getPduMaker();
		makert.setCharset(getCharset());

		tl1Client = new TL1Client("Telefield-Conf", ip, port, Logger.logger,
				this, makert);
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

}

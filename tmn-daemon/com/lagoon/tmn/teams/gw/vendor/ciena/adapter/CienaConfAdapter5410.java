package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg.CAPACITY_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.PORT_TYP_CD;
import com.lagoon.tmn.teams.co.AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD;
import com.lagoon.tmn.teams.co.exp.LoginDenyException;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedCardVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.DetectedPortVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TL1ConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.Ciena;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaTL1PduMaker;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.TL1Client;
import fxms.nms.co.tl1_2.vo.ORMF;

/**
 * CIENA 5400 수집<br>
 * 명령어 결과 참고 파일 : datas/cmd-result/CINEA(5410).txt<br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class CienaConfAdapter5410 extends TL1ConfAdapter<EquipDcn> implements
		ConfAdapter {

	private final SimpleDateFormat MMDD = new SimpleDateFormat("MM-dd");
	private final String SHELF_NAME = "SHELF-1";
	
	public static void main(String[] args) throws ConnectException, Exception {
		Logger.logger = Logger.createLogger(".", "ciena5410");
		Logger.logger.setLevel(LOG_LEVEL.trace);

		EquipDcn dcn = new EquipDcn();
		dcn.setEquipIpAddr("12.64.58.143");
		dcn.setConsPortNum(10201);
		dcn.setLoginId("administrator");
		dcn.setLoginPwd("admin1!");

		CienaConfAdapter5410 conf = new CienaConfAdapter5410(dcn, new Ciena());

		conf.collectConf();
		conf.collectPs();
	}

	private String charset = "utf-8";

	public CienaConfAdapter5410(EquipDcn dcn, VendorBaseParser parser) {

		super(dcn, parser);

	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException,
			Exception {
		open(dcn.getEquipIpAddr(), dcn.getConsPortNum());
		ORMF m = rtrv("RTRV-ALM-ALL", null, null, null);
		String tid = m.getHeader().getSid();
		
		List<DetectedAlarmVo> alarmList = parser.parseAlarms(dcn, m.getDataList(), tid);
		
		String headerHstime = m.getHeader().getHstime() + "";
		for (DetectedAlarmVo alarmVo : alarmList) {
			alarmVo.setOccurHstime(headerHstime);
		}
		
		return alarmList;
	}

	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		try {

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum());

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

	@Override
	public List<PsVo> collectPs() throws ConnectException, Exception {
		try {

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum());

			List<PsVo> psList = new ArrayList<PsVo>();
			List<PsVo> e;

			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);

			String mmdd = MMDD.format(new Date());

			rtrvCardPort(ne);

			String prePortNum = null;
			boolean FlagEnd = false;

			for (DetectedPortVo vo : ne.getPortList()) {
				String[] portNm = vo.getPortDescr().split("-");
				
				if (FlagEnd == true && prePortNum.equals(portNm[1])) { // 1-A-1-3이끝이면 1-A-2-1이런식으로다음포트로넘어감
					continue;
				}
				e = rtrvPortOptLevel(vo.getPortDescr(), mmdd);

				if (e != null) {
					psList.addAll(e);
					prePortNum = portNm[1];
					FlagEnd = false;
				} else {
					FlagEnd = true;
					Logger.logger
							.debug("Port Rtrv End:[{}]", vo.getPortDescr());
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

	@Override
	protected String getCharset() {
		return charset;
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new CienaTL1PduMaker();
	}

	@Override
	protected void logout() throws Exception {
		tl1Client.send("CANC-USER", null, null,
				new String[] { dcn.getLoginId() });
	}

	/**
	 * ACR-USER:DOJ-TDM-AF01:ADMIN:1234::ADMIN;
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	protected int sendLogin() throws Exception {
		return tl1Client.send("ACT-USER", dcn.getEquipTidVal(),
				dcn.getLoginId(), new String[] { "", dcn.getLoginPwd() });
	}

	private int rtrvCardPort(DetectedNeVo ne) throws Exception {

		ORMF m = rtrv("RTRV-EQPT", null, "ALL", null);

		List<String> rtrv = m.getDataList();
		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);
		
		
		for (String line : rtrv) {
			String[] result = line.split(":|\"");

			if (result.length < 5) {
				// Logger.logger.fail("Parse Fail : Unkown Result");
				continue;
			}
			// Logger.logger.debug("Line :{},{}", line, "true");
			String[] result2 = result[1].split("-");
			if (result2.length == 3) { // card
	
				boolean o = parseCard(ne, result);
				if (o == false) {
					continue;
				}
			} else if (result2.length == 4) { // port
				boolean o = parsePort(ne, result);
				if (o == false) {
					continue;
				}
			}
		}

		return 0;
	}

	private boolean parsePort(DetectedNeVo ne, String[] result) {
		DetectedPortVo pVo = new DetectedPortVo();

		String[] result2 = result[1].split("-");

		String cardNm = result2[0] + "-" + result2[1] + "-" + result2[2];
		String slotNm = result2[0] + "-" + result2[1] + "-" + result2[2];
		String portDesc =slotNm + "-"+ result2[3];
		pVo.setCardNm(slotNm);
		pVo.setPortDescr(portDesc);
		pVo.setPortAlsNm(pVo.getPortDescr());
		int cardSize=ne.getCardList().size();
		DetectedCardVo card = ne.getCardList().get(cardSize-1);
		if(cardSize!= 0 && cardNm.equals(card.getCardNm())){
			card.setPortCnt(card.getPortCnt()+1);
			pVo.setPortNo(card.getPortCnt());
		}else{
			card.setPortCnt(0);
		}
		
		pVo.setPortType(PORT_TYP_CD.unknown);

		if (result[3].contains("10Gb")) {
			pVo.setPortSpeedCd(TRMS_NET_EQUIP_CAPA_CL_CD._10G.getCode());
		} else {
			pVo.setPortSpeedCd(TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
		}

		if (result.length > 6) { // result[6]!=null
			result2 = result[6].split(",");
			if (result2.length == 2) {
				pVo.setPortState(result2[0]);
				pVo.setPortSubState(result2[1]);
			} else if (result2.length == 1) {
				pVo.setPortState(result2[0]);
			}
		}
		Logger.logger
				.debug("PortDescr[{}]:PortType[{}]:PortState[{}]:PortSubState[{}]:portsize[{}], Completed",
						portDesc, pVo.getPortType(), pVo.getPortState(),
						pVo.getPortSubState(),card.getPortCnt());
		ne.getPortList().add(pVo);
		return true;
	}

	private boolean parseCard(DetectedNeVo ne, String[] result) {
		
		String cardModel;
		DetectedCardVo vo = new DetectedCardVo();
		String[] result2 = result[1].split("-");
		
		String cardNm = result2[0] + "-" + result2[1] + "-" + result2[2];
		String slotNm = result2[1] + "-" + result2[2];
		vo.setCardNm(cardNm);
		vo.setSlotNm(slotNm);
		if (result[3] != null) {

			result2 = result[3].split(",|=");
			if (result2.length < 4) {
				// Logger.logger.fail("Parse Fail : Unkown Result");
				return false;
			}
			cardModel = result2[4]; // 카드모델
			vo.setCardModel(cardModel);
		}
		if (result.length > 6) { // 상태값 result[6] != null
			result2 = result[6].split(",");
			if (result2.length == 2) {
				vo.setCardState(result2[0]);
				vo.setCardSubState(result2[1]);
			} else if (result2.length == 1) {
				vo.setCardState(result2[0]);
			}
			Logger.logger
					.debug("cardNm[{}]:cardModel[{}]:cardState[{}]:cardSubState[{}]:ne.cardSize[{}], Completed",
							cardNm, vo.getCardModel(), vo.getCardState(),
							vo.getCardSubState(),ne.getCardList().size());
		}

		getCardCapacity(vo);
		vo.setParentSlotNm(SHELF_NAME);
		ne.getCardList().add(vo);
		return true;

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

		if (vo.getCardModel().equals("OSLM-2D")) {
			vo.setCapacityStr("OSLM-2D WL3");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_100G);
		} else if (vo.getCardModel().equals("OLSM-50")) {
			vo.setCapacityStr("OLSM-50");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_10G);
		} else if (vo.getCardModel().equals("OLSM-48")) {
			vo.setCapacityStr("OLSM-48");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_1G);
		} else if (vo.getCardModel().equals("OSLM-50-10G")) {
			vo.setCapacityStr("10G");
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_COM_10G);
		} else {
			vo.setCapacityCd(CAPACITY_CD.CAPACITY_CD_NONE);
		}
	}

	private List<PsVo> rtrvPortOptLevel(String portName, String mmdd)
			throws Exception {

		List<PsVo> psList = new ArrayList<PsVo>();
		String data = ":,LEV-DIRN,NEND,NA,15-MIN," + mmdd + ",00-20";

		ORMF m = rtrv("RTRV-PM-OPTPWR", null, portName, new String[] { data });

		List<String> rtrv = m.getDataList();
		if (rtrv.size() <= 3) { // 메시지 끝
			return null;
		} else {
			parsePortOpt(rtrv, psList);
		}

		return psList;
	}

	private void parsePortOpt(List<String> rtrv, List<PsVo> psList) {

		for (String line : rtrv) {
			String[] result = line.split(":|\"|,");

			if (result.length < 9) {
				continue;
			}
			if (!result[3].contains("OPR-AVG")
					&& !result[3].contains("OPT-AVG")) {
				continue;
			}
			String[] iStrChk = result[1].split("-");
			if (iStrChk.length < 4) { // "%s-%s-%s-%s 형식이 아니면
				continue;
			}

			String Val = result[4].replace("dBm", "").trim();
			String cardNm;
			String portNm;
			if (result[7].equals("RCV")) { // RX
				PsVo rx = new PsVo();
				cardNm = result[1];
				portNm = result[1];
				psList.add(new PsVo(dcn.getEquipId(), cardNm, portNm, "RX", PsVo.PSCODE_OPT_LEVEL_CURR, Float.parseFloat(Val)));
				
				Logger.logger.debug("Slot:[{}],Rx::[{}]", rx.getCardNm(),
						rx.getPsValue());
			} else { // TX
				PsVo tx = new PsVo();
				cardNm = result[1];
				portNm = result[1];
				psList.add(new PsVo(dcn.getEquipId(), cardNm, portNm, "TX", PsVo.PSCODE_OPT_LEVEL_CURR, Float.parseFloat(Val)));
				Logger.logger.debug("Slot:[{}],Tx:[{}]", tx.getCardNm(),
						tx.getPsValue());
			}
		}

	}

	private String rtrvVer(DetectedNeVo ne) throws Exception {

		ORMF m = rtrv("RTRV-NETYPE", null, null, null);

		String line;
		for (String s : m.getDataList()) {
			line = s.replaceAll("\"", "");
			String ss[] = line.split(",");
			ne.setVerSw(ss[ss.length - 1]);
			return ne.getVerSw();
		}

		return null;

		// 5410-ChuncheonB-1 19-10-07 08:07:20
		// M 1234 COMPLD
		// "CIENA,5410,OPTICAL_SWITCH,4.4.0"

	}

	@Override
	public void open(String ip, int port) throws ConnectException, Exception {

		NetPduMakerTL1 makert = getPduMaker();
		makert.setCharset(getCharset());

		tl1Client = new TL1Client("Ciena5410-Conf", ip, port, Logger.logger,
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

package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

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

import subkjh.bas.co.log.Logger;

public class CienaConfAdapterQopas extends TelnetConfAdapter<EquipDcn> implements ConfAdapter {
	public static void main(String[] args) {

		EquipDcn dcn = new EquipDcn();
		dcn.setEquipIpAddr("12.32.171.131");
		dcn.setConsPortNum(40001);
		dcn.setLoginId("admin");
		dcn.setLoginPwd("opasadmin!");
	}

	private final String SHELF_NAME = "SHELF-1";

	public CienaConfAdapterQopas(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);

		this.setLoginPrompt("login", "passwd", new String[] { ">" });
	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException, Exception {

		try {

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());

			String rtrv = send("RTRV-ALM:" + dcn.getEquipTidVal() + "::1;"); // TODO
																				// TL1
																				// 명령
																				// 확인
			/**
			 * <pre>
			 *
			 * OPAS [2] >RTRV-ALM:QOPAS-ChuncheonB-1::1;
			 * Slot    Port      Sev  UnitType  Reason              Date
			 * ---------------------------------------------------------------------------
			 * 
			 * OPAS [2] >RTRV-ALMHIS:QOPAS-ChuncheonB-1::1;
			 * Address sev UType Reason          OccurTime           Recov ReleaseTime
			 * --------------------------------------------------------------------------------
			 * OTRU2 P3-SCR  QOPAS RX_POWER        2019-10-10 02:14:52 YES   2019-10-10 02:41:33
			 * OTRU2 P3-SCR  QOPAS LOS             2019-10-10 02:14:52 YES   2019-10-10 02:41:34
			 * OTRU2 P4-SMN  QOPAS RX_POWER_ALERT  2019-10-10 02:14:52 YES   2019-10-10 02:41:46
			 * OTRU2 P4-SCR  QOPAS RX_POWER        2019-10-10 02:14:52 YES   2019-10-10 02:41:46
			 * OTRU2 P4-SCR  QOPAS LOS             2019-10-10 02:14:52 YES   2019-10-10 02:41:46
			 * 
			 * #########################################################################
			 * 장비 10종정도 확인 결과 현재 장애가 없어서 히스토리 데이터로 임시 개발한다.(아래 내용)
			 * OPAS [2] >RTRV-ALM:QOPAS-ChuncheonB-1::1;
			 * Slot    Port      Sev  UnitType  Reason              Date
			 * --------------------------------------------------------------------------- 
			 * OTRU2 P3-SCR  QOPAS RX_POWER        2019-10-10 02:14:52
			 * OTRU2 P3-SCR  QOPAS LOS             2019-10-10 02:14:52
			 * OTRU2 P4-SMN  QOPAS RX_POWER_ALERT  2019-10-10 02:14:52
			 * OTRU2 P4-SCR  QOPAS RX_POWER        2019-10-10 02:14:52
			 * OTRU2 P4-SCR  QOPAS LOS             2019-10-10 02:14:52
			 * ######################################################################### 
			 * * ^MALM Detect  CR  OTRU4-   P4-W      QOPAS     LOS            2019-07-18 02:06:23
			 * </pre>
			 * 
			 */
			String[] lines = rtrv.split("\r\n");

			List<String> dataList = new ArrayList<String>();
			boolean bStart = false;
			for (String line : lines) {
				if (line.contains("---")) {
					bStart = true;
					continue;
				}
				if (bStart == true) {
					dataList.add(line);
				}
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

	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		try {

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());

			List<DetectedNeVo> neList = new ArrayList<DetectedNeVo>();
			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);

			ne.setSysname(ne.getNetName());

			neList.add(ne);

			rtrvVer(ne);

			rtrvCard(ne);

			rtrvPort(ne);

			cardNmRename(ne); // 1- 붙여줌

			return neList;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	private void cardNmRename(DetectedNeVo ne) {

		for (DetectedCardVo card : ne.getCardList()) {
			if (!card.getCardNm().contains("SHELF"))
				card.setCardNm("1-" + card.getCardNm());
		}

	}

	@Override
	public List<PsVo> collectPs() throws ConnectException, Exception {

		open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());
		try {
			DetectedNeVo ne = ConfAdapter.makeEquipDetectedNeVo(dcn);
			this.rtrvCard(ne); // 카드목록 가져오기
			this.rtrvPs(ne);
			return this.rtrvPs(ne);
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}

	}

	/**
	 * 2020.03.30 by Kim,JongHoon
	 * 
	 * 계정이 없으면 강제로 넣는 로직으로 이 메소드 추가함.
	 */
	@Override
	public void open(String ip, int port, String loginId, String loginPwd) throws Exception {

		if (loginId == null) {
			loginId = "admin";
		}
		if (loginPwd == null) {
			loginPwd = "opasadmin!";
		}

		super.open(ip, port, loginId, loginPwd);
	}

	private List<PsVo> rtrvPs(DetectedNeVo ne) throws Exception {
		List<PsVo> list = new ArrayList<PsVo>();

		String rtrv = send("rtrv-pwr"); // rx만 있음

		String lines[] = rtrv.split("\n");
		boolean isCard = false;
		String[] result;

		for (String line : lines) {
			if (isCard) {
				result = line.split(" +");
				if (result.length < 3 || line.contains(">")) {
					continue;
				}
				String cardNm = result[0].trim();
				String portDesc = cardNm + "-" + result[1].trim();
				Logger.logger.trace("cardNm[{}], portDesc[{}]: rx[{}] Completed", cardNm, portDesc, result[2].trim());
				float rx = Float.parseFloat(result[2]);

				list.add(new PsVo(dcn.getEquipId(), cardNm, portDesc, "RX", PsVo.PSCODE_OPT_LEVEL_CURR, rx));
			}
			if (line.contains("-------")) {
				isCard = true;
				continue;
			}
		}
		return list;

	}

	private int rtrvCard(DetectedNeVo ne) throws Exception {

		String rtrv = send("rtrv-slot");

		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);

		int SLOT_NO = 0;
		int CARD_TYPE = 1;

		// rtrv-slot
		// Slot UnitType sw mode working port State
		// ---------------------------------------------------------
		// SP OSP ACT
		// OTRU1 QOPAS N N N N S W W W ACT
		// OTRU2 QOPAS N N N N W W W W ACT
		// OTRU3 QOPAS N N N N S W S W ACT
		// OTRU4 QOPAS N N N N W W S S ACT
		// OTRU5 QOPAS N N N N W W W W ACT
		// OTRU6 QOPAS N N N N W S S W ACT
		// OTRU7 QOPAS N N N N W W W W ACT
		// OTRU8 QOPAS N N N N W W W W ACT
		// OTRU9 QOPAS N N N N W W W W ACT
		// OTRU10 QOPAS N N N N S S S S ACT
		// OTRU11 QOPAS N N N N W W W W ACT
		// OTRU12 QOPAS N N N N W W W W ACT
		// OTRU13 QOPAS N N N N W W W W ACT

		DetectedCardVo card;
		String lines[] = rtrv.split("\n");
		boolean isCard = false;
		for (String line : lines) {
			line = line.trim();

			if (isCard) {
				String ss[] = line.split(" +");
				if (ss.length >= 3 && !line.contains(">")) {
					card = new DetectedCardVo();
					card.setParentSlotNm(SHELF_NAME);
					if (ss[CARD_TYPE].equals("---")) {
						card.setCardModel("Line card");
					} else {
						card.setCardModel(ss[CARD_TYPE]);
					}
					card.setCardNm(ss[SLOT_NO]);
					card.setSlotNm(ss[SLOT_NO]);
					card.setCardState(ss[ss.length - 1]);
					ne.getCardList().add(card);
				}
				continue;
			}

			if (line.contains("-------")) {
				isCard = true;
				continue;
			}

		}

		return ne.getCardList().size();
	}

	private int rtrvPort(DetectedNeVo ne) throws Exception {

		String result = send("rtrv-port");

		// OPAS [25] >rtrv-port
		// Slot port equip state
		// ----------------------------------------
		// OTRU1 P1-W EQUIP ACT
		// OTRU1 P1-S EQUIP ACT
		// OTRU1 P2-W EQUIP DEACT
		// OTRU1 P2-S EQUIP DEACT
		// ...
		// OTRU10 P1-W EQUIP DEACT
		// OTRU10 P1-S EQUIP DEACT
		// OTRU11 P1-W EQUIP ACT
		// OTRU11 P1-S EQUIP ACT

		DetectedPortVo port;
		String lines[] = result.split("\n");
		String ss[];
		boolean isPort = false;

		for (String line : lines) {
			line = line.trim();

			if (line.contains("-------")) {
				isPort = true;
				continue;
			}

			if (isPort) {

				ss = line.split(" +");
				if (ss.length >= 3 && !line.contains(">")) {
					port = new DetectedPortVo();
					port.setCardNm(ss[0]);
					port.setPortDescr(ss[0] + "-" + ss[1]);
					port.setPortAlsNm(port.getPortDescr());
					port.setPortSpeedCd(AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown.getCode());
					port.setPortState(ss[3]);
					port.setSlotNm(ss[0]);
					ne.getPortList().add(port);

					DetectedCardVo card = ne.getCardBySlotNm(port.getSlotNm());
					if (card != null) {
						card.setPortCnt(card.getPortCnt() + 1);
						port.setPortNo(card.getPortCnt());
					}
					continue;
				}

			}
		}

		return ne.getCardList().size();
	}

	private String rtrvVer(DetectedNeVo ne) throws Exception {

		String result = send("rtrv-ver-sw");

		boolean isVer = false;
		String lines[] = result.split("\n");
		for (String line : lines) {
			if (isVer) {
				String ss[] = line.split(" +");
				if (ss.length >= 2) {
					ne.setVerSw(ss[2]);
					return ne.getVerSw();
				}
			}
			if (line.contains("------")) {
				isVer = true;
				continue;
			}
		}

		return null;

		// OPAS [25] >rtrv-ver-sw
		// LOC BOOT VERSION DATE/TIME
		// --------------------------------------------------------------
		// PRI ACTIVE 3.8 2019-03-14 11:40:00
		// SEC --- 3.8 2019-03-14 11:40:00
		//
		// Running os : (SEC) 3.8 2019-03-14 11:40:00
	}
}

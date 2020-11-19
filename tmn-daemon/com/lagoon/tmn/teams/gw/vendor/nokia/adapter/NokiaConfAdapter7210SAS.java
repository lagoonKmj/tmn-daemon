package com.lagoon.tmn.teams.gw.vendor.nokia.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
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
import com.lagoon.tmn.teams.gw.vendor.nokia.Nokia;
import com.lagoon.tmn.teams.gw.vendor.nokia.NokiaService;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import subkjh.bas.net.emulator.Emulator;

/**
 * Nokia 7210SAS-T 장비<br>
 * 명령어 결과 파일 : NOKIA(7210SAS-T).txt<br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class NokiaConfAdapter7210SAS extends TelnetConfAdapter<EquipDcn> implements ConfAdapter {
	private final String SHELF_NAME = "SHELF-1";
	// private final String CONTINUE_CHARS = "more? y=[yes] q=[quit] >";
	private final String PROMPT = "#";
	public static void main(String[] args) throws ConnectException, Exception {
		
		Logger.logger = Logger.createLogger(".", "nokia");
		Logger.logger.setLevel(LOG_LEVEL.trace);

		EquipDcn dcn = new EquipDcn();
		
		dcn.setEquipIpAddr("13.1.247.31");	
		dcn.setConsPortNum(23);
		dcn.setEquipMdlCd(NokiaService.MODEL_7210SAS_T );
		dcn.setLoginId("admin");
		dcn.setLoginPwd("admin");
		
		NokiaConfAdapter7210SAS confAda = new NokiaConfAdapter7210SAS(dcn, new Nokia());

		confAda.collectConf();
		confAda.collectPs(); 
		
	}

	public NokiaConfAdapter7210SAS(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
		
		this.setLoginPrompt("Login:", "Password:", new String[] { "#" } );
	}

	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException,
			Exception {
		try {
			open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());
			
			String rtrv = send("show system alarms");
			/**
			 * <pre>
			 * A:KBFG-K_Building# show system alarms
			 * 
			 * ===============================================================================
			 * Alarms [Critical:0 Major:0 Minor:0 Warning:3 Total:3]
			 * ===============================================================================
			 * Index      Date/Time               Severity  Alarm      Resource
   			 * Details
			 * -------------------------------------------------------------------------------
			 * 5          2019/01/19 20:27:51.40  WARNING   59-2004-1  Port 1/1/17
   			 * Interface 1/1/17 is not operational
			 * 
			 * 4          2019/01/19 20:27:51.40  WARNING   59-2004-1  Port 1/1/3
   			 * Interface 1/1/3 is not operational
			 * 
			 * 2          2019/01/19 20:26:45.70  WARNING   59-2004-1  Port 1/1/1
   			 * Interface 1/1/1 is not operational
			 * ===============================================================================
			 * A:KBFG-K_Building#
			 * 
			 * </pre>
			 * 
			 */
			String lines[] = rtrv.split("\r\n");

			int tick = 0;
			boolean bStart = false;
			List<String> dataList = new ArrayList<String>();
			String processedLine = ""; // TODO 우선 시스로그 포멧을 따라간다.
			for (String line : lines) {
				if (line.contains("---")) {
					bStart = true;
					continue;
				}
				if (bStart == true) {
					if (line.contains("===")) {
						dataList.add(processedLine);
						break;
					}
					
					switch (tick) {
					case 0:
						List<String> list = VendorBaseParser.split(line, " ");
						processedLine += list.get(1) + " ";
						processedLine += list.get(2).substring(0, 8) + "|";
						processedLine += dcn.getIpAddress() + "|"; // SYSLOG_IP_PRIMARY
						processedLine += dcn.getIpAddress() + "|"; // SYSLOG_IP_SECONDRARY
						processedLine += dcn.getEquipTidVal() + "|";
						processedLine += list.get(3).toLowerCase() + "|";
						processedLine += list.get(4) + " ";
						processedLine += "[" + list.get(5);
						if (list.size() > 6) {
							processedLine += " " + list.get(6) + "]";
						} else {
							processedLine += "]";
						}
						tick++;
						break;
					case 1:
						processedLine += ": " + line; 
						tick++;
						break;
					case 2:
						dataList.add(processedLine);
						processedLine = "";
						tick = 0;
						break;
					}
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

	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		try {

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());

			List<DetectedNeVo> neList = new ArrayList<DetectedNeVo>();

			DetectedNeVo ne = new DetectedNeVo();
			ne.fill(dcn);
			ne.setIpAddr(dcn.getIpAddress());
			ne.setDetectedMstime(System.currentTimeMillis());
			neList.add(ne);

			rtrvVer(ne);

			rtrvCard(ne); 

			this.rtrvPort(ne);

			return neList;
			
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	@Override
	public List<PsVo> collectPs() throws ConnectException, Exception {
		return null;
	}

	private TRMS_NET_EQUIP_CAPA_CL_CD getPortSpeedCd(String s) {
		
		if (s.indexOf("10G") > 0) {
			return AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._10G;
		} else if (s.indexOf("GIGE") > 0) {
			return AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD._1G;
		} else {
			return AdamsCfg.TRMS_NET_EQUIP_CAPA_CL_CD.Unknown;
		}

	}

	private int rtrvCard(DetectedNeVo ne) throws Exception {

		String result = send("show card");
		// ===============================================================================
		// Card Summary
		// ===============================================================================
		// Slot Provisioned Type Admin Operational Comments
		// Equipped Type (if different) State State
		// -------------------------------------------------------------------------------
		// 1 iom-sas up up
		// A sfm-sas up up/active
		// ===============================================================================

		DetectedCardVo shelf = DetectedCardVo.makeStaticShelf(SHELF_NAME);
		ne.getCardList().add(shelf);
		
		DetectedCardVo card;
		String lines[] = result.split("\n");
		boolean isCard = false;
		for (String line : lines) {

			if (isCard) {
				String ss[] = line.split(" +");
				if (ss.length >= 4) {
					card = new DetectedCardVo();
					card.setParentSlotNm(SHELF_NAME);
					card.setCardModel(ss[1]);
					card.setCardNm("1-"+ss[0]);
					card.setSlotNm(ss[0]);
					card.setCardState(ss[2]);
					ne.getCardList().add(card);
					Logger.logger.trace("CardNm[{}], CardModel([{}] Completed",card.getCardNm() , card.getCardModel());
					
				}
				continue;
			}

			if (line.startsWith("-------")) {
				isCard = true;
				continue;
			}

		}
		return ne.getCardList().size();
	}
	private String rtrv(String command) throws Exception {

		String result = send(command, PROMPT, "Press any key to continue (Q to quit)");
		do {

			if (Emulator.isPrompt(result, new String[] { PROMPT })) {
				return result;
			}

			if (Emulator.isPrompt(result, new String[] { "Press any key to continue (Q to quit)" })) {
				// --More--이면 공백을 보낸다.
				String e = send(" ", "#", "Press any key to continue (Q to quit)");
				if (e != null) {
					result += e;
				} else {
					return result;
				}
			}

		} while (true);

	}
	private int rtrvPort(DetectedNeVo ne) throws Exception {
		String result =  rtrv("show port");
		
		final int PORT_ID = 0;
		final int PORT_STATE = 3;
		

		// ===============================================================================
		// Ports on Slot 1
		// ===============================================================================
		// Port Admin Link Port Cfg Oper LAG/ Port Port Port C/QS/S/XFP/
		// Id State State MTU MTU Bndl Mode Encp Type MDIMDX
		// -------------------------------------------------------------------------------
		// 1/1/1 Up No Down 9212 9212 - accs null xcme GIGE-SX
		// 1/1/2 Up Yes Up 9212 9212 - accs null xcme MDI GIGE-SX
		// 1/1/3 Up No Down 1514 1514 - accs null xcme
		// 1/1/4 Down No Down 9212 9212 - netw null xcme
		// 1/1/5 Down No Down 9212 9212 - netw null xcme
		// 1/1/6 Down No Down 9212 9212 - netw null xcme
		// 1/1/7 Down No Down 9212 9212 - netw null xcme
		// 1/1/8 Down No Down 9212 9212 - netw null xcme
		// 1/1/9 Down No Down 9212 9212 - netw null xcme
		// 1/1/10 Down No Down 9212 9212 - netw null xcme
		// 1/1/11 Down No Down 9212 9212 - netw null xcme
		// 1/1/12 Down No Down 9212 9212 - netw null xcme
		// 1/1/13 Down No Down 9212 9212 - accs dotq xcme
		// 1/1/14 Down No Down 9212 9212 - netw null xcme
		// 1/1/15 Down No Down 9212 9212 - netw null xcme
		// 1/1/16 Down No Down 9212 9212 - netw null xcme
		// 1/1/17 Up No Down 9212 9212 - accs null xcme
		// 1/1/18 Down No Down 9212 9212 - accs null xcme
		// 1/1/19 Down No Down 9212 9212 - netw null xcme
		// 1/1/20 Down No Down 9212 9212 - netw null xcme
		// 1/1/21 Down No Down 9212 9212 - netw null xcme
		// 1/1/22 Down No Down 9212 9212 - netw null xcme
		// 1/1/23 Up Yes Up 9212 9212 - netw null xgige 10GBASE-LR 10*
		// 1/1/24 Down No Down 9212 9212 - netw null xgige
		// 1/1/25 Up Yes Up 9212 9212 - netw null xgige 10GBASE-LR 10*
		// 1/1/26 Up Yes Up 9212 9212 - accs null xgige 10GBASE-SR 10G*
		//
		// ===============================================================================
		// Ports on Slot A
		// ===============================================================================
		// Port Admin Link Port Cfg Oper LAG/ Port Port Port C/QS/S/XFP/
		// Id State State MTU MTU Bndl Mode Encp Type MDIMDX
		// -------------------------------------------------------------------------------
		// A/1 Up No Down 1514 1514 - netw null faste
		// ===============================================================================

		DetectedPortVo port;
		String lines[] = result.split("\n");
		String ss[];
		String cardNm = null;
		boolean isPort = false;
		for (String line : lines) {
			ss = line.split(" +");
			
			if(line.contains("Press")){
				continue;
			}

			if (isPort && ss.length >= 10) {
				port = new DetectedPortVo();
				port.setCardNm(cardNm);
				port.setSlotNm(cardNm);
				port.setPortDescr(ss[PORT_ID].replaceAll("/", "-"));
				port.setPortAlsNm(port.getPortDescr());
				port.setPortNo(getPortNo(port.getPortDescr()));
				port.setPortSpeedCd(getPortSpeedCd(line).getCode());
				port.setPortState(ss[PORT_STATE]);
				ne.getPortList().add(port);
				
				DetectedCardVo card  = ne.getCardBySlotNm(port.getSlotNm());	
				if (card != null) {
					card.setPortCnt(card.getPortCnt() + 1);
					port.setPortNo(card.getPortCnt());
				}
				Logger.logger.debug("Slot[{}]Port[{}]:[{}]:[{}]:[{}], Completed",  cardNm,port.getPortDescr(),port.getPortSpeedCd(),port.getPortState());
				continue;
			}

			if (line.startsWith("Ports on")) {
				cardNm = ss[3].trim();
			} else if (line.startsWith("-------")) {
				isPort = true;
				continue;
			}

			isPort = false;
		}
		
		return ne.getCardList().size();
	}

	private void rtrvVer(DetectedNeVo ne) throws Exception {

		String result = send("show version");

		String lines[] = result.split("\n");
		if (lines.length > 0 && lines[1].contains("Nokia")) {
			String ss[] = lines[1].split(" ");
			if (ss.length > 0) {
				ne.setVerSw(ss[0]);
				ne.setVerFw(ss[0]);
				ne.setNetName(ss[3]);
				Logger.logger.trace("Ver[{}], Name[{}] Completed", ne.getVerSw() , ne.getNetName());
			}
		}

		// TiMOS-B-9.0.R8 both/hops Nokia SAS-T 12F10T 4XFP ETR 7210 Copyright
		// (c) 2000-2017 Nokia.
		// All rights reserved. All use subject to applicable license
		// agreements.
		// Built on Fri Aug 4 13:46:15 IST 2017 by builder in
		// /home/builder/9.0B1/R8/panos/main
	}
}

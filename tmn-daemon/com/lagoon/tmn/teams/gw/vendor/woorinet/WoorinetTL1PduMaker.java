package com.lagoon.tmn.teams.gw.vendor.woorinet;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.NetPduTL1;
import fxms.nms.co.tl1_2.vo.ACK;

public class WoorinetTL1PduMaker extends NetPduMakerTL1 {
	
//	public static void main(String[] args) {
//		String s = "   TL1ADAPTER 2019-09-30 17:59:58\n"+
//"M  1 COMPLD\n"+
//"    /* UID */\n"+
//"   \"teams\"\n"+
//";";
//		
//		WoorinetTL1PduMaker maker = new WoorinetTL1PduMaker();
//		try {
//			NetPduTL1 pdu =	maker.makePdu(s);
//			System.out.println(pdu);
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//	
//	}
	
//
//	   TL1ADAPTER 2019-10-08 17:13:39
//	A  133849 REPT EVT SESSION-CHECK
//	;
//
//	   JCN▒▒▒_▒▒▒▒COT1 2019-10-08 17:14:59
//	A  133850 REPT TCA
//	   /* AID,UNIT,SIGNAL,TIME,PM_ELEMENT,DATETIME */
//	   "M04-P1,MXG02UB,10GE,PM15M,UAS,2019-10-08 17:15:00"
//	;
//
//	   JCN▒▒▒_▒▒▒▒COT1_▒▒õ1 2019-10-08 17:15:00
//	A  133851 REPT TCA
//	   /* AID,UNIT,SIGNAL,TIME,PM_ELEMENT,DATETIME */
//	   "M05-P1,MXG02UB,10GE,PM15M,UAS,2019-10-08 17:15:00"
//	;
//
//	   TL1ADAPTER 2019-10-08 17:15:39
//	A  133852 REPT EVT SESSION-CHECK
//	;

	   

	public WoorinetTL1PduMaker() {
		super("WoorinetPdu", Logger.logger);
		setCharset("euc-kr");
	}

	@Override
	public NetPduTL1 makePdu(String s) throws Exception {
		logger.debug("RECV=[{}]", s);

		String contents = s.trim();
		String line[] = contents.split("\n");

		if (line.length == 2 && line[1].charAt(0) == '<') {
			return new ACK(s);
		}

		int index = 0; // 첫번째 라인은 명령어가 온다.
		for (int i = index; i < line.length; i++) {
			if (line[i].trim().length() > 0) {
				index = i;
				break;
			}
		}

		try {
			return makePdu(index, line, s);
		} catch (Exception e) {

			if (line[line.length - 1].charAt(0) == '<') {
				return new ACK(s);
			}

			throw e;

		}

	}

}

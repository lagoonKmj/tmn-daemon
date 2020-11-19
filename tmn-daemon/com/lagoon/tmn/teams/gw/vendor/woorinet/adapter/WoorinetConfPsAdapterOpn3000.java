package com.lagoon.tmn.teams.gw.vendor.woorinet.adapter;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TelnetConfAdapter;

import subkjh.bas.co.log.Logger;

/**
 * OPN-3000 일일 점검 아답터
 * 
 * @author subkjh(김종훈)
 *
 */
public class WoorinetConfPsAdapterOpn3000 extends TelnetConfAdapter<EquipDcn>  implements ConfAdapter{

	public static void main(String[] args) {
		String s = "[ 1]:[  OK]:[----]:[system_info]";
		s = "            IN-ERROR-PACKETS               0           0           0           0";
		// String ss[] = WoorinetConfAdapterOpn3000.split(s);
		// System.out.println(Arrays.toString(ss));

		System.out.println(Arrays.toString(s.trim().split(" +")));
	}
	

	public WoorinetConfPsAdapterOpn3000(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
		this.setLoginPrompt("login:", "Password:", new String[] { "#" });
		telnet.setCharset("euc-kr");
	}
	

	/**
	 * 광레벨,온도를 수집한다.
	 * 
	 * @throws Exception
	 */
	public List<PsVo> collectPs() throws Exception {
		List<PsVo> psList = new ArrayList<PsVo>();
		try {

			if (dcn.getConsPortNum() <= 0) {
				dcn.setConsPortNum(23);
			}
			if (dcn.getLoginId() == null) {
				dcn.setLoginId("admin");
			}
			if (dcn.getLoginPwd() == null) {
				dcn.setLoginPwd("admin");
			}

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum(), dcn.getLoginId(), dcn.getLoginPwd());

			psList = rtrvOpticModule();

			return psList;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	/**
	 * 광레벨 정보
	 * @return 
	 * 
	 * @throws Exception
	 */
	private List<PsVo> rtrvOpticModule() throws Exception {
		String result = send("rtrv-optic-module:;");

		List<String> list = getContents(result);
		List<PsVo> psList = new ArrayList<PsVo>();
		String ss[];
		String portNm;
		String scardNm;
		String val;

		for (String s : list) {

			ss = s.replaceAll("\"", "").split(",");

			if (ss.length < 20) {
				continue;
			}

			// AID
			portNm = ss[0].split("=")[1];
			scardNm = portNm.split("-")[0];

			float tx;
			float rx;
			float temper;
			// TX-POWER
			val = ss[14].split("=")[1];
			if (VendorBaseParser.isNumeric(val)) {
				tx= Float.parseFloat(val);
			}else{
				tx = 0;
			}
			psList.add(new PsVo(dcn.getEquipId(), scardNm, portNm, "TX", PsVo.PSCODE_OPT_LEVEL_CURR, tx));
			Logger.logger.debug("TX[{}], Completed", tx);

			// RX-POWER			
			val = ss[15].split("=")[1];
			if (VendorBaseParser.isNumeric(val)) {
				rx = Float.parseFloat(val);
			}else{
				rx = 0;
			}
			psList.add(new PsVo(dcn.getEquipId(), scardNm, portNm, "RX", PsVo.PSCODE_OPT_LEVEL_CURR, rx));
			Logger.logger.debug("RX[{}], Completed", rx);

			// TEMP
			val = ss[22].split("=")[1];
			if (VendorBaseParser.isNumeric(val)) {
				temper = Float.parseFloat(val);
			}else{
				temper = 0;
			}
			
			psList.add(new PsVo(dcn.getEquipId(), scardNm, portNm, null, PsVo.PSCODE_TEMPERRATURE, temper));
			Logger.logger.debug("Temper[{}], Completed", temper);
			
		
		}
		return psList;

		// WNO3000_송파COT01 2019-08-30,17:47:02
		// M 11505310 COMPLD
		// "AID=S01-P1,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P2,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P3,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P4,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P5,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P6,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"

	}

	private List<String> getContents(String result) {

		String lines[] = result.split("\n");
		boolean isData = false;
		List<String> list = new ArrayList<String>();
		String s;

		for (int i = 0; i < lines.length; i++) {

			s = lines[i].trim();

			if (s.length() == 1 && s.equals(";")) {
				break;
			}

			if (isData) {
				if (s.length() > 1) {
					list.add(s);
				}
			} else if (s.startsWith("M") && s.contains("COMPLD")) {
				isData = true;
			}
		}

		return list;
	}


	@Override
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException,
			Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
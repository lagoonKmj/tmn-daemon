package com.lagoon.tmn.teams.gw.vendor.woorinet.adapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OCL28011;
import com.lagoon.tmn.teams.fxms.dbo.OCL28012;
import com.lagoon.tmn.teams.fxms.dbo.OCL28101;
import com.lagoon.tmn.teams.fxms.dbo.OCL28102;
import com.lagoon.tmn.teams.fxms.dbo.OCL28103;
import com.lagoon.tmn.teams.fxms.dbo.OCL28104;
import com.lagoon.tmn.teams.fxms.dbo.OCL28105;
import com.lagoon.tmn.teams.fxms.dbo.OCL28106;
import com.lagoon.tmn.teams.fxms.dbo.OCL28201;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221Ext;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.TelnetConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.vo.Opn3000Vo;

import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.bas.co.def.PS_TYPE;

/**
 * OPN-3000 일일 점검 아답터
 * 
 * @author subkjh(김종훈)
 *
 */
public class WoorinetConfAdapterOpn3000 extends TelnetConfAdapter<EquipDcn> {

	public static void main(String[] args) {
		String s = "[ 1]:[  OK]:[----]:[system_info]";
		s = "            IN-ERROR-PACKETS               0           0           0           0";
		// String ss[] = WoorinetConfAdapterOpn3000.split(s);
		// System.out.println(Arrays.toString(ss));

		System.out.println(Arrays.toString(s.trim().split(" +")));
	}

	private static String[] split(String s) {
		String ss[] = s.split(":");
		for (int i = 0; i < ss.length; i++) {
			ss[i] = ss[i].replace('[', ' ');
			ss[i] = ss[i].replace(']', ' ');

			ss[i] = ss[i].trim();
		}
		return ss;
	}

	public WoorinetConfAdapterOpn3000(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
		this.setLoginPrompt("login:", "Password:", new String[] { "#" });
		telnet.setCharset("euc-kr");
	}

	/**
	 * 일일점검을 실시한다.
	 * 
	 * @throws Exception
	 */
	public Opn3000Vo rtrvDaily() throws Exception {

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

			Opn3000Vo ret = getData(dcn);

			collectRt();

			return ret;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			close();
		}
	}

	private void collectRt() {

		if (dcn.getRtList() == null) {
			return;
		}

		for (EquipDcn equip : dcn.getRtList()) {
			try {
				String ret = this.send("telnet " + equip.getIpAddress(), "login:", "timeout");
				if (ret.contains("login:") == false) {
					continue;
				}

				this.send(equip.getLoginId(), "Password:");
				// TODO 암호 틀린 경우

				this.send(equip.getLoginPwd(), "#");
				// TODO
			} catch (Exception e) {

				try {
					// TODO 계정 오류로 접속이 불가한 경우 강제 끊는 신호 보내기
				} catch (Exception e2) {

				}

				Logger.logger.error(e);
				continue;
			}

			try {
				Opn3000Vo rtRet = getData(equip);
				try {
					GwApi.getApi().addOpn3000DailyReport(rtRet);
					Logger.logger.info(Logger.makeString("OPN-3000 " + equip.getEquipIpAddr() + " Daily", "OK"));
				} catch (Exception e) {
					Logger.logger.error(e);
					Logger.logger.fail(Logger.makeString("OPN-3000 " + equip.getEquipIpAddr() + " Daily", "fail"));
				}

			} catch (Exception e) {
				Logger.logger.error(e);
			}

			try {
				this.send("exit", "#");
			} catch (Exception e) {
				Logger.logger.error(e);
			}

		}
	}

	private Opn3000Vo getData(EquipDcn equip) throws Exception {

		Opn3000Vo ret = new Opn3000Vo();

		ret.setEquipId(equip.getEquipId());

		rtrvUnitHealth(ret);

		rtrvDbCheck(ret);
		rtrvDbfileCheck(ret);
		rtrvDbramdiskCheck(ret);
		rtrvDate(ret);
		rtrvEmsNoti(ret);
		rtrvNeNoti(ret);

		rtrvUnitTemperaturepm(ret);

		rtrvFanInfo(ret);
		rtrvProgramInfoall(ret);
		rtrvClk(ret);
		rtrvClkSsm(ret);

		rtrvOpticModule(ret);

		rtrvAlm(ret);

		rtrvMplsTe(ret);

		rtrvPm(ret);

		return ret;

	}

	/**
	 * 시스템 자원 관리<br>
	 * 
	 * @throws Exception
	 */
	private void rtrvUnitHealth(Opn3000Vo opn3000) throws Exception {

		String result = send("rtrv-unit-health;");
		List<String> list = getContents(result);

		OIV10221Ext o;
		String slotNm;
		String clctDtm = String.valueOf(FxApi.getDate());

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}
			String ss[] = s.replaceAll("\"", "").split(",");

			o = new OIV10221Ext();

			// SLOT
			slotNm = ss[0].split("=")[1];

			o.setClctDtm(clctDtm);
			o.setEquipId(opn3000.getEquipId());
			o.setEquipConsItmNm(slotNm);
			o.setScardNm(slotNm);

			// CPU-USAGE
			o.setCpuUseRt(Double.valueOf(ss[1].split("=")[1].replaceAll("%", "")));

			// MEMORY-USAGE
			o.setMmrUseRt(Double.valueOf(ss[4].split("=")[1].replaceAll("%", "")));

			// TEMPERATURE
			o.setTmprVal(Double.valueOf(ss[6].split("=")[1]));

			opn3000.getOiv10221List().add(o);
		}

		// WNO3000_송파COT01 2019-08-30,17:45:15
		// M 11503537 COMPLD
		// /* CID-LID:PVID */
		// "AID=OMA,CPU-USAGE=6%,MEMORY-TOTAL=4088588K,MEMORY-FREE=1595620K,MEMORY-USAGE=60%,IS-WORKING=TRUE,TEMPERATURE=30.5"
		// "AID=OMB,CPU-USAGE=1%,MEMORY-TOTAL=4088588K,MEMORY-FREE=3777240K,MEMORY-USAGE=7%,IS-WORKING=FALSE,TEMPERATURE=27.5"
		// "AID=S01,CPU-USAGE=13%,MEMORY-TOTAL=2073200K,MEMORY-FREE=669616K,MEMORY-USAGE=67%,IS-WORKING=TRUE,TEMPERATURE=44.5"
		// "AID=S05,CPU-USAGE=10%,MEMORY-TOTAL=2073200K,MEMORY-FREE=982116K,MEMORY-USAGE=52%,IS-WORKING=TRUE,TEMPERATURE=31.0"
		// "AID=OXCA,CPU-USAGE=4%,MEMORY-TOTAL=2073288K,MEMORY-FREE=1515584K,MEMORY-USAGE=26%,IS-WORKING=TRUE,TEMPERATURE=45.5"
		// "AID=OXCB,CPU-USAGE=4%,MEMORY-TOTAL=2073288K,MEMORY-FREE=1515344K,MEMORY-USAGE=26%,IS-WORKING=FALSE,TEMPERATURE=42.5"
		// "AID=S06,CPU-USAGE=10%,MEMORY-TOTAL=2073200K,MEMORY-FREE=981944K,MEMORY-USAGE=52%,IS-WORKING=TRUE,TEMPERATURE=36.0"
		// "AID=S10,CPU-USAGE=13%,MEMORY-TOTAL=2073200K,MEMORY-FREE=669292K,MEMORY-USAGE=67%,IS-WORKING=TRUE,TEMPERATURE=39.0"
		// ;

	}

	/**
	 * DB검사 관리<br>
	 * (1) DRAM/FLASH<br>
	 * 
	 * @throws Exception
	 */
	private void rtrvDbCheck(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-db-check;");

		makeOCL28011("DRAM/FLASH", result, opn3000);

		// WNO3000_▒▒▒▒01 2019-10-17,15:22:47
		// M 12678904 COMPLD
		// [ 1]:[ OK]:[----]:[system_info]"
		// [ 2]:[ OK]:[----]:[user_info]"
		// [ 3]:[ OK]:[----]:[slot_info]"
		// [ 4]:[ OK]:[----]:[l2_info]"
		// [ 5]:[ OK]:[----]:[l2_sec_info]"
		// [ 6]:[ OK]:[----]:[l3_info]"
		// [ 7]:[ OK]:[----]:[ext_l3_info]"
		// [ 8]:[ OK]:[----]:[tm_qos_info]"
		// [ 9]:[ OK]:[----]:[port_loop]"
		// [10]:[ OK]:[----]:[optic_info]"
		// [11]:[ OK]:[----]:[tpid_profile_info]"
		// [12]:[ OK]:[----]:[metaswitch_info]"
		// [13]:[ OK]:[----]:[vsi_info]"
		// [14]:[ OK]:[----]:[ac_info]"
		// [15]:[ OK]:[----]:[l2encap_info]"
		// [16]:[ OK]:[----]:[mpls_label_info]"
		// [17]:[ OK]:[----]:[static_tunnel_info]"
		// [18]:[ OK]:[----]:[static_pw_info]"
		// [19]:[ OK]:[----]:[mspw_info]"
		// [20]:[ OK]:[----]:[mpls_comm_info]"
		// [21]:[ OK]:[----]:[mpls_prot_comm_info]"
		// [22]:[ OK]:[----]:[load_info]"
		// [23]:[ OK]:[----]:[fmpm_info]"
		// [24]:[ OK]:[----]:[net_info]"
		// [25]:[ OK]:[----]:[clk_info]"
		// [26]:[ OK]:[----]:[mstp_info]"
		// [27]:[ OK]:[----]:[lacp_info]"
		// [28]:[ OK]:[----]:[eth_oam_info]"
		// [29]:[ OK]:[----]:[eth_oam_mip_info]"
		// [30]:[ OK]:[----]:[mpls_tp_oam_info]"
		// [31]:[ OK]:[----]:[mpls_oam_mip_info]"
		// [32]:[ OK]:[----]:[pw_oam_info]"
		// [33]:[ OK]:[----]:[sec_oam_info]"
		// [34]:[ OK]:[----]:[efm_oam_info]"
		// [35]:[ OK]:[----]:[mpls_prot_cfg]"
		// [36]:[ OK]:[----]:[unit_prot]"
		// [37]:[ OK]:[----]:[sdh_prot]"
		// [38]:[ OK]:[----]:[pw_prot]"
		// [39]:[ OK]:[----]:[pm_mon]"
		// UPDATE TIME:[2019:10:17-15:3:0], Total Num:[39]"
		// ;

	}

	/**
	 * DB검사 관리<br>
	 * (2) ACTIVE/STANDBY
	 * 
	 * @throws Exception
	 */
	private void rtrvDbfileCheck(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-dbfile-check;");
		makeOCL28011("ACTIVE/STANDBY", result, opn3000);
	}

	/**
	 * DB검사 관리<br>
	 * (3) FLASH/RAMDISK
	 * 
	 * @throws Exception
	 */
	private void rtrvDbramdiskCheck(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-dbramdisk-check;");
		makeOCL28011("FLASH/RAMDISK", result, opn3000);
	}

	/**
	 * 시스템 시각 관리<br>
	 * 
	 * @throws Exception
	 */
	private void rtrvDate(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-date;");

		List<String> list = getContents(result);

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			OCL28011 ret = new OCL28011();

			long now = FxApi.getDate();
			long sysdate = ConfAdapter.getDate(s);

			ret.setEquipId(opn3000.getEquipId());
			ret.setClctItmClNm("DATE");
			ret.setCmprCtt(String.valueOf(now));
			ret.setClctItmCtt(String.valueOf(sysdate));

			long nowMstime = PS_TYPE.getMstimeByHstime(now);
			long sysdateMstime = PS_TYPE.getMstimeByHstime(sysdate);

			// 5분이상 차이나면
			if (Math.abs(nowMstime - sysdateMstime) >= 5 * 60 * 1000) {
				ret.setAbnCnt(1);
			} else {
				ret.setNormCnt(1);
			}

			opn3000.getOcl28011List().add(ret);
		}

		// WNO3000_송파COT01 2019-08-30,17:45:43
		// M 11504030 COMPLD
		// /* DATE,TIME */
		// "2019-08-30,17:45:43"
		// ;

	}

	/**
	 * 리포트 관리<br>
	 * (1) EMS 리포트
	 * 
	 * @throws Exception
	 */
	private void rtrvEmsNoti(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-ems-noti;");

		List<String> list = getContents(result);
		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}
			String ss[] = s.replaceAll("\"", "").split(",");

			OCL28012 ret = new OCL28012();
			ret.setEquipId(opn3000.getEquipId());
			ret.setNotiClVal("EMS");
			ret.setNotiRcvObjNm(ss[0].split(":")[1]);
			ret.setAlmNotiYn("ON".equals(ss[1]) ? "Y" : "N");
			ret.setPrfmNotiYn("ON".equals(ss[2]) ? "Y" : "N");
			ret.setSwNotiYn("ON".equals(ss[3]) ? "Y" : "N");

			opn3000.getOcl28012List().add(ret);
		}

		// WNO3000_송파COT01 2019-08-30,17:45:50
		// M 11504141 COMPLD
		// /* NAME:IP,ALM-NOTI,PM-NOTI,SW-NOTI */
		// "12.4.132.84:12.4.132.84,ON,ON,ON"

	}

	/**
	 * 리포트 관리<br>
	 * (2) NE 리포트
	 * 
	 * @throws Exception
	 */
	private void rtrvNeNoti(Opn3000Vo opn3000) throws Exception {

		String result = send("rtrv-ne-noti;");

		List<String> list = getContents(result);

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			String ss[] = s.replaceAll("\"", "").split(",");

			if (ss.length >= 5) {
				OCL28012 ret = new OCL28012();
				ret.setEquipId(opn3000.getEquipId());
				ret.setNotiClVal("NE");
				ret.setNotiRcvObjNm(ss[0].split(":")[0]);
				ret.setAlmNotiYn("ON".equals(ss[0].split(":")[1]) ? "Y" : "N");
				ret.setPrfmNotiYn("ON".equals(ss[1]) ? "Y" : "N");
				ret.setSwNotiYn("ON".equals(ss[2]) ? "Y" : "N");
				ret.setSnmpNotiYn("ON".equals(ss[3]) ? "Y" : "N");
				ret.setSysLogNotiYn("ON".equals(ss[4]) ? "Y" : "N");

				opn3000.getOcl28012List().add(ret);
			}

		}

		// WNO3000_송파COT01 2019-08-30,17:45:56
		// M 11504245 COMPLD
		// /* TID:ALM-NOTI,PM-NOTI,SW-NOTI,SNMP-NOTI,SYSLOG-NOTI */
		// ;

	}

	/**
	 * 온도관리
	 * 
	 * @throws Exception
	 */
	private void rtrvUnitTemperaturepm(Opn3000Vo opn3000) throws Exception {

		String result = send("rtrv-unit-temperaturepm;");

		List<String> list = getContents(result);
		OIV10221 o;
		String ss[];
		String slotNm;

		for (String s : list) {

			ss = s.replaceAll("\"", "").split(",");

			// SLOT
			slotNm = ss[0].split("=")[1];

			o = opn3000.getOIV10221(slotNm);

			if (o != null) {

				// 장비 ID
				o.setEquipId(opn3000.getEquipId());

				// SLOT
				o.setEquipConsItmNm(slotNm);

				// UNIT
				o.setEquipConsItmTypNm(ss[1].split("=")[1]);

				// TEMPERATURE
				o.setTmprVal(Double.valueOf(ss[3].split("=")[1]));

			}
		}

		// WNO3000_송파COT01 2019-08-30,17:46:04
		// M 11504373 COMPLD
		// "AID=OMA,UNIT-TYPE=OMU,PM-TIME=PM_CURRENT,TEMPERATURE=30.50"
		// "AID=OMB,UNIT-TYPE=OMU,PM-TIME=PM_CURRENT,TEMPERATURE=30.50"
		// "AID=S01,UNIT-TYPE=OGE40U,PM-TIME=PM_CURRENT,TEMPERATURE=44.50"
		// "AID=S05,UNIT-TYPE=OMX24U,PM-TIME=PM_CURRENT,TEMPERATURE=31.00"
		// "AID=OXCA,UNIT-TYPE=OXCU,PM-TIME=PM_CURRENT,TEMPERATURE=45.50"
		// "AID=OXCB,UNIT-TYPE=OXCU,PM-TIME=PM_CURRENT,TEMPERATURE=42.50"
		// "AID=S06,UNIT-TYPE=OMX24U,PM-TIME=PM_CURRENT,TEMPERATURE=36.00"
		// "AID=S10,UNIT-TYPE=OGE40U,PM-TIME=PM_CURRENT,TEMPERATURE=39.00"
		// ;

	}

	/**
	 * FAN 관리
	 * 
	 * @throws Exception
	 */
	private void rtrvFanInfo(Opn3000Vo opn3000) throws Exception {

		String result = send("rtrv-fan-info;");

		List<String> list = getContents(result);
		String val;
		OCL28106 o;

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			String ss[] = s.replaceAll("\"", "").split(",");

			o = new OCL28106();
			o.setEquipId(opn3000.getEquipId());

			// FAN
			val = ss[0].split("=")[1];
			o.setFanStNm(val);

			// FANMODE
			val = ss[1].split("=")[1];
			o.setFanModeNm(val);

			// CURRENTMODE
			val = ss[2].split("=")[1];
			o.setFanStNm(val);

			// INC2HIGH
			val = ss[3].split("=")[1];
			o.setMaxSpeedUpmvCondVal(ConfAdapter.getInt(val));

			// INC2MID
			val = ss[4].split("=")[1];
			o.setMedmSpeedUpmvCondVal(ConfAdapter.getInt(val));

			// INC2LOW
			val = ss[5].split("=")[1];
			o.setMinSpeedUpmvCondVal(ConfAdapter.getInt(val));

			// DEC2MID
			val = ss[6].split("=")[1];
			o.setMedmSpeedDnmvCondVal(ConfAdapter.getInt(val));

			// DEC2LOW
			val = ss[7].split("=")[1];
			o.setMinSpeedUpmvCondVal(ConfAdapter.getInt(val));

			// DEC2OFF
			val = ss[8].split("=")[1];
			o.setFanOffCondVal(ConfAdapter.getInt(val));

			opn3000.getOcl28106List().add(o);
		}

		// WNO3000_송파COT01 2019-08-30,17:46:12
		// M 11504513 COMPLD
		// /* FAN,HIGHLOWOFF */
		// "FAN=HIGH,FANMODE=AUTO,CURRENTMODE=HIGH,INC2HIGH=50,INC2MID=45,INC2LOW=20,DEC2MID=40,DEC2LOW=25,DEC2OFF=10"

	}

	/**
	 * 버전관리
	 * 
	 * @throws Exception
	 */
	private void rtrvProgramInfoall(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-program-infoall;");

		List<String> list = getContents(result);
		String slotNm;
		String val;
		OIV10221Ext o;

		for (String s : list) {

			String ss[] = s.replaceAll("\"", "").split(",");

			// SLOT
			slotNm = ss[0].split("=")[1];

			o = opn3000.getOIV10221(slotNm);

			if (o == null) {
				continue;
			}

			// UNIT
			val = ss[1].split("=")[1];
			o.setEquipConsItmTypNm(val);

			// ACTIVE-BANK
			val = ss[2].split("=")[1];
			o.setActvnIdxNum(val);

			// BANK1 VER
			val = ss[4].split("=")[1];
			o.setFstEtcVerInfo(val);

			// BANK1 DATE
			val = ss[5].split("=")[1] + " " + ss[6];

			// BANK2 VER
			val = ss[9].split("=")[1];
			o.setScndEtcVerInfo(val);

			// BANK2 DATE
			val = ss[10].split("=")[1] + " " + ss[11];

			for (int i = 12; i < ss.length; i++) {
				if (ss[i].startsWith("RUN")) {

					i++;
					// RUN VER
					val = ss[i].split("=")[1];
					o.setVerInfo(val);

					// RUN DATE
					val = ss[i + 1].split("=")[1] + " " + ss[i + 2];
					o.setVerApplyMstime(PS_TYPE.getMstimeByHstime(ConfAdapter.getDate(val)));
					break;
				}
			}

		}

		// WNO3000_송파COT01 2019-08-30,17:46:36
		// M 11504889 COMPLD
		// "AID=OMA,UNIT=OMU,ACTIVE-BANK=1,BANK1[FILE=OPN3000.OMU.1.1.4,VER=1.1.4,DATE=2019-08-12,10:09:18,SIZE=44642049],BANK2[FILE=OPN3000.OMU.1.1.4,VER=1.1.4,DATE=2019-08-12,10:09:18,SIZE=44642049],RUN[FILE=OPN3000.OMU.1.1.4,VER=1.1.4,DATE=2019-08-12,10:09:18,SIZE=44642049],"

		// "AID=S10,UNIT=OGE40U,ACTIVE-BANK=1,BANK1[FILE=OPN3000.OPKU.1.1.5,VER=1.1.5,DATE=2019-11-08,09:30:35,SIZE=46882683],BANK2[FILE=OPN3000.OPKU.1.1.5,VER=1.1.5,DATE=2019-11-08,09:30:35,SIZE=46882683],BANK3[FILE=,VER=0.0.0,DATE=2000-00-00,00:00:00,SIZE=0],BANK4[FILE=,VER=0.0.0,DATE=2000-00-00,00:00:00,SIZE=0],RUN[FILE=OPN3000.OPKU.1.1.5,VER=1.1.5,DATE=2019-11-08,09:30:35,SIZE=46882683],"
	}

	/**
	 * 클럭원 정보
	 * 
	 * @throws Exception
	 */
	private void rtrvClk(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-clk;");

		List<String> list = getContents(result);
		int index = 0;

		OCL28103 o = new OCL28103();
		o.setEquipId(opn3000.getEquipId());

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			s = s.replaceAll("\"", "");

			if (index == 0) {
				// CLKMODE
				o.setEquipClkModeNm(s);
			} else if (index == 1) {
				// CURCLK
				o.setCurrEquipClkVal(s);
			} else if (index == 2) {
				// PRVCLK1,PRVCLK2,PRVCLK3,PRVCLK4,PRVCLK5,PRVCLK6,PRVCLK7,PRVCLK8
				String ss[] = s.split(",");

				if (ss.length >= 1)
					o.setEquipClkVal1(ss[0]);
				if (ss.length >= 2)
					o.setEquipClkVal2(ss[1]);
				if (ss.length >= 3)
					o.setEquipClkVal3(ss[2]);
				if (ss.length >= 4)
					o.setEquipClkVal4(ss[3]);
				if (ss.length >= 5)
					o.setEquipClkVal5(ss[4]);
				if (ss.length >= 6)
					o.setEquipClkVal6(ss[5]);
				if (ss.length >= 7)
					o.setEquipClkVal7(ss[6]);
				if (ss.length >= 8)
					o.setEquipClkVal8(ss[7]);

			} else if (index == 3) {
				// STARTTIME
				o.setStaDtm(ConfAdapter.getDateString(s));
			} else if (index == 4) {
				// OPRMODE
				o.setEquipMgmtModeNm(s);
			} else if (index == 5) {
				// PLL1_STATE
			} else if (index == 6) {
				// PLL2_STATE
			}

			index++;

		}

		opn3000.getOcl28103List().add(o);

		// WNO3000_송파COT01 2019-08-30,17:46:45
		// M 11505043 COMPLD
		// /* CLKMODE */
		// /* CURCLK */
		// /* PRVCLK1,PRVCLK2,PRVCLK3,PRVCLK4,PRVCLK5,PRVCLK6,PRVCLK7,PRVCLK8 */
		// /* STARTTIME */
		// /* OPRMODE */
		// /* PLL1_STATE */
		// /* PLL2_STATE */
		// "PHYSICAL"
		// "EXTB"
		// "EXTA,EXTB,---,---,---,---,---,---"
		// "2019-08-30,15:57:17"
		// "N_REVERTIVE"
		// "LOCK"
		// "---"
		// ;

	}

	/**
	 * 클럭품질 정보
	 * 
	 * @throws Exception
	 */
	private void rtrvClkSsm(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-clk-ssm;");

		List<String> list = getContents(result);
		String ss[];
		String clk;
		String val;
		OCL28104 o;
		String saPrv = null;
		String saStatus = null;

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			ss = s.replaceAll("\"", "").split(",");

			o = new OCL28104();
			o.setEquipId(opn3000.getEquipId());

			if (ss.length == 5) {
				// CLK:RXSSM
				clk = ss[0].split(":")[0];
				val = ss[0].split(":")[1];

				o.setEquipClkVal(clk);
				o.setRcvSyncQltyInfo("---".equals(val) ? "" : val);

				// PRVRXSSM
				val = ss[1];
				o.setSetRcvSyncQltyInfo("---".equals(val) ? "" : val);

				// TXSSM
				val = ss[2];
				o.setSendSyncQltyInfo("---".equals(val) ? "" : val);

				// PRVTXSSM
				val = ss[3];
				o.setSetSendSyncQltyInfo("---".equals(val) ? "" : val);

				// AVAILABLE
				val = ss[4];
				o.setAvailYn("ON".equals(val) ? "Y" : "N");

				opn3000.getOcl28104List().add(o);

			} else if (ss[0].length() == 1) {
				String aa[] = ss[0].split(":");
				if ("SA_PRV".equals(aa[0])) {
					saPrv = aa[1];
				} else if ("SA_STATUS".equals(aa[0])) {
					saStatus = aa[1];
				}
			}

		}

		for (OCL28104 e : opn3000.getOcl28104List()) {

			Logger.logger.trace("{} {}", e.getEquipId(), e.getEquipClkVal());

			e.setSaBitSetNm(saPrv);
			e.setSaBitStNm(saStatus);
		}

		// WNO3000_송파COT01 2019-08-30,17:46:53
		// M 11505174 COMPLD
		// /* CLK:RXSSM,PRVRXSSM,TXSSM,PRVTXSSM,AVAILABLE */
		// "CLK1:G811,---,---,---,ON"
		// "CLK2:G811,---,---,---,ON"
		// "CLK3:---,---,---,---,OFF"
		// "CLK4:---,---,---,---,OFF"
		// "CLK5:---,---,---,---,OFF"
		// "CLK6:---,---,---,---,OFF"
		// "CLK7:---,---,---,---,OFF"
		// "CLK8:---,---,---,---,OFF"
		// "OTHER:---,---,G811,---,---"
		// "DERIVED:---,---,G811,---,---"
		// "SA_PRV:SA_4"
		// "SA_STATUS:SA_8"

	}

	/**
	 * 광레벨 정보
	 * 
	 * @throws Exception
	 */
	private void rtrvOpticModule(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-optic-module:;");

		List<String> list = getContents(result);
		String ss[];
		String portNm;
		String scardNm;
		String val;
		OCL28101 o;
		OIV10221 ocard;

		for (String s : list) {

			ss = s.replaceAll("\"", "").split(",");

			if (ss.length < 20) {
				continue;
			}

			o = new OCL28101();
			o.setEquipId(opn3000.getEquipId());

			// AID
			portNm = ss[0].split("=")[1];
			o.setPortNm(portNm);

			scardNm = portNm.split("-")[0];
			o.setScardNm(scardNm);

			ocard = opn3000.getOIV10221(scardNm);
			if (ocard != null) {
				o.setScardTypNm(ocard.getEquipConsItmTypNm());
			}

			// MODULE-EQUIP
			val = ss[1].split("=")[1];
			o.setModulePutonYn("0".equals(val) ? "N" : "Y");

			// MODULE-TYPE
			val = ss[2].split("=")[1];

			// CORE-NUM
			val = ss[3].split("=")[1];
			o.setCoreNum(val);

			// STM64-SUPPORT
			val = ss[4].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("STM64");
			}

			// STM16-SUPPORT
			val = ss[5].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("STM16");
			}

			// STM4-SUPPORT
			val = ss[6].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("STM4");
			}

			// STM1-SUPPORT
			val = ss[7].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("STM1");
			}

			// GELX-SUPPORT
			val = ss[8].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("GELX");
			}

			// GESX-SUPPORT
			val = ss[9].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("GESX");
			}

			// GETX-SUPPORT
			val = ss[10].split("=")[1];
			if ("1".equals(val)) {
				o.setSuprtSgnlVal("GETX");
			}

			// TX-WAVE-LENGTH
			val = ss[11].split("=")[1];
			o.setTxWvlengVal(ConfAdapter.getFloat(val));

			// RX-WAVE-LENGTH
			val = ss[12].split("=")[1];
			o.setRxWvlengVal(ConfAdapter.getFloat(val));

			// DISTANCE
			val = ss[13].split("=")[1];
			o.setDistInfo(val);

			// TX-POWER
			val = ss[14].split("=")[1];
			o.setTxPwrVal(ConfAdapter.getFloat(val));

			// RX-POWER
			val = ss[15].split("=")[1];
			o.setRxPwrVal(ConfAdapter.getFloat(val));

			// TEMP
			val = ss[22].split("=")[1];
			o.setTmprVal(ConfAdapter.getFloat(val));

			opn3000.getOcl28101List().add(o);
		}

		// WNO3000_송파COT01 2019-08-30,17:47:02
		// M 11505310 COMPLD
		// "AID=S01-P1,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P2,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P3,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P4,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P5,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"
		// "AID=S01-P6,MODULE-EQUIP=0,MODULE-TYPE=GENERAL,CORE-NUM=2-CORE,STM64-SUPPORT=0,STM16-SUPPORT=0,STM4-SUPPORT=0,STM1-SUPPORT=0,GELX-SUPPORT=0,GESX-SUPPORT=0,GETX-SUPPORT=0,TX-WAVE-LENGTH=0.00,RX-WAVE-LENGTH=0.00,DISTANCE=0.000000,TX-POWER=0.000000,RX-POWER=0.000000,TX-BIAS-CURRENT=0.000000,VENDOR=,PART-NUMBER=,SERIAL-NUMBER=,CONNECTOR-TYPE=UNKNOWN,ANGLE-TYPE=UNKNOWN,TEMP=0.000"

	}

	/**
	 * 경보내역
	 * 
	 * @throws Exception
	 */
	private void rtrvAlm(Opn3000Vo opn3000) throws Exception {

		String result = send("rtrv-alm;");

		List<String> list = getContents(result);
		String ss[];
		String val;
		OCL28201 o;

		for (String s : list) {

			if (s.startsWith("/*")) {
				continue;
			}

			ss = s.replaceAll("\"", "").split(",");

			o = new OCL28201();
			o.setEquipId(opn3000.getEquipId());

			// PID:SEVERITY
			val = ss[0].split(":")[0];
			o.setAlmLocInfo(val);

			val = ss[0].split(":")[1];
			o.setAlmGrInfo(val);

			// SA
			val = ss[1];

			// UNIT-TYPE
			val = ss[2];
			o.setScardTypNm(val);

			// ALARM-TYPE
			val = ss[3];
			o.setAlmTypInfo(val);

			// DATE
			o.setOccrDtm(ConfAdapter.getDateString(ss[4]));

			opn3000.getOcl28201List().add(o);
		}

		// WNO3000_송파COT01 2019-08-30,17:47:13
		// M 11505510 COMPLD
		// /* PID:SEVERITY,SA,UNIT-TYPE,ALARM-TYPE,DATE */
		// "WNO3000_송파COT01-OPN3000-S01-P1:CR,SA,OGE40U,MODULE_OUT,2019-08-30 15:57:24"
		// "WNO3000_송파COT01-OPN3000-S05-P2:CR,SA,OMX24U,LINK_DOWN,2019-08-30 15:57:25"
		// "WNO3000_송파COT01-OPN3000-S06-P3:CR,SA,OMX24U,LINK_DOWN,2019-08-30 15:57:29"
		// "WNO3000_송파COT01-OPN3000-S10-P4:CR,SA,OGE40U,MODULE_OUT,2019-08-30 15:57:34"
		// ;

	}

	/**
	 * 트렁크 용량 관리
	 * 
	 * @throws Exception
	 */

	private void rtrvMplsTe(Opn3000Vo opn3000) throws Exception {
		String result = send("rtrv-mpls-te;");
		List<String> list = getContents(result);
		String ss[];
		String portNm;
		String scardNm;
		String val;
		OCL28105 o;

		for (String s : list) {

			ss = s.replaceAll("\"", "").split(",");

			o = new OCL28105();
			o.setEquipId(opn3000.getEquipId());

			// PID:SEVERITY
			portNm = ss[0].split(":")[0];
			scardNm = portNm.split("-")[0];
			o.setPortNm(portNm);
			o.setScardNm(scardNm);

			// TE-METRIC
			val = ss[1].split("=")[1];

			// MAX-BW
			val = ss[2].split("=")[1];
			o.setMaxBandwSpeedVal(ConfAdapter.getLong(val));

			// MAX-CIR-BW
			val = ss[3].split("=")[1];
			o.setMaxCirVal(ConfAdapter.getLong(val));

			// RES-CIR-BW
			val = ss[4].split("=")[1];

			// CIR-USE-RATE
			val = ss[5].split("=")[1];
			o.setCirUseRt(ConfAdapter.getFloat(val));

			// CIR-USE-RATE-TH
			val = ss[6].split("=")[1];
			o.setCirUseRtThrsVal(ConfAdapter.getFloat(val));

			// MAX-PIR-BW
			val = ss[7].split("=")[1];
			o.setMaxPirVal(ConfAdapter.getLong(val));

			// RES-PIR-BW
			val = ss[8].split("=")[1];

			// PIR-USE-RATE
			val = ss[9].split("=")[1];
			o.setPirUseRt(ConfAdapter.getFloat(val));

			// PPIR-USE-RATE-TH
			val = ss[10].split("=")[1];
			o.setPirUseRtThrsVal(ConfAdapter.getFloat(val));

			opn3000.getOcl28105List().add(o);

		}
		// WNO3000_송파COT01 2019-08-30,17:47:20
		// M 11505630 COMPLD
		// "S05-P2:IF-INDEX=701,TE-METRIC=7,MAX-BW=10000Mbps,MAX-CIR-BW=9950Mbps,RES-CIR-BW=0Mbps,CIR-USE-RATE=0.00%,CIR-USE-RATE-TH=90%,MAX-PIR-BW=10000Mbps,RES-PIR-BW=0Mbps,PIR-USE-RATE=0.00%,PIR-USE-RATE-TH=0%"
		// "S06-P3:IF-INDEX=1002,TE-METRIC=7,MAX-BW=10000Mbps,MAX-CIR-BW=9950Mbps,RES-CIR-BW=0Mbps,CIR-USE-RATE=0.00%,CIR-USE-RATE-TH=90%,MAX-PIR-BW=10000Mbps,RES-PIR-BW=0Mbps,PIR-USE-RATE=0.00%,PIR-USE-RATE-TH=0%"

	}

	/**
	 * 성능관리
	 * 
	 * @throws Exception
	 */
	private void rtrvPm(Opn3000Vo opn3000) throws Exception {
		String result1 = send("rtrv-slot;");
		String ss[];
		String slotNm;
		String portNm = null;
		String prvUnit;
		OCL28102 o;
		List<String> list = getContents(result1);
		boolean dataStart = false;

		for (String s : list) {

			if (result1.contains("COT : SLOT INFO")) {
				if (s.contains("-----")) {
					if (dataStart == false) {
						dataStart = true;
						continue;
					} else { // 두번째 "----" 를 만나면 끝
						break;
					}
				} else if (dataStart == true) {
					// SLOT | PRV-UNIT
					ss = s.split("\\|");
					slotNm = ss[0].trim();
					prvUnit = ss[1].trim();
				} else {
					continue;
				}

			} else {
				if (s.startsWith("/*")) {
					continue;
				}
				// SLOT:PRV-UNIT
				ss = s.replaceAll("\"", "").split(",");
				slotNm = ss[0].split(":")[0].trim();
				prvUnit = ss[0].split(":")[1].trim();
			}

			if (slotNm.startsWith("S") && "----".equals(prvUnit) == false) {

				String result2 = send("rtrv-pm-ethernet::" + slotNm + ";");

				List<String> list2 = getContents(result2);

				portNm = null;

				for (String s2 : list2) {

					if (s2.startsWith("=====") || s2.startsWith("----")) {
						portNm = null;
						continue;
					}

					ss = s2.split(" +");
					if (ss.length == 1) {
						portNm = ss[0];
					}

					if (portNm != null && ss.length == 5) {
						o = new OCL28102();
						o.setEquipId(opn3000.getEquipId());
						o.setScardNm(slotNm);
						o.setScardTypNm(prvUnit);
						o.setPortDesc(portNm);
						o.setClctPrfmNm(ss[0]);
						o.setCurrMin15PrfmVal(ConfAdapter.getLong(ss[1]));
						o.setBfMin15PrfmVal(ConfAdapter.getLong(ss[2]));
						o.setCurrD1PrfmVal(ConfAdapter.getLong(ss[3]));
						o.setBfD1PrfmVal(ConfAdapter.getLong(ss[4]));

						opn3000.getOcl28102List().add(o);
					}
				}

			}
		}
		// <응답 TYPE 1>
		// WNO3000_송파COT01 2019-08-30,17:47:29
		// M 11505770 COMPLD
		// /*
		// SLOT:PRV-UNIT,EQ-UNIT,EQ-STATE,ADMIN-STATE,INIT-OK,BP-STATE,IPC-STATE,FAIL-STATE,HW-VER,SW-VER,FPGA-VER,FPGA-DATE
		// */
		// "OMA:OMU,OMU,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.0.1,2019-5-16"
		// "OMB:OMU,OMU,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.0.1,2019-5-16"
		// "S01:OGE40U,OGE40U,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.1.0,2019-6-18"
		// "S02:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S03:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S04:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S05:OMX24U,OMX24U,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.2.0,2019-7-30"
		// "OXCA:OXCU,OXCU,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.1.0,1.1.4,1.1.0,2019-6-7"
		// "OXCB:OXCU,OXCU,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.1.0,1.1.4,1.1.0,2019-6-7"
		// "S06:OMX24U,OMX24U,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.2.0,2019-7-30"
		// "S07:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S08:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S09:----,----,----,----,FALSE,----,----,----,----,----,----,----"
		// "S10:OGE40U,OGE40U,IN,ACT,TRUE,PRV_COMPLETE,OK,OK,1.0.0,1.1.4,1.1.0,2019-6-18"
		// ;

		//
		// WNO3000_송파COT01 2019-08-30,17:47:44
		// M 11506018 COMPLD
		// PERFORMANCE MONITORING REPORT
		// =============================================================================
		// PORT 15 MINUTE 1 DAY
		// ADDRESS CURRENT PREVIOUS CURRENT PREVIOUS
		// -----------------------------------------------------------------------------
		// WNO3000_송파COT01-OPN3000-S01-P1
		// IN-ERROR-PACKETS 0 0 0 0
		// ES 0 0 0 0
		// SES 0 0 0 0
		// UAS 163 900 6626 0
		// IN-DISCARD-PACKETS 0 0 0 0
		// OUT-ERROR-PACKETS 0 0 0 0
		// OUT-DISCARD-PACKETS 0 0 0 0
		// -----------------------------------------------------------------------------
		//
		// ;

		// <응답 TYPE 2>
		// COT : SLOT INFO
		// =======+========+========+======+=======+=========+===================+======+=======+========+========+========+============
		// SLOT | P-UNIT | E-UNIT | EQT | ADMIN | INIT-OK | BP-STATE | IPC |
		// FAIL | HW-VER | SW-VER | FPGAVER| FPGA-DATE
		// -------+--------+--------+------+-------+---------+-------------------+------+-------+--------+--------+--------+------------
		// OMA | OMU | OMU | IN | ACT | TRUE | PRV_COMPLETE | OK | OK | 1.0.0 |
		// 1.1.5 | 1.0.1 | 2019-5-16
		// OMB | OMU | OMU | IN | ACT | TRUE | PRV_COMPLETE | OK | OK | 1.0.0 |
		// 1.1.5 | 1.0.1 | 2019-5-16
		// S01 | OGE40U | OGE40U | IN | ACT | TRUE | PRV_COMPLETE | OK | OK |
		// 1.0.0 | 1.1.5 | 1.1.0 | 2019-6-18
		// S02 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// S03 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// S04 | OMX24U | OMX24U | IN | ACT | TRUE | PRV_COMPLETE | OK | OK |
		// 1.0.0 | 1.1.5 | 1.2.0 | 2019-7-30
		// S05 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// OXCA | OXCU | OXCU | IN | ACT | TRUE | PRV_COMPLETE | OK | OK | 1.1.0
		// | 1.1.5 | 1.2.0 | 2019-10-17
		// OXCB | OXCU | OXCU | IN | ACT | TRUE | PRV_COMPLETE | OK | OK | 1.1.0
		// | 1.1.5 | 1.2.0 | 2019-10-17
		// S06 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// S07 | OMX24U | OMX24U | IN | ACT | TRUE | PRV_COMPLETE | OK | OK |
		// 1.0.0 | 1.1.5 | 1.2.0 | 2019-7-30
		// S08 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// S09 | ---- | ---- | ---- | ---- | FALSE | ---- | ---- | ---- | ---- |
		// ---- | ---- | ----
		// S10 | OGE40U | OGE40U | IN | ACT | TRUE | PRV_COMPLETE | OK | OK |
		// 1.0.0 | 1.1.5 | 1.1.0 | 2019-6-18
		// -------+--------+--------+------+-------+---------+-------------------+------+-------+--------+--------+--------+------------
		// ;

		// (OPN3000-OMA-WNO3000_▒▒▒T01)#rtrv-pm-ethernet::S04;
		// WNO3000_▒▒▒T01 2019-11-11,16:30:45
		// M 14831017 COMPLD
		// PERFORMANCE MONITORING REPORT
		// =============================================================================
		// PORT 15 MINUTE 1 DAY
		// ADDRESS CURRENT PREVIOUS CURRENT PREVIOUS
		// -----------------------------------------------------------------------------
		// WNO3000_▒▒▒T01-OPN3000-S04-P1
		// IN-ERROR-PACKETS 0 0 0 0
		// ES 0 0 0 0
		// SES 0 0 0 0
		// UAS 0 0 0 0
		// IN-DISCARD-PACKETS 0 0 0 0
		// OUT-ERROR-PACKETS 0 0 0 0
		// OUT-DISCARD-PACKETS 0 0 0 0
		// -----------------------------------------------------------------------------

	}

	private void makeOCL28011(String clctItemClNm, String result, Opn3000Vo opn3000) throws Exception {

		OCL28011 ret = new OCL28011();

		ret.setEquipId(opn3000.getEquipId());
		ret.setClctItmClNm(clctItemClNm);

		String ss[];
		List<String> list = getContents(result);
		for (String line : list) {
			ss = split(line);
			if (ss.length == 4) {
				try {
					Integer.parseInt(ss[0]);
					if ("OK".equals(ss[1])) {
						ret.setNormCnt(ret.getNormCnt() + 1);
					} else {
						ret.setAbnCnt(ret.getAbnCnt() + 1);
					}
				} catch (Exception e) {
				}
			}
		}

		opn3000.getOcl28011List().add(ret);
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

}
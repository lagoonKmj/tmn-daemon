package com.lagoon.tmn.teams.co;

import fxms.bas.fxo.FxCfg;

public class AdamsCfg extends FxCfg {

	public static String ADAMSDB = "ADAMS"; // "ADAMSDEV, ADAMS", MIDBDEV
	public static String ADAMSDEV = "ADAMSDEV"; // "ADAMSDEV, ADAMS", MIDBDEV

	/* ****************************************************************************************** */
	/* 성능코드 */
	/* ****************************************************************************************** */
	/** 광레벨최소값 */
	public static final String PSCODE_OPT_LEVEL_MIN = "OMIN";
	/** 광레벨최대값 */
	public static final String PSCODE_OPT_LEVEL_MAX = "OMAX";
	/** 광레벨평균값 */
	public static final String PSCODE_OPT_LEVEL_AVG = "OAVG";
	/** 광레벨현재값 */
	public static final String PSCODE_OPT_LEVEL_CURR = "OCURR";
	/** 모듈온도값 */
	public static final String PSCODE_TEMPERRATURE = "TEMP";

	public class TeamsAlarmCode {
		/** 미 정의 장애 */
		public static final String NOT_DEF_ALARM = "1030003";
		/** 링 고립 (TODO 밸류값은 수정 필요함) */
		public static final String NET_DABL_CD_RING_ISOLATION = "035006";
		/** 링 오픈 (TODO 밸류값은 수정 필요함) */
		public static final String NET_DABL_CD_RING_OPEN = "035001";
		/** 트렁크 알람 (TODO 밸류값은 수정 필요함) */
		public static final String NET_DABL_CD_TRUNK_ALARM = "035010";
		/** 전송망 장비 장애 */
		public static final String DABL_CD_EQUIP_ALARM = "012001";
		/** 전송망 회선 장애 */
		public static final String DABL_CD_LINE_ALARM = "042001";
		/** 전송망 링 장애 */
		public static final String DABL_CD_RING_ALARM = "042003";
	}

	/**
	 * 전송망토폴로지유형코드
	 * 
	 * <pre>
	 * select * from OCO20101 where comm_cd = 'TRMS_NET_TOPO_TYP_CD';
	 * </pre>
	 * 
	 * @author lagoon
	 */
	public class TrmsNetTopoTypCd {

		public static final String NONE = "0000";
		public static final String RING = "0001";
		public static final String PTP = "0002";
		public static final String PTMP = "0003";
		public static final String LINEAR = "0004";
		public static final String FREE = "0005";
		public static final String ADM = "0006";
		public static final String CWDM = "0007";
		public static final String DWDM = "0008";
		public static final String PTS_RING = "0009";
		public static final String RINGMUX_RING = "0010";
		public static final String IBS = "0011";
		public static final String IBN_L3SW = "0012";
		public static final String WDM_Ring = "0013";
		public static final String PTP_ROADM = "0014";
		public static final String IBRR = "0015";
		public static final String MESH = "0020";
		public static final String TRUNK = "0100";
		public static final String WDM_TRUNK = "0101";
		public static final String TUNNEL = "0110";
		public static final String PW = "0111";
		public static final String EVC = "0112";

	}

	/**
	 * 회선상태코드
	 * 
	 * <pre>
	 * select * from OCO20101 where comm_cd = 'LINE_ST_CD';
	 * 알수없음	00
	 * 예비	01
	 * 운용	02
	 * 대기	03
	 * 원부접수	04
	 * 일시이용중단	05
	 * 예약가입	06
	 * 서비스중단	07
	 * 해지	08
	 * 계약휴지	09
	 * 서비스제공휴지	10
	 * 번호이동휴지	11
	 * 미개통	12
	 * 개통중	13
	 * 설변	14
	 * 사용중	15
	 * 직권해지	16
	 * 직권해지신청	17
	 * 정지	18
	 * CIS PB상태자료이전	19
	 * </pre>
	 * 
	 * @author lagoon
	 */
	public class LineStCd {

		/** 해지 */
		public static final String CLOSE = "08";
		/** 미개통 */
		public static final String NOT_OPEN = "12";
		/** 개통중 */
		public static final String OPENING = "13";

	}

	public class TeamsCode {
		/** 시스템 아이디 */
		public static final String AUDIT_ID = "ADAMSTMN";
		/** LOS */
		public static final String REASON_LOS = "LOS";
		/** 망 구분(링) */
		public static final String NET_TYPE_RING = "RING";
		/** 망 구분(트렁크) */
		public static final String NET_TYPE_TRUNK = "TRUNK";
	}

	/**
	 * 장애 등급 코드
	 * 
	 * @author lagoon
	 */
	public enum DABL_GR_CD {
		Clear("00"), Info("01"), Warning("02"), Minor("03"), Major("04"), Critical(
				"05"), Fatal("07");

		private String code;

		private DABL_GR_CD(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	public enum CLCT_MTHD_CL_CD {
		NONE("00"), EMS("01"), NE("02"), NE_EMS("03");

		private String code;

		private CLCT_MTHD_CL_CD(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}
	}

	public enum TRMS_NET_EQUIP_CAPA_CL_CD {
		E1("0001"), STM1("0004"), _10G("0032"), _1G("0053"), _1GE("0018"), Unknown(
				"0000"), _100G("0142"), _100m("0048")

		;

		private String code;

		private TRMS_NET_EQUIP_CAPA_CL_CD(String code) {
			this.code = code;
		}

		public String getCode() {
			return code;
		}

	}

	public enum EQUIP_ATTR_CHG_CL_CD {

		/** 일괄변경 */
		B0

		/** 구성변경 */
		, C0

		/** SWING변경 */
		, S0

		/** 운용자변경 */
		, U0

		;
	}

	/**
	 * TABLE OIV10901 내용
	 * 
	 * @author subkjh(김종훈)
	 *
	 */
	public enum EQUIP_ATTR_CD {

		A12("TPO_CD"), A13("MGMT_TPO_CD"), A14("EQUIP_TID_VAL"), A15("DEL_YN"), B01(
				"SNMP_VER_NUM"), A01("M_OBJ_YN"), A02("EQUIP_IP_ADDR"), A03(
				"SW_VER_INFO"), A04("MEMO"), A05("EQUIP_SET_LOC_DESC"), A07(
				"CHRGR_NM"), A08("CHRGR_CNTC_NUM"), A09("SUB_CHRGR_NM"), A10(
				"SUB_CHRGR_CNTC_NUM");

		private String col;

		private EQUIP_ATTR_CD(String col) {
			this.col = col;
		}

		public static EQUIP_ATTR_CD getCd(String col) {
			for (EQUIP_ATTR_CD e : EQUIP_ATTR_CD.values()) {
				if (e.col.equals(col)) {
					return e;
				}
			}
			return null;
		}
	}

	public class CAPACITY_CD {
		public static final String CAPACITY_CD_COM_10G = "047";
		public static final String CAPACITY_CD_COM_1G = "068";
		public static final String CAPACITY_CD_COM_25G = "046";
		public static final String CAPACITY_CD_COM_622M = "067";
		public static final String CAPACITY_CD_COM_1_25G = "022";
		public static final String CAPACITY_CD_COM_155M = "062";
		public static final String CAPACITY_CD_COM_45M = "061";
		public static final String CAPACITY_CD_COM_E1 = "001";
		public static final String CAPACITY_CD_COM_T1 = "002";
		public static final String CAPACITY_CD_COM_DWDM = "100";
		public static final String CAPACITY_CD_COM_10GE = "101";
		public static final String CAPACITY_CD_COM_100G = "158";
		public static final String CAPACITY_CD_COM_200G = "166";
		public static final String CAPACITY_CD_NONE = "000";

	}

	public class CONN_ST_VAL {
		public static final String CONNECTED = "1";
		public static final String DISCONNECTED = "0";
		public static final String UNKNOWN = "2";
	}

	public class PORT_TYP_CD {
		public static final String unknown = "0";
		public static final String other = "1";
		public static final String gigabitEthernet = "117";
		public static final String hdlc = "118";
		public static final String docsCableMaclayer = "127";
		public static final String docsCableDownstream = "128";
		public static final String docsCableUpstream = "129";
		public static final String tunnel = "131";
		public static final String atmSubInterface = "134";
		public static final String l2vlan = "135";
		public static final String l3ipvlan = "136";
		public static final String l3ipxvlan = "137";
		public static final String ipForward = "142";
		public static final String mplsTunnel = "150";
		public static final String ieee8023adLag = "161";
		public static final String mpls = "166";
		public static final String pos = "171";
		public static final String ds1 = "18";
		public static final String e1 = "19";
		public static final String regular1822 = "2";
		public static final String propPointToPointSerial = "22";
		public static final String ppp = "23";
		public static final String MACSecControlled = "231";
		public static final String MACSecUncontrolled = "232";
		public static final String softwareLoopback = "24";
		public static final String frameRelay = "32";
		public static final String atm = "37";
		public static final String sonet = "39";
		public static final String aal5g = "49";
		public static final String sonetPath = "50";
		public static final String propVirtual = "53";
		public static final String ethernetCsmacd = "6";
		public static final String fastEther = "62";
		public static final String fastEtherFX = "69";
		public static final String ds0Bundle = "82";
	}

	public class CONS_ITM_TYP_CD {
		/** 07 랙구성품 */
		public static final String RACK = "07";
		/** 08 Shelf구성품 */
		public static final String SHELF = "08";
		/** 09 Card류 구성품 */
		public static final String CARD = "09";
		/** 10 시스템구성품 */
		public static final String SYSTEM = "10";
		/** 93 장비 기타구성품 */
		public static final String ETC = "93";
	}

	public enum EQUIP_CONS_ITM_TYP_CD {
		Other(1), Port(10), Stack(11), Unknown(2), Chassis(3), Backplane(4), Container(
				5), PowerSupply(6), Fan(7), Sensor(8), Module(9);

		private int code;

		private EQUIP_CONS_ITM_TYP_CD(int code) {
			this.code = code;
		}

		public int getCode() {
			return code;
		}
	}

	public class CF_NE_MODEL_CD {
		public static final String CF_NE_MODEL_TFOM_FM700 = "218";
		public static final String CF_NE_MODEL_TFOM_S5500 = "219";
		public static final String CF_NE_MODEL_TFOM_S520 = "220";
		public static final String CF_NE_MODEL_TFOM_S530 = "221";
		public static final String CF_NE_MODEL_TFOM_S540A = "222";
		public static final String CF_NE_MODEL_TFOM_S540 = "222";
		public static final String CF_NE_MODEL_TFOM_S550 = "223";
		public static final String CF_NE_MODEL_TFOM_S541 = "241";
		public static final String CF_NE_MODEL_TFOM_C60 = "225";
		public static final String CF_NE_MODEL_TFOM_S560 = "248";
		public static final String CF_NE_MODEL_TFOM_S570 = "249";
	}

	public class TF_PORT_TYPE {
		public static final String OTSU = "TFOM_PORT";

	}

	/**
	 * 데이터등록방식구분코드
	 * 
	 */
	public class DATA_RGST_MTHD_CL_CD {
		public static final String USER = "01"; // 운용자	
		public static final String SYSTEM = "10"; // 시스템	
		public static final String USER_AND_SYSTEM = "11"; // 시스템 + 운용자 
	}
	
}

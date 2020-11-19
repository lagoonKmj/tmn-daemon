package com.lagoon.tmn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.co.utils.FxmsClient;
import fxms.bas.fxo.FxCfg;

public class TestAlarmMaker {

	public static void main(String[] args) throws Exception {

		Logger.logger.setLevel(LOG_LEVEL.info);
		if (args.length != 1) {
			Logger.logger.error("1 : 장애생성(망 기준 검색), 2 : 장애생성(회선 기준 검색), 3. 검증");
			System.exit(0);
		}

		TestAlarmMaker maker = new TestAlarmMaker();

		switch (args[0]) {
		case "1":
			maker.make("SELECT_TEST___GET_NET");
			break;
		case "2":
			maker.make("SELECT_TEST___GET_LINE");
			break;
		case "3":
			maker.check();
			break;

		}
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql("test_tmn.xml"));
	}

	@SuppressWarnings("unchecked")
	private void make(String qid) throws Exception {

		List<BaseData> baseDataList = new ArrayList<TestAlarmMaker.BaseData>();

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			// <select id="SELECT_TEST___GET_NET" resultMap="RESULT_MAP">
			// <select id="SELECT_TEST___GET_LINE" resultMap="RESULT_MAP">
			// <select id="SELECT_TEST___GET_REASON" resultMap="RESULT_MAP">

			List<Map<String, Object>> qryList = tran.selectQid2Res(qid, null);

			for (Map<String, Object> map : qryList) {
				BaseData baseData = new BaseData();
				baseData.setEquipId(map.get("EQUIP_ID") + "");
				baseData.setEquipAPortInfo(map.get("EQUIP_A_PORT_INFO"));
				baseData.setEquipBPortInfo(map.get("EQUIP_B_PORT_INFO"));
				baseDataList.add(baseData);
			}

			Map<String, Object> para = new HashMap<String, Object>();
			// 리즌 값 가져오기(장비ID 로)
			for (BaseData baseData : baseDataList) {

				para.put("equipId", baseData.getEquipId());
				List<Map<String, Object>> reasonList = tran.selectQid2Res(
						"SELECT_TEST___GET_REASON", para);

				for (Map<String, Object> reasonMap : reasonList) {
					if (reasonMap.get("CMPR_CHAR_STR_VAL") != null) {
						baseData.setReason(reasonMap.get("CMPR_CHAR_STR_VAL")+ "");
						break; // 하나만 캐치하고 ㅂㅂ
					}
				}
			}

			for (BaseData baseData : baseDataList) {

				if (baseData.getReason() == null) {
					continue;
				}

				TrmsNetEventVo trmsNetEventVo = new TrmsNetEventVo();
				trmsNetEventVo.setEquipId(baseData.getEquipId());

				if (baseData.getEquipAPortInfo() != null) {
					trmsNetEventVo.setPortDescr(baseData.getEquipAPortInfo() + "");
				} else if (baseData.getEquipBPortInfo() != null) {
					trmsNetEventVo.setPortDescr(baseData.getEquipBPortInfo() + "");
				} else {
					continue;
				}
				trmsNetEventVo.setReason(baseData.getReason());
				trmsNetEventVo.setLocation("LAGOON-LOC");
				trmsNetEventVo.setFullMsg("by.lagoon");
				trmsNetEventVo.setRecvTime(System.currentTimeMillis());
				trmsNetEventVo.setOccurTime(System.currentTimeMillis());
				Map<String, Object> para__2 = new HashMap<String, Object>();
				para__2.put("event", trmsNetEventVo);

				FxmsClient client = new FxmsClient(FxCfg.getCfg().getString(
						"APP_SERVER", "localhost"), 10005);
				client.login("AdamsService", "AdamsService");

				client.testRetObj("teams", "fire-event", para__2);

				Thread.sleep(100L);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tran.stop();
		}

	}

	@SuppressWarnings("unchecked")
	private void check() throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

//			<select id="SELECT_TEST___GET_ALARM" resultMap="RESULT_MAP">
//			<![CDATA[
//			select a.trms_equip_dabl_num, a.equip_id, a.port_desc, a.cmpr_char_str_val,
//			(select count(1) from OMN33830 b where a.trms_equip_dabl_num = b.trms_equip_dabl_num) as net_alarm_count,
//			(select count(1) from OMN33820 b where a.trms_equip_dabl_num = b.trms_equip_dabl_num) as line_alarm_count 
//			from OMN33810 a
//			]]>
//			</select>

			List<Map<String, Object>> alarmList = tran.selectQid2Res(
					"SELECT_TEST___GET_ALARM", null);
			for (Map<String, Object> alarmMap : alarmList) {
				// <select id="SELECT_TEST___GET_NET_ALARM"
				// resultMap="RESULT_MAP">
				// <![CDATA[
				// select b.* FROM oiv10615 a INNER JOIN oiv10614 b ON a.net_num
				// = b.net_num AND b.trms_net_mgmt_ownr_cd = '03' and
				// trms_net_topo_typ_cd != '0100'
				// and equip_id = $equipId and (equip_a_port_info = $portDesc or
				// equip_b_port_info = $portDesc)
				// ]]>
				// </select>
				String trms_equip_dabl_num = alarmMap.get("TRMS_EQUIP_DABL_NUM") + "";
				String equip_id = alarmMap.get("EQUIP_ID") + "";
				String port_desc = alarmMap.get("PORT_DESC") + "";
				String cmpr_char_str_val = alarmMap.get("CMPR_CHAR_STR_VAL") + "";
				
				Map<String, Object> para = new HashMap<String, Object>();
				para.put("equipId", equip_id);
				para.put("portDesc", port_desc);
				
//				Logger.logger.info("LAGOON LOG : TRMS_EQUIP_DABL_NUM : {}, EQUIP_ID : {}, PORT_DESC : {}, REASON : {}", trms_equip_dabl_num, equip_id, port_desc, cmpr_char_str_val);
				
				List<Map<String, Object>> netAlarmList = tran.selectQid2Res(
						"SELECT_TEST___GET_NET_ALARM", para);
				if (Math.round(Double.parseDouble((alarmMap.get("NET_ALARM_COUNT") + ""))) != netAlarmList
						.size()) {
					Logger.logger.info("LAGOON LOG : TRMS_EQUIP_DABL_NUM : {}, EQUIP_ID : {}, PORT_DESC : {}, REASON : {}", trms_equip_dabl_num, equip_id, port_desc, cmpr_char_str_val);
					Logger.logger.info("		ㄴ LAGOON LOG NET FAIL: NET_ALARM_COUNT : {}, QUERY_COUNT : {}", Math.round(Double.parseDouble(alarmMap.get("NET_ALARM_COUNT") + "")), netAlarmList.size());
				} else { // 망 기준으로 회선 검증
					
					
//					<select id="SELECT_TEST___GET_LINE_ALARM__BY_NET_NUMS" resultMap="RESULT_MAP">
//					<![CDATA[
//					select distinct b.line_num FROM oiv10612 a INNER JOIN oiv10611 b ON a.line_num = b.line_num and b.line_st_cd not in ('08', '12', '13') AND b.trms_net_mgmt_ownr_cd = '03' 
//					and a.ring_num in (#ringNums) and b.co_line_vrf_yn = 'Y'
//					]]>
//					</select>
					
					String ringNums = "";
					for (Map<String, Object> map__inner : netAlarmList) {
						
						 String netNum = map__inner.get("NET_NUM") + "";

						 ringNums += netNum + ","; 
					}
					if (ringNums.length() == 0) {
						continue;
					}
					ringNums = ringNums.substring(0, ringNums.length() - 1);
					
					Map<String, Object> para__inner = new HashMap<String, Object>();
					para__inner.put("ringNums", ringNums);
					
					List<Map<String, Object>> list__inner = tran.selectQid2Res(
							"SELECT_TEST___GET_LINE_ALARM__BY_NET_NUMS", para__inner);
					
					if (Math.round(Double.parseDouble((alarmMap.get("LINE_ALARM_COUNT") + ""))) != list__inner.size()) {
						
//						<select id="SELECT_TEST___GET_LINE_ALARM" resultMap="RESULT_MAP">
//						<![CDATA[
//						select distinct b.line_num FROM oiv10612 a INNER JOIN oiv10611 b ON a.line_num = b.line_num and b.line_st_cd not in ('08', '12', '13') AND b.trms_net_mgmt_ownr_cd = '03' 
//						and b.co_line_vrf_yn = 'Y' and a.equip_id = $equipId and (equip_a_port_info = $portDesc or equip_b_port_info = $portDesc);
//						]]>
//						</select>
						
						// 네트워크 정보로 데이터 못 찾음(회선다이렉트 검색)
						if (list__inner.size() == 0) {
							
							para__inner = new HashMap<String, Object>();
							para__inner.put("equipId", equip_id);
							para__inner.put("portDesc", port_desc);
							
							String qid = "SELECT_TEST___GET_LINE_ALARM";
							if (cmpr_char_str_val.toUpperCase().equals(TeamsCode.REASON_LOS)) {
								qid = "SELECT_TEST___GET_LINE_ALARM__LIKE";
							}
							
							List<Map<String, Object>> list__inner__2 = tran.selectQid2Res(qid, para__inner);
							
							if (Math.round(Double.parseDouble((alarmMap.get("LINE_ALARM_COUNT") + ""))) != list__inner__2.size()) {
								Logger.logger.info("LAGOON LOG : TRMS_EQUIP_DABL_NUM : {}, EQUIP_ID : {}, PORT_DESC : {}, REASON : {}", trms_equip_dabl_num, equip_id, port_desc, cmpr_char_str_val);
								Logger.logger.info("		ㄴ LAGOON LOG LINE FAIL BY 다이렉트: LINE_ALARM_COUNT : {}, QUERY_COUNT : {}", Math.round(Double.parseDouble(alarmMap.get("LINE_ALARM_COUNT") + "")), list__inner__2.size());
							}
							
						} else {
							Logger.logger.info("LAGOON LOG : TRMS_EQUIP_DABL_NUM : {}, EQUIP_ID : {}, PORT_DESC : {}, REASON : {}", trms_equip_dabl_num, equip_id, port_desc, cmpr_char_str_val);
							Logger.logger.info("		ㄴ LAGOON LOG LINE FAIL BY 링아이디: LINE_ALARM_COUNT : {}, QUERY_COUNT : {}", Math.round(Double.parseDouble(alarmMap.get("LINE_ALARM_COUNT") + "")), list__inner.size());							
						}
						
					}
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			tran.stop();
		}

	}
	
	class BaseData {
		String equipId;
		Object equipAPortInfo;
		Object equipBPortInfo;
		String reason;

		public String getEquipId() {
			return equipId;
		}

		public void setEquipId(String equipId) {
			this.equipId = equipId;
		}

		public Object getEquipAPortInfo() {
			return equipAPortInfo;
		}

		public void setEquipAPortInfo(Object equipAPortInfo) {
			this.equipAPortInfo = equipAPortInfo;
		}

		public Object getEquipBPortInfo() {
			return equipBPortInfo;
		}

		public void setEquipBPortInfo(Object equipBPortInfo) {
			this.equipBPortInfo = equipBPortInfo;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		@Override
		public String toString() {
			return "BaseData [equipId=" + equipId + ", equipAPortInfo="
					+ equipAPortInfo + ", equipBPortInfo=" + equipBPortInfo
					+ ", reason=" + reason + "]";
		}

	}

}
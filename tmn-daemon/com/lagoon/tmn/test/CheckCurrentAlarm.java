package com.lagoon.tmn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.dao.AdamsDcnDao;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.Ciena;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaService;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapter5150;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapter5410;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapterQopas;
import com.lagoon.tmn.teams.gw.vendor.coweaver.Coweaver;
import com.lagoon.tmn.teams.gw.vendor.coweaver.CoweaverService;
import com.lagoon.tmn.teams.gw.vendor.coweaver.adapter.CoweaverConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.nokia.Nokia;
import com.lagoon.tmn.teams.gw.vendor.nokia.NokiaService;
import com.lagoon.tmn.teams.gw.vendor.nokia.adapter.NokiaConfAdapter7210SAS;
import com.lagoon.tmn.teams.gw.vendor.telefield.Telefield;
import com.lagoon.tmn.teams.gw.vendor.telefield.TelefieldService;
import com.lagoon.tmn.teams.gw.vendor.telefield.adapter.TelefieldConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.woorinet.Woorinet;
import com.lagoon.tmn.teams.gw.vendor.woorinet.WoorinetService;
import com.lagoon.tmn.teams.gw.vendor.woorinet.adapter.WoorinetConfAdapter;

import subkjh.bas.co.log.Logger;

public class CheckCurrentAlarm {

	public static void main(String[] args) throws Exception {
		
//		Logger.logger.setLevel(LOG_LEVEL.info);
//		GwApi.getApi().loadSyslogPattern(); 
		CheckCurrentAlarm alarm = new CheckCurrentAlarm();
		alarm.getVendor();
		alarm.getDcn();
	}

	private Map<String, Map<String, Object>> vendorMap = new HashMap<String, Map<String,Object>>(); 
	public void getVendor() throws Exception {
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("emsTypVal", "'" + WoorinetService.WOORINET_PTN_EMS  + "'");
		parameters.put("equipMdlCd", WoorinetService.MODEL_OPN_3000 );
		parameters.put("equipMfactCd", WoorinetService.VENDOR_WOORINET );
		parameters.put("clctSvrIpAddr", "12.4.110.96" );
		vendorMap.put("우리넷", parameters);
		
		parameters = new HashMap<String, Object>();
		parameters.put("emsTypVal", "'" + TelefieldService.TFOM_C60_EMS  + "'" + ",'" + TelefieldService.TFOM_EMS  + "'");
		parameters.put("equipMdlCd", TelefieldService.MODEL_S570 );
		parameters.put("equipMfactCd", TelefieldService.VENDOR_TELEFIELD );
		parameters.put("clctSvrIpAddr", "12.4.110.96" );
		vendorMap.put("텔레필드", parameters);
		
		parameters = new HashMap<String, Object>();
		parameters.put("emsTypVal", "'" + NokiaService.MODEL_7210SAS_T   + "'");
		parameters.put("equipMfactCd", NokiaService.VENDOR_NOKIA);
		parameters.put("clctSvrIpAddr", "12.4.110.96" );
		vendorMap.put("노키아", parameters);

		parameters = new HashMap<String, Object>();
		parameters.put("equipMdlCd", "'" + CoweaverService.MODEL_UTRANS_7200  + "'" + ",'" + CoweaverService.MODEL_UTRANS_7300 + "'" + ",'" + CoweaverService.MODEL_UTRANS_7400  + "'"+ ",'" + CoweaverService.MODEL_UTRANS_7400C + "'");
		parameters.put("equipMfactCd", CoweaverService.VENDOR_COWEAVER);
		parameters.put("clctSvrIpAddr", "12.4.110.96" );
		vendorMap.put("코위버", parameters);

		parameters = new HashMap<String, Object>();
		parameters.put("equipMdlCd", "'" + CienaService.MODEL_5410  + "'" + ",'" + CienaService.MODEL_CESD_5150  + "'" + ",'" + CienaService.MODEL_QOPAS+ "'");
		parameters.put("equipMdlCd", "'" + CienaService.MODEL_5410  + "'" + ",'" + CienaService.MODEL_CESD_5150  + "'");
//		parameters.put("equipMdlCd", "'" + CienaService.MODEL_QOPAS  + "'");
		parameters.put("equipMfactCd", CienaService.VENDOR_CIENA);
		parameters.put("clctSvrIpAddr", "12.4.110.96" );
		vendorMap.put("시에나", parameters);
		
	}

	public void getDcn()  {
		
		AdamsDcnDao dao = new AdamsDcnDao();

		for (String key : vendorMap.keySet()) {
			List<IDcn> ret = new ArrayList<IDcn>();
			Map<String, Object> parameters = vendorMap.get(key);
			List<EmsDcn> list;
			try {
				
				if (parameters.containsKey("emsTypVal")) {
					list = dao.selectEmsDcnList(parameters);
					if (list != null) {
						ret.addAll(list);
					}
				} else {
					List<EquipDcn> list2 = dao.selectEquipDcnList(parameters);
					if (list2 != null) {
						for (EquipDcn dcn : list2) {
							if (dcn.getEquipIpAddr() != null) {
								ret.add(dcn);
							}
						}
					}
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			Logger.logger.info("라군 테스트__ 벤더 : {}, DCN 갯수 : {}", key, ret.size());
			int sum = 0;
			for (IDcn dcn : ret) {
				try {
					int cnt = getCurrentAlarm(dcn);
					sum += cnt;
					parameters.put("alarm_count", sum); 
				} catch (Exception e) {
					continue;
				}	
			}
		}
		
		for (String key : vendorMap.keySet()) {
			Map<String, Object> parameters = vendorMap.get(key);
			Logger.logger.info("벤더 : {}, 장애수 : {}", key, parameters.get("alarm_count"));
		}
	}
	
	public int getCurrentAlarm(IDcn dcn) {
		
		ConfAdapter adapter = null;
		List<DetectedAlarmVo> alarms = null;
		if (dcn instanceof EquipDcn) {
			EquipDcn equip = (EquipDcn) dcn;
			
			switch (equip.getEquipMdlCd()) {
			case NokiaService.MODEL_7210SAS_T:
				adapter= new NokiaConfAdapter7210SAS(equip, new Nokia());
				break;
			case CoweaverService.MODEL_UTRANS_7200:
			case CoweaverService.MODEL_UTRANS_7300:
			case CoweaverService.MODEL_UTRANS_7400:
			case CoweaverService.MODEL_UTRANS_7400C:
				adapter= new CoweaverConfAdapter(equip, new Coweaver());	
				break;
			case CienaService.MODEL_QOPAS:
				adapter= new CienaConfAdapterQopas(equip, new Ciena());
				break;
			case CienaService.MODEL_5410:
				adapter= new CienaConfAdapter5410(equip, new Ciena());
				break;
			case CienaService.MODEL_CESD_5150:
				adapter= new CienaConfAdapter5150(equip, new Ciena());
				break;
			}
			
		} else if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			
			switch (ems.getEmsTypVal()) {
			case WoorinetService.WOORINET_PTN_EMS:
				adapter= new WoorinetConfAdapter(ems, new Woorinet());
				break;
			case TelefieldService.TFOM_C60_EMS:
			case TelefieldService.TFOM_EMS:
				adapter = new TelefieldConfAdapter(ems, new Telefield());
				break;
			}
		}
//		alarms  = adapter.collectAlarm();
		try {
			Logger.logger.debug("접속 시도 KEY : {}, IP : {}", dcn.getDcnKey(), dcn.getIpAddress());
			alarms  = adapter.collectAlarm();
		} catch (Exception e) {
			Logger.logger.error("   ㄴ 접속 오류");
		}
		Logger.logger.info("라군 테스트__    ㄴ 장애 수 : {}" , alarms.size());
		return alarms.size();
	}
	
}

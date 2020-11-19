package com.lagoon.tmn.teams.gw.vendor.ciena.trap;

import subkjh.bas.co.log.Logger;

import com.lagoon.tmn.teams.co.AdamsCfg.DABL_GR_CD;
import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;
import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.cienaCesMplsMIB;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.cienaCesTrap;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.cienaCommon;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.cienaGlobalSeverity;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.wwpLeosCfmMIB;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.wwpLeosCfmServiceFaultType;
import com.lagoon.tmn.teams.gw.vendor.ciena.trap.CienaMIB.wwpLeosCfmServiceType;

import fxms.bas.api.FxApi;
import fxms.bas.fxo.FxActorImpl;
import fxms.nms.co.snmp.trap.TrapNode;
import fxms.nms.co.snmp.trap.adapter.TrapAdapter;
import fxms.nms.co.snmp.trap.vo.TrapVo;
import fxms.nms.co.snmp.vo.OidValue;

/**
 * CIENA 5150
 * 
 * @author subkjh(김종훈)
 *
 */
public class Ciena5150TrapAdapter extends FxActorImpl implements TrapAdapter {

	@Override
	public TrapVo parse(TrapNode node, TrapVo trap) {

		if ((node instanceof AdamsEquipVo) == false) {
			return trap;
		}

		AdamsEquipVo equip = (AdamsEquipVo) node;
		DetectedAlarmVo detectedAlarm = new DetectedAlarmVo();

		if (trap.getTrapOid().startsWith(wwpLeosCfmMIB.wwpLeosCfmNotifiMIBNotification)) {
			detectedAlarm = makeWwpLeosCfmNotifiMIBNotification(trap);
		} else if (trap.getTrapOid().startsWith(cienaCesMplsMIB.cienaCesMplsMIBNotificationPrefix)) {
			detectedAlarm = makeCienaCesMplsMIBNotificationPrefix(trap);
		} else {
			return trap;
		}

		if (detectedAlarm != null) {
			detectedAlarm.setDcn(null); // TODO 오류 발생 확인 필요
			detectedAlarm.setEquip(equip);
			detectedAlarm.setEquipIpAddr(equip.getEquipIpAddr());
			detectedAlarm.setEquipTidVal(equip.getEquipTidVal());
			if (detectedAlarm.getDablGrCd().equals(DABL_GR_CD.Clear.getCode())) {
				detectedAlarm.setClearHstime(FxApi.getDate() + "");
			} else {
				detectedAlarm.setOccurHstime(FxApi.getDate() + "");
			}
			setReason(trap, detectedAlarm);

			try {
				detectedAlarm.setIgnoreOmn33812(true);
				GwApi.getApi().fireAlarm(detectedAlarm);
			} catch (Exception e) {
				Logger.logger.error(e);
			}

			return null;
		}

		return trap;
	}

	private DetectedAlarmVo makeWwpLeosCfmNotifiMIBNotification(TrapVo trap) {
		DetectedAlarmVo ret = new DetectedAlarmVo();
		wwpLeosCfmMIB mib = new CienaMIB.wwpLeosCfmMIB();
		int val;
		StringBuffer etc = new StringBuffer();

		// make location, etc
		// location: svc_name[]/svc_vlan[], etc: fault_type[]/fault_desc[]
		// location: svc_name[]/svc_type[], etc:
		// fault_type[]/fault_desc[]/admin_state[]/oper_state[]
		// location: svc_name[]/svc_vlan[], etc:
		StringBuffer location = new StringBuffer();

		for (OidValue ov : trap.getList()) {

			if (ov.getOid().startsWith(mib.wwpLeosCfmServiceFaultType)) {
				val = ov.getInt(-1);
				wwpLeosCfmServiceFaultType e = wwpLeosCfmServiceFaultType.get(val);
				etc.append("fault_type").append(e.name()).append("]");
				if (e == wwpLeosCfmServiceFaultType.none) {
					ret.setDablGrCd(DABL_GR_CD.Clear.getCode());
				} else {
					ret.setDablGrCd(DABL_GR_CD.Major.getCode());
				}
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceName)) {
				location.append("svc_name[").append(ov.getValue()).append("]");
				ret.setLocation(ov.getValue());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceType)) {

				val = ov.getInt(-1);
				wwpLeosCfmServiceType e = wwpLeosCfmServiceType.get(val);
				location.append("svc_type[").append(e.name()).append("]");

			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceFaultDesc)) {
				etc.append("fault_desc[").append(ov.getValue()).append("]");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceVlan)) {
				location.append("svc_vlan[").append(ov.getValue()).append("]");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceAdminState)) {
				val = ov.getInt(-1);
				etc.append("admin_state[").append(val == 1 ? "disabled" : val == 2 ? "enabled" : "").append("]");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmServiceOperState)) {
				val = ov.getInt(-1);
				etc.append("oper_state[").append(val == 1 ? "disabled" : val == 2 ? "enabled" : "").append("]");
			}
		}

		ret.setLocation(location.toString());
		ret.setFullMsg(etc.toString());

		return ret;
	}

	private DetectedAlarmVo makeCienaCesMplsMIBNotificationPrefix(TrapVo trap) {
		DetectedAlarmVo ret = new DetectedAlarmVo();
		cienaCommon mib = new CienaMIB.cienaCommon();
		int val;
		for (OidValue ov : trap.getList()) {

			if (ov.getOid().startsWith(mib.cienaGlobalSeverity)) {
				val = ov.getInt(-1);
				cienaGlobalSeverity e = cienaGlobalSeverity.get(val);
				ret.setDablGrCd(e.getDablGrCd());
			}
			if (ov.getOid().startsWith(mib.cienaGlobalMacAddress)) {
				ret.setLocation(ov.getValue());
			}
		}

		// cienaCesTrap a = cienaCesTrap.getTrap(trap.getTrapOid());
		// if (a != null) {
		// ret.setReason(a.name());
		// }

		return ret;
	}

	private DetectedAlarmVo setReason(TrapVo trap, DetectedAlarmVo detectedAlarm) {

		wwpLeosCfmMIB mib = new CienaMIB.wwpLeosCfmMIB();

		for (OidValue ov : trap.getList()) {
			if (ov.getOid().startsWith(mib.wwpLeosCfmFaultTrap)) {
				detectedAlarm.setReason("Fault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmFaultTrapSet)) {
				detectedAlarm.setReason("FaultTrap");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmFaultTrapClear)) {
				detectedAlarm.setReason("FaultTrap");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFaultTrapSet)) {
				detectedAlarm.setReason("ExtFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFaultTrapClear)) {
				detectedAlarm.setReason("ExtFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtSynLossSessionNearFaultTrap)) {
				detectedAlarm.setReason("ExtSynLossSessionNearFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtSynLossSessionNearFaultClearTrap)) {
				detectedAlarm.setReason("ExtSynLossSessionNearFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtSynLossSessionFarFaultTrap)) {
				detectedAlarm.setReason("ExtSynLossSessionFarFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtSynLossSessionFarFaultClearTrap)) {
				detectedAlarm.setReason("ExtSynLossSessionFarFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionAvgDelayFaultSetTrap)) {
				detectedAlarm.setReason("ExtDelaySessionAvgDelayFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionAvgDelayFaultClearTrap)) {
				detectedAlarm.setReason("ExtDelaySessionAvgDelayFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionAvgJitterFaultSetTrap)) {
				detectedAlarm.setReason("ExtDelaySessionAvgJitterFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionAvgJitterFaultClearTrap)) {
				detectedAlarm.setReason("ExtDelaySessionAvgJitterFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionMaxDelayFaultSetTrap)) {
				detectedAlarm.setReason("ExtDelaySessionMaxDelayFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionMaxDelayFaultClearTrap)) {
				detectedAlarm.setReason("ExtDelaySessionMaxDelayFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionMaxJitterFaultSetTrap)) {
				detectedAlarm.setReason("ExtDelaySessionMaxJitterFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtDelaySessionMaxJitterFaultClearTrap)) {
				detectedAlarm.setReason("ExtDelaySessionMaxJitterFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFrameLossSessionFrameLossNearFaultSetTrap)) {
				detectedAlarm.setReason("ExtFrameLossSessionFrameLossNearFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFrameLossSessionFrameLossNearFaultClearTrap)) {
				detectedAlarm.setReason("ExtFrameLossSessionFrameLossNearFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFrameLossSessionFrameLossFarFaultSetTrap)) {
				detectedAlarm.setReason("ExtFrameLossSessionFrameLossFarFault");
			} else if (ov.getOid().startsWith(mib.wwpLeosCfmExtFrameLossSessionFrameLossFarFaultClearTrap)) {
				detectedAlarm.setReason("ExtFrameLossSessionFrameLossFarFault");
				detectedAlarm.setDablGrCd(DABL_GR_CD.Clear.getCode());
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesMplsTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("MplsTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesMplsTransitTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("MplsTransitTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesMplsAssociatedTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("MplsAssociatedTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsEncapUnidirTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsEncapUnidirTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsEncapCoroutedTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsEncapCoroutedTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsDecapCoroutedTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsDecapCoroutedTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsTransitUnidirTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsTransitUnidirTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsTransitCoroutedTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsTransitCoroutedTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsAssociatedTunnelOperStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsAssociatedTunnelOperStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsEncapCoroutedTunnelAisFaultStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsEncapCoroutedTunnelAisFaultStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsDecapCoroutedTunnelAisFaultStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsDecapCoroutedTunnelAisFaultStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsAssociatedTunnelAisFaultStateChgTrap.getOid())) {
				detectedAlarm.setReason("GmplsAssociatedTunnelAisFaultStateChg");
			} else if (ov.getOid().startsWith(cienaCesTrap.cienaCesGmplsTunnelAisFaultErrorTrap.getOid())) {
				detectedAlarm.setReason("GmplsTunnelAisFaultError");
			} else {
				detectedAlarm.setReason(ov.getOid());
			}
		}

		return detectedAlarm;
	}

}

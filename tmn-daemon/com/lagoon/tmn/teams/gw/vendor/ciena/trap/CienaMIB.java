package com.lagoon.tmn.teams.gw.vendor.ciena.trap;

import com.lagoon.tmn.teams.co.AdamsCfg;

public class CienaMIB {

	public class cienaCesMplsMIB {

		public static final String cienaCesMplsMIBNotificationPrefix = ".1.3.6.1.4.1.1271.2.2.17";
	}

	public enum cienaCesTrap {

		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesMplsNotifEncapTunnelIndex 4: cienaCesMplsNotifEncapTunnelType
		 * 5: cienaCesMplsNotifEncapTunnelName 6:
		 * cienaCesMplsNotifEncapTunnelAdminState 7:
		 * cienaCesMplsNotifEncapTunnelOperState 8:
		 * cienaCesMplsNotifEncapTunnelOamFaulted 9:
		 * cienaCesMplsNotifEncapTunnelFaultedNodeId
		 */
		cienaCesMplsTunnelOperStateChgTrap("0.1") //
		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesMplsNotifTransitTunnelIndex 4:
		 * cienaCesMplsNotifTransitTunnelType 5:
		 * cienaCesMplsNotifTransitTunnelName 6:
		 * cienaCesMplsNotifTransitTunnelAdminState 7:
		 * cienaCesMplsNotifTransitTunnelOperState 8:
		 * cienaCesMplsNotifTransitTunnelOamFaulted
		 */
		, cienaCesMplsTransitTunnelOperStateChgTrap("0.3") //
		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesMplsNotifAssociatedTunnelIndex 4:
		 * cienaCesMplsNotifAssociatedTunnelType 5:
		 * cienaCesMplsNotifAssociatedTunnelName 6:
		 * cienaCesMplsNotifAssociatedTunnelAdminState 7:
		 * cienaCesMplsNotifAssociatedTunnelOperState 8:
		 * cienaCesMplsNotifAssociatedTunnelOamFaulted 9:
		 * cienaCesMplsNotifAssociatedTunnelFaultedNodeId
		 */
		, cienaCesMplsAssociatedTunnelOperStateChgTrap("0.6") //
		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifEncapTunnelIndex 4:
		 * cienaCesGmplsNotifEncapTunnelType 5:
		 * cienaCesGmplsNotifEncapTunnelName 6:
		 * cienaCesGmplsNotifEncapTunnelAdminState 7:
		 * cienaCesGmplsNotifEncapTunnelOperState 8:
		 * cienaCesGmplsNotifEncapTunnelOamFaulted 9:
		 * cienaCesGmplsNotifEncapTunnelFaultedNodeId
		 */
		, cienaCesGmplsEncapUnidirTunnelOperStateChgTrap("1.1") //
		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifEncapTunnelIndex 4:
		 * cienaCesGmplsNotifEncapTunnelType 5:
		 * cienaCesGmplsNotifEncapTunnelName 6:
		 * cienaCesGmplsNotifEncapTunnelAdminState 7:
		 * cienaCesGmplsNotifEncapTunnelOperState 8:
		 * cienaCesGmplsNotifEncapTunnelOamFaulted 9:
		 * cienaCesGmplsNotifEncapTunnelFaultedNodeId
		 */
		, cienaCesGmplsEncapCoroutedTunnelOperStateChgTrap("1.2") //
		/**
		 * 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifDecapTunnelIndex 4:
		 * cienaCesGmplsNotifDecapTunnelType 5:
		 * cienaCesGmplsNotifDecapTunnelName 6:
		 * cienaCesGmplsNotifDecapTunnelAdminState 7:
		 * cienaCesGmplsNotifDecapTunnelOperState 8:
		 * cienaCesGmplsNotifDecapTunnelOamFaulted 9:
		 * cienaCesGmplsNotifDecapTunnelFaultedNodeId
		 */
		, cienaCesGmplsDecapCoroutedTunnelOperStateChgTrap("1.3") //
		/**
		 * reason: GmplsTransitUnidirTunnelOperStateChg, location: mac[], etc:
		 * Objects: 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifTransitTunnelIndex 4:
		 * cienaCesGmplsNotifTransitTunnelType 5:
		 * cienaCesGmplsNotifTransitTunnelName 6:
		 * cienaCesGmplsNotifTransitTunnelAdminState 7:
		 * cienaCesGmplsNotifTransitTunnelOperState 8:
		 * cienaCesGmplsNotifTransitTunnelOamFaulted
		 */
		, cienaCesGmplsTransitUnidirTunnelOperStateChgTrap("1.4")//

		/**
		 * reason: GmplsTransitCoroutedTunnelOperStateChg, location: mac[], etc:
		 * Objects: 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifTransitTunnelIndex 4:
		 * cienaCesGmplsNotifTransitTunnelType 5:
		 * cienaCesGmplsNotifTransitTunnelName 6:
		 * cienaCesGmplsNotifTransitTunnelAdminState 7:
		 * cienaCesGmplsNotifTransitTunnelOperState 8:
		 * cienaCesGmplsNotifTransitTunnelOamFaulted
		 */

		, cienaCesGmplsTransitCoroutedTunnelOperStateChgTrap("1.5") //
		/**
		 * reason: GmplsAssociatedTunnelOperStateChg, location: mac[], etc:
		 * Objects: 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifAssociatedTunnelIndex 4:
		 * cienaCesGmplsNotifAssociatedTunnelType 5:
		 * cienaCesGmplsNotifAssociatedTunnelName 6:
		 * cienaCesGmplsNotifAssociatedTunnelAdminState 7:
		 * cienaCesGmplsNotifAssociatedTunnelOperState 8:
		 * cienaCesGmplsNotifAssociatedTunnelOamFaulted 9:
		 * cienaCesGmplsNotifAssociatedTunnelFaultedNodeId
		 */
		, cienaCesGmplsAssociatedTunnelOperStateChgTrap("1.9")//

		/**
		 * reason: GmplsEncapCoroutedTunnelAisFaultStateChg, location: mac[],
		 * etc: Objects: 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress
		 * 3: cienaCesGmplsNotifEncapTunnelIndex 4:
		 * cienaCesGmplsNotifEncapTunnelType 5:
		 * cienaCesGmplsNotifEncapTunnelName 6:
		 * cienaCesGmplsNotifEncapTunnelAisFaulted 7:
		 * cienaCesGmplsNotifEncapTunnelFaultedNodeId 8:
		 * cienaCesGmplsNotifEncapTunnelFarEndLerId
		 */

		, cienaCesGmplsEncapCoroutedTunnelAisFaultStateChgTrap("1.10") //
		/**
		 * reason: GmplsDecapCoroutedTunnelAisFaultStateChg, location: mac[],
		 * etc: Objects: 1: cienaGlobalSeverity - major 2: cienaGlobalMacAddress
		 * 3: cienaCesGmplsNotifDecapTunnelIndex 4:
		 * cienaCesGmplsNotifDecapTunnelType 5:
		 * cienaCesGmplsNotifDecapTunnelName 6:
		 * cienaCesGmplsNotifDecapTunnelAisFaulted 7:
		 * cienaCesGmplsNotifDecapTunnelFaultedNodeId 8:
		 * cienaCesGmplsNotifDecapTunnelFarEndLerId
		 */
		, cienaCesGmplsDecapCoroutedTunnelAisFaultStateChgTrap("1.11")

		/**
		 * reason: GmplsAssociatedTunnelAisFaultStateChg, location: mac[], etc:
		 * Objects: 1: cienaGlobalSeverity 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifAssociatedTunnelIndex 4:
		 * cienaCesGmplsNotifAssociatedTunnelType 5:
		 * cienaCesGmplsNotifAssociatedTunnelName 6:
		 * cienaCesGmplsNotifAssociatedTunnelAisFaulted 7:
		 * cienaCesGmplsNotifAssociatedTunnelFaultedNodeId 8:
		 * cienaCesGmplsNotifAssociatedTunnelFarEndLerId
		 */

		, cienaCesGmplsAssociatedTunnelAisFaultStateChgTrap("1.12")

		/**
		 * reason: GmplsTunnelAisFaultError, location: mac[], etc: Objects: 1:
		 * cienaGlobalSeverity 2: cienaGlobalMacAddress 3:
		 * cienaCesGmplsNotifTunnelDecapLabel 4:
		 * cienaCesGmplsNotifTunnelErrorMsg
		 */
		, cienaCesGmplsTunnelAisFaultErrorTrap("1.13");

		private String oid;

		private cienaCesTrap(String oid) {
			this.oid = cienaCesMplsMIB.cienaCesMplsMIBNotificationPrefix + "."
					+ oid;
		}

		public static cienaCesTrap getTrap(String oid) {
			for (cienaCesTrap e : cienaCesTrap.values()) {
				if (oid.equals(e.getOid())) {
					return e;
				}
			}
			return null;
		}

		public String getOid() {
			return oid;
		}

	}

	public enum wwpLeosCfmServiceFaultType {

		none(1), someRDIDefect(2), someMACStatusDefect(3), someRMEPCCMDefect(4), errorCCMDefect(
				5), xconCCMDefect(6), instability(7);

		private int val;

		private wwpLeosCfmServiceFaultType(int val) {
			this.val = val;
		}

		public static wwpLeosCfmServiceFaultType get(int val) {
			for (wwpLeosCfmServiceFaultType e : wwpLeosCfmServiceFaultType
					.values()) {
				if (e.val == val) {
					return e;
				}
			}

			return null;
		}

		public int getVal() {
			return val;
		}
	}

	public static class wwpLeosCfmMIB {

		final String wwpLeosCfmMIB = ".1.3.6.1.4.1.6141.2.60.35";
		final String wwpLeosCfmMIBObjects = ".1.3.6.1.4.1.6141.2.60.35.1";
		final String wwpLeosCfmNotifiMIBNotificationPrefix = ".1.3.6.1.4.1.6141.2.60.35.2";
		public static final String wwpLeosCfmNotifiMIBNotification = ".1.3.6.1.4.1.6141.2.60.35.2.0";

		// #define CIENA_5150_EQUIP_BASE_OID ".1.3.6.1.4.1.6141.2.60.35"
		// #define CIENA_5150_EQUIP_ALARM_OID ".1.3.6.1.4.1.6141.2.60.35.2.0"
		// #define CIENA_5150_EQUIP_OBJECT_OID ".1.3.6.1.4.1.6141.2.60.35.1"

		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmServiceMdLevel<br>
		 * 4: wwpLeosCfmServiceFaultTime<br>
		 * 5: wwpLeosCfmServiceFaultType<br>
		 * 6: wwpLeosCfmServiceFaultDesc<br>
		 * 7: wwpLeosCfmServiceFaultMep<br>
		 */
		final String wwpLeosCfmFaultTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.1";

		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceType<br>
		 * 3: wwpLeosCfmServiceValue<br>
		 * 4: wwpLeosCfmServiceAdminState<br>
		 * 5: wwpLeosCfmServiceOperState<br>
		 * 6: wwpLeosCfmServiceMdLevel<br>
		 * 7: wwpLeosCfmServiceFaultTime<br>
		 * 8: wwpLeosCfmServiceFaultType<br>
		 * 9: wwpLeosCfmServiceFaultDesc<br>
		 * 10: wwpLeosCfmServiceFaultMep<br>
		 * 11: wwpLeosCfmServiceVsPbtName<br>
		 */
		final String wwpLeosCfmFaultTrapSet = ".1.3.6.1.4.1.6141.2.60.35.2.0.10";

		final String wwpLeosCfmFaultTrapClear = ".1.3.6.1.4.1.6141.2.60.35.2.0.11";

		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceType<br>
		 * 3: wwpLeosCfmServiceValue<br>
		 * 4: wwpLeosCfmServiceAdminState<br>
		 * 5: wwpLeosCfmServiceOperState<br>
		 * 6: wwpLeosCfmServiceMdLevel<br>
		 * 7: wwpLeosCfmServiceFaultTime<br>
		 * 8: wwpLeosCfmServiceFaultType<br>
		 * 9: wwpLeosCfmServiceFaultDesc<br>
		 * 10: wwpLeosCfmServiceFaultMep<br>
		 */
		final String wwpLeosCfmExtFaultTrapSet = ".1.3.6.1.4.1.6141.2.60.35.2.0.21";
		final String wwpLeosCfmExtFaultTrapClear = ".1.3.6.1.4.1.6141.2.60.35.2.0.22";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtSynLossSessionFrameLossNear<br>
		 * 4: wwpLeosCfmExtSynLossSessionFlnThreshold<br>
		 */
		final String wwpLeosCfmExtSynLossSessionNearFaultTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.26";
		final String wwpLeosCfmExtSynLossSessionNearFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.28";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtSynLossSessionFrameLossFar<br>
		 * 4: wwpLeosCfmExtSynLossSessionFlfThreshold<br>
		 */
		final String wwpLeosCfmExtSynLossSessionFarFaultTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.27";
		final String wwpLeosCfmExtSynLossSessionFarFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.29";

		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtDelaySessionAvgDelay<br>
		 * 4: wwpLeosCfmExtDelaySessionDelayThreshold<br>
		 */
		final String wwpLeosCfmExtDelaySessionAvgDelayFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.30";
		final String wwpLeosCfmExtDelaySessionAvgDelayFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.31";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtDelaySessionAvgJitter<br>
		 * 4: wwpLeosCfmExtDelaySessionJitterThreshold<br>
		 */
		final String wwpLeosCfmExtDelaySessionAvgJitterFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.32";
		final String wwpLeosCfmExtDelaySessionAvgJitterFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.33";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtDelaySessionMaxDelay<br>
		 * 4: wwpLeosCfmExtDelaySessionMaxDelayThreshold<br>
		 */
		final String wwpLeosCfmExtDelaySessionMaxDelayFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.34";
		final String wwpLeosCfmExtDelaySessionMaxDelayFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.35";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtDelaySessionMaxJitter<br>
		 * 4: wwpLeosCfmExtDelaySessionMaxJitterThreshold<br>
		 */
		final String wwpLeosCfmExtDelaySessionMaxJitterFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.36";
		final String wwpLeosCfmExtDelaySessionMaxJitterFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.37";

		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtFrameLossSessionFrameLossNear<br>
		 * 4: wwpLeosCfmExtFrameLossSessionFrameLossNearThreshold<br>
		 */
		final String wwpLeosCfmExtFrameLossSessionFrameLossNearFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.38";
		final String wwpLeosCfmExtFrameLossSessionFrameLossNearFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.39";
		/**
		 * 1: wwpLeosCfmServiceName<br>
		 * 2: wwpLeosCfmServiceVlan<br>
		 * 3: wwpLeosCfmExtFrameLossSessionFrameLossFar<br>
		 * 4: wwpLeosCfmExtFrameLossSessionFrameLossFarThreshold<br>
		 */
		final String wwpLeosCfmExtFrameLossSessionFrameLossFarFaultSetTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.40";
		final String wwpLeosCfmExtFrameLossSessionFrameLossFarFaultClearTrap = ".1.3.6.1.4.1.6141.2.60.35.2.0.41";

		final String wwpLeosCfmServiceName = ".1.3.6.1.4.1.6141.2.60.35.1.2.1.1.9";
		final String wwpLeosCfmServiceVlan = ".1.3.6.1.4.1.6141.2.60.35.1..2.2.1.5";
		final String wwpLeosCfmServiceMdLevel = ".1.3.6.1.4.1.6141.2.60.35.1.2.1.1.10";
		final String wwpLeosCfmServiceFaultTime = ".1.3.6.1.4.1.6141.2.60.35.1.2.2.1.1";
		/**
		 * Value list: 1: none(1)<br>
		 * 2: someRDIDefect(2)<br>
		 * 3: someMACStatusDefect(3)<br>
		 * 4: someRMEPCCMDefect(4)<br>
		 * 5: errorCCMDefect(5)<br>
		 * 6: xconCCMDefect(6)<br>
		 * 7: instability(7)<br>
		 */
		final String wwpLeosCfmServiceFaultType = ".1.3.6.1.4.1.6141.2.60.35.1.2.2.1.2";

		final String wwpLeosCfmServiceFaultDesc = ".1.3.6.1.4.1.6141.2.60.35.2.2.1.3";
		/**
		 * Value list: 1: mplsVs(1) 2: ethVs(2) 3: vlan(3) 4: pbtTunnel(4) 5:
		 * vs(5) 6: other(9)
		 */
		final String wwpLeosCfmServiceType = ".1.3.6.1.4.1.6141.2.60.35.2.1.1.2";

		final String wwpLeosCfmServiceValue = ".1.3.6.1.4.1.6141.2.60.35.2.1.1.3";
		/**
		 * Value list: 1: disabled(1) 2: enabled(2) Default values: 1: enabled
		 * (name)
		 */

		final String wwpLeosCfmServiceAdminState = ".1.3.6.1.4.1.6141.2.60.35.2.1.1.4";

		/**
		 * Value list: 1: disabled(1) 2: enabled(2)
		 */
		final String wwpLeosCfmServiceOperState = ".1.3.6.1.4.1.6141.2.60.35.2.1.1.5";

		final String wwpLeosCfmServiceVsPbtName = ".1.3.6.1.4.1.6141.2.60.35.2.2.1.50";
	}

	public enum wwpLeosCfmServiceType {
		mplsVs(1), ethVs(2), vlan(3), pbtTunnel(4), vs(5), other(9);

		private int val;

		private wwpLeosCfmServiceType(int val) {
			this.val = val;
		}

		public static wwpLeosCfmServiceType get(int val) {
			for (wwpLeosCfmServiceType e : wwpLeosCfmServiceType.values()) {
				if (e.val == val) {
					return e;
				}
			}

			return null;
		}

		public int getVal() {
			return val;
		}

	}

	public static class cienaCommon {
		final String cienaCommon = ".1.3.6.1.4.1.1271.1";

		/**
		 * CLEAR(1), CRI(3), MAJ(4), MIN(5), WAR(6), INFO(8)
		 */
		final String cienaGlobalSeverity = ".1.3.6.1.4.1.1271.1.3.1";
		final String cienaGlobalMacAddress = ".1.3.6.1.4.1.1271.1.3.2";
	}

	public enum cienaGlobalSeverity {
		CLEAR(1), CRI(3), MAJ(4), MIN(5), WAR(6), INFO(8);

		private int val;

		private cienaGlobalSeverity(int val) {
			this.val = val;
		}

		public static cienaGlobalSeverity get(int val) {
			for (cienaGlobalSeverity e : cienaGlobalSeverity.values()) {
				if (e.val == val) {
					return e;
				}
			}

			return null;
		}

		public int getVal() {
			return val;
		}

		public String getDablGrCd() {
			switch (val) {
			case 1:
				return AdamsCfg.DABL_GR_CD.Clear.getCode();
			case 3:
				return AdamsCfg.DABL_GR_CD.Critical.getCode();
			case 4:
				return AdamsCfg.DABL_GR_CD.Major.getCode();
			case 5:
				return AdamsCfg.DABL_GR_CD.Minor.getCode();
			case 6:
				return AdamsCfg.DABL_GR_CD.Warning.getCode();
			case 8:
				return AdamsCfg.DABL_GR_CD.Info.getCode();
			}

			return AdamsCfg.DABL_GR_CD.Major.getCode();

		}
	}

}

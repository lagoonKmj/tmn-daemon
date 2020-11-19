package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.GwServiceImpl;

import fxms.bas.fxo.FxCfg;

public class WoorinetServiceImpl extends GwServiceImpl implements
		WoorinetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3217758219958055840L;

	public static void main(String[] args) {
		WoorinetServiceImpl.start(WoorinetService.class.getSimpleName(),
				WoorinetServiceImpl.class, args);
	}

	public WoorinetServiceImpl(String name, int port) throws RemoteException,
			Exception {
		super(name, port);
	}

	@Override
	protected void onStarted() throws Exception {

		super.onStarted();

		GwApi.setEquipMfactCd(WoorinetService.VENDOR_WOORINET); //
		GwApi.setEquipMdlCd(new String[] { WoorinetService.MODEL_OPN_3000 }); //
		GwApi.setEmsTypVal(new String[] { WoorinetService.WOORINET_PTN_EMS });

		GwApi.getApi().reload();

		if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
			new WoorinetGwThread().start();
		}

	}

}
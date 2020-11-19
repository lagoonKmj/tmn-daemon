package com.lagoon.tmn.teams.gw.vendor.telefield;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.GwServiceImpl;

import fxms.bas.fxo.FxCfg;

public class TelefieldServiceImpl extends GwServiceImpl implements
		TelefieldService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4961803697506565159L;

	public static void main(String[] args) {
		TelefieldServiceImpl.start(TelefieldService.class.getSimpleName(),
				TelefieldServiceImpl.class, args);
	}

	public TelefieldServiceImpl(String name, int port) throws RemoteException,
			Exception {
		super(name, port);
	}

	@Override
	protected void onStarted() throws Exception {

		GwApi.setEquipMfactCd(TelefieldService.VENDOR_TELEFIELD); // 텔레필드
		// GwApi.getApi().setEquipMdlCd(new String[] { "0000000806" }); //
		// TFOM-S570
		GwApi.setEmsTypVal(new String[] { TelefieldService.TFOM_C60_EMS,
				TelefieldService.TFOM_EMS });
		GwApi.setEquipMdlCd(new String[] { TelefieldService.MODEL_S570 });

		GwApi.getApi().reload();

		super.onStarted();

		if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
			new TelefieldGwThread().start();
		}
	}

}
package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.GwServiceImpl;

import fxms.bas.fxo.FxCfg;

public class CienaServiceImpl extends GwServiceImpl implements CienaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4846613758021028207L;

	public static void main(String[] args) {
		CienaServiceImpl.start(CienaService.class.getSimpleName(),
				CienaServiceImpl.class, args);
	}

	public CienaServiceImpl(String name, int port) throws RemoteException,
			Exception {
		super(name, port);
	}
	

	// @Override
	// public void requestTdmCollect() throws RemoteException, Exception {
	// this.runCron("CienaCrsCron");
	// }

	@Override
	protected void onStarted() throws Exception {

		GwApi.setEquipMfactCd(CienaService.VENDOR_CIENA); // CIENA
		GwApi.setEquipMdlCd(new String[] { CienaService.MODEL_5410,
				CienaService.MODEL_CESD_5150, CienaService.MODEL_QOPAS });

		GwApi.getApi().reload();

		super.onStarted();

		
		if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
			new CienaGwThread().start();
		}
	}

}

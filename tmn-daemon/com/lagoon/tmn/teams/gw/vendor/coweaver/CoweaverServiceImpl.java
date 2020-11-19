package com.lagoon.tmn.teams.gw.vendor.coweaver;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.GwServiceImpl;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaService;

import fxms.bas.fxo.FxCfg;

public class CoweaverServiceImpl extends GwServiceImpl implements CoweaverService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1021149822558772059L;

	public static void main(String[] args) {
		CoweaverServiceImpl.start(CienaService.class.getSimpleName(), CoweaverServiceImpl.class, args);
	}

	public CoweaverServiceImpl(String name, int port) throws RemoteException, Exception {
		super(name, port);
	}

	@Override
	protected void onStarted() throws Exception {

		GwApi.setEquipMfactCd(CoweaverService.VENDOR_COWEAVER); // 코위버
		GwApi.setEquipMdlCd(
				new String[] { CoweaverService.MODEL_UTRANS_7200, CoweaverService.MODEL_UTRANS_7300,
						CoweaverService.MODEL_UTRANS_7400, CoweaverService.MODEL_UTRANS_7400C });
		
		GwApi.getApi().reload();

		super.onStarted();

		if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
			new CoweaverGwThread().start();
		}

	}

}

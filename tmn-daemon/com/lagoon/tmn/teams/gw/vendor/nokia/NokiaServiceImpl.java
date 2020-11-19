package com.lagoon.tmn.teams.gw.vendor.nokia;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.co.GwServiceImpl;

import fxms.bas.fxo.FxCfg;

public class NokiaServiceImpl extends GwServiceImpl implements NokiaService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -682888386960511641L;

	public static void main(String[] args) {
		NokiaServiceImpl.start(NokiaService.class.getSimpleName(), NokiaServiceImpl.class, args);
	}

	public NokiaServiceImpl(String name, int port) throws RemoteException, Exception {
		super(name, port);
	}

	@Override
	protected void onStarted() throws Exception {

		GwApi.setEquipMfactCd(NokiaService.VENDOR_NOKIA); // 노키아
		GwApi.setEquipMdlCd(new String[] { NokiaService.MODEL_7210SAS_T });
		GwApi.getApi().reload();

		GwApi.getApi().loadSyslogPattern(); // 노키아는 시스로그를 이용하여 장애를 생성하므로, 초기 로딩시 패턴을 로드 함 
		
		super.onStarted();

		if (FxCfg.getCfg().isEnable("GW-THREAD-ENABLED",  true)) {
			new NokiaGwThread().start();
		}
	}

}
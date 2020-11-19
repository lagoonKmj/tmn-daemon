package com.lagoon.tmn.teams.gw.co;

import java.rmi.RemoteException;

import fxms.bas.fxo.service.FxServiceImpl;

public class GwServiceImpl extends FxServiceImpl implements GwService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -995294743027485326L;

	public static GwServiceImpl service;

	public static void main(String[] args) {
		GwServiceImpl.start(GwService.class.getSimpleName(), GwServiceImpl.class, args);
	}

	public GwServiceImpl(String name, int port) throws RemoteException, Exception {
		super(name, port);

		GwServiceImpl.service = this;
	}

	@Override
	protected void onStarted() throws Exception {

		super.onStarted();

		new StateCheckThread().start();
		new FireEventThread().start();

	}

}

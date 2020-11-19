package com.lagoon.tmn.teams.app;

import java.rmi.RemoteException;

import fxms.bas.fxo.service.ext.ExtServiceImpl;

public class DataServiceImpl extends ExtServiceImpl implements DataService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6940971949100977560L;

	public static void main(String[] args) {
		DataServiceImpl.start(DataService.class.getSimpleName(), DataServiceImpl.class, args);
	}

	public DataServiceImpl(String name, int port) throws RemoteException, Exception {
		super(name, port);
	}
}

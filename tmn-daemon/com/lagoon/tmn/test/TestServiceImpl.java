package com.lagoon.tmn.test;

import java.rmi.RemoteException;

import com.lagoon.tmn.teams.gw.co.GwServiceImpl;

public class TestServiceImpl extends GwServiceImpl implements TestService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6393509158107560196L;

	public static void main(String[] args) {
		TestServiceImpl.start(TestService.class.getSimpleName(), TestServiceImpl.class, args);
	}

	public TestServiceImpl(String name, int port) throws RemoteException, Exception {
		super(name, port);
	}

}

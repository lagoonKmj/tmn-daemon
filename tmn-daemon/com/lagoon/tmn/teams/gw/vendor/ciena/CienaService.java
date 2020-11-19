package com.lagoon.tmn.teams.gw.vendor.ciena;

import com.lagoon.tmn.teams.gw.co.GwService;

public interface CienaService extends GwService {

	public static final String VENDOR_CIENA = "459";

	// public static final String VENDOR_CIENA = "576"; // 운용에 제조사 번호

	public static final String MODEL_5410 = "5760965000";
	public static final String MODEL_QOPAS = "5760966000";
	public static final String MODEL_CESD_5150 = "4594527000";

//public void requestTdmCollect() throws RemoteException, Exception;

//	public static final String LOGIN_ID = "administrator";
//	public static final String LOGIN_PWD = "admin1!";
//	public static final int PORT = 10201;

	// ACR-USER:5410-GangreungB-1::1234::administrator,admin1!;
	// ACR-USER:administrator::1234::admin1!;
}

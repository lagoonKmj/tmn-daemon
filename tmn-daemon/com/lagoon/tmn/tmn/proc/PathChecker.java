package com.lagoon.tmn.tmn.proc;

import java.util.List;
import java.util.Map;

import com.lagoon.tmn.tmn.dao.AdamsCheckLineDao;
import com.lagoon.tmn.tmn.vo.OIV10611Ext;
import com.lagoon.tmn.tmn.vo.OIV10614Ext;

public class PathChecker {

	@SuppressWarnings("unused")
	private final String NET_TOPO_TYPE_RING = "R";
	@SuppressWarnings("unused")
	private final String NET_TOPO_TYPE_TRUNK = "T";
	private final String NET_TOPO_TYPE_WDMTRUNK = "WT";

	public static void main(String[] args) {
		PathChecker checker = new PathChecker();
		try {
			checker.checkWdmLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PathChecker() {
		// TODO Auto-generated constructor stub
	}

	public void checkWdmNet() throws Exception {

		AdamsCheckLineDao dao = new AdamsCheckLineDao();
		Map<String, OIV10614Ext> wdmMap = dao.selectNet(NET_TOPO_TYPE_WDMTRUNK);
		List<OIV10614Ext> netList = dao.selectNetUse(NET_TOPO_TYPE_WDMTRUNK);

		for (OIV10614Ext net : netList) {
			net.checkWdmTrunkPath(wdmMap);
		}

	}

	public void checkWdmLine() throws Exception {

		AdamsCheckLineDao dao = new AdamsCheckLineDao();
		Map<String, OIV10614Ext> wdmMap = dao.selectNet(NET_TOPO_TYPE_WDMTRUNK);
		List<OIV10611Ext> lineList = dao.selectLineUse(NET_TOPO_TYPE_WDMTRUNK);

		int okCnt = 0, errCnt = 0;
		boolean bret;

		for (OIV10611Ext line : lineList) {
			bret = line.checkWdmTrunkPath(wdmMap);
			if (bret) {
				okCnt++;
			} else {
				errCnt++;
			}
		}

		System.out.println("ok=" + okCnt + ", err=" + errCnt);

	}
}

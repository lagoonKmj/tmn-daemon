package com.lagoon.tmn.tmn.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OIV10611Ext extends OIV10611 {

	public OIV10611Ext() {
		// TODO Auto-generated constructor stub
	}

	private List<OIV10612Ext> pathList = new ArrayList<OIV10612Ext>();

	public List<OIV10612Ext> getPathList() {
		return pathList;
	}

	public void setPathList(List<OIV10612Ext> pathList) {
		this.pathList = pathList;
	}

	public boolean checkWdmTrunkPath(Map<String, OIV10614Ext> wdmMap) {

		StringBuffer sb = new StringBuffer();
		boolean isError = false;

		OIV10615Ext wdmPath;
		OIV10614Ext wdm;
		for (OIV10612Ext vo : pathList) {
			if (vo.getWdmTrunkId() == null) {
				sb.append("\t").append(vo.toPath()).append("\t>>> NO WDM \n");
			} else {
				wdm = wdmMap.get(vo.getWdmTrunkId());
				if (wdm == null) {
					sb.append("\t").append(vo.toPath()).append("\t>>> NOT FOUND WDM : ").append(vo.getWdmTrunkId()).append("\n");
					isError = true;
				} else {
					wdmPath = vo.findSamePath(wdm.getPathList());
					if (wdmPath == null) {
						sb.append("\t").append(vo.toPath()).append("\t>>> NOT FOUDN WDM PATH : ").append(vo.getWdmTrunkId())
								.append("\n");
						isError = true;
					} else {
						sb.append("\t").append(vo.toPath()).append("\t>>> WDM\t").append(wdmPath.toPath()).append("\n");
					}
				}
			}
		}

		System.out.println("-----------------------------------------------------------------------");
		System.out.println("LINE-NUM : " + toName());

		if (isError) {
			System.out.println(sb);
		}

		return isError;
	}

	public String toName() {
		return getLineNum() + ")" + getLineNm();
	}

}

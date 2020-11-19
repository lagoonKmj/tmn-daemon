package com.lagoon.tmn.tmn.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OIV10614Ext extends OIV10614 {

	public OIV10614Ext() {
	}

	private String trmsNetTopoTypGrpNm;

	public String getTrmsNetTopoTypGrpNm() {
		return trmsNetTopoTypGrpNm;
	}

	public void setTrmsNetTopoTypGrpNm(String trmsNetTopoTypGrpNm) {
		this.trmsNetTopoTypGrpNm = trmsNetTopoTypGrpNm;
	}

	private List<OIV10615Ext> pathList = new ArrayList<OIV10615Ext>();

	public List<OIV10615Ext> getPathList() {
		return pathList;
	}

	public void setPathList(List<OIV10615Ext> pathList) {
		this.pathList = pathList;
	}

	public void checkWdmTrunkPath(Map<String, OIV10614Ext> wdmMap) {

		StringBuffer sb = new StringBuffer();
		boolean isError = false;

		OIV10615Ext wdmPath;
		OIV10614Ext wdm;
		for (OIV10615Ext vo : pathList) {
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
		System.out.println("NET-NUM : " + toName());

		if (isError) {
			System.out.println(sb);
		}

	}

	public String toName() {
		return getNetNum() + ")" + getNwNm();
	}

	public void checkLinePath(OIV10611Ext line) {

	}

	/**
	 * 선번에 WDM트렁크가 있으면 제공한다.
	 * 
	 * @return
	 */
	public List<String> getWdmTrunkIdList() {

		List<String> list = new ArrayList<String>();

		for (OIV10615Ext vo : pathList) {
			if (vo.getWdmTrunkId() != null) {
				if (list.contains(vo.getWdmTrunkId()) == false) {
					list.add(vo.getWdmTrunkId());
				}
			}
		}
		return list;
	}

}

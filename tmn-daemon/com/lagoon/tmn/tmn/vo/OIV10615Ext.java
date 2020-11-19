package com.lagoon.tmn.tmn.vo;

import java.util.List;

public class OIV10615Ext extends OIV10615 {

	public OIV10615Ext() {
		// TODO Auto-generated constructor stub
	}

	public String toPath() {
		StringBuffer sb = new StringBuffer();
		sb.append("'").append(getEquipAPortInfo()).append("'");
		sb.append(" [").append(getEquipId()).append("]");
		sb.append(" ").append("'").append(getEquipBPortInfo()).append("'");
		sb.append(" ").append(getNetNum());

		return sb.toString();
	}

	public String getPath() {
		StringBuffer sb = new StringBuffer();
		sb.append("'").append(getEquipAPortInfo()).append("'");
		sb.append(" [").append(getEquipId()).append("]");
		sb.append(" ").append("'").append(getEquipBPortInfo()).append("'");

		return sb.toString();
	}

	/**
	 * 목록에서 동일한 선번을 찾는다.
	 * 
	 * @return
	 */
	public OIV10615Ext findSamePath(List<OIV10615Ext> list) {

		if (list == null) {
			return null;
		}

		String mePath = getPath();

		for (OIV10615Ext vo : list) {
			if (vo.getPath().equals(mePath)) {
				return vo;
			}
		}

		return null;
	}
}

package com.lagoon.tmn.teams.fxms.dbo;

import com.lagoon.tmn.teams.co.vo.AdamsEquipVo;

import fxms.nms.mo.property.Modelable;

public class SyslogPatternAdams extends fxms.nms.co.syslog.vo.SyslogPattern {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6379325281955760985L;

	private String equipMdlCd;

	public SyslogPatternAdams() {
	}

	@Override
	public boolean equalModel(Modelable o) {
		if (o instanceof AdamsEquipVo) {
			return ((AdamsEquipVo) o).getEquipMdlCd().equals(equipMdlCd);
		}
		return false;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

}

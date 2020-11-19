package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

public class CienaCronPs extends PsCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7755951690152631940L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(true);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Ciena().makeAdapter(dcn);
	}

	@Override
	public String getGroup() {
		return "CienaCron";
	}

}

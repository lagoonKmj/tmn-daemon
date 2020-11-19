package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.ConfCron;

/**
 * Ciena 장비에 대한 구성정보 수집
 * 
 * @author subkjh(김종훈)
 *
 */
public class CienaCron extends ConfCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1240379170664345833L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(false);
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

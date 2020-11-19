package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

/**
 * 우리넷 OPN-3000 장비에 대한 일일 점검 데이터를 수집하는 크론
 * 
 * @author subkjh(김종훈)
 *
 */
public class Opn3000CronPs extends PsCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 469123006131301789L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(false);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Woorinet().makeAdapterOpn3000(dcn);
	}

	@Override
	public String getGroup() {
		return "WoorinetCron";
	}

}

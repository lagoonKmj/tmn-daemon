package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

/**
 * 우리넷 장비 성능(광레벨, 온도) 수집 설정파일 : deploy/filter/woorinet-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class WoorinetCronPs extends PsCron {

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
		return new Woorinet().makeAdapter(dcn);
	}

	@Override
	public String getGroup() {
		return "WoorinetCron";
	}
}
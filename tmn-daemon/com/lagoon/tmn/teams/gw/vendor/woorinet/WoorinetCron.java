package com.lagoon.tmn.teams.gw.vendor.woorinet;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.ConfCron;

/**
 * 우리넷 구성 수집<br>
 * 설정파일 : deploy/filter/woorinet-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class WoorinetCron extends ConfCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 253436570894937807L;

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
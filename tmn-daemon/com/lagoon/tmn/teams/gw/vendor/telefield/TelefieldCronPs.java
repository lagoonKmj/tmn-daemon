package com.lagoon.tmn.teams.gw.vendor.telefield;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

/**
 * 텔레필드 장비의 성능을 수집하는 크론 <br>
 * 설정파일 : deploy/filter/telefield-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class TelefieldCronPs extends PsCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5275176093981912592L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(false);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Telefield().makeAdapter(dcn);
	}

}
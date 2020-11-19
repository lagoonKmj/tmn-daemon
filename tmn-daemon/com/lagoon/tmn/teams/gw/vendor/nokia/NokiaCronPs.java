package com.lagoon.tmn.teams.gw.vendor.nokia;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

/**
 * 노키아 성능을 수집하는 크론<br>
 * 설정파일 : deploy/filter/nokia-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class NokiaCronPs extends PsCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789251885627322451L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(false);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Nokia().makeAdapter(dcn);
	}

}
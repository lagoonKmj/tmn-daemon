package com.lagoon.tmn.teams.gw.vendor.nokia;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.ConfCron;

/**
 * 노키아 장비 구성 수집<br>
 * 설정파일 : deploy/filter/nokia-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class NokiaCron extends ConfCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3987305012090794124L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(false);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		
		return new Nokia().makeAdapter(dcn);
	}

}
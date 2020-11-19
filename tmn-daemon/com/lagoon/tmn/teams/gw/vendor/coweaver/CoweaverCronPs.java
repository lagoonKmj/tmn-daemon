package com.lagoon.tmn.teams.gw.vendor.coweaver;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.PsCron;

/**
 * 
 * 코위버 장비 성능(광레벨, 온도) 수집<br>
 * 설정파일 : deploy/filter/coweaver-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class CoweaverCronPs extends PsCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7368564362714866954L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(true);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Coweaver().makeAdapter(dcn);
	}
}

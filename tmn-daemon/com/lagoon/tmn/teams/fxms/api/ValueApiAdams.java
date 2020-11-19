package com.lagoon.tmn.teams.fxms.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fxms.bas.co.def.PS_TYPE;
import fxms.bas.po.PsVo;
import fxms.bas.po.TimeSeriesVo;
import fxms.bas.po.ValueApi;
import fxms.bas.po.VoList;
import fxms.bas.po.item.PsItem;
import fxms.bas.po.vo.FxServicePsVo;
import fxms.bas.po.vo.UpdateDataVo;

public class ValueApiAdams extends ValueApi {

	@Override
	public Map<String, Integer> doInsertValue(VoList voList) throws Exception {
		return new HashMap<String, Integer>();
	}

	@Override
	public List<PsVo> doSelectValueCur() throws Exception {
		return new ArrayList<PsVo>();
	}

	@Override
	public void doUpdateColumn(List<UpdateDataVo> updateList) throws Exception {

	}

	@Override
	protected List<TimeSeriesVo> doSelectPsValue(long moNo, PsItem item, PS_TYPE pstype, long startDate, long endDate)
			throws Exception {
		return new ArrayList<TimeSeriesVo>();
	}

	@Override
	public void doUpdateServicePsCode() throws Exception {

	}

	@Override
	public List<FxServicePsVo> doSelectServicePs(String arg0, String arg1) throws Exception {
		return null;
	}

}

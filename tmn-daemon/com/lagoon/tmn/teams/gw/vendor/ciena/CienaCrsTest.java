package com.lagoon.tmn.teams.gw.vendor.ciena;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.vendor.ciena.adapter.CienaConfAdapterCrs;
import com.lagoon.tmn.teams.gw.vendor.ciena.dao.AdamsTdmDao;

import subkjh.bas.co.log.Logger;
import subkjh.bas.co.utils.FileUtil;
import fxms.bas.api.FxApi;

public class CienaCrsTest extends CienaConfAdapterCrs {
	
	public static void main(String[] args) {

		try {
			CienaCrsTest crs = new CienaCrsTest(null, new Ciena());
			crs.test();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public CienaCrsTest(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
	}
	

	void test() {
		String clctDtm = String.valueOf(FxApi.getDate());
		OIV28160 o;
		List<OIV28160> oList = new ArrayList<OIV28160>();
		List<String> list = FileUtil.getLines(new File("datas/RTRV-CRS-ALL.log"));
		for (String s : list) {
			try {
				o = make28160(s);
				if (o != null) {
					o.setEquipId("TEST");
					o.setClctDtm(clctDtm);
					oList.add(o);
				}
			} catch (Exception e) {
				System.out.println(s);
				Logger.logger.error(e);
			}
		}
		if (oList.size() > 0) {

			Map<String, OIV28160> map = new HashMap<String, OIV28160>();
			for (OIV28160 e : oList) {
				if (map.get(e.getAdrcEquipPortDesc()) != null) {
					System.out.println(e.getAdrcEquipPortDesc());
				} else {
					map.put(e.getAdrcEquipPortDesc(), e);
				}
			}

			try {
				new AdamsTdmDao().setOIV28160("TEST", oList);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

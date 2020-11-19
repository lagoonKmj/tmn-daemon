package com.lagoon.tmn.mig;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.fxms.dbo.OMN33812;

import subkjh.bas.co.utils.FileUtil;
import subkjh.bas.dao.data.TabData;

public class MigTeams {

	public static void main(String[] args) {
		MigTeams c = new MigTeams();
		try {
			// c.updateOIV10104();
			// c.updateOIV10105();
			c.updateOIV10200ClctMthdClCd();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * OIV10200(장비모델)의 수집 유형을 업데이트 한다.
	 * 
	 * @throws Exception
	 */
	void updateOIV10200ClctMthdClCd() throws Exception {
		TeamsMigDao dao = new TeamsMigDao();

		Map<Integer, MigModelVo> map = dao.selectModelInfo();
		List<MigModelVo> updateList = new ArrayList<MigModelVo>();

		TabData data = parse(new File("datas/datas.txt"));
		MigModelVo vo;
		int modelId;
		int modelIndex = data.getIndexCol("MODEL_ID");
		int index = data.getIndexCol("EMS_TYPE");
		for (Object e[] : data.getDataList()) {

			modelId = Integer.parseInt(e[modelIndex].toString());
			vo = map.get(modelId);
			if (vo == null) {
				continue;
			}

			if ("none".equalsIgnoreCase(e[index].toString())) {
				vo.setClctMthdClCd("02"); // NE
			} else {
				if (modelId <= 0) {
					continue;
				}
				vo.setClctMthdClCd("01"); // EMS
			}

			updateList.add(vo);

		}

		if (updateList.size() > 0) {
			dao.updateModelClctMthdClCd(updateList);
		}

	}

	@SuppressWarnings("unused")
	void update2() throws Exception {
		TeamsMigDao dao = new TeamsMigDao();

		Map<Integer, MigModelVo> map = dao.selectModelInfo();
		List<MigModelVo> updateList = new ArrayList<MigModelVo>();

		TabData data = parse(new File("datas/datas.txt"));
		MigModelVo vo;
		int modelId;
		int modelIndex = data.getIndexCol("MODEL_ID");
		int index = data.getIndexCol("EMS_TYPE");
		int typeIndex = data.getIndexCol("PORT_TYPE");

		for (Object e[] : data.getDataList()) {

			modelId = Integer.parseInt(e[modelIndex].toString());
			vo = map.get(modelId);
			if (vo == null) {
				continue;
			}

			if ("none".equalsIgnoreCase(e[index].toString())) {
				System.out.println(Arrays.toString(e));
			} else {

			}

		}

	}

	/**
	 * DCN의내용을 장비상세보기에 넣는다.
	 * 
	 * @throws Exception
	 */
	void updateOIV10104() throws Exception {
		TeamsMigDao dao = new TeamsMigDao();
		dao.mergeFromDcn();
	}

	protected TabData parse(File file) {
		List<String> list = FileUtil.getLines(new File("datas/datas.txt"));
		String ss[];
		String cols[] = null;

		TabData ret = new TabData();

		for (String line : list) {
			ss = line.split("\t");
			if (ss.length == 2 && line.startsWith("Table Name")) {
				ret.setName(ss[1]);
			} else if (cols == null) {
				cols = ss;
				ret.setColArr(cols);
			} else if (cols.length == ss.length) {
				ret.addDataArr(ss);
			}
		}

		return ret;
	}

	void insertOMN33812() throws Exception {
		TeamsMigDao dao = new TeamsMigDao();
		dao.sync("SELECT_cf_alarm_reason_info", OMN33812.class, new IChecker() {

			OMN33812 o1, o2;

			@Override
			public boolean isValid(Object prev, Object cur) {
				if (prev == null) {
					return true;
				}

				o1 = (OMN33812) prev;
				o2 = (OMN33812) cur;
				return (o1.getEquipMdlCd().equals(o2.getEquipMdlCd()) && o1.getCmprCharStrVal().equals(o2.getCmprCharStrVal())) == false;
			}
		});

	}
}

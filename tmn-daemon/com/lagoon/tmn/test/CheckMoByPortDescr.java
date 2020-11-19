package com.lagoon.tmn.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.dao.AdamsDao;
import com.lagoon.tmn.teams.co.vo.LineNetworkBaseVo;
import com.lagoon.tmn.teams.co.vo.LineVo;
import com.lagoon.tmn.teams.co.vo.TrmsMoData;
import com.lagoon.tmn.teams.fxms.mo.EquipMo;
import com.lagoon.tmn.teams.fxms.mo.PortMo;

import subkjh.bas.co.log.Logger;

/**
 * <pre>
 * 1. 원래 포트정보(OIV0400) 으로 조립할려고 했으나,, 데이터량 도 많고 안좋은 방향인듯해서 파싱하는 방향으로 우회
 * 2. 레거시 소스 확인 결과 굳이 포트,채널을 분리 안해도될듯하여 추후에 보기로하고 무시
 * </pre>
 * 
 * @author lagoon
 *
 */
public class CheckMoByPortDescr {

	public static void main(String[] args) throws Exception {

		// Logger.logger.setLevel(LOG_LEVEL.info);
		CheckMoByPortDescr clazz = new CheckMoByPortDescr();
		// clazz.getMo();
		// clazz.getPort();
		clazz.getLineList();
	}

	public void getLineList() throws Exception {
		List<LineVo> lines = new AdamsDao().selectLineList();
		for (LineVo lineVo : lines) {
			if (lineVo.getAPortInfo() != null) {
				boolean isEtcAndB2B = lineVo.getTrmsNetLineUsgCd().equals("8")
						|| lineVo.getTrmsNetLineUsgCd().equals("14");
				String portInfo = parse(lineVo.getAPortInfo(), isEtcAndB2B);
				// if (portInfo.equals(lineVo.getAPortInfo()) ||
				// portInfo.equals("#####LAGOON2####")) {
				//
				// } else {
				Logger.logger.debug("lineNum : {}, before : {}, after : {}",
						lineVo.getLineNum(), lineVo.getEquipAPortInfo(),
						portInfo);
				// }
			}
		}
	}

	@SuppressWarnings("unused")
	private String parse(String value, boolean isEtcAndB2B) {
		String rtnVal = "";
		if (value == null) {
			return "#####LAGOON2####";
		}
		String[] arry = value.split("-");
		if (arry.length == 1) {
			if (isEtcAndB2B == true) {
				String[] arry2 = value.split(".");
				if (arry2.length < 2) {
					return value;
				}
				if (true) {
					rtnVal = arry[0];
				} else {
					// rtnVal = '.'+arry[1]+'~'+Pf_comm.Pf_plus(arry[1]);
				}
			} else {

				if (true) {
					rtnVal = arry[0];
				} else {
					rtnVal = value.replace(arry[0], "");
				}
			}
		} else {

			if (arry.length < 3) {
				return value;
			}
			if (arry[0].indexOf("S") == 0 || arry[1].indexOf("P") == 0) {
				if (true) {
					rtnVal = arry[0] + "-" + arry[1];
				} else {
					rtnVal = value.replace(arry[0] + "-" + arry[1], "");
				}
			} else {
				return value;
			}
		}
		return rtnVal;
	}

	private Map<String, TrmsMoData> trmsMoDataMap = new HashMap<String, TrmsMoData>();

	public void getMo() {
		List<EquipMo> equipMoList = getNeList();
		TrmsMoData data;
		for (EquipMo equipMo : equipMoList) {
			data = new TrmsMoData(equipMo);
			trmsMoDataMap.put(equipMo.getEquipId(), data);
		}
	}

	public void getPort() {

		List<PortMo> portList = getPortList();
		portList.forEach(portMo -> {
			TrmsMoData trmsMoData = trmsMoDataMap.get(portMo.getEquipId());
			trmsMoData.getPortLines().put(portMo.getPortDesc(),
					new ArrayList<LineNetworkBaseVo>());
		});

	}

	private List<EquipMo> getNeList() {
		try {
			return new AdamsDao().selectNeList(null, null, 0);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	private List<PortMo> getPortList() {
		try {
			return new AdamsDao().selectPortList();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

}

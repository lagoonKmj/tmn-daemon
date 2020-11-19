package com.lagoon.tmn.teams.co.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lagoon.tmn.teams.fxms.mo.EquipMo;

/**
 * TRMS 장비(MO) 기준 필요한 정보를 담는 VO
 * 
 * @author lagoon(강명중)
 *
 */
public class TrmsMoData {

	private EquipMo equipMo;
	private Map<String, List<LineNetworkBaseVo>> portLines;
	private Map<String, List<LineNetworkBaseVo>> portNetworks;

	public TrmsMoData(EquipMo equipMo) {

		setEquipMo(equipMo);

		portLines = new HashMap<String, List<LineNetworkBaseVo>>();
		portNetworks = new HashMap<String, List<LineNetworkBaseVo>>();
	}

	public EquipMo getEquipMo() {
		return equipMo;
	}

	public void setEquipMo(EquipMo equipMo) {
		this.equipMo = equipMo;
	}

	public Map<String, List<LineNetworkBaseVo>> getPortLines() {
		return portLines;
	}

	public void setPortLines(Map<String, List<LineNetworkBaseVo>> portLines) {
		this.portLines = portLines;
	}

	public Map<String, List<LineNetworkBaseVo>> getPortNetworks() {
		return portNetworks;
	}

	public void setPortRings(Map<String, List<LineNetworkBaseVo>> portNetworks) {
		this.portNetworks = portNetworks;
	}

	public List<LineNetworkBaseVo> getLineListByPortInfo(String portInfo) {
		return portLines.get(portInfo);
	}

	public List<LineNetworkBaseVo> getNetworkListByPortInfo(String portInfo) {
		return portNetworks.get(portInfo);
	}

	private Map<String, List<LineNetworkBaseVo>> getPortLnBaseListMap(
			boolean isNetwork) {
		if (isNetwork == true) {
			return this.portNetworks;
		}
		return this.portLines;
	}

	/**
	 * portDescr을 사용하여 값을 검색하여 반환 한다.
	 * 
	 * <pre>
	 * 		1. LIKE 쿼리 조건(레거시 팀스 소스)
	 * 			(	
	 * 				B.A_PORT_DESCR like '' || 'S7.B-P1' || ',%%' OR
	 * 			 	B.B_PORT_DESCR like '' || 'S7.B-P1' || ',%%' OR
	 * 			 	B.A_PORT_DESCR like '' || 'S7.B-P1' || '-%%' OR
	 * 				B.B_PORT_DESCR like '' || 'S7.B-P1' || '-%%' OR
	 * 				(B.A_PORT_DESCR='S7.B-P1' or B.B_PORT_DESCR='S7.B-P1')
	 * 			)
	 * 		2. portDescr 키가 정확히 맞을때 반환 한다.
	 *      2. 데이터가 없을시 LIKE  검색을 조건에 따라 사용
	 *         ㄴ 링,트렁크 조회
	 *         ㄴ 회선 단독 검색시(LOS만)
	 * 
	 * 
	 * </pre>
	 * 
	 * @param portDescr
	 * @param isNetwork
	 * @param isLikeSearch
	 * @return List<LineNetworkBaseVo>
	 */
	public List<LineNetworkBaseVo> getLnBaseListByPortDescr(String portDescr,
			boolean isNetwork, boolean isLikeSearch) {

		List<LineNetworkBaseVo> lnBaseList = getPortLnBaseListMap(isNetwork)
				.get(portDescr);

		if (lnBaseList == null) {
			lnBaseList = new ArrayList<LineNetworkBaseVo>();
		}

		// LIKE 검색
		if (isLikeSearch == true) {
			Set<String> keys = getPortLnBaseListMap(isNetwork).keySet();
			for (String key : keys) {
				if (key.startsWith(portDescr + ",")
						|| key.startsWith(portDescr + "-")) {
					lnBaseList.addAll(getPortLnBaseListMap(isNetwork).get(key));
				}
			}
		}

		return lnBaseList;
	}

}

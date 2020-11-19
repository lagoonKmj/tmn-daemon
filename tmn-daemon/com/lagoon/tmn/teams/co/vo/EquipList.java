package com.lagoon.tmn.teams.co.vo;

import java.util.ArrayList;

import com.lagoon.tmn.teams.fxms.mo.EquipMo;

/**
 * 데이터베이스에 저장된 장비 목록
 * 
 * @author subkjh(김종훈)
 *
 */
public class EquipList extends ArrayList<EquipMo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746302480639068600L;

	public EquipMo getEquipByTid(String tid) {

		for (EquipMo ne : this) {
			if (tid.equals(ne.getEquipTidVal())) {
				return ne;
			}
		}

		return null;
	}

	public EquipMo getEquipByIp(String ip) {

		for (EquipMo ne : this) {
			if (ip.equals(ne.getEquipIpAddr().equals(ip))) {
				return ne;
			}
		}

		return null;
	}
}

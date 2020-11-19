package com.lagoon.tmn.teams.fxms.dbo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.23 11:08
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OMN25112", comment = "전송망장비온도감시이력")
public class OMN25112 {

	@FxColumn(name = "CLCT_DTM", size = 14, comment = "수집일시")
	private String clctDtm;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "SCARD_NM", size = 50, comment = "SLOT카드명")
	private String scardNm;

	@FxColumn(name = "PORT_NM", size = 100, comment = "포트명")
	private String portNm;

	@FxColumn(name = "TMPR_VAL", size = 15, comment = "온도값")
	private float tmprVal;

	public OMN25112() {
	}

	/**
	 * 수집일시
	 * 
	 * @return 수집일시
	 */
	public String getClctDtm() {
		return clctDtm;
	}

	/**
	 * 장비ID
	 * 
	 * @return 장비ID
	 */
	public String getEquipId() {
		return equipId;
	}

	/**
	 * 포트명
	 * 
	 * @return 포트명
	 */
	public String getPortNm() {
		return portNm;
	}

	/**
	 * SLOT카드명
	 * 
	 * @return SLOT카드명
	 */
	public String getScardNm() {
		return scardNm;
	}

	public float getTmprVal() {
		return tmprVal;
	}

	/**
	 * 수집일시
	 *
	 * @param clctDtm
	 *            수집일시
	 */
	public void setClctDtm(String clctDtm) {
		this.clctDtm = clctDtm;
	}

	/**
	 * 장비ID
	 *
	 * @param equipId
	 *            장비ID
	 */
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	/**
	 * 포트명
	 *
	 * @param portNm
	 *            포트명
	 */
	public void setPortNm(String portNm) {
		this.portNm = portNm;
	}

	/**
	 * SLOT카드명
	 *
	 * @param scardNm
	 *            SLOT카드명
	 */
	public void setScardNm(String scardNm) {
		this.scardNm = scardNm;
	}

	/**
	 * 온도값
	 *
	 * @param tmprVal
	 *            온도값
	 */
	public void setTmprVal(float tmprVal) {
		this.tmprVal = tmprVal;
	}

}

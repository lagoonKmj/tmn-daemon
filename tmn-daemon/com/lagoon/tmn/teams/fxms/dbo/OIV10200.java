package com.lagoon.tmn.teams.fxms.dbo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.12 15:39
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10200", comment = "장비모델기본")
public class OIV10200 {

	public OIV10200() {
	}

	@FxColumn(name = "EQUIP_MDL_CD", size = 10, comment = "장비모델코드")
	private String equipMdlCd;

	@FxColumn(name = "EQUIP_MFACT_CD", size = 3, comment = "장비제조사코드")
	private String equipMfactCd;

	@FxColumn(name = "EQUIP_MDL_NM", size = 300, nullable = true, comment = "장비모델명")
	private String equipMdlNm;

	@FxColumn(name = "EQUIP_MDL_TYP_CD", size = 3, comment = "장비모델종류코드")
	private String equipMdlTypCd;

	@FxColumn(name = "EQUIP_TYP_CD", size = 4, comment = "장비종류코드")
	private String equipTypCd;

	@FxColumn(name = "CONS_ITM_MANL_RGST_YN", size = 1, comment = "구성품수동등록여부", defValue = "'N'")
	private String consItmManlRgstYn = "N";

	@FxColumn(name = "CLCT_MTHD_CL_CD", size = 2, nullable = true, comment = "수집방식구분코드", defValue = "'00'")
	private String clctMthdClCd = "00";

	/**
	 * 장비모델코드
	 * 
	 * @return 장비모델코드
	 */
	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	/**
	 * 장비모델코드
	 *
	 * @param equipMdlCd
	 *            장비모델코드
	 */
	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	/**
	 * 장비제조사코드
	 * 
	 * @return 장비제조사코드
	 */
	public String getEquipMfactCd() {
		return equipMfactCd;
	}

	/**
	 * 장비제조사코드
	 *
	 * @param equipMfactCd
	 *            장비제조사코드
	 */
	public void setEquipMfactCd(String equipMfactCd) {
		this.equipMfactCd = equipMfactCd;
	}

	/**
	 * 장비모델명
	 * 
	 * @return 장비모델명
	 */
	public String getEquipMdlNm() {
		return equipMdlNm;
	}

	/**
	 * 장비모델명
	 *
	 * @param equipMdlNm
	 *            장비모델명
	 */
	public void setEquipMdlNm(String equipMdlNm) {
		this.equipMdlNm = equipMdlNm;
	}

	/**
	 * 장비모델종류코드
	 * 
	 * @return 장비모델종류코드
	 */
	public String getEquipMdlTypCd() {
		return equipMdlTypCd;
	}

	/**
	 * 장비모델종류코드
	 *
	 * @param equipMdlTypCd
	 *            장비모델종류코드
	 */
	public void setEquipMdlTypCd(String equipMdlTypCd) {
		this.equipMdlTypCd = equipMdlTypCd;
	}

	/**
	 * 장비종류코드
	 * 
	 * @return 장비종류코드
	 */
	public String getEquipTypCd() {
		return equipTypCd;
	}

	/**
	 * 장비종류코드
	 *
	 * @param equipTypCd
	 *            장비종류코드
	 */
	public void setEquipTypCd(String equipTypCd) {
		this.equipTypCd = equipTypCd;
	}

	/**
	 * 구성품수동등록여부
	 * 
	 * @return 구성품수동등록여부
	 */
	public String isConsItmManlRgstYn() {
		return consItmManlRgstYn;
	}

	/**
	 * 구성품수동등록여부
	 *
	 * @param consItmManlRgstYn
	 *            구성품수동등록여부
	 */
	public void setConsItmManlRgstYn(String consItmManlRgstYn) {
		this.consItmManlRgstYn = consItmManlRgstYn;
	}

	/**
	 * 수집방식구분코드
	 * 
	 * @return 수집방식구분코드
	 */
	public String getClctMthdClCd() {
		return clctMthdClCd;
	}

	/**
	 * 수집방식구분코드
	 *
	 * @param clctMthdClCd
	 *            수집방식구분코드
	 */
	public void setClctMthdClCd(String clctMthdClCd) {
		this.clctMthdClCd = clctMthdClCd;
	}
}

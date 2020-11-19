package com.lagoon.tmn.teams.co.vo;

public class PsVo {

	/* ****************************************************************************************** */
	/* 성능코드 */
	/* ****************************************************************************************** */
	public static final String PSCODE_OPT_LEVEL = "O";
	/** 광레벨최소값 */
	public static final String PSCODE_OPT_LEVEL_MIN = PSCODE_OPT_LEVEL + "MIN";
	/** 광레벨최대값 */
	public static final String PSCODE_OPT_LEVEL_MAX = PSCODE_OPT_LEVEL + "MAX";
	/** 광레벨평균값 */
	public static final String PSCODE_OPT_LEVEL_AVG = PSCODE_OPT_LEVEL + "AVG";
	/** 광레벨현재값 */
	public static final String PSCODE_OPT_LEVEL_CURR = PSCODE_OPT_LEVEL + "CURR";
	/** 모듈온도값 */
	public static final String PSCODE_TEMPERRATURE = "TEMP";

	private String equipId;
	private String cardNm;
	private String portDesc;
	private String instance;

	private String psCode;
	private float psValue;

	public PsVo() {

	}

	/**
	 * 
	 * @param equipId
	 * @param cardNm
	 * @param portDesc
	 * @param instance
	 * @param psCode
	 * @param psValue
	 */
	public PsVo(String equipId, String cardNm, String portDesc, String instance, String psCode, float psValue) {
		this.equipId = equipId;
		this.cardNm = cardNm;
		this.portDesc = portDesc;
		this.instance = instance;
		this.psCode = psCode;
		this.psValue = psValue;
	}

	public String getEquipId() {
		return equipId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public String getCardNm() {
		return cardNm;
	}

	public void setCardNm(String cardNm) {
		this.cardNm = cardNm;
	}

	public String getPortDesc() {
		return portDesc;
	}

	public void setPortDesc(String portDesc) {
		this.portDesc = portDesc;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public float getPsValue() {
		return psValue;
	}

	public void setPsValue(float psValue) {
		this.psValue = psValue;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

}

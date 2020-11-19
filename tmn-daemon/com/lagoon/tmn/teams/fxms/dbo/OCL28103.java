package com.lagoon.tmn.teams.fxms.dbo;

import java.sql.Timestamp;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.10.24 18:04
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OCL28103", comment = "전송장비CPU클럭수집내역")
public class OCL28103 {

	public OCL28103() {
	}

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;
	
	@FxColumn(name = "INSP_SER_NUM", size = 15, comment = "점검일련번호")
	private Long inspSerNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "EQUIP_CLK_MODE_NM", size = 50, comment = "장비클럭모드명")
	private String equipClkModeNm;

	@FxColumn(name = "CURR_EQUIP_CLK_VAL", size = 30, comment = "현재장비클럭값")
	private String currEquipClkVal;

	@FxColumn(name = "STA_DTM", size = 14, nullable = true, comment = "시작일시")
	private String staDtm;

	@FxColumn(name = "EQUIP_MGMT_MODE_NM", size = 30, nullable = true, comment = "장비운용모드명")
	private String equipMgmtModeNm;

	@FxColumn(name = "EQUIP_CLK_VAL1", size = 30, nullable = true, comment = "장비클럭값1")
	private String equipClkVal1;

	@FxColumn(name = "EQUIP_CLK_VAL2", size = 30, nullable = true, comment = "장비클럭값2")
	private String equipClkVal2;

	@FxColumn(name = "EQUIP_CLK_VAL3", size = 30, nullable = true, comment = "장비클럭값3")
	private String equipClkVal3;

	@FxColumn(name = "EQUIP_CLK_VAL4", size = 30, nullable = true, comment = "장비클럭값4")
	private String equipClkVal4;

	@FxColumn(name = "EQUIP_CLK_VAL5", size = 30, nullable = true, comment = "장비클럭값5")
	private String equipClkVal5;

	@FxColumn(name = "EQUIP_CLK_VAL6", size = 30, nullable = true, comment = "장비클럭값6")
	private String equipClkVal6;

	@FxColumn(name = "EQUIP_CLK_VAL7", size = 30, nullable = true, comment = "장비클럭값7")
	private String equipClkVal7;

	@FxColumn(name = "EQUIP_CLK_VAL8", size = 30, nullable = true, comment = "장비클럭값8")
	private String equipClkVal8;

	/**
	 * 장비ID
	 * 
	 * @return 장비ID
	 */
	public String getEquipId() {
		return equipId;
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
	 * 최종변경자ID
	 * 
	 * @return 최종변경자ID
	 */
	public String getAuditId() {
		return auditId;
	}

	/**
	 * 최종변경자ID
	 *
	 * @param auditId
	 *            최종변경자ID
	 */
	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	/**
	 * 최종변경일시
	 * 
	 * @return 최종변경일시
	 */
	public Timestamp getAuditDtm() {
		return auditDtm;
	}

	/**
	 * 최종변경일시
	 *
	 * @param auditDtm
	 *            최종변경일시
	 */
	public void setAuditDtm(Timestamp auditDtm) {
		this.auditDtm = auditDtm;
	}

	/**
	 * 장비클럭모드명
	 * 
	 * @return 장비클럭모드명
	 */
	public String getEquipClkModeNm() {
		return equipClkModeNm;
	}

	/**
	 * 장비클럭모드명
	 *
	 * @param equipClkModeNm
	 *            장비클럭모드명
	 */
	public void setEquipClkModeNm(String equipClkModeNm) {
		this.equipClkModeNm = equipClkModeNm;
	}

	/**
	 * 현재장비클럭값
	 * 
	 * @return 현재장비클럭값
	 */
	public String getCurrEquipClkVal() {
		return currEquipClkVal;
	}

	/**
	 * 현재장비클럭값
	 *
	 * @param currEquipClkVal
	 *            현재장비클럭값
	 */
	public void setCurrEquipClkVal(String currEquipClkVal) {
		this.currEquipClkVal = currEquipClkVal;
	}

	/**
	 * 시작일시
	 * 
	 * @return 시작일시
	 */
	public String getStaDtm() {
		return staDtm;
	}

	/**
	 * 시작일시
	 *
	 * @param staDtm
	 *            시작일시
	 */
	public void setStaDtm(String staDtm) {
		this.staDtm = staDtm;
	}

	/**
	 * 장비운용모드명
	 * 
	 * @return 장비운용모드명
	 */
	public String getEquipMgmtModeNm() {
		return equipMgmtModeNm;
	}

	/**
	 * 장비운용모드명
	 *
	 * @param equipMgmtModeNm
	 *            장비운용모드명
	 */
	public void setEquipMgmtModeNm(String equipMgmtModeNm) {
		this.equipMgmtModeNm = equipMgmtModeNm;
	}

	/**
	 * 장비클럭값1
	 * 
	 * @return 장비클럭값1
	 */
	public String getEquipClkVal1() {
		return equipClkVal1;
	}

	/**
	 * 장비클럭값1
	 *
	 * @param equipClkVal1
	 *            장비클럭값1
	 */
	public void setEquipClkVal1(String equipClkVal1) {
		this.equipClkVal1 = equipClkVal1;
	}

	/**
	 * 장비클럭값2
	 * 
	 * @return 장비클럭값2
	 */
	public String getEquipClkVal2() {
		return equipClkVal2;
	}

	/**
	 * 장비클럭값2
	 *
	 * @param equipClkVal2
	 *            장비클럭값2
	 */
	public void setEquipClkVal2(String equipClkVal2) {
		this.equipClkVal2 = equipClkVal2;
	}

	/**
	 * 장비클럭값3
	 * 
	 * @return 장비클럭값3
	 */
	public String getEquipClkVal3() {
		return equipClkVal3;
	}

	/**
	 * 장비클럭값3
	 *
	 * @param equipClkVal3
	 *            장비클럭값3
	 */
	public void setEquipClkVal3(String equipClkVal3) {
		this.equipClkVal3 = equipClkVal3;
	}

	/**
	 * 장비클럭값4
	 * 
	 * @return 장비클럭값4
	 */
	public String getEquipClkVal4() {
		return equipClkVal4;
	}

	/**
	 * 장비클럭값4
	 *
	 * @param equipClkVal4
	 *            장비클럭값4
	 */
	public void setEquipClkVal4(String equipClkVal4) {
		this.equipClkVal4 = equipClkVal4;
	}

	/**
	 * 장비클럭값5
	 * 
	 * @return 장비클럭값5
	 */
	public String getEquipClkVal5() {
		return equipClkVal5;
	}

	/**
	 * 장비클럭값5
	 *
	 * @param equipClkVal5
	 *            장비클럭값5
	 */
	public void setEquipClkVal5(String equipClkVal5) {
		this.equipClkVal5 = equipClkVal5;
	}

	/**
	 * 장비클럭값6
	 * 
	 * @return 장비클럭값6
	 */
	public String getEquipClkVal6() {
		return equipClkVal6;
	}

	/**
	 * 장비클럭값6
	 *
	 * @param equipClkVal6
	 *            장비클럭값6
	 */
	public void setEquipClkVal6(String equipClkVal6) {
		this.equipClkVal6 = equipClkVal6;
	}

	/**
	 * 장비클럭값7
	 * 
	 * @return 장비클럭값7
	 */
	public String getEquipClkVal7() {
		return equipClkVal7;
	}

	/**
	 * 장비클럭값7
	 *
	 * @param equipClkVal7
	 *            장비클럭값7
	 */
	public void setEquipClkVal7(String equipClkVal7) {
		this.equipClkVal7 = equipClkVal7;
	}

	/**
	 * 장비클럭값8
	 * 
	 * @return 장비클럭값8
	 */
	public String getEquipClkVal8() {
		return equipClkVal8;
	}

	/**
	 * 장비클럭값8
	 *
	 * @param equipClkVal8
	 *            장비클럭값8
	 */
	public void setEquipClkVal8(String equipClkVal8) {
		this.equipClkVal8 = equipClkVal8;
	}
	
	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public Long getInspSerNum() {
		return inspSerNum;
	}

	/**
	 * 점검일련번호
	 *
	 * @param abnCnt
	 *            점검일련번호
	 */
	public void setInspSerNum(Long inspSerNum) {
		this.inspSerNum = inspSerNum;
	}
}

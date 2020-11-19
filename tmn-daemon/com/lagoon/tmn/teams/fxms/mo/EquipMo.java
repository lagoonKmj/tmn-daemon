package com.lagoon.tmn.teams.fxms.mo;

import fxms.bas.mo.Mo;

@SuppressWarnings("serial")
public class EquipMo extends Mo {

	private String equipId; /* 장비ID */
	private String equipIpAddr; /* 장비IP주소 */
	private String equipTidVal; /* 장비 TID 값 */
	private int dcnNum;
	private String swVerInfo; /* SW버전정보 */
	private String equipMdlCd; /* 장비모델코드 */

	private String tpoCd; /* 국사코드 */
	private String tpoNm; /* 국사명 */
	private String equipMdlNm; /* 장비모델명 */

	public String getTpoCd() {
		return tpoCd;
	}

	public void setTpoCd(String tpoCd) {
		this.tpoCd = tpoCd;
	}

	public String getTpoNm() {
		return tpoNm;
	}

	public void setTpoNm(String tpoNm) {
		this.tpoNm = tpoNm;
	}

	public String getEquipMdlNm() {
		return equipMdlNm;
	}

	public void setEquipMdlNm(String equipMdlNm) {
		this.equipMdlNm = equipMdlNm;
	}

	public String getSwVerInfo() {
		return swVerInfo;
	}

	public void setSwVerInfo(String swVerInfo) {
		this.swVerInfo = swVerInfo;
	}

	public int getDcnNum() {
		return dcnNum;
	}

	public String getEquipId() {
		return equipId;
	}

	public String getEquipIpAddr() {
		return equipIpAddr;
	}

	public String getEquipTidVal() {
		return equipTidVal;
	}

	public void setDcnNum(int dcnNum) {
		this.dcnNum = dcnNum;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public void setEquipIpAddr(String equipIpAddr) {
		this.equipIpAddr = equipIpAddr;
	}

	public void setEquipTidVal(String equipTidVal) {
		this.equipTidVal = equipTidVal;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	@Override
	public String toString() {
		return "EquipMo [equipId=" + equipId + ", equipIpAddr=" + equipIpAddr
				+ ", equipTidVal=" + equipTidVal + ", dcnNum=" + dcnNum
				+ ", swVerInfo=" + swVerInfo + ", equipMdlCd=" + equipMdlCd
				+ ", tpoCd=" + tpoCd + ", tpoNm=" + tpoNm + ", equipMdlNm="
				+ equipMdlNm + "]";
	}

}

package com.lagoon.tmn.teams.co.vo;

/**
 * 회선 검증용 국사 정보
 * @author subkjh(김종훈)
 *
 */
public class LineVerifyTpoVo {
	
	private String tpoCd;
	private String tpoNm;
	private String lineNum;

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

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

}

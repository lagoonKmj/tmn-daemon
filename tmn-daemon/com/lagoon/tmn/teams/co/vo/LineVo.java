package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * 회선 선번 VO (OIV10612)
 * 
 * <pre>
 * SqlUtil 클래스 이용하여 자동 생성
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@FxTable(name = "OIV10612", comment = "회선선번")
@SuppressWarnings("serial")
public class LineVo extends LineNetworkBaseVo implements Serializable {

	//  OIV10611 에 있는 코드 필드
	@FxColumn(name = "TRMS_NET_LINE_USG_CD", size = 2, comment = "전송망회선용도코드")
	private String trmsNetLineUsgCd;
	
	/**
	 * 전송망회선용도코드
	 * 
	 * @return 전송망회선용도코드
	 */
	public String getTrmsNetLineUsgCd() {
		return trmsNetLineUsgCd;
	}

	/**
	 * 전송망회선용도코드
	 *
	 * @param trmsNetLineUsgCd
	 *            전송망회선용도코드
	 */
	public void setTrmsNetLineUsgCd(String trmsNetLineUsgCd) {
		this.trmsNetLineUsgCd = trmsNetLineUsgCd;
	}
}

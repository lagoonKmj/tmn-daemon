package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;

import com.lagoon.tmn.teams.co.AdamsCfg.TrmsNetTopoTypCd;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * 네트워크 선번 VO (OIV10615)
 * 
 * <pre>
 * SqlUtil 클래스 이용하여 자동 생성
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@SuppressWarnings("serial")
@FxTable(name = "OIV10615", comment = "전송망회선선번내역")
public class NetworkVo extends LineNetworkBaseVo implements Serializable {

	@FxColumn(name = "RING_COT_RT_INFO", size = 5, comment = "링COTRT정보")
	private String ringCotRtInfo;

	/**
	 * 링COTRT정보
	 * 
	 * @return 링COTRT정보
	 */
	public String getRingCotRtInfo() {
		return ringCotRtInfo;
	}

	/**
	 * 링COTRT정보
	 *
	 * @param ringCotRtInfo
	 *            링COTRT정보
	 */
	public void setRingCotRtInfo(String ringCotRtInfo) {
		this.ringCotRtInfo = ringCotRtInfo;
	}

	/**
	 * 전송망 토폴로지 유형 코드
	 */
	private String trmsNetTopoTypCd = "";

	public String getTrmsNetTopoTypCd() {
		return trmsNetTopoTypCd;
	}

	public void setTrmsNetTopoTypCd(String trmsNetTopoTypCd) {
		this.trmsNetTopoTypCd = trmsNetTopoTypCd;
	}

	public boolean isRingType() {
		if (!trmsNetTopoTypCd.equals(TrmsNetTopoTypCd.TRUNK)) {
			return true;
		}
		return false;
	}

	public boolean isTrunkType() {
		if (trmsNetTopoTypCd.equals(TrmsNetTopoTypCd.TRUNK)) {
			return true;
		}
		return false;
	}

}

package com.lagoon.tmn.tmn.vo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.11.18 16:00
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10615", comment = "네트워크선번")
public class OIV10615 {

	public OIV10615() {
	}

	@FxColumn(name = "NET_NUM", size = 12, comment = "망번호")
	private String netNum;

	@FxColumn(name = "NET_LINK_SEQ1", size = 10, comment = "망연결순번1")
	private Long netLinkSeq1;

	@FxColumn(name = "NET_LINK_SEQ2", size = 10, comment = "망연결순번2")
	private Long netLinkSeq2;

	@FxColumn(name = "RING_NUM", size = 10, nullable = true, comment = "링번호")
	private String ringNum;

	@FxColumn(name = "EQUIP_ID", size = 12, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_A_PORT_INFO", size = 100, nullable = true, comment = "장비A포트정보")
	private String equipAPortInfo;

	@FxColumn(name = "EQUIP_B_PORT_INFO", size = 100, nullable = true, comment = "장비B포트정보")
	private String equipBPortInfo;

	@FxColumn(name = "RING_DIRN_NUM", size = 3, nullable = true, comment = "링방향번호")
	private Integer ringDirnNum;

	@FxColumn(name = "RING_COT_RT_INFO", size = 5, comment = "링COTRT정보")
	private String ringCotRtInfo;

	@FxColumn(name = "CHGR_INFO", size = 20, nullable = true, comment = "변경자정보")
	private String chgrInfo;

	@FxColumn(name = "CHG_DTM", size = 14, nullable = true, comment = "변경일시")
	private String chgDtm;

	@FxColumn(name = "A_PORT_INFO", size = 100, nullable = true, comment = "A포트정보")
	private String aPortInfo;

	@FxColumn(name = "A_CHNL_INFO", size = 100, nullable = true, comment = "A채널정보")
	private String aChnlInfo;

	@FxColumn(name = "B_PORT_INFO", size = 100, nullable = true, comment = "B포트정보")
	private String bPortInfo;

	@FxColumn(name = "B_CHNL_INFO", size = 100, nullable = true, comment = "B채널정보")
	private String bChnlInfo;

	@FxColumn(name = "NET_PATH_DISP_YN", size = 1, comment = "망경로표시여부", defValue = "'N'")
	private String netPathDispYn = "N";

	@FxColumn(name = "TRUNK_DIRN_NUM", size = 3, nullable = true, comment = "TRUNK방향번호")
	private Integer trunkDirnNum;

	@FxColumn(name = "PORT_CHG_YN", size = 1, comment = "포트변경여부")
	private String portChgYn;

	@FxColumn(name = "BF_A_PORT_INFO", size = 100, nullable = true, comment = "이전A포트정보")
	private String bfAPortInfo;

	@FxColumn(name = "BF_B_PORT_INFO", size = 100, nullable = true, comment = "이전B포트정보")
	private String bfBPortInfo;

	@FxColumn(name = "A_PORT_IDLE_RT", size = 15, nullable = true, comment = "A포트유휴율")
	private Double aPortIdleRt;

	@FxColumn(name = "B_PORT_IDLE_RT", size = 15, nullable = true, comment = "B포트유휴율")
	private Double bPortIdleRt;

	@FxColumn(name = "WDM_TRUNK_ID", size = 12, nullable = true, comment = "WDMTRUNKID")
	private String wdmTrunkId;

	@FxColumn(name = "ETC_DESC", size = 200, nullable = true, comment = "기타설명")
	private String etcDesc;

	@FxColumn(name = "LINK_INS_DTM", size = 14, nullable = true, comment = "링크입력일시")
	private String linkInsDtm;

	@FxColumn(name = "LINK_RGSTR_INFO", size = 20, nullable = true, comment = "링크등록자정보")
	private String linkRgstrInfo;

	@FxColumn(name = "SKT_EQUIP_NM", size = 100, nullable = true, comment = "SKT장비명")
	private String sktEquipNm;

	@FxColumn(name = "SKT_EQUIP_NUM", size = 32, nullable = true, comment = "SKT장비번호")
	private Long sktEquipNum;

	@FxColumn(name = "TRUNK_NUM", size = 12, nullable = true, comment = "TRUNK번호")
	private String trunkNum;

	@FxColumn(name = "BBNT_RNG_CL_CD", size = 3, nullable = true, comment = "기간망구간분류코드")
	private String bbntRngClCd;

	/**
	 * 망번호
	 * 
	 * @return 망번호
	 */
	public String getNetNum() {
		return netNum;
	}

	/**
	 * 망번호
	 *
	 * @param netNum
	 *            망번호
	 */
	public void setNetNum(String netNum) {
		this.netNum = netNum;
	}

	/**
	 * 망연결순번1
	 * 
	 * @return 망연결순번1
	 */
	public Long getNetLinkSeq1() {
		return netLinkSeq1;
	}

	/**
	 * 망연결순번1
	 *
	 * @param netLinkSeq1
	 *            망연결순번1
	 */
	public void setNetLinkSeq1(Long netLinkSeq1) {
		this.netLinkSeq1 = netLinkSeq1;
	}

	/**
	 * 망연결순번2
	 * 
	 * @return 망연결순번2
	 */
	public Long getNetLinkSeq2() {
		return netLinkSeq2;
	}

	/**
	 * 망연결순번2
	 *
	 * @param netLinkSeq2
	 *            망연결순번2
	 */
	public void setNetLinkSeq2(Long netLinkSeq2) {
		this.netLinkSeq2 = netLinkSeq2;
	}

	/**
	 * 링번호
	 * 
	 * @return 링번호
	 */
	public String getRingNum() {
		return ringNum;
	}

	/**
	 * 링번호
	 *
	 * @param ringNum
	 *            링번호
	 */
	public void setRingNum(String ringNum) {
		this.ringNum = ringNum;
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
	 * 장비ID
	 *
	 * @param equipId
	 *            장비ID
	 */
	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	/**
	 * 장비A포트정보
	 * 
	 * @return 장비A포트정보
	 */
	public String getEquipAPortInfo() {
		return equipAPortInfo;
	}

	/**
	 * 장비A포트정보
	 *
	 * @param equipAPortInfo
	 *            장비A포트정보
	 */
	public void setEquipAPortInfo(String equipAPortInfo) {
		this.equipAPortInfo = equipAPortInfo;
	}

	/**
	 * 장비B포트정보
	 * 
	 * @return 장비B포트정보
	 */
	public String getEquipBPortInfo() {
		return equipBPortInfo;
	}

	/**
	 * 장비B포트정보
	 *
	 * @param equipBPortInfo
	 *            장비B포트정보
	 */
	public void setEquipBPortInfo(String equipBPortInfo) {
		this.equipBPortInfo = equipBPortInfo;
	}

	/**
	 * 링방향번호
	 * 
	 * @return 링방향번호
	 */
	public Integer getRingDirnNum() {
		return ringDirnNum;
	}

	/**
	 * 링방향번호
	 *
	 * @param ringDirnNum
	 *            링방향번호
	 */
	public void setRingDirnNum(Integer ringDirnNum) {
		this.ringDirnNum = ringDirnNum;
	}

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
	 * 변경자정보
	 * 
	 * @return 변경자정보
	 */
	public String getChgrInfo() {
		return chgrInfo;
	}

	/**
	 * 변경자정보
	 *
	 * @param chgrInfo
	 *            변경자정보
	 */
	public void setChgrInfo(String chgrInfo) {
		this.chgrInfo = chgrInfo;
	}

	/**
	 * 변경일시
	 * 
	 * @return 변경일시
	 */
	public String getChgDtm() {
		return chgDtm;
	}

	/**
	 * 변경일시
	 *
	 * @param chgDtm
	 *            변경일시
	 */
	public void setChgDtm(String chgDtm) {
		this.chgDtm = chgDtm;
	}

	/**
	 * A포트정보
	 * 
	 * @return A포트정보
	 */
	public String getAPortInfo() {
		return aPortInfo;
	}

	/**
	 * A포트정보
	 *
	 * @param aPortInfo
	 *            A포트정보
	 */
	public void setAPortInfo(String aPortInfo) {
		this.aPortInfo = aPortInfo;
	}

	/**
	 * A채널정보
	 * 
	 * @return A채널정보
	 */
	public String getAChnlInfo() {
		return aChnlInfo;
	}

	/**
	 * A채널정보
	 *
	 * @param aChnlInfo
	 *            A채널정보
	 */
	public void setAChnlInfo(String aChnlInfo) {
		this.aChnlInfo = aChnlInfo;
	}

	/**
	 * B포트정보
	 * 
	 * @return B포트정보
	 */
	public String getBPortInfo() {
		return bPortInfo;
	}

	/**
	 * B포트정보
	 *
	 * @param bPortInfo
	 *            B포트정보
	 */
	public void setBPortInfo(String bPortInfo) {
		this.bPortInfo = bPortInfo;
	}

	/**
	 * B채널정보
	 * 
	 * @return B채널정보
	 */
	public String getBChnlInfo() {
		return bChnlInfo;
	}

	/**
	 * B채널정보
	 *
	 * @param bChnlInfo
	 *            B채널정보
	 */
	public void setBChnlInfo(String bChnlInfo) {
		this.bChnlInfo = bChnlInfo;
	}

	/**
	 * 망경로표시여부
	 * 
	 * @return 망경로표시여부
	 */
	public String isNetPathDispYn() {
		return netPathDispYn;
	}

	/**
	 * 망경로표시여부
	 *
	 * @param netPathDispYn
	 *            망경로표시여부
	 */
	public void setNetPathDispYn(String netPathDispYn) {
		this.netPathDispYn = netPathDispYn;
	}

	/**
	 * TRUNK방향번호
	 * 
	 * @return TRUNK방향번호
	 */
	public Integer getTrunkDirnNum() {
		return trunkDirnNum;
	}

	/**
	 * TRUNK방향번호
	 *
	 * @param trunkDirnNum
	 *            TRUNK방향번호
	 */
	public void setTrunkDirnNum(Integer trunkDirnNum) {
		this.trunkDirnNum = trunkDirnNum;
	}

	/**
	 * 포트변경여부
	 * 
	 * @return 포트변경여부
	 */
	public String isPortChgYn() {
		return portChgYn;
	}

	/**
	 * 포트변경여부
	 *
	 * @param portChgYn
	 *            포트변경여부
	 */
	public void setPortChgYn(String portChgYn) {
		this.portChgYn = portChgYn;
	}

	/**
	 * 이전A포트정보
	 * 
	 * @return 이전A포트정보
	 */
	public String getBfAPortInfo() {
		return bfAPortInfo;
	}

	/**
	 * 이전A포트정보
	 *
	 * @param bfAPortInfo
	 *            이전A포트정보
	 */
	public void setBfAPortInfo(String bfAPortInfo) {
		this.bfAPortInfo = bfAPortInfo;
	}

	/**
	 * 이전B포트정보
	 * 
	 * @return 이전B포트정보
	 */
	public String getBfBPortInfo() {
		return bfBPortInfo;
	}

	/**
	 * 이전B포트정보
	 *
	 * @param bfBPortInfo
	 *            이전B포트정보
	 */
	public void setBfBPortInfo(String bfBPortInfo) {
		this.bfBPortInfo = bfBPortInfo;
	}

	/**
	 * A포트유휴율
	 * 
	 * @return A포트유휴율
	 */
	public Double getAPortIdleRt() {
		return aPortIdleRt;
	}

	/**
	 * A포트유휴율
	 *
	 * @param aPortIdleRt
	 *            A포트유휴율
	 */
	public void setAPortIdleRt(Double aPortIdleRt) {
		this.aPortIdleRt = aPortIdleRt;
	}

	/**
	 * B포트유휴율
	 * 
	 * @return B포트유휴율
	 */
	public Double getBPortIdleRt() {
		return bPortIdleRt;
	}

	/**
	 * B포트유휴율
	 *
	 * @param bPortIdleRt
	 *            B포트유휴율
	 */
	public void setBPortIdleRt(Double bPortIdleRt) {
		this.bPortIdleRt = bPortIdleRt;
	}

	/**
	 * WDMTRUNKID
	 * 
	 * @return WDMTRUNKID
	 */
	public String getWdmTrunkId() {
		return wdmTrunkId;
	}

	/**
	 * WDMTRUNKID
	 *
	 * @param wdmTrunkId
	 *            WDMTRUNKID
	 */
	public void setWdmTrunkId(String wdmTrunkId) {
		this.wdmTrunkId = wdmTrunkId;
	}

	/**
	 * 기타설명
	 * 
	 * @return 기타설명
	 */
	public String getEtcDesc() {
		return etcDesc;
	}

	/**
	 * 기타설명
	 *
	 * @param etcDesc
	 *            기타설명
	 */
	public void setEtcDesc(String etcDesc) {
		this.etcDesc = etcDesc;
	}

	/**
	 * 링크입력일시
	 * 
	 * @return 링크입력일시
	 */
	public String getLinkInsDtm() {
		return linkInsDtm;
	}

	/**
	 * 링크입력일시
	 *
	 * @param linkInsDtm
	 *            링크입력일시
	 */
	public void setLinkInsDtm(String linkInsDtm) {
		this.linkInsDtm = linkInsDtm;
	}

	/**
	 * 링크등록자정보
	 * 
	 * @return 링크등록자정보
	 */
	public String getLinkRgstrInfo() {
		return linkRgstrInfo;
	}

	/**
	 * 링크등록자정보
	 *
	 * @param linkRgstrInfo
	 *            링크등록자정보
	 */
	public void setLinkRgstrInfo(String linkRgstrInfo) {
		this.linkRgstrInfo = linkRgstrInfo;
	}

	/**
	 * SKT장비명
	 * 
	 * @return SKT장비명
	 */
	public String getSktEquipNm() {
		return sktEquipNm;
	}

	/**
	 * SKT장비명
	 *
	 * @param sktEquipNm
	 *            SKT장비명
	 */
	public void setSktEquipNm(String sktEquipNm) {
		this.sktEquipNm = sktEquipNm;
	}

	/**
	 * SKT장비번호
	 * 
	 * @return SKT장비번호
	 */
	public Long getSktEquipNum() {
		return sktEquipNum;
	}

	/**
	 * SKT장비번호
	 *
	 * @param sktEquipNum
	 *            SKT장비번호
	 */
	public void setSktEquipNum(Long sktEquipNum) {
		this.sktEquipNum = sktEquipNum;
	}

	/**
	 * TRUNK번호
	 * 
	 * @return TRUNK번호
	 */
	public String getTrunkNum() {
		return trunkNum;
	}

	/**
	 * TRUNK번호
	 *
	 * @param trunkNum
	 *            TRUNK번호
	 */
	public void setTrunkNum(String trunkNum) {
		this.trunkNum = trunkNum;
	}

	/**
	 * 기간망구간분류코드
	 * 
	 * @return 기간망구간분류코드
	 */
	public String getBbntRngClCd() {
		return bbntRngClCd;
	}

	/**
	 * 기간망구간분류코드
	 *
	 * @param bbntRngClCd
	 *            기간망구간분류코드
	 */
	public void setBbntRngClCd(String bbntRngClCd) {
		this.bbntRngClCd = bbntRngClCd;
	}
}

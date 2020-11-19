package com.lagoon.tmn.teams.co.vo;

import subkjh.bas.fxdao.define.FxColumn;

/**
 * 분석에 필요한 데이터를 경량화 한 VO
 * 
 * <pre>
 * 		회선 선번 VO (OIV10612), 네트워크 선번 VO (OIV10615) 의 공통적인 필드를 정의
 * 		단, LINE_NUM 과 NET_NUM 은 새로운 키를 만들지 않고 따로 관리 한다. 
 *      (새로운 키[공통] 를 생성하면 혼돈 문제 때문)
 * 
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
public class LineNetworkBaseVo {

	@FxColumn(name = "LINE_NUM", size = 12, comment = "회선번호")
	private String lineNum;
	
	@FxColumn(name = "NET_NUM", size = 12, comment = "망번호")
	private String netNum;

	@FxColumn(name = "NET_LINK_SEQ1", size = 10, comment = "망연결순번1")
	private long netLinkSeq1;

	@FxColumn(name = "NET_LINK_SEQ2", size = 10, comment = "망연결순번2")
	private long netLinkSeq2;

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

	@FxColumn(name = "A_PORT_INFO", size = 100, nullable = true, comment = "A포트정보")
	private String aPortInfo;

	@FxColumn(name = "A_CHNL_INFO", size = 100, nullable = true, comment = "A채널정보")
	private String aChnlInfo;

	@FxColumn(name = "B_PORT_INFO", size = 100, nullable = true, comment = "B포트정보")
	private String bPortInfo;

	@FxColumn(name = "B_CHNL_INFO", size = 100, nullable = true, comment = "B채널정보")
	private String bChnlInfo;

	@FxColumn(name = "TRUNK_NUM", size = 12, nullable = true, comment = "TRUNK번호")
	private String trunkNum;

	/**
	 * 회선번호
	 * 
	 * @return 회선번호
	 */
	public String getLineNum() {
		return lineNum;
	}

	/**
	 * 회선번호
	 *
	 * @param lineNum
	 *            회선번호
	 */
	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}
	
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
	public long getNetLinkSeq1() {
		return netLinkSeq1;
	}

	/**
	 * 망연결순번1
	 *
	 * @param netLinkSeq1
	 *            망연결순번1
	 */
	public void setNetLinkSeq1(long netLinkSeq1) {
		this.netLinkSeq1 = netLinkSeq1;
	}

	/**
	 * 망연결순번2
	 * 
	 * @return 망연결순번2
	 */
	public long getNetLinkSeq2() {
		return netLinkSeq2;
	}

	/**
	 * 망연결순번2
	 *
	 * @param netLinkSeq2
	 *            망연결순번2
	 */
	public void setNetLinkSeq2(long netLinkSeq2) {
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

}

package com.lagoon.tmn.tmn.vo;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.11.18 16:00
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OIV10612", comment = "회선선번")
public class OIV10612 {

	public OIV10612() {
	}

	@FxColumn(name = "LINE_NUM", size = 12, comment = "회선번호")
	private String lineNum;

	@FxColumn(name = "NET_LINK_SEQ1", size = 10, comment = "망연결순번1")
	private Long netLinkSeq1;

	@FxColumn(name = "NET_LINK_SEQ2", size = 10, comment = "망연결순번2")
	private Long netLinkSeq2;

	@FxColumn(name = "RING_NUM", size = 10, nullable = true, comment = "링번호")
	private String ringNum;

	@FxColumn(name = "EQUIP_ID", size = 12, nullable = true, comment = "장비ID")
	private String equipId;

	@FxColumn(name = "EQUIP_A_PORT_INFO", size = 100, nullable = true, comment = "장비A포트정보")
	private String equipAPortInfo;

	@FxColumn(name = "EQUIP_B_PORT_INFO", size = 100, nullable = true, comment = "장비B포트정보")
	private String equipBPortInfo;

	@FxColumn(name = "RING_DIRN_NUM", size = 3, nullable = true, comment = "링방향번호")
	private Integer ringDirnNum;

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

	@FxColumn(name = "LINE_PATH_DISP_YN", size = 1, nullable = true, comment = "회선경로표시여부")
	private String linePathDispYn;

	@FxColumn(name = "TRUNK_GRP_NUM", size = 10, nullable = true, comment = "TRUNK그룹번호")
	private Long trunkGrpNum;

	@FxColumn(name = "TRUNK_DIRN_NUM", size = 3, nullable = true, comment = "TRUNK방향번호")
	private Integer trunkDirnNum;

	@FxColumn(name = "TRUNK_SUB_DIRN_VAL", size = 2, nullable = true, comment = "TRUNK하위방향값")
	private Integer trunkSubDirnVal;

	@FxColumn(name = "RT_INFO", size = 5, nullable = true, comment = "RT정보")
	private String rtInfo;

	@FxColumn(name = "A_PORT_DTL_SEQ", size = 10, nullable = true, comment = "A포트세부순번")
	private Long aPortDtlSeq;

	@FxColumn(name = "B_PORT_DTL_SEQ", size = 10, nullable = true, comment = "B포트세부순번")
	private Long bPortDtlSeq;

	@FxColumn(name = "A_PORT_GRP_INFO", size = 500, nullable = true, comment = "A포트그룹정보")
	private String aPortGrpInfo;

	@FxColumn(name = "B_PORT_GRP_INFO", size = 500, nullable = true, comment = "B포트그룹정보")
	private String bPortGrpInfo;

	@FxColumn(name = "ETC_DESC", size = 200, nullable = true, comment = "기타설명")
	private String etcDesc;

	@FxColumn(name = "WDM_TRUNK_ID", size = 12, nullable = true, comment = "WDMTRUNKID")
	private String wdmTrunkId;

	@FxColumn(name = "WDM_TRUNK_DIRN_NUM", size = 3, nullable = true, comment = "WDMTRUNK방향번호")
	private Integer wdmTrunkDirnNum;

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
	 * 회선경로표시여부
	 * 
	 * @return 회선경로표시여부
	 */
	public String isLinePathDispYn() {
		return linePathDispYn;
	}

	/**
	 * 회선경로표시여부
	 *
	 * @param linePathDispYn
	 *            회선경로표시여부
	 */
	public void setLinePathDispYn(String linePathDispYn) {
		this.linePathDispYn = linePathDispYn;
	}

	/**
	 * TRUNK그룹번호
	 * 
	 * @return TRUNK그룹번호
	 */
	public Long getTrunkGrpNum() {
		return trunkGrpNum;
	}

	/**
	 * TRUNK그룹번호
	 *
	 * @param trunkGrpNum
	 *            TRUNK그룹번호
	 */
	public void setTrunkGrpNum(Long trunkGrpNum) {
		this.trunkGrpNum = trunkGrpNum;
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
	 * TRUNK하위방향값
	 * 
	 * @return TRUNK하위방향값
	 */
	public Integer getTrunkSubDirnVal() {
		return trunkSubDirnVal;
	}

	/**
	 * TRUNK하위방향값
	 *
	 * @param trunkSubDirnVal
	 *            TRUNK하위방향값
	 */
	public void setTrunkSubDirnVal(Integer trunkSubDirnVal) {
		this.trunkSubDirnVal = trunkSubDirnVal;
	}

	/**
	 * RT정보
	 * 
	 * @return RT정보
	 */
	public String getRtInfo() {
		return rtInfo;
	}

	/**
	 * RT정보
	 *
	 * @param rtInfo
	 *            RT정보
	 */
	public void setRtInfo(String rtInfo) {
		this.rtInfo = rtInfo;
	}

	/**
	 * A포트세부순번
	 * 
	 * @return A포트세부순번
	 */
	public Long getAPortDtlSeq() {
		return aPortDtlSeq;
	}

	/**
	 * A포트세부순번
	 *
	 * @param aPortDtlSeq
	 *            A포트세부순번
	 */
	public void setAPortDtlSeq(Long aPortDtlSeq) {
		this.aPortDtlSeq = aPortDtlSeq;
	}

	/**
	 * B포트세부순번
	 * 
	 * @return B포트세부순번
	 */
	public Long getBPortDtlSeq() {
		return bPortDtlSeq;
	}

	/**
	 * B포트세부순번
	 *
	 * @param bPortDtlSeq
	 *            B포트세부순번
	 */
	public void setBPortDtlSeq(Long bPortDtlSeq) {
		this.bPortDtlSeq = bPortDtlSeq;
	}

	/**
	 * A포트그룹정보
	 * 
	 * @return A포트그룹정보
	 */
	public String getAPortGrpInfo() {
		return aPortGrpInfo;
	}

	/**
	 * A포트그룹정보
	 *
	 * @param aPortGrpInfo
	 *            A포트그룹정보
	 */
	public void setAPortGrpInfo(String aPortGrpInfo) {
		this.aPortGrpInfo = aPortGrpInfo;
	}

	/**
	 * B포트그룹정보
	 * 
	 * @return B포트그룹정보
	 */
	public String getBPortGrpInfo() {
		return bPortGrpInfo;
	}

	/**
	 * B포트그룹정보
	 *
	 * @param bPortGrpInfo
	 *            B포트그룹정보
	 */
	public void setBPortGrpInfo(String bPortGrpInfo) {
		this.bPortGrpInfo = bPortGrpInfo;
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
	 * WDMTRUNK방향번호
	 * 
	 * @return WDMTRUNK방향번호
	 */
	public Integer getWdmTrunkDirnNum() {
		return wdmTrunkDirnNum;
	}

	/**
	 * WDMTRUNK방향번호
	 *
	 * @param wdmTrunkDirnNum
	 *            WDMTRUNK방향번호
	 */
	public void setWdmTrunkDirnNum(Integer wdmTrunkDirnNum) {
		this.wdmTrunkDirnNum = wdmTrunkDirnNum;
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
}

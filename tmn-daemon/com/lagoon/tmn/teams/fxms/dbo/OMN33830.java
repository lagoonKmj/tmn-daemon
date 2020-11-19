package com.lagoon.tmn.teams.fxms.dbo;

import java.io.Serializable;
import java.sql.Timestamp;

import subkjh.bas.dao.define.INDEX_TYPE;
import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxIndex;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.07.31 11:15
 * @author subkjh autometic create by subkjh.dao
 *
 */

@SuppressWarnings("serial")
@FxTable(name = "OMN33830", comment = "전송망링장애")
@FxIndex(name = "OMN33830_PK", type = INDEX_TYPE.PK, columns = { "TRMS_RING_DABL_NUM" })
public class OMN33830 implements Serializable {

	public OMN33830() {
	}

	@FxColumn(name = "TRMS_RING_DABL_NUM", size = 15, comment = "전송링장애번호")
	private long trmsRingDablNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "TRMS_EQUIP_DABL_NUM", size = 15, comment = "전송장비장애번호")
	private long trmsEquipDablNum;

	@FxColumn(name = "NET_NUM", size = 12, comment = "망번호")
	private String netNum;

	@FxColumn(name = "NW_NM", size = 100, comment = "NETWORK명")
	private String nwNm;

	@FxColumn(name = "TRMS_NET_TOPO_TYP_CD", size = 4, comment = "전송망토폴로지유형코드")
	private String trmsNetTopoTypCd;

	@FxColumn(name = "TRMS_NET_USG_CD", size = 2, comment = "전송망용도코드")
	private String trmsNetUsgCd;

	@FxColumn(name = "TRMS_NET_EQUIP_CAPA_CL_CD", size = 4, comment = "전송망용량구분코드")
	private String trmsNetEquipCapaClCd;

	@FxColumn(name = "TRUNK_NUM", size = 12, nullable = true, comment = "TRUNK번호")
	private String trunkNum;

	@FxColumn(name = "DABL_SEND_YN", size = 1, comment = "장애송신여부", defValue = "'N'")
	private boolean dablSendYn = false;

	@FxColumn(name = "DABL_SEND_DTM", size = 0, nullable = true, comment = "장애송신일시")
	private Timestamp dablSendDtm;

	@FxColumn(name = "DABL_SEND_RSLT_VAL", size = 1, nullable = true, comment = "장애송신결과값")
	private String dablSendRsltVal;

	@FxColumn(name = "NET_DABL_CD", size = 30, nullable = true, comment = "망장애코드")
	private String netDablCd;

	/**
	 * 전송링장애번호
	 * 
	 * @return 전송링장애번호
	 */
	public long getTrmsRingDablNum() {
		return trmsRingDablNum;
	}

	/**
	 * 전송링장애번호
	 *
	 * @param trmsRingDablNum
	 *            전송링장애번호
	 */
	public void setTrmsRingDablNum(long trmsRingDablNum) {
		this.trmsRingDablNum = trmsRingDablNum;
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
	 * 전송장비장애번호
	 * 
	 * @return 전송장비장애번호
	 */
	public long getTrmsEquipDablNum() {
		return trmsEquipDablNum;
	}

	/**
	 * 전송장비장애번호
	 *
	 * @param trmsEquipDablNum
	 *            전송장비장애번호
	 */
	public void setTrmsEquipDablNum(long trmsEquipDablNum) {
		this.trmsEquipDablNum = trmsEquipDablNum;
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
	 * NETWORK명
	 * 
	 * @return NETWORK명
	 */
	public String getNwNm() {
		return nwNm;
	}

	/**
	 * NETWORK명
	 *
	 * @param nwNm
	 *            NETWORK명
	 */
	public void setNwNm(String nwNm) {
		this.nwNm = nwNm;
	}

	/**
	 * 전송망토폴로지유형코드
	 * 
	 * @return 전송망토폴로지유형코드
	 */
	public String getTrmsNetTopoTypCd() {
		return trmsNetTopoTypCd;
	}

	/**
	 * 전송망토폴로지유형코드
	 *
	 * @param trmsNetTopoTypCd
	 *            전송망토폴로지유형코드
	 */
	public void setTrmsNetTopoTypCd(String trmsNetTopoTypCd) {
		this.trmsNetTopoTypCd = trmsNetTopoTypCd;
	}

	/**
	 * 전송망용도코드
	 * 
	 * @return 전송망용도코드
	 */
	public String getTrmsNetUsgCd() {
		return trmsNetUsgCd;
	}

	/**
	 * 전송망용도코드
	 *
	 * @param trmsNetUsgCd
	 *            전송망용도코드
	 */
	public void setTrmsNetUsgCd(String trmsNetUsgCd) {
		this.trmsNetUsgCd = trmsNetUsgCd;
	}

	/**
	 * 전송망용량구분코드
	 * 
	 * @return 전송망용량구분코드
	 */
	public String getTrmsNetEquipCapaClCd() {
		return trmsNetEquipCapaClCd;
	}

	/**
	 * 전송망용량구분코드
	 *
	 * @param trmsNetEquipCapaClCd
	 *            전송망용량구분코드
	 */
	public void setTrmsNetEquipCapaClCd(String trmsNetEquipCapaClCd) {
		this.trmsNetEquipCapaClCd = trmsNetEquipCapaClCd;
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
	 * 장애송신여부
	 * 
	 * @return 장애송신여부
	 */
	public boolean isDablSendYn() {
		return dablSendYn;
	}

	/**
	 * 장애송신여부
	 *
	 * @param dablSendYn
	 *            장애송신여부
	 */
	public void setDablSendYn(boolean dablSendYn) {
		this.dablSendYn = dablSendYn;
	}

	/**
	 * 장애송신일시
	 * 
	 * @return 장애송신일시
	 */
	public Timestamp getDablSendDtm() {
		return dablSendDtm;
	}

	/**
	 * 장애송신일시
	 *
	 * @param dablSendDtm
	 *            장애송신일시
	 */
	public void setDablSendDtm(Timestamp dablSendDtm) {
		this.dablSendDtm = dablSendDtm;
	}

	/**
	 * 장애송신결과값
	 * 
	 * @return 장애송신결과값
	 */
	public String getDablSendRsltVal() {
		return dablSendRsltVal;
	}

	/**
	 * 장애송신결과값
	 *
	 * @param dablSendRsltVal
	 *            장애송신결과값
	 */
	public void setDablSendRsltVal(String dablSendRsltVal) {
		this.dablSendRsltVal = dablSendRsltVal;
	}

	public String getNetDablCd() {
		return netDablCd;
	}

	public void setNetDablCd(String netDablCd) {
		this.netDablCd = netDablCd;
	}

}

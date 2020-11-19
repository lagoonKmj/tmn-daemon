package com.lagoon.tmn.nws.dbo;

import java.sql.Timestamp;

import com.lagoon.tmn.teams.fxms.dbo.IAudit;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
 * @since 2019.08.09 14:44
 * @author subkjh autometic create by subkjh.dao
 *
 */

@FxTable(name = "OES30307", comment = "SWING건물동목록테이블")
public class OES30307 implements IAudit {

	public OES30307() {
	}

	@FxColumn(name = "BLDBLK_NUM", size = 15, comment = "건물동번호")
	private Long bldblkNum;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "BLDBLK_NM", size = 20, comment = "건물동명")
	private String bldblkNm;

	@FxColumn(name = "BLD_CD", size = 15, comment = "건물코드")
	private String bldCd;

	@FxColumn(name = "RMK", size = 200, nullable = true, comment = "비고")
	private String rmk;

	@FxColumn(name = "UTM_X_CODN_VAL", size = 7, nullable = true, comment = "UTMX좌표값")
	private Integer utmXCodnVal;

	@FxColumn(name = "UTM_Y_CODN_VAL", size = 7, nullable = true, comment = "UTMY좌표값")
	private Integer utmYCodnVal;

	/**
	 * 건물동번호
	 * 
	 * @return 건물동번호
	 */
	public Long getBldblkNum() {
		return bldblkNum;
	}

	/**
	 * 건물동번호
	 *
	 * @param bldblkNum
	 *            건물동번호
	 */
	public void setBldblkNum(Long bldblkNum) {
		this.bldblkNum = bldblkNum;
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
	 * 건물동명
	 * 
	 * @return 건물동명
	 */
	public String getBldblkNm() {
		return bldblkNm;
	}

	/**
	 * 건물동명
	 *
	 * @param bldblkNm
	 *            건물동명
	 */
	public void setBldblkNm(String bldblkNm) {
		this.bldblkNm = bldblkNm;
	}

	/**
	 * 건물코드
	 * 
	 * @return 건물코드
	 */
	public String getBldCd() {
		return bldCd;
	}

	/**
	 * 건물코드
	 *
	 * @param bldCd
	 *            건물코드
	 */
	public void setBldCd(String bldCd) {
		this.bldCd = bldCd;
	}

	/**
	 * 비고
	 * 
	 * @return 비고
	 */
	public String getRmk() {
		return rmk;
	}

	/**
	 * 비고
	 *
	 * @param rmk
	 *            비고
	 */
	public void setRmk(String rmk) {
		this.rmk = rmk;
	}

	/**
	 * UTMX좌표값
	 * 
	 * @return UTMX좌표값
	 */
	public Integer getUtmXCodnVal() {
		return utmXCodnVal;
	}

	/**
	 * UTMX좌표값
	 *
	 * @param utmXCodnVal
	 *            UTMX좌표값
	 */
	public void setUtmXCodnVal(Integer utmXCodnVal) {
		this.utmXCodnVal = utmXCodnVal;
	}

	/**
	 * UTMY좌표값
	 * 
	 * @return UTMY좌표값
	 */
	public Integer getUtmYCodnVal() {
		return utmYCodnVal;
	}

	/**
	 * UTMY좌표값
	 *
	 * @param utmYCodnVal
	 *            UTMY좌표값
	 */
	public void setUtmYCodnVal(Integer utmYCodnVal) {
		this.utmYCodnVal = utmYCodnVal;
	}
}

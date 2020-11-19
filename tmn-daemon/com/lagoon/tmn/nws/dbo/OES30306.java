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

@FxTable(name = "OES30306", comment = "SWING도로명주소테이블")
public class OES30306 implements IAudit {

	public OES30306() {
	}

	@FxColumn(name = "LDONG_CD", size = 10, comment = "법정동코드")
	private String ldongCd;

	@FxColumn(name = "ST_NM_CD", size = 12, comment = "도로명코드")
	private String stNmCd;

	@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
	private String auditId;

	@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
	private Timestamp auditDtm;

	@FxColumn(name = "ST_NM", size = 80, comment = "도로명")
	private String stNm;

	@FxColumn(name = "EFF_STA_DT", size = 8, comment = "유효시작일자")
	private String effStaDt;

	@FxColumn(name = "EFF_END_DT", size = 8, comment = "유효종료일자")
	private String effEndDt;

	@FxColumn(name = "ST_ENG_NM", size = 80, nullable = true, comment = "도로영문명")
	private String stEngNm;

	@FxColumn(name = "UP_MYUN_DONG_SER_NUM", size = 2, nullable = true, comment = "읍면동일련번호")
	private String upMyunDongSerNum;

	@FxColumn(name = "UP_MYUN_DONG_CL_CD", size = 1, comment = "읍면동구분코드")
	private String upMyunDongClCd;

	@FxColumn(name = "SUP_ST_CD", size = 7, nullable = true, comment = "상위도로코드")
	private String supStCd;

	@FxColumn(name = "SUP_ST_NM", size = 80, nullable = true, comment = "상위도로명")
	private String supStNm;

	@FxColumn(name = "ST_NM_CHG_RSN_CD", size = 1, nullable = true, comment = "도로명변경사유코드")
	private String stNmChgRsnCd;

	/**
	 * 법정동코드
	 * 
	 * @return 법정동코드
	 */
	public String getLdongCd() {
		return ldongCd;
	}

	/**
	 * 법정동코드
	 *
	 * @param ldongCd
	 *            법정동코드
	 */
	public void setLdongCd(String ldongCd) {
		this.ldongCd = ldongCd;
	}

	/**
	 * 도로명코드
	 * 
	 * @return 도로명코드
	 */
	public String getStNmCd() {
		return stNmCd;
	}

	/**
	 * 도로명코드
	 *
	 * @param stNmCd
	 *            도로명코드
	 */
	public void setStNmCd(String stNmCd) {
		this.stNmCd = stNmCd;
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
	 * 도로명
	 * 
	 * @return 도로명
	 */
	public String getStNm() {
		return stNm;
	}

	/**
	 * 도로명
	 *
	 * @param stNm
	 *            도로명
	 */
	public void setStNm(String stNm) {
		this.stNm = stNm;
	}

	/**
	 * 유효시작일자
	 * 
	 * @return 유효시작일자
	 */
	public String getEffStaDt() {
		return effStaDt;
	}

	/**
	 * 유효시작일자
	 *
	 * @param effStaDt
	 *            유효시작일자
	 */
	public void setEffStaDt(String effStaDt) {
		this.effStaDt = effStaDt;
	}

	/**
	 * 유효종료일자
	 * 
	 * @return 유효종료일자
	 */
	public String getEffEndDt() {
		return effEndDt;
	}

	/**
	 * 유효종료일자
	 *
	 * @param effEndDt
	 *            유효종료일자
	 */
	public void setEffEndDt(String effEndDt) {
		this.effEndDt = effEndDt;
	}

	/**
	 * 도로영문명
	 * 
	 * @return 도로영문명
	 */
	public String getStEngNm() {
		return stEngNm;
	}

	/**
	 * 도로영문명
	 *
	 * @param stEngNm
	 *            도로영문명
	 */
	public void setStEngNm(String stEngNm) {
		this.stEngNm = stEngNm;
	}

	/**
	 * 읍면동일련번호
	 * 
	 * @return 읍면동일련번호
	 */
	public String getUpMyunDongSerNum() {
		return upMyunDongSerNum;
	}

	/**
	 * 읍면동일련번호
	 *
	 * @param upMyunDongSerNum
	 *            읍면동일련번호
	 */
	public void setUpMyunDongSerNum(String upMyunDongSerNum) {
		this.upMyunDongSerNum = upMyunDongSerNum;
	}

	/**
	 * 읍면동구분코드
	 * 
	 * @return 읍면동구분코드
	 */
	public String getUpMyunDongClCd() {
		return upMyunDongClCd;
	}

	/**
	 * 읍면동구분코드
	 *
	 * @param upMyunDongClCd
	 *            읍면동구분코드
	 */
	public void setUpMyunDongClCd(String upMyunDongClCd) {
		this.upMyunDongClCd = upMyunDongClCd;
	}

	/**
	 * 상위도로코드
	 * 
	 * @return 상위도로코드
	 */
	public String getSupStCd() {
		return supStCd;
	}

	/**
	 * 상위도로코드
	 *
	 * @param supStCd
	 *            상위도로코드
	 */
	public void setSupStCd(String supStCd) {
		this.supStCd = supStCd;
	}

	/**
	 * 상위도로명
	 * 
	 * @return 상위도로명
	 */
	public String getSupStNm() {
		return supStNm;
	}

	/**
	 * 상위도로명
	 *
	 * @param supStNm
	 *            상위도로명
	 */
	public void setSupStNm(String supStNm) {
		this.supStNm = supStNm;
	}

	/**
	 * 도로명변경사유코드
	 * 
	 * @return 도로명변경사유코드
	 */
	public String getStNmChgRsnCd() {
		return stNmChgRsnCd;
	}

	/**
	 * 도로명변경사유코드
	 *
	 * @param stNmChgRsnCd
	 *            도로명변경사유코드
	 */
	public void setStNmChgRsnCd(String stNmChgRsnCd) {
		this.stNmChgRsnCd = stNmChgRsnCd;
	}
}

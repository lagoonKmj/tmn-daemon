package com.lagoon.tmn.nws.dbo;


import java.sql.Timestamp;

import com.lagoon.tmn.teams.fxms.dbo.IAudit;

import subkjh.bas.fxdao.define.FxColumn;
import subkjh.bas.fxdao.define.FxTable;

/**
* @since 2019.08.09 14:44
* @author subkjh 
* autometic create by subkjh.dao 
*
*/


@FxTable(name = "OES10302", comment = "SWING서비스설치주소테이블")
public class OES10302 implements IAudit {

public OES10302() {
 }

@FxColumn(name = "SVC_MGMT_NUM", size = 10, comment = "서비스관리번호")
private Long svcMgmtNum;


@FxColumn(name = "PROD_ID", size = 10, comment = "상품ID")
private String prodId;


@FxColumn(name = "EFF_STA_DTM", size = 14, comment = "유효시작일시")
private String effStaDtm;


@FxColumn(name = "EFF_END_DTM", size = 14, comment = "유효종료일시")
private String effEndDtm;


@FxColumn(name = "SER_NUM", size = 15, comment = "일련번호")
private Long serNum;


@FxColumn(name = "AUDIT_ID", size = 15, comment = "최종변경자ID")
private String auditId;


@FxColumn(name = "AUDIT_DTM", size = 0, comment = "최종변경일시", defValue = "sysdate")
private Timestamp auditDtm ;


@FxColumn(name = "ADDR_EFF_STA_DTM", size = 14, nullable = true, comment = "주소유효시작일시")
private String addrEffStaDtm;


@FxColumn(name = "ADDR_EFF_END_DTM", size = 14, nullable = true, comment = "주소유효종료일시")
private String addrEffEndDtm;


@FxColumn(name = "ADDR_ID", size = 15, nullable = true, comment = "주소ID")
private String addrId;


@FxColumn(name = "BLDBLK_NUM", size = 15, nullable = true, comment = "건물동번호")
private Long bldblkNum;


@FxColumn(name = "BLDUNT_NUM", size = 15, nullable = true, comment = "건물호번호")
private Long blduntNum;


@FxColumn(name = "ASSIST_ADDR", size = 100, nullable = true, comment = "보조주소")
private String assistAddr;


@FxColumn(name = "NEW_ADDR_YN", size = 1, comment = "새주소여부", defValue = "'N'")
private String newAddrYn = "N";


@FxColumn(name = "INTEG_TXT_ADDR_ID", size = 15, nullable = true, comment = "통합텍스트주소ID")
private String integTxtAddrId;


@FxColumn(name = "ZIP", size = 6, nullable = true, comment = "우편번호")
private String zip;


@FxColumn(name = "ZIP_SER_NUM", size = 15, nullable = true, comment = "우편번호일련번호")
private Long zipSerNum;


@FxColumn(name = "KT_DONG_CD", size = 6, nullable = true, comment = "KT동코드")
private String ktDongCd;


@FxColumn(name = "OLD_ZIP", size = 6, nullable = true, comment = "구우편번호")
private String oldZip;


@FxColumn(name = "OLD_ZIP_SER_NUM", size = 15, nullable = true, comment = "구우편번호일련번호")
private Long oldZipSerNum;


/**
* 서비스관리번호
* @return 서비스관리번호
*/
public Long getSvcMgmtNum() {
return svcMgmtNum;
}
/**
* 서비스관리번호
*@param svcMgmtNum 서비스관리번호
*/
public void setSvcMgmtNum(Long svcMgmtNum) {
	this.svcMgmtNum = svcMgmtNum;
}
/**
* 상품ID
* @return 상품ID
*/
public String getProdId() {
return prodId;
}
/**
* 상품ID
*@param prodId 상품ID
*/
public void setProdId(String prodId) {
	this.prodId = prodId;
}
/**
* 유효시작일시
* @return 유효시작일시
*/
public String getEffStaDtm() {
return effStaDtm;
}
/**
* 유효시작일시
*@param effStaDtm 유효시작일시
*/
public void setEffStaDtm(String effStaDtm) {
	this.effStaDtm = effStaDtm;
}
/**
* 유효종료일시
* @return 유효종료일시
*/
public String getEffEndDtm() {
return effEndDtm;
}
/**
* 유효종료일시
*@param effEndDtm 유효종료일시
*/
public void setEffEndDtm(String effEndDtm) {
	this.effEndDtm = effEndDtm;
}
/**
* 일련번호
* @return 일련번호
*/
public Long getSerNum() {
return serNum;
}
/**
* 일련번호
*@param serNum 일련번호
*/
public void setSerNum(Long serNum) {
	this.serNum = serNum;
}
/**
* 최종변경자ID
* @return 최종변경자ID
*/
public String getAuditId() {
return auditId;
}
/**
* 최종변경자ID
*@param auditId 최종변경자ID
*/
public void setAuditId(String auditId) {
	this.auditId = auditId;
}
/**
* 최종변경일시
* @return 최종변경일시
*/
public Timestamp getAuditDtm() {
return auditDtm;
}
/**
* 최종변경일시
*@param auditDtm 최종변경일시
*/
public void setAuditDtm(Timestamp auditDtm) {
	this.auditDtm = auditDtm;
}
/**
* 주소유효시작일시
* @return 주소유효시작일시
*/
public String getAddrEffStaDtm() {
return addrEffStaDtm;
}
/**
* 주소유효시작일시
*@param addrEffStaDtm 주소유효시작일시
*/
public void setAddrEffStaDtm(String addrEffStaDtm) {
	this.addrEffStaDtm = addrEffStaDtm;
}
/**
* 주소유효종료일시
* @return 주소유효종료일시
*/
public String getAddrEffEndDtm() {
return addrEffEndDtm;
}
/**
* 주소유효종료일시
*@param addrEffEndDtm 주소유효종료일시
*/
public void setAddrEffEndDtm(String addrEffEndDtm) {
	this.addrEffEndDtm = addrEffEndDtm;
}
/**
* 주소ID
* @return 주소ID
*/
public String getAddrId() {
return addrId;
}
/**
* 주소ID
*@param addrId 주소ID
*/
public void setAddrId(String addrId) {
	this.addrId = addrId;
}
/**
* 건물동번호
* @return 건물동번호
*/
public Long getBldblkNum() {
return bldblkNum;
}
/**
* 건물동번호
*@param bldblkNum 건물동번호
*/
public void setBldblkNum(Long bldblkNum) {
	this.bldblkNum = bldblkNum;
}
/**
* 건물호번호
* @return 건물호번호
*/
public Long getBlduntNum() {
return blduntNum;
}
/**
* 건물호번호
*@param blduntNum 건물호번호
*/
public void setBlduntNum(Long blduntNum) {
	this.blduntNum = blduntNum;
}
/**
* 보조주소
* @return 보조주소
*/
public String getAssistAddr() {
return assistAddr;
}
/**
* 보조주소
*@param assistAddr 보조주소
*/
public void setAssistAddr(String assistAddr) {
	this.assistAddr = assistAddr;
}
/**
* 새주소여부
* @return 새주소여부
*/
public String isNewAddrYn() {
return newAddrYn;
}
/**
* 새주소여부
*@param newAddrYn 새주소여부
*/
public void setNewAddrYn(String newAddrYn) {
	this.newAddrYn = newAddrYn;
}
/**
* 통합텍스트주소ID
* @return 통합텍스트주소ID
*/
public String getIntegTxtAddrId() {
return integTxtAddrId;
}
/**
* 통합텍스트주소ID
*@param integTxtAddrId 통합텍스트주소ID
*/
public void setIntegTxtAddrId(String integTxtAddrId) {
	this.integTxtAddrId = integTxtAddrId;
}
/**
* 우편번호
* @return 우편번호
*/
public String getZip() {
return zip;
}
/**
* 우편번호
*@param zip 우편번호
*/
public void setZip(String zip) {
	this.zip = zip;
}
/**
* 우편번호일련번호
* @return 우편번호일련번호
*/
public Long getZipSerNum() {
return zipSerNum;
}
/**
* 우편번호일련번호
*@param zipSerNum 우편번호일련번호
*/
public void setZipSerNum(Long zipSerNum) {
	this.zipSerNum = zipSerNum;
}
/**
* KT동코드
* @return KT동코드
*/
public String getKtDongCd() {
return ktDongCd;
}
/**
* KT동코드
*@param ktDongCd KT동코드
*/
public void setKtDongCd(String ktDongCd) {
	this.ktDongCd = ktDongCd;
}
/**
* 구우편번호
* @return 구우편번호
*/
public String getOldZip() {
return oldZip;
}
/**
* 구우편번호
*@param oldZip 구우편번호
*/
public void setOldZip(String oldZip) {
	this.oldZip = oldZip;
}
/**
* 구우편번호일련번호
* @return 구우편번호일련번호
*/
public Long getOldZipSerNum() {
return oldZipSerNum;
}
/**
* 구우편번호일련번호
*@param oldZipSerNum 구우편번호일련번호
*/
public void setOldZipSerNum(Long oldZipSerNum) {
	this.oldZipSerNum = oldZipSerNum;
}
}

package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;

/**
 * 네트워크 연결 내역을 관리하는 VO
 * 
 * <pre>
 * 링 장애 생성시 필요하며, 추후에 불필요하다고 판단되면 로직 삭제 필요~!
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
@SuppressWarnings("serial")
public class NetworkLinkVo implements Serializable {

	/**
	 * <pre>
	 * ***** 레거시 소스 구조체 ***** *
	 *  
	 * typedef struct _ST_NETWORK_LINK_PORT
	 * {
	 * 		int     network_id;
	 * 		int     src_ne_id;
	 * 		char    src_port_descr[LEN_PORT_DESCR+1];
	 * 		int     dst_ne_id;
	 * 		char    dst_port_descr[LEN_PORT_DESCR+1];
	 * } ST_NETWORK_LINK_PORT;
	 * </pre>
	 * 
	 **/
	private String netNum;
	private String srcEquipId;
	private String srcPortDescr;
	private String dstEquipId;
	private String dstPortDescr;

	public String getNetNum() {
		return netNum;
	}

	public void setNetNum(String netNum) {
		this.netNum = netNum;
	}

	public String getSrcEquipId() {
		return srcEquipId;
	}

	public void setSrcEquipId(String srcEquipId) {
		this.srcEquipId = srcEquipId;
	}

	public String getSrcPortDescr() {
		return srcPortDescr;
	}

	public void setSrcPortDescr(String srcPortDescr) {
		this.srcPortDescr = srcPortDescr;
	}

	public String getDstEquipId() {
		return dstEquipId;
	}

	public void setDstEquipId(String dstEquipId) {
		this.dstEquipId = dstEquipId;
	}

	public String getDstPortDescr() {
		return dstPortDescr;
	}

	public void setDstPortDescr(String dstPortDescr) {
		this.dstPortDescr = dstPortDescr;
	}

}
package com.lagoon.tmn.teams.co.vo;

public interface IDcn {

	/**
	 * EMS, 장비를 통합하여 DCN 키를 제공한다.
	 * 
	 * @return
	 */
	public String getDcnKey();

	public String getIpAddress();

	/**
	 * 사람이 인지 가능한 문자열
	 * 
	 * @return
	 */
	public String toInfo();
}

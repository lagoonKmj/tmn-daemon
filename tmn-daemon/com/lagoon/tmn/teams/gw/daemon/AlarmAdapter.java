package com.lagoon.tmn.teams.gw.daemon;

/**
 * 장애를 처리하는 아답터
 * 
 * @author subkjh(김종훈)
 *
 */
public interface AlarmAdapter {

	/**
	 * 시작
	 * 
	 * @throws Exception
	 */
	public void startDcn() throws Exception;

	/**
	 * 종료
	 */
	public void stopDcn();
}

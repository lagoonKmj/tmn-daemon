package com.lagoon.tmn.teams.co.exp;

/**
 * 로그인 거부<br>
 * 접속ID나 암호가 틀린 경우 발생됩니다.
 * 
 * @author subkjh
 * 
 */
public class LoginDenyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7242338740940617023L;

	public LoginDenyException(String msg) {
		super(msg);
	}
}

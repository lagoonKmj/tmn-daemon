package com.lagoon.tmn.teams.fxms.ao.pdu;

public class BodyLogin extends BodyComm {

	private String userId;
	private String passwd;

	public BodyLogin() {
		this.setEventType(Header.SERVICE_CODE_LOGIN);
	}

	@Override
	public String getBody() {
		StringBuffer sb = new StringBuffer();

		append(sb, userId);
		append(sb, passwd);

		return sb.toString();
	}

	public String getPasswd() {
		return passwd;
	}

	public String getUserId() {
		return userId;
	}

	public void setBody(byte bytes[]) throws Exception {
		String items[] = this.split(bytes);
		setItem(items);
	}

	public void setItem(String items[]) {

		this.userId = items[0];
		this.passwd = items[1];
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
		return userId + "/" + passwd;
	}

}

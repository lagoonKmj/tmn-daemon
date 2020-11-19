package com.lagoon.tmn.teams.fxms.ao.vo;

import fxms.bas.co.noti.FxEventImpl;

public class AlarmSendVo extends FxEventImpl {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5699904135995216955L;

	private long sendDtm;
	private boolean sendYn = false;
	private String sendRsltVal; /* 송신결과값. 0:대기, 1:성공, 2:폐기 */

	public long getSendDtm() {
		return sendDtm;
	}

	public String getSendRsltVal() {
		return sendRsltVal;
	}

	public boolean isSendYn() {
		return sendYn;
	}

	public void setSendDtm(long sendDtm) {
		this.sendDtm = sendDtm;
	}

	public void setSendRsltVal(String sendRsltVal) {
		this.sendRsltVal = sendRsltVal;
	}

	public void setSendYn(boolean sendYn) {
		this.sendYn = sendYn;
	}

}

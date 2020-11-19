package com.lagoon.tmn.teams.fxms.ao.pdu;

public interface Body {

	public void setBody(byte bytes[]) throws Exception;

	public byte[] getBytes() throws Exception;

	public String getBody();

}

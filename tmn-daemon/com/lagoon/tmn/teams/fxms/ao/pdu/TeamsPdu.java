package com.lagoon.tmn.teams.fxms.ao.pdu;

public class TeamsPdu {

	public static void main(String[] args) {
		try {
			TeamsPdu pdu = new TeamsPdu(Header.PDU_EVENT, Header.SERVICE_CODE_EQUIP_FAULT, new BodyEquip());
			System.out.println(new String(pdu.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Header header;
	private Body body;

	public TeamsPdu(char pduType, String serviceCode, Body body) throws Exception {

		this.header = new Header(pduType, serviceCode);
		this.header.setBodyLength(body.getBytes().length);

		this.body = body;
	}

	public TeamsPdu(Header header, byte bytes[]) throws Exception {
		this.header = header;

		body = getBodyClass();
		if (body == null) {
			throw new Exception("서비스코드에 대한 클래스가 정의되지 않았습니다. (" + header.getServiceCode() + ")");
		}
		body.setBody(bytes);

	}

	public Body getBody() {
		return body;
	}

	private Body getBodyClass() {
		if (Header.SERVICE_CODE_CLEAR_MANUAL.equals(header.getServiceCode())) {
			return new BodyClearManual();
		} else if (Header.SERVICE_CODE_EQUIP_FAULT.equals(header.getServiceCode())) {
			return new BodyEquip();
		} else if (Header.SERVICE_CODE_LINE_FAULT.equals(header.getServiceCode())) {
			return new BodyLine();
		} else if (Header.SERVICE_CODE_RING_FAULT.equals(header.getServiceCode())) {
			return new BodyRing();
		} else if (Header.SERVICE_CODE_CLEAR.equals(header.getServiceCode())) {
			return new BodyClear();
		} else if (Header.SERVICE_CODE_LOGIN.equals(header.getServiceCode())) {
			return new BodyLogin();
		}
		return null;
	}

	public byte[] getBytes() throws Exception {

		byte ret[] = new byte[20 + header.getBodyLength()];

		System.arraycopy(header.getBytes(), 0, ret, 0, 20);
		System.arraycopy(body.getBytes(), 0, ret, 20, header.getBodyLength());

		return ret;
	}

	public Header getHeader() {
		return header;
	}

	@Override
	public String toString() {
		return header.getServiceCode() + "|" + body.toString();
	}

}

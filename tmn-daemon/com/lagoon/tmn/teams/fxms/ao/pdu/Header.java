package com.lagoon.tmn.teams.fxms.ao.pdu;

import java.util.Arrays;

/**
 * 
 * ESC ESC byte 1 Frame시작/끝을 나타낸다. <br>
 * STX STX byte 1 Frame시작을 나타낸다. <br>
 * PDUType PduType byte 1 1':Event, '2':Request, '3':Response <br>
 * 서비스코드 ServiceCode byte 4. 0411:장비장애, 0415:회선장애, 0416:링장애, 0413:장애해제<br>
 * 메시지일련번호 messageSerialNo byte 2 <br>
 * 마지막검사 lastYn byte 1 '0':마지막Frame, '1':연속Frame <br>
 * 응답결과 responseResult byte 1 '0':실패, '1':성공 <br>
 * 확장영역 extendArea byte 4 <br>
 * Body길이 bodyLength byte 5 <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class Header {

	public static final int HEADER_SIZE = 20;

	public static final char PDU_EVENT = '1'; // 0x31
	public static final char PDU_REQUEST = '2'; // 0x32
	public static final char PDU_RESPONSE = '3'; // 0x33
	
	public static String SERVICE_CODE_LOGIN = "0005";
	public static String SERVICE_CODE_EQUIP_FAULT = "0411";
	public static String SERVICE_CODE_LINE_FAULT = "0415";
	public static String SERVICE_CODE_RING_FAULT = "0416";
	public static String SERVICE_CODE_CLEAR = "0413";
	public static String SERVICE_CODE_CLEAR_MANUAL = "0450"; /* 수동해제 */
	
	private final byte ESC = 0x1b;
	private final byte STX = 0x02;
	private char pduType; // '1':Event, '2':Request, '3':Response
	private String messageSerialNo = "00";
	private String lastYn = "0";
	private String responseResult = "0";
	private String extendArea = "0000";
	private int bodyLength = 0;
	private String serviceCode = SERVICE_CODE_EQUIP_FAULT;

	public Header(byte bytes[]) throws Exception {

		if (bytes[0] != ESC) {
			throw new Exception("header[0] is not ESC");
		}
		if (bytes[1] != STX) {
			throw new Exception("header[1] is not STX");
		}

		pduType = (char) bytes[2];
		if (pduType != PDU_EVENT && pduType != PDU_REQUEST && pduType != PDU_RESPONSE) {
			throw new Exception("invalid pduType : " + pduType);
		}

		serviceCode = new String(getBytes(bytes, 3, 4));
		bodyLength = Integer.parseInt(new String(getBytes(bytes, 15, 5)));
	}

	public Header(char pduType, String serviceCode) {
		this.pduType = pduType;
		this.serviceCode = serviceCode;
	}

	public int getBodyLength() {
		return bodyLength;
	}

	public byte[] getBytes() {
		byte ret[] = new byte[20];

		Arrays.fill(ret, (byte) '0');

		String len = String.format("%05d", bodyLength);

		ret[0] = ESC;
		ret[1] = STX;
		ret[2] = (byte) pduType;
		System.arraycopy(serviceCode.getBytes(), 0, ret, 3, 4);
		System.arraycopy(messageSerialNo.getBytes(), 0, ret, 7, 2);
		System.arraycopy(lastYn.getBytes(), 0, ret, 9, 1);
		System.arraycopy(responseResult.getBytes(), 0, ret, 10, 1);
		System.arraycopy(extendArea.getBytes(), 0, ret, 11, 4);
		System.arraycopy(len.getBytes(), 0, ret, 15, 5);

		return ret;
	}

	private byte[] getBytes(byte[] src, int pos, int len) {
		byte[] dest = new byte[len];
		System.arraycopy(src, pos, dest, 0, len);
		return dest;
	}

	public char getPduType() {
		return pduType;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setBodyLength(int bodyLength) {
		this.bodyLength = bodyLength;
	}

}

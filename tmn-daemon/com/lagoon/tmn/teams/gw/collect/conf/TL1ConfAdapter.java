package com.lagoon.tmn.teams.gw.collect.conf;

import java.net.ConnectException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.lagoon.tmn.teams.co.exp.LoginDenyException;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.co.vo.NetListener;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.NetPduTL1;
import fxms.nms.co.tl1_2.TL1Client;
import fxms.nms.co.tl1_2.vo.ACK;
import fxms.nms.co.tl1_2.vo.ORMF;

/**
 * TL1을 이용하여 장비, 슬롯, 포트 정보를 가져오는 아답터
 * 
 * @author subkjh(김종훈)
 *
 * @param <DCN>
 */
public abstract class TL1ConfAdapter<DCN extends IDcn> implements NetListener {

	protected final DCN dcn;
	protected TL1Client tl1Client;
	public boolean isConnected = false;
	private LinkedBlockingQueue<NetPduTL1> queue;
	private int timeout = 60;
	protected VendorBaseParser parser;

	public TL1ConfAdapter(DCN dcn) {
		this.dcn = dcn;
		this.queue = new LinkedBlockingQueue<NetPduTL1>();
	}

	public TL1ConfAdapter(DCN dcn, VendorBaseParser parser) {
		this.dcn = dcn;
		this.parser = parser;
		this.queue = new LinkedBlockingQueue<NetPduTL1>();
	}

	public void close() {

		if (tl1Client != null) {

			try {
				logout();
			} catch (Exception e) {
				Logger.logger.error(e);
			}

			tl1Client.close();
			tl1Client = null;
		}
	}

	@Override
	public void onNetState(String state, Object obj) {

		Logger.logger.trace("{},{}", state, obj);

		if (NetListener.tcpConnected.equals(state)) {
			Logger.logger.debug("{},{}", state, obj);
			isConnected = true;
		} else if (NetListener.tcpDisconnected.equals(state)) {
			Logger.logger.debug("{},{}", state, obj);
			isConnected = false;
		} else if (TL1Client.TL1_NET_STATE_TL1PduRecv.equals(state)) {
			try {
				Logger.logger.debug("{},{},{}", state, obj.getClass().getName(), obj.toString());
				queue.put((NetPduTL1) obj);
			} catch (InterruptedException e) {
			}
		}

	}

	/**
	 * 
	 * @param ip
	 * @param port
	 * @throws ConnectException
	 * @throws Exception
	 */
	public void open(String ip, int port) throws ConnectException, Exception {

		NetPduMakerTL1 makert = getPduMaker();
		makert.setCharset(getCharset());

		tl1Client = new TL1Client("Ciena-EmsConf", ip, port, Logger.logger, this, makert);
		tl1Client.setCharset(getCharset());
		tl1Client.open();

		long ptime = System.currentTimeMillis();

		try {

			while (isConnected == false) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}

				if (ptime + 15000 < System.currentTimeMillis()) {
					throw new Exception("Timeout");
				}
			}

			if (waitForReady() == false) {
				throw new Exception("< 표시가 안 나타남.");
			}

			int ctag = sendLogin();

			ORMF m = getORMF(ctag);
			if (m == null) {
				throw new Exception("Timeout");
			}
			if (m.isCompld())
				return;

			if (m.isDeny())
				new LoginDenyException("Login fail : deny");

			if (m.getRi().getCc().toLowerCase().indexOf("deny") >= 0) {
				new LoginDenyException(m.getRi().getCc());
			}

			throw new Exception("Login fail : " + m.getRi().getCc());

		} catch (Exception e) {
			close();
			throw e;
		}
	}

	protected ORMF getORMF(int ctag) {

		Logger.logger.debug("ctag={}", ctag);

		NetPduTL1 pdu = null;

		while (true) {
			try {
				pdu = queue.poll(timeout, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
			}
			if (pdu == null)
				return null;

			if (pdu instanceof ORMF) {
				ORMF m = (ORMF) pdu;
				if (m.getRi().getCtag().equals(ctag + ""))
					return m;
			}
		}
	}

	protected abstract NetPduMakerTL1 getPduMaker();

	protected abstract void logout() throws Exception;

	public ORMF rtrv(String cmd, String tid, String aid, String datas[]) throws Exception {

		int ctag = tl1Client.send(cmd, tid, aid, datas);
		ORMF m = getORMF(ctag);
		if (m == null) {
			throw new Exception("NOT FOUND CTAG");
		}

		if (m.isCompld() || m.isRtrv()) {
			return m;
		} else if (m.isDeny()) {
			throw new Exception("rtrv deny");
		} else {
			throw new Exception("");
		}

	}

	/**
	 * ACR-USER:DOJ-TDM-AF01:ADMIN:1234::ADMIN;
	 * 
	 * @return
	 * @throws Exception
	 */
	protected abstract int sendLogin() throws Exception;

	private boolean waitForReady() {
		NetPduTL1 pdu = null;

		while (true) {
			try {
				pdu = queue.poll(timeout, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
			}

			Logger.logger.trace("{}", pdu);

			if (pdu == null)
				return false;

			if (pdu instanceof ACK) {
				return true;
			}
		}
	}

	protected abstract String getCharset();

	/**
	 * 숫자만의 취하여 정수형으로 변환한다. 오류인 경우 0으로 리턴한다.
	 * 
	 * @param s
	 * @return
	 */
	protected int parseInt(String s) {
		if (s == null) {
			return 0;
		}

		StringBuffer sb = new StringBuffer();
		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9') {
				sb.append(c);
			}
		}

		try {
			return Integer.parseInt(sb.toString());
		} catch (Exception e) {
			return 0;
		}

	}
}

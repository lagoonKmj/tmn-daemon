package com.lagoon.tmn.teams.gw.daemon;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.co.vo.NetListener;
import subkjh.bas.net.emulator.EmulatorTelnet;

public abstract class TelnetAlarmAdapter<DCN extends IDcn> extends Thread
		implements AlarmAdapter, NetListener {

	private LinkedBlockingQueue<String> queue;
	private EmulatorTelnet telnet;
	protected DCN dcn;
	private int reconnectTimeout = 150; /* 재연결 시간(초) */
	protected GwDcnMngThread gwThread;
	private boolean isContinue;
	private Thread thread = this;
	protected VendorBaseParser parser;

	private String loginIdPrompt;
	private String loginPwdPrompt;
	private String prompt[];
	
	public void setLoginPrompt(String loginIdPrompt, String loginPwdPrompt, String prompt[]) {
		this.loginIdPrompt = loginIdPrompt;
		this.loginPwdPrompt = loginPwdPrompt;
		this.prompt = prompt;
	}
	
	public TelnetAlarmAdapter(DCN dcn, GwDcnMngThread gwThread, VendorBaseParser parser) {
		this.queue = new LinkedBlockingQueue<String>();
		this.dcn = dcn;
		this.gwThread = gwThread;
		this.parser = parser;
		this.telnet = new EmulatorTelnet(this);

		setName("TELNET-ALARM-ADAPTER#" + dcn.getDcnKey());
	}

	@Override
	public void onNetState(String state, Object obj) {

		if (EmulatorTelnet.TELNET_NET_STATE_MsgRecv.equals(state)) {
			try {
				queue.put((String) obj);
			} catch (InterruptedException e) {
			}

		} else {
			gwThread.onConnected(dcn, state);
		}
	}

	@Override
	public void startDcn() throws Exception {

		telnet.setWaitLoginString(true);
		
		if (loginIdPrompt != null) {
			telnet.setStrLoginIndi(new String[] { loginIdPrompt });
		}
		if (loginPwdPrompt != null) {
			telnet.setStrPassIndi(new String[] { loginPwdPrompt });
		}
		if (prompt != null) {
			telnet.setPromptArr(prompt);
		}
		
		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			telnet.connect(ems.getIpAddress(), ems.getDablPortNum(), ems.getLoginId(), ems.getLoginPwd(), this);
		} else {
			EquipDcn equip = (EquipDcn) dcn;
			telnet.connect(equip.getEquipIpAddr(), equip.getDablPortNum(), equip.getLoginId(), equip.getLoginPwd(), this);
		}

		isContinue = true;

		start();
		
	}

	@Override
	public void run() {
		String msg;
		
		while (isContinue) {
			try {
				msg = queue.poll(reconnectTimeout, TimeUnit.SECONDS);
				
				if (msg == null) {
					try {
						telnet.cmdln("");
					} catch (Exception e) {
						// 엔터키를 보냈는데 오류가 발생하면 끊긴 것으로 본다.
						Logger.logger.error(e);
						break;
					}
				} else {
					try {
						process(msg);
					} catch (Exception e) {
						Logger.logger.error(e);
					}
				}
				
			} catch (InterruptedException e) {
				continue;
			}
		}
	}

	@Override
	public void stopDcn() {

		isContinue = false;

		thread.interrupt();

		if (telnet != null) {
			telnet.disconnect();
			telnet = null;
		}
	}
	
	protected void process(String msg) throws Exception {
		
		if (msg == null || msg.trim().length() == 0) {
			return;
		}
		Logger.logger.debug("MSG={}", msg);
		
		DetectedAlarmVo detectedAlarm = parseAlarm(msg);
		
		if (detectedAlarm != null) {
			gwThread.put(detectedAlarm);
		}
	}

	/**
	 * 장애 파서 
	 * Override 하여 재구현 한다.
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	protected abstract DetectedAlarmVo parseAlarm(String msg) throws Exception;

}
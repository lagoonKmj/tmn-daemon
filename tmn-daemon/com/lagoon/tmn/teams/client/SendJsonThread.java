package com.lagoon.tmn.teams.client;

import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.json.simple.JSONObject;

import subkjh.bas.co.log.Logger;

/**
 * adams-cm-batch 프로젝트에서 들고와서 필요한 부분만 임시 로 사용
 * 
 * 
 * @author lagoon (강명중)
 *
 */

public class SendJsonThread extends Thread {
	final private byte ETX = 0x03;

	private String pushIpAddress;
	private int pushPortNo;
	private boolean runThread;

	private BlockingQueue<JSONObject> blockingQueue;
	private LinkedList<JSONObject> notSendJson;
	private Socket pushSocket;
	private OutputStream pushOutputStream;
	private boolean running;

	public SendJsonThread(String pushIpAddress, int pushPortNo,
			boolean runThread) {
		super();
		this.pushIpAddress = pushIpAddress;
		this.pushPortNo = pushPortNo;
		this.runThread = runThread;

		blockingQueue = new LinkedBlockingQueue<JSONObject>();
		notSendJson = new LinkedList<JSONObject>();
		if (runThread) {
			start();
		}
	}

	private void openPushSocket() throws Exception {
		Logger.logger.info("openPushSocket");
		closePushSocket();
		pushSocket = new Socket(pushIpAddress, pushPortNo);
		pushOutputStream = pushSocket.getOutputStream();
		Logger.logger.info(String.format("connected %s:%d", pushIpAddress,
				pushPortNo));
	}

	public void run() {
		running = true;
		while (running) {
			try {
				Logger.logger.info("queue size : " + blockingQueue.size());
				JSONObject jsonObject = blockingQueue.take();
				send(jsonObject);
			} catch (Exception e) {
				Logger.logger.error(e);
			}
		}
	}

	public void put(JSONObject jsonObject) {
		if (runThread) {
			blockingQueue.add(jsonObject);
		} else {
			send(jsonObject);
		}
	}

	private void send(JSONObject jsonObject) {
		try {
			if (pushOutputStream == null) {
				openPushSocket();
			}
			sendNotSendedJson();
			pushOutputStream.write(makeSendBuffer(jsonObject.toJSONString()
					.getBytes("UTF-8")));
		} catch (SocketException se) {
			Logger.logger.error(se);
			closePushSocket();
			notSendJson.add(jsonObject);
			MiscUtils.sleep(1000);
		} catch (Exception e) {
			Logger.logger.error(e);
			notSendJson.add(jsonObject);
		}
	}

	private void sendNotSendedJson() throws Exception {
		if (notSendJson.size() == 0)
			return;
		Logger.logger.info("notSendedJson size : " + notSendJson.size());
		while (notSendJson.size() > 0) {
			JSONObject jsonObject = notSendJson.get(0);
			pushOutputStream.write(makeSendBuffer(jsonObject.toJSONString()
					.getBytes("UTF-8")));
			notSendJson.remove(0);
			MiscUtils.sleep(50);
		}
	}

	public void closePushSocket() {
		try {
			if (pushOutputStream != null)
				pushOutputStream.close();
		} catch (Exception e) {
		}
		try {
			if (pushSocket != null)
				pushSocket.close();
		} catch (Exception e) {
		}
		pushOutputStream = null;
		pushSocket = null;
	}

	private byte[] makeSendBuffer(byte[] temp) {
		byte[] sendBuffer = new byte[temp.length + 1];
		System.arraycopy(temp, 0, sendBuffer, 0, temp.length);
		sendBuffer[temp.length] = ETX;
		return sendBuffer;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
}

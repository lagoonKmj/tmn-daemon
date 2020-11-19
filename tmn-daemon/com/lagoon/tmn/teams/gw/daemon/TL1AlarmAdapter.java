package com.lagoon.tmn.teams.gw.daemon;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.EmsDcn;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.co.vo.NetListener;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.NetPduTL1;
import fxms.nms.co.tl1_2.TL1Client;
import fxms.nms.co.tl1_2.vo.AM;

public abstract class TL1AlarmAdapter<DCN extends IDcn> extends Thread
		implements AlarmAdapter, NetListener {

	private LinkedBlockingQueue<NetPduTL1> queue;
	private TL1Client tl1Client;
	protected DCN dcn;
	private int reconnectTimeout = 150; /* 재연결 시간(초) */
	protected GwDcnMngThread gwThread;
	private boolean isContinue;
	private Thread thread = this;
	protected VendorBaseParser parser;

	public TL1AlarmAdapter(DCN dcn, GwDcnMngThread gwThread, VendorBaseParser parser) {
		this.queue = new LinkedBlockingQueue<NetPduTL1>();
		this.dcn = dcn;
		this.gwThread = gwThread;
		this.parser = parser;

		setName("TL1#" + dcn.getDcnKey());
	}

	@Override
	public void onNetState(String state, Object obj) {

		if (TL1Client.TL1_NET_STATE_TL1PduRecv.equals(state)) {
			try {
				queue.put((NetPduTL1) obj);
			} catch (InterruptedException e) {
			}

		} else {
			gwThread.onConnected(dcn, state);
		}
	}

	/**
	 * 사용할 PDU 생성자
	 * 
	 * @return
	 */
	protected abstract NetPduMakerTL1 getPduMaker();

	@Override
	public void startDcn() throws Exception {

		if (dcn instanceof EmsDcn) {
			EmsDcn ems = (EmsDcn) dcn;
			tl1Client = new TL1Client(getName() + "Client", ems.getEmsIpAddr(),
					ems.getDablPortNum(), Logger.logger, this, getPduMaker());
		} else {
			EquipDcn equip = (EquipDcn) dcn;
			tl1Client = new TL1Client(getName() + "Client",
					equip.getEquipIpAddr(), equip.getDablPortNum(),
					Logger.logger, this, getPduMaker());
		}

		tl1Client.open();

		isContinue = true;

		start();
	}

	@Override
	public void run() {

		NetPduTL1 pdu;

		while (isContinue) {

			try {
				pdu = queue.poll(reconnectTimeout, TimeUnit.SECONDS);
			} catch (InterruptedException e) {
				continue;
			}

			// 일정 시간 자료가 오지 않으면 재 연결한다.
			if (pdu == null) {
				try {
					tl1Client.send("\n");
				} catch (Exception e) {
					// 엔터키를 보냈는데 오류가 발생하면 끊긴 것으로 본다.
					Logger.logger.error(e);
					break;
				}
				// tl1Client.doReconnect();
				continue;
			}

			if (pdu instanceof AM) {
				try {
					process((AM) pdu);
				} catch (Exception e) {
					Logger.logger.error(e);
				}
			}

		}

	}

	@Override
	public void stopDcn() {

		isContinue = false;

		thread.interrupt();

		if (tl1Client != null) {
			tl1Client.close();
			tl1Client = null;
		}
	}

	protected void process(AM am) throws Exception {

		Logger.logger.debug("AM={}", am);

		String tid = am.getHeader().getSid();

		if (am.getAi().getVerb() == null || am.getAi().getVerb().size() < 2)
			return;

		if ((am.getAi().getVerb().get(0).equals("REPT") && am.getAi().getVerb()
				.get(1).equals("ALM")) == false)
			return;

		List<DetectedAlarmVo> alarmList = parseAlarm(am.getDataList(), tid);

		Logger.logger.debug("{} = SIZE {}", am.getAi().getAlmcode(),
				(alarmList == null ? 0 : alarmList.size()));

		if (alarmList == null) {
			return;
		}

		setDataAlarmListAndGwThreadPut(am, alarmList);
	}

	/**
	 * 장애 파서 
	 * Override 하여 재구현 한다.
	 * 
	 * @param dataList
	 * @param tid
	 * @return
	 * @throws Exception
	 */
	protected abstract List<DetectedAlarmVo> parseAlarm(List<String> dataList,
			String tid) throws Exception;

	/**
	 * 파싱된 데이터에 필요한 셋팅을 하여 GW쓰레드에 입력 한다.
	 * Override 하여 재구현 한다. 
	 * 
	 * @param am
	 * @param alarmList
	 */
	protected void setDataAlarmListAndGwThreadPut(AM am,
			List<DetectedAlarmVo> alarmList) {
		
		for (DetectedAlarmVo detectedAlarm : alarmList) {
			gwThread.put(detectedAlarm);
		}
/*		
		// clear
		if ("non".equals(am.getAi().getAlmcode())) {
			for (DetectedAlarmVo a : alarmList) {
				a.setClearHstime(am.getHeader().getHstime() + "");
				gwThread.put(a);
			}

			// 발생
		} else {
			// 이미 존재하는 장애 제거
			for (int i = alarmList.size() - 1; i >= 0; i--) {
				// if (TnmsApi.getApi().isExistCur(alarmList.get(i))) {
				// Logger.logger.trace("dup remove {}",
				// alarmList.get(i).getMsgFull());
				// alarmList.remove(i);
				// }
			}

			if (alarmList.size() > 0) {

				for (DetectedAlarmVo a : alarmList) {
					gwThread.put(a);
				}
			}
		}
 */	
	}
}
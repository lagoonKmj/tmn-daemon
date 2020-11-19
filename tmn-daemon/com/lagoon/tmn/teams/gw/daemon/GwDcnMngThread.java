package com.lagoon.tmn.teams.gw.daemon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.co.vo.NetListener;
import fxms.bas.fxo.thread.QueueFxThread;

/**
 * DCN과 연결하여 실시간으로 장애를 수신하여 처리하는 쓰레드
 * 
 * @author subkjh(김종훈)
 *
 */
public abstract class GwDcnMngThread extends QueueFxThread<DetectedAlarmVo> {

	/** key : getDcnKey(), DCN 목록 */
	private Map<String, IDcn> dcnMap = new HashMap<String, IDcn>();
	/** key : getDcnKey(), AlarmAdapter 목록 */
	private Map<String, AlarmAdapter> adapterMap = new HashMap<String, AlarmAdapter>();
	private long lastDcnChecked = 0;
	private long lastDcnSelect = 0;
	private QueueFxThread<IDcn> alarmSyncThread = null;
	private List<IDcn> dcnList = new ArrayList<IDcn>();

	/**
	 * 
	 * @param emsType
	 *            EMS종류
	 * @param equipMdlCd
	 *            장비모델코드목록. 컴마로 구분
	 * @throws Exception
	 */
	public GwDcnMngThread() throws Exception {

		// 1분 DCN 변경 여부를 확인하기 위해서
		super(1 * 60);

		setName("DcnGwThread");

	}

	/**
	 * 
	 * @param dcn
	 * @param reason
	 * @param b
	 *            연결됨(true), 연결이끊김(false)
	 */
	public void onConnected(IDcn dcn, String state) {

		if (NetListener.tcpConnected.equals(state)) {

			Logger.logger.info(Logger.makeString(dcn.getDcnKey(),
					dcn.getIpAddress() + " started"));

			if (alarmSyncThread != null) {
				alarmSyncThread.put(dcn);
			}

		} else if (NetListener.tcpConnectFail.equals(state)
				|| NetListener.tcpDisconnected.equals(state)) {

			Logger.logger.info(Logger.makeString(dcn.getDcnKey(),
					dcn.getIpAddress() + " start failed"));

			AlarmAdapter adapter = adapterMap.remove(dcn.getDcnKey());

			// 연결이 끊김
			if (adapter != null) {
				try {
					adapter.stopDcn();
				} catch (Exception e) {
					Logger.logger.error(e);
				}
			}
		}
	}

	/**
	 * 현재 알람을 DCN으로부터 가져온다.
	 * 
	 * @param dcn
	 * @return
	 * @throws Exception
	 */
	protected abstract List<DetectedAlarmVo> getCurAlarmAll(IDcn dcn)
			throws Exception;

	@Override
	protected void doInit() throws Exception {

		// EMS에 다시 연결되면 그 사이에 못 받은 이벤트가 존재할 수 있으므로 동기화 합니다.
		alarmSyncThread = new QueueFxThread<IDcn>() {

			@Override
			protected void doWork(IDcn dcn) throws Exception {
				try {
					
					Thread.sleep(5000);
					List<DetectedAlarmVo> list = getCurAlarmAll(dcn);
					Logger.logger.info(Logger.makeString(dcn.getDcnKey(),
							"synchronizing alarm(count : " + list.size() + ")"));
					GwApi.getApi().syncAlarmCur(dcn, list);

				} catch (Exception e) {
					Logger.logger.error(e);
				} finally {

				}
			}

			@Override
			protected void onNoDatas(long arg0) {
			}

			@Override
			protected void doInit() throws Exception {
			}

		};
		alarmSyncThread.setName(getName() + "-Sync");
		alarmSyncThread.start();

		checkDcnChanged();
	}

	@Override
	protected void doWork(DetectedAlarmVo data) throws Exception {

		GwApi.getApi().fireAlarm(data);

	}

	@Override
	protected void onNoDatas(long index) {

		checkDcnChanged();

	}

	/**
	 * DCN 시작
	 * 
	 * @param dcn
	 * @throws Exception
	 */
	protected abstract AlarmAdapter makeAlarmAdapter(IDcn dcn) throws Exception;

	/**
	 * DCN 목록이 변경된 경우 처리한다.
	 */
	private void checkDcnChanged() {

		// 1분 이전에 체크했으면 무시한다.
		if (lastDcnChecked + 60 * 1000L > System.currentTimeMillis()) {
			return;
		}

		lastDcnChecked = System.currentTimeMillis();

		// 30분 마다 한번씩 DB에서 읽어 본다.
		if (dcnList.size() == 0
				|| (lastDcnSelect + 1800 * 1000L) < System.currentTimeMillis()) {
			lastDcnSelect = System.currentTimeMillis();
			List<IDcn> tmpList = GwApi.getApi().getDcnList(true);
			if (tmpList == null) {
				return;
			}
			dcnList = tmpList;
		}

		AlarmAdapter adapter;
		List<IDcn> newList = new ArrayList<IDcn>();
		List<IDcn> nowList = new ArrayList<IDcn>();
		IDcn dcn;
		for (IDcn e : dcnList) {
			dcn = dcnMap.remove(e.getDcnKey());
			if (dcn == null) {
				newList.add(e);
			} else {
				nowList.add(dcn);
			}
		}

		Logger.logger.info(Logger.makeString("DCN", "del=" + dcnMap.size()
				+ ",add=" + newList.size()));

		// 없어진 DCN 대해서 삭제하고 중지함.
		for (String key : dcnMap.keySet()) {
			dcn = dcnMap.remove(key);
			adapter = adapterMap.remove(key);

			if (adapter != null) {
				adapter.stopDcn();
				Logger.logger.info(Logger.makeString(key, "stopped"));
			}
		}

		// 새로운 DCN을 시작
		for (IDcn e : newList) {
			startDcn(e);
		}

		// 현재 DCN에서 연결이 끊긴 DCN은 다시 시작한다.
		for (IDcn e : nowList) {
			adapter = adapterMap.get(e.getDcnKey());
			if (adapter == null) {
				startDcn(e);
			} else {
				dcnMap.put(e.getDcnKey(), e);
			}
		}
	}

	private void startDcn(IDcn dcn) {
		try {
			AlarmAdapter adapter;

			adapter = makeAlarmAdapter(dcn);
			if (adapter != null) {
				adapter.startDcn();
				dcnMap.put(dcn.getDcnKey(), dcn);
				adapterMap.put(dcn.getDcnKey(), adapter);
				GwApi.getApi().updateDcnStatus(dcn, true);
			}
			
		} catch (Exception e) {
			Logger.logger.error(e);
			GwApi.getApi().updateDcnStatus(dcn, false);

		}
	}

}

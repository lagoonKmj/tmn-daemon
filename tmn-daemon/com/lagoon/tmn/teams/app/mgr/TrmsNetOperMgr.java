package com.lagoon.tmn.teams.app.mgr;

import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.co.dao.AlarmDao;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;
import com.lagoon.tmn.teams.fxms.dbo.ONM10100;

import subkjh.bas.co.log.Logger;
import fxms.bas.co.cron.Cron;
import fxms.bas.fxo.thread.CycleFxThread;

/**
 * 망 작업 내역 을 관리해 주는 메니저
 * 
 * <pre>
 *  Cron으로 인한 단순 데이터 만 쿼리하고 가지고 있는다.
 * </pre>
 * 
 * @author lagoon(강명중)
 *
 */
public class TrmsNetOperMgr extends CycleFxThread implements IManager {

	// 망작업번호를 기준으로 망작업내역을 가진다. (필요시 사용)
	@SuppressWarnings("unused")
	private Map<String, List<OMN33830>> trmsNetOper2MapByEquipIdAndPortDescr;
	// 장비ID 와 포트Descr 정보로 망작업내역 을 가진다.(포트 정보가 NULL이라서 보류)
	@SuppressWarnings("unused")
	private Map<String, ONM10100> trmsNetOper2Map;
	// 장비ID를 기준으로 망작업내역을 가진다.
	private Map<String, List<ONM10100>> trmsNetOper2MapByEquipId;

	/**
	 * 
	 * CycleFxThread 30SEC
	 * 
	 * @throws Exception
	 */
	public TrmsNetOperMgr() throws Exception {
		super("GET-ONM10100-THREAD", Cron.CYCLE_30_SECONDS);
	}

	@Override
	protected void doCycle(long arg0) {

		if (AdamsApiApp.getApiApp().isReady()) {
			reload();
		}

	}

	@Override
	public void init() {
		Logger.logger.trace("TrmsNetOperMgr Initalize.");
		start();
	}

	@Override
	public void reload() {

		trmsNetOper2MapByEquipId = selectNetOper2MapByEquipId();

		Logger.logger.info("TRMS NET OPER COUNT : {}",
				trmsNetOper2MapByEquipId.size());
	}

	/**
	 * 메니저 초기화 여부
	 * 
	 * <pre>
	 * 	각 객체들이 null이 아니면 준비된 상태라고 판단
	 * </pre>
	 * 
	 * @return
	 */
	@Override
	public boolean isReady() {
		return (trmsNetOper2MapByEquipId != null);
	}

	/**
	 * 장비ID를 기준으로 망작업내역을 Map 으로 가공 하여 반환 한다.
	 * 
	 * @param
	 * @return Map<String, ONM10100>
	 */
	private Map<String, List<ONM10100>> selectNetOper2MapByEquipId() {
		try {
			return new AlarmDao().selectNetOper2MapByEquipId();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 장비ID로 망작업내역을 찾아 반환 한다.
	 * 
	 * @param equipId
	 * @return
	 */
	public List<ONM10100> findNetOperListByEquipId(String equipId) {
		return trmsNetOper2MapByEquipId.get(equipId);
	}

}

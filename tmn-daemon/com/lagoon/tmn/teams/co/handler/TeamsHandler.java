package com.lagoon.tmn.teams.co.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsApi;
import com.lagoon.tmn.teams.co.AdamsApiApp;
import com.lagoon.tmn.teams.co.dao.AdamsDao;
import com.lagoon.tmn.teams.co.dao.AdamsOMN33812Dao;
import com.lagoon.tmn.teams.co.vo.TrmsNetAlcdVo;
import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;
import com.lagoon.tmn.teams.fxms.dbo.OMN33811Ext;
import com.lagoon.tmn.teams.fxms.dbo.OMN33812;

import subkjh.bas.co.log.Logger;
import subkjh.bas.co.utils.ObjectUtil;
import fxms.bas.co.signal.ReloadSignal;
import fxms.module.restapi.CommHandler;
import fxms.module.restapi.vo.SessionVo;

public class TeamsHandler extends CommHandler {

	/**
	 * 데이터 처리부 REST-API<br>
	 * <br>
	 * 변경된 정보가 있는지 확인한다.<br>
	 * 수집부에서 n초에 한번씩 호출하여 변경된 정보가 있는지 확인한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object checkState(SessionVo session, Map<String, Object> parameters) throws Exception {

		Map<String, Object> ret = new HashMap<String, Object>();

		Object obj = parameters.get("last-mstime");
		if (obj == null) {
			return ret;
		}

		long mstime = 0;
		try {
			mstime = Long.parseLong(obj.toString());
		} catch (Exception e) {
			Logger.logger.error(e);
			return ret;
		}

		return AdamsApi.getApi().getChangedState(mstime);

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object fireEvent(SessionVo session, Map<String, Object> parameters) throws Exception {

		Map<String, Object> eventMap = (Map) parameters.get("event");
		TrmsNetEventVo event = new TrmsNetEventVo();

		ObjectUtil.toObject(eventMap, event);

		try {
			OMN33811Ext alarm = AdamsApiApp.getApiApp().fireEvent(event);
			return alarm;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 현재 장애를 해제한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object clearAlarm(SessionVo session, Map<String, Object> parameters) throws Exception {
		long trmsEquipDablNum = this.getLong(parameters, "trmsEquipDablNum");

		try { 
			 OMN33811Ext alarm = AdamsApiApp.getApiApp().clearAlarm(trmsEquipDablNum);
			return alarm;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	public Object getDcnCotList(SessionVo session, Map<String, Object> parameters) throws Exception {

		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) parameters.get("equipMdlCd");
		String equipMdlCd[] = list.toArray(new String[list.size()]);

		try {
			return new AdamsDao().selectDcnCotListByModel(equipMdlCd);
		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		}
	}

	public Object getDcnList(SessionVo session, Map<String, Object> parameters) throws Exception {
		try {
			return new AdamsDao().selectDcnList(parameters);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 이벤트 추신 시간을 제공한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object getEventTime(SessionVo session, Map<String, Object> parameters) throws Exception {
		try {
			// return FxServiceImpl.fxService.getEventRecvMap();
			return null;
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getNeList(SessionVo session, Map<String, Object> parameters) throws Exception {

		String dcnEmsTyp = this.getString(parameters, "dcnEmsTyp");
		String dcnNum = this.getString(parameters, "dcnNum");
		List<String> list = (List) parameters.get("equipMdlCd");
		String equipMdlCd[] = list.toArray(new String[list.size()]);

		try {
			return new AdamsDao().selectNeList(dcnEmsTyp, equipMdlCd, dcnNum == null ? -1 : Integer.parseInt(dcnNum));
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object getNeListWithCot(SessionVo session, Map<String, Object> parameters) throws Exception {
		List<String> list = (List) parameters.get("equipMdlCd");
		String equipMdlCd[] = list.toArray(new String[list.size()]);
		try {
			return new AdamsDao().selectNeListWithCot(equipMdlCd);
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 클라이언트에서 reload 요청이 오면 데이터를 갱신 한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object reload(SessionVo session, Map<String, Object> parameters) throws Exception {

		ReloadSignal reloadSignal = new ReloadSignal();
		AdamsApiApp.getApiApp().onNotify(reloadSignal);

		return new ResultMap();
	}

	/**
	 * 해지되지않은 장애 모두 삭제(이벤트)
	 * 
	 * <pre>
	 * 테스트 용도
	 * </pre>
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object releaseCurrentAlarmAll(SessionVo session, Map<String, Object> parameters) throws Exception {

		try {
			AdamsApiApp.getApiApp().releaseCurrentAlarmAll();
			return new ResultMap();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

	/**
	 * 새로운 전송망장비메시지내역(OMN33812)을 추가하고 추가된 내용을 제공한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object addOmn33812(SessionVo session, Map<String, Object> parameters) throws Exception {

		String equipMdlCd = getString(parameters, "equipMdlCd");
		String reason = getString(parameters, "reason");
		String dablGrCd = getString(parameters, "dablGrCd");

		OMN33812 omn33812 = new AdamsOMN33812Dao().insertOMN33812(equipMdlCd, reason, dablGrCd);

		// TODO 하나의 VO로 사용 필요(OMN33812)
		TrmsNetAlcdVo trmsNetAlcdVo = new TrmsNetAlcdVo(omn33812);
		AdamsApiApp.getApiApp().addTrmsNetAlcd(trmsNetAlcdVo);

		return omn33812;
	}

	/**
	 * 저속급 TDM점검결과 '불일치' 일시 장애 생성
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object findCrsAlarmAndFireEvent(SessionVo session,
			Map<String, Object> parameters) throws Exception {

		Logger.logger.debug(Logger.makeString("저속급TDM 장애 체크"));
		try {
			AdamsApiApp.getApiApp().findCrsAlarmAndFireEvent();
			return new ResultMap();
		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		}
	}

}
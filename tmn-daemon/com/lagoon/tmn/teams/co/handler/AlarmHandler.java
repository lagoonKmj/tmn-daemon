package com.lagoon.tmn.teams.co.handler;

import java.util.Map;

import com.lagoon.tmn.teams.co.vo.TrmsNetEventVo;

import subkjh.bas.co.utils.ObjectUtil;
import fxms.module.restapi.CommHandler;
import fxms.module.restapi.vo.SessionVo;

/**
 * 장애 처리부 REST-API
 * 
 * @author subkjh(김종훈)
 *
 */
public class AlarmHandler extends CommHandler {

	/**
	 * 수집부에서 보낸 이벤트를 처리한다.
	 * 
	 * @param session
	 * @param parameters
	 * @return
	 * @throws Exception
	 */
	public Object receiveNetEvent(SessionVo session, Map<String, Object> parameters) throws Exception {

		TrmsNetEventVo vo = new TrmsNetEventVo();

		ObjectUtil.toObject(parameters, vo);
		// TODO : 이벤트 처리 작업

		return null;
	}
}

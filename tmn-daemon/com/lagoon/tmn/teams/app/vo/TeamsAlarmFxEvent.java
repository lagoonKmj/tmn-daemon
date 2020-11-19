package com.lagoon.tmn.teams.app.vo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;
import com.lagoon.tmn.teams.fxms.dbo.OMN33820;
import com.lagoon.tmn.teams.fxms.dbo.OMN33830;

import fxms.bas.co.noti.FxEventImpl;

/**
 * ADAMS-TEAMS 서버와 ADAMS 클라이언트간의 메세지를 송,수신 하는 이벤트 VO
 * 
 * 
 * @author lagoon
 *
 */
@SuppressWarnings("serial")
public class TeamsAlarmFxEvent extends FxEventImpl implements Serializable {

	public static final int ALARM = 1;
	public static final int NO_SEND_ALARM = 2;
	public static final int ECHO = 3;
	public static final int LOGIN = 4;

	public static final int EQUIP_ALARM = 1;
	public static final int NETWORK_ALARM = 2;
	public static final int LINE_ALARM = 3;

	private int type = -1;
	private String userId;
	private String passwd;
	private String msg;
	private String currentTime;

	private OMN33810 omn33810;
	private OMN33820 omn33820;
	private OMN33830 omn33830;

	public TeamsAlarmFxEvent() {
		setCurrentTime(System.currentTimeMillis() + "");
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

	public OMN33810 getOmn33810() {
		return omn33810;
	}

	public void setOmn33810(OMN33810 omn33810) {
		this.omn33810 = omn33810;
	}

	public OMN33820 getOmn33820() {
		return omn33820;
	}

	public void setOmn33820(OMN33820 omn33820) {
		this.omn33820 = omn33820;
	}

	public OMN33830 getOmn33830() {
		return omn33830;
	}

	public void setOmn33830(OMN33830 omn33830) {
		this.omn33830 = omn33830;
	}

	public int getAlarmType() {
		if (omn33820 != null) {
			return LINE_ALARM;
		} else if (omn33830 != null) {
			return NETWORK_ALARM;
		}
		return EQUIP_ALARM;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}

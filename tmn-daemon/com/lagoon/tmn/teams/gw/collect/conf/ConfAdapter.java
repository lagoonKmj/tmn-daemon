package com.lagoon.tmn.teams.gw.collect.conf;

import java.net.ConnectException;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.DetectedAlarmVo;
import com.lagoon.tmn.teams.co.vo.DetectedNeVo;
import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.co.vo.PsVo;

/**
 * EMS를 통해 장비의 구성, 성능을 조회하는 클래스
 * 
 * @author subkjh(김종훈)
 *
 */
public interface ConfAdapter {

	/**
	 * 구성 정보(버전, 슬롯, 포트)를 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DetectedNeVo> collectConf() throws ConnectException, Exception;

	/**
	 * 온도, 광레벨을 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PsVo> collectPs() throws ConnectException, Exception;

	/**
	 * 현재 장애를 조회한다.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<DetectedAlarmVo> collectAlarm() throws ConnectException, Exception;

	public static DetectedNeVo makeEquipDetectedNeVo(EquipDcn dcn) {

		DetectedNeVo ne = new DetectedNeVo();
		ne.fill(dcn);
		ne.setIpAddr(dcn.getIpAddress());
		ne.setDetectedMstime(System.currentTimeMillis());
		return ne;
	}

	public static long getDate(String s) {

		char chs[] = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			if (ch >= '0' && ch <= '9') {
				sb.append(ch);
			}
		}

		if (sb.toString().length() != 14) {
			return 0;
		}

		return Long.parseLong(sb.toString());

	}

	public static String getDateString(String s) {

		char chs[] = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			if (ch >= '0' && ch <= '9') {
				sb.append(ch);
			}
		}

		if (sb.toString().length() != 14) {
			return "";
		}

		return sb.toString();

	}

	public static float getFloat(String s) {

		char chs[] = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			if ((ch >= '0' && ch <= '9') || ch == '-' || ch == '.') {
				sb.append(ch);
			}
		}

		if (sb.length() == 0) {
			return 0;
		}

		return Float.parseFloat(sb.toString());
	}

	public static int getInt(String s) {

		char chs[] = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			if ((ch >= '0' && ch <= '9') || ch == '-') {
				sb.append(ch);
			}
		}

		if (sb.length() == 0) {
			return 0;
		}

		return Integer.parseInt(sb.toString());
	}

	public static long getLong(String s) {

		char chs[] = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char ch : chs) {
			if ((ch >= '0' && ch <= '9') || ch == '-') {
				sb.append(ch);
			}
		}

		if (sb.length() == 0) {
			return 0;
		}

		return Long.parseLong(sb.toString());
	}

}

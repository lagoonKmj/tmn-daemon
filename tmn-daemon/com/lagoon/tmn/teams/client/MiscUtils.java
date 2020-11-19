package com.lagoon.tmn.teams.client;

import java.util.Calendar;

/**
 * adams-cm-batch 프로젝트에서 들고와서 필요한 부분만 임시 로 사용
 * 
 * 
 * @author lagoon (강명중)
 *
 */
public class MiscUtils {
	static public String getProcessDate(int day) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, day);
		return String.format("%04d%02d%02d", cal.get(Calendar.YEAR),
				cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
	}

	static public String getDateTime() {
		Calendar cal = Calendar.getInstance();
		return String.format("%04d-%02d-%02d %02d:%02d:%02d",
				cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1,
				cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY),
				cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}

	static public String getAlphaNumeric(String src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			char ch = src.charAt(i);
			if (Character.isDigit(ch) || Character.isAlphabetic(ch)) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	public static String getStandardDateFormatString(String src) {
		if (src == null)
			return null;
		String ret = getNumeric(src);
		// YYYYMMDDHHMISS
		if (ret.length() == 14)
			return ret;
		return src;
	}

	static public String getNumeric(String src) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < src.length(); i++) {
			char ch = src.charAt(i);
			if (Character.isDigit(ch)) {
				sb.append(ch);
			}
		}
		return sb.toString();
	}

	static public void sleep(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (Exception e) {
		}
	}

	static public int getNextMinuteMills() {
		long mstime = System.currentTimeMillis();
		return (60 * 1000) - (int) (mstime % (60 * 1000));
	}
}

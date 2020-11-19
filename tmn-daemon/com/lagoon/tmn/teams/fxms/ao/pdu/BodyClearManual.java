package com.lagoon.tmn.teams.fxms.ao.pdu;

import java.util.ArrayList;
import java.util.List;

public class BodyClearManual extends BodyComm {

	private List<BodyClear> list;

	public BodyClearManual() {
		this.setEventType(Header.SERVICE_CODE_CLEAR_MANUAL);
	}

	public void setBody(byte[] bytes) {
		int idx = 0;
		for (idx = 0; idx < bytes.length; idx++) {
			if (bytes[idx] == SEPARATOR) {
				break;
			}
		}
		int count = 0;
		try {
			count = Integer.parseInt(new String(bytes, 0, idx));
		} catch (Exception e) {
			return;
		}
		if (count == 0) {
			return;
		}

		try {
			list = new ArrayList<BodyClear>();
			String value = new String(bytes, idx, bytes.length - idx, CHARSET_NAME);
			String[] items = value.split("\\^");
			for (int i = 0; i < items.length; i++) {
				String[] items2 = items[i].split("\\|");
				BodyClear event = new BodyClear();
				event.setItem(items2);
				list.add(event);
			}
		} catch (Exception e) {

		}
	}

	public List<BodyClear> getList() {
		return list;
	}

	public void setList(List<BodyClear> list) {
		this.list = list;
	}

	@Override
	public String getBody() {
		
		StringBuffer sb = new StringBuffer();
		sb.append(list.size());
		sb.append(SEPARATOR);

		for (BodyClear e : list) {
			sb.append(e.getBody());
			sb.append("^");
		}

		return sb.toString();
	}
}

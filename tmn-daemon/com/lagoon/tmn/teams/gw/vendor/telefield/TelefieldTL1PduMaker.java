package com.lagoon.tmn.teams.gw.vendor.telefield;

import subkjh.bas.co.log.Logger;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.NetPduTL1;
import fxms.nms.co.tl1_2.vo.ACK;

public class TelefieldTL1PduMaker extends NetPduMakerTL1 {

	public TelefieldTL1PduMaker() {
		super("TelefieldPdu", Logger.logger);
	}

	@Override
	public NetPduTL1 makePdu(String s) throws Exception {
		logger.debug("RECV=[{}]", s);

		String contents = s.trim();
		String line[] = contents.split("\n");

		if (line.length == 2 && line[1].charAt(0) == '<') {
			return new ACK(s);
		}

		int index = 0; // 첫번째 라인은 명령어가 온다.
		for (int i = index; i < line.length; i++) {
			if (line[i].trim().length() > 0) {
				index = i;
				break;
			}
		}

		try {
			return makePdu(index, line, s);
		} catch (Exception e) {

			if (line[line.length - 1].charAt(0) == '<') {
				return new ACK(s);
			}

			throw e;

		}

	}

}

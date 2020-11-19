package com.lagoon.tmn.teams.fxms.ao;

import com.lagoon.tmn.teams.fxms.ao.pdu.Body;
import com.lagoon.tmn.teams.fxms.ao.pdu.BodyLogin;
import com.lagoon.tmn.teams.fxms.ao.pdu.Header;
import com.lagoon.tmn.teams.fxms.ao.pdu.TeamsPdu;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.soproth.Soproth;

public class TeamsSoproth extends Soproth {

	private BodyLogin loginInfo;

	public TeamsSoproth() {

	}

	@Override
	protected void initProcess() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void process(byte[] recvBytes) throws Exception {

		byte bytes[] = recvBytes;
		TeamsPdu teamsPdu;

		System.out.println(new String(recvBytes));

		while (bytes.length > 0) {
			if (bytes.length >= 20) {
				Header header = new Header(bytes);
				if (bytes.length >= Header.HEADER_SIZE + header.getBodyLength()) {
					byte body[] = new byte[header.getBodyLength()];
					System.arraycopy(bytes, Header.HEADER_SIZE, body, 0, body.length);
					teamsPdu = new TeamsPdu(header, body);

					if (teamsPdu.getHeader().getServiceCode().equals(Header.SERVICE_CODE_LOGIN)) {
						login(teamsPdu.getBody());
					}

					byte tmp[] = new byte[bytes.length - (Header.HEADER_SIZE + header.getBodyLength())];
					if (tmp.length > 0) {
						System.arraycopy(bytes, body.length, tmp, 0, tmp.length);
					}
					bytes = tmp;
				}
			} else {
				bytes = mergeNext(bytes);
			}
		}

	}

	private void login(Body body) {
		if (body instanceof BodyLogin) {
			loginInfo = (BodyLogin) body;
			Logger.logger.info("{}", loginInfo.toString());
		}
	}

	/**
	 * LOGIN된 클라이언트에게만 내용을 보낸다.
	 */
	@Override
	public void send(byte bytes[]) throws Exception {
		if (loginInfo != null) {
			super.send(bytes);
		}
	}
}

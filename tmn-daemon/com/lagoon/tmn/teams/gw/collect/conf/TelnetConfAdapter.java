package com.lagoon.tmn.teams.gw.collect.conf;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;

import subkjh.bas.co.log.Logger;
import subkjh.bas.net.co.vo.NetListener;
import subkjh.bas.net.emulator.EmulatorTelnet;

public class TelnetConfAdapter<DCN extends IDcn> implements NetListener {

	protected final DCN dcn;
	protected final EmulatorTelnet telnet = new EmulatorTelnet();
	private String loginIdPrompt;
	private String loginPwdPrompt;
	private String prompt[];
	protected VendorBaseParser parser;

	public void setLoginPrompt(String loginIdPrompt, String loginPwdPrompt, String prompt[]) {
		this.loginIdPrompt = loginIdPrompt;
		this.loginPwdPrompt = loginPwdPrompt;
		this.prompt = prompt;
	}

	public TelnetConfAdapter(DCN dcn, VendorBaseParser parser) {
		this.dcn = dcn;
		this.parser = parser;
	}

	@Override
	public void onNetState(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	public String send(String cmd, String... waitStrArr) throws Exception {
		String result = telnet.cmd(cmd + "\n", waitStrArr);

		Logger.logger.debug("\ncommand : [{}]\nresult : [{}]", cmd, result);

		return result;
	}

	public void open(String ip, int port, String loginId, String loginPwd) throws Exception {

		telnet.setWaitLoginString(true);

		if (loginIdPrompt != null) {
			telnet.setStrLoginIndi(new String[] { loginIdPrompt });
		}
		if (loginPwdPrompt != null) {
			telnet.setStrPassIndi(new String[] { loginPwdPrompt });
		}
		if (prompt != null) {
			telnet.setPromptArr(prompt);
		}

		telnet.connect(ip, port, loginId, loginPwd);

	}

	public void close() {
		if (telnet != null) {
			telnet.disconnect();
		}
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	protected int getPortNo(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		String ss[] = s.split("/|-");
		try {
			return Integer.parseInt(ss[ss.length - 1]);
		} catch (Exception e) {
			Logger.logger.error(e);
			return 0;
		}

	}
}

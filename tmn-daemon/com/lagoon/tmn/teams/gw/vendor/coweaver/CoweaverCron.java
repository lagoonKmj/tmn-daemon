package com.lagoon.tmn.teams.gw.vendor.coweaver;

import java.util.List;

import com.lagoon.tmn.teams.co.vo.IDcn;
import com.lagoon.tmn.teams.gw.co.GwApi;
import com.lagoon.tmn.teams.gw.collect.conf.ConfAdapter;
import com.lagoon.tmn.teams.gw.collect.conf.ConfCron;

/**
 * 
 * 코위버 장비 구성 수집<br>
 * 설정파일 : deploy/filter/coweaver-filter-list.xml <br>
 * 
 * @author subkjh(김종훈)
 *
 */
public class CoweaverCron extends ConfCron {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054791169924067950L;

	@Override
	protected List<IDcn> getDcnList() {
		return GwApi.getApi().getDcnList(true);
	}

	@Override
	protected ConfAdapter makeAdapter(IDcn dcn) {
		return new Coweaver().makeAdapter(dcn);
	}

	//
	// private void workEquip(EquipDcn equip) {
	// CoweaverConfAdapter adapter = new CoweaverConfAdapter(equip);
	//
	// // adapter.collectPs();
	//
	// StringBuffer sb = new StringBuffer();
	// sb.append(Logger.makeString("Collect Config", equip.getEquipIpAddr()));
	//
	// EmulatorTelnet telnet = new EmulatorTelnet();
	// telnet.setWaitLoginString(true);
	//
	// try {
	// telnet.setStrLoginIndi(new String[] { "Login ID:" });
	// telnet.setStrPassIndi(new String[] { "Password:" });
	//
	// telnet.connect(equip.getEquipIpAddr(), equip.getConsPortNum(),
	// equip.getLoginId(), equip.getLoginPwd());
	//
	// String result = telnet.cmd("show system version hardware");
	// if (equip.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7200)) {
	// result = telnet.cmd("show system software package working-papa");
	// } else if
	// (equip.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7300)) {
	// result = telnet.cmd("show system software package working-papu");
	// }
	//
	// Logger.logger.info(sb.toString());
	//
	// } catch (Exception e) {
	//
	// Logger.logger.fail(sb.toString());
	// Logger.logger.error(e);
	//
	// return;
	//
	// } finally {
	// telnet.disconnect();
	// }
	//
	// }
	//
	// private void workRt(EquipDcn cot, EquipDcn rt) {
	// CoweaverConfAdapter adapter = new CoweaverConfAdapter(cot);
	//
	// // adapter.collectPs();
	//
	// StringBuffer sb = new StringBuffer();
	// sb.append(Logger.makeString("Collect Config", cot.getEquipIpAddr()));
	//
	// EmulatorTelnet telnet = new EmulatorTelnet();
	// telnet.setWaitLoginString(true);
	//
	// try {
	// telnet.setStrLoginIndi(new String[] { "Login ID:" });
	// telnet.setStrPassIndi(new String[] { "Password:" });
	//
	// telnet.connect(cot.getEquipIpAddr(), cot.getConsPortNum(),
	// cot.getLoginId(), cot.getLoginPwd());
	//
	// telnet.cmd("telnet " + rt.getEquipIpAddr(), (new String[] { "Login ID:"
	// });
	// telnet.cmd("telnet " + rt.getEquipIpAddr(), (new String[] { "Login ID:"
	// });
	//
	//
	// String result = telnet.cmd("show system version hardware");
	// if (equip.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7200)) {
	// result = telnet.cmd("show system software package working-papa");
	// } else if
	// (equip.getEquipMdlCd().equals(CoweaverService.MODEL_UTRANS_7300)) {
	// result = telnet.cmd("show system software package working-papu");
	// }
	//
	// Logger.logger.info(sb.toString());
	//
	// } catch (Exception e) {
	//
	// Logger.logger.fail(sb.toString());
	// Logger.logger.error(e);
	//
	// return;
	//
	// } finally {
	// telnet.disconnect();
	// }
	//
	// }
}

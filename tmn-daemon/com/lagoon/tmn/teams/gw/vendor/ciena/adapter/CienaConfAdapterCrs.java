package com.lagoon.tmn.teams.gw.vendor.ciena.adapter;

import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.vo.EquipDcn;
import com.lagoon.tmn.teams.fxms.dbo.OIV28160;
import com.lagoon.tmn.teams.gw.co.parser.VendorBaseParser;
import com.lagoon.tmn.teams.gw.collect.conf.TL1ConfAdapter;
import com.lagoon.tmn.teams.gw.vendor.ciena.Ciena;
import com.lagoon.tmn.teams.gw.vendor.ciena.CienaTL1PduMaker;

import subkjh.bas.co.log.LOG_LEVEL;
import subkjh.bas.co.log.Logger;
import fxms.bas.api.FxApi;
import fxms.nms.co.tl1_2.NetPduMakerTL1;
import fxms.nms.co.tl1_2.vo.ORMF;

/**
 * 저속급 TDM CRS 가져오기
 * 
 * @author subkjh(김종훈)
 *
 */
public class CienaConfAdapterCrs extends TL1ConfAdapter<EquipDcn> {

	public static void main(String[] args) {

		Logger.logger = Logger.createLogger(".", "test");
		Logger.logger.setLevel(LOG_LEVEL.debug);

		EquipDcn dcn = new EquipDcn();
		dcn.setEquipId("000000601341");
		dcn.setConsPortNum(23);
		dcn.setLoginPwd("ADMIN");
		dcn.setLoginId("ADMIN");
		dcn.setEquipTidVal("DOJ-TDM-AF02");
		dcn.setEquipIpAddr("12.4.44.128");

		try {
			CienaConfAdapterCrs crs = new CienaConfAdapterCrs(dcn, new Ciena());
			crs.getCrs();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// CienaCrs crs = new CienaCrs(null);
		// try {
		// OIV28160 o =
		// crs.make28160("\"VT2AU4-1-10-5-22-2-4-1,VT2AU4-1-1-1-151-1-7-1:2WAY:PRIMARYXC=SHELF:VT2:\"");
		// System.out.println(o);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// try {
		// CienaCrs crs = new CienaCrs(null);
		// crs.test();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public CienaConfAdapterCrs(EquipDcn dcn, VendorBaseParser parser) {
		super(dcn, parser);
	}

	public List<OIV28160> getCrs() {
		try {

			if (dcn.getConsPortNum() == 0) {
				dcn.setConsPortNum(23);
			}
			if (dcn.getLoginId() == null) {
				dcn.setLoginId("ADMIN");
			}
			if (dcn.getLoginPwd() == null) {
				dcn.setLoginPwd("ADMIN");
			}

			open(dcn.getEquipIpAddr(), dcn.getConsPortNum());

			return rtrvCrsAll();

		} catch (Exception e) {
			Logger.logger.error(e);
			return null;
		} finally {
			close();
		}
	}

	protected OIV28160 make28160(String s) throws Exception {

		String line = s.trim();
		if (line.length() == 0 || line.charAt(0) != '"') {
			return null;
		}

		line = line.replaceAll("\"", "");
		String ss[] = line.split(":");

		if (ss.length >= 4) {

			OIV28160 ret = new OIV28160();
			String port[] = ss[0].split(",");
			if (port[0].compareTo(port[1]) < 0) {
				ret.setAdrcEquipPortDesc(port[0]);
				ret.setBdrcEquipPortDesc(port[1]);
			} else {
				ret.setAdrcEquipPortDesc(port[1]);
				ret.setBdrcEquipPortDesc(port[0]);
			}
			ret.setCrsClVal("CRS");
			ret.setCrsRmk(ss[2]);
			ret.setSgnlTypNm(ss[3] == null ? "X" : ss[3]); // VT2
			return ret;
		}

		return null;

		// DOJ-TDM-AF01 19-08-21 07:20:20
		// M 1236 COMPLD
		// "VT2AU4-1-3-1-163-1-1-1,VT2AU4-1-12-8-46-1-2-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-3-1-163-1-1-2,VT2AU4-1-12-8-46-1-3-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-3-1-163-1-1-3,VT2AU4-1-12-8-46-1-3-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-3-1-163-1-2-1,VT2AU4-1-12-8-46-1-3-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-3-1-163-1-2-2,VT2AU4-1-12-8-46-1-4-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-1-1,VT2AU4-1-1-1-151-1-2-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-1-2,VT2AU4-1-1-1-151-1-2-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-1-3,VT2AU4-1-1-1-151-1-2-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-2-1,VT2AU4-1-1-1-151-1-3-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-2-2,VT2AU4-1-1-1-151-1-3-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-9-7-25-1-1-1,VT2AU4-1-1-1-151-1-4-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-9-7-25-1-1-2,VT2AU4-1-1-1-151-1-4-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-9-7-25-1-1-3,VT2AU4-1-1-1-151-1-5-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-9-7-25-1-2-1,VT2AU4-1-1-1-151-1-5-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-2-3,VT2AU4-1-1-1-151-1-5-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-3-1,VT2AU4-1-1-1-151-1-6-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-3-2,VT2AU4-1-1-1-151-1-6-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-3-3,VT2AU4-1-1-1-151-1-6-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// >
		//
		// DOJ-TDM-AF01 19-08-21 07:20:20
		// M 1236 COMPLD
		// "VT2AU4-1-10-5-22-2-4-1,VT2AU4-1-1-1-151-1-7-1:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-10-5-22-2-4-2,VT2AU4-1-1-1-151-1-7-2:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-1-1-151-2-1-1,VT2AU4-1-10-5-22-2-4-3:2WAY:PRIMARYXC=SHELF:VT2:"
		// "VT2AU4-1-1-1-151-2-1-2,VT2AU4-1-10-5-22-2-5-1:2WAY:PRIMARYXC=SHELF:VT2:"
	}

	private List<OIV28160> rtrvCrsAll() throws Exception {

		String clctDtm = String.valueOf(FxApi.getDate());
		OIV28160 o;
		List<OIV28160> oList = new ArrayList<OIV28160>();
		int ctag = tl1Client.send("RTRV-CRS-ALL", dcn.getEquipTidVal(), "ALL,ALL", new String[] { "", "2WAY", "DISPLAY=PROV" });

		ORMF m = null;
		while (true) {
			m = getORMF(ctag);
			if (m == null)
				break;

			if (m.isCompld() || m.isRtrv()) {
				for (String s : m.getDataList()) {
					try {
						o = make28160(s);
						if (o != null) {
							o.setEquipId(dcn.getEquipId());
							o.setClctDtm(clctDtm);
							oList.add(o);
						}
					} catch (Exception e) {
						Logger.logger.error(e);
					}
				}
			} else {
				break;
			}

			if (m.isCompld())
				break;
		}

		return oList;
	}

	@Override
	protected NetPduMakerTL1 getPduMaker() {
		return new CienaTL1PduMaker();
	}

	@Override
	protected void logout() throws Exception {
		tl1Client.send("CANC-USER", null, null, new String[] { dcn.getLoginId() });
	}

	/**
	 * ACR-USER:DOJ-TDM-AF01:ADMIN:1234::ADMIN;
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	protected int sendLogin() throws Exception {
		return tl1Client.send("ACT-USER", dcn.getEquipTidVal(), dcn.getLoginId(), new String[] { "", dcn.getLoginPwd() });
	}

	@Override
	protected String getCharset() {
		return "utf-8";
	}
}

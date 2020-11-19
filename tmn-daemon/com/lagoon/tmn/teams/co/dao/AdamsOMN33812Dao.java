package com.lagoon.tmn.teams.co.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.AdamsCfg.TeamsAlarmCode;
import com.lagoon.tmn.teams.fxms.dbo.OMN33812;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;

/**
 * 장비로부터 가져온 구성정보를 DB에 적용한다.
 * 
 * @author subkjh(김종훈)
 *
 */
public class AdamsOMN33812Dao {

	public static void main(String[] args) {
		AdamsOMN33812Dao o = new AdamsOMN33812Dao();
		try {
			o.selectOMN33812("459", null, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private AdamsOMN33812Qid QID = new AdamsOMN33812Qid();

	@SuppressWarnings("unchecked")
	public List<OMN33812> selectOMN33812(String equipMfactCd, String equipMdlCd[], boolean isSyslogRcv) throws Exception {
		DbTrans tran = getDbTrans();
		try {
			tran.start();

			Map<String, Object> para = new HashMap<String, Object>();
			
			if (equipMfactCd != null) {
				para.put("equipMfactCd", equipMfactCd);
			}
			if (equipMdlCd != null) {
				StringBuffer sb = new StringBuffer();
				for (String s : equipMdlCd) {
					if (sb.length() > 0) {
						sb.append(", ");
					}
					sb.append("'").append(s).append("'");
				}
				para.put("equipMdlCd", sb.toString());
			}

			para.put("syslogRcvYn", (isSyslogRcv) ? "Y" : "N");

			return (List<OMN33812>) tran.selectQid2Res(QID.SELECT_OMN33812__BY_equipMfactCd_equipMdlCd, para);

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	public OMN33812 insertOMN33812(String equipMdlCd, String reason, String dablGrCd) throws Exception {

		DbTrans tran = getDbTrans();
		try {
			tran.start();

			long trmsNetEquipMsgMgmtNum = tran.selectQidLong(QID.SELECT_NEW_OMN33812_ID, null, -1);
			if (trmsNetEquipMsgMgmtNum < 0) {
				throw new Exception("ERROR : trmsNetEquipMsgMgmtNum ");
			}

			OMN33812 o = new OMN33812();

			o.setTrmsNetEquipMsgMgmtNum(trmsNetEquipMsgMgmtNum);

			o.setCmprCharStrVal(reason);
			o.setEquipMdlCd(equipMdlCd);
			o.setDablGrCd(dablGrCd);

//			o.setDablCd(TeamsAlarmCode.NOT_DEF_ALARM); // 미 정의 장애, 신규로 등록된 이벤트 장애
			o.setDablCd(TeamsAlarmCode.DABL_CD_EQUIP_ALARM); // 장비 장애(TODO 위에 "미정의 장애" 검토 필요)
			o.setDablAplyYn("Y");
			o.setEmcyDablYn("N");
			o.setNetDablAplyYn("Y"); // TODO 실 운영 반영시 Y로 수정하여 반영(테스트 시에는 링 장애 생성 OK)

			tran.execute(QID.INSERT_OMN33812, o);

			tran.commit();

			return o;

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.stop();
		}
	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(AdamsOMN33812Qid.QUERY_XML_FILE));
	}
}

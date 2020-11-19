package com.lagoon.tmn.teams.co.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.co.AdamsCfg;
import com.lagoon.tmn.teams.co.vo.PsVo;
import com.lagoon.tmn.teams.fxms.dbo.OCL28011;
import com.lagoon.tmn.teams.fxms.dbo.OCL28012;
import com.lagoon.tmn.teams.fxms.dbo.OCL28101;
import com.lagoon.tmn.teams.fxms.dbo.OCL28102;
import com.lagoon.tmn.teams.fxms.dbo.OCL28103;
import com.lagoon.tmn.teams.fxms.dbo.OCL28104;
import com.lagoon.tmn.teams.fxms.dbo.OCL28105;
import com.lagoon.tmn.teams.fxms.dbo.OCL28106;
import com.lagoon.tmn.teams.fxms.dbo.OCL28190;
import com.lagoon.tmn.teams.fxms.dbo.OCL28201;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221Ext;
import com.lagoon.tmn.teams.gw.vendor.woorinet.vo.Opn3000Vo;

import subkjh.bas.co.log.Logger;
import subkjh.bas.dao.control.DbTrans;
import subkjh.bas.dao.database.DBManager;
import fxms.bas.api.FxApi;

/**
 * OPN3000 일일점검 내역 처리
 * 
 * @author subkjh(김종훈)
 *
 */
public class Opn3000DailyDao {

	public static void main(String[] args) {
		LineVerifyDao dao = new LineVerifyDao();
		try {
			dao.checkLine();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Opn3000DailyQid QID;

	public Opn3000DailyDao() {
		QID = new Opn3000DailyQid();
	}

	public void insert(Opn3000Vo vo) throws Exception {

		DbTrans tran = getDbTrans();

		try {
			tran.start();

			long inspSerNum = tran.selectQidLong(QID.SELECT_INSP_SER_NUM__NEXT, null, -1);
			if (inspSerNum <= 0) {
				throw new Exception("INSP_SER_NUM : ");
			}

			// 장비정기점검이력 기록
			OCL28190 o = new OCL28190();
			o.setClctDtm(String.valueOf(FxApi.getDate()));
			o.setEquipId(vo.getEquipId());
			o.setInspSerNum(inspSerNum);
			o.setInspSussYn("Y");
			tran.execute(QID.INSERT_OCL28190, o);

			if (vo.getOiv10221List().size() > 0) {
				for (OIV10221Ext e : vo.getOiv10221List()) {
					e.setAuditDtm(new Timestamp(e.getVerApplyMstime()));
					e.setInspSerNum(inspSerNum);
					tran.execute(QID.MERGE_OIV10224, e);
				}
				tran.commit();
			}

			// OCL28011 테이블명 전송장비항목별건수수집내역
			if (vo.getOcl28011List().size() > 0) {
				for (OCL28011 o1 : vo.getOcl28011List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28011, vo.getOcl28011List());
				tran.commit();
			}

			// OCL28012 테이블명 전송장비통보설정수집내역
			if (vo.getOcl28012List().size() > 0) {
				for (OCL28012 o1 : vo.getOcl28012List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28012, vo.getOcl28012List());
				tran.commit();
			}

			// OCL28101 테이블명 전송장비광레벨수집내역

			if (vo.getOcl28101List().size() > 0) {
				for (OCL28101 o1 : vo.getOcl28101List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28101, vo.getOcl28101List());
				tran.commit();
			}

			// OCL28102 테이블명 전송장비성능수집내역

			if (vo.getOcl28102List().size() > 0) {
				for (OCL28102 o1 : vo.getOcl28102List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28102, vo.getOcl28102List());
				tran.commit();
			}

			// OCL28103 테이블명 전송장비CPU클럭수집내역

			if (vo.getOcl28103List().size() > 0) {
				for (OCL28103 o1 : vo.getOcl28103List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28103, vo.getOcl28103List());
				tran.commit();
			}

			// OCL28104 테이블명 전송장비클럭품질수집내역

			if (vo.getOcl28104List().size() > 0) {
				for (OCL28104 o1 : vo.getOcl28104List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28104, vo.getOcl28104List());
				tran.commit();
			}

			// OCL28105 테이블명 전송장비TRUNK용량수집내역

			if (vo.getOcl28105List().size() > 0) {
				for (OCL28105 o1 : vo.getOcl28105List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28105, vo.getOcl28105List());
				tran.commit();
			}

			// OCL28106 테이블명 전송장비FAN수집내역

			if (vo.getOcl28106List().size() > 0) {
				for (OCL28106 o1 : vo.getOcl28106List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28106, vo.getOcl28106List());
				tran.commit();
			}

			// OCL28201 테이블명 전송장비장애수집내역

			if (vo.getOcl28201List().size() > 0) {
				for (OCL28201 o1 : vo.getOcl28201List()) {
					o1.setInspSerNum(inspSerNum);
				}
				tran.execute(QID.INSERT_OCL28201, vo.getOcl28201List());
				tran.commit();
			}

		} catch (Exception e) {
			Logger.logger.error(e);
			throw e;
		} finally {
			tran.setDaoListener(null);
			tran.stop();
		}

		insertPs(vo);
	}

	private void insertPs(Opn3000Vo vo) {

		if (vo.getOiv10221List().size() > 0) {
			List<PsVo> psList = new ArrayList<PsVo>();
			PsVo ps;
			for (OIV10221 e : vo.getOiv10221List()) {
				if (e.getTmprVal() != null) {
					ps = new PsVo();
					ps.setEquipId(e.getEquipId());
					ps.setCardNm(e.getEquipConsItmNm());
					ps.setPortDesc(null);
					ps.setPsValue(e.getTmprVal().floatValue());
					psList.add(ps);
				}
			}
			try {
				// 온도 기록
				new AdamsOMN25112Dao().insertOMN25112(psList);
			} catch (Exception e) {
				Logger.logger.error(e);
			}
		}

	}

	private DbTrans getDbTrans() throws Exception {
		return DBManager.getMgr().getDataBase(AdamsCfg.ADAMSDB)
				.createDbTrans(AdamsCfg.getHomeDeployConfSql(Opn3000DailyQid.QUERY_XML_FILE));
	}

}

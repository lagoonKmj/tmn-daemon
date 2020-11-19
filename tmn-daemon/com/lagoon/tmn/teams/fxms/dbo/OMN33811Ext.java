package com.lagoon.tmn.teams.fxms.dbo;

import com.lagoon.tmn.teams.co.vo.IDcn;

@SuppressWarnings("serial")
public class OMN33811Ext extends OMN33811 {

	public IDcn dcn;

	public OMN33811Ext() {
		super();
	}

	public OMN33811Ext(OMN33810 omn33810) {
		super();
		setTrmsEquipDablNum(omn33810.getTrmsEquipDablNum());
		setDablGrCd(omn33810.getDablGrCd());
		setDablCd(omn33810.getDablCd());
		setEquipId(omn33810.getEquipId());
		setScardDesc(omn33810.getScardDesc());
		setPortDesc(omn33810.getPortDesc());
		setDablOccrLocDesc(omn33810.getDablOccrLocDesc());
		setEmsId(omn33810.getEmsId());
		setEmsAlmVal(omn33810.getEmsAlmVal());
		setAllMsgCtt(omn33810.getAllMsgCtt());
	}

	private String allMsgCtt; /* ALL_MSG_CTT */

	private String equipMdlCd; /* EQUIP_MDL_CD 장비모델코드 */

	private String rlseDtm; /* RLSE_DTM 해제일시 */

	public boolean isClear = false;;

	public String getAllMsgCtt() {
		return allMsgCtt;
	}

	public void setAllMsgCtt(String allMsgCtt) {
		this.allMsgCtt = allMsgCtt;
	}

	public String getAlarmKey() {
		return this.getAllMsgCtt();
	}

	public boolean isClear() {

		return (isClear == true ? true : (getRlseDtm() != null));
	}

	public void setClear(boolean isClear) {
		this.isClear = isClear;
	}

	public String getRlseDtm() {
		return rlseDtm;
	}

	public void setRlseDtm(String rlseDtm) {
		this.rlseDtm = rlseDtm;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	public IDcn getDcn() {
		return dcn;
	}

	public void setDcn(IDcn dcn) {
		this.dcn = dcn;
	}

}

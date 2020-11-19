package com.lagoon.tmn.teams.gw.vendor.woorinet.vo;

import java.util.ArrayList;
import java.util.List;

import com.lagoon.tmn.teams.fxms.dbo.OCL28011;
import com.lagoon.tmn.teams.fxms.dbo.OCL28012;
import com.lagoon.tmn.teams.fxms.dbo.OCL28101;
import com.lagoon.tmn.teams.fxms.dbo.OCL28102;
import com.lagoon.tmn.teams.fxms.dbo.OCL28103;
import com.lagoon.tmn.teams.fxms.dbo.OCL28104;
import com.lagoon.tmn.teams.fxms.dbo.OCL28105;
import com.lagoon.tmn.teams.fxms.dbo.OCL28106;
import com.lagoon.tmn.teams.fxms.dbo.OCL28201;
import com.lagoon.tmn.teams.fxms.dbo.OIV10221Ext;

/**
 * OPN-3000 일일점검 결과를 가지는 VO
 * 
 * @author subkjh(김종훈)
 *
 */
public class Opn3000Vo {

	private List<OCL28011> ocl28011List = new ArrayList<OCL28011>(); // 전송장비항목별건수수집내역
	private List<OCL28012> ocl28012List = new ArrayList<OCL28012>(); // 전송장비통보설정수집내역
	private List<OCL28101> ocl28101List = new ArrayList<OCL28101>(); // 전송장비광레벨수집내역
	private List<OCL28102> ocl28102List = new ArrayList<OCL28102>(); // 전송장비성능수집내역
	private List<OCL28103> ocl28103List = new ArrayList<OCL28103>(); // 전송장비CPU클럭수집내역
	private List<OCL28104> ocl28104List = new ArrayList<OCL28104>(); // 전송장비클럭품질수집내역
	private List<OCL28105> ocl28105List = new ArrayList<OCL28105>(); // 전송장비TRUNK용량수집내역
	private List<OCL28106> ocl28106List = new ArrayList<OCL28106>(); // 전송장비FAN수집내역
	private List<OCL28201> ocl28201List = new ArrayList<OCL28201>(); // 전송장비장애수집내역
	private List<OIV10221Ext> oiv10221List = new ArrayList<OIV10221Ext>(); // 전송장비구성품수집내역
	private String equipId;

	public Opn3000Vo() {
	}

	public String getEquipId() {
		return equipId;
	}

	public List<OCL28011> getOcl28011List() {
		return ocl28011List;
	}

	public List<OCL28012> getOcl28012List() {
		return ocl28012List;
	}

	public List<OCL28101> getOcl28101List() {
		return ocl28101List;
	}

	public List<OCL28102> getOcl28102List() {
		return ocl28102List;
	}

	public List<OCL28103> getOcl28103List() {
		return ocl28103List;
	}

	public List<OCL28104> getOcl28104List() {
		return ocl28104List;
	}

	public List<OCL28105> getOcl28105List() {
		return ocl28105List;
	}

	public List<OCL28106> getOcl28106List() {
		return ocl28106List;
	}

	public List<OCL28201> getOcl28201List() {
		return ocl28201List;
	}

	public OIV10221Ext getOIV10221(String slotNm) {
		for (OIV10221Ext o : oiv10221List) {
			if (o.getEquipConsItmNm().equals(slotNm)) {
				return o;
			}
		}
		return null;
	}

	public List<OIV10221Ext> getOiv10221List() {
		return oiv10221List;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public void setOcl28011List(List<OCL28011> ocl28011List) {
		this.ocl28011List = ocl28011List;
	}

	public void setOcl28012List(List<OCL28012> ocl28012List) {
		this.ocl28012List = ocl28012List;
	}

	public void setOcl28101List(List<OCL28101> ocl28101List) {
		this.ocl28101List = ocl28101List;
	}

	public void setOcl28102List(List<OCL28102> ocl28102List) {
		this.ocl28102List = ocl28102List;
	}

	public void setOcl28103List(List<OCL28103> ocl28103List) {
		this.ocl28103List = ocl28103List;
	}

	public void setOcl28104List(List<OCL28104> ocl28104List) {
		this.ocl28104List = ocl28104List;
	}

	public void setOcl28105List(List<OCL28105> ocl28105List) {
		this.ocl28105List = ocl28105List;
	}

	public void setOcl28106List(List<OCL28106> ocl28106List) {
		this.ocl28106List = ocl28106List;
	}

	public void setOcl28201List(List<OCL28201> ocl28201List) {
		this.ocl28201List = ocl28201List;
	}

	public void setOiv10221List(List<OIV10221Ext> oiv10221List) {
		this.oiv10221List = oiv10221List;
	}

}

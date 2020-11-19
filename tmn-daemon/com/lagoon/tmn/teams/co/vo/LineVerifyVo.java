package com.lagoon.tmn.teams.co.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 회선 검증용 회선 정보
 * 
 * @author subkjh(김종훈)
 *
 */
@SuppressWarnings("unused")
public class LineVerifyVo {

	private String subTpoCd;
	private String subTpoNm;
	private String supTpoCd;
	private String supTpoNm;
	private String lineNum;
	private String lineNm;
	private String trmsNetDirnCd;

	private List<LineVerifyPathVo> pathList = new ArrayList<LineVerifyPathVo>();
	private List<LineVerifyTpoVo> tpoList = new ArrayList<LineVerifyTpoVo>();

	private final String DIRN_UNKNOWN = "0";
	private final String DIRN_Outgoing = "1";
	private final String DIRN_Incoming = "2";
	private final String DIRN_BOTH = "3";

	public void addPath(LineVerifyPathVo vo) {
		pathList.add(vo);
	}

	public void addTpo(LineVerifyTpoVo vo) {
		tpoList.add(vo);
	}

	public String check() {
		if (pathList.size() == 0) {
			return "선번 없음";
		} else {

			StringBuffer sb = new StringBuffer();

			LineVerifyPathVo firstVo = pathList.get(0);
			LineVerifyPathVo lastVo = pathList.get(pathList.size() - 1);

			if (firstVo.getMgmtTpoCd() == null) {
				return "상위국사 없음";
			}
			if (lastVo.getMgmtTpoCd() == null) {
				return "하위국사 없음";
			}

			if (firstVo.getMgmtTpoCd().equals(supTpoCd) == false && firstVo.getTpoCd().equals(supTpoCd) == false) {
				sb.append("상위국사 다름 (" + firstVo.getTpoCd() + "," + supTpoCd + ")");
			}
			if (lastVo.getMgmtTpoCd().equals(subTpoCd) == false && lastVo.getTpoCd().equals(supTpoCd) == false) {
				sb.append("하위국사 다름 (" + lastVo.getTpoCd() + "," + subTpoCd + ")");
			}

			if (sb.length() > 0) {
				sb = new StringBuffer();
				lastVo = pathList.get(0);
				firstVo = pathList.get(pathList.size() - 1);

				if (firstVo.getMgmtTpoCd().equals(supTpoCd) == false && firstVo.getTpoCd().equals(supTpoCd) == false) {
					sb.append("상위국사 다름 (" + firstVo.getTpoCd() + "," + supTpoCd + ")");
				}
				if (lastVo.getMgmtTpoCd().equals(subTpoCd) == false && lastVo.getTpoCd().equals(supTpoCd) == false) {
					sb.append("하위국사 다름 (" + lastVo.getTpoCd() + "," + subTpoCd + ")");
				}

			}

			return sb.length() == 0 ? "OK" : sb.toString();
		}

	}

	public String checkTpo() {
		if (tpoList.size() == 0) {
			return "국사 없음";
		} else {

			StringBuffer sb = new StringBuffer();

			LineVerifyTpoVo firstVo = tpoList.get(0);
			LineVerifyTpoVo lastVo = tpoList.get(tpoList.size() - 1);

			if (firstVo.getTpoCd().equals(supTpoCd) == false) {
				sb.append("상위국사 다름(" + firstVo.getTpoCd() + "," + supTpoCd + ")");
			}
			if (lastVo.getTpoCd().equals(subTpoCd) == false) {
				sb.append("하위국사 다름(" + lastVo.getTpoCd() + "," + subTpoCd + ")");
			}

			return sb.length() == 0 ? "OK" : sb.toString();
		}

	}

	public String getSubTpoCd() {
		return subTpoCd;
	}

	public void setSubTpoCd(String subTpoCd) {
		this.subTpoCd = subTpoCd;
	}

	public String getSubTpoNm() {
		return subTpoNm;
	}

	public void setSubTpoNm(String subTpoNm) {
		this.subTpoNm = subTpoNm;
	}

	public String getSupTpoCd() {
		return supTpoCd;
	}

	public void setSupTpoCd(String supTpoCd) {
		this.supTpoCd = supTpoCd;
	}

	public String getSupTpoNm() {
		return supTpoNm;
	}

	public void setSupTpoNm(String supTpoNm) {
		this.supTpoNm = supTpoNm;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getLineNm() {
		return lineNm;
	}

	public void setLineNm(String lineNm) {
		this.lineNm = lineNm;
	}

	public String getTrmsNetDirnCd() {
		return trmsNetDirnCd;
	}

	public void setTrmsNetDirnCd(String trmsNetDirnCd) {
		this.trmsNetDirnCd = trmsNetDirnCd;
	}

}

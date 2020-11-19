package com.lagoon.tmn.teams.co.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.lagoon.tmn.teams.co.AdamsCfg.TeamsCode;
import com.lagoon.tmn.teams.fxms.dbo.OMN33810;

import subkjh.bas.co.log.Logger;

/**
 * 
 * @author subkjh
 *
 *         ST_PARSED_ALARM_RAW_DATA<br>
 * 
 *         DCN 내용을 파싱하여 얻은 데이터
 */
public class TrmsNetEventVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3017875327561582453L;
	public static final String TDM_CRS = "TDM-CRS";
	
	public static final int CLEAR_EVENT_ALARM = 0;
	public static final int OCCUR_EVENT_ALARM = 1;
	private String equipId; /* 장비ID(찾은값) */
	private String ipAddr; /* 장비IP주소(받은값) */

	private String tid; /* 장비TID(받은값) */

	private String equipMdlCd; /* 장비모델코드(찾은값) */
	private String fullMsg; /* 받은전체메시지 */
	private String cardDescr; /* 카드명(분석값) */
	private String portDescr;/* 포트명(분석값) */

	private String location;/* 위치(분석값) */
	private String reason; /* 사유(분석값) */

	private String dablCd; /* 장애코드 */
	private String dablGrCd; /* 장애등급 코드 */
	private long occurTime; /* 발생일시(분석값) */

	private long clearTime;/* 해제일시(분석값) */
	private long recvTime; /* 받은시간 */

	private String emsId; /* EMSID(EMS를 통해서 받은 이벤트인 경우 */

	private String emsAlmVal; /* EMS알람값(EMS를 통해서 받은 이벤트인 경우 */

	private int failEvtNm = 0; /* fireEvent 실패건수 */

	/** 전송망장비메시지관리번호 */
	private long trmsNetEquipMsgMgmtNum;

	/** 전송장비장애번호(전송망 장비장애를 생성할때 필요한 필드, 분석에서 사용) **/
	private long trmsEquipDablNum;

	/** 피해회선수(전송망 장비장애를 생성할때 필요한 필드, 분석에서 사용) **/
	private int dmgLineCnt;

	/** 장애중복횟수(전송망 장비장애를 생성할때 필요한 필드, 분석에서 사용) **/
	private long dablDupCnt;

	/** 작업장애여부(전송망 장비장애를 생성할때 필요한 필드, 분석에서 사용) **/
	private boolean operDablYn;

	/** 장애 적용 여부 */
	private String dablAplyYn = "N";

	private IDcn dcn;

	public IDcn getDcn() {
		return dcn;
	}

	public void setDcn(IDcn dcn) {
		this.dcn = dcn;
	}

	/** OMN33812(전송망메세지) 로직을 무시(시스로그, 트랩) */
	private boolean ignoreOmn33812 = false;

	public boolean isIgnoreOmn33812() {
		return ignoreOmn33812;
	}

	public void setIgnoreOmn33812(boolean ignoreOmn33812) {
		this.ignoreOmn33812 = ignoreOmn33812;
	}

	public String getCardDescr() {
		return cardDescr;
	}

	public long getClearTime() {
		return clearTime;
	}

	public String getDablCd() {
		return dablCd;
	}

	public long getDablDupCnt() {
		return dablDupCnt;
	}

	public String getDablGrCd() {
		return dablGrCd;
	}

	public int getDmgLineCnt() {
		return dmgLineCnt;
	}

	public String getEmsAlmVal() {
		return emsAlmVal;
	}

	public String getEmsId() {
		return emsId;
	}

	public String getEquipId() {
		return equipId;
	}

	public String getEquipMdlCd() {
		return equipMdlCd;
	}

	/**
	 * 이벤트 타입 을 반환
	 *
	 * <pre>
	 * 받은 해제 일시로 이벤트 판단
	 * </pre>
	 * 
	 * @return
	 */
	public int getEventType() {
		if (clearTime > 0L) {
			return TrmsNetEventVo.CLEAR_EVENT_ALARM;
		}
		return TrmsNetEventVo.OCCUR_EVENT_ALARM;
	}

	public int getFailEvtNm() {
		return failEvtNm;
	}

	public String getFullMsg() {
		return fullMsg;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public String getLocation() {
		return location;
	}

	public long getOccurTime() {
		return occurTime;
	}

	public String getOccurTimeStr() {
		return String.valueOf(occurTime);
	}

	public String getPortDescr() {
		return portDescr;
	}

	public String getReason() {
		return reason;
	}

	public long getRecvTime() {
		return recvTime;
	}

	public String getTid() {
		return tid;
	}

	public long getTrmsEquipDablNum() {
		return trmsEquipDablNum;
	}

	public long getTrmsNetEquipMsgMgmtNum() {
		return trmsNetEquipMsgMgmtNum;
	}

	/**
	 * 장비ID, TID, 로케이션, 리즌이 동일하면 중복장애로 판단
	 * 
	 * <pre>
	 * 	"CLEAR_FLAG=:f1<char[%d]> AND NE_ID=:f2<int> AND TID=:f3<char[%d]> AND LOCATION=:f4<char[%d]> AND REASON=:f5<char[%d]> "\
	 * </pre>
	 * 
	 * @param trmsAlarmMap
	 */
	public void increaseDablDupCnt(Map<Long, OMN33810> trmsAlarmMap) {

		if (equipId == null || tid == null || location == null
				|| reason == null) {
			return;
		}

		for (OMN33810 omn33810 : trmsAlarmMap.values()) {

			if (equipId.equals(omn33810.getEquipId())
					&& tid.equals(omn33810.getEquipTidVal())
					&& location.equals(omn33810.getDablOccrLocDesc())
					&& reason.equals(omn33810.getCmprCharStrVal())) {

				dablDupCnt++;
			}
		}
	}

	public boolean isDablAplyYn() {
		return dablAplyYn.equals("Y");
	}

	/**
	 * 망장애 발생 여부
	 * 
	 * <pre>
	 *   발생시간이 06시 이후 
	 * 	 포트Descr 존재
	 * </pre>
	 * 
	 * @return
	 */
	public boolean isMakeOccurNetAlarm() {

		int hour = Integer.parseInt(getOccurTimeStr().substring(8, 10));

		boolean isMake = (portDescr != null && portDescr.trim().length() > 0 && hour > 5);

		if (isMake == false) {
			Logger.logger.info("망 장애 발생 하지 않음, 이벤트 메세지 : {}", fullMsg);
		}

		return isMake;
	}

	public boolean isOperDablYn() {
		return operDablYn;
	}

	/**
	 * 사유 값이 LOS 이면 true를 반환
	 * 
	 * @return
	 */
	public boolean isReasonLos() {
		if (reason == null) {
			return false;
		}
		return reason.toUpperCase().equals(TeamsCode.REASON_LOS);
	}

	/**
	 * 현재장애와 일치 하는지 확인
	 * 
	 * <pre>
	 * 	장비ID, 포트DESCR, 리즌(비교문자열) 이 맞으면 true
	 * </pre>
	 * 
	 * @param omn33810
	 * @return
	 */
	public boolean match(OMN33810 omn33810) {
		
		boolean isOk = (equipId.equals(omn33810.getEquipId()) && portDescr.equals(omn33810.getPortDesc()));
		if (isOk ==true && !(getPortDescr().equals(TrmsNetEventVo.TDM_CRS))) {
			isOk = reason.equals(omn33810.getCmprCharStrVal());
		}
		return isOk;
	}

	public void setCardDescr(String cardDescr) {
		this.cardDescr = cardDescr;
	}

	public void setClearTime(String hstimeRelease) {
		this.clearTime = setHstimeToLong(hstimeRelease);
	}

	public void setDablAplyYn(String dablAplyYn) {
		this.dablAplyYn = dablAplyYn;
	}

	public void setDablCd(String dablCd) {
		this.dablCd = dablCd;
	}

	public void setDablDupCnt(long dablDupCnt) {
		this.dablDupCnt = dablDupCnt;
	}

	public void setDablGrCd(String dablGrCd) {
		this.dablGrCd = dablGrCd;
	}

	public void setDmgLineCnt(int dmgLineCnt) {
		this.dmgLineCnt = dmgLineCnt;
	}

	public void setDmgLineCnt(List<?> list) {
		if (list == null) {
			dmgLineCnt = 0;
		} else {
			this.dmgLineCnt = list.size();
		}
	}

	public void setEmsAlmVal(String emsAlmVal) {
		this.emsAlmVal = emsAlmVal;
	}

	public void setEmsId(String emsId) {
		this.emsId = emsId;
	}

	public void setEquipId(String equipId) {
		this.equipId = equipId;
	}

	public void setEquipMdlCd(String equipMdlCd) {
		this.equipMdlCd = equipMdlCd;
	}

	public void setFailEvtNm(int failEvtNm) {
		this.failEvtNm = failEvtNm;
	}

	public void setFullMsg(String fullMsg) {
		this.fullMsg = fullMsg;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setOccurTime(long occurTime) {
		this.occurTime = occurTime;
	}

	public void setOccurTime(String hstimeCreate) {
		this.occurTime = setHstimeToLong(hstimeCreate);
	}

	public void setOperDablYn(boolean operDablYn) {
		this.operDablYn = operDablYn;
	}

	public void setPortDescr(String portDescr) {
		this.portDescr = portDescr;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setRecvTime(long recvTime) {
		this.recvTime = recvTime;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public void setTrmsEquipDablNum(long trmsEquipDablNum) {
		this.trmsEquipDablNum = trmsEquipDablNum;
	}

	public void setTrmsNetEquipMsgMgmtNum(long trmsNetEquipMsgMgmtNum) {
		this.trmsNetEquipMsgMgmtNum = trmsNetEquipMsgMgmtNum;
	}

	private Long setHstimeToLong(String str) {

		if (str == null) {
			return 0L;
		}

		Long retValue = null;
		try {
			char chs[] = str.toCharArray();
			String hstime = "";
			for (char ch : chs) {
				if (ch >= '0' && ch <= '9')
					hstime += ch;
			}

			if (hstime.length() == 14) {
				retValue = Long.parseLong(hstime);
			} else if (hstime.length() == 12) {
				retValue = Long.parseLong("20" + hstime);
			}
		} catch (Exception e) {

		}
		return retValue;
	}

	@Override
	public String toString() {
		return "TrmsNetEventVo [equipId=" + equipId + ", ipAddr=" + ipAddr
				+ ", tid=" + tid + ", equipMdlCd=" + equipMdlCd + ", fullMsg="
				+ fullMsg + ", cardDescr=" + cardDescr + ", portDescr="
				+ portDescr + ", location=" + location + ", reason=" + reason
				+ ", dablCd=" + dablCd + ", dablGrCd=" + dablGrCd
				+ ", occurTime=" + occurTime + ", clearTime=" + clearTime
				+ ", recvTime=" + recvTime + ", emsId=" + emsId
				+ ", emsAlmVal=" + emsAlmVal + ", trmsNetEquipMsgMgmtNum="
				+ trmsNetEquipMsgMgmtNum + ", trmsEquipDablNum="
				+ trmsEquipDablNum + ", dmgLineCnt=" + dmgLineCnt
				+ ", dablDupCnt=" + dablDupCnt + "]";
	}

}

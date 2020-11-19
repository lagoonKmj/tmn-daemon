package com.lagoon.tmn.teams.co.vo;

public class DataStateVo {

	private boolean neChanged;

	private boolean networkChanged;

	private boolean lineChanged;

	private long lastMstime;

	public long getLastMstime() {
		return lastMstime;
	}

	public boolean isLineChanged() {
		return lineChanged;
	}

	public boolean isNeChanged() {
		return neChanged;
	}

	public boolean isNetworkChanged() {
		return networkChanged;
	}

	public void setLastMstime(long lastMstime) {
		this.lastMstime = lastMstime;
	}

	public void setLineChanged(boolean lineChanged) {
		this.lineChanged = lineChanged;
	}

	public void setNeChanged(boolean neChanged) {
		this.neChanged = neChanged;
	}

	public void setNetworkChanged(boolean networkChanged) {
		this.networkChanged = networkChanged;
	}

}

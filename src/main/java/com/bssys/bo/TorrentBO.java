package com.bssys.bo;

public class TorrentBO {
	private String status;
	private float progress;
	private String torrentId;
	
	private String fileName;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public float getProgress() {
		return progress;
	}
	public void setProgress(float progress) {
		this.progress = progress;
	}
	public String getTorrentId() {
		return torrentId;
	}
	public void setTorrentId(String torrentId) {
		this.torrentId = torrentId;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}

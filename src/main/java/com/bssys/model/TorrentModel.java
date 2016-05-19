package com.bssys.model;

import java.util.List;

import com.bssys.bo.TorrentBO;

public class TorrentModel {
	
	private List<TorrentBO> torrents;

	public List<TorrentBO> getTorrents() {
		return torrents;
	}

	public void setTorrents(List<TorrentBO> torrents) {
		this.torrents = torrents;
	}

}

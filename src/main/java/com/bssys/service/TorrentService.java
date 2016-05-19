package com.bssys.service;

import java.util.ArrayList;
import java.util.List;

import com.bssys.bo.TorrentBO;
import com.bssys.model.TorrentModel;

public class TorrentService {
	
	private static TorrentService instance;
	
	private List<Torrent> torrentList = new ArrayList<Torrent>();
	
	private TorrentService() {}
	
	public static synchronized TorrentService getInstance() {
		if (instance == null){
			instance = new TorrentService();
		}
		return instance;
	}
	
	public void addTorrent(Torrent torrent) {
		torrentList.add(torrent);
		torrent.start();
	}
	
	public void deleteTorrent(Torrent torrent){
		torrentList.remove(torrent);
		torrent.stop(false);
	}
	
	
	
	public List<Torrent> getTorrentList() {
		return torrentList;
	}

	public TorrentModel getTorrentModel(){
		TorrentModel torrentModel = new TorrentModel();
		List<TorrentBO> torrentBOs = new ArrayList<TorrentBO>();
		for(Torrent torrent: torrentList){
			torrentBOs.add(torrent.getTorrentBO());
		}
		torrentModel.setTorrents(torrentBOs);
		return torrentModel;
	}
}

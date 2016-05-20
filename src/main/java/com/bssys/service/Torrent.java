package com.bssys.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import com.bssys.bo.TorrentBO;
import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;

public class Torrent {
	private Client client;
	private TorrentBO torrentBO;

	public Torrent(byte[] torrentFile, String fileName, File dest) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
		SharedTorrent sharedTorrent = new SharedTorrent(torrentFile, dest);
		client = new Client(InetAddress.getLocalHost(), sharedTorrent);
		client.setMaxDownloadRate(50.0);
		client.setMaxUploadRate(50.0);
		
		torrentBO = new TorrentBO();
		torrentBO.setTorrentId(UUID.randomUUID().toString());
		torrentBO.setProgress(client.getTorrent().getCompletion());
		torrentBO.setStatus("info");
		torrentBO.setFileName(fileName);
	}

	public synchronized void start() {
		client.download();
		client.deleteObservers();

		client.addObserver(new Observer() {
			public void update(Observable observable, Object data) {
				Client client = (Client) observable;
				float progress = client.getTorrent().getCompletion();
				if (progress==100){
					torrentBO.setStatus("success");
					stop(false);
				}
				torrentBO.setProgress(progress);
			}
		});
	}

	public synchronized void stop(boolean wait) {
		client.stop(wait);
		client.deleteObservers();
	}

	public synchronized TorrentBO getTorrentBO() {
		return torrentBO;
	}

}

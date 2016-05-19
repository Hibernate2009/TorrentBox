package com.bssys.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.bssys.service.Torrent;
import com.bssys.service.TorrentService;

/**
 * Application Lifecycle Listener implementation class TorrentAppListener
 *
 */
public class TorrentAppListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public TorrentAppListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	List<Torrent> torrentList = TorrentService.getInstance().getTorrentList();
    	for (Torrent torrent: torrentList){
    		torrent.stop(false);
    	}
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}

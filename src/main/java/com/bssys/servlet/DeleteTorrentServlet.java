package com.bssys.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtilsBean;

import com.bssys.form.DeleteForm;
import com.bssys.form.ManagerForm;
import com.bssys.model.TorrentModel;
import com.bssys.service.Torrent;
import com.bssys.service.TorrentService;

/**
 * Servlet implementation class DeleteTorrentServlet
 */
public class DeleteTorrentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTorrentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		DeleteForm managerForm = new DeleteForm();
		Map<String, String[]> directoryModelMap = request.getParameterMap();
		BeanUtilsBean beanUtilsBean = new BeanUtilsBean();
		try {
			beanUtilsBean.populate(managerForm, directoryModelMap);
			String torrentId = managerForm.getTorrentId();
			List<Torrent> torrentList = TorrentService.getInstance().getTorrentList();
			Iterator<Torrent> i = torrentList.iterator();
			while (i.hasNext()) {
				Torrent torrent = i.next(); // must be called before you can call i.remove()
				if (torrentId.equals(torrent.getTorrentBO().getTorrentId())){
					torrent.stop(false);
					i.remove();
				}
			}
			TorrentModel torrentModel = TorrentService.getInstance().getTorrentModel();
			session.setAttribute("torrentModel", torrentModel);
			response.sendRedirect("index.jsp");
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

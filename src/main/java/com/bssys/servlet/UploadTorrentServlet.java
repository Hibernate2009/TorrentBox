package com.bssys.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.bssys.bo.TorrentBO;
import com.bssys.model.TorrentModel;
import com.bssys.service.Torrent;
import com.bssys.service.TorrentService;

/**
 * Servlet implementation class UploadTorrentServlet
 */

public class UploadTorrentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean isMultipart;
	private String filePath;
	private int maxFileSize = 50 * 1024;
	private int maxMemSize = 4 * 1024;
	private File file;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadTorrentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();

		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			
			while (it.hasNext()) {
				
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					fileItem.getFieldName();
					fileItem.getString();
				} else {
					
					InputStream inputStream = null;
					
					try{
						inputStream = fileItem.getInputStream();
						byte[] torrentContent = IOUtils.toByteArray(inputStream);
						String fileName = fileItem.getName();
						List<Torrent> torrentList = TorrentService.getInstance().getTorrentList();
						boolean flag= false;
						for(Torrent torrent: torrentList){
							if (fileName.equals(torrent.getTorrentBO().getFileName())){
								flag=true;
							}
						}
						if (!flag){
							Torrent torrent = new Torrent(torrentContent, fileName, new File("D:/torrent/to/output/directory"));
							TorrentService.getInstance().addTorrent(torrent);
						}
						
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}finally{
						IOUtils.closeQuietly(inputStream);
					}
				}
			}
			
			TorrentModel torrentModel = TorrentService.getInstance().getTorrentModel();
			session.setAttribute("torrentModel", torrentModel);
			response.sendRedirect("index.jsp");
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check that we have a file upload request
		doGet(request, response);
	}

}

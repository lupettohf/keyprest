package keyprest.housekeeping;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import keyprest.store.Product;
import keyprest.store.ProductUtils;
import keyprest.user.UserUtils;

@WebServlet(name = "ImageImporterServlet", urlPatterns = {"/uploadimage"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class ImageImporterServlet extends HttpServlet {
	
	private final String UPLOAD_DIRECTORY = "/static/img/products";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       
			HttpSession session = request.getSession();
			String SessionKey = (String) session.getAttribute("sessionkey");
			String ProductID = request.getParameter("productid");
					
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");

			String applicationPath = request.getServletContext().getRealPath("");

			String uploadFilePath = applicationPath + File.separator + UPLOAD_DIRECTORY;

			File uploadFolder = new File(uploadFilePath);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdirs();
			}
			
			for (Part part : request.getParts()) {
				if (part != null && part.getSize() > 0) {
					String fileName = ProductID + ".png";
					String contentType = part.getContentType();
					
					if(contentType.contains("image")){
						part.write(uploadFilePath + File.separator + fileName);
					}
						
					response.sendRedirect("housekeeping");
				}
			}
	    }
}

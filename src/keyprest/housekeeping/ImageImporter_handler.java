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
import keyprest.store.Product_utils;
import keyprest.user.User_utils;

@WebServlet(name = "ImageImporter_handler", urlPatterns = {"/uploadimage"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class ImageImporter_handler extends HttpServlet {
	
	private final String UPLOAD_DIRECTORY = "/static/images/products";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       
			HttpSession session = request.getSession();
			String SessionKey = (String) session.getAttribute("sessionkey");
			String ProductID = request.getParameter("productid");
			
		    String appPath = request.getServletContext().getRealPath("");
		    String savePath = appPath + File.separator + UPLOAD_DIRECTORY;
		         
			File game_image_dir = new File(savePath);
			if (!game_image_dir.exists()) {
				game_image_dir.mkdir();
			}
			
			 System.out.println("Game_TITLES upload dir is: " + game_image_dir.getAbsolutePath());

			for (Part part : request.getParts()) {
				String fileName = ProductID + ".png";
				if (fileName != null && !fileName.equals("")) {
					part.write(savePath + File.separator + fileName);
				}
			}

			RequestDispatcher view = request.getRequestDispatcher("/housekeeping");
			view.forward(request, response);
	    }
}

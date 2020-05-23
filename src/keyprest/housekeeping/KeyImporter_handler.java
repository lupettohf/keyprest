package keyprest.housekeeping;

import java.io.BufferedReader;
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

@WebServlet(name = "KeyImporter_handler", urlPatterns = {"/importkey"})
public class KeyImporter_handler extends HttpServlet{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		int _imported = 0;
		int ProductID = 2;
		
		
		if(ProductID <=0) { request.setAttribute("error", "L'id prodotto é errato."); }
		
		try {
			if(SessionKey.isEmpty() || !(User_utils.isAdmin(SessionKey)))
			{
			    if(ServletFileUpload.isMultipartContent(request)){
			        try {
			            List<FileItem> multiparts = new ServletFileUpload(
			                                     new DiskFileItemFactory()).parseRequest(request);
			          
			            for(FileItem item : multiparts){
			                if(!item.isFormField()){
			                    _imported = importKeys(item, ProductID);
			                }
			            }
			       
			           //File uploaded successfully
			           request.setAttribute("message", "Imported " + _imported + "keys.");
			        } catch (Exception ex) {
			           request.setAttribute("message", "File Upload Failed due to " + ex);
			        }          
			     
			    }else{
			        request.setAttribute("message",
			                             "Sorry this Servlet only handles file upload request");
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	private static int importKeys(FileItem file, int product_id)
	{
		Scanner scanner = new Scanner(file.getString());
		int _imported = 0;
		
		while (scanner.hasNextLine()) {
			  String line = scanner.nextLine();
			  try {
				if(Product_utils.importKey(product_id, line)) {_imported++;}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return _imported;
	}
}

package keyprest.housekeeping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import javax.servlet.http.Part;

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
		RequestDispatcher req = request.getRequestDispatcher("/template/pages/housekeeping.jsp");
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		String Keys = request.getParameter("keys");
		String ProductID = request.getParameter("productid");
		int _ProductID = 0;
		int _Imported = 0;		
		
		if(ProductID != null)
		{
			try {
				_ProductID = Integer.parseInt(ProductID);
			} catch (NumberFormatException e) {
				session.setAttribute("error", "L'id prodotto é errato.");
			}
		}
		
		if(_ProductID <=0) { session.setAttribute("error", "L'id prodotto é errato."); }
		
		try {
			if(SessionKey.isEmpty() || !(User_utils.isAdmin(SessionKey)))
			{
				if(Keys.isEmpty()) {
					session.setAttribute("error", "Non vi sono pervenute chiavi.");
				} else {
					_Imported = importKeys(Keys, _ProductID);
					
					if(_Imported > 0) { 
						session.setAttribute("success", "Sono state importate " + _Imported + " chiavi.");
					} else {
						session.setAttribute("error", "Si é verificato un errore nell'importazione.");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		req.include(request, response);
    }
    
	private static int importKeys(String Keys, int product_id)
	{
		Scanner scanner = new Scanner(Keys);
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
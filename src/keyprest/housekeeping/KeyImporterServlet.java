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
import keyprest.store.ProductUtils;
import keyprest.user.UserUtils;

@WebServlet(name = "KeyImporterServlet", urlPatterns = {"/importkeys"})
public class KeyImporterServlet extends HttpServlet{
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/housekeeping/index.jsp");
	
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
		
		if(_ProductID <=0) {session.setAttribute("error", "L'id prodotto é errato."); }
		
		if(SessionKey == null || !(UserUtils.isAdmin(SessionKey)))
		{
			 System.out.println(Keys + "0");
			if(Keys.isEmpty()) {
				session.setAttribute("error", "Non vi sono pervenute chiavi.");
				 System.out.println(Keys + "1");
			} else {
				_Imported = importKeys(Keys, _ProductID);
				
				if(_Imported > 0) { 
					session.setAttribute("success", "Sono state importate " + _Imported + " chiavi.");
				} else {
					session.setAttribute("error", "Si é verificato un errore nell'importazione.");
				}
			}
		}
		
		req.include(request, response);
    }
    
	private static int importKeys(String Keys, int product_id)
	{
		
		Scanner scanner = new Scanner(Keys);
		int _imported = 0;
		while (scanner.hasNextLine()) {
			  String line = scanner.nextLine();
			  System.out.println(line);
			  try {
				if(ProductUtils.importKey(product_id, line)) {_imported++;}
			} catch (SQLException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		return _imported;
	}
}

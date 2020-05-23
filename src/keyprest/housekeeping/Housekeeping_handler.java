
package keyprest.housekeeping;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.database.connectionManager;
import keyprest.store.Product;
import keyprest.store.Product_utils;
import keyprest.user.User_utils;

@WebServlet(name = "Housekeeping_handler", urlPatterns = {"/housekeeping"})
public class Housekeeping_handler extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		try {
			connectionManager.createConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		if(SessionKey == null)
		{
			req = request.getRequestDispatcher("/template/pages/login.jsp");
		} else
			try {
				if(User_utils.isAdmin(SessionKey)) {
					req = request.getRequestDispatcher("/template/pages/housekeeping.jsp");
					
					int start_id = 1;
					int end_id = 10;
					
					// Manda la lista dei prodotti
						
					session.setAttribute("products", Product_utils.retriveProducts(start_id, end_id));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		req.include(request, response);
		
	}
	
}

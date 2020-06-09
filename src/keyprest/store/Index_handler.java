package keyprest.store;

import java.io.IOException;
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
import keyprest.user.User_utils;

@WebServlet(name = "Index_handler", urlPatterns = {"/"})
public class Index_handler extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(Product_utils.countKeysStock(1));	
		
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String StartID = request.getParameter("sid");
		String EndID = request.getParameter("eid");
		
		int _StartID = 0;
		int _EndID = 10;
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		try {	
			if(StartID != null || EndID != null) {
				try {
					_StartID = Integer.parseInt(StartID);
					_EndID = Integer.parseInt(EndID);
				} catch(NumberFormatException e) {}						
			}
					
			session.setAttribute("products", Product_utils.retriveProducts(_StartID, _EndID));
			
			req = request.getRequestDispatcher("/index.jsp");
			
		} catch (SQLException e) {}
		req.include(request, response);
	}
}
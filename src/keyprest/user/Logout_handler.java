package keyprest.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout_handler extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, ServletException, IOException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException{
		int Logout = request.getIntHeader("x");
		
		HttpSession session = request.getSession();  
		
		if(session.getAttribute("sessionkey") != null ){ 
			session.invalidate(); 
			RequestDispatcher req = request.getRequestDispatcher("/template/pages/loggedout.jsp");
			req.include(request, response);
		} 
          
	}
	
		
}

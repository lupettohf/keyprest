package keyprest.user_model;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class logout_handler {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NumberFormatException{
		int Logout = request.getIntHeader("logout");
		
		HttpSession session = request.getSession();  
		
		if(session.getAttribute("username") != null ){ session.invalidate(); } 
          
	}
	
		
}

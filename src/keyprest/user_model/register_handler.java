package keyprest.user_model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class register_handler extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String EMail = request.getParameter("e-mail");
		
		
	}
	
	private boolean ValidateUsername(String Username)
	{
		if(Username.length() > 30) { return false; }
		
		/* Dalla A alla Z (maiuscole e minuscole), numeri 0-9, e underscore. */
		if(!Username.matches("^[a-zA-Z0-9_]*$")) { return false; }
		
		return true;
	}
	
	private boolean ValidateEmail(String Email)
	{
		return Email.matches("[\\w-]+@([\\w-]+\\.)+[\\w-]+");
	}
}

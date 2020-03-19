package keyprest.user_model;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class userProfile_handler {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Mostra la pagina utente nel caso vine fatto accesso diretto alla servelet. 
		RequestDispatcher req = request.getRequestDispatcher("user.jsp");
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String Old_password = request.getParameter("old_password");
		String EMail = request.getParameter("e-mail");
		String Realname = request.getParameter("realname");
		String Billing_address = request.getParameter("billing");
		
		// Se l'utente popola i campi di cambio password la richiesta viene passata. 
		if(!(Password.isEmpty() || Password_confim.isEmpty() || Old_password.isEmpty()) && Password.equals(Password_confim))
		{
			if(changePassword(Old_password, Password)) {
				//TODO: Informa del successo. 
			} else {
				//TODO: Vecchia password incorretta. 
			}
		}
		
	}
	
	private boolean changePassword(String Old_password, String New_password)
	{
		return false;
	}
	
	private boolean setBillingAddress(String BillingAddress)
	{
		return false;
	}
	
	private boolean setRealName(String RealName)
	{
		return false;
	}
}

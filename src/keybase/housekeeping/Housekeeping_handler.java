package keybase.housekeeping;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.database.connectionManager;
import keyprest.store.Product;

public class Housekeeping_handler extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		try {
			connectionManager.createConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		if(session.getAttribute("username") == null)
		{
			req = request.getRequestDispatcher("login.jsp");
		} else if() {
			req = request.getRequestDispatcher("user.jsp");
		}
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String Old_password = request.getParameter("old_password");
		String EMail = request.getParameter("e-mail");
		String Realname = request.getParameter("realname");
		String Billing_address = request.getParameter("billing");
		
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("user.jsp");
		
		//Verifica che l'utente sia loggato.
		if(session.getAttribute("username") == null) { 
			req = request.getRequestDispatcher("login.jsp");
			req.include(request, response);
		}
		
		String Session_username = (String) session.getAttribute("username");
		
		// Se l'utente popola i campi di cambio password la richiesta viene passata. 
		if(!(Password.isEmpty() || Password_confim.isEmpty() || Old_password.isEmpty()) && Password.equals(Password_confim))
		{
			try {
				if(changePassword(Session_username, Old_password, Password)) {
					// Restituisce il messaggio di successo
					session.setAttribute("success", "Password impostata.");				
				} else {
					session.setAttribute("error", "Impossibile cambiare la password.");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.include(request, response);
		}
		
		if(!Realname.isEmpty())
		{
			try {
				if(setRealName(Session_username, Realname)) 
				{
					session.setAttribute("success", "Nome impostato");			
				} else {
					session.setAttribute("error", "Impossibile cambiare il nome.");	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(!Billing_address.isEmpty())
		{
			try {
				if(setRealName(Session_username, Realname)) 
				{
					session.setAttribute("success", "Indirizzo di fatturazione impostato");			
				} else {
					session.setAttribute("error", "Impossibile cambiare l'indirizzo di fatturazione.");	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}

package keyprest.user_model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import keyprest.database.connectionManager;
import keyprest.utils.Globals;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "UserProfile_handler", urlPatterns = {"/user"})
public class UserProfile_handler extends HttpServlet{
	
	public void init()
	{
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		if(session.getAttribute("username") == null)
		{
			req = request.getRequestDispatcher("login.jsp");
		} else {
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
		
	}
	
	private boolean changePassword(String Username, String Old_password, String New_password) throws SQLException
	{
		String QUERY = "UPDATE users " + 
				"SET `password` = ? " + 
				"WHERE EXISTS(select 1 where username = ?) AND password = ?";
		
		if(Old_password.isEmpty() || New_password.isEmpty()) { return false; }
		
		String HashedPassword = DigestUtils.sha256Hex(New_password + Globals.SALT); 
		String HashedPassword_old = DigestUtils.sha256Hex(Old_password + Globals.SALT); 
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, HashedPassword);
		preparedStatement.setString(2, Username);
		preparedStatement.setString(3, HashedPassword_old);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	private boolean setBillingAddress(String Username, String BillingAddress) throws SQLException
	{
		String QUERY = "UPDATE users" + 
				"SET `b_address` = ?" + 
				"WHERE EXISTS(select 1 from users where username = ?)";
		
		if(BillingAddress.isEmpty()) { return false; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, BillingAddress);
		preparedStatement.setString(2, Username);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
	
	private boolean setRealName(String Username, String RealName) throws SQLException
	{
		String QUERY = "UPDATE users" + 
				"SET `realname` = ?" + 
				"WHERE EXISTS(select 1 from users where username = ?)";
		
		if(RealName.isEmpty()) { return false; }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, RealName);
		preparedStatement.setString(2, Username);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
}

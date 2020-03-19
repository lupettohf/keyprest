package keyprest.user_model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import keyprest.utils.validators;
import keyprest.database.connectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet("/register")
public class register_handler extends HttpServlet{
	String GLOBAL_SALT = ""; /* TODO: questo deve essere spostato*/
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String EMail = request.getParameter("e_mail");
		HttpSession session = request.getSession();
		
		try {
			if(createUser(Username, Password, Password_confim, EMail)) 
			{
				RequestDispatcher req = request.getRequestDispatcher("login.jsp");
				req.include(request, response);
			} else {
				RequestDispatcher req = request.getRequestDispatcher("register.jsp");
				session.setAttribute("error", "Registration Failed");	
				req.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
	private boolean createUser(String Username, String Password, String Password_confirm, String EMail) throws SQLException, ClassNotFoundException
	{
		//TODO: Verificare che username ed email non siano gia presenti nel database, attualmente sono contrassegnati come UNIQUE
		
		String QUERY = "INSERT INTO users" +
						"(username, password, mailaddr) VALUES (?, ?, ?)";
		/* Primo stage controlli: campi vuoti/password non uguali.
		 * Richiede anche verifica client-side per informare l'utente dell'eventuale errore. 
		 */
		if(Username.isEmpty() || Password.isEmpty() || EMail.isEmpty() || (Password.contentEquals(Password_confirm))) {return false;}
		/* Controllo che mail e username contengano caratteri validi e rispettino le lunghezze */
		if(!validators.ValidateEmail(EMail) || !validators.ValidateUsername(Username)) { return false; }
		
		String HashedPassword = DigestUtils.sha256Hex(Password + GLOBAL_SALT); 
		
		connectionManager.createConnection(); 
		
		if(connectionManager.databaseConnection.isClosed()) { connectionManager.createConnection(); }
			
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, HashedPassword);
		preparedStatement.setString(3, EMail);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
}

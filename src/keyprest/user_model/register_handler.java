package keyprest.user_model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import keyprest.utils.validators;
import keyprest.database.connectionManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

public class register_handler extends HttpServlet{
	String GLOBAL_SALT = ""; /* TODO: questo deve essere spostato*/
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String EMail = request.getParameter("e-mail");
		
		
	}
	
	private boolean createUser(String Username, String Password, String Password_confirm, String EMail) throws SQLException, ClassNotFoundException
	{
		String QUERY = "INSERT INTO users" +
						"(username, password, mailaddr) VALUES (?, ?, ?)";
		/* Primo stage controlli: campi vuoti/password non uguali.
		 * Richiede anche verifica client-side per informare l'utente dell'eventuale errore. 
		 */
		if(Username.isEmpty() || Password.isEmpty() || EMail.isEmpty() || (Password != Password_confirm)) {return false;}
		/* Controllo che mail e username contengano caratteri validi e rispettino le lunghezze */
		if(!validators.ValidateEmail(EMail) || !validators.ValidateUsername(Username)) { return false; }
		
		String HashedPassword = DigestUtils.sha256Hex(Password + GLOBAL_SALT); 
		
		if(connectionManager.databaseConnection.isClosed()) { connectionManager.createConnection(); }
			
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, HashedPassword);
		preparedStatement.setString(3, EMail);
		
		if(preparedStatement.executeUpdate() == 1) {return true;}
		
		return false;
	}
}

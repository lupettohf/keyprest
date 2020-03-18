package keyprest.user_model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.SessionCookieConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.commons.codec.digest.DigestUtils;

import keyprest.database.connectionManager;

public class login_handler extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	String GLOBAL_SALT = ""; /* TODO: questo deve essere spostato */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		try {
			/* TODO: feedback in caso di login negativo */
			if(doLogin(Username, Password)) { session.setAttribute("username", Username); }
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean doLogin(String Username, String Password) throws ClassNotFoundException, SQLException
	{
		String QUERY = "SELECT * FROM users" + 
				"         WHERE EXISTS(select 1 from users where username = ? OR mailaddr = ? )" + 
				"               AND password = ?";
		
		if(Username.isEmpty() || Password.isEmpty()) { return false; }
		
		String HashedPassword = DigestUtils.sha256Hex(Password + GLOBAL_SALT); 
		
		if(connectionManager.databaseConnection.isClosed()) { connectionManager.createConnection(); }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, Username);
		preparedStatement.setString(3, HashedPassword);
		
		return preparedStatement.execute();
		
	}
	
}

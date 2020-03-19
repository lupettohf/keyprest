package keyprest.user_model;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.commons.codec.digest.DigestUtils;

import keyprest.database.connectionManager;

@WebServlet("/login")
public class login_handler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String GLOBAL_SALT = ""; /* TODO: questo deve essere spostato */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Mostra la pagina di login nel caso vine fatto accesso diretto alla servelet. 
		RequestDispatcher req = request.getRequestDispatcher("login.jsp");
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		try {
			/* TODO: feedback in caso di login negativo */
			if(doLogin(Username, Password)) { 
				session.setAttribute("username", Username); 
				RequestDispatcher req = request.getRequestDispatcher("user.jsp");
				
				req.include(request, response);
				
			} else {
				// Reindirizza al login nel caso i dati sono errati
				RequestDispatcher req = request.getRequestDispatcher("login.jsp");
				
				// Setta il testo dell'errore nella sessione
				session.setAttribute("error", "Login Failed");
				
				req.include(request, response);
			}
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
		
		connectionManager.createConnection();
		
		if(connectionManager.databaseConnection.isClosed()) { connectionManager.createConnection(); }
		
		PreparedStatement preparedStatement = connectionManager.databaseConnection.prepareStatement(QUERY);
		
		preparedStatement.setString(1, Username);
		preparedStatement.setString(2, Username);
		preparedStatement.setString(3, HashedPassword);
		
		ResultSet rs = preparedStatement.executeQuery();
		
		//Se ci sono risultati nella query, il login é corretto. 
		if(rs.next()) { return true; } else { return false; }
		
	}
	
}

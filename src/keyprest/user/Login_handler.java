package keyprest.user;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.commons.codec.digest.DigestUtils;

import keyprest.utils.Globals;
import keyprest.database.connectionManager;

@WebServlet(name = "Login_handler", urlPatterns = {"/login"})
public class Login_handler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Mostra la pagina di login nel caso vine fatto accesso diretto alla servelet. 
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		
		try {
			String SessionKey = User_utils.doLogin(Username, Password);
			
			System.out.println("User " + Username + " with sessionkey: " + SessionKey);
			
			if(!SessionKey.equals("false") && !SessionKey.isEmpty()) { 
				User user = User_utils.getUser(SessionKey);
				session.setAttribute("username", user.getUsername()); 
				session.setAttribute("sessionkey", SessionKey);
				if(user.IsAdmin()) { session.setAttribute("housekeeper", true); }
				
				System.out.println("User logged (" + user.getUsername() + ") with id: " + user.getID());
				
				response.sendRedirect("user");
			} else {
				// Reindirizza al login nel caso i dati sono errati
				response.sendRedirect("login");
				
				// Setta il testo dell'errore nella sessione
				session.setAttribute("error", "Login Failed");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.include(request, response);
	}
	
}

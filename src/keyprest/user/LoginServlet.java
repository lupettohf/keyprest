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
import keyprest.store.Alerts;
import keyprest.store.Alerts.AlertType;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String Logged_In = (String) session.getAttribute("logged");
		
		if(Logged_In == null)
		{
			 req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		} else {
			 req = request.getRequestDispatcher("/skeletons/pages/user.jsp");
		}		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		
		try {
			String SessionKey = UserUtils.doLogin(Username, Password);			
			
			if(!SessionKey.equals("false") && !SessionKey.isEmpty()) { 
				User user = UserUtils.getUser(SessionKey);
				session.setAttribute("logged", "true"); 
				session.setAttribute("sessionkey", SessionKey);
				if(user.IsAdmin()) { session.setAttribute("housekeeper", true); }						
				
				response.sendRedirect("user");
			} else {
				// Reindirizza al login nel caso i dati sono errati
				response.sendRedirect("login");
				
				// Setta il testo dell'errore nella sessione
				Alerts.setAlert("Wrong username or password.", AlertType.ERROR, session);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.include(request, response);
	}
	
}

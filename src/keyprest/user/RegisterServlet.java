package keyprest.user;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import keyprest.utils.Validators;
import keyprest.utils.Globals;
import keyprest.database.connectionManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
			connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Mostra la pagina di registrazione nel caso vine fatto accesso diretto alla servelet. 
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/register.jsp");
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String Username = request.getParameter("username");
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String EMail = request.getParameter("e_mail");
		HttpSession session = request.getSession();
		
		try {
			if(UserUtils.createUser(Username, Password, Password_confim, EMail)) 
			{
				RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
				req.include(request, response);
			} else {
				RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/register.jsp");
				session.setAttribute("error", "Registration Failed");	
				req.include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
}

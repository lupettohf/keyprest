package keyprest.user;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import keyprest.database.connectionManager;
import keyprest.utils.Globals;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

@WebServlet(name = "UserProfileServlet", urlPatterns = {"/user"})
public class UserProfileServlet extends HttpServlet{
	
	public void init(ServletConfig config) throws ServletException {
		connectionManager.createConnection();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		String LoggedIn = (String) session.getAttribute("logged");
		String SessionKey = (String) session.getAttribute("sessionkey");
		if(LoggedIn == "false" || LoggedIn == null)
		{
			req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
		} else {
			req = request.getRequestDispatcher("/skeletons/pages/user.jsp");
			/*
			String gavatarHash;
			try {
				gavatarHash = gavatarMD5(User_utils.getUser(SessionKey).getMailAddress());
				session.setAttribute("gavatar", gavatarHash);
			} catch (SQLException e) {e.printStackTrace();}*/
			ArrayList<Order> orders = new ArrayList<Order>();
			
			try {
				orders = OrdersUtils.fetchUserOrders(UserUtils.getUser(SessionKey).getID());
			
				if(!orders.isEmpty() || orders != null)
				{
					session.setAttribute("orders", orders);
				} else {
					//todo: orders empty
				}
			} catch (NullPointerException e) {
				// TODO RIMUOVERE TRY
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		req.include(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Password = request.getParameter("password");
		String Password_confim = request.getParameter("password_confirm");
		String Old_password = request.getParameter("old_password");
		String EMail = request.getParameter("e-mail");
		String Realname = request.getParameter("realname");
		String Billing_address = request.getParameter("address");
		
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/user.jsp");
		
		//Verifica che l'utente sia loggato.
		if(session.getAttribute("username") == null) { 
			req = request.getRequestDispatcher("/skeletons/pages/login.jsp");
			req.include(request, response);
		}
		
		String Session_Key = (String) session.getAttribute("sessionkey");
		
		// Se l'utente popola i campi di cambio password la richiesta viene passata. 
		if(!(Password.isEmpty() || Password_confim.isEmpty() || Old_password.isEmpty()) && Password.equals(Password_confim))
		{
			try {
				if(UserUtils.changePassword(Session_Key, Old_password, Password)) {
					// Restituisce il messaggio di successo
					session.setAttribute("success", "Password impostata.");				
				} else {
					session.setAttribute("error", "Impossibile cambiare la password.");	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(!Realname.isEmpty())
		{
			try {
				if(UserUtils.setRealName(Session_Key, Realname)) 
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
				if(UserUtils.setBillingAddress(Session_Key, Billing_address)) 
				{
					session.setAttribute("success", "Indirizzo di fatturazione impostato");			
				} else {
					session.setAttribute("error", "Impossibile cambiare l'indirizzo di fatturazione.");	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		req.include(request, response);
	}
	
	private static String gavatarMD5(String Email)
	{
		return DigestUtils.md5Hex(Email); 
	}
}

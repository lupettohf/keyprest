package keyprest.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.store.CartItem;
import keyprest.store.Cart_utils;
import keyprest.store.Product_utils;

@WebServlet(name = "UserOrders_handler", urlPatterns = {"/orders"})
public class UserOrders_handler extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/orders.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		if(SessionKey != null)
		{
			User _user = User_utils.getUser(SessionKey);
			
			if(_user != null)
			{
				ArrayList<Order> orders = Orders_utils.fetchUserOrders(_user.getID());
				
				if(orders.isEmpty()) { }
				
				session.setAttribute("orders", orders);
				
				req.include(request, response);
				
			} else { response.sendRedirect("login"); } 
		} else { response.sendRedirect("login"); }
				
	}

}

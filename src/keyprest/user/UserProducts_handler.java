package keyprest.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.store.CartItem;
import keyprest.store.Cart_utils;

public class UserProducts_handler extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		ArrayList<Orders> orders = new ArrayList<Orders>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/skeletons/product.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		try {
			if(User_utils.getUser(SessionKey) != null) { 

				orders = Orders_utils.fetchUserOrders(User_utils.getUser(SessionKey).getID());
			
				if(!orders.isEmpty())
				{
					session.setAttribute("orders", orders);
				} else {
					//todo: orders empty
				}
			} else { response.sendRedirect("login"); }
			req.include(request, response);
		
		} catch (SQLException | IOException | NullPointerException e) {response.sendRedirect("login");}
	}

}

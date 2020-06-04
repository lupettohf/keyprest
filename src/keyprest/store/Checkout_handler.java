package keyprest.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import keyprest.user.User_utils;


@WebServlet(name = "Checkout_handler", urlPatterns = {"/checkout"})
public class Checkout_handler {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/skeletons/cart.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		float cur_price = 0;

		if(SessionKey != null) {
		
			cart_items = Cart_utils.getCartItems(SessionKey);
			
			if(!cart_items.isEmpty())
			{
				session.setAttribute("cart", cart_items);
				
				for(CartItem item : cart_items) {
					cur_price = item.productPrice() + cur_price;
				}
				
				session.setAttribute("subtotal", String.valueOf(cur_price));
			}
			
			
		
			req.include(request, response);
		
		}else {response.sendRedirect("login");}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		HttpSession session = request.getSession();
		RequestDispatcher req = null;
		
		String SessionKey = (String) session.getAttribute("sessionkey");
		String Action = request.getParameter("action");
		int Cart_id = 0;
		int Product_id = 0;
		
		if(request.getParameter("cart_id") != null) {
			Cart_id = Integer.parseInt(request.getParameter("cart_id")); 
		}
		
		if(request.getParameter("product_id") != null) {
			Product_id = Integer.parseInt(request.getParameter("product_id"));
		}
	
		try {
			if(!(User_utils.getUser(SessionKey) != null)) { return; }
		
			if(Action.contentEquals("add_product"))
			{	
				if(Product_utils.productByID(Product_id) != null){
					Cart_utils.addToCart(Product_id, SessionKey);
					//TODO: Aggiungere messaggi di feedback.
					response.sendRedirect("cart");
				}
			}
			
			if(Action.contentEquals("delete_product"))
			{
				
				Cart_utils.deleteFromCart(SessionKey, Cart_id);
				response.sendRedirect("cart");
				//TODO: Aggiungere messaggi di feedback.
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

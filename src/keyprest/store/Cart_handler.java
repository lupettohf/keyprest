package keyprest.store;

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

import keyprest.user.User_utils;

@WebServlet(name = "Cart_handler", urlPatterns = {"/cart"})
public class Cart_handler extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/skeletons/pages/cart.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		float cur_price = 0;
		try {
			if(User_utils.getUser(SessionKey) != null) { 

				cart_items = Cart_utils.getCartItems(SessionKey);
			
				if(!cart_items.isEmpty())
				{
					session.setAttribute("cart", cart_items);
					session.setAttribute("empty", false);
					for(CartItem item : cart_items) {
						if(item.productDiscountPercetage() > 0)
						{
							cur_price = item.productDiscountPrice() + cur_price;
						} else {
							cur_price = item.productPrice() + cur_price;
						}
						
					}
				
				session.setAttribute("subtotal", String.valueOf(cur_price));
				} else {
					session.setAttribute("subtotal", String.valueOf(0));
					session.setAttribute("empty", true);
					//todo:cart is empty
				}
			} else { response.sendRedirect("login"); }
			req.include(request, response);
		
		} catch (IOException | NullPointerException e) {response.sendRedirect("login");}
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
			if(!(User_utils.getUser(SessionKey) != null)) { response.sendRedirect("login"); }
		
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
		} catch (NullPointerException e) {response.sendRedirect("login");}
		
	}
}

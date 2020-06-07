package keyprest.store;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet(name = "Checkout_handler", urlPatterns = {"/checkout"})
public class Checkout_handler extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/skeletons/checkout.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		PrintWriter out = response.getWriter();
		
		try {
			if(SessionKey != null || User_utils.getUser(SessionKey) != null) {
			
				cart_items = Cart_utils.getCartItems(SessionKey);
				
				if(cart_items == null) { response.sendRedirect("login"); } 
				
				out.println("<h6>getting cart items...</h6>"); 
				if(!cart_items.isEmpty())
				{
					if(Checkout_utils.processCart(cart_items))
					{
						for(CartItem item: cart_items)
						{
							Cart_utils.deleteFromCart(SessionKey, item.getCartID());
							out.println("<h6>added " + item.productName() + "</h6>"); 
						}
						
						//TODO: ACQUISTO EFFETTUATO
					} else {
						out.println("<h6>Cannot buy...</h6>"); 
					}
				}
				req.include(request, response);
			
			}else {response.sendRedirect("login");}
		} catch (SQLException | ServletException | IOException e) {}
	}
}

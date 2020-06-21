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

import keyprest.user.UserUtils;


@WebServlet(name = "CheckoutServlet", urlPatterns = {"/checkout"})
public class CheckoutServlet extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		RequestDispatcher req = request.getRequestDispatcher("/template/skeletons/checkout.jsp");
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		PrintWriter out = response.getWriter();
		
		try {
			if(SessionKey != null || UserUtils.getUser(SessionKey) != null) {
			
				cart_items = CartUtils.getCartItems(SessionKey);
				
				if(cart_items == null) { response.sendRedirect("login"); } 
				
				out.println("<h6>getting cart items...</h6>"); 
				if(!cart_items.isEmpty())
				{
					if(Checkout_utils.processCart(cart_items))
					{
						for(CartItem item: cart_items)
						{
							CartUtils.deleteFromCart(SessionKey, item.getCartID());
							out.println("<h6>added " + item.productName() + "</h6>"); 
						}
						
						//TODO: ACQUISTO EFFETTUATO
					} else {
						out.println("<h6>Cannot buy...</h6>"); 
					}
				}
				req.include(request, response);
			
			}else {response.sendRedirect("login");}
		} catch (ServletException | IOException e) {}
	}
}

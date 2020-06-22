package keyprest.payments;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import com.paypal.base.rest.PayPalRESTException;

import keyprest.store.CartItem;
import keyprest.store.CartUtils;
import keyprest.store.Checkout_utils;
import keyprest.user.UserUtils;

@WebServlet(name = "AuthorizePaymentServlet", urlPatterns = {"/paypalAuthorize"})
public class AuthorizePaymentServlet extends HttpServlet{

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		try {
			if(SessionKey != null || UserUtils.getUser(SessionKey) != null) {
			
				cart_items = CartUtils.getCartItems(SessionKey);
				
				if(cart_items == null) { response.sendRedirect("login"); } 
				if(!cart_items.isEmpty())
				{
		            PaymentServices paymentServices = new PaymentServices();
		            String approvalLink = paymentServices.authorizePayment(cart_items, SessionKey);
		 
		            response.sendRedirect(approvalLink);
				}
			}
        } catch (PayPalRESTException ex) {
            request.setAttribute("error", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("/skeletons/pages/payment_error.jsp").forward(request, response);
        }
	}
}

		
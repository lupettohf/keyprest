package keyprest.payments;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
import com.paypal.base.rest.PayPalRESTException;

import keyprest.store.CartItem;
import keyprest.store.CartUtils;
import keyprest.store.CheckoutUtils;
import keyprest.user.UserUtils;

@WebServlet(name = "AuthorizePaymentServlet", urlPatterns = {"/paypalAuthorize"})
public class AuthorizePaymentServlet extends HttpServlet{
	
	private final static String CLIENT_ID = "Ae21ksvTCEqOuELhv_n5mf4oX2S_T-VkiMbd7_rK17Ts5f48DOt7RAZWgjBqQkG6-fGZrIf-OxTE3WcN";
	private final static String CLIENT_SECRET = "EC_4sc99xjXwy5YSsdLtFMwHPcJ5Jkq2f5Ft3kBuROdeYmXfKIdIIGo7xynoHE2nUZyJC4QPbsCqhltq";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<CartItem> cart_items = new ArrayList<CartItem>();
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String SessionKey = (String) session.getAttribute("sessionkey");
		
		if(SessionKey != null || UserUtils.getUser(SessionKey) != null) {
		
			cart_items = CartUtils.getCartItems(SessionKey);
			
			if(cart_items == null) { response.sendRedirect("login"); } 
			if(!cart_items.isEmpty())
			{
		        PaymentServices paymentService;
		        if (request.getParameter("PayerID") != null) {
		            paymentService = new PaymentServices(CLIENT_ID,CLIENT_SECRET,"sandbox");
		            paymentService.completePayment(request, response, SessionKey, cart_items);
		             
		        } else {
		            paymentService = new PaymentServices(CLIENT_ID,CLIENT_SECRET,"sandbox");
		            paymentService.processPayment(request, response, SessionKey, cart_items);
		        }
			}
		}
	}
}

		
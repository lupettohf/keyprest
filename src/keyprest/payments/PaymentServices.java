package keyprest.payments;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import keyprest.store.CartItem;
import keyprest.store.ProductUtils;
 
public class PaymentServices {
    private APIContext apiContext;
    
    public PaymentServices(String clientId, String clientSecret, String mode) {
        apiContext = new APIContext(clientId, clientSecret, mode);  
    }
    
    public void processPayment(HttpServletRequest request, HttpServletResponse response, String SessionKey, ArrayList<CartItem> cart) {
		PrintWriter out;
		try {
			out = response.getWriter();

    	RedirectUrls redirectUrls = new RedirectUrls();
    	Payer payer = new Payer();
    	Payment payment = new Payment();
    	Details details = new Details();
    	Amount amount = new Amount();
    	Transaction transaction;
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	payer.setPaymentMethod("paypal");
        
        // TODO CONFIG Redirect URLS
        
        redirectUrls.setCancelUrl("http://localhost:8080/paypalrestintegration/cancel");
        redirectUrls.setReturnUrl("http://localhost:8080/paypalrestintegration/PaypalDirectPayment");
      
        for(CartItem item: cart)
        {
        	out.println(item.productName());
        	transaction = new Transaction();
        	
        	float _total = 0;
        	float _subTotal = 0;
        	float _tax  = (_subTotal*22)/100;
        	_subTotal = item.productDiscountPrice() + _subTotal;
        	
            details.setSubtotal(String.valueOf(_subTotal));
            details.setTax(String.valueOf(_tax));
        
            _total = _total+_tax;
        
            amount.setCurrency("EUR");
            amount.setTotal(String.valueOf(_total));
            amount.setDetails(details);    
            
            transaction.setAmount(amount);
            transaction.setDescription(item.productName());
 
            transactions.add(transaction);
        }
       
        
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);  
         
        try {       	
          Payment createdPayment = payment.create(apiContext);          
          out.println("dssssssdsds");
          Iterator<Links> links = createdPayment.getLinks().iterator();
          out.println(links.toString());
          while (links.hasNext()) {
            Links link = links.next();
            if (link.getRel().equalsIgnoreCase("approval_url")) {
            	response.sendRedirect(link.getHref()); 
            }
          }
        } catch (PayPalRESTException e) {
            System.err.println(e.getDetails());
        } catch (IOException e) {
            e.printStackTrace();
        }
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void completePayment(HttpServletRequest req, HttpServletResponse res) {
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));
 
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));
        try {
          Payment createdPayment = payment.execute(apiContext, paymentExecution);
          if(createdPayment.getState().contains("approved"))
          {
        	  res.sendRedirect("user"); 
          }
        } catch (PayPalRESTException | IOException e) {
        }
    }
}
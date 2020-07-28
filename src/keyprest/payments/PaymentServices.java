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

import keyprest.store.Alerts;
import keyprest.store.CartItem;
import keyprest.store.CartUtils;
import keyprest.store.CheckoutUtils;
import keyprest.store.ProductUtils;
import keyprest.store.Alerts.AlertType;
 
public class PaymentServices {
    private APIContext apiContext;
    
    public PaymentServices(String clientId, String clientSecret, String mode) {
        apiContext = new APIContext(clientId, clientSecret, mode);  
    }
    
    public void processPayment(HttpServletRequest request, HttpServletResponse response, String SessionKey, ArrayList<CartItem> cart) {
    	RedirectUrls redirectUrls = new RedirectUrls();
    	Payer payer = new Payer();
    	Payment payment = new Payment();
    	Details details = new Details();
    	Amount amount = new Amount();
    	Transaction transaction = new Transaction();
    	List<Transaction> transactions = new ArrayList<Transaction>();
    	
    	payer.setPaymentMethod("paypal");
        
        // TODO CONFIG Redirect URLS
        
        redirectUrls.setCancelUrl("http://keyprest.napalm.rocks/cancel");
        redirectUrls.setReturnUrl("http://keyprest.napalm.rocks/paypalAuthorize");
      
    	float _total = 0;
    	float _subTotal = 0;     
    	float _tax = 0; 
    	
        for(CartItem item: cart)
        {
        	_subTotal = _subTotal + item.productDiscountPrice();           
        }
        
        _tax = (_subTotal*22)/100;
        
        _total = _subTotal + _tax;       
        
      	details.setSubtotal(String.format("%.2f", _subTotal).replace(",", "."));
        details.setTax(String.format("%.2f", _tax).replace(",", "."));  
        
        amount.setCurrency("USD");
        amount.setTotal(String.format("%.2f", _total).replace(",", "."));
        amount.setDetails(details);    
        
        transaction.setAmount(amount);
        transaction.setDescription("Keyprest Purchase");
        transactions.add(transaction);
      
        payment.setIntent("sale");
        payment.setPayer(payer);
        payment.setRedirectUrls(redirectUrls);
        payment.setTransactions(transactions);  
         
        try {       	
          Payment createdPayment = payment.create(apiContext);                    
          Iterator<Links> links = createdPayment.getLinks().iterator();
        
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
    }
    
    public void completePayment(HttpServletRequest req, HttpServletResponse res, String SessionKey, ArrayList<CartItem> cart) {
        Payment payment = new Payment();
        payment.setId(req.getParameter("paymentId"));
 
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(req.getParameter("PayerID"));
        try {
          Payment createdPayment = payment.execute(apiContext, paymentExecution);
          if(createdPayment.getState().contains("approved"))
          {
        	  if(CheckoutUtils.processCart(cart, SessionKey))
        	  {
        		  Alerts.setAlert("Payment id:" + createdPayment.getId() + " has been verified.", AlertType.INFO, req.getSession());
        		  res.sendRedirect("orders");
        	  }       	  
          }
        } catch (PayPalRESTException | IOException e) {
        }
    }
}
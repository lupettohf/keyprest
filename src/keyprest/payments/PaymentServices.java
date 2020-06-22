package keyprest.payments;


import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;

import keyprest.store.CartItem;
import keyprest.store.CartUtils;
import keyprest.user.User;
import keyprest.user.UserUtils;
 

public class PaymentServices {

    private static final String CLIENT_ID = "Your_PayPal_Client_ID";
    private static final String CLIENT_SECRET = "Your_PayPal_Client_Secret";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(List<CartItem> orderDetail, String sessionKey)        
            throws PayPalRESTException {       
 
        Payer payer = getPayerInformation(sessionKey);
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = makeFormedTransaction(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
    
    private List<Transaction> makeFormedTransaction(List<CartItem> orderDetail) {
    
    	Details details = new Details();
    	Amount amount = new Amount();
    	Transaction transaction = new Transaction();
        ItemList itemList = new ItemList();
        Item pp_item = new Item();
        List<Item> items = new ArrayList<>();
        
    	String _subtotal = String.valueOf(CartUtils.getCartSubtotal(orderDetail));
    	   	
    	
        details.setShipping("Digital Delivery");
        details.setSubtotal(_subtotal);
        details.setTax("10");     
        
        amount.setCurrency("USD");
        amount.setTotal(_subtotal);
        amount.setDetails(details);   
        
        transaction.setAmount(amount);
         

        for(CartItem item: orderDetail)
        {          
        	pp_item.setCurrency("USD");
        	pp_item.setName(item.productName());
        	pp_item.setPrice(String.valueOf(item.productDiscountPrice()));
        	pp_item.setTax("10");
        	pp_item.setQuantity("1");
        	
            items.add(pp_item);
        }
      
        itemList.setItems(items);
        transaction.setItemList(itemList);
     
        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);  
         
        return listTransaction;
    }
    
    private Payer getPayerInformation(String session_key) {
    	
        User user = UserUtils.getUser(session_key);
    	Payer payer = new Payer();
        PayerInfo payerInfo = new PayerInfo();
        
        payerInfo.setFirstName(user.getRealName())
                 .setEmail(user.getMailAddress());
        payer.setPaymentMethod("paypal"); 
        payer.setPayerInfo(payerInfo);
         
        return payer;
    }
    
    private String getApprovalLink(Payment approvedPayment) {
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
         
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")) {
                approvalLink = link.getHref();
                break;
            }
        }      
         
        return approvalLink;
    }
    
    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:8080/PaypalTest/cancel.html");
        redirectUrls.setReturnUrl("http://localhost:8080/PaypalTest/review_payment");
         
        return redirectUrls;
    }
}

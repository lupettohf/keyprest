<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="keyprest.store.Cart_utils" %>

 <% 
    String LoggedIn = (String) session.getAttribute("logged");
    boolean logged_in; 
   
    if(LoggedIn == null)
    {
    	logged_in = false;
    } else { 
    	logged_in = true; 
    	
    	String SessionKey = (String) session.getAttribute("sessionkey");
    	int CartElements = Cart_utils.getCartElements(SessionKey);
    	
    	pageContext.setAttribute("cartElements", CartElements);
   	}
    
    pageContext.setAttribute("logged_in", logged_in);
    
 %>
			<c:if test="${logged_in}">
             <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
                <a href="cart" class="cart-nav"><img src="img/core-img/cart.png" alt=""> Cart <span>(${cartElements})</span></a>
            </div>
            </c:if>
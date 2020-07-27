<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="keyprest.user.*"%>

 <% 
    String LoggedIn = (String) session.getAttribute("logged");
 	Boolean Housekeeper = (Boolean) session.getAttribute("housekeeper");
    boolean logged_in;
    
    if(LoggedIn == null)
    {
    	logged_in = false;
    } else { 
    	logged_in = true; 
    	if(Housekeeper != null)
    	{
    		pageContext.setAttribute("housekeeper", Housekeeper);
    	} else { Housekeeper = false; }
    }
    
    pageContext.setAttribute("logged_in", logged_in);
 %>
 
             <nav class="keyprest-nav">
                <ul>
                    <li><a href="index">Home</a></li>
                    <li><a href="shop">Shop</a></li>
                    <c:if test="${logged_in}">
                    <jsp:include page="navbarCart.jsp" />
                    <li><a href="user">Your Profile</a></li>
                    <li><a href="orders">Orders</a></li>
                    <c:if test="${housekeeper}">
                    <li><a href="housekeeping">Housekeeping</a></li>
                    </c:if>
                    <li><a href="logout">Logout</a></li>
                    </c:if>
                    <c:if test="${!logged_in}">
                    <li><a href="login">Login</a></li>
                    <li><a href="register">Register</a></li>
                    </c:if>                   
                </ul>
            </nav>
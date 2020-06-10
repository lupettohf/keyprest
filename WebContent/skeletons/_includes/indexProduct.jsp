<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>

<% if(products != null){ %>
<c:forEach var="product" items="${products}">
                <div class="single-products-catagory clearfix">
                    <a href="product?id=${product.getID()}">
                        <!-- <img src="static/img/bg-img/${product.getID()}.png" alt=""> -->
                        <img src="https://www.stevensegallery.com/300/600/" alt=""> 
                        <div class="hover-content">
                            <div class="line"></div>
                            <c:if test="${product.getDiscountPrice() > 0}">
                            <p>${product.getDiscountPrice()}$ <b>(${product.getDiscount()}% off!)</b></p>
                            </c:if>
							<c:if test="${product.getDiscountPrice() == 0}">
        					<p>${product.getPrice()}$</p>
        					</c:if>
                            <h4>${product.getName()}</h4>
                        </div>
                    </a>
                </div>
</c:forEach>  
<% } %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% 	ArrayList<CartItem> cart = (ArrayList<CartItem>) session.getAttribute("cart");
	String subtotal = (String) session.getAttribute("subtotal");
	Boolean cartEmpty = (Boolean) session.getAttribute("empty");
	
	pageContext.setAttribute("cartEmpty", cartEmpty);
%>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />

        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="cart-title mt-50">
                            <h2>Cart</h2>
                        </div>

                        <div class="cart-table clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th>Product Name</th>
                                        <th>Price</th>
                                        <th>Region</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
<c:if test="${!cartEmpty}">
<c:forEach var="item" items="${cart}">                             
                                    <tr>
                                        <td class="cart_product_desc">
                                            <h5>${item.productName()}</h5>
                                        </td>
                                        <td class="price">
                                        <c:if test="${item.productDiscountPercetage() > 0}">
                                            <span>${ item.productDiscountPrice() }$ <small>(${item.productDiscountPercetage()}% off)</small></span>
                                        </c:if>
                                       	<c:if test="${item.productDiscountPercetage() == 0}">
                                            <span>${ item.productPrice() }$</span>
                                        </c:if>
                                        </td>
                                       	<td class="region">
                                       		<span>${ item.productRegion() }</span>
                                       	</td>
                                       	<td class="delete">
                                       		<form method="post" action="cart">
   												<input type="hidden" name="action" value="delete_product">
  												<input type="hidden" name="cart_id" value="${item.getCartID()}">	
        										<button type="submit" class="btn amado-btn" style="background-color: #d32020;">Delete</button>
    										</form>
                                       	</td>
                                    </tr>
</c:forEach>  
</c:if>                           
                                </tbody>
                            </table>
<c:if test="${cartEmpty}">
                            <div class="col-10 text-center">
     							<h2>The cart is out of mana <i class="fa fa-frown-o" aria-hidden="true"></i></h2>
     							<a style="font-size: 1em; color: #d32020;" href="shop">
     								<h6 class="text-center" >Go buy some potions at the store!</h6>
     							</a>
     						</div>
</c:if>
                        </div>
                    </div>
                    <div class="col-12 col-lg-4">
                        <div class="cart-summary">
                            <h5>Transaction</h5>
                            <ul class="summary-table">
                                <li><span>Total:</span> <span>${subtotal} $</span></li>
                            </ul>
                            <div class="cart-btn mt-100">
                                <a href="cart.html" class="btn amado-btn w-100">Checkout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
<jsp:include page="../footer.jsp" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %> 
<% ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("orders");  %>

<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />

        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-12">
                        <div class="cart-title mt-20">
                            <h2>Your Orders</h2>
                        </div>
                        <div class="clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                    	<th style="flex: 0 0 15%;">Order No</th>
                                        <th style="flex: 0 0 15%;">Product Name</th>
                                        <th style="flex: 0 0 15%;">Price</th>
                                        <th style="flex: 0 0 15%;">Product Key</th>
                                    </tr>
                                </thead>
                                <tbody>
<c:forEach var="item" items="${orders}">                             
                                    <tr>
                                    	<td style="flex: 0 0 15%;">
                                    		<h5>${item.getOrderID()}</h5>
                                    	</td>
                                        <td style="flex: 0 0 15%;">
                                            <h6>${item.getProductName()}</h6>
                                        
                                        <td style="flex: 0 0 15%;">
                                            <span> getFinalPrice()</span>
                                        </td>
                                       	<td style="flex: 0 0 15%;">
                                    		<input type="text" class="form-control" id="product_name" name="product_key" placeholder="Product Key" value="${ item.getKey() }">
                                    	</td>
                                    </tr>
</c:forEach>                           
                                </tbody>
                            </table>
                        </div>
                    </div>
				</div>
		  	</div>
		</div>						
    
<jsp:include page="../footer.jsp" />
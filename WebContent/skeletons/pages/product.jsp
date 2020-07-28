<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<% Product product = (Product) session.getAttribute("product");  %>
<% String LoggedIn = (String) session.getAttribute("logged"); %>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />

    <!-- Product Details Area Start -->
        <div class="single-product-area section-padding-100 clearfix">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-7">
						<div class="carousel-item active">
							<img class="d-block w-100" src="static/img/products/${product.getID()}.png">
                     	</div>
                    </div>
                    <div class="col-12 col-lg-5">
                        <div class="single_product_desc">
                            <div class="product-meta-data">
                                <div class="line"></div>
                                <c:if test="${product.getDiscountPrice() > 0}">
                            	<p class="product-price">${product.getDiscountPrice()}$ <b>(${product.getDiscount()}% off!)</b></p>
                            	</c:if>
                            	<c:if test="${product.getDiscountPrice() == 0}">
        						<p class="product-price">${product.getPrice()}$</p>
        						</c:if>
                                <h6>${product.getName()}</h6>                         
                                <c:if test="${product.getStock() > 0}">
                                <p class="avaibility"><i class="fa fa-circle"></i> ${product.getStock()} Keys in stock</p>
                                </c:if>
                                <c:if test="${product.getStock() == 0}">
                                <p class="avaibility"><i class="fa fa-circle" style="color: #d32020;"></i> Out of stock </p>
                                </c:if>                                
                            </div>

                            <div class="short_overview my-5">
                                <p>${product.getDescription()}</p>
                                <p><small>${product.getRegion()}</small></p>
                            </div>

                            <% if(LoggedIn != null){ %>
                            <button onclick='$.post("cart",{action:"add_product",product_id: ${product.getID()} }).done(function(t){$("#cartelements").text("("+t+")")});' class="btn keyprest-btn">Add to cart</button>
                            <% } %>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    
<jsp:include page="../footer.jsp" />
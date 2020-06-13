<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<% Product product = (Product) session.getAttribute("product");  %>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />

    <!-- Product Details Area Start -->
        <div class="single-product-area section-padding-100 clearfix">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-7">
						<div class="carousel-item active">
							<img class="d-block w-100" src="https://www.stevensegallery.com/300/300/">
                     	</div>
                    </div>
                    <div class="col-12 col-lg-5">
                        <div class="single_product_desc">
                            <!-- Product Meta Data -->
                            <div class="product-meta-data">
                                <div class="line"></div>
                                <c:if test="${product.getDiscountPrice() > 0}">
                            	<p class="product-price">${product.getDiscountPrice()}$ <b>(${product.getDiscount()}% off!)</b></p>
                            	</c:if>
                            	<c:if test="${product.getDiscountPrice() == 0}">
        						<p class="product-price">${product.getPrice()}$</p>
        						</c:if>
                                <h6>${product.getName()}</h6>
                                <!-- Avaiable -->
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

                            <!-- Add to Cart Form -->
                            <form class="cart clearfix" method="post" action="cart">
  								<input type="hidden" name="product_id" value="${product.getID()}">	
                                <button type="submit" class="btn amado-btn">Add to cart</button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Product Details Area End -->
    
<jsp:include page="../footer.jsp" />
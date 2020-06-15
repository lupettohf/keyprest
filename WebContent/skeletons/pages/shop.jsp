<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>
<% pageContext.setAttribute("pages", (int) session.getAttribute("totalpages"));  %>
<% pageContext.setAttribute("curpage", (int) session.getAttribute("curpage"));  %>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />  
        <div class="amado_product_area section-padding-100">
            <div class="container-fluid">
                <div class="row">
<% if(products != null){ %>
<c:forEach var="product" items="${products}">
                    <div class="col-12 col-sm-6 col-md-12 col-xl-6">
                        <div class="single-product-wrapper">
                            <!-- Product Image -->
                            <div class="product-img">
                                <img src="static/images/products/${product.getID()}.png" alt="">
                            </div>

                            <div class="product-description d-flex align-items-center justify-content-between">
                                <div class="product-meta-data">
                                    <div class="line"></div>
<c:if test="${product.getDiscountPrice() > 0}">
                            <p class="product-price">${product.getDiscountPrice()}$ <b>(${product.getDiscount()}% off!)</b></p>
                            </c:if>
<c:if test="${product.getDiscountPrice() == 0}">
        					<p class="product-price">${product.getPrice()}$</p>
</c:if>
                                    <a href="product?id=${product.getID()}">
                                        <h6>${product.getName()}</h6>
                                    </a>
                                </div>
                                    <div class="cart">
                                    	<form class="cart clearfix" method="post" action="cart">
                               				<input type="hidden" name="action" value="add_product">
  											<input type="hidden" name="product_id" value="${product.getID()}">	
                                			<button type="submit" class="btn amado-btn">Add to cart</button>
                            			</form>
                                    </div>
                                </div>
                            </div>
                        </div>
</c:forEach>  
<% } %>
						</div>
                <div class="row">
                    <div class="col-12">
                        <nav aria-label="navigation">
                            <ul class="pagination justify-content-end mt-50">
<c:forEach var = "i" begin = "0" end = "${pages}">
<c:if test="${ curpage == i }">
                                <li class="page-item active"><a class="page-link" href="shop?p=${i}">${i}</a></li>
</c:if>
<c:if test="${ curpage != i }">
                                <li class="page-item"><a class="page-link" href="shop?p=${i}">${i}</a></li>
</c:if>
</c:forEach>                                
                            </ul>
                        </nav>
                    </div>
                </div>					
                    	</div>
                	</div>
           		</div>
			</div>
<jsp:include page="../footer.jsp" />
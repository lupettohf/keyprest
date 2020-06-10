<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>


<% if(products != null){ %>
<section class="products-section">
<c:forEach var="product" items="${products}">
 <div class="product-card">
  <img src="static/images/products/${product.getID()}.png"/>
  <div class="product-card-content">
    <span class="product-category">${product.getRegion()}</span>
    <div class="product-name">${product.getName()}</div>
    <p class="product-description">${product.getDescription()}</p>
  </div>
  <div class="product-card-footer">
    <div class="product-additional-wrapper">
      
      <div class="product-discount">
     	<c:if test="${product.getDiscountPrice() > 0}">
        <span>${product.getDiscountPrice()}$ (${product.getDiscount()}% off!)</span>
        </c:if>
        <c:if test="${product.getDiscountPrice() == 0}">
        <span>${product.getPrice()}$</span>
        </c:if>
      </div>
      
      <div class="product-quantity">${product.stockQuantity()} keys available</div>
    </div>
    <div class="button-wrapper">
    <c:if test="${product.stockQuantity() > 0}">
    <form method="post" action="cart">
   		<input type="hidden" name="action" value="add_product">
  		<input type="hidden" name="product_id" value="${product.getID()}">	
        <button type="submit">Add to cart</button>
    </form>
    </c:if>
    <c:if test="${product.stockQuantity() == 0}">
    <form method="post" action="cart">
   		<input type="hidden" name="action" value="add_product">
  		<input type="hidden" name="product_id" value="${product.getID()}">	
        <button type="submit" disabled>Out of stock</button>
    </form>
    </c:if>
    </div>
  </div>
</div>
</c:forEach>  
</section>
<% } %>
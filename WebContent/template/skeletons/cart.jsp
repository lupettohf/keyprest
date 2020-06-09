<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="../skeletons/header.jsp" />
<% ArrayList<CartItem> cart = Cart_utils.getCartItems((String) session.getAttribute("sessionkey")); %>
<% String subtotal = (String) session.getAttribute("subtotal"); %>

<% if(cart != null){ %>
<section class="products-section">
<c:forEach var="product" items="${cart}">
 <div class="product-card">
  <img src="static/images/products/${product.getItemID()}.png"/>
  <div class="product-card-content">
    <span class="product-category">${product.productRegion()}</span>
    <div class="product-name">${product.productName()}</div>
    <p class="product-description">${product.productDescription()}</p>
  </div>
  <div class="product-card-footer">
    <div class="product-additional-wrapper">
      <div class="product-discount">
        <span>${product.productPrice()}$</span>
      </div>
    </div>
    <div class="button-wrapper">
    <form method="post" action="cart">
   		<input type="hidden" name="action" value="delete_product">
  		<input type="hidden" name="product_id" value="${product.getCartID()}">	
        <button type="submit">Delete</button>
    </form>
    </div>
  </div>
</div>
</c:forEach>  
</section>
<% } %>

 <h5>Subtotal: <c:out value="${subtotal}"/>$</h5>
 <form method="post" action="checkout">
  	<button type="submit" class="add-to-cart" name="submit_param" value="submit">Checkout</button>
 </form>
<jsp:include page="../skeletons/footer.jsp" />


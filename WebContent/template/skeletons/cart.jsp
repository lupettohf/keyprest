<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="../skeletons/header.jsp" />
<% ArrayList<CartItem> cart = Cart_utils.getCartItems((String) session.getAttribute("sessionkey")); %>
<% String subtotal = (String) session.getAttribute("subtotal"); %>
  <div class="row">
<c:forEach var="item" items="${cart}">
    <div class="col-md-3 col-sm-6">
      <div class="product-grid">
        <div class="product-image">
          <a href="#" class="image">
           <img class="pic-1" src="https://via.placeholder.com/300.png"> <!-- TODO: FIX --> 
          </a>
          <form method="post" action="cart">
  			<input type="hidden" name="action" value="delete_product">
  			<input type="hidden" name="cart_id" value="${item.getCartID()}">
  			<button type="submit" class="add-to-cart" name="submit_param" value="submit">Delete</button>
		  </form>
        </div>
        <div class="product-content">
          <span class="product-name"><c:out value="${item.productName()}"/></span>
          <ul class="icon">
            <li><a href="#"><i class="far fa-heart"></i></a></li>
            <li><a href="#"><i class="fas fa-shopping-cart"></i></a></li>
            <li><a href="#"><i class="far fa-eye"></i></a></li>
          </ul>
          <span class="region"><c:out value="${item.productRegion()}"/></span>
          <div class="price"><c:out value="${item.productPrice()}"/> $</div>
        </div>
      </div>
    </div>
</c:forEach>  

  <h5>Subtotal: <c:out value="${subtotal}"/>$</h5>
  <form method="post" action="checkout">
  	<button type="submit" class="add-to-cart" name="submit_param" value="submit">Checkout</button>
  </form>
  </div>

<jsp:include page="../skeletons/footer.jsp" />


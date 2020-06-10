<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Orders> orders = (ArrayList<Orders>) session.getAttribute("orders");  %>

<% if(orders != null){ %>

<section class="products-section">
<c:forEach var="product" items="${orders}">
 <div class="product-card">
  <img src="static/images/products/${product.getProductID()}.png"/>
  <div class="product-card-content">
    <div class="product-name">${product.getProductName()}</div>
  </div>
  <div class="product-card-footer">
    <div class="product-additional-wrapper">  
      <div class="product-discount">
        <span>${product.getFinalPrice()}$</span>
      </div>
    </div>
    <div class="button-wrapper">
		<input type="text" id="productcode" name="productcode" value="${ product.getKey() }">
    </div>
  </div>
</div>
</c:forEach>  
</section>

<% } %>
<jsp:include page="../skeletons/footer.jsp" />

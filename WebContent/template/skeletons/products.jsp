<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>

<% if(products != null){ %>
<div class="container">
  <div class="row">
<c:forEach var="product" items="${products}">
    <div class="col-md-3 col-sm-6">
      <div class="product-grid">
        <div class="product-image">
          <a href="#" class="image">
          <!--<img class="pic-1" src="https://via.placeholder.com/300.png">-->
          <img class="pic-1" src="static/images/products/${product.getID()}.png">
          </a>
          <form method="post" action="cart">
  			<input type="hidden" name="action" value="add_product">
  			<input type="hidden" name="product_id" value="${product.getID()}">
  			<button type="submit" class="add-to-cart" name="submit_param" value="submit">Add To Cart</button>
		  </form>
        </div>
        <div class="product-content">
          <span class="product-name"><c:out value="${product.getName()}"/></span>
          <ul class="icon">
            <li><a href="#"><i class="far fa-heart"></i></a></li>
            <li><a href="#"><i class="fas fa-shopping-cart"></i></a></li>
            <li><a href="#"><i class="far fa-eye"></i></a></li>
          </ul>
          <span class="region"><c:out value="${product.getRegion()}"/></span>
          <div class="price"><c:out value="${product.getPrice()}"/> $</div>
        </div>
      </div>
    </div>
</c:forEach>  
  </div>
</div>
<% } %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<%@page import="java.util.ArrayList" %>
<jsp:include page="../skeletons/header.jsp" />
<% ArrayList<Product> products = (ArrayList<Product>) Product_utils.retriveProducts(1, 1); %>

<div class="container">
  <div class="row">
<c:forEach var="product" items="${products}">
    <div class="col-md-3 col-sm-6">
      <div class="product-grid">
        <div class="product-image">
          <a href="#" class="image">
          <img class="pic-1" src="static/images/products/${product.getID()}/.png">
          </a>
          <a class="add-to-cart" href="">ADD TO CART</a>
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
          <ul class="rating">
            <li class="fas fa-star"></li>
            <li class="fas fa-star"></li>
            <li class="fas fa-star"></li>
            <li class="fas fa-star"></li>
            <li class="fas fa-star disable"></li>
          </ul>
        </div>
      </div>
    </div>
</c:forEach>  
  </div>
</div>

<jsp:include page="../skeletons/footer.jsp" />

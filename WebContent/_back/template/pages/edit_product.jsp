<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="keyprest.user.*"%>
<% Product product = (Product) session.getAttribute("product"); %>

<h1>Housekeeping 🧹</h1>
<h4>Bentornato leader supremo <% out.println(session.getAttribute("username")); %></h4>
<br>
<jsp:include page="../skeletons/success.jsp" />
<jsp:include page="../skeletons/error.jsp" />

<form action="editproduct" method="post">
			<table style="with: 50%">
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="product_name" value="<c:out value="${product.getName()}"/>"/></td>
				</tr>
				<tr>
					<td>Product Description</td>
					<td><input type="text" name="product_description" value="<c:out value="${product.getDescription()}"/>"/></td>
				</tr>
				<tr>
					<td>Service (numerical)</td>
					<td><input type="text" name="product_service" value="<c:out value="${product.getService()}"/>"/></td>
				</tr>
				<tr>
					<td>Price (numerical float)</td>
					<td><input type="text" name="product_price" value="<c:out value="${product.getPrice()}"/>"/></td>
				</tr>
				<tr>
					<td>Region (text)</td>
					<td><input type="text" name="product_region" value="<c:out value="${product.getRegion()}"/>"/></td>
				</tr>
				<tr>
					<td>Discount (numerical) (percentage)</td>
					<td><input type="text" name="product_discount" value="<c:out value="${product.getDiscount()}"/>"/></td>
				</tr>
				<tr>
					<td>Is DLC</td>
					<td><input type="checkbox" name="product_isdlc" value="<c:out value="${product.IsDLC()}"/>" /></td>
				</tr>
			</table>
			 <input type="hidden" id="product_id" name="product_id" value="<c:out value="${product.getID()}"/>">
			<input type="submit" value="Submit" />
</form>
<a href="logout?x=1">Logout</a>
<jsp:include page="../skeletons/footer.jsp" />
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.user.*"%>
<jsp:include page="../skeletons/header.jsp" />
<h1>Housekeeping 🧹</h1>
<h4>Bentornato leader supremo <% out.println(session.getAttribute("username")); %></h4>
<br>
<jsp:include page="../skeletons/success.jsp" />
<jsp:include page="../skeletons/error.jsp" />
<hr>
<h3>Prodotti disponibili</h3>
<jsp:include page="../skeletons/products.jsp" />
<hr>
<br>
<hr>
<h3>Aggiungi prodotto</h3>
<form action="createproduct" method="post">
			<table style="with: 50%">
				<tr>
					<td>Product Name</td>
					<td><input type="text" name="product_name" /></td>
				</tr>
				<tr>
					<td>Product Description</td>
					<td><input type="text" name="product_description" /></td>
				</tr>
				<tr>
					<td>Service (numerical)</td>
					<td><input type="text" name="product_service" /></td>
				</tr>
				<tr>
					<td>Price (numerical float)</td>
					<td><input type="text" name="product_price" /></td>
				</tr>
				<tr>
					<td>Region (text)</td>
					<td><input type="text" name="product_region" /></td>
				</tr>
				<tr>
					<td>Discount (numerical) (percentage)</td>
					<td><input type="text" name="product_discount" /></td>
				</tr>
				<tr>
					<td>Is DLC</td>
					<td><input type="checkbox" name="product_isdlc" value="1" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
</form>
<br>
<hr>
<h3>Upload Keys To Product<h3>
<form action="importkey" method="post">
	<select name="productid">
	<c:forEach var="product" items="${products}">
		<option value="${product.getID()}"><c:out value="${product.getName()}"/></option>
	</c:forEach> 
	</select>
	<textarea name="keys"/></textarea>
	<input type="submit" value="upload" />
</form>
<hr>
<h3>Upload Products Images<h3>
<form action="uploadimage" enctype="multipart/form-data" method="post">
	Product:
	<select name="productid">
<c:forEach var="product" items="${products}">
		<option value="${product.getID()}"><c:out value="${product.getName()}"/></option>
</c:forEach> 
	</select>
	<br>
	<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
	<br>		
	<input type="submit"><input type="reset">
</form>
<a href="logout?x=1">Logout</a>
<jsp:include page="../skeletons/footer.jsp" />
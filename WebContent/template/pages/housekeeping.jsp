<jsp:include page="../skeletons/header.jsp" />
<%@page import="keyprest.user.*"%>

<jsp:include page="../skeletons/error.jsp" />
<h1>Housekeeping 🧹</h1>
<h4>Bentornato leader supremo <% out.println(session.getAttribute("username")); %></h4>
<br>
<jsp:include page="../skeletons/success.jsp" />
<jsp:include page="../skeletons/error.jsp" />

<hr>
<h3>Prodotti disponibili</h3>
<jsp:include page="../skeletons/products.jsp" />
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
<form action="importkey" method="post" enctype="multipart/form-data">
	<input type="file" name="file" />
	<input type="submit" value="upload" />
	<input type="text" name="productid"/>
</form>
<hr>
<h3>Upload Products Images<h3>
<form action="uploadimage" enctype="multipart/form-data" method="post">
	Id:
	<select name="productid">
		<option value="1">1</option>
		<option value="2">2</option>
		<option value="3">3</option>
		<option value="4">4</option>
	</select>
	<br>
	<input class="file" type="file" name="talkPhoto" value="" maxlength="255">	
	<br>		
	<input type="submit"><input type="reset">
</form>
<a href="logout?x=1">Logout</a>
<jsp:include page="../skeletons/footer.jsp" />
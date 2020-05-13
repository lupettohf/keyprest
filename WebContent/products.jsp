<jsp:include page="template/header.jsp" />
<%@page import="keyprest.user.*"%>

<jsp:include page="template/error.jsp" />

 <%
 	//Controlla che l'utente sia giá loggato, e nel caso manda un redirect. 
    String Username = (String) session.getAttribute("username");
    if (null == Username) {
    	response.sendRedirect("login.jsp");
    }
 %>

<h1>Housekeeping 🧹/h1>
<h3><% out.println(session.getAttribute("username")); %></h3>
<br>
<jsp:include page="template/success.jsp" />
<jsp:include page="template/error.jsp" />
<form action="create_product" method="post">
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
<a href="logout?x=1">Logout</a>
<jsp:include page="template/footer.jsp" />
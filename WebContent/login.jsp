<jsp:include page="template/header.jsp" />
<%@page import="keyprest.user.*"%>

<%
	//Controlla che l'utente non sia giá loggato, e nel caso manda un redirect. 
   	String Username = (String) session.getAttribute("username");
    if (null != Username) {
    	response.sendRedirect("user.jsp");
    }
%>

<h1>Login</h1>

<jsp:include page="template/error.jsp" />

<form action="login" method="post">
			<table style="with: 50%">
				<tr>
					<td>Username or E-Mail</td>
					<td><input type="text" name="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
</form>
<jsp:include page="template/footer.jsp" />
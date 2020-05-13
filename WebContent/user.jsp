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

<h1>User Panel</h1>
<h3><% out.println(session.getAttribute("username")); %></h3>
<br>
<jsp:include page="template/success.jsp" />
<jsp:include page="template/error.jsp" />
<form action="user" method="post">
			<table style="with: 50%">
				<tr>
					<td>New Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>New Password Repeat</td>
					<td><input type="password" name="password_confirm" /></td>
				</tr>
				<tr>
					<td>Old Password</td>
					<td><input type="password" name="old_password" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
</form>
<a href="logout?x=1">Logout</a>
<jsp:include page="template/footer.jsp" />
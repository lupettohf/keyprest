<jsp:include page="template/header.jsp" />
<%@page import="keyprest.user_model.*"%>

<jsp:include page="template/error.jsp" />

<h1>User Panel</h1>
<h3><% out.println(session.getAttribute("username")); %></h3>
<br>
<a href="logout?x=1">Logout</a>
<jsp:include page="template/footer.jsp" />
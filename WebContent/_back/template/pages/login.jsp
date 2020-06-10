<jsp:include page="../skeletons/header.jsp" />
<%@page import="keyprest.user.*"%>

<%
	//Controlla che l'utente non sia giá loggato, e nel caso manda un redirect. 
   	String SessionKey = (String) session.getAttribute("sessionkey");
    if (null != SessionKey) {
    	response.sendRedirect("user");
    }
%>

<jsp:include page="../skeletons/error.jsp" />
<div class="container">
  <div class="row">
    <div class="col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
      <h2>Login</h2>
      <form action="login" method="post">
        <div class="form-group">
          <label for="username">Username:</label>
          <input type="text" name="username" class="form-control">
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" name="password" class="form-control">
        </div>
        <button type="submit" class="btn btn-default submit" value="Submit">Login</button>
      </form>
    </div>
  </div>
<jsp:include page="../skeletons/footer.jsp" />
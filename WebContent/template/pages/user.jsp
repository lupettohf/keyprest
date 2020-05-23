<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../skeletons/header.jsp" />
<%@page import="keyprest.user.*"%>

<jsp:include page="../skeletons/error.jsp" />

 <% 
 	//Controlla che l'utente sia giá loggato, e nel caso manda un redirect. 
    String SessionKey = (String) session.getAttribute("sessionkey");
    if (SessionKey.contains("false")) {
    	response.sendRedirect("login.jsp");
    } else {
    	User user = User_utils.getUser(SessionKey);
    	pageContext.setAttribute("username", user.getUsername());
    	pageContext.setAttribute("realname", user.getRealName());
    	pageContext.setAttribute("billingaddr", user.getBillingAddress());
    }
 %>
<jsp:include page="../skeletons/success.jsp" />
<jsp:include page="../skeletons/error.jsp" />

<div class="container">
  <div class="row">
    <div class="col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
      <h2>🕹 <small><c:out value="${username}" escapeXml="false"/></small></h2>
      <form action="user" method="post">
      	<br>
      	<h4>Change Password</h4>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" name="password" class="form-control">
          <label for="password">Repeat Password:</label>
          <input type="password" name="password_confirm" class="form-control">
          <label for="password">Old Password:</label>
          <input type="password" name="password_old" class="form-control">
        </div>
        <br>
        <h4>Billing Informations</h4>
        <div class="form-group">
          <label for="realname">Real Name:</label>
          <input type="realname" name="realname" value="<c:out value="${realname}" escapeXml="false"/>" class="form-control">
          <label for="address">Billing Address:</label>
          <input type="address" name="address" value="<c:out value="${billingaddr}" escapeXml="false"/>" class="form-control">
        </div>
        <button type="submit" class="btn btn-default submit" value="Submit">Change</button>
      </form>
    </div>
  </div>
<a href="logout?x=1">Logout</a>
<jsp:include page="../skeletons/footer.jsp" />
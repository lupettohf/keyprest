<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>    
<%@page import="keyprest.user.*"%>
<jsp:include page="../header.jsp" />
<jsp:include page="../navbar.jsp" />    

 <% 
 	//Controlla che l'utente sia giá loggato, e nel caso manda un redirect. 
    String LoggedIn = (String) session.getAttribute("logged");
 	String SessionKey = (String) session.getAttribute("sessionkey");
    if (LoggedIn == "false" || LoggedIn == null) {
    	response.sendRedirect("login.jsp");
    } else {
    	User user = User_utils.getUser(SessionKey);
    	pageContext.setAttribute("username", user.getUsername());
    	pageContext.setAttribute("realname", user.getRealName());
    	pageContext.setAttribute("billingaddr", user.getBillingAddress());
    }
 %>
    
      <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-8">
                        <div class="checkout_details_area mt-50 clearfix">
                            <div class="cart-title">
                                <h2>Hello ${username}</h2>
                            </div>

                            <form action="user" method="post">
                                <h4>Change Password</h4>
                                <div class="row">                                	
                                    <div class="col-10 mb-3">
                                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" value="">
                                    </div>
                                    <div class="col-10 mb-3">
                                        <input type="password" class="form-control" id="password_confirm" name="password_confirm" placeholder="Repeat Password" value="">
                                    </div>
                                    <div class="col-10 mb-3">
                                        <input type="password" class="form-control" id="password_old" name="password_old" placeholder="Old Password" value="">
                                    </div>
                                </div>
                                <h4>Change Personal Details</h4>
                                <div class="row">
                                	<div class="col-10 mb-3">
                                        <input type="text" class="form-control" id="realname" name="realname" placeholder="Real Name" value="<c:out value="${realname}" escapeXml="false"/>">
                                    </div>
                                    <div class="col-10 mb-3">
                                    	<input type="text" class="form-control" id="address" name="address" placeholder="Billing Address" value="<c:out value="${billingaddr}" escapeXml="false"/>">
                                    </div>
                                </div>
                                <div class="row">
                              		<div class="col-10">
                                      <div class="cart-btn mt-100">
                                          <input type="submit" class="btn amado-btn w-60 float-right" value="Change">
                                      </div>
                                	</div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
             
<jsp:include page="../footer.jsp" />
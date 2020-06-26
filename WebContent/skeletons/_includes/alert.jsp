<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="keyprest.store.*"%>

<% 
    pageContext.setAttribute("Error", (String) session.getAttribute("error"));
	pageContext.setAttribute("Info", (String) session.getAttribute("info"));
	pageContext.setAttribute("Success", (String) session.getAttribute("success"));
%>

<c:if test="${ Error != null }">
<div class="alert alert-danger" role="alert">
	${Error}
</div>
</c:if>
<c:if test="${ Info != null }">
<div class="alert alert-primary" role="alert">
	${Info}
</div>
</c:if>
<c:if test="${ Success != null }">
<div class="alert alert-success" role="alert">
	${Success}
</div>
</c:if>

<% 
	session.removeAttribute("error");
	session.removeAttribute("info");
	session.removeAttribute("success");
%>
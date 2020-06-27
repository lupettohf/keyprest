<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="keyprest.store.*"%>

<% 
    String Error = (String) session.getAttribute("error");
    String Info = (String) session.getAttribute("info");
	String Success = (String) session.getAttribute("success");
%>

<% if(Error != null){ %>
<% if(!Error.isEmpty()) {%>
<div class="alert alert-danger" role="alert">
	<%= Error %>
</div>
<% } %> 
<% } %> 
<% if(Info != null){ %>
<% if(!Info.isEmpty()) {%>
<div class="alert alert-primary" role="alert">
	<%= Info %>
</div>
<% } %> 
<% } %> 
<% if(Success != null){ %>
<% if(!Info.isEmpty()) {%>
<div class="alert alert-success" role="alert">
	<%= Success %>
</div>
<% } %> 
<% } %> 
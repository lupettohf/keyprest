<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="java.util.ArrayList" %> 
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>
<% pageContext.setAttribute("pages", (int) session.getAttribute("totalpages"));  %>
<% pageContext.setAttribute("curpage", (int) session.getAttribute("curpage"));  %>

<jsp:include page="../../header.jsp" />
<jsp:include page="../../navbar.jsp" />

<jsp:include page="listproducts.jsp" />
    
<jsp:include page="../../footer.jsp" />
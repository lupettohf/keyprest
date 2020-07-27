<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="keyprest.store.*"%>
<%@page import="java.util.ArrayList" %> 
<% ArrayList<Product> products = (ArrayList<Product>) session.getAttribute("products");  %>
<% pageContext.setAttribute("pages", (int) session.getAttribute("totalpages"));  %>
<% pageContext.setAttribute("curpage", (int) session.getAttribute("curpage"));  %>
        <div class="cart-table-area section-padding-100">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-lg-12">
                        <div class="cart-title mt-20">
                            <h2>Housekeeping</h2>                                     
                            <ul class="pagination justify-content-end mb-30">                            
<c:forEach var = "i" begin = "0" end = "${pages}">
<c:if test="${ curpage == i }">
                            	<li class="page-item active"><a class="page-link" href="housekeeping?p=${i}">${i}</a></li>
</c:if>
<c:if test="${ curpage != i }">
                                <li class="page-item"><a class="page-link" href="housekeeping?p=${i}">${i}</a></li>
</c:if>
</c:forEach>                                
                            </ul>
                        </div>
                        <div class="clearfix">
                            <table class="table table-responsive">
                                <thead>
                                    <tr>
                                        <th style="flex: 0 0 15%;">Product Name</th>
                                        <th style="flex: 0 0 10%;">Price</th>
                                        <th style="flex: 0 0 10%;">Region</th>
                                        <th style="flex: 0 0 10%;">ID</th>
                                        <th style="flex: 0 0 10%;">Keys Stock</th>
                                        <th style="flex: 0 0 10%;"></th>
                                        <th style="flex: 0 0 10%;"></th>
                                    </tr>
                                </thead>
                                <tbody>
<c:forEach var="item" items="${products}">                             
                                    <tr>
                                        <td style="flex: 0 0 15%;">
                                            <h5>${item.getName()}</h5>
                                        </td>
                                        <td style="flex: 0 0 10%;">
                                            <span>${item.getPrice()}$</span>
                                        </td>
                                       	<td style="flex: 0 0 10%;">
                                       		<span>${item.getRegion()}</span>
                                       	</td>
                                       	<td style="flex: 0 0 10%;">
                                    		<h5>${item.getID()}</h5>
                                    	</td>
                                    	<td style="flex: 0 0 10%;">
                                    		<h5>${item.getStock()}</h5>
                                    	</td>
                                       	<td style="flex: 0 0 10%;">
                                       		<a class="keyprest-btn text-center" href="housekeeping?action=edit&id=${item.getID()}"> Edit</a> 
                                       	</td>
                                       	<td style="flex: 0 0 10%;">
                                       		<a class="keyprest-btn text-center" href="housekeeping?action=import&id=${item.getID()}"> Import Keys</a> 
                                       	</td>
                                    </tr>
</c:forEach>                           
                                </tbody>
                            </table>
                        </div>
                    </div>
				    <ul class="pagination justify-content-start mb-30">                           
						<a class="keyprest-btn text-center" href="createproduct">Create Product</a> 
                    </ul>
               </div>	
		</div>
</div>					
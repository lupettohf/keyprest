<%
String errorMsg = (String) session.getAttribute("error");
if (null !=errorMsg) { %> <h4> <%= errorMsg %></h4> <%}
%>

<% 

// Pulisce l'errore dopo averlo mostrato
session.removeAttribute("error"); 

%>
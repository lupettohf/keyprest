<%
String message = (String) session.getAttribute("success");
if (null !=message) { %> <h4 style="color: green;"> <%= message %></h4> <%}
%>

<% 

// Pulisce il messaggio dopo averlo mostrato
session.removeAttribute("success"); 

%>
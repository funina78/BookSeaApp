<%-- 
    Author     : Nina
--%>

<%@ page import="webapp.model.Book" %>
<%@ page import="java.util.ArrayList" %>
<%
  String passmessage = (String)request.getAttribute("passmessage");
  String emailmessage = (String)request.getAttribute("emailmessage");

%>
<%@include file="/includes/header.jsp" %>
<link href="includes/css/recipelist.css" rel="stylesheet" type="text/css">
<div id="body">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <ul id="latest">
           <li> <%=passmessage%></li>
           <li> <%=emailmessage%></li>
        </ul>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>
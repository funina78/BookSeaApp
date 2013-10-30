<%--
    Document   : registration
    Author     : Nina
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% //passed in data
    String title = "Bye bye";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="includes/css/core.css" rel="stylesheet" type="text/css">
        <link href="includes/css/logout.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            var redirect = function(url){
                setTimeout("window.location ='" + url + "'", 3000);
            }
        </script>
        <title><%=title%> | Yummy</title>
    </head>
    <body id="monsters_content" onload="redirect('recommend');">
        <div id="logout">
            <p>Bye bye!</p>
        </div>
<%@include file="/includes/footer.jsp" %>

<%--
      Author     : Nina
--%>
<%@ page import="webapp.model.Book" %>
<%
    String feedback_message = (String) request.getAttribute("feedback_message");

    String feedback_link = (String) request.getAttribute("feedback_link");
    String feedback_src = (String) request.getAttribute("feedback_src");

    //need to figure out why relative paths for images  won't work here
    String image_path = "http://localhost:8081/Yummy/includes/images/";
%>
<%@include file="/includes/header.jsp" %>
<link href="includes/css/feedback.css" rel="stylesheet" type="text/css">
<div id="body" style="height: 220px">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <ul id="feedback">
            <li><%=feedback_message%></li>
            <% if(feedback_link != null && feedback_src != null){ %>
                <li><a href="<%=feedback_src%>" ><%=feedback_link%></a></li>
            <% } %>
        </ul>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>

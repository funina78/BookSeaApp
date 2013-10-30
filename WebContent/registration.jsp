<%-- 
    Document   : registration
    Author     : Nina
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<% //passed in data    
    String validation = (String) request.getAttribute("validation");
    String message = (String) request.getAttribute("message");
    String valid_name = (String) request.getAttribute("valid_name");
    String valid_password = (String) request.getAttribute("valid_password");
    String valid_email = (String) request.getAttribute("valid_email");

    String title = "Registration";
    if (validation == "false") {
        title = title + " error";
    }
%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="includes/css/core.css" rel="stylesheet" type="text/css">
        <link href="includes/css/registration.css" rel="stylesheet" type="text/css">
        <title><%=title%> | Cooking Monsters</title>
    </head>
    <body id="monsters_content">
        <div id="registration">
            <form action="registerUserServlet" method="POST" enctype="multipart/form-data">
                
                <p id="header_message">
                    Become a Cooking Monster!
                </p>
                <% if(message != null){%>
                    <p id="error_message">
                        <%=message%>
                    </p>
                <%}%>
                <p>
                    <%if(valid_name != null){%><label for="username_error" class="error_label"><%=valid_name%></label><%}%>
                    <label for="username">Monstername</label>
                    <input class="text_area" type="text" name="username"/>
                </p>
                <p>
                    <%if(valid_password != null){%><label for="password_error" class="error_label"><%=valid_password%></label><%}%>
                    <label for="password">Password</label>
                    <input class="text_area" type="password" name="password"/>
                </p>
                <p>
                    <%if(valid_email != null){%><label for="email_error" class="error_label"><%=valid_email%></label><%}%>
                    <label for="email">Email</label>
                    <input class="text_area" type="text" name="email"/>
                </p>
                <input class="button" name="submit" type="submit" value="Submit"/>
            </form>
        </div>
<%@include file="/includes/footer.jsp" %>

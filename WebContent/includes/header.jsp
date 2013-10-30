<%--
    Document   : header
    Author     : Nina
--%>
<%@ page import="webapp.controller.SessionManager" %>
<% //passed in data
    SessionManager sm = new SessionManager(request);
    boolean user_status = sm.isLoggedIn();
    String user_name = sm.getUserName();
    int user_id = sm.getUserID();
    
    String login_message = (String) request.getAttribute("login_message");
    if(login_message == null){
        login_message = "";
    }

    String feedback_redirect = (String) request.getAttribute("feedback_redirect");
    
    String title = (String) request.getAttribute("title");
    if(title == null || title == "") {
        title = "";
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title><%=title%> | BookSea </title>
        <link href="includes/css/core.css" rel="stylesheet" type="text/css">
        <script type="text/javascript">
            var redirect = function(url){
                setTimeout("window.location ='" + url + "'", 3000);
            }
        </script>
    </head>
    <body id="monsters_content" <% if(feedback_redirect != null && !feedback_redirect.equals("")){ %> onload="redirect('<%=feedback_redirect%>');" <% } %>>
        <div id="header">
            <a href="recommend" id="logo"><span>yummy.com</span></a>
            <div id="search">
                <form action="search" method="get">
                
                    <% String type = (String) request.getAttribute("Type");
                    if (type == null) type = ""; %>                
                	<label for="Type">Type</label>
					<select name="Type">
						<option value="0">  </option>
  						<option value="4" <% if(type.equals("4")){%>SELECTED<%}%>> American </option>
						<option value="3" <% if(type.equals("3")){%>SELECTED<%}%>> Chinese </option>
						<option value="1" <% if(type.equals("1")){%>SELECTED<%}%>> Italian </option>
  						<option value="6" <% if(type.equals("6")){%>SELECTED<%}%>> Japanese </option>
  						<option value="5" <% if(type.equals("5")){%>SELECTED<%}%>> Mexican </option>
						<option value="2" <% if(type.equals("2")){%>SELECTED<%}%>> Thai </option>
					</select> 

                    <% String pricing = (String) request.getAttribute("Price");
                    if (pricing == null) pricing = ""; %>
				   	<label for="Price">Price</label>
					<select name="Price">
						<option value=""> </option>
  						<option value="$" <% if(pricing.equals("$")){%>SELECTED<%}%>> $+ </option>
						<option value="$$" <% if(pricing.equals("$$")){%>SELECTED<%}%>> $$+ </option>
						<option value="$$$" <% if(pricing.equals("$$$")){%>SELECTED<%}%>> $$$+ </option>
  						<option value="$$$$" <% if(pricing.equals("$$$$")){%>SELECTED<%}%>> $$$$+ </option>
  						<option value="$$$$$" <% if(pricing.equals("$$$$$")){%>SELECTED<%}%>> $$$$$ </option>
					</select> 

                    <% String rating = (String) request.getAttribute("Rating");
                    if (rating == null) rating = ""; %>
				   	<label for="Rating">Rating</label>
					<select name="Rating">
						<option value="0"> </option>
  						<option value="1" <% if(rating.equals("1")){%>SELECTED<%}%>> *+ </option>
						<option value="2" <% if(rating.equals("2")){%>SELECTED<%}%>> **+ </option>
						<option value="3" <% if(rating.equals("3")){%>SELECTED<%}%>> ***+ </option>
  						<option value="4" <% if(rating.equals("4")){%>SELECTED<%}%>> ****+ </option>
  						<option value="5" <% if(rating.equals("5")){%>SELECTED<%}%>> ***** </option>
					</select> 
                        
                    <% String keyword = (String) request.getAttribute("keyword");
                    if (keyword == null) keyword = ""; %>
                    <input class="text_area" type="text" name="keyword" value="<%=keyword%>"/>
                    <input class="button" type="submit" value="Search" />
                </form>
            </div>
            <div id="user_status">
                <% if(user_status){ %>
                    <p id="welcome_message">WELCOME <span id="username"><%=user_name %></span> !</p>
                    <p id="sign_out">Wrong user? <a href="logout">Sign out</a>.</p>
                <%}else{%>
                    <%if(login_message != ""){%>
                        <p><%=login_message%></p>
                    <%}%>
                    <form action="login" method="post">
                        <input class="text_area" value="username" type="text" name="monster_name"/>
                        <input class="text_area" value="password" type="password" name="password"/>
                        <input class="button" type="submit" value="Sign In"/>
                    </form>
                    <p id="registration_message">Not a member? <a id="header_register" href="register">Register</a>, it is fun and free!</p>
                <% }%>
            </div>
        </div>
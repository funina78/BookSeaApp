<%--
    Document   : header
    Author     : Nina
--%>
<%@page import="webapp.model.User" %>
<%@ page import="webapp.controller.SessionManager" %>
<% //passed in data
String current_page = "";
if(request.getAttribute("current_page") != null) {
    current_page = (String) request.getAttribute("current_page");
}

User u = (User) request.getAttribute("user");
boolean userStatus = new SessionManager(request).isLoggedIn();

%>
<div id="left">
    <% if(userStatus){%>
        <div id="fullmenu" class="menu">
    <% } else {%>
        <div id="basicmenu" class="menu">
    <% } %>
           <div class="bd">
                <ul class="first-of-type">
                    <% if(userStatus){ %>
                        <li>
                            <a <%if(current_page.equals("accountsettings")){%>class="current"<%}%> href="accountsettings">Account Settings</a>
                        </li>
                    <% } %>
                    <li>
                        <a <%if(current_page.equals("recommend")){%>class="current"<%}%> href="recommend">This Week's Top Books</a>
                    </li>
                    <li>
                        <a <%if(current_page.equals("favorite")){%>class="current"<%}%> href="favorite">BookSea's Favorite</a>
                    </li>
                </ul>
            </div>
    </div>
</div>

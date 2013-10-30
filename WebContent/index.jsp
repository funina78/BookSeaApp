<%-- 
    Document   : index
    Author     : Nina
Purpose: Defines menu used throughout webapp
--%>
<%
    //need to figure out why relative paths for images  won't work here
    String image_path = "http://localhost:8081/BookSeaApp/includes/images/";
%>
<%@include file="/includes/header.jsp" %>
<div id="body">
    <div id="left">
        <% if(user_status){%>
            <div id="fullmenu" class="menu">
        <% } else {%>
            <div id="basicmenu" class="menu">
        <% } %>
               <div class="bd">
                    <ul class="first-of-type">
                        <% if(user_status){ %>
                            <li>
                                <a href="myBooks">My Books</a>
                            </li>
                            <li>
                                <a href="getBookMark">Books Recommended to me</a>
                            </li>
                            <li>
                                <a href="#">Account Setings</a>
                            </li>
                        <% } %>
                        <li>
                            <a class="current" href="recommend">This Week's Top Books</a>
                        </li>
                        <li>
                            <a href="#">Books Others like</a>
                        </li>
                        <li>
                            <a href="#">BookSea's Favorite</a>
                        </li>
                    </ul>
                </div>
        </div>
    </div>
    <div id="right">

    </div>
</div>
<%@include file="/includes/footer.jsp" %>
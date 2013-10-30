<%-- 
    Author     : Nina
--%>
<%@ page import="webapp.model.Book" %>
<%@ page import="webapp.model.User" %>
<%@ page import="webapp.model.UserManager" %>
<%
	Book r = (Book) request.getAttribute("book");

UserManager mgr = new UserManager();

String book_image_url = r.getId() + ".jpg";
if(r.getImage_url() != null)
	book_image_url = r.getImage_url();

String name = r.getName();
String author = r.getAuthor();
String publishDate = r.getPublishDate();
String price = r.getPrice();
String rate = r.getRating();
String description = r.getDescription();

String image_path = "http://localhost:8081/BookSeaApp/includes/images/";
%>
<%@include file="/includes/header.jsp" %>
<link href="includes/css/viewrecipe.css" rel="stylesheet" type="text/css">
<link href="includes/css/addcomment.css" rel="stylesheet" type="text/css">
<div id="body">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <div id="tab"><span>Add Comment</span></div>
       <div class="view_recipe" id="recipe_<%=r.getId() %>">
            <img width="140" height="140" title="<%=r.getName()%>" src="books/<%=book_image_url %>" alt="books picture!"/>
            <p class="title"><%=name%></p>
            <p class="rating">Author: <%=author%></p>
            <p class="rating">Published: <%=publishDate%></p>
            <p class="rating">Price: <%=price%></p>
            <p class="rating">Rating: <%=rate%>/5.0</p>
            <p class="rating">Introduction: <%=description%></p>
           
        </div>
        <div id="comment_form">
            <form action="addComment" method="get">
                <input type="hidden" name="book_id" value="<%=r.getId() %>"/>
                <p>
                    <label class="rating" for="rating">Rating:</label>
                    <input class="text_area" type="text" name="rating"/>
                </p>
                <p>
                    <label class="comment_text" for="comment_text">Comment:</label>
                    <textarea rows="5" cols="49" wrap="physical" name="comment_text"></textarea>
                </p>
                <p>
                    <input class="button" name="submit" type="submit" value="Submit"/>
                </p>
            </form>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>


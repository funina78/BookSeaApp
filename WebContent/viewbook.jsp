<%-- 
    Document   : viewbook
    Author     : Nina
              
--%>

<%@ page import="webapp.model.Book" %>
<%@ page import="webapp.model.Comment" %>
<%@ page import="webapp.model.UserManager" %>
<%@ page import="webapp.model.User" %>
<%@ page import="java.util.ArrayList" %>
<%
	Book r = (Book) request.getAttribute("book");
    String book_image_url = r.getId() + ".jpg";
    if(r.getImage_url() != null)
        book_image_url = r.getImage_url();

    ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");

    int commentHeight = (comments == null) ? 0 : comments.size();


    //double rate = 0.0;
    
     
     String name = r.getName();
     String author = r.getAuthor();
     String publishDate = r.getPublishDate();
     String price = r.getPrice();
     String rate = r.getRating();
     String description = r.getDescription();

    UserManager um = new UserManager();
%>

<%@include file="/includes/header.jsp" %>
<link href="includes/css/viewbook.css" rel="stylesheet" type="text/css">
<div id="body" style="height:<%=(600 + commentHeight * 210)%>px">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <%if(user_status){%>
            <div id="recipe_actions">
                <!--  <a id="rate" title="Rate" href="rate?book_id=<%=r.getId() %>"><span>Rate Book</span></a> -->
                <a id="comment" title="Comment" href="addcomment?book_id=<%=r.getId() %>"><span>Review This Book</span></a>
            </div>
        <%}%>
        <div class="view_recipe" id="recipe_<%=r.getId() %>">
            <img width="140" height="140" title="<%=r.getName()%>" src="books/<%=book_image_url %>" alt="books picture!"/>
            <p class="title"><%=name%></p>
            <p class="rating">Author: &nbsp;&nbsp;&nbsp;<%=author%></p>
            <p class="rating">Published: &nbsp;&nbsp;&nbsp;<%=publishDate%></p>
            <p class="rating">Price: &nbsp;&nbsp;&nbsp;<%=price%></p>
            <p class="rating">Rating: &nbsp;&nbsp;&nbsp;<%=rate%>/5.0</p>
            <p class="rating">Introduction: <br/><br/><%=description%></p>
           
         
        </div>
        <div id="comments">
             <%if(comments != null){
                 for(int i = 0; i < comments.size(); i++){
                	User commentOwner = um.getUserByID(comments.get(i).getUser_id());                 
                    String owner_img_url = "default.jpg";
             %>
                 <ul>
                     <li class="image"><img width="80px" height="71px" src="users/<%=owner_img_url %>"/></li>
                     <li class="posted_date"><p>Posted: <%=comments.get(i).getDate() %></p></li>
                     <li class="posted_date"><p>By: <%=commentOwner.getUserName() %></p></li>
                     <li class="comment_text"><p><%=comments.get(i).getReview() %></p></li>
                 </ul>
             <%}
             }%>
        </div>
        <div>
        
        <!-- insert recommended books -->
        </div>
 
<%@include file="/includes/footer.jsp" %>

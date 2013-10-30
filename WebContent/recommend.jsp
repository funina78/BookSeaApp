<%--
    
    Author     : Nina
--%>
<%@ page import="webapp.model.Book" %>
<%@ page import="webapp.model.User" %>
<%@ page import="webapp.model.UserManager" %>

<%
	Book[] recommend_book = (Book[]) request.getAttribute("recommend");
%>
<%@include file="/includes/header.jsp" %>
<link href="includes/css/booklist.css" rel="stylesheet" type="text/css">
<div id="body">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <ul id="latest">
        <%
        	 UserManager mgr = new UserManager();
                for(int i = 0; i < recommend_book.length; i++){
                    Book r = recommend_book[i];
                    //String author=mgr.getUserNameByID(r.getOwnerId());

                    String image_path = r.getImage_url();
                    if(image_path == null)
                        image_path = Integer.toString(r.getId()) + ".jpg";

                    String rate = "";
                    
                    String name = r.getName();
                    String author = r.getAuthor();
                    String publishDate = r.getPublishDate();
                    String price = r.getPrice();
                    rate = r.getRating();
                    //String description = r.getDescription(); 
        %>
               <li id="recipe_<%=r.getId() %>" >
                    <img width="140" height="140" title="<%=r.getName()%>" src="books/<%=image_path %>" alt="booka picture!"/>
                
            		<p class="title"><a href="viewbook?book_id=<%=r.getId() %>"><%=name%></a></p>
            		<p class="rating">Author: <%=author%></p>
            		<p class="rating">Published: <%=publishDate%></p>
            		<p class="rating">Price: <%=price%></p>
            		<p class="rating">Rating: <%=rate%>/5.0</p>
            		
                </li>
  
        
                  
           
            <% }
           %>
     </ul>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>

<%-- 
    Author     : Nina
--%>

<%
String cemail = (String) request.getAttribute("cemail");
String image_path = "http://localhost:8081/Yummy/includes/images/";

%>
<%@include file="/includes/header.jsp" %>
<script type="text/javascript">
    var changeTab = function(v){
        var tabs = document.getElementById("tabs").childNodes;
        for(var i = 0; i < tabs.length; i++){
            tabs[i].className = "";
        }
        var contents = document.getElementById("contents").childNodes;
        for(var j = 0; j < contents.length; j++){
            contents[j].className = "";
        }
        document.getElementById(v+"_tab").className = "current";
        document.getElementById(v+"_content").className = "current";
    }
</script>
<link href="includes/css/accountsettings.css" rel="stylesheet" type="text/css">
<div id="body">
    <%@include file="/includes/menu.jsp" %>
    <div id="right">
        <div id="account_settings">
            <div id="tabs">
                <div class="current" onclick="changeTab('password')" id="password_tab"><span>password</span></div>
             </div>
            <div id="contents">         
                <div class="current" id="password_content">
                    <form action="accountSettingsServlet" method="POST" enctype="multipart/form-data">
                        <p>
                            <label for="old_password">Current Password: </label>
                            <input type="password" name="old_password"/>
                        </p>
                        <p>
                            <label for="new_password">New Password: </label>
                            <input type="password" name="new_password"/>
                        </p>
                        <p>
                            <input type="hidden" name="change_password"/>
                            <input class="button" name="submit" type="submit" value="Submit"/>
                        </p>
                    </form>
                </div>
                
            </div>
        </div>
    </div>
</div>
<%@include file="/includes/footer.jsp" %>

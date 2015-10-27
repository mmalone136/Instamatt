<%-- 
    Document   : SinglePic
    Created on : 21-Oct-2015, 15:45:05
    Author     : Matt
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uk.ac.dundee.computing.aec.instamatt.stores.LoggedIn"%>
<%@page import="java.util.UUID"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html style="height:100%">
        <head>
            <link rel="stylesheet" type="text/css" href="../Styles.css"  />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <nav> 

            <ul>
                <li><a href="../index.jsp">
                        <img border="0" alt="LOGOTHINGS" src="../stuff/logo2.png" width="45" height="45">
                    </a></li> <br> 
                <li><a href="../index.jsp">Home</a></li>
                <li><a href="../upload.jsp">Upload</a></li>
                <form id="search" method="GET" action="../search" >
                    <input type="text" name="target" />
                    <input type='submit' />
                </form>

            </ul>
        </nav>
    </head>
    <body id="page" style="display: inline-block">
        <h1 style="margin-left:50px" >Soem relevant text</h1>

        <%

            String str = (String) request.getAttribute("pic");
            UUID pic = UUID.fromString(str);


        %>

        
        <img style="margin-left:75px" border="0" height="450px" src="/instamatt/Image/<%=pic%>" >
        
        
        <div style="float:right; background-color:grey">
        <%
            String found = (String) request.getAttribute("found");
            if (found == "no") {
        %>
        <p> no comments yet</P>
        
        
        <%
        } else {
         %>
         --------------------------------------<br>
         <%
            ArrayList<String[]> comments = new ArrayList<String[]>();
            comments = (ArrayList<String[]>) request.getAttribute("ComList");
            int x =  comments.size();
            String[] curr = new String[3];
            for (int i = 0; i < comments.size(); i++) {
                curr = comments.get(i);

        %>
        
        USER:     <br><%=curr[1]%>  <br><br>
        COMMENT:  <br><br><%=curr[0]%>  <br><br>
        TIME:     <br><%=curr[2]%>  <br>  
        <br>--------------------------------------<br>
        
        <%
                }
            }
        %>
        </div>
        <%
            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) {
                String user = lg.getUsername();
        %>
        
        <form style="margin-top:100px; margin-left:100px" method="POST"  action="picture">
            <ul>
                <li>Comment    <input style="margin-left:50px"type="text" maxlength="80" name="comment"></li><br><br>
                <input type="hidden" name="user" value="<%=user%>">
                <input type="hidden" name="picid" value="<%=pic%>">
            </ul>
            <input type="submit" value="Post Comment"> 
        </form>
        <%}%>
    </body>
</html>

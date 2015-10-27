<%-- 
    Document   : test
    Created on : Sep 29, 2014, 9:16:48 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instamatt.stores.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body id = "page" style=" background-repeat: no-repeat;
   background-image: url('stuff/9551.jpg');
   background-attachment: fixed;
   background-size:100% auto;
   color:white;
   margin-top: 75px;">
        
        <h1>404 - PAGE NOT FOUND</h1>
        
        
    <li> <a href="/instamatt">REDIRECT Here</a></li>
    
    <br><br><br><br>
       

    
    <%
            Pic ps = new Pic();
            LoggedIn lg = new LoggedIn();
            %>
    </body>
</html>

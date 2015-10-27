<%-- 
    Document   : profile
    Created on : 15-Oct-2015, 22:35:10
    Author     : Matt
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instamatt.stores.*" %>
<!DOCTYPE html>
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
<body id="page" style="margin-left:10px">
    <div >
        <h1> Profile</h1>


        <%
            String first = (String) request.getAttribute("first_name");
            String last = (String) request.getAttribute("last_name");
            String email = (String) request.getAttribute("email");
            String Username = (String) request.getAttribute("username");
        %>

        <br>
        Username    : <%=Username%>
        <br><br>
        First Name  : <%=first%> 
        <br><br>
        Last Name   : <%=last%>
        <br><br>
        Email       : <%=email%>

        <br><br><br><br> 

        <li><a style="color:white;font-size: 20px" href="/instamatt/Images/<%=Username%>">User's Images</a></li>

        <%

            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) {
                String currUser = lg.getUsername();
                if (currUser.equals(Username)) {
        %>
        <br><br>
        <li><a style="color: white; font-size: 20px" href="/instamatt/UpdateDetails.jsp">Update Details</a></li>    
            <%} else {
                    }
                }%>

    </div>
    <%
        String str = (String) request.getAttribute("profpic");
        if (str != null) {
            UUID pic = UUID.fromString(str);

    %>

    <img style="float:right; margin-right:75px; margin-left:75px; margin-top:60px" border="0" height="300px" src="/instamatt/Image/<%=pic%>" >
    <%
    } else {
    %>
    <img style="float:right; margin-right:75px; margin-left:75px; margin-top:60px" border="0" height="300px" src="../stuff/defaultPic.png" >

    <%}%>
</body>
</html>
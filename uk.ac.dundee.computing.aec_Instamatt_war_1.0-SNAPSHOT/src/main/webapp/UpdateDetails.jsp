<%-- 
    Document   : UpdateDetails
    Created on : 24-Oct-2015, 16:44:22
    Author     : Matt
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instamatt.stores.*" %>
<!DOCTYPE html>
<html style="height:100%">
    <head>
        <link rel="stylesheet" type="text/css" href="Styles.css"  />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <nav> 
        <ul>
            <li><a href="index.jsp">
                    <img border="0" alt="LOGOTHINGS" src="stuff/logo2.png" width="45" height="45">
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
<body id="page">
    <div>
    <h1>Hello World!</h1>

    <% LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
        if (lg != null) {
            String currUser = lg.getUsername();
    %>

<li>User Name : <%=currUser%> </li><br><br>

<form method="POST"  action="update" >
    <ul>
        <input type="hidden" name="user" value="<%=currUser%>">
        <li>First Name   <input style="margin-left:50px"type="text" name="first_name"></li><br><br><br>
        <li>Surname      <input style="margin-left:65px"type="text" name="last_name"></li><br><br>
        <li>Email        <input style="margin-left:82px"type="email" name="email"></li><br><br>


        <br>
    </ul>
    <br>
    <input type="submit" value="UPDATE"> 
</form>

    </div>
        
<form style="float:right; margin-right:80px; margin-top:100px" method="POST" enctype="multipart/form-data" action="Image">
    Profile picture to upload: <br><br><input type="file" name="upfile"><br>
    <input type ="hidden" name="check" value="1"><br>
    <input type="submit" value="Press"> to upload the file!
</form>

<%} else {
//redirect
    }
%>

</body>
</html>

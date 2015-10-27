<%-- 
    Document   : index
    Created on : Sep 28, 2014, 7:01:44 PM
    Author     : Administrator
--%>


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
            <li><a href="index.jsp">Home</a></li>
            <li><a href="upload.jsp">Upload</a></li>

            <form id="search" method="GET" action="search" >
                <input type="text" name="target" />
                <input type='submit' />
            </form>

        </ul>

    </nav>
</head>
<body id="page">

    <p >
        <br><br>  <br><br>       
        <a href="../java/uk/ac/dundee/computing/aec/instamatt/stores/LoggedIn.java"></a>
        <%

            LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
            if (lg != null) {
                String UserName = lg.getUsername();
                if (lg.getlogedin()) {
        %>

<li><a href="/instamatt/Images/<%=lg.getUsername()%>">Your Images</a></li>

<%
    //session.setAttribute("username",lg.getUsername());
%>
<!--<li><a  href="/instamatt/profile/<%=lg.getUsername()%>">Your Profile</a></li>-->

<form method="GET" Action="profile/<%=lg.getUsername()%>">
     <input type="hidden" value="<%=lg.getUsername()%>" name="username">
    <input id="buttonTwo" type="submit" value="Your Profile">
    
</form>
   


<form style=" margin-top:60px;margin-left:50px"action="LogOut" method="POST">

    <input type="submit" value="Logout"> 
</form>

<%}
} else {
%>

<ul style="margin-left:200px">
    <a id='button' href="register.jsp"><br><br><br><br>Register</a>
    <a id='button' href="login.jsp"><br><br><br><br>Login</a>
</ul>

<%
    }
%>
</p>
</body>   

</html>

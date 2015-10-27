<%-- 
    Document   : SearchResults
    Created on : 15-Oct-2015, 22:34:46
    Author     : Matt
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uk.ac.dundee.computing.aec.instamatt.stores.*" %>
<!DOCTYPE html>
<html style="height:100%">
    <head>
        <link rel="stylesheet" type="text/css" href="/instamatt/Styles.css"  />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <nav> 

        <ul>
            <li><a href="index.jsp">
                    <img border="0" alt="LOGOTHINGS" src="/instamatt/stuff/logo2.png" width="45" height="45">
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
    <h1>SEARCH RESULTS</h1>


    <article>
    </article>
    Put the results of the datas of things here

    <%
        String found = (String) request.getAttribute("found");
        String username = (String) request.getAttribute("username");
        ArrayList<String[]> list = (ArrayList<String[]>) request.getAttribute("list");
    %>
    <br><br>
    <% if (found == "YES") {
            for (int i = 0; i < list.size(); i++) {
                String[] temp = list.get(i);

                username = temp[0];
                String first = temp[1];
                String last = temp[2];             

    %>  
    <br><br>
    First Name :   <%=first%> <br>
    Surname    :   <%=last%> <br>
    Username   :   <%=username%> <br>

<!--<li><a  href="/instamatt/profile/<%//=username%>">User's Profile</a></li> -->


<form method="GET" Action="profile/<%=username%>">
     <input type="hidden" value="<%=username%>" name="username">
    <input id="buttonTwo" type="submit" value="User's Profile">
    
</form>

<br><br>
<% }
} else {

%> 
<p>No Results were found for "<%=username%>"</p>

<%  }%>             
</body>



</body>
</html>
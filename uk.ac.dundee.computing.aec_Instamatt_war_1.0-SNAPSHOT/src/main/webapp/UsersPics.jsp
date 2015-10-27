<%-- 
    Document   : UsersPics
    Created on : Sep 24, 2014, 2:52:48 PM
    Author     : Administrator
--%>

<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="uk.ac.dundee.computing.aec.instamatt.stores.*" %>
<!DOCTYPE html>
<html style="height:100%">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instamatt</title>
        <link rel="stylesheet" type="text/css" href="/instamatt/Styles.css" />
    </head>
    <body id="page">

        <nav>
            <ul>
                <li><a href="../index.jsp">
                        <img border="0" alt="LOGOTHINGS" src="../stuff/logo2.png" width="45" height="45">
                    </a></li> <br> 
                <li class="nav"><a href="/instamatt/upload.jsp">Upload</a></li>
                <li class="nav"><a href="/instamatt/Images/majed">Sample Images</a></li>
                <form id="search" method="GET" action="../search" >
                    <input type="text" name="target" />
                    <input type='submit' />
                </form>
            </ul>
        </nav>

        <article>
            <%

                String curr = (String) request.getAttribute("curr");

                String url = request.getRequestURL().toString();

                LoggedIn lg = (LoggedIn) session.getAttribute("LoggedIn");
                if (lg != null) {
                    String UserName = lg.getUsername();
                      //if (lg.getlogedin()) {

            %>
            <h1><%=curr%>              's Pictures</h1>

            <%
                }
                java.util.LinkedList<Pic> lsPics = (java.util.LinkedList<Pic>) request.getAttribute("Pics");
                if (lsPics == null) {
            %>
            <p>No Pictures found</p>
            <%
            } else {
                Iterator<Pic> iterator;
                iterator = lsPics.iterator();
                while (iterator.hasNext()) {
                    Pic p = (Pic) iterator.next();

            %>

            <form method="GET" action="../picture/<%=p.getSUUID()%>" >
                <input type="hidden" value="<%=p.getSUUID()%>" name="pic">

                <input id="seven" type='image' name="submit" src="/instamatt/Thumb/<%=p.getSUUID()%>" border="0" height="275px" width="275px"/>

            </form>

            <%}
                    }%>



        </div> 

        <br><br><br>
    </article>

    <footer style="margin-top:1000px">            
        <li class="footer"><a style="padding:50px; text-align:center; color:white"  href="/instamatt">Home</a></li>
        <br><br><br>
    </footer>

</body>
</html>

<%-- 
    Document   : login.jsp
    Created on : Sep 28, 2014, 12:04:14 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instamatt</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />

    </head>
    <body id="page">

        <nav>
            <ul>
                <li><a href="index.jsp">
                        <img border="0" alt="LOGOTHINGS" src="stuff/logo2.png" width="45" height="45">
                    </a></li> <br> 
                <li><a href="/instamatt/Images/majed">Sample Images</a></li>
                <form id="search" method="GET" action="search" >
                    <input type="text" name="target" />
                    <input type='submit' />
                </form>

            </ul>
        </nav>

        <article>
            <h3>Login</h3>
            <form method="POST"  action="Login">
                <ul>
                    <li>User Name <input type="text" name="username"></li>
                    <li>Password <input type="password" name="password"></li>
                </ul>
                <br/>
                <input type="submit" value="Login"> 
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/instamatt">Home</a></li>
            </ul>
        </footer>
    </body>
</html>

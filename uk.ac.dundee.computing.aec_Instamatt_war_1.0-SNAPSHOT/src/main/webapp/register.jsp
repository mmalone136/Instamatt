<%-- 
    Document   : register.jsp
    Created on : Sep 28, 2014, 6:29:51 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head style="height:100%">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>instamatt</title>
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body id="page">
        <header>
        <h1>instamatt ! </h1>
        <h2>Your world in Black and White</h2>
        </header>
        <nav>
            <ul>

        <li><a href="index.jsp">
                <img border="0" alt="LOGOTHINGS" src="stuff/logo2.png" width="45" height="45">
            </a></li> <br> 
            
            <li><a href="index.jsp">Home</a></li>
             <li><a href="/instamatt/Images/majed">Sample Images</a></li>
             <form id="search" method="GET" action="search" >
                <input type="text" name="target" />
                <input type='submit' />
            </form>

            </ul>
        
           </nav>
                

       
        <article>
            <h3>Register as user</h3>
            <form method="POST"  action="Register">
                <ul>
                    <li>User Name    <input style="margin-left:50px"type="text" name="username"></li><br><br>
                    <li>Password     <input style="margin-left:61px"type="password" name="password"></li><br><br>
                    <li>First Name   <input style="margin-left:50px"type="text" name="first_name"></li><br><br>
                    <li>Surname      <input style="margin-left:65px"type="text" name="last_name"></li><br><br>
                    <li>Email        <input style="margin-left:82px"type="email" name="email"></li><br><br>
                                   
                </ul>
                <br/>
                <input type="submit" value="Register"> 
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/instamatt">Home</a></li>
            </ul>
        </footer>
    </body>
</html>

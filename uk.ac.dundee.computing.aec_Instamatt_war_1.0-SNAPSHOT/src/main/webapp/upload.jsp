<%-- 
    Document   : upload
    Created on : Sep 22, 2014, 6:31:50 PM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="height:100%">
    <head >
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--<title>instamatt</title> -->
        <link rel="stylesheet" type="text/css" href="Styles.css" />
    </head>
    <body id="page" style="color:white">
        <nav>
            <ul>        <li><a href="index.jsp">
                <img border="0" alt="LOGOTHINGS" src="stuff/logo2.png" width="45" height="45">
            </a></li> <br> 
                <li class="nav"><a href="upload.jsp">Upload</a></li>
                <li class="nav"><a href="/instamatt/Images/majed">Sample Images</a></li>
                <form id="search" method="GET" action="search" >
                <input type="text" name="target" />
                <input type='submit' />
            </form>

            </ul>
        </nav>
 
        <article>
            <h3>File Upload</h3>
            <form method="POST" enctype="multipart/form-data" action="Image">
                File to upload: <input type="file" name="upfile"><br/>
                <input type ="hidden" name="check" value="0">

                <br/>
                <input type="submit" value="Press"> to upload the file!
            </form>

        </article>
        <footer>
            <ul>
                <li class="footer"><a href="/instamatt">Home</a></li>
            </ul>
        </footer>
    </body>
</html>

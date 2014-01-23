<%-- 
    Document   : login
    Created on : Apr 18, 2013, 9:18:20 AM
    Author     : user
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="<c:url value="/resources/css/stylelogin.css"/>" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body style="background-color: #f5f5f5;">
        <br><br><br><br><br><br>

        <div id="main">
            <form action="donation.htm" method="POST" class="login">
                Username : <input id="login" name="username" maxlength="15" type="text" /><br><br>
                Password : <input id="password" name="password"  maxlength="15" type="password" /><br><br>
                <input name="submit" type="submit" value="Login!" />

                <a>${NOTLOGED}</a>
            </form>


        </div>
    </body>
</html>

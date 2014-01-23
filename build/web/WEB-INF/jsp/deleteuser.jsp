<%-- 
    Document   : delete_user
    Created on : Apr 18, 2013, 9:06:59 PM
    Author     : Tanevski
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete User Page</title>
        <style type="text/css">
            html{
                font-family: Segoe UI Light,sans-serif,serif;
                margin:0 auto;
                text-align:center;
            }
            header{
            }
            #submitButton{
                font-family: Segoe UI Light,sans-serif,serif;
            }
            #redLabel{
                color:red;
            }
            #greenLabel{
                color:green;
            }
        </style>
    </head>

    <body>
        <h1>Delete user:</h1>
        <form:form commandName="deleteUserForm">
            Username: <form:input path="username" name="deleteUsername" required="required" autocomplete="autocomplete" maxlength="15"/><br>
            <input type="submit" value="Delete!"/>
        </form:form>
        <p id="redLabel">${redLabel}</p>
        <p id="greenLabel">${greenLabel}</p>
    </body>

</html>

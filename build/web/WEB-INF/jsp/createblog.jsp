<%-- 
    Document   : createblog
    Created on : Apr 24, 2013, 4:17:58 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Blog Page</title>

    </head>
    <body>
        <%@include file="header_admin.jsp" %>
        <div id="main">


            <div id="content"> 
                <table> 
                    <form:form commandName="createBlog" >
                        <tr><td> Наслов:</td><td><form:input path="title" required="required" maxlength="20"/></td></tr>
                        <tr><td>Краток Опис:</td><td><form:textarea path="shortDescription" rows="5" cols="50" maxlength="500" required="required"/></td></tr>
                        <tr><td>Долг Опис</td><td><form:textarea path="longDescription" rows="10" cols="50" maxlength="1000" required="required"/></td></tr>
                        <form:hidden path="user.id" />
                        <tr><td></td><td><input type="submit" value="Креирај" /></td></tr>
                            </form:form>
                </table>
            </div>            
        </div>
    </body>
</html>

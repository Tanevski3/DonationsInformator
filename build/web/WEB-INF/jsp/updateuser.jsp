<%-- 
    Document   : update_user
    Created on : Apr 19, 2013, 9:16:24 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user Page</title>


    </head>
    <body>
        <%@include file="header_admin.jsp" %>
        <div id="main">


            <div id="content">
                <table>
                    <form:form commandName="updateUserForm">
                        <c:choose>
                            <c:when test="${empty param}">
                                ID: <form:input path="id" required="required" /><br>
                            </c:when>
                            <c:otherwise>
                                <form:hidden path="id" value="${idToUpdate}" /><br>
                            </c:otherwise>
                        </c:choose>
                       <tr> <td>Име: </td> <td> <form:input path="firstName" required="required" maxlength="20" value="${currentFirstName}" /></td></tr>
                        <tr> <td>Презиме: </td> <td> <form:input path="lastName" maxlength="20" value="${currentLastName}" /></td></tr>
                    <tr> <td>Корисничко име: </td> <td>  <form:input path="username" required="required" maxlength="20" autocomplete="autocomplete" value="${currentUsername}"/></td></tr>
                       <tr> <td>Шифра</td> <td>     <form:password path="password" required="required" maxlength="15" autocomplete="autocomplete" value="${currentPassword}"/></td></tr>
                           <tr> <td>Активен: </td> <td> <form:checkbox path="isActive" checked="${currentIsActive}" /></td></tr>
                        <tr> <td>Е-маил:</td> <td>     <form:input path="email" maxlength="45" autocomplete="autocomplete" value="${currentEmail}"/></td></tr>
                        <tr><td></td><td><input type="submit" value="Ажурирај" /></td></tr>
                            </form:form>
                </table>
                <p id="redLabel">${redLabel}</p>
                <p id="greenLabel">${greenLabel}</p>
            </div></div>
    </body>
</html>

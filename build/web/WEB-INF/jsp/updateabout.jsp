<%-- 
    Document   : updateabout
    Created on : May 7, 2013, 10:53:42 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header_admin.jsp" %>
        <div id="content">
            <table>
                <form:form commandName="UpdateAboutUs">
                    <form:hidden path="id" value="${idToUpdate}" />
                    <tr><td>Содржина</td><td>   <textarea path="content" name="content" rows="10" cols="50">${currentContent}</textarea></td></tr>
                    <tr><td>Држава</td><td>   <textarea path="country" name="country" rows="1" cols="10">${currentCountry}</textarea></td></tr>
                    <tr><td>Град</td><td>   <textarea path="city" name="city" rows="1" cols="10">${currentCity}</textarea></td></tr>
                    <tr><td>Телефон</td><td>   <textarea path="telephone" name="telephone" rows="1" cols="10">${currentTelephone}</textarea></td></tr>
                    <tr><td>Факс</td><td>   <textarea path="fax" name="fax" rows="1" cols="10">${currentFax}</textarea></td></tr>
                    <tr><td>Е-маил</td><td>   <textarea path="email" name="email" rows="1" cols="20">${currentEmail}</textarea></td></tr>
                    <tr><td></td><td>   <input type="submit" value="Ажурирај" />
                        </form:form>
            </table>
    </body>
</html>

<%-- 
    Document   : aboutusAdminPanel
    Created on : May 7, 2013, 10:48:04 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About Us</title>
    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <table style="width: auto" cellspacing="0">
            <th>Град</th>
            <th>Држава</th>
            <th>Телефон</th>
            <th>Факс</th>
            <th>Е-маил</th>
                <c:forEach var="abouts" items="${abouts}">

                <div id="content"> 
                    <tr>
                        <td>
                            <a href="updateabout.htm?aboutId=${abouts.id}">${abouts.city}</a>
                        </td>

                        <td>
                            ${abouts.country}
                        </td>
                        <td>
                            ${abouts.telephone}
                        </td>
                        <td>
                            ${abouts.fax}
                        </td>
                        <td>
                            ${abouts.email}
                        </td>
                    </tr>
                </c:forEach>
        </table>
    </body>
</html>

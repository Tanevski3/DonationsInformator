<%-- 
    Document   : login_success
    Created on : Apr 18, 2013, 11:14:51 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Type</title>

    </head>
    <body>

        <%@include file="header_admin.jsp" %>


        <a style="padding-left: 48%; font-size: 14pt;" href="createtype.htm">Нов Тип</a><br><br>
        <table style="width: auto;"cellspacing="0">
            <th>Име</th>

            <th>Избриши</th>
                <c:forEach var="type" items="${allTypes}">

                <div id="content"> 
                    <tr>
                        <td>
                            <a href="updatetype.htm?typeId=${type.id}">${type.name}</a>
                        </td>

                        <td>
                            <a class="removeBtn"href="type.htm?typeId=${type.id}" background-image="url(resources/images/admin_rmv_btn.png)"></a>
                        </td>
                    </tr>
                </c:forEach>


        </table><br><br>

    </body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User</title>

    </head>
    <body>
        <%@include file="header_admin.jsp" %>


        <a style="padding-left: 47%; font-size: 14pt;" href="createuser.htm">Нов Корисник</a><br><br>
        <table style="width: auto;" cellspacing="0">



            <th>Име</th>
            <th>Презиме</th>
            <th>Корисничко име</th>
            <th>Активен</th>
            <th>Е-маил</th>

            <c:set var="begin" value="${from}" scope="page" />

            <c:set var="count" value="0" scope="page" />

            <c:forEach var="user" items="${allUsers.subList(begin-10,begin>allUsers.size()?allUsers.size():begin)}" >

                <c:set var="count" value="${count + 1}" scope="page"/>

                <tr>

                    <td>
                        <a href="updateuser.htm?userId=${user.id}">${user.firstName}</a>

                    </td>
                    <td>
                        ${user.lastName}
                    </td>
                    <td>
                        ${user.username}
                    </td>
                    <td>
                        ${user.isActive}
                    </td>
                    <td>
                        ${user.email}
                    </td>


                </tr>


            </c:forEach>
        </table><br>

        <div style="text-align: center; font-size:1.2em;">
            <c:choose>
                <c:when test="${allUsers.size()%10==0}">
                    <c:forEach begin="0" step="1" end="${allUsers.size()/10}" varStatus="counter">
                        <a href="user.htm?from=${count+10*counter.index}">${counter.index}</a>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" step="1" end="${allUsers.size()/10}" varStatus="counter">
                        <a href="user.htm?from=${count+10*counter.index}">${counter.index+1}</a>
                    </c:forEach>
                    <a href="user.htm?from=${count}">${counter.index}</a>
                </c:otherwise>
            </c:choose>
        </div>
        <br>


    </body>
</html>



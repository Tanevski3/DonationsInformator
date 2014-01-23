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
        <title>Donations</title>
       
    </head>
    <body>

        <%@include file="header_admin.jsp" %>


        <a style="padding-left: 47%; font-size: 14pt;"href="createdonation.htm">Нова Донација</a><br><br>
        <table style="width: auto"cellspacing="0">
            <th style="text-align: center">Наслов
                <a style="font-size: 12pt;"href="donation.htm?order=title asc">˄</a>
                <a style="font-size: 12pt;"href="donation.htm?order=title desc">˅</a></th>
            <th style="text-align: center">Важи до
                <a style="font-size: 12pt;"href="donation.htm?order=endDate asc">˄</a>
                <a style="font-size: 12pt;"href="donation.htm?order=endDate desc">˅</a></th>
            <th style="text-align: center">Приоритет
                <a style="font-size: 12pt;"href="donation.htm?order=priority desc">˄</a>
                <a style="font-size: 12pt;"href="donation.htm?order=priority asc">˅</a></th>
            <th>Име</th>
            <th>Категорија</th>
            <th>Тип</th>
            <th>Избриши Донација</th>

            <c:set var="begin" value="${from}" scope="page" />

            <c:set var="count" value="0" scope="page" />

            <c:forEach var="donation" items="${allDonations.subList(begin-8,begin>allDonations.size()?allDonations.size():begin)}" >

                <c:set var="count" value="${count + 1}" scope="page"/>


                <tr>
                    <td>
                        <a href="updatedonation.htm?donationId=${donation.id}">${donation.title}</a>
                    </td>
                    <td>
                        ${donation.endDate}
                    </td>
                    <td>
                        ${donation.priority}
                    </td>
                    <td>
                        ${donation.user.firstName}
                    </td>
                    <td>
                        ${donation.category.name}
                    </td>
                    <td>
                        ${donation.type.name}
                    </td> 
                    <td>
                        <a class="removeBtn"href="donation.htm?donationId=${donation.id}" background-image="url(resources/images/admin_rmv_btn.png)"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>

        <div style="text-align: center; font-size:1.2em;">
            <c:choose>
                <c:when test="${allDonations.size()%8==0}">
                    <c:forEach begin="0" step="1" end="${allDonations.size()/8-1}" varStatus="counter">
                        <a href="donation.htm?from=${count+8*counter.index}">${counter.index+1}</a>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" step="1" end="${allDonations.size()/8}" varStatus="counter">
                        <a href="donation.htm?from=${count+8*counter.index}">${counter.index+1}</a>
                    </c:forEach>
                    <a href="donation.htm?from=${count}">${counter.index}</a>
                </c:otherwise>
            </c:choose>
        </div>




    </body>
</html>

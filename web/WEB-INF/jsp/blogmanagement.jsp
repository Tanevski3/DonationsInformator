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
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>

        <link rel="stylesheet" href="<c:url value="/resources/css/admin_style.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/css/style_horizontal_panel.css"/>" />
        <script type="text/javascript">
            $(function() {
                /* For zebra striping */
                $("table tr:nth-child(odd)").addClass("odd-row");
                /* For cell text alignment */
                $("table td:first-child, table th:first-child").addClass("first");
                /* For removing the last border */
                $("table td:last-child, table th:last-child").addClass("last");
            });
        </script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Blog</title>

    </head>
    <body>

        <%@include file="header_admin.jsp" %>

        <table style="width: auto" cellspacing="0">
            <a style="padding-left: 49%; font-size: 14pt;" href="createblog.htm">Нов Блог</a><br><br>
            <th>Наслов</th>
            <th>Постиран </th>
            <th>Избриши</th>

            <c:set var="begin" value="${from}" scope="page" />

            <c:set var="count" value="0" scope="page" />

            <c:forEach var="blog" items="${AllBlogs.subList(begin-5,begin>AllBlogs.size()?AllBlogs.size():begin)}" >

                <c:set var="count" value="${count + 1}" scope="page"/>


                <tr>
                    <td>
                        <a href="updateblog.htm?blogId=${blog.id}">${blog.title}</a>
                    </td>
                    <td>
                        ${blog.postDate}
                    </td>

                    <td>
                        <a class="removeBtn"href="blogmanagement.htm?blogId=${blog.id}" background-image="url(resources/images/admin_rmv_btn.png)"></a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>

        <div style="text-align: center; font-size:1.2em;">
            <c:choose>
                <c:when test="${AllBlogs.size()%5==0}">
                    <c:forEach begin="0" step="1" end="${AllBlogs.size()/5}" varStatus="counter">
                        <a href="blogmanagement.htm?from=${count+5*counter.index}">${counter.index+1}</a>
                    </c:forEach>

                </c:when>
                <c:otherwise>
                    <c:forEach begin="0" step="1" end="${AllBlogs.size()/5}" varStatus="counter">
                        <a href="blogmanagement.htm?from=${count+5*counter.index}">${counter.index+1}</a>
                    </c:forEach>
                    <a href="blogmanagement.htm?from=${count}">${counter.index}</a>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

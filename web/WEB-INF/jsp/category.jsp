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
        <title>Category</title>

    </head>
    <body>

        <%@include file="header_admin.jsp" %>
        <a style="padding-left: 47%; font-size: 14pt;" href="createcategory.htm">Нова Категорија</a><br><br>
        <table style="width: auto" cellspacing="0">
            <th>Име</th>

            <th>Избриши</th>
                <c:forEach var="category" items="${allCategories}">

                <div id="content"> 
                    <tr>
                        <td>
                            <a href="updatecategory.htm?categoryId=${category.id}">${category.name}</a>
                        </td>

                        <td>
                            <a class="removeBtn"href="category.htm?categoryId=${category.id}" background-image="url(resources/images/admin_rmv_btn.png)"></a>
                        </td>
                    </tr>
                </c:forEach>
        </table>
        <br><br>

    </body>
</html>

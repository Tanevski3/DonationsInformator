<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>

    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <div id="main">


            <div id="content">
                <form:form commandName="updateCategoryForm">
                    <c:choose>
                        <c:when test="${empty param}">
                            ID: <form:input path="id" required="required" /><br>
                        </c:when>
                        <c:otherwise>
                            <form:hidden path="id" value="${idToUpdate}" /><br>
                        </c:otherwise>
                    </c:choose>
                    <table>
                        <tr><td>Име</td><td> <form:input path="name"  required="required" value="${currentName}" maxlength="45" /></td>
                            <td> <input type="submit" value="Ажурирај" />
                            </td>          </form:form>
                    </tr>  </table>
            </div>
        </div>

    </body>
</html>


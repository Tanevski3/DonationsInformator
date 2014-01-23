<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Create Category</title>

    </head>

    <body>
        <%@include file="header_admin.jsp" %>



        <div id="content">
            <table>
                <form:form commandName="createCategory" >
                    <tr><td>Име</td><td> <form:input path="name" required="required" maxlength="45" /></td>
                        <td> <input type="submit" value="Креирај" /></td></tr>
                        </form:form>
            </table>
        </div>
</body>
</html>
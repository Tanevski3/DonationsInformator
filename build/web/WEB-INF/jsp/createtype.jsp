<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Type</title>



    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <div id="main">

            <div id="content">
                <table>
                    <form:form commandName="myCommand">
                        <tr><td> Name:</td><td> <form:input path="name" required="required" maxlength="45" /></td>
                            <td>  <input type="submit" value="Create" /></td></tr>
                    </form:form></table>

            </div></div>
    </body>
</html>

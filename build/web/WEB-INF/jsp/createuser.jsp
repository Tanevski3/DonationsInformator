<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>

        <script>
            function validateForm()
            {
                var x = document.forms["createUserForm"]["email"].value;
                var atpos = x.indexOf("@");
                var dotpos = x.lastIndexOf(".");
                if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= x.length)
                {
                    alert("Not a valid e-mail address");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <div id="main">
            <div id="content">
                <form:form commandName="createUserForm" onsubmit="return validateForm();">
                    <table>
                        <tr> <td>Име: </td> <td><form:input path="firstName" required="required"/></td></tr>
                        <tr> <td>Презиме: </td> <td>  <form:input path="lastName"/></td></tr>
                        <tr> <td>Корисничко име: </td> <td>    <form:input path="username" required="required"  autocomplete="autocomplete"/></td></tr>
                        <tr> <td>Шифра</td> <td>    <form:password path="password" required="required"  autocomplete="autocomplete" id="password" /></td></tr>
                        <tr> <td>Активен: </td> <td>      <form:checkbox path="isActive" autocomplete="autocomplete"/></td></tr>
                        <tr> <td>Е-маил:</td> <td>        <form:input path="email" name="email" required="required" maxlength="45"/></td></tr>
                        <tr><td></td><td><input type="submit" value="Креирај"/></td></tr>
                    </table>
                </form:form>
                <p id="redLabel">${redLabel}</p>
                <p id="greenLabel">${greenLabel}</p>

            </div> </div>
    </body>
</html>

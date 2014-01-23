<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Blog Page</title>

    </head>
    <body>
        <%@include file="header_admin.jsp" %>

        <div id="main">
            <div id="content">
                <table style="vertical-align: central">
                    <form:form commandName="updateBlogForm">
                        
                        <form:hidden path="id" value="${idToUpdate}" /><br>
                        <tr><td>Наслов</td><td> <form:input path="title" required="required" maxlength="20" value="${currentTitle}"/></td></tr>
                        <tr><td> Краток Опис:</td><td><textarea required="required" path="shortDescription" name="shortDescription" rows="5" cols="50" maxlength="500" >${currentShortDescription}</textarea></td></tr>
                        <tr><td>Долг Опис:</td><td><textarea required="required" path="longDescription" name="longDescription" rows="10" cols="50" maxlength="1000" >${currentLongDescription}</textarea></td></tr>
                        <tr><td>Постиран</td><td><label>${currentPostDate}</label></td></tr>

                        <tr><td></td><td> <input type="submit" value="Ажурирај" /></td></tr>
                    </table>
                </form:form>
            </div>
        </div>
    </body>
</html>
<%--
    Document   : helloView
    Created on : May 2, 2010, 2:06:25 PM
    Author     : nbuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="header.jsp" %>

<div class="homemain">

    <br>

    <h1>Results for ' ${searchValue} '</h1>
    <div id="dldiv" style="padding-right: 50px;padding-left:50px;">
        <dl >

            <c:forEach var="blog" items="${allBlogs}">


                <dt ><a href="blogdetail.htm?blogId=${blog.id}"><b>${blog.title}</b></a></dt>

                <dd> <small>Posted On: ${blog.postDate}<br></small>${blog.shortDescription}</dd>
                <br>
                <a style="float:right; margin-left:70%;" href="blogdetail.htm?blogId=${blog.id}">More...</a>Posted by: ${blog.user.username}
                <br>
                <br>
            </c:forEach>
        </dl>

    </div>

</div>

<%@include file="footer.jsp" %>
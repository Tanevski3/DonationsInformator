<%--
    Document   : helloView
    Created on : May 2, 2010, 2:06:25 PM
    Author     : nbuser
--%>

<%@page import="com.donations.model.Blog"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>

<form style="text-align: right; padding-right:170px;" action="searchresultsblog" method="POST">
    <input type="text"name="searchText" >
    <input type="submit" value="Search">
</form>
<div class="homemain" >

    <div style="padding-left: 100px;padding-right: 100px;">

        <c:set var="begin" value="${from}" scope="page" />
        <div id="dldiv">
            <dl>
                <c:set var="count" value="0" scope="page" />
                <c:forEach var="blog" items="${allBlogs.subList(begin-3,begin>allBlogs.size()?allBlogs.size():begin)}" >

                    <c:set var="count" value="${count + 1}" scope="page"/>

                    <dt><a href="blogdetail.htm?blogId=${blog.id}"><b>${blog.title}</b></a></dt>

                    <dd><small>Постиран на: ${blog.postDate}<br><br></small>${blog.shortDescription}</dd>
                    <br>
                    <a style="float:right; margin-left:70%;" href="blogdetail.htm?blogId=${blog.id}">повеќе...</a><strong>Постиран од:</strong> ${blog.user.username}
                    <hr style="color:blue"/>
                    <br>
                </c:forEach>
            </dl>
            <div style="text-align: center; font-size:1.2em;">
                <c:choose>
                    <c:when test="${allBlogs.size()%3==0}">
                        <c:forEach begin="0" step="1" end="${allBlogs.size()/3-1}" varStatus="counter">
                            <a href="blog.htm?from=${count+3*counter.index}" >${counter.index+1}</a>
                        </c:forEach>

                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="0" step="1" end="${allBlogs.size()/3}" varStatus="counter">
                            <a href="blog.htm?from=${count+3*counter.index}">${counter.index+1}</a>
                        </c:forEach>
                        <a href="blog.htm?from=${count}">${counter.index}</a>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>

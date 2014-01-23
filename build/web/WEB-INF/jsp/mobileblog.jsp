<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob" style="overflow-y: scroll; max-height: 300px;">
    <c:forEach var="blog" items="${allBlogs}">
   <a href="mobileblogdetail.htm?blogId=${blog.id}"><b>${blog.title}</b></a>

   <a style="font-size: 10pt"> &nbsp ${blog.postDate}</a><br>${blog.shortDescription} <br><br>
   <a style="font-size: 10pt">Постиран од:${blog.user.username}</a><br><br><hr>
</c:forEach>
  

</div>
<%@include file="mobilefooter.jsp" %>
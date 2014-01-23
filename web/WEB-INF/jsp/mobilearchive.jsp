<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob" style="overflow-y: scroll; max-height: 300px;">
    <c:forEach var="donation" items="${allDonationsMobOld}">
        <a href="mobiledetail.htm?donationId=${donation.id}">${donation.title}</a><br>
        <a style="font-size: 10pt">Важи до:${donation.endDate}</a><br><br><hr>
    </c:forEach>
</div>
<%@include file="mobilefooter.jsp" %>
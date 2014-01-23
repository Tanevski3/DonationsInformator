<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob" style="overflow-y:scroll; max-height:410px;">
    <c:forEach var="donation" items="${allDonationsMob}">
        <a href="mobiledetail.htm?donationId=${donation.id}">${donation.title}</a><br>
        Важи до: ${donation.endDate}<br><br><hr>
    </c:forEach>
</div>
<%@include file="mobilefooter.jsp" %>
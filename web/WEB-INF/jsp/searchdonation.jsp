<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>
<div class="homemain">

    <table style="padding-left:8%;padding-right:8%;">

        <c:if test="${not empty allDonations}">
            <c:forEach var="donation" items="${allDonations}">
                <tr>
                    <td style="width: 150px;height: 150px;">
                        <a href="donationdetail.htm?donationId=${donation.id}"><img width="150px" height="150px"src="/data/images/${donation.imageSource}"></a>
                    </td>
                    <td style="padding-left:5%">
                        <table>
                            <tr>
                                <td style="width: 350px;height: 50px;padding-left: 5px;font-size: 16px;"><strong> <a href="donationdetail.htm?donationId=${donation.id}">${donation.title}</a></strong></td>
                            </tr>
                            <tr>
                                <td style="height: 100px;padding-left: 5px;">${donation.description}</td>
                            </tr>
                            <tr>
                                <td>${donation.endDate}</td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
    </table>
    <c:if test="${empty allDonations}"><div style="height:100px; font-size: 32px;font-family: verdana; text-align: center; ">Nema Rezultati</div>  </c:if>

    </div>
<%@include file="footer.jsp" %>
<%-- 
    Document   : archive
    Created on : May 7, 2013, 9:17:43 AM
    Author     : user
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="header.jsp" %>
<!DOCTYPE html>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Archive</title>

<div class="homemain">


    <div style="padding-left: 100px;padding-right: 100px;">

        <c:set var="begin" value="${from}" scope="page" />
        <div id="dldiv">

            <c:set var="count" value="0" scope="page" />
            <c:forEach var="donation" items="${allDonationOld.subList(begin-4,begin>allDonationOld.size()?allDonationOld.size():begin)}" varStatus="myCounter">

                <c:set var="count" value="${count + 1}" scope="page"/>

                <c:choose>
                    <c:when test="${myCounter.index%2==0}">

                        <dl style="width:200px; height: 200px; float:left; clear:both;">
                            <a href="donationdetail.htm?donationId=${donation.id}"><img width="50px" height="50px"src="/data/images/${donation.imageSource}"></a>
                            <dt><a href="donationdetail.htm?donationId=${donation.id}"><b>${donation.title}</b></a></dt>
                                    <c:if test="${donation.description.length()>80}">
                                <dd><small>Краен рок: ${donation.endDate}<br><br></small>${donation.description.substring(0,60)}</dd>
                                    </c:if>
                                    <c:if test="${donation.description.length()<=80}">
                                <dd><small>End date: ${donation.endDate}<br><br></small>${donation.description}</dd>
                                    </c:if>
                            <a style="float:right; margin-left:70%;" href="donationdetail.htm?donationId=${donation.id}">повеќе...</a><strong>Постиран Од:</strong> ${donation.user.username}

                        </dl>
                    </c:when>
                    <c:otherwise>
                        <dl style="width:200px; height: 200px; float:right;">

                            <a href="donationdetail.htm?donationId=${donation.id}"><img width="50px" height="50px"src="/data/images/${donation.imageSource}"></a>
                            <dt><a href="donationdetail.htm?donationId=${donation.id}"><b>${donation.title}</b></a></dt>
                                    <c:if test="${donation.description.length()>80}">
                                <dd><small>End date: ${donation.endDate}<br><br></small>${donation.description.substring(0,60)}</dd>
                                    </c:if>
                                    <c:if test="${donation.description.length()<=80}">
                                <dd><small>End date: ${donation.endDate}<br><br></small>${donation.description}</dd>
                                    </c:if>
                            <a style="float:right; margin-left:70%;" href="donationdetail.htm?donationId=${donation.id}">повеќе...</a><strong>Постиран Од:</strong> ${donation.user.username}

                        </dl>
                    </c:otherwise>
                </c:choose>



            </c:forEach>

            <div style="text-align: center; font-size:1.2em; clear:both;">
                <c:choose>
                    <c:when test="${allDonationOld.size()%4==0}">
                        <c:forEach begin="0" step="1" end="${(allDonationOld.size()/4-1)}" varStatus="counter">
                            <a href="archive.htm?from=${count+4*counter.index}">${counter.index+1}</a>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach begin="0" step="1" end="${allDonationOld.size()/4}" varStatus="counter">
                            <a href="archive.htm?from=${count+4*counter.index}">${counter.index+1}</a>
                        </c:forEach>
                        <a href="archive.htm?from=${count}">${counter.index}</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>


</div>
<%@include file="footer.jsp" %>
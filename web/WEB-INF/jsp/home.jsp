<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.donations.model.Donation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@include file="header.jsp" %>


<form style="text-align: right; padding-right:170px;"action="searchdonation" method="POST">

    <input id="searchText" name="searchText" maxlength="15" type="text" value="" />

    <button type="submit">Search</button>

</form>
<div class="homemain">

    <div style="padding-left: 100px;padding-right: 100px;">


        <c:set var="begin" value="${from}" scope="page" />

        <c:if test="${from==4}">
            <table style="margin:0 auto;width: 600px;">
                <c:forEach var="donation" begin="0"  end="2" items="${allDonation}">

                    <tr>
                        
                        <td style="width: 150px;height: 150px;"><a href="donationdetail.htm?donationId=${donation.id}"><img width="150px" height="150px"src="/data/images/${donation.imageSource}"></a></td>
                        <td>
                            <table style="border-bottom: 1px solid gray;">
                                <tr>

                                    <td style="width: 350px;height: 50px;padding-left: 5px;font-size: 16px;"><strong><a href="donationdetail.htm?donationId=${donation.id}">${donation.title}</a></strong></td>

                                </tr>
                                <tr>
                                    <c:if test="${donation.description.length()>80}">
                                        <td style="height: 100px;padding-left: 5px;">${donation.description.substring(0,80)}<a href="donationdetail.htm?donationId=${donation.id}">... повеќе</a></td>
                                    </c:if> 
                                    <c:if test="${donation.description.length()<=80}">
                                        <td style="height: 100px;padding-left: 5px;">${donation.description}<a href="donationdetail.htm?donationId=${donation.id}">... повеќе</a></td>
                                    </c:if> 
                                </tr>
                                <tr>
                                    <td>${donation.endDate}</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>

        <br> <br> 
        <hr style="color:blue;"/>
        <br>
        <!------------------------------------------------------------------------------------>

        <div style="padding-left: 100px;padding-right: 100px;">

            <c:set var="begin" value="${from}" scope="page" />
            <c:set var="mainDonations" value="${allDonation.subList(3,allDonation.size())}" scope="page" />
            <div id="dldiv">

                <c:set var="count" value="0" scope="page" />
                <c:forEach var="donation" items="${mainDonations.subList(begin-4,begin>mainDonations.size()?mainDonations.size():begin)}" varStatus="myCounter">

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
                                    <dd><small>Краен рок: ${donation.endDate}<br><br></small>${donation.description}</dd>
                                        </c:if>
                                <a style="float:right; margin-left:70%;" href="donationdetail.htm?donationId=${donation.id}">повеќе...</a><strong>Постиран од:</strong> ${donation.user.username}

                            </dl>
                        </c:when>
                        <c:otherwise>
                            <dl style="width:200px; height: 200px; float:right;">
                                <a href="donationdetail.htm?donationId=${donation.id}"><img width="50px" height="50px"src="/data/images/${donation.imageSource}"></a>
                                <dt><a href="donationdetail.htm?donationId=${donation.id}"><b>${donation.title}</b></a></dt>
                                        <c:if test="${donation.description.length()>80}">
                                    <dd><small>Краен рок: ${donation.endDate}<br><br></small>${donation.description.substring(0,60)}</dd>
                                        </c:if>
                                        <c:if test="${donation.description.length()<=80}">
                                    <dd><small>Краен рок: ${donation.endDate}<br><br></small>${donation.description}</dd>
                                        </c:if>
                                <a style="float:right; margin-left:70%;" href="donationdetail.htm?donationId=${donation.id}">повеќе...</a><strong>Постиран од:</strong> ${donation.user.username}

                            </dl>
                        </c:otherwise>
                    </c:choose>



                </c:forEach>

                <div style="text-align: center; font-size:1.2em; clear:both;">
                    <c:choose>
                        <c:when test="${allDonation.size()%4==0}">
                            <c:forEach begin="0" step="1" end="${(allDonation.size()/4)-1}" varStatus="counter">
                                <a href="home.htm?from=${count+4*counter.index}">${counter.index+1}</a>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach begin="0" step="1" end="${allDonation.size()/4-1}" varStatus="counter">
                                <a href="home.htm?from=${count+4*counter.index}">${counter.index+1}</a>
                            </c:forEach>
                            <a href="home.htm?from=${count}">${counter.index}</a>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>

    </div>
  <hr>
    <c:forEach var="result" items="${results}">
        ${result}<br><hr>
    </c:forEach>

    <%@include file="footer.jsp" %>
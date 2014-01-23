<%-- 
    Document   : donationdetail
    Created on : Apr 25, 2013, 9:10:00 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>

<div class="homemain" >
    <table style="padding-left: 150px;">
        <tr>
            <td style="font-size: 24pt; padding-left: 35px;">
                <strong>${currentTitle}</strong> 
            </td>
        </tr>
        <tr>
            <td>
                <img width="150px" height="150px"src="/data/images/${currentImageSource}"></a>
            </td>
            <td style="font-size: 16pt; padding-left: 35px;">
                <b> Извор:</b> ${currentDonationUrl}
                <br><br><br>
               <b> Постиран: </b>${currentStartDate}
                <br><br><br>

                <b>Краен рок:</b> ${currentEndDate}
                <br><br><br>
            </td>
            <td style="font-size: 16pt; padding-left: 35px;">
                <br><br><br>
                <b>Телефон:</b> ${currentDonationPhone}
                <br><br><br>
               <b>Сметка:</b>${currentDonationAccount}
                <br><br><br>
            </td>
        </tr>
    </table>

    <table style="padding-left: 200px;">
        <tr>
            <td style="text-align: justify;font-size: 14pt; padding-right:17%;">
                <b><font style="font-size: 16pt;"> Опис:</font><br><br></b>
                ${currentDescription}<br>
            </td>
        </tr>
    </table>

</div>        
<%@include file="footer.jsp" %>
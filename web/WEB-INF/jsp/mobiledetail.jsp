<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob">



    <img width="150px" height="150px"src="/data/images/${currentImageSource}"><br>
   <b> Наслов:</b>  ${currentTitle} <br><br>

   <b> Извор:</b>  ${currentDonationUrl} <br><br>

   <b> Постирана:</b>  ${currentStartDate} <br><br>

  <b>  Важи до:</b>  ${currentEndDate}<br><br>

   <b> Телефон:</b>  ${currentDonationPhone} <br><br>

   <b> Сметка:</b>  ${currentDonationAccount} <br><br>

    ${currentDescription}<br>

</div>
<%@include file="mobilefooter.jsp" %>
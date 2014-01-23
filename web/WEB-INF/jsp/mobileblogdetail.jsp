<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob">
    
   
    
    <b> Наслов:</b> ${blogTitle} <br><br>
    
    ${blogShortDescription} <br><br>
    
     ${blogLongDescription} <br><br>
    
    <b>Постирана:</b>  ${blogPostDate} <br><br>
    
     <b>Постиран од:</b>  ${blogUserUsername} <br><br>


    
</div>
<%@include file="mobilefooter.jsp" %>

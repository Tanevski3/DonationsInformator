<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="mobileheader.jsp" %>
<div id="contentmob">

    <c:forEach var="abouts" items="${abouts}">
        <table>

            <tr>
                <td>
                    <b>Држава:</b>
                </td>

                <td>
                    ${abouts.country}
                </td>
            </tr>
            <tr>
                <td>
                    <b>Град: </b>
                </td>
                <td>
                    ${abouts.city} 
                </td>
            </tr>
            <tr>
                <td>
                    <b> Телефон:</b>
                </td>
                <td>
                    ${abouts.telephone} 
                </td>
            </tr>
            <tr>
                <td>
                    <b> Факс:</b>
                </td>
                <td>
                    ${abouts.fax} 
                </td>
            </tr>
            <tr>
                <td>
                    <b> Е-Маил:</b>
                </td>
                <td>
                    ${abouts.email} 
                </td>
            </tr>
            <tr>
                <td>
                    
                </td>    
                <td style="text-align: justify; width:300px">
                    ${abouts.content} 
                </td>
            </tr>
        </table>
    </c:forEach>
</div>




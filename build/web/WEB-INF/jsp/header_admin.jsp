<!DOCTYPE html>

    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Light Horizontal Navigation</title>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <link rel="stylesheet" href="<c:url value="/resources/css/style_horizontal_panel.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/css/admin_style.css"/>" />
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(function() {
                /* For zebra striping */
                $("table tr:nth-child(odd)").addClass("odd-row");
                /* For cell text alignment */
                $("table td:first-child, table th:first-child").addClass("first");
                /* For removing the last border */
                $("table td:last-child, table th:last-child").addClass("last");
            });
        </script>

    </head>
    <body>
        <br>
        <p class="logout">Welcome &nbsp;&nbsp;<strong>${loggeduserName}</strong> &nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <a class="logoutBtn" href="logout">Logout</a>
        <div class="container">
            <nav>
                <ul class="nav">

                    <li><a href="donation.htm" title="Donation">Донации</a></li>
                    <li><a href="user.htm" title="User">Корисници</a></li>
                    <li><a href="type.htm" title="Type">Тип</a></li>
                    <li><a href="category.htm" title="Category">Категорија</a></li>
                    <li><a href="blogmanagement.htm" title="Blog">Блог</a></li>
                    <li><a href="aboutusadminpanel.htm" title="AboutUs">За нас</a></li>

                </ul>
            </nav>
        </div>
        <b><a style="color:green;padding-left: 47%">${Action}</a></b><br><br>


    </body>
</html>
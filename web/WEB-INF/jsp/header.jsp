<%-- 
    Document   : header
    Created on : Apr 24, 2013, 3:50:34 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            .homemain{
                position: relative;
                width:1000px;
                margin:0 auto;
                background-color: #FFFFFF;
            }
            body{
                background-color: #CED6DB; 
                font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            }
            #header {
                text-align: center;
                margin:0 auto;
                width:1000px;
                height:100px;
                background-color:#3E454C;
                margin-bottom: 5px;
            }
            #footer {
                margin:0 auto;
                width:1000px;
                height:100px;
                background-color:#3E454C;
                margin-top: 5px;
            }

            #topmenu ul {

                list-style-type:none;
                list-style-image:none;
                width:960px;
                margin:0 auto;
                text-align:center;
                background-color: #3E454C;
                margin-bottom: 5px;


            }

            #topmenu ul li {

                color:black;
                display:inline-block;
                width:5em;
                padding:1.5em;
                background-color: #3E454C;
            }

            #topmenu ul li a {

                font-size:1.2em;
                font-weight:normal;
                text-decoration:none;
                color:#FFF6E5;
            }

            #topmenu ul li:hover
            {
                background-color:#656A70;
            }

            #topmenu ul a:active
            {
                color:black;
                background-color:#656A70;
            }

        </style>

    </head>
    <body>
        <header>
            <div id="header">
                <img src="<c:url value="/resources/images/donationlogo.jpg"/>" alt="No logo available" />
            </div>



            <div id="topmenu">
                <ul>
                    <li>
                        <a href="home.html">Дома</a>
                    </li>
                    <li>
                        <a href="blog.html">Вести</a>
                    </li>
                    <li>
                        <a href="aboutus.html">За Нас</a>
                    </li>
                    <li>
                        <a href="archive.html">Архива</a>
                    </li>

                </ul>



            </div>
        </header>
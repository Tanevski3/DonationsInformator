<%-- 
    Document   : mobileheader
    Created on : May 9, 2013, 1:22:12 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <style type="text/css">
            body{
             background-color: #CED6DB; 
            }
            #contentmob{
                position: absolute;
                top:120px;
                width:300px;
            }
            li{
               display:inline;
               padding: 10px;
            }
            header{
                position: fixed;
                }
            
        </style>
    </head>
    <body>
        <header>
            <a style="border-width:0px" href="mobilehome.htm"><img src="<c:url value="/resources/images/donationlogomini.png"/>" alt="No logo available" /></a>
            <div id="topmenu" style="margin:0">
           <ul>
               <li>
                   <a href="mobilehome.html">Дома</a>
               </li>
               <li>
                  <a href="mobileblog.html">Вести</a>
               </li>
               <li>
                   <a href="mobileaboutus.html">За нас</a>
               </li>
               <li>
                   <a href="mobilearchive.html">Архива</a>
               </li>
               
           </ul>
        </div>
        </header>
        
       
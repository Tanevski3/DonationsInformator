

<%--
    Document   : helloView
    Created on : May 2, 2010, 2:06:25 PM
    Author     : nbuser
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="header.jsp" %>

<div class="homemain" >
    <div style="padding-left: 100px;padding-right: 100px;">
        <div id="main">
            <link rel="canonical" href="http://www.donacii.mk" />
            <script type="text/javascript" src="https://apis.google.com/js/plusone.js">
            </script>

            <div id="fb-root"></div>
            <script>(function(d, s, id) {
                    var js, fjs = d.getElementsByTagName(s)[0];
                    if (d.getElementById(id))
                        return;
                    js = d.createElement(s);
                    js.id = id;
                    js.src = "//connect.facebook.net/en_US/all.js#xfbml=1";
                    fjs.parentNode.insertBefore(js, fjs);
                }(document, 'script', 'facebook-jssdk'));
                !function(d, s, id) {
                    var js, fjs = d.getElementsByTagName(s)[0];
                    if (!d.getElementById(id)) {
                        js = d.createElement(s);
                        js.id = id;
                        js.src = "https://platform.twitter.com/widgets.js";
                        fjs.parentNode.insertBefore(js, fjs);
                    }
                }(document, "script", "twitter-wjs");
            </script>

            <h1>${blogTitle}</h1>
            <strong>Posted On :</strong> ${blogPostDate}
            <p style="font-size: 12pt">${blogShortDescription}</p>
            <p style="font-size: 14pt">${blogLongDescription}</p>
            <strong>Posted By:</strong> ${blogUserUsername}
        </div>
        <br><br>
        <div style="text-align: center">

            <div class="centerShare">
                <g:plusone></g:plusone><br>
                <div style="text-align:center;"class="fb-like" data-href="http://www.donacii.mk" data-send="false" data-width="450" data-show-faces="true"></div><br>

                <a href="https://twitter.com/share" class="twitter-share-button" data-url="http://www.donacii.mk" data-text="Check out this site">Tweet</a>
                <script>!function(d, s, id) {
        var js, fjs = d.getElementsByTagName(s)[0], p = /^http:/.test(d.location) ? 'http' : 'https';
        if (!d.getElementById(id)) {
            js = d.createElement(s);
            js.id = id;
            js.src = p + '://platform.twitter.com/widgets.js';
            fjs.parentNode.insertBefore(js, fjs);
        }
    }(document, 'script', 'twitter-wjs');</script>
            </div>
        </div></div>
</div>
<%@include file="footer.jsp" %>

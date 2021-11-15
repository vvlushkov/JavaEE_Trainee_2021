<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 05.10.2021
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <title>Page not found</title>
        <style>
            .classParag {
                position: relative;
                margin-top: 30px;
                margin-bottom: 30px;
                text-align: center;
                font-size: 20pt;
                font-weight: 600;
            }
        </style>
    </head>
    <body>
        <p class="classParag">
            Sorry, but page not found :(
        </p>
        <p align="center">
            <img src="${pageContext.request.contextPath}/images/404.png" width="600px" height="600px">
        </p>
    </body>
</html>

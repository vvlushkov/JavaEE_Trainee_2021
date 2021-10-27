<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 27.10.2021
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
    <head>
        <title>Error</title>
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
            User with this login already exists.
            Try again with another login.
        </p>
        <p align="center">
            <img src="${pageContext.request.contextPath}/images/sad-kitty.png" width="400px" height="250px">
        </p>
    </body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 04.10.2021
  Time: 20:10
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
            Sorry, but something went wrong :(
            Error: <%= exception.getClass() %>
        </p>
        <p align="center">
            <img src="${pageContext.request.contextPath}/images/sad-kitty.png" width="800px" height="500px">
        </p>
    </body>
</html>

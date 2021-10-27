<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exceptionPage.jsp" %>
<html>
    <head>
        <title>User Page</title>
        <style>
            .imgClass {
                margin-left: 120px;
            }
        </style>
    </head>
    <body>
        <h2 align="center">Hello, ${sessionScope.userFirstName}! What are you doing here?</h2>
        <h1 align="center">GO AWAY!</h1>
        <p align="center">
            <img src="${pageContext.request.contextPath} /images/sticker.png" class="imgClass">
        </p>
        <p align="center">
            <h3 align="center">Click <a href="/logout">here</a> to log out.</h3>
        </p>
    </body>
</html>
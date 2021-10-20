<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 02.10.2021
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exceptionPage.jsp" %>
<!DOCTYPE html>
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
        .classA {
            position: relative;
            margin-top: 30px;
            margin-bottom: 30px;
            text-align: center;
            font-size: 15pt;
            font-weight: 300;
        }
    </style>
</head>
<body>
<p class="classParag">
    You can't delete yourself!
</p>
<p align="center">
    <img src="${pageContext.request.contextPath}/images/stop.jpg" class="imgClass" width="500" height="500">
</p>
<p align="center">
    <a href="/adminPage" class="classA">Return</a>
</p>
</body>
</html>

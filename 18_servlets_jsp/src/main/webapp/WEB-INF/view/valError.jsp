<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="exceptionPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        </style>
    </head>
    <body>
        <p class="classParag">
            <c:out value="${requestScope.errMsg}"/>
        </p>
        <p align="center">
            <img src="${pageContext.request.contextPath} /images/not-valid.png">
        </p>
    </body>
</html>
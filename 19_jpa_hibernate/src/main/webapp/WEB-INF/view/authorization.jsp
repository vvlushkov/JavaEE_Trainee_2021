<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exceptionPage.jsp"%>

<html>
    <head>
        <title>Authorization</title>
        <style>
            .classDiv {
                width: 100%;
                border: 1px solid gray;
                border-radius: 3px;
                padding-bottom: 20px;
                position: relative;
                font-weight: 500;
                margin-bottom: 7px;
            }
            .classForm {
                width: 20%;
                margin: 100px auto;
                font: 700 .9em Arial, serif;
                background-color: #d3d3d3;
            }
            .classParag {
                position: relative;
                margin-top: 30px;
                margin-bottom: 30px;
                text-align: center;
            }
            .simpleClassDiv {
                margin-top: 30px;
            }
        </style>
    </head>
    <body>
        <div class="simpleClassDiv">
            <h1 align="center">Sign in to your account</h1><br/>
        </div>
        <c:if test="${isValid == false}">
            <div class="simpleClassDiv">
                <h2 align="center">Wrong login or password. Try again, please.</h2><br/>
            </div>
        </c:if>
        <form class="classForm"  method="post" action="/authorization">
            <div class="classDiv">
                <p class="classParag">
                    <label for="login">Login</label><br>
                    <input type="text" id="login" name="login" required placeholder="Your login">
                </p>
                <p class="classParag">
                    <label for="password">Password</label><br>
                    <input type="password" id="password" name="password" required placeholder="Your password">
                </p>
                <p align="center">
                    <input type="submit" value="Sign in">
                </p>
            </div>
        </form>
    </body>
</html>
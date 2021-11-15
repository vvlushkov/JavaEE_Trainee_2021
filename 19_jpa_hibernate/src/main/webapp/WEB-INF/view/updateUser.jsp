<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 01.10.2021
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exceptionPage.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="lib" uri="/WEB-INF/tags-library/dbTags.tld" %>

<html>
    <head>
        <title>Updating User</title>
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
                margin: 30px auto 100px;
                font: 700 .9em Arial, serif;
                background-color: #d3d3d3;
            }
            .classParag {
                position: relative;
                margin-top: 15px;
                margin-bottom: 15px;
                text-align: center;
            }
            .simpleClassParag {
                margin-right: 25px;
                margin-bottom: 0px;
                font-size: 15pt;
                font-weight: 300;
            }
        </style>
        <script type="text/javascript">
            function onChange() {
                const password = document.querySelector('input[id=password]');
                const confirm = document.querySelector('input[id=confirm_password]');
                if (confirm.value === password.value) {
                    confirm.setCustomValidity('');
                } else {
                    confirm.setCustomValidity('Passwords do not match');
                }
            }
        </script>
    </head>
    <body>
        <p class="classParag">
            <h2 align="center">Edit user</h2>
        </p>
        <p align="right" class="simpleClassParag">
            ${sessionScope.userFirstName} ${sessionScope.userLastName}
            (<a href="/logout">Logout</a>)
        </p>
        <form class="classForm"  method="post" action="/updateUser">
            <div class="classDiv">
                <p class="classParag">
                    <label for="newLogin">Login</label><br>
                    <input type="text" id="newLogin" name="login" required
                           placeholder="Your login" value="${user.login}" readonly>
                </p>
                <p class="classParag">
                    <label for="password">Enter new password</label><br>
                    <input type="password" id="password" name="password"
                           onChange="onChange()" required value="${user.password}"
                           placeholder="Enter password" >
                </p>
                <p class="classParag">
                    <label for="confirm_password">Enter password again</label><br>
                    <input type="password" id="confirm_password" name="password"
                           onChange="onChange()" required
                           placeholder="ReEnter password" >
                </p>
                <p class="classParag">
                    <label for="email">Email</label><br>
                    <input type="email" id="email" name="email"
                           title="Contact's email (format: xxx@xxx.xxx)"
                           pattern=".+@.+(\.[a-zA-Z0-9-]+)+" value="${user.email}"
                           required placeholder="Enter E-mail" >
                </p>
                <p class="classParag">
                    <label for="firstName">First name</label><br>
                    <input type="text" id="firstName" name="firstName"
                           required placeholder="Enter first name" value="${user.firstName}">
                </p>
                <p class="classParag">
                    <label for="lastName">Last name</label><br>
                    <input type="text" id="lastName" name="lastName"
                           required placeholder="Enter last name" value="${user.lastName}">
                </p>
                <p class="classParag">
                    <label for="birthday">Birthday (old: ${user.birthday})</label><br>
                    <input type="date" id="birthday" name="birthday" value="${user.birthday}"
                           required placeholder="Your birthday" size="auto">
                </p>
                <p class="classParag">
                    <label for="chosenRole">Role</label><br>
                    <select name="chosenRole" id="chosenRole" required size="auto">
                        <c:forEach var="role" items="${roleList}">
                            <option value="${role.role_name}" <lib:chosenRoleTag userRoleId="${user.role.role_id}" roleName="${role.role_name}"/> >${role.role_name}</option>
                        </c:forEach>
                    </select>
                </p>
                <p align="center">
                    <input type="submit" value="Submit">
                </p>
            </div>
        </form>
    </body>
</html>

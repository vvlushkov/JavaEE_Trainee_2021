<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="exceptionPage.jsp"%>
<%@ taglib uri="/WEB-INF/tags-library/dbTags.tld" prefix="dbutils" %>
<html>
    <head>
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            table {
                width: 60%;
                border-spacing: 1px;
            }
            td, th {
                text-align: center;
                padding: 5px;
            }
            .formClass {
                margin-bottom: 0px;
            }
            .displayBlock {
                padding-top: 5px; /* Поля вокруг текста */
                display: inline-block;
            }
            .simpleClassParag {
                margin-right: 25px;
            }
        </style>
    </head>
    <body>
        <h1 align="center">Hello, ${sessionScope.userLastName}! What are you want to do?</h1><br>
        <p align="right" class="simpleClassParag">
            ${sessionScope.userFirstName} ${sessionScope.userLastName}
                (<a href="/logout">Logout</a>)
        </p>
        <table align="center" border="3px">
            <tr>
                <th class="mr-auto">
                    <a href="/createUser">Add user</a>
                </th>
            </tr>
            <tr>
                <th>Login</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Age</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="user" items="${usersList}">
            <tr>
                <td>${user.login}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td><dbutils:ageTag age="${user.birthday}"/></td>
                <td><dbutils:getRoleName roleId="${user.role.role_id}"/></td>
                <td>
                    <div class="displayBlock">
                        <form class="formClass" method="get" action="/updateUser">
                            <input type="number" hidden name="userId" value="${user.userId}">
                            <input type="submit" value="Edit" class="btn btn-secondary">
                        </form>
                    </div>
                    <div class="displayBlock">
                        <!-- Button trigger modal -->
                        <button type="button" class="btn btn-secondary" data-toggle="modal" data-target="#deleteModal${user.userId}">
                            Delete
                        </button>
                    </div>
                    <!-- Modal -->
                    <div class="modal fade" id="deleteModal${user.userId}" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="deleteModalLabel">Modal title</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    Do you want to delete this user?
                                </div>
                                <div class="modal-footer">
                                    <form action="/delete" method="post" class="formClass">
                                        <input type="number" hidden name="userId" value="${user.userId}">
                                        <input type="submit" name="Delete" class="btn btn-default" role="button"
                                               aria-pressed="true" value="Delete">
                                    </form>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            </c:forEach>
        </table>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 27.11.2022 г.
  Time: 0:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lecturer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }

        a {
            text-decoration: none;
            color: white;
            margin-top: 10px;
            margin-bottom: 10px;
        }

        #login, #logout, #register {
            margin: 10px;
        }

        #loginText, #logoutText, #registerText {
            color: black;
        }

        #userEmail {
            font-weight: bold;
            float: right;
            color: black;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <div class="container-fluid">
        <a class="navbar-brand">Programming Course CRUD</a>
        <div class="collapse navbar-collapse" id="navbarColor03">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item active">
                    <a class="nav-link" href="../course/getAll">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="getAll">Lecturers</a>
                </li>
                <li class="d-flex me-2">
                </li>
            </ul>
            <p id="userEmail" style="margin-top: 15px">${userEmail != null ? 'Logged in as: '.concat(userEmail) : ''}</p>
            ${userEmail == null ? '<button type="button" id="register" class="btn btn-info"><a id="registerText" href="../authentication/loadRegisterForm">Register</a></button>' : '' }
            <button type="button" id="login" class="btn btn-success">
                <a id="loginText" href="../authentication/loadLoginForm">
                    Login
                </a>
            </button>
            ${userEmail != null ? '<button type="button" id="logout" class="btn btn-danger"><a id="logoutText" href="../authentication/logout">Logout</a></button>' : '' }
        </div>
    </div>
</nav>
<h3>Update Lecturer</h3>
<a href="getAll" class="btn btn-primary">Back to Lecturers</a>
<form:form method="POST"
           action="update" modelAttribute="lecturer">
    <table>
        <tr>
            <td><form:hidden path="id" value="${lecturerToUpdate.id}"/></td>
        </tr>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" value="${lecturerToUpdate.name}" cssClass="form-control"/></td>
            <td>
                <form:errors path="name" cssClass="error" element="div" />
            </td>
        </tr>
        <tr>
            <td><form:label path="age">Age</form:label></td>
            <td><form:input path="age" value="${lecturerToUpdate.age}" cssClass="form-control"/></td>
            <td>
                <form:errors path="age" cssClass="error" element="div" />
            </td>
        </tr>
        <tr>
            <td><form:label path="description">Description</form:label></td>
            <td><form:input path="description" value="${lecturerToUpdate.description}" cssClass="form-control"/></td>
            <td>
                <form:errors path="description" cssClass="error" element="div" />
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Update" class="btn btn-primary"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

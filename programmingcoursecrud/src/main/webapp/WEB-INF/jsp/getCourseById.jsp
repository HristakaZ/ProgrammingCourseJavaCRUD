<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 27.11.2022 г.
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Course By Id</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <style>
      a {
        text-decoration: none;
        color: white;
        margin-top: 10px;
        margin-bottom: 10px;
      }

      #login, #logout {
          margin: 10px;
      }

      #loginText, #logoutText {
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
                    <a class="nav-link" href="getAll">Courses</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="../lecturer/getAll">Lecturers</a>
                </li>
                <li class="d-flex me-2">
                </li>
            </ul>
            <p id="userEmail" style="margin-top: 15px">${userEmail != null ? 'Logged in as: '.concat(userEmail) : ''}</p>
            <button type="button" id="login" class="btn btn-success">
                <a id="loginText" href="../authentication/loadLoginForm">
                    Login
                </a>
            </button>
            ${userEmail != null ? '<button type="button" id="logout" class="btn btn-danger"><a id="logoutText" href="../authentication/logout">Logout</a></button>' : '' }
        </div>
    </div>
</nav>
    <h3>Course Details</h3>
    <h4>${userEmail != null ? 'Logged in as: '.concat(userEmail) : ''}</h4>
    <a href="getAll" class="btn btn-primary">Back to Courses</a>
    <table class="table table-bordered table-hover" style="margin-top: 3%">
      <thead class="table table-primary">
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Lecturer</th>
      </tr>
      </thead>
      <tbody>
      <td>${course.name}</td>
      <td>${course.description}</td>
      <td>${course.lecturer.name}</td>
      </tbody>
    </table>
</body>
</html>

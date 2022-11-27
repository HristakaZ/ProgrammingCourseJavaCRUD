<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 27.11.2022 г.
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>Delete Course</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
  <style>
    a {
      text-decoration: none;
      color: white;
      margin-top: 10px;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
<h3>Are you sure you want to delete this course?</h3>
<a href="getAll" class="btn btn-primary">Back to Courses</a>
<form:form method="POST"
           action="delete" modelAttribute="course">
  <table class="table table-bordered table-hover" style="margin-top: 3%">
    <thead class="table table-primary">
    <tr>
      <th>Name</th>
      <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <td>${courseToDelete.name}</td>
    <td>${courseToDelete.description}</td>
    </tbody>
  </table>

  <form:hidden path="id" value="${courseToDelete.id}"/>

  <input type="submit" value="Delete" class="btn btn-danger" />

</form:form>
</body>
</html>
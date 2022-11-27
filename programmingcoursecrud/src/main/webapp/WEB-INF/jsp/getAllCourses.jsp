<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 27.11.2022 г.
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Get All Courses</title>
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
    <h1 style="text-align: center; margin-top: 5%">Courses</h1>
    <a href="loadCreateForm" class="btn btn-primary">Create Course</a>
    <table class="table table-bordered table-hover" style="margin-top: 3%">
      <thead class="table table-primary">
      <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Lecturer</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach items="${courses}" var="course">
        <tr>
          <td><c:out value="${course.name}"/></td>
          <td><c:out value="${course.description}"/></td>
          <td><c:out value="${course.lecturer.name}"/></td>
          <td class="table-warning">
            <a href="getById?id=${course.id}" class="btn btn-info">Details</a> |
            <a href="loadUpdateForm?id=${course.id}" class="btn btn-warning">Update</a> |
            <a href="loadDeleteForm?id=${course.id}" class="btn btn-danger">Delete</a>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
</body>
</html>

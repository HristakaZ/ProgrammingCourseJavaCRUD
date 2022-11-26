<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 19.11.2022 г.
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Get All Lecturers</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
    <h1 style="text-align: center; margin-top: 5%">Lecturers</h1>
    <a href="loadCreateForm">Create Lecturer</a>
    <table class="table table-bordered table-hover" style="margin-top: 3%">
        <thead class="table table-primary">
        <tr>
            <th>Name</th>
            <th>Age</th>
            <th>Description</th>
            <th>Courses</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${lecturers}" var="lecturer">
            <tr>
                <td><c:out value="${lecturer.name}"/></td>
                <td><c:out value="${lecturer.age}"/></td>
                <td><c:out value="${lecturer.description}"/></td>
                <td class="table-warning">
                    <c:forEach items="${lecturer.courses}" var="course">
                        ${course.name};
                    </c:forEach>
                </td>
                <td>
                    <a href="getById?id=${lecturer.id}">Details</a> |
                    <a href="getById?id=${lecturer.id}">Update</a> |
                    <a href="getById?id=${lecturer.id}">Details</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
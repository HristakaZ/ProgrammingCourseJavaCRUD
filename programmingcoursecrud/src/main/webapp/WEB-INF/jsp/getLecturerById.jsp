<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 26.11.2022 г.
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
<a href=""></a>
<h3>Lecturer Details</h3>
<a href="getAll" class="btn btn-primary">Back to Lecturers</a>
<table class="table table-bordered table-hover" style="margin-top: 3%">
    <thead class="table table-primary">
    <tr>
        <th>Name</th>
        <th>Age</th>
        <th>Description</th>
    </tr>
    </thead>
    <tbody>
    <td>${lecturer.name}</td>
    <td>${lecturer.age}</td>
    <td>${lecturer.description}</td>
    </tbody>
</table>
</body>
</html>

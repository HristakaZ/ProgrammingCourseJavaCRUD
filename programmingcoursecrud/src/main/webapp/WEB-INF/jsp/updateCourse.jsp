<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 27.11.2022 г.
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Update Course</title>
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
    </style>
</head>
<body>
<h3>Update Course</h3>
<a href="getAll" class="btn btn-primary">Back to Courses</a>
<form:form method="POST"
           action="update" modelAttribute="course">
  <table>
    <tr>
      <td><form:hidden path="id" value="${courseToUpdate.id}"/></td>
    </tr>
    <tr>
      <td><form:label path="name">Name</form:label></td>
      <td><form:input path="name" value="${courseToUpdate.name}" cssClass="form-control"/></td>
      <td>
        <form:errors path="name" cssClass="error" element="div" />
      </td>
    </tr>
    <tr>
      <td><form:label path="description">Description</form:label></td>
      <td><form:input path="description" value="${courseToUpdate.description}" cssClass="form-control"/></td>
      <td>
        <form:errors path="description" cssClass="error" element="div" />
      </td>
    </tr>
    <tr>
      <td><form:label path="lecturer">Lecturer</form:label></td>
      <td>
        <form:select path="lecturer" cssClass="form-select">
          <form:option selected="selected" value="${courseToUpdate.lecturer.id}">${courseToUpdate.lecturer.name}</form:option>
          <form:options items="${lecturers}" itemValue="id" itemLabel="name"/>
        </form:select>
      </td>
    </tr>
    <tr>
      <td><input type="submit" value="Update" class="btn btn-primary"/></td>
    </tr>
  </table>
</form:form>
</body>
</html>

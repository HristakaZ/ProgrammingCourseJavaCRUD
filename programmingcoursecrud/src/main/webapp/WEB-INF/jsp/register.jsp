<%--
  Created by IntelliJ IDEA.
  User: Hristoslav-PC
  Date: 2.12.2022 г.
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register</title>
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
<h3>Register</h3>
<h6>Logged in as: ${userEmail}</h6>
<form:form method="POST"
           action="register" modelAttribute="user">
    <table>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email" cssClass="form-control"/></td>
            <td><form:errors path="email" cssClass="error" element="div" /></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:password path="password" cssClass="form-control"/></td>
            <td><form:errors path="password" cssClass="error" element="div" /></td>
        </tr>
        <tr>
            <td><input type="submit" value="Register" class="btn btn-primary"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>

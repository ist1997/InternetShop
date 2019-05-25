<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<div style="text-align: center;">
    <c:out value="You entered as ${user.getLogin()} (${user.getRole()})"/>
    <br>
    <h1>User List</h1>
</div>
<table border='1' width='100%'>
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Password</th>
        <th>Email</th>
        <th>Role id</th>
        <th>Salt</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="currUser" items="${users}">
        <tr>
            <td>${currUser.getId()}</td>
            <td>${currUser.getLogin()}</td>
            <td>${currUser.getPassword()}</td>
            <td>${currUser.getEmail()}</td>
            <td>${currUser.getRole().ordinal()}</td>
            <td>${currUser.getSalt()}</td>
            <td><a href='/admin/updateUser?id=${currUser.getId()}&type=user'>Update user</a></td>
            <td><a href='/admin/deleteUser?id=${currUser.getId()}&type=user'>Delete user</a></td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center;">
    <br>
    <a href="/admin/addUser">Add new user</a>
    <br>
    <a href="/admin/marketplace">Back to good list</a>
</div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h1>Update information about user</h1>
<form action="update" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" readonly name="id" value="${user.getId()}"/></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login" value="${user.getLogin()}"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="text" readonly name="password" value="${user.getPassword()}"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email" value="${user.getEmail()}"/></td>
        </tr>
        <tr>
            <td>Role id:</td>
            <td><input type="number" name="roleId" value="${user.getRole().ordinal()}"/></td>
        </tr>
        <tr>
            <td>Salt:</td>
            <td><input type="text" readonly name="salt" value="${user.getSalt()}"/></td>
        </tr>
        <tr>
            <input type="hidden" name="type" value="user" />
            <td colspan="2"><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
<br/>
<form action="userlist.jsp">
    <input type="submit" value="Back to user list">
</form>
</body>
</html>

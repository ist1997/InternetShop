<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form action="/admin/addUser" method="post">
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>Role id:</td>
            <td><input type="number" name="roleId"/></td>
        </tr>
        <tr>
            <input type="hidden" name="type" value="user"/>
            <td colspan="2"><input type="submit" value="Add user"/></td>
        </tr>
    </table>
</form>
<br>
<a href="/admin/users">Back to user list</a>
</body>
</html>

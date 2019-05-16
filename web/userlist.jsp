<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userDao" class="dao.UserDaoHibernate"/>
<html>
<head>
    <title>User list</title>
</head>
<body>
<div style="text-align: center;">
    <c:out value="You entered as ${user.getLogin()} (${userDao.getUserRole(user)})"/>
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
    <c:forEach var="currUser" items="${userDao.allUsers}">
        <tr>
            <td>${currUser.getId()}</td>
            <td>${currUser.getLogin()}</td>
            <td>${currUser.getPassword()}</td>
            <td>${currUser.getEmail()}</td>
            <td>${currUser.getRole().ordinal()}</td>
            <td>${currUser.getSalt()}</td>
            <td><a href='update?id=${currUser.getId()}&type=user'>Update user</a></td>
            <td><a href='delete?id=${currUser.getId()}&type=user'>Delete user</a></td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center;">
    <br>
    <form action="add_user.jsp" method="post">
        <input type="submit" value="Add new user">
    </form>
    <br>
    <form action="marketplace.jsp" method="post">
        <input type="submit" value="Back to good list">
    </form>
</div>
</body>
</html>

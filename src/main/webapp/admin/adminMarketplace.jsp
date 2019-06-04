<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Good list</title>
</head>
<body>
<div style="text-align: center;">
    <c:out value="You entered as ${user.getLogin()} (${user.getRole()})"/>
    <a href="/admin/users"> User list</a>
    <br>
    <h1>Good List</h1>
</div>
<table border='1' width='100%'>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="good" items="${goods}">
        <tr>
            <td>${good.getId()}</td>
            <td>${good.getName()}</td>
            <td>${good.getDescription()}</td>
            <td>${good.getPrice()} UAH</td>
            <td><a href='/admin/updateGood?id=${good.getId()}&type=good'>Update item</a></td>
            <td><a href='/admin/deleteGood?id=${good.getId()}&type=good'>Delete item</a></td>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center;">
    <br>
    <a href="/admin/addGood">Add new good</a>
</div>
</body>
</html>

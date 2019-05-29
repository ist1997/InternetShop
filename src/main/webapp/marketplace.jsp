<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Good list</title>
</head>
<body>
<div style="text-align: center;">
    <c:out value="You entered as ${user.getLogin()} (${user.getRole()})"/>
    <br>
    <h1>Good List</h1>
</div>
<table border='1' width='100%'>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <th>Add to order</th>
    </tr>
    <c:forEach var="good" items="${goods}">
        <tr>
            <td>${good.getId()}</td>
            <td>${good.getName()}</td>
            <td>${good.getDescription()}</td>
            <td>${good.getPrice()} UAH</td>
            <td><a href='addToOrder?id=${good.getId()}'>Add to order</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<h2>Your order</h2>
<p>Count of goods: ${goodsCount}</p>
<a href="/buy">Buy!</a>
</body>
</html>

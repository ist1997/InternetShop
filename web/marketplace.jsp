<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Role" %>
<jsp:useBean id="userDao" class="dao.UserDaoHibernate"/>
<jsp:useBean id="goodDao" class="dao.GoodDaoHibernate"/>
<html>
<head>
    <title>Good list</title>
</head>
<body>
<div style="text-align: center;">
    <c:out value="You entered as ${user.getLogin()} (${userDao.getUserRole(user)})"/>
    <c:if test="${user.getRole()==Role.ADMIN}">
        <a href='userlist.jsp'> User list</a>
    </c:if>
    <br>
    <h1>Good List</h1>
</div>
<table border='1' width='100%'>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Price</th>
        <c:if test="${user.getRole()==Role.ADMIN}">
            <th>Update</th>
            <th>Delete</th>
        </c:if>
        <c:if test="${user.getRole()==Role.USER}">
            <th>Buy</th>
        </c:if>
    </tr>
    <c:forEach var="good" items="${goodDao.allGoods}">
        <tr>
            <td>${good.getId()}</td>
            <td>${good.getName()}</td>
            <td>${good.getDescription()}</td>
            <td>${good.getPrice()} UAH</td>
            <c:if test="${user.getRole()==Role.ADMIN}">
                <td><a href='update?id=${good.getId()}&type=good'>Update item</a></td>
                <td><a href='delete?id=${good.getId()}&type=good'>Delete item</a></td>
            </c:if>
            <c:if test="${user.getRole()==Role.USER}">
                <td><a href='buy?id=${good.getId()}'>Buy!</a></td>
            </c:if>
        </tr>
    </c:forEach>
</table>
<div style="text-align: center;">
    <c:if test="${user.getRole()==Role.ADMIN}">
        <br>
        <form action="add_good.jsp" method="post">
            <input type="submit" value="Add new item">
        </form>
    </c:if>
</div>
</body>
</html>

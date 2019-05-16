<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<div style="text-align: center;">
    <c:if test="${userDoesntExist==true || wrongPassword==true}">
        You entered wrong login/password<br>
    </c:if>
    <h1>Log in</h1>
    <form action="login" method="post">
        Login <input type="text" name="login">
        Password <input type="password" name="password">
        <input type="submit" value="Log in">
    </form>
    <form>
        Haven`t any account?
        <a href="registration.jsp">Registration</a>
    </form>
</div>
</body>
</html>

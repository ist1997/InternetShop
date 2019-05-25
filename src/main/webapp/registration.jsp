<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div style="text-align: center;">
    <c:if test="${userExists==true}">
        This user is already exists in database!<br>
    </c:if>
    <h1>Registration</h1>
    <form action="registration" method="post">
        Login <input type="text" name="login">
        Password <input type="password" name="password">
        Email <input type="email" name="email">
        <input type="submit" value="Registration">
    </form>
    <form>
        Have an account?
        <a href="/login">Log in</a>
    </form>
</div>
</body>
</html>

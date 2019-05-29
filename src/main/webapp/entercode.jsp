<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter code</title>
</head>
<body>
<div style="text-align: center;">
    <h2>Enter your code:</h2>
    <form action="/buy" method="post">
        <input hidden type="text" name="order_id" value="${order_id}">
        <input type="password" name="code">
        <input type="submit" value="OK">
    </form>
</div>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new item</title>
</head>
<body>
<form action="/admin/addGood" method="post">
    <table>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" height="2" name="description"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" name="price"/></td>
        </tr>
        <tr>
            <input type="hidden" name="type" value="good"/>
            <td colspan="2"><input type="submit" value="Add item"/></td>
        </tr>
    </table>
</form>
<br/>
<a href="/admin/marketplace">Back to good list</a>
</body>
</html>

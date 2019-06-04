<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update good</title>
</head>
<body>
<h1>Update information about good</h1>
<form action="/admin/updateGood" method="post">
    <table>
        <tr>
            <td>Id:</td>
            <td><input type="text" readonly name="id" value="${good.getId()}"/></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="name" value="${good.getName()}"/></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type="text" name="description" value="${good.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" name="price" value="${good.getPrice()}"/></td>
        </tr>
        <tr>
            <input type="hidden" name="type" value="good"/>
            <td colspan="2"><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
<br/>
<a href="/admin/marketplace">Back to good list</a>
</body>
</html>

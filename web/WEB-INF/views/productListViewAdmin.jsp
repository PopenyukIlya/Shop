<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin product List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Admin product List</h3>
<a href="createProduct" >Создать продукт</a>
<a href="usersList" >Лист Пользователей</a>
<a href="orders" >Заказы</a>
<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="editProduct?id=${product.id}">Edit</a>
            </td>
            <td>
                <a href="deleteProduct?id=${product.id}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>



<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
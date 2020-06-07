<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Add to cart</th>
    </tr>
    <c:forEach items="${productList}" var="product" >
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>
                <a href="addProduct?id=${product.id}&name=${product.name}&price=${product.price}">Add to cart</a>
            </td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
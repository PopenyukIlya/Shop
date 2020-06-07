<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkout</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>Product id</th>
        <th>Product name</th>
        <th>Quantity</th>
        <th>Product price</th>
    </tr>
    <c:forEach items="${cartList}" var="cart" >
        <tr>
            <td>${cart.product_id}</td>
            <td>${cart.product_name}</td>
            <td>${cart.quantity}</td>
            <td>${cart.product_price}</td>
            <td>
                <a href="makeOrder?id=${cart.product_id}&quantity=${cart.quantity}&product_name=${cart.product_name}&product_price=${cart.product_price}">Make order</a>
            </td>

        </tr>
    </c:forEach>
</table>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
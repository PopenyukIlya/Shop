<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Начало</h3>

<p>Это пример приложения где используются технологии Servlet, JSP и JDBC </p>
<b>Done</b>
<ul>
    <li>Login,logout,registration by db.</li>
    <li>Storing user information in cookies.</li>
    <li>User that have role not null have func. in product list:create product, edit product, delete product.</li>
</ul>
<b>To do</b>
<ul>
    <li>User can add to cart products, and make a order</li>
    <li></li>
    <li></li>
</ul>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
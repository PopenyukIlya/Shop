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
<p style="color: red;">${errorString}</p>
<h3>List of Users</h3>

<form method="POST" action="${pageContext.request.contextPath}/usersList">
   <b>Ваше имя:</b><br>
        <input type="text" name="userName" value="${userAccount.userName}" size="40">
      <input type="submit" value="Отправить">
</form>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Add to Blacklist</th>
    </tr>
    <c:forEach items="${listOfUsers}" var="user" >
        <tr>
            <td>${user.id}</td>
            <td>${user.userName}</td>
            <td>
                <a href="addToBlaclist?id=${user.id}">Add to Blacklist</a>
            </td>
        </tr>
    </c:forEach>
</table>


<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
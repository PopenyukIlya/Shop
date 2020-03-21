<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>
<p style="color: red;">${errorString}</p>
<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Привет: ${user.userName}</h3>

User Name: <b>${user.userName}</b>
<br />
Gender: ${user.gender} <br />

<a href="${pageContext.request.contextPath}/contacts">Контактные данные</a>
<a href="${pageContext.request.contextPath}/${link}">${linkName}</a>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
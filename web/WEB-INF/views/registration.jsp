<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Registration</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/registration">
        <tr>
            <td>User Name</td>
            <td><input type="text" name="userName" value="${userAccount.userName}" /></td>
        </tr>
        <tr>
            <td>Gender</td>
            <td><select type="text" name="gender" value="${userAccount.gender}"/>
            <option value="M">M</option>
            <option value="F">F</option>
        </select>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="password" value="${userAccount.password}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="login">Cancel</a>
            </td>
        </tr>
</form>

<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
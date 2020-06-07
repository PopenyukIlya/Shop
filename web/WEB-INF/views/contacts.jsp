<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contacts</title>
</head>
<body>

<jsp:include page="_header.jsp"></jsp:include>
<jsp:include page="_menu.jsp"></jsp:include>

<h3>Update contacts</h3>

<p style="color: red;">${errorString}</p>

<form method="POST" action="${pageContext.request.contextPath}/contacts">
    <table border="0">
        <tr>
            <td>Address</td>
            <td><input type="text" name="address" value="${address}" /></td>
        </tr>
        <tr>
            <td>Phone num</td>
            <td><input type="text"id="phone"  placeholder="+375(00)000-00-00" name="phone_number" value="${phone_number}" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit" />
                <a href="userInfo">Cancel</a>
            </td>
        </tr>
    </table>
</form>
<script src="https://unpkg.com/imask"></script>
<script>var element = document.getElementById('phone');
var maskOptions = {
    mask: '+375(00)000-00-00',
    lazy: false
}
var mask = new IMask(element, maskOptions);</script>
<jsp:include page="_footer.jsp"></jsp:include>

</body>
</html>
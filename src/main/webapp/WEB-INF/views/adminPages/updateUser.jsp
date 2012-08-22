<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
    <title>Designate User</title>
</head>
<body>
<spring:url value="/profile/" var="profileUrl" />
<a href="${profileUrl}" title="Profile"> Profile</a>
<br />
<hr />

<h3>Fill in login of user to designate</h3>
<table border="1">
    <tr>
        <td>Login</td>
        <td>Role</td>
    </tr>
    <c:forEach var="a" items="${userList.users}">
        <tr>
            <td>${a.login}</td>
            <td>${a.role}</td>
        </tr>
    </c:forEach>
</table>
<hr />
<form:form method="post" commandName="loginForm">
    <table>
        <tr>
            <td>Login</td>
            <td><form:input path="login" /></td>
            <td>${Error}</td>
        </tr>
        <tr>
            <td colspan="3">
                <input type="submit" value="Designate" />
            </td>
        </tr>
    </table>
</form:form>
</body>
</html>
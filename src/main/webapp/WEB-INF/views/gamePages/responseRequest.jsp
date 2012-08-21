<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Response Request</title>
</head>
<body>

<spring:url value="/profile/" var="profileUrl" />
<a href="${profileUrl}" title="Profile"> Profile</a>
<br />
<hr />

<h3>Fill in id of request to response it</h3>
<table border="1">
    <tr>
        <td>id</td>
        <td>Login of User</td>
    </tr>
    <c:forEach var="a" items="${requestList.requests}">
        <tr>
            <td>${a.id}</td>
            <td>${a.firstLogin}</td>
        </tr>
    </c:forEach>
</table>
<hr />
<form:form method="post" commandName="idForm">
    <table>
        <tr>
            <td>Id</td>
            <td><form:input path="id" /></td>
            <td>
                <form:select path="state" >
                    <form:option value="agree">agree</form:option>
                    <form:option value="refuse">refuse</form:option>
                </form:select>
            </td>
            <td>${Error}</td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="submit" value="Response" />
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
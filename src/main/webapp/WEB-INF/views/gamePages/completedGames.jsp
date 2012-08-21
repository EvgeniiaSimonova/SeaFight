<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Completed Games</title>
</head>
<body>

<spring:url value="/profile/" var="profileUrl" />
<a href="${profileUrl}" title="Profile"> Profile</a>
<br />
<hr />

<h3>All my completed games</h3>

<table border="1">
    <tr>
        <td>Id</td>
        <td>First player</td>
        <td>Second Player</td>
        <td>Winner</td>
    </tr>
    <c:forEach var="a" items="${completedGameList.games}">
        <tr>
            <td>${a.id}</td>
            <td>${a.firstLogin}</td>
            <td>${a.secondLogin}</td>
            <td>${a.winner}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Game</title>
</head>
<body>

<spring:url value="/profile/" var="profileUrl" />
<a href="${profileUrl}" title="Profile"> Profile</a>
<br />
<hr />

<spring:url value="" var="gameUrl" />

<form:form method="post" commandName="tableForm" action="/game/mygames/continued/${id}">
    <table border="1">
    <c:forEach begin="0" end="9" var="a">
        <tr>
            <c:forEach begin="0" end="9" var="b">
                <td width="30" height="30" align="center" valign="middle">
                    <form:checkbox path="number" value="${a*10+b}" />
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
    </table>

    <input type="submit" value="Send" />
</form:form>

</body>
</html>
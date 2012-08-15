<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page session="true"%>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<table width="80%" align="center">
    <tr>
        <td colspan="3" align="center"><h3>Profile</h3></td>
    </tr>
    <tr>
        <td width="25%">Menu</td>
        <td>Hi, ${userDetailsImpl.username}!</td>
        <td width="25%">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                Menu Admin
            </sec:authorize>
        </td>
    </tr>
    <tr>
        <td>
            <spring:url value="/game/" var="gameUrl" />
            <spring:url value="/statistics/" var="statisticsUrl" />
            <spring:url value="/logout" var="logoutUrl" />

            <a href="${gameUrl}" title="Start Game"> - start game</a>
            <br />
            <a href="${statisticsUrl}" title="Statistics"> - statistics</a>
            <br />
            <a href="${logoutUrl}" title="Logout"> - logout</a>
        </td>
        <td></td>
        <td>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <spring:url value="/admin/deleteuser/" var="deleteuserUrl" />
                <spring:url value="/admin/updateuser/" var="updateuserUrl" />

                <a href="${deleteuserUrl}" title="Delete User"> - delete user</a>
                <br />
                <a href="${updateuserUrl}" title="Designate Admin"> - designate admin</a>
            </sec:authorize>
        </td>
    </tr>
</table>

</body>
</html>
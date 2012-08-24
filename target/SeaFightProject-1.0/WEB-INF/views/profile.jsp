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
        <td colspan="2" align="center"><h3>Profile</h3></td>
    </tr>
    <tr>
        <td width="35%">
            <h3>Menu</h3>
            <spring:url value="/game/requests/send/" var="sendRequestUrl" />
            <spring:url value="/game/requests/response/" var="responseRequestUrl" />
            <spring:url value="/game/mygames/continued/" var="continuedGamesUrl" />
            <spring:url value="/game/mygames/completed/" var="completedGamesUrl" />
            <spring:url value="/logout" var="logoutUrl" />
            <p> - Requests</p>
            <a href="${sendRequestUrl}" title="Send Request"> - - send request</a>
            <br />
            <a href="${responseRequestUrl}" title="Response Request"> - - response request</a>
            <br />

            <p> - Games</p>
            <a href="${continuedGamesUrl}" title="Continued Games"> - - continued games</a>
            <br />
            <a href="${completedGamesUrl}" title="Completed Games"> - - completed games</a>
            <br />

            <p> - Exit</p>
            <a href="${logoutUrl}" title="Logout"> - - logout</a>
            <br />
            <br />


            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <h3>Menu Admin</h3>
                <spring:url value="/admin/deleteuser/" var="deleteuserUrl" />
                <spring:url value="/admin/updateuser/" var="updateuserUrl" />

                <a href="${deleteuserUrl}" title="Delete User"> - delete user</a>
                <br />
                <a href="${updateuserUrl}" title="Designate Admin"> - designate admin</a>
            </sec:authorize>
        </td>

        <td>Hi, ${userDetailsImpl.username}!</td>
    </tr>
</table>

</body>
</html>
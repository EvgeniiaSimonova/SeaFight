<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Game</title>
    <c:if test="${move == 1}">
        <meta http-equiv="refresh" content="1; url=/games/${id}/">
    </c:if>
    <c:if test="${move == 0}">
        <meta http-equiv="refresh" content="1; url=/game/mygames/continued/">
    </c:if>
</head>
<body>
<h3 align="center">
<c:if test="${move == 1}">
    It is your move
</c:if>
<c:if test="${move == 0}">
    It is not your move. Please, come later.
</c:if>
</h3>
</body>
</html>
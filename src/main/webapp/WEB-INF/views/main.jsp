<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags"%>
<%@ page session="true"%>
<html>
    <head>
        <!--<meta http-equiv="refresh" content="5; url=/profile/">-->
        <title>Main Page</title>
    </head>
    <body>
        <div align="center">
            <h3>You have to sign in or register to have access</h3>
            <spring:url value="/registration/" var="registrationUrl" />
            <spring:url value="/spring_security_login" var="signinUrl" />

            <a href="${signinUrl}" title="Sign in">Sign in</a>
            <br/>
            <a href="${registrationUrl}" title="Registration">Registration</a><br />
        </div>
    </body>
</html>
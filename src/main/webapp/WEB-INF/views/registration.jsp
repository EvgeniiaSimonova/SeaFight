<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>

<html>
<head>
    <title>Registration</title>
</head>
<body>
    <h3>Fill in next fields</h3>
    <form:form method="post" commandName="userForm">
        <table>
            <tr>
                <td>Login</td>
                <td><form:input path="login" /></td>
                <td>${LoginError}</td>
            </tr>
            <tr>
                <td>Password</td>
                <td><form:password path="password" /></td>
                <td></td>
            </tr>
            <tr>
                <td>Confirm Password</td>
                <td><form:password path="confirmPassword" /></td>
                <td>${PasswordError}</td>
            </tr>
            <tr>
                <td colspan="3">
                    <input type="submit" value="Submit" />
                </td>
            </tr>
        </table>
    </form:form>
</body>
</html>
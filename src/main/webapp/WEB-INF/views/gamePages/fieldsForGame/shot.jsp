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
<table align="center" cellpadding="20">
    <tr>
        <td>
            <p>My Field</p>
            <table border="1">
                <tr bgcolor="#ffd700">
                    <td></td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>E</td>
                    <td>F</td>
                    <td>G</td>
                    <td>H</td>
                    <td>I</td>
                    <td>J</td>
                </tr>
                <c:forEach var="b" begin="0" end="9">
                    <tr>
                        <td bgcolor="#ffd700">${b+1}</td>
                        <c:forEach var="a" items="${myField.cells}" begin="${b*10}" end="${b*10+9}">
                            <td width="30" height="30" align="center" valign="middle">${a}</td>
                        </c:forEach>
                    </tr>
                </c:forEach>

            </table>
        </td>
        <td>
            <p>Opponent's Field</p>
            <table border="1">
                <tr bgcolor="#7fffd4">
                    <td></td>
                    <td>A</td>
                    <td>B</td>
                    <td>C</td>
                    <td>D</td>
                    <td>E</td>
                    <td>F</td>
                    <td>G</td>
                    <td>H</td>
                    <td>I</td>
                    <td>J</td>
                </tr>
                <c:forEach var="b" begin="0" end="9">
                    <tr>
                        <td bgcolor="#7fffd4">${b+1}</td>
                        <c:forEach var="a" items="${opponentField.cells}" begin="${b*10}" end="${b*10+9}">
                            <td width="30" height="30" align="center" valign="middle">
                                ${a}
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </td>
    </tr>
    <tr>
        <td>
            d - deck <br />
            k - killed <br />
            o - miss <br />
        </td>
        <td>
            <form:form method="post" commandName="shotForm">
                <table>
                    <tr>
                        <td>Letter</td>
                        <td>
                            <form:select path="letter">
                                <form:option value="0">A</form:option>
                                <form:option value="1">B</form:option>
                                <form:option value="2">C</form:option>
                                <form:option value="3">D</form:option>
                                <form:option value="4">E</form:option>
                                <form:option value="5">F</form:option>
                                <form:option value="6">G</form:option>
                                <form:option value="7">H</form:option>
                                <form:option value="8">I</form:option>
                                <form:option value="9">J</form:option>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td>Figure</td>
                        <td>
                            <form:select path="figure" >
                                <form:option value="0">1</form:option>
                                <form:option value="1">2</form:option>
                                <form:option value="2">3</form:option>
                                <form:option value="3">4</form:option>
                                <form:option value="4">5</form:option>
                                <form:option value="5">6</form:option>
                                <form:option value="6">7</form:option>
                                <form:option value="7">8</form:option>
                                <form:option value="8">9</form:option>
                                <form:option value="9">10</form:option>
                            </form:select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Choose" /></td>
                    </tr>
                </table>
            </form:form>
        </td>
    </tr>
</table>
</body>
</html>
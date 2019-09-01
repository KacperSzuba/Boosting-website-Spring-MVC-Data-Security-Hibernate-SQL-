<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 01.09.2019
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <nav>
            <security:authorize access="hasAnyRole('USER','ADMIN')">
                <a href="${pageContext.request.contextPath}/account">Account page</a>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <a href="${pageContext.request.contextPath}/admin">Admin page</a>
            </security:authorize>
            <c:if test="${empty pageContext.request.userPrincipal}">
                <a href="${pageContext.request.contextPath}/login">Login Page</a>
                <a href="${pageContext.request.contextPath}/register">Register Page</a>
            </c:if>
        </nav>

        <h1>Change Email</h1>

        <form  action="${pageContext.request.contextPath}/account/changeEmailForm" method="get">
            <table>
                <tr>
                    <td><input name="email"  type="text" placeholder="Email"/></td>
                </tr>
                <tr>
                    <td><input name="repeatEmail"  type="text" placeholder="Repeat Email"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
            <p>${newEmail}</p>
        </form>
</body>
</html>

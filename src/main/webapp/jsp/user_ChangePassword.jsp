<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 31.08.2019
  Time: 23:19
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

        <h1>Change Password</h1>

        <form  action="${pageContext.request.contextPath}/account/changePasswordForm" method="get">
            <table>
                <tr>
                    <td><input name="password"  type="password" placeholder="Password"/></td>
                </tr>
                <tr>
                    <td><input name="repeatPassword"  type="password" placeholder="Repeat Password"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>

            <p> ${newPassword}</p>
        </form>
    </body>
</html>
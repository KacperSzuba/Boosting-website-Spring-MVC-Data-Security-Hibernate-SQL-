<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 23.08.2019
  Time: 22:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

    <nav>
        <ul>
            <security:authorize access="hasAnyRole('USER','ADMIN','BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/account">Account page</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/booster">Booster page</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li>
                <a href="${pageContext.request.contextPath}/admin">Admin page</a>
                </li>
            </security:authorize>
            <c:if test="${empty pageContext.request.userPrincipal}">
                <li>
                    <a href="${pageContext.request.contextPath}/login">Login Page</a>
                </li>
                <li>
                <a href="${pageContext.request.contextPath}/register">Register Page</a>
                </li>
            </c:if>
            <li>
                <a href="${pageContext.request.contextPath}/order/">Order page</a>
            </li>
        </ul>
    </nav>

    <h2>Home Page</h2>
    </body>
</html>

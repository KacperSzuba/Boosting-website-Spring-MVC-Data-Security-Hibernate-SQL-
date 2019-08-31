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
        <meta name='viewport' content='width=device-width, initial-scale=1'>
        <script src='https://kit.fontawesome.com/a076d05399.js'></script>
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

    <h2>Home Page</h2>




    </body>
</html>

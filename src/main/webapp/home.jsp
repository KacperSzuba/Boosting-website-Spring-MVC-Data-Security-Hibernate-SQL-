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

    <h2>Home Page</h2>


    <security:authorize access="hasRole('USER')">
    <a href="${pageContext.request.contextPath}/account">Account page</a>
    </security:authorize>
    <c:if test="${empty pageContext.request.userPrincipal}">
        <a href="${pageContext.request.contextPath}/login">Login Page</a>
        <a href="${pageContext.request.contextPath}/register">Register Page</a>
    </c:if>
    </body>
</html>

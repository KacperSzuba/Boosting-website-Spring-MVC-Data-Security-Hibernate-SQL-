<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 12.09.2019
  Time: 13:23
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
        <security:authorize access="hasAnyRole('USER','ADMIN')">
            <li>
                <a href="${pageContext.request.contextPath}/account">Account page</a>
            </li>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN')">
            <li>
                <a href="${pageContext.request.contextPath}/admin">Admin page</a>
            </li>
        </security:authorize>
    </ul>
</nav>
    <p>Username : ${user.username}</p>
    <p>Email : ${user.email}</p>
    <p>Role : ${user.roles}</p>
    <spring:url value="/admin/ban/${user.id}" var="banUser" />
    <spring:url value="/admin/un-ban/${user.id}" var="unbanUser" />

    <c:if test="${user.enabled == true}">
    <input type="button"  onclick="location.href='${banUser}'" value="Ban">
    </c:if>
    <c:if test="${user.enabled == false}">
    <input type="button"  onclick="location.href='${unbanUser}'" value="Un-ban">
    </c:if>
</body>
</html>

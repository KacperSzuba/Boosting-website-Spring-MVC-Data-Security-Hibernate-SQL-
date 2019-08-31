<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 28.08.2019
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
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
    </nav>
<i class='fas fa-lock-open'></i>
    <h1>Admin Page</h1>
    <a href="${pageContext.request.contextPath}/admin/ban_user">Ban User</a>
</body>
</html>

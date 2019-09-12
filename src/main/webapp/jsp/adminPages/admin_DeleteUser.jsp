<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
</body>
</html>

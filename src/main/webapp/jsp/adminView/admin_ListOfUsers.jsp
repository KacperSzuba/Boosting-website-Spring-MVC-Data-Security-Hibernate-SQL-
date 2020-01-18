<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 30.08.2019
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    </head>
    <body>
    <nav>
        <ul>
            <security:authorize access="hasAnyRole('USER','ADMIN','BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/account">Account page</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li>
                    <a href="${pageContext.request.contextPath}/admin">Admin page</a>
                </li>
            </security:authorize>
            <li>
                <a href="${pageContext.request.contextPath}/order/">Order page</a>
            </li>
        </ul>
    </nav>
        <form:form method="get" modelAttribute="users">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">No.</th>
                    <th scope="col">Id</th>
                    <th scope="col">Username</th>
                    <th scope="col">Ban User</th>
                </tr>
                </thead>
                <c:forEach items="${users}" var="user" varStatus="status">
                    <tbody>
                    <tr>
                        <th>${status.count}</th>
                        <th><c:out value="${user.id}" /></th>
                        <th><c:out value="${user.username}" /></th>
                        <th>
                            <spring:url value="/admin/userDetails/${user.id}" var="banUser" />
                            <a href="${banUser}">More</a>
                        </th>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>
        </form:form>
    </body>
</html>

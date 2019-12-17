<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 17.12.2019
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
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
                    <a href="${pageContext.request.contextPath}/order">Order page</a>
                </li>
            </ul>
        </nav>
        <form:form method="get" modelAttribute="orders">
            <table class="table">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">No.</th>
                    <th scope="col">Current division</th>
                    <th scope="col">Destination Division </th>
                    <th scope="col">Lol Username</th>
                    <th scope="col">Region</th>
                    <th scope="col">Booster</th>
                    <th scope="col">Whether Done</th>
                </tr>
                </thead>
                <c:forEach items="${orders}" var="order" varStatus="status">
                    <tbody>
                        <tr>
                            <th>${status.count}</th>
                            <th><c:out value="${order.currentTier}${order.currentDivision}" /></th>
                            <th><c:out value="${order.destinationTier}${order.destinationDivision}" /></th>
                            <th><c:out value="${order.lolUsername}" /></th>
                            <th><c:out value="${order.region}" /></th>
                            <th><c:out value="${order.booster.username}" /></th>
                            <th><c:out value="${order.whetherDone}"/></th>
                        </tr>
                    </tbody>
                </c:forEach>
            </table>
        </form:form>
    </body>
</html>

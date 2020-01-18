<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 25.09.2019
  Time: 11:17
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
            <security:authorize access="hasRole('BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/booster">Booster page</a>
                </li>
            </security:authorize>
            <li>
                <a href="${pageContext.request.contextPath}/order/">Order page</a>
            </li>
        </ul>
    </nav>

        <h1>Current Boost</h1>
    <form:form modelAttribute="currentOrderBoost" method="get">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">No.</th>
                <th scope="col">Id</th>
                <th scope="col">Current Tier</th>
                <th scope="col">Destination Tier</th>
                <th scope="col">More about boost</th>
                <th scope="col">Finish boost</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>
                    <c:if test="${currentOrderBoost.id != null}">
                        <c:out value="1" />
                    </c:if>
                </th>
                <th><c:out value="${currentOrderBoost.id}" /></th>
                <th><c:out value="${currentOrderBoost.currentTier}" /></th>
                <th><c:out value="${currentOrderBoost.destinationTier}" /></th>
                <th>
                    <spring:url value="/booster/orderDetails/${currentOrderBoost.id}" var="orderDetails" />
                    <a href="${orderDetails}">More</a>
                </th>
                <th>
                    <c:if test="${currentOrderBoost.id != null}">
                        <a href="${pageContext.request.contextPath}/booster/currentBoost/finishBoost">Finish</a>
                    </c:if>
                </th>
            </tr>
            </tbody>
        </table>
    </form:form>
    </body>
</html>

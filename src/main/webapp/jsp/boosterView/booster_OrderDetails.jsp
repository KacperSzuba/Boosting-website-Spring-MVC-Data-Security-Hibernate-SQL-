<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 16.09.2019
  Time: 15:16
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
            <li>
                <a href="${pageContext.request.contextPath}/order">Order page</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/booster/listOfFreeOrders">Back to previous page</a>
            </li>
        </ul>
    </nav>
    <h1>Order details</h1>
        <spring:url value="/booster/orderDetails/${id}/addBoost" var="addBoost" />
        <a href="${addBoost}">Add boost</a>
        <p><label>League Of Legends username:</label>${boostDetails.lolUsername}</p>
        <p><label>League Of Legends password</label>${boostDetails.lolPassword}</p>
        <p>
            <label>Current Tier:</label>${boostDetails.currentTier} ${boostDetails.currentDivision}
            <label>Points:</label>
        </p>
        <p>
            <label>Destination Tier:</label>${boostDetails.destinationTier} ${boostDetails.destinationDivision}
            <label>Points:</label>
        </p>
        <p><label>Region:</label>${boostDetails.region}</p>
        <p><label>Note to booster:</label>${boostDetails.noteToBoosters}</p>

    </body>
</html>

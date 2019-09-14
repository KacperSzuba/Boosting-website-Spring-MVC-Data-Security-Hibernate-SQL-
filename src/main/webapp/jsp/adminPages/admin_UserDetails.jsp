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
            <li>
                <a href="${pageContext.request.contextPath}/admin/ban_user">Back to previous page</a>
            </li>
        </ul>
    </nav>
        <p>Username : ${user.username}</p>
        <p>Email : ${user.email}</p>
        <p>Role : ${currentRole}</p>
        <spring:url value="/admin/ban/${user.id}" var="banUser" />
        <spring:url value="/admin/un-ban/${user.id}" var="unbanUser" />
        <spring:url value="/admin/setAsBooster/${user.id}" var="setAsBooster"/>
        <spring:url value="/admin/setAsUser/${user.id}" var="setAsUser"/>

        <c:if test="${user.enabled == true}">
            <input type="button"  onclick="location.href='${banUser}'" value="Ban">
        </c:if>
        <c:if test="${user.enabled == false}">
            <input type="button" onclick="location.href='${unbanUser}'" value="Un-ban">
        </c:if>
        <c:if test="${currentRole.equals(expectedRoleIsROLE_USER)}">
            <input type="button" onclick="location.href='${setAsBooster}'" value="Set Role name as a Booster">
        </c:if>
        <c:if test="${currentRole.equals(expectedRoleIsROLE_BOOSTER)}">
            <input type="button" onclick="location.href='${setAsUser}'" value="Set Role name as a User ">
        </c:if>
    </body>
</html>

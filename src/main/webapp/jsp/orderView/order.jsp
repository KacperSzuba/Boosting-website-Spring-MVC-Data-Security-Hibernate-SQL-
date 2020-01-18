<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 17.01.2020
  Time: 22:55
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
                <li>
                    <a href="${pageContext.request.contextPath}/order">Order page</a>
                </li>
            </ul>
        </nav>

        <form:form action="informationAboutAccount" modelAttribute="orderBoost" >
            <form:input path="lolUsername" placeholder="LoL Username" />
            <form:input path="lolPassword" placeholder="LoL Password" />
            <form:select path="region">
                <c:forEach items ="${listOfRegions}" var = "name">
                    <form:option value="${name}" />
                </c:forEach>
            </form:select>
            <form:textarea path="noteToBoosters" placeholder="Message to the booster" />
            <input type="submit" value="Submit"/>
        </form:form>
    </body>
</html>

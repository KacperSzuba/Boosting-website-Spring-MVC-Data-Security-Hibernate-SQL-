<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 09.12.2019
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Messages received</h1>
        <c:forEach items="${messages}" var="project">
            <div>
                <c:out value="${project}" />
                <spring:url value="/message/singleConversation/${project}" var="orderDetails" />
                <a href="${orderDetails}">More</a>
            </div>
        </c:forEach>
    </body>
</html>

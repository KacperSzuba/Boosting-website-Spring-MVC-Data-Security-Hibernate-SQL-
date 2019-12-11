<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 09.12.2019
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Single Conversation</h1>
        <c:forEach items="${msg2}" var="project">
            <div>
                <c:out value="${project.user.id}" />
                <c:out value="${project.message}" />
            </div>
        </c:forEach>
    </body>
</html>
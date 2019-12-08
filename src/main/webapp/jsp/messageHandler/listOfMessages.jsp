<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 08.12.2019
  Time: 23:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Message retrieve</h1>
    <c:forEach items="${msg}" var="project">
        <div>
            <c:out value="${project}" />
        </div>
    </c:forEach>
</body>
</html>

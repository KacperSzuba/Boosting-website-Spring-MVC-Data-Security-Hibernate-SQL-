<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 25.08.2019
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>

    <body>
    <h2>AccountPage</h2>
    <form:form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Logout" />
    </form:form>

    </body>
</html>

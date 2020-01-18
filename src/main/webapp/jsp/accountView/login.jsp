<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 26.08.2019
  Time: 17:07
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
            <c:if test="${empty pageContext.request.userPrincipal}">
                <li>
                    <a href="${pageContext.request.contextPath}/login">Login Page</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/register">Register Page</a>
                </li>
            </c:if>
            <li>
                <a href="${pageContext.request.contextPath}/order/">Order page</a>
            </li>
        </ul>
    </nav>
    <h1>Login to your account</h1>
    <form:form action="${pageContext.request.contextPath}/authenticateTheUser"
               method="POST">
        <p>
            User name: <input type="text" name="username" />
        </p>

        <p>
            Password: <input type="password" name="password" />
        </p>

        <input type="submit" value="Login" />

    </form:form>
    </body>
</html>

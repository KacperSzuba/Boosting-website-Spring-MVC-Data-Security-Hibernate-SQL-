<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 25.08.2019
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style type="text/css">
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
    <div id="Register">
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
        <h1>Account registration</h1>
        <p>${message}</p>
        <form:form action="${pageContext.request.contextPath}/registerForm" modelAttribute="register" method="post">
            <table>
                <tr>
                    <td><form:input path="username" name="username"  placeholder="Username" /></td>
                    <td><form:errors path="username" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><form:input path="password" name="password"  placeholder="Password" type="password" /></td>
                    <td><form:errors path="password" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><form:input path="email" name="email"  placeholder="Email" /></td>
                    <td><form:errors path="email" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit" /></td>
                </tr>
            </table>
        </form:form>
    </div>
    </body>
</html>

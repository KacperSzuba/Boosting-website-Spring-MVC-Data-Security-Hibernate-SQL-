<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
</head>
<body>
<div id="Register">
    <h2>Account Registration</h2>

    <form:form action="registerForm"  method="post">
        <table>
            <tr>
                <td>
                    <input name="username"  placeholder="Username"></input>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="password"  placeholder="Password" type="password"></input>
                </td>
            </tr>
            <tr>
                <td>
                    <input name="email"  placeholder="Email"></input>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="submit" value="Submit" class="save" />
                </td>
            </tr>
        </table>
    </form:form>
</div>
</body>
</html>

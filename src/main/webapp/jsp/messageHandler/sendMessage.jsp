<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 12.12.2019
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
    <form:form method="post" action="message/send" modelAttribute="message">
        <form:select path="user2.id" items="${users}" itemLabel="username" itemValue="id"/>
        <form:input path="title" placeholder="title"/>
        <form:input path="message" placeholder="message"/>
        <input type="submit" value="Submit"/>
    </form:form>
    </body>
</html>

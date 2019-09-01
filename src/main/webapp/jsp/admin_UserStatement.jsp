<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 30.08.2019
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/style.css" rel="stylesheet">
    <link href="/preloader.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
</head>
<body>

    <form:form method="get" modelAttribute="user">
        <table class="table">
            <thead class="thead-dark">
            <tr>
                <th scope="col">No.</th>
                <th scope="col">Id</th>
                <th scope="col">Username</th>
                <th scope="col">Enabled</th>
                <th scope="col">Ban User</th>
            </tr>
            </thead>
            <c:forEach items="${user}" var="iterator" varStatus="status">
                <tbody>
                <tr>
                    <th>${status.count}</th>
                    <th><c:out value="${iterator.id}" /></th>
                    <th><c:out value="${iterator.username}" /></th>
                    <th><c:out value="${iterator.enabled}" /></th>
                    <th>
                        <spring:url value="/admin/ban/${iterator.id}" var="banUser" />
                        <spring:url value="/admin/un-ban/${iterator.id}" var="unbanUser" />

                        <c:if test="${iterator.enabled == true}">
                            <input type="button"  onclick="location.href='${banUser}'" value="Ban" >
                        </c:if>
                        <c:if test="${iterator.enabled == false}">
                            <input type="button"  onclick="location.href='${unbanUser}'" value="Un-ban" >
                        </c:if>
                    </th>
                </tr>
                </tbody>
            </c:forEach>
        </table>
    </form:form>
</body>
</html>

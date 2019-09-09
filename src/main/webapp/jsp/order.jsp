<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 03.09.2019
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <style>
            .center {
                display: block;
                margin-left: auto;
                margin-right: auto;
                margin-top: 10%;
            }
        </style>
    </head>
    <body>
    <nav>
        <ul>
            <security:authorize access="hasAnyRole('USER','ADMIN')">
                <li>
                    <a href="${pageContext.request.contextPath}/account">Account page</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <li>
                    <a href="${pageContext.request.contextPath}/admin">Admin page</a>
                </li>
            </security:authorize>
            <li>
                <a href="${pageContext.request.contextPath}/order/showOrderPage">Order page</a>
            </li>
        </ul>
    </nav>


        <img class="center" src="${image}" />

    <div class="left">
        <a href="${pageContext.request.contextPath}/order/moveCurrentTierImageToLeft">Left</a>
    </div>
    <div class="right">
        <a href="${pageContext.request.contextPath}/order/moveCurrentTierImageToRight">Right</a>
    </div>
    <div class="Top">
        <a href="${pageContext.request.contextPath}/order/moveCurrentTierImageUp">Top</a>
    </div>
    <div class="Bottom">
        <a href="${pageContext.request.contextPath}/order/moveCurrentTierImageDown">Bottom</a>
    </div>
    <a href="${pageContext.request.contextPath}/order/makeOrder">Make order</a>
    <p>${msg}</p>
    </body>
</html>

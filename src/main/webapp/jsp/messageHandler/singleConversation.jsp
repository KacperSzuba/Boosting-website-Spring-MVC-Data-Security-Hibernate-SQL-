<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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
    <nav>
        <ul>
            <security:authorize access="hasAnyRole('USER','ADMIN','BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/account">Account page</a>
                </li>
            </security:authorize>
            <security:authorize access="hasRole('BOOSTER')">
                <li>
                    <a href="${pageContext.request.contextPath}/booster">Booster page</a>
                </li>
            </security:authorize>
            <li>
                <a href="${pageContext.request.contextPath}/order">Order page</a>
            </li>
        </ul>
    </nav>
        <h1>Single Conversation</h1>
        <c:forEach items="${conversation}" var="conversation">
            <div>
                <c:out value="${conversation.user.id}" />
                <c:out value="${conversation.message}" />
            </div>
        </c:forEach>
        <div id="div1">
            <c:forEach items="${conv}" var="conv">
                <div class="div">
                    <p class="message">${conv.message}</p>
                </div>
            </c:forEach>
        </div>
        <script>
            var div1 = document.getElementById("div1").setAttribute("align","right");
            var div = document.getElementsByClassName("div").setAttribute("align","right");
            var message = document.getElementsByClassName("message").value.toString();
            var you = String("123");

            if(JSON.stringify(message) === JSON.stringify(you)){
                 div1 = document.getElementById("div1").setAttribute("align","left");
                 div = document.getElementsByClassName("div").setAttribute("align","left");
            }
            /*
            else {
                var div1 = document.getElementById("div1").setAttribute("align","right");
                var div = document.getElementsByClassName("div").setAttribute("align","right");
            }

             */
        </script>
    </body>
</html>
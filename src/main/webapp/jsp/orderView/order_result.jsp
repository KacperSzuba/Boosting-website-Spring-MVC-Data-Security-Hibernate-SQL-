<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 18.01.2020
  Time: 14:40
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
                <li>
                    <a href="${pageContext.request.contextPath}/order/">Order page</a>
                </li>
            </ul>
        </nav>
        <p>${orderBoost.region}</p>
        <p>${orderBoost.lolUsername}</p>
        <p>${orderBoost.lolPassword}</p>
        <p>${orderBoost.noteToBoosters}</p>
    </body>
</html>

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
                <a href="${pageContext.request.contextPath}/order/">Order page</a>
            </li>
        </ul>
    </nav>
        <h1>Single Conversation</h1>

    <div id="test1"></div>
        <script>
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        console.log(conversation);
                        for (var i = 0; i < conversation.length; i++){
                            var singleMessage = document.createElement("div");
                            singleMessage.innerHTML = conversation[i]['message'];
                            singleMessage.setAttribute("class","message");
                            singleMessage.setAttribute("id","message"+i);
                            var initialDiv = document.getElementById('test1');
                            document.body.insertBefore(singleMessage,initialDiv);
                            if(conversation[i]['messageSender']===4){
                                document.getElementById("message"+i).style.textAlign = "right";
                            }
                            else {
                                document.getElementById("message"+i).style.textAlign = "left";
                            }
                         //   document.getElementById('test1').innerHTML = new Date(res[20]['date']); //.getHours();
                        }
                        //document.getElementById('test1').remove();
                    }
                }
                xhr.open('GET','./Message.json',true);
                xhr.send();
        </script>
    </body>
</html>
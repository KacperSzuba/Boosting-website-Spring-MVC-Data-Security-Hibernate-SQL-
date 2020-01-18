<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kacpe
  Date: 16.01.2020
  Time: 17:09
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
                <security:authorize access="hasRole('ADMIN')">
                    <li>
                        <a href="${pageContext.request.contextPath}/admin">Admin page</a>
                    </li>
                </security:authorize>
                <c:if test="${empty pageContext.request.userPrincipal}">
                    <li>
                        <a href="${pageContext.request.contextPath}/login">Login Page</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/register">Register Page</a>
                    </li>
                </c:if>
                <li>
                    <a href="${pageContext.request.contextPath}/order">Order page</a>
                </li>
            </ul>
        </nav>
        <button onclick="upCurrentTier()">Increase current tier</button>
        <button onclick="downCurrentTier()">Decrease current tier</button>
        <button onclick="leftCurrentTier()">Decrease current division</button>
        <button onclick="rightCurrentTier()">Increase current division</button>
        <img class="center" id="currentTier">

        <button onclick="upDestinationTier()">Increase destination tier</button>
        <button onclick="downDestinationTier()">Decrease destination tier</button>
        <button onclick="leftDestinationTier()">Decrease destination division</button>
        <button onclick="rightDestinationTier()">Increase destination division</button>
        <img class="center" id="destinationTier">

        <a href="${pageContext.request.contextPath}/NewOrder/informationAboutDivision" onclick="writeDestinationTierAndDivisionJSONFile()">Place an order</a>
        <script>
            const xhr = new XMLHttpRequest();
            xhr.onload = function () {
                if(this.status === 200){
                    temp = JSON.parse(this.responseText);
                    const conversation = JSON.parse(this.responseText);
                    var defaultCurrentTier = "${pageContext.request.contextPath}"+conversation[21]['img_source'];
                    var defaultDestinationTier = "${pageContext.request.contextPath}"+conversation[17]['img_source'];
                    document.getElementById('currentTier').setAttribute("src",defaultCurrentTier);
                    document.getElementById('destinationTier').setAttribute("src",defaultDestinationTier);
                }
            }
            xhr.open('GET','./Order.json',true);
            xhr.send();
        </script>

        <script>
            function upCurrentTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('currentTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var tier = parseInt(op[0]['tier_id']);
                        if(tier === 1){
                            let temp2 = conversation.filter(data => data.tier_id === 6 && data.division_id === parseInt(op[0]['division_id']));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']-1) && data.division_id == parseInt(op[0]['division_id']));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }

                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }
            function upDestinationTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('destinationTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var tier = parseInt(op[0]['tier_id']);
                        if(tier === 1){
                            let temp2 = conversation.filter(data => data.tier_id === 6 && data.division_id === parseInt(op[0]['division_id']));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']-1) && data.division_id == parseInt(op[0]['division_id']));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function downCurrentTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('currentTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var tier = parseInt(op[0]['tier_id']);
                        if(tier === 6){
                            let temp2 = conversation.filter(data => data.tier_id === 1 && data.division_id === parseInt(op[0]['division_id']));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']+1) && data.division_id == parseInt(op[0]['division_id']));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function downDestinationTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('destinationTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var tier = parseInt(op[0]['tier_id']);
                        if(tier === 6){
                            let temp2 = conversation.filter(data => data.tier_id === 1 && data.division_id === parseInt(op[0]['division_id']));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']+1) && data.division_id == parseInt(op[0]['division_id']));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function leftCurrentTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('currentTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var division = parseInt(op[0]['division_id']);
                        if(division ===4 && parseInt(op[0]['tier_id'])===6 ){
                            let temp2 = conversation.filter(data => data.tier_id == 1 && data.division_id == 1);
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else if(division === 4){
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']+1) && data.division_id == 1);
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }

                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']) && data.division_id == parseInt(op[0]['division_id']+1));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function leftDestinationTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('destinationTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var division = parseInt(op[0]['division_id']);
                        if(division ===4 && parseInt(op[0]['tier_id'])===6 ){
                            let temp2 = conversation.filter(data => data.tier_id == 1 && data.division_id == 1);
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else if(division === 4){
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']+1) && data.division_id == 1);
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }

                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']) && data.division_id == parseInt(op[0]['division_id']+1));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function rightCurrentTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('currentTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var division = parseInt(op[0]['division_id']);
                        if(division ===1 && parseInt(op[0]['tier_id'])===1 ){
                            let temp2 = conversation.filter(data => data.tier_id == 6 && data.division_id == 4);
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else if(division === 1){
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']-1) && data.division_id == 4);
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }

                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']) && data.division_id == parseInt(op[0]['division_id']-1));
                            document.getElementById('currentTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }

            function rightDestinationTier() {
                const xhr = new XMLHttpRequest();
                xhr.onload = function () {
                    if(this.status === 200){
                        const conversation = JSON.parse(this.responseText);
                        var temp = document.getElementById('destinationTier').src.toString();
                        let op = conversation.filter(data => "http://localhost:8080/SpringSecurityUserDetailsService_war_exploded"+data.img_source == temp);
                        var division = parseInt(op[0]['division_id']);
                        if(division ===1 && parseInt(op[0]['tier_id'])===1 ){
                            let temp2 = conversation.filter(data => data.tier_id == 6 && data.division_id == 4);
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                        else if(division === 1){
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']-1) && data.division_id == 4);
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }

                        else {
                            let temp2 = conversation.filter(data => data.tier_id == parseInt(op[0]['tier_id']) && data.division_id == parseInt(op[0]['division_id']-1));
                            document.getElementById('destinationTier').setAttribute("src","${pageContext.request.contextPath}"+temp2[0]['img_source']);
                        }
                    }
                }
                xhr.open('GET','./Order.json',true);
                xhr.send();
            }
        </script>

        <script>
            function writeDestinationTierAndDivisionJSONFile() {
                var d = new Date();
                d.setTime(d.getTime()+(24 * 60 *60*1000));
                var expires = "expires="+d.toUTCString();
                var currentTierImageSource = document.getElementById('currentTier').src.toString().replace("http://localhost:8080/SpringSecurityUserDetailsService_war_exploded","");
                document.cookie = "currentTierImageSource ="+ currentTierImageSource+";"+expires+";";
                var destinationTierImageSource = document.getElementById('destinationTier').src.toString().replace("http://localhost:8080/SpringSecurityUserDetailsService_war_exploded","");
                document.cookie = "destinationTierImageSource ="+ destinationTierImageSource+";"+expires+";";
            }
        </script>
    </body>
</html>

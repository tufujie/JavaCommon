<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basic JSP</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/activemq/activemq.js"></script>
</head>
<body>
<form id="objectPost" action="" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input id="userName" name="name" value="Jef"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" id="userPassword" name="password" value="123456"></td>
        </tr>
        <tr>
            <td>手机号</td>
            <td><input id="phone" name="phone" value="18390220001"></td>
        </tr>

        <tr>
            <td><input type="button" value="sendActiveMQMessage" onclick="sendActiveMQMessage(1)"></td>
        </tr>
    </table>
</form>
</body>
</html>

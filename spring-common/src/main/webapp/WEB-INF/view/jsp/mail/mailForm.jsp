<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testPost</title>
    <%-- 动态include指令引入页面 --%>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/mail/mail.js"></script>
</head>
<body>
<form id="" action="" method="post">
    <table>
        <tr>
            <td>发送者邮箱</td>
            <td><input id="from" name="from" value="jef.tu@foxmail.com" readonly></td>
        </tr>
        <tr>
            <td>接受者邮箱</td>
            <td><input type="to" id="to" name="to"></td>
        </tr>
        <tr>
            <td>主题</td>
            <td><input id="title" name="title"></td>
        </tr>
        <tr>
            <td>内容</td>
            <td><input id="content" name="content"></td>
        </tr>

        <tr>
            <td><input type="button" value="mail" onclick="mail(1)"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</body>
</html>

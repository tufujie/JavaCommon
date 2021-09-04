<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>register</title>
    <%-- 动态include指令引入页面 --%>
    <jsp:include page="basic.jsp" />
</head>
<body>
<form id="form_register" action="/user/register1" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input id="userName" name="name"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" id="userPassword" name="password" onblur="md5Encode()"></td>
        </tr>
        <tr>
            <td>重复密码</td>
            <td><input type="password" id="repeatUserPassword" name="repeatUserPassword" onblur="md5Encode()"></td>
        </tr>
        <tr>
            <td>手机号</td>
            <td><input id="phone" name="phone"></td>
        </tr>
        <tr>
            <td>年龄</td>
            <td><input id="age" name="age"></td>
        </tr>
        <tr>
            <td>验证码</td>
            <td>
                <input type="text" class="input input-big" name="verifyCode" placeholder="填写右侧的验证码" data-validate="required:请填写右侧的验证码" />
                <img src="<%=path%>/verifyCodeServlet" alt="" width="100" height="32" class="passcode" style="height:43px;cursor:pointer;" onclick="this.src=this.src+'?'">
            </td>
        </tr>
        <tr>
            <td><input type="button" value="注册" onclick="register()"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
    <font color="red">${requestScope.registerErrorMessage}</font>
</form>
</body>
</html>

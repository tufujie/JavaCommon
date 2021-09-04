<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%-- 引入别的页面的静态文件，这样重复代码少，代码整洁 --%>
<%-- 上面是JSP的注释方式，JSP注释 --%>
<html>
<head>
    <title>login page</title>
</head>
<body>
<jsp:include page="basic.jsp" />
<h1 class="text-center">Welcome to Jef's small world</h1>
<%--<a href="/user/gotoLogin" target="_blank">登录</a>，--%>
<span class="text-center">没有账号<a href="/user/gotoRegister" target="_blank">点击注册</a></span><br>
<%--<form id="form_login_two" action="/login/loginTwo" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input id="name" name="name"></td>
        </tr>
        <tr>
            <td>密码</td>
            <input type="hidden" id="pwd" name="pwd">
            <td><input type="password" id="password" name="password" onblur="md5Encode()"></td>
        </tr>
        <tr>
            <td><input type="button" value="登录" onclick="login()"></td>
            <td><input type="reset" value="重置"></td>
            <td><input type="button" value="一键登录" onclick="oneKeyLogin()"></td>
        </tr>
    </table>
    <font color="red">${requestScope.message}</font>
</form>--%>
<form class="form col-md-12 center-block" id="form_login_two" action="/login/loginTwo" method="post">
    <div class="form-group-lg" id="nameDiv">
        <label class="sr-only" for="name">用户名</label>
        <div class="input-group">
            <div class="input-group-addon"><span class="glyphicon glyphicon-user" aria-hidden="true"></span></div>
            <input class="form-control" id="name" name="name" type="text" placeholder="账号" required autofocus>
        </div>
        <div class="hidden text-center text-danger" id="nameNontInput">请输入用户名</div>
    </div>
    <br>
    <div class="form-group-lg" id="pwdDiv">
        <label class="sr-only" for="password">密码</label>
        <div class="input-group">
            <div class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></div>
            <input class="form-control" id="password" name="password" type="password" placeholder="密码" required>
        </div>
        <div class="hidden text-center text-danger" id="passwordNontInput">请输入密码</div>
    </div>
    <div class="hidden text-center text-danger" id="nameOrPasswordError">用户名或密码错误</div>
    <div class="checkbox">
        <label> <input type="checkbox" value="rememberMe">记住我</label>
    </div>
    <div class="form-group">
        <button class="btn btn-default btn-lg col-md-6" type="button" onclick="login()">登录</button>
        <button class="btn btn-success btn-lg col-md-6" type="button" onclick="oneKeyLogin()">一键登录</button>
    </div>
</form>
<%--<form id="form_login_one" action="/login/loginOne" method="post">
    <table>
        <tr>
            <td><label>登录名</label></td>
            <td><input type="text" id="loginName" name="name"></td>
        </tr>
        <tr>
            <td><label>密码</label></td>
            <td><input type="password" id="loginPassword" name="password"></td>
        </tr>
        <tr>
            <td><input type="submit" value="登录" /></td>
        </tr>
    </table>
    <font color="red">${requestScope.message}</font>
</form>--%>
<script>
    $(document).ready(function() {
        console.log("JS已引入")
    });
</script>
</body>
</html>

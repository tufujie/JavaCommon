<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mongo demo</title>
    <jsp:include page="../basic.jsp" />
</head>
<body>
<form id="objectPost" action="" method="post">
    <table>
        <tr>
            <td>id</td>
            <td><input id="id" name="id" value="1"></td>
        </tr>
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
            <td>年龄</td>
            <td><input id="age" name="age" value="25"></td>
        </tr>
        <tr>
            <td><input type="button" value="addMongoVo" onclick="addMongoVo()"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
<a href="/mongo/getVo" target="_blank">getVo</a><br>
<a href="/mongo/getVoList" target="_blank">getVoList</a><br>
<script>
    function addMongoVo() {
        var id = $.trim($( "#id" ).val());
        var name = $.trim($( "#userName" ).val());
        var password = $.trim($( "#userPassword" ).val());
        var phone = $.trim($( "#phone" ).val());
        var age = $.trim($( "#age" ).val());
        var user = {};
        user.id = id;
        user.name = name;
        user.password = password;
        user.phone = phone;
        user.age = age;
        url = "/mongo/addVo";
        var json_user = JSON.stringify(user);
        $.ajax({
            type:"post",
            url: url,
            contentType:"application/json;charset=utf-8",
            data: json_user ,
            success:function(data){
                console.log("here");
            },
            error:function(){
                alert("出错啦");
            }
        });
    }
</script>
</body>
</html>

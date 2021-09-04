/**
 * 发送activemq消息
 */
function sendActiveMQMessage() {
    var name = $.trim($( "#userName" ).val()),
        password = $.trim($( "#userPassword" ).val()),
        phone = $.trim($( "#phone" ).val()),
        url = "/activemq/sendMessageOne";
    if (name == "") {
        alert( "请输入用户名" );
        return false;
    }
    if (password == "") {
        alert( "请输入密码" );
        return false;
    }
    if (phone == "") {
        alert( "请输入手机号" );
        return false;
    }

    var user = {};
    user.name = name;
    user.password = password;
    user.phone = phone;
    $.post(url,
        user,
        function(result) {
            console.log("here");
        }
    );
}

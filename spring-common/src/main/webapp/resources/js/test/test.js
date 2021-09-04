function Post(type) {
    var name = $.trim($( "#userName" ).val());
    var password = $.trim($( "#userPassword" ).val());
    var phone = $.trim($( "#phone" ).val());
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

    var url;
    if (type == 1) {
        url = "/myTest/sayHello";
        $.post(url,
            {
                hello: "world",
                name: name,
                password: password,
                phone: phone,
                num: 10
            },
            function(result) {
                console.log("here");
            }
        );
    }


}
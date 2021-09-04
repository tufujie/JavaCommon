/**
 * 登录请求
 */
function login() {
    var name = $.trim($( "#name" ).val()),
        password = $.trim($( "#password" ).val()),
        $nameNontInput = $('#nameNontInput'),
        $passwordNontInput = $('#passwordNontInput'),
        $nameOrPasswordError = $('#nameOrPasswordError');
    $nameNontInput.addClass('hidden');
    $passwordNontInput.addClass('hidden');
    $nameOrPasswordError.addClass('hidden');
    if (!name) {
        $nameNontInput.removeClass('hidden');
        return false;
    }
    if (!password) {
        $passwordNontInput.removeClass('hidden');
        return false;
    }
    // 表单提交
    $.post('/login/loginValidate',
        {
            name: name,
            password: password
        },

        function(data, status) {
            if (data.data == null) {
                $nameOrPasswordError.removeClass('hidden');
            } else {
                $("#form_login_two").submit();
            }
        }
    );
}

/**
 * MD5加密，现在使用后台代码进行加密，暂时不用这个
 */
function md5Encode() {
    var password = $( "#password" ).val();
    var pwd = $.md5(password);
    $( "#pwd" ).val(pwd);
    // 这里前端加密和后端加密不一样，统一用其中一种，暂时使用后端加密
    console.log("前端密码加密的结果=" + pwd);
}

/**
 * 注册请求
 */
function register() {
    var name = $.trim($( "#userName" ).val());
    var password = $.trim($( "#userPassword" ).val());
    var repeatUserPassword = $.trim($( "#repeatUserPassword" ).val());
    var phone = $.trim($( "#phone" ).val());
    var age = $.trim($( "#age" ).val());
    var verifyCode = $.trim($( "input[name='verifyCode']" ).val());
    if (name == "") {
        alert( "请输入用户名" );
        return false;
    }
    if (password == "") {
        alert( "请输入密码" );
        return false;
    }
    if (repeatUserPassword == "") {
        alert( "请输入重复密码" );
        return false;
    }
    if (password !== repeatUserPassword) {
        alert( "输入密码不一致" );
        return false;
    }
    if (phone == "") {
        alert( "请输入手机号" );
        return false;
    }
    if (age == "") {
        alert( "请输入年龄" );
        return false;
    }
    if (verifyCode == "") {
        alert( "请输入验证码" );
        return false;
    }
    // 表单提交
    $("#form_register").submit();

}

/**
 * Jef一键登录
 */
function oneKeyLogin() {
    $( "#name" ).val('Jef');
    $( "#password" ).val('******');
    login();
}

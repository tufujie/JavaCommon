/**
 * 邮件请求
 */
function mail(type) {
    var from = $.trim($( "#from" ).val());
    var to = $.trim($( "#to" ).val());
    var title = $.trim($( "#title" ).val());
    var content = $.trim($( "#content" ).val());
    if (from == "") {
        alert( "请输入发送者邮箱" );
        return false;
    }
    if (to == "") {
        alert( "请输入接受者邮箱" );
        return false;
    }
    if (title == "") {
        alert( "请输入主题" );
        return false;
    }
    if (content == "") {
        alert( "请输入内容" );
        return false;
    }

    var url;
    if (type == 1) {
        url = "/mail/sendMailToOnePerson";
        var mail = {};
        mail.from = from;
        mail.to = to;
        mail.title = title;
        mail.content = content;
        $.post(url,
            mail,
            function(result) {
                alert(result);
            }
        );
    };

}
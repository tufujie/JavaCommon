/**
 * 单个添加
 */
function addInfo() {
    var fuDate = $.trim($( "#fuDate" ).val()),
        red1 = $.trim($( "#red1" ).val()),
        red2 = $.trim($( "#red2" ).val()),
        red3 = $.trim($( "#red3" ).val()),
        red4 = $.trim($( "#red4" ).val()),
        red5 = $.trim($( "#red5" ).val()),
        red6 = $.trim($( "#red6" ).val()),
        blue = $.trim($( "#blue" ).val()),
        url = "/fuCai/addInfo";
    if (fuDate == "") {
        alert( "请输入福彩期号" );
        return false;
    } else if (fuDate.length < 7 || fuDate.length > 8) {
        alert( "请正确输入福彩期号" );
        return false;
    }
    if (red1 == "" || red2 == "" || red3 == ""  || red4 == "" || red5 == "" || red6 == "") {
        alert( "请输入完整的六个红球号码" );
        return false;
    }
    if (blue == "") {
        alert( "请输入蓝球号码" );
        return false;
    }

    var fuCai = {};
    fuCai.fuDate = fuDate;
    fuCai.red1 = red1;
    fuCai.red2 = red2;
    fuCai.red3 = red3;
    fuCai.red4 = red4;
    fuCai.red5 = red5;
    fuCai.red6 = red6;
    fuCai.blue = blue;
    $.post(url,
        fuCai,
        function(result) {
            alert("操作成功");
            console.log("here");
        }
    );
}

/**
 * 批量插入
 */
function batchAddInfo() {
    url = "/fuCai/addInfo";
    $('table tr').each(function() {
        var $tr = $(this), tdArr = $tr.find('td'), $tdArr = $(tdArr);
        var firstTdText = $($tdArr[0]).text();
        if (firstTdText == '期号' || firstTdText == '红球') {
            return true;
        }
        var fuCai = {};
        fuCai.fuDate = firstTdText;
        var redTdSpanArr = $($tdArr[2]).find("span");
        for (var i = 0; i <= redTdSpanArr.length; i++) {
            var redText = $(redTdSpanArr[i]).text(), tempI = i + 1;
            if (tempI == 1) {
                fuCai.red1 = redText;
            } else if (tempI == 2) {
                fuCai.red2 = redText;
            } else if (tempI == 3) {
                fuCai.red3 = redText;
            } else if (tempI == 4) {
                fuCai.red4 = redText;
            } else if (tempI == 5) {
                fuCai.red5 = redText;
            } else if (tempI == 6) {
                fuCai.red6 = redText;
            }
        }
        fuCai.blue = $($tdArr[3]).text();
        $.post(url,
            fuCai,
            function(result) {
                console.log("操作成功");
            }
        );
    });
}

/**
 * 修改变更次数为0，即未修改
 * @param e
 */
function changeEditNumZero(e) {
    var $this = $(e);
    var $tr = $this.closest("tr");
    var fuCai = {};
    fuCai.id = $($tr.find('td')[0]).text();
    $.post("/fuCai/changeEditNumZero",
        fuCai,
        function(result) {
            location.reload();
            console.log("操作成功");
        }
    );
}

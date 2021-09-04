/**
 * 单个添加
 */
function addInfo() {
    var code = $.trim($( "#code" ).val()),
        name = $.trim($( "#name" ).val()),
        url = "/foundation/addInfo";
    if (code == "") {
        alert( "请输入基金编码" );
        return false;
    }
    if (name == "") {
        alert( "请输入基金名称" );
        return false;
    }

    var foundation = {};
    foundation.name = name;
    foundation.code = code;
    $.post(url,
        foundation,
        function(result) {
            alert("操作成功");
            location.reload();
            console.log("here");
        }
    );
}

/**
 * 添加分录
 */
function addInfoEntry() {
        var unitPrice = $.trim($( "#unitPrice" ).val()),
        url = "/foundation/addInfoEntry";
    if (unitPrice == "") {
        alert( "请输入单位净值" );
        return false;
    }
    var foundationEntry = {};
    foundationEntry.code = code;
    foundationEntry.createDate = getCreateDate();
    foundationEntry.unitPrice = unitPrice;
    $.post(url,
        foundationEntry,
        function(result) {
            alert("操作成功");
            // location.reload();
            console.log("here");
        }
    );
}

/**
 * 批量插入
 */
function batchAddInfo() {
    var url = "/foundation/addInfoEntry";
    $('table tbody tr').each(function() {
        var $tr = $(this), $td = $tr.find('td');
        var foundationEntry = {};
        foundationEntry.code = code;
        foundationEntry.createDate = $td[0].textContent;
        foundationEntry.unitPrice = $td[1].textContent;
        $.post(url,
            foundationEntry,
            function(result) {
            }
        );
    });
    alert("操作成功");
}

/**
 * 买入
 */
function buy() {
    var money = $.trim($( "#money" ).val()), buyRatePrice = $.trim($( "#ratePrice" ).val());
    if (money == "") {
        alert( "请输入买入金额" );
        return false;
    }
    if (buyRatePrice == "") {
        alert( "请输入手续费" );
        return false;
    }

    var foundationBuy = {};
    foundationBuy.code = code;
    foundationBuy.buyDate = getCreateDate();
    foundationBuy.money = money;
    foundationBuy.buyRatePrice = buyRatePrice;
    $.post("/foundation/buy",
        foundationBuy,
        function(result) {
            alert("买入成功");
            location.reload();
            console.log("here");
        }
    );
}

/**
 * 买入
 */
function shop() {
    var shopRatePrice = $.trim($( "#ratePrice" ).val()), linkBuyDate = $.trim($( "#linkBuyDate" ).val());
    if (shopRatePrice == "") {
        alert( "请输入手续费" );
        return false;
    }
    if (linkBuyDate == "") {
        alert( "请输入卖出关联的买入日期" );
        return false;
    }

    var foundationShop = {};
    foundationShop.code = code;
    foundationShop.shopDate = getCreateDate();
    foundationShop.shopRatePrice = shopRatePrice;
    foundationShop.linkBuyDate = linkBuyDate;
    $.post("/foundation/shop",
        foundationShop,
        function(result) {
            alert("卖出成功");
            location.reload();
            console.log("here");
        }
    );
}

function getCreateDate() {
    var year = $.trim($("select[name='YYYY']").val()),
        month = $.trim($("select[name='MM']").val()),
        day = $.trim($("select[name='DD']").val());
    if (year == "") {
        alert( "请选择年份" );
        return false;
    }
    if (month == "") {
        alert( "请选择月份" );
        return false;
    }
    if (month < 10) {
        month = "0" + month;
    }
    if (day == "") {
        alert( "请选择日" );
        return false;
    }
    if (day < 10) {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day;
}




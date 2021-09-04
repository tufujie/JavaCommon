$(document).ready(function() {
    console.log("JS已引入")
});
function Post(type) {
    var allData = getAllData();
    var url = $.trim($("#fUrl").val());
    if (type == 7) {
        url = '/rentContract/getMoneyDetailOfXB';
    } else if (type == 8) {
        url = '/rentContract/rentContractSaveOfXB';
    } else if (type == 9) {
        url = '/rentContract/rentContractPrintLoadOfXB';
    }
    $('#returnResult').text('');
    if (type == 2) {
        $.ajax(url, {
            data: allData,
            dataType: 'jsonp',
            crossDomain: true,
            success: function (data) {
                console.log("任务执行完毕");
                alert("任务执行完毕");
            }
        });
    } else if (type == 1) {
        RE.AJAX.getJSON({

            url: url,
            type: RE.HTTP.POST,
            params: allData,
            isAsync: true,
            onSuccess: function (data) {
                var resultData = JSON.stringify(data);
                $('#returnResult').text(resultData);
                console.log("任务执行完毕");
                alert("任务执行完毕");
            },
            onFailure: function (msg, data) {
                new subox(msg, {
                    type: 'error',
                    timeout: 5000
                });
            },
            onComplete: function () {

            }
        })
    } else if (type == 3) {
        url = "/rentGoods/saveDeliveryGoods";
        // 多实体post请求
        var fId = $.trim($("#fId").val());
        var deliveryGoods = {id: fId, name: "交付物品清单名称", description: "交付物品清单备注"},
            deliveryGoodsEntry = [];
        // 物品勾选至少一个
        var deliveryGoodsEntryItem = {
            goodsID: 'c6b0289386b743648f46c4213c87f871',
            num: 2
        };
        var deliveryGoodsEntryItem2 = {
            goodsID: 'a5a22493f05f471e949918487a23bf7f',
            num: 3
        };
        deliveryGoodsEntry.push(deliveryGoodsEntryItem);
        deliveryGoodsEntry.push(deliveryGoodsEntryItem2);
        var allData = {
            deliveryGoods: deliveryGoods,
            deliveryGoodsEntry: deliveryGoodsEntry
        };

        RE.AJAX.getJSON({
            url: url,
            type: RE.HTTP.POST,
            params: {
                allData: JSON.stringify(allData)
            },
            isAsync: true,
            onSuccess: function (data) {
                console.log("任务执行完毕");
                console.log(data);
                $('#returnResult').text(data);
                alert("任务执行完毕");
            },
            onFailure: function (msg, data) {
                new subox(msg, {
                    type: 'error',
                    timeout: 5000
                });
            },
            onComplete: function () {

            }
        })
    } else if (type == 4) {
        // 多实体post请求
        var fId = $.trim($("#fId").val());
        var allData = {}, firstDate = "2018-01-01", contractType = 1, number = "合同编码", name = "合同名称",
            partAName = "甲方公司名称", cusType = "P", partBName = "乙方名称", phone = "123456789", memName = "联系人姓名",
            memPhone = "联系人电话",
            consultantName = "招商顾问名称", category = "经营类别", rentPurpose = "租赁用途", realPurpose = "实际用途",
            commercialForm = "业态", isSelfLive = 0,
            brand = "品牌", manager = "涂富杰", additionRemark = "申请事由", signDate = firstDate, handerDate = firstDate,
            startDate = firstDate,
            endDate = "2018-12-31", feeType = 2, circleType = 2, firstPayType = 1, firstPayDate = firstDate,
            firstStartDate = firstDate, firstEndDate = "2018-01-31",
            rentType = 1, rentDays = 1;

        test = "123";

        RE.AJAX.getJSON({
            url: url,
            type: RE.HTTP.POST,
            params: {
                allData: JSON.stringify(allData)
            },
            isAsync: true,
            onSuccess: function (data) {
                console.log("任务执行完毕");
                console.log(data);
                $('#returnResult').text(data);
                alert("任务执行完毕");
            },
            onFailure: function (msg, data) {
                new subox(msg, {
                    type: 'error',
                    timeout: 5000
                });
            },
            onComplete: function () {

            }
        })
    } else if (type == 5) {
        var params = {allData: JSON.stringify(allData)};
        postExcelFile(params, url);
    } else if (type == 6) {


    }
}


    function getAllData() {
        var chinese = $.trim($("#fChinese").val());
        var fId = $.trim($("#fId").val());
        var idKey = $.trim($("#fIdKey").val());
        var idValue = $.trim($("#fIdValue").val());
        var id2Key = $.trim($("#fId2Key").val());
        var id2Value = $.trim($("#fId2Value").val());
        var id3Key = $.trim($("#fId3Key").val());
        var id3Value = $.trim($("#fId3Value").val());
        var id4Key = $.trim($("#fId4Key").val());
        var id4Value = $.trim($("#fId4Value").val());
        var id5Key = $.trim($("#fId5Key").val());
        var id5Value = $.trim($("#fId5Value").val());
        var chinese = encodeURIComponent(chinese);
        var fBusinessType = $("#fBusinessType").val();
        var allData = {};
        allData.chinese = chinese;
        allData.id = fId;
        allData[idKey] = idValue;
        allData[id2Key] = id2Value;
        allData[id3Key] = id3Value;
        allData[id4Key] = id4Value;
        allData[id5Key] = id5Value;
        allData["fBusinessType"] = fBusinessType;
        return allData;
    }

    function postExcelFile(params, url) { //params是post请求需要的参数，url是请求url地址
        var form = document.createElement("form");
        form.style.display = 'none';
        form.action = url;
        form.method = "post";
        document.body.appendChild(form);

        for(var key in params){
            var input = document.createElement("input");
            input.type = "hidden";
            input.name = key;
            input.value = params[key];
            form.appendChild(input);
        }

        form.submit();
        form.remove();
    }
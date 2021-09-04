/**
 * 注册请求
 */
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
        url = "/postAll/postOne";
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
    } else if (type == 2) {
        url = "/postAll/postTwo";
        $.post(url,
            {
                name: name,
                password: password,
                phone: phone
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 3) {
        var orderInfos = getOrderInfoArray(), ids = getIds(),
        json_orderInfos = JSON.stringify(orderInfos);
        url = "/postAll/postThree";
        $.post(url,
            {
                name: name,
                password: password,
                phone: phone,
                ids: ids,
                orderInfos: json_orderInfos
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 4) {
        url = "/postAll/postFour";
        // 存放集合
        var orderInfos = getOrderInfoArray();
        // 对象
        var user = {
            name: name,
            password: password,
            phone: phone
        };
        var allData = {
            user: user,
            orderInfos : orderInfos
        };
        $.post(url,
            {
                allData: JSON.stringify(allData)
            },
            function(result) {
                console.log("here");
            }
        );
    }  else if (type == 5) {
        var orderInfos = getOrderInfoArray();
        url = "/postAll/postOneMore";
        var json_orderInfos = JSON.stringify(orderInfos);
        $.ajax({
            type:"post",
            url: url,
            contentType:"application/json;charset=utf-8",
            data: json_orderInfos ,
            success:function(data){
                console.log("here");
            },
            error:function(){
                alert("出错啦");
            }
        });
    } else if (type == 6) {
        var user = {};
        user.name = name;
        user.password = password;
        user.phone = phone;
        url = "/postAll/postOneMoreJSON";
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
    } else if (type == 7) {
        var orderInfos = getOrderInfoArray();
        var user = {
            name: name,
            password: password,
            phone: phone
        };
        url = "/postAll/postFive";
        $.post(url,
            {
                "user" : JSON.stringify(user),
                "orderInfos" : JSON.stringify(orderInfos)
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 8) {
        var user = {
            name: name,
            password: password,
            phone: phone
        };
        url = "/postAll/postSix";
        $.post(url,
            user,
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 9) {
        var orderInfos = getOrderInfoArray();
        url = "/postAll/postSeven";
        $.post(url,
            orderInfos,
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 10) {
        var searchParams = {
            "cityID": "test1",
            "areaID": "test2"
        };
        url = '/postAll/postGetParams';
        $.post(url,
            {
                searchParams: encodeURIComponent(JSON.stringify(searchParams))
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 11) {
        var searchParams = {
            "cityID": "test1",
            "areaID": "test2"
        };
        url = '/postAll/postGetParams';
        $.post(url,
            {
                searchParams: JSON.stringify(searchParams)
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 12) {
        url = "/postAll/postTwoExtend";
        $.post(url,
            {
                name: name,
                password: password,
                phone: phone
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 13) {
        url = "/postAll/postTwoExtendTwo";
        $.post(url,
            {
                name: name,
                password: password,
                phone: phone
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 14) {
        var user = {};
        user.name = name;
        user.password = password;
        user.phone = phone;
        url = "/postAll/postOneMoreJSONBody";
        var json_user = JSON.stringify(user);
        $.ajax({
            type:"post",
            url: url,
            contentType:"application/json;charset=utf-8",
            data: json_user,
            success:function(data){
                console.log("here");
            },
            error:function(){
                alert("出错啦");
            }
        });
    } else if (type == 15) {
        var jsonUser = $.trim($( "#jsonUser" ).val());
        url = "/postAll/postEight";
        $.post(url,
            {
                user: jsonUser
            },
            function(result) {
                console.log("here");
            }
        );
    }


}


/**
 * ids请求
 */
function postIds(type, more) {
    var url;
    if (type == 1) {
        url = "/postAll/postIds";
        if (more == 'one') {
            url = "/postAll/postIdsOne";
        } else if (more == 'more') {
            url = "/postAll/postIdsOneMore";
        }
        var ids = getIds();
        $.post(url,
            {
                ids : ids.join()
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 2) {
        url = "/postAll/postIdsTwo";
        var ids = getIds();
        $.post(url,
            {
                ids : ids
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 3) {
        url = "/postAll/postIdsThree";
        var ids = getIds();
        $.post(url,
            {
                ids : ids
            },
            function(result) {
                console.log("here");
            }
        );
    } else if (type == 4) {
        url = "/postAll/postIdsFour";
        var ids = getIds();
        var json_ids = JSON.stringify(ids);
        $.ajax({
            type:"post",
            url: url,
            contentType:"application/json;charset=utf-8",
            data: json_ids ,
            success:function(data){
                console.log("here");
            },
            error:function(){
                alert("出错啦");
            }
        });
    }
}

/**
 * 模拟获取Object数组
 * 这里特定获取订单
 * @returns {Array}
 */
function getOrderInfoArray() {
    var orderInfos = [];
    var orderInfoOne = {
        extraOrderId: "10001"
    };
    var orderInfoTwo = {
        extraOrderId: "10002"
    };
    orderInfos.push(orderInfoOne, orderInfoTwo);
    return orderInfos;
}

/**
 * 模拟获取单一类型数组
 * 这里特定获取输入ID
 * @returns {Array}
 */
function getIds() {
    var idFirst = $.trim($( "#id_first" ).val());
    var idSecond = $.trim($( "#id_second" ).val());
    if (idFirst == "") {
        alert( "请输入第一个id" );
        return;
    }
    if (idSecond == "") {
        alert( "请输入第二个id" );
        return;
    }
    var ids = new Array();
    ids.push(idFirst, idSecond);
    return ids;
}
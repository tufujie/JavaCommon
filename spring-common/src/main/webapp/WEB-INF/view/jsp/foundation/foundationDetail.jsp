<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>基金详情</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/foundation/foundation.js"></script>
    <style>
        table {
            margin:0 auto;
            text-align: center;
        }
    </style>
    <script>
        var code = '${foundation.code}';
    </script>
</head>
<body>
<h1 align="center" style="color: blue;">${foundation.name}</h1>
<h3 align="center" style="color: blue;">${foundation.code}</h3>
<a href="/foundation/getFoundationShop?code=${foundation.code}" target="_blank" style="margin-left: 95%">卖出记录</a>
<br>
<a href="http://fundf10.eastmoney.com/jjjz_${foundation.code}.html" target="_blank">净值获取网站</a>
<br>
<a href="/foundation/batchAdd?code=${foundation.code}" target="_blank">批量插入</a><br>
<form name="reg_testdate">
    <select name="YYYY" onChange="YYYYDD(this.value)">
        <option value="">请选择 年</option>
    </select>
    <select name="MM" onChange="MMDD(this.value)">
        <option value="">选择 月</option>
    </select>
    <select name="DD" onChange="DDD(this.value)">
        <option value="">选择 日</option>
    </select>
    净值<input id="unitPrice" name="unitPrice" value="">
    买入金额<input id="money" name="money" value="">
    手续费<input id="ratePrice" name="ratePrice" value="">
    卖出关联买入日期<input id="linkBuyDate" name="linkBuyDate" value="">
    <input type="button" value="添加净值" onclick="addInfoEntry(1)">
    <input type="button" value="买" onclick="buy(1)">
    <input type="button" value="卖" onclick="shop(1)">
</form>
<c:choose>
    <c:when  test="${newest}">
        <h1 align="center"><span style="color: green">数据已是最新</span></h1>
    </c:when>
    <c:otherwise>
        <h1 align="center"><span style="color: red">请更新最新数据</span></h1>
    </c:otherwise>
</c:choose>
<h1 align="center" style="color: blue;">分析处理了${day}天,即${month}个月的数据<a href="/foundation/getFoundationDetail?code=${foundation.code}&day=22" target="_blank">1个月</a>
    <a href="/foundation/getFoundationDetail?code=${foundation.code}&day=44" target="_blank">2个月</a>
    <a href="/foundation/getFoundationDetail?code=${foundation.code}&day=66" target="_blank">3个月</a></h1>
<c:choose>
    <c:when  test="${buyFlag}">
        <h1 align="center"><span style="color: blue">建议买入</span><span>，最新净值比净值平均值低了${downNum}</span></h1>
    </c:when>
    <c:otherwise>
        <h1 align="center"><span style="color: red">不建议买入</span><span>，最新净值比平均净值高了${downNum}</span></h1>
    </c:otherwise>
</c:choose>
<h1 align="center" style="color: blue;">基金平均净值=${betweenPrice}</h1>
<h1 align="center">基金详情</h1>
<table bgcolor="#faebd7" border="1" width="300px">
    <th>日期</th>
    <th>净值</th>
    <c:forEach var="item" items="${foundationEntryList}">
        <tr>
            <td>${item.createDate}</td>
            <td>${item.unitPrice}</td>
        </tr>
    </c:forEach>
</table>
<br>

<script language="JavaScript">
    var changeDD = 1;//->一个全局变量
    function YYYYMMDDstart() {
        MonHead = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        //先给年下拉框赋内容
        var y = new Date().getFullYear();
        for (var i = (y - 47); i < (y + 21); i++) //以今年为准，前30年，后30年
            document.reg_testdate.YYYY.options.add(new Option(" " + i + " 年", i));
        //赋月份的下拉框
        for (var i = 1; i < 13; i++)
            document.reg_testdate.MM.options.add(new Option(" " + i + " 月", i));
        document.reg_testdate.YYYY.value = y;
        document.reg_testdate.MM.value = new Date().getMonth() + 1;
        var n = MonHead[new Date().getMonth()];
        if (new Date().getMonth() == 1 && IsPinYear(y)) n++;
        writeDay(n); //赋日期下拉框
        //->赋值给日，为当天日期
//        document.reg_testdate.DD.value = new Date().getDate();
    }
    if (document.attachEvent)
        window.attachEvent("onload", YYYYMMDDstart);
    else
        window.addEventListener('load', YYYYMMDDstart, false);

    function YYYYDD(str) //年发生变化时日期发生变化(主要是判断闰平年)
    {
        var MMvalue = document.reg_testdate.MM.options[document.reg_testdate.MM.selectedIndex].value;
        if (MMvalue == "") {
//            var e = document.reg_testdate.DD;
            optionsClear(e);
            return;
        }
        var n = MonHead[MMvalue - 1];
        if (MMvalue == 2 && IsPinYear(str)) n++;
        writeDay(n)
    }

    function MMDD(str) //月发生变化时日期联动
    {
        var YYYYvalue = document.reg_testdate.YYYY.options[document.reg_testdate.YYYY.selectedIndex].value;
        if (YYYYvalue == "") {
            var e = document.reg_testdate.DD;
            optionsClear(e);
            return;
        }
        var n = MonHead[str - 1];
        if (str == 2 && IsPinYear(YYYYvalue)) n++;
        writeDay(n)
    }

    function writeDay(n) //据条件写日期的下拉框
    {
        var e = document.reg_testdate.DD;
        optionsClear(e);
        for (var i = 1; i < (n + 1); i++)
        {
            e.options.add(new Option(" " + i + " 日", i));
            if(i == changeDD){
                e.options[i].selected = true;  //->保持选中状态
            }
        }
        console.log(i);
        console.log(changeDD);
    }

    function IsPinYear(year) //判断是否闰平年
    {
        return (0 == year % 4 && (year % 100 != 0 || year % 400 == 0));
    }

    function optionsClear(e) {
        e.options.length = 1;
    }
    //->随时监听日的改变
    function DDD(str){
        changeDD = str;
    }
</script>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>盈亏详情</title>
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
<table align="center" bgcolor="#faebd7" border="1" width="600px">
    <th>买入日期</th>
    <th>买入金额</th>
    <th>手续费</th>
    <th>确认金额</th>
    <th>确认净值</th>
    <th>确认份额</th>
    <c:forEach var="item" items="${foundationBuyList}">
        <tr>
            <td>${item.buyDate}</td>
            <td>${item.money}</td>
            <td>${item.buyRatePrice}</td>
            <td>${item.sureMoney}</td>
            <td>${item.unitPrice}</td>
            <td>${item.num}</td>
        </tr>
    </c:forEach>
</table>
<h1 align="center" style="color: blue;">卖出日期 = ${foundationShop.shopDate}</h1>
<h1 align="center" style="color: blue;">卖出份数 = ${totalNum}</h1>
<h1 align="center" style="color: blue;">卖出确认净值 = ${shopUnitPrice}</h1>
<h1 align="center" style="color: blue;">卖出手续费 = ${foundationShop.shopRatePrice}元</h1>
<h1 align="center" style="color: blue;">买入总金额 = ${totalBuyMoney}元</h1>
<h1 align="center">总收益 = <span style="color: red">${totalGetMoney}元</span></h1>
<h1 align="center">年化率 = <span style="color: red">${yearRate}%</span></h1>

</body>
</html>

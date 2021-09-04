<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>splitTable demo</title>
</head>
<body>
<jsp:include page="../basic.jsp" />
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTable?shopId=1" target="_blank">TableSplit访问库1表1</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTable?shopId=3" target="_blank">TableSplit访问库2表2</a><br>
<h1>方式2</h1>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV2?shopId=1" target="_blank">TableSplit访问库1表1</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV2?shopId=2" target="_blank">TableSplit访问库1表2</a><br>
<h1>方式3</h1>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV3?shopId=1" target="_blank">TableSplit访问库1表2</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV3?shopId=2" target="_blank">TableSplit访问库2表1</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV3?shopId=3" target="_blank">TableSplit访问库2表2</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV3?shopId=4" target="_blank">TableSplit访问库1表1</a><br>
<h1>方式4</h1>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV4?shopId=1" target="_blank">TableSplit访问库1表2</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV4?shopId=2" target="_blank">TableSplit访问库2表1</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV4?shopId=3" target="_blank">TableSplit访问库2表2</a><br>
<a href="/splitTable/getOrderInfoListByShopIDUseSplitTableV4?shopId=4" target="_blank">TableSplit访问库1表1</a><br>
</body>
</html>

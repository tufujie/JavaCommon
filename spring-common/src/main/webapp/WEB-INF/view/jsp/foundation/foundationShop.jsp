<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>卖出列表</title>
    <jsp:include page="../basic.jsp" />
</head>
<body>
<h1 align="center" style="color: blue;">${foundation.name}</h1>
<h3 align="center" style="color: blue;">${foundation.code}</h3>
<h1 align="center">卖出列表</h1>
<table align="center" bgcolor="#faebd7" border="1" width="600px">
    <th>卖出时间</th>
    <th>详情</th>
    <c:forEach var="item" items="${foundationShopList}">
        <tr>
            <td>${item.shopDate}</td>
            <td style="color: yellow; cursor: pointer;"><a href="/foundation/getShopDetail?id=${item.id}" target="_blank">盈亏情况</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>基金</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/foundation/foundation.js"></script>
</head>
<body>
<form style="text-align: center" id="objectPost" action="" method="post">
    <table>
        <tr>
            <th>基金名称</th>
            <th>基金编码</th>
        </tr>
        <tr>
            <td><input id="name" name="name" value=""></td>
            <td><input id="code" name="code" value=""></td>
            <td><input type="button" value="添加基金" onclick="addInfo(1)"></td>
        </tr>
    </table>
</form>
<h1 align="center">以下结果通过分析${month}个月数据得出，具体查看详情</h1>
<h1 align="center">基金列表</h1>
<table align="center" bgcolor="#faebd7" border="1" width="600px">
    <th>基金编码</th>
    <th>基金名称</th>
    <th>数据</th>
    <th>操作</th>
    <th>详情</th>
    <c:forEach var="item" items="${foundationList}">
        <tr>
            <td>${item.code}</td>
            <td>${item.name}</td>
            <c:choose>
                <c:when test="${item.newest}">
                    <td style="color: blue">最新</td>
                </c:when>
                <c:otherwise>
                    <td style="color: red">非最新</td>
                </c:otherwise>
            </c:choose>
            <c:choose>
                <c:when test="${item.buyFlag}">
                    <td style="color: blue">买入</td>
                </c:when>
                <c:otherwise>
                    <td style="color: red">不买</td>
                </c:otherwise>
            </c:choose>
            <td style="color: yellow; cursor: pointer;"><a href="/foundation/getFoundationDetail?code=${item.code}" target="_blank">查看详情</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>福彩预测</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/fucai/fucai.js"></script>
    <style>
        table {
            margin:0 auto;
            text-align: center;
        }
        .red {
            color: red;
        }
        .blue {
            color: blue;
        }
        .rcc64 {
            background: url(/image/qiuRed46.jpg) no-repeat;
        }
        .bcc64 {
            background: url(/image/qiuBlue46.jpg) no-repeat;
        }
        .rcc {
            background: url(/image/red.jpg) no-repeat;
        }
        .bcc {
            background: url(/image/blue.jpg) no-repeat;
        }
        .c64 {
            float: left;
            _display: inline;
            width: 46px;
            height: 46px;
            margin: 0 1px;
            display: inline-block;
            font: 24px/46px "Microsoft YaHei", "微软雅黑", Arial;
            text-align: center;
            color: #fff;
        }
        .c {
            overflow: hidden;
            width: 25px;
            height: 25px;
            margin: 0 1px;
            display: inline-block;
            text-align: center;
            font: 12px/25px "Microsoft YaHei", "微软雅黑", Arial;
            color: #fff;
            border-radius: 50%;
        }
    </style>
</head>
<body>
<a href="/fuCai/fuCai" target="_blank">补充期号</a><br>
<h1 align="center" style="color: blue;">推荐球号</h1>
<table bgcolor="#faebd7" border="1" style="width: 300px;">
    <th>红色球1</th>
    <th>红色球2</th>
    <th>红色球3</th>
    <th>红色球4</th>
    <th>红色球5</th>
    <th>红色球6</th>
    <th>蓝色球</th>
    <tr>
        <td><span class="rcc64 c64">${fuCai.red1}</span></td>
        <td><span class="rcc64 c64">${fuCai.red2}</span></td>
        <td><span class="rcc64 c64">${fuCai.red3}</span></td>
        <td><span class="rcc64 c64">${fuCai.red4}</span></td>
        <td><span class="rcc64 c64">${fuCai.red5}</span></td>
        <td><span class="rcc64 c64">${fuCai.red6}</span></td>
        <td><span class="bcc64 c64">${fuCai.blue}</span></td>
    </tr>
</table>
<h2 align="center">统计期数为：${fuCaiList.size()} 期</h2>
<h1 align="center" style="color: red;">红色球排行榜</h1>
<table bgcolor="#faebd7" border="1" width="300px">
    <th>红色球号码</th>
    <th>出现次数</th>
    <th>排名</th>
    <c:forEach var="obj" items="${redDescMap}" varStatus="status">
        <tr>
            <td><span class="rcc c">${obj.key}</span></td>
            <td>${obj.value}</td>
            <td>${status.count}</td>
        </tr>
    </c:forEach>
</table>
<h1 align="center" style="color: blue;">蓝色球排行榜</h1>
<table bgcolor="#faebd7" border="1" width="300px">
    <th>蓝色球号码</th>
    <th>出现次数</th>
    <th>排名</th>
    <c:forEach var="obj" items="${blueDescMap}" varStatus="status">
        <tr>
            <td><span class="bcc c">${obj.key}</span></td>
            <td>${obj.value}</td>
            <td>${status.count}</td>
        </tr>
    </c:forEach>
</table>
<br>
<h1 align="center">中奖号码详情</h1>
<table bgcolor="#faebd7" border="1" width="300px">
    <th class="hide">id</th>
    <th>期号</th>
    <th>红色球1</th>
    <th>红色球2</th>
    <th>红色球3</th>
    <th>红色球4</th>
    <th>红色球5</th>
    <th>红色球6</th>
    <th>蓝色球</th>
    <th>编辑次数</th>
    <c:forEach var="item" items="${fuCaiList}">
        <tr>
            <td class="hide">${item.id}</td>
            <td>${item.fuDate}</td>
            <td><span class="rcc c">${item.red1}</span></td>
            <td><span class="rcc c">${item.red2}</span></td>
            <td><span class="rcc c">${item.red3}</span></td>
            <td><span class="rcc c">${item.red4}</span></td>
            <td><span class="rcc c">${item.red5}</span></td>
            <td><span class="rcc c">${item.red6}</span></td>
            <td><span class="bcc c">${item.blue}</span></td>
            <c:choose>
            <c:when test="${item.editNum > 0}">
                <td style="color: yellow; cursor: pointer;" onclick="changeEditNumZero(this)">${item.editNum}</td>
            </c:when>
            <c:otherwise>
                <td>${item.editNum}</td>
            </c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
</table>
<br>
</body>
</html>

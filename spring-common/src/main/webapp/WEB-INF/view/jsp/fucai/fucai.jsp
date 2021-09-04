<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>福彩</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/fucai/fucai.js"></script>
</head>
<body>
<a href="/fuCai/getFuture" target="_blank" style="margin-left: 45%; font-size: 30px;">双色球荐号</a><br>
<form id="objectPost" action="" method="post">
    <table>
        <tr>
        <th>福彩期号</th>
        </tr>
        <tr>
            <td><input id="fuDate" name="fuDate" value=""></td>
        </tr>
        <tr>
        <th>红色球1</th>
        <th>红色球2</th>
        <th>红色球3</th>
        <th>红色球4</th>
        <th>红色球5</th>
        <th>红色球6</th>
        </tr>
        <tr>
            <td><input id="red1" name="red1" value=""></td>
            <td><input id="red2" name="red2" value=""></td>
            <td><input id="red3" name="red3" value=""></td>
            <td><input id="red4" name="red4" value=""></td>
            <td><input id="red5" name="red5" value=""></td>
            <td><input id="red6" name="red6" value=""></td>
        </tr>
        <tr>
        <th>蓝色球</th>
        </tr>
        <tr>
            <td><input id="blue" name="blue" value=""></td>
        </tr>
        <tr>
            <td><input type="button" value="添加期号信息" onclick="addInfo(1)"></td>
        </tr>
    </table>
</form>
<a href="/fuCai/batchAdd" target="_blank">批量插入</a><br>
批量插入table网址<input type="text" value="http://www.cwl.gov.cn/kjxx/ssq/kjgg/" style="width: 500px;"/>
<br>
</body>
</html>

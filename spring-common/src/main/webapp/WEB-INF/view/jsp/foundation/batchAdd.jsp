<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>基金批量插入</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/foundation/foundation.js"></script>
    <script>
        var code = '${foundation.code}';
    </script>
</head>
<body>
<td style="color: yellow; cursor: pointer;"><a href="/foundation/getFoundationDetail?code=${foundation.code}" target="_blank">${foundation.name}</a></td>
<form id="objectPost" action="" method="post">
        <jsp:include page="foundationData.jsp" />
</form>
<input type="button" value="添加基金净值" onclick="batchAddInfo(1)">
</body>
</html>

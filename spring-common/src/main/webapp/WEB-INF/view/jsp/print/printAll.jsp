<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Print All JSP</title>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/print/printAll.js"></script>
</head>
<body>
<input id="userID" name="userID"><br>
<button onclick="printUser(1)">print User[当前页签打开]</button><br>
<button onclick="printUser(2)">print User2[新页签打开]</button><br>
</body>
</html>

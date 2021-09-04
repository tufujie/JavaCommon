<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>aspect demo</title>
</head>
<body>
<jsp:include page="../basic.jsp" />
<a href="/aspect/getUserList?rowCount=10&current=0" target="_blank">切面日志和SQL测试</a><br>
<a href="/aspect/exceptionTest" target="_blank">切面异常测试</a><br>
</body>
</html>

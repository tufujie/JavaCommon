<%-- 动作指令，jsp:forward --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp:forward</title>
</head>
<body>
<%-- 跳转并赋值 --%>
<jsp:forward page="result.jsp">
    <jsp:param name="age" value="20" />
</jsp:forward>
</body>
</html>

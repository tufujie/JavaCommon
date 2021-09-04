<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp:include</title>
</head>
<body>
<jsp:include page="result.jsp">
    <jsp:param name="username" value="Jef" />
    <jsp:param name="age" value="21" />
</jsp:include>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>putApplication</title>
</head>
<body>
<%!
    int i;
%>
<%
    application.setAttribute("counter", String.valueOf(++i));
%>
<%= i %>
</body>
</html>

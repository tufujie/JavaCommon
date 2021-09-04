<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basic JSP</title>
</head>
<body>
<html>
<html>
<head>
    <title>页面重定向</title>
</head>
<body>
<h1>页面重定向</h1>
<%
    // 重定向到新地址
    String site = new String("http://www.cnblogs.com/tufujie");
    response.setStatus(response.SC_MOVED_TEMPORARILY);
    response.setHeader("Location", site);
%>
</body>
</html>

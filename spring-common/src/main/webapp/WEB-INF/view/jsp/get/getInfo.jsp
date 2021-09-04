<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Basic JSP</title>
    <jsp:include page="../basic.jsp" />
</head>
<body>
<h1>使用 GET 方法读取数据</h1>
<ul>
    <li><p><b>名称:</b>
        <%= request.getParameter("name")%>
    </p></li>
    <li><p><b>电话:</b>
        <%= request.getParameter("phone")%>
    </p></li>
</ul>
</body>
</html>

<%-- jsp:forward的跳转页面 --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>forward-result</title>
</head>
<body>
<table>
    <th>年龄</th>
    <th>用户名</th>
    <tr>
        <td><%=request.getParameter("age")%></td>
        <td> <%=request.getParameter("username")%></td>
    </tr>
</table>
</body>
</html>

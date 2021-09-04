<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JSP demo</title>
</head>
<body>
<%--
JSP声明，使用方式
<%!
Java代码
%>
以及声明之后的使用，JSP脚本，使用方式
<%
Java代码
%>
--%>
<%!
    // 声明一个整型变量
    private int count;
    private String info() {
        return "Hello";
    }
%>
<%
    // 脚本输出
    out.println(count);
    out.println(info());
%>
<br>
<%
out.println("IP=" + request.getRemoteAddr());
%>
<%--
JSP输出表达式，使用方式
<%= Java代码 %>
--%>
<br>
<%= count %>
<br>
<%= info() %>
<%-- 常用使用方式可以是声明，脚本，表达式等两者或三者结合，配合HTML元素，CSS样式等，demo如下 --%>
<%!
    private int size = 5;
%>
<table bgcolor="#faebd7" border="1" width="300px">
    <%
        for (int i = 0; i < size; i++) {
    %>
    <tr>
        <td>循环值：</td>
        <td><%= i %></td>
    </tr>
    <%
        }
    %>
</table>
<a href="/error/gotoError" target="_blank">尝试跳转错误页</a>
<h1>JSP编译指令（3个）</h1>
page<br>
include<br>
taglib
<h1>JSP动作指令（7个）</h1>
<a href="/jsp/forward" target="_blank">jsp:forward</a><br>
拓展：这个会将填入的名称和jsp-forward.jsp页面中设置的年龄一起传到forward-result.jsp中
<form action="jsp-forward.jsp" method="post">
    <input type="text" name="username" />
    <input type="submit" value="login">
</form>
<a href="/jsp/include" target="_blank">jsp:include</a><br>
<a href="/jsp/useBeanSetPropertyGetProperty" target="_blank">jsp:useBean、jsp:setProperty、jsp:getProperty</a><br>
<h1>JSP内置对象（9个）</h1>
applicatioin（对于整个Web应用有效）<a href="/jsp/putApplication" target="_blank">putApplication</a>
<a href="/jsp/getApplication" target="_blank">getApplication</a><br>
session（仅对一次会话有效）<br>
request（仅对本次请求有效）<br>
page（仅对当前页面有效）<br>
config<br>
exception<br>
pageContext<br>
response<br>
<a href="/jsp/redirect" target="_blank">页面重定向</a><br>
</body>
</html>

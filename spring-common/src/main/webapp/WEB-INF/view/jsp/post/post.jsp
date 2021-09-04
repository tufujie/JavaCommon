<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>testPost</title>
    <%-- 动态include指令引入页面 --%>
    <jsp:include page="../basic.jsp" />
    <script type="text/javascript" src="/js/post/post.js"></script>
</head>
<body>
<form id="objectPost" action="" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input id="userName" name="name" value="Jef"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" id="userPassword" name="password" value="123456"></td>
        </tr>
        <tr>
            <td>手机号</td>
            <td><input id="phone" name="phone" value="18390220001"></td>
        </tr>
        <tr>
            <td>JSONUser</td>
            <td style="width: 1000px"><input id="jsonUser" name="jsonUser" value="{'name':'testName'}"></td>
        </tr>

        <tr>
            <td><input type="button" value="Post1" onclick="Post(1)"></td>
            <td><input type="button" value="Post2" onclick="Post(2)"></td>
            <td><input type="button" value="Post3" onclick="Post(3)"></td>
            <td><input type="button" value="Post4" onclick="Post(4)"></td>
            <td><input type="button" value="Post5" onclick="Post(5)"></td>
            <td><input type="button" value="Post6" onclick="Post(6)"></td>
            <td><input type="button" value="Post7" onclick="Post(7)"></td>
            <td><input type="button" value="Post8" onclick="Post(8)"></td>
            <td><input type="button" value="Post9" onclick="Post(9)"></td>
            <td><input type="button" value="查看请求参数，encode" onclick="Post(10)"></td>
            <td><input type="button" value="查看请求参数，未encode" onclick="Post(11)"></td>
            <td><input type="button" value="post2Extend" onclick="Post(12)"></td>
            <td><input type="button" value="post2ExtendTwo" onclick="Post(13)"></td>
            <td><input type="button" value="postJsonUser" onclick="Post(15)"></td>
            <td><input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
<p>传递id集合</p>
<form id="listPost" action="" method="post">
    <input type="text" name="id_first" id="id_first" value="id_first" />
    <input type="text" name="id_second" id="id_second" value="id_second" />
    <input type="button" value="PostIds1" onclick="postIds(1, '')">
    <input type="button" value="PostIds1One" onclick="postIds(1, 'one')">
    <input type="button" value="PostIds1OneMore" onclick="postIds(1, 'more')">
    <input type="button" value="PostIds2" onclick="postIds(2, '')">
    <input type="button" value="PostIds3" onclick="postIds(3, '')">
    <input type="button" value="PostIds4" onclick="postIds(4, '')">
</form>
</body>
</html>

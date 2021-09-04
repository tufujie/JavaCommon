<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jsp:useBean、jsp:setProperty、jsp:getProperty</title>
</head>
<body>
<%-- User user = new User; --%>
<jsp:useBean id="user" class="com.jef.entity.User" scope="page" />
<%-- user.setName(Jef); --%>
<jsp:setProperty name="user" property="name" value="Jef" />
<%-- user.setAge(20); --%>
<jsp:setProperty name="user" property="age" value="20" />
<%-- user.getName(); --%>
<jsp:getProperty name="user" property="name" /><br>
<%-- user.getAge(); --%>
<jsp:getProperty name="user" property="age" /><br>
</body>
</html>

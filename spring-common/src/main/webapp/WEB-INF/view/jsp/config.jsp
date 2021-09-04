<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>config JSP</title>
    <style type="text/css">
        .marginRight10 {
            margin-right: 10px;
        }
    </style>
</head>
<body>
<tr>
    <th width="120" valign="top"><span class="required">*</span>评级：</th>
    <td>
        <c:forEach items="${configs}" var="config">
            <input class="marginRight10" type="radio" name="level" value="${config.level}" <c:if test="${config.level == 1}">checked="checked"</c:if>>
            <span class="marginRight20">${config.name}</span>
        </c:forEach>
    </td>
</tr>
</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>pie-doughnut</title>
    <jsp:include page="../basic.jsp" />
    <script>
        var projectNameList = '${projectNameList}',
            showDataList = '${showDataList}';
    </script>
</head>
<body>
<div id="container" style="height: 100%"></div>
<script type="text/javascript" src="/js/baidu/echarts.common.min.js"></script>
<script type="text/javascript" src="/js/baidu/pie-doughnut.js"></script>
</body>
</html>

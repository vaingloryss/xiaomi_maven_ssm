<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--<jsp:forward page="${pageContext.request.contextPath}/userController/toHome"/>--%>
<%--<jsp:forward page="/adminController/adminLogin"/>--%>
<a href="${pageContext.request.contextPath}/userController/toHome">用户首页</a><br>
<a href="${pageContext.request.contextPath}/adminController/toLogin">管理员登录</a>
</body>
</html>

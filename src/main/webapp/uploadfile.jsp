<%@ page language="java" contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");
		String path = request.getContextPath();
	%>
	<h2>文件上传</h2>
	<form action="<%=path%>/user/uploadTest.action" method="post" enctype="multipart/form-data">
		名称: <input type="text" name="username"><br/>
        file: <input type="file" name="file"><br/>
        <input type="color" value="选择颜色" name="mycolor">
        <input type="submit" value="上传">
	</form>
</body>
</html>
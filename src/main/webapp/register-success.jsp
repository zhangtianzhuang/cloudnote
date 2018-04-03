<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = path + "/images";
%>	

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册成功</title>

<link href="css/register.css" type="text/css" rel="stylesheet" />
<style>
* {	font-family: '黑体';font-size:20px}
body.bodyer {background-image: url(images/bg2-find-note.png);background-size: cover;}
.boss-box {	width:960px;height:100px;margin:80px auto;background:#fff;border-radius:100px;padding:16px;text-align:center;
	line-height:100px;}
</style>
</head>
<body class="bodyer">
	<div class="boss-box">
		恭喜<s:property value="#request.register_success_username"/>用户注册成功，如忘记密码可通过邮箱找回，立即
		<a style="color:#747474" href='<%=path%>/user_login.jsp?username=<s:property value="#request.register_success_username"/>'>登录</a>吧
	</div>
</body>
</html>
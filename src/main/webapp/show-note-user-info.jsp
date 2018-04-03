<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ztz.entity.User" %>
<%@ taglib prefix="s" uri="/struts-tags" %>  

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = path + "/images";
%>
<s:if test="#session.noteuser==null">
	<jsp:forward page="/note-detail.jsp"></jsp:forward>
</s:if>

<html>
  <head>
    <title>爱分享云笔记-个人信息</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="images/header.png" type="image/png" >
	<link rel="stylesheet" href="/css/index.css" type="text/css" />
	<script type="text/javascript" src="/js/jquery-1.11.3.min.js"></script>	
	<script type="text/javascript" src="/js/main.js"></script>
<style>
	*{
		font-family: '微软雅黑';
		margin: 0px;
		padding: 0px;
		list-style-type: none;
		color: #000;
		font-size: 16px;
	}
	input[type='submit']{
		padding: 5px 10px;
		background: #797979;
		float:right;
		color: #fff;
		border: 1px solid #797979;
		margin-top: 14px;
		margin-right: 10px;
		cursor:pointer;
	}
	body{
		background-image: url(myWebSite/images/bg2-find-note.png);background-attachment: fixed;
	}
	.user-info-form{
		width:950px;height:630px;padding:5px;margin:5px auto;background-color:#f0f0f0
	}
	.user-info-form h4{margin:4px;color:#000}
	.w_header{
		min-height:100px;background-color:#fff;border:1px solid #d0d0d0;
	}
	.w_header p{padding:10px 20px;line-height:50px;text-vertical:middle}
	.w_header p input[type='text'],input[type='number']{width:280px;height:40px;padding-left:4px}
	.w_header p textarea{width:350px;height:100px;background:#f0f0f0;resize:none}
	.edit{float:right;margin-right:10px;position:relative;bottom:26px;cursor:pointer;}
	.edit:hover{text-decoration: underline;}
</style>
  </head>
  <body>
	<jsp:include page="navier.jsp"/>
	<form action="/user/motifyUser.action" method="get" class="user-info-form">
		<h4>个人信息</h4>
		<div class="w_header">
			<p>	
				<input type="hidden" value='${noteuser.registerDate }'/>
				用户名：<input type="text" readonly="readonly" value='${noteuser.username }' /><br/>
				年　龄：<input type="number"  value='${noteuser.age }' disabled="disabled"/><br/>
				性　别： <input type="text"  readonly="readonly" value='${noteuser.sex }' /><br/>
				手机号：<input type="number" value='${noteuser.phone }' disabled="disabled"/><br/>
				兴趣爱好：<br/>
					 <textarea name="user.habit" disabled="disabled">${noteuser.habit }</textarea>
					 <br/>
				QQ：  <input type="number" value='${noteuser.qqNum }' disabled="disabled"/><br/>
				微信：<input type="text" value='${noteuser.weChat }' disabled="disabled"/><br/>
				邮箱：<input type="text" value='${noteuser.email }' disabled="disabled"/><br/>
			</p>
		</div>
	</form>
  </body>
</html>

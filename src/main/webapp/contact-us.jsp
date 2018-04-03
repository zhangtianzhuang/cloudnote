<%@ page language="java" pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.ztz.entity.User"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	User cUser = (User) session.getAttribute("user");
	if (null == cUser) {
%>
<jsp:forward page="user_login.jsp"></jsp:forward>
<%
	}
%>

<html>
<head>
<title>云笔记-联系我们</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="icon" href="images/header.png" type="image/png">
<link rel="stylesheet" href="css/index.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>

<style>
* {
	font-family: '微软雅黑';
	margin: 0px;
	padding: 0px;
	list-style-type: none;
	color: #000;
	font-size: 16px;
}

body {
	background-image: url(images/bg2-find-note.png);
	background-attachment: fixed;
}

.note-form {
	width: 950px;
	height: 100%;
	padding: 5px;
	margin: 5px auto;
}

.note-form h4 {
	margin: 4px;
	color: #fff
}

.w_header {
	min-height: 440px;
	background: #fff;
	border: 1px solid #d0d0d0;
}

.w_header p {
	padding: 10px 20px;
}

.w_header p input {
	margin-top: 6px
}

.w_header input[type='text'] {
	width: 200px;
	height: 30px
}

.w_header textarea {
	width: 100%;
	height: 200px;
	resize: none;
}

#sendCode{margin-bottom:8px;}

input[type='button'] {
	padding: 5px 10px;
	background: #797979;
	color: #fff;
	border: 1px solid #797979;
	margin-top: 14px;
	margin-right: 10px
}

#sendinfo{float: right;}

#myform{width:960px;margin:0px auto}

</style>
</head>
<body>

	<jsp:include page="navier.jsp" />
	<script type="text/javascript">
		$(function() {
			var s1 = false;
			var s2 = false;
			var s3 = false;
			var s4 = false;
			//验证邮箱
			$("#email").blur(function() {
				var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
				if (!myreg.test($("#email").val())) {
					$(".checkEmail").text('邮箱不合法').css({
						color : "#f00"
					});
					s1 = false;
				} else {
					$(".checkEmail").text('通过验证').css({
						color : "#05D400"
					});
					s1 = true;
				}
			});

			// 标题验证
			$("#title").blur(function() {
				if ($("#title").val()==''){
					$(".checkTitle").text('标题不可为空').css({
						color : "#f00"
					});
					s2 = false;
				} else {
					$(".checkTitle").text('通过验证').css({
						color : "#05D400"
					});
					s2 = true;
				}
			});
			
			// 内容验证
			$("#content").blur(function() {
				if ($("#content").val()==''){
					$(".checkContent").text('内容不可为空').css({
						color : "#f00"
					});
					s3 = false;
				} else {
					$(".checkContent").text('通过验证').css({
						color : "#05D400"
					});
					s3 = true;
				}
			});
			/*
			$("#code").blur(function(){
				var mailCode = $("#code");
				$.ajax({
					type : "post",
					url : "mailAction.action",
					dataType : "text",
					data : {
						mailCode:mailCode
					},
					success : function(data) {
						if(data=='success'){
							$("checkMailCode").text('验证码正确').css({
								color : "#05D400"
							});
							s4 = true ;
						}else{
							$("checkMailCode").text('验证码错误').css({
								color : "#f00"
							});
							s4 = false ;
						}
					},
					error : function(data) {
						alert('出现异常了...');
					}
				});	
			});
			*/
			
			$("#sendCode").click(function(){
				if(s1){ //邮箱可用
					$("#sendCode").val('已发送');
					var mail = $("#email").val();	
					$.ajax({
						type : "post",
						url : "<%=basePath%>/checkMailAction.action",
						dataType : "text",
						data : {
							mail:mail,
						},
						success : function(data) {
						},
						error : function(data) {
							alert('发送邮件出现异常，稍后重试...');
						}
					});
				}else{
					alert('请输入正确的邮箱地址');
				}
			});
			
			$("#sendinfo").click(function() {
				if (s1 && s2 && s3) {
					$("#myform").submit();
				} else {
					alert('信息不完整');
				}
			});
		});
	</script>

	<form action="sendMailAction.action" method="post" id="myform">
		<h4>联系我们</h4>
		<div class="w_header">
			<p>
				标题：<input type="text" name="title" id="title"/>&nbsp;&nbsp;<span class="checkTitle"></span><br />  
				邮箱：<input type="text" name="email" id="email"/>&nbsp;&nbsp;<span class="checkEmail"></span><br /> 
				<input type="text" placeholder="验证码" id="code" />
				<input type="button" value="发送验证码"  id="sendCode" /><span id="checkMailCode"></span><br/>
				内容：
				<textarea id="content" name="content"></textarea>
				<span class="checkContent"></span>
			</p>
			<input type="button" value="提交信息" id="sendinfo" />
		</div>
	</form>

</body>
</html>

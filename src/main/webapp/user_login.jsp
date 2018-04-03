<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = path + "/images";
	String success_username = null ;
	String str = request.getParameter("username") ; 
	if(null!=str){
		success_username = str ;
		System.out.println("--------username 参数："+success_username);
	}else{
		success_username = "" ;
	}
%>	

<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>

<link href="css/register.css" type="text/css" rel="stylesheet" />
<style>
* {
	font-family: 微软雅黑
}

body.bodyer {
	background-image: url(images/bg2-find-note.png);
	background-size: cover;
}

.boss-box {
	width: 600px;
	height: 300px;
	margin: 14% auto;
	background:#fff;
	border-radius: 5px;
}

.main {
	height:100px;
	padding:20px;
	margin-left:110px;
}

.main tr td:first-child img{
	width:30px;height:30px
}
#checkEmail,#checkPassword,#checkPassword2{
	width:20px;height:20px
}


.main tr td input[type='text'],input[type='password'] {
	width: 260px;
	height: 36px;
	padding-left:5px;
}

.main tr td input[type='submit'],input[type='button']{
	width:100%;height:35px;margin-top:10px;border-radius:6px;
	background:#666E8A;color:#fff;border:1px solid #666E8A;cursor:pointer
}

</style>

<!-- ajax对验证码进行验证 -->
<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="js/user_check.js"></script>
<script type="text/javascript">
	$(function () {
        $("#login").click(function(){
            if(checkPassword($("#password").val()) && checkPassword($("#password").val())){
                $("#myform").submit();
            }else{
                alert('恁填完嘞，嘛溜嘞');
            }
        });
    });
</script>
</head>
<body class="bodyer">
	<div class="boss-box">
		<form name="register" action="<%=path%>/user/login.action" method="post" id="myform">
			<div style="width:100%;height:70px;text-align:center;line-height:100px">
				<h3>登录</h3>
			</div>
			
			<table class="main">
				<tr>
					<td><img src="images/logo-user.png"/></td>
					<td><input type="text" name="user.username" placeholder="邮箱" id="email" value='<%=success_username%>'/></td>
					<td><span id="checkEmail"></span></td>
				</tr>
				
				<tr>
					<td><img src="images/logo-password.png"/></td>
					<td><input type="password" name="user.password" placeholder="密码" id="password" /></td>
					<td><span id="checkPassword"></span></td>
				</tr>

				<tr>
					<td colspan="2"><input type="button" id="login" value="登录" /></td>
				</tr>
			</table>
		</form>
		
		<span style="float:right;margin-right:10px;font-size:14px">还没账号？立即
			<a href="user_register.jsp" style="color:#666E8A">注册</a></span>
	</div>
</body>
</html>
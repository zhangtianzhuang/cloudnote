<%@ page language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = path + "/images";
%>
<s:if test="#session.user==null">
	<jsp:forward page="user_login.jsp"></jsp:forward>
</s:if>
<html>
  <head>
    <title>爱分享云笔记-个人信息</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="images/header.png" type="image/png" >
	<link rel="stylesheet" href="css/index.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
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
		background-image: url(images/bg2-find-note.png);background-attachment: fixed;
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
		<h4>基本信息</h4><span class="edit">编辑</span>
		<div class="w_header">
			<p>	
				<input type="hidden" name="user.id" value='${user.id }'/>
				<input type="hidden" name="user.password" value='${user.password }'/>
				<input type="hidden" name="user.registerDate" value='${user.registerDate }'/>
				用户名：<input type="text" name="user.username" readonly="readonly" value='${user.username }' /><br/>
				年　龄：<input type="number" name="user.age" value='${user.age }' disabled="disabled"/><br/>
				性　别：  &nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="user.sex" value='男' disabled="disabled" />男</label>
					 &nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="user.sex" value='女' disabled="disabled"/>女</label>
					 &nbsp;&nbsp;&nbsp;&nbsp;<label><input type="radio" name="user.sex" value='保密' disabled="disabled"/>保密</label>
					 <br/>
				手机号：<input type="number" name="user.phone" value='${user.phone }' disabled="disabled"/><br/>
				兴趣爱好：<br/>
					 <textarea name="user.habit" disabled="disabled">${user.habit }</textarea>
					 <br/>
				QQ：  <input type="number" name="user.qqNum" value='${user.qqNum }' disabled="disabled"/><br/>
				微信：<input type="text" name="user.weChat" value='${user.weChat }' disabled="disabled"/><br/>
				邮箱：<input type="text" name="user.email" value='${user.email }' disabled="disabled"/><br/>
			</p>
			<input type="submit" value="修改信息" disabled="disabled" id="motifyInfo"/>
		</div>
	</form>
	<script>
		$(function(){
			var getSex = '${session.user.sex}';
			if(getSex=='男'){ //男
				$("input[name='user.sex']").eq(0).attr('checked', 'true');
			}else if(getSex=='女'){ //女
				$("input[name='user.sex']").eq(1).attr('checked', 'true');
			}else{ //保密
				$("input[name='user.sex']").eq(2).attr('checked', 'true');
			}
			var flag = true ; //点击编辑，所有组件可用
			var id = $("input[name='user.id']");
			var username = $("input[name='user.username']");
			var password = $("input[name='user.password']");
			var age = $("input[name='user.age']");
			var sex = $("input[name='user.sex']");
			var phone = $("input[name='user.phone']");
			var habit = $("textarea[name='user.habit']");
			var qq = $("input[name='user.qqNum']");
			var weChat = $("input[name='user.weChat']");
			var email = $("input[name='user.email']");
			
			$(".edit").click(function(){
				if(flag){
					age.attr("disabled",false);
					sex.attr("disabled",false);
					phone.attr("disabled",false);
					habit.attr("disabled",false);
					qq.attr("disabled",false);
					weChat.attr("disabled",false);
					age.attr("disabled",false);
					email.attr("disabled",false);
					
					$("#motifyInfo").attr("disabled",false);
					$(".edit").text('保存');
					flag = false ;
				}else{
					age.attr("disabled",true);
					sex.attr("disabled",true);
					phone.attr("disabled",true);
					habit.attr("disabled",true);
					qq.attr("disabled",true);
					weChat.attr("disabled",true);
					age.attr("disabled",true);
					email.attr("disabled",true);
					
					$("#motifyInfo").attr("disabled",true);
					$(".edit").text('编辑');
					flag = true ;
				}								
			});
					
			
			$("#motify").click(function(){
				var user = {
						"user.id":id.val(),
						"user.username":username.val(),
						"user.password":password.val(),
						"user.age":age.val(),
						"user.sex":sex.val(),
						"user.phone":phone.val(),
						"user.habit":habit.val(),
						"user.qqNum":qq.val(),
						"user.weChat":weChat.val(),
						"user.email":email.val()
				};
				$.ajax({
					type:"post",
					url:"<%=basePath%>/user/motifyUser.action",
					dataType:"text",
					data:{
						user:user
					},
					success:function(data){
						alert('修改成功');
					},
					error:function(data){
						alert('服务器出现异常，稍后重试...');
					}
				});
			});
		});
	</script>
  </body>
</html>

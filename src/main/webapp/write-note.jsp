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
    <title>写笔记</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="images/header.png" type="image/png" >
	<link rel="stylesheet" href="css/index.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>  
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>	
	<script type="text/javascript" src="js/jquery.min.js"></script>	
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
	body{
		background-image: url(images/bg2-find-note.png);background-attachment: fixed;
	}
	.note-form{
		width:960px;height:860px;padding:5px;margin:5px auto;background:#f0f0f0
	}
	.note-form h4{margin:4px;color:#000}
	.w_header{
		min-height:100px;background:#fff;border:1px solid #d0d0d0
	}
	.w_header div{padding:10px 20px;line-height:30px;height:200px}
	.w_header div input{margin-top:6px}
	.w_header div input[type='text']{width:200px;height:30px}
	.selectColor{width:20px;height:20px;margin-right:15px;margin-left:10px;display:block;
		position:relative;bottom:16px;left:5px}
	.w_header div p label {float:left;margin-right:10px;}
	.main{width:940px;height:500px;border:1px solid #d0d0d0;background:#fff}
	.main textarea{width:940px;height:100%;resize:none;border:1px solid #d0d0d0;outline: none;padding:10px}
	.upload-note {width:950px;height:60px;background:#fff;border:1px solid #d0d0d0}
	.upload-note input[type='submit']{padding:5px 16px;float:right;background:#d0d0d0;color:#000;
			border:1px solid #d0d0d0;height:60px;cursor:pointer;}
	.uploadImage{width:100%;height:60px;background:#fff}
	#upload-image{margin-top:15px;margin-left:10px;}
	
</style>
  </head>
  <body>
	<jsp:include page="navier.jsp"/>
	<form action="<%=basePath%>/note/publishAction.action" method="post" class="note-form"  enctype="multipart/form-data">
		<h4>基本信息</h4>
		<div class="w_header">
			<div>
				标题：<input type="text" name="note.title"/><br/>
				作者：<input type="text" name="note.user.username" value='${user.username }' readonly="readonly"/><br/>
				是否分享?
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><input type="radio" name="note.isShow" value="1" checked="checked"/>是</label>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<label><input type="radio" name="note.isShow" value="0"/>否</label><br/>
				
				选择主题（默认白色<input type="radio" name="note.bgColor" value="#fff" checked="checked"/>）
				<br/>
				<p>
					<label></label>
					<label><input type="radio" name="note.bgColor" value="#C8BFE7"/><span class="selectColor" style="background:#C8BFE7">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#FFAEC9"/><span class="selectColor" style="background:#FFAEC9">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#C3C3C3"/><span class="selectColor" style="background:#C3C3C3">　</span></label>
					
					<label><input type="radio" name="note.bgColor" value="#99D9EA"/><span class="selectColor" style="background:#99D9EA">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#EFE4B0"/><span class="selectColor" style="background:#EFE4B0">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#FFC90E"/><span class="selectColor" style="background:#FFC90E">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#F8A267"/><span class="selectColor" style="background:#F8A267">　</span></label>
					
					<label><input type="radio" name="note.bgColor" value="#ffa"/><span class="selectColor" style="background:#ffa">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#ffd1a4"/><span class="selectColor" style="background:#ffd1a4">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#d9b3b3"/><span class="selectColor" style="background:#d9b3b3">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#cdcd9a"/><span class="selectColor" style="background:#cdcd9a">　</span></label>
					
					<label><input type="radio" name="note.bgColor" value="#e8ffc4"/><span class="selectColor" style="background:#e8ffc4">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#b3d9d9"/><span class="selectColor" style="background:#b3d9d9">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#d8d8e8"/><span class="selectColor" style="background:#d8d8e8">　</span></label>
					<label><input type="radio" name="note.bgColor" value="#ffbfff"/><span class="selectColor" style="background:#ffbfff">　</span></label>
				</p>
			</div>
		</div>
		
		<h4>笔记内容</h4>
		<div class="main">
			<textarea name="note.content"></textarea>
		</div>
		<!-- 
		<h4>上传图片</h4>
		<div class="uploadImage">
			<input type="file" value="选择图片" name="img" id="upload-image" />			
		</div>
		-->
		
		<h4>上传笔记</h4>
		<div class="upload-note">
			<input type="submit" value="提交云笔记"/>
		</div>		
	</form>
<script>
function selectColor(){
	$("input[name='note.bgColor']").click(function(){
		var color = $("input[name='note.bgColor']:checked").val();//获取单选按钮选中的值
		$("textarea[name='note.content']").css("background", color);
	});
}

$(function(){
	selectColor();	
});
</script>
  </body>
</html>

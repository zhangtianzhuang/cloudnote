<%@ page language="java" pageEncoding="UTF-8"%>
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
    <title>写笔记</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="images/header.png" type="image/png" >
	<link rel="stylesheet" href="css/index.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
<style>
	*{font-family: '微软雅黑';margin: 0px;padding: 0px;list-style-type: none;color: #000;font-size: 16px;}
	body{background-image: url(images/bg2-find-note.png);background-attachment: fixed;}
	.note-form{width:950px;min-height:600px;padding:5px;margin:5px auto;background-color:#f0f0f0}
	.note-form h4{margin:4px;color:#000}
	.w_header{min-height:100px;background:#fff;border:1px solid #d0d0d0}
	.w_header p{padding:10px 20px;}
	.w_header p input{margin-top:6px}
	.w_header p input{width:200px;height:30px}
	.w_header p{line-height:25px;}
	.main{width:950px;height:400px;border:1px solid #d0d0d0;background:#fff}
	.main textarea{width:100%;height:100%;resize:none;padding:10px;background:#C8BFE7;}
	.upload-note {width:950px;height:60px;background:#f0f0f0;border:1px solid #d0d0d0}
	.upload-note input[type='button']{padding:5px 10px;float:right;background:#d0d0d0;color:#000;
			border:1px solid #d0d0d0;width:90px;height:60px;cursor:pointer;}
	#comment{width:860px;height:60px;resize:none;border:1px solid #d0d0d0;outline:none;padding:10px}
	#showComment{width:950px;margin:20px 0px;min-height:20px;}
	#showComment ul{width:100%;min-height:100px;}
	.comment-list{min-height:36px;line-height:36px;margin:5px 0px;background-color:#fff;padding-left:10px;
		border:1px solid #d0d0d0}
	.level_date{float:right;margin-right:10px;}
	#collect{float:right;color:#000;margin:5px 0px;cursor:pointer;background:#f0f0f0;border:1px solid #f0f0f0}
	/*用户其他笔记*/
	.other-notes{width:1000px;height:160px;margin:0px auto;padding:5px;}
	
	.note-item{float:left;margin-left:20px;margin-bottom:60px;box-shadow: 0px 10px 10px #e0e3ec;
	  width: 225px;height: 140px;background: #f1f4f5;color: #000;	border-radius:10px;}
	.note-item:hover {box-shadow: 0px 0px 20px #ccc;position: relative;top: 5px}
	.note-title{font-size:18px;color:#1296db;line-height:40px}
	.note-content{line-height:30px;}
	.note-browser{line-height:30px;}
	.note-upload-time{line-height:30px;}
	.note-title,.note-content,.note-browser,.note-upload-time{margin-left:8px;}
	
	.show-username:hover{text-decoration:underline;}
</style>
  </head>
  <body>
	
	<jsp:include page="navier.jsp"/>

	<form action="<%=path%>/note/motifyAction.action?id=${note.id}" method="get" class="note-form">
		<h4>基本信息</h4>
		<div class="w_header">
			<p>
				标题：&nbsp;&nbsp;${note.title}<br/>
				作者：&nbsp;&nbsp;<a class="show-username" href="user/findUser.action?userid=${note.user.id }">${note.user.username}</a><br/>
				<s:if test="#session.note.user.username==#session.user.username">
				当前状态: &nbsp;&nbsp;<span>${note.isShow==1?'已分享':'未分享' }</span><br/>
				</s:if>
				浏览量:&nbsp;&nbsp;<span>${note.browserCounts }</span>
				<s:else>
				</s:else>
			</p>
		</div>
		
		<h4>笔记内容</h4>
		<div class="main">
			<textarea name="note.content" disabled="disabled" id="note-content">${note.content}</textarea>
		</div>
		<s:if test="#session.user!=null">
			<input type="button" id="collect" value='[收藏]'/>
		</s:if>
		<br/>
		<s:if test="#session.note.user.username!=#session.user.username">
			<h4>评论</h4>
			<div class="upload-note">
				<textarea id="comment"></textarea>
				<input type="button" value="评论" id="levelWord" />
			</div>
		</s:if>

		<!-- 显示评论 -->
		<div id="showComment">
			<h4>留言</h4>
			<ul id="content-item">
			</ul>
		</div>
		
		<h4>其他笔记</h4>
	</form>
	<div class="other-notes">
	</div>
<script type="text/javascript">
	//查看是否收藏了	
	function isCollect(){
		$.ajax({
	 	    type:"post",
	  	    async:false,
	        url : "<%=path%>/note/isCollectAction.action?id="+${note.id},
	        dataType:"text",
	        success : function(data) {
	            if(data=='hasCollect'){ //已收藏
					console.log('已收藏');
	            	$("#collect").val("[已收藏]").css({color:"#f00"});
	            	$("#collect").attr('disabled',true);
	            	console.log('设置收藏不可点击');
	            }else{
	            	console.log('未收藏');
	            }
	        },
	        error : function(data) {
	     	    alert('服务器异常，评论失败...');
	        }
	    });
	}
	
	//加载留言
	function loadLevelWord(){
		$.ajax({
	  		type:"post",
	  		async:false,
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
	  		url:"<%=path%>/level/selectAction.action?id2="+${note.id},
	  		success:function(data){
	  			var jsonData = eval(data);
	  			$("#content-item").empty();
	  			if(jsonData!=''){
	  				$.each(jsonData,function(index,note){
	  					var level_date = '暂无日期' ;
	  					if(note.level_date!=undefined){
	  						level_date = new Date(note.level_date).toLocaleDateString();	
	  					}
		  				var str = '<li class="comment-list">'
		  					+ (index+1)+"、"
		  					+ note.content
		  					+ '<span class="level_date">'
		  					+  level_date
		  					+ '</span>'
		  					+'</li>';
						$("#content-item").append(str);
		  			});	
	  			}else{
	  				var str = '<li class="comment-list" style="height:60px;text-align:center;line-height:60px;font-size:18px">暂无评论</li>';
	  				$("#content-item").append(str);
	  			}
	  		},
	  		error:function(data){
	  		}
  		});
	}
	
	function ajaxLoadNote(url,which){
		$.ajax({
	  		type:"post",
	  		async:false,
	  		url:url,
	  		success:function(data){
	  			var jsonData = eval(data);
	  			$.each(jsonData,function(index,note){
	  				var title = note.title ;
	  				var content = note.content ;
	  				if(title.trim().length>6){
	  					title = title.substring(0,6)+'..';
	  				}
	  				if(content.trim().length>10){
	  					content = content.substring(0,10)+'..';
	  				}
	  				var str = '<div class="note-item">'
					+ '<span>'
					+ '<a class="note-title" href="<%=basePath%>/note/selectAction?id='+ note.id + '">'
					+ '标题:' + title
					+ '</a>'
					+ '</span><br/>'
					
					+ '<span class="note-content">'
					+ '内容:' + content
					+ '</span><br/>'
					
					+ '<span class="note-browser">'
					+ '浏览量:' + note.browserCounts
					+ '</span><br/>'
					
					+ '<span class="note-upload-time">'
					+ '上传日期:' + new Date(note.uploadDate).toLocaleDateString()
					+ '</span>'
	  				+ '</div>' ;
					which.append(str);
	  			}); 
	  		},
	  		error:function(data){
	  			alert('服务器异常，加载错误...');
	  		}
  		});	
	}
	
	//加载评论
	$(function(){
		var url = "<%=path%>/note/selectAction?id=-4&userid="+${note.user.id}+"&noteid="+${note.id};
		loadLevelWord();
		isCollect();
		ajaxLoadNote(url, $(".other-notes"));
	});
	
	//监听事件，点击评论
	$("#levelWord").click(function(){
        var content = $("#comment").val(); //获取文本框中的评论内容
        var url = "<%=basePath%>/level/levelAction.action?id="+${note.id}+"&content="+content;
        if(content!=undefined && content!=null){
     	   $.ajax({
         	    type:"post",
		  	    async:true,
                url : url,
                success : function(data) {
                	$("#comment").val('');
                    loadLevelWord();//重新加载评论	
                },
                error : function(data) {
             	    alert('网速较慢，评论失败，稍后重试...');
                }
            });   
        }
	});
	//根据笔记背景
	$("#note-content").css({background:'${note.bgColor}'});
	$("#collect").click(function(){
		var url = "<%=basePath%>/note/collectAction.action?id="+${note.id} ;
		$.ajax({
     	    type:"post",
	  	    async:false,
            url : url,
            success : function(data) {
				isCollect();               
            },
            error : function(data) {
         	    alert('服务器异常，收藏失败...');
            }
        });
	});
</script>
  </body>
</html>

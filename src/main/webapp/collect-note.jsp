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
	<jsp:forward page="/user_login.jsp"></jsp:forward>
</s:if>
<html>
  <head>
    <title>我的收藏-爱分享云笔记平台</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<link rel="icon" href="images/header.png" type="image/png" >
	<link rel="stylesheet" href="css/index.css" type="text/css" />
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<style>
		*{font-family:'微软雅黑';color:#000}
		body{background-image: url(images/bg2-find-note.png);background-attachment: fixed;}
		h4{margin-top:4px;;margin-bottom:4px}
		.all-div{width:960px;min-height:300px;margin:10px auto;background:#f0f0f0;padding:10px}
		.f-header form{width:960px;height:60px;background:#fff;border:1px solid #d0d0d0;margin:10px 0px}
		.f-header form input[type='text']{width:500px;height:40px;margin-left:100px;margin-top:10px;padding-left:4px;
				border:1px solid #f0f0f0;color:#000;background:#f0f0f0;outline:none;}
		.f-header form input[type='button']{width:100px;height:40px;background:#d0d0d0;
			color:#000;position:relative;right:10px;cursor:pointer;outline:none;border:1px solid #d0d0d0}
		.note-comment{background:#fff;border:1px solid #d0d0d0}
		#note-show-list,.note-show-list{width:100%;min-height:0px;}
		.note-show-list-item{width:100%;height:60px;}
		.note-show-list-item th{border-right:1px solid #d0d0d0}
		.note-show-list-item th:nth-child(5){border-right:0px}
		#note-show-list .title,#note-show-list .content,#note-show-list .browser,#note-show-list .uploadTime{
			line-height:60px;text-ailgn:center;
		}
		#note-show-list tr td{border-top:1px solid #d0d0d0;border-right:1px solid #d0d0d0}
		#note-show-list tr td:nth-child(5){border-right:0px}
		#note-show-list .title,.note-show-list .title{width:28%;padding-left:10px;}
		#note-show-list .content,.note-show-list .content{width:36%;margin-left:30px;font-size:16px;text-align:center}
		#note-show-list .browser,.note-show-list .browser{width:10%;padding-left:10px;text-align:center}
		#note-show-list .status,.note-show-list .status{text-align:center}
		#note-show-list .uploadTime,.note-show-list .uploadTime{width:170px;text-align:center}
		
		.page{width:960px;height:60px;border:1px solid #d0d0d0;margin-top:10px;background:#fff}
		.page input[type='button']{width:100px;height:40px;background:#d0d0d0;cursor:pointer;outline:none;
				border:1px solid #d0d0d0;margin-top:8px;margin-left:10px}
		#next-page{float:right;margin-right:10px}
		
		.noCollect{width:960px;height:80px;text-align:center;font-size:24px;border-top:1px solid #d0d0d0;
			line-height:80px}
	</style>
  </head>
  
  <body>
    <jsp:include page="navier.jsp"/>
    <div class="all-div">
		 <div class="f-header">
			<h4>我的收藏</h4>
		 </div>
		 
		 <div class="note-comment">
	 		<table class="note-show-list" cellpadding="0" cellspacing="0">
	 			<tr class="note-show-list-item">
		 			<th class="title">标题</th>
		 			<th class="content">文章内容</th>
		 			<th class="browser">浏览量</th>
		 			<th class="status">分享状态</th>
		 			<th class="uploadTime">上传日期</th>
		 		</tr>
	 		</table>
	 		
	 		<table id="note-show-list" cellpadding="0" cellspacing="0">
	 		</table>
	 	 </div>
 		 <div class="page">
	 		<input type="button" value="上一页" id="previous-page" onclick="onPage('previous')"/>
	 		&nbsp;&nbsp;&nbsp;共1页
	 		<input type="button" value="下一页" id="next-page" onclick="onPage('next')" />
	 	</div>
    </div>
  </body>
  <script type="text/javascript">
	  //使用ajax查询笔记列表
	  function ajaxFindNote(url){
		   $.ajax({
       	   type:"post",
		  	  async:false,
              url : url,
              success : function(data) {
		  		   var jsonData = eval(data); // 
		  		   if(jsonData==''){
		  			   var str = '<p class="noCollect">暂无收藏</p>' ; 
			  		   $("#note-show-list").append(str); 
		  		   }else{
		  			//清空数据
			  		   $("#note-show-list").empty();
			  		   $.each(jsonData,function(index,note){
		  		     	var title = note.title ;
		  				var content = note.content ;
		  				if(title.length>15){
		  					title = title.substring(0,10)+'..' ;
		  				}
		  				if(content.length>15){
		  					content = content.substring(0,15)+'..';
		  				}
		  				var isShow = note.isShow ;
		  				var isShowText = '' ;
						if(isShow==1){
							isShowText = '已分享';
						}else{
							isShowText = '未分享';
						}
		  				var str = '<tr class="note-show-list-item">'
		       		  				+ '<td class="title">'
		  							+ '<a href="note/selectAction.action?id=' + note.id + '">'
		  							+ (index+1)+"、"+ title
		  							+ '</a>'
		  							+ '</td>'
		  							+ '<td class="content">'
		  							+ content
		  							+ '</td>'
		  							+ '<td class="browser">'
		  							+ note.browserCounts
		  							+ '</td>'
		  							+ '<td class="status">'
		  							+ isShowText
		  							+ '</td>'
		  							+ '<td class="uploadTime">'
		  							+ new Date(note.uploadDate).toLocaleDateString()
		  							+ '</td>'
		  							+ '</tr>';
						$("#note-show-list").append(str);
		  			}); 
		  		   }
	          },
              error : function(data) {
           	     alert('网速较慢，刷新重试...');
              }
          });
	   }
	  
	  //翻页按钮点击事件
	  function onPage(which){
	  }
      
	  //该方法在刷新页面是执行
	  $(function(){
	  	var url = "note/selectCollectAction.action" ;
	  	ajaxFindNote(url); //调用ajax查询
  	});
  	</script>
  
</html>

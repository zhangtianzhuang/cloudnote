<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imgPath = path + "/images";
%>

<div class="navier-bg">
</div>

<div class="navier">
	<ul class="list">
		<li><img src="<%=imgPath%>/header.png" class="header"/></li>
		
		<li><a href="index.jsp" class="nav_item">主页</a>
		</li>

		<li><a href="#" class="nav_item">热门语言</a>
			<div class="down">
				<a href="#">Java</a> <a href="#">C++</a> <a href="#">PHP</a> <a
					href="#">JavaScript</a>
			</div></li>

		<li><a href="#" class="nav_item">云笔记</a>
			<div class="down">
				<a href="#">开通会员</a> <a href="#">申请开发</a> <a href="#">加入我们</a> <a
					href="<%=path%>/contact-us.jsp">联系我们</a>
			</div></li>
		<li><a href="#" class="nav_item"><span>|</span></a></li>
		<li><a href="<%=path%>/user_register.jsp" class="btn">注册</a>
		</li>
		
		<li><s:if test="#session.user!=null">
				<!-- 如果用户已经登录 -->
				<a href="#" class="btn username"><s:property
						value="#session.user.username" /></a>
				<div class="down">
					<a href="<%=path%>/find-note.jsp">我的云笔记</a>
					<a href="<%=path%>/write-note.jsp">写笔记</a>
					<a href="<%=path%>/user-info.jsp">个人中心</a>
					<a href="http://haha.sogou.com/">看笑话</a>
					<a href="<%=path%>/user/logout.action">退出</a>
				</div>
			</s:if> <s:else>
				<!-- 用户还没有登录 -->
				<a href="<%=path%>/user_login.jsp" class="btn">登录</a>
			</s:else></li>
			
		<li><a href="collect-note.jsp" class="btn">收藏</a></li>
	</ul>
</div>
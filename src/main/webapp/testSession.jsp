<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.ztz.entity.User" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>testSession.jsp</title>
  </head>
  <body>
  	<%
  		User user = (User)session.getAttribute("user");
  		System.out.println(null==user?"user为空":"user不为空");
  	%>
	  
  </body>
</html>

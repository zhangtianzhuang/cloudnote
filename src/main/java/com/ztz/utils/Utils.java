package com.ztz.utils;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ztz.entity.User;


public class Utils {
	
	public static HttpSession getHttpSession(){
		return  ServletActionContext.getRequest().getSession();
	}
	
	public static User getCurrentUser(){
		return (User) getHttpSession().getAttribute("user");
	}
	
	
}

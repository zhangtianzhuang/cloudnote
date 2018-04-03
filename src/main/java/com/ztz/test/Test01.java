package com.ztz.test;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ztz.entity.User;

public class Test01 {

	public static void main(String[] args) {
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User) session.getAttribute("user");
		System.out.println(user==null?"user为空":"user不为空");
	}
}

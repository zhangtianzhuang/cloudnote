package com.ztz.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.ztz.entity.User;
import com.ztz.service.UserService;


@Controller("userAction")
public class UserAction {

	private User user;

	@Resource(name="userServiceImpl")
	private UserService userService ;

	//注册
	public String registerUser() {
		//此用户名还没有被注册
		if (userService.register(user)==0) { //注册成功
			System.out.println("注册成功了***********");
			ActionContext.getContext().getValueStack().setValue("#request.register_success_username", user.getUsername());
			return "success";
		}
		if(userService.register(user)==-1) { // 已经被注册了
			System.out.println("该账号已经被注册了***********");
			return "hasRegister" ;
		}
		return "failure" ;
	}

	public String findUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int userid = Integer.parseInt(request.getParameter("userid"));
		if(userid==0){
			return null ;
		}
		User getUser = userService.findUserById(userid);
		System.out.println("查询到该用户信息："+getUser.toString());
		HttpSession session = request.getSession();
		session.setAttribute("noteuser", getUser);
		return "success" ;
	}

	//登录
	public String loginUser() {
		if(userService.login(user)==0){
			System.out.println("-------------登录成功");
			return "success";
		}
		if(userService.login(user)==-2){
			return "nouser";
		}else{
			return "failure";
		}
	}
	//退出
	public String logoutUser(){
		System.out.println("当前用户要退出了***************");
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user",null);
		return "success" ;
	}
	//修改信息
	public String motifyUser(){
		System.out.println("修改用户信息：--->  "+user.toString());
		userService.motify(user);
		return "success" ;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}

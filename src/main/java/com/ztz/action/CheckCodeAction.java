package com.ztz.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class CheckCodeAction implements Action{

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain" +  ";charset=utf-8");
		PrintWriter write = response.getWriter();

		String getCode = (String) session.getAttribute("checkCode");
		String code = request.getParameter("code") ;

		System.out.println("-------打印验证码："+ code);

		if(code.trim().equalsIgnoreCase(getCode)){
			System.out.println("验证码正确");
			write.print("code-success");
		}else{
			System.out.println("验证码错误");
			write.print("code-error");
		}
		return null ;
	}
}
package com.ztz.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.ztz.entity.Email;
import com.ztz.utils.MailSender;
import com.ztz.utils.VerifyCodeUtils;

public class CheckMailAction implements Action {

	private String title ;
	private String email ;
	private String content ;

	@Override
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String code = VerifyCodeUtils.generateVerifyCode(4); //获取随机验证码

		String mail = request.getParameter("mail");
		session.setAttribute("mailCode",code); //向session保存验证码

		MailSender mailSender = MailSender.getMailSender();
		Email email = new Email();
		email.setMailHost("smtp.163.com");
		email.setMailHostPort("994");
		email.setUserName("18638790724@163.com");
		email.setPassword("2017zhc");
		email.setFrom("18638790724@163.com");
		email.setTitle("爱分享云笔记平台");
		email.setContent("您的验证码是："+code);
		email.setTo(mail);
		mailSender.sendAEmail(email); // 发送邮件
		return null;
	}

	public String checkMail() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/plain" +  ";charset=utf-8");
		PrintWriter write = response.getWriter();
		HttpSession session = ServletActionContext.getRequest().getSession();
		String code = (String) session.getAttribute("mailCode");
		String getCode = request.getParameter("mailCode");
		if(code.equalsIgnoreCase(getCode)){ //验证码正确
			write.print("success");
		}else{ //错误
			write.print("error");
		}
		return null ;
	}
	public String sendMail(){
		MailSender mailSender = MailSender.getMailSender();
		Email email = new Email();
		email.setMailHost("smtp.163.com");
		email.setMailHostPort("994");
		email.setUserName("18638790724@163.com");
		email.setPassword("2017zhc");
		email.setFrom("18638790724@163.com");
		email.setTo("874102849@qq.com");

		email.setTitle("来自"+email+"的反馈");
		email.setContent("标题："+title+"\n内容："+content);
		mailSender.sendAEmail(email); // 发送邮件

		email.setTo(this.email);
		email.setTitle("来自爱分享云笔记平台的邮件");
		email.setContent("尊敬的"+this.email+"用户，您好，我是爱分享云笔记平台的小助手，很高兴为您服务\n"+
				"非常感谢您提出的意见，我们为及时处理您的反馈，将会在24小时之内以邮件的方式给您答复，请注意查收");
		mailSender.sendAEmail(email);
		return "success" ;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
/*	
	@Test
	public void test(){
		String code = VerifyCodeUtils.generateVerifyCode(6);
		System.out.println(code);
	}
	
*/}

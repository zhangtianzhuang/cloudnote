package com.ztz.intercept;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.ztz.entity.User;
import com.ztz.utils.Utils;

public class CheckLoginInterceptor implements Interceptor{

	/**
	 */
	private static final long serialVersionUID = 44086509692154414L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation ai) throws Exception {
		HttpSession hss = Utils.getHttpSession();
		User user = (User) hss.getAttribute("user");
		if(user!=null){ //已经登录
			return ai.invoke(); //放行,验证通过
		}
		return "login" ; //验证不通过
	}

}

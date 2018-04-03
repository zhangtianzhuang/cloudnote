package com.ztz.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.ValueStack;
import com.ztz.utils.VerifyCodeUtils;

public class CreateImageAction extends ActionSupport{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private ByteArrayInputStream inputStream;

    public ByteArrayInputStream getInputStream(){
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream){
        this.inputStream = inputStream;
    }

    @Override
    public String execute() throws Exception{

        HttpServletResponse response = ServletActionContext.getResponse();
        // 设置浏览器不要缓存此图片
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int w = 100, h = 40;

        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        VerifyCodeUtils.outputImage(w, h, outputStream, verifyCode);

        ByteArrayInputStream input = new ByteArrayInputStream(outputStream
                .toByteArray());
        this.setInputStream(input);

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("checkCode",verifyCode);

        input.close();
        outputStream.close();
        return "success";
    }
}
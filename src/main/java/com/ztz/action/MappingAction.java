package com.ztz.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

import org.springframework.stereotype.Controller;

import com.ztz.utils.VerifyCodeUtils;

@Controller("mappingAction")
public class MappingAction {
	
	private ByteArrayOutputStream out ;
	
	public String mapping() throws Exception{
		
		out = new ByteArrayOutputStream();
		int w = 200, h = 80;
    	String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
    	// 
    	// session.
    	
        VerifyCodeUtils.outputImage(w, h, out, verifyCode);
        
		return "success";
		
		
	}
}

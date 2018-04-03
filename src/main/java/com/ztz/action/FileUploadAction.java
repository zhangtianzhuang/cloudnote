package com.ztz.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;

@Controller("fileUploadAction")
public class FileUploadAction {

//	private String username;
//	// 注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
//	private File file;
//	// 提交过来的file的名字
//	private String fileFileName;
//	// 提交过来的file的MIME类型
//	private String fileContentType;

	private String color ;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String uploadFile() throws Exception{

		System.out.println("---------> color ;" + color);

		/*
		HttpServletRequest request = ServletActionContext.getRequest() ;
//		String path = request.getContextPath();
//		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
//		String root = basePath + "/upload" + "/" + username;
//		System.out.println("basePath-->"+basePath);

		String root = "E:/upload"+"/" + username;
		checkPath(root);//检查路径

		InputStream is = new FileInputStream(file);
		System.out.println("fileFileName: " + fileFileName);
		//获取文件后缀名
		String prefix=fileFileName.substring(fileFileName.lastIndexOf(".")+1);
		System.out.println("------文件后缀名为："+prefix);
		OutputStream os = new FileOutputStream(new File(root,fileFileName));
		// 因为file是存放在临时文件夹的文件，我们可以将其文件名和文件路径打印出来，看和之前的fileFileName是否相同
		System.out.println("----------file.getName(): " + file.getName());
		System.out.println("----------file.getPath(): " + file.getPath());
		byte[] buffer = new byte[500];
		int length = 0;
		while (-1 != (length = is.read(buffer, 0, buffer.length))) {
			os.write(buffer);
		}
		os.close();
		is.close();*/
		return "success";
	}

	//检查路径是否存在，不存在创建一个
	private void checkPath(String path){
		File file = new File(path);
		if(!file.exists()){ //如果路径不存在
			file.mkdirs();
		}
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public File getFile() {
//		return file;
//	}
//
//	public void setFile(File file) {
//		this.file = file;
//	}
//
//	public String getFileFileName() {
//		return fileFileName;
//	}
//
//	public void setFileFileName(String fileFileName) {
//		this.fileFileName = fileFileName;
//	}
//
//	public String getFileContentType() {
//		return fileContentType;
//	}
//
//	public void setFileContentType(String fileContentType) {
//		this.fileContentType = fileContentType;
//	}

}

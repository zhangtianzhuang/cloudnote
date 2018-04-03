package com.ztz.service;

import com.ztz.entity.User;

public interface UserService {
	//注册
	public int register(User user);
	//登录
	public int login(User user);
	//修改用户信息
	public int motify(User user);
	//退出
	public int exit(User user);
	//查询用户
	public User findUserById(int id);
}

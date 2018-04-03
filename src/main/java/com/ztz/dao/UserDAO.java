package com.ztz.dao;

import com.ztz.entity.User;

public interface UserDAO extends BaseDAO<User>{

	public User findByUserName(String username);
	
	public void update(User user);
}

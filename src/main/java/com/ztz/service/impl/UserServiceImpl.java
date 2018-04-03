package com.ztz.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.ztz.dao.UserDAO;
import com.ztz.entity.User;
import com.ztz.service.UserService;
import com.ztz.utils.DateUtils;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

	@Resource(name="userDAOImpl")
	private UserDAO userDAO ;

	@Override
	public int register(User user) {
		User u = userDAO.findByUserName(user.getUsername());
		if(u==null){
			System.out.println("-----该用户名没有被注册-----");
			user.setRegisterDate(DateUtils.getDate()); //设置注册时间
			if(user.getUsername().contains("@")){ //如果用户名中包含@，将其设置为邮箱
				user.setEmail(user.getUsername());
			}
			userDAO.add(user);
			System.out.println("-------注册成功--用户名："+user.getUsername()+"-------");
			return 0 ;
		}else{
			System.out.println("--------该用户名已经被注册---------");
			return -1;
		}
	}
	/**
	 *  用户名不存在 -2
	 *  密码不正确 -1
	 *  登录成功 0
	 */
	@Override
	public int login(User user) {
		User u = userDAO.findByUserName(user.getUsername());
		if(u==null){ //用户不存在
			System.out.println("----------用户名不存在");
			return -2 ;
		}else{
			if(!u.getPassword().equals(user.getPassword())){ //密码不正确
				System.out.println("---------密码不正确！----------");
				return -1 ;
			}
			if(user.getPassword().equals(u.getPassword())){//用户存在,登录成功
				HttpSession session = ServletActionContext.getRequest().getSession();
				System.out.println("向sesion中存User对象--------->"+u);
				session.setAttribute("user",u);
				return 0 ;
			}
		}
		return -1;
	}

	public User findUserById(int id){
		return userDAO.findById(id);
	}

	@Override
	public int motify(User user) {
		userDAO.motify(user);
		//修改成功了
		//重新向session中保存user
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute("user");
		session.setAttribute("user", user);
		return 0;
	}

	@Override
	public int exit(User user) {
		return 0;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}

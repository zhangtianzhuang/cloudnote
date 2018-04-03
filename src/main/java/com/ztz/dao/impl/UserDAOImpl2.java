package com.ztz.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.ztz.dao.UserDAO;
import com.ztz.entity.User;

@Repository("userDAOImpl")
public class UserDAOImpl2 extends BaseDAOImpl<User> implements UserDAO{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public UserDAOImpl2(){
		super(User.class);
	}
	
	public UserDAOImpl2(Class<?> c) {
		super(User.class);
	}
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User findByUserName(String username) {
		String hql = "from User u where u.username = ?" ;
		Query query = getCurrentSession().createQuery(hql);
		query.setString(0, username);
		List<User> list = query.list();
		if(list.size()==0){
			return null ;
		}
		return list.get(0);
	}

	@Override
	public void update(User user) {
		getCurrentSession().update(user);
	}
}

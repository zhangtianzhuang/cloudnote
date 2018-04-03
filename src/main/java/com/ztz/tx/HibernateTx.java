package com.ztz.tx;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateTx implements MethodInterceptor{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory ;

	@Override
	public Object invoke(MethodInvocation mi) throws Throwable {
		//开始事务
		Session session = sessionFactory.getCurrentSession();
		if(session==null){
			System.out.println("session为空");
			session = sessionFactory.openSession();
		}
		Transaction tx = session.beginTransaction(); //开始事务
		Object ret = null ;
		try{
			System.out.println("-------------------数据库事务已开启---------------------");
			ret = mi.proceed();
			tx.commit(); //提交事务
			System.out.println("-------------------数据库事务已提交---------------------");
		}catch(Exception e){
			tx.rollback();
		}
		return ret ;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionfactory) {
		this.sessionFactory = sessionfactory;
	}
}

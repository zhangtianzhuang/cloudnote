package com.ztz.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ztz.dao.BaseDAO;

@Repository("baseDAOImpl")
public class BaseDAOImpl<T> implements BaseDAO<T> {

	private Class<?> clazz;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public BaseDAOImpl(){
	}

	public BaseDAOImpl(Class<?> c) {
		// 目的：得到实际类型参数
		// 得到当前运行对象
		Class<?> clazz = this.getClass();
		// 得到当前对象父类的参数化类型,一般使用type子接口ParameterizedType
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		// 得到实际类型参数
		Type[] types = ptype.getActualTypeArguments();
		Class<?> clazzParameter = (Class<?>) types[0];
		this.clazz = clazzParameter;
	}

	@Override
	public void add(T t) {
		Session session = getSession();
		session.save(t);
	}

	@Override
	public void motify(T t) {
		getSession().merge(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public void delete(int id) {
		String simpleClass = clazz.getSimpleName();
		String hql = "delete " + simpleClass + " where id=:id";
		getSession().createQuery(hql).setInteger("id", id).executeUpdate();

	}

	@Override
	public T findById(Serializable id) {
		return (T)getSession().get(clazz,id);
	}

	//使用hql方式分页查询

	/**
	 *  pageIndex  页码索引，从1开始
	 *  pageSize   每页数量
	 *  hql 	      查询条件
	 */
	@Override
	public List<T> findByPage(int pageIndex, int pageSize,String hql){
		Query query = getSession().createQuery(hql);
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}

	//分页查询所有
	@Override
	public List<T> findByPage(int pageIndex, int pageSize) {
		return findByPage(pageIndex, pageSize, " from " + clazz.getSimpleName());
	}

	//在子类中可以使用
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public int getCounts(String hql) {
		Query query = getSession().createQuery(hql);
		return Integer.parseInt(query.list().get(0).toString());
	}
}
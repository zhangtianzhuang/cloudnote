package com.ztz.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T> {
	//添加
	public void add(T t);
	//修改
	public void motify(T t);
	//删除
	public void delete(T t);
	//删除
	public void delete(int id);
	//查找
	public T findById(Serializable id);
	//分页查找
	public List<T> findByPage(int pageIndex, int pageSize, String hql);
	//无条件的分页查询，pageIndex: 页码，默认第一页为1 ,pageSize: 每页的最大数量
	public List<T> findByPage(int pageIndex, int pageSize);

	public int getCounts(String hql);

}

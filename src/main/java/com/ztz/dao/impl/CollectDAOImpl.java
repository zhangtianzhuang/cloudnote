package com.ztz.dao.impl;

import org.springframework.stereotype.Repository;

import com.ztz.dao.CollectDAO;
import com.ztz.entity.Collect;

@Repository("collectDAOImpl")
public class CollectDAOImpl extends BaseDAOImpl<Collect> implements CollectDAO{
	public CollectDAOImpl(){
		super(Collect.class);
	}
}

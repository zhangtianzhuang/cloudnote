package com.ztz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ztz.dao.CollectDAO;
import com.ztz.entity.Collect;
import com.ztz.service.CollectService;

@Service("collectServiceImpl")
public class CollectServiceImpl implements CollectService{

	@Resource(name="collectDAOImpl")
	private CollectDAO collectDAOImpl ;

	@Override
	public void collect(Collect collect) {
		collectDAOImpl.add(collect);
	}

	@Override
	public void unCollect(Collect collect) {
		collectDAOImpl.delete(collect);
	}

	@Override
	public boolean isCollect(Collect collect) {
		List<Collect> list = collectDAOImpl.findByPage(1,5,"from Collect c where c.u_id="+collect.getU_id()+" and c.n_id="+collect.getN_id());
		if(list.size()==0){
			//未收藏
			System.out.println("------未收藏");
			return false ;
		}else{
			System.out.println("------已收藏");
			return true ;
		}
	}
}

package com.ztz.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ztz.dao.LevelDAO;
import com.ztz.entity.LevelWord;
import com.ztz.service.LevelWordService;

@Service("levelWordServiceImpl")
public class LevelWordServiceImpl implements LevelWordService {

	@Resource(name="levelDAOImpl")
	private LevelDAO levelDAOImpl ;
	
	@Override
	public int insert(LevelWord word) {
		System.out.println("--------留言：------- :"+word.getContent());
		levelDAOImpl.add(word);
		return 0 ;
	}

	@Override
	public int update(LevelWord word) {
		return 0;
	}

	@Override
	public int delete(int id) {
		return 0;
	}

	@Override
	public int delete(LevelWord word) {
		return 0;
	}

	@Override
	public LevelWord select(LevelWord word) {
		return null;
	}

	@Override
	public List<LevelWord> select(int id) {
		return levelDAOImpl.findByPage(0, 20,"from LevelWord lw where lw.note.id = " + id + " order by lw.id desc ");
	}

}

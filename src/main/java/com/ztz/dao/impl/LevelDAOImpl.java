package com.ztz.dao.impl;

import org.springframework.stereotype.Repository;

import com.ztz.dao.LevelDAO;
import com.ztz.entity.LevelWord;

@Repository("levelDAOImpl")
public class LevelDAOImpl extends BaseDAOImpl<LevelWord> implements LevelDAO{
	
	public LevelDAOImpl(){
		super(LevelWord.class);
	}
	
}

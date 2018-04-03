package com.ztz.service;

import java.util.List;

import com.ztz.entity.LevelWord;

public interface LevelWordService {
	//留言
	public int insert(LevelWord word);
	//修改
	public int update(LevelWord word);
	//删除通过id
	public int delete(int id);
	//删除
	public int delete(LevelWord word);
	//查找
	public LevelWord select(LevelWord word);
	//查找
	public List<LevelWord> select(int noteId);
}

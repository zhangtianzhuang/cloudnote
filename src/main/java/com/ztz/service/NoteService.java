package com.ztz.service;

import java.util.List;

import com.ztz.entity.Note;

public interface NoteService {
	//发布云笔记
	public int publishNote(Note note);
	//修改云笔记
	public int motifyNote(Note note);
	//删除云笔记
	public int deleteNote(Note note);
	//收藏云笔记
	public int collectNote(Note note);
	//分享云笔记
	public int shareNote(Note note);
	//查询云笔记
	public List<Note> select(int pageIndex,int pageSize,String hql);
	//查询一个云笔记，通过id
	public Note select(int id);
}

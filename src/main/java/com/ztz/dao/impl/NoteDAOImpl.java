package com.ztz.dao.impl;

//import javax.annotation.Resource;

//import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ztz.dao.NoteDAO;
import com.ztz.entity.Note;

@Repository("noteDAOImpl")
public class NoteDAOImpl extends BaseDAOImpl<Note> implements NoteDAO{
	 
//	@Resource(name="sessionFactory")
//	private SessionFactory sessionFactory;
	
	public NoteDAOImpl(){
		super(Note.class);
	}	
}
